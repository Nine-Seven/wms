package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleMModel;
import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleModel;
import com.sealinkin.bdef.service.IBdef_WmsTaskallotRuleMService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

/** 模块名称：任务切分规则配置
 * 模块编码：I201
 * 创建：huangb 20160612
 */
public class Bdef_WmsTaskallotRuleMAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_WmsTaskallotRuleMService bdef_WmsTaskallotRuleMImpl;
	private String strQuery;
	private String str;
	
	//获取任务切分规则头档数据
	public void getMasterInfo(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_WmsTaskallotRuleMModel> list=bdef_WmsTaskallotRuleMImpl.getMasterInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取任务切分规则明细数据
	public void getDetailInfo(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_WmsTaskallotRuleModel> list=bdef_WmsTaskallotRuleMImpl.getDetailInfo(
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取波次规则下拉框
	public void getWaveRuleForUI(){
		try {
			List<ComboxBo> list=bdef_WmsTaskallotRuleMImpl.getWaveRuleForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//保存任务切分规则头档
	public void saveWmsTaskallotRuleM(){
		try {
			MsgRes msg=bdef_WmsTaskallotRuleMImpl.saveWmsTaskallotRuleM(str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//保存任务切分规则明细
	public void saveWmsTaskallotRuleD(){
		try {
			MsgRes msg=bdef_WmsTaskallotRuleMImpl.saveWmsTaskallotRuleD(str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//删除任务切分规则头档
	public void deleteWmsTaskallotRuleM(){
		try {
			MsgRes msg = bdef_WmsTaskallotRuleMImpl.deleteWmsTaskallotRuleM(
					     super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//删除任务切分规则明细
	public void deleteWmsTaskallotRuleD(){
		try {
			MsgRes msg = bdef_WmsTaskallotRuleMImpl.deleteWmsTaskallotRuleD(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public IBdef_WmsTaskallotRuleMService getBdef_WmsTaskallotRuleMImpl() {
		return bdef_WmsTaskallotRuleMImpl;
	}

	public void setBdef_WmsTaskallotRuleMImpl(IBdef_WmsTaskallotRuleMService bdef_WmsTaskallotRuleMImpl) {
		this.bdef_WmsTaskallotRuleMImpl = bdef_WmsTaskallotRuleMImpl;
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
