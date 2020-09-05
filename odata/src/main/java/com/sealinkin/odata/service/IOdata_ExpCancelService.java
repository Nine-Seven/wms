package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_ExpCancelDModel;
import com.sealinkin.odata.model.Odata_ExpCancelMModel;

public interface IOdata_ExpCancelService {
	/**
	 * 获取货主下拉内容
	 * @param strEnterpriseNo
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getOwnerCombo(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str)throws Exception;
    /**
     * 获取病单来源下拉内容
     * @param strEnterpriseNo
     * @param strWarehouseNo
     * @param str
     * @return
     * @throws Exception
     */
	public List<ComboxBo> getSuorceTypeCombo(String strEnterpriseNo,String strWarehouseNo,String str,String strFlag)throws Exception;
	/**
     * 获取单据状态下拉内容
     * @param strEnterpriseNo
     * @param strWarehouseNo
     * @param str
     * @return
     * @throws Exception
     */
	public List<ComboxBo> getStatusTextCombo(String strEnterpriseNo,String strWarehouseNo,String str,String strOperateDate)throws Exception;

	/**
     * 获取撕票单号下拉内容
     * @param strEnterpriseNo
     * @param strWarehouseNo
     * @param str
     * @param strOperateDate
     * @return
     * @throws Exception
     */
	public List<ComboxBo> getCancelNoCombo(String strEnterpriseNo,String strWarehouseNo,String str,String strOperateDate,String strFlag)throws Exception;
	/**
     * 获取病单处理单头档信息
     * @param strEnterpriseNo
     * @param strWarehouseNo
     * @param str
     * @param strOperateDate
     * @param pageBo
     * @return
     * @throws Exception
     */
	public ExtListDataBo<Odata_ExpCancelMModel> getExpCancelMList(String strEnterpriseNo,String strWarehouseNo,String str,String strOperateDate,String strFlag,PageBo pageBo)throws Exception;
	/**
     * 获取病单处理单明细信息
     * @param strEnterpriseNo
     * @param strWarehouseNo
     * @param str
     * @param strOperateDate
     * @param pageBo
     * @return
     * @throws Exception
     */
	public ExtListDataBo<Odata_ExpCancelDModel> getExpCancelDList(String strEnterpriseNo,String strWarehouseNo,String str,String strFlag,PageBo pageBo)throws Exception;
    
	/**
     * 上传
     * @param strEnterpriseNo
     * @param strDetail
     * @param strDetail1
     * @param strFlag
     * @return
     * @throws Exception
     */
	public MsgRes upLocad(String strEnterpriseNo,String strDetail, String strFlag)throws Exception;
	/**
     * 补拣
     * @param strEnterpriseNo
     * @param strDetail
     * @param strFlag
     * @return
     * @throws Exception
     */
	public MsgRes repeatLocate(String strEnterpriseNo,String strDetail, String strFlag)throws Exception;

}
