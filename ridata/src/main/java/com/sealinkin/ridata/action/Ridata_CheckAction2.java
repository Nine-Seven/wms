package com.sealinkin.ridata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.service.IRidata_CheckService2;
import com.sealinkin.util.ExceptionUtil;

public class Ridata_CheckAction2 extends CommAction {

	private static final long serialVersionUID = 1L;

	private IRidata_CheckService2 ridata_checkImpl2;
	private String whereSql;
	private String jsonMaster;
	private String jsonDetail;
	private String strQuery;
	private String strPageSql;
	private String strSUntreadNo;

	/**
	 * 返配表单验收列
	 * 
	 * @throws Exception
	 */
	public void getRidata_Check_MList() {
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Ridata_CheckMModel> list = ridata_checkImpl2
					.getRidata_Check_MList(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							super.getMdBdef_DefWorker().getWorkerOwner(), strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返配表单验收明细
	 * 
	 * @throws Exception
	 */
	public void getRidata_Check_DList() {
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Ridata_CheckDModel> list = ridata_checkImpl2
					.getRidata_Check_DList(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(), 
							whereSql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			ridata_checkImpl2.save(jsonMaster, jsonDetail);
			super.writeObj(new MsgRes(true, "保存成功！", null));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	// 获得返配汇总单号
	public void getUntreadNoList() {
		try {
			List<ComboxBo> list = ridata_checkImpl2.getUntreadNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strPageSql);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//根据返配汇总单号获取货主编码
    public void getOwnerNo(){
		try {
			MsgRes msg=ridata_checkImpl2.getOwnerNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strSUntreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
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

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public void setRidata_checkImpl2(IRidata_CheckService2 ridata_checkImpl2) {
		this.ridata_checkImpl2 = ridata_checkImpl2;
	}

	public String getStrPageSql() {
		return strPageSql;
	}
	public void setStrPageSql(String strPageSql) {
		this.strPageSql = strPageSql;
	}
	public String getStrSUntreadNo() {
		return strSUntreadNo;
	}
	public void setStrSUntreadNo(String strSUntreadNo) {
		this.strSUntreadNo = strSUntreadNo;
	}
}
