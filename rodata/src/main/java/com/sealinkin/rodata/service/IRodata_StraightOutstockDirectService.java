package com.sealinkin.rodata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDirectModel;


public interface IRodata_StraightOutstockDirectService {
    //获取波次下拉内容
	List<ComboxBo> getWaveNoComboList(String enterpriseNo,String warehouseNo,String str, Integer start, Integer pagesize);
    //获取商品明细
	ExtListDataBo<Rodata_OutstockDirectModel> getRodata_OutstockDirect(
			String enterpriseNo,String warehouseNo,String str, Integer start, Integer limit);
    //获取供应商下拉内容
	List<ComboxBo> getSupplierCombo(String enterpriseNo,String warehouseNo,String owenrNo,String str,String wheresql, String flag, Integer start,
			Integer pagesize);
	//获取退货类型下拉内容
	List<ComboxBo> getClassTypeComboList(String enterpriseNo,String warehouseNo,String owenrNo,String str,String wheresql, String flag, Integer start,
			Integer pagesize);

    //发单
	MsgRes send(String enterpriseNo,String warehouseNo,String workerNo,String sendWorker,String strWaveNo, String strSupplierNo, String workSpaceNo,String strClassType) throws Exception;

}
