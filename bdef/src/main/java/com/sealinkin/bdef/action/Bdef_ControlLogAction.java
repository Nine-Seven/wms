package com.sealinkin.bdef.action;


import com.sealinkin.bdef.service.IBdef_ControlLogService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_InterfaceLogModel;

public class Bdef_ControlLogAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBdef_ControlLogService bdef_ControlLogServiceImpl;
	
	//获取接口监控日志列表
	public void getInterfaceLogList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_InterfaceLogModel> list=bdef_ControlLogServiceImpl.getInterfaceLogList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					 pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取接口监控日志汇总列表
	public void getInterfaceLogSumList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_InterfaceLogModel> list=bdef_ControlLogServiceImpl.getInterfaceLogSumList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					 pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IBdef_ControlLogService getBdef_ControlLogServiceImpl() {
		return bdef_ControlLogServiceImpl;
	}

	public void setBdef_ControlLogServiceImpl(
			IBdef_ControlLogService bdef_ControlLogServiceImpl) {
		this.bdef_ControlLogServiceImpl = bdef_ControlLogServiceImpl;
	}
		

}
