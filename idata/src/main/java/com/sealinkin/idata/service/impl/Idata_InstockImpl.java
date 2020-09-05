package com.sealinkin.idata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.ICheckCellService;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_InstockDModel;
import com.sealinkin.idata.model.Idata_InstockMModel;
import com.sealinkin.idata.po.Idata_InstockM;
import com.sealinkin.idata.service.Iidata_InstockService;
import com.sealinkin.protocolExchange.comm.CommSingleDataAnswerModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.idata.BoxBarcodeRequestModel;
import com.sealinkin.protocolExchange.idata.BoxCodeCellAnswerModel;
import com.sealinkin.protocolExchange.idata.BoxCodeOkRequestModel;
import com.sealinkin.protocolExchange.idata.IdataInStockBarcodeRequestModel;
import com.sealinkin.protocolExchange.idata.IdataInStockLabelAnswerModel;
import com.sealinkin.protocolExchange.idata.IdataInStockLabelRequestModel;
import com.sealinkin.protocolExchange.idata.IdataInStockRequestModel;
import com.sealinkin.protocolExchange.idata.IdataInstockLableOrSerialNoAnswerModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.TipUtil;

/*
 *  @上架回单实现类
 *  @author 周欢
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Idata_InstockImpl implements Iidata_InstockService {
	
    
	private IGenericManager genDao;
	private ICheckCellService checkCellImpl;     
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
		
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao  =  genDao;
    }
    public ICheckCellService getCheckCellImpl() {
		return checkCellImpl;
	}
	public void setCheckCellImpl(ICheckCellService checkCellImpl) {
		this.checkCellImpl = checkCellImpl;
	}
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}	
	/**
     * 获得上架单头档
     * strFlag=1:非历史单据
     * strFlag=2:历史单据
     */
	public ExtListDataBo<Idata_InstockMModel> getInstockMList(  String enterpriseNo,
																String strWarehouseNo,
																String strOwnerNo,
																PageBo pageBo,
																String strFlag,
																String strQuery) 
	{//instockMbody
		String strsql = "select  distinct  a.WAREHOUSE_NO," +
				"a.INSTOCK_NO,a.STATUS,"  + 
				"a.DISPATCH_WORKER," +
				"a.DISPATCH_DATE," +
				"a.INSTOCK_WORKER,"  + 
				"a.INSTOCK_DATE," +
				"a.OPERATE_TYPE," +
				"a.LOCATE_TYPE," +
				"a.OWNER_NO,"  + 
				"a.CONTAINER_LOCATE_FLAG," +
				"a.RGST_NAME," +
				"a.RGST_DATE," +
				"a.UPDT_NAME,"  + 
				"a.UPDT_DATE," +
				"f_get_fieldtext('N','STATUS',a.status) statusText ";		
		//工作表or历史表
        if(strFlag.equals("1"))
        {
        	strsql  +=  "from Idata_Instock_M a ,idata_instock_d d,bdef_defarticle e " +
        			"where a.WAREHOUSE_NO = '" +strWarehouseNo+"' " +
        					"and a.enterprise_no=d.enterprise_no " +
        					"and a.warehouse_no=d.warehouse_no " +
        					"and a.instock_no=d.instock_no " +
        					"and d.enterprise_no=e.enterprise_no " +
        					"and d.article_no=e.article_no" +
        			"  and a.enterprise_no = '"+enterpriseNo+"' "+
        			" %s1 "  ;		
        }else
        {
        	strsql  +=  "from Idata_Instock_MHTY a " +
        			"where a.WAREHOUSE_NO = '" + strWarehouseNo + "' %s1 "  ;		
        }
        //货主权限
  		if(strOwnerNo!=null && !strOwnerNo.equals(""))
  		{
  			strsql+=" and a.owner_no in("+strOwnerNo+") ";
  		}else{
  			strsql+=" and 1=2";
  		}
        
        //查询条件（货主、日期等）
        if(strQuery != null && !strQuery.equals(""))
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(strQuery);
			strsql = strsql.replace("%s1", ws);
		}else
		{
			strsql = strsql.replace("%s1", "");
		}
		strsql = strsql+" order by a.INSTOCK_NO desc";
		List<Idata_InstockMModel> list = genDao.getListByNativeSql(
				strsql,
				Idata_InstockMModel.class, 
				pageBo.getStart(), 
				10000);
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+strsql+")");
		ExtListDataBo<Idata_InstockMModel> extListBo=new ExtListDataBo<Idata_InstockMModel>(list,count);
		return extListBo;
	}//instockMbody
	
	/**
	 * 上架单明细
	 * @throws Exception 
	 */
	public ExtListDataBo<Idata_InstockDModel> getInstockDList(String enterpriseNo,String strWarehouseNo,String strOwner,String str,
			String strFlag, PageBo pageBo) throws Exception 
	{
		//String strFindAttSql1  =  getArticleAttrString("1").get(0);//调用方法
		//String strFindAttSql2  =  getArticleAttrString("2").get(0);
		//String strSql = "";
		//String strSql1 = "";
		//String strSql2 = "";
		//String strSql3 = "";
		//String strSql4 = "";
		//String strSql5 = "";
		//String strSql6 = "";
		String strTotsql = "";
		/*strSql1 = "select " 
				 + "iim.warehouse_no,"
				 + "iim.operate_type,"
				 + "iim.CONTAINER_LOCATE_FLAG,"
				 + "bda.owner_article_no,"
				 + "bda.article_name,"
				 //+ "nvl(bap.packing_unit, decode(iid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"
				 + "f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingUnit,"
				 + "f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingUnitQmin,"
				 + "f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) Unit,"
				 + "f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingSpec,"
				 + "f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingSpecQmin,"
				 + "f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) spec,"
				 + "bda.barcode,bda.QMIN_OPERATE_PACKING,bda.unit_packing,"
				 + "  trunc(iid.article_qty/iid.packing_qty) as planBox,"
	             + "  trunc(mod(iid.article_qty,iid.packing_qty)/bda.QMIN_OPERATE_PACKING) as planQmin,"
			     + "   mod(iid.article_qty,bda.QMIN_OPERATE_PACKING) as planDis,"
				 + "iid.* " 
				 + " from ";
		
		if(strFlag.equals("1"))
		{
			strSql2 =  "Idata_Instock_M   iim,";
		}else
		{
			strSql2 =  "Idata_Instock_Mhty   iim,";
		}
		
		strSql3 =  "bdef_article_packing bap,"
				 + "bdef_defarticle bda," 
				 + "(select  " 
				 + " iid.enterprise_no," 
				 + "iid.auto_locate_flag," 
				 + "iid.label_no,"
				 + "iid.article_no,"
				 + "iid.instock_no,"
				 + "iid.dest_cell_no,"
				 + "iid.cell_no,"
				 + "iid.real_cell_no,"
				 + "iid.business_type,"
				 + "iid.packing_qty," 
				 + "iid.owner_no,"
				 + "count(distinct iid.article_no) as articleCount," 
				 + "sum(iid.article_qty) as article_qty," 
				 + " sum(iid.REAL_QTY) as REAL_QTY  ";
		strSql4 =  " group by    "
				 + "iid.enterprise_no,"
				 + "iid.label_no,"
				 + "iid.instock_no,"
				 + "iid.auto_locate_flag,"
				 + "iid.label_no,"
				 + "iid.article_no,"
				 + "iid.dest_cell_no,"
				 + "iid.cell_no,"
				 + "iid.real_cell_no,"
				 + "iid.business_type,"
				 + "iid.packing_qty,"
				 + "iid.owner_no,"
				 + "sai.QUALITY";
		strSql5 = " where iim.enterprise_no=iid.enterprise_no " 
				 + "and iim.instock_no = iid.instock_no   " 
				 + "and iid.enterprise_no= bap.enterprise_no(+) "
				 + "and iid.article_no = bap.article_no(+)    "
				 + "and iid.packing_qty = bap.packing_qty(+)  " 
				 + "and iim.enterprise_no=bda.enterprise_no " 
				 + "and iim.owner_no = bda.owner_no "
				 + "and iid.article_no = bda.article_no ";	
		
		strSql6 = " order by "
				 + "iid.instock_no,"
				 + "iid.label_no,"
				 + "iid.dest_cell_no,"
				 + "iid.article_no";		
		if(strFindAttSql1 != null && !strFindAttSql1.equals(""))	
		{
			strSql  =  strSql1 + strSql2 + strSql3 +  "," + strFindAttSql1  +  
					", f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality " +
					" from  s1% iid,stock_article_info sai " +
					" where iid.enterprise_no = sai.enterprise_no " +
					" and iid.article_id=sai.article_id " +
					" and sai.article_no  =  iid.article_no " +
					" and iid.article_id=sai.article_id " +
					" and sai.article_no  =  iid.article_no " ;
		}else
		{
			strSql  =  strSql1 + strSql2 + strSql3  +
					" from  s1% iid,stock_article_info sai " +
					" where iid.article_id=sai.article_id " +
					" and sai.article_no  =  iid.article_no " +
					" and sai.enterprise_no = iid.enterprise_no" ;
		}*/
/*		if(strFlag!=null && strFlag.equals("1"))
		{
			strSql = strSql.replace("s1%", " idata_instock_d ");
		}else if(strFlag!=null && strFlag.equals("2")){
			strSql = strSql.replace("s1%", " idata_instock_dhty ");
		}
		if(str != null && !str.equals("") && strFlag.equals("2"))
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(str);
			strSql  =  strSql + ws;
		}else
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(str);
			strSql  =  strSql + ws + " and iid.status = '10' ";
		}
		
		if(strOwner!= null && !strOwner.equals(""))
		{
			strSql  =  strSql + " and iid.owner_no in(" + strOwner + ")";
		}else{
			strSql  =  strSql + " and 1 = 2";
		}
		
		if(strWarehouseNo!= null && !strWarehouseNo.equals(""))
		{
			strSql  =  strSql + " and iid.warehouse_no ='" + strWarehouseNo + "'";
		}else{
			strSql  =  strSql + " and 1 = 2";
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql  =  strSql + " and iid.enterprise_no ='" + enterpriseNo + "'";
		}else{
			strSql  =  strSql + " and 1 = 2";
		}*/
		
//		if(strFindAttSql2 !=  null && !strFindAttSql2.equals(""))
//		{
//			strSql  =  strSql  + strSql4 +  ","  +  strFindAttSql2 + ") iid " + strSql5;
//		}else
//		{
//			strSql  =  strSql + strSql4 +  ","  +  strFindAttSql2 + ") iid "+ strSql5;
//		}
//		
//		if(strWarehouseNo!= null && !strWarehouseNo.equals(""))
//		{
//			strSql  =  strSql + " and iim.warehouse_no ='" + strWarehouseNo + "'";
//		}else{
//			strSql  =  strSql + " and 1 = 2";
//		}
//		
//		if(enterpriseNo!=null && !enterpriseNo.equals("")){
//			strSql  =  strSql + " and iim.enterprise_no ='" + enterpriseNo + "'";
//		}else{
//			strSql  =  strSql + " and 1 = 2";
//		}

		
	/*	if(str != null && !str.equals(""))
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(str);
			strSql  =  strSql + ws + strSql6;
		}else
		{
			strSql  =  strSql + strSql6;
		}*/
		
		String strSql = "select " 
				 + "iim.warehouse_no,"
				 + "iim.operate_type,"
				 + "iim.CONTAINER_LOCATE_FLAG,"
				 + "bda.owner_article_no,"
				 + "bda.article_name,"
				 //+ "nvl(bap.packing_unit, decode(iid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"
				 + "f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingUnit,"
				 + "f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingUnitQmin,"
				 + "f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) Unit,"
				 + "f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingSpec,"
				 + "f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingSpecQmin,"
				 + "f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) spec,"
				 + "bda.barcode,bda.QMIN_OPERATE_PACKING,bda.unit_packing,"
				 + "  trunc(iid.article_qty/iid.packing_qty) as planBox,"
	             + "  trunc(mod(iid.article_qty,iid.packing_qty)/bda.QMIN_OPERATE_PACKING) as planQmin,"
			     + "   (iid.article_qty - trunc(iid.article_qty/iid.packing_qty) * iid.packing_qty - trunc(mod(iid.article_qty,iid.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as planDis,"
				 + "iid.* " 
				 + " from s2%,bdef_article_packing bap,"
		 + "bdef_defarticle bda," 
		 + "(select  " 
		 + " iid.enterprise_no," 
		 + "iid.auto_locate_flag," 
		 + "iid.label_no,"
		 + "iid.article_no,"
		 + "iid.instock_no,"
		 + "iid.dest_cell_no,"
		 + "iid.cell_no,"
		 + "iid.real_cell_no,"
		 + "iid.business_type,"
		 + "iid.packing_qty," 
		 + "iid.owner_no,"
		 + "count(distinct iid.article_no) as articleCount," 
		 + "sum(iid.article_qty) as article_qty," 
		 + "sum(iid.REAL_QTY) as REAL_QTY," +
		 "  sai.PRODUCE_DATE,sai.expire_date,sai.import_batch_no,sai.lot_no, " +
		 "f_get_fieldtext('N', 'quality',sai.QUALITY) textQuality " +
		 " from  s1%,stock_article_info sai " +
			" where iid.enterprise_no = sai.enterprise_no " +
			" and iid.article_id=sai.article_id " +
			" and sai.article_no  =  iid.article_no " +
			" and iid.owner_no in( "+
			strOwner+
	        "        ) "+
	        "        and iid.warehouse_no ='"+strWarehouseNo+"' "+
	        "        and iid.enterprise_no ='"+enterpriseNo+"' ";
	    	
		if(str != null && !str.equals("") && strFlag.equals("2"))
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(str);
			strSql  =  strSql + ws;
		}else
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(str);
			strSql  =  strSql + ws + " and iid.status = '10' ";
		}
		strSql = strSql +"  group by    "
		 + "iid.enterprise_no,"
		 + "iid.label_no,"
		 + "iid.instock_no,"
		 + "iid.auto_locate_flag,"
		 + "iid.label_no,"
		 + "iid.article_no,"
		 + "iid.dest_cell_no,"
		 + "iid.cell_no,"
		 + "iid.real_cell_no,"
		 + "iid.business_type,"
		 + "iid.packing_qty,"
		 + "iid.owner_no,"
		 + "sai.QUALITY,sai.PRODUCE_DATE,sai.expire_date,sai.import_batch_no,sai.lot_no )iid  " +
		 " where iim.enterprise_no=iid.enterprise_no " 
		 + "and iim.instock_no = iid.instock_no   " 
		 + "and iid.enterprise_no= bap.enterprise_no(+) "
		 + "and iid.article_no = bap.article_no(+)    "
		 + "and iid.packing_qty = bap.packing_qty(+)  " 
		 + "and iim.enterprise_no=bda.enterprise_no " 
		 + "and iim.owner_no = bda.owner_no "
		 + "and iid.article_no = bda.article_no "
		 + "and iim.warehouse_no ='"+strWarehouseNo+"'  "
		 + "and iim.enterprise_no ='"+enterpriseNo+"'  ";
	
		if(str != null && !str.equals(""))
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(str);
			strSql  =  strSql + ws;
		}
		
		if(strFlag!=null && strFlag.equals("1"))
		{
			strSql = strSql.replace("s1%", " idata_instock_d iid ");
			strSql = strSql.replace("s2%", " Idata_Instock_M   iim ");
		}else if(strFlag!=null && strFlag.equals("2")){
			strSql = strSql.replace("s1%", " idata_instock_dhty iid ");
			strSql = strSql.replace("s2%", " Idata_Instock_Mhty   iim ");
		}
		strSql = strSql + " order by "
				 + "iid.instock_no,"
				 + "iid.label_no,"
				 + "iid.dest_cell_no,"
				 + "iid.article_no";	
		
		strTotsql  =  "select count(*) from "  + "(" + strSql + ")";	
		List<Idata_InstockDModel> list  =  genDao.getListByNativeSql(strSql, Idata_InstockDModel.class, 0, 10000);
		Integer count  =  genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Idata_InstockDModel> extListBo  =  new ExtListDataBo<Idata_InstockDModel>(list,count);
		return extListBo;
	}
	
	//保存
	public MsgRes save(String strInstockM, String strInstockD) throws Exception
	{
		Idata_InstockM iim = JSON.parseObject(strInstockM,Idata_InstockM.class);
		List<Idata_InstockDModel> iid = JSON.parseArray(strInstockD,Idata_InstockDModel.class);

		List<String>  outList = new ArrayList<String>();
		List  resultList = new ArrayList();
		
		outList.add("S");
		for (Idata_InstockDModel i : iid) 
		{
			List<Comparable>  inList = new ArrayList<Comparable>();
			
			inList.add(i.getId().getEnterpriseNo());
			inList.add(i.getId().getWarehouseNo());//strWareHouseNo
			inList.add(i.getId().getInstockNo());//strInstockNo
			inList.add(i.getDestCellNo());//strDestCellNo
			inList.add(i.getRealCellNo());//strRealCellNo
			inList.add(i.getLabelNo());//strLabelNo
			inList.add(i.getArticleNo());//strArticleNo
			inList.add(i.getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):i.getProduceDate());
			inList.add(i.getExpireDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):i.getExpireDate());
			inList.add(StringUtil.isStrEmpty(i.getQuality())?"0":i.getQuality());
			inList.add(StringUtil.isStrEmpty(i.getLotNo())?"N":i.getLotNo());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch1())?"N":i.getRsvBatch1());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch2())?"N":i.getRsvBatch2());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch3())?"N":i.getRsvBatch3());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch4())?"N":i.getRsvBatch4());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch5())?"N":i.getRsvBatch5());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch6())?"N":i.getRsvBatch6());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch7())?"N":i.getRsvBatch7());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch8())?"N":i.getRsvBatch8());
			inList.add(i.getPackingQty());//nPackingQty
			inList.add(i.getArticleQty());//nArticleQty
			inList.add(i.getRealQty());//nRealQty
			inList.add(iim.getInstockWorker());//strUserId
			inList.add(iim.getUpdtName());//strPaperUserId
			inList.add("1");//strTools
			if(i.getSplitFlag().equals("1"))
			{
				inList.add("1");
			}else
			{
				inList.add("0");
			}
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKLG_IDATA.p_Save_Idata_InStock");
			if(resultList.get(0).toString().indexOf("N|") != -1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			System.out.println(resultList.get(0).toString());
		}
		
		
		List<Comparable> inList2  =  new ArrayList<Comparable>();
		List<String> outList2  =  new ArrayList<String>();
		List resultList2  =  new ArrayList();
		
		outList2.add("S");
		inList2.add(iim.getId().getEnterpriseNo());
		inList2.add(iim.getId().getWarehouseNo());
		inList2.add(iim.getId().getInstockNo());
		inList2.add(iim.getInstockWorker());//strUserId
		inList2.add(iim.getUpdtName());//strPaperUserId
		inList2.add("1");//strTools
		System.out.println(inList2);
		resultList2  =  genDao.execProc(inList2, outList2, "PKLG_IDATA.p_Comfire_Idata_InStock");
		if(resultList2.get(0).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList2.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E21302"),null);//保存成功！
	}//saveBody
	
	 /**
     * 上架单下拉
     */
	public List<Idata_InstockMModel> getInstockNoCombo(String enterpriseNo,String strwarehouseNo,
			String strworkerOwner, String strWheresql, String strFlag, String strQuery, int start, int total) 
	{
		String strSql  =  "";		
		if(strFlag.equals("1"))
		{
			strSql  =  "select a.* "  + 
					" from idata_instock_m a where " +
					" a.warehouse_no = '" + strwarehouseNo + "' " +
					" and a.enterprise_no= '"+ enterpriseNo +"' "+
					" %s1 %s2 "  + 
					" and a.status  =  '10' order by a.instock_no desc";
		}else if(strFlag.equals("2"))
		{
			strSql  =  "select a.*  "  + 
					" from idata_instock_mhty a where " +
					" a.warehouse_no = '" + strwarehouseNo + "' " +
					" and a.enterprise_no= '"+ enterpriseNo +"' "+
					" %s1 %s2 " +
					" order by a.instock_no desc";
		}
		if(strQuery != null && !strQuery.equals(""))
		{
			String ws  =  CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql.replace("%s1", ws);
		}else
		{
			strSql = strSql.replace("%s1", " and 1=2 ");
		}
		if(strWheresql != null && !strWheresql.equals(""))
		{
			strSql = strSql.replace("%s2", "and a.instock_no like '%" + strWheresql + "%' ");
		}else{
			strSql = strSql.replace("%s2", " ");
		}
		List list = genDao.getListByNativeSql(strSql,Idata_InstockMModel.class, start, total);
		return  (List<Idata_InstockMModel>)list;
	}
	
	 /**
     * 储位下拉
     */
	public List<ComboxBo> getCellNoComboList(String enterpriseNo,String strWarehouseNo,
			String strOwnerNo, String strWheresql, int i, int j) 
	{//cmbBody
		String strSql  =  "";
		String sqlCheckOwner  =  "select a.fixedcell_flag from bdef_defowner a " +
				                 "where a.owner_no = '" + strOwnerNo + "' " +
				                   "and a.enterprise_no='" +enterpriseNo+"' ";
		List listFixedcellFlag  =  genDao.getListByNativeSql(sqlCheckOwner);
		if(listFixedcellFlag.get(0).equals("0"))
		{
			strSql  =  "  select a.cell_no value,a.cell_no text,a.cell_no dropValue from Cdef_Defcell a,cdef_defarea b "  + 
					 "  where a.warehouse_No = '" + strWarehouseNo +"' "+ 
					 "  and a.enterprise_no='"+enterpriseNo+"' "+
					 "  and a.cell_no not in "  + 
					 "  (select distinct a.cell_no from stock_content a " +
					 "    where a.warehouse_no = '" + strWarehouseNo + "' "  +
					 "    and a.enterprise_no='"+enterpriseNo+"' "+
					 "    and a.owner_no <> '" + strOwnerNo + "' and a.qty + a.instock_qty <> 0) "  + 
					 " and a.enterprise_no=b.enterprise_no and a.warehouse_no = b.warehouse_no and a.ware_no = b.ware_no and a.area_no = b.area_no "  + 
					 " and b.area_usetype in('1','5','6') and b.area_attribute = '0'"  + 
					 " and a.cell_status in('0','2')  and a.check_status = '0'" ;
		}else if(listFixedcellFlag.get(0).equals("1"))
		{
			strSql  =  "select a.cell_no value,a.cell_no text,a.cell_no dropValue  "  + 
					" from Cdef_Defcell a ,cdef_defarea b  " +
					" where a.warehouse_No = '"  +  strWarehouseNo + "' "  + 
					" and a.enterprise_no='"+enterpriseNo+"' "+
					" and a.cell_no in (select a.cell_no from cset_owner_cell a "  + 
					" where a.owner_no  =  '"  +  strOwnerNo  +  "' " +
					"  and a.enterprise_no='"+enterpriseNo+"' "+
					" and warehouse_no  =  '"  +  strWarehouseNo + "') "  + 
					" and a.enterprise_no=b.enterprise_no and a.warehouse_no  =  b.warehouse_no and a.ware_no  =  b.ware_no and a.area_no = b.area_no "  + 
					" and b.area_usetype in('1','5','6') and b.area_attribute = '0'"  + 
					" and a.cell_status in('0','2')  and a.check_status = '0'";
		}
		
		if(strWheresql !=  null && !strWheresql.equals(""))
		{
			strSql  =  strSql  +  "and a.cell_no like '%"  +  strWheresql + "%'";
		}
		strSql += " order by cell_no ";
		List list  =  genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 10);
		return  (List<ComboxBo>)list;
	}//cmbBody
	
	/*
	 * @func 获取货主下拉列表
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<ComboxBo> getOwnerComboList(String enterpriseNo,String wareHouseNo,String strWorkerOwner,String strFlag) throws Exception 
	{
		String strSql  =  "";
		if(strFlag.equals("1"))
		{
		strSql  =  "select distinct t1.owner_no value,t2.owner_name text,"  + 
				" '['|| ltrim(t1.owner_no)||']'||t2.owner_name dropValue "  + 
				"from idata_instock_m t1, bdef_defowner t2 where 1 = 1 and " +
				"t1.owner_no = t2.owner_no and t1.enterprise_no=t2.enterprise_no " +
				"and t1.owner_no in (" + strWorkerOwner + ") " +
				" and t1.enterprise_no='"+enterpriseNo+"' " +
				" and t1.wareHouse_no='"+wareHouseNo+"' ";
		}else if(strFlag.equals("2"))
		{
		strSql  =  "select distinct t1.owner_no value,t2.owner_name text,"  + 
				" '['|| ltrim(t1.owner_no)||']'||t2.owner_name dropValue "  + 
				"from idata_instock_mhty t1, bdef_defowner t2 where 1 = 1 and " +
				" t1.owner_no = t2.owner_no and t1.enterprise_no=t2.enterprise_no " +
				" and t1.owner_no in (" + strWorkerOwner + ") " +
				" and t1.enterprise_no='"+enterpriseNo+"' " +
				" and t1.wareHouse_no='"+wareHouseNo+"' ";
		}
 		
		strSql  +=  " order by t1.owner_no";
		List list  =  genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	/*
	 * @func 获得批属性字符串
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<String> getArticleAttrString(String strFlag) throws Exception 
	{
		String strSql  =  "";
		if(strFlag.equals("1"))//strFlag = 1:查询字段批属性字段
		{
			 strSql  =  "select a.field_id "  + 
					" from (select case when group_flag  =  1  then  "  + 
					" 'sai.'||a.field_id else 'min(' ||  'sai.'||a.field_id || ')' || ' as '|| a.field_id "  + 
					" end as field_id from wms_deffielddesc a "  + 
					" where a.use_type  = '3' and a.desc_flag = '1') a where 1 = 1  ";
		}else if(strFlag.equals("2"))//strFlag = 2:group by字段批属性字段
		{
			 strSql  =  " select 'sai.' || a.field_id from wms_deffielddesc a "  + 
					" where a.use_type  = '3' and a.desc_flag = '1' and a.group_flag = '1'   ";
		}
		List<String> strFindAtt1  =  genDao.getListByNativeSql(strSql);
		return (List<String>) strFindAtt1;
	}
	
	/**
	 * Rf输入板号
	 */
	@Override
	public MsgRes IdataInstockLabelNo(String strRecvData)
			throws Exception {
		IdataInStockLabelRequestModel resMod=JSON.parseObject(strRecvData, IdataInStockLabelRequestModel.class);
		String strEnterpriseNo=resMod.getEnterpriseNo();
		String strWarehouseNo=resMod.getWarehouseNo();
		String strOwnerPermissions=resMod.getOwnerPermissions();
		String strLabelNo=resMod.getLabelNo();
		
		MsgRes msgRes=new MsgRes();
		IdataInstockLableOrSerialNoAnswerModel ansMod=new IdataInstockLableOrSerialNoAnswerModel();
		//检验标签状态
		String strSql="select * from stock_label_m slm " +
				"where  slm.warehouse_no='"+strWarehouseNo+"' " +
						"and slm.enterprise_no='" +strEnterpriseNo+"' "+
						"and slm.label_no='"+strLabelNo+"'";
		List<Stock_LabelMModel> listslm=genDao.getListByNativeSql(strSql, Stock_LabelMModel.class);
		if(listslm.size()==0)
		{
			strSql="select * from idata_instock_d iid " +
					"where iid.warehouse_no='"+strWarehouseNo+"'" +
					"and iid.enterprise_no='" +strEnterpriseNo+"' "+
					"and iid.owner_no in(" +strOwnerPermissions+") "+
					"and iid.serial_no='"+strLabelNo+"'";
			List<IdataInStockLabelAnswerModel> listiid=genDao.getListByNativeSql(strSql, IdataInStockLabelAnswerModel.class);
			if(listiid.size()==0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("输入的标签号或流水号不存在！");
				return msgRes;
			}else
			{				
				ansMod.setFlag("2");
				ansMod.setListInStLaAns(listiid);
				
				msgRes.setIsSucc(true);
				msgRes.setObj(JSONObject.toJSON(ansMod));
				return msgRes;
			}
		}else
		{
			if(!listslm.get(0).getUseType().equals("0"))
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("该标签号不是上架标签！");
				return msgRes;
			}
			if(!listslm.get(0).getStatus().equals("26"))
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("该标签号状态不对！");
				return msgRes;
			}
		}
		strSql="select "
				+"            iim.warehouse_no, "
		        +"			  iim.owner_no,"						
				+"            iim.operate_type, "
				+"            iim.CONTAINER_LOCATE_FLAG, "
				+"            bda.article_name, "
				//+"            nvl(bap.packing_unit, decode(iid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"
				+"            bda.barcode,"
				+"            nvl(bap.pal_base_qbox,999) as pal_base_qbox, "
				+"            nvl(bap.pal_height_qbox,999)as pal_height_qbox, "
				+"            nvl(bap.pal_base_qbox||'*'||bap.pal_height_qbox,999) as qpalette,"
				+"            bda.unit_packing,bda.qmin_operate_packing,bda.QMIN_OPERATE_PACKING_UNIT as qminOpeartePackingUnit,  " 
				//+"            nvl(bap.spec, '1*' || iid.packing_qty || bda.unit) spec, "
				+"            f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingUnit, "
				+"            f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingUnitQmin, "
				+"            f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) Unit, "
				+"            f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingSpec, "
				+"            f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingSpecQmin, "
				+"            f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) spec, "
		        +"            bda.expiry_days, iid.articleQty poQty,cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.disp_cell_no dispCellNo,"
				+"            iid.*   "
				+"        from "
				+"            Idata_Instock_M   iim, "
				+"            bdef_article_packing bap, "
				+"            bdef_defarticle bda,cdef_defcell cdc, "
				+"            (select iid.warehouse_no,"
				+"                iid.enterprise_no,"
				+"                iid.auto_locate_flag, "
				+"                iid.label_no, "
				+"                iid.article_no, "
				+"                iid.instock_no, "
				+"                iid.dest_cell_no as cell_no, "
				+"                iid.real_cell_no, "
				+"                iid.business_type, "
				+"                iid.packing_qty, "
				+"                iid.owner_no, "
				+"                count(distinct iid.article_no) as articleCount, "
				+"                sum(iid.article_qty) as articleQty, "
				+"                sum(iid.ARTICLE_QTY/iid.PACKING_QTY) as realBox, "
				+"                sum(iid.REAL_QTY) as REAL_QTY  , "
				+"                min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO, "
				+"                min(sai.RSV_BATCH1) as RSV_BATCH1, "
				+"                min(sai.RSV_BATCH2) as RSV_BATCH2, "
				+"                min(sai.RSV_BATCH3) as RSV_BATCH3, "
				+"                min(sai.RSV_BATCH4) as RSV_BATCH4, "
				+"                min(sai.RSV_BATCH5) as RSV_BATCH5, "
				+"                min(sai.RSV_BATCH6) as RSV_BATCH6, "
				+"                min(sai.RSV_BATCH7) as RSV_BATCH7, "
				+"                min(sai.RSV_BATCH8) as RSV_BATCH8, "
				+"                to_char(sai.EXPIRE_DATE,'yyyyMMdd') EXPIRE_DATE, "
				+"                sai.LOT_NO, "
				+"                to_char(sai.PRODUCE_DATE,'yyyyMMdd') PRODUCE_DATE, "
				+"                sai.QUALITY, "
				+"                f_get_fieldtext('N', "
				+"                'quality', "
				+"                sai.QUALITY) textQuality   "
				+"            from "
				+"                idata_instock_d  iid, "
				+"                stock_article_info sai   "
				+"            where "
				+"                iid.article_id=sai.article_id   "
				+"                and sai.article_no  =  iid.article_no   "
				+"                and iid.article_id=sai.article_id   "
				+"                and sai.article_no  =  iid.article_no   "
				+"                and iid.warehouse_no ='"+strWarehouseNo+"'  "
				+"				  and iid.enterprise_no='" +strEnterpriseNo+"' "
				+"                and (iid.label_no='"+strLabelNo+"' or iid.SERIAL_NO='"+strLabelNo+"' )"
				+"                and iid.status = '10'   "
				+"            group by" 
				+"                iid.enterprise_no,iid.warehouse_no, "
				+"                iid.label_no, "
				+"                iid.instock_no, "
				+"                iid.auto_locate_flag, "
				+"                iid.label_no, "
				+"                iid.article_no, "
				+"                iid.dest_cell_no, "
				+"                iid.cell_no, "
				+"                iid.real_cell_no, "
				+"                iid.business_type, "
				+"                iid.packing_qty, "
				+"                iid.owner_no, "
				+"                sai.EXPIRE_DATE, "
				+"                sai.LOT_NO, "
				+"                sai.PRODUCE_DATE, "
				+"                sai.QUALITY) iid   "
				+"        where "
				+"            iim.instock_no = iid.instock_no   " 
				+"            and iim.enterprise_no=iid.enterprise_no  "
				+"            and iid.article_no = bap.article_no(+)     "
				+"            and iid.packing_qty = bap.packing_qty(+)" 
				+"            and iid.enterprise_no=bap.enterprise_no(+)  "
				+"            and iim.owner_no = bda.owner_no  "
				+"            and iid.enterprise_no=bda.enterprise_no    "
				+"            and iid.article_no = bda.article_no  " 
				//添加关联储位表，上架根据储位动线排序  wyf 2015-08-18
				+" 		      and iid.enterprise_no = cdc.enterprise_no and iid.warehouse_no=cdc.warehouse_no and iid.cell_no=cdc.cell_no "
				+"            and iim.warehouse_no ='"+strWarehouseNo+"'  "
				+"        order by cdc.ware_no,cdc.area_no ,cdc.stock_no,cdc.pick_order,"
				+"            iid.cell_no, "
				+"            iid.article_no desc";
		List<IdataInStockLabelAnswerModel> list=genDao.getListByNativeSql(strSql, IdataInStockLabelAnswerModel.class);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该标签号上没有商品！");
			return msgRes;
		}
		ansMod.setFlag("1");
		ansMod.setListInStLaAns(list);
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONObject.toJSON(ansMod));
		return msgRes;
	}
	/**
	 * 上架回单	
	 */
	@Override
	public MsgRes tscSaveIdataInStock(String strEnterpriseNo,
										String strWarehouseNo,
										String strInstockNo,
										String strDestCellNo,
										String strRealCellNo,
										String strLabelNo,
										String strArticleNo,
										Date dtProduceDate,
										Date dtExpireDate,
										String strQuality,
										String strLotNo,
										String strRSV_BATCH1,
										String strRSV_BATCH2,
										String strRSV_BATCH3,
										String strRSV_BATCH4,
										String strRSV_BATCH5,
										String strRSV_BATCH6,
										String strRSV_BATCH7,
										String strRSV_BATCH8,
										Double nPackingQty,
										Double nArticleQty,
										Double nRealQty,
										String strUserId,
										String strPaperUserId,
										String strTools,
										Integer nIsAddFlag) throws Exception 
	{
		MsgRes msgRes=new MsgRes();
		List<Comparable>  inList = new ArrayList<Comparable>();
		List<String>  outList = new ArrayList<String>();
		List  resultList = new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strInstockNo);//strInstockNo
		inList.add(strDestCellNo);//strDestCellNo
		inList.add(strRealCellNo);//strRealCellNo
		inList.add(strLabelNo);//strLabelNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(dtProduceDate);//dtProduceDate
		inList.add(dtExpireDate);//dtExpireDate
		inList.add(strQuality);//strQuality
		inList.add(strLotNo);//strLotNo
		inList.add(strRSV_BATCH1);//strRSV_BATCH1
		inList.add(strRSV_BATCH2);//strRSV_BATCH2
		inList.add(strRSV_BATCH3);//strRSV_BATCH1
		inList.add(strRSV_BATCH4);//strRSV_BATCH1
		inList.add(strRSV_BATCH5);//strRSV_BATCH1
		inList.add(strRSV_BATCH6);//strRSV_BATCH1
		inList.add(strRSV_BATCH7);//strRSV_BATCH1
		inList.add(strRSV_BATCH8);//strRSV_BATCH1
		inList.add(nPackingQty);//nPackingQty
		inList.add(nArticleQty);//nArticleQty
		inList.add(nRealQty);//nRealQty
		inList.add(strUserId);//strUserId
		inList.add(strPaperUserId);//strPaperUserId
		inList.add("1");//strTools
		inList.add("1");
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.p_Save_Idata_InStock");
		if(resultList.get(0).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		msgRes.setObj("回单成功");
		return msgRes;
	}
	/**
	 * Rf上架回单确认
	 */
	@Override
	public MsgRes tscRfComfireIdataInStock(String strEnterpriseNo,
											String strWarehouseNo,
											String strInstockNo, 
											String strUserId, 
											String strPaperUserId)
											throws Exception 
    {
		MsgRes msgRes=new MsgRes();
		List<Comparable>  inList = new ArrayList<Comparable>();
		List<String>  outList = new ArrayList<String>();
		List  resultList = new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strInstockNo);//strInstockNo		
		inList.add(strUserId);//strUserId
		inList.add(strPaperUserId);//strPaperUserId
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.p_RfComfire_Idata_InStock");
		if(resultList.get(0).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		msgRes.setObj("回单确认成功！");
		return msgRes;
	}
	/**
	 * RF上架 确认	
	 * lich
	 * 1、调用tscSaveIdataInStock
	 * 2、调用tscRfComfireIdataInStock
	 * 3、扫流水号上架，检查是否已全部回单
	 */	
	@Override
	public MsgRes tscComfire(String strRecvData) throws Exception {
		
		IdataInStockRequestModel resMod=JSON.parseObject(strRecvData, IdataInStockRequestModel.class);
		
		String strEnterpriseNo=resMod.getEnterpriseNo();
		String strWarehouseNo=resMod.getWareHouseNo();
		//String strOwnerNo=resMod.getOwnerNo();
		String strInstockNo=resMod.getInstockNo();
		String strDestCellNo=resMod.getDestCellNo();
		String strRealCellNo=resMod.getRealCellNo();
		String strLabelNo=resMod.getLabelNo();
		String strArticleNo=resMod.getArticleNo();                          
		Date dtProduceDate=DateUtil.GetDate(resMod.getProduceDate(), "yyyyMMdd");
		Date dtExpireDate=DateUtil.GetDate(resMod.getExpireDate(), "yyyyMMdd");
		String strQuality=resMod.getQuality();//品质                                
		String strLotNo=resMod.getLotNo();//批次号
		String strRSV_BATCH1=resMod.getRsvBatch1(); //预留批属性1
		String strRSV_BATCH2=resMod.getRsvBatch2(); //预留批属性2
		String strRSV_BATCH3=resMod.getRsvBatch3(); //预留批属性3
		String strRSV_BATCH4=resMod.getRsvBatch4(); //预留批属性4
		String strRSV_BATCH5=resMod.getRsvBatch5(); //预留批属性5
		String strRSV_BATCH6=resMod.getRsvBatch6(); //预留批属性6
		String strRSV_BATCH7=resMod.getRsvBatch7(); //预留批属性7
		String strRSV_BATCH8=resMod.getRsvBatch8(); //预留批属性8                                
		Double nPackingQty=Double.parseDouble(resMod.getPackingQty());
		Double nArticleQty=Double.parseDouble(resMod.getArticleQty());
		Double nRealQty=Double.parseDouble(resMod.getRealQty());
		String strUserId=resMod.getUserId(); //上架人
		String strPaperUserId=resMod.getPaperUserId(); //回单人
		String strTools=resMod.getTools();
		Integer nIsAddFlag=Integer.parseInt(resMod.getIsAddFlag());//是否新增记录 1是0否			
		MsgRes msgRes=new MsgRes();
		
		//检验实际上架储位（该方法暂不使用，所有校验已在保存时调用底层的时校验）
		/*if(!strRealCellNo.equals(strDestCellNo))
		{
			msgRes=checkCellImpl.checkCell(strOwnerNo, 
				strRealCellNo, 
				strWarehouseNo,
				strEnterpriseNo);
			if(!msgRes.getIsSucc())
			{
				throw new Exception(msgRes.getMsg());
			}
		}*/
		//取单据流水号
		msgRes=getSerialNoByInstockNo(strInstockNo);
		if(!msgRes.getIsSucc())
		{
			throw new Exception(msgRes.getMsg());
		}
		
		String strSerialNo=msgRes.getObj().toString();
		//调用保存
		msgRes=tscSaveIdataInStock(strEnterpriseNo,
				strWarehouseNo, 
				strInstockNo, 
				strDestCellNo, 
				strRealCellNo, 
				strLabelNo, 
				strArticleNo, 
				dtProduceDate, 
				dtExpireDate, 
				strQuality, 
				strLotNo, 
				strRSV_BATCH1, 
				strRSV_BATCH2, 
				strRSV_BATCH3, 
				strRSV_BATCH4, 
				strRSV_BATCH5, 
				strRSV_BATCH6, 
				strRSV_BATCH7, 
				strRSV_BATCH8, 
				nPackingQty, 
				nArticleQty,
				nRealQty, 
				strUserId, 
				strPaperUserId, 
				strTools, 
				nIsAddFlag);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("上架确认失败！");
		}
		
		//调用RF上架回单确认
		msgRes=tscRfComfireIdataInStock(strEnterpriseNo,
				strWarehouseNo, 
				strInstockNo, 
				strUserId, 
				strPaperUserId);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("上架确认失败！");
		}
		//Rf扫流水上架》扫条码，判断该流水号是否已全部上架
		CommSingleDataAnswerModel ansMod=new CommSingleDataAnswerModel();
		if(CheckIsAllBackOrders(strSerialNo))
		{
			ansMod.setAnsObj("true");
		}else
		{
			ansMod.setAnsObj("false");
		}
		msgRes.setObj(JSONObject.toJSON(ansMod));
		return msgRes;
	}
	@Override
	public MsgRes IdataInstockBarcodeNo(String strRecvData) throws Exception {
		IdataInStockBarcodeRequestModel reqMod=JSON.parseObject(strRecvData, IdataInStockBarcodeRequestModel.class);		
		MsgRes msgRes=new MsgRes();	
		//根据扫描的商品条码查找商品信息
		msgRes =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),
				reqMod.getBarcode(),
				reqMod.getOwnerNo(),
				reqMod.getEnterpriseNo());
		String strArticlNo="";
		if(!msgRes.getIsSucc())
		{
			return msgRes;
		}else
		{
			strArticlNo=msgRes.getObj().toString();
		}
		
		//关联储位表 huangb 20160720
		String strSql="select "
		+"            iim.warehouse_no, "
        +"			  iim.owner_no,"						
		+"            iim.operate_type, "
		+"            iim.CONTAINER_LOCATE_FLAG, "
		+"            bda.article_name, "
		//+"            nvl(bap.packing_unit, decode(iid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"
		+"            bda.barcode, "
		+"            nvl(bap.pal_base_qbox,999) as pal_base_qbox, "
		+"            nvl(bap.pal_height_qbox,999) as pal_height_qbox, "
		+"            nvl(bap.pal_base_qbox||'*'||bap.pal_height_qbox,999) as qpalette,"
		+"            bda.qmin_operate_packing, bda.QMIN_OPERATE_PACKING_UNIT as qminOpeartePackingUnit, "
		+"            bda.unit_packing as unitPacking, "  
		//+"            nvl(bap.spec, '1*' || iid.packing_qty || bda.unit) spec, " +
		+"            f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingUnit, "
       	+"            f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingUnitQmin, "
    	+"            f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) Unit, "
    	+"            f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingSpec, "
    	+"            f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingSpecQmin, "
    	+"            f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) spec, "
        +"            bda.expiry_days, iid.articleQty poQty,cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.disp_cell_no dispCellNo,"
		+"            iid.*   "
		+"        from "
		+"            Idata_Instock_M   iim, "
		+"            bdef_article_packing bap, "
		+"            bdef_defarticle bda,cdef_defcell cdc, "
		+"            (select " 
		+"                iid.enterprise_no,"
		+"                iid.warehouse_no," 
		+"                iid.auto_locate_flag, "
		+"                iid.label_no, "
		+"                iid.article_no, "
		+"                iid.instock_no, "
		+"                iid.serial_no, "
		+"                iid.dest_cell_no as cell_no, "
		+"                iid.real_cell_no, "
		+"                iid.business_type, "
		+"                iid.packing_qty, "
		+"                iid.owner_no, "
		+"                count(distinct iid.article_no) as articleCount, "
		+"                sum(iid.article_qty) as articleQty, "
		+"                sum(iid.ARTICLE_QTY/iid.PACKING_QTY) as realBox, "
		+"                sum(iid.REAL_QTY) as REAL_QTY  , "
		+"                min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO, "
		+"                min(sai.RSV_BATCH1) as RSV_BATCH1, "
		+"                min(sai.RSV_BATCH2) as RSV_BATCH2, "
		+"                min(sai.RSV_BATCH3) as RSV_BATCH3, "
		+"                min(sai.RSV_BATCH4) as RSV_BATCH4, "
		+"                min(sai.RSV_BATCH5) as RSV_BATCH5, "
		+"                min(sai.RSV_BATCH6) as RSV_BATCH6, "
		+"                min(sai.RSV_BATCH7) as RSV_BATCH7, "
		+"                min(sai.RSV_BATCH8) as RSV_BATCH8, "
		+"                to_char(sai.EXPIRE_DATE,'yyyyMMdd') EXPIRE_DATE, "
		+"                sai.LOT_NO, "
		+"                to_char(sai.PRODUCE_DATE,'yyyyMMdd') PRODUCE_DATE, "
		+"                sai.QUALITY, "
		+"                f_get_fieldtext('N', "
		+"                'quality', "
		+"                sai.QUALITY) textQuality   "
		+"            from "
		+"                idata_instock_d  iid, "
		+"                stock_article_info sai  " 	
		+"            where "
		+"                iid.article_id=sai.article_id   " 
		+"                and iid.enterprise_no=sai.enterprise_no"
		+"                and sai.article_no  =  iid.article_no   "
		+"                and iid.article_id=sai.article_id   "
		+"                and sai.article_no  =  iid.article_no   " 
		+"            	  and iid.serial_no='"+reqMod.getSerialNo()+"' "
		+"  			  and iid.article_no in("+strArticlNo+") " 
		+"                and iid.enterprise_no='"+reqMod.getEnterpriseNo()+"' "
		+"                and iid.status = '10'   "
		+"            group by " 
		+"                iid.enterprise_no,"
		+"                iid.warehouse_no,"
		+"                iid.label_no, "
		+"                iid.instock_no, "
		+"                iid.serial_no, "
		+"                iid.auto_locate_flag, "
		+"                iid.label_no, "
		+"                iid.article_no, "
		+"                iid.dest_cell_no, "
		+"                iid.cell_no, "
		+"                iid.real_cell_no, "
		+"                iid.business_type, "
		+"                iid.packing_qty, "
		+"                iid.owner_no, "
		+"                sai.EXPIRE_DATE, "
		+"                sai.LOT_NO, "
		+"                sai.PRODUCE_DATE, "
		+"                sai.QUALITY) iid   "
		+"        where "
		+"            iim.instock_no = iid.instock_no    " 
		+"            and iim.enterprise_no=iid.enterprise_no "
		+"            and iid.enterprise_no=bap.enterprise_no(+) "
		+"            and iid.article_no = bap.article_no(+)     "
		+"            and iid.packing_qty = bap.packing_qty(+)   "
		+"            and iim.owner_no = bda.owner_no  "
		+"            and iid.article_no = bda.article_no  "
		+"            and iid.article_no = bda.article_no " 
		//添加关联储位表，上架根据储位动线排序  huangb 20160720
		+" 		      and iid.enterprise_no = cdc.enterprise_no and iid.warehouse_no=cdc.warehouse_no and iid.cell_no=cdc.cell_no "
		+"        order by cdc.ware_no,cdc.area_no ,cdc.stock_no,cdc.pick_order,"
		+"            iid.cell_no, "
		+"            iid.article_no ";
		List<IdataInStockLabelAnswerModel> list=genDao.getListByNativeSql(strSql, IdataInStockLabelAnswerModel.class);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("条码错误！");
			return msgRes;
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONObject.toJSON(list));
		return msgRes;
	}
	//Rf扫流水上架》扫条码，判断该流水号是否已全部上架
	private boolean CheckIsAllBackOrders(String strSerialNo)
	{
		String strSql="select * " +
				"from " +
				"	idata_instock_d a " +			
				"where a.serial_no = '"+strSerialNo+"' and a.status='10' and rownum<2";
		
		List<Idata_InstockDModel> list=genDao.getListByNativeSql(strSql, Idata_InstockDModel.class);
		if(list.size()>0)
		{
			return false;//未上架回单
		}else
		{
			return true;//已全部上架回单
		}
	}
	
	//Rf根据单据编号取流水号
	private MsgRes getSerialNoByInstockNo(String strInstockNo)
	{
		String strSql="select a.* " +
				"	 from idata_instock_d a " +
				"    where a.instock_no = '"+strInstockNo+"' and rownum<2";
		
		List<Idata_InstockDModel> list=genDao.getListByNativeSql(strSql, Idata_InstockDModel.class);
		if(list.size()>0)
		{
			return new MsgRes(true, "", list.get(0).getSerialNo());
		}else
		{
			return new MsgRes(false, "单据已处理！", null);
		}
	}
	@Override
	public MsgRes RfBoxCodeInput(String strRecvData) throws Exception {		
		BoxBarcodeRequestModel reqMod=JSON.parseObject(strRecvData, BoxBarcodeRequestModel.class);		
		String strSql="select " +
					" b.owner_article_no," +
					" b.article_name," +
					" b.article_no," +
					" max(d.packing_qty) packing_qty " +
				" from " +
					" BSET_ARTICLE_BARCODE_M a," +
					" BSET_ARTICLE_BARCODE_D d," +
					" bdef_defarticle b " +					
				" where " +
					" a.SERIAL_NO=d.SERIAL_NO " +
					" and a.enterprise_no=d.enterprise_no "+
					" and a.warehouse_no=d.warehouse_no " +
					" and d.enterprise_no=b.enterprise_no "+
					" and d.article_no=b.article_no " +
					" and d.article_no=bab.article_no " +					
					" and a.status='10' "+
					" and d.status='10' "+
					" and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					" and a.SERIAL_NO='"+reqMod.getSerialNo()+"' " +
					" and b.barcode='" +reqMod.getBarcode()+"' "+
				" group by " +
					" b.owner_article_no," +
					" b.article_name," +
					" b.article_no";
		List<BoxCodeCellAnswerModel> listA=genDao.getListByNativeSql(strSql, BoxCodeCellAnswerModel.class);
		if(listA.size()>0)
		{
			return new MsgRes(true, "", JSONObject.toJSON(listA));
		}else
		{
			return new MsgRes(false, "条码不存在！", null);
		}		
	}
	@Override
	public MsgRes tscRfBoxCodeOk(String strRecvData) throws Exception {
		BoxCodeOkRequestModel reqMod=JSON.parseObject(strRecvData, BoxCodeOkRequestModel.class);
		
		MsgRes msgRes=new MsgRes();
		List<Comparable>  inList = new ArrayList<Comparable>();
		List<String>  outList = new ArrayList<String>();
		List  resultList = new ArrayList();
		
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWareHouseNo());//strWareHouseNo
		inList.add(reqMod.getSerialNo()); //
		inList.add(reqMod.getArticleNo());//strArticleNo		
		inList.add(reqMod.getBarCode());//strBarCode
		inList.add(reqMod.getPackingQty());//strPaperUserId
		inList.add(reqMod.getUserNo());//strPaperUserNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.P_Get_Boxbarcode");
		if(resultList.get(0).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		return msgRes;
	}
	//条码采集 扫流水
	@Override
	public MsgRes barCodeSerialNo(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strSql="select distinct  b.owner_article_no,b.article_name,b.article_no " +
				"from BSET_ARTICLE_BARCODE_M a,bdef_defarticle b,BSET_ARTICLE_BARCODE_D d " +
				"where d.article_no=b.article_no " +
				" and a.SERIAL_NO=d.SERIAL_NO "+
				" and a.warehouse_no=d.warehouse_no "+
				" and a.status='10' "+
				" and d.status='10' "+
				" and d.barcode_type='1' "+
				" and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				" and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
				" and a.SERIAL_NO='"+reqMod.getReqObj()+"'";
		List<BoxCodeCellAnswerModel> listA=genDao.getListByNativeSql(strSql, BoxCodeCellAnswerModel.class);
		if(listA.size()>0)
		{
			return new MsgRes(true, "", JSONObject.toJSON(listA));
		}else
		{
			return new MsgRes(false, "流水号不存在！", null);
		}		
	}
	//条码采集 确认
	@Override
	public MsgRes tscRfbarCodeOK(String strRecvData) throws Exception {

		BoxCodeOkRequestModel reqMod=JSON.parseObject(strRecvData, BoxCodeOkRequestModel.class);
		
		MsgRes msgRes=new MsgRes();
		List<Comparable>  inList = new ArrayList<Comparable>();
		List<String>  outList = new ArrayList<String>();
		List  resultList = new ArrayList();
		
		outList.add("S");

		inList.add(reqMod.getWareHouseNo());//strWareHouseNo
		inList.add(reqMod.getSerialNo()); //
		inList.add(reqMod.getArticleNo());//strArticleNo		
		inList.add(reqMod.getBarCode());//strBarCode
		inList.add(reqMod.getPackingQty());//strPaperUserId
		inList.add(reqMod.getUserNo());//strPaperUserNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.P_Get_Boxbarcode");
		if(resultList.get(0).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		return msgRes;
	
	}
	@Override
	public MsgRes LogisticsBarcodeInput(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);		
		MsgRes msgRes=new MsgRes();	
		//根据扫描的商品条码查找商品信息
		msgRes =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),
				reqMod.getReqObj(),
				reqMod.getEnterpriseNo());
		String strArticlNo="";
		if(!msgRes.getIsSucc())
		{
			return msgRes;
		}else
		{
			strArticlNo=msgRes.getObj().toString();
		}
		
		String strSql=" select " +
							"a.owner_article_no," +
							"a.article_no," +
							"a.article_name, " +
							"max(b.packing_qty) as packingQty " +
						"from " +
							"bdef_defarticle a,bdef_article_packing b  " +
						"where " +
							"a.article_no=b.article_no " +
							"and a.enterprise_no='" +reqMod.getEnterpriseNo()+"' "+
							"and a.article_no in("+strArticlNo+") " +
						"group by " +
							"a.owner_article_no," +
							"a.article_no," +
							"a.article_name ";
		List<BoxCodeCellAnswerModel> list=genDao.getListByNativeSql(strSql, BoxCodeCellAnswerModel.class);
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONObject.toJSON(list.get(0)));
		return msgRes;
	}
	@Override
	public MsgRes tscRflogisticsCodeOK(String strRecvData) throws Exception {

		BoxCodeOkRequestModel reqMod=JSON.parseObject(strRecvData, BoxCodeOkRequestModel.class);
		
		MsgRes msgRes=new MsgRes();
		List<Comparable>  inList = new ArrayList<Comparable>();
		List<String>  outList = new ArrayList<String>();
		List  resultList = new ArrayList();
		
		outList.add("S");

		inList.add(reqMod.getWareHouseNo());//strWareHouseNo
		inList.add(reqMod.getArticleNo());//strArticleNo	
		inList.add(reqMod.getBoxNo());
		inList.add(reqMod.getPackingQty());//strPaperUserId
		inList.add(reqMod.getUserNo());//strPaperUserNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.PROC_CREATE_STOCK_BOX");
		if(resultList.get(0).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		return msgRes;
	
	}
	
	/**
	 * 27箱码采集 扫流水
	 * @return
	 */
	@Override
	public MsgRes boxCodeSerialNo(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strSql="select distinct  b.owner_article_no,b.article_name,b.article_no " +
				"from BSET_ARTICLE_BARCODE_M a,bdef_defarticle b,BSET_ARTICLE_BARCODE_D d " +
				"where d.article_no=b.article_no " +
				" and a.SERIAL_NO=d.SERIAL_NO "+
				" and a.warehouse_no=d.warehouse_no "+
				" and a.status='10' "+
				" and d.status='10' "+
				" and d.barcode_type='0' "+
				" and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				" and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +				
				" and a.SERIAL_NO='"+reqMod.getReqObj()+"'";
		List<BoxCodeCellAnswerModel> listA=genDao.getListByNativeSql(strSql, BoxCodeCellAnswerModel.class);
		if(listA.size()>0)
		{
			return new MsgRes(true, "","");
		}else
		{
			return new MsgRes(false, "流水号不存在！", null);
		}		
	}
	
	
}
