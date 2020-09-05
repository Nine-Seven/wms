package com.sealinkin.ridata.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.ridata.service.IRidata_TacticsService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.MyDateMorpher;
import com.sealinkin.wms.model.Wms_OutorderFlowModel;
import com.sealinkin.wms.model.Wms_RiorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.model.Wms_OwnerRiorderModel;
import com.sealinkin.wms.model.Wms_OwnerOutorderModel;
import com.sealinkin.wms.po.Wms_Riordertype;
import com.sealinkin.wms.po.Wms_OwnerRiordertype;
import com.sealinkin.wms.po.Wms_WarehouseRiordertype;
import com.sealinkin.wms.po.Wms_OutorderFlow;
import com.sealinkin.wms.po.Wms_OwnerOutorder;
import com.sealinkin.wms.po.Wms_OwnerOutorderFlow;
import com.sealinkin.wms.po.Wms_WarehouseOutorder;
import com.sealinkin.wms.po.Wms_WarehouseOutorderFlow;

@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_TacticsImpl implements IRidata_TacticsService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	////////////////////////////////////////系统级出货策略配置///////////////////////////////////////////////////

	/**
	 * 获取策略主档
	 */
	@Override
	public ExtListDataBo<Wms_RiorderModel> queryWmsRiOrderList(PageBo poPageBo,String enterpriseNo)
			throws Exception {
		String strSql=" select ENTERPRISE_NO,f_get_fieldtext('N','CLASS_TYPE',CLASS_TYPE) classTypeText,CLASS_TYPE classType,"+
				" f_get_fieldtext('RIDATA_UNTREAD_M','UNTREAD_TYPE',UNTREAD_TYPE) untreadTypeText,UNTREAD_TYPE untreadType,"+
				" f_get_fieldtext('RIDATA_UNTREAD_M','QUALITY',QUALITY_FLAG) qualityFlagText,QUALITY_FLAG qualityFlag,"+
       " f_get_fieldtext('N','YESORNO',OVER_QTY_FLAG )overQtyFlagText, OVER_QTY_FLAG overQtyFlag, "+
       " f_get_fieldtext('N','YESORNO',auto_instock )autoInstockText,auto_instock autoInstock ,"+
       " f_get_fieldtext('N','YESORNO',advance_locate ) advanceLocateText,advance_locate advanceLocate ,"+
       " f_get_fieldtext('N','YESORNO',device_compute ) deviceComputeText,device_compute deviceCompute,"+
       " f_get_fieldtext('N','YESORNO',mix_ordercheck ) mixOrdercheckText,mix_ordercheck mixOrdercheck ,"+
       " f_get_fieldtext('N','YESORNO',rsv_label_flag ) rsvLabelFlagText,rsv_label_flag rsvLabelFlag,"+
       " f_get_fieldtext('N','YESORNO',direct_cell_flag ) directCellFlagText,direct_cell_flag directCellFlag,"+
       " f_get_fieldtext('N','YESORNO',print_check_flag ) printCheckFlagText,print_check_flag printCheckFlag,"+
       " f_get_fieldtext('N','YESORNO',auto_check_comfir_flag ) autoCheckComfirFlagText,auto_check_comfir_flag autoCheckComfirFlag"+
       " from wms_riordertype "+
       " where ENTERPRISE_NO="+enterpriseNo ;
		String strTotsql = "select count(1) from wms_riordertype where ENTERPRISE_NO='"+enterpriseNo+"' ";
		List<Wms_RiorderModel> list=genDao.getListByNativeSql(
				strSql, Wms_RiorderModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_RiorderModel> extListBo = new ExtListDataBo<Wms_RiorderModel>(list,intCount);
		return extListBo;
	}
	
	/**
	 * 保存或修改策略明细
	 */
	@Override
	public MsgRes saveOrUpdateTactics(String strJsonDetail)
			throws Exception {
		Wms_Riordertype bd=(Wms_Riordertype)JSONObject.toBean(JSONObject.fromObject(strJsonDetail),Wms_Riordertype.class);
		this.genDao.saveOrUpdateObj(bd);
		System.out.println(bd.getId().getUntreadType());
		
		return new MsgRes(true,"保存成功",null);//保存成功！
	}
	
	/**
	 * 获取策略配置（查看）
	 */
	@Override
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsOutOrderFlow(
			String enterpriseNo,String strExpType,String strType) throws Exception {
		String strSql = "";
		if(strType.equals("1")){//新增的时候获取工作流节点
			strSql="select s.status_type,s.status_name,s.flow_flag,s.flow_value," +
					"row_number() over (order by s.flow_value) as flowOrder," +
					"s.flow_name as flowvalueText " +
					"from wms_deflabel_status s where s.flow_flag='2'";	
		}else if(strType.equals("0")){
			strSql=" select case when a.flow_flag =1 then 'true' else 'false' end flag," +
					" a.*, f_get_fieldtext('WMS_OUTORDER_FLOW','FLOW_VALUE',a.flow_value)flowvalueText " +
					"from wms_outorder_flow a " +
					"where a.enterprise_no='"+enterpriseNo+"' "+
					"and a.exp_type='"+strExpType+"' "  +
					"order by a.flow_order";
			
		}
		List<Wms_OutorderFlowModel> list=genDao.getListByNativeSql(
				strSql, Wms_OutorderFlowModel.class, 0, 100);
		ExtListDataBo<Wms_OutorderFlowModel> extListBo = new ExtListDataBo<Wms_OutorderFlowModel>(list,0);
		return extListBo;
	}
	
	
	
	//保存工作流
	@Override
	public MsgRes saveTactics(String strJsonDetail) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Wms_OutorderFlow> bb=JSONArray.toCollection(JSONArray.fromObject(strJsonDetail),Wms_OutorderFlow.class);
		List<Wms_OutorderFlow> bwl=(List)bb;
	//	deleteTactics(bwl.get(0).getId().getExpType());
		this.genDao.saveList(bwl);
		return new MsgRes(true,"保存成功","");
	}
	
	//删除工作流
	@Override
	public MsgRes deleteTactics(String levelFlag,String strQuery,String enterpriseNo) throws Exception {
		String strSql="";
		if(levelFlag.equals("1"))
		{
			strSql="delete wms_riordertype a where a.enterprise_no='"+enterpriseNo+"' ";
			if(strQuery!=null && !strQuery.equals(""))
			{
				String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
				strSql=strSql+strWs;
			}
		}
		if(levelFlag.equals("2"))
		{
			strSql="delete wms_owner_riordertype a where a.enterprise_no='"+enterpriseNo+"' ";
			if(strQuery!=null && !strQuery.equals(""))
			{
				String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
				strSql=strSql+strWs;
			}
		}
		if(levelFlag.equals("3"))
		{
			strSql="delete wms_warehouse_riordertype a where a.enterprise_no='"+enterpriseNo+"' ";
			if(strQuery!=null && !strQuery.equals(""))
			{
				String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
				strSql=strSql+strWs;
			}
		}
		genDao.updateBySql(strSql);
		return new MsgRes(true,"删除成功","");
	}
	
	//校验单据（三种级别公用）
	@Override
	public MsgRes checkExpType(String enterpriseNo,String warehouseNo,
			String strExpType,String strQuality,String strPriority,String wheresql,String ownerNo)
			throws Exception {
		String strSql = null;
		if(wheresql.equals("1")){//系统级别
			strSql="select a.UNTREAD_TYPE from wms_riordertype a " +
				"where a.UNTREAD_TYPE='"+strExpType+"' " +
				"and a.CLASS_TYPE='"+strPriority+"' " +
				"and a.QUALITY_FLAG='"+strQuality+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		}else if(wheresql.equals("2")){//仓别级别
			strSql="select a.UNTREAD_TYPE from wms_warehouse_riordertype a " +
					"where a.UNTREAD_TYPE='"+strExpType+"' " +
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+warehouseNo+"' " +
					"and a.owner_no='"+ownerNo+"' ";
		}else if(wheresql.equals("3")){//货主级别
			strSql="select a.UNTREAD_TYPE from wms_owner_riordertype a " +
					"where a.UNTREAD_TYPE='"+strExpType+"' " +
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.owner_no='"+ownerNo+"' ";
		}
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(false,"此单据类型已有,请重新选择","");
		}else{
			return new MsgRes(true,"","");
		}
		
	}
	
	//根据出货单别获取波次计划号（三种级别公用）
	@Override
	public List<Wms_OutwaveplanMModel> queryStrategy(
			String enterpriseNo, String strExpType) throws Exception {
		String strSql="select a.enterprise_no,a.exp_type,a.strategy_id," +
				"'['|| ltrim(a.strategy_id)||']'||a.strategy_name dropValue, " +
				"a.strategy_name,a.between_times " +
				"from wms_outwaveplan_m a " +
				"where a.exp_type='"+strExpType+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		List list = genDao.getListByNativeSql(strSql, Wms_OutwaveplanMModel.class, 0, 10);
		return (List<Wms_OutwaveplanMModel>) list;
	}
