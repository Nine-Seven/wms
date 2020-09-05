/**
 * 模块名称：计费公式管理实现
 * 模块编码：B103
 * 创建：hcx 
 */
package com.sealinkin.cost.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.sealinkin.cost.po.Cost_FormulaArticlefamily;
import com.sealinkin.cost.po.Cost_FormulaDiscount;
import com.sealinkin.cost.po.Cost_Formulaset;
import com.sealinkin.cost.service.ICost_FormulasetService;
import com.sealinkin.cost.model.Cost_FormulaArticlefamilyModel;
import com.sealinkin.cost.model.Cost_FormulaDiscountModel;
import com.sealinkin.cost.model.Cost_FormulasetModel;
import com.sealinkin.bset.po.Bill_FamilyUnitPrice;
import com.sealinkin.bset.po.Bill_FormulasetTmp;
import com.sealinkin.bset.po.Bill_FormulasetTmpId;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_FormulasetImpl implements ICost_FormulasetService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//保存计费公式
	@Override
	public void saveFormulaset(String str) throws Exception {

		Cost_Formulaset formulaset=(Cost_Formulaset)JSON.parseObject(str,Cost_Formulaset.class);
		genDao.saveOrUpdateObj(formulaset);		
	}
	//保存计费公式
	@Override
	public void saveFormulaset2(String str) throws Exception {
		List<Cost_Formulaset> formulaset=JSON.parseArray(str, Cost_Formulaset.class);
		genDao.saveList(formulaset);	
	}
	//获取计费公式
	@Override
	public ExtListDataBo<Cost_FormulasetModel> getFormulasetList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception {
		String sql="select t1.* , " +
				" '['|| ltrim(t1.owner_no)||']'||t2.owner_name ownerNoText, " +
				" '['|| ltrim(t1.billing_project)||']'||t1.project_name projectText, " +
				" f_get_fieldtext('COST_FORMULASET','BILLING_CYCLE',t1.billing_cycle)billingCycleText, "+
				" f_get_fieldtext('COST_FORMULASET','BILLING_FLAG',t1.billing_flag)billingFlagText, "+
				" f_get_fieldtext('COST_FORMULASET','BILLING_UNIT',t1.billing_unit)billingUnitText, "+
				" '['|| ltrim(t1.billing_type)||']'|| t3.billing_type_name billingTypeText, "+
				" f_get_fieldtext('COST_FORMULASET','STATUS',t1.Status)statusText, " +
				" f_get_fieldtext('COST_FORMULASET','STANDARD_FLAG',t1.standard_flag)standardFlagText, " +
				" to_char(t1.begin_date,'yyyy-mm-dd') beginDateText, " +
				" to_char(t1.end_date,'yyyy-mm-dd') endDateText, " +
				" case when t1.billing_cycle in('3') then t1.balance_day else "+
				" f_get_fieldtext('COST_FORMULASET','BALANCE_DAY',t1.balance_day) end balanceDayText, " +
				" '['|| ltrim(t1.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',t1.cost_flag)costFlagText " +
				" from cost_formulaset t1 ,bdef_defowner t2,cost_billing_type t3 " +
		       " where t1.enterprise_no=t2.enterprise_no " +
	             " and t1.owner_no=t2.owner_no " +
	             " and t1.enterprise_no=t3.enterprise_no " +
	             " and t1.billing_type=t3.billing_type " +
		         " and t1.warehouse_no ='"+warehouseNo+"' " +
				 " and t1.enterprise_no = '"+enterpriseNo+"' " +
				 " and t1.owner_no in("+workerOwner+") ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		
		sql += " order by t1.owner_no,t1.billing_type,t1.billing_project ";
		String strTotsql ="select count(*) from (" + sql + ")";

		List<Cost_FormulasetModel> list = genDao.getListByNativeSql(sql,Cost_FormulasetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_FormulasetModel> extListBo = new ExtListDataBo<Cost_FormulasetModel>(list,intCount);
		return extListBo;
	}
	//获取用于查找的货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
				"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) dropValue " +
				"from cost_formulaset t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no ='"+enterpriseNo+"' " +
				"and t1.owner_no in("+workerOwner+") ";
		
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	//获取UI界面的项目名称
	@Override
	public List<ComboxBo> getBillingProjectForUI(String enterpriseNo,String warehouseNo,String str) {
		String strSql="select distinct t1.billing_project value,t1.project_name text," +
				 "'['|| ltrim(t1.billing_project)||']'||t1.project_name dropValue " +
				"from cost_formulaset t1 where 1=1  " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no='"+warehouseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	//判断计费项目编号是否唯一
	@Override
	public List<String> billingProjectCheck(
			String enterpriseNo,String str,String warehouseNo,String ownerNo) throws Exception {
		String strSql="select t1.billing_project "+
				"from cost_formulaset t1 where 1=1  "+
				"and t1.billing_project='"+str+
				"' and t1.warehouse_no='"+warehouseNo+
				"' and t1.owner_no='"+ownerNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	//获取计费类型
	@Override
	public List<ComboxBo> getBillingTypeForWind(String enterpriseNo, String str)
			throws Exception {
		String strSql="select distinct t1.billing_type value,t1.billing_type_name text," +
				 "'['|| ltrim(t1.billing_type)||']'||t1.billing_type_name dropValue " +
				"from cost_billing_type t1 where 1=1  " +
				"and t1.enterprise_no='"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取取值策略
	@Override
	public List<ComboxBo> getValueFlagCombo(String enterpriseNo,String billingType,
			String billingUnit) {
		String strSql="	select distinct t.rule_id value ,  t.strategy_name text," +
				 "'['|| ltrim(t.rule_id)||']'||t.strategy_name dropValue" +
				 " from cost_billing_rule t where 1=1  " +
			     " and t.rule_id is not null "+
				 " and t.billing_unit='"+billingUnit+
				 "' and t.billing_type='"+billingType+
				 "' and t.enterprise_no='"+enterpriseNo+"' ";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	@Override
	public List<String> getRemarkCombo(String billingType, String billingUnit,
			String ruleId) {
		String strSql="select  t.strategy_name  from cost_billing_rule t " +
			          " where t.billing_type='"+billingType+
			          "' and t.billing_unit='"+billingUnit+
			          "' and t.rule_id='"+ruleId+"'";
				
		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	@Override
	public List<ComboxBo> getarticleFamilyNoCombo(String enterpriseNo,
			String workerOwner,String str) throws Exception {
		String strSql="select distinct a.family_no value, " +
				"a.family_name text, " +
				"'['|| ltrim(a.family_no)||']'||a.family_name dropValue " +
				"from bdef_article_family_m a where  "+
				"a.enterprise_no = '"+enterpriseNo+"' " +
				"and a.owner_no in("+workerOwner+") ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	@Override
	public void saveFamilyUnitPrice(String str) throws Exception {
		Bill_FamilyUnitPrice familyUnitPrice=(Bill_FamilyUnitPrice)JSON.parseObject(str,Bill_FamilyUnitPrice.class);
		genDao.saveOrUpdateObj(familyUnitPrice);		
	}

	@Override
	public MsgRes upLoad(File file, String strWarehouseNo,
			String strEnterpriseNo,String strUserId) throws Exception {
		System.out.println("aaaaaaa");
		FileUtilSys.writeFile(file, "billFTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		
		List<Bill_FormulasetTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"billFTmp.xlsx",strWarehouseNo,strEnterpriseNo);
			
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strUserId);
		resultList=genDao.execProc(inList, outList, "PKOBJ_CREATE_BASE.P_bill_formulaset");

		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,resultList.get(0).toString(),"");
	}
	/**
	 * Excel数据转List
	 */
	@Override
	public List<Bill_FormulasetTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception {
		List<Bill_FormulasetTmp> iitList = new ArrayList<Bill_FormulasetTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from bill_formulaset_tmp a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Bill_FormulasetTmp po = new Bill_FormulasetTmp();
				Bill_FormulasetTmpId id = new Bill_FormulasetTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);//企业
				id.setWarehouseNo(strWarehouseNo);//仓别
				
				id.setOwnerNo(excelList.get(i).get(0).toString());//货主编码
				id.setBillingType(excelList.get(i).get(1).toString());//项目类型
				id.setBillingProject(excelList.get(i).get(2).toString());//项目编码
				id.setProjectName(excelList.get(i).get(3).toString());//项目名称
				id.setFamilyNo(excelList.get(i).get(4).toString());//群组编码
				id.setBillingFlag(excelList.get(i).get(5).toString());//计费方式
				id.setBillingUnit(excelList.get(i).get(6).equals("")?"":excelList.get(i).get(6).toString());//计费单位
				id.setValueFlag(excelList.get(i).get(7).equals("")?"":excelList.get(i).get(7).toString());//取值方式
				id.setFixedValue(Double.parseDouble(excelList.get(i).get(8).toString().equals("")?"0":excelList.get(i).get(8).toString()));//固定值
				id.setUnitPrice(Double.parseDouble(excelList.get(i).get(9).toString().equals("")?"1":excelList.get(i).get(9).toString()));//默认单价
				id.setBillingCycle(excelList.get(i).get(10).toString());//计费周期
				id.setAppendCondition(excelList.get(i).get(11).toString());//附加条件
				id.setAppendValue1(Double.parseDouble(excelList.get(i).get(12).equals("")?"0":excelList.get(i).get(12).toString()));//附加值1
				id.setAppendValue2(Double.parseDouble(excelList.get(i).get(13).equals("")?"0":excelList.get(i).get(13).toString()));//附加值2
				id.setBalanceDay(excelList.get(i).get(14).equals("")?"":excelList.get(i).get(15).toString());//结算日期
				if(excelList.get(i).get(15).toString().equals("")){
					id.setEndDate(null);//截止日期
				}else{
					id.setEndDate(sdf.parse(excelList.get(i).get(15).toString()));//截止日期	
				}
//				id.setEndDate(sdf.parse(excelList.get(i).get(14).toString().equals("")?"1900-1-1":excelList.get(i).get(14).toString()));//截止日期

				if(excelList.get(i).size() == 16 ){
					id.setRemark(excelList.get(i).get(16).toString());//备注
				}
				id.setStatus("10");
				id.setRowId(i+2);
				po.setId(id);
				iitList.add(po);
			}
		}
		return iitList;
	}
	
	/**
	 * 解析Excel
	 */
	@Override
	public List<List> impExcel(String execelFile)throws Exception{
		List<List> inputlist = new ArrayList<List>();
		try {
			// 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)
			Workbook book = null;
			try {
				// Excel 2007获取方法
				book = new XSSFWorkbook(new FileInputStream(execelFile));
			} catch (Exception ex) {
				// Excel 2003获取方法
				book = new HSSFWorkbook(new FileInputStream(execelFile));
			}
			// 读取表格的第一个sheet页
			Sheet sheet = book.getSheetAt(0);
			// 定义 row、cell
			Row row;
			String cell;
			// 总共有多少行,从0开始
			int totalRows = sheet.getLastRowNum();
			// 总共有多少列
			int totalCells = sheet.getRow(0).getLastCellNum();
			// 循环输出表格中的内容,首先循环取出行,再根据行循环取出列
			for (int i = 1; i <= totalRows; i++) {
				row = sheet.getRow(i);
				List object = new ArrayList();
				// 处理空行
				if (row == null) {
					// object.add("");
					continue;
				}
//				// 总共有多少列,从0开始
//				int totalCells = row.getLastCellNum();
				for (int j = row.getFirstCellNum(); j < totalCells; j++) {
					// 处理空列
					if (row.getCell(j) == null) {
						object.add("");
						continue;
					}
					// 通过 row.getCell(j).toString() 获取单元格内容
					cell = this.getValue((HSSFCell) row.getCell(j));
					object.add(cell.trim());
					System.out.print(cell + "\n");
				}
				inputlist.add(object);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return inputlist;
	}
	
	//Excel格式转换
		@SuppressWarnings("static-access")
		private String getValue(HSSFCell xssfCell) {

			if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfCell.getBooleanCellValue());
			} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
				DecimalFormat df = new DecimalFormat("0");
				String whatYourWant = df.format(xssfCell.getNumericCellValue());
				return whatYourWant;
			} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
				return String.valueOf(xssfCell.getStringCellValue());
			} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfCell.getBooleanCellValue());
			} else {
				return String.valueOf(xssfCell.getStringCellValue());
			}
		}
    //取优惠策略
	@Override
	public ExtListDataBo<Cost_FormulaDiscountModel> getFormulaDiscountList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String str, PageBo pageBo)
			throws Exception {
		String sql="select t1.* , " +
					" f_get_fieldtext('COST_FORMULA_DISCOUNT','DISCOUNT_FLAG',t1.discount_flag)discountFlagText "+
					" from cost_formula_discount t1 " +
			       " where t1.warehouse_no ='"+strWarehouseNo+"' " +
					" and t1.enterprise_no = '"+strEnterpriseNo+"' " +
					" and t1.owner_no in("+strWorkerOwner+") ";
			
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
			
		String strTotsql ="select count(*) from (" + sql + ")";

		List<Cost_FormulaDiscountModel> list = genDao.getListByNativeSql(sql,Cost_FormulaDiscountModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_FormulaDiscountModel> extListBo = new ExtListDataBo<Cost_FormulaDiscountModel>(list,intCount);
		return extListBo;
	}
    //取计费项目包含商品群组信息列表
	@Override
	public ExtListDataBo<Cost_FormulaArticlefamilyModel> getFormulaArticlefamilyList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String str, PageBo pageBo) throws Exception {
		String sql="select t1.* ,t2.family_name " +
				" from cost_formula_articlefamily t1,bdef_article_family_m t2 " +
		       " where t1.enterprise_no=t2.enterprise_no " +
		        " and t1.owner_no=t2.owner_no " +
		        " and t1.family_no=t2.family_no " +
		        " and t1.warehouse_no ='"+strWarehouseNo+"' " +
				" and t1.enterprise_no = '"+strEnterpriseNo+"' " +
				" and t1.owner_no in("+strWorkerOwner+") ";
		
	    if(str!=null && !str.equals(""))
	    {
		     String ws=CommUtil.covtCollectionToWhereSql(str);
		     sql=sql+ws;
	    }	
		
	    String strTotsql ="select count(*) from (" + sql + ")";

	    List<Cost_FormulaArticlefamilyModel> list = genDao.getListByNativeSql(sql,Cost_FormulaArticlefamilyModel.class,pageBo.getStart(), pageBo.getPagesize());
	    Integer intCount = genDao.getCountByNativeSql(strTotsql);
	    ExtListDataBo<Cost_FormulaArticlefamilyModel> extListBo = new ExtListDataBo<Cost_FormulaArticlefamilyModel>(list,intCount);
	    return extListBo;
	}
	//取商品群组信息列表
	@Override
	public ExtListDataBo<Cost_FormulaArticlefamilyModel> getFormulaArticlefamily(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner,String falg, String str,String strWhereSql, PageBo pageBo) throws Exception {
		String sql="select t2.* " +
				" from bdef_article_family_m t2 " +
				" where t2.enterprise_no = '"+strEnterpriseNo+"' " +
				" and t2.owner_no in("+strWorkerOwner+") " ;
		if(falg.equals("1")){
			   sql += " and t2.family_no not in(select t1.family_no from cost_formula_articlefamily t1 " +
						"where t1.warehouse_no ='"+strWarehouseNo+"' " +
						" and t1.enterprise_no = '"+strEnterpriseNo+"' " +
						" and t1.owner_no in("+strWorkerOwner+")" ;
			   if(str!=null && !str.equals(""))
			    {
				     String ws=CommUtil.covtCollectionToWhereSql(str);
				     sql=sql+ws;
			    }
						
				sql += " )";
		}		
				
		
		if(strWhereSql!=null && !strWhereSql.equals(""))
	    {
		     String ws=CommUtil.covtCollectionToWhereSql(strWhereSql);
		     sql=sql+ws;
	    }	
		
	    String strTotsql ="select count(*) from (" + sql + ")";

	    List<Cost_FormulaArticlefamilyModel> list = genDao.getListByNativeSql(sql,Cost_FormulaArticlefamilyModel.class,pageBo.getStart(), pageBo.getPagesize());
	    Integer intCount = genDao.getCountByNativeSql(strTotsql);
	    ExtListDataBo<Cost_FormulaArticlefamilyModel> extListBo = new ExtListDataBo<Cost_FormulaArticlefamilyModel>(list,intCount);
	    return extListBo;
	}
	//包含商品群组添加商品群组
	@Override
	public boolean saveFormulaArticlefamilyList(String str) throws Exception {
		Collection<Cost_FormulaArticlefamily> printer=JSONArray.toCollection(JSONArray.fromObject(str),Cost_FormulaArticlefamily.class);
		List<Cost_FormulaArticlefamily> group=(List)printer;
		this.genDao.saveList(group);
		return true;
	}
	//包含商品群组移除商品群组
	@Override
	public boolean deleteFormulaArticlefamilyList(
			String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String strBillingProject,String strFamilyNo)
			throws Exception {
		String wheresql1[]=strWarehouseNo.split(",");
		String wheresql2[]=strOwnerNo.split(",");
		String wheresql3[]=strBillingProject.split(",");
		String wheresql4[]=strFamilyNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete cost_formula_articlefamily a " +
			"where a.warehouse_no='"+wheresql1[i].trim()+"' " +
			"and a.owner_no='"+wheresql2[i].trim()+"' " +
			"and a.billing_project='"+wheresql3[i].trim()+"' " +
			"and a.family_no='"+wheresql4[i].trim()+"' " +
			"and a.enterprise_no='"+strEnterpriseNo+"'";
			genDao.updateBySql(sql);
		}
		return true;
	}
	//保存计费公式-优惠策略
	@Override
	public void saveFormulaDiscount(String str) throws Exception {
		Cost_FormulaDiscount formulaDiscount=(Cost_FormulaDiscount)JSON.parseObject(str,Cost_FormulaDiscount.class);
		genDao.saveOrUpdateObj(formulaDiscount);		
		
	}

	@Override
	public ExtListDataBo<Cost_FormulasetModel> getFormulasetForWindList(
			String strEnterpriseNo, String strWarehouseNo, String workerOwner,
			String str, PageBo pageBo) throws Exception {
		String sql="select '0' as choiceFlag, a.billing_type as billingType,"+
                   "'['|| ltrim(a.billing_type)||']'||a.billing_type_name billingTypeText,"+
                   "case when a.billing_type='ALL' then null else 1 end fixedValue,"+
                   "case when a.billing_type='ALL' then 1 else null end unitPrice,"+
                   "'1' as standardFlagText,'0' as costFlagText,'0' as statusText,"+
                   "0 as otherCost1,0 as otherCost2,0 as otherCost3,"+
                   "0 as otherCost4,0 as otherCost5 "+
                   "from cost_billing_type a ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		String strTotsql ="select count(*) from (" + sql + ")";

		List<Cost_FormulasetModel> list = genDao.getListByNativeSql(sql,Cost_FormulasetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_FormulasetModel> extListBo = new ExtListDataBo<Cost_FormulasetModel>(list,intCount);
		return extListBo;
	}
	//删除计费项目
	@Override
	public MsgRes deleteBillingProject(String strEnterpriseNo,
			String strWarehouseNo, String ownerNo, String billingType,
			String strBillingProject) throws Exception {

		String sql1=" select a.billing_project from cost_expenses_list a " +
				    " where a.enterprise_no='"+strEnterpriseNo+"' " +
				   	" and a.warehouse_no='"+strWarehouseNo+"' " +
				   	" and a.owner_no='"+ownerNo+"' " +
				    " and a.billing_type='"+billingType+"' " +
				    " and a.billing_project='"+strBillingProject+"' ";
		
		List list = genDao.getListByNativeSql(sql1);
		
		if(list.size()>0){
			return new MsgRes(false,"该项目已产生消费清单，不能删除","");
		}
		String sql2=" select a.billing_project from cost_formula_articlefamily a " +
				   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				   	" and a.warehouse_no='"+strWarehouseNo+"' " +
					" and a.owner_no='"+ownerNo+"' " +
				    " and a.billing_type='"+billingType+"' " +
				    " and a.billing_project='"+strBillingProject+"' ";
		
		List list2 = genDao.getListByNativeSql(sql2);
		
		if(list2.size()>0){
			return new MsgRes(false,"该项目已包含商品群组，不能删除","");
		}
		
		String sql3=" select a.billing_project from cost_formula_discount a " +
				   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
					" and a.warehouse_no='"+strWarehouseNo+"' " +
					" and a.owner_no='"+ownerNo+"' " +
				    " and a.billing_type='"+billingType+"' " +
				    " and a.billing_project='"+strBillingProject+"' ";
		
		List list3 = genDao.getListByNativeSql(sql3);
		
		if(list3.size()>0){
			return new MsgRes(false,"该项目已维护优惠策略，不能删除","");
		}
	    String deleteSql="delete from cost_formulaset  a  " +
	    		  "  where a.enterprise_no='"+strEnterpriseNo+"' " +
					" and a.warehouse_no='"+strWarehouseNo+"' " +
					" and a.owner_no='"+ownerNo+"' " +
				    " and a.billing_type='"+billingType+"' " +
				    " and a.billing_project='"+strBillingProject+"' ";
	
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

}
