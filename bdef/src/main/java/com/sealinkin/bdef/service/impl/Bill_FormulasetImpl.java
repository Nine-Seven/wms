/**
 * 模块名称：计费公式管理实现
 * 模块编码：B101
 * 创建：chensr 
 */
package com.sealinkin.bdef.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBill_FormulasetService;
import com.sealinkin.bset.model.Bill_FormulasetModel;
import com.sealinkin.bset.po.Bill_FamilyUnitPrice;
import com.sealinkin.bset.po.Bill_Formulaset;
import com.sealinkin.bset.po.Bill_FormulasetTmp;
import com.sealinkin.bset.po.Bill_FormulasetTmpId;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.po.Idata_ImportTmp;
import com.sealinkin.idata.po.Idata_ImportTmpId;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bill_FormulasetImpl implements IBill_FormulasetService {
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

		Bill_Formulaset formulaset=(Bill_Formulaset)JSON.parseObject(str,Bill_Formulaset.class);
		genDao.saveOrUpdateObj(formulaset);		
	}
	//获取计费公式
	@Override
	public ExtListDataBo<Bill_FormulasetModel> getFormulasetList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception {
		String sql=" select t1.*, case when t1.family_no is null then null else "+
         " '['|| ltrim(t1.family_no)||']'||t3.family_name end familyText, " +
         "case when t1.billing_cycle in('3') then t1.balance_day else "+
		 " f_get_fieldtext('BILL_FORMULASET','BALANCE_DAY',t1.balance_day) end balanceDayText " +
         " from (select t.* , t2.family_no, " +
				" '['|| ltrim(t.billing_project)||']'||t.project_name projectText, " +
				" f_get_fieldtext('BILL_FORMULASET','BILLING_CYCLE',t.billing_cycle)billingCycleText, "+
				" f_get_fieldtext('BILL_FORMULASET','BILLING_FLAG',t.billing_flag)billingFlagText, "+
				" f_get_fieldtext('BILL_FORMULASET','BILLING_UNIT',t.billing_unit)billingUnitText, "+
				" '['|| ltrim(t.billing_type)||']'||f_get_fieldtext('BILL_FORMULASET','BILLING_TYPE',t.billing_type)billingTypeText, "+
				" f_get_fieldtext('BILL_FORMULASET','APPEND_CONDITION',t.append_condition)appendConditionText， "+
				" f_get_fieldtext('BILL_FORMULASET','STATUS',t.Status)statusText, " +
				" to_char(t.end_date,'yyyy-mm-dd') endDateText " +
				" from bill_formulaset t ,bill_family_unit_price t2 " +
				"where 1=1 " +
				" and t.enterprise_no=t2.enterprise_no(+) " +
				" and t.warehouse_no=t2.warehouse_no(+) " +
				" and t.owner_no=t2.owner_no(+) " +
				" and t.billing_project=t2.billing_project(+) )t1," +
				" bdef_article_family_m t3 " +
		      " where t1.enterprise_no=t3.enterprise_no（+） " +
				" and t1.owner_no=t3.owner_no(+) " +
				" and t1.family_no=t3.family_no(+) "+
				" and t1.warehouse_no ='"+warehouseNo+"' " +
				" and t1.enterprise_no = '"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
		}
		String strTotsql ="select count(*) from (" + sql + ")";

		List<Bill_FormulasetModel> list = genDao.getListByNativeSql(sql,Bill_FormulasetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bill_FormulasetModel> extListBo = new ExtListDataBo<Bill_FormulasetModel>(list,intCount);
		return extListBo;
	}
	//获取用于查找的货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
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

	//获取UI界面的项目名称
	@Override
	public List<ComboxBo> getBillingProjectForUI(String enterpriseNo,String warehouseNo,String str) {
		String strSql="select distinct t1.billing_project value,t1.project_name text," +
				 "'['|| ltrim(t1.billing_project)||']'||t1.project_name dropValue " +
				"from bill_formulaset t1 where 1=1  " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no='"+warehouseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	//判断计费项目编号是否唯一
	@Override
	public List<String> billingProjectCheck(
			String enterpriseNo,String str,String warehouseNo,String ownerNo) throws Exception {
		String strSql="select t1.billing_project "+
				"from bill_formulaset t1 where 1=1  "+
				"and t1.billing_project='"+str+
				"' and t1.warehouse_no='"+warehouseNo+
				"' and t1.owner_no='"+ownerNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	@Override
	public List<ComboxBo> getValueFlagCombo(String enterpriseNo,String billingType,
			String billingUnit) {
		String strSql="	select distinct t.rule_id value ,  t.strategy_name text," +
				 "'['|| ltrim(t.rule_id)||']'||t.strategy_name dropValue" +
				 " from WMS_Billing_RULE t where 1=1  " +
			     " and t.rule_id is not null "+
				 " and t.billing_unit='"+billingUnit+
				 "' and t.billing_type='"+billingType+
				 "' and t.enterprise_no='"+enterpriseNo+"' ";
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	@Override
	public List<String> getRemarkCombo(String billingType, String billingUnit,
			String ruleId) {
		String strSql="select  t.strategy_name  from WMS_Billing_RULE t " +
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
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
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
}
