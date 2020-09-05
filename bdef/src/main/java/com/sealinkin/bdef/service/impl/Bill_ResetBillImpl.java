package com.sealinkin.bdef.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBill_ResetBillService;
import com.sealinkin.bset.model.Bill_Cost_ListModel;
import com.sealinkin.bset.model.Bill_Expenses_ListModel;
import com.sealinkin.bset.model.Bill_FinancialModel;
import com.sealinkin.bset.po.Bill_Formulaset;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bill_ResetBillImpl implements IBill_ResetBillService{

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public MsgRes tscResetExp(String enterpriseNo,String warehouseNo, String ownerNo,
			String beginDate, String endDate) throws Exception {
		Date beD=DateUtil.GetDate(beginDate, "yyyy-MM-dd");
		Date enD=DateUtil.GetDate(endDate, "yyyy-MM-dd");
		if(beD.compareTo(enD)>0)
		{
			return new MsgRes(false,"开始日期不能大于结束日期！","");
		}
		while (beD.compareTo(enD)<=0) {
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();

			outList.add("S");

			inList.add(enterpriseNo);
			inList.add(warehouseNo);//strWareHouseNo
			inList.add(ownerNo);//strOwnerNo
			inList.add(beD);//重算日期
			inList.add("1");//0:代表正常日结生成，1：代表重算                                     

			resultList = genDao.execProc(inList, outList, "pkobj_bill.P_Generate_ConsumerList");
			System.out.println(inList);
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			beD=DateUtil.addDateTime(beD, 1);
		}			
		return new MsgRes(true,"重算成功！","");
	}

	@Override
	public MsgRes tscResetCost(String enterpriseNo,String warehouseNo, String ownerNo,
			String beginDate, String endDate) throws Exception {
		Date beD=DateUtil.GetDate(beginDate, "yyyy-MM-dd");
		Date enD=DateUtil.GetDate(endDate, "yyyy-MM-dd");
		if(beD.compareTo(enD)>0)
		{
			return new MsgRes(false,"开始日期不能大于结束日期！","");
		}

		while (beD.compareTo(enD)<=0) {
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();

			outList.add("S");

			inList.add(enterpriseNo);
			inList.add(warehouseNo);//strWareHouseNo
			inList.add(ownerNo);//strOwnerNo
			inList.add(beD);//重算日期

			resultList = genDao.execProc(inList, outList, "pkobj_bill.P_Generate_Cost_Details");
			System.out.println(inList);
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			beD=DateUtil.addDateTime(beD, 1);
		}			
		return new MsgRes(true,"重算成功！","");
	}

	@Override
	public MsgRes tscResetFin( String enterpriseNo,String warehouseNo, String ownerNo,
			String month) throws Exception {

		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();

		outList.add("S");

		inList.add(enterpriseNo);
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(ownerNo);//strOwnerNo
		inList.add(month);//重算日期

		resultList = genDao.execProc(inList, outList, "pkobj_bill.P_Generate_Financial_Details");
		System.out.println(inList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"重算成功！","");
	}
	@Override
	public ExtListDataBo<Bill_Expenses_ListModel> getExpList(
			String enterpriseNo,String warehouseNo, String strOwnerNo, String strQuery,
			PageBo poPageBo, Integer intRequestFlag) throws Exception {
		String strSql="select  " +
				"a.warehouse_no," +
				"a.owner_no," +
				"'['||a.billing_project||']'||b.project_name billing_project," +
				"a.group_no," +
				"a.begin_date," +
				"a.end_date," +
				"a.area," +
				"a.tray," +
				"a.qty," +
				"a.volume," +
				"a.weight," +
				"a.reserved1," +
				"a.reserved2," +
				"a.reserved3," +
				"a.reserved4," +
				"a.reserved5," +
				"a.reserved6," +
				"a.serial_no," +
				"a.billing_type," +
				"a.flag, " +
				"    (case b.billing_cycle " +
				"     when '1' then " +
				"    (select c.value " +
				"     from bill_base_amount c " +
				"    where b.warehouse_no = c.warehouse_no " +
				"    and b.enterprise_no = c.enterprise_no " +
				"    and b.billing_project = c.billing_project " +
				"    and b.owner_no = c.owner_no " +
				"    and trunc(a.begin_date) = trunc(c.amount_date)) " +
				"    when '2'  then " +
				"    (select c.value " +
				"    from bill_base_amount c " +
				"    where b.warehouse_no = c.warehouse_no " +
				"    and b.enterprise_no = c.enterprise_no " +
				"    and b.billing_project = c.billing_project " +
				"    and b.owner_no = c.owner_no " +
				"    and trunc(a.begin_date) <= trunc(c.amount_date) "+
                "    and trunc(a.end_date) >= trunc(c.amount_date)) " +
                "    when '3'  then " +
				"    (select c.value " +
				"    from bill_base_amount c " +
				"    where b.warehouse_no = c.warehouse_no " +
				"    and b.enterprise_no = c.enterprise_no " +
				"    and b.billing_project = c.billing_project " +
				"    and b.owner_no = c.owner_no " +
				"    and trunc(a.begin_date) <= trunc(c.amount_date) "+
                "    and trunc(a.end_date) >= trunc(c.amount_date)) " +
				"    end) value "+
				"from " +
				"bill_expenses_list a," +
				"bill_formulaset b " +
				"where " +
				"a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.owner_no=b.owner_no " +
				"and a.billing_project=b.billing_project ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}

		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql+=" order by a.begin_date ";
		List<Bill_Expenses_ListModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bill_Expenses_ListModel> extListBo=null;
		if(intRequestFlag==1)
		{
			list = genDao.getListByNativeSql(strSql,Bill_Expenses_ListModel.class,poPageBo.getStart(),poPageBo.getPagesize());
			intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");
		}
		else
		{
			list = genDao.getListByNativeSql(strSql,Bill_Expenses_ListModel.class);
			intCount = list.size();
		}
		if(list.size()>0){
			List<Bill_Expenses_ListModel> listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no, " +
					"sum(area) area," +
					"sum(tray) tray," +
					"sum(qty) qty," +
					"sum(volume) volume," +
					"sum(weight) weight from("+strSql+")",Bill_Expenses_ListModel.class);
			list.add(listtot.get(0));
		}
		extListBo= new ExtListDataBo<Bill_Expenses_ListModel>(list, intCount);
		return extListBo;
	}
	@Override
	public ExtListDataBo<Bill_Cost_ListModel> getCostList(String enterpriseNo,String warehouseNo,
			String strOwnerNo, String strQuery, PageBo poPageBo,
			Integer intRequestFlag) throws Exception {
		String strSql="select a.warehouse_no,    "+
				"     a.owner_no,    " +
				"     a.billing_project as billingNo, "+
				"     '[' || a.billing_project || ']' || b.project_name billing_project,    "+
				"     a.billing_date,    "+
				"     a.amount,    "+
				"     a.flag,    "+
				"     a.serial_no,    "+
				"     a.append_condition,    "+
				"     a.append_value1,    "+
				"     a.append_value2,    "+
				"     f_get_fieldtext('BILL_FORMULASET','BILLING_UNIT',a.billing_unit) billing_unit,"+
				"     a.unit_price, " +
				"	  a.qty,    " +
				"	  a.status, " +
				"	  f_get_fieldtext('BILL_COST_LIST','STATUS', a.status) statusText " +
//				"    (case b.billing_cycle " +
//				"     when '1' then " +
//				"    (select c.value " +
//				"     from bill_base_amount c " +
//				"    where b.warehouse_no = c.warehouse_no " +
//				"    and b.enterprise_no = c.enterprise_no " +
//				"    and b.billing_project = c.billing_project " +
//				"    and b.owner_no = c.owner_no " +
//				"    and trunc(a.billing_date) = trunc(c.amount_date)) " +
//				"    when '2'  then " +
//				"    (select c.value " +
//				"    from bill_base_amount c " +
//				"    where b.warehouse_no = c.warehouse_no " +
//				"    and b.enterprise_no = c.enterprise_no " +
//				"    and b.billing_project = c.billing_project " +
//				"    and b.owner_no = c.owner_no " +
//				"    and trunc(a.begin_date) <= trunc(c.amount_date) "+
//                "    and trunc(a.end_date) >= trunc(c.amount_date)) " +
//                "    when '3'  then " +
//				"    (select c.value " +
//				"    from bill_base_amount c " +
//				"    where b.warehouse_no = c.warehouse_no " +
//				"    and b.enterprise_no = c.enterprise_no " +
//				"    and b.billing_project = c.billing_project " +
//				"    and b.owner_no = c.owner_no " +
//				"    and trunc(a.begin_date) <= trunc(c.amount_date) "+
//                "    and trunc(a.end_date) >= trunc(c.amount_date)) " +
//				"    end) value "+
				" from " +
				"	bill_cost_list a, " +
				"	bill_formulaset b   " +
				" where " +
				"	a.warehouse_no = b.warehouse_no    "+
				"	and a.enterprise_no = b.enterprise_no    "+
				"   and a.owner_no = b.owner_no    "+
				"   and a.billing_project = b.billing_project    ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql+=" order by a.billing_date ";
		List<Bill_Cost_ListModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bill_Cost_ListModel> extListBo=null;
		if(intRequestFlag==1)
		{
			list = genDao.getListByNativeSql(strSql,Bill_Cost_ListModel.class,poPageBo.getStart(),poPageBo.getPagesize());
			intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");
		}
		else
		{
			list = genDao.getListByNativeSql(strSql,Bill_Cost_ListModel.class);
			intCount = list.size();
		}
		if(list.size()>0){
			List<Bill_Cost_ListModel> listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no, sum(amount) amount,sum(qty) qty from("+strSql+")",Bill_Cost_ListModel.class);
			list.add(listtot.get(0));
		}
		extListBo= new ExtListDataBo<Bill_Cost_ListModel>(list, intCount);
		return extListBo;
	}
	@Override
	public ExtListDataBo<Bill_FinancialModel> getFinList(String enterpriseNo,String warehouseNo,
			String strOwnerNo, String strQuery, PageBo poPageBo,
			Integer intRequestFlag) throws Exception {
		String strSql="select a.warehouse_no,    "+
				"       a.owner_no,    "+
				"       a.billing_month,    "+
				"       '[' || a.account_no || ']' || b.account_name account_no,    "+
				"       a.amount,    "+
				"       a.discount_amount    "+
				"from     "+
				"   bill_financial a, " +
				"	bill_account_m b    "+
				"where " +
				"	a.warehouse_no = b.warehouse_no    "+
				"	and a.enterprise_no = b.enterprise_no    "+
				"   and a.owner_no = b.owner_no    "+
				"   and a.account_no = b.account_no";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}

		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		List<Bill_FinancialModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bill_FinancialModel> extListBo=null;
		if(intRequestFlag==1)
		{
			list = genDao.getListByNativeSql(strSql,Bill_FinancialModel.class,poPageBo.getStart(),poPageBo.getPagesize());
			intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");
		}
		else
		{
			list = genDao.getListByNativeSql(strSql,Bill_FinancialModel.class);
			intCount = list.size();
		}
		if(list.size()>0){
			List<Bill_FinancialModel> listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no, " +
					"sum(amount) amount,sum(discount_amount) discount_amount from("+strSql+")",Bill_FinancialModel.class);
			list.add(listtot.get(0));
		}
		extListBo= new ExtListDataBo<Bill_FinancialModel>(list, intCount);
		return extListBo;
	}
	@Override
	public List getExpList2(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String strQuery, Integer intRequestFlag)
					throws Exception {
		String strSql="select  " +
				"a.warehouse_no," +
				"a.owner_no," +
				"'['||a.billing_project||']'||b.project_name billing_project," +
				"to_char(a.begin_date,'yyyy-mm-dd')begin_date," +
				"to_char(a.end_date,'yyyy-mm-dd')end_date," +
				"    (case b.billing_cycle " +
				"     when '1' then " +
				"    (select c.value " +
				"     from bill_base_amount c " +
				"    where b.warehouse_no = c.warehouse_no " +
				"    and b.enterprise_no = c.enterprise_no " +
				"    and b.billing_project = c.billing_project " +
				"    and b.owner_no = c.owner_no " +
				"    and trunc(a.begin_date) = trunc(c.amount_date)) " +
				"    when '2'  then " +
				"    (select c.value " +
				"    from bill_base_amount c " +
				"    where b.warehouse_no = c.warehouse_no " +
				"    and b.enterprise_no = c.enterprise_no " +
				"    and b.billing_project = c.billing_project " +
				"    and b.owner_no = c.owner_no " +
				"    and trunc(a.begin_date) <= trunc(c.amount_date) "+
                "    and trunc(a.end_date) >= trunc(c.amount_date)) " +
                "    when '3'  then " +
				"    (select c.value " +
				"    from bill_base_amount c " +
				"    where b.warehouse_no = c.warehouse_no " +
				"    and b.enterprise_no = c.enterprise_no " +
				"    and b.billing_project = c.billing_project " +
				"    and b.owner_no = c.owner_no " +
				"    and trunc(a.begin_date) <= trunc(c.amount_date) "+
                "    and trunc(a.end_date) >= trunc(c.amount_date)) " +
				"    end) value, "+
				"a.area," +
				"a.tray," +
				"a.qty," +
				"a.volume," +
				"a.weight " +
				
				"from " +
				"bill_expenses_list a," +
				"bill_formulaset b " +
				"where " +
				"a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.owner_no=b.owner_no " +
				"and a.billing_project=b.billing_project ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}

		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql+=" order by a.begin_date ";
		List list=genDao.getListByNativeSql(strSql);

		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no,'','','','','', " +
					"sum(area) area," +
					"sum(tray) tray," +
					"sum(qty) qty," +
					"sum(volume) volume," +
					"sum(weight) weight from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;
	}
	@Override
	public List getCostList2(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String strQuery, Integer intRequestFlag)
			throws Exception {
		String strSql="select a.warehouse_no,    "+
				"     a.owner_no,    "+
				"     '[' || a.billing_project || ']' || b.project_name billing_project,    "+
				"     to_char(a.billing_date,'yyyy-mm-dd')billing_date,    "+
				"     f_get_fieldtext('BILL_FORMULASET','BILLING_UNIT',a.billing_unit) billing_unit,"+
				"     a.unit_price, " +
				"	  a.qty,    " +
				"     a.amount,    "+
				"     a.append_condition,    "+
				"     a.append_value1,    "+
				"     a.append_value2,    "+
				"	  f_get_fieldtext('BILL_COST_LIST','STATUS', a.status) statusText " +
				" from " +
				"	bill_cost_list a, " +
				"	bill_formulaset b   " +
				" where " +
				"	a.warehouse_no = b.warehouse_no    "+
				"	and a.enterprise_no = b.enterprise_no    "+
				"   and a.owner_no = b.owner_no    "+
				"   and a.billing_project = b.billing_project    ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql+=" order by a.billing_date ";
		
		List list = genDao.getListByNativeSql(strSql);
		
		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no,'','','','','', sum(amount) amount,sum(qty) qty from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;

	}
	@Override
	public List getFinList2(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String strQuery, Integer intRequestFlag)
			throws Exception {
		String strSql="select a.warehouse_no,    "+
				"       a.owner_no,    "+
				"       a.billing_month,    "+
				"       '[' || a.account_no || ']' || b.account_name account_no,    "+
				"       a.amount,    "+
				"       a.discount_amount    "+
				"from     "+
				"   bill_financial a, " +
				"	bill_account_m b    "+
				"where " +
				"	a.warehouse_no = b.warehouse_no    "+
				"	and a.enterprise_no = b.enterprise_no    "+
				"   and a.owner_no = b.owner_no    "+
				"   and a.account_no = b.account_no";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}

		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}

		List list = genDao.getListByNativeSql(strSql);
		
		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no, " +
					"'','','',sum(amount) amount,sum(discount_amount) discount_amount from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;
	}
	//费用上缴
	@Override
	public MsgRes paidCost(String jsonStr) throws Exception {
		List<Bill_Cost_ListModel> costList=JSON.parseArray(jsonStr,Bill_Cost_ListModel.class);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		for(int i=0; i<costList.size();i++){
			String sql="update bill_cost_list a set status='13' " +
					"where a.enterprise_no='"+costList.get(i).getEnterpriseNo()+"' " +
					  "and a.warehouse_no='"+costList.get(i).getWarehouseNo()+"' " +
					  "and a.owner_no='"+costList.get(i).getOwnerNo()+"' " +
					  "and a.billing_project='"+costList.get(i).getBillingNo()+"' " +
					  "and to_char(a.billing_date,'yyyy/MM/dd')='"+formatter.format(costList.get(i).getBillingDate())+"' ";
			genDao.updateBySql(sql);
		}
		return new MsgRes(true, "费用上缴成功！", "");

	}
	//取消费用
	@Override
	public MsgRes undoCost(String jsonStr) throws Exception {
		List<Bill_Cost_ListModel> costList=JSON.parseArray(jsonStr,Bill_Cost_ListModel.class);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		for(int i=0; i<costList.size();i++){
			String sql="update bill_cost_list a set status='16' " +
					"where a.enterprise_no='"+costList.get(i).getEnterpriseNo()+"' " +
					  "and a.warehouse_no='"+costList.get(i).getWarehouseNo()+"' " +
					  "and a.owner_no='"+costList.get(i).getOwnerNo()+"' " +
					  "and a.billing_project='"+costList.get(i).getBillingNo()+"' " +
					  "and to_char(a.billing_date,'yyyy/MM/dd')='"+formatter.format(costList.get(i).getBillingDate())+"' ";
			genDao.updateBySql(sql);
		}
		return new MsgRes(true, "取消费用成功！", "");
	}
	@Override
	public List<ComboxBo> getStatusList(String enterpriseNo,
			String workerOwner, String warehouseNo, String str)
			throws Exception {
		String strSql="select distinct t1.status value,t1.status text,"+
				" f_get_fieldtext('BILL_COST_LIST','STATUS',t1.status) dropValue "+
				" from bill_cost_list t1 where 1=1 ";

			if(workerOwner!=null && !workerOwner.equals(""))
			{
				strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
			}
			
			if (str != null && !str.equals("")) {
				String ws = CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql + ws;
			}
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 200);
			return (List<ComboxBo>) list;
	}

}
