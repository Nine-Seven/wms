/**
 * 模块名称：费用清单维护
 * 模块编码：B102
 * 创建：hcx 
 */
package com.sealinkin.bdef.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.bdef.service.IBill_BaseAmountService;
import com.sealinkin.bset.model.Bill_Base_AmountModel;
import com.sealinkin.bset.model.Bill_FormulasetModel;
import com.sealinkin.bset.po.Bill_BaseAmount;
import com.sealinkin.bset.po.Bill_Formulaset;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bill_BaseAmountImpl implements IBill_BaseAmountService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//取流水号
	@Override
	public MsgRes getSerialNo(String enterpriseNo, String warehouseNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add("F");
		outList.add("S");
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception();
		}
		return new MsgRes(true, "", resultList.get(0).toString());
	}	
	//保存基础费用清单
	@Override
	public void saveBillBaseAmount(String str) throws Exception {
		Bill_BaseAmount billBaseAmount=(Bill_BaseAmount)JSON.parseObject(str,Bill_BaseAmount.class);
		genDao.saveOrUpdateObj(billBaseAmount);
	}
	//获取费用清单信息
	@Override
	public ExtListDataBo<Bill_Base_AmountModel> getBillBaseAmountList(
			String enterpriseNo, String warehouseNo, String str, PageBo pageBo,
			String workerOwner) throws Exception {
		String sql="select a.*, b.fixed_value,b.billing_cycle,b.unit_price, "+
                " f_get_fieldtext('BILL_FORMULASET','BILLING_CYCLE',b.billing_cycle)billingCycleText, " +
				" to_char(a.amount_date,'yyyy-mm-dd')amountDateText," +
				" f_get_fieldtext('BILL_BASE_AMOUNT','FLAG',a.flag)flagText, "+
				" '['|| ltrim(a.billing_project)||']'||b.project_name projectText "+
				" from bill_base_amount a ,bill_formulaset b "+
				" where a.enterprise_no=b.enterprise_no "+
				" and a.warehouse_no=b.warehouse_no "+
				" and a.owner_no=b.owner_no "+
				" and a.billing_project=b.billing_project "+
				" and a.warehouse_no ='"+warehouseNo+"' " +
				" and a.enterprise_no = '"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
		}
		String strTotsql = "select count(*) from (" + sql + ")";

		List<Bill_Base_AmountModel> list = genDao.getListByNativeSql(sql,Bill_Base_AmountModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bill_Base_AmountModel> extListBo = new ExtListDataBo<Bill_Base_AmountModel>(list,intCount);
		return extListBo;
	}
	//获取用于查找的货主下拉
		@Override
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
				"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) dropValue " +
				"from bill_base_amount t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no ='"+enterpriseNo+"' ";
			
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取UI界面的项目名称
	@Override
	public List<ComboxBo> getBillingProjectForUI(String enterpriseNo,String warehouseNo,String str) {
		String strSql="select distinct a.billing_project value,b.project_name text," +
					"'['|| ltrim(a.billing_project)||']'||b.project_name dropValue " +
					" from bill_base_amount a ,bill_formulaset b "+
					" where 1=1" +
					" and a.enterprise_no=b.enterprise_no "+
					" and a.warehouse_no=b.warehouse_no "+
					" and a.owner_no=b.owner_no "+
					" and a.billing_project=b.billing_project "+
					" and a.warehouse_no ='"+warehouseNo+"' " +
					" and a.enterprise_no = '"+enterpriseNo+"' ";
			
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取新增窗口货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForWindow(String enterpriseNo,
			String warehouseNo, String workerOwner) throws Exception {
		String strSql="select distinct t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
				"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) dropValue " +
				"from bill_formulaset t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no ='"+enterpriseNo+"' ";
			
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取新增窗口计费项目下拉
	@Override
	public List<ComboxBo> getBillingProjectForWindow(String enterpriseNo,
			String warehouseNo, String workerOwner, String str)
			throws Exception {
		String strSql="select distinct t1.billing_project value,t1.project_name text," +
				 "'['|| ltrim(t1.billing_project)||']'||t1.project_name dropValue " +
				"from bill_formulaset t1 where 1=1  " +
				"and t1.billing_flag='2' " +
				"and t1.value_flag='2' " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no='"+warehouseNo+"' " +
				"and t1.owner_no in("+workerOwner+") ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//根据计费方式获取固定值和计费周期
	@Override
	public List<Bill_Base_AmountModel> getFixedValueAndBillingCycle(
			String enterpriseNo, String warehouseNo, String workerOwner,
			String str) throws Exception {
		String strSql="select a.fixed_value,a.billing_cycle, a.unit_price, " +
				" f_get_fieldtext('BILL_FORMULASET','BILLING_CYCLE',a.billing_cycle)billingCycleText " +
				"from bill_formulaset a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				 " and a.warehouse_no ='"+warehouseNo+"' " +
				 " and a.owner_no in("+workerOwner+") " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		List<Bill_Base_AmountModel> list=genDao.getListByNativeSql(strSql,Bill_Base_AmountModel.class,0,100);
		return (List<Bill_Base_AmountModel>)list;
	}

	@Override
	public MsgRes check(String strEnterpriseNo, String warehouseNo,String strOwnerNo,
			String billingProject, String amountDate, String billingCycle)
			throws Exception {
//		String sql="";
//		//String billing = billingCycle;
//		if(billingCycle.equals("1") || billingCycle.equals("2")){
//			 sql="select a.amount_date from bill_base_amount a where to_char(a.amount_date,'yyyymmdd') ='"+amountDate+"'  " +
//					" and a.warehouse_no='"+warehouseNo+"' and a.enterprise_no='"+strEnterpriseNo+"' " +
//				     "and a.billing_project = '"+billingProject+"' ";
//		}
//		if(billingCycle.equals("3")){
//			 sql="select a.amount_date from bill_base_amount a where to_char(a.amount_date,'yyyymm') ='"+amountDate+"'    " +
//					" and a.warehouse_no='"+warehouseNo+"' and a.enterprise_no='"+strEnterpriseNo+"' " +
//				     "and a.billing_project = '"+billingProject+"' ";
//		}
//		List<String> list=genDao.getListByNativeSql(sql);
//		if(list.size()==0){
//			return "0";
//		}else{
//			return "1";
//		}
		Date date=DateUtil.GetDate(amountDate, "yyyy-MM-dd");
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(billingProject);//strBillingProject
		inList.add(billingCycle);//strBillingCycle
		inList.add(date);//strAmountDate

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKOBJ_BILL.P_AmountDateCheck");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		System.out.println(resultList.get(0).toString());
		return new MsgRes(true, "",resultList.get(0).toString());
	}	
		
}
