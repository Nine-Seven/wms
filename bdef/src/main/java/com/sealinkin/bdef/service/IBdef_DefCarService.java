package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefcarModel;
import com.sealinkin.bdef.model.Bdef_DefcartypeModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefCarService {
	
	//获取车辆类型列表
	public ExtListDataBo<Bdef_DefcartypeModel> getCartypeList(String strEnterpriseNo,
			String strQuery,PageBo pageBo)throws Exception;
	//车辆类型下拉
	public List<ComboxBo> getCarTypeQuery(String strEnterpriseNo,String queryStr) throws Exception;
	//判断车辆类型代码是否唯一
	public List<String> carTypeCheck(String enterpriseNo,String queryStr) throws Exception;
	//车辆类型保存
	public MsgRes saveCarType(String queryStr)throws Exception;
	//删除车辆类型
	public MsgRes deleteCarType(String enterpriseNo,String queryStr)throws Exception;
	//获取车辆信息列表
	public ExtListDataBo<Bdef_DefcarModel> getCarList(String strEnterpriseNo,
			String strWarehouseNo,String strQuery,String str,PageBo pageBo)throws Exception;
	//判断车辆代码是否唯一
	public List<String> carCheck(String enterpriseNo,String strWarehouseNo,String queryStr) throws Exception;
	//车辆信息保存
	public MsgRes saveCar(String queryStr)throws Exception;
	
	//填充车辆类型代码下拉控件 7-9添加  hj  
	public List<ComboxBo> querycarTypeNoCombo(
			String enterpriseNo,String strOwnerNo,String strWheresql, String strQuery)throws Exception;
	
	//填充车辆类型名称下拉控件 7-9添加  hj  
	public List<ComboxBo> querycarTypeNameCombo(
			String enterpriseNo,String strOwnerNo,String strWheresql, String strQuery)throws Exception;

	//填充车辆名称下拉控件 7-9添加  hj  
	public List<ComboxBo> querycarNameCombo(
			String enterpriseNo,String strWarehouseNo,String strWheresql, String strQuery)throws Exception;

	//填充车牌号下拉控件 7-9添加  hj  
	public List<ComboxBo> querycarPlateCombo(
			String enterpriseNo,String strWarehouseNo,String strWheresql, String strQuery)throws Exception;

	//填充司机名称下拉控件 7-9添加  hj  
	public List<ComboxBo> querydeiverWorkerCombo(
			String enterpriseNo,String strWarehouseNo,String strWheresql, String strQuery)throws Exception;
}




























