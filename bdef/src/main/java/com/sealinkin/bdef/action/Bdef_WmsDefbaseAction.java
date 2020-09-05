/*
 * 系统参数配置
 * zhm 
 */
package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsDefbaseModel;
import com.sealinkin.bdef.service.IBdef_WmsDefbaseService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("serial")
public class Bdef_WmsDefbaseAction extends CommAction{
	private IBdef_WmsDefbaseService Bdef_WmsDefbaseImpl;
	private String strGroupNo;
	private String strSubGroupNo;
	private String strWmsDefbase;
	private String colname;
	
	
	/**
	 * 获取系统参数列
	 */
	public void getWmsDefbaseList()
	{
		try 
		{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_WmsDefbaseModel> pageListBo = Bdef_WmsDefbaseImpl.getWmsDefbaseList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strGroupNo,strSubGroupNo,
					poPagebo);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存、修改系统参数
	 * @author lich 2014.04.10
	 */
	public void saveOrUpdateWmsDefbase(){
		try{
			MsgRes msg=Bdef_WmsDefbaseImpl.saveWmsDefbase(strWmsDefbase);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "savefail", ""));
		}
	}
//	/**
//	 * 获取模块
//	 */
//	public void getGroupNoCombo()
//	{
//		try 
//		{
//			List<ComboxBo> list=new ArrayList<ComboxBo>();
//			list=Bdef_WmsDefbaseImpl.getGroupNoCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
//			super.writeArray(list);
//		} catch (Exception e) 
//		{
//			e.printStackTrace();
//			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
//		}
//	}
	/**
	 * 获取子模块
	 */
	public void getSubGroupNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			if(!strGroupNo.equals("all")){
				list=Bdef_WmsDefbaseImpl.getSubGroupNoCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						strGroupNo);
				super.writeArray(list);
				getWmsDefbaseList();
				
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取值1
	 */
	public void getSdefineCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=Bdef_WmsDefbaseImpl.getSdefineCombo(colname);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 *通过模块加载全部数据
	 */
	public void getAllFromGroupNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=Bdef_WmsDefbaseImpl.getAllFromGroupNoCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public String getStrGroupNo() {
		return strGroupNo;
	}

	public void setStrGroupNo(String strGroupNo) {
		this.strGroupNo = strGroupNo;
	}

	public String getStrSubGroupNo() {
		return strSubGroupNo;
	}

	public void setStrSubGroupNo(String strSubGroupNo) {
		this.strSubGroupNo = strSubGroupNo;
	}

	public IBdef_WmsDefbaseService getBdef_WmsDefbaseImpl() {
		return Bdef_WmsDefbaseImpl;
	}

	public void setBdef_WmsDefbaseImpl(IBdef_WmsDefbaseService bdef_WmsDefbaseImpl) {
		Bdef_WmsDefbaseImpl = bdef_WmsDefbaseImpl;
	}

	public String getStrWmsDefbase() {
		return strWmsDefbase;
	}

	public void setStrWmsDefbase(String strWmsDefbase) {
		this.strWmsDefbase = strWmsDefbase;
	}

	
	

}
