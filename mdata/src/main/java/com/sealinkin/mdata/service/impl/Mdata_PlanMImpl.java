package com.sealinkin.mdata.service.impl;

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
import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.mdata.model.Mdata_PlanMTmpModel;
import com.sealinkin.mdata.po.Mdata_PlanTmp;
import com.sealinkin.mdata.po.Mdata_PlanTmpId;
import com.sealinkin.mdata.service.IMdata_PlanMService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"unchecked","rawtypes"})
public class Mdata_PlanMImpl implements IMdata_PlanMService{
	
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//安全量补货
	@Override
	public ExtListDataBo<Stock_ContentModel> getCset_CellArticleList(
			String str, Boolean str1, Integer start,
			Integer pageSize,String warehouseNo,String enterpriseNo) {
		String sql="";
		String sql1=" order by cell_no, article_no, alert_qty desc";
	    if(str1==true){//sql缘由不清楚
	    	sql="SELECT v1.*, TRUNC(v1.max_qty - v1.qty) demand_qty," +
	    		"NVL(v1.qty, 0) available_qty,BDA.OWNER_ARTICLE_NO," +
	    		"BDA.ARTICLE_NAME,BDA.ARTICLE_ENAME,BDA.ARTICLE_IDENTIFIER," +
	    		"BDA.ARTICLE_ONAME,BDA.ARTICLE_ALIAS,BDA.MODEL,BDA.SPEC,BDA.APPROVE_NO," +
	    		"BDA.FACTORY,B  DA.Formulation_No,BDA.STYLE, BDA.FANCY," +
	    		"BDA.CUP,BDA.COLOR_CODE, BDA.SIZE_CODE,BDA.QMIN_OPERATE_PACKING," +
	    		"BDA.SELL_PRICE,BDA.COLOR_DESC,BDA.SIZE_DESC,BDA.BRAND, BDA.BUTTON_ROWS," +
	    		"BDA.THICKNESS,BDA.COVER_CUP,BDA.PANTS_MODE,BDA.RULE_FLAG,BDA.ABC," +
	    		"BDA.QC_FLAG,BDA.MEASURE_MODE, BDA.TEMPERATURE_FLAG,BDA.VIRTUAL_FLAG," +
	    		"BDA.SCAN_FLAG,BDA.UNIT,BDA.DOUBLE_CHECK,BDA.EXPIRY_DAYS,BDA.ARTICLE_VIRTUE,BDA.divide_box_flag " +
	    		"FROM (SELECT cca.enterprise_no, cca.warehouse_no,cca.cell_no, cca.pick_type,cca.owner_no,cca.article_no," +
	    		"NVL(SUM(sc.qty - sc.outstock_qty + sc.instock_qty), 0) qty,cca.packing_qty," +
	    		"cca.ware_no,cca.area_no,cca.stock_no,sai.BARCODE,CASE WHEN cdc.a_flag = '1' THEN " +
	    		"cca.max_qty_a ELSE cca.max_qty_na  END max_qty," +
	    		" CASE WHEN cdc.a_flag = '1' THEN cca.alert_qty_a" +
	    		" ELSE cca.alert_qty_na END AS alert_qty," +
	    		" CASE WHEN cdc.a_flag = '1' THEN cca.keep_cells_a" +
	    		" ELSE cca.keep_cells END keep_cell" +
	    		" FROM cset_cell_article cca LEFT JOIN stock_content sc ON sc.enterprise_no=cca.enterprise_no " +
	    		" and sc.warehouse_no = cca.warehouse_no" +
	    		" AND sc.owner_no = cca.owner_no AND sc.article_no = cca.article_no" +
	    		" AND sc.cell_no LIKE cca.cell_no || '%'  AND sc.flag <> '2' AND sc.status = '0'" +
	    		" left JOIN stock_article_info sai ON sc.enterprise_no=sai.enterprise_no" +
	    		" and sc.article_no = sai.article_no" +
	    		" and sai.QUALITY = '0' AND sc.article_id = sai.article_id" +
	    		" left JOIN cdef_defcell cdc ON sc.enterprise_no=cdc.enterprise_no" +
	    		" and sc.warehouse_no = cdc.warehouse_no " +
	    		" AND sc.cell_no = cdc.cell_no AND cdc.cell_status = '0'" +
	    		" AND cdc.check_status = '0' GROUP BY cca.enterprise_no,cca.owner_no," +
	    		" cca.cell_no, cca.warehouse_no, cca.article_no, cca.packing_qty," +
	    		" cca.area_no, cdc.a_flag, cca.max_qty_a, cca.ware_no, cca.max_qty_na," +
	    		" cca.alert_qty_a,cca.stock_no, cca.pick_type, sai.BARCODE, cca.alert_qty_na," +
	    		" cca.keep_cells, cca.keep_cells_a) v1" +
	    		" INNER JOIN bdef_defarticle BDA ON BDA.article_no = v1.article_no" +
	    		" AND BDA.owner_no = v1.owner_no and bda.enterprise_no=v1.enterprise_no " +
	    		" WHERE 1 = 1 and v1.enterprise_no='"+enterpriseNo+"' " +
	    		" and qty <= alert_qty  and v1.qty >= 0"; 
            
	    }else{
	    	if(str1==false){
	    		sql="SELECT cca.enterprise_no,cca.warehouse_no, BDA.OWNER_ARTICLE_NO, BDA.ARTICLE_NAME,cca.cell_no AS cell_no," +     //去掉了, BDA.UNIT
	    			"cca.pick_type,cca.owner_no, cca.article_no AS article_no, v1.packing_qty," +
	    			//"nvl(pk.packing_unit, decode(v1.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit," +
	    			"f_get_packingunit(v1.enterprise_no,v1.owner_no,v1.article_no,v1.packing_qty) packingUnit,"+
	    			"f_get_packingunit(v1.enterprise_no,v1.owner_no,v1.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
	    			"f_get_packingunit(v1.enterprise_no,v1.owner_no,v1.article_no,bda.unit_packing) Unit,"+
	    			"f_get_spec(v1.enterprise_no,v1.owner_no,v1.article_no,v1.packing_qty) packingSpec,"+
	    			"f_get_spec(v1.enterprise_no,v1.owner_no,v1.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
	    			"f_get_spec(v1.enterprise_no,v1.owner_no,v1.article_no,bda.unit_packing) spec,"+
	    			"cca.ware_no, BDA.BARCODE," +
	    			" CASE  WHEN cdc.a_flag = 'A' THEN (cca.max_qty_a * cca.keep_cells_a)  ELSE (cca.max_qty_na * cca.keep_cells)  END max_qty,"+
	              " CASE WHEN cdc.a_flag = 'A' THEN cca.alert_qty_a  ELSE cca.alert_qty_na END AS alert_qty,"+
	              " CASE  WHEN cdc.a_flag = 'A' THEN cca.keep_cells_a  ELSE cca.keep_cells END keep_cell,"+
	              " v1.canqty as availableQty,"+//update by czh 2016.7.11
	              " v2.currqty as qty,"+//update by czh 2016.7.11
	              " (( CASE  WHEN cdc.a_flag = 'A' THEN (cca.max_qty_a * cca.keep_cells_a) ELSE (cca.max_qty_na * cca.keep_cells)  END)-v2.currqty) as demandQty "+  
	              " FROM cset_cell_article cca,cdef_defcell cdc,( " +
	    			" select cc.enterprise_no,cc.warehouse_no,cc.owner_no,cc.article_no,cc.packing_qty,sum(cc.qty) canqty " +
	    			" from stock_content cc,stock_article_info sai,cdef_defcell cd,cdef_defarea ca " +
	    			" where cd.warehouse_no=ca.warehouse_no " +
	    			" and cc.enterprise_no=sai.enterprise_no and cc.enterprise_no=cd.enterprise_no " +
	    			" and cc.enterprise_no=ca.enterprise_no " +
	    			" and cd.ware_no=ca.ware_no " +
	    			" and cd.area_no=ca.area_no and cc.warehouse_no=cd.warehouse_no " +
	    			" and cc.article_no=sai.article_no and cc.article_id=sai.article_id " +
	    			" and cc.cell_no=cd.cell_no and ca.area_usetype in('1','5','6') and ca.area_attribute='0'" +
	    			" and cc.status='0' and cc.flag<>'2' and cc.warehouse_no='"+warehouseNo+"'" +
	    		    " and cc.qty-cc.outstock_qty>0 " +
	    		    " group by cc.enterprise_no,cc.warehouse_no,cc.owner_no,cc.article_no,cc.packing_qty) v1, " +
	    		    " (select cca.enterprise_no,cca.warehouse_no,cca.owner_no,cca.article_no,sc.packing_qty,sum(sc.qty-sc.outstock_qty+sc.instock_qty) currQty " +
	    		    " from cset_cell_article cca,stock_content sc " +
	    		    " where cca.warehouse_no=sc.warehouse_no " +
	    		    " and cca.enterprise_no=sc.enterprise_no and cca.article_no=sc.article_no " +
	    		    " and cca.cell_no=sc.cell_no and cca.warehouse_no='"+warehouseNo+"'" +
	    		    " group by cca.enterprise_no,cca.warehouse_no,cca.owner_no,cca.article_no,sc.packing_qty)v2," +
	    		    " bdef_defarticle BDA ,bdef_article_packing pk  " +
	    		    " WHERE cca.enterprise_no=cdc.enterprise_no and cca.enterprise_no=v1.enterprise_no " +
	    		    " and cca.enterprise_no=v2.enterprise_no and cca.enterprise_no=bda.enterprise_no " +
	    		    " and v1.warehouse_no=v2.warehouse_no and v1.article_no=v2.article_no " +
	    		    " and cca.warehouse_no=v1.warehouse_no and cca.article_no=v1.article_no " +
	    		    " and cca.article_no=bda.ARTICLE_NO and cca.warehouse_no=cdc.warehouse_no " +
	    		    " and cca.cell_no=cdc.cell_no " +
	    		    " and v2.currqty <=cca.alert_qty_a " +
	    		    " and v1.packing_qty=v2.packing_qty " +
	    		    " and cca.packing_qty=v1.packing_qty " +
	    		    " and v1.article_no=pk.article_no(+) " +
	    		    " and v1.packing_qty=pk.packing_qty(+) " +
	    		    " and v1.enterprise_no=pk.enterprise_no(+)" +
	    		    " and v1.warehouse_no ='"+warehouseNo+"' " +
	    		    " and cca.enterprise_no='"+enterpriseNo+"' " ;
	    		
	    	}
	    }
	    if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws+sql1;
		}else{
			sql=sql+sql1;
		}
	    List<Stock_ContentModel> list = genDao.getListByNativeSql(sql,Stock_ContentModel.class,start,pageSize);
		ExtListDataBo<Stock_ContentModel> extListBo= new ExtListDataBo<Stock_ContentModel>(list, 0);
       return extListBo;
		
	}

	/*
	 * 人工移库
	 * (non-Javadoc)
	 * @see com.sealinkwms.mdata.service.IMdata_Plan_MService#getStock_ContentList(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public ExtListDataBo<Stock_ContentModel> getStockContentList(String str,
			String ownerArticleNo, String barcode, Integer start,
			Integer pageSize,Integer requestFlag,String enterpriseNo,String oldOdata) {
		
		String sql1="select v.*, bdo.owner_name, BDA.OWNER_ARTICLE_NO," +
				" BDA.ARTICLE_NAME, BDA.ARTICLE_ENAME, BDA.ARTICLE_IDENTIFIER," +
				" BDA.ARTICLE_ONAME, BDA.ARTICLE_ALIAS, " +
				//" nvl(bag.spec, '1*' || v.packing_qty || bda.unit) spec,"+
				" BDA.QMIN_OPERATE_PACKING, BDA.SELL_PRICE,BDA.RULE_FLAG, BDA.ABC," +
				" BDA.QC_FLAG, BDA.MEASURE_MODE, BDA.TEMPERATURE_FLAG," +
				" BDA.VIRTUAL_FLAG, BDA.SCAN_FLAG," +
				//" nvl(bag.packing_unit, decode(v.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) unit," +
				"f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,v.packing_qty) packingUnit,"+
				"f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,bda.unit_packing) Unit,"+
				"f_get_spec(v.enterprise_no,v.owner_no,v.article_no,v.packing_qty) packingSpec,"+
				"f_get_spec(v.enterprise_no,v.owner_no,v.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(v.enterprise_no,v.owner_no,v.article_no,bda.unit_packing) spec,"+
				" BDA.DOUBLE_CHECK," +
				" BDA.EXPIRY_DAYS, BDA.barcode," +
				" qty pk_qty, " +//update by czh 2016.7.11
				"trunc(qty/v.packing_qty) as planBox,"+
				"trunc(mod(qty,v.packing_qty)/bda.QMIN_OPERATE_PACKING) as planQmin,"+
				"(qty - trunc(qty/v.packing_qty) * v.packing_qty - trunc(mod(qty,v.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as planDis "+
				 
				" from (select sc.enterprise_no,sc.warehouse_no, sc.owner_no, sc.sub_label_no, " +
				" sc.cell_no, sc.stock_type, sc.STOCK_VALUE, sc.article_no," +
				" sc.packing_qty,sum(sc.qty) qty, " +
				" sai.LOT_NO, " +
				" trunc(sai.produce_date) as produce_date," +
				" trunc(sai.EXPIRE_DATE) as EXPIRE_DATE, sai.quality," +
				" sum(sc.qty - sc.outstock_qty) as available_qty,  " +
				" f_get_fieldtext('N', 'quality', sai.QUALITY) textQuality,   " +
				" sc.label_no from stock_article_info sai, stock_content sc" +
				" where sc.article_no = sai.article_no  " +
				" and sc.enterprise_no=sai.enterprise_no" +
				" and sc.enterprise_no='"+enterpriseNo+"'" +
				" and sc.article_id = sai.article_id and sc.flag <> '2'" +
				" and sc.status = '0'";
	        String sql2=" group by sc.enterprise_no,sc.warehouse_no, sc.owner_no," +
	        		"  sc.cell_no, sc.stock_type," +
	        		" sc.STOCK_VALUE, sc.article_no, sc.label_no," +
	        		" sc.packing_qty,   sc.sub_label_no ," +
	        		" sai.PRODUCE_DATE, sai.EXPIRE_DATE, sai.QUALITY," +
	        		"  sai.LOT_NO ) v" +
	        		" inner join bdef_defarticle BDA on v.article_no = BDA.article_no" +
	        		" and v.owner_no = BDA.owner_no" +
	        		" and v.enterprise_no=bda.enterprise_no" +
	        		" left join bdef_article_packing bag on v.article_no = bag.article_no" +
	        		" and v.enterprise_no=bag.enterprise_no" +
	        		" and v.packing_qty = bag.packing_qty" +
	        		" inner join bdef_defowner bdo on v.enterprise_no=bdo.enterprise_no" +
	        		" and v.owner_no = bdo.owner_no" +
	        		" inner join CDEF_DEFCELL def on v.enterprise_no=def.enterprise_no" +
	        		" and def.CELL_NO = v.CELL_NO" +
	        		" and def.warehouse_no = v.warehouse_no" +
	        		" AND def.CELL_STATUS in ('0', '2') AND def.CHECK_STATUS = '0'" +
	        		" inner join cdef_defarea cda on cda.warehouse_no = def.warehouse_no" +
	        		" and v.enterprise_no=cda.enterprise_no" +
	        		" and cda.ware_no = def.ware_no and cda.area_no = def.area_no" +
	        		" WHERE qty > 0 and available_qty > 0 ";
	     
	           String sql3="order by v.cell_no,v.article_no";
	           
	   		   String ws1=CommUtil.covtCollectionToWhereSql(str);
	   		   String ws2=CommUtil.covtCollectionToWhereSql(barcode);
	   		   String ws3=CommUtil.covtCollectionToWhereSql(ownerArticleNo);
	   		   String sql=sql1+ws1+sql2+sql3;
	           if(barcode!=null && !barcode.equals("") && !(ownerArticleNo!=null && !ownerArticleNo.equals("")))
		   		{
		   			sql=sql1+ws1+ws2+sql2+sql3;
		   		}
	           if(barcode!=null && !barcode.equals("") && ownerArticleNo!=null && !ownerArticleNo.equals(""))
		   		{
		   			sql=sql1+ws1+ws2+sql2+ws3+sql3;
		   		}
	           if(!(barcode!=null && !barcode.equals("")) && ownerArticleNo!=null && !ownerArticleNo.equals(""))
		   		{
		   			sql=sql1+ws1+sql2+ws3+sql3;
		   		}
	List<Stock_ContentModel> list = genDao.getListByNativeSql(sql,Stock_ContentModel.class,start,1000000);
	List<Stock_ContentModel> list2=new ArrayList<Stock_ContentModel>();
	
	
	List<Stock_ContentModel> oldD=JSON.parseArray(oldOdata,Stock_ContentModel.class);
	
	int flag=0;
	
	for(int i=0; i<list.size();i++){
		for(int j=0; j<oldD.size();j++){
			
		   if(oldD.get(j).getArticleNo().endsWith(list.get(i).getArticleNo()) && 
			  oldD.get(j).getCellNo().equals(list.get(i).getCellNo())&&
			  oldD.get(j).getPackingQty()==list.get(i).getPackingQty() && 
			  oldD.get(j).getExpireDate().equals(list.get(i).getExpireDate()) &&
			  oldD.get(j).getProduceDate().equals(list.get(i).getProduceDate())&&
			  oldD.get(j).getQuality().equals(list.get(i).getQuality()) &&
			  oldD.get(j).getLabelNo().equals(list.get(i).getLabelNo())&&
			  oldD.get(j).getLotNo().equals(list.get(i).getLotNo())){
			   
			  flag=1;
			  break;
		   }
			   flag=0;   
		}
		if(flag==0){
			System.out.println(i);
			 list2.add(list.get(i));
			
		 }
	}
	
	if(oldD.size()>0){
		ExtListDataBo<Stock_ContentModel> extListBo = new ExtListDataBo<Stock_ContentModel>(list2, 0);
		return extListBo;
	}else{
		ExtListDataBo<Stock_ContentModel> extListBo = new ExtListDataBo<Stock_ContentModel>(list, 0);
		return extListBo;
	}
	
   
   
	}

	@Override
	public List<ComboxBo> getOdata_GetCombo(String enterpriseNo,String ownerNo,String warehouseNo, String flag,
			String str, Integer start, Integer pageSize) {
			String sql="";
			
			if(flag!=null && flag.equals("1")){
				sql="select distinct v1.owner_no value,v2.owner_name text," +
						"'['|| ltrim(v1.owner_no)||']'||v2.owner_name dropValue  " +
						"from bdef_defowner v2,odata_outstock_direct v1 " +
						"where v1.owner_no=v2.owner_no  " +
						"and v1.enterprise_no=v2.enterprise_no " +
						"and v1.outstock_type in('2','3','4') " +
						"and v1.status='10'" +
						"and v1.warehouse_no='"+warehouseNo+"' " +
						"and v1.enterprise_no='"+enterpriseNo+"' ";	
			}else if(flag!=null && flag.equals("2")){
				sql="select distinct v1.pick_type value,v2.text text," +
						"'['|| ltrim(v1.pick_type)||']'||v2.text dropValue  " +
						"from cset_cell_article v1,wms_deffieldval v2 " +
						"where v2.table_name='CSET_CELL_ARTICLE' and  v1.pick_type=v2.value " +
						"and v1.warehouse_no='"+warehouseNo+"' " +
						"and v1.enterprise_no='"+enterpriseNo+"' ";	
			}else if(flag!=null && flag.equals("3")){
				sql="  select distinct v1.ware_no value,v2.ware_Name text," +
						"'['|| ltrim(v1.ware_no)||']'|| v2.ware_Name dropValue  " +
						"from cset_cell_article v1 inner join cdef_defware v2 " +
						"on v1.warehouse_no=v2.warehouse_no " +
						"and v1.enterprise_no=v2.enterprise_no and v1.ware_no=v2.ware_no " +
						"and v1.warehouse_no='"+warehouseNo+"' " +
						"and v1.enterprise_no='"+enterpriseNo+"' ";
			}else if(flag!=null && flag.equals("4")){
				sql="select distinct v1.area_no value,v2.area_Name text," +
						"'['|| ltrim(v1.area_no)||']'|| v2.area_Name dropValue  " +
						"from cset_cell_article v1 inner join cdef_defarea v2 " +
						"on v1.area_no=v2.area_no and v1.enterprise_no=v2.enterprise_no " +
						"and v1.warehouse_no=v2.warehouse_no " +
						"and v1.ware_no=v2.ware_no " +
						"and v1.warehouse_no='"+warehouseNo+"' " +
						"and v1.enterprise_no='"+enterpriseNo+"' ";
			}else if(flag!=null && flag.equals("5")){
				sql=" select distinct v1.stock_no value,v1.stock_no text," +
						"v1.stock_no dropValue  " +
						" from cset_cell_article v1 " +						
						" where v1.warehouse_no='"+warehouseNo+"' " +
						" and v1.enterprise_no='"+enterpriseNo+"' ";	
			}
			if(str!=null && !str.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql+ws;
			}
			if(ownerNo!=null && !ownerNo.equals(""))
			{
				sql=sql+" and v1.owner_no in("+ownerNo+")";
			}else{
				sql=sql+" and 1=2";
			}
			List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
			return  (List<ComboxBo>)list;
		}

	@Override
	public List<ComboxBo> getMdata_GetCombo(String enterpriseNo,String ownerNo,
			String warehouseNo, String flag,String str2,
			String strWheresql,int start, int pageSize) {
		String sql="";
		if(flag!=null && flag.equals("1") ){
			sql=" select distinct v1.owner_article_no value,v1.owner_article_no text," +
			"'['|| ltrim(v1.owner_article_no)||']'||v1.owner_article_no dropValue  " +
			"from bdef_defarticle v1 "+
			"where v1.enterprise_no='"+enterpriseNo+"' " ;
			if( strWheresql!=null && !strWheresql.equals("")){
				 sql+=" and v1.owner_article_no like '%"+strWheresql+"%'";
			}
		}else if(flag!=null && flag.equals("2")){
			sql=" select distinct v1.cell_no value,v1.cell_no text," +
			"'['|| ltrim(v1.cell_no)||']'||v1.cell_no dropValue  " +
			"from stock_content v1 "+
			"where v1.warehouse_no='"+warehouseNo+"' " +
			"and v1.enterprise_no='"+enterpriseNo+"' "; 
			if( strWheresql!=null && !strWheresql.equals("")){
				 sql+=" and v1.cell_no like '%"+strWheresql+"%'" ;
			}
		}else if(flag!=null && flag.equals("3")){
			sql=" select distinct v1.barcode value,v1.barcode text," +
			"'['|| ltrim(v1.barcode)||']'||v1.barcode dropValue  " +
			"from bdef_defarticle v1 , stock_content v2 "+
			"where v1.enterprise_no=v2.enterprise_no " +
			"and v1.enterprise_no='"+enterpriseNo+"' " ;
			if( strWheresql!=null && !strWheresql.equals("")){
				sql+=" and v1.barcode like '%"+strWheresql+"%'" ;
			}
		}
		if(str2!=null && !str2.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str2);
			sql=sql+ws;
		}
		if(ownerNo!=null && !ownerNo.equals(""))
		{
			sql=sql+" and v1.owner_no in("+ownerNo+")";
		}else{
			sql=sql+" and 1=2";
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 10);
		return  (List<ComboxBo>)list;
	}

	@Override
	public MsgRes tscSend(String enterpriseNo,String warehouseNo,String workerNo,String str,String flag,String strDockNo) throws Exception {
		List<Stock_ContentModel> odd=JSON.parseArray(str,Stock_ContentModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		String outStockType=null;
		String back="N";
		outList.add("S");
		outList.add("S");
		//1安全量补货建单；2人工移库建单
		if(flag.equals("1")){
			outStockType="3";
			for(int i=0;i<odd.size();i++){
				List  inList=new ArrayList();
				
				inList.add(enterpriseNo);
				inList.add(warehouseNo);
				inList.add(odd.get(i).getOwnerNo());
				inList.add(workerNo);
				inList.add("1");
			    inList.add(flag.equals("1") ? "3":"4");
				inList.add(odd.get(i).getArticleNo());
				inList.add(odd.get(i).getPackingQty());
				inList.add(odd.get(i).getQty());
				inList.add(odd.get(i).getStockType());
				inList.add(odd.get(i).getStockValue());
				inList.add(odd.get(i).getDCellNo());
				inList.add(back);
			
				resultList=genDao.execProc(inList, outList, "PKLG_MDATA.P_mdata_HMPlanMove");
				
				
				if(resultList.get(1).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(1).toString());
				}else{
					back=resultList.get(0).toString();
				}
			}
		
			
		}else if(flag.equals("2")){
			outStockType="4";
			for(int i=0;i<odd.size();i++){
				List  inList=new ArrayList();
				inList.add(enterpriseNo);
				inList.add(warehouseNo);
				inList.add(odd.get(i).getOwnerNo());
				inList.add(workerNo);
				inList.add("1");
			    inList.add(flag.equals("1") ? "3":"4");//4人工移库
				inList.add(odd.get(i).getArticleNo());
				inList.add(odd.get(i).getPackingQty());
				inList.add(odd.get(i).getProduceDate());
				inList.add(odd.get(i).getExpireDate());
				inList.add(odd.get(i).getQuality());
				inList.add(odd.get(i).getLotNo());
				
				inList.add("N");//strRSV_BATCH1
				inList.add("N");//strRSV_BATCH2
				inList.add("N");//strRSV_BATCH3
				inList.add("N");//strRSV_BATCH4
				inList.add("N");//strRSV_BATCH5
				inList.add("N");//strRSV_BATCH6
				inList.add("N");//strRSV_BATCH7
				inList.add("N");//strRSV_BATCH8
				
				inList.add(odd.get(i).getQty());
				inList.add(odd.get(i).getStockType());
				inList.add(odd.get(i).getStockValue());
				inList.add(odd.get(i).getSCellNo());
				inList.add(odd.get(i).getLabelNo());
				inList.add(odd.get(i).getSubLabelNo());
				inList.add(odd.get(i).getDCellNo());
				inList.add(back);
			
				resultList=genDao.execProc(inList, outList, "PKLG_MDATA.P_MDATA_PlANMOVE");
				
				
				if(resultList.get(1).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(1).toString());
				}else{
					back=resultList.get(0).toString();
				}
			}
		}
		
		
//		List  inList2=new ArrayList();
//		List  outList2=new ArrayList();
//		List  resultList2=new ArrayList();
//		inList2.add(enterpriseNo);
//		inList2.add(warehouseNo);
//		inList2.add(odd.get(0).getOwnerNo());
//		inList2.add(resultList.get(0));
//		inList2.add(odd.get(0).getRgstName());
//		
//		outList2.add("S");
//		
//		resultList2=genDao.execProc(inList2, outList2, "PKLG_MDATA.P_mdata_Locate_Main");
//		if(resultList2.get(0).toString().indexOf("N|")!=-1){
//			throw new Exception(resultList2.get(0).toString());
//		}
		List  inList2=new ArrayList();
		List  outList2=new ArrayList();
		List  resultList2=new ArrayList();
		inList2.add(enterpriseNo);//strEnterPriseNo
		inList2.add(warehouseNo);//strWareHouseNo
		inList2.add(odd.get(0).getOwnerNo());//strOwnerNo
		inList2.add(resultList.get(0));//strPlanNo
		inList2.add(outStockType);//strOutStockType
		inList2.add("1");//strMoveSeqType
		inList2.add(strDockNo);//strDock_No
		inList2.add("1");//strReportType
		inList2.add(odd.get(0).getRgstName());//strUserId
		
		outList2.add("S");
		
		resultList2=genDao.execProc(inList2, outList2, "PKLG_MDATA.P_mdata_LocateAndSend");
		if(resultList2.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList2.get(0).toString());
		}
		
		return new MsgRes(true,"建单成功！",null);
	}

	/**
	 * 上传Excel导入数据库
	 */
	@Override
	public MsgRes cfgUpLoad(File file, String strWarehouseNo,
			String strCurrEnterpriseNo,String strWorkerNo) throws Exception {
		FileUtilSys.writeFile(file, "MdataTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		List<Mdata_PlanTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"MdataTmp.xlsx",strWarehouseNo,strCurrEnterpriseNo);
		
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strCurrEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_Create_base.P_mdata_planUpLoad");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,resultList.get(0).toString(),"");
	}
	
	
	/**
	 * Excel数据转List
	 */
	@Override
	public List<Mdata_PlanTmp> changeexcelBean(String fileName,
			String strWarehouseNo,String strCurrEnterpriseNo) throws Exception {
		List<Mdata_PlanTmp> iitList = new ArrayList<Mdata_PlanTmp>();
		//导入前删除临时表数据
		String delsql="delete mdata_plan_TMP a where a.enterprise_no='"+strCurrEnterpriseNo+"' ";
		genDao.updateBySql(delsql);
				
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Mdata_PlanTmp po = new Mdata_PlanTmp();
				Mdata_PlanTmpId id = new Mdata_PlanTmpId();
				id.setEnterpriseNo(strCurrEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);	
				id.setStatus("10");
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setOwnerNo(excelList.get(i).get(1).toString());
				id.setOwnerArticleNo(excelList.get(i).get(2).toString());
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(3).toString()));
				id.setProduceDate(sdf.parse(excelList.get(i).get(4).toString().equals("")?"1900-01-01":excelList.get(i).get(4).toString()));
				id.setExpireDate(sdf.parse(excelList.get(i).get(5).toString().equals("")?"1900-01-01":excelList.get(i).get(5).toString()));
				id.setLotNo(excelList.get(i).get(6).toString().equals("")?"N":excelList.get(i).get(6).toString());
				id.setQuality(excelList.get(i).get(7).toString().equals("")?"0":excelList.get(i).get(7).toString());
				
				id.setOriginQty(Double.parseDouble(excelList.get(i).get(8).toString().equals("")?"0":excelList.get(i).get(8).toString())*Double.parseDouble(excelList.get(i).get(3).toString())+Double.parseDouble(excelList.get(i).get(9).toString().equals("")?"0":excelList.get(i).get(9).toString()));	
				id.setOrgNo(excelList.get(i).get(10).toString());
				id.setSCellNo(excelList.get(i).get(11).toString().equals("")?"N":excelList.get(i).get(11).toString());
				id.setDCellNo(excelList.get(i).get(12).toString());
				id.setPlanRemark(excelList.get(i).get(13).toString().equals("")?"":excelList.get(i).get(13).toString());			
				id.setRsvBatch1(excelList.get(i).get(14).toString().equals("")?"N":excelList.get(i).get(14).toString());
				id.setRsvBatch2(excelList.get(i).get(15).toString().equals("")?"N":excelList.get(i).get(15).toString());
				id.setRsvBatch3(excelList.get(i).get(16).toString().equals("")?"N":excelList.get(i).get(16).toString());
				id.setRsvBatch4(excelList.get(i).get(17).toString().equals("")?"N":excelList.get(i).get(17).toString());
				id.setRsvBatch5(excelList.get(i).get(18).toString().equals("")?"N":excelList.get(i).get(18).toString());
				id.setRsvBatch6(excelList.get(i).get(19).toString().equals("")?"N":excelList.get(i).get(19).toString());
				id.setRsvBatch7(excelList.get(i).get(20).toString().equals("")?"N":excelList.get(i).get(20).toString());
				id.setRsvBatch8(excelList.get(i).get(21).toString().equals("")?"N":excelList.get(i).get(21).toString());

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
						if(j==3 || j==8 || j==9){	
							
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

	//储位下拉，暂存区只允许同区域移，作业区不能移到暂存区
	//str：目的储位，strWheresql：来源储位
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,
			String warehouseNo, String str, String strWheresql, int i, int j) {
		//查询来源储位的储区信息
		String sqls = "select area.ware_no,area.area_no," +
				"area.area_name,area.AREA_ATTRIBUTE  " +
				"from cdef_defcell cell,cdef_defarea area,cdef_defware ware  " +
				"where cell.cell_no='"+strWheresql+"'" +
				"and cell.enterprise_no=ware.enterprise_no " +
				"and cell.warehouse_no=ware.warehouse_no " +
				"and cell.ware_no=ware.ware_no and ware.ware_no=area.ware_no " +
				"and cell.area_no=area.area_no " +
				"and cell.enterprise_no=area.enterprise_no " +
				"and cell.warehouse_no=area.warehouse_no " +
				"and cell.enterprise_no='"+enterpriseNo+"' " +
				"and cell.warehouse_no='"+warehouseNo+"' ";
		List<Cdef_DefareaModel> arealist=genDao.getListByNativeSql(sqls, Cdef_DefareaModel.class);

		String sql = "";
		//如果来源储位是暂存区
		if(arealist.get(0).getAreaAttribute().equals("1")){
			sql = "select cell.cell_no value,cell.cell_no text,cell.cell_no dropValue  "+
				  "from Cdef_Defcell cell  "+
				  "where cell.cell_status='0' "+
				  "and cell.warehouse_No='001' "+
				  "and cell.enterprise_no = '8888' "+
                  "and cell.area_no='"+arealist.get(0).getAreaNo()+"' "+
                  "and cell.cell_no<>'"+strWheresql+"' ";
		}else{
			sql = "select cell.cell_no value,cell.cell_no text,cell.cell_no dropValue "+ 
					 "from Cdef_Defcell cell,cdef_defarea area,cdef_defware ware   "+ 
					 "where cell.cell_status='0' "+ 
					 "and cell.warehouse_No='"+warehouseNo+"' "+ 
					 "and cell.enterprise_no = '"+enterpriseNo+"' "+ 
					"and cell.enterprise_no=ware.enterprise_no " +
					"and cell.warehouse_no=ware.warehouse_no " +
					"and cell.ware_no=ware.ware_no and ware.ware_no=area.ware_no " +
			         "and cell.area_no=area.area_no "+ 
			         "and cell.enterprise_no=area.enterprise_no "+ 
			         "and cell.warehouse_no=area.warehouse_no "+ 
			         "and area.area_attribute<>'1' "+
			         "and cell.cell_no<>'"+strWheresql+"' ";
		}
		
		if (str != null && !str.equals("")) {
			sql = sql + "and cell.cell_no like '%" + str + "%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	/*
	 * 获得移库导入失败信息
	 * hkl
	 */
	@Override
	public ExtListDataBo<Mdata_PlanMTmpModel> movefailList(String enterpriseNo,
			String warehouseNo) {
		String sql = "select t.owner_no,t.owner_article_no," +
				"t.origin_qty,t.packing_qty,t.s_cell_no,t.d_cell_no," +
				"t.plan_remark,t.org_no, " +
				"f_get_packingunit(t.enterprise_no,t.owner_no,bda.article_no,t.packing_qty) packingUnit,"+
				"f_get_packingunit(t.enterprise_no,t.owner_no,bda.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(t.enterprise_no,t.owner_no,bda.article_no,bda.unit_packing) Unit,"+
				"f_get_spec(t.enterprise_no,t.owner_no,bda.article_no,t.packing_qty) packingSpec,"+
				"f_get_spec(t.enterprise_no,t.owner_no,bda.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(t.enterprise_no,t.owner_no,bda.article_no,bda.unit_packing) spec,"+
				"trunc(t.origin_qty/t.packing_qty) as planBox,"+
				"trunc(mod(t.origin_qty,t.packing_qty)/bda.QMIN_OPERATE_PACKING) as planQmin,"+
				"(t.origin_qty - trunc(t.origin_qty/t.packing_qty) * t.packing_qty - trunc(mod(t.origin_qty,t.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as planDis "+
				"from mdata_plan_tmp t,bdef_defarticle bda " +
				"where t.enterprise_no='"+enterpriseNo+"' " +
				"and t.warehouse_no='"+warehouseNo+"' "+
				" and t.enterprise_no=bda.enterprise_no "+
				"and t.owner_no=bda.owner_no "+
				"and t.owner_article_no=bda.owner_article_no";
		
		List<Mdata_PlanMTmpModel> list = genDao.getListByNativeSql(sql, Mdata_PlanMTmpModel.class);
		ExtListDataBo<Mdata_PlanMTmpModel> extListBo= new ExtListDataBo<Mdata_PlanMTmpModel>(list, 0);
		
		return extListBo;
	}

	
}
