package com.sealinkin.cost.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_FinancialModel;
import com.sealinkin.cost.model.Cost_ReturnAmountModel;
import com.sealinkin.cost.po.Cost_ReturnAmount;
import com.sealinkin.cost.service.ICost_ReceivingService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_ReceivingImpl implements ICost_ReceivingService{

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//获取用于查找的货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner) {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
					"union "+
					"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
					"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) dropValue " +
					"from cost_financial t1 where 1=1  "+
					"and t1.warehouse_no ='"+strWarehouseNo+"' " +
					"and t1.enterprise_no ='"+strEnterpriseNo+"' " +
					"and t1.owner_no in("+strWorkerOwner+") ";
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取UI界面的对账单号
	@Override
	public List<ComboxBo> getCheckNoForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.check_no value,a.check_no text," +
				 "a.check_no dropValue " +
				"from cost_financial a " +
				"where 1=1  " +
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.owner_no in("+strWorkerOwner+") ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	@Override
	public ExtListDataBo<Cost_FinancialModel> getFinList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,
			PageBo poPageBo, Integer intRequestFlag) throws Exception {
		String strSql="select a.enterprise_no,a.warehouse_no, " +
                            "a.owner_no,a.check_no,a.begin_date, " +
                            "a.end_date,a.build_date,a.status, " +
                            "a.rgst_name,a.cost_flag, " +
                      "'['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText," +
                      "'[' || a.owner_no || ']' || c.owner_name ownerNoText, " +
                      "to_char(a.build_date,'yyyy-mm-dd')buildDateText, " +
                      "to_char(a.begin_date,'yyyy-mm-dd')beginDateText, " +
                      "to_char(a.end_date,'yyyy-mm-dd')endDateText, " +
                      "nvl(a.planAmount,0)planAmount, " +
                      "nvl(d.real_amount,0) realAmount, " +
                      "nvl(a.planAmount,0)-nvl(d.real_amount,0) as spareAmount, " +
                      "'['|| ltrim(a.status)||']'||f_get_fieldtext('COST_FINANCIAL','STATUS',a.status) statusText " +
                      "from " +
                          "(select cf.enterprise_no,cf.warehouse_no, " +
                            "cf.owner_no,cf.check_no,cf.begin_date, " +
                            "cf.end_date,cf.build_date,cf.status, " +
                            "cf.rgst_name, cf.cost_flag," +
                            "sum(nvl(cf.amount,0) " +
                            "+nvl(cad.other_cost1,0) " +
                            "+nvl(cad.other_cost1,0) " +
                            "+nvl(cad.other_cost1,0) " +
                            "+nvl(cad.other_cost1,0) " +
                            "+nvl(cad.other_cost1,0) " +
                            "-nvl(cf.discount_amount,0)) as planAmount " +
                            "from cost_financial cf " +
                       "left join cost_account_d cad " +
                              "on cf.warehouse_no = cad.warehouse_no  " +
                             "and cf.enterprise_no = cad.enterprise_no " + 
                             "and cf.owner_no = cad.owner_no  " +
                             "and cf.account_no = cad.account_no " + 
                        "group by cf.enterprise_no, " +
                                 "cf.warehouse_no, " +
                                 "cf.owner_no, " +
                                 "cf.check_no, " +
                                 "cf.begin_date, " +
                                 "cf.end_date, " +
                                 "cf.build_date, " +
                                 "cf.status, " +
                                 "cf.rgst_name," +
                                 "cf.cost_flag)a, " +
                            "bdef_defowner c, " +
                         "(select cra.enterprise_no, " +
                                 "cra.warehouse_no, " +
                                 "cra.owner_no, " +
                                 "cra.check_no, " +
                                 "sum(cra.real_amount) real_amount " + 
                            "from cost_return_amount cra  " +
                        "group by cra.enterprise_no, " +
                                 "cra.warehouse_no, " +
                                 "cra.owner_no, " +
                                 "cra.check_no) d " + 
                     "where a.enterprise_no = c.enterprise_no " + 
                       "and a.owner_no = c.owner_no  " +
                       "and a.enterprise_no = d.enterprise_no(+) " + 
                       "and a.warehouse_no = d.warehouse_no(+)  " +
                       "and a.owner_no = d.owner_no(+) " + 
                       "and a.check_no = d.check_no(+) "+
                       "and a.enterprise_no='"+strEnterpriseNo+"' " +
   				       "and a.warehouse_no='"+strWarehouseNo+"' " +
   				       "and a.owner_no in("+strWorkerOwner+") ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
	
		strSql+=" order by a.owner_no,a.begin_date desc,a.check_no desc ";
		List<Cost_FinancialModel> list = genDao.getListByNativeSql(strSql,Cost_FinancialModel.class,poPageBo.getStart(),poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
		ExtListDataBo<Cost_FinancialModel> extListBo= new ExtListDataBo<Cost_FinancialModel>(list, intCount);
		return extListBo;
	}
	@Override
	public List getFinList2(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,
			Integer intRequestFlag) throws Exception {
		String strSql="select  "+
				"a.warehouse_no," +
                "'[' || a.owner_no || ']' || c.owner_name ownerNoText," +
                "a.check_no, "+
                "'['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText," +
                "to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
                "to_char(a.begin_date,'yyyy-mm-dd')beginDateText, "+
                "to_char(a.end_date,'yyyy-mm-dd')endDateText, "+
                "nvl(a.planAmount,0)planAmount, " +
                "nvl(d.real_amount,0) realAmount, " +
                "nvl(a.planAmount,0)-nvl(d.real_amount,0) as spareAmount, " +
                "'['|| ltrim(a.status)||']'||f_get_fieldtext('COST_FINANCIAL','STATUS',a.status) statusText " +
                "from " +
                    "(select cf.enterprise_no,cf.warehouse_no, " +
                      "cf.owner_no,cf.check_no,cf.begin_date, " +
                      "cf.end_date,cf.build_date,cf.status, " +
                      "cf.rgst_name,cf.cost_flag, " +
                      "sum(nvl(cf.amount,0) " +
                      "+nvl(cad.other_cost1,0) " +
                      "+nvl(cad.other_cost1,0) " +
                      "+nvl(cad.other_cost1,0) " +
                      "+nvl(cad.other_cost1,0) " +
                      "+nvl(cad.other_cost1,0) " +
                      "-nvl(cf.discount_amount,0)) as planAmount " +
                      "from cost_financial cf " +
                 "left join cost_account_d cad " +
                        "on cf.warehouse_no = cad.warehouse_no  " +
                       "and cf.enterprise_no = cad.enterprise_no " + 
                       "and cf.owner_no = cad.owner_no  " +
                       "and cf.account_no = cad.account_no " + 
                  "group by cf.enterprise_no, " +
                           "cf.warehouse_no, " +
                           "cf.owner_no, " +
                           "cf.check_no, " +
                           "cf.begin_date, " +
                           "cf.end_date, " +
                           "cf.build_date, " +
                           "cf.status, " +
                           "cf.rgst_name," +
                           "cf.cost_flag)a, " +
                      "bdef_defowner c, " +
                   "(select cra.enterprise_no, " +
                           "cra.warehouse_no, " +
                           "cra.owner_no, " +
                           "cra.check_no, " +
                           "sum(cra.real_amount) real_amount " + 
                      "from cost_return_amount cra  " +
                  "group by cra.enterprise_no, " +
                           "cra.warehouse_no, " +
                           "cra.owner_no, " +
                           "cra.check_no) d " + 
               "where a.enterprise_no = c.enterprise_no " + 
                 "and a.owner_no = c.owner_no  " +
                 "and a.enterprise_no = d.enterprise_no(+) " + 
                 "and a.warehouse_no = d.warehouse_no(+)  " +
                 "and a.owner_no = d.owner_no(+) " + 
                 "and a.check_no = d.check_no(+) "+
                 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				 "and a.warehouse_no='"+strWarehouseNo+"' " +
				 "and a.owner_no in("+strWorkerOwner+") ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}		
		strSql+=" order by a.owner_no,a.build_date desc ";
		List list=genDao.getListByNativeSql(strSql);

		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no,'','','','','','', " +
					"sum(planAmount) planAmount, " +
					"sum(realAmount) realAmount, " +
					"sum(spareAmount) spareAmount " +
					"from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;
	}
	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.status value,a.status text,"+
				" f_get_fieldtext('COST_FINANCIAL','STATUS',a.status) dropValue "+
				" from cost_financial a " +
				"where 1=1 " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				 "and a.warehouse_no='"+strWarehouseNo+"' " +
			     "and a.owner_no in("+strWorkerOwner+") ";
			
			if (str != null && !str.equals("")) {
				String ws = CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql + ws;
			}
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
	//取回款信息列表
	@Override
	public ExtListDataBo<Cost_ReturnAmountModel> getReturnAmountList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery, PageBo poPageBo)
			throws Exception {
		String strSql="select a.*, "+
                "'[' || b.worker_no || ']' || b.worker_name rgstNameText, "+
                "'['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText," +
                "to_char(a.rgst_date,'yyyy-mm-dd')rgstDateText "+
            "from cost_return_amount a, bdef_defworker b "+
           "where a.enterprise_no = b.enterprise_no "+
             "and a.rgst_name = b.worker_no "+
             "and a.enterprise_no='"+strEnterpriseNo+"' " +
				   "and a.warehouse_no='"+strWarehouseNo+"' " +
				   "and a.owner_no in("+strWorkerOwner+") ";
	
	if(strQuery!=null && !strQuery.equals(""))
	{
		String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		strSql=strSql+strWs;
	}

	strSql+=" order by a.rgst_date desc ";
	List<Cost_ReturnAmountModel> list = genDao.getListByNativeSql(strSql,Cost_ReturnAmountModel.class,poPageBo.getStart(),poPageBo.getPagesize());
	Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	ExtListDataBo<Cost_ReturnAmountModel> extListBo= new ExtListDataBo<Cost_ReturnAmountModel>(list, intCount);
	return extListBo;
	}
	//保存回款信息
	@Override
	public void saveReturnAmount(String str) throws Exception {
		Cost_ReturnAmount formulaset=(Cost_ReturnAmount)JSON.parseObject(str,Cost_ReturnAmount.class);
		genDao.saveOrUpdateObj(formulaset);	
	}
	@Override
	public String getRowId(String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String str) throws Exception {
		String strSql="select nvl(max(t1.row_id),'0')  " +
				"from cost_return_amount t1 " +
			  "where t1.warehouse_no ='"+strWarehouseNo+"' " +
				"and t1.enterprise_no = '"+strEnterpriseNo+"'" +
				"and t1.owner_no in("+strWorkerOwner+") ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	
		List list=genDao.getListByNativeSql(strSql);
		return (String) list.get(0).toString();
	}
	//修改账单状态
	@Override
	public MsgRes update(String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strCheckNo,String strCostFlag,String strStatus) throws Exception {
		String sql="update cost_financial a set a.status='"+strStatus+"' " +
				   "where a.warehouse_no ='"+strWarehouseNo+"' " +
		             "and a.enterprise_no= '"+strEnterpriseNo+"' " +
		             "and a.owner_no in("+strWorkerOwner+") " +
		             "and a.check_no='"+strCheckNo+"' " +
		             "and a.cost_flag='"+strCostFlag+"' ";
		this.genDao.updateBySql(sql);
		return new MsgRes(true, "","");
	}
	
}
