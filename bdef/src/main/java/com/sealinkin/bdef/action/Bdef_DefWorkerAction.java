package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.bdef.model.Bdef_DeflocModel;
import com.sealinkin.bdef.service.IBdef_DefWorkerService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bdef_DefWorkerAction extends CommAction {
	
	private static final long serialVersionUID = 1L;
	private IBdef_DefWorkerService bdef_DefWorkerImpl;
	private String strWheresql;
	private String strOwnerNo;
	private String strWarehouseNo;
	private String strQuery;
	private String strFlag;
	private String strWhereSql;
	

	public String getStrWhereSql() {
		return strWhereSql;
	}

	public void setStrWhereSql(String strWhereSql) {
		this.strWhereSql = strWhereSql;
	}

	public void setBdef_DefWorkerImpl(IBdef_DefWorkerService bdef_DefWorkerImpl) {
		this.bdef_DefWorkerImpl = bdef_DefWorkerImpl;
	}
	
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrWarehouseNo() {
		return strWarehouseNo;
	}
	public void setStrWarehouseNo(String strWarehouseNo) {
		this.strWarehouseNo = strWarehouseNo;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	/**
	 * 保存、修改用户
	 * @author lich 2014.04.11
	 */
	public void saveBdef_DefWorker()
	{
		try
		{	
			System.out.println("测试: " + this.getText());
			MsgRes msg=bdef_DefWorkerImpl.saveBdef_DefWorker(getText());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "savefail", ""));
		}
	}
	/**
	 * 获取用户信息
	 * @author lich 2014.04.10
	 */
	public void getBdef_DefWorker()
	{
		try{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefWorkerModel> list=bdef_DefWorkerImpl.getBdef_DefWorker(getText(), pageBo);

			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断用户代码是否存在
	 * @author lich 2014.04.10
	 */
	public void existsWorkerNo(){
		try{		
			PageBo pageBo = new PageBo(0, 20);
			ExtListDataBo<Bdef_DefWorkerModel> list=bdef_DefWorkerImpl.getBdef_DefWorker(getText(), pageBo);
			if(list.getRootList().size()>0){
				super.writeObj(new MsgRes(false, "no_exists", ""));
			}else{
				super.writeObj(new MsgRes(true, "", ""));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 填充员工下拉控件
	 */
	public void queryWorkerCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = bdef_DefWorkerImpl.query_WorkerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	
	
	public void getWorkerList(){
		try {
			System.out.println("--------------------");
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefWorkerModel> list=bdef_DefWorkerImpl.getWorkerList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery,
					strWarehouseNo, pageBo,
					getStrFlag());
			System.out.println("+++++++++++++++++++++++++++++++++");
			System.out.println(list.getRootList().get(0).getStatusText()+"+++++++++++++++++++++++++++++++++++");
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getWarehouseList(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DeflocModel> list=bdef_DefWorkerImpl.getWarehouseList(super.getMdBdef_DefWorker().getCurrEnterpriseNo(), pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通过用户编号获取仓别
	public void getWarehouseListByWorkerNo(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DeflocModel> list=bdef_DefWorkerImpl.getWarehouseListByWorkerNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strWhereSql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getOwnerList(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefOwnerModel> list=bdef_DefWorkerImpl.getOwnerList(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//通过用户编号获取货主列表
	public void getOwnerListByWorkerNo(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefOwnerModel> list=bdef_DefWorkerImpl.getOwnerListByWorkerNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strWhereSql,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getWorkerList2(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefWorkerModel> list=bdef_DefWorkerImpl.getWorkerList2(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
