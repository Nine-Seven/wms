package com.sealinkin.ridata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckBadService;
import com.sealinkin.ridata.service.IRidata_QingchangService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 模块名称：返配清场扫描验收
 * 模块编码：6902
 * 创建：hekl
 */
public class Ridata_QingchangAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IRidata_QingchangService ridata_QingchangImpl;
	private String strWhereSql;
	private String jsonMaster;
	private String jsonDetail;
	private String queryStr;
	private String strFlag;
	private String strPageSql;
	private String strOwnerNo;
	private String strBarcode;
	private String strSImportNo;
	private String strUntreadNo;
	private String strSupplierNo;
	private String strWorkerNo;
	private String strDockNo;
	private String strSUntreadNo2;
	private String strLabelNo;
	private String strUser_Id;//操作人
	private String strSCheckNo;
	/**
	 * 返配清场扫描验收列
	 * @throws Exception
	 */
	public void getRidata_QingchangDList()
	{
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_CheckDModel> list=ridata_QingchangImpl.getRidata_QingchangDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strUntreadNo,pageBo);
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
			list=ridata_QingchangImpl.getComboList(
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
	
	//保存
	public void save(){
		try {
			MsgRes msg = ridata_QingchangImpl.save(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),jsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 校验码头与单号是否一致
	 */
	public void checkDockNoUNo(){
		try {
			MsgRes msg =ridata_QingchangImpl.checkDockNoUNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strUntreadNo,strDockNo);
			super.writeObj(msg);
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
			MsgRes msg=ridata_QingchangImpl.queryBarcode(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strBarcode, 
					strOwnerNo, 
					strUntreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
   
	/**
	 * 封板
	 */
	public void tscClosePal(){
		try {
			MsgRes msg=ridata_QingchangImpl.tscClosePal(jsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	/**
	 * 获取临时表数据
	 */
	public void queryCheckPalTmp(){
		try {
			ExtListDataBo<Ridata_CheckPalTmpModel> list=ridata_QingchangImpl.queryCheckPalTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strDockNo,strSUntreadNo2,strUntreadNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 校验目的上架货位
	 */
	public void checkCellNo(){
		try {
			MsgRes msg=ridata_QingchangImpl.checkCellNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWhereSql,strOwnerNo,strSUntreadNo2,strSCheckNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 换单时校验该码头是否还有未封箱的数据
	 */
	public void checkCloseBox(){
		try {
			MsgRes msg=ridata_QingchangImpl.checkCloseBox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strDockNo,strUntreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 *取该单的扫描数量
	 */
	public void tscBoxQty(){
		try {
			MsgRes msg=ridata_QingchangImpl.tscBoxQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strDockNo,strUntreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public IRidata_QingchangService getRidata_QingchangImpl() {
		return ridata_QingchangImpl;
	}


	public void setRidata_QingchangImpl(
			IRidata_QingchangService ridata_QingchangImpl) {
		this.ridata_QingchangImpl = ridata_QingchangImpl;
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
	
	
	public String getStrUntreadNo() {
		return strUntreadNo;
	}


	public void setStrUntreadNo(String strUntreadNo) {
		this.strUntreadNo = strUntreadNo;
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


	public String getStrSUntreadNo2() {
		return strSUntreadNo2;
	}
	public void setStrSUntreadNo2(String strSUntreadNo2) {
		this.strSUntreadNo2 = strSUntreadNo2;
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


	public String getStrSCheckNo() {
		return strSCheckNo;
	}


	public void setStrSCheckNo(String strSCheckNo) {
		this.strSCheckNo = strSCheckNo;
	}
	
	
	
}
