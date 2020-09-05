package com.sealinkin.ridata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.comm.service.IGetSystemParameterService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.comm.CommSingleDataAnswerModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.ridata.RIBarcodeAnswerModel;
import com.sealinkin.protocolExchange.ridata.RIBarcodeAnswerTTHModel;
import com.sealinkin.protocolExchange.ridata.RIBarcodeRequestModel;
import com.sealinkin.protocolExchange.ridata.RIBarcodeRequestTTHModel;
import com.sealinkin.protocolExchange.ridata.RICheckLabelRequestModel;
import com.sealinkin.protocolExchange.ridata.RICheckSaveRequestModel;
import com.sealinkin.protocolExchange.ridata.RICloseBoxModel;
import com.sealinkin.protocolExchange.ridata.RIGetSaveInfoModel;
import com.sealinkin.protocolExchange.ridata.RISerialNoAnswerModel;
import com.sealinkin.protocolExchange.ridata.RIInputBarcodeRequestModel;
import com.sealinkin.protocolExchange.ridata.RIClosePalModel;
import com.sealinkin.protocolExchange.ridata.RIUntreadNoAndWallNoModel;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckService;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.wms.model.Wms_DefbaseModel;
import com.sealinkin.wms.model.Wms_IDataTypeModel;

/**
 * 返配扫描验收service
 * @author zhouhuan
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_CheckImpl implements IRidata_CheckService{
	
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
	
	private IGetSystemParameterService getSystemParameterImpl;
	
	public IGetSystemParameterService getGetSystemParameterImpl() {
		return getSystemParameterImpl;
	}
	public void setGetSystemParameterImpl(
			IGetSystemParameterService getSystemParameterImpl) {
		this.getSystemParameterImpl = getSystemParameterImpl;
	}
	
	/**
	 * 返配扫描验收列
	 */
	@Override
	public ExtListDataBo<Ridata_CheckDModel> getRidata_CheckDList(
			String enterpriseNO,String strWarehouseNo,String strWhereSql) 
	{
		String strSql = "select b.article_no,b.packing_qty,c.article_name,c.barcode," +
			"(b.untread_qty/b.packing_qty) as pobox," +
			"(b.untread_qty/b.packing_qty) as checkbox," +
			"b.untread_qty - b.check_qty - case when (select sum(tmp.check_qty) from ridata_check_pal_tmp tmp where a.untread_no='"+strWhereSql+"'  " +
			"and a.s_untread_no = tmp.s_untread_no and tmp.warehouse_no='"+strWarehouseNo+"' and tmp.article_no=b.article_no ) is null then 0 " +
			"else (select sum(tmp.check_qty)/tmp.packing_qty from ridata_check_pal_tmp tmp where a.untread_no='"+strWhereSql+"' " +
			"and a.s_untread_no = tmp.s_untread_no  and tmp.warehouse_no='"+strWarehouseNo+"' and tmp.article_no=b.article_no and tmp.packing_qty=b.packing_qty) end untreadQty," +
			"c.expiry_days,f.cust_no, g.cust_name," +
			"case when c.expiry_days=-1 then to_date ('1900-01-01','yyyy-mm-dd') else null end as produceDate," +
			"case when c.expiry_days=-1 then to_date ('1900-01-01','yyyy-mm-dd') else null end as expireDate," +
			"b.packing_qty," +
			//"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) unit,"+
            //"nvl(e.spec, '1*' || b.packing_qty || c.unit) spec," +
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"+
			"a.warehouse_no,a.owner_no," +
			"b.supplier_no,a.untread_no,a.s_untread_no,b.check_qty, F.QUALITY as qualityFlag, f.CLASS_TYPE " +
			"from ridata_untread_sm a,ridata_untread_d b,Bdef_Defarticle c," +
			"bdef_article_packing e, " +
			"ridata_untread_m f , bdef_defcust g " +
			"where a.untread_no=b.untread_no and b.article_no=c.article_no " +
			"and e.article_no(+)=b.article_no and e.packing_qty(+)=b.packing_qty " +
			"and b.warehouse_no=f.warehouse_no and b.owner_no=f.owner_no " +
			"and b.untread_no=f.untread_no and f.cust_no=g.cust_no  " +
			"and b.untread_qty-b.check_qty>0 " +
			"and a.warehouse_no='"+strWarehouseNo+"'" +
			"and a.untread_no = '"+strWhereSql+"' and a.enterprise_no=b.enterprise_no " +
			"and a.enterprise_no=c.enterprise_no " +
			"and b.enterprise_no=e.enterprise_no(+) and a.enterprise_no=f.enterprise_no " +
			"and a.enterprise_no=g.enterprise_no and a.enterprise_no='"+enterpriseNO+"' ";
	
		List<Ridata_CheckDModel> list = genDao.getListByNativeSql(strSql,Ridata_CheckDModel.class,0, 1000);
		ExtListDataBo<Ridata_CheckDModel> extListBo= new ExtListDataBo<Ridata_CheckDModel>(list, 0);
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
				"and rum.status=10 and rum.enterprise_no=t2.enterprise_no " +
				"and rum.enterprise_no='"+enterpriseNO+"'";		
        List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
//	//获得返配汇总单号
//	@Override
//	public List<ComboxBo> getUntreadNoList(
//			String workerOwner,String enterpriseNO,
//			String warehouseNo,String strPageSql, String strWhereSql) throws Exception {
//		String sql="select a.s_untread_no as value," +
//				"a.s_untread_no as text," +
//				"a.s_untread_no dropValue " +
//				"from ridata_untread_mm a where a.warehouse_no='"+warehouseNo+"' " +
//				"and a.status not in ('13','16') and a.enterprise_no='"+enterpriseNO+"'";
//		if(strWhereSql!=null && !strWhereSql.equals(""))
//		{
//			String ws=CommUtil.covtCollectionToWhereSql(strWhereSql);
//			sql=sql+ws;
//		}else{
//			sql=sql+" and 1=2";
//		}
//		if(strPageSql!=null && strPageSql!=""){
//		    sql+="and a.s_untread_no like '%"+strPageSql+"%'";
//		}
//		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
//		return (List<ComboxBo>) list;
//	}
//	
	//保存
	@Override
	public MsgRes save(String enterpriseNO,String strWarehouseNo,String jsonDetail) throws Exception {
		Ridata_CheckDModel pocd = (Ridata_CheckDModel)JSONObject.toBean(
				JSONObject.fromObject(jsonDetail),Ridata_CheckDModel.class);
		
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.parse("1900-01-01");
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");//strsCheckNo
		outList.add("S");//strResult
		
		inList.add(enterpriseNO);//strEnterpriseNO
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(pocd.getOwnerNo());//strOwnerNo
		inList.add(pocd.getSUntreadNo());//strsUntreadNo
		inList.add(pocd.getArticleNo());//strArticleNo
		inList.add(pocd.getBarcode());//strBarcode
		inList.add(pocd.getPackingQty());//nPackingQty
		inList.add(pocd.getCheckQty());//nCheckQty
		inList.add("N");//strPrinterGroupNo
		inList.add(pocd.getDockNo());//strDockNo
		inList.add(pocd.getWorkerNo());//strWorkerNo
		inList.add("1");//strCheckTools
		inList.add(1);//nIsAdd
		inList.add("0");//strQuality商品品质
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
		inList.add(pocd.getLabelNo());//strLabelNo  strLabelNo
		inList.add(pocd.getLabelNo());//strSubLabelNo
		inList.add(pocd.getSupplierNo());//strSupplierNo
		inList.add("3");//strFixPalFlag
		inList.add("0");//strBusinessType
		inList.add(pocd.getDeviceNo());//strDeviceNo
		inList.add(pocd.getLabelId());//strLabelId
		inList.add(pocd.getRsvAttr2());//strStyle
		inList.add("N");//strCellNo
		
		inList.add(pocd.getClassType());//strClassType
		inList.add(pocd.getQualityFlag());//strQualityFlag
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_SaveCheckTTH");
		System.out.println(resultList);
		if(resultList.get(1).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"保存成功！",null);
	}
	
	/**
	 * 获取码头下拉
	 */
	@Override
	public List<ComboxBo> queryDockCombo(String enterpriseNO,String strWarehouseNo,
			String strWheresql) throws Exception {
		String strSql="select a.workstation_no as value,a.workstation_name as text," +
				"'['|| ltrim(a.workstation_no)||']'||a.workstation_name dropValue " +
				"from PNTSET_PRINTER_WORKSTATION a " +
				"where a.warehouse_no= '"+strWarehouseNo+"' and a.enterprise_no='"+enterpriseNO+"' ";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and a.workstation_no like '%"+strWheresql+"%' ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 校验条码
	 */
	@Override
	public MsgRes queryBarcode(String enterpriseNO,String strWarehouseNo, String strBarcode,
			String strOwnerNo, String strSImportNo, String strSUntreadNo)
			throws Exception {
		MsgRes msg=null;
		msg=getArticleForBarcodeImpl.checkBarcode(strWarehouseNo, strBarcode, strOwnerNo,enterpriseNO);
		if(msg.getIsSucc()){
			String strSql="select a.article_no,a.supplier_no,a.packing_qty,a.qty-nvl(b.uncheckqty,0), A.RSV_ATTR2 from "+
			             "(select rus.enterprise_no,rus.warehouse_no," +
			             "rus.s_untread_no,rud.untread_no,rud.article_no," +
			             "rud.packing_qty,rud.supplier_no,rud.untread_qty-rud.check_qty qty, DEF.RSV_ATTR2  "+ 
		                 "from ridata_untread_sm rus,ridata_untread_d rud, BDEF_DEFARTICLE DEF  "+
		                 "where rus.warehouse_no=rud.warehouse_no " +
		                 "and rus.enterprise_no=rud.enterprise_no " +
		                 "and rus.untread_no=rud.untread_no "+
		                 "and rus.s_untread_no='"+strSUntreadNo+"' " +
		                 "and rud.warehouse_no='"+strWarehouseNo+"' " +
		                 "and rud.enterprise_no='"+enterpriseNO+"' " +
		                 "AND DEF.ENTERPRISE_NO=RUS.ENTERPRISE_NO AND DEF.OWNER_NO=RUS.OWNER_NO " +
		                 "AND DEF.ARTICLE_NO=RUD.ARTICLE_NO " +
		                 "and rud.article_no in ("+(msg.getObj().toString())+")) a,"+
		                 "(select c.enterprise_no,c.warehouse_no,c.s_untread_no,c.article_no,sum(c.check_qty) uncheckqty from "+
		                 "ridata_check_pal_tmp c " +
		                 "where c.article_no in("+(msg.getObj().toString())+") " +
		                 "and c.s_untread_no='"+strSUntreadNo+"' " +
		                 "and c.warehouse_no='"+strWarehouseNo+"' " +
		                 "and c.enterprise_no='"+enterpriseNO+"'" +
		                 "group by c.enterprise_no,c.warehouse_no,c.s_untread_no,c.article_no)b "+
		                 "where a.warehouse_no=b.warehouse_no(+) " +
		                 "and a.enterprise_no=b.enterprise_no(+) " +
		                 "and a.s_untread_no=b.s_untread_no(+) "+
		                 "and a.article_no=b.article_no(+) " +
		                 "and a.qty- nvl(b.uncheckqty,0)>0";

			List<String> list=genDao.getListByNativeSql(strSql);
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
	
	/**
	 * 获取临时表数据
	 */
	@Override
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryCheckPalTmp(String enterpriseNO,
			String strWarehouseNo, String strDeviceNo,String strLabelId,String strDockNo) throws Exception {
		String strSql="select a.label_no,a.barcode,a.packing_qty," +
				"sum(a.check_qty)/a.packing_qty as check_qty,a.label_id,b.article_name " +
				"from ridata_check_pal_tmp a," +
				"bdef_defarticle b " +
				"where a.article_no=b.article_no " +
				"and a.device_no='"+strDeviceNo+"' " +
				"and a.label_id='"+strLabelId+"' " +
				"and a.warehouse_no = '"+strWarehouseNo+"' " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no='"+enterpriseNO+"'  " +
			    "group by a.barcode,a.packing_qty,a.label_no,a.label_id,b.article_name ";
		List<Ridata_CheckPalTmpModel> list=null;
		ExtListDataBo<Ridata_CheckPalTmpModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Ridata_CheckPalTmpModel.class,0,1000);
		extListBo= new ExtListDataBo<Ridata_CheckPalTmpModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 封板
	 */
	@Override
	public MsgRes tscClosePal(String strDetail) throws Exception {
		List<Ridata_CheckPalTmpModel> list=JSON.parseArray(strDetail,Ridata_CheckPalTmpModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");//strResult
		
		for(int i=0;i<list.size();i++){
			List inList=new ArrayList();
			inList.add(list.get(i).getEnterpriseNo());
			inList.add(list.get(i).getWarehouseNo());
			inList.add(list.get(i).getOwnerNo());
			inList.add("N");
			inList.add("N");
			inList.add(list.get(i).getLabelNo());
			inList.add(list.get(i).getWorkerNo());
			inList.add(list.get(i).getDockNo());
			inList.add("N");
			inList.add("2");
			resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_ClosePalDivide_main");
			if(resultList.get(0).toString().indexOf("Y")==-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"封板成功","");
	}
	
	/**
	 * 获取标签号（获取扫描墙格子号）
	 */
	@Override
	public MsgRes tscGetLabelNo(String enterpriseNO,String strWarehouseNo,
			String strSupplierNo, String strWorkerNo,
			String strDeviceNo,String SUntreadNo,String strOwnerNo,String strArticleNo) throws Exception {
		//获取波次号
		String strSql="select m.wave_no " +
				"from ridata_untread_mm m " +
				"where m.warehouse_no='"+strWarehouseNo+"' " +
				"and m.s_untread_no='"+SUntreadNo+"' " +
				"and m.enterprise_no='"+enterpriseNO+"'";
		List<String> list=genDao.getListByNativeSql(strSql);
		String waveNo = list.get(0);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");//strLaebelNo
		outList.add("S");//strResult
		inList.add(enterpriseNO);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(SUntreadNo);
		inList.add(strDeviceNo);
		inList.add(strArticleNo);
		//inList.add(strDockNo);
		inList.add(waveNo);
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.p_GET_SCAN_LABEL_ID");
		if(resultList.get(1).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"",resultList.get(0)+","+resultList.get(1));
	}
	
	/**
	 * 获取临时表标签
	 */
	@Override
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryTmpLabelList(
			String enterpriseNo,
			String strWarehouseNo, String strDeviceNo) throws Exception {
		String strSql="select distinct tmp.label_no,TMP.label_id " +
				"from ridata_check_pal_tmp tmp " +
				"WHERE TMP.DEVICE_NO='"+strDeviceNo+"' " +
				"and tmp.warehouse_no = '"+strWarehouseNo+"' " +
				"and tmp.enterprise_no='"+enterpriseNo+"' ";
		List<Ridata_CheckPalTmpModel> list=null;
		ExtListDataBo<Ridata_CheckPalTmpModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Ridata_CheckPalTmpModel.class,0,1000);
		extListBo= new ExtListDataBo<Ridata_CheckPalTmpModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 获取临时表标签明细
	 */
	@Override
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryTmpLabelDetail(
			String enterpriseNo,
			String strWarehouseNo, String strDockNo, String strLabelNo)
			throws Exception {
		String strSql="select tmp.label_no," +
				"tmp.barcode,tmp.article_no,tmp.packing_qty," +
				"sum(tmp.check_qty)/tmp.packing_qty as check_qty,tmp.label_id,bda.article_name  " +
				"from ridata_check_pal_tmp tmp,bdef_defarticle bda " +
				"where tmp.article_no=bda.article_no " +
				"and tmp.enterprise_no=bda.enterprise_no " +
				"and tmp.enterprise_no='"+enterpriseNo+"' " +
			    "and tmp.warehouse_no = '"+strWarehouseNo+"' " +
				"and tmp.label_no in ("+strLabelNo+")  " +
			    "group by tmp.article_no,tmp.packing_qty,tmp.barcode,tmp.label_no,tmp.label_id,bda.article_name  ";
		List<Ridata_CheckPalTmpModel> list=null;
		ExtListDataBo<Ridata_CheckPalTmpModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Ridata_CheckPalTmpModel.class,0,1000);
		extListBo= new ExtListDataBo<Ridata_CheckPalTmpModel>(list, 0);
        return extListBo;
	}
	//获取箱数
	@Override
	public MsgRes getSupperAllotCell(String enterpriseNO,String strWarehouseNo, 
			String strUntreadNo2,String strDockNo, 
			String strUser_Id,String strDeviceNo,String strOwnerNo) throws Exception {
		//获取汇总返配单号
		String strSql="select m.s_untread_no " +
				"from ridata_untread_sm m " +
				"where m.warehouse_no='"+strWarehouseNo+"' " +
				"and m.untread_no='"+strUntreadNo2+"' " +
				"and m.enterprise_no='"+enterpriseNO+"'";
		List<String> list=genDao.getListByNativeSql(strSql);
		String strSUntreadNo = list.get(0);
		
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");//strBoxCount
		outList.add("S");//strResult
		
		inList.add(enterpriseNO);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strSUntreadNo);
		inList.add(strDockNo);
		inList.add(strDeviceNo);
		inList.add(strUser_Id);
		inList.add("N");
		
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.p_RI_SupperAllotCell");
		if(resultList.get(1).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"",resultList.get(0)+","+resultList.get(1));
	}
	
	/*新增品质参数 huangb 20160811*/
	@Override
	public MsgRes InPutSerialNo(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		
		//huangb 20160813
	    /*String strSql="select a.*,sysdate as dateNow  from ridata_untread_mm a " +
				"where a.warehouse_no='"+reqMod.getWarehouseNo()+"' and a.serial_no="+reqMod.getReqObj();*/
		String strSql="";
		strSql = " select a.enterprise_no, a.warehouse_no,a.owner_no,a.s_untread_no,a.serial_no" +
				"         , sysdate as dateNow, a.class_type, nvl(a.device_no,'N') device_no " +
				 "        , (t.quality_flag,a.quality) quality " +
				 "        , nvl(wdd.text,'N') quality_desc " +
                 "   from ridata_untread_mm a " +
                 "   left join (select rcpt.enterprise_no, " +
                 "                     rcpt.warehouse_no, " +
                 "                     rcpt.owner_no, " +
                 "                     rcpt.s_untread_no, " +
                 "                     min(rcpt.quality_flag) quality_flag " +
                 "                from ridata_check_pal_tmp rcpt " +
                 "               group by rcpt.enterprise_no, " +
                 "                        rcpt.warehouse_no, " +
                 "                        rcpt.owner_no, " +
                 "                        rcpt.s_untread_no) t " +
                 "     on t.enterprise_no = a.enterprise_no " +
                 "    and t.warehouse_no = a.warehouse_no " +
                 "    and t.owner_no = a.owner_no " +
                 "    and t.s_untread_no = a.s_untread_no " +
                 "   left join wms_deffieldval wdd " +
                 "     on upper(wdd.table_name) = 'RIDATA_CHECK_PAL' " +
                 "    and upper(wdd.colname) = 'QUALITY_FLAG' " +
                 "    and wdd.value = t.quality_flag " + 
                 "   where a.warehouse_no='"+reqMod.getWarehouseNo()+"' and a.serial_no="+reqMod.getReqObj();
		
		List<RISerialNoAnswerModel> list = genDao.getListByNativeSql(strSql,RISerialNoAnswerModel.class,0,10);
		if(list.size()==0){
			msg.setIsSucc(false);
			msg.setMsg("流水号不存在，请重新输入！");
		}else{
			if(list.get(0).getQualityDesc().equals("N")){
				//验收时,是否选品质:0-不选(默认和返配单据头档一致);1-人工选择
				List<Wms_DefbaseModel> listQualityFlag = 
						getSystemParameterImpl.getParameterList
						(list.get(0).getEnterpriseNo()
						 , reqMod.getWarehouseNo()
						 , list.get(0).getOwnerNo()
						 , "RI_Check_SelectQuality","RI","RI_IC");
				
				for(int i = 0;i < list.size(); i++)
				{
					list.get(i).setIsSelectQuality(listQualityFlag.get(0).getSdefine());
				}
			}else{
				for(int i = 0;i < list.size(); i++)
				{
					list.get(i).setIsSelectQuality("0");
				}
			}
			
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSON(list.get(0)));
		}
		return msg;
	}
	@Override
	public MsgRes InPutBarcode(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		RIBarcodeRequestModel reqMod=JSON.parseObject(strRecvData, RIBarcodeRequestModel.class);
		//根据条码找商品编码
		msg=getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(), reqMod.getBarcode(),reqMod.getOwnerNo(),reqMod.getEnterpriseNo());
		if(!msg.getIsSucc())
		{
			return msg;
		}
		String strSql="select distinct a.warehouse_no,   "+
						"       a.owner_no,   "+
						"       a.s_untread_no,   "+
						"       b.article_no,   "+
						"       d.ARTICLE_NAME,   "+
						"       d.OWNER_ARTICLE_NO,  " +
						"		b.barcode, "+
						//"nvl(e.spec, '1*' || b.packing_qty || '*'|| d.unit) spec,"+
						
						/*"       b.lot_no,   "+
						"       to_char(b.produce_date,'yyyymmdd') produce_date,   "+
						"       to_char(b.expire_date,'yyyymmdd') expire_date,   "+
						"       b.quality,   "+
						"       b.rsv_batch1,   "+
						"       b.rsv_batch2,   "+
						"       b.rsv_batch3,   "+
						"       b.rsv_batch4,   "+
						"       b.rsv_batch5,   "+
						"       b.rsv_batch6,   "+
						"       b.rsv_batch7,   "+
						"       b.rsv_batch8,   " +*/
						"		d.lot_type,"+
						"       d.EXPIRY_DAYS,   "+
						"       e.packing_qty packQty,d.qmin_operate_packing,d.unit_packing,   "+
						"       e.pal_base_qbox,   "+
						"       e.pal_height_qbox,   "+
						"       e.pal_base_qbox || '*' || e.pal_height_qbox as qpalette,   "+
						//"nvl(e.packing_unit, decode(b.packing_qty,d.qmin_operate_packing,d.qmin_operate_packing_unit,d.unit)) packing_unit "+
						"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
			            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,d.qmin_operate_packing) packingUnitQmin,"+
			            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,d.unit_packing) Unit,"+
			            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
			            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,d.qmin_operate_packing) packingSpecQmin,"+
			            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,d.unit_packing) spec"+
						"  from ridata_untread_mm    a,   "+
						"       ridata_check_pal_tmp b,   "+
						"       bdef_defarticle    d,   "+
						"       bdef_article_packing e," +
						"		(select " +
						"			 f.enterprise_no,f.article_no," +
						"           case   "+
						"                 when '"+reqMod.getCheckType()+"' = 'NormalCheck' then   "+
						"                  	max(f.packing_qty)   "+
						"           else   "+
						"                  min(f.packing_qty)   "+
						"			end packing_qty           " +
						"		  from " +
						"			bdef_article_packing f   " +
						"		  where " +
						"           1 = 1   "+
						"   	    and f.article_no in("+msg.getObj().toString()+") "+
						"		  group by  f.enterprise_no,f.article_no" +
						"		 ) f    "+
						" where a.s_untread_no = b.s_untread_no   "+
						"   and b.article_no = d.ARTICLE_NO  " +
						"	and b.article_no=f.article_no     "+
						"   and f.article_no = e.article_no   "+
						"   and f.packing_qty = e.packing_qty" +
						"   and a.enterprise_no = b.enterprise_no" +
						"   and a.enterprise_no=d.enterprise_no" +
						"   and a.enterprise_no=e.enterprise_no " +
						"   and a.enterprise_no=f.enterprise_no" +
						"   and a.enterprise_no='"+reqMod.getEnterpriseNo()+"'"+
						"   and a.warehouse_no='"+reqMod.getWarehouseNo()+"'    "+
						"   and a.serial_no="+reqMod.getSerialNo()+
						" 	and b.article_no in("+msg.getObj().toString()+")  " ;	
		
		List<RIBarcodeAnswerModel> list = genDao.getListByNativeSql(strSql,RIBarcodeAnswerModel.class);
		if(list.size()>0){
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSON(list));			
		}else{
			strSql="select distinct a.article_no,   "+
					"       a.ARTICLE_NAME,   "+
					"       a.OWNER_ARTICLE_NO,    " +
					"		d.barcode," +
					"		a.lot_type,"+
					"       a.SPEC,   "+
					"       a.EXPIRY_DAYS,   "+
					"       b.packing_qty,   "+
					"       b.pal_base_qbox,   "+
					"       b.pal_height_qbox,   "+
					"       b.pal_base_qbox || '*' || b.pal_height_qbox as qpalette,   "+
					"       b.packing_unit   "+
					"  from bdef_defarticle a,   "+
					"       bdef_article_packing b,   "+
					"       (select c.enterprise_no,c.article_no,   "+
					"               case   "+
					"                 when '"+reqMod.getCheckType()+"' = 'NormalCheck' then   "+
					"                  max(c.packing_qty)   "+
					"                 else   "+
					"                  min(c.packing_qty)   "+
					"               end packing_qty   "+
					"        from    "+
					"          bdef_article_packing c   "+
					"        where    "+
					"          1 = 1   "+
					"   	   and c.article_no in("+msg.getObj().toString()+") "+
					"        group by c.enterprise_no,c.article_no) c," +
					"	     bdef_defarticle d   "+
					" where a.article_no = b.article_no   "+
					"   and b.article_no = c.article_no   "+
					"   and b.packing_qty = c.packing_qty  " +
					"   and a.article_no=d.ARTICLE_NO " +
					"	and a.status='0' " +
					"   and a.enterprise_no =b.enterprise_no " +
					"   and a.enterprise_no=c.enterprise_no" +
					"   and a.enterprise_no=d.enterprise_no" +
					"   and a.enterprise_no='"+reqMod.getEnterpriseNo()+"'"+
					"   and a.article_no in("+msg.getObj().toString()+")";
			list = genDao.getListByNativeSql(strSql,RIBarcodeAnswerModel.class);
			if(list.size()>0){
				msg.setIsSucc(true);
				msg.setObj(JSON.toJSON(list));			
			}else
			{
				msg.setIsSucc(false);
				msg.setMsg("此商品信息不完整或已停用，请检查 ！");	
			}
		}
		return msg;
	}
	@Override
	public MsgRes tscCheckSave(String strEnterpriseNo,String strWareHouseNo, 
			String strOwnerNo,
			String strsUntreadNo, 
			String strArticleNo, 
			String strBarcode,
			Double nPackingQty, 
			Double nCheckQty, 
			String strPrinterGroupNo,
			String strDockNo, 
			String strWorkerNo, 
			String strCheckTools,
			Integer nIsAdd, 
			String strQuality, 
			Date dtProduceDate,
			Date dtExpireDate, 
			String strLotNo, 
			String strRSV_BATCH1,
			String strRSV_BATCH2, 
			String strRSV_BATCH3, 
			String strRSV_BATCH4,
			String strRSV_BATCH5, 
			String strRSV_BATCH6, 
			String strRSV_BATCH7,
			String strRSV_BATCH8, 
			String strLabelNo, 
			String strSubLabelNo,
			String strSupplierNo, 
			String strFixPalFlag, 
			String strBusinessType)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");//strsCheckNo
		outList.add("S");//strResult
		
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strsUntreadNo);//strsUntreadNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strBarcode);//strBarcode
		inList.add(nPackingQty);//nPackingQty
		inList.add(nCheckQty);//nCheckQty
		inList.add(strPrinterGroupNo);//strPrinterGroupNo
		inList.add(strDockNo);//strDockNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strCheckTools);//strCheckTools
		inList.add(nIsAdd);//nIsAdd		
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH1)?"N":strRSV_BATCH1);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH2)?"N":strRSV_BATCH2);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH3)?"N":strRSV_BATCH3);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH4)?"N":strRSV_BATCH4);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH5)?"N":strRSV_BATCH5);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH6)?"N":strRSV_BATCH6);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH7)?"N":strRSV_BATCH7);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH8)?"N":strRSV_BATCH8);		
		inList.add(strLabelNo);//strLabelNo  strLabelNo
		inList.add(strSubLabelNo);//strSubLabelNo
		inList.add(strSupplierNo);//strSupplierNo
		inList.add(strFixPalFlag);//strFixPalFlag
		inList.add(strBusinessType);//strBusinessType
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_SaveCheck");
		System.out.println(resultList);
		if(resultList.get(1).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"保存成功！",null);
	}
	@Override
	public MsgRes tscRfCheckSave(String strRecvData) throws Exception {
		RICheckSaveRequestModel reqMod=JSON.parseObject(strRecvData, RICheckSaveRequestModel.class);
		
		return tscCheckSave(reqMod.getEnterpriseNo(),reqMod.getWareHouseNo(), 
				reqMod.getOwnerNo(), 
				reqMod.getSuntreadNo(), 
				reqMod.getArticleNo(), 
				reqMod.getBarcode(), 
				Double.parseDouble(reqMod.getPackingQty()), 
				Double.parseDouble(reqMod.getCheckQty()),
				reqMod.getPrinterGroupNo(), 
				reqMod.getDockNo(), 
				reqMod.getWorkerNo(), 
				reqMod.getCheckTools(), 
				Integer.parseInt(reqMod.getIsAdd()),
				reqMod.getQuality(), 
				DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"),
				DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"),
				reqMod.getLotNo(), 
				reqMod.getRsvBatch1(),
				reqMod.getRsvBatch2(),
				reqMod.getRsvBatch3(),
				reqMod.getRsvBatch4(),
				reqMod.getRsvBatch5(),
				reqMod.getRsvBatch6(),
				reqMod.getRsvBatch7(),
				reqMod.getRsvBatch8(),
				reqMod.getLabelNo(), 
				reqMod.getSubLabelNo(), 
				reqMod.getSupplierNo(), 
				reqMod.getFixPalFlag(), 
				reqMod.getBusinessType());
	}
	@Override
	public MsgRes CheckLabel(String strRecvData) throws Exception {
		RICheckLabelRequestModel reqMod=JSON.parseObject(strRecvData, RICheckLabelRequestModel.class);
		
		String strSql="select a.s_check_no " +
				"from " +
					"ridata_check_pal_tmp a " +
				"where " +
					"not exists(select 1 " +
								"from " +
									"ridata_untread_mm b " +
								"where " +
									"b.serial_no!=" +reqMod.getSerialNo()+
									"and a.s_untread_no=b.s_untread_no" +
								") " +
					"and a.quality!='"+reqMod.getQuality()+"'"+ 
					" and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					" and a.label_no='"+reqMod.getLabelNo()+"' " +
					" and rownum<2";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()!=0)
		{
			return new MsgRes(false,"该板号已被使用","");
		}
		//检查标签表
		strSql="select " +
				"	label_no " +
				"from " +
				"	stock_label_m " +
				"where " +
				"	label_no='"+reqMod.getLabelNo()+"' " +
				"	and status<>'0' " +
				"	and warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				"	and source_no not in(select " +
											"a.s_check_no " +
										"from " +
											"ridata_check_m a," +
											"ridata_untread_mm b," +
											"ridata_untread_sm c  " +
										"where " +
											"b.s_untread_no=c.s_untread_no " +
											"and a.untread_no=c.untread_no " +
											"and b.serial_no= " +reqMod.getSerialNo()+
											" and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
										")";
		list=genDao.getListByNativeSql(strSql);
		if(list.size()!=0)
		{
			return new MsgRes(false,"该板号已被使用","");
		}		

		return new MsgRes(true,"","");
	}
	
	/**
	 * 返配RF验收 输入码头号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes CheckDock(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		
		String strSql="select * " +
				  "from " +
						"PNTSET_PRINTER_WORKSTATION a " +
				  "where " +
						"a.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
						"and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
						"and a.workstation_no='"+reqMod.getReqObj()+"'";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("码头不存在！");
		}else{
			msgRes.setIsSucc(true);
		}
		
		return msgRes;
	}
	
	/***
	 * RF资源试算，并返回相应的单据信息（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscResourceCalculation(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RIUntreadNoAndWallNoModel req=JSON.parseObject(strRecvData, RIUntreadNoAndWallNoModel.class);
		
		String strSql=" select rum.owner_no,rum.quality,rus.s_untread_no,rum.QUALITY " +
				      "   from ridata_untread_m rum ,ridata_untread_sm rus " +
				      "  where rum.enterprise_no=rus.enterprise_no " +
				      "    and rum.warehouse_no=rus.warehouse_no " +
				      "    and rum.owner_no=rus.owner_no " +
				      "    and rum.untread_no=rus.untread_no " +
				      "    and rum.po_no ='"+req.getPoNo()+"' " +
				      "    and rum.enterprise_no='"+req.getEnterpriseNo()+"' " +
				      "    and rum.warehouse_no='"+req.getWarehouseNo()+"' ";
		List<RIUntreadNoAndWallNoModel> list = genDao.getListByNativeSql(strSql,RIUntreadNoAndWallNoModel.class);
		if(list.size()==0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("单号不存在");
			return msgRes;
		}
		strSql="select DOCK_NO from ridata_check_pal_tmp "+
				" where ENTERPRISE_NO='"+req.getEnterpriseNo()+"'"+
				" and WAREHOUSE_NO='"+req.getWarehouseNo()+"'"+
				" and OWNER_NO='"+list.get(0).getOwnerNo()+"'"+
				" and S_UNTREAD_NO='"+list.get(0).getSUntreadNo()+"'"+
				" and DOCK_NO<>'"+req.getDockNo()+"'";

		List<RIUntreadNoAndWallNoModel> list1 = genDao.getListByNativeSql(strSql,RIUntreadNoAndWallNoModel.class);
		if(list1.size()>0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("已在码头"+ list1.get(0).getDockNo() +"被扫描，请重新选择输入单号！");
			return msgRes;
		}
		
		//滞销品和过季品
		if(list.get(0).getQuality().equals("0") || list.get(0).getQuality().equals("1"))
		{
			List inList1=new ArrayList();
			List outList1=new ArrayList();
			List resultList1=new ArrayList();
			
			outList1.add("S");
			
			inList1.add(req.getEnterpriseNo());
			inList1.add(req.getWarehouseNo());
			inList1.add(list.get(0).getOwnerNo());
			inList1.add(list.get(0).getSUntreadNo());
	
			System.out.println(inList1);
			resultList1=genDao.execProc(inList1,outList1,"PKLG_RIDATA.Proc_RIData_Locate_Tmp");	
			if(resultList1.get(0).toString().indexOf("N")!=-1){
				throw new Exception(resultList1.get(0).toString());
			}
		}
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		outList.add("S");
		inList.add(req.getEnterpriseNo());
		inList.add(req.getWarehouseNo());
		inList.add(list.get(0).getOwnerNo());
		inList.add(list.get(0).getSUntreadNo());
		inList.add(req.getDockNo());
		inList.add(req.getWallNo());
		inList.add(req.getUserId());
		inList.add("N");
		
		System.out.println(inList);
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.p_RI_SupperAllotCell");	
		                 
		if(resultList.get(2).toString().indexOf("N")!=-1){
			throw new Exception(resultList.get(2).toString());
		}
		if(resultList.get(2).toString().indexOf("Y")!=-1 && resultList.get(1).toString().equals("1")){
			//throw new Exception(resultList.get(2).toString());
			//该扫描墙号有结束的波次，已打印出封箱标签，请先处理封箱商品！
			//
			RIUntreadNoAndWallNoModel stu = new RIUntreadNoAndWallNoModel();
			stu.setDockNo(resultList.get(1).toString());//此处代表存储过程返回参数strFinishBox
			stu.setWallNo(resultList.get(2).toString());//此处代表存储过程返回参数strOutMsg
			List<RIUntreadNoAndWallNoModel> listAns =new ArrayList<RIUntreadNoAndWallNoModel>();
			listAns.add(stu);
			
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONArray.fromObject(listAns.get(0)));
			return msgRes;
			
		}	
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list.get(0)));
		return msgRes;
	}
	/***
	 * 扫描条码确认（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscScanBarcodeNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RIBarcodeRequestTTHModel req=JSON.parseObject(strRecvData, RIBarcodeRequestTTHModel.class);
		
		//根据扫描的商品条码查找商品信息
		msgRes =getArticleForBarcodeImpl.checkBarcode(req.getWarehouseNo(),req.getBarcode(),req.getEnterpriseNo());			
		if(!msgRes.getIsSucc())
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码有误！");
			return msgRes;
		}
		if(msgRes.getObj().toString().equals("") || msgRes.getObj() == null)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码不存在！");
			return msgRes;
		}
		
		String sql="select rum.untread_no, rum.owner_no ,rum.untread_type, rum.quality, rum.class_type, rud.article_no, bda.barcode,  "+
                "rud.packing_qty, rud.supplier_no, bda.article_name,rumm.wave_no,bda.rsv_attr2 as style ,   "+
                " nvl((select sum(rcpt.check_qty) from ridata_check_pal_tmp rcpt where rus.enterprise_no=rcpt.enterprise_no    "+
                "and rus.warehouse_no=rcpt.warehouse_no    "+
                "and rus.owner_no=rcpt.owner_no    "+
                "and rus.s_untread_no=rcpt.s_untread_no    "+
                "and bda.article_no=rcpt.article_no),0 ) as check_qty, "+
                "rud.untread_qty-rud.check_qty-nvl((  "+
                "select sum(rcpt.check_qty) from ridata_check_pal_tmp rcpt where rus.enterprise_no=rcpt.enterprise_no    "+
                "and rus.warehouse_no=rcpt.warehouse_no    "+
                "and rus.owner_no=rcpt.owner_no    "+
                "and rus.s_untread_no=rcpt.s_untread_no    "+
                "and bda.article_no=rcpt.article_no),0 ) as qty     "+
	           "from ridata_untread_m rum   "+ 
	          "inner join ridata_untread_d rud  "+  
	             "on rum.enterprise_no=rud.enterprise_no    "+
	            "and rum.warehouse_no=rud.warehouse_no    "+
	            "and rum.owner_no=rud.owner_no    "+
	            "and rum.untread_no=rud.untread_no    "+
	          "inner join bdef_defarticle bda    "+
	             "on bda.enterprise_no=rud.enterprise_no    "+
	            "and bda.article_no=rud.article_no    "+
	          "inner join ridata_untread_sm rus    "+
	             "on rum.enterprise_no=rus.enterprise_no    "+
	            "and rum.warehouse_no=rus.warehouse_no    "+
	            "and rum.owner_no=rus.owner_no    "+
	            "and rum.untread_no=rus.untread_no    "+
	          "inner join ridata_untread_mm rumm    "+
	             "on rus.enterprise_no=rumm.enterprise_no   "+ 
	            "and rus.warehouse_no=rumm.warehouse_no    "+
	            "and rus.s_untread_no=rumm.s_untread_no    "+
	            "and rus.owner_no=rumm.owner_no    "+
	          "where rum.enterprise_no='"+req.getEnterpriseNo()+"'    "+
	            "and rum.warehouse_no='"+req.getWarehouseNo()+"'    "+
	            //"and bda.barcode='"+req.getBarcode()+"'   " +
        		" and bda.article_no in ("+ msgRes.getObj().toString() +")"+  
	            "and rum.po_no='"+req.getPoNo()+"' ";
		List<RIGetSaveInfoModel> list = genDao.getListByNativeSql(sql,RIGetSaveInfoModel.class);
		if(list.size()==0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入商品条码有误");
			return msgRes;
		}
		//当前需要保存的扫描量
		Double dCheckQty=Double.parseDouble(req.getBox())*Double.parseDouble(list.get(0).getPackingQty())
			    +Double.parseDouble(req.getPesc());
		//在底层判断
		/*if(Double.parseDouble(list.get(0).getQty()) < dCheckQty){
			System.out.println(dCheckQty);
			System.out.println(list.get(0).getQty());
			
			msgRes.setIsSucc(false);
			msgRes.setMsg("验收数量不能超过计划数量");
			return msgRes;
		}*/
		//扫描量为负数，需要判断已扫描量是否满足扣减
		if(dCheckQty < 0)
		{
			if(Double.parseDouble(list.get(0).getCheckQty()) + dCheckQty < 0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("扣减数量超出已扫描数量！");
				return msgRes;
			}
		}
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(req.getEnterpriseNo());
		inList.add(req.getWarehouseNo());
		inList.add(list.get(0).getOwnerNo());
		inList.add(req.getSUntreadNo());
		inList.add(req.getWallNo());
		inList.add(list.get(0).getArticleNo());
		inList.add(list.get(0).getWaveNo());
		
		System.out.println(inList);
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.p_GET_SCAN_LABEL_ID");	
        
		if(resultList.get(1).toString().indexOf("N")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		
		List inList1=new ArrayList();
		List outList1=new ArrayList();
		List resultList1=new ArrayList();
		outList1.add("S");
		outList1.add("S");
		inList1.add(req.getEnterpriseNo());
		inList1.add(req.getWarehouseNo());
		inList1.add(list.get(0).getOwnerNo());
		inList1.add(list.get(0).getUntreadType());
		inList1.add(req.getSUntreadNo());
		inList1.add(list.get(0).getArticleNo());
		inList1.add(list.get(0).getBarcode());
		inList1.add(Double.parseDouble(list.get(0).getPackingQty()));
		inList1.add(Double.parseDouble(req.getBox())*Double.parseDouble(list.get(0).getPackingQty())
				    +Double.parseDouble(req.getPesc()));
		inList1.add("N");
		inList1.add(req.getDockNo());
		inList1.add(req.getUserId());
		inList1.add("2");   //RF验收
		inList1.add(1);
		inList1.add("0");
		inList1.add(DateUtil.GetDate("19000101", "yyyyMMdd"));
		inList1.add(DateUtil.GetDate("19000101", "yyyyMMdd"));
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add(list.get(0).getSupplierNo());
		inList1.add("3");
		inList1.add("0");
		inList1.add(req.getWallNo());
		inList1.add(resultList.get(0).toString());
		inList1.add(StringUtil.isStrEmpty(list.get(0).getStyle())?"N":list.get(0).getStyle());
		inList1.add("N");
		inList1.add(list.get(0).getClassType());
		inList1.add(list.get(0).getQuality());
		System.out.println(inList1);
		
		resultList1=genDao.execProc(inList1,outList1,"PKLG_RIDATA.P_SaveCheckTTH");	
        
		if(resultList1.get(1).toString().indexOf("N")!=-1){
			throw new Exception(resultList1.get(1).toString());
		}
		
		//返回显示信息
		sql=" select rud.article_no,cust.cust_name,bda.article_name," +
				//"nvl(bap.packing_unit, decode(rud.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit," +
				"f_get_packingunit(rud.enterprise_no,rud.owner_no,rud.article_no,rud.packing_qty) packingUnit,"+
				"f_get_packingunit(rud.enterprise_no,rud.owner_no,rud.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(rud.enterprise_no,rud.owner_no,rud.article_no,bda.unit_packing) Unit,"+
				"f_get_spec(rud.enterprise_no,rud.owner_no,rud.article_no,rud.packing_qty) packingSpec,"+
				"f_get_spec(rud.enterprise_no,rud.owner_no,rud.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(rud.enterprise_no,rud.owner_no,rud.article_no,bda.unit_packing) spec,"+
				" case when rud.packing_qty = 1 then 0 else "+
				" (rud.untread_qty - rud.check_qty - sum(nvl(rcpt.check_qty, 0))) / rud.packing_qty  end unCheckBox, "+
				" case when rud.packing_qty = 1 then (rud.untread_qty - rud.check_qty - sum(nvl(rcpt.check_qty, 0))) / rud.packing_qty "+
				" else mod(rud.untread_qty - rud.check_qty - sum(nvl(rcpt.check_qty, 0)), rud.packing_qty) end uncheckPsc, "+
				" (select case when c.packing_qty = 1 then 0 else  sum(nvl(c.check_qty, 0)) / c.packing_qty end "+
				" from ridata_check_pal_tmp c  where c.enterprise_no = '"+ req.getEnterpriseNo() +"' "+
				" and c.warehouse_no = '"+ req.getWarehouseNo() +"'  and c.device_no = rcpt.device_no "+
				" and c.label_id = rcpt.label_id   group by c.packing_qty) checkBox, "+
				" (select case  when c.packing_qty = 1 then sum(nvl(c.check_qty, 0)) "+
				" else  mod(sum(nvl(c.check_qty, 0)), c.packing_qty) end "+
				" from ridata_check_pal_tmp c  where c.enterprise_no = '"+ req.getEnterpriseNo() +"' "+
				" and c.warehouse_no = '"+ req.getWarehouseNo() +"'  and c.device_no = rcpt.device_no "+
				" and c.label_id = rcpt.label_id  group by c.packing_qty) checkPsc," +
				" (case when rud.packing_qty = 1 then 0  else" +
				" (select sum((sum(d.untread_qty - d.check_qty) - nvl(sum(t.check_qty), 0)) / d.packing_qty)" +
				" from ridata_untread_sm sm join ridata_untread_d d" +
				" on sm.enterprise_no = d.enterprise_no and sm.warehouse_no = d.warehouse_no and sm.owner_no = d.owner_no and sm.untread_no = d.untread_no" +
				" left join ridata_check_pal_tmp t" +
				" on sm.enterprise_no = t.enterprise_no and sm.warehouse_no = t.warehouse_no and sm.s_untread_no = t.s_untread_no and d.article_no = t.article_no" +
				" where sm.enterprise_no = rus.enterprise_no and sm.warehouse_no = rus.warehouse_no and sm.owner_no = rus.owner_no and sm.untread_no = rus.untread_no" +
				" group by d.untread_qty, d.check_qty, d.packing_qty)  end) unCheckSumCQty," +
				" (case when rud.packing_qty = 1 then (select sum(d.untread_qty - d.check_qty) -  nvl(sum(t.check_qty), 0)" +
				" from ridata_untread_sm sm join ridata_untread_d d" +
				" on sm.enterprise_no = d.enterprise_no and sm.warehouse_no = d.warehouse_no and sm.owner_no = d.owner_no and sm.untread_no = d.untread_no" +
				" left join ridata_check_pal_tmp t" +
				" on sm.enterprise_no = t.enterprise_no and sm.warehouse_no = t.warehouse_no and sm.s_untread_no = t.s_untread_no and d.article_no = t.article_no" +
				" where sm.enterprise_no = rus.enterprise_no and sm.warehouse_no = rus.warehouse_no and sm.owner_no = rus.owner_no and sm.untread_no = rus.untread_no)" +
				" else (select sum(mod((sum(d.untread_qty - d.check_qty) - nvl(sum(t.check_qty), 0)), d.packing_qty))" +
				" from ridata_untread_sm sm join ridata_untread_d d" +
				" on sm.enterprise_no = d.enterprise_no and sm.warehouse_no = d.warehouse_no and sm.owner_no = d.owner_no and sm.untread_no = d.untread_no" +
				" left join ridata_check_pal_tmp t" +
				" on sm.enterprise_no = t.enterprise_no and sm.warehouse_no = t.warehouse_no and sm.s_untread_no = t.s_untread_no and d.article_no = t.article_no" +
				" where sm.enterprise_no = rus.enterprise_no and sm.warehouse_no = rus.warehouse_no and sm.owner_no = rus.owner_no and sm.untread_no = rus.untread_no" +
				" group by d.untread_qty, d.check_qty, d.packing_qty)  end) unCheckSumSQty" +
				" from ridata_untread_m rum " +
				" inner join ridata_untread_d rud " +
				"    on rum.enterprise_no=rud.enterprise_no " +
				"   and rum.warehouse_no=rud.warehouse_no " +
				"   and rum.owner_no=rud.owner_no " +
				"   and rum.untread_no=rud.untread_no " +
				" inner join bdef_defarticle bda " +
				"    on bda.enterprise_no=rud.enterprise_no " +
				"   and bda.article_no=rud.article_no " +
				"  left join bdef_article_packing bap " +
				"    on rud.enterprise_no=bap.enterprise_no " +
				"   and rud.article_no=bap.article_no " +
				"   and rud.packing_qty=bap.packing_qty " +
				"  inner join bdef_defcust cust " +
				"    on rum.enterprise_no=cust.enterprise_no " +
				"   and rum.owner_no=cust.owner_no " +
				"   and rum.cust_no=cust.cust_no " +
				" inner join ridata_untread_sm rus " +
				"    on rum.enterprise_no=rus.enterprise_no " +
				"   and rum.warehouse_no=rus.warehouse_no " +
				"   and rum.owner_no=rus.owner_no " +
				"   and rum.untread_no=rus.untread_no " +
				"  left join ridata_check_pal_tmp rcpt " +
				"    on rus.enterprise_no=rcpt.enterprise_no " +
				"   and rus.warehouse_no=rcpt.warehouse_no " +
				"   and rus.s_untread_no=rcpt.s_untread_no " +
				"   and rud.article_no=rcpt.article_no "+
				"   and rcpt.DEVICE_NO='"+req.getWallNo()+"'" +
				" where rum.enterprise_no='"+req.getEnterpriseNo()+"' " +
				"   and rum.warehouse_no='"+req.getWarehouseNo()+"' " +
				"   and rus.s_untread_no='"+req.getSUntreadNo()+"' " +
				"   and bda.article_no='"+list.get(0).getArticleNo()+"' " +
				" group by  rus.enterprise_no, rus.warehouse_no, rus.owner_no,cust.cust_name, "+
				" rus.s_untread_no,rus.untread_no, rcpt.device_no, rcpt.label_id, rud.article_no, "+
				" bda.article_name, "+
				//"nvl(bap.packing_unit, decode(rud.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit," +
				" rud.enterprise_no,rud.owner_no,bda.qmin_operate_packing,bda.unit_packing, "+
				" rud.packing_qty, rud.untread_qty, rud.check_qty";
		List<RIBarcodeAnswerTTHModel> res = genDao.getListByNativeSql(sql,RIBarcodeAnswerTTHModel.class);
		
		if(res.size()==0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("获取商品信息失败");
		}
		res.get(0).setCellNo(resultList.get(0).toString());
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(res.get(0)));		
		return msgRes;
	}
	
	/***
	 * 返配封箱（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscClosBox(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RICloseBoxModel req=JSON.parseObject(strRecvData, RICloseBoxModel.class);
		
		String sql=" select distinct rcpt.label_no,rcpt.quality_flag,rcpt.owner_no, " +
				"rcpt.QUALITY_FLAG,rcpt.CLASS_TYPE,rcpt.UNTREAD_TYPE,rcpt.s_check_no,rcpt.s_untread_no " +
				   "   from ridata_check_pal_tmp rcpt " +
				   "  where rcpt.enterprise_no='"+req.getEnterpriseNo()+"' " +
				   "    and rcpt.warehouse_no='"+req.getWarehouseNo()+"' " +
				   "    and rcpt.label_id='"+req.getLabelId()+"' " +
				   "    and rcpt.device_no='"+req.getWallNo()+"' " +
				   "    and rcpt.check_tools='2' ";
		List<RICloseBoxModel> list = genDao.getListByNativeSql(sql,RICloseBoxModel.class);

		if(list.size()<1){
			msgRes.setIsSucc(false);
			msgRes.setMsg("该格子没有对应的商品");
			return msgRes;
		}
		
		if(req.getDestCellNo()!=null && req.getDestCellNo()!=""
			&&	!req.getDestCellNo().equals("N")){
			String strSql=" select distinct cdc.cell_no  " +
			      "   from cdef_defcell cdc, cdef_defarea cda " +
			      "  where cdc.enterprise_no=cda.enterprise_no " +
			      "    and cdc.warehouse_no = cda.warehouse_no " +
			      "    and cdc.ware_no = cda.ware_no " +
			      "    and cdc.area_no = cda.area_no " +
			      "    and cda.area_usetype='1' " +
			      "    and cda.area_quality='1' " +
			      "    and cdc.cell_status = '0' " +
			      "    and cdc.check_status = '0' " +
			      "    and cda.Area_Attribute = '0' " +
			      "    and cda.attribute_type='0' " +
			      "    and cdc.enterprise_no='"+req.getEnterpriseNo()+"' " +
			      "    and cdc.warehouse_no = '"+req.getWarehouseNo()+"' " +
			      "    and cdc.cell_no='"+req.getDestCellNo()+"' ";	
			List checkList = genDao.getListByNativeSql(strSql);
			if(checkList.size()<=0){
				msgRes.setIsSucc(false);
				msgRes.setMsg("找不到相应的储位");
				return msgRes;
			}
		}
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(req.getEnterpriseNo());
		inList.add(req.getWarehouseNo());
		inList.add(list.get(0).getOwnerNo());
		inList.add(list.get(0).getUntreadType());
		inList.add(list.get(0).getClassType());
		inList.add(list.get(0).getQualityFlag());
		inList.add("N");
		inList.add("N");
		inList.add(list.get(0).getLabelNo());
	
		inList.add(req.getUserId());
		inList.add(req.getDockNo());
		inList.add(StringUtil.isStrEmpty(req.getDestCellNo())?"N":req.getDestCellNo());
		inList.add("2");//strPrintFlag
		
		System.out.println(inList);
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_TTHClosePal_Main");	
        
		if(resultList.get(0).toString().indexOf("N")!=-1){
			throw new Exception(resultList.get(0).toString());
		}		
		
		msgRes.setIsSucc(true);
		return msgRes;
	}
	@Override
	public MsgRes getCellNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		
		String strSql=" select distinct cdc.cell_no  " +
				      "   from cdef_defcell cdc, cdef_defarea cda " +
				      "  where cdc.enterprise_no=cda.enterprise_no " +
				      "    and cdc.warehouse_no = cda.warehouse_no " +
				      "    and cdc.ware_no = cda.ware_no " +
				      "    and cdc.area_no = cda.area_no " +
				      "    and cda.area_usetype='1' " +
				      "    and cda.area_quality='1' " +
				      "    and cdc.cell_status = '0' " +
				      "    and cdc.check_status = '0' " +
				      "    and cda.Area_Attribute = '0' " +
				      "    and cda.attribute_type='0' " +
				      "    and cdc.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
				      "    and cdc.warehouse_no = '"+reqMod.getWarehouseNo()+"' ";
		
		List list = genDao.getListByNativeSql(strSql);
		
		msgRes.setIsSucc(true);
		if(list.size()>0){		
			msgRes.setObj(list.get(0));
		}
		
		return msgRes;
	}
	
	//校验扫描墙数据是否正确
	@Override
	public MsgRes getqualityByWallNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		
		String sql=" select distinct a.Quality_Flag " +
				   "   from ridata_check_pal_tmp a " +
				   "  where a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
				   "    and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				   "    and a.device_no='"+reqMod.getReqObj()+"' " +
				   "    and a.device_no<>'N' ";
		
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()==0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("该扫描墙没有要扫描的数据，或扫描墙不存在");
			return msgRes;
		}else if(list.size()>1){
			msgRes.setIsSucc(false);
			msgRes.setMsg("扫描墙数据有问题");
			return msgRes;
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(list.get(0));
		return msgRes;
	}
	/***
	 * 获取PO_NO
	 */
	@Override
	public MsgRes GetPoNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RIUntreadNoAndWallNoModel req=JSON.parseObject(strRecvData, RIUntreadNoAndWallNoModel.class);
		
		String strSql=" select rum.po_no as AnsObj,t.cust_name " +
				      "   from ridata_untread_m rum,bdef_defcust t " +
				      "  where rum.po_no like '%"+req.getPoNo()+"' " +
				  	  "   and rum.enterprise_no=t.enterprise_no " +
					  "   and rum.owner_no=t.owner_no " +
					  "   and rum.cust_no=t.cust_no " +
				      "    and rum.enterprise_no='"+req.getEnterpriseNo()+"' " +
				      "    and rum.warehouse_no='"+req.getWarehouseNo()+"' " +
				      "    and rum.status not in ('13','16')";
		List<CommSingleDataAnswerModel> list = genDao.getListByNativeSql(strSql,CommSingleDataAnswerModel.class);
		if(list.size()==0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("单号不存在");
			return msgRes;
		}
				
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list));
		return msgRes;
	}
	
	/***
	 * 铁越RF反配扫描条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes GetMixRIScanBarcodeNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RIInputBarcodeRequestModel req=JSON.parseObject(strRecvData, RIInputBarcodeRequestModel.class);
		
		//根据扫描的商品条码查找商品信息  新增委托业主查询req.getOwnerNo() huangb 20160815
		msgRes =getArticleForBarcodeImpl.checkBarcode(req.getWarehouseNo(),req.getBarcode(),req.getOwnerNo(),req.getEnterPriseNo());			
		if(!msgRes.getIsSucc())
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码有误！");
			return msgRes;
		}
		if(msgRes.getObj().toString().equals("") || msgRes.getObj() == null)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码不存在！");
			return msgRes;
		}
		
		String sql="";
		//获取汇总信息 huangb 20160815
		sql = " select distinct rusm.untread_type, rumm.class_type " + 
			  "   from ridata_untread_mm rumm, ridata_untread_sm rusm " + 
			  "  where rusm.enterprise_no = rumm.enterprise_no " + 
			  "    and rusm.warehouse_no = rumm.warehouse_no " + 
			  "    and rusm.owner_no = rumm.owner_no " + 
			  "    and rusm.s_untread_no = rumm.s_untread_no " +
			  "    and rumm.enterprise_no = '"+req.getEnterPriseNo()+"' " +
			  "    and rumm.warehouse_no = '"+req.getWarehouseNo()+"' " +
			  "    and rumm.owner_no = '"+req.getOwnerNo()+"' " +
			  "    and rumm.SERIAL_NO = '"+req.getSerialNo()+"' ";
		List<RISerialNoAnswerModel> listUntreadMM = genDao.getListByNativeSql(sql,RISerialNoAnswerModel.class);
		if(listUntreadMM.size()<=0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("汇总单号不存在");
			return msgRes;
		}
		
		//获取是否允许超品验收系统参数 huangb 20160815
		String strCheckOverFlag = "";
		List<Wms_IDataTypeModel> listCheckOverFlag = getSystemParameterImpl.getRidataOrderStrategy
				(req.getEnterPriseNo(), req.getWarehouseNo(), req.getOwnerNo()
				, listUntreadMM.get(0).getUntreadType(),listUntreadMM.get(0).getClassType(),req.getQualityFlag()
				,"over_qty_flag");
		if(listCheckOverFlag.size()==0){
			strCheckOverFlag="0";
		}else{
			strCheckOverFlag=listCheckOverFlag.get(0).getColumnValue();
		}
		
	    sql=" SELECT V.*,NVL(NVL(BWP.PAL_BASE_QBOX,BDP.PAL_BASE_QBOX),'999') || '*' " +
				"|| NVL(NVL(BWP.PAL_HEIGHT_QBOX,BDP.PAL_HEIGHT_QBOX),'999') QPALETTE," +
				//"nvl(bdp.packing_unit, decode(v.packing_qty,v.qmin_operate_packing,v.qmin_operate_packing_unit,v.unit)) PACKINGUNIT,"+
				"NVL(SUM(T.CHECK_QTY),0) CHECKQTY  FROM (SELECT MM.ENTERPRISE_NO ENTERPRISENO," +
				"MM.WAREHOUSE_NO WAREHOUSENO, MM.SERIAL_NO SERIALNO,MM.QUALITY QUALITYFLAG,D.UNTREAD_NO UNTREADNO, D.PACKING_QTY packQty," +
				"A.LOT_TYPE LOTTYPE,D.ARTICLE_NO ARTICLENO,  D.UNTREAD_QTY QTY,A.ARTICLE_NAME ARTICLENAME," +
				//"nvl(bdp.spec, '1*' || v.packing_qty || '*'|| v.unit) spec," +
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,a.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,a.unit_packing) Unit,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,a.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,a.unit_packing) spec,"+
				"a.qmin_operate_packing,a.unit_packing," +
				"A.RSV_ATTR1 RSVBATCH1, A.RSV_ATTR2 RSVBATCH2,A.RSV_ATTR3 RSVBATCH3,A.RSV_ATTR4 RSVBATCH4, SM.UNTREAD_TYPE UNTREADTYPE," +
				"A.RSV_ATTR5 RSVBATCH5, A.RSV_ATTR6 RSVBATCH6,A.RSV_ATTR7 RSVBATCH7,A.RSV_ATTR8 RSVBATCH8,MM.DEVICE_NO DEVICENO,MM.CLASS_TYPE CLASSTYPE,D.QUALITY, " +
				"A.BARCODE,A.EXPIRY_DAYS EXPIRYDAYS,MM.S_UNTREAD_NO SUNTREADNO,MM.OWNER_NO OWNERNO,A.QMIN_OPERATE_PACKING QminOperatePacking FROM RIDATA_UNTREAD_MM MM," +
				"RIDATA_UNTREAD_SM SM, RIDATA_UNTREAD_D D, BDEF_DEFARTICLE A WHERE MM.ENTERPRISE_NO = SM.ENTERPRISE_NO" +
				" AND MM.WAREHOUSE_NO = SM.WAREHOUSE_NO AND MM.S_UNTREAD_NO = SM.S_UNTREAD_NO " +
				"AND SM.WAREHOUSE_NO = D.WAREHOUSE_NO AND SM.UNTREAD_NO = D.UNTREAD_NO" +
				" AND MM.ENTERPRISE_NO = A.ENTERPRISE_NO AND MM.OWNER_NO = A.OWNER_NO AND D.ARTICLE_NO = A.ARTICLE_NO " +
				" AND MM.ENTERPRISE_NO = '"+req.getEnterPriseNo()+"' " +
				"AND MM.WAREHOUSE_NO = '"+req.getWarehouseNo()+"' AND D.ARTICLE_NO IN ("+msgRes.getObj().toString()+") " +
				"and mm.owner_no = '"+req.getOwnerNo()+"'" + //huangb 20160815
				"AND MM.SERIAL_NO = '"+req.getSerialNo()+"' AND D.STATUS = '10') V " +
				"LEFT JOIN BDEF_WAREHOUSE_PACKING BWP ON V.ARTICLENO = BWP.ARTICLE_NO  AND V.packQty = BWP.PACKING_QTY " +
				"AND BWP.WAREHOUSE_NO = V.WAREHOUSENO LEFT JOIN BDEF_ARTICLE_PACKING BDP " +
				"ON V.ARTICLENO = BDP.ARTICLE_NO AND V.packQty = BDP.PACKING_QTY LEFT JOIN RIDATA_CHECK_PAL_TMP T ON V.ENTERPRISENO = T.ENTERPRISE_NO " +
				"AND V.WAREHOUSENO = T.WAREHOUSE_NO AND V.SUNTREADNO = T.S_UNTREAD_NO AND V.ARTICLENO = T.ARTICLE_NO " +
				"GROUP BY V.ENTERPRISENO,V.WAREHOUSENO,V.SERIALNO,V.QUALITY,V.UNTREADNO,V.LOTTYPE,V.ARTICLENO,V.QTY,V.ARTICLENAME,bdp.spec," +
				"V.RSVBATCH1,V.RSVBATCH2,V.RSVBATCH3,V.RSVBATCH4,V.RSVBATCH5,V.RSVBATCH6,V.RSVBATCH7,V.RSVBATCH8,V.BARCODE,V.UNTREADTYPE,V.DEVICENO,V.CLASSTYPE,V.QUALITYFLAG," +
				"V.EXPIRYDAYS,V.SUNTREADNO,V.OWNERNO,V.QminOperatePacking ,NVL(NVL(BWP.PAL_BASE_QBOX,BDP.PAL_BASE_QBOX)," +
				"'999') || '*' || NVL(NVL(BWP.PAL_HEIGHT_QBOX,BDP.PAL_HEIGHT_QBOX),'999')," +
				"BDP.PACKING_UNIT,v.qmin_operate_packing,v.unit_packing,v.unit,V.packQty, " +
				"v.packingUnit,v.packingUnitQmin,v.packingSpec,v.packingSpecQmin,v.spec";
		List<RIInputBarcodeRequestModel> list = genDao.getListByNativeSql(sql,RIInputBarcodeRequestModel.class);
		if(list.size()<=0){

			if(strCheckOverFlag.equals("2")){
				sql=" select t.*, " +
					" nvl(nvl(bwp.pal_base_qbox, t.pal_base_qbox), '999') " +
					" || '*' || nvl(nvl(bwp.pal_height_qbox, t.pal_height_qbox), '999') qpalette, " +
					" f_get_spec(t.enterprise_no, t.owner_no, t.article_no, t.unit_packing) spec, " +
					" f_get_packingunit(t.enterprise_no, t.owner_no, t.article_no, t.unit_packing) unit, " +
					" f_get_packingunit(t.enterprise_no, t.owner_no, t.article_no, t.qmin_operate_packing) packingUnitQmin, " +
					" f_get_packingunit(t.enterprise_no, t.owner_no, t.article_no, t.packQty) packingUnit, " +
					" 0 as qty " +
					" from (select bda.enterprise_no,bda.owner_no,bda.article_no,bda.barcode,bda.article_name " +
					"              ,bda.unit_packing,bda.qmin_operate_packing,bda.lot_type,bda.expiry_days, " +
					"              max(nvl(bap.packing_qty, nvl(bda.qmin_operate_packing, bda.unit_packing))) packQty, " +
					"              max(bap.pal_base_qbox) pal_base_qbox,max(bap.pal_height_qbox) pal_height_qbox," +
					"              bda.rsv_attr1 rsvbatch1, bda.rsv_attr2 rsvbatch2,bda.rsv_attr3 rsvbatch3,bda.rsv_attr4 rsvbatch4, " +
					"              bda.rsv_attr5 rsvbatch5, bda.rsv_attr6 rsvbatch6,bda.rsv_attr7 rsvbatch7,bda.rsv_attr8 rsvbatch8 " +
					"         from bdef_defarticle bda " +
					"         left join bdef_article_packing bap " +
					"           on bap.article_no = bda.article_no " +
					"        group by bda.enterprise_no, bda.owner_no, bda.article_no, bda.barcode, bda.article_name, " +
					"                 bda.unit_packing,bda.qmin_operate_packing,bda.lot_type,bda.expiry_days," +
					"                 bda.rsv_attr1, bda.rsv_attr2,bda.rsv_attr3,bda.rsv_attr4," +
					"                 bda.rsv_attr5, bda.rsv_attr6,bda.rsv_attr7,bda.rsv_attr8) t " +
					" left join bdef_warehouse_packing bwp " +
					"        on bwp.enterprise_no = t.enterprise_no " +
					"       and bwp.warehouse_no = '"+req.getWarehouseNo()+"' " +
					"       and bwp.article_no = t.article_no " +
					"       and bwp.packing_qty = t.packQty " +
					"     where t.enterprise_no = '"+req.getEnterPriseNo()+"'" +
					"       and t.article_no in ("+msgRes.getObj().toString()+")";
				List<RIInputBarcodeRequestModel> listOver = genDao.getListByNativeSql(sql,RIInputBarcodeRequestModel.class);
				if(listOver.size()<=0){
					msgRes.setIsSucc(false);
					msgRes.setMsg("商品不存在");
					return msgRes; 
				}else{
					msgRes.setIsSucc(true);
					msgRes.setObj(JSONArray.fromObject(listOver));	
					return msgRes;
				}
			}else{
				msgRes.setIsSucc(false);
				msgRes.setMsg("输入商品条码有误");
				return msgRes; 
			}
			
		}else{
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONArray.fromObject(list));	
			return msgRes;
		}
		
	}

	/***
	 * 铁越RF反配验收取批次信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes GetMixRILotInfo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RICheckSaveRequestModel req=JSON.parseObject(strRecvData, RICheckSaveRequestModel.class);
		
		
		String sql=" SELECT M.ARTICLE_NO ARTICLENO,M.LOT_NO LOTNO,TO_CHAR(M.PRODUCE_DATE,'YYYYMMDD') PRODUCEDATE," +
				"TO_CHAR(M.EXPIRE_DATE,'YYYYMMDD') EXPIREDATE,M.WAREHOUSE_NO WAREHOUSENO,M.ENTERPRISE_NO ENTERPRISENO " +
				"FROM BDEF_ARTICLE_LOT_MANAGE M WHERE M.ARTICLE_NO = '"+req.getArticleNo()+"' " +
				"AND TRUNC(M.PRODUCE_DATE)=TRUNC(TO_DATE('"+req.getProduceDate()+"','YYYYMMDD')) " +
				"AND M.ENTERPRISE_NO ='"+req.getEnterpriseNo()+"' AND M.WAREHOUSE_NO ='"+req.getWareHouseNo()+"'  ";
		List<RICheckSaveRequestModel> list = genDao.getListByNativeSql(sql,RICheckSaveRequestModel.class);
		if(list.size()==0){
			list.get(0).setLotNo("N");
			list.get(0).setEnterpriseNo(req.getWareHouseNo());
			list.get(0).setProduceDate(req.getProduceDate());
			list.get(0).setWareHouseNo(req.getWareHouseNo());
			list.get(0).setArticleNo(req.getArticleNo()); 
			
			return msgRes; 
		} 
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list));	
		return msgRes;
	}
	/***
	 * 铁越RF反配验收
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscMixRICheckSave(String strRecvData) throws Exception {
		RICheckSaveRequestModel reqMod=JSON.parseObject(strRecvData, RICheckSaveRequestModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");//strsCheckNo
		outList.add("S");//strOutLabelNo   
		outList.add("S");//strResult     
		  
        
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWareHouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSuntreadNo());//strsUntreadNo
		inList.add(reqMod.getUntreadType());//strUntreadType
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getBarcode());//strBarcode
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty
		inList.add(Double.parseDouble(reqMod.getCheckQty()));//nCheckQty
		inList.add(reqMod.getPrinterGroupNo());//strPrinterGroupNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getWorkerNo());//strWorkerNo
		inList.add(reqMod.getCheckTools());//strCheckTools
		inList.add(Integer.parseInt(reqMod.getIsAdd()));//nIsAdd		
		inList.add(StringUtil.isStrEmpty(reqMod.getQuality())?"0":reqMod.getQuality());
		inList.add(DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"));
		inList.add(DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"));
		inList.add(StringUtil.isStrEmpty(reqMod.getLotNo())?"N":reqMod.getLotNo());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());	

		//inList.add(reqMod.getLabelID());//strLabeId --N
		inList.add(reqMod.getLabelNo());//strLabelNo 
		inList.add(reqMod.getSubLabelNo());//strSubLabelNo
		inList.add(reqMod.getSupplierNo());//strSupplierNo
		inList.add(reqMod.getFixPalFlag());//strFixPalFlag
		inList.add(reqMod.getBusinessType());//strBusinessType

		inList.add(reqMod.getDeviceNo());//strDeviceNo
		inList.add(reqMod.getCellNo());//strCellNo  ---N
		inList.add(StringUtil.isStrEmpty(reqMod.getQualityFlag())?"0":reqMod.getQualityFlag());
		inList.add(reqMod.getBatchNo());//strBatchNo  ---N
		inList.add(reqMod.getClassType());//strClassType

		System.out.println(inList);
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_RF_SaveCheck");
		System.out.println(resultList);
		if(resultList.get(2).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(2).toString());
		}  
		RIClosePalModel C = new RIClosePalModel();
		C.setLabelNo(resultList.get(1).toString()); 
		C.setSCheckNo(resultList.get(0).toString());  
		MsgRes msgRes=new MsgRes();
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(C));	
		return msgRes;
	}
	

	/***
	 * 铁越RF反配封板
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscMixRIClosePal(String strRecvData) throws Exception {
		RIClosePalModel reqMod=JSON.parseObject(strRecvData, RIClosePalModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");//strsCheckNo 
		   
		inList.add(reqMod.getEnterPriseNo());//strEnterPriseNo
		inList.add(reqMod.getWareHouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSUntreadNo());//strsUntreadNo

		inList.add(reqMod.getUntreadType());//strUntreadType
		inList.add(reqMod.getClassType());//strClassType
		inList.add(reqMod.getQualityFlag());//strQualityFlag
		
		inList.add(reqMod.getSCheckNo());//strsCheckNo
		inList.add(reqMod.getLabelNo());//strLabelNo
		inList.add(reqMod.getWorkerNo());//strWorkerNo
		inList.add(reqMod.getDockNo());//strDockNo 
				
		inList.add(reqMod.getPrintFlag());//strPrintFlag  打印标识，0：不打印，1：打印表单，2：打印标签

		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_SaveClosePal_main");
		System.out.println(inList);
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"封板成功！",null);
	}
	

	/***
	 * 铁越RF获取反配验收信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes GetMixRICheckInfo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RIClosePalModel req=JSON.parseObject(strRecvData, RIClosePalModel.class);
		
		String sql="SELECT DISTINCT T.ENTERPRISE_NO ENTERPRISENO,T.WAREHOUSE_NO WAREHOUSENO,T.OWNER_NO OWNERNO,T.S_CHECK_NO SCHECKNO,T.S_UNTREAD_NO SUNTREADNO,T.RGST_NAME WORKERNO,T.QUALITY_FLAG QUALITYFLAG,T.UNTREAD_TYPE UNTREADTYPE," +
				"T.LABEL_NO LABELNO,T.DOCK_NO DOCKNO,MM.CLASS_TYPE CLASSTYPE,MM.quality qualityflag FROM RIDATA_CHECK_PAL_TMP T,RIDATA_UNTREAD_MM MM WHERE" +
				" T.ENTERPRISE_NO = MM.ENTERPRISE_NO AND T.WAREHOUSE_NO = MM.WAREHOUSE_NO AND T.S_UNTREAD_NO = MM.S_UNTREAD_NO AND T.OWNER_NO = MM.OWNER_NO AND T.ENTERPRISE_NO = '"+req.getEnterPriseNo()+"' " +
				"AND T.WAREHOUSE_NO = '"+req.getWareHouseNo()+"' AND T.OWNER_NO = '"+req.getOwnerNo()+"' AND T.S_UNTREAD_NO = '"+req.getSUntreadNo()+"' " +
				//根据品质查找可以封板的数据 huangb 20160816
				//"AND T.RGST_NAME ='"+req.getWorkerNo()+"' " +
				"AND T.QUALITY_FLAG ='"+req.getQualityFlag()+"' " +
				"AND T.DOCK_NO ='"+req.getDockNo()+"' ";
		List<RIClosePalModel> list = genDao.getListByNativeSql(sql,RIClosePalModel.class);
		if(list.size()==0){  
			String sql1="SELECT t.class_type classtype,t.quality qualityflag  FROM ridata_untread_mm t WHERE" +
					" T.ENTERPRISE_NO = '"+req.getEnterPriseNo()+"' " +
					"AND T.WAREHOUSE_NO = '"+req.getWareHouseNo()+"' AND T.OWNER_NO = '"+req.getOwnerNo()+"' AND T.S_UNTREAD_NO = '"+req.getSUntreadNo()+"' ";
					//"AND T.RGST_NAME ='"+req.getWorkerNo()+"' "; haungb 20160816
			List<RIClosePalModel> list1 = genDao.getListByNativeSql(sql1,RIClosePalModel.class);
			
			RIClosePalModel C = new RIClosePalModel();
			 
			C.setDockNo(req.getDockNo());
			C.setEnterPriseNo(req.getEnterPriseNo());
			C.setLabelNo("N");
			C.setOwnerNo(req.getOwnerNo());
			C.setSCheckNo("");
			C.setSUntreadNo(req.getSUntreadNo());
			C.setWareHouseNo(req.getWareHouseNo());
			C.setWorkerNo(req.getWorkerNo());
			if(list1.size() != 0){
			C.setClassType(list1.get(0).getClassType());
			C.setQualityFlag(list1.get(0).getQualityFlag());
			}
			else
			{
				C.setClassType("");
				C.setQualityFlag("");
			}
		    list.add(C); 
		} 
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list));	
		return msgRes;
	}
}
