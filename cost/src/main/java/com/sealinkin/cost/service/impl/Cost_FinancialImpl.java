package com.sealinkin.cost.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_FinancialModel;
import com.sealinkin.cost.service.ICost_FinancialService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_FinancialImpl implements ICost_FinancialService{

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
					"and t1.owner_no in("+strWorkerOwner+") " +
					"and t1.status='10' ";
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取UI界面的科目
	@Override
	public List<ComboxBo> getAccountNoForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.account_no value,b.account_name text," +
				 "'['|| ltrim(a.account_no)||']'||nvl(b.account_name,'杂项费用') dropValue " +
				"from cost_financial a ,cost_account_set b " +
				"where 1=1  " +
				"and a.enterprise_no=b.enterprise_no(+) " +
				"and a.account_no=b.account_no(+) " +
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
	//获取UI界面的对账单号
	@Override
	public List<ComboxBo> getCheckNoForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str,
			String strWheresql) throws Exception {
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
		if (strWheresql != null && !strWheresql.equals("")) {
			strSql = strSql + "and a.check_no like '%" + strWheresql + "%' ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//取已出账账单信息列表
	@Override
	public ExtListDataBo<Cost_FinancialModel> getFinList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,String buildDate,
			PageBo poPageBo, Integer intRequestFlag) throws Exception {
		String strSql="select a.enterprise_no,a.warehouse_no, "+
                       "a.owner_no,a.account_no,a.check_no,nvl(a.amount,0)amount, "+
                       "nvl(a.discount_amount,0)discountAmount,nvl(a.real_amount,0)realAmount,a.begin_date, "+
                       "a.end_date,a.flag,a.create_flag,a.build_date,a.status, "+
                       "a.rgst_name,a.rgst_date,a.updt_name,a.updt_date, a.cost_flag,"+
                       "'['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText," +
                       "'[' || a.account_no || ']' || nvl(d.account_name,'杂项费用') accountNoText, "+
                      "'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
                      "to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
                      "to_char(a.begin_date,'yyyy-mm-dd')beginDateText, "+
                      "to_char(a.end_date,'yyyy-mm-dd')endDateText, "+
                      "nvl(b.other_cost1,0)otherCost1, "+
                      "nvl(b.other_cost2,0)otherCost2, "+
                      "nvl(b.other_cost3,0)otherCost3, "+
                      "nvl(b.other_cost4,0)otherCost4, "+
                      "nvl(b.other_cost5,0)otherCost5,"+
                      "(nvl(a.amount,0)+nvl(b.other_cost1,0)" +
                       "+nvl(b.other_cost2,0)+nvl(b.other_cost3,0)" +
                       "+nvl(b.other_cost4,0)+nvl(b.other_cost5,0)" +
                       "-nvl(a.discount_amount,0)) as total " +
                  "from cost_financial a, cost_account_d b, bdef_defowner c,cost_account_set d "+
                 "where a.warehouse_no = b.warehouse_no(+) "+
                   "and a.enterprise_no = b.enterprise_no(+) "+
                   "and a.owner_no = b.owner_no(+) "+
                   "and a.account_no = b.account_no(+) "+
                   "and a.enterprise_no = c.enterprise_no "+
                   "and a.owner_no = c.owner_no " +
                   "and b.enterprise_no = d.enterprise_no(+) " +
                   "and b.account_no = d.account_no(+) " +
                   "and a.enterprise_no='"+strEnterpriseNo+"' " +
   				   "and a.warehouse_no='"+strWarehouseNo+"' " +
   				   "and a.owner_no in("+strWorkerOwner+") ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		
		if(buildDate!=null && !buildDate.equals(""))
		{
			strSql += " and to_date('"+buildDate+"','yyyy/mm/dd') =a.build_date  ";
		}
		strSql+=" order by a.owner_no,a.check_no,a.account_no,a.build_date desc ";
		List<Cost_FinancialModel> list = genDao.getListByNativeSql(strSql,Cost_FinancialModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
		ExtListDataBo<Cost_FinancialModel> extListBo= new ExtListDataBo<Cost_FinancialModel>(list, intCount);
		return extListBo;
	}
	//取导出的已出账账单信息
	@Override
	public List getFinList2(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,
			Integer intRequestFlag) throws Exception {
		String strSql="select "+
				"a.warehouse_no," +
                "'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
                "'[' || a.account_no || ']' || nvl(d.account_name,'杂项费用') accountNoText, "+
                "a.check_no, "+
                "'['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText," +
                "to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
                "to_char(a.begin_date,'yyyy-mm-dd')beginDateText, "+
                "to_char(a.end_date,'yyyy-mm-dd')endDateText, " +
                "nvl(a.amount,0)amount, "+
                "nvl(b.other_cost1,0)otherCost1, "+
                "nvl(b.other_cost2,0)otherCost2, "+
                "nvl(b.other_cost3,0)otherCost3, "+
                "nvl(b.other_cost4,0)otherCost4, "+
                "nvl(b.other_cost5,0)otherCost5,"+
                "nvl(a.discount_amount,0)discountAmount, " +
                "(nvl(a.amount,0)+nvl(b.other_cost1,0)" +
                "+nvl(b.other_cost2,0)+nvl(b.other_cost3,0)" +
                "+nvl(b.other_cost4,0)+nvl(b.other_cost5,0)" +
                "-nvl(a.discount_amount,0)) as total " +
                "from cost_financial a, cost_account_d b, bdef_defowner c,cost_account_set d "+
                "where a.warehouse_no = b.warehouse_no(+) "+
                "and a.enterprise_no = b.enterprise_no(+) "+
                "and a.owner_no = b.owner_no(+) "+
                "and a.account_no = b.account_no(+) "+
                "and a.enterprise_no = c.enterprise_no "+
                "and a.owner_no = c.owner_no " +
                "and b.enterprise_no = d.enterprise_no(+) " +
                "and b.account_no = d.account_no(+) " +
                "and a.enterprise_no='"+strEnterpriseNo+"' " +
			    "and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.owner_no in("+strWorkerOwner+") ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}		
		strSql+=" order by a.owner_no,a.account_no,a.build_date desc ";
		List list=genDao.getListByNativeSql(strSql);

		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no,'','','','','','','', " +
					"sum(amount) amount, " +
					"sum(otherCost1) otherCost1, " +
					"sum(otherCost2) otherCost2, " +
					"sum(otherCost3) otherCost3, " +
					"sum(otherCost4) otherCost4, " +
					"sum(otherCost5) otherCost5, " +
					"sum(discountAmount) discountAmount, " +
					"sum(total) total " +
					"from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;
	}
	//获取科目组
	@Override
	public String getAccountGroupNo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct nvl(a.account_group_no,'A')  " +
				"from cost_financial a " +
				"where  a.warehouse_no ='"+strWarehouseNo+"' " +
				"and a.enterprise_no = '"+strEnterpriseNo+"'" +
				"and a.owner_no in("+strWorkerOwner+") ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	
		List<String> list=genDao.getListByNativeSql(strSql);
		return  list.get(0);
	}
	//获取对账单生成标识
	@Override
	public String getCreateFlag(String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String str) throws Exception {
		String strSql="select nvl(a.create_flag,'A')  " +
				"from cost_financial a " +
				"where  a.warehouse_no ='"+strWarehouseNo+"' " +
				"and a.enterprise_no = '"+strEnterpriseNo+"'" +
				"and a.owner_no in("+strWorkerOwner+") ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	
		List list=genDao.getListByNativeSql(strSql);
		return (String) list.get(0).toString();
	}
	//重算账单
	@Override
	public MsgRes tscResetFin(String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String str) throws Exception {
		List<Cost_FinancialModel> str1=JSON.parseArray(str, Cost_FinancialModel.class);
		
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(str1.get(0).getOwnerNo());//strOwnerNo
		inList.add(str1.get(0).getBuildDate());//dtDate   
		inList.add(str1.get(0).getAccountGroupNo());//strAccountGroupNo   
		inList.add(str1.get(0).getCheckNo());//strCheckNo      
		inList.add(str1.get(0).getFlag());//strFlag         
		inList.add(str1.get(0).getRgstName());//strWorkerNo     
		resultList=genDao.execProc(inList, outList, "PKOBJ_COST.P_FINANCIAL_DETAILS_BYAUTO");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"重算成功！","");
	}
	//账单回退
	@Override
	public MsgRes tscUndoFinancial(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		List<Cost_FinancialModel> str1=JSON.parseArray(str, Cost_FinancialModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		
		for (int i=0; i<str1.size();i++) {
			List  inList=new ArrayList();
			
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(str1.get(i).getOwnerNo());//strOwnerNo
			inList.add(str1.get(i).getCheckNo());//strCheckNo                   
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKOBJ_COST.P_REBACK_FINANCIAL");
			System.out.println(resultList.get(0).toString());

			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"回退成功！","");
	}	
}
