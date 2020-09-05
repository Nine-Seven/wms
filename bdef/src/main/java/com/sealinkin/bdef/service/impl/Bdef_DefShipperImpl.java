package com.sealinkin.bdef.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;

import com.sealinkin.bdef.model.Bdef_DefShipperModel;
import com.sealinkin.bdef.po.Bdef_Defshipper;
import com.sealinkin.bdef.service.IBdef_DefShipperService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.oset.model.Oset_ShipperLineModel;
import com.sealinkin.oset.po.Oset_ShipperLine;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

/**
 * 承运商资料维护service
 * @author zhouhuan
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefShipperImpl implements IBdef_DefShipperService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 获得承运商资料维护列
	 */
	@Override
	public ExtListDataBo<Bdef_DefShipperModel> getDefShipperList(
			String enterpriseNo,String strWarehouseNo, String strQuery, PageBo pageBo,
			Integer strRequestFlag) throws Exception 
	{
		String sql = "select bds.* ," +
			"f_get_fieldtext('N','DEF_STATUS',bds.status)statusText " +
			"from bdef_defshipper bds where 1=1 " +
			" and bds.enterprise_no='"+enterpriseNo+"' ";
        String totsql = "select count(1) " +
        		"from bdef_defshipper bds where 1=1 " +
        		" and bds.enterprise_no='"+enterpriseNo+"' ";	
		if(strQuery != null && !strQuery.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql+ws;
			totsql = totsql+ws;
		}
		
		if(strWarehouseNo != null && !strWarehouseNo.equals(""))
		{
			sql=sql+" and bds.warehouse_no in("+strWarehouseNo+") ";
			totsql=totsql+" and bds.warehouse_no in("+strWarehouseNo+")";
		}else{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
		sql = sql+" order by bds.shipper_no";
		List<Bdef_DefShipperModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefShipperModel> extListBo=null;
		if(strRequestFlag == 1){
			list = genDao.getListByNativeSql(sql,Bdef_DefShipperModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(strRequestFlag == 2){
			list = genDao.getListByNativeSql(sql,Bdef_DefShipperModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bdef_DefShipperModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 获得承运商资料列表
	 * hj
	 */
	@Override
	public ExtListDataBo<Bdef_DefShipperModel> getDefShipperMaintainList(
			String enterpriseNo,String strWarehouseNo, String strQuery, PageBo pageBo,
			Integer strRequestFlag) throws Exception 
	{
		String sql = "select bds.* ," +
			"f_get_fieldtext('N','DEF_STATUS',bds.status) statusText, " +
			"f_get_fieldtext('BDEF_DEFSHIPPER','PAPER_TYPE',bds.paper_type) paperTypeText, " +	
			"f_get_fieldtext('BDEF_DEFSHIPPER','SHIPPER_TYPE',bds.shipper_type) shipperTypeText " +
			
			" from bdef_defshipper bds where 1=1 " +
			" and bds.enterprise_no='"+enterpriseNo+"' ";
        String totsql = "select count(1) " +
        		" from bdef_defshipper bds where 1=1 " +
        		" and bds.enterprise_no='"+enterpriseNo+"' ";	
		if(strQuery != null && !strQuery.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql+ws;
			totsql = totsql+ws;
		}
		
		if(strWarehouseNo != null && !strWarehouseNo.equals(""))
		{
			sql=sql+" and bds.warehouse_no in("+strWarehouseNo+") ";
			totsql=totsql+" and bds.warehouse_no in("+strWarehouseNo+")";
		}else{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
		sql = sql+" order by bds.shipper_no";
		List<Bdef_DefShipperModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefShipperModel> extListBo=null;
		if(strRequestFlag == 1){
			list = genDao.getListByNativeSql(sql,Bdef_DefShipperModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(strRequestFlag == 2){
			list = genDao.getListByNativeSql(sql,Bdef_DefShipperModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bdef_DefShipperModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 保存或修改承运商
	 */
	public MsgRes saveOrUpdateShipper(String str, String strWorkerNo) throws Exception {
		Bdef_Defshipper shipper = (Bdef_Defshipper)JSON.parseObject(str,Bdef_Defshipper.class);
		String strSql = "select * from bdef_defshipper a where " +
				" a.shipper_no='"+shipper.getId().getShipperNo()+
				"' and a.enterprise_no='"+shipper.getId().getEnterpriseNo()+
				"' and a.warehouse_no='"+shipper.getId().getWarehouseNo()+"'";
		List<Bdef_Defshipper> list = genDao.getListByNativeSql(strSql,Bdef_Defshipper.class);
		if(list.size() != 0)
		{
			shipper.setRgstDate(list.get(0).getRgstDate());
			shipper.setRgstName(list.get(0).getRgstName());
		}else
		{
			shipper.setUpdtDate(null);
			shipper.setUpdtName(null);
		}
		this.genDao.saveOrUpdateObj(shipper);
		String sql= "insert into bdef_defshipper_log"
				+"  (enterprise_no," 
				+"   warehouse_no,"
				+"   shipper_no,"
				+"   shipper_name,"
				+"   address,"
				+"   tel,"
				+"   contact,"
				+"   status,"
				+"   disprice,"
				+"   graprice,"
				+"   compact_date,"
				+"   end_date,"
				+"   multi,"
				+"   memo,"
				+"   volprice,"
				+"   rgst_name,"
				+"   rgst_date,"
				+"   updt_name,"
				+"   updt_date,"
				+"   serialid,"
				+"   modiattr,"
				+"   moditime,"
				+"   modiopid)"
				+" select "
				+"   enterprise_no,"
				+"   warehouse_no,"
				+"   shipper_no,"
				+"   shipper_name,"
				+"   address,"
				+"   tel,"
				+"   contact,"
				+"   status,"
				+"   disprice,"
				+"   graprice,"
				+"   compact_date,"
				+"   end_date,"
				+"   multi,"
				+"   memo,"
				+"   volprice,"
				+"   rgst_name,"
				+"   rgst_date,"
				+"   updt_name,"
				+"   updt_date,"
				+"   SEQ_BDEF_DEFSHIPPER_LOG.nextval,"
				+"   'U',sysdate,"
				+"   '"+strWorkerNo+"' "
				+"   from bdef_defshipper "
				+"   where shipper_no='"+shipper.getId().getShipperNo()
				+"'  and enterprise_no='"+shipper.getId().getEnterpriseNo()
				+"'  and warehouse_no='"+shipper.getId().getWarehouseNo()+"'";
		genDao.updateBySql(sql);
		return new MsgRes(true,TipUtil.getTipsByKey("E21302"),null);//保存成功！
	}
	
	/**
	 * 保存或修改承运商
	 * hj
	 */
	public MsgRes saveOrUpdateShipperMaintain(String str, String strWorkerNo) throws Exception {
		Bdef_Defshipper shipper = (Bdef_Defshipper)JSON.parseObject(str,Bdef_Defshipper.class);
		String strSql = "select * from bdef_defshipper a where " +
				" a.shipper_no='"+shipper.getId().getShipperNo()+
				"' and a.enterprise_no='"+shipper.getId().getEnterpriseNo()+
				"' and a.warehouse_no='"+shipper.getId().getWarehouseNo()+"'";
		List<Bdef_Defshipper> list = genDao.getListByNativeSql(strSql,Bdef_Defshipper.class);
		if(list.size() != 0)
		{
			shipper.setRgstDate(list.get(0).getRgstDate());
			shipper.setRgstName(list.get(0).getRgstName());
		}else
		{
			shipper.setUpdtDate(null);
			shipper.setUpdtName(null);
		}
		this.genDao.saveOrUpdateObj(shipper);
		String sql= "insert into bdef_defshipper_log"
				+"   (warehouse_no,"
				+"   shipper_no,"
				+"   shipper_name,"
				+"   address,"
				+"   tel,"
				+"   contact,"
				+"   status,"
				+"   disprice,"
				+"   graprice,"
				+"   compact_date,"
				+"   end_date,"
				+"   multi,"
				+"   memo,"
				+"   volprice,"
				+"   rgst_name,"
				+"   rgst_date,"
				+"   updt_name,"
				+"   updt_date,"
				+"   serialid,"
				//+"   modiattr,"
				//+"   moditime,"
				//+"   modiopid,"
				+"   enterprise_no,"
				+"   REPORT_ID,"			
				//+"   PRINT_TYPE,"
				+"   PAPER_TYPE,"
				+"   SINGLE_LOCATE_FLAG,"
				+"   SHIPPER_TYPE,"
				+"   PAPER_COMIFIRE_FLAG,"
				+"   GET_PAPER_TYPE)"		
				+" select "
				+"   warehouse_no,"
				+"   shipper_no,"
				+"   shipper_name,"
				+"   address,"
				+"   tel,"
				+"   contact,"
				+"   status,"
				+"   disprice,"
				+"   graprice,"
				+"   compact_date,"
				+"   end_date,"
				+"   multi,"
				+"   memo,"
				+"   volprice,"
				+"   rgst_name,"
				+"   rgst_date,"
				+"   updt_name,"
				+"   updt_date,"
				+"   SEQ_BDEF_DEFSHIPPER_LOG.nextval,"
				+"   ENTERPRISE_NO,"
				+"   REPORT_ID,"
				//+"   PRINT_TYPE,"
				+"   PAPER_TYPE,"
				+"   SINGLE_LOCATE_FLAG,"
				+"   SHIPPER_TYPE,"
				+"   PAPER_COMIFIRE_FLAG,"
				+"   GET_PAPER_TYPE "
				
				
				//+"   'U',sysdate,"
				//+"   '"+strWorkerNo+"' "
				+"   from bdef_defshipper "
				+"   where shipper_no='"+shipper.getId().getShipperNo()
				+"'  and enterprise_no='"+shipper.getId().getEnterpriseNo()
				+"'  and warehouse_no='"+shipper.getId().getWarehouseNo()+"'";
		genDao.updateBySql(sql);
		return new MsgRes(true,TipUtil.getTipsByKey("E21302"),null);//保存成功！
	}
	
	@Override
	public List<ComboxBo> getCarCombo(String enterpriseNo,String warehouseNo,String pageSql) throws Exception {
		String sql="select t1.car_no value,t1.car_name text," +
				"'['|| ltrim(t1.car_no)||']'||t1.car_name dropValue " +
				" from bdef_defcar t1 where t1.warehouse_no='"+warehouseNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' ";		
		if(pageSql!=null && pageSql!=""){
		    sql+="and t1.car_no like '%"+pageSql+"%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 承运商编号校验
	 */
	@Override
	public String checkShipperNo(String enterpriseNo,String strShipperNo,String warehouseNo) throws Exception {
		String sql="select a.shipper_no from bdef_defshipper a " +
				"where a.shipper_no='"+strShipperNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	
	@Override
	public ExtListDataBo<Oset_DeflineModel> queryLineList(String enterpriseNo,String strWarehouseNo)
			throws Exception {
		String strSql="select a.* " +
				"from OSET_DEFLINE a " +
				"where a.line_no not in (select line_no from oset_shipper_line b " +
				"where b.warehouse_no=a.warehouse_no and b.enterprise_no=a.enterprise_no) " +
				"and a.warehouse_no='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		List<Oset_DeflineModel> list = null;
		ExtListDataBo<Oset_DeflineModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Oset_DeflineModel.class,0, 1000);
		extListBo= new ExtListDataBo<Oset_DeflineModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 获取承运商下的线路
	 */
	@Override
	public ExtListDataBo<Oset_ShipperLineModel> queryShipperLine(
			String enterpriseNo,String strWarehouseNo, String strShipperNo) throws Exception {
		String strSql="select a.*,b.shipper_name,c.line_name " +
				"from oset_shipper_line a,bdef_defshipper b,oset_defline c " +
				"where a.shipper_no=b.shipper_no " +
				"and a.line_no=c.line_no " +
				"and a.warehouse_no=b.warehouse_no " +
				"and a.warehouse_no=c.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no  " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.shipper_no='"+strShipperNo+
				"' and a.warehouse_no='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<Oset_ShipperLineModel> list = null;
		ExtListDataBo<Oset_ShipperLineModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Oset_ShipperLineModel.class,0, 1000);
		extListBo= new ExtListDataBo<Oset_ShipperLineModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 删除承运商关系
	 */
	@Override
	public MsgRes deleteShipperLine(String enterpriseNo,String strWarehouseNo,String strShipperNo, String strLineNo)
			throws Exception {
		String wheresql1[]=strShipperNo.split(",");
		String wheresql2[]=strLineNo.split(",");
		for(int i=0;i<wheresql2.length;i++){
			String strSql="delete oset_shipper_line os " +
					"where os.warehouse_no='"+strWarehouseNo+"' " +
							"and os.shipper_no='"+wheresql1[i].trim()+"' " +
							"and os.line_no='"+wheresql2[i].trim()+"' " +
							"and os.enterprise_no='"+enterpriseNo+"'";
			genDao.updateBySql(strSql);
		}
		return new MsgRes(true,"","");
	}
	
	/**
	 * 新增承运商关系
	 */
	@Override
	public MsgRes insertShipperLine(String strDetail) throws Exception {
		Collection<Oset_ShipperLine> shipperLine=JSONArray.toCollection(JSONArray.fromObject(strDetail),Oset_ShipperLine.class);
		List<Oset_ShipperLine> list=(List)shipperLine;
		this.genDao.saveList(list);
		return new MsgRes(true,"保存成功","");
	}
	
	//填充报表id下拉
	@Override
	public List<ComboxBo> queryReportIdCombo(String enterpriseNo,String warehouseNo,
			String strOwnerNo, String strFlag, String strWheresql)
			throws Exception {
		String strSql="select distinct a.REPORT_ID value,a.REPORT_NAME text," +
				"'['|| ltrim(a.REPORT_ID)||']'||a.REPORT_NAME dropValue " +
				"from pntdef_report a, pntset_paper_type b " +
				"where a.paper_type_no = b.paper_type_no " +
				"and b.enterprise_no='"+enterpriseNo+"' " +
				"and b.warehouse_no ='"+warehouseNo+"' " +
				"order by a.report_name";
		
		/*if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}*/
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//删除承运商hj
	@Override
	public MsgRes delete(String enterpriseNo, String warehouseNo, String shipperNo) {
		String sql=" select a.shipper_no from oset_shipper_line a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.shipper_no='"+shipperNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' " +
				   " union " +
				   " select a.shipper_no from odata_exp_m a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.shipper_no='"+shipperNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该承运商有业务数据，不能删除","");
		}
		
		String deleteSql="delete from bdef_defshipper where warehouse_no='" + warehouseNo + 
				 "' and shipper_no='" + shipperNo + "" +
				 "' and enterprise_no='" + enterpriseNo + "'";
      genDao.updateBySql(deleteSql);
      return new MsgRes(true,"删除成功","");
	}
	
	//填充承运商下拉控件 7-7添加  hj
	@Override
	public List<ComboxBo> queryBdefDefShipperCombo(String enterpriseNo,
			String strOwnerNo, String strWheresql, String strQuery)
			throws Exception {
		System.out.println("strWheresql:"+strWheresql);
		String strSql="select distinct t1.shipper_no value,t1.shipper_name text, " +
				"'['|| ltrim(t1.shipper_no)||']'||t1.shipper_name dropValue " +
				" from bdef_defshipper t1 " +
				" where 1=1 " +
				" and t1.enterprise_no='"+enterpriseNo+"'" +
				" and t1.status='1' ";

		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and (t1.shipper_no like '%"+strWheresql+"%' " +
					"or t1.shipper_name like '%"+strWheresql+"%') ";
		}
		/*if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}*/
		
		strSql=strSql+" order by t1.shipper_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
}










