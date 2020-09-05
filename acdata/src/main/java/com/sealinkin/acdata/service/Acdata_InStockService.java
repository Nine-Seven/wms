package com.sealinkin.acdata.service;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_InstockDModel;
import com.sealinkin.acdata.model.Acdata_InstockMModel;
import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface Acdata_InStockService{
	//��ȡ��ⵥͷ��
	public ExtListDataBo<Acdata_InstockMModel> getAcdataInstockMList(String warehouseNo,String queryStr,PageBo pageBo);
	//��ȡ��ⵥ��ϸ
	public ExtListDataBo<Acdata_InstockDModel> getAcdataInstockDList(String wheresql,PageBo pageBo);
	//��ȡ����ѡ���б�
	public ExtListDataBo<Acdata_OrderMModel> getAcdataCheckSourceNoList(String queryStr,PageBo pageBo);
	//����������
	public List<Acdata_OrderMModel> queryAcdataSImport(String strWheresql)throws Exception;
	//��������Ļ������Ż�ȡ�ӵ�ͷ��
	public List<Acdata_OrderMModel> queryAcdataOrderMList(String strSourceNo)throws Exception;
	//��ȡ�ӵ�����ϸ
	public List<Acdata_OrderDModel> getAcdataOrderDList(String strOrderNo);
	//����
	public MsgRes save(String jsonMaster, String jsonDetail)throws Exception;
	//�޸�
	public MsgRes edit(String strInstockNo,String strArticleName,String strBarcodeNo, String strIstockQty, String strIstockWt, String strIstockVl)throws Exception;
	//���
	public MsgRes send(String strWarehouseNo,String strOrderNo,String strSourceNo,String strInstockNo)throws Exception;

}
