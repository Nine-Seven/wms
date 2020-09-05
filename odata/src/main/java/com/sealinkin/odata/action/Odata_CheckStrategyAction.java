/**
 * @复核策略配置Action
 * @author liucl 20160813
 *
 */
package  com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.odata.model.Odata_WmsCheckStrategyModel;
import com.sealinkin.odata.service.IOdata_CheckStrategyService;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Odata_CheckStrategyAction extends CommAction{

	private static final long serialVersionUID = 123123311552L;
	private IOdata_CheckStrategyService Odata_CheckStrategyImpl;
	private String strQuery;
	private String str;
	private String strStrategyType;
	private String strStrategyId;
	private String strRuleOrder;
	private String flag;
	private String strRuleOrderChoice;
	
	//获取策略头档列表(cg)
	public void getCheckStrategyList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_WmsCheckStrategyModel> list=Odata_CheckStrategyImpl.getCheckStrategyList(
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取策略类型下拉
	public void getCheckTypeForUI(){
		try 
		{
			List<ComboxBo> list = Odata_CheckStrategyImpl.getCheckTypeForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI复核级别下拉
	public void getCheckLevelForUI(){
		try 
		{
			List<ComboxBo> list = Odata_CheckStrategyImpl.getCheckLevelForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI策略id下拉
	public void getCheckStrategyIdAndName(){
		try 
		{
			List<ComboxBo> list = Odata_CheckStrategyImpl.getCheckStrategyIdAndName(
					strQuery,str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存策略头档
	public void saveStrategy(){
		try {
			MsgRes msg=Odata_CheckStrategyImpl.saveStrategy(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除策略头档
	public void deleteStrategy(){
		try {
			MsgRes msg = Odata_CheckStrategyImpl.deleteStrategy(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public IOdata_CheckStrategyService getOdata_CheckStrategyImpl() {
		return Odata_CheckStrategyImpl;
	}

	public void setOdata_CheckStrategyImpl(
			IOdata_CheckStrategyService odata_CheckStrategyImpl) {
		Odata_CheckStrategyImpl = odata_CheckStrategyImpl;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}