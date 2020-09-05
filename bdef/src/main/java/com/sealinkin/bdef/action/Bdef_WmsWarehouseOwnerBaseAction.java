/**
 * @货主仓别参数配置 Action
 * @author chensr
 *
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBdef_WmsWarehouseOwnerBaseService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_WarehouseOwnerBaseModel;

public class Bdef_WmsWarehouseOwnerBaseAction extends CommAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBdef_WmsWarehouseOwnerBaseService bdef_WmsWarehouseOwnerBaseImpl;
	private String strWheresql;
	private String str;
	private String strQuery;
	
	/**
	 * 获取货主仓别参数配置列表
	 */
	public void getWarehouseOwnerBaselist(){		
		try 
		{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_WarehouseOwnerBaseModel> warehouseOwnerBaselist = bdef_WmsWarehouseOwnerBaseImpl.getWarehouseOwnerBaselist(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),this.getStrWheresql(), pageBo);
			super.writeObj(warehouseOwnerBaselist);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存货主仓别参数信息
	 */
	public void saveWarehouseOwnerBase(){
		try
		{	
			bdef_WmsWarehouseOwnerBaseImpl.saveWarehouseOwnerBase(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	
	/**
	 * 根据group_no获取子colname下拉按钮
	 */
	public void getColNameComboList(){
		try
		{
			List<ComboxBo> list = bdef_WmsWarehouseOwnerBaseImpl.getColNameComboList(this.getStr());		
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
			List<ComboxBo> list = bdef_WmsWarehouseOwnerBaseImpl.getGroupNoComboList();		
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
			List<ComboxBo> list = bdef_WmsWarehouseOwnerBaseImpl.getGroupNoComboListForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo()
					);		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取子业务下拉按钮(用于窗口)
	 */
	public void getSubGroupNoComboList(){
		try 
		{
			List<ComboxBo> list = bdef_WmsWarehouseOwnerBaseImpl.getSubGroupNoComboList(this.getStrQuery());		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取子业务下拉按钮(用于UI)
	 */
	public void getSubGroupNoComboListForUI(){
		try 
		{
			List<ComboxBo> list = bdef_WmsWarehouseOwnerBaseImpl.getSubGroupNoComboListForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrQuery());		
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
			List<ComboxBo> list = bdef_WmsWarehouseOwnerBaseImpl.getSdefineComboList(this.getStrQuery());		
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
			List<String> list = bdef_WmsWarehouseOwnerBaseImpl.getMemo(this.getStrQuery());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public IBdef_WmsWarehouseOwnerBaseService getBdef_WmsWarehouseOwnerBaseImpl() {
		return bdef_WmsWarehouseOwnerBaseImpl;
	}

	public void setBdef_WmsWarehouseOwnerBaseImpl(
			IBdef_WmsWarehouseOwnerBaseService bdef_WmsWarehouseOwnerBaseImpl) {
		this.bdef_WmsWarehouseOwnerBaseImpl = bdef_WmsWarehouseOwnerBaseImpl;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	
	

}
