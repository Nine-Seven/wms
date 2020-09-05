/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
package com.sealinkin.bdef.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import com.sealinkin.bdef.service.IBset_workstationDivideService;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.bset.model.Bset_WorkstationDivideModel;
import com.sealinkin.bset.po.Bset_WorkstationDivide;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bset_workstationDivideImpl implements IBset_workstationDivideService{
	
	 private IGenericManager genDao;
	 public IGenericManager getGenDao() {
		 return genDao;
	 }
	 public void setGenDao(IGenericManager genDao) {
		 this.genDao = genDao;
	 }
	//获取工作站信息
	@Override
	public ExtListDataBo<Bset_Printer_WorkstationModel> getBsetPrinterWorkstationList(
			String enterpriseNo, String warehouseNo, String wheresql,
			PageBo pageBo) throws Exception {
		String sql=" select a.warehouse_no,a.workstation_no,a.workstation_name " +
				" from PNTSET_PRINTER_WORKSTATION a  " +
				" where a.warehouse_no= '"+warehouseNo+ "' "+
				" and a.enterprise_no= '"+enterpriseNo+"' " +
				" and a.workstation_no not in(select b.workstation_no from " +
				"bset_workstation_divide b where b.warehouse_no='"+warehouseNo+"' " +
				"and b.enterprise_no='"+enterpriseNo+"' )";
	        
			if(wheresql!=null && !wheresql.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(wheresql);
				sql=sql+ws;
			}
	        String totsql = "select count(*) from (" + sql + ") " ;	

			List<Bset_Printer_WorkstationModel> list = genDao.getListByNativeSql(sql,Bset_Printer_WorkstationModel.class,pageBo.getStart(), pageBo.getPagesize());
			Integer count = genDao.getCountByNativeSql(totsql);
			ExtListDataBo<Bset_Printer_WorkstationModel> extListBo= new ExtListDataBo<Bset_Printer_WorkstationModel>(list, count);
	        return extListBo;
	}
	//获取设备组列表
	@Override
	public ExtListDataBo<Bset_WorkstationDivideModel> getBsetWorkstationDivideList(
			String enterpriseNo, String warehouseNo, String wheresql,
			PageBo pageBo) throws Exception {
		String sql="select a.warehouse_no,a.device_group_no,a.workstation_no," +
				"(select b.workstation_name from  PNTSET_PRINTER_WORKSTATION b where b.workstation_no =a.workstation_no and a.enterprise_no=b.enterprise_no and b.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ) workstationName  " +
				"from bset_workstation_divide a " +
				"where " +
				"a.device_group_no='"+wheresql+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		
        String totsql = "select count(*) from (" + sql + ") " ;	

		List<Bset_WorkstationDivideModel> list = genDao.getListByNativeSql(sql,Bset_WorkstationDivideModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Bset_WorkstationDivideModel> extListBo= new ExtListDataBo<Bset_WorkstationDivideModel>(list, count);
        return extListBo;
	}
	//设备组添加工作站
	@Override
	public boolean saveWorkstationDivide(String str) throws Exception {
		Collection<Bset_WorkstationDivide> printer=JSONArray.toCollection(JSONArray.fromObject(str),Bset_WorkstationDivide.class);
		List<Bset_WorkstationDivide> printerGroup=(List)printer;
		this.genDao.saveList(printerGroup);
		return true;
	}
	//设备组移除工作站
	@Override
	public boolean deleteWorkstationDivide(String enterpriseNo,
			String warehouseNo, String deviceGroupNo, String workstationNo)
			throws Exception {
		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=deviceGroupNo.split(",");
		String wheresql3[]=workstationNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete bset_workstation_divide a " +
			"where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
			"a.device_group_no='"+wheresql2[i].trim()+"' and " +
			"a.workstation_no='"+wheresql3[i].trim()+
			"' and a.enterprise_no='"+enterpriseNo+"'";
			genDao.updateBySql(sql);
		}
		return true;
	}

}	


