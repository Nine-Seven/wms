package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_LoadproposeMModel;
import com.sealinkin.protocolExchange.odata.OdataDeliverParameterModel;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

public interface IOdata_CarPlanService {

	/**
	 * 填充线路下拉控件
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @param strFlag
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryLineNoCombo(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strFlag)throws Exception;
	
	/**
	 * 获取承运商下拉
	 * @param strWarehouseNo
	 * @param strFlag
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryShipperNoCombo(
			String enterpriseNo,String strWarehouseNo,String strFlag)throws Exception;
	
	/**
	 * 获取标签下的承运商下拉
	 * @param strWarehouseNo
	 * @param strShipperNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryLabelShipperLineNo(
			String enterpriseNo,String strWarehouseNo,String strShipperNo)throws Exception;
	
	/**
	 * 获取派车单下拉
	 * @param strWarehouseNo
	 * @param strShipperNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryCarPlanCombo(
			String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 根据派车单获取配送对象
	 */
	public ExtListDataBo<Odata_LoadproposeMModel> queryDeliverObj(
			String enterpriseNo,String strWarehouseNo,String strDeliverObj)throws Exception;

	/**
	 * 获取标签头档
	 * @param strWarehouseNo
	 * @param strLineNo
	 * @param strShipperNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Stock_LabelMModel> queryStockLabelM(
			String enterpriseNo,String strWarehouseNo,String strLineNo, String strShipperNo,String strDeliverObj)throws Exception;
	
	/**
	 * 获取临时表的数据
	 * @param strWarehouseNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Stock_LabelMModel> queryStockLabelTmp(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 勾选中的数据保存进临时表
	 * @param strWarehouseNo
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveStockTmp(String enterpriseNo,String strWarehouseNo,String strWheresql)throws Exception;
	
	/**
	 * 删除取消勾选的数据
	 * @param strWarehouseNo
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteStockTmp(String enterpriseNo,String strWarehouseNo,String strWheresql)throws Exception;
	
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
	public MsgRes tscCloseCar(String enterpriseNo,
							  String strWheresql,
							  String strWarehouseNo,
							  String strWorkSpaceNo)throws Exception;
	
	/**
	 * 获取建议单号
	 * @param strWarehouesNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryLoadproposeNo(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 获取建议单头档
	 * @param strWarehouseNo
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_LoadproposeMModel> queryLoadProposeM(
			String enterpriseNo,String strWarehouseNo,String strLineNo,String strLoadproposeNo)throws Exception;
	
	/**
	 * 获取承运商下的线路
	 * @param strWarehouseNo
	 * @param strShipperNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryLoadproposeLineCombo(
			String enterpriseNo,String strWarehouseNo,String strShipperNo)throws Exception;
	
	
	/**
	 * 获取车辆下拉
	 * @param strWarehouseNo
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryCarCombo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)throws Exception;
	
	/**
	 * 整单封车
	 * @param strJsonDetail1
	 * @return
	 */
	public MsgRes tscLoadCar(String strJsonDetail1)throws Exception;
	
	
	/**
	 * 新增装车建议单头档
	 * @param strJsonDetail1
	 * @return
	 */
	public MsgRes tscPInsertOdataLoadproposeM(String strEnterpriseNo ,
			String strWareHouseNo ,//仓库编码
			String strDeliverObj  ,//配送对象（客户编码、单号）
			String strShipperNo   ,//承运商编码
			String strLineNo      ,//线路
			String strCarNo       ,//-车辆编码
			String strUserId      ,
			String strDockNo      ,
			String strDivideTrunk ,//分车编号
			String strcarPlanNo   ,//派车计划单
			String strSealNo      ,//封条号
			String strLoadType    ,//装车类型：1：按承运商；2：线路；3：配送对象（客户\单）
			String strPaperUserId)throws Exception;
	
	/**
	 * 新增装车建议明细档
	 * @param strJsonDetail1
	 * @return
	 */
	public MsgRes tscPOdataLoadCarItem(String strEnterpriseNo,String strWareHouseNo ,//仓库编码
			String strProposeNo  ,//配送对象（客户编码、单号）
			String strContainerNo   ,//承运商编码
			String strUserId      ,
			String strPaperUserId)throws Exception;
	/**
	 * 读取装车建议单信息
	 * @param OdataDeliverParameterModel
	 * @return
	 */
	public MsgRes GetLoadproposeInfo(String strRecvData)throws Exception;
	
	/**
	 * 新增装车建议单
	 * @param OdataDeliverParameterModel
	 * @return
	 */
	public MsgRes tscInsertLoadproposeM(OdataDeliverParameterModel odataDeliverModel)throws Exception;
	
	/**
	 * 封车功能
	 * @param OdataDeliverParameterModel
	 * @return
	 */
	public MsgRes tscPOdataDeliver(String enterpriseNo,String strWareHouseNo ,//仓库编码
			String strProposeNo  ,//装车建议单
			String strUserId   ,//装车人
			String strPaperUserId //单据人
			)throws Exception;
	
