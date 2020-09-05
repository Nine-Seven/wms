package com.sealinkin.idata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_InstockDModel;
import com.sealinkin.idata.model.Idata_InstockMModel;
import com.sealinkin.idata.service.Iidata_InstockService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 上架回单Action
 * @author zhouhuan
 * 模块编码：4701
 */
public class Idata_InstockAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private Iidata_InstockService idata_InstockImpl;
	private String wheresql;
	private String strInstockM;
	private String strInstockD;
	private String str;
	private String strQuery;
	private String strWheresql;
	private String strFlag;
	private String strOwnerNo;
	private String strInstockNo;
	
	
	/**
	 * 上架单头档
	 * @throws Exception
	 */
	public void getInstockMList()throws Exception{
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_InstockMModel> list=idata_InstockImpl.getInstockMList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					pageBo, 
					strFlag,
					strQuery);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上架单明细
	 * @throws Exception
	 */
	public void getInstockDList()throws Exception{
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_InstockDModel> list=idata_InstockImpl.getInstockDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(),strFlag, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存验收单
	 */
	public void save(){
		try {
			MsgRes msg=idata_InstockImpl.save(strInstockM,strInstockD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	/**
     * 上架单下拉
     */
	public void getInstockNoCombo() 
	{
		try{
			List<Idata_InstockMModel> list=new ArrayList<Idata_InstockMModel>();
			list=idata_InstockImpl.getInstockNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql, strFlag,strQuery, 0, 100);
			super.writeArray(list);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
     * 储位下拉
     */
	public void getCellNoComboList() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_InstockImpl.getCellNoComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,
					strWheresql, 0, 100);
			super.writeArray(list);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/*
	 * @func 获取货主下拉列表
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public void getOwnerComboList()
	{
		try {
			List<ComboxBo> list=idata_InstockImpl.getOwnerComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),strFlag);
			writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setIdata_InstockImpl(Iidata_InstockService idataInstockImpl) {
		idata_InstockImpl = idataInstockImpl;
	}
	
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getStrInstockM() {
		return strInstockM;
	}

	public void setStrInstockM(String strInstockM) {
		this.strInstockM = strInstockM;
	}

	public String getStrInstockD() {
		return strInstockD;
	}

	public void setStrInstockD(String strInstockD) {
		this.strInstockD = strInstockD;
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

	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrInstockNo() {
		return strInstockNo;
	}

	public void setStrInstockNo(String strInstockNo) {
		this.strInstockNo = strInstockNo;
	}	
	
}
