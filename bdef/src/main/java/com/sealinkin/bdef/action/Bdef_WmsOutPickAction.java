/**
 * @拣货策略参数配置Action
 * @author MM
 *
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBdef_WmsOutPickService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OutpickStrategyModel;

public class Bdef_WmsOutPickAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private IBdef_WmsOutPickService bdef_WmsOutPickImpl;
	private String strQuery;
	private String str;
	/**
	 * 获取拣货参数配置列表
	 */
	public void getoutPickStrategy(){		
		try 
		{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_OutpickStrategyModel> wmsOutPicklist = bdef_WmsOutPickImpl.getWmsOutPickList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),this.getStr(), pageBo);
			super.writeStr(covtObjectToJson(wmsOutPicklist));		
		} catch (Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 保存拣货参数信息
	 */
	public void saveWmsOutPick(){
		try
		{	
			bdef_WmsOutPickImpl.saveWmsOutPick(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
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

	public IBdef_WmsOutPickService getBdef_WmsOutPickImpl() {
		return bdef_WmsOutPickImpl;
	}

	public void setBdef_WmsOutPickImpl(IBdef_WmsOutPickService bdef_WmsOutPickImpl) {
		this.bdef_WmsOutPickImpl = bdef_WmsOutPickImpl;
	}
}
