package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_ArticleInfoModel;
import com.sealinkin.comm.model.AnswerModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_ExpCheckService;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.odata.ReqGetExpCheckInfoModel;
import com.sealinkin.protocolExchange.odata.StuGetExpCheckInfoModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_ExpCheckImpl implements IOdata_ExpCheckService{
		
	private IGenericManager genDao;
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
		
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}	
	
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
    
	/**
	 * RF获取上位系统下传的出货单号
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes GetSourceExpNo(String strRecvData) throws Exception {
		ReqGetExpCheckInfoModel req_mode = (ReqGetExpCheckInfoModel)JSONObject.toBean(
				JSONObject.fromObject(strRecvData),ReqGetExpCheckInfoModel.class);
		MsgRes msgRes=new MsgRes();
		String Sql = "";
		Sql = " select oem.sourceexp_no as SourceExpNo,oem.owner_no as ownerNo from odata_exp_m oem " + 
			  " where oem.enterprise_no = '" + req_mode.getEnterpriseNo() + "'" +
			  "    and oem.warehouse_no = '" + req_mode.getWareHouseNo() + "'" + 
			  "    and oem.sourceexp_no like '%" + req_mode.getSourceExpNo() + "%'" + 
		      "  order by oem.sourceexp_no ";
		List<StuGetExpCheckInfoModel> stu_model = genDao.getListByNativeSql(Sql,StuGetExpCheckInfoModel.class);
		if(stu_model.size() <= 0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("该出货单号不存在或转历史！");
			return msgRes;
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(stu_model));
		return msgRes;
	}
	
	/**
	 * 出库扫描-整单扫描
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscExpCheckScanOrder(String strRecvData) throws Exception{
		ReqGetExpCheckInfoModel reqMod=JSON.parseObject(strRecvData, ReqGetExpCheckInfoModel.class);
		MsgRes msgRes=new MsgRes();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");

		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(reqMod.getWareHouseNo());//strwarehouse_no
		inList.add(reqMod.getSourceExpNo());//strSourceexp_No	 
		inList.add(reqMod.getUserID());//strUser_Id 
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_HB.p_ODATA_ExpCheckScanOrder");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(0).toString());
			return msgRes; 
		} 
		msgRes.setIsSucc(true); 
		msgRes.setMsg("整单扫描成功");
		return msgRes;
	}
	
	/**
	 * 根据扫描码获取商品信息
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes GetArticleInfoByScan(String strRecvData) throws Exception {
		ReqGetExpCheckInfoModel req_mode = (ReqGetExpCheckInfoModel)JSONObject.toBean(
				JSONObject.fromObject(strRecvData),ReqGetExpCheckInfoModel.class);
		MsgRes msgRes=new MsgRes();
		//获取扫描码对应的商品编号和包装
		List<Bdef_ArticleInfoModel> listArticleInfo = getArticleForBarcodeImpl.getArticleByScanNo
											(req_mode.getWareHouseNo()
							                 ,req_mode.getScanNo()
							                 ,req_mode.getOwnerNo()
							                 ,req_mode.getEnterpriseNo());
		if(listArticleInfo.size() <= 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("商品条码找不到商品信息！");
			return msgRes;
		}
		
		List<StuGetExpCheckInfoModel> listStu = new ArrayList<StuGetExpCheckInfoModel>();
		//获取存在出货通知单中扫描码对应的商品
		for(Bdef_ArticleInfoModel m:listArticleInfo){    
			String Sql = "";
			Sql = " select bda.article_name as ReqObj,oed.row_id as FieldEX1 " + 
				  "   from odata_exp_m oem,odata_exp_d oed,bdef_defarticle bda " + 
				  "  where oed.enterprise_no = oem.enterprise_no " +
				  "    and oed.warehouse_no = oem.warehouse_no" +
				  "    and oed.owner_no = oem.owner_no" +
				  "    and oed.exp_no = oem.exp_no " +
				  "    and bda.enterprise_no = oed.enterprise_no " +
				  "    and bda.owner_no = oed.owner_no " + 
				  "    and bda.article_no = oed.article_no " +
				  "    and oem.enterprise_no = '" + req_mode.getEnterpriseNo() + "'" +
				  "    and oem.warehouse_no = '" + req_mode.getWareHouseNo() + "'" + 
				  "    and oem.sourceexp_no = '" + req_mode.getSourceExpNo() + "'" + 
				  "    and oed.article_no = '" + m.getArticleNo() + "'";
			List<CommSingleDataRequestModel> list = genDao.getListByNativeSql(Sql,CommSingleDataRequestModel.class);
			if(list.size() > 0){
				StuGetExpCheckInfoModel stu = new StuGetExpCheckInfoModel();
				stu.setArticleNo(m.getArticleNo());
				stu.setArticleName(list.get(0).getReqObj());
				stu.setPackingQty(Double.toString(m.getPackingQty()));
				stu.setRowId(list.get(0).getFieldEX1());
				listStu.add(stu);
			}
		}
		if(listStu.size() <= 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该扫描码对应的商品不存在出货单明细中！");
			return msgRes;
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(listStu));
		return msgRes;
	}
	
	/**
	 * 出库扫描-商品扫描保存数据 huangb 20160523
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscExpCheckScanBarcode(String strRecvData) throws Exception{
		ReqGetExpCheckInfoModel reqMod=JSON.parseObject(strRecvData, ReqGetExpCheckInfoModel.class);
		
		MsgRes msgRes=new MsgRes();
		
		//保存商品扫描数据
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo 企业号
		inList.add(reqMod.getWareHouseNo());//strwarehouse_no 仓别
		inList.add(reqMod.getSourceExpNo());//strSourceexp_No 出货来源单号
		inList.add(reqMod.getScanNo());//strScan_No 扫描码
		inList.add(reqMod.getArticleNo());//strArticle_No 商品编码
		inList.add(Double.parseDouble(reqMod.getScanPackingQty()));//nScanPackingQty 扫描商品所对应的包装
		inList.add(Double.parseDouble(reqMod.getScanQty()));//nScanQty 扫描量
		inList.add(Integer.parseInt(reqMod.getRowID()));//nRow_Id 出货扫描明细行号
		inList.add(reqMod.getUserID());//strUser_Id 操作人员
		
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_HB.p_ODATA_ExpCheckScanArticle");
	
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(1).toString());
			return msgRes; 
		}
		
		//获取扫描商品信息
		String Sql = "";
		Sql = " select bda.article_name as articleName " +
			  "        ,d.plan_qty as orderQty " +
			  "        ,d.scan_qty as yesScanQty " +
			  "        ,d.plan_qty - d.scan_qty as noScanQty " +  
              " from odata_exp_check_m m,odata_exp_check_d d,bdef_defarticle bda " + 
              " where d.enterprise_no = m.enterprise_no " + 
              "   and d.owner_no = m.owner_no " + 
              "     and d.exp_no = m.exp_no " + 
              "     and bda.enterprise_no = d.enterprise_no " + 
              "     and bda.owner_no = d.owner_no " + 
              "     and bda.article_no = d.article_no " + 
              "     and m.enterprise_no = '" + reqMod.getEnterpriseNo() + "'" +
			  "     and m.warehouse_no = '" + reqMod.getWareHouseNo() + "'" + 
			  "     and m.sourceexp_no = '" + reqMod.getSourceExpNo() + "'" + 
			  "     and d.row_id = " + reqMod.getRowID() + 
			  "     and d.article_no = '" + reqMod.getArticleNo() + "'";
		
		List<StuGetExpCheckInfoModel> stuMod = genDao.getListByNativeSql(Sql,StuGetExpCheckInfoModel.class);
		if(stuMod.size() <= 0){
			msgRes.setIsSucc(false); 
			msgRes.setMsg("获取商品("+ reqMod.getArticleNo() +")扫描明细失败");
			return msgRes;
		}
		
		for(StuGetExpCheckInfoModel m:stuMod){
			//1：返回整单扫描完成
			m.setScanFlag(resultList.get(0).toString());
		}
		
		msgRes.setIsSucc(true); 
		msgRes.setObj(JSONArray.fromObject(stuMod));
		msgRes.setMsg("商品扫描成功");
		return msgRes;
	}
	
	//获得出库审核信息列表
	@Override
	public ExtListDataBo<Odata_ExpMModel> getOdata_NoCkeckOrderList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery, PageBo poPageBo)
			throws Exception {
		String strSql = " select m.sourceexp_no, m.cust_no,m.owner_no, bdc.cust_name,to_char(m.rgst_date,'yyyy-mm-dd') strRgstDate " +
			" from odata_exp_check_m m, bdef_defcust bdc " + 
			" where bdc.enterprise_no = m.enterprise_no " +
			" and bdc.owner_no = m.owner_no " +
			" and bdc.cust_no = m.cust_no " +
			" and m.status = '12' " +
			" and m.enterprise_no='" + strEnterpriseNo + "' "+
			" and m.warehouse_No='" + strWarehouseNo + "' "+
			" and m.owner_no in(" + strWorkerOwner + ") ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpMModel.class);
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");	
		ExtListDataBo<Odata_ExpMModel> extListBo= new ExtListDataBo<Odata_ExpMModel>(list, intCount);
		return extListBo;
	}
	
	//取出货申请单下拉列表
	@Override
	public List<ComboxBo> getOrderNumberList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strWheresql,
			String str,String strTableName) throws Exception {
		String sql = "";
		sql = " select distinct m.sourceexp_no value,m.sourceexp_no text,m.sourceexp_no dropValue " +
				" from " + strTableName + " m " + 
				//" where m.status = '12' " + 
				" where m.enterprise_no='" + strEnterpriseNo + "' "+
				" and m.warehouse_No='" + strWarehouseNo + "' "+
				" and m.owner_no in(" + strWorkerOwner + ") ";
		
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and m.sourceexp_no like '%" + strWheresql + "%' ";
		}
		
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//取客户编码下拉列表
	@Override
	public List<ComboxBo> getCustomNumberList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strWheresql,
			String str,String strTableName) throws Exception {
		String sql = " select distinct m.cust_no value,m.cust_no text,m.cust_no dropValue " +
				" from " + strTableName + " m " + 
				//" where m.status = '12' " + 
				" where m.enterprise_no='" + strEnterpriseNo + "' "+
				" and m.warehouse_No='" + strWarehouseNo + "' "+
				" and m.owner_no in(" + strWorkerOwner + ") ";;
		
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and m.cust_no like '%" + strWheresql + "%' ";
		}
		
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//获得货主商品编码下拉列表
	@Override
	public List<ComboxBo> getCustomGoodsNumberList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strWheresql,
			String str) throws Exception {
		String sql = " select distinct d.article_no value,d.article_no text,d.article_no dropValue " +
				" from odata_exp_check_d d " + 
				" where d.enterprise_no='" + strEnterpriseNo + "' "+
				" and d.warehouse_No='" + strWarehouseNo + "' "+
				" and d.owner_no in(" + strWorkerOwner + ") ";;
		
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and d.article_no like '%" + strWheresql + "%' ";
		}
		
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//获得状态下拉列表
	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strWheresql,
			String str) throws Exception {
		String sql = " select t.text value,t.text text,t.text dropValue " +
				" from wms_deffieldval t " +
				" where upper(t.table_name) = upper('odata_exp_check_m') ";
				
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//审核操作
	@Override
	public MsgRes checkOrder(String strEnterpriseNo, String strWarehouseNo,
			String strUserId, String strSourceexpNo) throws Exception {
		List resultList=new ArrayList();
		List inList = new ArrayList();
		List outList=new ArrayList();
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strSourceexpNo);
		inList.add(strUserId);
		outList.add("S");
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_HB.p_ODATA_ExpCheckUpdtMaster");
		
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "审核成功", " ");
	}
}















