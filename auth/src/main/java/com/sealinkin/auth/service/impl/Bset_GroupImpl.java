package com.sealinkin.auth.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.bset.model.Bset_GroupModel;
import com.sealinkin.bset.model.Bset_Printer_GroupModel;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.bset.po.Bset_Group;
import com.sealinkin.bset.po.Bset_Printer_Group;
import com.sealinkin.auth.service.IBset_GroupService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bset_GroupImpl implements IBset_GroupService{
	
	 private IGenericManager genDao;
	 public IGenericManager getGenDao() {
		 return genDao;
	 }
	 public void setGenDao(IGenericManager genDao) {
		 this.genDao = genDao;
	 }
	@Override
	public ExtListDataBo<Bset_GroupModel> getBset_GroupList(String enterpriseNo,String warehouseNo,
			String queryStr, PageBo pageBo, Integer requestFlag)
			throws Exception {
		String sql="select a.warehouse_no,a.printer_group_no,a.printer_group_name from bset_group a where " +
				" a.warehouse_no='"+warehouseNo+"' " +
				" and a.enterprise_no='"+enterpriseNo+"' " ;
		String totsql="select count(1) from bset_group a where " +
				" a.warehouse_no='"+warehouseNo+"' " +
				" and a.enterprise_no='"+enterpriseNo+"' " ;
		
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		
		List<Bset_GroupModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bset_GroupModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bset_GroupModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bset_GroupModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bset_GroupModel>(list, count);
        return extListBo;
	}
	
	@Override
	public boolean saveOrUpdateGroup(String str) throws Exception {
		Bset_Group bd=(Bset_Group)JSONObject.toBean(JSONObject.fromObject(str),Bset_Group.class);
		this.genDao.saveOrUpdateObj(bd);
		return true;
	}
	
	@Override
	public boolean savePrinter_Group(String str)throws Exception{
		Collection<Bset_Printer_Group> printer=JSONArray.toCollection(JSONArray.fromObject(str),Bset_Printer_Group.class);
		List<Bset_Printer_Group> printerGroup=(List)printer;
		this.genDao.saveList(printerGroup);
		return true;
	}
	
	@Override
	public ExtListDataBo<Bset_Printer_GroupModel> getBset_Printer_GroupList(
			String enterpriseNo,String warehouseNo, String wheresql, PageBo pageBo)
			throws Exception {
		
		String sql="select a.warehouse_no,a.printer_no,a.printer_group_no,b.printer_name " +			
				"from PNTSET_PRINTER_GROUP a,PNTDEF_DEFPRINTER b " +
				"where " +
				"a.printer_group_no='"+wheresql+
				"' and b.printer_no =a.printer_no "+
				"and a.enterprise_no=b.enterprise_no "+
				"and a.warehouse_no=b.warehouse_no " +
				" and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		
		
		String totsql="select count(1) from PNTSET_PRINTER_GROUP a " +
				"where a.printer_group_no='"+wheresql+
						"' and a.warehouse_no='"+warehouseNo+
						"' and a.enterprise_no='"+enterpriseNo+"'";
		List<Bset_Printer_GroupModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bset_Printer_GroupModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bset_Printer_GroupModel.class,pageBo.getStart(), pageBo.getPagesize());
		count = genDao.getCountByNativeSql(totsql);
		extListBo= new ExtListDataBo<Bset_Printer_GroupModel>(list, count);
        return extListBo;
	}
	@Override
	public ExtListDataBo<Bdef_DefprinterModel> getBdef_DefPrinterList(
			String enterpriseNo,String warehouseNo,String wheresql,String str, PageBo pageBo)
			throws Exception {
		String sql="select a.warehouse_no,a.printer_no,a.printer_name,b.paper_type_no," +
		"a.printer_driver,a.printer_port,a.status," +
		"f_get_fieldtext('N','STATUS',a.status)statusText," +
		"b.paper_type_name printertypeText " +
		"from PNTDEF_DEFPRINTER a," +
		"PNTSET_PAPER_TYPE b " +
		"where a.enterprise_no=b.enterprise_no " ;
		
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql+ws;
		}

		sql = sql+ //"and a.warehouse_no=b.warehouse_no " +
		"and a.paper_type_no=b.paper_type_no " +
		"and a.warehouse_no='"+warehouseNo+
		"' and a.enterprise_no='"+enterpriseNo+
		"' and a.paper_type_no not in(" +
		"  select pnf.paper_type_no " +
		"    from PNTSET_PRINTER_GROUP c, pntdef_defprinter  pnf " +
		"   where c.enterprise_no=pnf.enterprise_no " +
		"     and c.warehouse_no=pnf.warehouse_no  " +
		"     and c.printer_no=pnf.printer_no " +
		"     and c.warehouse_no='"+warehouseNo+"' "+
		"     and c.enterprise_no='"+enterpriseNo+"' " +
	    "     and c.printer_group_no='" +wheresql+"' "+
	    ") order by a.paper_type_no,a.printer_no  " ;		
		
		String totsql = "select count(1) from PNTDEF_DEFPRINTER a " +
		"where a.warehouse_no='"+warehouseNo+
		"' and a.enterprise_no='"+enterpriseNo+
		"' and a.paper_type_no not in(" +
		"  select pnf.paper_type_no " +
		"    from PNTSET_PRINTER_GROUP c, pntdef_defprinter  pnf " +
		"   where c.enterprise_no=pnf.enterprise_no " +
		"     and c.warehouse_no=pnf.warehouse_no  " +
		"     and c.printer_no=pnf.printer_no " +
		"     and c.warehouse_no='"+warehouseNo+"' "+
		"     and c.enterprise_no='"+enterpriseNo+"' " +
	    "     and c.printer_group_no='" +wheresql+"' "+
	    ") ";	
		
		List<Bdef_DefprinterModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefprinterModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bdef_DefprinterModel.class,pageBo.getStart(), pageBo.getPagesize());
		count = genDao.getCountByNativeSql(totsql);
		extListBo= new ExtListDataBo<Bdef_DefprinterModel>(list, count);
		return extListBo;
	}
	@Override
	public boolean deletePrinter_Group(String enterpriseNo,String warehouseNo,
			String printerGroupNo, String printerNo) throws Exception {
		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=printerNo.split(",");
		String wheresql3[]=printerGroupNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete PNTSET_PRINTER_GROUP a " +
			"where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
			"a.printer_no='"+wheresql2[i].trim()+"' and " +
			"a.printer_group_no='"+wheresql3[i].trim()+
			"' and a.enterprise_no='"+enterpriseNo+"'";
			genDao.updateBySql(sql);
		}
		return true;
	}
	@Override
	public ExtListDataBo<Bset_GroupModel> getBset_GroupList2(
			String enterpriseNo,String warehouseNo, PageBo pageBo) throws Exception {
		
		String sql="select a.warehouse_no,a.printer_group_no,a.printer_group_name " +
				"from bset_group a " +
				"where a.printer_group_no not in " +
				"(select b.printer_group_no from PNTSET_PRINTER_WORKSTATION b where where b.warehouse_no=a.warehouse_no and b.enterprise_no=a.enterprise_no)" +
				" and a.warehouse_no='"+warehouseNo+
		        "' and a.enterprise_no='"+enterpriseNo+"'";
		String totsql="select count(1) from bset_group a " +
				"where a.printer_group_no not in (select b.printer_group_no from PNTSET_PRINTER_WORKSTATION b)";
		List<Bset_GroupModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bset_GroupModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bset_GroupModel.class,pageBo.getStart(), pageBo.getPagesize());
		count = genDao.getCountByNativeSql(totsql);
		extListBo= new ExtListDataBo<Bset_GroupModel>(list, count);
        return extListBo;
	}
	
	@Override
	public ExtListDataBo<Bset_Printer_WorkstationModel> getBset_Printer_WorkstationList(
			String enterpriseNo,String warehouseNo,String wheresql, PageBo pageBo) throws Exception {
		String sql="select a.warehouse_no,a.workstation_no,a.printer_group_no " +
				"from PNTSET_PRINTER_WORKSTATION a where a.workstation_no='"+wheresql+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		List<Bset_Printer_WorkstationModel> list = null;
		ExtListDataBo<Bset_Printer_WorkstationModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bset_Printer_WorkstationModel.class,pageBo.getStart(), pageBo.getPagesize());
		extListBo= new ExtListDataBo<Bset_Printer_WorkstationModel>(list, 0);
		return extListBo;
	}
	@Override
	public List<ComboxBo> getBset_GroupComboList(
			String enterpriseNo,String warehouseNo,String str, int start,
			int pagesize) throws Exception {
		String sql="select a.printer_group_no value,a.printer_group_name text,'['|| ltrim(a.printer_group_no)||']'||a.printer_group_name dropValue " +
				"from bset_group a where a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	@Override
	public String checkBset_GroupCombo(String enterpriseNo,String warehouseNo ,String printerGroupNo) {
		String sql="select a.printer_group_no from PNTSET_PRINTER_WORKSTATION a " +
				"where a.printer_group_no ='"+printerGroupNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	
	@Override
	public String checkPrinterGroupNo(String enterpriseNo,String warehouseNo, String printerGroupNo)
			throws Exception {
		String sql="select a.printer_group_no from bset_group a where a.warehouse_no='"+warehouseNo+
				"' and a.printer_group_no='"+printerGroupNo+
			    "' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	
	//检查打印机组是否有相同来类型
	@Override
	public List<String> checkPrinterType(String enterpriseNo,String warehouseNo,
			String printerGroupNo, String printerType) {
		String sql="select distinct aa.paper_type_no from "+
			"(select b.paper_type_no from  PNTSET_PRINTER_GROUP a , PNTDEF_DEFPRINTER b "+
			"where  a.printer_group_no ='"+printerGroupNo+
			"' and a.warehouse_no='"+warehouseNo+
			"' and a.enterprise_no='"+enterpriseNo+
			"' and a.printer_no=b.printer_no " +
			"  and a.warehouse_no=b.warehouse_no" +
			"  and a.enterprise_no=b.enterprise_no) aa "+
			"where aa.paper_type_no='"+printerType+"'";
		
		List list = genDao.getListByNativeSql(sql);
		return (List<String>) list;
	}
	@Override
	public List<ComboxBo> getPrinterGroupInfo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strQuery,String wheresql)
			throws Exception {
		String sql="select distinct a.printer_no value," +
				"'[' || ltrim(a.printer_no)||']'||a.printer_name dropValue "+
				"a.printer_no text " +
				"from PNTDEF_DEFPRINTER a," +
				"PNTSET_PAPER_TYPE b " +
				"where a.enterprise_no=b.enterprise_no " ;

				sql = sql+"and a.warehouse_no=b.warehouse_no " +
				"and a.paper_type_no=b.paper_type_no " +
				"and a.warehouse_no='"+strWarehouseNo+
				"' and a.enterprise_no='"+strEnterpriseNo+
				"' and a.paper_type_no not in(" +
				"  select pnf.paper_type_no " +
				"    from PNTSET_PRINTER_GROUP c, pntdef_defprinter  pnf " +
				"   where c.enterprise_no=pnf.enterprise_no " +
				"     and c.warehouse_no=pnf.warehouse_no  " +
				"     and c.printer_no=pnf.printer_no " +
				"     and c.warehouse_no='"+strWarehouseNo+"' "+
				"     and c.enterprise_no='"+strEnterpriseNo+"' " +
			    "     and c.printer_group_no='" +wheresql+"' "+
			    ")  " ;
				List list = genDao.getListByNativeSql(sql, ComboxBo.class);
				return (List<ComboxBo>) list;
		
	}
}
