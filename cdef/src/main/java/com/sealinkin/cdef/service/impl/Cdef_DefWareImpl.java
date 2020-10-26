package com.sealinkin.cdef.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyD;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyM;
import com.sealinkin.bdef.po.Tmp_Formexcel_Defcell;
import com.sealinkin.bdef.po.Tmp_Formexcel_DefcellId;
import com.sealinkin.bset.po.Bset_WorkstationDivide;
import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.cdef.model.Cdef_DefcellModel;
import com.sealinkin.cdef.model.Cdef_DefwareModel;
import com.sealinkin.cdef.po.Cdef_Defarea;
import com.sealinkin.cdef.po.Cdef_DefareaLog;
import com.sealinkin.cdef.po.Cdef_Defcell;
import com.sealinkin.cdef.po.Cdef_Defware;
import com.sealinkin.cdef.po.Cdef_DefwareLog;
import com.sealinkin.cdef.service.ICdef_DefWare;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.ModuleQueryColumn;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Cdef_DefWareImpl implements ICdef_DefWare{
	
	private IGenericManager genDao;
	    
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	public Boolean saveCdef_DefWare(String str,String strLog,String strWorkerNo) throws RuntimeException {
		Cdef_Defware defware=(Cdef_Defware)JSON.parseObject(str,Cdef_Defware.class);
		this.genDao.saveOrUpdateObj(defware);
		
		Cdef_DefwareLog cdl = (Cdef_DefwareLog)JSON.parseObject(strLog,Cdef_DefwareLog.class);
		cdl.getId().setUpdtName(strWorkerNo);
		cdl.getId().setUpdtDate(new Date());
		this.genDao.saveOrUpdateObj(cdl);
		return true;	
	}

	@Override
	public ExtListDataBo<Cdef_DefwareModel> getCdef_DefWare(
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String str,
			PageBo pageBo,
			Integer requestFlag) {
		String sql="select * from cdef_defware a " +
				   "where a.warehouse_no='"+warehouseNo+"' " +
				   "and a.enterprise_no='"+enterpriseNo+"' " ;	
		
        String totsql = "select count(1) " +
        		        "from cdef_defware a " +
        		        "where a.warehouse_no='"+warehouseNo+"' " +
        		        "and a.enterprise_no='"+enterpriseNo+"' " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			totsql=totsql+ws;
		}
        List<Cdef_DefwareModel> list = null;
		Integer count = 0;
		ExtListDataBo<Cdef_DefwareModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Cdef_DefwareModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Cdef_DefwareModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Cdef_DefwareModel>(list, count);
        return extListBo;
	}

	@Override
	public ExtListDataBo<Cdef_DefwareModel> existsWareList(String enterpriseNo,String queryStr,
			String warehouseNo) {
		String sql="select a.* from cdef_defware a " +
				   "where a.warehouse_no='"+warehouseNo+"' " +
				   "and a.enterprise_no='"+enterpriseNo+"' ";
		String totsql="select count(*) from cdef_defware a " +
				      "where a.warehouse_no='"+warehouseNo+"' " +
				      "and a.enterprise_no='"+enterpriseNo+"' ";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		List<Cdef_DefwareModel> list = genDao.getListByNativeSql(sql, Cdef_DefwareModel.class,0, 1000);
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Cdef_DefwareModel> extListBo= new ExtListDataBo<Cdef_DefwareModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 删除库区
	 */
	@Override
	public MsgRes deleteCdefDefware(String enterpriseNo,String strWarehouseNo, String wareNo)
			throws Exception {
		//校验该库区下的储位是否有库存
		String sq="select * from stock_content c " +
				"where c.cell_no in " +
				"(select a.cell_no " +
				"from cdef_defcell a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.ware_no='"+wareNo+"'  )";
		List list=genDao.getListByNativeSql(sq);
		if(list.size()>0){
			return new MsgRes(false,"该库区下的储位还有库存！","");
		}else{
		
			String strSql1="insert into cdef_defware_log select * from  CDEF_DEFWARE " +
					       "where warehouse_no='"+strWarehouseNo+"' " +
					       "and ware_no='"+wareNo+"' " +
					       "and enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(strSql1);
			String strSql2="delete CDEF_DEFWARE " +
					       "where warehouse_no='"+strWarehouseNo+"' " +
					       "and ware_no='"+wareNo+"' " +
					       "and enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(strSql2);
			String strSql3="delete CDEF_DEFAREA " +
					   "where warehouse_no='"+strWarehouseNo+"' " +
					   "and enterprise_no='" +enterpriseNo+"' "+
					   "and ware_no='"+wareNo+"' ";
			genDao.updateBySql(strSql3);
			String strSql4="delete cdef_defcell " +
					   "where warehouse_no='"+strWarehouseNo+"' " +
					   "and enterprise_no='" +enterpriseNo+"' "+
					   "and ware_no='"+wareNo+"' ";
	 	    genDao.updateBySql(strSql4);
			return new MsgRes(true,"删除成功","");
			
		}
	}
	
	//获取库区的长度
	@Override
	public String getWareNoOfLength(String enterpriseNo, String warehouseNo) throws Exception {
		String sql="select t.code_length from wms_code_ref t " +
				   "where t.code_name='WARE_NO' " +
				   "and t.warehouse_no='"+warehouseNo+"' " +
				   "and t.enterprise_no='"+enterpriseNo+"' ";
			
		List list = genDao.getListByNativeSql(sql);
		if(list.size()>0){
			return  list.get(0).toString();
		}
		return null;
	}
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Boolean saveCdef_DefArea(String str,String strLog,String strWorkerNo) throws Exception {
		Cdef_Defarea defArea=(Cdef_Defarea)JSON.parseObject(str,Cdef_Defarea.class);
		if(defArea.getItemType()==null)
		{
			defArea.setItemType("0");
		}
		if(defArea.getDivideLineFlag()==null)
		{
			defArea.setDivideLineFlag("0");
		}
		this.genDao.saveOrUpdateObj(defArea);
		Cdef_DefareaLog cdl=(Cdef_DefareaLog)JSON.parseObject(strLog,Cdef_DefareaLog.class);
		cdl.getId().setUpdtName(strWorkerNo);
		cdl.getId().setUpdtDate(new Date());
		this.genDao.saveOrUpdateObj(cdl);
		return true;	
	}

	
	@Override
	public ExtListDataBo<Cdef_DefareaModel> getCdef_DefArea(
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String str,
			PageBo pageBo,
			Integer requestFlag) {
		String sql="select a.*,b.ware_name," +
					"f_get_fieldtext('N','O_TYPE',a.O_TYPE) O_TYPETEXT, "
					+"f_get_fieldtext('N','AREA_TYPE',a.AREA_TYPE) AREA_TYPETEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','AREA_USETYPE',a.AREA_USETYPE) AREA_USETYPETEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','AREA_QUALITY',a.AREA_QUALITY) AREA_QUALITYTEXT, "
					+"f_get_fieldtext('N','MIX_FLAG',a.MIX_FLAG) MIX_FLAGTEXT, "
					+"f_get_fieldtext('N','MIX_SUPPLIER',a.MIX_SUPPLIER) MIX_SUPPLIERTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','DIVIDE_FLAG',a.DIVIDE_FLAG) DIVIDE_FLAGTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','B_DIVIDE_FLAG',a.B_DIVIDE_FLAG)  B_DIVIDE_FLAGTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','AREA_ATTRIBUTE',a.AREA_ATTRIBUTE) AREA_ATTRIBUTETEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','ATTRIBUTE_TYPE',a.ATTRIBUTE_TYPE) ATTRIBUTE_TYPETEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','LIMIT_TYPE',a.LIMIT_TYPE) LIMIT_TYPETEXT, "
					+"f_get_fieldtext('N','B_PICK',a.B_PICK) B_PICKTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','AREA_PICK',a.AREA_PICK) AREA_PICKTEXT, "
					+"f_get_fieldtext('N','A_FLAG',a.A_FLAG) A_FLAGTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','IO_BUFFER_FLAG',a.IO_BUFFER_FLAG) IO_BUFFER_FLAGTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','PICK_FLAG',a.PICK_FLAG) PICK_FLAGTEXT, "
					+"f_get_fieldtext('CDEF_DEFAREA','ITEM_TYPE',a.ITEM_TYPE) ITEM_TYPETEXT,                                                                                     "
					+"f_get_fieldtext('CDEF_DEFAREA','DIVIDE_LINE_FLAG',a.DIVIDE_LINE_FLAG) DIVIDE_LINE_FLAGTEXT," +
					"f_get_fieldtext('CDEF_DEFAREA','ADVANCER_PICK_FLAG',a.advancer_pick_flag)ADVANCER_PICK_FLAGTEXT " +
				"from cdef_defarea a,cdef_defware b " +
				"where a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.ware_no=b.ware_no " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;		
        String totsql = "select count(1) " +
        		"from cdef_defarea a,cdef_defware b " +
				"where a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.ware_no=b.ware_no " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;	
        if(str!=null && !str.equals("")){
        	List<ModuleQueryColumn> defArea=JSONArray.parseArray(str, ModuleQueryColumn.class);
            if(!defArea.get(0).getValue().equals("ALL"))
    		{
    			String ws=CommUtil.covtCollectionToWhereSql(str);
    			sql=sql+ws;
    			totsql=totsql+ws;
    		}
        
        }
		
		sql=sql+" order by a.ware_no,a.area_no ";
		List<Cdef_DefareaModel> list = null;
		Integer count = 0;
		ExtListDataBo<Cdef_DefareaModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Cdef_DefareaModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Cdef_DefareaModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Cdef_DefareaModel>(list, count);
        return extListBo;
	}
	
	@Override
	public ExtListDataBo<Cdef_DefareaModel> existsAreaList(
			String enterpriseNo,
			String queryStr,
			String warehouseNo) {
		String sql="select a.* from cdef_defarea a " +
				   "where a.warehouse_no='"+warehouseNo+"' " +
				   "and a.enterprise_no='"+enterpriseNo+"' ";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		List<Cdef_DefareaModel> list = genDao.getListByNativeSql(sql, Cdef_DefareaModel.class,0, 1000);
		ExtListDataBo<Cdef_DefareaModel> extListBo= new ExtListDataBo<Cdef_DefareaModel>(list, 0);
        return extListBo;
	}
	
	//获取储区的长度
	@Override
	public String getAreaNoOfLength(String enterpriseNo, String warehouseNo) throws Exception {
			String sql="select t.code_length from wms_code_ref t where t.code_name='AREA_NO' " +
					   "and t.warehouse_no='"+warehouseNo+"' " +
					   "and t.enterprise_no='"+enterpriseNo+"' ";
			List list = genDao.getListByNativeSql(sql);
			if(list.size()>0){
				return  list.get(0).toString();
			}
			return null;
		}
		
	
	//从wms_replenish_formula_m表中获取拆零补货算法下拉列表
	public List<ComboxBo> getReplenishRuleCombo(int intStart, int intPagesize) 
	{
	    String varSql = "select distinct a.strategy_id value,a.strategy_name text," +
	        	"'['|| ltrim(a.strategy_id)||']'||a.strategy_name dropValue " +
	        	"from wms_replenish_formula_m a " +
	        	" where 1=1 ";
		List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);		 
		return  (List<ComboxBo>)list;
	}
	
	// 删除储区
	@Override
	public MsgRes deleteCdefDefarea(String enterpriseNo,String strWarehouseNo, String strWareNo,
			String strAreaNo) throws Exception {
		
		//校验该储区下的储位是否有库存
		String sq="select * from stock_content c " +
				"where c.cell_no in " +
				"(select a.cell_no " +
				"from cdef_defcell a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.ware_no='"+strWareNo+"' " +
				"and a.area_no='"+strAreaNo+"' )";
		List list=genDao.getListByNativeSql(sq);
		if(list.size()>0){
			return new MsgRes(false,"该储区下的储位还有库存！","");
		}else{
			String strSql1="insert into Cdef_Defarea_Log " +
					"select * from CDEF_DEFAREA " +
					"where warehouse_no='"+strWarehouseNo+"' " +
					"and enterprise_no='" +enterpriseNo+"' "+
					"and ware_no='"+strWareNo+"' " +
					"and area_no='"+strAreaNo+"'";
			genDao.updateBySql(strSql1);
			String strSql2="delete CDEF_DEFAREA " +
						   "where warehouse_no='"+strWarehouseNo+"' " +
						   "and enterprise_no='" +enterpriseNo+"' "+
						   "and ware_no='"+strWareNo+"' " +
						   "and area_no='"+strAreaNo+"'";
			genDao.updateBySql(strSql2);
			String strSql3="delete cdef_defcell " +
					   "where warehouse_no='"+strWarehouseNo+"' " +
					   "and enterprise_no='" +enterpriseNo+"' "+
					   "and ware_no='"+strWareNo+"' " +
					   "and area_no='"+strAreaNo+"'";
	    	genDao.updateBySql(strSql3);
			return new MsgRes(true,"删除成功","");
		}
	}
	

