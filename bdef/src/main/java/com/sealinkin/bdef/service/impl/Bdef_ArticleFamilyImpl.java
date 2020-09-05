package com.sealinkin.bdef.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_ArticleFamilyDModel;
import com.sealinkin.bdef.model.Bdef_ArticleFamilyMModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyD;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyM;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyTmp;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyTmpId;
import com.sealinkin.bdef.service.IBdef_ArticleFamilyService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_ArticleFamilyImpl implements IBdef_ArticleFamilyService{
	
	 private IGenericManager genDao;
	 public IGenericManager getGenDao() {
		 return genDao;
	 }
	 public void setGenDao(IGenericManager genDao) {
		 this.genDao = genDao;
	 }
	 
	 //获取商品群组列表
	@Override
	public ExtListDataBo<Bdef_ArticleFamilyMModel> getArticleFamily_MList(String enterpriseNo,
			String queryStr, PageBo pageBo)
			throws Exception {
		String sql="select a.owner_no,a.family_no,a.family_name from bdef_article_family_m a where " +
				" a.enterprise_no='"+enterpriseNo+"' " ;
		String totsql="select count(1) from bdef_article_family_m a where " +
				" a.enterprise_no='"+enterpriseNo+"' " ;
		
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		
		sql += "order by a.owner_no,a.family_no ";
		List<Bdef_ArticleFamilyMModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_ArticleFamilyMModel> extListBo=null;
	    list = genDao.getListByNativeSql(sql,Bdef_ArticleFamilyMModel.class);
		count = genDao.getCountByNativeSql(totsql);
		
		extListBo= new ExtListDataBo<Bdef_ArticleFamilyMModel>(list, count);
        return extListBo;
	}
	/**
	 * 获取商品资料列
	 */
	@Override
	public ExtListDataBo<Bdef_DefarticleModel> getBdefDefarticleList(
			String enterpriseNo,String strOwnerNo, String strQuery,String str,
			String wheresql,String ownerNo, PageBo poPageBo
		) throws Exception {
	
    	String sql="select a.owner_article_no,a.article_no,a.article_name" +
    			" from bdef_defarticle a " +
    			" where a.enterprise_no='"+enterpriseNo+
    			"' and a.article_no not in (select b.article_no from bdef_article_family_D b) " +
    			" and a.owner_no='"+ownerNo+"' " ;		
    			
    			String totsql = "select count(1) from bdef_defarticle a " +
    			"where  a.enterprise_no='"+enterpriseNo+
    			"' and a.article_no not in (select b.article_no from bdef_article_family_D b) " +
    			" and a.owner_no='"+ownerNo+"' " ;	
    			
    			if(str!=null && !str.equals(""))
    			{
    				String strarticle = " and (a.article_no like '%"+str+"%' " +
    						" or a.owner_article_no like '%"+str+"%' " +
    						" or a.barcode like '%"+str+"%') ";
    				sql=sql+strarticle;
    				totsql=totsql+strarticle;
    			}
    			if(strQuery!=null && !strQuery.equals(""))
    			{
    				System.out.println(strQuery);
    				String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
    				sql=sql+strWs;
    				totsql=totsql+strWs;
    			}
    			sql += "order by a.article_no ";
    			List<Bdef_DefarticleModel> list = null;
    			Integer count = 0;
    			ExtListDataBo<Bdef_DefarticleModel> extListBo=null;
    			list = genDao.getListByNativeSql(sql,Bdef_DefarticleModel.class,poPageBo.getStart(),poPageBo.getPagesize());
    			count = genDao.getCountByNativeSql(totsql);
    			extListBo= new ExtListDataBo<Bdef_DefarticleModel>(list, count);
    			return extListBo;
	}
	
	//保存商品群主
	@Override
	public boolean saveOrUpdateFamily(String str) throws Exception {
		Bdef_ArticleFamilyM bd=(Bdef_ArticleFamilyM)JSONObject.toBean(JSONObject.fromObject(str),Bdef_ArticleFamilyM.class);
		this.genDao.saveOrUpdateObj(bd);
		return true;
	}
	
	//增加商品群组关系列表
	@Override
	public boolean saveArticle_Family(String str)throws Exception{
		Collection<Bdef_ArticleFamilyD> family=JSONArray.toCollection(JSONArray.fromObject(str),Bdef_ArticleFamilyD.class);
		List<Bdef_ArticleFamilyD> articleFamily=(List)family;
		this.genDao.saveList(articleFamily);
		return true;
	}
	
	//获取商品与商品群组列表
	@Override
	public ExtListDataBo<Bdef_ArticleFamilyDModel> getArticleFamily_DList(
			String enterpriseNo,String wheresql,String str, PageBo pageBo)
			throws Exception {
		
		String sql="select a.family_no,a.article_no,b.owner_article_no,b.article_name " +
				"from bdef_article_family_D a,bdef_defarticle b " +
				"where a.article_no=b.article_no " +
				"and a.enterprise_no=b.enterprise_no" +
				" and a.family_no='"+wheresql+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		String totsql="select count(1) from (" + sql +")";
		List<Bdef_ArticleFamilyDModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_ArticleFamilyDModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bdef_ArticleFamilyDModel.class);
		count = genDao.getCountByNativeSql(totsql);
		extListBo= new ExtListDataBo<Bdef_ArticleFamilyDModel>(list, count);
        return extListBo;
	}

	
	//移除商品群组关系列表
	@Override
	public boolean deleteArticle_Family(String enterpriseNo,String ownerNo,
			String familyNo, String articleNo) throws Exception {
		String wheresql1[]=ownerNo.split(",");
		String wheresql2[]=familyNo.split(",");
		String wheresql3[]=articleNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete bdef_article_family_d a " +
			"where a.owner_no='"+wheresql1[i].trim()+"' and " +
			"a.family_no='"+wheresql2[i].trim()+"' and " +
			"a.article_no='"+wheresql3[i].trim()+
			"' and a.enterprise_no='"+enterpriseNo+"'";
			genDao.updateBySql(sql);
		}
		return true;
	}

	
	//校验商品群组
	@Override
	public String checkArticleFamilyNo(String enterpriseNo,String ownerNo, String familyNo)
			throws Exception {
		String sql="select a.family_no from bdef_article_family_m a where a.owner_no='"+ownerNo+
				"' and a.family_no='"+familyNo+
			    "' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//获取商品类别
	@Override
	public List<ComboxBo> getArticleGroupNoCombo(String enterpriseNo,String ownerNo) throws Exception {
		String strSql="select distinct a.group_no value, " +
				"b.group_name text, " +
				"'['|| ltrim(a.group_no)||']'||b.group_name dropValue " +
				"from bdef_defarticle a,bdef_article_group b where a.group_no=b.group_no  "+
				"and a.enterprise_no=b.enterprise_no and a.owner_no=b.owner_no and "+
				"a.enterprise_no = '"+enterpriseNo+"' "+
				"and a.owner_no ='"+ownerNo+"' "+
				"and a.article_no not in (select c.article_no from bdef_article_family_D c) ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//获取商品编码
	@Override
	public List<ComboxBo> getArticleNoForUI(String enterpriseNo,
			String ownerNo, String str,String strQuery)
			throws Exception {
		String strSql="select distinct a.article_no value,a.article_no text,a.article_no dropValue " +
				"from bdef_defarticle a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.owner_no ='"+ownerNo+"' "+
				"and a.article_no not in (select b.article_no from bdef_article_family_D b) ";
		
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql + ws;
		}
		if (str != null && !str.equals("")) {
			strSql = strSql + "and (a.owner_article_no like '%" + str + "%' "+
					"or a.article_no like '%"+ str +"%' " +
					"or a.article_name like '%"+ str +"%') "+
					"order by a.article_no ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	/**
	 * 上传Excel导入数据库
	 */
	@Override
	public MsgRes upLoad(File file, String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo) throws Exception {
		FileUtilSys.writeFile(file, "ArticleFamilyTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		List<Bdef_ArticleFamilyTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"ArticleFamilyTmp.xlsx",strWarehouseNo,strCurrEnterpriseNo, strWorkerNo);
		
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strCurrEnterpriseNo);
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_Create_base.P_bdef_article_family");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功","");
	}
	
	/**
	 * Excel数据转List
	 */
	@Override
	public List<Bdef_ArticleFamilyTmp> changeexcelBean(String fileName,
			String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo) throws Exception {
		List<Bdef_ArticleFamilyTmp> iitList = new ArrayList<Bdef_ArticleFamilyTmp>();
		//导入前删除临时表数据
		String delsql="delete bdef_article_family_tmp a where a.enterprise_no='"+strCurrEnterpriseNo+"' ";
		genDao.updateBySql(delsql);
				
		List<List> excelList = this.impExcel(fileName);

		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Bdef_ArticleFamilyTmp po = new Bdef_ArticleFamilyTmp();
				Bdef_ArticleFamilyTmpId id = new Bdef_ArticleFamilyTmpId();
				id.setEnterpriseNo(strCurrEnterpriseNo);
				id.setOwnerNo(excelList.get(i).get(0).toString());
				id.setFamilyNo(excelList.get(i).get(1).toString());
				id.setFamilyName(excelList.get(i).get(2).toString());
				id.setOwnerArticleNo(excelList.get(i).get(3).toString());
				id.setUseType(excelList.get(i).get(5).toString().equals("")?"0":excelList.get(i).get(5).toString());
				id.setRgstName(strWorkerNo);
				id.setRgstDate(new Date());
				
				id.setStatus("10");
								
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
}
