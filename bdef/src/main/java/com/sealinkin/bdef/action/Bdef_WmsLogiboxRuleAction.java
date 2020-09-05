package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Wms_LogiboxRuleModel; 
import com.sealinkin.bdef.service.IBdef_WmsLogiboxRuleService;
import com.sealinkin.comm.action.CommAction; 
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo; 

/**
 * @货主资料维护Action
 * @author lich
 *
 */
public class Bdef_WmsLogiboxRuleAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_WmsLogiboxRuleService bdef_WmsLogiboxRuleImpl;	
	private String str;
	private String strQuery;
	private Integer intQequestFlag = 1;//1：查询2：导出
	public IBdef_WmsLogiboxRuleService getBdef_WmsLogiboxRuleImpl() {
		return bdef_WmsLogiboxRuleImpl;
	}
	public void setBdef_WmsLogiboxRuleImpl(
			IBdef_WmsLogiboxRuleService bdef_WmsLogiboxRuleImpl) {
		this.bdef_WmsLogiboxRuleImpl = bdef_WmsLogiboxRuleImpl;
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
	
	/**
	 * 保存、修改货主
	 * @author lich 2014.04.10
	 */
	public void saveOrUpdateWmsLogiboxRule(){
		try{					
			MsgRes msg=bdef_WmsLogiboxRuleImpl.saveOrUpdateWmsLogiboxRule(
					getStr(),super.getMdBdef_DefWorker().getWorkerNo());
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
	public void getWmsLogiboxRule(){
		try{					
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_LogiboxRuleModel> list=bdef_WmsLogiboxRuleImpl.getWms_LogiboxRule(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
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
			MsgRes msg=bdef_WmsLogiboxRuleImpl.existsOwnerNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),getStr());
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "fail", ""));
		}
	}
	
	 
	//删除货主
	public void deleteWmsLogiboxRule(){
		try {
			MsgRes msgRes=bdef_WmsLogiboxRuleImpl.deleteWmsLogiboxRule(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),this.getStr());
			super.writeObj(msgRes);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "删除失败", ""));
		}	
	}
	
	 
}
