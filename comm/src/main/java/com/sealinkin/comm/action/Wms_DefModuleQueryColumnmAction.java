package com.sealinkin.comm.action;

import java.util.List;

import com.sealinkin.comm.service.IWms_DefModuleQueryColumnm;
import com.sealinkin.wms.model.Wms_DefModuleQueryColumnmModel;


public class Wms_DefModuleQueryColumnmAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IWms_DefModuleQueryColumnm wms_DefModuleQueryColumnmImpl;
	private String str;
	private String moduleId;
	
	public IWms_DefModuleQueryColumnm getWms_DefModuleQueryColumnmImpl() {
		return wms_DefModuleQueryColumnmImpl;
	}
	public void setWms_DefModuleQueryColumnmImpl(
			IWms_DefModuleQueryColumnm wms_DefModuleQueryColumnmImpl) {
		this.wms_DefModuleQueryColumnmImpl = wms_DefModuleQueryColumnmImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public void getModuleQueryColumn()
	{
		try {
			System.out.println("getModuleId:"+getModuleId());
			List<Wms_DefModuleQueryColumnmModel> list=
					wms_DefModuleQueryColumnmImpl.getModuleQueryColumn(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							getModuleId());
			super.writeStr(covtListToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
