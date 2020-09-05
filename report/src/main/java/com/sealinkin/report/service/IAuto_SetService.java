package com.sealinkin.report.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_Defcustom_DModel;
import com.sealinkin.wms.model.Wms_Defcustom_MenuModel;


public interface IAuto_SetService {

	//获取主设置菜单
	List<ComboxBo> getModubleMenu(String enterpriseNo) throws Exception;

	//保存子设置菜单
	MsgRes saveSubReport(String enterpriseNo, String workerNo,
			String modubleId, String customId, String customName)throws Exception;
	//获取子菜单列表
	ExtListDataBo<Wms_Defcustom_MenuModel> getReportformenu(
			String currEnterpriseNo, String strQuery, PageBo pageBo)throws Exception;

	//获取显示的子菜单
	List<ComboxBo> getReportList(String modubleId)throws Exception;

	//获取参数
	List<Wms_Defcustom_DModel> getCustomD(String enterpriseNo, String customId)throws Exception;

	//保存参数
	MsgRes saveCustomD(String wmsCustomD)throws Exception;

	//上移、下移
	MsgRes seqencingDfm(String currEnterpriseNo, String modubleId,
			String customId, String orderNo, String flag)throws Exception;

	//修改子菜单是否可见
	MsgRes changeShow(String enterpriseNo, String modubleId, String customId,
			String show)throws Exception;

}
