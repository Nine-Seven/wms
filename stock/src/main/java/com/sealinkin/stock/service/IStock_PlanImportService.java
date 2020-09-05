package com.sealinkin.stock.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.stock.model.Stock_PlanDModel;
import com.sealinkin.stock.model.Stock_PlanMModel;


public interface IStock_PlanImportService {
	//��ȡ���˵�ͷ����Ϣ�б�
	public ExtListDataBo<Stock_PlanMModel> getStockPlanMList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,String queryStr,PageBo pageBo)throws Exception;
	//��ȡ��Ʒȱ����Ϣ
	ExtListDataBo<Stock_ContentModel> getStockPlanArticleList(String enterpriseNo,String warehouseNo,String str)throws Exception;
	//��ȡ���˵���ϸ��Ϣ�б�
	public List<Stock_PlanDModel> getStockPlanDList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	//У��ԭ���˵����Ƿ����
	public String checkPoNo(String enterpriseNo, String warehouseNo,String strOwnerNo, String poNo)throws Exception;
	//ȡ��Ʒ����
	public List<Stock_PlanDModel> getArticle(String articleNo,String strEnterpriseNo)throws Exception;
	//ȡ����
	public List<ComboxBo> queryLot(String strEnterpriseNo,String articleNo,String produceDate,String lotNo)throws Exception;
	//��λ���� 
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String strWarehouseNo, String strJson,
				String str,String strQuery,  int i, int j);
	//У�����䴢λ��Ʒ�Ƿ��п��
	public String checkCellNo(String enterpriseNo, String warehouseNo,String strOwnerNo, String cellNo,String articleNo)throws Exception;

	//����
	public MsgRes save(String strJsonMaster, String strJsonDetail) throws Exception;
	//��λ
	public MsgRes send(String enterpriseNo,String workerNo, 
			String warehouseNo, String strDockNo,String str,String strQuery)throws Exception;
	//ȡ������
	public MsgRes closeOrder(String strQuery)throws Exception;
	
	//8-4 ���   �޸�
	public MsgRes changeStock(String strJsonMaster,String strJsonDetail)throws Exception;
		
	//8-4 ɾ��
	public void deleteStock(String currEnterpriseNo,String planNo)throws Exception;
}













