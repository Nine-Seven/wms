/**
 * 直通验收批次管理action
 * @author hcx
 */
package com.sealinkin.idata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_BsetDefbatchModel;
import com.sealinkin.idata.service.Iidata_StraightCheckBatchService;

public class Idata_StraightCheckBatchAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private Iidata_StraightCheckBatchService idata_StraightCheckBatchImpl;
	private String batchNo;
	private String operateDate;
	
	//获取批次列表
	public void getIdataStraightCheckBatchList(){
			try 
			{		
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Idata_BsetDefbatchModel> list = idata_StraightCheckBatchImpl.getIdataStraightCheckBatchList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), pageBo,operateDate,batchNo);
				super.writeObj(list);		
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	//获取UI的批次列表
	public void getBatchNoForUI(){
			try 
			{
				List<ComboxBo> list = idata_StraightCheckBatchImpl.getBatchNoForUI(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),operateDate);
				super.writeArray(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//结束扫描
	public void endBatch(){
			try {
				MsgRes msg = idata_StraightCheckBatchImpl.endBatch(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),operateDate,
						batchNo);
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public Iidata_StraightCheckBatchService getIdata_StraightCheckBatchImpl() {
		return idata_StraightCheckBatchImpl;
	}
	public void setIdata_StraightCheckBatchImpl(
			Iidata_StraightCheckBatchService idata_StraightCheckBatchImpl) {
		this.idata_StraightCheckBatchImpl = idata_StraightCheckBatchImpl;
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
