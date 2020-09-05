package com.sealinkin.odata.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_PackageDModel;
import com.sealinkin.odata.model.Odata_PackageMModel;
import com.sealinkin.odata.po.Odata_PackageTmp;
import com.sealinkin.odata.po.Odata_PackageTmpId;
import com.sealinkin.odata.service.IOdata_PackageService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_PackageImpl implements IOdata_PackageService{
	private IGenericManager genDao;

	
	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	/**
	 * 获取包裹单头档
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_PackageMModel> queryPackageM(
			String enterpriseNo, String strWarehouseNo, String strOwnerNo,
			String strQuery, PageBo poPageBo) throws Exception {
		String sql="select a.enterprise_no, a.warehouse_no,a.owner_no,a.po_no,a.exp_date,a.remark, "
				+" f_get_fieldtext('ODATA_PACKAGE_M','PO_TYPE',a.po_type) as poType,"
				+" f_get_fieldtext('ODATA_PACKAGE_M','STATUS',a.status) as statusText "
				+"from odata_package_m a "
				+"where a.enterprise_no='"+enterpriseNo+"' "
				+"and a.warehouse_no='"+strWarehouseNo+"' "
				//+"and a.status !=13 "
				+"and a.owner_no in ("+strOwnerNo+") "
				/*+"and a.owner_no='"+strOwnerNo+"' "*/;
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		sql = sql+"order by a.status";
		String strTotsql = "select count(*) from ("+sql+") ";
		List<Odata_PackageMModel> list = genDao.getListByNativeSql(sql,Odata_PackageMModel.class,poPageBo.getStart(),100);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_PackageMModel> extListBo= new ExtListDataBo<Odata_PackageMModel>(list, count);
		return extListBo;
	}

	@Override
	/**
	 * 获取包裹单明细
	 * @param strPackageNo
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_PackageDModel> queryPackageD(
			String enterpriseNo, String strWarehouseNo, String strOwnerNo,
			String strPackageNo,String strQuery,PageBo poPageBo) throws Exception {
		// TODO Auto-generated method stub
		String sql="select a.*,b.shipper_name, "
				+" f_get_fieldtext('ODATA_PACKAGE_D','STATUS',a.status) statusText, "
				+"'['|| ltrim(c.owner_no)||']'||c.owner_name ownerName " 
				+"from odata_package_d a,bdef_defshipper b,bdef_defowner c "
				+"where a.enterprise_no='"+enterpriseNo+"' "
				+"and a.warehouse_no='"+strWarehouseNo+"' "
				+"and a.po_no='"+strPackageNo+"' "
				+"and a.owner_no in ("+strOwnerNo+") "
				+"and a.enterprise_no=b.enterprise_no "
				+"and a.warehouse_no=b.warehouse_no "
				+"and a.shipper_no=b.shipper_no "
				+"and a.owner_no=c.owner_no "
				/*+"and a.owner_no='"+strOwnerNo+"' "*/;
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		String strTotsql = "select count(*) from ("+sql+") ";
		List<Odata_PackageDModel> list = genDao.getListByNativeSql(sql,Odata_PackageDModel.class,poPageBo.getStart(),poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_PackageDModel> extListBo= new ExtListDataBo<Odata_PackageDModel>(list, count);
		return extListBo;
	}

	@Override
	public List<ComboxBo> getOwnerCombo(String enterpriseNo,
			String warehouseNo, String strWorkerOwner) throws Exception {
		// TODO Auto-generated method stub
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.owner_no in( " +
				"select owner_no from odata_package_m a " + 
				"where "+
				"a.warehouse_no='"+warehouseNo+"' "+
				"and a.owner_no in ("+strWorkerOwner+") "+
				"and t1.enterprise_no='"+enterpriseNo+"') " +
				" union all select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from dual ";
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
			String warehouseNo, String strOwnerNo, String strQuery)
			throws Exception {
		String strSql="select distinct a.status value,a.status text,"+
				" f_get_fieldtext('ODATA_PACKAGE_M','STATUS',a.status) dropValue "+
				" from odata_package_m a " + 
				"where 1=1 " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"' " +
			     "and a.owner_no in("+strOwnerNo+") ";
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql + ws;
		}
		strSql = strSql+"order by a.status";
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	@Override
	public List<ComboxBo> getPackageInfo(String strEnterpriseNo,
			String strWarehouseNo, String strOwnerNo, String str,
			String strQuery) throws Exception {
		String sql = "select distinct a.po_no value,a.po_no text,"
				+"a.po_no dropValue "
					+ "from odata_package_m a "
					+ " where a.enterprise_no='" + strEnterpriseNo + "' "
					+ " and a.warehouse_no='" + strWarehouseNo + "' "
					+ " and a.owner_no in(" + strOwnerNo + ") ";
					
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + 
					" and (a.po_no like '%"+str+"%' )";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	public MsgRes PackageIntoOrOutStock(String enterpriseNo, String warehouseNo,
			String ownerNo, String poNo, String sourceExpNo,
			String operateWorker, String operateType) throws Exception{
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		outList.add("S");
		List  inList=new ArrayList();
		inList.add(enterpriseNo);//STRENTERPRISENO
		inList.add(warehouseNo);//STRWAREHOUSENO
		inList.add(ownerNo);//STROWNERNO
		inList.add(poNo);//STRPONO
		inList.add(sourceExpNo);//STRSOURCEEXPNO
		inList.add(operateWorker);//STRUpdt_Name
		inList.add(operateType);//STRTYPE
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_odata.p_collectgoods_scansourceexpno");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		System.out.println(resultList.get(0).toString());
		return new MsgRes(true,"操作成功！",null);
	}

	@Override
	public MsgRes printPackageInfo(String enterpriseNo, String warehouseNo,
			String reportType, String sourceNo, String dockNo,
			String paperType, String userId) throws Exception {
		MsgRes msgRes=new MsgRes();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		List  outList1=new ArrayList();
		List  resultList1=new ArrayList();
		outList.add("S");
		outList.add("S");
		outList1.add("S");
		outList1.add("S");
		List  inList=new ArrayList();
		inList.add(enterpriseNo);//STRENTERPRISENO
		inList.add(warehouseNo);//STRWAREHOUSENO
		inList.add(paperType);//strPaperType
		inList.add(reportType);//strReportType
		inList.add(sourceNo);//strSourceNo
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_wms_public.p_GetReportId");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		List  inList1=new ArrayList();
		inList1.add(enterpriseNo);//STRENTERPRISENO
		inList1.add(warehouseNo);//STRWAREHOUSENO
		inList1.add(sourceNo);//strSourceNo
		inList1.add(resultList.get(0).toString());//strreportid
		inList1.add("0");//strbackflag
		inList1.add(dockNo);//strdockno
		inList1.add("0");//strreprintflag
		inList1.add(userId);//struserno
		inList1.add("0");//stradddetailflag
		inList1.add("N");//strcontainerno
		inList1.add(Float.parseFloat("0"));//nserialno
		inList1.add(Float.parseFloat("1"));//nprintqty
		System.out.println(inList1);
		resultList1=genDao.execProc(inList1, outList1, "PKOBJ_PRINTTASK.p_insert_taskinfo");
		System.out.println(resultList1.get(1).toString());
		if(resultList1.get(1).toString().indexOf("N|")!=-1)
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList1.get(1).toString());
			return msgRes; 
		}
	
		msgRes.setIsSucc(true); 
		msgRes.setObj(resultList1.get(0).toString());
		msgRes.setMsg("打印成功");
		return msgRes;
		}

	@Override
	public MsgRes upLoad(File file, String strWarehouseNo,
			String strCurrEnterpriseNo, String strWorkerNo) throws Exception {
		FileUtilSys.writeFile(file, "OdataPackageTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		
		List<Odata_PackageTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"OdataPackageTmp.xlsx",strWarehouseNo,strCurrEnterpriseNo);
			
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strCurrEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strWorkerNo);
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.P_odata_package");

		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功","");
	}

	@Override
	public List<Odata_PackageTmp> changeexcelBean(String fileName,
			String strWarehouseNo, String strEnterpriseNo) throws Exception {
		List<Odata_PackageTmp> iitList = new ArrayList<Odata_PackageTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from odata_package_tmp a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long poNo = System.currentTimeMillis();
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Odata_PackageTmp po = new Odata_PackageTmp();
				Odata_PackageTmpId id = new Odata_PackageTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				
				//id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setOwnerNo(changeType(excelList.get(i).get(1).toString()));
				if(excelList.get(i).get(2).toString().equals("")){
					id.setPoNo(Long.toString(poNo));
				}else{
					id.setPoNo(changeType(excelList.get(i).get(2).toString()));
				}
				id.setPoType(excelList.get(i).get(3).toString());
				if(!excelList.get(i).get(4).toString().equals("")){
					sdf.parse(excelList.get(i).get(4).toString());
				}			
				id.setSourceexpNo(changeType(excelList.get(i).get(5).toString()));	
				id.setShipperDeliverNo(changeType(excelList.get(i).get(6).toString()));
				id.setShipperNo(changeType(excelList.get(i).get(7).toString()));
				id.setCustAddress(changeType(excelList.get(i).get(8).toString()));
				id.setCustPhone(changeType(excelList.get(i).get(9).toString()));
				id.setContactorName(changeType(excelList.get(i).get(10).toString()));
				id.setSendAddress(changeType(excelList.get(i).get(11).toString()));
				id.setSendMobilePhone(changeType(excelList.get(i).get(12).toString()));
				id.setSendName(changeType(excelList.get(i).get(13).toString()));
				
				po.setId(id);
				iitList.add(po);
			}
		}
		return iitList;
	}

	@Override
	public List<List> impExcel(String execelFile) throws Exception {
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
				// 总共有多少列,从0开始
				for (int j = row.getFirstCellNum(); j < totalCells; j++) {
					// 处理空列
					if (row.getCell(j) == null) {
						object.add("");
						continue;
					}
					//获取单元格内容
					try 
					{							
						cell = this.getValue((HSSFCell) row.getCell(j),0);									
					}catch (Exception e) 
					{   
						cell = row.getCell(j).toString();
					}
					object.add(cell.trim());
					
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
	private String getValue(HSSFCell xssfCell, int length) {
		String style = "0";
		for(int i=0; i<length;i++){
			if(i==0){
				style=style+".";
			}
			style=style+"0";
		}
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat(style);
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
	
	public String changeType(String var) throws Exception {
		String result="";
		try{	
			Float temp =Float.parseFloat(var);
			int tempInt =temp.intValue();
			if(var.charAt(0)=='0'){
				result=var;
			}else{
				if(var.length()!=String.valueOf(temp).length()){
				
					result=var;
				}else{
					result=String.valueOf(tempInt);	
				}	
			}		
		}catch(Exception e){
			result=var;
		}		
		return result;
	}

}
