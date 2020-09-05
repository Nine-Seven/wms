package com.sealinkin.fcdata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.fcdata.model.Fcdata_CheckDModel;
import com.sealinkin.fcdata.model.Fcdata_CheckDirectModel;
import com.sealinkin.fcdata.model.Fcdata_CheckMModel;

/*
 *  @初盘发单接口
 */
public interface IFcdata_CheckService {
	
    /////////////////////////////////初盘发单/////////////////////////////////////////////////////
	//获取盘点类型
	public List<ComboxBo> getFcdataTypeCombo(
			String enterpriseNo,
			String warehouseNo,
			String workerOwner, 
			String strFlag,  
			int i,
			int j);
	
	//获得计划单号
	public List<ComboxBo> getPlanNoCombo(
			String enterpriseNo,
			String warehouseNo,
			String strOwner,
			String strFlag,
			String strFcdataType,
			int start,
			int pagesize) ;
	
	//获得盘点类别
	public List<String> CheckType(
			String enterpriseNo,
			String warehouseNo, 
			String workerOwner,
			String strPlanNo, 
			int i, int j);
	
	// 获得盘点定位指示
	public ExtListDataBo<Fcdata_CheckDirectModel> getCheckDirect(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String wheresql,
			String strFlag,
			PageBo pageBo)throws Exception;
	
	//切单
	public MsgRes tscCut8201(String str,String ownerNo) throws Exception;
	
	//初盘发单
	public MsgRes send(String str,String dockNo) throws Exception;
	
    //////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////复盘、三盘发单///////////////////////////////////////////
	
	// 获得盘点头档单 
	public ExtListDataBo<Fcdata_CheckMModel> getCheckM(	
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String strCheckType,
			String strDifferentFlag,
			PageBo pageBo)throws Exception;
	
	// 获得盘点头明细
	
	public ExtListDataBo<Fcdata_CheckDModel> getCheckD(	
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String wheresql,
			String strCheckType,
			PageBo pageBo)throws Exception;
	
	
	//复盘/三盘发单
	public MsgRes sendAgain(String str,String strCheckType,String dockNo) throws Exception;

	

	// 结束盘点
	public MsgRes sendEndFcdata(
			String enterpriseNo,
			String warehouseNo,
			String str,
			String workerNo) throws Exception;
   ////////////////////////////////////////////////////////////////////////////////////////////

}
