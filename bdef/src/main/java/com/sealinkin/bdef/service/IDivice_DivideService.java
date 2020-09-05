package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Device_DivideDModel;
import com.sealinkin.bdef.model.Device_DivideMModel;
import com.sealinkin.bdef.model.Device_Divide_GroupModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IDivice_DivideService {
	//获取设备组
	ExtListDataBo<Device_Divide_GroupModel> getDeviceDivideGroup(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo)throws Exception;

	//保存设备组
	void saveDeviceDivideGroup(String str)throws Exception;

	//获取设备
	ExtListDataBo<Device_DivideMModel> getDeviceDivideM(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo)throws Exception;

	//获取设备组combo
	List<ComboxBo> getDeviceDivideGroupCombo(String currEnterpriseNo,
			String warehouseNo, String strQuery)throws Exception;

	//保存设备
	void saveDeviceDivideM(String str)throws Exception;

	//设备唯一性
	String checkDeviceNo1S01(String currEnterpriseNo, String warehouseNo,
			String str)throws Exception;

	//获取设备combo
	List<ComboxBo> getDeviceDivideMCombo(String currEnterpriseNo,
			String warehouseNo, String strQuery)throws Exception;
	
	//保存格子号
	void saveDeviceDivideD(String enterpriseNo, String warehouseNo, String workerNo,
			String str,String perfix, String bayXMin, String bayXMax, String floorMin,
			String floorMax)throws Exception;

	//获取格子信息
	ExtListDataBo<Device_DivideDModel> getDeviceDivideD(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo)throws Exception;

	//修改拣货顺序
	void changePickOrder(String str)throws Exception;

	//编辑格子号
	void editDeviceDivideD(String str)throws Exception;

	MsgRes checkCell(String currEnterpriseNo, String warehouseNo,
			String strDeviceDivideGroup, String strDeviceNo, String perfix,
			String bayXMin, String bayXMax, String floorMin, String floorMax)throws Exception;

}
