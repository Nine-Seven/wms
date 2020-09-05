package com.sealinkin.sodata.service.impl;

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
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.sodata.model.Sodata_WasteDModel;
import com.sealinkin.sodata.model.Sodata_WasteMModel;
import com.sealinkin.sodata.po.SodataWasteTmp;
import com.sealinkin.sodata.po.SodataWasteTmpId;
import com.sealinkin.sodata.po.Sodata_WasteD;
import com.sealinkin.sodata.po.Sodata_WasteM;
import com.sealinkin.sodata.service.ISodata_WasteService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;
/**
 * 报损中心
 * @author hekangli
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Sodata_WasteImpl implements ISodata_WasteService{
	
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 报损手建单M
	 */
	@Override
	public ExtListDataBo<Sodata_WasteMModel> getWaste_MList(String currEnterpriseNo,String warehouseNo,String workerOwner,
			String queryStr,PageBo pageBo) throws Exception {
		String sql="select a.enterprise_no,a.warehouse_no," +
				"a.owner_no,a.waste_type,a.waste_no," +
				"a.po_no,a.waste_date,a.status,a.create_flag," +
				"a.send_flag,a.org_no,	f_get_fieldtext('N','ORG_NO',a.Org_No)OrgNoText," +
				"f_get_fieldtext('SODATA_WASTE_M','STATUS',a.status)statusText  " +
				"from sodata_waste_m a " +
				"where a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+currEnterpriseNo+"' and a.status<'13' ";
		String totsql="select count(1) " +
				"from sodata_waste_m a " +
				"where a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+currEnterpriseNo+"' and a.status<'13'  ";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+")";
			totsql=totsql+" and a.owner_no in("+workerOwner+")";
		}else{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
		sql=sql+" order by a.waste_type,a.waste_no desc ";
		List<Sodata_WasteMModel> list = genDao.getListByNativeSql(sql,Sodata_WasteMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Sodata_WasteMModel> extListBo= new ExtListDataBo<Sodata_WasteMModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 报损手建单库存
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ExtListDataBo<Stock_ContentModel> getSodataWasteList(
			String currEnterpriseNo, String warehouseNo, String wheresql,
			PageBo pageBo) throws Exception {
		String sql ="select D.WASTE_NO labelNo," +
				"   D.ARTICLE_NO,AR.OWNER_ARTICLE_NO,d.packing_qty," +
				"   AR.ARTICLE_NAME, SUM(D.WASTE_QTY) recedeQty,"+
		        "   nvl(SUM(T.QTY),0) availableQty,"+
		        "f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
                "f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,ar.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,ar.unit_packing) Unit,"+
                "f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
                "f_get_spec(d.enterprise_no,d.owner_no,d.article_no,ar.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(d.enterprise_no,d.owner_no,d.article_no,ar.unit_packing) spec,"+
		        "   case when nvl(SUM(T.QTY),0)-sum(D.WASTE_QTY)<0 then (sum(D.WASTE_QTY)-nvl(SUM(T.QTY),0)) else 0 end as  noEnoughQty from " +
			    "   SODATA_WASTE_D D "+
			    "    LEFT JOIN "+
			    "        ( select sc.enterprise_no,sc.warehouse_no,sc.owner_no,sc.article_no,sc.packing_qty," +
			    "                 sum(sc.qty-sc.outstock_qty) qty " +
			    "          from stock_content sc where sc.cell_no='BS111' " +
                         "group by sc.enterprise_no,sc.warehouse_no," +
                                  "sc.owner_no,sc.article_no,sc.packing_qty)T "+
			    "        ON D.ENTERPRISE_NO = T.ENTERPRISE_NO"+
			    "        AND D.WAREHOUSE_NO=T.WAREHOUSE_NO"+
			    "        AND D.ARTICLE_NO=T.ARTICLE_NO  " +
			    "        and d.packing_qty=t.packing_qty "+
			    "    INNER JOIN"+
			    "        BDEF_DEFARTICLE AR "+
			    "        ON D.ARTICLE_NO=AR.ARTICLE_NO  and d.enterprise_no=ar.enterprise_no "+
                "    WHERE D.WASTE_NO='"+wheresql+"' " +
               	"    AND D.ENTERPRISE_NO='"+currEnterpriseNo+"' " +
               	"    AND D.WAREHOUSE_NO='"+warehouseNo+"' " +
                "    GROUP BY D.WASTE_NO,D.ARTICLE_NO,AR.OWNER_ARTICLE_NO,d.packing_qty,AR.ARTICLE_NAME," +
                "d.enterprise_no,d.owner_no,AR.Qmin_Operate_Packing,ar.unit_packing";
		List<Stock_ContentModel> list=genDao.getListByNativeSql(sql, Stock_ContentModel.class, pageBo.getStart(), 3000);
		ExtListDataBo<Stock_ContentModel> extListBo=new ExtListDataBo<Stock_ContentModel>(list,0);
		return extListBo;
	}
	
	/**
	 *  手建单D
	 */
	@Override
	public ExtListDataBo<Sodata_WasteDModel> getWaste_DList(String currEnterpriseNo,
			String warehouseNo,String wheresql,
			PageBo pageBo) throws Exception {
		String sql="select a.enterprise_no,a.warehouse_no," +
				"a.owner_no,a.waste_no,a.po_id,a.article_no," +
				"a.quality,a.produce_date,a.expire_date," +
				"a.waste_qty,a.real_qty,a.locate_qty, " +
				"a.packing_qty," +
				//"nvl(d.packing_unit, decode(a.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"+
                //"nvl(d.spec, '1*' || a.packing_qty || c.unit) spec,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec,"+
				"c.owner_article_no,c.article_name, c.barcode," +
				/*"(a.waste_qty/a.packing_qty) as po_box," +
			    "(a.real_qty/a.packing_qty) as real_box "+ */
			    "trunc(a.waste_qty/a.packing_qty) as planBox,"+
				"trunc(mod(a.waste_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin,"+
				"(a.waste_qty - trunc(a.waste_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.waste_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis "+
				
				"from sodata_waste_d a,bdef_defarticle c," +
				"bdef_article_packing d " +
				"where a.enterprise_no='"+currEnterpriseNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"'  " +
				"and a.enterprise_no=c.enterprise_no and a.enterprise_no=d.enterprise_no(+) and " +
				"a.article_no=c.article_no and " +
				"a.article_no=d.article_no(+) and " +
				"a.packing_qty=d.packing_qty(+) and a.waste_no='"+wheresql+"'";
		List<Sodata_WasteDModel> list=genDao.getListByNativeSql(sql, Sodata_WasteDModel.class, pageBo.getStart(), 1000);
		ExtListDataBo<Sodata_WasteDModel> extListBo=new ExtListDataBo<Sodata_WasteDModel>(list,0);
		return extListBo;
	}
	
	//修改
	@Override
	public MsgRes changeWaste(String wasteM, String wasteD)
			throws Exception {
		Sodata_WasteM om=(Sodata_WasteM)JSON.parseObject(wasteM,Sodata_WasteM.class);
		List<Sodata_WasteD> list=JSON.parseArray(wasteD,Sodata_WasteD.class);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String s1 = null;
		if(om.getWasteDate()!=null){  //to_date('1980-12-13','yyyy-mm-dd')
			String wasteDate=sdf.format(om.getWasteDate());
			s1=" m.waste_date=to_date('"+wasteDate+"' ,'yyyy-mm-dd')";
		}else{
			s1=" m.status=m.status,";
		}
		
		String sql="update sodata_waste_m m set m.owner_no='"+om.getOwnerNo()+"',m.waste_type='"+om.getWasteType()+"' " +
				",m.po_no='"+om.getPoNo()+"',m.org_no='"+om.getOrgNo()+"',"+ s1 +
				",m.updt_name='"+om.getUpdtName() + "', m.updt_date=sysdate " +
				"where m.waste_no='"+om.getId().getWasteNo()+"' and m.enterprise_no='"+om.getId().getEnterpriseNo()+"'";  
		String wasteNo="";
		if(om.getStatus().equals("10")){
			deleteWaste(om.getId().getEnterpriseNo(),om.getId().getWasteNo());
			wasteNo=om.getId().getWasteNo();
			this.genDao.saveList(list);
		}
		this.genDao.updateBySql(sql);
		return new MsgRes(true, "数据保存成功！", wasteNo);
	}
	
	//保存手建单
	public MsgRes saveWaste(String wasteM,String wasteD)
			throws Exception {
		Sodata_WasteM om=(Sodata_WasteM)JSON.parseObject(wasteM,Sodata_WasteM.class);
		List<Sodata_WasteD> list=JSON.parseArray(wasteD,Sodata_WasteD.class);
		om.setUpdtDate(null);
		om.setUpdtName(null);
		String watesNo="";
		if(om.getId().getWasteNo().equals("保存时自动生成")){
			List inList2=new ArrayList();
			List outList2=new ArrayList();
			List resultList2=new ArrayList();
			
			inList2.add(list.get(0).getId().getEnterpriseNo());
			inList2.add(list.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.SODATASM);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			watesNo=resultList2.get(0).toString();
			om.getId().setWasteNo(watesNo);
			for(Sodata_WasteD d:list){
				d.getId().setWasteNo(watesNo);
			}
		}else{
			//deleteExp(om.getId().getEnterpriseNo(),om.getId().getExpNo());
			//expNo=om.getId().getExpNo();
		}
		this.genDao.saveOrUpdateObj(om);
		this.genDao.saveList(list);
		return new MsgRes(true, "数据保存成功！", watesNo);
	}
/*	@Override
	public void deleteExp(String currEnterpriseNo,String expNo) throws Exception {
		String delsql="delete Odata_Exp_D where exp_no='"+expNo+"' " +
			         "and enterprise_no='"+currEnterpriseNo+"'";
		genDao.updateBySql(delsql);
		
	}*/
	
	@Override
	public String checkWatesNo(String currEnterpriseNo,String warehouseNo,String poNo) throws Exception {
		String sql="select a.po_no from sodata_waste_m a where a.po_no='"+poNo+"' " +
				" and a.warehouse_no='"+warehouseNo+"'" +
				" and a.enterprise_no='"+currEnterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	
    //获取包装
	@Override
	public List<Bdef_ArticlePackingModel> getPackingUnit(String currEnterpriseNo,String articleNo,String packingQty,String type)throws Exception {
		System.out.println(type);
		if(type==null){
			String sql="select a.packing_unit,a.spec from bdef_article_packing a " +
					"where a.enterprise_no='"+currEnterpriseNo+"' and a.article_no='"+articleNo+"' and a.packing_qty="+packingQty+"";
			List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(sql,Bdef_ArticlePackingModel.class,0,100);
			return (List<Bdef_ArticlePackingModel>)list;
		}else{
			String sql="select a.packing_unit,a.spec,b.owner_article_no " +
					"from bdef_article_packing a,bdef_defarticle b " +
					"where a.article_no='"+articleNo+"' and a.packing_qty="+packingQty+" and " +
					"a.article_no=b.article_no and a.enterprise_no='"+currEnterpriseNo+"' and a.enterprise_no=b.enterprise_no";
			List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(sql,Bdef_ArticlePackingModel.class,0,100);
			return (List<Bdef_ArticlePackingModel>)list;
		}
	}
	


	//发单
	@Override
	public MsgRes tscBillPrint(String currEnterpriseNo,String warehouseNo,
			String workerNo, String strDockNo,String wasteNo, 
			String ownerNo,String type)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(wasteNo);
		inList.add(workerNo);
		inList.add(strDockNo);
		inList.add(type);//打印标识
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_SODATA.P_LocateAndGetTask");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"操作成功","");
	}
	//获取商品信息
	@Override
	public List<Idata_ImportDModel> getArticle(String articleNo,String currEnterpriseNo)
			throws Exception {
		String sql="select a.article_no,a.article_name,a.owner_article_no,a.barcode,a.qmin_operate_packing from " +
				"bdef_defarticle a where " +
				" a.article_no='"+articleNo+"' and a.enterprise_no='"+currEnterpriseNo+"' ";
		List<Idata_ImportDModel> list=genDao.getListByNativeSql(sql,Idata_ImportDModel.class,0,1000);
		return (List<Idata_ImportDModel>)list;
	}
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscUpLoad(File file, String strWarehouseNo,
			String strEnterpriseNo,String strWorkerNo) throws Exception {
		FileUtilSys.writeFile(file, "SodataTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
	
		List<SodataWasteTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"SodataTmp.xlsx",strWarehouseNo,strEnterpriseNo);
			
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strWorkerNo);
		resultList=genDao.execProc(inList, outList, "pkobj_Create_base.P_sodata_waste");

		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,resultList.get(0).toString(),"");
	
	}
	/**
	 * Excel数据转List
	 */
	public List<SodataWasteTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception {
		List<SodataWasteTmp> iitList = new ArrayList<SodataWasteTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from SODATA_WASTE_TMP a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcel(fileName);
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				SodataWasteTmp po = new SodataWasteTmp();
				SodataWasteTmpId id = new SodataWasteTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setOwnerNo(changeType(excelList.get(i).get(1).toString()));
				id.setPoNo(changeType(excelList.get(i).get(2).toString()));
			
				id.setOrgNo(changeType(excelList.get(i).get(3).toString()));
				id.setOwnerArticleNo(changeType(excelList.get(i).get(4).toString()));
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(5).toString()));
				id.setWasteQty(Double.parseDouble(excelList.get(i).get(6).toString())*Double.parseDouble(excelList.get(i).get(5).toString()));
				id.setRemark(changeType(excelList.get(i).get(7).toString().equals("")?"N":excelList.get(i).get(7).toString()));
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
						if(j==5 || j==6){	
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
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
	//获取系统参数判断WMS手建单是否要ERP审核
	@Override
	public String getSodataAuditFlag(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo) throws Exception {
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add("Sodata_Audit_Flag");
		inList.add("SM");
		inList.add("SM");
       
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_GetBasePara");
		if(resultList.get(2).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(2).toString());
		}	
		
		return (String) resultList.get(0).toString();
	}
	
	//取消订单
	@Override
	public MsgRes closeOrder(String strEnterpriseNo,String warehouseNo, String workerNo,
			String wasteNo, String ownerNo) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(wasteNo);
		inList.add(workerNo);
		resultList=genDao.execProc(inList, outList, "PKLG_SODATA.P_WasteCancel");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}

		return new MsgRes(true,"操作成功","");
	}
	
	//删除
	@Override
	public void deleteWaste(String currEnterpriseNo, String WasteNo)
			throws Exception {
		String delsql="delete sodata_waste_d where waste_no='"+WasteNo+"' " +
		         "and enterprise_no='"+currEnterpriseNo+"'";
		genDao.updateBySql(delsql);
	}

}


















