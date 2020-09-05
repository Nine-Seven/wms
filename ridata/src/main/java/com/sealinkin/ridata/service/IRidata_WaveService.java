package com.sealinkin.ridata.service;


import com.sealinkin.bset.model.Bset_WaveManageModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IRidata_WaveService {
	//获取批次列表
	public ExtListDataBo<Bset_WaveManageModel> getRidata_WaveList(
			String enterpriseNo,String warehouseNo,
			PageBo pageBo,String status) throws Exception;
    //结束确认后更新状态
	public MsgRes endSweep(String enterpriseNo,String warehouseNo,String workerNo,String waveNo)throws Exception;

}
