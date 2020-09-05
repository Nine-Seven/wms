package com.sealinkin.idata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.idata.service.Iidata_StraightCheckWorkService;
import com.sealinkin.util.ExceptionUtil;

public class Idata_StraightCheckWorkAction extends CommAction {
	
	private static final long serialVersionUID = 1L;
	private Iidata_StraightCheckWorkService idata_StraightCheckWorkImpl;
	private String strJsonDetail1;
	private String strJsonMaster;
	
	public void save(){
		try 
		{
			MsgRes msg=this.idata_StraightCheckWorkImpl.save(this.strJsonMaster,this.strJsonDetail1);
			super.writeObj(msg);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public Iidata_StraightCheckWorkService getIdata_StraightCheckWorkImpl() {
		return idata_StraightCheckWorkImpl;
	}

	public void setIdata_StraightCheckWorkImpl(
			Iidata_StraightCheckWorkService idata_StraightCheckWorkImpl) {
		this.idata_StraightCheckWorkImpl = idata_StraightCheckWorkImpl;
	}

	public String getStrJsonDetail1() {
		return strJsonDetail1;
	}

	public void setStrJsonDetail1(String strJsonDetail1) {
		this.strJsonDetail1 = strJsonDetail1;
	}

	public String getStrJsonMaster() {
		return strJsonMaster;
	}

	public void setStrJsonMaster(String strJsonMaster) {
		this.strJsonMaster = strJsonMaster;
	}

}
