package com.sealinkin.odata.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.jk.model.JkBymSheetwarehouseModel;
import com.sealinkin.jk.po.JkBymSheetwarehouse;

import com.sealinkin.odata.service.IOdata_BYMSheetService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_BYMSheetImpl implements IOdata_BYMSheetService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}



	//获取BYM出货订单
	@Override
	public ExtListDataBo<JkBymSheetwarehouseModel> getBYMSheet(String str, PageBo pageBo, String warehouseNo)
			throws Exception {
		String sql="select t1.sheet_no, t1.warehouse_no, t1.rgst_name, t1.rgst_date,"+
			       "f_get_fieldtext('JK_BYM_SHEETWAREHOUSE','CORPKEY',t1.corpkey)corpkey, "+
			       "f_get_fieldtext('JK_BYM_SHEETWAREHOUSE','SHEETTYPE',t1.sheet_type)sheetType "+
				   " from JK_BYM_SHEETWAREHOUSE t1 where 1=1 "+
				   " and t1.warehouse_no ='"+warehouseNo+"'";
				
		String strTotsql = "select count(1) from JK_BYM_SHEETWAREHOUSE t1 where 1=1 "+
					"and t1.warehouse_no ='"+warehouseNo+"'";
				
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}	
		
		sql=sql+" order by t1.rgst_date desc";
		List<JkBymSheetwarehouseModel> list = genDao.getListByNativeSql(sql,JkBymSheetwarehouseModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    for( int i =0 ; i<list.size() ; i++){	   
		   list.get(i).setDateText(formatter.format(list.get(i).getRgstDate()));
	    }
		
		ExtListDataBo<JkBymSheetwarehouseModel> extListBo = new ExtListDataBo<JkBymSheetwarehouseModel>(list,intCount);
		return extListBo;
	}
	
	//获取当前用户拥有的仓库
	@Override
	public List<ComboxBo> getWarehouse(String workerNo) {
		String sql ="select  a.warehouse_no value,b.warehouse_name text,'['|| ltrim(a.warehouse_no)||']'||b.warehouse_name dropValue "+
					" from bset_worker_loc a,  bdef_defloc b "+
					"where a.warehouse_no=b.warehouse_no "+
					"and a.worker_no='"+workerNo+"'";
		
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	
	//保存出货订单
	@Override
	public void saveBYMSheet(String str) throws Exception {
		List<JkBymSheetwarehouse> list=JSON.parseArray(str,JkBymSheetwarehouse.class);
		this.genDao.saveList(list);
	}
	//删除出货订单
	@Override
	public MsgRes deleteBYMSheet(String warehouseNo, String sheetNo,
			String rgstName, String rgstDate,String corpkey,String sheettype) 
	{
		
	    Long timestamp = Long.parseLong(rgstDate);    
	    String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(timestamp)); 
	         
	    String sql=" select a.value from wms_deffieldval a where a.table_name='JK_BYM_SHEETWAREHOUSE' and a.colname='CORPKEY' and a.text='"+corpkey+"'";
	    List list = genDao.getListByNativeSql(sql);
		
	    sql="select a.value from wms_deffieldval a where a.table_name='JK_BYM_SHEETWAREHOUSE' and a.colname='SHEETTYPE' and a.text='"+sheettype+"'";
	    List list1 = genDao.getListByNativeSql(sql);
	    
		String strSql = "delete from JK_BYM_SHEETWAREHOUSE "+
			"where warehouse_no='"+ warehouseNo + 
			 "' and sheet_no='" + sheetNo + 
			 "' and corpkey='" + list.get(0).toString()  + 
			 "' and rgst_name='" +rgstName +
			 "' and sheet_type='" +list1.get(0).toString() +
			 "' and rgst_date= (select to_date('"+date+"','yyyy-mm-dd hh24:mi:ss') from dual) ";
		
		this.genDao.updateBySql(strSql);
		return new MsgRes(Boolean.valueOf(true),
				"删除成功", null);
	}
	@Override
	public MsgRes createData(String workerNo) throws Exception {
		List outList=new ArrayList();
		List inList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(workerNo);
		
		resultList=genDao.execProc(inList, outList, "PKOBJ_JK.P_BYM_CODEANDSHEET");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(Boolean.valueOf(true),
				"生成数据成功", null);
	}
	@Override
	public List<ComboxBo> getCompany() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='JK_BYM_SHEETWAREHOUSE' "+
				 "and colname='CORPKEY' ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	@Override
	public ExtListDataBo<JkBymSheetwarehouseModel> getBymSheetErrorInfo(
			String str, PageBo pageBo, String warehouseNo) throws Exception {
		String sql="select t1.sheet_no, " +
				"t1.error_desc as dateText , " +
				"t1.rgst_name, " +
				"t1.rgst_date, " +
				"t1.warehouse_no " +
				"from jk_bym_codeandsheet_error t1 " +
				"where 1=1 " +
				"and t1.warehouse_no ='"+warehouseNo+"'";
			
		String strTotsql = "select count(1) from jk_bym_codeandsheet_error t1 where 1=1 "+
					"and t1.warehouse_no ='"+warehouseNo+"'";
				
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}	
		sql=sql+" order by t1.SHEET_NO desc";
		List<JkBymSheetwarehouseModel> list = genDao.getListByNativeSql(sql,JkBymSheetwarehouseModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<JkBymSheetwarehouseModel> extListBo = new ExtListDataBo<JkBymSheetwarehouseModel>(list,intCount);
		return extListBo;
	}
	@Override
	public List<ComboxBo> getsheetType() throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and table_name='JK_BYM_SHEETWAREHOUSE' "+
				 "and colname='SHEETTYPE'";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	

}
