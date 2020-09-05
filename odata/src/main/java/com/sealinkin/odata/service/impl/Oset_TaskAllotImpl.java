package com.sealinkin.odata.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.service.IOset_TaskAllotService;
import com.sealinkin.oset.model.Oset_TaskAllotDModel;
import com.sealinkin.oset.model.Oset_TaskAllotMModel;
import com.sealinkin.oset.po.Oset_TaskAllotD;
import com.sealinkin.oset.po.Oset_TaskAllotM;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Oset_TaskAllotImpl implements IOset_TaskAllotService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//获取切单规则头档
	@Override
	public ExtListDataBo<Oset_TaskAllotMModel> getAccountList(
			String warehouseNo, PageBo pageBo) throws Exception {
		String sql="select t1.*, f_get_fieldtext('N','DEFAULT_FLAG',t1.default_flag) defaultFlagText "+
				"from oset_task_allot_m t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"'";
			
	String strTotsql = "select count(1) from oset_task_allot_m t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"'";
			
	List<Oset_TaskAllotMModel> list = genDao.getListByNativeSql(sql,Oset_TaskAllotMModel.class,pageBo.getStart(), pageBo.getPagesize());
	Integer intCount = genDao.getCountByNativeSql(strTotsql);
	
	ExtListDataBo<Oset_TaskAllotMModel> extListBo = new ExtListDataBo<Oset_TaskAllotMModel>(list,intCount);
	return extListBo;
	}
	
	//保存或更新切单规则头档
	@Override
	public void saveOrupdateTaskAllotM(String str) throws Exception {
		Oset_TaskAllotM otm=(Oset_TaskAllotM)JSON.parseObject(str,Oset_TaskAllotM.class);
		genDao.saveOrUpdateObj(otm);	
	}
	
	//获取默认配置的下拉
	@Override
	public List<ComboxBo> getDefaultFlagmComboList() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='N' "+
				 "and colname='DEFAULT_FLAG'";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//判断当前仓别tasId是否唯一
	@Override
	public List<String> billingProjectCheck(String warehouseNo, String str)
			throws Exception {
		String strSql="select t1.task_id "+
				"from oset_task_allot_m t1 where 1=1  "+
				"and t1.warehouse_no='"+warehouseNo+
				"' and t1.task_id='"+str+"'";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	
	//获取储区(匹配了规则或没匹配规则的储区，区别在于this.getStr()有木有值)
	@Override
	public ExtListDataBo<Cdef_DefareaModel> getDefarea(String warehouseNo,
			PageBo pageBo, String str) {
		String sql="select t1.warehouse_no, t1.ware_no, t1.area_no, t1.area_name "+
				"from cdef_defarea t1 where 1=1 "+
				"and area_type in('1','5','6') "+
				"and area_attribute='0' "+
				"and t1.warehouse_no ='"+warehouseNo+"' ";
			
		String strTotsql = "select count(1) from cdef_defarea t1 where 1=1  "+
				"and area_type in('1','5','6') "+
				"and area_attribute='0' "+
				"and t1.warehouse_no ='"+warehouseNo+"' ";
		
		if(str!=null && !str.equals("")){
			sql=sql+" and t1.task_id ='"+str+"' ";
			strTotsql = strTotsql+" and t1.task_id ='"+str+"' ";
		}else{
			sql=sql+" and t1.task_id is null";
			strTotsql = strTotsql+" and t1.task_id is null";
		}
			
		List<Cdef_DefareaModel> list = genDao.getListByNativeSql(sql,Cdef_DefareaModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
	
		ExtListDataBo<Cdef_DefareaModel> extListBo = new ExtListDataBo<Cdef_DefareaModel>(list,intCount);
		return extListBo;
	}
	
	//跟新切单规则和储区的关系
	@Override
	public void updateAllotAndDefareaRelation(String taskId,
			String warehouseNo, String wareNo, String areaNo) {
		String wheresql1[]=taskId.split(",");
		String wheresql2[]=warehouseNo.split(",");
		String wheresql3[]=wareNo.split(",");
		String wheresql4[]=areaNo.split(",");
		
		for(int i=0;i<wheresql1.length;i++){
			String sql="update cdef_defarea t set t.task_id='"+wheresql1[i].trim()+"' "+
			"where t.warehouse_no='"+wheresql2[i].trim()+"' " +
			"and t.ware_no='"+wheresql3[i].trim()+"'  " +
			"and t.area_no='"+wheresql4[i].trim()+"' "+
			"and area_type in('1','5','6') "+
			"and area_attribute='0' ";
			genDao.updateBySql(sql);
		}		
	}
	
	//根据切单规则头档获取切单规则明细
	@Override
	public ExtListDataBo<Oset_TaskAllotDModel> getAccountList(PageBo pageBo,
			String str) throws Exception {
		String sql="select t.* ,"+
				" f_get_fieldtext('OSET_TASK_ALLOT_D','ALLOT_RULE',t.allot_rule) allotRuleText, "+
				" f_get_fieldtext('OSET_TASK_ALLOT_D','BOX_FLAG',t.box_flag) boxFalgText, "+
				" f_get_fieldtext('N','OPERATE_TYPE',t.operate_type) operateTypeText, "+
				" f_get_fieldtext('N','OUTSTOCK_TYPE',t.outstock_type) outstockTypeText, "+
				" f_get_fieldtext('OSET_TASK_ALLOT_D','TASK_TYPE',t.task_type) taskTypeText, "+
				" f_get_fieldtext('OSET_TASK_ALLOT_D','SOURCE_TYPE',t.source_type) sourceTypeText "+
				" from oset_task_allot_d t where 1=1 ";
			
		String strTotsql = "select count(1) from oset_task_allot_d t where 1=1  ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}
			
		List<Oset_TaskAllotDModel> list = genDao.getListByNativeSql(sql,Oset_TaskAllotDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		
		ExtListDataBo<Oset_TaskAllotDModel> extListBo = new ExtListDataBo<Oset_TaskAllotDModel>(list,intCount);
		return extListBo;
	}
	
	//获取下架类型下拉
	@Override
	public List<ComboxBo> getOutstockTypeComboList() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='N' "+
				 "and colname='OUTSTOCK_TYPE'";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取切单范围下拉
	@Override
	public List<ComboxBo> getAllotRuleComboList() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='OSET_TASK_ALLOT_D' "+
				 "and colname='ALLOT_RULE'";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取切单规则下拉
	@Override
	public List<ComboxBo> getBoxFlagComboList() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='OSET_TASK_ALLOT_D' "+
				 "and colname='BOX_FLAG'";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	//获取拣货打单方式下拉
	@Override
	public List<ComboxBo> getTaskTypeComboList() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='ODATA_OUTSTOCK_M' "+
				 "and colname='TASK_TYPE'";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	//获取作业类型2
	@Override
	public List<ComboxBo> getOoperateTypeComboList() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='N' "+
				 "and colname='OPERATE_TYPE'";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	//保存或修改订单规则明细
	@Override
	public void saveOrupdateTaskAllotD(String str) throws Exception {
		Oset_TaskAllotD otd=(Oset_TaskAllotD)JSON.parseObject(str,Oset_TaskAllotD.class);
		genDao.saveOrUpdateObj(otd);		
	}
	
	////获取作业类型1
	@Override
	public List<ComboxBo> getSourceTypeComboList(String str) {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='OSET_TASK_ALLOT_D' "+
				 "and colname='SOURCE_TYPE'";
		String strTemp="";
		
		if(str.equals("1")||str.equals("2")){
			strTemp="and t1.value in('1','3')";
		}else if(str.equals("3")||str.equals("4")){
			strTemp="and t1.value in('1','2','5')";
		}
			
		strSql=strSql+strTemp;
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
		
}