	/**
	 * 获取承运商下拉
	 * @param strWarehouseNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryDeliverCombo(
			String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 检查配送对象是否与客户一至
	 * @param strWarehouseNo
	 * @param strDeliverObj
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkDeliverObj(String enterpriseNo,String strWarehouseNo,String strDeliverObj)throws Exception;
	
	
	/**
	 * RF新增装车建议单
	 * @param strRecvData
	 * @return
	 */
	public MsgRes tscRfInsertLoadproposeM(String strRecvData)throws Exception;
	
	/**
	 * RF新增装车建议单明细
	 * @param strRecvData
	 * @return
	 */
	public MsgRes tscRfInsertLoadproposeD(String strRecvData)throws Exception;
	
	/**
	 * RF封车
	 * @param strRecvData
	 * @return
	 */
	public MsgRes tscRfSaveDeliver(String strRecvData)throws Exception;
    /**
     * 天天惠获取装车建议单号，获取不到则新增装车建议单
     * @param strRecvData
     * @return
     * @throws Exception
     */
	public MsgRes tscGetLoadproposeM(String strRecvData)throws Exception;
	/**
	 * 天天惠获取获校验客户/标签以及取装车客户装车信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes CheckCustLabelNo(String strRecvData)throws Exception;
	/**
	 * 天天惠装车客户扫描确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCustClose(String strRecvData)throws Exception;
	/***
	 * 天天惠标签装车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCreateLoadItem(String strRecvData)throws Exception;
	/***
	 * 天天惠封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCloseCarTTH(String strRecvData)throws Exception;
	/***
	 * 铁越封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCloseCarP(String strRecvData)throws Exception;
	
	/***
	 * 铁越装车确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscContainer(String strRecvData)throws Exception;
	/***
	 * 扫描派车单号，获取不到则新增装车建议单
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	//public MsgRes tscSendTY(String strRecvData)throws Exception;
	/***
	 * 扫描码头
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes GETDock(String strRecvData)throws Exception;

	 
	
	/***
	 * 已扫未扫标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes GETWsweep(String strRecvData)throws Exception;
	/***
	 * 以扫未扫数量
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes GETNoweep(String strRecvData)throws Exception;
	
	/***
	 * 读取派车单号,并校验是否可装车的过程
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscNotRead(String strRecvData)throws Exception;
	
	/* 按线路装车---扫描线路
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscLineScanLineNo(String strRecvData)throws Exception;

	/* 按线路装车---扫描客户编码/客户箱标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscLineScanCustNo(String strRecvData)throws Exception;

	/* 按线路装车---扫描 暂存区编码（月台号）+输入物流箱数
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscLineScanAreaNo(String strRecvData)throws Exception;
	
	/* 按线路装车---封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscLineCloseCar(String strRecvData)throws Exception;
	
	/* 装车获取暂存区编码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMGetBufferNo(String strRecvData)throws Exception;

	/* 按线路装车---客户扫描完成
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscLineCustScanOver(String strRecvData) throws Exception;

	/* 装车--改原天天惠装车--装车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscSLoadCar(String strRecvData) throws Exception;

	/* 装车--改原天天惠装车--扫描客户或标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscSScanLabel(String strRecvData) throws Exception;
	
	/* 装车--原天天惠装车--封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscSCloseCar(String strRecvData) throws Exception;
	
	/* 装车--改原天天惠装车--客户扫描确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes tscSCloseCust(String strRecvData) throws Exception;
    
	//面单交接-根据承运商编号获取装车单号 huangb 20160606
	public MsgRes tscGetLoadNoByShipperNo(String strRecvData) throws Exception;
    
	//面单交接-新增装车建议单明细 huangb 20160606
	public MsgRes tscInserLoadDByShipper(String strRecvData) throws Exception;
    
	//面单交接-根据承运商封车 huangb 20160606
	public MsgRes tscDeliverCarByShipper(String strRecvData) throws Exception;
	
	/* 按波次装车--扫描客户或标签
	 * huangb 20160707
	 */
    public MsgRes checkScanDataByWave(String strRecvData) throws Exception;
    
    /* 按波次装车
	 * huangb 20160707
	 */
	public MsgRes tscLoadCarByWaveNo(String strRecvData) throws Exception;
    
	/* 按波次装车--获取车辆信息
	 * huangb 20160708
	 */
	public MsgRes getCarInfo(String strRecvData) throws Exception;
    
	/* 按波次封车
	 * huangb 20160708
	 */
	public MsgRes tscDeliverCarByWaveNo(String strRecvData) throws Exception;
	
	

	/* 集货作业--扫提单号
	 * sunl 20160714
	 */
	public MsgRes tscCollectGoods_ScanPO_NO(String strRecvData) throws Exception;
	/* 集货作业--扫订单号
	 * sunl 20160714
	 */
	public MsgRes tscCollectGoods_ScanSourceExpNO(String strRecvData) throws Exception;
}
