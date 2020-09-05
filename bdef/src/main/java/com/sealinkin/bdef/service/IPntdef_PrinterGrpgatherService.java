/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
package com.sealinkin.bdef.service;


import java.util.List;

import com.sealinkin.bdef.model.Pntdef_PrinterGrpgatherModel;
import com.sealinkin.bdef.model.Pntset_GrpgatherPrinterGroupModel;
import com.sealinkin.bset.model.Bset_GroupModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public interface IPntdef_PrinterGrpgatherService {
	//获取打印机群组信息
	public ExtListDataBo<Pntdef_PrinterGrpgatherModel> getPntdefPrinterGrpgatherList(
			String enterpriseNo,String warehouseNo,String queryStr,
			PageBo pageBo,Integer requestFlag)throws Exception;
	//获取打印机组信息
	public ExtListDataBo<Bset_GroupModel> getBset_GroupList(
			String enterpriseNo,String warehouseNo,String wheresql,String str,PageBo pageBo)throws Exception;
	//获取打印机群组列表
	public ExtListDataBo<Pntset_GrpgatherPrinterGroupModel> getPntsetGrpgatherPrinterGroupList(
			String enterpriseNo,String warehouseNo,String str,String wheresql,PageBo pageBo)throws Exception;
	//保存或修改打印机群组信息
	public boolean saveOrUpdateGroup(String str)throws Exception;
	//群组添加打印机组
	public boolean savePrinter_Group(String str)throws Exception;
	//群组移除打印机组
	public boolean deletePrinter_Group(
			String enterpriseNo,String warehouseNo,String grpgatherNo,String printerGroupNo)throws Exception;
	//校验打印机群组是否已存在
	public String checkGrpgatherNo(String enterpriseNo,String warehouseNo,String grpgatherNo)throws Exception;
	//根据打印机组编号获取打印机组(模糊查询)
	public List<ComboxBo> getPrinterGroupInfo(String strEnterpriseNo,String strWarehouseNo,String str,String strQuery,String wheresql)throws Exception;

}
