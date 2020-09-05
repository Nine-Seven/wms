package com.sealinkin.report.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.report.service.IReport_Service;
import com.sealinkin.cdef.model.Cdef_DefwareModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.report.model.ArticleInvAccReportModel;
import com.sealinkin.report.model.Idata_Check_DSourceReportModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.wms.model.Wms_DefSearch_DModel;
import com.sealinkin.wms.model.Wms_DefSearch_MModel;
import com.sealinkin.wms.model.Wms_DefsearchDSetModel;
import com.sealinkin.wms.po.Wms_DefsearchDSet;

@SuppressWarnings({"unchecked","rawtypes"})
public class Report_Impl implements IReport_Service{
	private IGenericManager genDao;
    
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }
	
	@Override
	public ExtListDataBo<Idata_Check_DSourceReportModel> getSourceReportList()throws Exception {
		String sql=" SELECT icm.WAREHOUSE_NO,"
		+" bds.supplier_no,"
		+" bds.supplier_name,"
		+" icm.import_no,"
		+" sm.po_no,"
		+" icm.s_import_no,"
		+" MM.serial_no,"
		+" bda.owner_article_no,"
		+" bda.article_no,"
		+" bda.article_name,"
		+" bda.style,"
		+" bda.color_desc,"
		+" bda.size_desc,"
		+" ICP.packing_qty,"
		+" SD.po_qty,"
		+" (SD.po_qty - SD.import_qty) marginQty,"
		+" ICP.CHECK_QTY,"
		+" DECODE (icd.quality,"
		+"         '0', '合格品',"
		+"         '2', 'B品',"
		+"         'A', '不合格品',"
		+"         '1', '待验',"
		+"         '3', 'C品')"
		+"    quality,"
		+" bda.GROUP_NO,"
		+" bda.GROUP_NAME,"
		+" bda.M_GROUP_NO,"
		+" bda.M_GROUP_NAME,"
		+" bda.L_GROUP_NO,"
		+" bda.L_GROUP_NAME,"
		+" FLOOR (ICP.CHECK_QTY / ICP.packing_qty) box_qty,"
		+" MOD (ICP.CHECK_QTY, ICP.packing_qty) ling_qty,"
		+" DECODE (icm.status,"
		+"         '10', '建单',"
		+"         '11', '正在验收',"
		+"         '12', '验收完成未结案',"
		+"         '13', '已结案',"
		+"         '')"
		+"    status,"
		+" TRUNC (icm.check_end_date) check_end_date,"
		+" TRUNC (icd.check_end_date) check_date,"
		+" TRUNC (icm.updt_date) import_end_date,"
		+" icm.check_end_date check_end_time,"
		+" icd.check_end_date check_time,"
		+" icm.updt_date import_end_time,"
		+" BDW.worker_name,"
		+" ICD.batch_serial_no,"
		+" Bda.SEASON,"
		+" Bda.Yearmon"
		+" FROM IDATA_CHECK_D icd"
		+" INNER JOIN IDATA_check_m icm"
		+"    ON     ICM.WAREHOUSE_NO = ICD.WAREHOUSE_NO"
		+"       AND ICM.OWNER_NO = ICD.OWNER_NO"
		+"       AND ICM.CHECK_NO = ICD.CHECK_NO"
		+" INNER JOIN IDATA_CHECK_PAL ICP"
		+"    ON     ICP.WAREHOUSE_NO = ICM.WAREHOUSE_NO"
		+"       AND ICP.OWNER_NO = ICM.OWNER_NO"
		+"       AND ICP.S_CHECK_NO = ICM.S_CHECK_NO"
		+"       AND ICP.CHECK_NO = ICM.CHECK_NO"
		+"       AND ICP.CHECK_ROW_ID = ICD.ROW_ID"
		+"       AND ICP.ARTICLE_NO = ICD.ARTICLE_NO"
		+"       AND ICP.PACKING_QTY = ICD.PACKING_QTY"
		+" INNER JOIN (SELECT WAREHOUSE_NO,"
		+"                    OWNER_NO,"
		+"                    CONTAINER_NO,"
		+"                    SOURCE_NO,"
		+"                    ARTICLE_NO,"
		+"                    PACKING_QTY,"
		+"                    STATUS,"
		+"                    RGST_NAME,"
		+"                    SCAN_LABEL_NO"
		+"               FROM IDATA_LOCATE_DIRECT"
		+"             UNION"
		+"             SELECT WAREHOUSE_NO,"
		+"                    OWNER_NO,"
		+"                    CONTAINER_NO,"
		+"                    SOURCE_NO,"
		+"                    ARTICLE_NO,"
		+"                    PACKING_QTY,"
		+"                    STATUS,"
		+"                    RGST_NAME,"
		+"                    SCAN_LABEL_NO"
		+"               FROM IDATA_LOCATE_DIRECTHTY) IID"
		+"    ON     IID.WAREHOUSE_NO = ICM.WAREHOUSE_NO"
		+"       AND IID.OWNER_NO = ICM.OWNER_NO"
		+"       AND IID.SOURCE_NO = ICM.S_CHECK_NO"
		+"       AND IID.ARTICLE_NO = ICD.ARTICLE_NO"
		+"       AND IID.PACKING_QTY = ICD.PACKING_QTY"
		+"       AND IID.CONTAINER_NO = ICP.CONTAINER_NO"
		+"       AND IID.SCAN_LABEL_NO = ICP.SCAN_LABEL_NO"
		+" INNER JOIN bdef_defworker bdw"
		+"    ON bdw.worker_no = iid.RGST_NAME"
		+" INNER JOIN (SELECT WAREHOUSE_NO,"
		+"                    owner_no,"
		+"                    s_import_no,"
		+"                    article_no,"
		+"                    po_qty,"
		+"                    import_qty,"
		+"                    PACKING_QTY"
		+"               FROM IDATA_import_sd"
		+"             UNION"
		+"             SELECT WAREHOUSE_NO,"
		+"                    owner_no,"
		+"                    s_import_no,"
		+"                    article_no,"
		+"                    po_qty,"
		+"                    import_qty,"
		+"                    PACKING_QTY"
		+"               FROM IDATA_import_sdhty) SD"
		+"    ON     ICM.WAREHOUSE_NO = SD.WAREHOUSE_NO"
		+"       AND ICM.OWNER_NO = SD.OWNER_NO"
		+"       AND ICM.S_IMPORT_NO = SD.S_IMPORT_NO"
		+"       AND ICD.ARTICLE_NO = SD.ARTICLE_NO"
		+"       AND ICD.PACKING_QTY = SD.PACKING_QTY"
		+" INNER JOIN (SELECT WAREHOUSE_NO,"
		+"                    owner_no,"
		+"                    s_import_no,"
		+"                    serial_no,"
		+"                    supplier_no"
		+"               FROM IDATA_import_mm"
		+"             UNION"
		+"             SELECT WAREHOUSE_NO,"
		+"                    owner_no,"
		+"                    s_import_no,"
		+"                    serial_no,"
		+"                    supplier_no"
		+"               FROM IDATA_import_mmhty) mm"
		+"    ON     ICM.WAREHOUSE_NO = MM.WAREHOUSE_NO"
		+"       AND ICM.OWNER_NO = MM.OWNER_NO"
		+"       AND ICM.S_IMPORT_NO = MM.S_IMPORT_NO"
		+" INNER JOIN bdef_defsupplier bds"
		+"    ON bds.owner_no = mm.owner_no"
		+"       AND bds.supplier_no = mm.supplier_no"
		+" INNER JOIN (SELECT WAREHOUSE_NO,"
		+"                    owner_no,"
		+"                    s_import_no,"
		+"                    import_no,"
		+"                    po_no"
		+"               FROM IDATA_import_sm"
		+"             UNION"
		+"             SELECT WAREHOUSE_NO,"
		+"                    owner_no,"
		+"                    s_import_no,"
		+"                    import_no,"
		+"                    po_no"
		+"               FROM IDATA_import_smhty) sm"
		+"    ON     ICM.WAREHOUSE_NO = SM.WAREHOUSE_NO"
		+"       AND ICM.OWNER_NO = SM.OWNER_NO"
		+"       AND ICM.S_IMPORT_NO = SM.S_IMPORT_NO"
		+"       AND ICM.IMPORT_NO = SM.IMPORT_NO"
		+" INNER JOIN bdef_defarticle bda"
		+"    ON BDA.ARTICLE_NO = ICD.ARTICLE_NO";
		
	    List<Idata_Check_DSourceReportModel> list = genDao.getListByNativeSql(sql,Idata_Check_DSourceReportModel.class,
	    		0,10000);
		//Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_Check_DSourceReportModel> extListBo= new ExtListDataBo<Idata_Check_DSourceReportModel>(list, 0);
       return extListBo;
	}
	
	//获取报表显示数据
	public ExtListDataBo getGridData(
			String enterpriseNo,String warehouseNo,String ownerNo,String pgmId,
			String str,PageBo pageBo,Integer requestFlag,String strName) throws Exception{	
		
		String sql="";
		String preparedSql="select * from wms_defsearch_m where pgm_id='"+pgmId+"'";
		
		List<Wms_DefSearch_MModel> listSearch_m=genDao.getListByNativeSql(preparedSql, Wms_DefSearch_MModel.class);
		if(listSearch_m.isEmpty()){
			return null;
		}
		//若需要预处理
		if(!StringUtil.isEmpty(listSearch_m.get(0).getBeforeTreatment())){
			excebeforeTreatment(pgmId, 1);
		}
		//取数据sql
		sql=listSearch_m.get(0).getPreparedSql();					
		//替换查询参数	
		if(listSearch_m.get(0).getNeedEnterpriseNo().intValue()==1){
			sql=sql.replace("%s0", " and enterprise_no='"+enterpriseNo+"'");
		}else{
			sql=sql.replace("%s0", "");
		}
		
		if(listSearch_m.get(0).getNeedLoc().intValue()==1){
			sql=sql.replace("%s1", " and warehouse_no='"+warehouseNo+"'");
		}else{
			sql=sql.replace("%s1", "");
		}
		if(listSearch_m.get(0).getNeedOwner().intValue()==1){
			sql=sql.replace("%s2", " and owner_no in ("+ownerNo+")");
		}else{
			sql=sql.replace("%s2", "");
		}
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql.replace("%s3", ws);
		}else{
			sql=sql.replace("%s3", "");
		}			
		String strAutoValue="";
		if(!StringUtil.isEmpty(strName)){
			String strNames = URLDecoder.decode(strName, "utf-8");  
			strAutoValue  =  "select *  " +
					"from " +
						"wms_defsearch_d_set a " +
					"where " +
						"a.pgm_id='"+pgmId+"' " +
						"and a.name='"+strNames+"'  " +
					"order " +
						"by a.seq";		
			
			String strFinField="";
			String strGroField="";
			String strOrdField="";
			List<Wms_DefsearchDSetModel>  searchDSet=  genDao.getListByNativeSql(strAutoValue,Wms_DefsearchDSetModel.class);
			for (Wms_DefsearchDSetModel w : searchDSet) {
				if("1".equals(w.getStatisticsFormulae())){
					strFinField+=" count("+w.getFieldId()+") as "+w.getFieldId()+",";
				}else if("2".equals(w.getStatisticsFormulae())){
					strFinField+=" sum("+w.getFieldId()+") as "+w.getFieldId()+",";
				}else if("3".equals(w.getStatisticsFormulae())){
					strFinField+=w.getFieldId()+" as "+w.getFieldId()+",";
					strGroField+=w.getFieldId()+" ,";
				}else if("4".equals(w.getStatisticsFormulae())){
					strFinField+=" max("+w.getFieldId()+") as "+w.getFieldId()+",";
				}else if("5".equals(w.getStatisticsFormulae())){
					strFinField+=" min("+w.getFieldId()+") as "+w.getFieldId()+",";
				}else if("6".equals(w.getStatisticsFormulae())){
					strFinField+=" count(distinct "+w.getFieldId()+") as "+w.getFieldId()+",";
				}else{
					strFinField+=w.getFieldId()+" ,";
				}
				
				if("1".equals(w.getOrderType())){
					strOrdField+=w.getFieldId()+" ,";
				}else if("2".equals(w.getOrderType())){
					strOrdField+=w.getFieldId()+" desc ,";
				}
			}
			sql="select "+strFinField.substring(0, strFinField.length()-1)+" from ("+sql+") wdm ";				
			if(strGroField.length()>0){
				sql+=" group by "+strGroField.substring(0, strGroField.length()-1);
			}
			if(strOrdField.length()>0){
				sql+=" order by "+strOrdField.substring(0, strOrdField.length()-1);
			}
		}else{
			//显示字段sql
			strAutoValue  =  "select " +
								" to_char(wmsys.wm_concat('wdm.'||a.field_id))  " +
							 "from ( " +
							 	"select *  " +
							 	"from " +
							 		"wms_defsearch_d a " +
						 		"where " +
						 			"a.pgm_id='"+pgmId+"' " +
			 					"order by a.seq" +
		 					 ") a";
			
			List strFindAtt1  =  genDao.getListByNativeSql(strAutoValue);
			sql="select "+strFindAtt1.get(0)+" from ("+sql+") wdm ";
			
		}
		
		List list =null; 
		Integer count = 0; 
		ExtListDataBo<Cdef_DefwareModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql, pageBo.getStart(), pageBo.getPagesize());	
			count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Cdef_DefwareModel>(list, count);
		
		
		//查询后，处理
		if(!StringUtil.isEmpty(listSearch_m.get(0).getAfterTreatment())){
			excebeforeTreatment(pgmId, 2);
		}
	    return extListBo;		
		
	}
	//获取报表显示字段
	public List<Wms_DefSearch_DModel> getGridColumModle(String str, String strName) throws Exception {
		String sql="";
		if(strName!=null && !strName.equals(""))
		{
			sql= "select regexp_replace(lower(a.field_id),'_','') field_id,a.field_name,a.FIELD_TYPE,decode(b.width,0,a.width,b.width) width from WMS_DEFSEARCH_D a" +
					" inner join wms_defsearch_d_set b on b.field_id=a.field_id " +
					" and a.pgm_id=b.pgm_id and a.field_id=b.field_id and b.pgm_id='"+str+"'" +
							" and b.name='"+strName+"' order by b.seq";
		}else
		{
			 sql="select regexp_replace(lower(field_id),'_','') field_id,field_name,FIELD_TYPE," +
					"width from WMS_DEFSEARCH_D where pgm_id='"+str+"' order by seq";
		}
		List<Wms_DefSearch_DModel> list=genDao.getListByNativeSql(sql, Wms_DefSearch_DModel.class);
		return list;
	}
	@Override
	public MsgRes getTotalData(String enterpriseNo,String warehouseNo,String ownerNo,String pgmId,
			String str,String strName) throws Exception {
		MsgRes msgRes=new MsgRes(); 
		//取合计字段
		String sql="";
		//显示字段
		if(strName!=null && !strName.equals(""))
		{
			String strNames = URLDecoder.decode(strName, "utf-8"); 
			sql="select to_char(wmsys.wm_concat('sum('||b.field_id||') as '||b.field_id)) field_id " +
				"from ( " +
					"select " +
						"b.field_id " +
					"from " +
						"wms_defsearch_d_set a," +
						"wms_defsearch_d b " +
					"where " +
						"a.pgm_id=b.pgm_id " +
						"and a.field_id=b.field_id " +
						"and b.statistics_flag=1 " +
						"and b.pgm_id='"+pgmId+"' " +
						"and a.name='"+strNames+"' " +
					"order by a.seq " +
				") b ";
		}else
		{
			sql="select to_char(wmsys.wm_concat('sum('||b.field_id||') as '||b.field_id)) field_id " +
				"from ( " +
					"select " +
						"field_id " +
					"from " +
						"WMS_DEFSEARCH_D d " +
					"where " +
						"pgm_id='"+pgmId+"' " +
						"and d.statistics_flag=1 " +
					"order by seq" +
				") b";
		}
		
		
		List<Wms_DefSearch_DModel> list=genDao.getListByNativeSql(sql, Wms_DefSearch_DModel.class);
		if(list.get(0).getFieldId()==null || "".equals(list.get(0).getFieldId()))
		{
			msgRes.setIsSucc(false);
			return msgRes;
		}
		//取报表查询sql
		sql=" select * from WMS_DEFSEARCH_m where pgm_id='"+pgmId+"' " ;
		List<Wms_DefSearch_MModel> listSearch_m=genDao.getListByNativeSql(sql, Wms_DefSearch_MModel.class);
		if(listSearch_m.isEmpty())
		{
			msgRes.setIsSucc(false);
			return msgRes;
		}
		sql=listSearch_m.get(0).getPreparedSql();		
		
		if(listSearch_m.get(0).getNeedEnterpriseNo().intValue()==1){
			sql=sql.replace("%s0", " and enterprise_no='"+enterpriseNo+"'");
		}else{
			sql=sql.replace("%s0", "");
		}
		if(listSearch_m.get(0).getNeedLoc().toString().equals("1")){
			sql=sql.replace("%s1", " and warehouse_no='"+warehouseNo+"'");
		}else{
			sql=sql.replace("%s1", "");
		}
		if(listSearch_m.get(0).getNeedOwner().toString().equals("1")){
			sql=sql.replace("%s2", " and owner_no in ("+ownerNo+")");
		}else{
			sql=sql.replace("%s2", "");
		}
		String ws="";
		if(str!=null && !str.equals(""))
		{
			ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql.replace("%s3", ws);
		}else{
			sql=sql.replace("%s3", "");
		}
		
		//合计数据
		List listTotalData =null;
		listTotalData = genDao.getListByNativeSql("select "+list.get(0).getFieldId()+" from ("+sql+")");
		Object[] obj=null;
		if (listTotalData.get(0) instanceof Object[]) {
			obj = (Object[]) listTotalData.get(0);
		}else
		{
			obj =new Object[]{listTotalData.get(0).toString()};
		}
		Integer index=0;
		
		//显示字段
		if(strName!=null && !strName.equals(""))
		{
			String strNames = URLDecoder.decode(strName, "utf-8"); 
			//显示字段sql
			sql  =  "select b.* " +
					"from " +
						"wms_defsearch_d_set a," +
						"wms_defsearch_d b " +
					"where " +
						"a.pgm_id=b.pgm_id " +
						"and a.field_id=b.field_id " +
						"and b.pgm_id='"+pgmId+"' " +
						"and a.name='"+strNames+"'  " +
					"order by a.seq";					
		}else
		{
			sql="select * " +
				"from " +
					"WMS_DEFSEARCH_D d " +
				"where " +
					"d.pgm_id='"+pgmId+"' " +
				"order by seq";
		}
		List<Wms_DefSearch_DModel> listSearch_d=genDao.getListByNativeSql(sql, Wms_DefSearch_DModel.class);
		Object[] objTotal=new Object[listSearch_d.size()];
		for (int i = 0; i < listSearch_d.size(); i++) {
			//如果显示字段需要合计
			if("1".equals(listSearch_d.get(i).getStatisticsFlag()))
			{
				objTotal[i]=obj[index];
				index++;
			}else {
				objTotal[i]=i==0?"合计：":"";
			}
		}
		
		msgRes.setIsSucc(true);
		msgRes.setObj(objTotal);
			
		return msgRes;
	}
	//获得对应模块下的报表
	public List<ComboxBo> getReportList(
			String strEnterpriseNo,String moduleId) {
		/*if(moduleId!=null && !moduleId.equals(""))
		{*/
		String strSql = "select distinct a.PGM_ID value,b.proc_name text," +
				"'['|| ltrim(a.PGM_ID)||']'||b.proc_name dropValue, " +
				"a.order_no from WMS_DEFREPORTFORMENU a," +
				"wms_defsearch_m b where a.pgm_id=b.pgm_id " +
				"and a.enterprise_no= "+strEnterpriseNo +
				" and a.show='1' " +
				"and a.module_id='"+moduleId+"' order by a.order_no";
		//}
		List<ComboxBo> list=genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	}
	
	//获得对应模块下的参考项
	public List<ComboxBo> getReferenceItemList(String referenceItem) {
		String strSql = "select distinct a.name value,a.name text," +
				" a.name dropValue  " +
				" from wms_defsearch_d_set a where " +
				" a.pgm_id='"+referenceItem+"' order by a.name";
		
		List<ComboxBo> list=genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	}
	
	//获取高阶查询设置项
	public ExtListDataBo<Wms_DefsearchDSetModel> getWmsDefsearckDSetList(
			String strName,String referenceItem) {
		String strSql = "";
		String strCount = "";
		ExtListDataBo<Wms_DefsearchDSetModel> extListBo=null;
		if(strName != null && !strName.equals(""))
		{
			 strSql = " select distinct b.pgm_id," +
							" a.name, " +
							"b.field_id," +
							"decode(a.width,0,b.width,null,b.width,a.width) width, " +
							"b.seq, " +
							"a.order_type," +
							"a.statistics_flag, " +
							" case when a.field_id is not null then 'true' else 'false' end showFlag, " +
							" case when a.statistics_flag=1 then 'true' else 'false' end statisticsFlagText, " +
							" b.field_name," +
							"a.statistics_formulae," +
							"b.field_type  " +
						"from" +
							" wms_defsearch_d b " +
							" left join" +
							" wms_defsearch_d_set a " +
							" on a.pgm_id=b.pgm_id " +
							" and a.field_id=b.field_id  " +
							" and a.name='"+strName+"' " +
						" where b.pgm_id='"+referenceItem+"' " +
						" order by b.seq";
			strCount = "select count(*) from" +
					" wms_defsearch_d b left join" +
					" wms_defsearch_d_set a on a.pgm_id=b.pgm_id " +
					" and a.field_id=b.field_id and a.name='"+strName+"' " +
					" where b.pgm_id='"+referenceItem+"'  ";
		}else{
			 strSql = " select distinct b.pgm_id," +
						" null as name, b.field_id, " +
						" b.seq as seq, null as order_type, " +
						" 'false' as showFlag,b.width,'false' as statisticsFlagText, " +
						"b.field_name,b.field_type  from" +
						" wms_defsearch_d b " +
						" where b.pgm_id='"+referenceItem+"' " +
						" order by b.seq ";
				strCount = " select count(1) from" +
						" wms_defsearch_d b " +
						" where b.pgm_id='"+referenceItem+"' " +
						" order by b.seq ";
		}
		List<Wms_DefsearchDSetModel> list=genDao.getListByNativeSql(strSql, Wms_DefsearchDSetModel.class);
		Integer count = genDao.getCountByNativeSql(strCount);
		extListBo= new ExtListDataBo<Wms_DefsearchDSetModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 参数设置保存
	 */
	public MsgRes SaveReportSet(String strParams) {
		List<Wms_DefsearchDSet> wdsd=JSON.parseArray(strParams, Wms_DefsearchDSet.class);
		String strDelete = "   delete from wms_defsearch_d_set a where a.pgm_id='"
		+wdsd.get(0).getId().getPgmId()+"' and a.name='"+wdsd.get(0).getId().getName()+"'";
		genDao.updateBySql(strDelete);
		this.genDao.saveList(wdsd);
		return new MsgRes(true,"保存成功","");	 
	}
	
	//重命名
	public MsgRes SaveRename(String strNewName, String strOldName,
			String strPgmId) {
		String strSql = "update wms_defsearch_d_set a set a.name='"+strNewName
				+"' where a.name='"+strOldName+"' and a.pgm_id='"+strPgmId+"'";
		genDao.exceuteSql(strSql);
		return new MsgRes(true,"保存成功","");	 
	}
	
	//另存为
	public MsgRes SaveOtherReportSet(String strParams) {
		List<Wms_DefsearchDSet> wdsd=JSON.parseArray(strParams, Wms_DefsearchDSet.class);
		this.genDao.saveList((List<Wms_DefsearchDSet>) wdsd);
		return new MsgRes(true,"保存成功","");	 
	}
	//检验参考项目名称是否已经存在
	public MsgRes checkProjectName(String strPgmId, String strName)throws Exception{
		String strSql = " select a.* from wms_defsearch_d_set a where a.name='"+strName+"' " +
				"and a.pgm_id='"+strPgmId+"'";
		List<Wms_DefsearchDSetModel> list=genDao.getListByNativeSql(strSql, Wms_DefsearchDSetModel.class);
		if(list.size()==0){
			return new MsgRes(true,"","");	
		}else
		{
			return new MsgRes(false,"该参考项目名称已经存在","");	
		}
	}
	
	//执行预处理语句
	public MsgRes excebeforeTreatment(String strPgmId, Integer nFlag)throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");			
		inList.add(strPgmId);
		inList.add(nFlag);
		resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_exce_beforeTreatment");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "", "");
	}	
	@Override
	public ExtListDataBo<ArticleInvAccReportModel> getArticleInvAccData(
			String warehouseNo,
			String ownerNo,
			String beginDate, 
			String endDate,
			String str,
			PageBo pageBo, 
			Integer requestFlag) throws Exception {
		//生成数据
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(DateUtil.GetDate(beginDate, "yyyy-MM-dd"));
		inList.add(DateUtil.GetDate(endDate, "yyyy-MM-dd"));
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_REPORT.p_create_articlinvacc_view");
		if(resultList.get(0).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		
		//查询
		String strSql="select a.*,b.owner_article_no,b.article_name,c.owner_name " +
				"from v_articlInvAcc a,bdef_defarticle b,bdef_defowner  c " +
				"where a.article_no=b.article_no " +
				"and a.owner_no=c.owner_no ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		if(!StringUtil.isEmpty(warehouseNo)){
			strSql+=" and a.warehouse_no='"+warehouseNo+"'";
		}
		
		if(!StringUtil.isEmpty(ownerNo)){
			strSql+=" and a.owner_no in ("+ownerNo+")";
		}
		
		strSql+=" order by b.owner_article_no ";
        List<ArticleInvAccReportModel> list = null;
		Integer count = 0;
		ExtListDataBo<ArticleInvAccReportModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(strSql,ArticleInvAccReportModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql("select count(*) from ("+strSql+") ");
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(strSql,ArticleInvAccReportModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<ArticleInvAccReportModel>(list, count);
        return extListBo;
	}
	@Override
	public List exportArticleInvAccData(String warehouseNo, String ownerNo,
			String strDate, String strQuery,
			int beginYear,
			int beginMonth,
			int beginDay,
			int endYear,
			int endMonth,
			int endDay,
			String str) throws Exception {
		//查询
		String strSql="select " +
				"a.owner_no," +
				"c.owner_name," +
				"b.owner_article_no," +
				"b.article_name," +
				"a.qcqty, " +
				"d.qcweight," ;
//		for(int i=1;i<=Integer.parseInt(str);i++)
//		{
//			strSql+="a.inqtyday"+i+",";
//			strSql+="a.outqtyday"+i+",";
//			strSql+="d.inweight"+i+",";
//			strSql+="d.outweight"+i+",";
//		}
		//同一年
		String day=null;
		if(beginYear==endYear){
			if(beginMonth==endMonth){
				for(int i=beginDay; i<=endDay;i++){
					day=String.format("%02d", endMonth)+String.format("%02d", i);
					strSql+="a.inqtyday"+day+",";
					strSql+="d.inweight"+day+",";
					strSql+="a.outqtyday"+day+",";
					strSql+="d.outweight"+day+",";
				}
			}else{
				for(int i=beginMonth; i<=endMonth;i++){
					int maxDay = getDaysByYearMonth(beginYear,i);
					if(i==beginMonth){
						for(int j=beginDay; j<=maxDay;j++){
							day=String.format("%02d", i)+String.format("%02d", j);
							strSql+="a.inqtyday"+day+",";
							strSql+="d.inweight"+day+",";
							strSql+="a.outqtyday"+day+",";
							strSql+="d.outweight"+day+",";
						}			
					}else if(i==endMonth){
						for(int j=1; j<=endDay;j++){
							day=String.format("%02d", i)+String.format("%02d", j);
							strSql+="a.inqtyday"+day+",";
							strSql+="d.inweight"+day+",";
							strSql+="a.outqtyday"+day+",";
							strSql+="d.outweight"+day+",";
						}			
					}else{
						for(int j=1; j<=maxDay;j++){
							day=String.format("%02d", i)+String.format("%02d", j);
							strSql+="a.inqtyday"+day+",";
							strSql+="d.inweight"+day+",";
							strSql+="a.outqtyday"+day+",";
							strSql+="d.outweight"+day+",";
						}	
					}
				}			
			}		
		}else{  //跨年
			for(int i=beginYear;i<=endYear;i++){
				if(i==beginYear){
					for(int j=beginMonth; j<=12;j++){
						int maxDay =getDaysByYearMonth(beginYear,j);
						if(j==beginMonth){
							for(int x=beginDay; x<=maxDay;x++){
								day=String.format("%02d", j)+String.format("%02d", x);
								strSql+="a.inqtyday"+day+",";
								strSql+="d.inweight"+day+",";
								strSql+="a.outqtyday"+day+",";
								strSql+="d.outweight"+day+",";
							}
						}else{
							for(int x=1; x<=maxDay;x++){
								day=String.format("%02d", j)+String.format("%02d", x);
								strSql+="a.inqtyday"+day+",";
								strSql+="d.inweight"+day+",";
								strSql+="a.outqtyday"+day+",";
								strSql+="d.outweight"+day+",";
							}
						}
					}
				}else if(i==endYear){
					for(int j=1; j<=endMonth;j++){
						int maxDay = getDaysByYearMonth(beginYear,j);
						
						if(j==endMonth){
							for(int x=1; x<=endDay;x++){
								day=String.format("%02d", j)+String.format("%02d", x);
								strSql+="a.inqtyday"+day+",";
								strSql+="d.inweight"+day+",";
								strSql+="a.outqtyday"+day+",";
								strSql+="d.outweight"+day+",";
							}					
						}else{
							for(int x=1; x<=maxDay;x++){
								day=String.format("%02d", j)+String.format("%02d", x);
								strSql+="a.inqtyday"+day+",";
								strSql+="d.inweight"+day+",";
								strSql+="a.outqtyday"+day+",";
								strSql+="d.outweight"+day+",";
							}
						}
					}
				}else{
					for(int j=1; j<=12;j++){
						int maxDay = getDaysByYearMonth(beginYear,j);
						for(int x=1; x<=maxDay;x++){
							day=String.format("%02d", j)+String.format("%02d", x);
							strSql+="a.inqtyday"+day+",";
							strSql+="d.inweight"+day+",";
							strSql+="a.outqtyday"+day+",";
							strSql+="d.outweight"+day+",";
						}											
					}
				}
			}			
		}
		strSql+="a.qty, d.weight " +
				"from " +
					"v_articlInvAcc a," +
					"v_articlInvAccWth d," +
					"bdef_defarticle b," +
					"bdef_defowner c " +
				"where " +
					" a.owner_no=d.owner_no1" +
					" and a.article_no=d.article_no1" +
					" and a.warehouse_no=d.warehouse_no1 " +
					" and a.owner_no=b.owner_no " +
					"and a.article_no=b.article_no " +
					"and  a.owner_no=c.owner_no ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
		}
		
		if(!StringUtil.isEmpty(warehouseNo)){
			strSql+=" and a.warehouse_no='"+warehouseNo+"'";
		}
		
		if(!StringUtil.isEmpty(ownerNo)){
			strSql+=" and a.owner_no in ("+ownerNo+")";
		}
		strSql+=" order by b.owner_article_no ";			
		List list=genDao.getListByNativeSql(strSql);
        return list;
	}
	@Override
	public MsgRes deleteProject(String strPgmId, String strOldName)
			throws Exception {
		String deleteSql="delete from wms_defsearch_d_set a where " +
				"a.pgm_id='"+strPgmId+"' " +
				"and a.name='"+strOldName+"' ";
		System.out.println(deleteSql);
		this.genDao.exceuteSql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	//获取当前月数的天数
    public int getDaysByYearMonth(int year, int month){      
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    } 
}
