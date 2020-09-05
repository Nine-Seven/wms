package com.sealinkin.auth.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.auth.service.IBset_Worker_Loc;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.MsgRes;

public class Bset_Worker_LocAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	private IBset_Worker_Loc bset_Worker_LocImpl;
	private String str;
	private String[] strArr;
	
    public IBset_Worker_Loc getBset_Worker_LocImpl() {
		return bset_Worker_LocImpl;
	}

	public void setBset_Worker_LocImpl(IBset_Worker_Loc bset_Worker_LocImpl) {
		this.bset_Worker_LocImpl = bset_Worker_LocImpl;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	/**
     * 员工作业仓别下拉
     * @author lich 2013.09.13
     */
	public void getBset_Worker_LocCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bset_Worker_LocImpl.getBset_Worker_LocCombo(
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveBset_Worker_Loc(){
		try {
			bset_Worker_LocImpl.saveBset_Worker_Loc(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void saveBset_Loc_Worker(){
		try {
			bset_Worker_LocImpl.saveBset_Loc_Worker(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void deleteBset_Worker_Loc(){
		try {
			bset_Worker_LocImpl.deleteBset_Worker_Loc(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void deleteBset_Loc_Worker(){
		try {
			bset_Worker_LocImpl.deleteBset_Loc_Worker(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void saveBset_Worker_Owner(){
		try {
			bset_Worker_LocImpl.saveBset_Worker_Owner(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	public void saveBset_Owner_Worker(){
		try {
			bset_Worker_LocImpl.saveBset_Owner_Worker(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void deleteBset_Worker_Owner(){
		try {
			bset_Worker_LocImpl.deleteBset_Worker_Owner(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void deleteBset_Owner_Worker(){
		try {
			bset_Worker_LocImpl.deleteBset_Owner_Worker(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	public void queryOwnerCombo(){
		try {
			List<Bdef_DefOwnerModel> list = bset_Worker_LocImpl.queryOwnerCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
