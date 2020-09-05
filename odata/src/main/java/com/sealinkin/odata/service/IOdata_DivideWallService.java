/**
 * 模块名称：分播墙回单
 * 模块编码：3915
 * 创建：lich
 */

package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.model.Device_DivideMModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

@SuppressWarnings("rawtypes")
public interface IOdata_DivideWallService {

	public List getOdata_DivideWallCell(String strEnterpriseNo,String strWarehouseNo,String strDeviceNo) throws Exception;
	
	public List<Stock_LabelMModel> getOdata_Divide_Direct(String strEnterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String str) throws Exception;
	
	public List<ComboxBo> getOdata_Divide_Art(String strEnterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strEquipmentNo,
			String str) throws Exception;
	
	public List<Odata_DivideDModel> tscSelectEquipmentNo(String strEnterpriseNo,
			String strWarehouseNo,
			String strEquipmentNo,
			String strDeviceNo,		//分播墙号
			String strWrokNo,
			String strDockNo) throws Exception;
	
	public MsgRes tscSendDivide(String strEnterpriseNo,
			String strWarehouseNo,
			String strEquipmentNo,
			String strDeviceNo,		//分播墙号
			String strWrokNo,
			String strDockNo) throws Exception;	
		
	public MsgRes getOdata_Divide(String strEnterpriseNo,
			String strWarehouseNo,
			String strEquipmentNo,
			String str) throws Exception;
	//校验分播墙号  6-25
	public MsgRes checkdeviceNo(String enterpriseNo,String warehouseNo,String deviceNo)throws Exception;
	
	//检查分播人员编号是否存在hj
	public MsgRes checkWorkerNo(String strEnterpriseNo,
			String strWorkerNo) throws Exception;
	
	//从device_divide_m 获得 分播墙号device_no集合  hj
	public MsgRes getOdata_DivideWallDeviceNo(String strEnterpriseNo,
			String strWarehouseNo) throws Exception;
			
	//通过标签号获得对应的商品信息  hj 7-1
	public ExtListDataBo<Odata_DivideDModel> queryDivideDetailList(
			String enterpriseNo,
			String strWarehouseNo,
			String labelNo,
			PageBo poPageBo)throws Exception;	 
				
	
	public List<Odata_DivideDModel> getOdata_Divide1(String strEnterpriseNo,//6-28修改了方法名
			String strWarehouseNo,
			String strEquipmentNo,String strDivideNo) throws Exception;	
	
	public MsgRes tscSaveDivide(String strEnterpriseNo,
			String strWarehouseNo,
			String strEquipmentNo,
			String strArticleNo,
			//Double nRealQty,
			//String strDpsCellNo,
			String strLabelNo,		//7-18添加
			String strAssign_No,
			String strWrokNo) throws Exception;	
	
	public MsgRes tscSureDivide(String strEnterpriseNo,
			String strWarehouseNo,
			String strEquipmentNo,
			String strAssign_No,
			String strWrokNo) throws Exception;		
}
