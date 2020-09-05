/**
 * @出货定位策略配置Action
 * @author liucl 20160804
 *
 */
package  com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.odata.model.odata_WmsOutlocateStrategyDModel;
import com.sealinkin.odata.model.odata_WmsOutlocateStrategyMModel;
import com.sealinkin.odata.service.IOdata_outLocateStrategyService;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Odata_outLocateStrategyAction extends CommAction{

	private static final long serialVersionUID = 1322343254651L;
	private IOdata_outLocateStrategyService Odata_outLocateStrategyImpl;
	private String strQuery;
	private String str;
	private String strStrategyType;
	private String strStrategyId;
	private String strRuleOrder;
	private String flag;
	private String strRuleOrderChoice;
	
	//获取策略头档列表(cg)
	public void getOutLocateStrategyMList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<odata_WmsOutlocateStrategyMModel> list=Odata_outLocateStrategyImpl.getOutLocateStrategyMList(
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取策略明细列表(cg)
	public void getOutLocateStrategyDList1(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<odata_WmsOutlocateStrategyDModel> list=Odata_outLocateStrategyImpl.getOutLocateStrategyDList1(
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取策略名下拉
	public void getStrategyNameForUI(){
		try 
		{
			List<ComboxBo> list = Odata_outLocateStrategyImpl.getStrategyNameForUI(
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
			List<ComboxBo> list = Odata_outLocateStrategyImpl.getDefaultFlagForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI策略id下拉
	public void getStrategyId(){
		try 
		{
			List<ComboxBo> list = Odata_outLocateStrategyImpl.getStrategyId(
					strQuery,str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存策略头档
	public void saveStrategy(){
		try {
			MsgRes msg=Odata_outLocateStrategyImpl.saveStrategy(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除策略头档
	public void deleteStrategy(){
		try {
			MsgRes msg = Odata_outLocateStrategyImpl.deleteStrategy(strQuery);
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
			List<ComboxBo> list = Odata_outLocateStrategyImpl.getRuleIdQuery(
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
			MsgRes msg=Odata_outLocateStrategyImpl.saveStrategyD(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除策略明细
	public void deleteStrategyD(){
		try {
			MsgRes msg = Odata_outLocateStrategyImpl.deleteStrategyD(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	public IOdata_outLocateStrategyService getOdata_outLocateStrategyImpl() {
		return Odata_outLocateStrategyImpl;
	}
	public void setOdata_outLocateStrategyImpl(
			IOdata_outLocateStrategyService odata_outLocateStrategyImpl) {
		Odata_outLocateStrategyImpl = odata_outLocateStrategyImpl;
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
