package com.sealinkin.ridata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.po.Idata_ImportM;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckBadService;
import com.sealinkin.ridata.service.IRidata_QingchangService;

/**
 * 模块名称：返配清场扫描验收
 * 模块编码：6902
 * 创建：hekl
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_QingchangImpl implements IRidata_QingchangService{
	
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
	
	/**
	 *返配清场扫描验收列
	 */
	@Override
	public ExtListDataBo<Ridata_CheckDModel> getRidata_QingchangDList(
			String enterpriseNO,String strWarehouseNo,String strWhereSql, PageBo pageBo) 
	{
		String sqls = "select SM.S_UNTREAD_NO from ridata_untread_sm sm ,ridata_untread_mm mm " +
				"where sm.enterprise_no=mm.enterprise_no and sm.warehouse_no=mm.warehouse_no " +
				"and sm.s_untread_no=mm.s_untread_no " +
				"and sm.untread_no='"+strWhereSql+"' and sm.enterprise_no='"+enterpriseNO+"' " +
				"and sm.warehouse_no='"+strWarehouseNo+"' ";
		List<String> lists = genDao.getListByNativeSql(sqls);
		String strSUntreadNo = lists.get(0);
		
		String strSql = "select b.article_no,b.packing_qty,c.article_name,c.barcode," +
			"(b.untread_qty/b.packing_qty) as pobox," +
			"(b.untread_qty/b.packing_qty) as checkbox," +
			"b.untread_qty - b.check_qty - case when (select sum(tmp.check_qty) from " +
			"ridata_check_pal_tmp tmp where tmp.s_untread_no='"+strSUntreadNo+"' " +
				"and tmp.warehouse_no='"+strWarehouseNo+"' " +
				"and tmp.article_no=b.article_no ) is null then 0 else (select sum(tmp.check_qty)/tmp.packing_qty " +
				"from ridata_check_pal_tmp tmp where tmp.s_untread_no='"+strSUntreadNo+"' " +
				"and tmp.warehouse_no='"+strWarehouseNo+"' " +
				"and tmp.article_no=b.article_no and tmp.packing_qty=b.packing_qty) end untreadQty," +
			"c.expiry_days,f.cust_no, g.cust_name," +
			"case when c.expiry_days=-1 then to_date ('1900-01-01','yyyy-mm-dd') else null end as produceDate," +
			"case when c.expiry_days=-1 then to_date ('1900-01-01','yyyy-mm-dd') else null end as expireDate," +
			"b.packing_qty,a.warehouse_no,a.owner_no," +
			//"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) unit,"+
            //" nvl(e.spec, '1*' || b.packing_qty || c.unit) spec,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"+
			"b.supplier_no,a.untread_no,a.untread_type,a.s_untread_no,b.check_qty,  F.QUALITY as qualityFlag, f.CLASS_TYPE " +
			"from ridata_untread_sm a,ridata_untread_d b,Bdef_Defarticle c," +
			"bdef_article_packing e, " +
			"ridata_untread_m f , bdef_defcust g " +
			"where a.untread_no=b.untread_no and b.article_no=c.article_no " +
			"and e.article_no(+)=b.article_no and e.packing_qty(+)=b.packing_qty " +
			"and b.warehouse_no=f.warehouse_no and b.owner_no=f.owner_no " +
			"and b.untread_no=f.untread_no and f.cust_no=g.cust_no  " +
			"and b.untread_qty-b.check_qty>0 " +
			"and a.warehouse_no='"+strWarehouseNo+"'" +
			"and a.s_untread_no = '"+strSUntreadNo+"' and a.enterprise_no=b.enterprise_no " +
			"and a.enterprise_no=c.enterprise_no " +
			"and b.enterprise_no=e.enterprise_no(+) and a.enterprise_no=f.enterprise_no " +
			"and a.enterprise_no=g.enterprise_no and a.enterprise_no='"+enterpriseNO+"' ";
	
		String totsql="select count(1) " +
				"from ridata_untread_sm a,ridata_untread_d b,Bdef_Defarticle c," +
				"bdef_article_packing e, " +
				"ridata_untread_m f , bdef_defcust g " +
				"where a.untread_no=b.untread_no and b.article_no=c.article_no " +
				"and e.article_no(+)=b.article_no and e.packing_qty(+)=b.packing_qty " +
				"and b.warehouse_no=f.warehouse_no and b.owner_no=f.owner_no " +
				"and b.untread_no=f.untread_no and f.cust_no=g.cust_no  " +
				"and b.untread_qty-b.check_qty>0 " +
				"and a.warehouse_no='"+strWarehouseNo+"'" +
				"and a.s_untread_no = '"+strSUntreadNo+"' and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no " +
				"and b.enterprise_no=e.enterprise_no(+) and a.enterprise_no=f.enterprise_no " +
				"and a.enterprise_no=g.enterprise_no and a.enterprise_no='"+enterpriseNO+"' ";
		
		List<Ridata_CheckDModel> list = genDao.getListByNativeSql(strSql,Ridata_CheckDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Ridata_CheckDModel> extListBo= new ExtListDataBo<Ridata_CheckDModel>(list, count);
		return extListBo;
	}
	
	/**
	 * 货主等下拉列表
	 * @param str
	 * @param pageBo
	 * @return
	 */
	@Override
	public List<ComboxBo> getComboList(String enterpriseNO,String strWarehouseNo, String strWorkerOwner,
			String strFlag) {
        String strSql = "select distinct rum.owner_no value,t2.owner_name text,"  + 
				" '['|| ltrim(rum.owner_no)||']'||t2.owner_name dropValue "  + 
				"from ridata_untread_m rum, bdef_defowner t2 " +
				"where rum.owner_no = t2.owner_no " +
				"and rum.warehouse_no='"+strWarehouseNo+"' " +
				"and rum.status='10' and rum.enterprise_no=t2.enterprise_no " +
				"and rum.enterprise_no='"+enterpriseNO+"'";		
        List list=genDao.getListByNativeSql(strSql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}
	
	
	//保存
	@Override
	public MsgRes save(String enterpriseNO,String strWarehouseNo,String jsonDetail) throws Exception {
		Ridata_CheckDModel pocd = (Ridata_CheckDModel)JSONObject.toBean(
				JSONObject.fromObject(jsonDetail),Ridata_CheckDModel.class);
		String labelNo=null;
		String subLabelNo=null;
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.parse("1900-01-01");
		String sql = "select distinct t.label_no,t.sub_label_no from ridata_check_pal_tmp t " +
				"where t.enterprise_no='"+enterpriseNO+"' " +
				"and t.warehouse_no='"+strWarehouseNo+"' " +
				"and t.s_untread_no='"+pocd.getSUntreadNo()+"' ";
		List<Ridata_CheckPalTmpModel> list=genDao.getListByNativeSql(sql,Ridata_CheckPalTmpModel.class);
		if(list.size()==0){
			labelNo="N";
			subLabelNo="N";
		}else{
			labelNo=list.get(0).getLabelNo();
			subLabelNo=list.get(0).getSubLabelNo();
		}
		
				
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");//strsCheckNo
		outList.add("S");//strResult
		outList.add("S");//strOutLabelNo
		
		inList.add(enterpriseNO);//strEnterpriseNO
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(pocd.getOwnerNo());//strOwnerNo
		inList.add(pocd.getSUntreadNo());//strsUntreadNo
		inList.add(pocd.getUntreadType());
		
		inList.add(pocd.getArticleNo());//strArticleNo
		inList.add(pocd.getBarcode());//strBarcode
		inList.add(pocd.getPackingQty());//nPackingQty
		inList.add(pocd.getCheckQty());//nCheckQty
		inList.add("N");//strPrinterGroupNo
		inList.add(pocd.getDockNo());//strDockNo
		inList.add(pocd.getWorkerNo());//strWorkerNo
		inList.add("1");//strCheckTools
		inList.add(1);//nIsAdd
		inList.add("0");//strQuality
		inList.add(date);//dtProduceDate
		inList.add(date);//dtExpireDate   
		inList.add("N");//strLotNo
		inList.add("N");//strRSV_BATCH1
		inList.add("N");//strRSV_BATCH2
		inList.add("N");//strRSV_BATCH3
		inList.add("N");//strRSV_BATCH4
		inList.add("N");//strRSV_BATCH5
		inList.add("N");//strRSV_BATCH6
		inList.add("N");//strRSV_BATCH7
		inList.add("N");//strRSV_BATCH8
		inList.add(labelNo);//strLabelNo  strLabelNo
		inList.add(subLabelNo);//strSubLabelNo
		inList.add(pocd.getSupplierNo());//strSupplierNo
		inList.add("3");//strFixPalFlag
		inList.add("0");//strBusinessType
		
		inList.add(pocd.getDeviceNo());//strDeviceNo
		inList.add(pocd.getCellNo());//strCellNo	
		inList.add(pocd.getQualityFlag());//strQualityFlag
		inList.add("N");//strBatchNo
		inList.add(pocd.getClassType());//strClassType
		
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_RF_SaveCheck");
		System.out.println(inList);
		if(resultList.get(2).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(2).toString());
		}
		return new MsgRes(true,"保存成功！",null);
	}
	
	/**
	 * 校验码头与单号是否一致
	 */
	@Override
	public MsgRes checkDockNoUNo(String enterpriseNO,String strWarehouseNo,
			String strUntreadNo,String strDockNo) throws Exception {
	    	String str="select l.s_untread_no " +
	    			"from ridata_untread_sm l " +
	    			"where l.untread_no='"+strUntreadNo+"' " +
	    			"and l.enterprise_no='"+enterpriseNO+"' " +
	    			"and l.warehouse_no='"+strWarehouseNo+"' ";
	    	List<String> list=genDao.getListByNativeSql(str);
	    	String strSUntreadNo = list.get(0);
	    
		
			String strSql="select a.dock_no " +
					"from ridata_check_pal_tmp a " +
					"where a.s_untread_no='"+strSUntreadNo+"' " +
					"and a.warehouse_no = '"+strWarehouseNo+"' " +
					"and a.enterprise_no='"+enterpriseNO+"'  ";
			List<String> list2=genDao.getListByNativeSql(strSql);
			String dockNo = null;
			if(list2.size()>0){
				dockNo = list2.get(0);
				if(!dockNo.equals(strDockNo)){
					return new MsgRes(false,"您选择的单号已经在其它扫描台验收，请重新选择单号！","");	
					
				}
			}
			return new MsgRes(true,"",dockNo);	
		
	}
	
	/**
	 * 校验条码（次品只允许一单一验）
	 */
	@Override
	public MsgRes queryBarcode(String enterpriseNO,String strWarehouseNo, String strBarcode,
			String strOwnerNo,  String strUntreadNo)
			throws Exception {
		MsgRes msg=null;
		msg=getArticleForBarcodeImpl.checkBarcode(strWarehouseNo, strBarcode, strOwnerNo,enterpriseNO);
		if(msg.getIsSucc()){
			String strSql="select a.article_no,a.supplier_no," +
					"a.unit_packing as packing_qty, A.RSV_ATTR2,a.barcode " +
					     " from bdef_defarticle a "+
		                 "where a.enterprise_no='"+enterpriseNO+"' " +
		                 "and a.article_no in ("+(msg.getObj().toString())+") ";
			List<String> list=genDao.getListByNativeSql(strSql);
			msg.setObj(list);
			msg.setIsSucc(true);
			msg.setMsg("成功");
			
		}else{
			msg.setIsSucc(false);
			msg.setMsg("该条码不存在");
		}
		return msg;
	}
	
	/**
	 * 封板
	 */
	@Override
	public MsgRes tscClosePal(String strDetail) throws Exception {
		Ridata_CheckPalTmpModel list=(Ridata_CheckPalTmpModel)JSON.parseObject(strDetail,Ridata_CheckPalTmpModel.class);

		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");//strResult
		
		List inList=new ArrayList();
		inList.add(list.getEnterpriseNo());
		inList.add(list.getWarehouseNo());
		inList.add(list.getOwnerNo());
		inList.add(list.getSUntreadNo());//strsUntreadNo
		inList.add(list.getSCheckNo());//strsCheckNo
		inList.add(list.getLabelNo());//strLabelNo
		inList.add(list.getWorkerNo());//strWorkerNo
		inList.add(list.getCellNo());//strCellNo
		inList.add(list.getDockNo());//strDockNo
		inList.add("0");//strPrintFlag
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_SpecialCloseBoxAndInstock");
		if(resultList.get(0).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"封板成功","");
	}


	//获取临时表数据
	@Override
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryCheckPalTmp(
			String enterpriseNO, String strWarehouseNo, 
			String strDockNo,String strSUntreadNo2,String strUntreadNo){
		
		    if(!strUntreadNo.equals("") && strUntreadNo!=null){
		    	String str="select l.s_untread_no " +
		    			"from ridata_untread_sm l " +
		    			"where l.untread_no='"+strUntreadNo+"' " +
		    			"and l.enterprise_no='"+enterpriseNO+"' " +
		    			"and l.warehouse_no='"+strWarehouseNo+"' ";
		    	List<String> list=genDao.getListByNativeSql(str);
		    	strSUntreadNo2 = list.get(0);
		    }
		
			String strSql="select a.label_no,a.sub_label_no,a.barcode,a.packing_qty," +
					"sum(a.check_qty)/a.packing_qty as check_qty,a.label_id,b.article_name,a.s_check_no,a.s_untread_no " +
					"from ridata_check_pal_tmp a," +
					"bdef_defarticle b " +
					"where a.article_no=b.article_no " +
					"and a.dock_no='"+strDockNo+"' " +
					"and a.s_untread_no='"+strSUntreadNo2+"' " +
					"and a.warehouse_no = '"+strWarehouseNo+"' " +
					"and a.enterprise_no=b.enterprise_no " +
					"and a.enterprise_no='"+enterpriseNO+"'  " +
				    "group by a.barcode,a.packing_qty,a.label_no,a.sub_label_no,a.label_id,b.article_name,a.s_check_no,a.s_untread_no  ";
			List<Ridata_CheckPalTmpModel> list=null;
			ExtListDataBo<Ridata_CheckPalTmpModel> extListBo=null;
			list = genDao.getListByNativeSql(strSql,Ridata_CheckPalTmpModel.class);
			extListBo= new ExtListDataBo<Ridata_CheckPalTmpModel>(list, 0);
	        return extListBo;
	}
	
	//校验目的储位
	@Override
	public MsgRes checkCellNo(String enterpriseNO, String strWarehouseNo,
			String strWhereSql,String strOwnerNo,String strSUntreadNo2,
			String strSCheckNo) throws Exception {
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");//strResult
		
		List inList=new ArrayList();
		inList.add(enterpriseNO);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strSUntreadNo2);//strsUntreadNo
		inList.add(strSCheckNo);//strsCheckNo
		inList.add(strWhereSql);//strCellNo
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_CheckCloseBoxCell");
		if(resultList.get(0).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"","");
	}
	
	//换单时校验当前码头是否还有其他未封箱的数据
	@Override
	public MsgRes checkCloseBox(String enterpriseNO, String strWarehouseNo,
			String strDockNo, String strUntreadNo) throws Exception {
		
		String sql = "SELECT * FROM  ridata_check_pal_tmp tmp " +
				"where tmp.enterprise_no='"+enterpriseNO+"' " +
				"and tmp.warehouse_no='"+strWarehouseNo+"' " +
				"and tmp.s_untread_no not in (select sm.s_untread_no from ridata_untread_sm sm where sm.enterprise_no=tmp.enterprise_no "+
				" and sm.warehouse_no=tmp.warehouse_no and sm.untread_no='"+strUntreadNo+"') " +
				"and tmp.dock_no='"+strDockNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()>0){
			return new MsgRes(false,"该码头还有未封箱的箱子，请先封箱！","");
		}
		//取返配类型
		String sql2 = "SELECT f_get_fieldtext('RIDATA_UNTREAD_M','QUALITY',tmp.quality)qualityText " +
				"FROM  ridata_untread_m tmp " +
				"where tmp.enterprise_no='"+enterpriseNO+"' " +
				"and tmp.warehouse_no='"+strWarehouseNo+"' " +
				"and tmp.untread_no='"+strUntreadNo+"' ";
		List<String> list2=genDao.getListByNativeSql(sql2);
		return new MsgRes(true,"",list2.get(0));
	}
	

	/**
	 *取该单的扫描数量
	 */
	@Override
	public MsgRes tscBoxQty(String enterpriseNO, String strWarehouseNo,
			String strDockNo, String strUntreadNo) throws Exception {
		String sqls = "select SM.S_UNTREAD_NO from ridata_untread_sm sm ,ridata_untread_mm mm " +
				"where sm.enterprise_no=mm.enterprise_no and sm.warehouse_no=mm.warehouse_no " +
				"and sm.s_untread_no=mm.s_untread_no " +
				"and sm.untread_no='"+strUntreadNo+"' and sm.enterprise_no='"+enterpriseNO+"' " +
				"and sm.warehouse_no='"+strWarehouseNo+"' ";
		List<String> sUnteradNo  = genDao.getListByNativeSql(sqls);
		String strSUntreadNo = sUnteradNo.get(0);
		
		String uncheckNo="select distinct nvl(to_char(sum(b.untread_qty - b.check_qty - case  " +
				           "       when (select sum(tmp.check_qty) from  ridata_check_pal_tmp tmp" +
				           "              where tmp.s_untread_no='"+strSUntreadNo+"' " +
				           "                and tmp.warehouse_no='"+strWarehouseNo+"' " +
				           "                and tmp.enterprise_no='"+enterpriseNO+"'"+
				           "                and tmp.article_no=b.article_no ) is null then 0 " +
				           "	   else (select sum(tmp.check_qty)/tmp.packing_qty  from ridata_check_pal_tmp tmp " +
				           "              where  tmp.s_untread_no='"+strSUntreadNo+"' " +
				           "                and tmp.warehouse_no='"+strWarehouseNo+"' " +
				           "                and tmp.enterprise_no='"+enterpriseNO+"' " +
				           "                and tmp.article_no=b.article_no and tmp.packing_qty=b.packing_qty) end)),0) untreadQty " +
				           " from ridata_untread_sm a, ridata_untread_d b, ridata_untread_m f  " +
				           "where a.untread_no=b.untread_no  " +
				           "  and b.warehouse_no=f.warehouse_no " +
				           "  and b.owner_no=f.owner_no " +
				           "  and b.untread_no=f.untread_no " +
				           "  and b.untread_qty-b.check_qty>0 " +
				           "  and a.enterprise_no='"+enterpriseNO+"' " +
				           "  and a.warehouse_no='"+strWarehouseNo+"' " +
				           "  and a.s_untread_no = '"+strSUntreadNo+"' " +
				           "  and a.enterprise_no=b.enterprise_no " +
				           "  and a.enterprise_no=f.enterprise_no  ";
		
		List<String> uncheckNoList = genDao.getListByNativeSql(uncheckNo);
		
		String sql ="select nvl(sum(t.check_qty)/t.packing_qty,0) from ridata_check_pal_tmp t,ridata_untread_sm sm " +
				"where t.enterprise_no=sm.enterprise_no " +
				"and t.warehouse_no=sm.warehouse_no and t.s_untread_no=sm.s_untread_no "+
				 "and t.enterprise_no='"+enterpriseNO+"' " +
				 "and t.warehouse_no='"+strWarehouseNo+"' " +
				 "and t.dock_no='"+strDockNo+"' " +
				 "and sm.untread_no='"+strUntreadNo+"' ";
		List<String> list = genDao.getListByNativeSql(sql);
		
		list.add(uncheckNoList.get(0));
		
		return new MsgRes(true, uncheckNoList.get(0), list.get(0));
	}
}