////////////////////////////////////////仓别级出货策略配置///////////////////////////////////////////////////
	//获取仓别出货策略配置列表
	@Override
	public ExtListDataBo<Wms_RiorderModel> queryWmsWarehouseOutorderList(
			PageBo poPageBo, String enterpriseNo, String warehouseNo)
			throws Exception {
		/*String sql = "select wo.*, loc.warehouse_name,owner.owner_name, "+
	       " f_get_fieldtext('WMS_OUTORDER','DELIVER_OBJ_LEVEL',wo.deliver_obj_level)deliverObjLevelText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','M_FLAG',wo.m_flag)mflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','W_FLAG',wo.w_flag)wflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','S_FLAG',wo.s_flag)sflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','D_FLAG',wo.d_flag)dflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','B_DIVIDE_FLAG',wo.b_divide_flag)bdivideflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','C_DIVIDE_FLAG',wo.c_divide_flag)cdivideflagText,  "+
	       " f_get_fieldtext('N','EXP_TYPE',wo.UNTREAD_TYPE)exptypeText   "+
	       " from wms_warehouse_riordertype wo,bdef_defloc loc,bdef_defowner owner    "+
	       " where wo.enterprise_no='"+enterpriseNo+"'  " +
	       " and wo.warehouse_no='"+warehouseNo+"' "+
	       " and wo.enterprise_no=loc.enterprise_no "+ 
	       " and loc.warehouse_no=wo.warehouse_no "+
	       " and wo.enterprise_no=owner.enterprise_no "+
	       " and wo.owner_no=owner.owner_no  " +
	       " order by wo.warehouse_no,wo.owner_no,wo.UNTREAD_TYPE";*/
		
		
		String sql = " select  loc.warehouse_name,owner.owner_name,wo.WAREHOUSE_NO WAREHOUSENO, wo.OWNER_NO OWNERNO,wo.ENTERPRISE_NO,f_get_fieldtext('N','CLASS_TYPE',CLASS_TYPE) classTypeText,CLASS_TYPE classType,"+
	       " f_get_fieldtext('RIDATA_UNTREAD_M','UNTREAD_TYPE',UNTREAD_TYPE) untreadTypeText,UNTREAD_TYPE untreadType,"+
		   " f_get_fieldtext('RIDATA_UNTREAD_M','QUALITY',QUALITY_FLAG) qualityFlagText,QUALITY_FLAG qualityFlag,"+
	       " f_get_fieldtext('N','YESORNO',OVER_QTY_FLAG )overQtyFlagText, OVER_QTY_FLAG overQtyFlag, "+
	       " f_get_fieldtext('N','YESORNO',auto_instock )autoInstockText,auto_instock autoInstock ,"+
	       " f_get_fieldtext('N','YESORNO',advance_locate ) advanceLocateText,advance_locate advanceLocate ,"+
	       " f_get_fieldtext('N','YESORNO',device_compute ) deviceComputeText,device_compute deviceCompute,"+
	       " f_get_fieldtext('N','YESORNO',mix_ordercheck ) mixOrdercheckText,mix_ordercheck mixOrdercheck ,"+
	       " f_get_fieldtext('N','YESORNO',rsv_label_flag ) rsvLabelFlagText,rsv_label_flag rsvLabelFlag,"+
	       " f_get_fieldtext('N','YESORNO',direct_cell_flag ) directCellFlagText,direct_cell_flag directCellFlag,"+
	       " f_get_fieldtext('N','YESORNO',print_check_flag ) printCheckFlagText,print_check_flag printCheckFlag,"+
	       " f_get_fieldtext('N','YESORNO',auto_check_comfir_flag ) autoCheckComfirFlagText,auto_check_comfir_flag autoCheckComfirFlag"+
	       " from wms_warehouse_riordertype wo"+        
	       "  ,bdef_defloc loc ,bdef_defowner owner    "+
	     " where wo.enterprise_no='"+enterpriseNo+"'  " +
	         " and wo.warehouse_no='"+warehouseNo+"' "+
	         " and wo.enterprise_no=loc.enterprise_no "+ 
	         " and loc.warehouse_no=wo.warehouse_no "+
	         " and wo.enterprise_no=owner.enterprise_no "+
	         " and wo.owner_no=owner.owner_no  " +
	         " order by wo.warehouse_no,wo.owner_no,wo.UNTREAD_TYPE";
		String strTotsql = "select count(1) from wms_warehouse_riordertype where enterprise_no='"+enterpriseNo+"' ";
		List<Wms_RiorderModel> list=genDao.getListByNativeSql(
				sql, Wms_RiorderModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_RiorderModel> extListBo = new ExtListDataBo<Wms_RiorderModel>(list,intCount);
		return extListBo;
	}
	//获取仓别工作流配置（查看时）
	@Override
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsWarehouseOutOrderFlow(
			String enterpriseNo,String warehouseNo,
			String strExpType, String strType,String ownerNo)
			throws Exception {
		String strSql = "";
		if(strType.equals("1")){//新增的时候获取工作流节点
			strSql="select s.status_type,s.status_name,s.flow_flag,s.flow_value," +
					"row_number() over (order by s.flow_value) as flowOrder," +
					"s.flow_name as flowvalueText " +
					"from wms_deflabel_status s where s.flow_flag='2'";	
		}else if(strType.equals("0")){
			strSql=" select case when a.flow_flag =1 then 'true' else 'false' end flag," +
					" a.*, f_get_fieldtext('WMS_OUTORDER_FLOW','FLOW_VALUE',a.flow_value)flowvalueText " +
					"from wms_warehouse_outorder_flow a " +
					"where a.enterprise_no='"+enterpriseNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					"and a.exp_type='"+strExpType+"' " +
					"and a.owner_no='"+ownerNo+"' "  +
					"order by a.flow_order";
			
		}
		List<Wms_OutorderFlowModel> list=genDao.getListByNativeSql(
				strSql, Wms_OutorderFlowModel.class);
		ExtListDataBo<Wms_OutorderFlowModel> extListBo = new ExtListDataBo<Wms_OutorderFlowModel>(list,0);
		return extListBo;
	}
	//保存或新增仓别货主策略
	@Override
	public MsgRes saveOrUpdateWasehouseTactics(String strJsonDetail)
			throws Exception {
		Wms_WarehouseRiordertype bd=(Wms_WarehouseRiordertype)JSONObject.toBean(JSONObject.fromObject(strJsonDetail),Wms_WarehouseRiordertype.class);
		this.genDao.saveOrUpdateObj(bd);
		return new MsgRes(true,"保存成功",null);//保存成功！
	}
	
	//保存仓别配置工作流
	@Override
	public MsgRes saveWasehouseTactics(String strJsonDetail) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Wms_WarehouseOutorderFlow> bb=JSONArray.toCollection(JSONArray.fromObject(strJsonDetail),Wms_WarehouseOutorderFlow.class);
		List<Wms_WarehouseOutorderFlow> bwl=(List)bb;
		this.genDao.saveList(bwl);
		return new MsgRes(true,"保存成功","");
	}
		
	
