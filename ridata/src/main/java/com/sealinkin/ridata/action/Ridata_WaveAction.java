package com.sealinkin.ridata.action;

import com.sealinkin.bset.model.Bset_WaveManageModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.service.IRidata_WaveService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 返配波次管理action
 * @author hkl
 */
public class Ridata_WaveAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IRidata_WaveService ridata_WaveImpl;
	private String waveNo;
	private String status;
	
	//获取波次 列表
	public void getRidata_WaveList(){
			try 
			{		
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Bset_WaveManageModel> list = ridata_WaveImpl.getRidata_WaveList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), pageBo,status);
				super.writeStr(covtObjectToJson(list));
			} catch (Exception e){
				e.printStackTrace();
			}
		}

	//结束扫描
	public void endSweep(){
			try {
				MsgRes msg = ridata_WaveImpl.endSweep(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerNo(),
						waveNo);
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
		}
	
	
	public IRidata_WaveService getRidata_WaveImpl() {
		return ridata_WaveImpl;
	}
	public void setRidata_WaveImpl(IRidata_WaveService ridata_WaveImpl) {
		this.ridata_WaveImpl = ridata_WaveImpl;
	}

	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
