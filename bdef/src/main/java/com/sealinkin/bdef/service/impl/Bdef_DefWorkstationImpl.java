package com.sealinkin.bdef.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBdef_DefWorkstationService;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.bset.po.Bset_Printer_Workstation;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_DefWorkstationImpl implements IBdef_DefWorkstationService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	/*
	 * 保存或增加工作站
	 * zhouhuan
	 */
	public Boolean add(String str) throws Exception {
		Bset_Printer_Workstation bpw=(Bset_Printer_Workstation) JSONObject.toBean(JSONObject.fromObject(str),Bset_Printer_Workstation.class);
		this.genDao.saveOrUpdateObj(bpw);
		return true;		
	}

	/**
	 * 获得工作站资料
	 * zhouhuan
	 **/
	@Override
	public ExtListDataBo<Bset_Printer_WorkstationModel> getBdef_defWorkstationList(
			String enterpriseNo,String warehouseNo, String queryStr, PageBo pageBo,
			Integer requestFlag)throws Exception {
	String sql=" select a.warehouse_no,a.workstation_no,a.workstation_name,a.printer_group_no,b.printer_group_name " +
			" from PNTSET_PRINTER_WORKSTATION a ,bset_group b " +
			" where a.printer_group_no=b.printer_group_no " +
			" and a.warehouse_no=b.warehouse_no "+
			" and a.warehouse_no= '"+warehouseNo+
			"' and a.enterprise_no=b.enterprise_no " +
			" and a.enterprise_no= '"+enterpriseNo+"' ";
        String totsql = "select count(1) " +
        		" from PNTSET_PRINTER_WORKSTATION a ,bset_group b " +
    			" where a.printer_group_no=b.printer_group_no " +
    			" and a.warehouse_no=b.warehouse_no "+
    			" and a.warehouse_no= '"+warehouseNo+
    			"' and a.enterprise_no=b.enterprise_no " +
    			" and a.enterprise_no= '"+enterpriseNo+"' ";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" order by a.workstation_no ";
		List<Bset_Printer_WorkstationModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bset_Printer_WorkstationModel> extListBo= new ExtListDataBo<Bset_Printer_WorkstationModel>(list, count);
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bset_Printer_WorkstationModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bset_Printer_WorkstationModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bset_Printer_WorkstationModel>(list, count);
        return extListBo;
	}
	/*
	 * 删除工作站
	 * zhouhuan
	 */
	@Override
	public void delete(String enterpriseNo, String warehouseNo,String workstationNo)throws Exception {
		 String sql="delete from bdef_defworkstation where warehouse_no='" + warehouseNo +
				 "' and workstation_no='" + workstationNo + 
				 "' and enterprise_no='" + enterpriseNo + "' ";
         genDao.updateBySql(sql);
	}

	@Override
	public void saveBset_Printer_Workstation(String str) throws Exception {
		Collection<Bset_Printer_Workstation> pw=JSONArray.toCollection(JSONArray.fromObject(str),Bset_Printer_Workstation.class);
		List<Bset_Printer_Workstation> printerWork=(List)pw;
		this.genDao.saveList(printerWork);
	}

	@Override
	public void deleteBset_Printer_Workstation(String enterpriseNo,String warehouseNo,
			String workstationNo, String printerGroupNo) throws Exception {
		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=workstationNo.split(",");
		String wheresql3[]=printerGroupNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete PNTSET_PRINTER_WORKSTATION " +
					"where warehouse_no='"+wheresql1[i].trim()+"' and " +
					"workstation_no='"+wheresql2[i].trim()+"' and " +
					"printer_group_no='"+wheresql3[i].trim()+"' " +
					"and enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(sql);
		}
	}

	@Override
	public String checkWorkstationNo(String enterpriseNo,String warehouseNo, String workstationNo)
			throws Exception {
		String sql="select a.workstation_no from PNTSET_PRINTER_WORKSTATION a where a.warehouse_no='"+warehouseNo+
				"' and enterprise_no='"+enterpriseNo+
				"' and a.workstation_no='"+workstationNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}

	@Override
	public ExtListDataBo<Bset_Printer_WorkstationModel> getWorkstationCombo(
			String enterpriseNo,String warehouseNo, String queryStr, PageBo pageBo,
			Integer requestFlag)throws Exception {
	String sql=" select a.warehouse_no,a.workstation_no,a.workstation_name,a.printer_group_no,b.printer_group_name " +
			" from PNTSET_PRINTER_WORKSTATION a ,bset_group b " +
			" where a.printer_group_no=b.printer_group_no " +
			" and a.warehouse_no=b.warehouse_no "+
			" and a.warehouse_no= '"+warehouseNo+
			"' and a.enterprise_no=b.enterprise_no " +
			" and a.enterprise_no= '"+enterpriseNo+"' ";
        String totsql = "select count(1) " +
        		" from PNTSET_PRINTER_WORKSTATION a ,bset_group b " +
    			" where a.printer_group_no=b.printer_group_no " +
    			" and a.warehouse_no=b.warehouse_no "+
    			" and a.warehouse_no= '"+warehouseNo+
    			"' and a.enterprise_no=b.enterprise_no " +
    			" and a.enterprise_no= '"+enterpriseNo+"' ";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" order by a.workstation_no ";
		List<Bset_Printer_WorkstationModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bset_Printer_WorkstationModel> extListBo= new ExtListDataBo<Bset_Printer_WorkstationModel>(list, count);
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bset_Printer_WorkstationModel.class,pageBo.getStart(), 10000);
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bset_Printer_WorkstationModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bset_Printer_WorkstationModel>(list, count);
        return extListBo;
	}
	

	@Override
	public MsgRes checkWorkStation(String strEnterpriseNo,
			String strWarehouseNo, String str) throws Exception {
		String strSql="select a.workstation_no " +
				"from PNTSET_PRINTER_WORKSTATION  a " +
				"where a.warehouse_no= '"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' "+
				"and a.workstation_no = '"+str+"' ";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0){
			return new MsgRes(false,"该工作站不存在","该工作站不存在");
		}else{
			return new MsgRes(true,"",JSON.toJSONString(list));
		}
	}

	@Override
	public List<ComboxBo> getWorkStationInfo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strQuery) throws Exception {
		String sql = "select distinct a.workstation_no value,a.workstation_name text,"
				+"'['|| a.workstation_no || ']'||a.workstation_name dropValue "
					+ "from PNTSET_PRINTER_WORKSTATION a "
					+ " where a.enterprise_no='" + strEnterpriseNo + "' "
					+ " and a.warehouse_no='" + strWarehouseNo + "' ";
					
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + 
					" and (a.workstation_no like '%"+str+"%' " +
					"or a.workstation_name like '%"+str+"%' )";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	@Override
	public List<ComboxBo> getprinterGroupInfo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strQuery) throws Exception {
		String sql = "select distinct a.printer_group_no value,b.printer_group_name text,"
				+"'['|| a.printer_group_no || ']'||b.printer_group_name dropValue "
					+ "from PNTSET_PRINTER_WORKSTATION a,bset_group b  "
					+ " where a.enterprise_no='" + strEnterpriseNo + "' "
					+ " and a.enterprise_no=b.enterprise_no "
					+ " and a.printer_group_no=b.printer_group_no "
					+ " and a.warehouse_no=b.warehouse_no "
					+ " and a.warehouse_no='" + strWarehouseNo + "' ";
					
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + 
					" and (a.printer_group_no like '%"+str+"%' " +
					"or b.printer_group_name like '%"+str+"%' )";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
}
