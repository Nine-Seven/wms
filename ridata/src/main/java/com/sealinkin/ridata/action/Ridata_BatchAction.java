package com.sealinkin.ridata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_BsetDefbatchModel;
import com.sealinkin.ridata.service.IRidata_BatchService;

/**
 * 返配批次管理action
 * @author hcx
 */
public class Ridata_BatchAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IRidata_BatchService ridata_BatchImpl;
	private String batchNo;
	private String operateDate;
	
	//获取批次列表
	public void getRidata_BatchList(){
			try 
			{		
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Ridata_BsetDefbatchModel> list = ridata_BatchImpl.getRidata_BatchList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), pageBo,operateDate,batchNo);
				super.writeObj(list);		
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	//获取UI的批次下拉
	public void getBatchNoForUI(){
			try 
			{
				List<ComboxBo> list = ridata_BatchImpl.getBatchNoForUI(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),operateDate);
				super.writeArray(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//结束扫描
	public void endSweep(){
			try {
				MsgRes msg = ridata_BatchImpl.endSweep(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),operateDate,
						batchNo);
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public IRidata_BatchService getRidata_BatchImpl() {
		return ridata_BatchImpl;
	}
	public void setRidata_BatchImpl(IRidata_BatchService ridata_BatchImpl) {
		this.ridata_BatchImpl = ridata_BatchImpl;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	
}
