package com.sealinkin.comm.action;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.ICheckCellService;
import com.sealinkin.comm.service.IWmsJobConfigService;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_JobConfigModel;
/**
 * 后台管理控制
 * @author hkl
 *
 */
public class WmsJobConfigAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IWmsJobConfigService wmsJobConfigImpl;
	private String flag;
	private String procName;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public IWmsJobConfigService getWmsJobConfigImpl() {
		return wmsJobConfigImpl;
	}
	public void setWmsJobConfigImpl(IWmsJobConfigService wmsJobConfigImpl) {
		this.wmsJobConfigImpl = wmsJobConfigImpl;
	}

	
	
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	//获取后台管理控制表list
	public void getWms_JobConfugList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_JobConfigModel> list=wmsJobConfigImpl.getWms_JobConfugList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改后台管理配置的状态
	public void updateWmsJobConfig(){
		try{
			MsgRes msg  = wmsJobConfigImpl.updateWmsJobConfig(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					procName,flag);
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
