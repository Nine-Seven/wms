package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;
import com.sealinkin.bdef.model.Bdef_WarehouseBaseModel;
import com.sealinkin.bdef.service.IBdef_WmsWarehouseBaseService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("serial")
public class Bdef_WmsWarehouseBaseAction extends CommAction{
	private IBdef_WmsWarehouseBaseService bdef_WmsWarehouseBaseImpl;
	private String strGroupNo;
	private String strSubGroupNo;
	private String strWarehouseBase;
	private String strColname;
	
	
	/**
	 * 获取所有列名
	 */
	public void getColnameCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_WmsWarehouseBaseImpl.getColnameCombo(strGroupNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取仓别参数列
	 */
	public void getWmsWarehouseBaseList()
	{
		try 
		{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_WarehouseBaseModel> pageListBo = 
			bdef_WmsWarehouseBaseImpl.getWmsWarehouseBaseList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strGroupNo, strSubGroupNo, poPagebo);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存、修改仓别参数
	 * @author
	 */
	public void saveOrUpdateWmsWarehouseBase(){
		try{
			MsgRes msg=bdef_WmsWarehouseBaseImpl.saveWmsWarehouseBase(strWarehouseBase);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "savefail", ""));
		}
	}
	
	
	/**
	 * 获取模块
	 */
	public void getGroupNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_WmsWarehouseBaseImpl.getGroupNoCombo();
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取子模块
	 */
	public void getSubGroupNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			if(!strGroupNo.equals("all")){
				list=bdef_WmsWarehouseBaseImpl.getSubGroupNoCombo(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						strGroupNo);
				super.writeArray(list);
				getWmsWarehouseBaseList();
				
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取模块
	 */
	public void getAllGroupNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_WmsWarehouseBaseImpl.getAllGroupNoCombo();
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取子模块
	 */
	public void getAllSubGroupNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			if(!strGroupNo.equals("all")){
				list=bdef_WmsWarehouseBaseImpl.getAllSubGroupNoCombo(strGroupNo);
				super.writeArray(list);
				getWmsWarehouseBaseList();
				
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
			list=bdef_WmsWarehouseBaseImpl.getSdefineCombo(strColname);
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
			list=bdef_WmsWarehouseBaseImpl.getAllFromGroupNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 *通过模块加载Memo
	 */
	public void getMemoCombo()
	{
		try 
		{
			List<String> list =bdef_WmsWarehouseBaseImpl.getMemoCombo(strColname);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public IBdef_WmsWarehouseBaseService getBdef_WmsWarehouseBaseImpl() {
		return bdef_WmsWarehouseBaseImpl;
	}
	public void setBdef_WmsWarehouseBaseImpl(
			IBdef_WmsWarehouseBaseService bdef_WmsWarehouseBaseImpl) {
		this.bdef_WmsWarehouseBaseImpl = bdef_WmsWarehouseBaseImpl;
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
	public String getStrColname() {
		return strColname;
	}
	public void setStrColname(String strColname) {
		this.strColname = strColname;
	}

	public String getStrWarehouseBase() {
		return strWarehouseBase;
	}

	public void setStrWarehouseBase(String strWarehouseBase) {
		this.strWarehouseBase = strWarehouseBase;
	}
	
	
	
}
