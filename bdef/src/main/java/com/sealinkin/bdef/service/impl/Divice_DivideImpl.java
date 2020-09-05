package com.sealinkin.bdef.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Device_DivideDModel;
import com.sealinkin.bdef.model.Device_DivideMModel;
import com.sealinkin.bdef.model.Device_Divide_GroupModel;
import com.sealinkin.bdef.po.Device_DivideD;
import com.sealinkin.bdef.po.Device_DivideM;
import com.sealinkin.bdef.po.Device_Divide_Group;
import com.sealinkin.bdef.service.IDivice_DivideService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Divice_DivideImpl implements IDivice_DivideService {
private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取设备组
	@Override
	public ExtListDataBo<Device_Divide_GroupModel> getDeviceDivideGroup(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo) throws Exception {
		String sql=" select a.device_group_no,a.device_group_name,a.use_type,a.status,a.default_flag," +
				   "        f_get_fieldtext('DEVICE_DIVIDE_GROUP','USETYPE',a.use_type)useTypeText ," +
				   "        f_get_fieldtext('DEVICE_DIVIDE_GROUP','STATUS',a.status)statusText, " +
				   "        f_get_fieldtext('DEVICE_DIVIDE_GROUP','DEFAULTFLAG',a.default_flag)defaultFlagText " +
				   "   from device_divide_group a " +
				   "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' ";
		String totsql=" select count(*)  " +
				      "   from device_divide_group a " +
				      "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+warehouseNo+"' ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql += " order by a.device_group_no ";
		List<Device_Divide_GroupModel> list  = genDao.getListByNativeSql(sql,Device_Divide_GroupModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		
		ExtListDataBo<Device_Divide_GroupModel> extListBo= new ExtListDataBo<Device_Divide_GroupModel>(list, count);
        return extListBo;
	}
	//保存设备组
	@Override
	public void saveDeviceDivideGroup(String str) throws Exception {		
		Device_Divide_Group ddg= (Device_Divide_Group)JSON.parseObject(str,Device_Divide_Group .class);
		this.genDao.saveOrUpdateObj(ddg);
	}
	//获取设备
	@Override
	public ExtListDataBo<Device_DivideMModel> getDeviceDivideM(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo) throws Exception {	
		String sql=" select a.device_group_no,a.device_no,a.device_name,a.device_alias," +
				   "        a.device_type,a.operate_type,a.max_qty,a.box_items,a.use_times," +
				   "        a.cust_qty,a.a_stock_no,a.status,a.grade,a.produce_capacity," +
				   "        a.produce_capacity_type,a.divice_rate,a.strategy_id,a.ref_rate_flag," +
				   "        f_get_fieldtext('ODATA_DIFFERENT_D','ADD_FLAG',a.ref_rate_flag)refRateFlagText," +
				   "        f_get_fieldtext('DEVICE_DIVIDE_M','PRODUCE_CAPACITY_TYPE',a.produce_capacity_type)produceCapacityTypeText," +
				   "        f_get_fieldtext('DEVICE_DIVIDE_M','DEVICETYPE',a.device_type)deviceTypeText, " +
				   "        f_get_fieldtext('DEVICE_DIVIDE_M','OPERATETYPE',a.operate_type)operateTypeText, " +
				   "        f_get_fieldtext('DEVICE_DIVIDE_M','STATUS',a.status)statusText " +
				   "   from device_divide_m a " +
				   "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' ";
		
		String totsql=" select count(*)  " +
					  "   from device_divide_m a " +
					  "  where a.enterprise_no='"+currEnterpriseNo+"' " +
					  "    and a.warehouse_no='"+warehouseNo+"' ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			totsql=totsql+ws;
		}					
		List<Device_DivideMModel> list  = genDao.getListByNativeSql(sql,Device_DivideMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
			
		ExtListDataBo<Device_DivideMModel> extListBo= new ExtListDataBo<Device_DivideMModel>(list, count);
	       return extListBo;
	}
	
	//获取设备组combo
	@Override
	public List<ComboxBo> getDeviceDivideGroupCombo(String currEnterpriseNo,
			String warehouseNo, String strQuery) throws Exception {
		 String sql = "select distinct a.device_group_no value,a.device_group_name text," +
			 		" '['|| ltrim(a.device_group_no)||']'||a.device_group_name dropValue" +
			 		" from device_divide_group a where 1=1 " +
			 		" and a.warehouse_no='"+warehouseNo+"' "+
			 		" and a.enterprise_no='"+currEnterpriseNo+"' ";
		 if(strQuery!=null && !strQuery.equals("")){
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql+ws;
		}	
		List list= genDao.getListByNativeSql(sql,ComboxBo.class,0,100);	
		return  (List<ComboxBo>)list;
	}
	//保存设备
	@Override
	public void saveDeviceDivideM(String str) throws Exception {
		Device_DivideM ddg= (Device_DivideM)JSON.parseObject(str,Device_DivideM.class);
		this.genDao.saveOrUpdateObj(ddg);
	}
	//设备唯一性
	@Override
	public String checkDeviceNo1S01(String currEnterpriseNo,
			String warehouseNo, String str) throws Exception {
		String sql="select a.device_no from device_divide_m a " +
				"    where a.enterprise_no='"+currEnterpriseNo+"' " +
				"      and a.warehouse_no='"+warehouseNo+"' "+				
				"      and a.device_no='"+str+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//获取设备combo
	@Override
	public List<ComboxBo> getDeviceDivideMCombo(String currEnterpriseNo,
			String warehouseNo, String strQuery) throws Exception {
		 String sql = "select distinct a.device_no value,a.device_name text," +
		              "       '['|| ltrim(a.device_no)||']'||a.device_name dropValue" +
		              "  from device_divide_m a " +
		              " where a.warehouse_no='"+warehouseNo+"' "+
					  "   and a.enterprise_no='"+currEnterpriseNo+"' ";
		 if(strQuery!=null && !strQuery.equals("")){
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql+ws;
		}	
		List list= genDao.getListByNativeSql(sql,ComboxBo.class,0,100);	
		return  (List<ComboxBo>)list;
	}
	
	//保存格子号
	@Override
	public void saveDeviceDivideD(String enterpriseNo, String warehouseNo,
			String workerNo,String str, String perfix, String bayXMin, String bayXMax,
			String floorMin, String floorMax) throws Exception {
		Device_DivideDModel ddd= (Device_DivideDModel)JSON.parseObject(str,Device_DivideDModel.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(perfix);
		inList.add(bayXMin);
		inList.add(bayXMax);
		inList.add(floorMin);
		inList.add(floorMax);
		inList.add(ddd.getDeviceGroupNo());
		inList.add(ddd.getDeviceNo());
		inList.add(ddd.getMixFlag());
		inList.add(ddd.getMixSupplier());
		inList.add(ddd.getMaxQty());
		inList.add(ddd.getMaxCase());
		inList.add(ddd.getMaxVolume());
		inList.add(ddd.getMaxWeight());
		inList.add(ddd.getStatus());
		inList.add(workerNo);
		
		outList.add("S");
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKOBJ_CDEF.p_create_divide_d");
		
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
	}
	@Override
	public ExtListDataBo<Device_DivideDModel> getDeviceDivideD(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo) throws Exception {
		String sql="select a.device_group_no,a.device_no,a.bay_x,a.stock_y,a.device_cell_no," +
				   "  a.mix_flag,a.mix_supplier,a.max_qty,a.max_weight,a.max_volume,a.max_case ," +
				   "  a.status,a.check_status,a.rgst_name,a.rgst_date,a.pick_order," +
				   "  f_get_fieldtext('CDEF_DEFCELL','CELL_STATUS',a.status)statusText," +
				   "  f_get_fieldtext('N','MIX_SUPPLIER',a.mix_supplier)mixSupplierText," +
				   "  f_get_fieldtext('N','MIX_FLAG',a.Mix_Flag)mixFlagText," +
				   "  b.device_name " +
				   "  from device_divide_d a, device_divide_m b" +
				   " where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "   and a.warehouse_no='"+warehouseNo+"' " +
				   "   and a.enterprise_no=b.enterprise_no " +
				   "   and a.warehouse_no=b.warehouse_no " +
				   "   and a.device_group_no=b.device_group_no " +
				   "   and a.device_no=b.device_no ";
		
		String totsql=" select count(*)  " +
				      "   from device_divide_d a " +
				      "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+warehouseNo+"' ";
		
		if(strQuery!=null && !strQuery.equals("")){
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			totsql=totsql+ws;
		}			
		sql=sql+" order by a.stock_y,a.bay_x";
		
		List<Device_DivideDModel> list  = genDao.getListByNativeSql(sql,Device_DivideDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		
		ExtListDataBo<Device_DivideDModel> extListBo= new ExtListDataBo<Device_DivideDModel>(list, count);
		return extListBo;		
	}
	
	//编辑拣货顺序
	@Override
	public void changePickOrder(String str) throws Exception {
		Device_DivideDModel ddd= (Device_DivideDModel)JSON.parseObject(str,Device_DivideDModel.class);
		
		String sql=" update device_divide_d a " +
				   "    set a.pick_order='"+ddd.getPickOrder()+"' " +
				   "  where a.enterprise_no='"+ddd.getEnterpriseNo()+"' " +
				   "    and a.warehouse_no='"+ddd.getWarehouseNo()+"'" +
				   "    and a.device_no='"+ddd.getDeviceNo()+"'" +
				   "    and a.device_cell_no='"+ddd.getDeviceCellNo()+"' " +
				   "    and a.device_group_no='"+ddd.getDeviceGroupNo()+"' ";
		this.genDao.exceuteSql(sql);
	}
	
	//修改格子号
	@Override
	public void editDeviceDivideD(String str) throws Exception {
		Device_DivideD ddg= (Device_DivideD)JSON.parseObject(str,Device_DivideD.class);
		this.genDao.update(ddg);
	}
	
	//检验格子号
	@Override
	public MsgRes checkCell(String currEnterpriseNo, String warehouseNo,
			String strDeviceDivideGroup, String strDeviceNo, String perfix,
			String bayXMin, String bayXMax, String floorMin, String floorMax)
			throws Exception {

		String sql="";
		String flag="";
		//判断位
		if(!bayXMin.equals("") && bayXMin!=null && !bayXMax.equals("") && bayXMax!=null){
			sql=" select a.device_cell_no " +
					"   from device_divide_d a " +
					"  where a.enterprise_no='"+currEnterpriseNo+"' " +
				    "    and a.warehouse_no='"+warehouseNo+"' " +
				    "    and a.device_group_no='"+strDeviceDivideGroup+"' " +
				    "    and a.device_no='"+strDeviceNo+"'" +
				    "    and a.bay_x >= '"+(perfix+bayXMin)+ "' "+
				    "    and a.bay_x <= '"+(perfix+bayXMax)+"' ";
				
				 List list = genDao.getListByNativeSql(sql);	
				 if(list.size()>0){
						flag="1";
				 }else{
					 flag="0";
				 }

		}
		
		//判断层
		if(!floorMin.equals("") && floorMin!=null && !floorMax.equals("") && floorMax!=null){
			sql=" select a.device_cell_no " +
				"   from device_divide_d a " +
				"  where a.enterprise_no='"+currEnterpriseNo+"' " +
			    "    and a.warehouse_no='"+warehouseNo+"' " +
			    "    and a.device_group_no='"+strDeviceDivideGroup+"' " +
			    "    and a.device_no='"+strDeviceNo+"'" +
			    "    and a.bay_x >= '"+(perfix+bayXMin)+"' "+ 
			    "    and a.bay_x <= '"+(perfix+bayXMax)+"' "+ 
			    "    and a.stock_y >= '"+floorMin+"' "+ 
			    "    and a.stock_y <= '"+floorMax+"' ";
			
			 List list = genDao.getListByNativeSql(sql);	
			 if(list.size()>0){
					flag="2";
			 }else{
				 flag="0";
			 }
		}
		
		if(flag.equals("0")){
			return new MsgRes(true,"-1","");
		}else{
			return new MsgRes(true,flag,"");
		}
		
	}	
}