////////////////////////////////////////货主级出货策略配置///////////////////////////////////////////////////
	//获取仓别货主单据主档
	@Override
	public ExtListDataBo<Wms_OwnerRiorderModel> queryWmsOwnerOutOrderList(
			PageBo poPageBo, String enterpriseNo, String warehouseNo)
			throws Exception {		
		String sql = " select distinct owner.owner_name , wo.OWNER_NO OWNERNO,wo.ENTERPRISE_NO,f_get_fieldtext('N','CLASS_TYPE',CLASS_TYPE) classTypeText,CLASS_TYPE classType,"+
			       " f_get_fieldtext('RIDATA_UNTREAD_M','UNTREAD_TYPE',UNTREAD_TYPE) untreadTypeText,UNTREAD_TYPE untreadType,"+
				   " f_get_fieldtext('RIDATA_UNTREAD_M','QUALITY',QUALITY_FLAG) qualityFlagText,QUALITY_FLAG qualityFlag,"+
			       " f_get_fieldtext('N','YESORNO',OVER_QTY_FLAG )overQtyFlagText, OVER_QTY_FLAG overQtyFlag, "+
			       " f_get_fieldtext('N','YESORNO',auto_instock )autoInstockText,auto_instock autoInstock ,"+
			       " f_get_fieldtext('N','YESORNO',advance_locate ) advanceLocateText,advance_locate advanceLocate ,"+
			       " f_get_fieldtext('N','YESORNO',device_compute ) deviceComputeText,device_compute deviceCompute,"+
			       " f_get_fieldtext('N','YESORNO',mix_ordercheck ) mixOrdercheckText,mix_ordercheck mixOrdercheck ,"+
			       " f_get_fieldtext('N','YESORNO',rsv_label_flag ) rsvLabelFlagText,rsv_label_flag rsvLabelFlag,"+
			       " f_get_fieldtext('N','YESORNO',direct_cell_flag ) directCellFlagText,direct_cell_flag directCellFlag,"+
			       " f_get_fieldtext('N','YESORNO',print_check_flag ) printCheckFlagText,print_check_flag printCheckFlag,"+
			       " f_get_fieldtext('N','YESORNO',auto_check_comfir_flag ) autoCheckComfirFlagText,auto_check_comfir_flag autoCheckComfirFlag"+
			       " from wms_owner_riordertype wo"+        
			       "  ,bdef_defowner owner    "+
			     " where wo.enterprise_no='"+enterpriseNo+"'  " +	         
			         " and wo.enterprise_no=owner.enterprise_no "+
			         " and wo.owner_no=owner.owner_no  " +
			         " order by wo.owner_no,wo.UNTREAD_TYPE";
		String strTotsql = "select count(1) from  ("+sql+") ";
		List<Wms_OwnerRiorderModel> list=genDao.getListByNativeSql(
				sql, Wms_OwnerRiorderModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OwnerRiorderModel> extListBo = new ExtListDataBo<Wms_OwnerRiorderModel>(list,intCount);
		return extListBo;
	}
	
	//获取货主工作流配置（查看时）
	@Override
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsOwnerOutOrderFlow(
			String enterpriseNo, String strExpType, String strType,
			String ownerNo) throws Exception {
		String strSql = "";
		if(strType.equals("1")){//新增的时候获取工作流节点
			strSql="select s.status_type,s.status_name,s.flow_flag,s.flow_value," +
					"row_number() over (order by s.flow_value) as flowOrder," +
					"s.flow_name as flowvalueText " +
					"from wms_deflabel_status s where s.flow_flag='2'";	
		}else if(strType.equals("0")){
			strSql=" select case when a.flow_flag =1 then 'true' else 'false' end flag," +
					" a.*, f_get_fieldtext('WMS_OUTORDER_FLOW','FLOW_VALUE',a.flow_value)flowvalueText " +
					"from WMS_OWNER_OUTORDER_FLOW a " +
					"where a.enterprise_no='"+enterpriseNo+"' "+
					"and a.exp_type='"+strExpType+"' " +
					"and a.owner_no='"+ownerNo+"' "  +
					"order by a.flow_order";
			
		}
		List<Wms_OutorderFlowModel> list=genDao.getListByNativeSql(
				strSql, Wms_OutorderFlowModel.class);
		ExtListDataBo<Wms_OutorderFlowModel> extListBo = new ExtListDataBo<Wms_OutorderFlowModel>(list,0);
		return extListBo;
	}
	
	//保存货主单据策略
	@Override
	public MsgRes saveOrUpdateOwnerTactics(String strJsonDetail)
			throws Exception {
		Wms_OwnerRiordertype bd=(Wms_OwnerRiordertype)JSONObject.toBean(JSONObject.fromObject(strJsonDetail),Wms_OwnerRiordertype.class);
		this.genDao.saveOrUpdateObj(bd);
		return new MsgRes(true,"保存成功",null);//保存成功！
	}
	
	//保存货主配置工作流
	@Override
	public MsgRes saveOwnerTactics(String strJsonDetail) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Wms_OwnerOutorderFlow> bb=JSONArray.toCollection(JSONArray.fromObject(strJsonDetail),Wms_OwnerOutorderFlow.class);
		List<Wms_OwnerOutorderFlow> bwl=(List)bb;
		this.genDao.saveList(bwl);
		return new MsgRes(true,"保存成功","");
	}
	
}
