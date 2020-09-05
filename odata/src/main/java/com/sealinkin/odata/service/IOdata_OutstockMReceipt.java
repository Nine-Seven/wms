package com.sealinkin.odata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;

/**
 * 模块名称：拣货回单
 * 模块编码：3401
 * 创建：周欢
 */
public interface IOdata_OutstockMReceipt {
	/**
	 * 获取拣货回单单据信息
	 * @author 周欢
	 */
	public ExtListDataBo<Odata_OutstockMModel> getOdata_OutstockM(String enterpriseNo, String warehouseNo,String owner,String flag,String str,
			String strSendFlag, int start,
			int pagesize);
	
	/**
	 * 获取拣货回单商品信息
	 * @author 周欢
	 */
	public ExtListDataBo<Odata_OutstockDModel> getOdata_OutstockD(String enterpriseNo,String warehouseNo,String owner,String flag,String str,
			String strSendFlag, int start,
			int pagesize) throws Exception;
	
	/**
     * 拣货回单 出货单别、波次……下拉
     * @author 周欢
	 * @param strCheckFlag 
     */
	public List<ComboxBo> getOdata_OutstockMReceiptCombo(String enterpriseNo,String warehouseNo,String workerOwner,String flag,String str,
			String strSendFlag, String strCheckFlag,String strB2CYesOrNo, int start,
			int pagesize) ;
	
	/**
     * 拣货回单》回单
     * @author 周欢
     */
	public MsgRes save(String enterpriseNo,String workerNo,String outstockName,String strPickType,String str, String workSpaceNo) throws Exception;
	
	//public MsgRes labelNoEdit(String warehouseNo,String workerOwner,String str) throws Exception;

	/**
     * 拣货回单》标签回单
     * @author 周欢
     */
	public MsgRes saveLabel(String enterpriseNo,String workerNo, String strOutstockNo,
			String strPickType, String str, String workSpaceNo)throws Exception;
	

	/**
	 * Rf校验板号
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes IdataCheckLabelNo(String strRecvData)throws Exception;
    
	
	/***
	 * RF校验板号（边拣边分）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataCheckLabelNoExpNo (String strRecvData)throws Exception;
	/**
	 * 任务标签拣货回单
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscPTaskLabelSaveOdataOutstock(
			String strWarehoseNo,//仓别
			String strOutstockNo    ,//下架单号
			String strFixLabelNo    ,//标签号码
			String strArticleNo     ,//商品编码
			Double nPackingQTY      ,//包装数量
			String strSCellNo       ,//来源储位
			Double nRealQTY         ,//下架数量
			String strQuality        ,//品质
			Date dtProduceDate     ,//生产日期
			Date dtExpireDate      ,//到期日期
			String strLotNo          ,//批次号
			String strRsvBatch1     , //预留批属性1
			String strRsvBatch2     , //预留批属性2
			String strRsvBatch3     , //预留批属性3
			String strRsvBatch4     , //预留批属性4
			String strRsvBatch5     , //预留批属性5
			String strRsvBatch6     , //预留批属性6
			String strRsvBatch7     , //预留批属性7
			String strRsvBatch8     , //预留批属性8                                      
			String strDockNo        ,//工作站
			String strUserID         ,//回单人
			String strOutstockID    ,//下架人
			String strInstockID     //上架人
			)throws Exception;
	
	/**
	 * 任务标签拣货回单
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscPOutStockDivide(String strwarehouse_no   , //仓别
			String strOWNER_NO       , //委托业主
			String strDockNo         , //码头
			String strSource_No      , //来源单号
			String strS_CONTAINER_NO , //来源容器
			String strAssign_No      , //预定分播人员
			String strUserID         , //系统操作人员
			String strPrint_Flag)throws Exception;

	/**
	 * lich 20140721
	 * 容器并板
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscPMergeContainer(String strWarehoseNo   , //仓别
			String strScontainerNo       , //源容器号
			String strDcontainerNo         , //目的容器号
			String strArticleNo      , //商品编码
			Double nPackingQty , //商品包装
			Double nMoveQty      , //移动数量
			String strBatchNo         , //批次号
			String strQuality        ,//品质
			Date dtProduceDate     ,//生产日期
			Date dtExpireDate      ,//到期日期
			String strLotNo          ,//批次号
			String strRsvBatch1     , //预留批属性1
			String strRsvBatch2     , //预留批属性2
			String strRsvBatch3     , //预留批属性3
			String strRsvBatch4     , //预留批属性4
			String strRsvBatch5     , //预留批属性5
			String strRsvBatch6     , //预留批属性6
			String strRsvBatch7     , //预留批属性7
			String strRsvBatch8     , //预留批属性8    
			String strUserId,//系统操作人员
			String strTerminalFlag//操作设备
			)throws Exception;
	
	public MsgRes tscOmPickReceipt(String strRecvData)throws Exception;
	/***
	 * 拣货回单确认（边拣边分）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscOmPickTypeExpNo(String strRecvData)throws Exception;
	
	/***
	 * 根据标签号获取分播数据(边拣边分)
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryOdataDivideDQty(String strRecvData)throws Exception;
	/**
	 * Rf校验员工号
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes CheckWorkerNo(String strRecvData) throws Exception;
	/**
	 * Rf校验条码是否为当前操作商品
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes IdataCheckBarcode(String strRecvData)throws Exception;
	
	/**
	 * Rf拣货回单，Add by sunl
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscOmReceiptPicking(String strRecvData)throws Exception;

	/**
	 * Rf拣货回单 查询下架单信息，Add by sunl
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscOmGetOutStockInfo(String strRecvData)throws Exception;
	

	/**
	 * Rf下架单登记，Add by sunl
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscOutStockRegister(String strRecvData)throws Exception;
	
	/**
	 * 获取下架单所属的商品明细信息，Add by sunl
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes GetOutStockArticleInfo(String strRecvData)throws Exception;
	/**
	 * 拣货索单 校验码头号 sunl 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscCheckClaimDock(String strRecvData)throws Exception;
	/**
	 * 拣货索单 索单 sunl 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscClaimOrder(String strRecvData)throws Exception;
	
}