/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<ComboxBo> getCdef_DefAreaCombo(String enterpriseNo,String warehouseNo,
			String str, 
			int start,
			int pagesize) {
		String sql="select a.area_no value,a.area_Name text,'['|| ltrim(a.area_no)||']'||a.area_Name dropValue  " +
				"from cdef_defarea a " +
				"where a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.area_no in( " +
				"select b.area_no from cdef_defcell b " +
				"where b.warehouse_no='"+warehouseNo+"' " +
				"and b.enterprise_no='"+enterpriseNo+"' " +
				"and a.ware_no=b.ware_no " +
				")" ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		sql=sql+" order by a.area_no ";
		List list=genDao.getListByNativeSql(sql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}

	@Override
	public List<ComboxBo> getCdef_DefWareCombo(String enterpriseNo,String warehouseNo,
			String str, 
			int start,
			int pagesize) {
		String sql="select a.ware_no value,a.ware_name text,'['|| ltrim(a.ware_no)||']'||a.ware_name dropValue  " +
				"from cdef_defware a " +
				"where a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and  a.ware_no in( " +
				"select b.ware_no from cdef_defcell b " +
				"where b.warehouse_no='"+warehouseNo+"' " +
				"and b.enterprise_no='"+enterpriseNo+"' " +
				")" ;	
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}

	@Override
	public List<ComboxBo> getCombo(String[] str, int start, int pagesize) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("table_name", str[0]);
        paraMap.put("colname", str[1]);
		List list= (List<Object>) genDao.getListByParm("cdef_GetCombo",paraMap,ComboxBo.class);		 
		return  (List<ComboxBo>)list;
	}

	@Override
	public List getCdef_DefCell(String str,String enterpriseNo,String strWarehouseNo) {
		String sql="";
	
        sql="select max(to_number(a.stock_x)) stock_x from cdef_defcell a where a.warehouse_no='"+strWarehouseNo+"' " +
        		"and a.enterprise_no='"+enterpriseNo+"' " ;
        
    	if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
        List<Cdef_DefcellModel> listStock_x=genDao.getListByNativeSql(sql, Cdef_DefcellModel.class, 0, 100000);
       
        sql="select max(to_number(a.bay_x)) bay_x from cdef_defcell a where a.warehouse_no='"+strWarehouseNo+"' " +
        		"and a.enterprise_no='"+enterpriseNo+"' ";
        if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
        List<Cdef_DefcellModel> listBay_x=genDao.getListByNativeSql(sql, Cdef_DefcellModel.class, 0, 100000);
        
        if(listStock_x.size()==0 || listBay_x.size()==0){
        	return null;
        }
        StringBuilder sb=new StringBuilder("");
        for(int i=1;i<=Math.abs(Integer.parseInt(listStock_x.get(0).getStockX()));i++){
        	for(int j=1;j<=Math.abs(Integer.parseInt(listBay_x.get(0).getBayX()));j++){
        		if(Integer.parseInt(listBay_x.get(0).getBayX())>0){
        			sb.append("max(case  when to_number(a.stock_x)='"+i+"' and to_number(a.bay_x)='"+j+"' then cell_no_and_cell_status else null end) col"+i+j+",");
        		}else{
        			sb.append("max(case  when to_number(a.stock_x)='"+i+"' and to_number(a.bay_x)='"+Integer.parseInt(listBay_x.get(0).getBayX())+"' then cell_no_and_cell_status else null end) col"+i+j+",");
        		}
        	}
        }
		sql="select a.warehouse_no,a.ware_no,a.area_no,a.stock_no,a.stock_y,"+sb.toString().substring(0, sb.toString().length()-1)+" from v_Cdef_DefCell a where a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "; 
		  if(str!=null && !str.equals(""))
		  {
				String ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql+ws;
		  }
		sql=sql+" group by a.enterprise_no,a.warehouse_no,a.ware_no,a.area_no,a.stock_no,a.stock_y order by a.stock_y";
		List list=genDao.getListByNativeSql(sql);		
		return list;	
	}

	//获取货位信息（货位信息查询、转区有用该方法）
	@Override
	public List<Cdef_DefcellModel> getCdef_DefCellDetails(
			String enterpriseNo,
			String warehouseNo,
			String str) {
		String sql="select a.*," +
				"('[' || cdw.ware_no||']'||cdw.ware_name)ware_name," +
				"('[' || cda.area_no||']'||cda.area_name)area_name  " +
				"from " +
					"cdef_defcell a,cdef_defware cdw,cdef_defarea cda " +
				"where  " +
					"a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+warehouseNo+"' " +
					"and a.enterprise_no=cdw.enterprise_no and a.warehouse_no=cdw.warehouse_no and a.ware_no=cdw.ware_no " +
					"and a.enterprise_no=cda.enterprise_no and a.warehouse_no=cda.warehouse_no and a.area_no=cda.area_no " ;
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		sql =sql+ " order by a.ware_no,a.area_no,a.stock_no,a.stock_y,a.cell_no  ";
        List<Cdef_DefcellModel> list=genDao.getListByNativeSql(sql,Cdef_DefcellModel.class);
        return (List<Cdef_DefcellModel>)list;
	}

	@Override
	public ExtListDataBo<Stock_ContentModel> getStock_Content(String str,
			PageBo pageBo) {
		String sql="select a.*,d.ware_no,d.area_no,e.area_name,f.ware_name,(a.qty/a.packing_qty) pk_qty," +
				"(a.outstock_qty/a.packing_qty) pk_outstock_qty,"+
	            "(a.instock_qty/a.packing_qty) pk_instock_qty," +
	            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
	            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin," +
	            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit," +
	            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
	            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin," +
	            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec," +
				"b.ARTICLE_NAME,b.BARCODE,c.produce_date,c.expire_date " +
				"from stock_content a,bdef_defarticle b ,bdef_article_packing pk,stock_article_info c,cdef_defcell d,cdef_defarea e,cdef_defware f " +
				"where a.owner_no=b.OWNER_NO " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.article_no=b.ARTICLE_NO " +
				"and a.enterprise_no=c.enterprise_no " +

				"and a.cell_no=d.cell_no " +
				"and d.area_no=e.area_no " +
				"and d.WAREHOUSE_NO=e.WAREHOUSE_NO " +
				"and d.ware_no=f.ware_no " +
				"and a.warehouse_no=f.warehouse_no " +

				"and a.article_no=c.article_no  " +
				"and a.article_id=c.article_id  " +
				"and a.article_no=pk.article_no(+) " +
				"and a.packing_qty=pk.packing_qty(+) " +
				"and a.enterprise_no=pk.enterprise_no(+)" ;		
        String totsql = "select count(1) " +
        		"from stock_content a where 1=1 " ;			
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		List<Stock_ContentModel> list = genDao.getListByNativeSql(sql,Stock_ContentModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Stock_ContentModel> extListBo= new ExtListDataBo<Stock_ContentModel>(list, count);
        return extListBo;
	}

	//获取未禁用的储位
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(
			String enterpriseNo,
			String warehouseNo,
			String jsonStr,
			String str, 
			int start,
			int pagesize) {
		String sql="select a.cell_no value,a.cell_no text,a.cell_no dropValue  " +
				"from Cdef_Defcell a " +
				"where a.cell_status<>1 " +
				"and a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;		
		if(jsonStr!=null && !jsonStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(jsonStr);
			sql=sql+ws;
		}
		if(str!=null && !str.equals(""))
		{
			sql=sql+"and a.cell_no like '%"+str+"%'";
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 10);
		return  (List<ComboxBo>)list;
	}
	
	//获取所有的储位（公用方法）
	@Override
	public List<ComboxBo> getCdef_DefCellAllCombo(
			String enterpriseNo,
			String warehouseNo,
			String jsonStr,//该参数暂未用到
			String str
			) {
		String sql="select a.cell_no value,a.cell_no text,a.cell_no dropValue  " +
				"from Cdef_Defcell a " +
				"where a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;		
		
		if(str!=null && !str.equals(""))
		{
			sql=sql+"and a.cell_no like '%"+str+"%'";
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class ,0, 20);//因为数据量比较大，所以加上行数限制
		return  (List<ComboxBo>)list;
	}

	/**
	 * 储位修改保存
	 */
	@Override
	public MsgRes saveCdef_DefCell(String str) throws Exception {
		Cdef_Defcell defCell=(Cdef_Defcell)JSON.parseObject(str,Cdef_Defcell.class);

		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
	    inList.add(defCell.getId().getEnterpriseNo());
	    inList.add(defCell.getId().getWarehouseNo());
	    inList.add(defCell.getId().getCellNo());
		inList.add(defCell.getMixFlag());
		inList.add(defCell.getMixSupplier());
		inList.add(defCell.getMixOwner());
		inList.add(defCell.getMaxQty());
		inList.add(defCell.getMaxWeight());
		inList.add(defCell.getMaxVolume());
		inList.add(defCell.getMaxCase());
		inList.add(defCell.getLimitType());
		inList.add(defCell.getLimitRate());
		inList.add(defCell.getBPick());
		inList.add(defCell.getCellStatus());
		inList.add(defCell.getCheckStatus());
		inList.add(defCell.getAFlag());
		inList.add(defCell.getPickFlag());
		inList.add(defCell.getRgstName());
		inList.add(defCell.getKeepLabelFlag());
		
	    System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKOBJ_CDEF.p_update_cell");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,"保存成功！","");	
	}


	//获取储区属性
	@Override
	public ExtListDataBo<Cdef_DefareaModel> getAttribute(String currEnterpriseNo,
			String warehouseNo, String strWareNo, String strAreaNo) {
		
		String sql="select a.mix_flag,a.b_pick,a.limit_type,a.limit_rate," +
				   "a.a_flag,a.pick_flag,a.mix_supplier,a.area_attribute," +
				   "a.max_qty,a.max_case,a.mix_owner,a.max_weight,a.max_volume,a.keep_label_flag " +
				   "from cdef_defarea a " +
				   "where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "and a.warehouse_no='"+warehouseNo+"' " +
				   "and a.ware_no='"+strWareNo+"' " +
				   "and a.area_no='"+strAreaNo+"' ";
		
		List<Cdef_DefareaModel> list = genDao.getListByNativeSql(sql, Cdef_DefareaModel.class,0, 1);
		ExtListDataBo<Cdef_DefareaModel> extListBo= new ExtListDataBo<Cdef_DefareaModel>(list, 0);
        return extListBo;
	}
	
	//新增储位
	@Override
	public MsgRes produceCell(String str,
			String nMinStockNo, 
			String nMaxStockNo,
			String nMinStockY, 
			String nMaxStockY, 
			String nMinStockX, 
			String nMaxStockX,
			String nMinBayX, 
			String nMaxBayX, 
			String strCodePrefix, 
			String perf1, 
			String perf2, 
			String perf3, 
			String perf4
			) throws Exception {
		Cdef_Defcell cell=(Cdef_Defcell)JSON.parseObject(str,Cdef_Defcell.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
	
		outList.add("S");
	    inList.add(cell.getId().getEnterpriseNo());
	    inList.add(cell.getId().getWarehouseNo());
		inList.add(cell.getWareNo());
		inList.add(cell.getAreaNo());
		inList.add(StringUtil.isStrEmpty(nMinStockNo)?"-1":nMinStockNo);
		inList.add(StringUtil.isStrEmpty(nMaxStockNo)?"-1":nMaxStockNo);
		inList.add(StringUtil.isStrEmpty(nMinStockY)?"-1":nMinStockY);
		inList.add(StringUtil.isStrEmpty(nMaxStockY)?"-1":nMaxStockY);
		inList.add(StringUtil.isStrEmpty(nMinStockX)?"-1":nMinStockX);
		inList.add(StringUtil.isStrEmpty(nMaxStockX)?"-1":nMaxStockX);
		inList.add(StringUtil.isStrEmpty(nMinBayX)?"-1":nMinBayX);
		inList.add(StringUtil.isStrEmpty(nMaxBayX)?"-1":nMaxBayX);
		inList.add(StringUtil.isStrEmpty(strCodePrefix)?"#":strCodePrefix);
		inList.add(cell.getMixFlag().toString());
		inList.add(cell.getMixSupplier());
		inList.add(cell.getMixOwner());
		inList.add(String.valueOf(cell.getMaxQty()));  
		inList.add(Double.toString(cell.getMaxWeight()));
		inList.add(Double.toString(cell.getMaxVolume()));
		inList.add(Double.toString(cell.getMaxCase()));
		inList.add(cell.getLimitType());
		inList.add(String.valueOf(cell.getLimitRate()));
		inList.add(cell.getBPick());
		inList.add(cell.getCellStatus());
		inList.add(cell.getCheckStatus());
	    inList.add(cell.getAFlag());
	    inList.add(cell.getPickFlag());
	    inList.add(perf1);
	    inList.add(perf2);
	    inList.add(perf3);
	    inList.add(perf4);
	    inList.add(cell.getRgstName());
	    inList.add(cell.getKeepLabelFlag());
	    inList.add("1");
	    System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKOBJ_CDEF.p_create_cell_no");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		
	    System.out.println(resultList.get(0).toString());
		return new MsgRes(true,"保存成功！","");	
		
	}
	@Override
	public List<ComboxBo> getCdef_DefAreaComboforWindow(
			String enterpriseNo, String warehouseNo, String str, int i,
			int j) {
		String sql="select a.area_no value,a.area_Name text,'['|| ltrim(a.area_no)||']'||a.area_Name dropValue  " +
				"from cdef_defarea a " +
				"where a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.ware_No='"+str+"' " ;		
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}
	@Override
	public List<ComboxBo> getCdef_DefStockCombo(String enterpriseNo,
			String warehouseNo, String str, int i, int j) {
		String sql="select distinct a.stock_no value, a.stock_no text, '['|| ltrim(a.stock_no)||']'||a.stock_no dropValue " +
				"from Cdef_Defcell a " +
				"where a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_No='"+warehouseNo+"' " ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
}

	@Override
	public MsgRes checkCell(String enterpriseNo, String warehouseNo,String strWareNo,
			String strAreaNo,String minStockNo, String maxStockNo, String minStockY,
			String maxStockY, String minStockX, String maxStockX,
			String minBayX, String maxBayX) {
		String flag="";
		String sql="";
		
		//判断通道
		if(!minStockNo.equals("") && minStockNo!=null && !maxStockNo.equals("") && maxStockNo!=null){
			    sql="select a.stock_no from cdef_defcell a " +
			        "where a.enterprise_no='"+enterpriseNo+"' " +
			        " and a.warehouse_no='"+warehouseNo+"' " +
			        " and a.ware_no='"+strWareNo+"' "+
			        " and a.area_no='"+strAreaNo+"' "+
			        " and a.stock_no>="+minStockNo+
			        " and a.stock_no<="+maxStockNo;
			    
			    List list = genDao.getListByNativeSql(sql);	
				if(list.size()>0){
					flag="1";
				}else{
					flag="0";
				}
		}
		
		//判断格
		if(flag.equals("0")){
			return new MsgRes(true,"-1","");
		}else{	
			if(!minStockX.equals("") && minStockX!=null && !maxStockX.equals("") && maxStockX!=null){
				 if(minStockNo.equals("") || minStockNo==null || maxStockNo.equals("") || maxStockNo==null){
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX;
				 }else{
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_no>="+minStockNo+
						        " and a.stock_no<="+maxStockNo+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX;
				 }
			    List list = genDao.getListByNativeSql(sql);	
				if(list.size()>0){
					flag="2";
				}else{
					flag="0";
				}
			}		
		}
		
		//判断位
		if(flag.equals("0")){
			return new MsgRes(true,"-1","");
		}else{	
			if(!minBayX.equals("") && minBayX!=null && !maxBayX.equals("") && maxBayX!=null){
				 if(minStockNo.equals("") || minStockNo==null || maxStockNo.equals("") || maxStockNo==null){
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX+
						        " and a.bay_x>="+minBayX+
						        " and a.bay_x>="+maxBayX;
				 }else{
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_no>="+minStockNo+
						        " and a.stock_no<="+maxStockNo+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX+
						        " and a.bay_x>="+minBayX+
						        " and a.bay_x<="+maxBayX;;
				 }
			    List list = genDao.getListByNativeSql(sql);	
				if(list.size()>0){
					flag="3";
				}else{
					flag="0";
				}
			}		
		}
		
		//判断层
		if(flag.equals("0")){
			return new MsgRes(true,"-1","");
		}else{	
			if(!maxStockY.equals("") && minStockY!=null && !maxStockY.equals("") && minStockY!=null){
				
				if(!minBayX.equals("") && minBayX!=null && !maxBayX.equals("") && maxBayX!=null && 
				   !minStockX.equals("") && minStockX!=null && !maxStockX.equals("") && maxStockX!=null){
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_no>="+minStockNo+
						        " and a.stock_no<="+maxStockNo+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX+
						        " and a.bay_x>="+minBayX+
						        " and a.bay_x<="+maxBayX+
						        " and a.stock_y>="+minStockY+
						        " and a.stock_y<="+maxStockY;
				}else if((minStockNo.equals("") || minStockNo==null || maxStockNo.equals("") || maxStockNo==null)&&
						 !minBayX.equals("") && minBayX!=null && !maxBayX.equals("") && maxBayX!=null){
					
						 sql="select a.stock_no from cdef_defcell a " +
							        "where a.enterprise_no='"+enterpriseNo+"' " +
							        " and a.warehouse_no='"+warehouseNo+"' " +
							        " and a.ware_no='"+strWareNo+"' "+
							        " and a.area_no='"+strAreaNo+"' "+
							        " and a.stock_x>="+minStockX+
							        " and a.stock_x<="+maxStockX+
							        " and a.bay_x>="+minBayX+
							        " and a.bay_x<="+maxBayX+
							        " and a.stock_y>="+minStockY+
							        " and a.stock_y<="+maxStockY;
				}else if((!minStockNo.equals("") && minStockNo!=null && !maxStockNo.equals("") && maxStockNo!=null)&&
						 (minBayX.equals("") || minBayX==null || !maxBayX.equals("") || maxBayX!=null)){
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_no>="+minStockNo+
						        " and a.stock_no<="+maxStockNo+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX+
						        " and a.stock_y>="+minStockY+
						        " and a.stock_y<="+maxStockY;
					 
				}else if((minStockNo.equals("") || minStockNo==null || maxStockNo.equals("") || maxStockNo==null)&&
						 (minBayX.equals("") || minBayX==null || maxBayX.equals("") || maxBayX==null)){
					 sql="select a.stock_no from cdef_defcell a " +
						        "where a.enterprise_no='"+enterpriseNo+"' " +
						        " and a.warehouse_no='"+warehouseNo+"' " +
						        " and a.ware_no='"+strWareNo+"' "+
						        " and a.area_no='"+strAreaNo+"' "+
						        " and a.stock_x>="+minStockX+
						        " and a.stock_x<="+maxStockX+
						        " and a.stock_y>="+minStockY+
						        " and a.stock_y<="+maxStockY;	
				}
				
			    List list = genDao.getListByNativeSql(sql);	
				if(list.size()>0){
					flag="4";
				}else{
					flag="0";
				}
			}		
		}
		
		if(flag.equals("0")){
			return new MsgRes(true,"-1","");
		}else{
			return new MsgRes(true,flag,"");
		}
	}
	
	//获取库区（添加储位）
	@Override
	public List<ComboxBo> getCdef_DefWareComboforWindow(
			String enterpriseNo, String warehouseNo, String str, int i,
			int j) {

		String sql="select a.ware_no value,a.ware_name text,'['|| ltrim(a.ware_no)||']'||a.ware_name dropValue  " +
				"from cdef_defware a " +
				"where a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "; ;	
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	
	}
	@Override
	public MsgRes tscDefcellUpLoad(String currEnterpriseNo, String warehouseNo,
			String workerNo, String ownerNo, File file) throws Exception {
		FileUtilSys.writeFile(file, "defCellTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		List<Tmp_Formexcel_Defcell> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"defCellTmp.xlsx",
				                                            currEnterpriseNo,warehouseNo,ownerNo);
		genDao.saveList(list);
		genDao.flush();
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(workerNo);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_insertDefcell");

		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception("第"+resultList.get(0).toString()+"条数据有误:"+resultList.get(1).toString());
		}
		
		return new MsgRes(true,"导入成功","");
	}
	
	private List<Tmp_Formexcel_Defcell> changeexcelBean(String fileName,
			String currEnterpriseNo, String warehouseNo, String ownerNo) {
		List<Tmp_Formexcel_Defcell> list = new ArrayList<Tmp_Formexcel_Defcell>();		
		//导入前删除临时表数据
		String delsql=" delete idata_import_tmp a " +
				      " where a.enterprise_no='"+currEnterpriseNo+"' " +
				      "   and a.warehouse_no='"+warehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcel(fileName);
		for (int i = 0; i < excelList.size(); i++) {
			Tmp_Formexcel_Defcell po = new Tmp_Formexcel_Defcell();
			Tmp_Formexcel_DefcellId id= new Tmp_Formexcel_DefcellId();
			
			id.setEnterpriseNo(currEnterpriseNo);
			id.setWarehouseNo(warehouseNo);
			id.setOwnerNo(ownerNo);
			id.setRowId(i+1.0);
			id.setStatus("10");
			
			id.setWareNo(excelList.get(i).get(0).toString());
			id.setAreaNo(excelList.get(i).get(1).toString());
			id.setStockNo(excelList.get(i).get(2).toString());
			id.setStockX(excelList.get(i).get(3).toString());
			id.setBayX(excelList.get(i).get(4).toString());
			id.setStockY(excelList.get(i).get(5).toString());
			id.setPrefix(excelList.get(i).get(6).toString());
			id.setCellNo(excelList.get(i).get(7).toString());
			id.setCellStatus(excelList.get(i).get(8).toString());
			id.setMixSupplier(excelList.get(i).get(9).toString());
			id.setMixFlag(Integer.parseInt(excelList.get(i).get(10).toString()));
			id.setMaxQty(Double.parseDouble(excelList.get(i).get(11).toString()));
			id.setMaxCase(Double.parseDouble(excelList.get(i).get(12).toString()));
			id.setMaxWeight(Double.parseDouble(excelList.get(i).get(13).toString()));
			id.setMaxVolume(Double.parseDouble(excelList.get(i).get(14).toString()));
			id.setPickOrder(Integer.parseInt(excelList.get(i).get(15).toString()));
			
			po.setId(id);
			list.add(po);
		}		
		return list;
	}
	
	private List<List> impExcel(String execelFile) {
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
			//总共多少列
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
	
	//格下拉（UI）
	@Override
	public List<ComboxBo> getCdef_DefStockXCombo(String currEnterpriseNo,
			String warehouseNo, String str) throws Exception {
		String sql=" select ' ' as value, ' ' as text, ' ' as dropValue from dual union "  +
				"select distinct a.stock_x value, a.stock_x text, '['|| ltrim(a.stock_x)||']'||a.stock_x dropValue  " +
				"from Cdef_Defcell a " +
				"where a.enterprise_no='"+currEnterpriseNo+"' "+
				"and a.warehouse_No='"+warehouseNo+"' " ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	//层下拉（UI）
	@Override
	public List<ComboxBo> getCdef_DefStockYCombo(String currEnterpriseNo,
			String warehouseNo, String str) {
		String sql=" select ' ' as value, ' ' as text, ' ' as dropValue from dual union "  +
				"select distinct a.stock_y value, a.stock_y text, '['|| ltrim(a.stock_y)||']'||a.stock_y dropValue  " +
				"from Cdef_Defcell a " +
				"where a.enterprise_no='"+currEnterpriseNo+"' "+
				"and a.warehouse_No='"+warehouseNo+"' " ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	//位下拉
	@Override
	public List<ComboxBo> getCdef_DefbayXCombo(String currEnterpriseNo,
			String warehouseNo, String str) throws Exception {
		String sql=" select ' ' as value, ' ' as text, ' ' as dropValue from dual union "  +
				"select distinct a.bay_x value, a.bay_x text, '['|| ltrim(a.bay_x)||']'||a.bay_x dropValue  " +
				"from Cdef_Defcell a " +
				"where a.enterprise_no='"+currEnterpriseNo+"' "+
				"and a.warehouse_No='"+warehouseNo+"' " ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	//批量修改储位
	@Override
	public MsgRes updateCellStatus(String currEnterpriseNo, String warehouseNo,
			String str, String flag) throws Exception {
		String sql=" select b.cell_no from cdef_defcell b " +
				   "  where b.enterprise_no = '"+currEnterpriseNo+"' " +
				   "    and b.warehouse_no= '"+warehouseNo+"' ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		if(flag.equals("1")){
			String checkSql="select a.cell_no from stock_content a " +
							" where a.enterprise_no = '"+currEnterpriseNo+"' " +
							"   and a.warehouse_no= '"+warehouseNo+"' " +
							"   and a.cell_no in ("+sql+")";
			List<String> list = genDao.getListByNativeSql(checkSql);
			if(list.size()>0){
				return new MsgRes(false,"有储位存在库存，不能禁用！","");
			}
		}
		
		
		String updateSql="update cdef_defcell a " +
				         "   set a.cell_status ='"+flag+"' " +
				         " where a.enterprise_no = '"+currEnterpriseNo+"' " +
				         "   and a.warehouse_no= '"+warehouseNo+"' " +
				         "   and a.cell_no in ("+sql+")";
		
		genDao.exceuteSql(updateSql);
		return new MsgRes(true,"操作成功","");
	}
	
	//转区
	@Override
	public MsgRes updateCellWareArea(String enterpriseNo,
			String strWarehouseNo, String str,String strWareNo,String strAreaNo)throws Exception {
		
		//Collection<Cdef_Defcell> list=JSONArray.toCollection(net.sf.json.JSONArray.fromObject(str),Cdef_Defcell.class);
		//List<Cdef_Defcell> articleFamily=(List)list;
    	List<Cdef_DefcellModel> a=JSONArray.parseArray(str, Cdef_DefcellModel.class);

    	if(a.size()>0){
    		for(int i=0;i<a.size();i++){
    			String updateSql="update cdef_defcell a " +
   			         "   set a.ware_no ='"+strWareNo+"',a.area_no='"+strAreaNo+"' " +
   			         " where a.enterprise_no = '"+enterpriseNo+"' " +
   			         "   and a.warehouse_no= '"+strWarehouseNo+"' " +
   			         "   and a.cell_no = '"+a.get(i).getCellNo()+"' ";
       		    genDao.exceuteSql(updateSql);
    		}
    		
    	}
		return new MsgRes(true,"转区成功","");
		
	}
}
