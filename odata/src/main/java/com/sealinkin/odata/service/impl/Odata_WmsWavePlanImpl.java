package com.sealinkin.odata.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.service.IOdata_WmsWavePlanService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_OutorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanDModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.po.Wms_Outorder;
import com.sealinkin.wms.po.Wms_OutwaveplanD;
import com.sealinkin.wms.po.Wms_OutwaveplanM;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_WmsWavePlanImpl implements IOdata_WmsWavePlanService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//获取头档
	@Override
	public ExtListDataBo<Wms_OutwaveplanMModel> getWmsWarePlanM(
			String currEnterpriseNo, PageBo pageBo,String str) throws Exception {

		String sql="select a.BATCH_STRATEGY_ID batchStrategyId,a.STRATEGY_NAME strategyName,a.RGST_NAME rgstName, to_char(a.RGST_DATE,'yyyy-mm-dd') rgstDateText " +
				   //"f_get_fieldtext('N','EXP_TYPE',a.exp_type) expTypeText "+
				   " from Wms_outWavePlan_m a where 1=1 "+
				   " and a.enterprise_no ='"+currEnterpriseNo+"' ";
				
		String strTotsql = "select count(1) from Wms_outWavePlan_m a where 1=1 "+
				" and a.enterprise_no ='"+currEnterpriseNo+"' ";
						
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		sql=sql+" order by a.batch_strategy_id ";
		List<Wms_OutwaveplanMModel> list = genDao.getListByNativeSql(sql,Wms_OutwaveplanMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OutwaveplanMModel> extListBo = new ExtListDataBo<Wms_OutwaveplanMModel>(list,intCount);
		return extListBo;	
	}
	
	//获取订单类型
	@Override
	public List<ComboxBo> queryExpTypeCombo(String currEnterpriseNo)
			throws Exception {
		String strSql="select t1.value value,t1.text text," +
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 " +
				"where t1.table_name='N' and t1.colName='EXP_TYPE' " +
				"and t1.value in(select distinct a.exp_type from wms_outorder a" +
				" where  a.enterprise_no='"+currEnterpriseNo+"') " ;
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	
	@Override
	public List<ComboxBo> getStrategy(String currEnterpriseNo,
			 String str) throws Exception {
		
		String strSql="select t1.value value,t1.text text," +
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 " +
				"where t1.table_name='WMS_OUTWAVEPLAN_D' and t1.colName='STRATEGYTYPE' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+"and t1.value not in(select a.strategy_type from Wms_outWavePlan_d a " +
					"where  a.enterprise_no='"+currEnterpriseNo+"' " +ws+" )";
		}	
		
		System.out.println(strSql);
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	//保存头档
	@Override
	public MsgRes saveOrUpdatePlanM(String str) throws Exception {
		Wms_OutwaveplanM opm = (Wms_OutwaveplanM) JSON.parseObject(str,Wms_OutwaveplanM.class);
		int id=1;
		
		if(opm.getId().getBatchStrategyId()==-1){
			String strSql="select a.BATCH_STRATEGY_ID from wms_outwaveplan_m a " +
					      "where a.enterprise_no='"+opm.getId().getEnterpriseNo()+"' " +
					      "order by a.BATCH_STRATEGY_ID desc ";
			
			List list = genDao.getListByNativeSql(strSql);	
			if(list.size()>0){
				id= Integer.parseInt(list.get(0).toString())+1;;
			}	
			opm.getId().setBatchStrategyId(id);
		}else{
			id=opm.getId().getBatchStrategyId();
		}
		this.genDao.saveOrUpdateObj(opm);
	    
		return new MsgRes(Boolean.valueOf(true),id+"", null);
	
	}
	//删除头档
	@Override
	public MsgRes deletePlanM(String enterpriseNo, String batchStrategyId) throws Exception {
		
		String deletePlanM="delete from wms_outwaveplan_m a " +
				           "where a.enterprise_no='"+enterpriseNo+"' " +
				           "and a.batch_strategy_id="+batchStrategyId ;
		genDao.updateBySql(deletePlanM);
		
		String deletePlanD="delete from wms_outwaveplan_d a " +
						   "where a.enterprise_no='"+enterpriseNo+"' " +
		                   "and a.batch_strategy_id="+batchStrategyId ;
		genDao.updateBySql(deletePlanD);
		
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
			
	//获取明细
	@Override
	public ExtListDataBo<Wms_OutwaveplanDModel> getWmsWavePlanD(
			String currEnterpriseNo, PageBo pageBo, String str)
			throws Exception {
		String sql="select a.*," +
					"a.BATCH_RULE_NAME batchRuleName,a.BATCH_RULE_ID batchRuleId,a.seq_order seqOrder "+
				   //"f_get_fieldtext('WMS_OUTWAVEPLAN_D','STRATEGYTYPE',a.strategy_type) strategyName "+
				   " from Wms_outWavePlan_d a where 1=1 "+
				   " and a.enterprise_no ='"+currEnterpriseNo+"' ";
				
		String strTotsql = "select count(1) from Wms_outWavePlan_d a where 1=1 "+
				" and a.enterprise_no ='"+currEnterpriseNo+"' ";
					
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		sql=sql+" order by a.seq_order ";
		List<Wms_OutwaveplanDModel> list = genDao.getListByNativeSql(sql,Wms_OutwaveplanDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OutwaveplanDModel> extListBo = new ExtListDataBo<Wms_OutwaveplanDModel>(list,intCount);
		return extListBo;
	}
	//保存明细
	@Override
	public MsgRes saveOrUpdatePlanD(String str) throws Exception {
		Wms_OutwaveplanD opd = (Wms_OutwaveplanD) JSON.parseObject(str,Wms_OutwaveplanD.class);
		
		int id=1;
		if(opd.getSeqOrder()==-1){
			String strSql="select a.seq_order from wms_outwaveplan_d a " +
					      "where a.enterprise_no='"+opd.getId().getEnterpriseNo()+"' " +
					     // "and a.exp_type='"+opd.getExpType()+"' " +
					      "and a.BATCH_STRATEGY_ID='" +opd.getId().getBatchStrategyId()+"' "+
					      "and a.BATCH_RULE_ID='" +opd.getId().getBatchRuleId()+"' "+
					      "order by a.seq_order desc ";
			List list = genDao.getListByNativeSql(strSql);	
			if(list.size()>0){
				id= Integer.parseInt(list.get(0).toString())+1;
			}	
				opd.setSeqOrder(id);	
			}
			this.genDao.saveOrUpdateObj(opd);    
		return new MsgRes(Boolean.valueOf(true),"", null);
	}
	@Override
	public MsgRes deletePlanD(String enterpriseNo, String strategyId,
			 String strategyType,String seqOrder) throws Exception {
		
		String deletePlanD="delete from wms_outwaveplan_d a " +
						   " where a.enterprise_no='"+enterpriseNo+"' " +
		                  // " and a.exp_type='"+expType+"' " +
		                   " and a.BATCH_STRATEGY_ID='"+strategyId+"' "+
		                   " and a.BATCH_RULE_ID='"+strategyType+"' ";
		                   //" and a.strategy_type='"+strategyType+"' ";
		genDao.updateBySql(deletePlanD);	
		
		//更新明细波次计划生成顺序
		System.out.println(seqOrder);
		
		String updatePlanD="update wms_outwaveplan_d a " +
				           " set a.seq_order = a.seq_order-1 " +
				           " where a.seq_order>"+Integer.parseInt(seqOrder) +
				           " and a.enterprise_no='"+enterpriseNo+"' " +
				           " and a.BATCH_STRATEGY_ID='"+strategyId+"' ";
		                   
		genDao.updateBySql(updatePlanD);
		
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
	@Override
	public MsgRes seqencing(String enterpriseNo, String strategyId,
			String strategyType, String seqOrder, String flag)
			throws Exception {
		if(flag.equals("1")){                     //选中数据上移
			if(!seqOrder.equals("1")){            //如果是第一条，则不处理
				//将选中的数据的上一条数据，顺序号+1，即向下移
				String updatePlanDtop="update wms_outwaveplan_d a " +
						              "set a.seq_order = a.seq_order+1 " +
						               " where a.enterprise_no='"+enterpriseNo+"' " +
					                   //" and a.exp_type='"+expType+"' " +
					                   //" and a.BATCH_RULE_ID = '"+strategyType + "'" +
					                   " and a.BATCH_STRATEGY_ID="+strategyId+
					                   " and a.seq_order="+(Integer.parseInt(seqOrder)-1);
			   genDao.updateBySql(updatePlanDtop);
			   
			 //将选中的数顺序号-1，即向上移
			 String updatePlanDown="update wms_outwaveplan_d a " +
						              "set a.seq_order = a.seq_order-1 " +
						               " where a.enterprise_no='"+enterpriseNo+"' " +
					                   //" and a.exp_type='"+expType+"' " +
					                   " and a.BATCH_RULE_ID = '"+strategyType + "'" +
					                   " and a.BATCH_STRATEGY_ID="+strategyId+
					                   " and a.seq_order="+Integer.parseInt(seqOrder);
					                   //" and a.strategy_type='"+strategyType+"' ";;
			   genDao.updateBySql(updatePlanDown);
			}
		}else{                                                          //选中数据下移		
		   String sql = "select count(1) from Wms_outWavePlan_d a "+
				   " where a.enterprise_no='"+enterpriseNo+"' " +
                   //" and a.exp_type='"+expType+"' " +
                   " and a.BATCH_STRATEGY_ID="+strategyId;
		   List list = genDao.getListByNativeSql(sql);
		   
		   if(!list.get(0).toString().equals(seqOrder)){  //如果是最后一条数据，则不处理
			 //将选中的数据的下一条数据，顺序号-1，即向上移
				String updatePlanDdown="update wms_outwaveplan_d a " +
						              "set a.seq_order = a.seq_order-1 " +
						               " where a.enterprise_no='"+enterpriseNo+"' " +
					                   //" and a.exp_type='"+expType+"' " +
					                   //" and a.BATCH_RULE_ID = '" + strategyType + "'" +
					                   " and a.BATCH_STRATEGY_ID="+strategyId+
					                   " and a.seq_order="+(Integer.parseInt(seqOrder)+1);
			   genDao.updateBySql(updatePlanDdown);
			   
			 //将选中的数顺序号+1，即向下移
			 String updatePlanDtop="update wms_outwaveplan_d a " +
						              "set a.seq_order = a.seq_order+1 " +
						               " where a.enterprise_no='"+enterpriseNo+"' " +
					                   //" and a.exp_type='"+expType+"' " +
						               " and a.BATCH_RULE_ID = '"+strategyType + "'" +
					                   " and a.BATCH_STRATEGY_ID="+strategyId+
					                   " and a.seq_order="+Integer.parseInt(seqOrder);
					                   //" and a.strategy_type='"+strategyType+"' ";;
			   genDao.updateBySql(updatePlanDtop);
			   
		   }
		}	
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
	
	//判断是否分播
	@Override
	public MsgRes ifDivede(String enterpriseNo,String expType) throws Exception {
		String sql ="select a.b_divide_flag,a.c_divide_flag from wms_outorder a " +
				    "where a.enterprise_no='"+enterpriseNo+"' " +
				    "and a.exp_type='"+expType+"' ";
		
		List<Wms_OutorderModel> list = genDao.getListByNativeSql(sql,Wms_OutorderModel.class);
		
		 String result=list.get(0).getbDivideFlag()+","+list.get(0).getcDivideFlag();
				
		 return new MsgRes(Boolean.valueOf(true),result, "");
	}
	
	//获得试算配置列表  6-22
	@Override
	public List<Wms_OutwaveplanDModel> getWmsWavePlanDTrial() throws Exception {
		String sql="select a.value as value,a.text text, ' ' as trialRuleId " +
			   " from wms_deffieldval a where 1=1 "+
			   " and a.table_name = 'N' " +
			   " and a.colname = 'TRIAL_SET' " +
			   " and a.status = '1' ";
			
	/*String strTotsql = "select count(1) from wms_deffieldval a where 1=1 "+
				" and a.table_name = 'N' " +
			    " and a.colname = 'TRIAL_SET' " +
			    " and a.status = '1' ";*/
				
		
	//List<Wms_OutwaveplanDModel> list = genDao.getListByNativeSql(sql,Wms_OutwaveplanDModel.class,pageBo.getStart(), pageBo.getPagesize());
	List<Wms_OutwaveplanDModel> list = genDao.getListByNativeSql(sql,Wms_OutwaveplanDModel.class);
	//Integer intCount = genDao.getCountByNativeSql(strTotsql);
	//ExtListDataBo<Wms_OutwaveplanDModel> extListBo = new ExtListDataBo<Wms_OutwaveplanDModel>(list,intCount);
	return list;
}
	
	//获得试算配置列表对应下拉框  6-22
	@Override
	public List<ComboxBo> getWmsWavePlanDTrialSelect(String enterpriseNo,String flag) {
		String strSql = null;
		if(flag != null && !flag.equals("")){
			if(flag.equals("1") || flag.equals("2")){
				strSql=" select t.rule_id value , t.memo text," +
						 "'['|| ltrim(t.rule_id)||']'||t.memo dropValue" +
						 " from wms_logibox_rule t where 1=1  " +
						 " and t.enterprise_no='"+enterpriseNo+"' ";
			}else if(flag.equals("3")){
				strSql=" select t.task_ruleid value , t.memo text," +
						 "'['|| ltrim(t.task_ruleid)||']'||t.memo dropValue" +
						 " from wms_taskallot_rule_m t where 1=1  " +
						 " and t.enterprise_no='"+enterpriseNo+"' ";
			}else if(flag.equals("4")){
				strSql=" select t.pick_strategy_id value , t.pick_strategy_name text," +
						 "'['|| ltrim(t.pick_strategy_id)||']'||t.pick_strategy_name dropValue" +
						 " from wms_outpick_strategy t where 1=1  " +
						 " and t.enterprise_no='"+enterpriseNo+"' ";
			}
		}else{
			strSql=" select t.rule_id value , t.memo text," +
					 "'['|| ltrim(t.rule_id)||']'||t.memo dropValue" +
					 " from wms_logibox_rule t where 1=1  " +
					 " and t.enterprise_no='"+enterpriseNo+"' ";
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//获得试算配置规则id对应记录的详细信息 6-23
	@Override
	public ExtListDataBo<Wms_OutwaveplanDModel> getWmsWavePlanDTrialDetail(
			String currEnterpriseNo, PageBo pageBo, String flagSet,String columnId)
			throws Exception {
		String sql = null;
		String strTotsql = null;
		if(flagSet.equals("1") || flagSet.equals("2")){
			sql=" select a.rule_id ruleId,a.memo memo," +
						//"a.allot_rule allotRule "+
						"(case " +
					    "      when nvl(a.allot_rule , '0') = '0' then '按区域规则' " +
					    "      when nvl(a.allot_rule, '0') = '1' then '订单数' " +
					    "      when nvl(a.allot_rule, '0') = '2' then '配送对象' " +        
					    "       end) allotRule " +      
					   " from wms_logibox_rule a where 1=1 "+
					   " and a.enterprise_no ='"+currEnterpriseNo+"' "+
					   " and a.rule_id = '"+columnId+"'";
			
			strTotsql = "select count(1) from wms_logibox_rule a where 1=1 "+
					" and a.enterprise_no ='"+currEnterpriseNo+"' "+
					" and a.rule_id = '"+columnId+"'";
		}else if(flagSet.equals("3")){
			sql=" select a.task_ruleid ruleId,a.memo memo," +
						//"a.print_type printTypeTest "+
					   "(case " +
					   "      when nvl(a.print_type , '0') = '1' then '打印标签头+明细' " +
					   "      when nvl(a.print_type, '0') = '2' then '标签头' " +
					   "      when nvl(a.print_type, '0') = '3' then '标签明细' " +  
					   "      when nvl(a.print_type , '0') = '4' then '表单' " +
					   "      when nvl(a.print_type, '0') = '5' then '表单+标签头+标签明细' " +
					   "      when nvl(a.print_type, '0') = '6' then '表单+标签头' " + 
					   "      when nvl(a.print_type, '0') = '7' then '表单+标签明细' " +
					   "      end) printTypeTest " +
					   " from wms_taskallot_rule_m a where 1=1 "+
					   " and a.enterprise_no ='"+currEnterpriseNo+"' "+
					   " and a.task_ruleid = '"+columnId+"'";
			
			strTotsql = "select count(1) from wms_logibox_rule a where 1=1 "+
					" and a.enterprise_no ='"+currEnterpriseNo+"' "+
					" and a.rule_id = '"+columnId+"'";
		}else if(flagSet.equals("4")){
			sql=" select a.pick_strategy_id ruleId," +
						"a.pick_strategy_name memo," +
						//"a.pick_diff_flag pickDiffFlag "+
					   " (case when nvl(a.pick_diff_flag,'0') = '0' then '否' else '是' end) pickDiffFlag " +
					   " from wms_outpick_strategy a where 1=1 "+
					   " and a.enterprise_no ='"+currEnterpriseNo+"' "+
					   " and a.pick_strategy_id = '"+columnId+"'";
			
			strTotsql = "select count(1) from wms_outpick_strategy a where 1=1 "+
					" and a.enterprise_no ='"+currEnterpriseNo+"' "+
					" and a.pick_strategy_id = '"+columnId+"'";
		}
	
	List<Wms_OutwaveplanDModel> list = genDao.getListByNativeSql(sql,Wms_OutwaveplanDModel.class,pageBo.getStart(), pageBo.getPagesize());
	Integer intCount = genDao.getCountByNativeSql(strTotsql);
	ExtListDataBo<Wms_OutwaveplanDModel> extListBo = new ExtListDataBo<Wms_OutwaveplanDModel>(list,intCount);
	return extListBo;
}
	@Override
	public List<ComboxBo> queryCdefAreaCombo(String wareHouseNo,
			String strWheresql) throws Exception {
		System.out.println("strWheresql:"+strWheresql);
		String strSql="select '['||c.ware_no||']'||c.area_no value,c.area_no text, " +
				" '['||c.ware_no||']'||c.area_no dropValue " +
				" from CDEF_DEFAREA c " +
				" where 1=1 " +
				" and c.warehouse_no = '"+wareHouseNo+"'" +
				" and c.area_pick = '1' " +
				" and c.area_attribute = '0' " +
				" and c.attribute_type = '0' ";

		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and (c.area_no like '%"+strWheresql+"%' " +
					"or c.ware_no like '%"+strWheresql+"%') ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
}
















