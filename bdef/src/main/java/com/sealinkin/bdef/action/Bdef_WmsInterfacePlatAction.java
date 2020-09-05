package com.sealinkin.bdef.action;

import com.sealinkin.bdef.model.Bdef_WmsInterfacePlatModel;
import com.sealinkin.bdef.service.IBdef_WmsInterfacePlatService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public class Bdef_WmsInterfacePlatAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private String str;
	private IBdef_WmsInterfacePlatService bdef_WmsInterfacePlatImpl;
	public void getWmsInterfacePlatList(){		
		try 
		{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_WmsInterfacePlatModel> wmsOwnerBaselist = bdef_WmsInterfacePlatImpl.getWmsInterfacePlatList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),this.getStr(), pageBo);
			super.writeObj(wmsOwnerBaselist);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}


	public String getStr() {
		return str;
	}


	public void setStr(String str) {
		this.str = str;
	}


	public IBdef_WmsInterfacePlatService getBdef_WmsInterfacePlatImpl() {
		return bdef_WmsInterfacePlatImpl;
	}


	public void setBdef_WmsInterfacePlatImpl(
			IBdef_WmsInterfacePlatService bdef_WmsInterfacePlatImpl) {
		this.bdef_WmsInterfacePlatImpl = bdef_WmsInterfacePlatImpl;
	}
	
	
	
	
}
