package com.sealinkin.fcdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.fcdata.model.Fcdata_PlanDModel;
import com.sealinkin.fcdata.model.Fcdata_PlanMModel;
import com.sealinkin.fcdata.po.Fcdata_PlanD;
import com.sealinkin.fcdata.po.Fcdata_PlanM;
import com.sealinkin.fcdata.service.IFcdata_PlanService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

/*
 *  手建盘点单实现类
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Fcdata_PlanImpl implements IFcdata_PlanService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	

	// 获得盘点计划单头档
	@Override
	public ExtListDataBo<Fcdata_PlanMModel> getPlanM(
			String enterpriseNo,
			String strWarehouseNo,
			String str,
			String strOwnerNo,
			String queryStr,
			PageBo pageBo)throws Exception 
	{
		 String strCheckSql="select a.enterprise_no,a.warehouse_no," +
		 		"a.plan_type,a.fcdata_type,a.source_plan_no,a.plan_no," +
		 		"a.plan_date,a.begin_date,a.end_date,a.status," +
		 		"a.create_flag,a.plan_remark,a.send_flag,a.org_no," +
		 		"case when a.owner_no='N' THEN 'ALL' else a.owner_no end as owner_no," +
		 		"f_get_fieldtext('N','STATUS',a.status) statusText " +
 					"from Fcdata_Plan_M a " +
 					"where 1=1 " +
 					"and a.warehouse_no='"+strWarehouseNo+
 					"' and a.enterprise_no='"+enterpriseNo+"' ";		
		 String intTotsql = "select count(1) " +
 	        		"from Fcdata_Plan_M a " +
 	        		"where 1=1 " +
 					"and a.warehouse_no='"+strWarehouseNo+
 					"' and a.enterprise_no='"+enterpriseNo+"' ";	
		 
	    if(strOwnerNo!=null && !strOwnerNo.equals("")  && strOwnerNo.equals("ALL")){
	    	
	    	strCheckSql = strCheckSql + " and a.owner_no = 'N' ";
			intTotsql = intTotsql + " and a.owner_no = 'N' ";
	    }else if(strOwnerNo!=null && !strOwnerNo.equals("")  && !strOwnerNo.equals("ALL")){
	    	strCheckSql = strCheckSql + " and a.owner_no = '"+strOwnerNo+"' ";
			intTotsql = intTotsql + " and a.owner_no = '"+strOwnerNo+"' ";
	    }
	    
        if(str != null && !str.equals(""))
        {
        	String strON=CommUtil.covtCollectionToWhereSql(str);
        	strCheckSql = strCheckSql + strON;
			intTotsql = intTotsql + strON;
        }
      
        if(queryStr!=null && !queryStr.equals(""))
		{        	
			String ws = CommUtil.covtCollectionToWhereSql(queryStr);
			strCheckSql = strCheckSql+ws;
			intTotsql = intTotsql+ws;
		}else{
			strCheckSql = strCheckSql+" and (a.status = '10' or a.status = '11') ";
			intTotsql = intTotsql+" and (a.status = '10' or a.status = '11') ";
		}
        strCheckSql+=" order by a.plan_no desc ";
		List<Fcdata_PlanMModel> list = genDao.getListByNativeSql(strCheckSql,Fcdata_PlanMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(intTotsql);
		ExtListDataBo<Fcdata_PlanMModel> extListBo= new ExtListDataBo<Fcdata_PlanMModel>(list, count);
        return extListBo;
	}
	
	//获得盘点计划单明细
	@Override
	public ExtListDataBo<Fcdata_PlanDModel> getFcdataPlanD(String enterpriseNo,String warehouseNo,String[] wheresql,
			PageBo pageBo)throws Exception {
		String sql="select distinct "
				+"a.*,c.group_name,d.owner_article_no,d.article_name,e.ware_name,f.area_name "
				+"from Fcdata_Plan_D a left join "
				+"bdef_article_group c on a.group_no=c.group_no and a.owner_no=c.owner_no and a.enterprise_no=c.enterprise_no "
				+"left join bdef_defarticle d on a.article_no=d.article_no and a.owner_no=d.owner_no and a.enterprise_no=d.enterprise_no "
				+"left join cdef_defware e on a.ware_no=e.ware_no and a.warehouse_no=e.warehouse_no and a.enterprise_no=e.enterprise_no "
				+"left join cdef_defarea f on a.ware_no=f.ware_no  and a.area_no=f.area_no and a.warehouse_no=f.warehouse_no  and a.enterprise_no=f.enterprise_no "
				+"left join cdef_defcell g on a.ware_no=g.ware_no and a.area_no=g.area_no and a.stock_no=g.stock_no and a.warehouse_no=g.warehouse_no  and a.enterprise_no=g.enterprise_no " 
				+"where a.plan_no='"+wheresql[1]+
				"' and a.enterprise_no='"+enterpriseNo+
				"' and a.warehouse_no='"+warehouseNo+"' ";		
		List<Fcdata_PlanDModel> list = genDao.getListByNativeSql(sql,Fcdata_PlanDModel.class,pageBo.getStart(), pageBo.getPagesize());
		ExtListDataBo<Fcdata_PlanDModel> extListBo= new ExtListDataBo<Fcdata_PlanDModel>(list, 0);
        return extListBo;
	}
	
	// 保存盘点计划单
	@Override
	public MsgRes saveFcdata_Plan(String planM,String planD1)throws Exception {
		Fcdata_PlanM fpm = (Fcdata_PlanM)JSONObject.toBean(
				JSONObject.fromObject(planM),Fcdata_PlanM.class);
	    List<Fcdata_PlanD> fpd=JSON.parseArray(planD1, Fcdata_PlanD.class);

		String strCheckSql = "select * from fcdata_plan_m a " +
				" where a.warehouse_no='a.warehouse_no="+fpm.getId().getWarehouseNo()+
				"' and a.plan_no='"+fpm.getId().getPlanNo()+
				"' and a.enterprise_no='"+fpm.getId().getEnterpriseNo()+"' ";
		List<Fcdata_PlanM> planMlist = genDao.getListByNativeSql(strCheckSql,Fcdata_PlanM.class);
		if(planMlist.size()==0){//新增
			fpm.setUpdtDate(null);
			fpm.setUpdtName(null);
		}else{//修改
			fpm.setRgstDate(planMlist.get(0).getRgstDate());
			fpm.setRgstName(planMlist.get(0).getRgstName());
		}
		if(fpm.getOwnerNo().equals("ALL")){
			fpm.setOwnerNo("N");
		}
		String strPlanNo="";
		if(fpm.getId().getPlanNo().equals("保存时自动生成"))
		{
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			inList.add(fpm.getId().getEnterpriseNo());
			inList.add(fpm.getId().getWarehouseNo());
			inList.add(DocumentTypeModel.FCDATACP);
			outList.add("S");
			outList.add("S");
			resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
			strPlanNo=resultList.get(0).toString();
			fpm.getId().setPlanNo(strPlanNo);
			fpm.setSourcePlanNo(strPlanNo);
			
			for(Fcdata_PlanD d:fpd){
				d.getId().setPlanNo(strPlanNo);
				if(d.getId().getOwnerNo().equals("ALL")){
					d.getId().setOwnerNo("N");
				}
			}
		}else{
			strPlanNo=fpm.getId().getPlanNo();
			deleteFcdata_Plan(fpm.getId().getEnterpriseNo(),fpm.getId().getWarehouseNo(),strPlanNo);
			fpm.setSourcePlanNo(strPlanNo);
			for(Fcdata_PlanD d:fpd){
				d.getId().setPlanNo(strPlanNo);
				if(d.getId().getOwnerNo().equals("ALL")){
					d.getId().setOwnerNo("N");
				}
			}
		}
		this.genDao.saveObj(fpm);
		this.genDao.saveList((List<Fcdata_PlanD>) fpd);
		return new MsgRes(true,TipUtil.getTipsByKey("E30001"),strPlanNo);//保存成功！
	}
	
	//删除盘点计划单
	@Override
	public MsgRes deleteFcdata_Plan(String enterpriseNo,String warehouseNo,String planNo) throws Exception{
		String delsql="delete Fcdata_Plan_M a " +
				"where a.plan_no='"+planNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
		delsql="delete Fcdata_Plan_D a " +
				"where a.plan_no='"+planNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
		return new MsgRes(true,TipUtil.getTipsByKey("E30004"),null);//数据删除成功！
	}
	
	// 发起
	public MsgRes sendFcdataPlan(String planMModel) throws Exception {
		Fcdata_PlanM fpm = (Fcdata_PlanMModel)JSONObject.toBean(
				JSONObject.fromObject(planMModel),Fcdata_PlanMModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(fpm.getId().getEnterpriseNo());
		inList.add(fpm.getId().getWarehouseNo());
		inList.add(fpm.getOwnerNo().equals("ALL") ? "N":fpm.getOwnerNo());
		inList.add(fpm.getRgstName());
		inList.add(fpm.getId().getPlanNo());
		//inList.add(fpm.getBeginDate());
		//inList.add(fpm.getEndDate());
		System.out.println(inList);
		outList.add("S");
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "PKLG_FCDATA.P_Fcdata_RequestLocate");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E30002"),null);//定位成功！
	}
	
	// 结案
	public MsgRes sendClosePlan(String planM) throws Exception {
		Fcdata_PlanM fpm = (Fcdata_PlanM)JSONObject.toBean(
				JSONObject.fromObject(planM),Fcdata_PlanM.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		inList.add(fpm.getId().getEnterpriseNo());
		inList.add(fpm.getId().getWarehouseNo());
		inList.add(fpm.getOwnerNo());
		inList.add(fpm.getId().getPlanNo());
		inList.add(fpm.getRgstName());
		
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "PKLG_FCDATA.P_fcdata_closePlan");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E30003"),null);//操作成功！
	}

	 // 储位下拉
	public List<com.sealinkin.comm.model.ComboxBo> getCdef_DefCellCombo(
			String enterpriseNo,String strWarehouseNo, 
			String strJson, String str,String strOwnerNo) {
		String ws = null;
		String sql= "select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from cdef_defarea a " +
				" union select a.cell_no value,a.cell_no text,a.cell_no dropValue  " +
				"from Cdef_Defcell a " +
				"where a.cell_status<>1 " +
				"and a.warehouse_No='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' " ;		
		if(strJson!=null && !strJson.equals(""))
		{
			ws=CommUtil.covtCollectionToWhereSql(strJson);
			sql=sql+ws;
		}
		if(str!=null && !str.equals(""))
		{
			sql=sql+"and a.cell_no like '%"+str+"%'";
		}
		//如果货主有绑定货位，则只能下拉绑定的货位
		if(strOwnerNo!=null && !strOwnerNo.equals("") && !strOwnerNo.equals("ALL")){
			String sql1="select a.fixedcell_flag from " +
					"bdef_defowner a where a.owner_no='"+strOwnerNo+"' " +
					" and a.enterprise_no='"+enterpriseNo+"' " ;
			List flag = genDao.getListByNativeSql(sql1);
			if(flag.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
				sql=sql+" and a.cell_no in (select distinct ca.cell_no from cset_owner_cell  ca " +
						"where ca.owner_no='"+strOwnerNo+"' "+
						" and ca.warehouse_No='"+strWarehouseNo+"' "+
						" and ca.enterprise_no='"+enterpriseNo+"')  " +
						"union " +//将不是作业区的储位拼接进来
			            "select a.cell_no value,a.cell_no text,a.cell_no dropValue  " +
			            "from Cdef_Defcell a,cdef_defarea b " +
			            "where a.ware_no=b.ware_no  and a.area_no=b.area_no and a.enterprise_no=b.enterprise_no " +
			            "and a.warehouse_no=b.warehouse_no and b.area_attribute<>'0' "+ws ;
		
			}
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 20);
		return  (List<ComboxBo>)list;
	}
	
	 // 储区下拉
	public List<ComboxBo> getCdef_DefAreaCombo(String enterpriseNo,String strWarehouseNo,
			String str,String strWheresql,String strOwnerNo) {
		String ws=null;
		String sql="select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from cdef_defware a union "+
				"select a.area_no value,a.area_Name text,'['|| ltrim(a.area_no)||']'||a.area_Name dropValue  " +
				"from cdef_defarea a " +
				"where a.warehouse_No='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+ enterpriseNo+"' ";
				//"and area_attribute<>'1'  ";	
		if(str!=null && !str.equals(""))
		{
			ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			sql=sql+"and a.area_no like '%"+strWheresql+"%'";
		}
		//如果货主有绑定货位，则只能下拉绑定的货位的所属储区
		if(strOwnerNo!=null && !strOwnerNo.equals("") && !strOwnerNo.equals("ALL")){
			String sql1="select a.fixedcell_flag from " +
					"bdef_defowner a where a.owner_no='"+strOwnerNo+"' " +
					" and a.enterprise_no='"+enterpriseNo+"' " ;
			List flag = genDao.getListByNativeSql(sql1);
			if(flag.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
				sql=sql+" and a.area_no in (select distinct cl.area_no " +
						"from cset_owner_cell l,cdef_defcell cl " +
						"where l.enterprise_no=cl.enterprise_no and l.warehouse_no=cl.warehouse_no "+
						"and l.cell_no=cl.cell_no " +
						" and l.owner_no='"+strOwnerNo+"' "+
						" and l.warehouse_No='"+strWarehouseNo+"' "+
						" and l.enterprise_no='"+enterpriseNo+"' and cl.ware_no=a.ware_no)  " +
						"union " +//将不是作业区的储区拼接进来
				        "select a.area_no value,a.area_Name text,'['|| ltrim(a.area_no)||']'||a.area_Name dropValue " +
				        "from cdef_defarea a where a.area_attribute<>'0' "+ws ;
			}
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}

	 // 通道下拉
	public List<ComboxBo> getCdef_DefStockCombo(String enterpriseNo,String strWarehouseNo,
			String str,String strWheresql,String strOwnerNo) 
	{
		    String ws=null;
			String sql=" select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from Cdef_Defcell a union "+
					"select distinct a.stock_no value, a.stock_no text, '['|| ltrim(a.stock_no)||']'||a.stock_no dropValue " +
					"from Cdef_Defcell a " +
					"where a.enterprise_no='"+enterpriseNo+"' "+
					"and a.warehouse_No='"+strWarehouseNo+"' " ;		
			if(str!=null && !str.equals(""))
			{
				ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql+ws;
			}
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				sql=sql+"and a.stock_no like '%"+strWheresql+"%'";
			}
			
			//如果货主有绑定货位，则只能下拉绑定的货位的所属通道
			if(strOwnerNo!=null && !strOwnerNo.equals("") && !strOwnerNo.equals("ALL")){
				String sql1="select a.fixedcell_flag from " +
						"bdef_defowner a where a.owner_no='"+strOwnerNo+"' " +
						" and a.enterprise_no='"+enterpriseNo+"' " ;
				List flag = genDao.getListByNativeSql(sql1);
				if(flag.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
					sql=sql+" and a.stock_no in (select distinct cl.stock_no " +
							"from cset_owner_cell l,cdef_defcell cl " +
							"where l.enterprise_no=cl.enterprise_no and l.warehouse_no=cl.warehouse_no "+
							"and l.cell_no=cl.cell_no " +
							" and l.owner_no='"+strOwnerNo+"' "+
							" and l.warehouse_No='"+strWarehouseNo+"' "+
							" and l.enterprise_no='"+enterpriseNo+"' and cl.area_no=a.area_no)  " +
						    "union " +//将不是作业区的通道拼接进来
				            "select distinct w.stock_no value, w.stock_no text, '['|| ltrim(w.stock_no)||']'||w.stock_no dropValue " +
				            "from Cdef_Defcell w,cdef_defarea a " +
				            "where w.ware_no=a.ware_no  and w.area_no=a.area_no and w.enterprise_no=a.enterprise_no " +
				            "and w.warehouse_no=a.warehouse_no and a.area_attribute<>'0' " +ws;
			}
			}
			List list=genDao.getListByNativeSql(sql,ComboxBo.class);
			return  (List<ComboxBo>)list;
	}
	
	 // 仓库下拉
	public List<ComboxBo> getCdef_DefWareCombo(String enterpriseNo,String strWarehouseNo,
			String str,String strWheresql,String strOwnerNo) 
	{
		String sql="select distinct 'ALL' as value ,'ALL' text ,'ALL' dropvalue from cdef_defware a  union "+	
				"select a.ware_no value,a.ware_name text,'['|| ltrim(a.ware_no)||']'||a.ware_name dropValue  " +
				"from cdef_defware a " +
				"where a.warehouse_No='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			sql=sql+"and a.ware_no like '%"+strWheresql+"%' ";
		}
		//如果货主有绑定货位，则只能下拉绑定的货位的所属库区
		if(strOwnerNo!=null && !strOwnerNo.equals("") && !strOwnerNo.equals("ALL")){
			String sql1="select a.fixedcell_flag from " +
					"bdef_defowner a where a.owner_no='"+strOwnerNo+"' " +
					" and a.enterprise_no='"+enterpriseNo+"' " ;
			List flag = genDao.getListByNativeSql(sql1);
			if(flag.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
				sql=sql+" and a.ware_no in (select distinct cl.ware_no " +
						"from cset_owner_cell l,cdef_defcell cl " +
						"where l.enterprise_no=cl.enterprise_no and l.warehouse_no=cl.warehouse_no "+
						"and l.cell_no=cl.cell_no " +
						" and l.owner_no='"+strOwnerNo+"' "+
						" and l.warehouse_No='"+strWarehouseNo+"' "+
						" and l.enterprise_no='"+enterpriseNo+"'and cl.ware_no=a.ware_no)  " +
			            "union " +//将不是作业区的库区拼接进来
			            "select w.ware_no value,w.ware_name text,'['|| ltrim(w.ware_no)||']'||w.ware_name dropValue " +
			            "from cdef_defware w,cdef_defarea a " +
			            "where w.ware_no=a.ware_no and w.enterprise_no=a.enterprise_no " +
			            "and w.warehouse_no=a.warehouse_no and a.area_attribute<>'0' ";
			}
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}
	
	// 通过artilce_no获取商品类别名称和编码
	public List<Bdef_DefarticleModel> getGroupByArticle(
			String enterpriseNo,
			String strArticleNo) {
		String strSql = "select a.OWNER_ARTICLE_NO, a.group_no,b.group_name from bdef_defarticle a," +
				"bdef_article_group b " +
				"where a.group_no=b.group_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.article_no='"+strArticleNo+"'";
		List<Bdef_DefarticleModel> list = genDao.getListByNativeSql(strSql,Bdef_DefarticleModel.class);
        return list;
	}

	// 获取商品下拉
	public List<ComboxBo> query_BdefArticleCombo(String enterpriseNo,
			String strGroupNo, String strWheresql) {
			String strSql="select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from bdef_defarticle t1 " +
					"union select t1.article_no value,t1.article_name text," +
					"'['|| ltrim(t1.owner_article_no)||']'||t1.article_name dropValue " +
					"from bdef_defarticle t1 " +
					"where t1.enterprise_no='"+enterpriseNo+"' ";
			if(strGroupNo!=null && !strGroupNo.equals("")&& !strGroupNo.equals("ALL"))
			{
				strSql+=" and t1.group_no='"+strGroupNo+"' ";
			}
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				strSql+=" and (t1.article_no like '%"+strWheresql+"%' " +
						" or t1.owner_article_no like  '%"+strWheresql+"%' " +
						" or t1.article_name like '%"+strWheresql+"%' " +
						" or t1.barcode like '%"+strWheresql+"%')";
			}			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);	//7-18  去掉了每页数量
			return (List<ComboxBo>) list;
	}
	
	//获得单头状态
	public List<String> getStatus(String enterpriseNo,String strWarehouseNo, String strOwnerNo,
			String strPlanNo, int i, int j) {
		String strSql = "";
		if(strPlanNo!=null && !strPlanNo.equals(""))
		{
			strSql = "select distinct a.status from FCDATA_REQUEST_M a " +
					"where a.warehouse_no='" +strWarehouseNo+
					"' and a.enterprise_no='"+enterpriseNo+
					"' and a.plan_no='"+strPlanNo+"' ";
		}
		List list=genDao.getListByNativeSql(strSql);
		return  (List<String>)list;
	}
	@Override
	public MsgRes closeOrder(String enterpriseNo, String warehouseNo,
			String workerNo,String planNo) throws Exception {
		//锁单
		String sql1="update fcdata_plan_m a set a.status =a.status " +
				"where a.plan_no='"+planNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(sql1);
		
		String sql="update fcdata_plan_m a set a.status =16, " +
				"a.updt_name='"+workerNo+"', " +
				"a.updt_date=sysdate " +
				"where a.plan_no='"+planNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'" +
				"  and a.status=10";
		genDao.updateBySql(sql);
		
		return new MsgRes(true,TipUtil.getTipsByKey("E30003"),planNo);//操作成功！;
	}

}
