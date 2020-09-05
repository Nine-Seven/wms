package com.sealinkin.idata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_BsetDefbatchModel;

public interface Iidata_StraightCheckBatchService {
	//获取批次列表
	public ExtListDataBo<Idata_BsetDefbatchModel> getIdataStraightCheckBatchList(
			String enterpriseNo, String warehouseNo, PageBo pageBo,String operateDate,String batchNo) throws Exception;
	//获取UI批次下拉列表
	public List<ComboxBo> getBatchNoForUI(String enterpriseNo,String warehouseNo, String operateDate) throws Exception;
   //结束确认后更新状态
	public MsgRes endBatch(String enterpriseNo,String warehouseNo, String operateDate,String batchNo)throws Exception;

}
