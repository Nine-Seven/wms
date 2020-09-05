package com.sealinkin.stock.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ADjDModel;

public interface IStock_AdjustAccountsService {
	/**
	 * ��ȡ��Ʒ����
	 * @param strOwnerNo
	 * @param strFlag
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getArticleCombo(String strEnterpriseNo,String warehouseNo,
			String strOwnerNo,String strQuery,String strWheresql)throws Exception;
	/**
	 * �������ں�����
	 * @param strOwnerNo
	 * @param strQuery
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getProductDateAndLotNo(String strEnterpriseNo,String warehouseNo,
			String strOwnerNo,String strQuery)throws Exception;
	/**
	 * ��ȡ��������ϸ
	 * @param strOwnerNo
	 * @param strFlag
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Stock_ADjDModel> getStockADjDList(String strEnterpriseNo,String warehouseNo,
			String strOwnerNo,String strQuery,PageBo pageBo)throws Exception;
	/**
	 * �����������Ϣ
	 * @param jsonMaster
	 * @param jsonDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes save(String jsonMaster, String jsonDetail)throws Exception;

	/**
	 * ����Ʒ��>>��Ʒ����ѡ��
	 * @param articleNo
	 * @return
	 * @throws Exception
	 */
	public List<Stock_ADjDModel> queryArticleInfo(String strEnterpriseNo,String strWheresql,String strOwnerNo)throws Exception;
	/**
	 * ��ȡ��λ
	 * @param warehouseNo
	 * @param str
	 * @param strWheresql
	 * @param i
	 * @param j
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getCdef_DefCellCombo(String strEnterpriseNo,String warehouseNo, String str,
			String strWheresql, int i, int j)throws Exception;
	/**
	 * ��ȡ��������
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<Stock_ADjDModel> queryLot(String strEnterpriseNo,String articleNo,String produceDate)throws Exception;
	public List<Bdef_DefarticleModel> getOwnerArticleNO(String strEnterpriseNo,String articleNo,String strOwnerNo)throws Exception;

}
