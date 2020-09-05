package com.sealinkin.cset.action;

import com.sealinkin.bdef.model.Bdef_DeflocModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.service.ICset_deflocService;
import com.sealinkin.util.ExceptionUtil;

public class Cset_deflocAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICset_deflocService cset_deflocServiceImpl;
	private String strQuery;
	private String str;
	
	public void getLoc(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DeflocModel> wavePlanM =cset_deflocServiceImpl.getLoc(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo()
					,pageBo,
					this.getStrQuery());
			super.writeObj(wavePlanM);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveOrUpdateDefloc(){

		try {
			MsgRes msg = cset_deflocServiceImpl.saveOrUpdateDefloc( getStr());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
					.getCacseMessage(e), ""));
		}
	
	}
	
	public ICset_deflocService getCset_deflocServiceImpl() {
		return cset_deflocServiceImpl;
	}
	public void setCset_deflocServiceImpl(ICset_deflocService cset_deflocServiceImpl) {
		this.cset_deflocServiceImpl = cset_deflocServiceImpl;
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
