package com.sealinkin.ridata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_InstockDModel;
import com.sealinkin.ridata.model.Ridata_InstockMModel;
import com.sealinkin.ridata.service.IRidata_InstockService;

public class Ridata_InstockAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IRidata_InstockService ridata_InstockImpl;
	private String wheresql;
	private String jsonDetail1;
	private String strQuery;
	private String strWheresql;
	private String instockNo;
	private String cellNo;
	private String workerNo;
	
	/**
	 * 返配上架回单M
	 * @throws Exception
	 */
	public void getRidata_InstockMList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_InstockMModel> list=ridata_InstockImpl.getRidata_InstockMList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返配上架回单D
	 * @throws Exception
	 */
	public void getRidata_InstockDList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_InstockDModel> list=ridata_InstockImpl.getRidata_InstockDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存
	public void save(){
		try {
			ridata_InstockImpl.save(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),jsonDetail1);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, e.getMessage(), ""));
		}
	}
	
	//获取实际上架储位
	public void queryCell(){
		try {
			List<ComboxBo> list = ridata_InstockImpl.queryCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strWheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, e.getMessage(), ""));
		}
	}
	//获取波次号
	public void queryWaveNo(){
		try {
			List<ComboxBo> list = ridata_InstockImpl.queryWaveNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strWheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, e.getMessage(), ""));
		}
	}
	
	//取消上架
	public void tscCloseInstock(){
		try {
			ridata_InstockImpl.tscCloseInstock(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					strWheresql);
			super.writeObj(new MsgRes(true, "取消成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, e.getMessage(), ""));
		}
	}
	
	//回单保存
	public void saveInstock(){

		try {
			MsgRes msg = ridata_InstockImpl.saveInstock(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getInstockNo(),
					this.getWorkerNo(),
					this.getCellNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, e.getMessage(), ""));
		}
	
	}
	
	public void setRidata_InstockImpl(IRidata_InstockService ridataInstockImpl) {
		ridata_InstockImpl = ridataInstockImpl;
	}
	
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getJsonDetail1() {
		return jsonDetail1;
	}
	public void setJsonDetail1(String jsonDetail1) {
		this.jsonDetail1 = jsonDetail1;
	}
	
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getInstockNo() {
		return instockNo;
	}

	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public String getWorkerNo() {
		return workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	
}
