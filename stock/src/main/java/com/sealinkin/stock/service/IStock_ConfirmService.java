package com.sealinkin.stock.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ConfirmDModel;
import com.sealinkin.stock.model.Stock_ConfirmMModel;

public interface IStock_ConfirmService {
	//����������
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner)throws Exception;
	//��������������
	public List<ComboxBo> queryPlanTypeCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner,String str)throws Exception;
	//��ȡ����ȷ�ϵ�ͷ����Ϣ�б�
	public ExtListDataBo<Stock_ConfirmMModel> getStockConfirmMList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	//��ȡ����ȷ�ϵ���ϸ��Ϣ�б�
	public List<Stock_ConfirmDModel> getStockConfirmDList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	//����ȷ��
	public MsgRes tscConfirm(String strJsonMaster)throws Exception;

}
