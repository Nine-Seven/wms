/**
 * @货主参数配置Action
 * @author chensr
 *
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBdef_WmsOwnerBaseService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OwnerBaseModel;

public class Bdef_WmsOwnerBaseAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private IBdef_WmsOwnerBaseService bdef_WmsOwnerBaseImpl;
	private String strQuery;
	private String str;
	/**
	 * 获取货主参数配置列表
	 */
	public void getWmsOwnerBaseList(){		
		try 
		{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_OwnerBaseModel> wmsOwnerBaselist = bdef_WmsOwnerBaseImpl.getWmsOwnerBaseList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),this.getStr(), pageBo);
			super.writeObj(wmsOwnerBaselist);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 根据group_no获取子colname下拉按钮
	 */
	public void getColNameComboList(){
		try
		{
			List<ComboxBo> list = bdef_WmsOwnerBaseImpl.getColNameComboList(this.getStrQuery());		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取业务下拉按钮
	 */
	public void getGroupNoComboList(){
		try 
		{
			List<ComboxBo> list = bdef_WmsOwnerBaseImpl.getGroupNoComboList();		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取业务下拉按钮(用于UI界面的业务下拉)
	 */
	public void getGroupNoComboListForUI(){
		try 
		{
			List<ComboxBo> list = bdef_WmsOwnerBaseImpl.getGroupNoComboListForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取子业务下拉按钮(窗口)
	 */
	public void getSubGroupNoComboList(){
		try 
		{
			List<ComboxBo> list = bdef_WmsOwnerBaseImpl.getSubGroupNoComboList(this.getStrQuery());		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取子业务下拉按钮(UI)
	 */
	public void getSubGroupNoComboListForUI(){
		try 
		{
			List<ComboxBo> list = bdef_WmsOwnerBaseImpl.getSubGroupNoComboListForUI(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),this.getStrQuery());		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取子sdefine下拉按钮
	 */
	public void getSdefineComboList(){
		try 
		{
			List<ComboxBo> list = bdef_WmsOwnerBaseImpl.getSdefineComboList(this.getStrQuery());		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据colname查找Memo
	 */
	public void getMemo(){
		try 
		{
			List<String> list = bdef_WmsOwnerBaseImpl.getMemo(this.getStrQuery());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存货主参数信息
	 */
	public void saveWmsOwnerBase(){
		try
		{	
			bdef_WmsOwnerBaseImpl.saveWmsOwnerBase(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}

	public IBdef_WmsOwnerBaseService getBdef_WmsOwnerBaseImpl() {
		return bdef_WmsOwnerBaseImpl;
	}

	public void setBdef_WmsOwnerBaseImpl(
			IBdef_WmsOwnerBaseService bdef_WmsOwnerBaseImpl) {
		this.bdef_WmsOwnerBaseImpl = bdef_WmsOwnerBaseImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
}
