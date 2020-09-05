package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.service.IRodata_ScanTTHService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_ScanTTHImpl implements IRodata_ScanTTHService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	private IGetArticleForBarcodeService getArticleForBarcodeImpl;

	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
	
	//获取下架单号
	@Override
	public List<ComboxBo> getRodata_ScanM(
			String enterpriseNo,String warehouseNo, String workerOwner, String strQuery,
			Integer start, Integer limit) {
		String sql="select a.outstock_no as value," +
				"a.outstock_no as text," +
				"a.outstock_no dropValue " +
			"from " +
				"rodata_outstock_m a " +		
			"where " +
				" a.status = '11' "+
				"and a.task_type='1' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_no='"+warehouseNo+"' ";
		
		if(strQuery!=null && strQuery!=""){
		    sql+=" and a.outstock_no = '"+strQuery+"' ";
		}
	    sql+=" order by a.outstock_no desc " ;
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
	   	return (List<ComboxBo>) list;
}
	
	//取未扫描商品信息
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_ScanD(
			String strQuery, Integer start, Integer limit) throws Exception {
		String sql="";
		String sql1= "SELECT DISTINCT V.*, "+
       " RRM.Supplier_No, "+
        "RRM.po_no, "+
       " BDA.OWNER_ARTICLE_NO, "+
        "BDA.ARTICLE_NAME, "+
        "BDA.ARTICLE_ENAME, "+
        "BDA.ARTICLE_IDENTIFIER, "+
        "BDA.ARTICLE_ONAME, "+
        "BDA.ARTICLE_ALIAS, "+
        "BDA.QMIN_OPERATE_PACKING, BDA.Barcode  "+
    "FROM "+
        "(SELECT "+
            "A.enterprise_no, "+
            "A.warehouse_no, "+
            "A.OWNER_NO, "+
            "A.OUTSTOCK_NO, "+
            "A.SOURCE_NO, "+
            "A.D_CELL_NO, "+
            "A.PACKING_QTY, "+
           " D.Article_No, "+
            "(SUM(A.OUTSTOCK_QTY)-sum(a.real_qty))/A.PACKING_QTY as OUTSTOCK_QTY, "+
            "(sum(a.article_qty)/ a.packing_qty) plan_wholeNum, "+
            "sum(A.REAL_QTY) as REAL_QTY "+
        "FROM "+
            "RODATA_OUTSTOCK_D A    "+
        "INNER JOIN "+
            "STOCK_ARTICLE_INFO D    "+
                "ON A.ARTICLE_NO = D.ARTICLE_NO   "+ 
                "AND A.ARTICLE_ID = D.ARTICLE_ID  "+  
               "AND A.ENTERPRISE_NO = D.ENTERPRISE_NO   "+  
       " WHERE "+
            "a.label_no='N'  "+
            "and A.OUTSTOCK_QTY-a.real_qty>0 ";
String sql2= "  GROUP BY "+
           " A.ENTERPRISE_NO, "+
            "A.warehouse_no, "+
            "A.OWNER_NO, "+
           " A.OUTSTOCK_NO, "+
           " A.SOURCE_NO, "+
            "A.D_CELL_NO, "+
            "A.PACKING_QTY, "+
            "D.Article_No) V  "+  
    "INNER JOIN "+
        "bdef_defarticle BDA   "+  
            "ON V.ARTICLE_NO = BDA.ARTICLE_NO   "+ 
            "AND V.enterprise_no = BDA.enterprise_no  "+ 
    "LEFT JOIN "+
            "RODATA_RECEDE_M RRM   "+  
                "ON RRM.warehouse_no = V.warehouse_no  "+  
                "AND RRM.ENTERPRISE_NO = V.ENTERPRISE_NO  "+ 
                "AND RRM.RECEDE_NO = V.SOURCE_NO   "+ 
    "ORDER BY "+
        "v.article_no ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql1+ws+sql2;
		}else{
			sql=sql1+sql2;
		}
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
	  	return extListBo;
	}
	
	//校验商品
	@Override
	public MsgRes checkArticleNo(String enterpriseNo,String barcode, String outstockNo,String warehouseNo)
			throws Exception {
		MsgRes msg=null;
		msg=getArticleForBarcodeImpl.checkBarcode(warehouseNo, barcode,enterpriseNo);
		if(msg.getIsSucc()){
			String sql="select a.owner_no,a.article_no ,bdf.article_name from rodata_outstock_d a ,bdef_defarticle bdf "+
				       "where a.outstock_no = '"+outstockNo+"' "+
				       " and a.warehouse_no='"+warehouseNo+"' " +
				       " and a.enterprise_no='"+enterpriseNo+"' " +
				       " and a.article_no=bdf.article_no " +
				       " and a.enterprise_no=bdf.enterprise_no " +
				       " and a.outstock_qty<>0 "+			       
				       " and bdf.article_no="+(msg.getObj().toString())+" ";		

			List<String> list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				msg.setObj(list);
				msg.setIsSucc(true);
				msg.setMsg("成功");
			}else{
				msg.setIsSucc(false);
				msg.setMsg("该商品不在此张表内或已验完");
			}
		}else{
			msg.setIsSucc(false);
			msg.setMsg("该条码不存在");
		}
		return msg;
	
	}
	//////////////////////////////////////////////////////////////////////
	//扫描商品
	@Override
	public MsgRes saveOutstock(String enterpriseNo,String articleNo, String outstockNo,
			String workerNo, String workSpaceNo, String warehouseNo,String labelNo)
			throws Exception {
		String sql="select * from  rodata_outstock_d a " +
				   " where a.outstock_no='"+outstockNo+
				   "' and a.article_no='"+articleNo+
				   "' and a.warehouse_no='"+warehouseNo+
				   "' and a.enterprise_no='" +enterpriseNo+"' " +
				   "and a.label_no='N' and A.OUTSTOCK_QTY-a.real_qty>0 "+
				   "  order by a.outstock_date";		
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
		
		
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		//int flag=0;
		
		outList.add("S");
		if(list.size()>0){
			if(list.get(0).getRealQty()<list.get(0).getOutstockQty()){
				List  inList=new ArrayList();
		
				inList.add(enterpriseNo);
				inList.add(warehouseNo);
				inList.add(list.get(0).getOwnerNo());
				inList.add(outstockNo);
				inList.add(list.get(0).getSourceNo());
				inList.add(list.get(0).getArticleNo());
				inList.add(labelNo);
				inList.add(list.get(0).getPackingQty());
				inList.add(1*list.get(0).getPackingQty());
				inList.add(workerNo);
				inList.add(workSpaceNo);
				resultList=genDao.execProc(inList, outList, "pklg_rodata.P_PO_ScanOutstock");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
				
			}
			return new MsgRes(true,"",null);
		}else{
			return new MsgRes(false,"",null);
		}
		
	}
	
	//扫描完成自动回单
	@Override
	public MsgRes save(String enterpriseNo,String outstockNo, String workerNo, String workSpaceNo,
			String warehouseNo, String ownerNo, String scanNo) throws Exception {
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		List  inList=new ArrayList();
		outList.add("S");

		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(outstockNo);
		inList.add(workerNo);
		inList.add(scanNo);
		inList.add(workSpaceNo);
		
		
		resultList=genDao.execProc(inList, outList, "pklg_rodata.P_PO_ScanSaveComfire");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",null);
	}
	
	//取标签号
	@Override
	public MsgRes getLoadBox(String enterpriseNo,String warehouseNo, String scanNo)
			throws Exception {
		    List  outList=new ArrayList();
			List  resultList=new ArrayList();
			List  inList=new ArrayList();
			outList.add("S");
			outList.add("S");
			outList.add("S");
			outList.add("S");
				
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add("P");
			inList.add(scanNo);
			inList.add("D");
			inList.add(1);
			inList.add("2");
			inList.add("31");
			resultList=genDao.execProc(inList, outList, "pklg_wms_base.p_get_containernobase");
			if(resultList.get(3).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(3).toString());
			}
			
			return new MsgRes(true,resultList.get(0).toString(),null);
	}
	
	//封箱
	@Override
	public MsgRes closeBox(String enterpriseNo,String workSpaceNo, String warehouseNo,
			String ownerNo, String scanNo, String labelNo) throws Exception {
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		List  inList=new ArrayList();
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(labelNo);
		inList.add(scanNo);
		inList.add(workSpaceNo);
		resultList=genDao.execProc(inList, outList, "pklg_rodata.P_RO_ScanCloseBox");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",null);
	}
	
	//获取标签列表
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getScanPackLabel(
			String enterpriseNo,String warehouseNo,
			String strQuery, Integer start, Integer limit) throws Exception {
		String sql ="select distinct d.outstock_no,d.LABEL_NO,d.status,d.owner_no," +
				"f_get_fieldtext('RODATA_OUTSTOCK_D','STATUS',d.status) as statusText " +
				"from rodata_outstock_d d " +
				"where d.enterprise_no='"+enterpriseNo+"' " +
				"and d.warehouse_no='"+warehouseNo+"' " +
				"and d.outstock_no='"+strQuery+"' and d.label_no<>'N' ";
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
		return extListBo;
	}
	
	//获取已扫描商品信息
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_ScanDNot(
			String strQuery, Integer start, Integer limit) throws Exception {
		String sql="";
		String sql1= "SELECT DISTINCT V.*,       "    
			+" BDA.OWNER_ARTICLE_NO,  "   
			+" BDA.ARTICLE_NAME,  "   
			+" BDA.ARTICLE_ENAME,  "   
			+" BDA.ARTICLE_IDENTIFIER,  "   
			+" BDA.ARTICLE_ONAME,  "   
			+" BDA.ARTICLE_ALIAS,  "   
			+" BDA.QMIN_OPERATE_PACKING,  "   
			+" BDA.RULE_FLAG,  "   
			+" BDA.ABC,  "   
			+" BDA.QC_FLAG,  "   
			+" BDA.MEASURE_MODE,  "   
			+" BDA.TEMPERATURE_FLAG,  "   
			+" BDA.VIRTUAL_FLAG,  "   
			+" BDA.SCAN_FLAG,  "   
			+" BDA.DOUBLE_CHECK,  "   
			+" BDA.EXPIRY_DAYS "   
			+" FROM (SELECT A.enterprise_no," 
			+" A.warehouse_no,  "   
			+" A.Label_No, "
			+" A.OWNER_NO,  "   
			+" A.OUTSTOCK_NO,  "   
			+" A.SOURCE_NO,  "   
			+" A.D_CELL_NO,  "   
			+" A.OUTSTOCK_CELL_NO,  "   
			+" A.OUTSTOCK_CONTAINER_NO,  "   
			+" A.PACKING_QTY,  "   
			+" D.Article_No,  "   
			+" RRM.Supplier_No,  "   
			+" D.BARCODE,  "   
			+" RRM.PO_NO,"
			+" SUM(A.OUTSTOCK_QTY) as OUTSTOCK_QTY, " 
			+" (sum(a.article_qty)/ a.packing_qty) plan_wholeNum," 
			+" sum(A.REAL_QTY)/ A.PACKING_QTY as REAL_QTY,  "   
			+" sum(RRD.RECEDE_QTY) RECEDE_QTY  "   
			+" FROM RODATA_OUTSTOCK_D A  "   
			+" INNER JOIN STOCK_ARTICLE_INFO D  "   
			+" ON A.ARTICLE_NO = D.ARTICLE_NO  "   
			+" AND A.ARTICLE_ID = D.ARTICLE_ID  " 
			+" AND A.ENTERPRISE_NO = D.ENTERPRISE_NO "   
			+" INNER JOIN RODATA_RECEDE_D RRD   "   
			+" ON RRD.warehouse_no = A.warehouse_no  "   
			+" AND RRD.RECEDE_NO = A.SOURCE_NO  "   
			+" AND RRD.ARTICLE_NO = A.ARTICLE_NO  " 
			+" AND RRD.ENTERPRISE_NO = A.ENTERPRISE_NO "
			+" LEFT JOIN RODATA_RECEDE_M RRM   "   
			+" ON RRM.warehouse_no = A.warehouse_no  " 
			+" AND RRM.ENTERPRISE_NO = A.ENTERPRISE_NO "
			+" AND RRM.RECEDE_NO = A.SOURCE_NO  "   
			+" WHERE a.label_no<>'N'   ";
String sql2= " GROUP BY A.ENTERPRISE_NO," 
		    +" A.warehouse_no,  " 
			+" A.Label_No,"
			+" A.OWNER_NO,  "   
			+" A.OUTSTOCK_NO,  "   
			+" A.SOURCE_NO,  "   
			+" A.D_CELL_NO,  "    
			+" A.OUTSTOCK_CELL_NO,  "   
			+" A.OUTSTOCK_CONTAINER_NO,  "   
			+" A.PACKING_QTY,  "   
			+" D.BARCODE,  "
			+" D.Article_No,  "    
			+" RRM.Supplier_No," 
			+" RRM.PO_NO) V  "   
			+" INNER JOIN bdef_defarticle BDA   "   
			+" ON V.ARTICLE_NO = BDA.ARTICLE_NO  " 
			+" AND V.enterprise_no = BDA.enterprise_no "     
			+" ORDER BY V.Label_No";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql1+ws+sql2;
		}else{
			sql=sql1+sql2;
		}
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
	  	return extListBo;
	}
	//检验该下架单是否扫描完成
	@Override
	public MsgRes checkIsFinish(String enterpriseNo,
			String outstockNo, String WarehouseNo) throws Exception {
		String sql="select * from rodata_outstock_d a "+
			       "where a.outstock_no = '"+outstockNo+"' "+
			       " and a.warehouse_no='"+WarehouseNo+"' " +
			       " and a.enterprise_no='"+enterpriseNo+"' " +
			       "and a.real_qty <> a.outstock_qty ";
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
		if(list.size()==0){
			return new MsgRes(true,"",null);
		}else{
			return new MsgRes(false,"",null);
		}
		
	}
	
	//取标签的汇总扫描数量
	@Override
	public MsgRes tscRodataBoxQty(String currEnterpriseNo, String warehouseNo,
			String labelNo,String outstockNo) throws Exception {
        String sql ="select sum(d.real_qty)/A.PACKING_QTY " +
        		"from rodata_outstock_d d " +
        		"where d.label_no='"+labelNo+"' " +
                "and d.outstock_no='"+outstockNo+"'  " +
        		"and d.enterprise_no='"+currEnterpriseNo+"' " +
        		"and d.warehouse_no='"+warehouseNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		return new MsgRes(true,"操作成功！",list.get(0));	
	}
}
