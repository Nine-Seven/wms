package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.model.Odata_LocateMModel;
import com.sealinkin.odata.model.Odata_LocateShortLogModel;

/**
 * 出货调度
 */
public interface IOdata_LocataService {

	/**
	 * 获取货主下拉
	 * @param enterpriseNo 
	 * @return 
	 * @throws Exception
	 */
	public List<ComboxBo> queryOwnerCombo(String strWarehouseNo, String enterpriseNo)throws Exception;

	/**
	 * 获取客户下拉
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getCust(String strWarehouseNo, String enterpriseNo,String strOwnerNo)throws Exception;
	/**
	 * 获取备注下拉
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getExpRemark(String strWarehouseNo, String enterpriseNo,String strOwnerNo)throws Exception;
	
	/**
	 * 获取出货单别下拉
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryExpTypeCombo(String strWarehouseNo, 
			String enterpriseNo,String strFlag)throws Exception;

	/**
	 * 获取集单头档
	 * @param strWarehouseNo
	 * @param strWhereSql
	 * @param poPageBo
	 * @param linkValue 
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_ExpMModel> queryLocateM(
			String enterpriseNo,String strWarehouseNo,
			String strWheresql,String strFlag,PageBo poPageBo,
			String linkValue,String str)throws Exception;
	
	/**
	 * 获取集单明细
	 * @param strWarehouseNo
	 * @param strIsNotEnought2 
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_ExpDModel> queryLocateD(
			String enterpriseNo,String strWarehouseNo,String strFlag,String strExpNo,String strCustNo,String strIsNotEnought)throws Exception;
	
	/**
	 * 获取客户策略
	 * @param String enterpriseNo 
	 * @return
	 * @throws Exception
	 */
	public List<String> queryTactics(String enterpriseNo)throws Exception;
	
	//重算月台资源
	public MsgRes tscBackrollres(String enterpriseNo,String warehouseNo,
			String strOwnerNo, String strExpType, String strFlag)throws Exception;
	
	//获取月台可用货位数v2
	public MsgRes tscBufferQty(String enterpriseNo,String warehouseNo,
			 String strFlag)throws Exception;
	
	/**
	 * 保存集单临时表
	 * @param strDetail
	 * @param strFlag
	 * @param linkValue 
	 * @param expDate 
	 * @param strFlag2 
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOdataTmpLocateSelect(String enterpriseNo,String strDetail,String strFlag, String expDate, String linkValue)throws Exception;
	
	/**
	 * 删除集单临时表
	 * @param strDetail
	 * @param strFlag
	 * @param strFlag2 
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteOdataTmpLocateSelect(String enterpriseNo,String strDetail,String strFlag)throws Exception;
	
	/**
	 * 定位
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscFixed(String strDetail)throws Exception;
	
	/**
	 * 删除临时表
	 * @param string 
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteTmpTable(String enterpriseNo,String warehouseNo)throws Exception;
	
	/**
	 * 获取临时表长度
	 * @param string 
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryTmpTableLength(String enterpriseNo,String warehouseNo)throws Exception;
	
	/**
	 * 定位 过程 管理事务
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public MsgRes fixed(String enterpriseNo,String strWorkerNo,String strWheresql)throws Exception;
	
	/**
	 * 计算勾选的单据详情
	 * @param string 
	 */
	public List<Odata_ExpMModel> countDetail(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 检查是否有商品缺量
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkNoEnoght(String enterpriseNo,String strWarehouseNo,String strFlag)throws Exception;
	
	public List<Odata_LocateMModel> getOdata_Locate_M(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String str,
			int start,
			int pagesize);
	//获取路线
	public List<ComboxBo> getLink(String enterpriseNo,String warehouseNo, String strQuery)throws Exception;

	//获取缺量的订单
	public ExtListDataBo<Odata_ExpMModel> checkOrder(String enterpriseNo,String warehouseNo,
			 PageBo poPageBo, String articleNo)throws Exception;

	//为要定位的商品添加储位
	public MsgRes changeArticle(String enterpriseNo,String warehouseNo, String ownerNo,
			String strCell )throws Exception;

	//获取储位
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String warehouseNo, String str,
			String strWheresql, int i, int j);

	public List<ComboxBo> queryOrgNoCombo(String warehouseNo,
			String currEnterpriseNo)throws Exception;

	public ExtListDataBo<Odata_LocateShortLogModel> getLocateFail(
			String currEnterpriseNo, String warehouseNo,String strQuery, PageBo poPageBo)throws Exception;

	public List<ComboxBo> queryDeliverTypeCombo(String warehouseNo,
			String currEnterpriseNo)throws Exception;

	public List<ComboxBo> getlocateArticleNo(String warehouseNo,
			String currEnterpriseNo,String articleNo, String strQuery)throws Exception;

	public List<ComboxBo> getlocateCustNo(String currEnterpriseNo,
			String warehouseNo,String pageSql, String strQuery)throws Exception;

	//保存调度临时表V2(电商版) 
	public MsgRes saveOdataTmpLocateSelectV2(String currEnterpriseNo,
			String strDetail, String strFlag, String calFlag, String expDate,
			String linkValue)throws Exception;

	//删除调度临时表v2(电商版)
	public MsgRes deleteOdataTmpLocateSelectV2(String currEnterpriseNo,
			String strDetail, String strFlag, String calFlag)throws Exception;

	//改变月台试算，重整月台
	public MsgRes changeCalculation(String currEnterpriseNo,
			String warehouseNo, String strOwnerNo, String expType,
			String calFlag)throws Exception;
	//获取定位失败查询-波次号下拉
	public List<ComboxBo> getLocateNoForQuery(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String strQuery) throws Exception;
	//获取定位失败查询-商品编码下拉
	public List<ComboxBo> getArticleNoForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str,String strQuery)throws Exception;
	//获取定位条件承运商下拉
	public List<ComboxBo> getlocateSanplNo(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str,String strQuery)throws Exception;
	//获取波次规则下拉v2
	public List<ComboxBo> queryOrdertypeCombo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner,String strQuery,String strExpType) throws Exception;
	
	
	//根据波次规则写临时表（过滤）
	public MsgRes tscOdataTmpLocateSelect(String currEnterpriseNo,
			String warehouseNo, String strOwnerNo, String strOrdertype,
			String strExpType)throws Exception;
	
}
