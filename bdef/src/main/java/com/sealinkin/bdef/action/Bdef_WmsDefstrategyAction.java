package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsDefstrategyDModel;
import com.sealinkin.bdef.model.Bdef_WmsDefstrategyModel;
import com.sealinkin.bdef.service.IBdef_WmsDefstrategyService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

/**
 * @策略配置Action
 * @author hcx 20160610
 *
 */
public class Bdef_WmsDefstrategyAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_WmsDefstrategyService bdef_WmsDefstrategyImpl;
	private String strQuery;
	private String str;
	private String strStrategyType;
	private String strStrategyId;
	private String strRuleOrder;
	private String flag;
	private String strRuleOrderChoice;
	
	//获取策略头档列表
	public void getWmsDefstrategyList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_WmsDefstrategyModel> list=bdef_WmsDefstrategyImpl.getWmsDefstrategyList(
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取策略明细列表
	public void getWmsDefstrategyDList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_WmsDefstrategyDModel> list=bdef_WmsDefstrategyImpl.getWmsDefstrategyDList(
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取UI策略类型下拉
	public void getStrategyTypeForUI(){
		try 
		{
			List<ComboxBo> list = bdef_WmsDefstrategyImpl.getStrategyTypeForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI默认标识下拉
	public void getDefaultFlagForUI(){
		try 
		{
			List<ComboxBo> list = bdef_WmsDefstrategyImpl.getDefaultFlagForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI策略id/名称下拉
	public void getStrategyIdOrName(){
		try 
		{
			List<ComboxBo> list = bdef_WmsDefstrategyImpl.getStrategyIdOrName(
					strQuery,str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存策略头档
	public void saveStrategy(){
		try {
			MsgRes msg=bdef_WmsDefstrategyImpl.saveStrategy(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除策略头档
	public void deleteStrategy(){
		try {
			MsgRes msg = bdef_WmsDefstrategyImpl.deleteStrategy(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取策略明细窗口规则ID下拉
	public void getRuleIdQuery(){
		try 
		{
			List<ComboxBo> list = bdef_WmsDefstrategyImpl.getRuleIdQuery(
					strQuery,str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//保存策略明细
	public void saveStrategyD(){
		try {
			MsgRes msg=bdef_WmsDefstrategyImpl.saveStrategyD(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除策略明细
	public void deleteStrategyD(){
		try {
			MsgRes msg = bdef_WmsDefstrategyImpl.deleteStrategyD(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//策略明细-上移、下移
	public void ruleOrderMove(){
		try {
			MsgRes msg=bdef_WmsDefstrategyImpl.ruleOrderMove(
					strStrategyType,
					strStrategyId,
					strRuleOrder,
				    strRuleOrderChoice,
					flag);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IBdef_WmsDefstrategyService getBdef_WmsDefstrategyImpl() {
		return bdef_WmsDefstrategyImpl;
	}
	public void setBdef_WmsDefstrategyImpl(
			IBdef_WmsDefstrategyService bdef_WmsDefstrategyImpl) {
		this.bdef_WmsDefstrategyImpl = bdef_WmsDefstrategyImpl;
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
	public String getStrStrategyType() {
		return strStrategyType;
	}
	public void setStrStrategyType(String strStrategyType) {
		this.strStrategyType = strStrategyType;
	}
	public String getStrStrategyId() {
		return strStrategyId;
	}
	public void setStrStrategyId(String strStrategyId) {
		this.strStrategyId = strStrategyId;
	}
	public String getStrRuleOrder() {
		return strRuleOrder;
	}
	public void setStrRuleOrder(String strRuleOrder) {
		this.strRuleOrder = strRuleOrder;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStrRuleOrderChoice() {
		return strRuleOrderChoice;
	}
	public void setStrRuleOrderChoice(String strRuleOrderChoice) {
		this.strRuleOrderChoice = strRuleOrderChoice;
	}
	
}
