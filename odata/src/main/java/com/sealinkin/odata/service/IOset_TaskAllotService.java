package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.oset.model.Oset_TaskAllotDModel;
import com.sealinkin.oset.model.Oset_TaskAllotMModel;

public interface IOset_TaskAllotService {

	//获取切单规则头档
	public ExtListDataBo<Oset_TaskAllotMModel> getAccountList(String warehouseNo,
			PageBo pageBo)throws Exception;

	//保存或更新切单规则头档
	public void saveOrupdateTaskAllotM(String str)throws Exception;

	//获取默认配置的下拉
	public List<ComboxBo> getDefaultFlagmComboList() throws Exception;

	//判断当前仓别tasId是否唯一
	public List<String> billingProjectCheck(String warehouseNo, String str) throws Exception;

	//获取储区(匹配了规则或没匹配规则的储区，区别在于this.getStr()有木有值)
	public ExtListDataBo<Cdef_DefareaModel> getDefarea(String warehouseNo,
			PageBo pageBo, String str);

	//跟新切单规则和储区的关系
	public void updateAllotAndDefareaRelation(String taskId,
			String warehouseNo, String wareNo, String areaNo);
	
	//根据切单规则头档获取切单规则明细
	public ExtListDataBo<Oset_TaskAllotDModel> getAccountList(PageBo pageBo,
			String str) throws Exception;

	//获取下架类型下拉
	public List<ComboxBo> getOutstockTypeComboList() throws Exception;

	//获取切单范围下拉
	public List<ComboxBo> getAllotRuleComboList() throws Exception;

	//获取切单规则下拉
	public List<ComboxBo> getBoxFlagComboList() throws Exception;

	//获取拣货打单方式下拉
	public List<ComboxBo> getTaskTypeComboList() throws Exception;
	
	//获取作业类型2
	public List<ComboxBo> getOoperateTypeComboList() throws Exception;

	//保存或修改订单规则明细
	public void saveOrupdateTaskAllotD(String str) throws Exception;

	//获取作业类型1
	public List<ComboxBo> getSourceTypeComboList(String str);


}
