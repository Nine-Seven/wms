package com.sealinkin.bdef.service.impl;

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

import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.bdef.model.Bdef_UploadfileModel;
import com.sealinkin.bdef.po.Bdef_Uploadfile;
import com.sealinkin.bdef.po.Bdef_UploadfileId;
import com.sealinkin.bdef.po.Tmp_Formexcel_Article;
import com.sealinkin.bdef.po.Tmp_Formexcel_ArticleId;
import com.sealinkin.bdef.po.Tmp_Formexcel_Article_Group;
import com.sealinkin.bdef.po.Tmp_Formexcel_Article_GroupId;
import com.sealinkin.bdef.po.Tmp_Formexcel_Cust;
import com.sealinkin.bdef.po.Tmp_Formexcel_CustId;
import com.sealinkin.bdef.po.Tmp_Formexcel_Owner;
import com.sealinkin.bdef.po.Tmp_Formexcel_OwnerId;
import com.sealinkin.bdef.po.Tmp_Fromexcel_Supplier;
import com.sealinkin.bdef.po.Tmp_Fromexcel_SupplierId;
import com.sealinkin.bdef.po.Tmp_Fromexcelcsetcell;
import com.sealinkin.bdef.po.Tmp_FromexcelcsetcellId;
import com.sealinkin.bdef.service.IDataImportService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;
@SuppressWarnings({"unchecked","rawtypes","unused","static-access"})
public class DataImportImpl implements IDataImportService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public MsgRes tscDataImport(String strDataType,File file, 
			String strWarehouseNo, String strWorkerNo,String strEnterpriseNo) throws Exception {
		String strExcName="";
		if("gro".equals(strDataType)){//商品类别导入
			strExcName="groTmp.xlsx";
		}else if("art".equals(strDataType)){//商品导入
			strExcName="artTmp.xlsx";
		}else if("cus".equals(strDataType)){//客户导入
			strExcName="cusTmp.xlsx";
		}else if("sup".equals(strDataType)){//供应商导入
			strExcName="supTmp.xlsx";
		}else if("cell".equals(strDataType)){//商品与货位对于关系
			strExcName="cellTmp.xlsx";
		}else if("owner".equals(strDataType)){//货主资料导入
			strExcName="ownerTmp.xlsx";
		}else if("artName".equals(strDataType)){//商品导入（商品名称拼为货主商品编码+商品名称）
			strExcName="artNameTmp.xlsx";
		}
		
		FileUtilSys.writeFile(file, strExcName, ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		
		if("gro".equals(strDataType)){
			List<Tmp_Formexcel_Article_Group> list  = changeexcelBeanGro(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertGro(strEnterpriseNo,strWorkerNo);
		}else if("art".equals(strDataType)){
			List<Tmp_Formexcel_Article> list  = changeexcelBeanArt(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertArt(strEnterpriseNo,strWorkerNo);
		}else if("cus".equals(strDataType)){
			List<Tmp_Formexcel_Cust> list  = changeexcelBeanCus(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertCus(strEnterpriseNo,strWorkerNo);
		}else if("sup".equals(strDataType)){
			List<Tmp_Fromexcel_Supplier> list  = changeexcelBeanSup(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertSup(strEnterpriseNo,strWorkerNo);//写真实表
		}else if("cell".equals(strDataType)){
			List<Tmp_Fromexcelcsetcell> list  = changeexcelBeanCell(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo,strWarehouseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertCell(strEnterpriseNo,strWarehouseNo,strWorkerNo);
		}else if("owner".equals(strDataType)){
			List<Tmp_Formexcel_Owner> list = changeExcelBeanOwner(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertOwner(strEnterpriseNo,strWorkerNo);
		}else if("artName".equals(strDataType)){
			List<Tmp_Formexcel_Article> list  = changeexcelBeanArt(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+strExcName,strWorkerNo,strEnterpriseNo);
			genDao.saveList(list);
			genDao.flush();
			trsInsertArt_name(strEnterpriseNo,strWorkerNo);
		}
		
		return new MsgRes(true,"导入成功！","");
	}
	
	private MsgRes trsInsertGro(String strEnterpriseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(new Date());
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_Create_base.P_insertArticleGroup");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}
	private List<Tmp_Formexcel_Article_Group> changeexcelBeanGro(String fileName,
			String strWorkerNo,String strEnterpriseNo) throws Exception {
		List<Tmp_Formexcel_Article_Group> iitList = new ArrayList<Tmp_Formexcel_Article_Group>();
		//删除临时表的数据
		String sql="delete tmp_formexcel_article_group";
		genDao.updateBySql(sql);
				
		List<List> excelList = this.impExcel(fileName);
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Tmp_Formexcel_Article_Group po=new Tmp_Formexcel_Article_Group();
				Tmp_Formexcel_Article_GroupId id=new Tmp_Formexcel_Article_GroupId();
				id.setEnterpriseNo(strEnterpriseNo);
				id.setOwnerNo(excelList.get(i).get(0).toString());	
				id.setLGroupNo(excelList.get(i).get(1).toString());
				id.setLGroupName(excelList.get(i).get(2).toString());
				id.setMGroupNo(excelList.get(i).get(3).toString());
				id.setMGroupName(excelList.get(i).get(4).toString());
				id.setGroupNo (excelList.get(i).get(5).toString());
				id.setGroupName(excelList.get(i).get(6).toString());
				
				id.setRowId(i+2.0);
				id.setStatus("10");		
				id.setRgstName(strWorkerNo);
				id.setRgstDate(new Date());
				id.setOperateDate(new Date());
				
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	
	private MsgRes trsInsertArt(String strEnterpriseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(new Date());
		inList.add("1");//导入标识  1：普通   2：货主编码+商品名称
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertdefarticle");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}	
	
	private MsgRes trsInsertArt_name(String strEnterpriseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(new Date());
		inList.add("2");//导入标识  1：普通   2：货主编码+商品名称
		inList.add(strWorkerNo);
		//System.out.println(inList+"==================================");
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertdefarticle");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}	
	
	private List<Tmp_Formexcel_Article> changeexcelBeanArt(String fileName,
			String strWorkerNo,String strEnterpriseNo) throws Exception {
		List<Tmp_Formexcel_Article> iitList = new ArrayList<Tmp_Formexcel_Article>();
		//删除临时表的数据
		String sql="delete tmp_formexcel_article";
		genDao.updateBySql(sql);
		
		List<List> excelList = this.impExcelArt(fileName);
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Tmp_Formexcel_Article po=new Tmp_Formexcel_Article();
				Tmp_Formexcel_ArticleId id=new Tmp_Formexcel_ArticleId();
				id.setEnterpriseNo(strEnterpriseNo);
				id.setOwnerNo((excelList.get(i).get(0).toString()));	
				id.setGroupNo(excelList.get(i).get(1).toString());
				id.setGroupName(excelList.get(i).get(2).toString());
				id.setSupplierNo(excelList.get(i).get(3).toString().equals("")?"":excelList.get(i).get(3).toString());
				id.setOwnerArticleNo(excelList.get(i).get(4).toString().equals("")?"":excelList.get(i).get(4).toString());
				id.setArticleName(excelList.get(i).get(5).toString());
				id.setArticleIdentifier(excelList.get(i).get(6).toString());
				id.setUnit(excelList.get(i).get(7).toString());
				//获取基本包装数量
				id.setUnitPacking(Double.parseDouble(excelList.get(i).get(8).toString()));
				id.setSpec(excelList.get(i).get(9).toString());
				//获取最小操作包装单位
				id.setQminOperatePackingUnit(excelList.get(i).get(10).toString().equals("")?excelList.get(i).get(7).toString():excelList.get(i).get(10).toString());
				id.setQminOperatePacking(Double.parseDouble(excelList.get(i).get(11).toString().equals("")?excelList.get(i).get(8).toString():excelList.get(i).get(11).toString()));
				
				id.setLotType(excelList.get(i).get(12).toString());
				id.setExpiryDays(Double.parseDouble(excelList.get(i).get(13).toString()));
				
				//商品导入根据包装材积、重量自动试算单位材积和单位重量
				//1、如果包装材积、重量(长宽高)为0则按照模板上单位材积和单位重量填写
				//2、如果不为0则试算
				if(Double.parseDouble(excelList.get(i).get(24).toString())==0){
					id.setUnitVolumn(Double.parseDouble(excelList.get(i).get(14).toString()));
				}else{
					id.setUnitVolumn(Double.parseDouble(excelList.get(i).get(24).toString())*Double.parseDouble(excelList.get(i).get(25).toString())*Double.parseDouble(excelList.get(i).get(23).toString())/Double.parseDouble(excelList.get(i).get(19).toString().equals("")?"1":excelList.get(i).get(19).toString()));
				}
				//重量
				if(Double.parseDouble(excelList.get(i).get(27).toString())==0){
					id.setUnitWeight(Double.parseDouble(excelList.get(i).get(15).toString()));
				}else{
					id.setUnitWeight(Double.parseDouble(excelList.get(i).get(27).toString())/Double.parseDouble(excelList.get(i).get(19).toString().equals("")?"1":excelList.get(i).get(19).toString()));
				}

				id.setBarcode(excelList.get(i).get(16).toString().equals("")?"":excelList.get(i).get(16).toString());
				
				id.setPackingSpec(excelList.get(i).get(17).toString().equals("")?"":excelList.get(i).get(17).toString());
				id.setPackingUnit(excelList.get(i).get(18).toString().equals("")?"":excelList.get(i).get(18).toString());
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(19).toString().equals("")?"0":excelList.get(i).get(19).toString()));
				id.setBoxNo(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(20).toString());
				id.setScanFlag(excelList.get(i).get(21).toString());
				
				id.setPalBaseQbox(Double.parseDouble(excelList.get(i).get(22).toString()));
				id.setPalHeightQbox(Double.parseDouble(excelList.get(i).get(23).toString()));
			
				id.setALength(Double.parseDouble(excelList.get(i).get(24).toString()));
				id.setAWidth(Double.parseDouble(excelList.get(i).get(25).toString()));
				id.setAHeight(Double.parseDouble(excelList.get(i).get(26).toString()));
				id.setAWeight(Double.parseDouble(excelList.get(i).get(27).toString()));
				id.setTemperatureFlag(excelList.get(i).get(28).toString().equals("")?"0":excelList.get(i).get(28).toString());
				//add by czh 2016.8.15
				id.setRuleFlag(excelList.get(i).get(29).toString().equals("")?"1":excelList.get(i).get(29).toString());
				
				id.setRsvAttr1(excelList.get(i).get(30).toString().equals("")?"":excelList.get(i).get(31).toString());
				id.setRsvAttr2(excelList.get(i).get(31).toString().equals("")?"":excelList.get(i).get(31).toString());
				id.setRsvAttr3(excelList.get(i).get(32).toString().equals("")?"":excelList.get(i).get(32).toString());
				id.setRsvAttr4(excelList.get(i).get(33).toString().equals("")?"":excelList.get(i).get(33).toString());
				id.setRsvAttr5(excelList.get(i).get(34).toString().equals("")?"":excelList.get(i).get(34).toString());
				id.setRsvAttr6(excelList.get(i).get(35).toString().equals("")?"":excelList.get(i).get(35).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(36).toString().equals("")?"0":excelList.get(i).get(36).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(37).toString().equals("")?"0":excelList.get(i).get(37).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(38).toString().equals("")?"0":excelList.get(i).get(38).toString()));
                
				//id.setBasePacking(Double.parseDouble(excelList.get(i).get(35).toString().equals("")?"0":excelList.get(i).get(35).toString()));
                //id.setqminUnit(excelList.get(i).get(36).toString());

				
				id.setStatus("10");		
				id.setRowId(i+2.0);
				id.setRgstName(strWorkerNo);
				id.setRgstDate(new Date());
				id.setOperateDate(new Date());
				
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	
	private MsgRes trsInsertCus(String strEnterpriseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertdefcust");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception("第"+resultList.get(0).toString()+"条数据有误:"+resultList.get(1).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}	
	private List<Tmp_Formexcel_Cust> changeexcelBeanCus(String fileName,
			String strWorkerNo,String strEnterpriseNo) throws Exception {
		List<Tmp_Formexcel_Cust> iitList = new ArrayList<Tmp_Formexcel_Cust>();
		//删除临时表的数据
		String sql="delete tmp_formexcel_cust a where a.enterprise_no='"+strEnterpriseNo+"' ";
		genDao.updateBySql(sql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Tmp_Formexcel_Cust po=new Tmp_Formexcel_Cust();
				Tmp_Formexcel_CustId id=new Tmp_Formexcel_CustId();
				
				id.setEnterpriseNo(strEnterpriseNo);
				id.setRowId(i+1.0);
				id.setStatus("10");
				id.setOwnerNo(excelList.get(i).get(0).toString());
				id.setOwnerCustNo(excelList.get(i).get(1).toString());
				id.setCustName(excelList.get(i).get(2).toString());
				id.setCustAlias(excelList.get(i).get(3).toString());
				id.setCustAddress(excelList.get(i).get(4).toString());
				id.setDeliveryAddress(excelList.get(i).get(5).toString());
				id.setContactorName1(excelList.get(i).get(6).toString());
				id.setCustPhone1(excelList.get(i).get(7).toString());
				id.setCustProvince(excelList.get(i).get(8).toString());
				id.setCustCity(excelList.get(i).get(9).toString());
				id.setCustZone(excelList.get(i).get(10).toString());
				id.setControlType(excelList.get(i).get(11).toString());
				id.setControlValue(Integer.parseInt(excelList.get(i).get(12).toString().equals("")?"0":excelList.get(i).get(12).toString()));
				
				id.setRsvVarod1(excelList.get(i).get(13).toString().equals("")?"":excelList.get(i).get(13).toString());
				id.setRsvVarod2(excelList.get(i).get(14).toString().equals("")?"":excelList.get(i).get(14).toString());
				id.setRsvVarod3(excelList.get(i).get(15).toString().equals("")?"":excelList.get(i).get(15).toString());
				id.setRsvVarod4(excelList.get(i).get(16).toString().equals("")?"":excelList.get(i).get(16).toString());
				id.setRsvVarod5(excelList.get(i).get(17).toString().equals("")?"":excelList.get(i).get(17).toString());
				id.setRsvVarod6(excelList.get(i).get(18).toString().equals("")?"":excelList.get(i).get(18).toString());
				id.setRsvVarod7(excelList.get(i).get(19).toString().equals("")?"":excelList.get(i).get(19).toString());
				id.setRsvVarod8(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(20).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(21).toString().equals("")?"0":excelList.get(i).get(21).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(22).toString().equals("")?"0":excelList.get(i).get(22).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(23).toString().equals("")?"0":excelList.get(i).get(23).toString()));
				/*id.setRsvDate1(sdf.parse(excelList.get(i).get(24).toString().equals("")?"1900-01-01":excelList.get(i).get(24).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(25).toString().equals("")?"1900-01-01":excelList.get(i).get(25).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(26).toString().equals("")?"1900-01-01":excelList.get(i).get(26).toString()));*/
								
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	private MsgRes trsInsertSup(String strEnterpriseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertdefsupplier");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception("第"+resultList.get(0).toString()+"条数据有误:"+resultList.get(1).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}		
	private List<Tmp_Fromexcel_Supplier> changeexcelBeanSup(String fileName,
			String strWorkerNo,String strEnterpriseNo) throws Exception {
		List<Tmp_Fromexcel_Supplier> iitList = new ArrayList<Tmp_Fromexcel_Supplier>();
		
		//删除临时表的数据
		String sql="delete tmp_fromexcel_supplier";
		genDao.updateBySql(sql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Tmp_Fromexcel_Supplier po=new Tmp_Fromexcel_Supplier();
				Tmp_Fromexcel_SupplierId id=new Tmp_Fromexcel_SupplierId();
				
				id.setEnterpriseNo(strEnterpriseNo);
				id.setStatus("10");
				id.setRowId(i+1.0);
				
				id.setOwnerNo(excelList.get(i).get(0).toString());
				id.setSupplierNo(excelList.get(i).get(1).toString());			
				id.setSupplierName(excelList.get(i).get(2).toString());				
				id.setSupplierAddress1(excelList.get(i).get(3).toString().equals("")?"N":excelList.get(i).get(3).toString());				
				id.setSupplier1(excelList.get(i).get(4).toString().equals("")?"N":excelList.get(i).get(4).toString());
				id.setSupplierPhone1(excelList.get(i).get(5).toString().equals("")?"N":excelList.get(i).get(5).toString());	
				id.setUnloadFlag(Integer.parseInt(excelList.get(i).get(6).toString()));
					
				//7-8添加
				id.setRsvVarod1(excelList.get(i).get(7).toString().equals("")?"":excelList.get(i).get(7).toString());
				id.setRsvVarod2(excelList.get(i).get(8).toString().equals("")?"":excelList.get(i).get(8).toString());
				id.setRsvVarod3(excelList.get(i).get(9).toString().equals("")?"":excelList.get(i).get(9).toString());
				id.setRsvVarod4(excelList.get(i).get(10).toString().equals("")?"":excelList.get(i).get(10).toString());
				id.setRsvVarod5(excelList.get(i).get(11).toString().equals("")?"":excelList.get(i).get(11).toString());
				id.setRsvVarod6(excelList.get(i).get(12).toString().equals("")?"":excelList.get(i).get(12).toString());
				id.setRsvVarod7(excelList.get(i).get(13).toString().equals("")?"":excelList.get(i).get(13).toString());
				id.setRsvVarod8(excelList.get(i).get(14).toString().equals("")?"":excelList.get(i).get(14).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(15).toString().equals("")?"0":excelList.get(i).get(15).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(16).toString().equals("")?"0":excelList.get(i).get(16).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(17).toString().equals("")?"0":excelList.get(i).get(17).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(18).toString().equals("")?"1900-01-01":excelList.get(i).get(18).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(19).toString().equals("")?"1900-01-01":excelList.get(i).get(19).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(20).toString().equals("")?"1900-01-01":excelList.get(i).get(20).toString()));
				
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	
	private List<Tmp_Fromexcelcsetcell> changeexcelBeanCell(String fileName,
			String strWorkerNo,String strEnterpriseNo,String strWarehouseNo) throws Exception {
		List<Tmp_Fromexcelcsetcell> iitList = new ArrayList<Tmp_Fromexcelcsetcell>();
		//删除临时表的数据
		String sql="delete tmp_fromexcelcsetcell";
		genDao.updateBySql(sql);
		
		List<List> excelList = this.impExcelArtCell(fileName);
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(1).toString()!=null && !excelList.get(i).get(1).toString().equals("")){
				Tmp_Fromexcelcsetcell po=new Tmp_Fromexcelcsetcell();
				Tmp_FromexcelcsetcellId id=new Tmp_FromexcelcsetcellId();
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				id.setStatus("10");	
				id.setRowId(i+1.0);
				
				id.setOwnerNo(excelList.get(i).get(0).toString());	
				id.setBarcode(excelList.get(i).get(1).toString());//货主商品编码
				id.setArticleName(excelList.get(i).get(2).toString());				
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(3).toString()));
				id.setCellNo(excelList.get(i).get(4).toString());
				id.setMaxQty(Double.parseDouble(excelList.get(i).get(5).toString()));
				id.setAlertQty(Double.parseDouble(excelList.get(i).get(6).toString()));
				id.setKeepCells(Double.parseDouble(excelList.get(i).get(7).toString()));
				id.setLineId(Double.parseDouble(excelList.get(i).get(8).toString().equals("")?"-1":excelList.get(i).get(8).toString()));
				
				id.setOperateDate(new Date());				
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	
	private MsgRes trsInsertCell(String strEnterpriseNo,String strWarehouseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strWorkerNo);
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertCsetCellArticle");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}	
	
	//货主导入
	private MsgRes trsInsertOwner(String strEnterpriseNo,String strWorkerNo) throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWorkerNo);
		System.out.println("in: " + inList.toString());
		
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertOwner");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception("第"+resultList.get(0).toString()+"条数据有误:"+resultList.get(1).toString());
		}
		return new MsgRes(true,"导入成功！","");
	}
	
	//excel数据转换（货主）
	private List<Tmp_Formexcel_Owner> changeExcelBeanOwner(String fileName,
			String strWorkerNo,String strEnterpriseNo) throws Exception {
		List<Tmp_Formexcel_Owner> iitList = new ArrayList<Tmp_Formexcel_Owner>();
		//删除临时表的数据
		String sql="delete from tmp_formexcel_owner a where a.enterprise_no='"+strEnterpriseNo+"' ";
		genDao.updateBySql(sql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Tmp_Formexcel_Owner po=new Tmp_Formexcel_Owner();
				Tmp_Formexcel_OwnerId id=new Tmp_Formexcel_OwnerId();
				id.setEnterpriseNo(strEnterpriseNo);
				id.setRowId(i+1.0);
				id.setStatus("10");
				
				id.setOwnerNo(excelList.get(i).get(0).toString());
				id.setOwnerName(excelList.get(i).get(1).toString());
				id.setOwnerAlias(excelList.get(i).get(2).toString());
				id.setOwnerAddress(excelList.get(i).get(3).toString());
				id.setOwnerPhone(excelList.get(i).get(4).toString());
				id.setOwnerFax(excelList.get(i).get(5).toString());		
				id.setOwnerContact(excelList.get(i).get(6).toString());
				id.setAuthorityType(excelList.get(i).get(7).toString());
				id.setCellManagerType(excelList.get(i).get(8).toString());
				id.setTypeValue(excelList.get(i).get(9).toString());
				
				//7-18添加
				id.setRsvVarod1(excelList.get(i).get(10).toString().equals("")?"":excelList.get(i).get(10).toString());
				id.setRsvVarod2(excelList.get(i).get(11).toString().equals("")?"":excelList.get(i).get(11).toString());
				id.setRsvVarod3(excelList.get(i).get(12).toString().equals("")?"":excelList.get(i).get(12).toString());
				id.setRsvVarod4(excelList.get(i).get(13).toString().equals("")?"":excelList.get(i).get(13).toString());
				id.setRsvVarod5(excelList.get(i).get(14).toString().equals("")?"":excelList.get(i).get(14).toString());
				id.setRsvVarod6(excelList.get(i).get(15).toString().equals("")?"":excelList.get(i).get(15).toString());
				id.setRsvVarod7(excelList.get(i).get(16).toString().equals("")?"":excelList.get(i).get(16).toString());
				id.setRsvVarod8(excelList.get(i).get(17).toString().equals("")?"":excelList.get(i).get(17).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(18).toString().equals("")?"0":excelList.get(i).get(18).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(19).toString().equals("")?"0":excelList.get(i).get(19).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(20).toString().equals("")?"0":excelList.get(i).get(20).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(21).toString().equals("")?"1900-01-01":excelList.get(i).get(21).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(22).toString().equals("")?"1900-01-01":excelList.get(i).get(22).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(23).toString().equals("")?"1900-01-01":excelList.get(i).get(23).toString()));
						
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	
	private List<List> impExcel(String execelFile) throws Exception {
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
					continue;
				}
				
				for (int j = row.getFirstCellNum(); j < totalCells; j++) {
					// 处理空列
					if (row.getCell(j) == null) {
						object.add("");
						continue;
					}
					// 通过 row.getCell(j).toString() 获取单元格内容
					try 
					{
						cell = this.getValue((HSSFCell) row.getCell(j),0);
					}catch (Exception e) 
					{
						cell = row.getCell(j).toString();
					}
					object.add(cell.trim());
					System.out.print(cell + "\t");
				}
				inputlist.add(object);
				System.out.println("");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return inputlist;
	}
	
	private List<List> impExcelArt(String execelFile) throws Exception {
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
						System.out.println(1111);
						if(j==8 || j==11 || j==19 || j==25 || j==22 || j==23 || j==24 || j==36 || j==37 || j==38){	
							System.out.println("1111——1");
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							System.out.println("1111——2");
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
					}catch (Exception e) 
					{   System.out.println("1111——3");
						cell = row.getCell(j).toString();
					}
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
	
	private List<List> impExcelArtCell(String execelFile) throws Exception {
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
						if(j==3 || j==5 || j==6){	
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
					}catch (Exception e) 
					{   
						cell = row.getCell(j).toString();
					}
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
	@Override
	public MsgRes upload(File file,String fileName, String currEnterpriseNo,
			String warehouseNo, String workerNo) throws Exception {
		
		Bdef_Uploadfile buf= new Bdef_Uploadfile();
		Bdef_UploadfileId bui = new Bdef_UploadfileId();
		
		bui.setEnterpriseNo(currEnterpriseNo);
		bui.setWarehouseNo(warehouseNo);
		bui.setFileName(fileName);
		
		buf.setId(bui);
		buf.setRgstName(workerNo);
		buf.setRgstDate(new Date());
		buf.setFilePath("uploadFiles"+System.getProperty ("file.separator")+"file"+System.getProperty ("file.separator")+fileName);
		
		genDao.saveOrUpdateObj(buf);
		
		FileUtilSys.writeFile(file, fileName, ContextUtil.getWebRootPath()+"uploadFiles"+System.getProperty ("file.separator")+"file"+System.getProperty ("file.separator"));
		return new MsgRes(true,"上传成功！","");
	}
	@Override
	public List<ComboxBo> getDownloadList(String currEnterpriseNo,
			String warehouseNo) throws Exception {
			
		String strSql=" select a.file_path as value,a.file_name text, " +
				      "     a.file_name as dropValue " +
				      "   from  bdef_uploadfile a" +
				      "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				     // "    and a.warehouse_no='"+warehouseNo+"' " +
				      "  order by a.file_name " ;
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes deleteFile(String currEnterpriseNo, String warehouseNo,
			String filePath) throws Exception {
		String sql="select * from bdef_uploadfile a " +
				 "   where a.enterprise_no='"+currEnterpriseNo+"' " +
				 "     and a.warehouse_no='"+warehouseNo+"' " +
			     "     and a.file_path='"+filePath+"' ";
		
		List<Bdef_UploadfileModel> list=genDao.getListByNativeSql(sql,Bdef_UploadfileModel.class);
	   
		File file = new File(ContextUtil.getWebRootPath()+"uploadFiles"+System.getProperty ("file.separator")+"file"+System.getProperty ("file.separator")+list.get(0).getFileName());
		if (file.isFile() && file.exists()) {  
	        file.delete();  
		}
		
		sql =" delete from  bdef_uploadfile a " +
		     "  where a.enterprise_no='"+currEnterpriseNo+"' " +
			 "    and a.warehouse_no='"+warehouseNo+"' " +
		     "    and a.file_path='"+filePath+"' ";
		genDao.exceuteSql(sql);
		return new MsgRes(true,"","");
	}
	
/*	
	
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
	}*/
}

	
