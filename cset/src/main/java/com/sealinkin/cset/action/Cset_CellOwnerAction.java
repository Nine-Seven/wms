package com.sealinkin.cset.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.service.ICset_AreaBackupService;
import com.sealinkin.cset.service.ICset_CellOwnerService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.comm.model.ComboxBo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"serial","rawtypes","unused"})
public class Cset_CellOwnerAction extends CommAction {
	private ICset_CellOwnerService cset_CellOwnerImpl;
	private MsgRes msgRes;
	private String str;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String cellNo;
	
	/**
	 * 货主下拉
	 */
	public void getOwnerCombo(){
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_CellOwnerImpl.getOwnerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//获取货位列表，取作业区（包括异常区）并且不允许混货主的的货位
	public void getCset_CellList() {
		try {
			ExtListDataBo list = this.cset_CellOwnerImpl.getCset_CellList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					cellNo);
			super.writeObj(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 货主货位关系列表
	public void getCset_Cell_OwnerList() {
		try {
			PageBo pageBo = new PageBo(getStart().intValue(), getLimit().intValue());
			ExtListDataBo list = this.cset_CellOwnerImpl.getCset_Cell_OwnerList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					ownerNo, cellNo,pageBo);
			super.writeObj(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//保存货主货位关系
	//添加群组关系列表
	public void saveCellOwner(){
		try {
			cset_CellOwnerImpl.saveCellOwner(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}
	
	//移除货主货位对应关系
	public void deleteCellOwner(){
		try {
			cset_CellOwnerImpl.deleteCellOwner(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					ownerNo, cellNo);
			super.writeObj(new MsgRes(true, "移除成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "移除失败", ""));
		}
	}
	public ICset_CellOwnerService getCset_CellOwnerImpl() {
		return cset_CellOwnerImpl;
	}

	public void setCset_CellOwnerImpl(ICset_CellOwnerService cset_CellOwnerImpl) {
		this.cset_CellOwnerImpl = cset_CellOwnerImpl;
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public MsgRes getMsgRes() {
		return this.msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
	
}