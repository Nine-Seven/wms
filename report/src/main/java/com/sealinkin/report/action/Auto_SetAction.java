package com.sealinkin.report.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.report.service.IAuto_SetService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_Defcustom_DModel;
import com.sealinkin.wms.model.Wms_Defcustom_MenuModel;


public class Auto_SetAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAuto_SetService auto_SetIpml;
	private String modubleId;
	private String customId;
	private String customName;
	private String strQuery;
	private String wmsCustomD;
	private String orderNo;
	private String flag;
	private String show;

	
	//获取主设置菜单
	public void getModubleMenu(){
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = auto_SetIpml.getModubleMenu(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	
	//保存子菜单
	public void saveSubCustom(){
		try {
			MsgRes msg=auto_SetIpml.saveSubReport(super.getMdBdef_DefWorker().getEnterpriseNo(),
													super.getMdBdef_DefWorker().getWorkerNo(),
													this.getModubleId(),
													this.getCustomId(),
													this.getCustomName());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	
	//修改子菜单是否可见
	public void changeShow(){
		try {
			MsgRes msg=auto_SetIpml.changeShow(super.getMdBdef_DefWorker().getEnterpriseNo(),
												 this.getModubleId(),
												 this.getCustomId(),
												 this.getShow());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"修改失败！",""));
		}
	}
	
	//获取子模块报表
	public void getCustomformenu(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_Defcustom_MenuModel> list=auto_SetIpml.getReportformenu(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//上移、下移
	public void seqencingDfm(){
		try {
			MsgRes msg=auto_SetIpml.seqencingDfm(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
												  this.getModubleId(),
												  this.getCustomId(),
												  this.getOrderNo(),
												  this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
    public void getReportList(){
    	try {
			List<ComboxBo> list = auto_SetIpml.getReportList(this.getModubleId());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取参数
    public void getCustomD(){
    	try{
			List<Wms_Defcustom_DModel> list =auto_SetIpml.getCustomD(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getCustomId()
					);
			super.writeArray(list);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	//保存参数
    public void saveCustomD(){
    	try {
			MsgRes msg=auto_SetIpml.saveCustomD(wmsCustomD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
    }
	
	
	
	
	
	
	
	
	
	public IAuto_SetService getAuto_SetIpml() {
		return auto_SetIpml;
	}
	public void setAuto_SetIpml(IAuto_SetService auto_SetIpml) {
		this.auto_SetIpml = auto_SetIpml;
	}

	public String getModubleId() {
		return modubleId;
	}

	public void setModubleId(String modubleId) {
		this.modubleId = modubleId;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getWmsCustomD() {
		return wmsCustomD;
	}

	public void setWmsCustomD(String wmsCustomD) {
		this.wmsCustomD = wmsCustomD;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
}
