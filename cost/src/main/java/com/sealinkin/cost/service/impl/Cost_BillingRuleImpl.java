package com.sealinkin.cost.service.impl;
/**
 * 模块名称：计费取值方式维护
 * 模块编码：B1003
 * @author czh
 */
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_BillingRuleModel;
import com.sealinkin.cost.model.Cost_BillingTypeModel;
import com.sealinkin.cost.po.Cost_BillingRule;
import com.sealinkin.cost.po.Cost_BillingType;
import com.sealinkin.cost.service.ICost_BillingRuleService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;


@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_BillingRuleImpl implements ICost_BillingRuleService{

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public ExtListDataBo<Cost_BillingTypeModel> getRuleTypeList(
			String strEnterpriseNo, String strQuery, PageBo pageBo)
			throws Exception {
		String sql="select a.* ," +
				" f_get_fieldtext('COST_BILLING_TYPE','STATUS',a.Status)statusText ," +
				"'['|| ltrim(a.billing_type)||']'||a.billing_type_name billingTypeText "+
				"from cost_billing_type a " +
				"where " +
				"1=1  " +
				"and a.enterprise_no = '"+strEnterpriseNo+"' ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		sql += " order by a.billing_type ";
		
		String strTotsql="select count(*) from (" + sql + ") " ;
		List<Cost_BillingTypeModel> list = genDao.getListByNativeSql(sql,Cost_BillingTypeModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_BillingTypeModel> extListBo = new ExtListDataBo<Cost_BillingTypeModel>(list,intCount);
		return extListBo;
	}

	@Override
	public ExtListDataBo<Cost_BillingRuleModel> getBillingRuleList(
			String strEnterpriseNo, String billingType, String strQuery,
			PageBo pageBo) throws Exception {
		// TODO Auto-generated method stub
		String sql="select a.*, " +
				" f_get_fieldtext('COST_BILLING_TYPE','STATUS',a.use_type)statusText," +
				/*" f_get_fieldtext('COST_FORMULASET','BILLING_UNIT',a.billing_unit)billingUnitText1," +*/
				"'['|| ltrim(a.billing_unit)||']'||f_get_fieldtext('COST_FORMULASET','BILLING_UNIT',a.billing_unit) billingUnitText "+
				"from cost_billing_rule a,cost_billing_type b  " +
				"where a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.billing_type=b.billing_type " +
				"and a.billing_type='"+billingType+"'";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		sql += " order by a.rule_id , a.billing_unit";
		String strTotsql="select count(*) from (" + sql + ") " ;
		
		List<Cost_BillingRuleModel> list = genDao.getListByNativeSql(sql,Cost_BillingRuleModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_BillingRuleModel> extListBo = new ExtListDataBo<Cost_BillingRuleModel>(list,intCount);
		return extListBo;
	}

	@Override
	public void saveCostRuleType(String str) throws Exception {
		// TODO Auto-generated method stub
		Cost_BillingType cost_BillingType=(Cost_BillingType)JSON.parseObject(str,Cost_BillingType.class);
		genDao.saveOrUpdateObj(cost_BillingType);
	}

	@Override
	public List<String> checkRuleTypeNo(String strEnterpriseNo, String str)
			throws Exception {
		// TODO Auto-generated method stub
		String strSql="select a.billing_type "+
				"from cost_billing_type a where 1=1  "+
				"and a.billing_type='"+str+
				"' and a.enterprise_no='"+strEnterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	@Override
	public MsgRes deleteCostRuleType(String strEnterpriseNo, String str)
			throws Exception {
		// TODO Auto-generated method stub
		String sql=" select a.billing_type from cost_billing_rule a " +
				   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				   "    and a.billing_type='"+str+"' ";
		
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该计费类型已使用，不能删除","");
		}
		String deleteSql="delete from cost_billing_type a  " +
				"where a.billing_type='"+str+"' "+
				"and a.enterprise_no='"+strEnterpriseNo+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

	@Override
	public void saveCostRule(String str) throws Exception {
		// TODO Auto-generated method stub
		Cost_BillingRule cost_BillingRule=(Cost_BillingRule)JSON.parseObject(str,Cost_BillingRule.class);
		if(cost_BillingRule.getId().getRuleId()==null){
			cost_BillingRule.getId().setRuleId(getMaxCostRuleNo());
		}
		genDao.saveOrUpdateObj(cost_BillingRule);
	}
	//自动生成新增计费取值方式编号
	public short getMaxCostRuleNo(){
    	short maxFileNo;
    	String sql = "select Max(t.rule_id) from cost_billing_rule t";
		List maxFileNoList = genDao.getListByNativeSql(sql);
		if (maxFileNoList.get(0) == null) {
			maxFileNo=1;
		} else {
			 maxFileNo = (short) (Short.parseShort(maxFileNoList.get(0)
					.toString()) + 1);
		}
		return  maxFileNo;
    }

	@Override
	public MsgRes deleteCostRule(String strEnterpriseNo, String str)
			throws Exception {
		// TODO Auto-generated method stub
		Cost_BillingRule cost_BillingRule=(Cost_BillingRule)JSON.parseObject(str,Cost_BillingRule.class);
		String deleteSql="delete from cost_billing_rule a  " +
				"where a.billing_type='"+cost_BillingRule.getId().getBillingType()+"' "+
				"and a.billing_unit='"+cost_BillingRule.getId().getBillingUnit()+"' "+
				"and a.rule_id='"+cost_BillingRule.getId().getRuleId()+"' "+
				"and a.enterprise_no='"+strEnterpriseNo+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

	
	

}
