package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefShipperModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.oset.model.Oset_ShipperLineModel;

/**
 * 承运商资料维护接口
 * @author zhouhuan
 */
public interface IBdef_DefShipperService {
	
	public List<ComboxBo> getCarCombo(String enterpriseNo,String warehouseNo,String pageSql)throws Exception;
	
	/**
	 * 获得承运商资料维护列
	 * @param strQuery2 
	 * @throws Exception 
	 */
	public ExtListDataBo<Bdef_DefShipperModel> getDefShipperList(
			String enterpriseNo,String warehouseNo, String strQuery, PageBo pageBo,
			Integer strRequestFlag) throws Exception;
	
	//删除承运商hj
	public MsgRes delete(String enterpriseNo,String warehouseNo, String shipperNo);
	
	/**
	 * 获得承运商资料维护列
	 * @param strQuery2 
	 * @throws Exception 
	 * hj
	 */
	public ExtListDataBo<Bdef_DefShipperModel> getDefShipperMaintainList(
			String enterpriseNo,String warehouseNo, String strQuery, PageBo pageBo,
			Integer strRequestFlag) throws Exception;
	
	//填充承运商下拉控件 7-7添加  hj
	public List<ComboxBo> queryBdefDefShipperCombo(
			String enterpriseNo,String strOwnerNo,String strWheresql, String strQuery)throws Exception;

	/**
	 * 承运商编号校验
	 * @param warehouseNo 
	 * @param string 
	 */
	public String checkShipperNo(String enterpriseNo,String strShipperNo, String warehouseNo)throws Exception;

	//保存
	public MsgRes saveOrUpdateShipper(String str, String workerNo) throws Exception;
	
	//保存或修改承运商hj
	public MsgRes saveOrUpdateShipperMaintain(String str, String workerNo) throws Exception;
	
	//填充报表id下拉
	public List<ComboxBo> queryReportIdCombo(
			String enterpriseNo,String warehouseNo,String strOwnerNo,String strFlag,String strWheresql)throws Exception;
	
	
	/**
	 * 获取未分配的线路列表
	 * @param strWarehouseNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Oset_DeflineModel> queryLineList(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	/**
	 * 获取承运商下的线路
	 * @param strWarehouseNo
	 * @param strShipperNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Oset_ShipperLineModel> queryShipperLine(
			String enterpriseNo,
			String strWarehouseNo,
			String strShipperNo)throws Exception;
	
	/**
	 * 删除承运商关系
	 * @param strShipperNo
	 * @param strLineNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteShipperLine(String enterpriseNo,String strWarehouseNo,String strShipperNo,String strLineNo)throws Exception;
	
	/**
	 * 新增承运商关系
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes insertShipperLine(String strDetail)throws Exception;
	
}
