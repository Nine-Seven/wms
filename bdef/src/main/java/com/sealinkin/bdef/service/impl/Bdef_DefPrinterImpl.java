package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.bdef.po.Bdef_Defprinter;
import com.sealinkin.bdef.service.IBdef_DefPrinterService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefPrinterImpl implements IBdef_DefPrinterService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	@Override
	public ExtListDataBo<Bdef_DefprinterModel> getPrinterList(
			String enterpriseNo,String warehouseNo,String queryStr,
			PageBo pageBo,Integer requestFlag) throws Exception {
		String sql=" select a.warehouse_no,a.printer_no,a.printer_name, " +
				   "    a.printer_driver,a.printer_port,a.status," +
				   "    f_get_fieldtext('N','DEF_STATUS',a.status)statusText," +
				   "    b.paper_type_no, b.paper_type_name printertypeText " +
				   "  from PNTDEF_DEFPRINTER a, PNTSET_PAPER_TYPE b  " +
				   " where 1=1 and a.warehouse_no='"+warehouseNo+"' " +
				   "   and a.enterprise_no='"+ enterpriseNo+"'   " +
				   "   and a.enterprise_no=b.enterprise_no(+) " +
				   "   and a.warehouse_no=b.warehouse_no(+) " +
				   "   and a.paper_type_no=b.paper_type_no(+) " ;		
		String totsql = "select count(1) from PNTDEF_DEFPRINTER a " +
				"where 1=1 and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+ enterpriseNo+"' ";	
		
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql = sql +" order by a.printer_no";
		
		List<Bdef_DefprinterModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefprinterModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bdef_DefprinterModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bdef_DefprinterModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bdef_DefprinterModel>(list, count);
        return extListBo;
	}
	@Override
	public boolean saveOrUpdatePrinter(String str) throws Exception {
		Bdef_Defprinter bd=(Bdef_Defprinter)JSONObject.toBean(JSONObject.fromObject(str),Bdef_Defprinter.class);
		this.genDao.saveOrUpdateObj(bd);
		return true;
	}
	@Override
	public String checkPrinterNo(String enterpriseNo,String warehouseNo,String printerNo) throws Exception {
		String sql="select a.printer_no from PNTDEF_DEFPRINTER a where a.warehouse_no='"+warehouseNo+
				"' and a.printer_no='"+printerNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	/**
	 * 填充打印机类型下拉
	 */
	@Override
	public List<ComboxBo> getPrinterTypeList(String enterpriseNo,
			String warehouseNo, String str) throws Exception {
		String sql = "select a.paper_type_no value,a.paper_type_no text," +
				"'['|| ltrim(a.paper_type_no)||']'||a.paper_type_name dropValue  "
				+ "from PNTSET_PAPER_TYPE a "
				+ " where a.enterprise_no='" + enterpriseNo + "' ";
			//	+ " and a.warehouse_No='" + warehouseNo + "' ";
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	
	}
	@Override
	public List<ComboxBo> getPrinterInfo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strQuery,String wheresql)
			throws Exception {
		String sql="select distinct a.printer_no value," +
				"'[' || ltrim(a.printer_no)||']'||a.printer_name dropValue, "+
				"a.printer_no text " +
				"from PNTDEF_DEFPRINTER a," +
				"PNTSET_PAPER_TYPE b " +
				"where a.enterprise_no=b.enterprise_no " ;
				
				if (strQuery != null && !strQuery.equals("")) {
					String ws = CommUtil.covtCollectionToWhereSql(strQuery);
					sql = sql + ws;
				}
				if (str != null && !str.equals("")) {
					sql = sql + 
							" and (a.printer_no like '%"+str+"%' " +
							"or a.printer_name like '%"+str+"%' )";
				}

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
			    ")  union all select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from dual" ;
				List list = genDao.getListByNativeSql(sql, ComboxBo.class);
				return (List<ComboxBo>) list;
	}
	@Override
	public List<ComboxBo> getTypeList(String strEnterpriseNo,
			String strWarehouseNo, String strQuery) throws Exception {
		String strSql="select distinct b.paper_type_no value,b.paper_type_name, "+
				"'[' || ltrim(b.paper_type_no)||']'||b.paper_type_name dropValue "+
				" from PNTSET_PAPER_TYPE b ,PNTDEF_DEFPRINTER a " + 
				"where 1=1 " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				 "and a.enterprise_no=b.enterprise_no " +
				 "and a.paper_type_no=b.paper_type_no " +
				 "and a.warehouse_no='"+strWarehouseNo+"' " ;
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql + ws;
		}
		/*strSql = strSql+" order by a.paper_type_no";*/
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
			String strWarehouseNo, String strQuery) throws Exception {
		String strSql="select distinct a.status value,a.status text,"+
				" f_get_fieldtext('N','DEF_STATUS',a.status) dropValue "+
				" from PNTDEF_DEFPRINTER a " + 
				"where 1=1 " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				 "and a.warehouse_no='"+strWarehouseNo+"' " ;
			     //"and a.owner_no in("+strOwnerNo+") ";
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql + ws;
		}
			
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
}
