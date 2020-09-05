package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.service.IBdef_DefOwnerService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

/**
 * @货主资料维护Action
 * @author lich
 *
 */
public class Bdef_DefOwnerAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_DefOwnerService bdef_DefOwnerImpl;
	private String str;
	private String strQuery;
	private Integer intQequestFlag = 1;//1：查询2：导出
	

	private String strWareHouseNO;//仓别
	private String strWcellManagerType;//仓别级 储位管理类型
	private String strWtypeValue;//仓别级 储位
	
	public IBdef_DefOwnerService getBdef_DefOwnerImpl() {
		return bdef_DefOwnerImpl;
	}
	public void setBdef_DefOwnerImpl(IBdef_DefOwnerService bdef_DefOwnerImpl) {
		this.bdef_DefOwnerImpl = bdef_DefOwnerImpl;
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
	public Integer getIntQequestFlag() {
		return intQequestFlag;
	}
	public void setIntQequestFlag(Integer intQequestFlag) {
		this.intQequestFlag = intQequestFlag;
	}
	
	
	
	
	public String getStrWareHouseNO() {
		return strWareHouseNO;
	}
	public void setStrWareHouseNO(String strWareHouseNO) {
		this.strWareHouseNO = strWareHouseNO;
	}
	public String getStrWcellManagerType() {
		return strWcellManagerType;
	}
	public void setStrWcellManagerType(String strWcellManagerType) {
		this.strWcellManagerType = strWcellManagerType;
	}
	public String getStrWtypeValue() {
		return strWtypeValue;
	}
	public void setStrWtypeValue(String strWtypeValue) {
		this.strWtypeValue = strWtypeValue;
	}
	/**
	 * 保存、修改货主
	 * @author lich 2014.04.10
	 */
	public void saveOrUpdateOwner(){
		try{					
			MsgRes msg=bdef_DefOwnerImpl.saveOrUpdateOwner(
					getStr(),super.getMdBdef_DefWorker().getWorkerNo()
					,getStrWareHouseNO(),getStrWcellManagerType(),getStrWtypeValue() );
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "savefail", ""));
		}
	}
	
	/**
	 * 查询货主信息
	 * @author lich 2014.04.10
	 */
	public void getBdef_DefOwner(){
		try{					
			PageBo pageBo = new PageBo(getStart(), getLimit());
			System.out.println("测试: " + super.getMdBdef_DefWorker().getWorkerOwner());
			ExtListDataBo<Bdef_DefOwnerModel> list=bdef_DefOwnerImpl.getBdef_DefOwner(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					getStrQuery(), 
					pageBo, 
					getIntQequestFlag());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "false", ""));
		}
	}
	
	/**
	 * 判断货主编码是否存在
	 * @author lich 2014.04.10
	 */
	public void existsOwnerNo(){
		try{					
			MsgRes msg=bdef_DefOwnerImpl.existsOwnerNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),getStr());
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "fail", ""));
		}
	}
	
	/**
	 * 填充货主下拉
	 */
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefOwnerImpl.queryOwnerCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), "", "");
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 填充承运商下拉
	 * hj
	 */
	public void queryShipperCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefOwnerImpl.queryShipperCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), "", "");
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//删除货主
	public void deleteOwner(){
		try {
			MsgRes msgRes=bdef_DefOwnerImpl.deleteOwner(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),this.getStr());
			super.writeObj(msgRes);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "删除失败", ""));
		}	
	}
	
	//判断是否可以修改货主状态
	public void isCanChange(){
		try {
			MsgRes msgRes=bdef_DefOwnerImpl.isCanChange(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeObj(msgRes);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "false", ""));
		}	
	}
	//获取状态
	public void  getStatusList(){
		try 
		{
			List<ComboxBo> list = bdef_DefOwnerImpl.getStatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),				
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery
					);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
}
