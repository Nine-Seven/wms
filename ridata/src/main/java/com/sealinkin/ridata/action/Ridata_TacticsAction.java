package com.sealinkin.ridata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.service.IRidata_TacticsService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_OutorderFlowModel;
import com.sealinkin.wms.model.Wms_RiorderModel;
import com.sealinkin.wms.model.Wms_OwnerRiorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.model.Wms_OwnerOutorderModel;

public class Ridata_TacticsAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IRidata_TacticsService ridata_TacticsImpl;
	private String strJsonDetail;
	private String strExpType;
	private String strQuality;
	private String levelFlag;
	public String getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getStrQuality() {
		return strQuality;
	}

	public void setStrQuality(String strQuality) {
		this.strQuality = strQuality;
	}

	public String getStrPriority() {
		return strPriority;
	}

	public void setStrPriority(String strPriority) {
		this.strPriority = strPriority;
	}

	private String strPriority;
	private String strType;
	private String wheresql;
	private String ownerNo;
	
	public void setRidata_TacticsImpl(IRidata_TacticsService ridata_TacticsImpl) {
		this.ridata_TacticsImpl = ridata_TacticsImpl;
	}

	////////////////////////////////////////系统级出货策略配置///////////////////////////////////////////////////
	//获取主档
	public void queryWmsRiOrderList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_RiorderModel> list=
					ridata_TacticsImpl.queryWmsRiOrderList(poPageBo,
							super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存或新增策略
	 */
	public void saveOrUpdateTactics(){
		try {
			MsgRes msg = ridata_TacticsImpl.saveOrUpdateTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//获取工作流配置（查看时）
	public void queryWmsOutOrderFlow(){
		try {
			ExtListDataBo<Wms_OutorderFlowModel> list=ridata_TacticsImpl.queryWmsOutOrderFlow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strExpType,strType);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//保存工作流
	public void saveTactics(){
		try {
			MsgRes msg=ridata_TacticsImpl.saveTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTactics(){
		try {
			MsgRes msg=ridata_TacticsImpl.deleteTactics(levelFlag,wheresql,super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//校验该单据是否存在（三种级别公用）
	public void checkExpType(){
		try {
			MsgRes msg=ridata_TacticsImpl.checkExpType(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strExpType,strQuality,strPriority,wheresql,ownerNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据出货单别获取波次规则号（三种级别公用）
	public void queryStrategy(){
		try {
			List<Wms_OutwaveplanMModel> list=ridata_TacticsImpl.queryStrategy(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strExpType);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
////////////////////////////////////////仓别级出货策略配置///////////////////////////////////////////////////
	
	//获取仓别单据主档
	public void queryWmsWarehouseOutorderList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_RiorderModel> list=
					ridata_TacticsImpl.queryWmsWarehouseOutorderList(poPageBo,
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//获取仓别工作流配置（查看时）
	public void queryWmsWarehouseOutOrderFlow(){
		try {
			ExtListDataBo<Wms_OutorderFlowModel> list=ridata_TacticsImpl.queryWmsWarehouseOutOrderFlow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strExpType,strType,ownerNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据获取失败！
		}
	}
	
	/**
	 * 保存或新增策略
	 */
	public void saveOrUpdateWasehouseTactics(){
		try {
			MsgRes msg = ridata_TacticsImpl.saveOrUpdateWasehouseTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//保存仓别配置工作流
	public void saveWasehouseTactics(){
		try {
			MsgRes msg=ridata_TacticsImpl.saveWasehouseTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////货主级出货策略配置///////////////////////////////////////////////////
	//获取仓别货主单据主档
	public void queryWmsOwnerOutOrderList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_OwnerRiorderModel> list=
					ridata_TacticsImpl.queryWmsOwnerOutOrderList(poPageBo,
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取货主工作流配置（查看时）
	public void queryWmsOwnerOutOrderFlow(){
		try {
			ExtListDataBo<Wms_OutorderFlowModel> list=ridata_TacticsImpl.queryWmsOwnerOutOrderFlow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strExpType,strType,ownerNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据获取失败！
		}
	}
	
	/**
	 * 保存或新增货主单据策略
	 */
	public void saveOrUpdateOwnerTactics(){
		try {
			MsgRes msg = ridata_TacticsImpl.saveOrUpdateOwnerTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//保存货主配置工作流
	public void saveOwnerTactics(){
		try {
			MsgRes msg=ridata_TacticsImpl.saveOwnerTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getWheresql() {
		return wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public void setOdata_TacticsImpl(IRidata_TacticsService ridata_TacticsImpl) {
		this.ridata_TacticsImpl = ridata_TacticsImpl;
	}

	public String getStrJsonDetail() {
		return strJsonDetail;
	}

	public void setStrJsonDetail(String strJsonDetail) {
		this.strJsonDetail = strJsonDetail;
	}

	public String getStrExpType() {
		return strExpType;
	}

	public void setStrExpType(String strExpType) {
		this.strExpType = strExpType;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	
}
