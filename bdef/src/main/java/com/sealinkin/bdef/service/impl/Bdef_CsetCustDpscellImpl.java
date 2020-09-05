/**
 * 客户与电子标签储位对应关系
 * @author hcx
 */
package com.sealinkin.bdef.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.bdef.po.Bdef_CsetCustDpscell;
import com.sealinkin.bdef.service.IBdef_CsetCustDpscellService;
import com.sealinkin.cdef.model.Cdef_DefcellModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_CsetCustDpscellImpl implements IBdef_CsetCustDpscellService{
	
	private IGenericManager genDao;
		
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取储位
	@Override
	public List getCdef_DefCell(String enterpriseNo,String warehouseNo,String strCustNo,String str) {
		String sql="";
        sql="select distinct a.stock_x from cdef_defcell a, CDEF_DEFAREA b, " +
             "cdef_defcell_dps c,device_divide_m d "+ 
             "where a.warehouse_no = b.warehouse_no "+ 
             "and a.enterprise_no = b.enterprise_no "+
             "and a.enterprise_no = '"+enterpriseNo+"' "+
             "and a.warehouse_no = '"+warehouseNo+"' "+
             "and a.ware_no = b.ware_no "+ 
             "and a.area_no = b.area_no " +
             "and a.enterprise_no=c.enterprise_no " +
             "and a.warehouse_no=c.warehouse_no " +
             "and a.cell_no=c.cell_no " +
             "and c.enterprise_no=d.enterprise_no " +
             "and c.warehouse_no=d.warehouse_no " +
             "and c.device_no=d.device_no "+
             "and b.area_usetype = '1' "+
             "and b.area_attribute = '1' "+ 
             "and b.attribute_type = '9' " +
//             "and d.device_group_no in('X','Y') " +
             "and d.device_no= '"+str+"' ";
        List<Cdef_DefcellModel> listStock_x=genDao.getListByNativeSql(sql, Cdef_DefcellModel.class, 0, 100000);
        sql="select distinct a.bay_x from cdef_defcell a, CDEF_DEFAREA b, " +
             "cdef_defcell_dps c,device_divide_m d "+ 
             "where a.warehouse_no = b.warehouse_no "+ 
             "and a.enterprise_no = b.enterprise_no "+
             "and a.enterprise_no = '"+enterpriseNo+"' "+
             "and a.warehouse_no = '"+warehouseNo+"' "+
             "and a.ware_no = b.ware_no "+ 
             "and a.area_no = b.area_no " +
             "and a.enterprise_no=c.enterprise_no " +
             "and a.warehouse_no=c.warehouse_no " +
             "and a.cell_no=c.cell_no " +
             "and c.enterprise_no=d.enterprise_no " +
             "and c.warehouse_no=d.warehouse_no " +
             "and c.device_no=d.device_no "+
             "and b.area_usetype = '1' "+
             "and b.area_attribute = '1' "+ 
             "and b.attribute_type = '9' " +
//             "and d.device_group_no in('X','Y') " +
             "and d.device_no= '"+str+"' ";
        List<Cdef_DefcellModel> listBay_x=genDao.getListByNativeSql(sql, Cdef_DefcellModel.class, 0, 100000);
        
        if(listStock_x.size()==0 || listBay_x.size()==0){
        	return null;
        }
        StringBuilder sb=new StringBuilder("");
        for(int i=0;i<listStock_x.size();i++){
        	for(int j=0;j<listBay_x.size();j++){
        		sb.append("max(case  when e.stock_x='"+listStock_x.get(i).getStockX()+"' and e.bay_x='"+listBay_x.get(j).getBayX()+"' then e.cell_no_and else null end) col"+listStock_x.get(i).getStockX()+listBay_x.get(j).getBayX()+",");
        	}
        }
        sql ="select e.warehouse_no,e.ware_no,e.area_no,e.stock_no,e.stock_y,"+sb.toString().substring(0, sb.toString().length()-1)+
        	 " from(select d.WAREHOUSE_NO,d.OWNER_NO,d.WARE_NO,d.AREA_NO,d.STOCK_NO,d.STOCK_X,d.BAY_X, "+
             "d.STOCK_Y ,d.cell_no||','|| case "+
        	 "when c.dps_cell_no is not null and c.cust_no = '"+strCustNo+"' then '1' "+
             "when c.dps_cell_no is not null then '2' "+
             "when c.dps_cell_no is null then '3' end cell_no_and "+ 
             "from(select a.ENTERPRISE_NO, a.WAREHOUSE_NO,a.OWNER_NO,a.WARE_NO,a.AREA_NO,a.STOCK_NO,a.STOCK_X,a.BAY_X,a.STOCK_Y ,a.cell_no "+
             "from cdef_defcell a, CDEF_DEFAREA b, " +
             "cdef_defcell_dps c,device_divide_m d "+ 
             "where a.warehouse_no = b.warehouse_no "+ 
             "and a.enterprise_no = b.enterprise_no "+
             "and a.enterprise_no = '"+enterpriseNo+"' "+
             "and a.warehouse_no = '"+warehouseNo+"' "+
             "and a.ware_no = b.ware_no "+ 
             "and a.area_no = b.area_no " +
             "and a.enterprise_no=c.enterprise_no " +
             "and a.warehouse_no=c.warehouse_no " +
             "and a.cell_no=c.cell_no " +
             "and c.enterprise_no=d.enterprise_no " +
             "and c.warehouse_no=d.warehouse_no " +
             "and c.device_no=d.device_no "+
             "and b.area_usetype = '1' "+
             "and b.area_attribute = '1' "+ 
             "and b.attribute_type = '9' " +
//             "and d.device_group_no in('X','Y') " +
             "and d.device_no= '"+str+"' )d "+
             "left join cset_cust_dpscell c "+ 
             "on d.cell_no=c.dps_cell_no and d.warehouse_no=c.warehouse_no and d.enterprise_no=c.enterprise_no)e "+
             "group by e.warehouse_no,e.ware_no,e.area_no,e.stock_no,e.stock_y order by e.stock_y";
        
		List list=genDao.getListByNativeSql(sql);		
		return list;	
	}
	//新增客户与电子标签储位对应关系
	@Override
	public MsgRes add(String jsonMaster)throws Exception {
		Bdef_CsetCustDpscell bdefCsetCustDpscell=(Bdef_CsetCustDpscell)JSON.parseObject(jsonMaster,Bdef_CsetCustDpscell.class);
		this.genDao.saveObj(bdefCsetCustDpscell);
		return new MsgRes(true, "新增成功！", "");	}
	//解除客户与电子标签储位对应关系
	@Override
	public MsgRes delete(String enterpriseNo,String warehouseNo, String strCustNo,String str) throws Exception {
		String sql = "delete from cset_cust_dpscell c "+
				     "where c.warehouse_no = '"+warehouseNo+"' "+
	                 "and c.cust_no = '"+strCustNo+"' "+
	                 "and c.enterprise_no = '"+enterpriseNo+"'  " +
	                 "and c.dps_cell_no= '"+str+"'  ";
		genDao.updateBySql(sql);
		return new MsgRes(true, "删除成功！", "");
	}
	//检索已分配储位客户
	@Override
	public String checkCustNo(String enterpriseNo,String warehouseNo,String strCustNo,String wheresql) {
		String sql="select a.cust_no from cset_cust_dpscell a where a.cust_no = '"+strCustNo+"' " +
				   "and a.warehouse_no = '"+warehouseNo+
				   "' and a.enterprise_no='"+enterpriseNo+"' ";
		if (wheresql != null && !wheresql.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(wheresql);
			sql = sql + ws;
		}
		List<String> custList=genDao.getListByNativeSql(sql);
		if(custList.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//检索已分配客户储位
	@Override
	public String checkDpsCellNo(String enterpriseNo,String warehouseNo, String strDpsCellNo) {
		String sql="select a.dps_cell_no from cset_cust_dpscell a where a.dps_cell_no = '"+strDpsCellNo+"' " +
				   "and a.warehouse_no = '"+warehouseNo+
				    "' and a.enterprise_no='"+enterpriseNo+"' ";
		List<String> cellList=genDao.getListByNativeSql(sql);
		if(cellList.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//获取客户列表
	@Override
	public ExtListDataBo<Bdef_DefCustModel> getBdefCsetCust(
			String enterpriseNo,String warehouseNo,String workerOwner,
			String strAssignedCustomer,String wheresql,String str,
			String strFlag,PageBo pageBo)
			throws Exception {
		String sql="select distinct a.cust_no," +
				"'['|| ltrim(a.cust_no)||']'||a.cust_name custName ," +
				"case when b.device_group_no <> '"+strFlag+"' then null else b.dps_cell_no  end  dpsCellNo, " +
				"case when b.device_group_no <> '"+strFlag+"' then null else b.device_no end deviceNo " +
				"from bdef_defcust a ,cset_cust_dpscell b " +
				"where 1=1 " +
			    " and a.enterprise_no='"+enterpriseNo+"' "+
		        "and a.enterprise_no=b.enterprise_no(+) " +
                "and a.owner_no=b.owner_no(+) " +
                "and a.cust_no=b.cust_no(+) " ;
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if(strAssignedCustomer!=null ){
		    int i = Integer.parseInt(strAssignedCustomer);
		   if(i == 2)
		      {
			     sql=sql+" and a.cust_no in (" +
			     		"select c.cust_no from cset_cust_dpscell c " +
                   "where 1=1 " +                    
                    "and c.enterprise_no='"+enterpriseNo+"' " +
                    "and c.warehouse_no='"+warehouseNo+"'  ";

			    
		      }
		   if(i == 1){
			     sql=sql+" and  a.cust_no not in(" +
				     		"select c.cust_no from cset_cust_dpscell c " +
			                   "where 1=1 " +                    
			                    "and c.enterprise_no='"+enterpriseNo+"' " +
			                    "and c.warehouse_no='"+warehouseNo+"' ";
		   }
		   if (wheresql != null && !wheresql.equals("")) {
				String w = CommUtil.covtCollectionToWhereSql(wheresql);
				sql = sql + w;
			}
	     
		   sql = sql +") ";
		}
		 sql=sql+" order by a.cust_no ";
	     String totsql = "select count(*) from (" + sql + ") " ;	
		List<Bdef_DefCustModel> list = genDao.getListByNativeSql(sql,Bdef_DefCustModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Bdef_DefCustModel> extListBo=new ExtListDataBo<Bdef_DefCustModel>(list, count);
        return extListBo;
	}
    //获取货主下拉列表
	@Override
	public List<ComboxBo> getOwnerComboList(String enterpriseNo,String str) throws Exception {

		String strSql="select a.owner_no value,a.owner_name text," +
				"'['|| ltrim(a.owner_no)||']'||a.owner_name dropValue " +
				"from bdef_defowner a where 1=1 " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.owner_no in("+str+") "+
				"order by a.owner_no asc";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取设备分播组
	@Override
	public List<ComboxBo> getDeviceGroupNoList(String enterpriseNo,
			String warehouseNo, String str) throws Exception {
		String strSql="select a.device_group_no value,a.device_group_name text," +
				"'['|| ltrim(a.device_group_no)||']'||a.device_group_name dropValue " +
				"from device_divide_group a where 1=1 " +
				"and a.use_type='1' " +
//				"and a.device_group_no in('X','Y') " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"' " ;
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		strSql = strSql +" order by a.device_group_no ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取分播线
	@Override
	public List<ComboxBo> getDeviceNoList(String enterpriseNo,
			String warehouseNo, String str) throws Exception {
		String strSql="select a.device_no value,a.device_name text," +
				"'['|| ltrim(a.device_no)||']'||a.device_name dropValue " +
				"from device_divide_m a where 1=1 " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"' " ;
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		strSql = strSql +" order by value ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//检索未分播完成客户
	@Override
	public MsgRes checkDeliverCustNo(String enterpriseNo, String warehouseNo,String ownerNo,
			String strCustNo)throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(enterpriseNo);//strEnterpriseNo
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(ownerNo);//strOwnerNo
		inList.add(strCustNo);//strCustNo
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_BDEF.P_CheckDeliverCustNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "",resultList.get(0).toString());
	}
	
}
