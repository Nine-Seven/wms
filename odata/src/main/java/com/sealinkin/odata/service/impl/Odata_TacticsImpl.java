package com.sealinkin.odata.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.service.IOdata_TacticsService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.MyDateMorpher;
import com.sealinkin.util.StringUtil;
import com.sealinkin.wms.model.Wms_OutorderFlowModel;
import com.sealinkin.wms.model.Wms_OutorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.model.Wms_OwnerOutorderModel;
import com.sealinkin.wms.model.Wms_strategyDModel;
import com.sealinkin.wms.model.Wms_strategyMModel;
import com.sealinkin.wms.po.Wms_Outorder;
import com.sealinkin.wms.po.Wms_OutorderFlow;
import com.sealinkin.wms.po.Wms_OwnerOutorder;
import com.sealinkin.wms.po.Wms_OwnerOutorderFlow;
import com.sealinkin.wms.po.Wms_WarehouseOutorder;
import com.sealinkin.wms.po.Wms_WarehouseOutorderFlow;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_TacticsImpl implements IOdata_TacticsService{
	
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
	public ExtListDataBo<Wms_OutorderModel> queryWmsOutOrderList(PageBo poPageBo,String enterpriseNo,String exptype ,String industryflag, String strQuery)
			throws Exception {
		String strSql="select wo.*," +
				"f_get_fieldtext('N','EXP_TYPE',wo.EXP_TYPE) exptypeText,"+
				"'['|| ltrim(wo.PRIORITY)||']'||f_get_fieldtext('ODATA_EXP_M','PRIORITY',wo.PRIORITY) priorityText," +
				"'['|| ltrim(wo.INDUSTRY_FLAG)||']'||f_get_fieldtext('WMS_OUTORDER','INDUSTRY_FLAG',wo.INDUSTRY_FLAG) industryflagText," +
				"'['|| ltrim(wo.print_packlist)||']'||f_get_fieldtext('N','PRINT',wo.print_packlist) printPacklistText,"+
				"'['|| ltrim(wo.print_envoice)||']'||f_get_fieldtext('N','PRINT',wo.print_envoice) printEnvoiceText,"+
				"'['|| ltrim(wo.print_waybill)||']'||f_get_fieldtext('N','PRINT',wo.print_waybill) printWaybillText,"+
				"'['|| ltrim(wo.AUTOBATCH_STRATEGY_ID)||']'||a.strategy_name autobatchStrategyIdText" +
				",'['|| ltrim(wo.MANUALBATCH_STRATEGY_ID)||']'||b.strategy_name manualbatchStrategyIdText "+
				",'['|| ltrim(wo.LOCATE_STRATEGY_ID)||']'||c.strategy_name locateStrategyIdText "+
				",'['|| ltrim(wo.COMPUTE_STRATEGY_ID)||']'||c.strategy_name computeStrategyIdText" +
				",'['|| ltrim(wo.WORKFLOW_STRATEGY_ID)||']'||c.strategy_name workflowStrategyIdText "+
				"from WMS_OUTORDER wo  "+
				"left join wms_outwaveplan_m a "+
				"on wo.enterprise_no = a.enterprise_no  "+
				"and wo.AUTOBATCH_STRATEGY_ID = a.batch_strategy_id    "+
				"left join wms_outwaveplan_m  b "+
				"on wo.enterprise_no = b.enterprise_no  "+
				"and wo.MANUALBATCH_STRATEGY_ID = b.batch_strategy_id   "+
				"left join wms_outlocate_strategy_m  c "+
				"on wo.enterprise_no = c.enterprise_no  "+
				"and wo.LOCATE_STRATEGY_ID = c.locate_strategy_id   "+
				"left join wms_compute_strategy_m  d "+
				"on wo.enterprise_no = d.enterprise_no  "+
				"and wo.COMPUTE_STRATEGY_ID = d.compute_strategy_id    "+
				"left join wms_outorder_flow_m  e "+
				"on wo.enterprise_no = e.enterprise_no  "+
				"and wo.WORKFLOW_STRATEGY_ID = e.workflow_strategy_id  "+
				"where wo.enterprise_no='"+enterpriseNo+"' ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}

		String strTotsql = "select count(1) from WMS_OUTORDER where enterprise_no='"+enterpriseNo+"' ";
		List<Wms_OutorderModel> list=genDao.getListByNativeSql(
				strSql, Wms_OutorderModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OutorderModel> extListBo = new ExtListDataBo<Wms_OutorderModel>(list,intCount);
		return extListBo;
	}
	
	/**
	 * 保存或修改策略明细
	 */
	@Override
	public MsgRes saveOrUpdateTactics(String strJsonDetail)
			throws Exception {
		Wms_Outorder bd=(Wms_Outorder)JSONObject.toBean(JSONObject.fromObject(strJsonDetail),Wms_Outorder.class);
		this.genDao.saveOrUpdateObj(bd);
		System.out.println(bd.getId().getExpType());
		/*this.deleteTactics(bd.getExpType());
		this.genDao.flush();
		String strSql="insert into wms_outorder_flow(exp_type, flow_order, flow_value, flow_flag, rgst_name, rgst_date) " +
				"values('"+bd.getExpType()+"','1','10','1','admin',sysdate)";
		genDao.updateBySql(strSql);
		strSql="insert into wms_outorder_flow(exp_type, flow_order, flow_value, flow_flag, rgst_name, rgst_date) " +
				"values('"+bd.getExpType()+"','2','20','1','admin',sysdate)";
		genDao.updateBySql(strSql);
		strSql="insert into wms_outorder_flow(exp_type, flow_order, flow_value, flow_flag, rgst_name, rgst_date) " +
				"values('"+bd.getExpType()+"','3','30','1','admin',sysdate)";
		genDao.updateBySql(strSql);
		strSql="insert into wms_outorder_flow(exp_type, flow_order, flow_value, flow_flag, rgst_name, rgst_date) " +
				"values('"+bd.getExpType()+"','4','40','1','admin',sysdate)";
		genDao.updateBySql(strSql);
		strSql="insert into wms_outorder_flow(exp_type, flow_order, flow_value, flow_flag, rgst_name, rgst_date) " +
				"values('"+bd.getExpType()+"','5','50','1','admin',sysdate)";
		genDao.updateBySql(strSql);
		strSql="insert into wms_outorder_flow(exp_type, flow_order, flow_value, flow_flag, rgst_name, rgst_date) " +
				"values('"+bd.getExpType()+"','6','60','1','admin',sysdate)";
		genDao.updateBySql(strSql);*/
		return new MsgRes(true,"保存成功",null);//保存成功！
	}
	/**
	 * 保存或修改策略明细
	 */
	public List<ComboxBo> getExptypeForUI(String strQuery)
			throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_outorder "+
				"union "+
				"select distinct a.EXP_TYPE value,a.EXP_TYPE text," +	
				"'['|| ltrim(a.EXP_TYPE)||']'||f_get_fieldtext('N','EXP_TYPE',a.EXP_TYPE) dropValue " +
				"from wms_outorder a " +
				"where 1=1 " ;
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
    }
	/**
	 * 行业标识
	 */
	public List<ComboxBo> getIndustryFlagForUI(String strQuery)
			throws Exception {
		String strSql=
				"select * from (select distinct a.INDUSTRY_FLAG value,a.INDUSTRY_FLAG text," +	
				"'['|| ltrim(a.INDUSTRY_FLAG)||']'||f_get_fieldtext('wms_outorder','INDUSTRY_FLAG',a.INDUSTRY_FLAG) dropValue " +
				"from wms_outorder a " +
				"where 1=1 " +
		"union "+
		"select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_outorder ) a order by value desc";
		 
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
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
	
	//删除出货策略配置
	@Override
	public MsgRes deleteTactics(String strExpType,String enterpriseNo) throws Exception {
		String delsql="delete wms_outorder a " +
				"where a.exp_type='"+strExpType+"' and a.enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(delsql);
		return new MsgRes(true,"","");
	}
	
	//校验单据（三种级别公用）
	@Override
	public MsgRes checkExpType(String enterpriseNo,String warehouseNo,
			String strExpType,String wheresql,String ownerNo)
			throws Exception {
		String strSql = null;
		if(wheresql.equals("1")){//系统级别
			strSql="select a.exp_type from wms_outorder a " +
				"where a.exp_type='"+strExpType+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		}else if(wheresql.equals("2")){//仓别级别
			strSql="select a.exp_type from wms_warehouse_outorder a " +
					"where a.exp_type='"+strExpType+"' " +
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+warehouseNo+"' " +
					"and a.owner_no='"+ownerNo+"' ";
		}else if(wheresql.equals("3")){//货主级别
			strSql="select a.exp_type from wms_owner_outorder a " +
					"where a.exp_type='"+strExpType+"' " +
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
	//策略类型信息列表
	@Override
	public ExtListDataBo<Wms_OutorderModel> getStrategyTypeList(String strQuery,PageBo poPageBo)
			throws Exception {
		String strSql="select a.value as strategyFlag, a.text as strategyName, '' as strategyId "+
                      "from wms_deffieldval a "+
                      "where a.table_name = 'N' "+
                        "and a.colname = 'STRATEGY_FLAG' "+
                        "and a.status = '1' ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		strSql += " order by a.value " ;
		String strTotsql = "select count(*) from ("+strSql+" ) ";

		List<Wms_OutorderModel> list=genDao.getListByNativeSql(
				strSql, Wms_OutorderModel.class/*, poPageBo.getStart(), poPageBo.getPagesize()*/);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OutorderModel> extListBo = new ExtListDataBo<Wms_OutorderModel>(list,intCount);
		return extListBo;
	}
	//获取策略ID下拉
	@Override
	public List<ComboxBo> getStrategyidCom(String strEnterpriseNo,String strategyFlag, String strQuery)
			throws Exception {
		String strSql="";
		if(strategyFlag!=null && !strategyFlag.equals(""))
		{
			 if(strategyFlag.equals("01")||strategyFlag.equals("02")){//自动批次规则策略/手工批次规则策略
				 strSql="select a.batch_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.batch_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_outwaveplan_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("03")){//分配策略
				 strSql="select a.locate_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.locate_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_outlocate_strategy_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("04")){//试算策略
				 strSql="select a.compute_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.compute_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_compute_strategy_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("05")){//工作流策略
				 strSql="select a.workflow_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.workflow_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_outorder_flow_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("14")||strategyFlag.equals("12")||strategyFlag.equals("13")){
				 //打印内置清单,打印发票,打印快递面单
				 strSql=" select value,text,'['|| ltrim(a.value)||']'||a.text dropValue   from wms_deffieldval a where a.table_name = 'N' AND colname='PRINT'";
						 
			 }else if(strategyFlag.equals("15")){//行业标识
				 strSql=" select value,text,'['|| ltrim(a.value)||']'||a.text dropValue   from wms_deffieldval a where a.table_name = 'WMS_OUTORDER' AND colname='INDUSTRY_FLAG'";
			 }/*else if(strategyFlag.equals("06")){//拣货策略
				 strSql="select a.batch_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.batch_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_outwaveplan_m a where 1=1  
							and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }*/
		}
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//策略头档信息列表
	@Override
	public ExtListDataBo<Wms_strategyMModel> getStrategyMList(
			String strEnterpriseNo,String strategyFlag, String strQuery, PageBo poPageBo)
			throws Exception {
		String strSql="";
		if(strategyFlag!=null && !strategyFlag.equals(""))
		{
			if(strategyFlag.equals("01")||strategyFlag.equals("02")){//自动批次规则策略/手工批次规则策略
				 strSql="select a.enterprise_no,a.batch_strategy_id as strategyId,a.strategy_name,a.defalut_flag, " +	
							"'['|| ltrim(a.defalut_flag)||']'||f_get_fieldtext('N','DEFAULT_FLAG',a.defalut_flag)defalutFlagText," +
							"a.rgst_name,a.rgst_date " +
							"from wms_outwaveplan_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("03")){//分配策略
				 strSql="select a.enterprise_no,a.locate_strategy_id as strategyId,a.strategy_name,a.defalut_flag, " +	
							"'['|| ltrim(a.defalut_flag)||']'||f_get_fieldtext('N','DEFAULT_FLAG',a.defalut_flag)defalutFlagText," +
							"a.rgst_name,a.rgst_date " +
							"from wms_outlocate_strategy_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("04")){//试算策略
				 strSql="select a.enterprise_no,a.compute_strategy_id as strategyId,a.strategy_name,a.defalut_flag, " +	
							"'['|| ltrim(a.defalut_flag)||']'||f_get_fieldtext('N','DEFAULT_FLAG',a.defalut_flag)defalutFlagText," +
							"a.rgst_name,a.rgst_date " +
							"from wms_compute_strategy_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }else if(strategyFlag.equals("05")){//工作流策略
				 strSql="select a.enterprise_no,a.workflow_strategy_id as strategyId,a.strategy_name,a.defalut_flag," +	
							"'['|| ltrim(a.defalut_flag)||']'||f_get_fieldtext('N','DEFAULT_FLAG',a.defalut_flag)defalutFlagText," +
							"a.rgst_name,a.rgst_date " +
							"from wms_outorder_flow_m a where 1=1  " +
							"and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }/*else if(strategyFlag.equals("06")){//拣货策略
				 strSql="select a.enterprise_no,a.batch_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.batch_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_outwaveplan_m a where 1=1  
							and a.enterprise_no ='"+strEnterpriseNo+"' ";
			 }*/
		}
		strSql = "select a.* from ("+strSql+" ) a where 1=1 " +
				 "and a.enterprise_no ='"+strEnterpriseNo+"' ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		String strTotsql = "select count(*) from ("+strSql+" ) ";
				
		List<Wms_strategyMModel> list=genDao.getListByNativeSql(
				strSql, Wms_strategyMModel.class);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_strategyMModel> extListBo = new ExtListDataBo<Wms_strategyMModel>(list,intCount);
		return extListBo;
	}
	//策略明细信息列表
	@Override
	public ExtListDataBo<Wms_strategyDModel> getStrategyDList(
			String strEnterpriseNo,String strategyFlag, String strQuery, PageBo poPageBo)
			throws Exception {
		String strSql="";
		if(strategyFlag!=null && !strategyFlag.equals(""))
		{
			if(strategyFlag.equals("01")||strategyFlag.equals("02")){//自动批次规则策略/手工批次规则策略
				 strSql="select a.*,a.batch_strategy_id as strategyId,batch_rule_id as ruleId, batch_rule_name as ruleName,"+
						"'['|| ltrim(a.status)||']'||f_get_fieldtext('N','CAN_USE',a.status)statusText, "+
						"'['|| ltrim(a.batch_compute_type)||']'||f_get_fieldtext('WMS_OUTWAVEPLAN_D','BATCH_COMPUTE_TYPE',a.batch_compute_type)batchComputeTypeText, "+
						"'['|| ltrim(a.batch_compute)||']'||f_get_fieldtext('WMS_OUTWAVEPLAN_D','BATCH_COMPUTE',a.batch_compute)batchComputeText, "+
						"'['|| ltrim(a.order_source)||']'||f_get_fieldtext('WMS_OUTWAVEPLAN_D','ORDER_SOURCE',a.order_source)orderSourceText, "+
						"'['|| ltrim(a.deliver_type)||']'||f_get_fieldtext('WMS_OUTWAVEPLAN_D','DELIVER_TYPE',a.deliver_type)deliverTypeText, "+
						"'['|| ltrim(a.line_flag)||']'||f_get_fieldtext('WMS_OUTORDER','LINE_FLAG',a.line_flag)lineFlagText, "+
						"'['|| ltrim(a.shipper_no)||']'||b.shipper_name shipperNoText, "+
						"'['|| ltrim(a.c_limmit)||']'||f_get_fieldtext('N','LIMIT',a.c_limmit)CLimmitText, "+
						"'['|| ltrim(a.area_limmit)||']'||f_get_fieldtext('N','ALLOW',a.area_limmit)areaLimmitText, "+
						"'['|| ltrim(a.item_type_flag)||']'||f_get_fieldtext('N','INCLUDE',a.item_type_flag)itemTypeFlagText, "+
						//"'['|| ltrim(a.print_type)||']'||f_get_fieldtext('BDEF_DEFSHIPPER','PRINT_TYPE',a.print_type)printTypeText, "+
						"'['|| ltrim(a.print_envoice)||']'||f_get_fieldtext('N','PRINT',a.print_envoice)printEnvoiceText, "+
						"'['|| ltrim(a.print_waybill)||']'||f_get_fieldtext('N','PRINT',a.print_envoice)printWaybillText, "+
						"'['|| ltrim(a.print_packlist)||']'||f_get_fieldtext('N','PRINT',a.print_envoice)printPacklistText "+
						"from wms_outwaveplan_d a,bdef_defshipper b where a.enterprise_no=b.enterprise_no(+) and a.shipper_no=b.shipper_no(+) ";
			 }else if(strategyFlag.equals("03")){//分配策略
				 strSql="select a.*,a.locate_strategy_id as strategyId,locate_rule_id as ruleId,locate_rule_name ruleName, "+
						"'['|| ltrim(a.deliver_obj_level)||']'||f_get_fieldtext('WMS_OUTORDER','DELIVER_OBJ_LEVEL',a.deliver_obj_level)deliverObjLevelText, "+
						"'['|| ltrim(a.p_flag)||']'||f_get_fieldtext('N','ALLOW',a.p_flag)PFlagText, "+
						"'['|| ltrim(a.m_flag)||']'||f_get_fieldtext('N','ALLOW',a.m_flag)MFlagText, "+
						"'['|| ltrim(a.w_flag)||']'||f_get_fieldtext('N','ALLOW',a.w_flag)WFlagText, "+
						"'['|| ltrim(a.s_flag)||']'||f_get_fieldtext('N','ALLOW',a.s_flag)SFlagText, "+
						"'['|| ltrim(a.d_flag)||']'||f_get_fieldtext('N','ALLOW',a.d_flag)DFlagText, "+
						"'['|| ltrim(a.b_divide_flag)||']'||f_get_fieldtext('WMS_OUTORDER','B_DIVIDE_FLAG',a.b_divide_flag)BDivideFlagText, "+
						"'['|| ltrim(a.c_divide_flag)||']'||f_get_fieldtext('WMS_OUTORDER','C_DIVIDE_FLAG',a.c_divide_flag)CDivideFlagText, "+
						"'['|| ltrim(a.commit_type)||']'||f_get_fieldtext('WMS_OUTORDER','COMMIT_TYPE',a.commit_type)commitTypeText, "+
						"'['|| ltrim(a.shortqty_type)||']'||f_get_fieldtext('WMS_OUTORDER','SHORTQTY_TYPE',a.shortqty_type)shortqtyTypeText "+
						"from wms_outlocate_strategy_d a where 1=1 ";
			 }else if(strategyFlag.equals("04")){//试算策略
				 strSql="select a.enterprise_no,a.compute_strategy_id  as ruleId,a.locate_rule_name ruleName, " +
			             " '['|| ltrim(a.CHECK_COMPUTE_FLAG)||']'||f_get_fieldtext('COMPUTE_STRATEGY_ID','CHECK_COMPUTE_FLAG',a.CHECK_COMPUTE_FLAG )  checkcomputeflagText,    " +
			            " '['|| ltrim(a.CHECK_COMPUTE_LEVEL)||']'||f_get_fieldtext('COMPUTE_STRATEGY_ID','CHECK_COMPUTE_LEVEL',a.CHECK_COMPUTE_LEVEL )  checkcomputelevelText,   " + 
			              "'['|| ltrim(a.CHECK_COMPUTE_VALUE)||']'||f_get_fieldtext('COMPUTE_STRATEGY_ID','CHECK_COMPUTE_VALUE',a.CHECK_COMPUTE_VALUE )  checkcomputevalueText,  " +
			             " '['|| ltrim(a.SENDBUF_COMPUTE_FLAG)||']'||f_get_fieldtext('COMPUTE_STRATEGY_ID','SENDBUF_COMPUTE_FLAG',a.SENDBUF_COMPUTE_FLAG )  sendbufcomputeflagText,  " +
			            "'['|| ltrim(a.SENDBUF_COMPUTE_LEVEL)||']'||f_get_fieldtext('COMPUTE_STRATEGY_ID','SENDBUF_COMPUTE_LEVEL',a.SENDBUF_COMPUTE_LEVEL )  sendbufcomputelevelText,  " +
			            "'['|| ltrim(a.SENDBUF_COMPUTE_VALUE)||']'||f_get_fieldtext('COMPUTE_STRATEGY_ID','SENDBUF_COMPUTE_VALUE',a.SENDBUF_COMPUTE_VALUE )  sendbufcomputevalueText,  " +
			              " a.rgst_name,a.rgst_date  " +
			               "from wms_compute_strategy_d a where 1=1  ";
			 }else if(strategyFlag.equals("05")){//工作流策略
				 strSql="select a.workflow_strategy_id as strategyId,flow_order ,"+
							"'['|| ltrim(a.FLOW_VALUE)||']'||f_get_fieldtext('WMS_OUTORDER_FLOW','FLOW_VALUE',a.FLOW_VALUE)flowvalueText,"+
							"'['|| ltrim(a.FLOW_FLAG)||']'||f_get_fieldtext('WMS_OUTORDER_FLOW','FLOW_FLAG',a.FLOW_FLAG)flowflagText,"+
							"a.rgst_name,a.rgst_date "+
							"from wms_outorder_flow_d a where 1=1  ";
			 }/*else if(strategyFlag.equals("06")){//拣货策略
				 strSql="select a.batch_strategy_id value,a.strategy_name text," +	
							"'['|| ltrim(a.batch_strategy_id)||']'||a.strategy_name dropValue " +
							"from wms_outwaveplan_d a where 1=1  ";
			 }*/
		}
		strSql = "select a.* from ("+strSql+" ) a where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		if(strategyFlag.equals("01")||strategyFlag.equals("02")){
			strSql += " order by a.seq_order ";
		}
		String strTotsql = "select count(*) from ("+strSql+" ) ";
				
		List<Wms_strategyDModel> list=genDao.getListByNativeSql(
				strSql, Wms_strategyDModel.class);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_strategyDModel> extListBo = new ExtListDataBo<Wms_strategyDModel>(list,intCount);
		return extListBo;
	}
////////////////////////////////////////仓别级出货策略配置///////////////////////////////////////////////////
	//获取仓别出货策略配置列表
	@Override
	public ExtListDataBo<Wms_OutorderModel> queryWmsWarehouseOutorderList(
			PageBo poPageBo, String enterpriseNo, String warehouseNo)
			throws Exception {
		String sql = "select wo.*, loc.warehouse_name,owner.owner_name, "+
	       " f_get_fieldtext('WMS_OUTORDER','DELIVER_OBJ_LEVEL',wo.deliver_obj_level)deliverObjLevelText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','M_FLAG',wo.m_flag)mflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','W_FLAG',wo.w_flag)wflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','S_FLAG',wo.s_flag)sflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','D_FLAG',wo.d_flag)dflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','B_DIVIDE_FLAG',wo.b_divide_flag)bdivideflagText,  "+
	       " f_get_fieldtext('WMS_OUTORDER','C_DIVIDE_FLAG',wo.c_divide_flag)cdivideflagText,  "+
	       " f_get_fieldtext('N','EXP_TYPE',wo.exp_type)exptypeText   "+
	       " from wms_warehouse_outorder wo,bdef_defloc loc,bdef_defowner owner    "+
	       " where wo.enterprise_no='"+enterpriseNo+"'  " +
	       " and wo.warehouse_no='"+warehouseNo+"' "+
	       " and wo.enterprise_no=loc.enterprise_no "+ 
	       " and loc.warehouse_no=wo.warehouse_no "+
	       " and wo.enterprise_no=owner.enterprise_no "+
	       " and wo.owner_no=owner.owner_no  " +
	       " order by wo.warehouse_no,wo.owner_no,wo.exp_type";
		String strTotsql = "select count(1) from wms_warehouse_outorder where enterprise_no='"+enterpriseNo+"' ";
		List<Wms_OutorderModel> list=genDao.getListByNativeSql(
				sql, Wms_OutorderModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OutorderModel> extListBo = new ExtListDataBo<Wms_OutorderModel>(list,intCount);
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
		Wms_WarehouseOutorder bd=(Wms_WarehouseOutorder)JSONObject.toBean(JSONObject.fromObject(strJsonDetail),Wms_WarehouseOutorder.class);
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
	public ExtListDataBo<Wms_OwnerOutorderModel> queryWmsOwnerOutOrderList(
			PageBo poPageBo, String enterpriseNo, String warehouseNo)
			throws Exception {
		String sql = "select wo.*, owner.owner_name,"+
       " f_get_fieldtext('WMS_OUTORDER','DELIVER_OBJ_LEVEL',wo.deliver_obj_level)deliverObjLevelText, "+
       " f_get_fieldtext('WMS_OUTORDER','M_FLAG',wo.m_flag)mflagText, "+
       " f_get_fieldtext('WMS_OUTORDER','W_FLAG',wo.w_flag)wflagText, "+
       " f_get_fieldtext('WMS_OUTORDER','S_FLAG',wo.s_flag)sflagText, "+
       " f_get_fieldtext('WMS_OUTORDER','D_FLAG',wo.d_flag)dflagText, "+
       " f_get_fieldtext('WMS_OUTORDER','B_DIVIDE_FLAG',wo.b_divide_flag)bdivideflagText,"+ 
       " f_get_fieldtext('WMS_OUTORDER','C_DIVIDE_FLAG',wo.c_divide_flag)cdivideflagText, "+
       " f_get_fieldtext('N','EXP_TYPE',wo.exp_type)exptypeText  "+
       " from wms_owner_outorder wo,bdef_defowner owner   "+
       " where wo.enterprise_no='"+enterpriseNo+"' "+
       " and wo.enterprise_no=owner.enterprise_no "+
       " and wo.owner_no=owner.owner_no " +
       " order by wo.enterprise_no,wo.owner_no,wo.exp_type ";
		String strTotsql = "select count(1) from wms_warehouse_outorder where enterprise_no='"+enterpriseNo+"' ";
		List<Wms_OwnerOutorderModel> list=genDao.getListByNativeSql(
				sql, Wms_OutorderModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OwnerOutorderModel> extListBo = new ExtListDataBo<Wms_OwnerOutorderModel>(list,intCount);
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
		Wms_OwnerOutorder bd=(Wms_OwnerOutorder)JSONObject.toBean(JSONObject.fromObject(strJsonDetail),Wms_OwnerOutorder.class);
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
