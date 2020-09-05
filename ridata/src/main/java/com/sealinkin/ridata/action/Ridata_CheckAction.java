package com.sealinkin.ridata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 返配扫描验收action
 * @author zhouhuan
 */
public class Ridata_CheckAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IRidata_CheckService ridata_CheckImpl;
	private String strWhereSql;
	private String jsonMaster;
	private String jsonDetail;
	private String queryStr;
	private String strFlag;
	private String strPageSql;
	private String strOwnerNo;
	private String strBarcode;
	private String strSImportNo;
	private String strSUntreadNo;
	private String strSupplierNo;
	private String strWorkerNo;
	private String strDockNo;
	private String strUntreadNo2;
	private String strLabelNo;
	private String strUser_Id;//操作人
	private String strDeviceNo;//设备号
	private String strArticleNo;
	private String strLabelId;//箱号（格子号）
	/**
	 * 返配扫描验收列
	 * @throws Exception
	 */
	public void getRidata_CheckDList()
	{
		try {
			ExtListDataBo<Ridata_CheckDModel> list=ridata_CheckImpl.getRidata_CheckDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strUntreadNo2);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/*
	 * 获取货主...下拉
	 */
	public void getComboList() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=ridata_CheckImpl.getComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag());
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
/*	//获得返配汇总单号
	public void getUntreadNoList(){
		try {
			List<ComboxBo> list = ridata_CheckImpl.getUntreadNoList(
					strOwnerNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strPageSql,strWhereSql);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}*/
	
	//保存
	public void save(){
		try {
			MsgRes msg = ridata_CheckImpl.save(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),jsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取码头下拉
	 */
	public void queryDockCombo(){
		try {
			List<ComboxBo> list=ridata_CheckImpl.queryDockCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWhereSql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 校验条码
	 */
	public void queryBarcode(){
		try {
			MsgRes msg=ridata_CheckImpl.queryBarcode(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strBarcode, 
					strOwnerNo, 
					strSImportNo, 
					strSUntreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取临时表数据
	 */
	public void queryCheckPalTmp(){
		try {
			ExtListDataBo<Ridata_CheckPalTmpModel> list=ridata_CheckImpl.queryCheckPalTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strDeviceNo,strLabelId,strDockNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取单号的状态
	 *//*
	public void getSUntreadNoStatus(){
		try {
			List<String> list=ridata_CheckImpl.getSUntreadNoStatus(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strSUntreadNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}*/
	
	/**
	 * 封板
	 */
	public void tscClosePal(){
		try {
			MsgRes msg=ridata_CheckImpl.tscClosePal(jsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			//super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	/**
	 * 获取箱号
	 */
	public void tscGetLabelNo(){
		try {
			MsgRes msg=ridata_CheckImpl.tscGetLabelNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strSupplierNo, 
					strWorkerNo,
					strDeviceNo,
					strSUntreadNo,strOwnerNo,strArticleNo
					);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			//super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	//查询封箱列表（封箱界面）
	public void queryTmpLabelList(){
		try {
			ExtListDataBo<Ridata_CheckPalTmpModel> list=ridata_CheckImpl.queryTmpLabelList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strDeviceNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//查询封箱明细列表（封箱界面）
	public void queryTmpLabelDetail(){
		try {
			ExtListDataBo<Ridata_CheckPalTmpModel> list=ridata_CheckImpl.queryTmpLabelDetail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strDockNo,strLabelNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取箱数
	public void getSupperAllotCell(){
		try {
			MsgRes msg=ridata_CheckImpl.getSupperAllotCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strUntreadNo2, 
					strDockNo,
					strUser_Id,
					strDeviceNo,
					strOwnerNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			//super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	
	public IRidata_CheckService getRidata_CheckImpl() {
		return ridata_CheckImpl;
	}
	public void setRidata_CheckImpl(IRidata_CheckService ridata_CheckImpl) {
		this.ridata_CheckImpl = ridata_CheckImpl;
	}

	public String getStrWhereSql() {
		return strWhereSql;
	}
	public void setStrWhereSql(String strWhereSql) {
		this.strWhereSql = strWhereSql;
	}
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}

	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}

	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrPageSql() {
		return strPageSql;
	}
	public void setStrPageSql(String strPageSql) {
		this.strPageSql = strPageSql;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrBarcode() {
		return strBarcode;
	}
	public void setStrBarcode(String strBarcode) {
		this.strBarcode = strBarcode;
	}

	public String getStrSImportNo() {
		return strSImportNo;
	}
	public void setStrSImportNo(String strSImportNo) {
		this.strSImportNo = strSImportNo;
	}
	
	public String getStrSUntreadNo() {
		return strSUntreadNo;
	}
	public void setStrSUntreadNo(String strSUntreadNo) {
		this.strSUntreadNo = strSUntreadNo;
	}
	public String getStrSupplierNo() {
		return strSupplierNo;
	}

	public void setStrSupplierNo(String strSupplierNo) {
		this.strSupplierNo = strSupplierNo;
	}
	public String getStrWorkerNo() {
		return strWorkerNo;
	}

	public void setStrWorkerNo(String strWorkerNo) {
		this.strWorkerNo = strWorkerNo;
	}
	
	public String getStrDockNo() {
		return strDockNo;
	}
	public void setStrDockNo(String strDockNo) {
		this.strDockNo = strDockNo;
	}


	public String getStrUntreadNo2() {
		return strUntreadNo2;
	}


	public void setStrUntreadNo2(String strUntreadNo2) {
		this.strUntreadNo2 = strUntreadNo2;
	}


	public String getStrLabelNo() {
		return strLabelNo;
	}
	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
	}


	public String getStrUser_Id() {
		return strUser_Id;
	}


	public void setStrUser_Id(String strUser_Id) {
		this.strUser_Id = strUser_Id;
	}


	public String getStrDeviceNo() {
		return strDeviceNo;
	}


	public void setStrDeviceNo(String strDeviceNo) {
		this.strDeviceNo = strDeviceNo;
	}


	public String getStrArticleNo() {
		return strArticleNo;
	}


	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}


	public String getStrLabelId() {
		return strLabelId;
	}


	public void setStrLabelId(String strLabelId) {
		this.strLabelId = strLabelId;
	}
	
	
	
}
