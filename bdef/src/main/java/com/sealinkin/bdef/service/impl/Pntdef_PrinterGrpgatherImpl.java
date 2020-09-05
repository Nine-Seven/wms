/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
package com.sealinkin.bdef.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Pntdef_PrinterGrpgatherModel;
import com.sealinkin.bdef.model.Pntset_GrpgatherPrinterGroupModel;
import com.sealinkin.bdef.po.Pntdef_PrinterGrpgather;
import com.sealinkin.bdef.po.Pntset_GrpgatherPrinterGroup;
import com.sealinkin.bdef.service.IPntdef_PrinterGrpgatherService;
import com.sealinkin.bset.model.Bset_GroupModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Pntdef_PrinterGrpgatherImpl implements IPntdef_PrinterGrpgatherService{
	
	 private IGenericManager genDao;
	 public IGenericManager getGenDao() {
		 return genDao;
	 }
	 public void setGenDao(IGenericManager genDao) {
		 this.genDao = genDao;
	 }
	//获取打印机群组信息
	@Override
	public ExtListDataBo<Pntdef_PrinterGrpgatherModel> getPntdefPrinterGrpgatherList(String enterpriseNo,String warehouseNo,
			String queryStr, PageBo pageBo, Integer requestFlag)
			throws Exception {
		String sql="select a.warehouse_no,a.grpgather_no,a.grpgather_name from pntdef_printer_grpgather a where " +
				" a.warehouse_no='"+warehouseNo+"' " +
				" and a.enterprise_no='"+enterpriseNo+"' " ;
	
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		sql += " order by a.grpgather_no ";
        String totsql = "select count(*) from (" + sql + ") " ;	
        
		List<Pntdef_PrinterGrpgatherModel> list = null;
		Integer count = 0;
		ExtListDataBo<Pntdef_PrinterGrpgatherModel> extListBo=null;
		
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Pntdef_PrinterGrpgatherModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Pntdef_PrinterGrpgatherModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Pntdef_PrinterGrpgatherModel>(list, count);
        return extListBo;
	}
	//打印机组信息
	@Override
	public ExtListDataBo<Bset_GroupModel> getBset_GroupList(
			String enterpriseNo,String warehouseNo, String wheresql,String str, PageBo pageBo)
			throws Exception {
		
		String sql="select a.warehouse_no,a.printer_group_no,a.printer_group_name " +
						"from BSET_GROUP a " +
				"where a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'" ;
		
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql+ws;
		}
				
	   sql=sql+"  and a.printer_group_no not in(select b.printer_group_no " +
				"from pntset_grpgather_printer_group b where b.enterprise_no='"+enterpriseNo+"' " +
			    "and b.grpgather_no='"+wheresql+"' "+
				"and b.warehouse_no='"+warehouseNo+"')";
		
        String totsql = "select count(*) from (" + sql + ") " ;	

		List<Bset_GroupModel> list = genDao.getListByNativeSql(sql,Bset_GroupModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Bset_GroupModel> extListBo= new ExtListDataBo<Bset_GroupModel>(list, count);
        return extListBo;
	}
	//打印机群组列表
	public ExtListDataBo<Pntset_GrpgatherPrinterGroupModel> getPntsetGrpgatherPrinterGroupList(
			String enterpriseNo, String warehouseNo,String str, String wheresql,
			PageBo pageBo) throws Exception {
		String sql="select a.warehouse_no,a.grpgather_no,a.printer_group_no," +
				"(select b.printer_group_name from  BSET_GROUP b where b.printer_group_no =a.printer_group_no and a.enterprise_no=b.enterprise_no and b.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ) printerGroupName  " +
				"from pntset_grpgather_printer_group a " +
				"where " +
				"a.grpgather_no='"+wheresql+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql+ws;
		}
		
        String totsql = "select count(*) from (" + sql + ") " ;	

		List<Pntset_GrpgatherPrinterGroupModel> list = genDao.getListByNativeSql(sql,Pntset_GrpgatherPrinterGroupModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Pntset_GrpgatherPrinterGroupModel> extListBo= new ExtListDataBo<Pntset_GrpgatherPrinterGroupModel>(list, count);
        return extListBo;
	}
	//保存或修改打印机群组信息
	@Override
	public boolean saveOrUpdateGroup(String str) throws Exception {
		Pntdef_PrinterGrpgather bd=(Pntdef_PrinterGrpgather)JSONObject.toBean(JSONObject.fromObject(str),Pntdef_PrinterGrpgather.class);
		this.genDao.saveOrUpdateObj(bd);
		return true;
	}
	//群组添加打印机组
	@Override
	public boolean savePrinter_Group(String str)throws Exception{
		Collection<Pntset_GrpgatherPrinterGroup> printer=JSONArray.toCollection(JSONArray.fromObject(str),Pntset_GrpgatherPrinterGroup.class);
		List<Pntset_GrpgatherPrinterGroup> printerGroup=(List)printer;
		this.genDao.saveList(printerGroup);
		return true;
	}
	//群组移除打印机组
	@Override
	public boolean deletePrinter_Group(String enterpriseNo, String warehouseNo,
			String grpgatherNo, String printerGroupNo) throws Exception {
		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=grpgatherNo.split(",");
		String wheresql3[]=printerGroupNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete pntset_grpgather_printer_group a " +
			"where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
			"a.grpgather_no='"+wheresql2[i].trim()+"' and " +
			"a.printer_group_no='"+wheresql3[i].trim()+
			"' and a.enterprise_no='"+enterpriseNo+"'";
			genDao.updateBySql(sql);
		}
		return true;
	}
	//校验打印机群组是否已存在
	@Override
	public String checkGrpgatherNo(String enterpriseNo, String warehouseNo,
			String grpgatherNo) throws Exception {
		String sql="select a.grpgather_no from pntdef_printer_grpgather a where a.warehouse_no='"+warehouseNo+
				"' and a.grpgather_no='"+grpgatherNo+
			    "' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	@Override
	public List<ComboxBo> getPrinterGroupInfo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strQuery, String wheresql)
			throws Exception {
		String sql="select a.printer_group_no value," +
				"'[' || ltrim(a.printer_group_no)||']'||a.printer_group_name dropValue, "+
				"a.printer_group_no text " +
				"from BSET_GROUP a " +
		"where a.warehouse_no='"+strWarehouseNo+
		"' and a.enterprise_no='"+strEnterpriseNo+"'" ;

		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + 
					" and (a.printer_group_no like '%"+str+"%' " +
					"or a.printer_group_name like '%"+str+"%' )";
		}
				
		sql=sql+"  and a.printer_group_no not in(select b.printer_group_no " +
				"from pntset_grpgather_printer_group b where b.enterprise_no='"+strEnterpriseNo+"' " +
			    "and b.grpgather_no='"+wheresql+"' "+
				"and b.warehouse_no='"+strWarehouseNo+"') union all select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from dual";
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
}	


