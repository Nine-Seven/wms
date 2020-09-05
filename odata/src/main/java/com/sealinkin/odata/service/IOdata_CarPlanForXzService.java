package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

public interface IOdata_CarPlanForXzService {
	
	/**
	 * 根据车辆获取司机
	 */
	public MsgRes getDriverName(String enterpriseNo,String strWarehouseNo,String strQuery)throws Exception;
	
	//检查临时表数据是否存在
	public List<String> labelTmpCheck(String strEnterpriseNo,String strWarehouseNo,String strQuery) throws Exception;

	/**
	 * 删除临时表中的数据
	 * @param string2 
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteTmp(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 获取勾选单据的品项明细
	 * @param string2 
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public List<Stock_LabelMModel> getItems(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 查看明细
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Stock_LabelDModel> searchDetail(String enterpriseNo,String strWarehouseNo,String strFlag,String strWheresql)throws Exception;
	
	/**
	 * 装车建议单，封车
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCloseCar(String strWheresql,
							  String strWorkSpaceNo,
							  String strQuery)throws Exception;		
	/**
	 * 整单封车
	 * @param strJsonDetail1
	 * @return
	 */
	public MsgRes tscLoadCar(String strJsonDetail1)throws Exception;
	/**
	 * 封车功能
	 * @param OdataDeliverParameterModel
	 * @return
	 */
	public MsgRes tscPOdataDeliver(String enterpriseNo,String strWareHouseNo ,//仓库编码
			String strProposeNo  ,//装车建议单
			String strCarPlate,
			String strUserId   ,//装车人
			String strPaperUserId //单据人
			)throws Exception;
	//取车辆代码
	public List<ComboxBo> getCarNoQuery(String strEnterpriseNo,String strWarehouseNo,String queryStr) throws Exception;

}
