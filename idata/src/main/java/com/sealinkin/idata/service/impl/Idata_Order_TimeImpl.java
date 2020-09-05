package com.sealinkin.idata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.DockSheetModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.model.Idata_OrderSheetModel;
import com.sealinkin.idata.model.Idata_OrderStatusModel;
import com.sealinkin.idata.service.Iidata_Order_Time;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Idata_Order_TimeImpl implements Iidata_Order_Time{
	
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public List<DockSheetModel> getDock_Sheet(String enterpriseNo,
			String warehouseNo) throws Exception {
		String sql="";
		sql="select a.* " +
			"from " +
				"DOCK_SHEET a " +
			"where " +
				"a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"'" ;
		List<DockSheetModel> list=genDao.getListByNativeSql(sql, DockSheetModel.class);
		return list;
	}
	@Override
	public List<ComboxBo> getIdata_Order_Time_GetCombo(String enterpriseNo,String warehouseNo,
			String owner, String flag, String str, int start, int pagesize) {
		String sql="";
		/**
		 * flag=1为委托业主
		 * flag=2为供应商
		 */
		if(flag!=null && flag.equals("1")){
			sql="select distinct  a.owner_no value," +
					"b.owner_name," +
					"'['||a.owner_no||']'||b.owner_name dropValue " +
					"from idata_import_m a," +
					"bdef_defowner b " +
					"where a.enterprise_no=b.enterprise_no " +
					"and a.owner_no=b.owner_no " +
					"and a.status=10  %s1 %s2 %s3 %s4 " ;			
		}else if(flag!=null && flag.equals("2")){
			sql="select distinct  a.supplier_no value," +
					"b.supplier_name," +
					"'['||a.supplier_no||']'||b.supplier_name dropValue " +
					"from idata_import_m a," +
					"bdef_defsupplier b " +
					"where a.enterprise_no=b.enterprise_no " +
					"and a.supplier_no=b.supplier_no " +
					"and a.owner_no=b.owner_no " +
					"and a.status=10  %s1 %s2 %s3 %s4 " +
					"order by a.supplier_no" ;
		}else if(flag!=null && flag.equals("3")){
			sql="select distinct  a.import_type value," +
					"b.text," +
					"'['||a.import_type||']'||b.text dropValue " +
					"from idata_import_m a,wms_deffieldval b " +
					"where a.status=10 " +
					"and a.import_type=b.value " +
					"and b.table_name='N' " +
					"and b.colname='IMPORT_TYPE'  %s1  %s2 %s3 %s4 " ;
		}else if(flag!=null && flag.equals("4")){
			sql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select distinct  a.owner_no value," +
					"b.owner_name," +
					"'['||a.owner_no||']'||b.owner_name dropValue " +
					"from idata_order_status a," +
					"bdef_defowner b " +
					"where a.enterprise_no=b.enterprise_no " +
					"and a.owner_no=b.owner_no " +
					" %s1 %s2 %s3 %s4 " ;
		}else if(flag!=null && flag.equals("5")){
			sql="select distinct  a.supplier_no value," +
					"b.supplier_name," +
					"'['||a.supplier_no||']'||b.supplier_name dropValue " +
					"from idata_order_status a," +
					"bdef_defsupplier b " +
					"where a.enterprise_no=b.enterprise_no " +
					"and a.supplier_no=b.supplier_no " +
					"and a.status=10  %s1  %s2 %s3 %s4 " +
					"order by a.supplier_no" ;
		}
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql.replace("%s1", ws);
		}else{
			sql=sql.replace("%s1", "");
		}
		
		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			sql=sql.replace("%s2", " and a.warehouse_no='"+warehouseNo+"' ");
		}else
		{
			sql=sql.replace("%s2", " and 1=2 ");
		}
		
		if(owner!=null && !owner.equals(""))
		{
			sql=sql.replace("%s3", " and a.owner_no in("+owner+") ");
		}else{
			sql=sql.replace("%s3", " and 1=2 ");
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals("")){
			sql=sql.replace("%s4", " and a.enterprise_no='"+enterpriseNo+"' ");
		}else
		{
			sql=sql.replace("%s4", " and 1=2 ");
		}
		if(flag!=null && flag.equals("4")){
			sql += "order by value desc ";
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	//获取预约状况码头下拉列表
	@Override
	public List<ComboxBo> getDockNoCombo(String strEnterpriseNo,
			String strWarehouseNo, String strOwnerNo, String strWheresql,
			String str) {
		String strSql="select distinct a.dock_no value,c.dock_name text," +
				"'['|| ltrim(a.dock_no)||']'||nvl(c.dock_name,'N') dropValue " +
				"from idata_order_status a ,bdef_defDock c " +
				"where  a.enterprise_no=c.enterprise_no(+) " +
				"and a.warehouse_no=c.warehouse_no(+) " +
				"and a.dock_no=c.dock_no(+) " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and a.dock_no like '%"+strWheresql+"%' ";
		}
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>)list;
	}

	@Override
	public ExtListDataBo<Idata_ImportMModel> getIdata_Import_MList(
			String enterpriseNo,String warehouseNo, String owner, String str, PageBo pageBo)
			throws Exception {
		String sql="select distinct a.import_no,a.owner_no,b.owner_name,a.supplier_no,c.supplier_name," +
				"a.po_no,a.order_date,a.request_date,a.status,a.order_end_date," +
				"a.po_type,a.create_flag,a.end_date ,a.import_type," +
				"f_get_fieldtext('N','STATUS',a.status)statusText," +
				"f_get_fieldtext('IDATA_IMPORT_M','PO_TYPE',a.po_type)potypeText,a.rgst_name," +
				"a.rgst_date,a.updt_name,a.updt_date," +
				"count(distinct d.article_no) articleItems," +
				"nvl((sum(d.po_qty-d.import_qty)),0) pkQty " +
				"from idata_import_m a,bdef_defowner b,bdef_defsupplier c, idata_import_d d " +
				"where a.enterprise_no=b.enterprise_no " +
				"and a.owner_no=b.owner_no " +
				"and a.enterprise_no = d.enterprise_no " +
				"and a.warehouse_no=d.warehouse_no " +
				"and a.import_no=d.import_no  " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.supplier_no=c.supplier_no " +
				"and a.owner_no=c.owner_no  " +
				"and a.status='10' " +
				" and a.import_no not in(" +
				"   select d.import_no " +
				"     from idata_import_sm d " +
				"    where d.enterprise_no=a.enterprise_no " +
				"    and d.warehouse_no=a.warehouse_no) "+
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;		
        String totsql = "select count(1) " +
				"from idata_import_m a,bdef_defowner b,bdef_defsupplier c " +
				"where a.enterprise_no=b.enterprise_no " +
				"and a.owner_no=b.owner_no " +
				"and a.supplier_no=c.supplier_no " +
				"and a.status='10' " +
				" and a.import_no not in(" +
				"   select d.import_no " +
				"     from idata_import_sm d " +
				"    where d.enterprise_no=a.enterprise_no " +
				"    and d.warehouse_no=a.warehouse_no) "+
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;	
        if(owner!=null && !owner.equals(""))
		{
			sql=sql+" and a.owner_no in("+owner+") ";
			totsql=totsql+" and a.owner_no in("+owner+")";
		}else{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
        
        if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			totsql=totsql+ws;
		}
        sql=sql+" group by a.import_no,a.owner_no,b.owner_name,a.supplier_no," +
        		" c.supplier_name, a.po_no,a.order_date,a.request_date,a.status," +
        		" a.order_end_date,a.po_type,a.create_flag,a.end_date,a.import_type," +
        		" a.status,a.po_type, a.rgst_name, a.rgst_date, a.updt_name,a.updt_date" +
        		" order by a.import_type,a.import_no desc ";
		List<Idata_ImportMModel> list = genDao.getListByNativeSql(sql,Idata_ImportMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_ImportMModel> extListBo= new ExtListDataBo<Idata_ImportMModel>(list, count);
        return extListBo;
	}
	
	//保存
	@SuppressWarnings("deprecation")
	@Override
	public MsgRes save(String enterpriseNo,String warehouseNo,String importNo, String time, String workerNo,String strWorkSpaceNo,String strDockNo,String flag,String printFlag)
			throws Exception {
		
		int order=0;
		String tmp_importNo="";
		
		String[] importNos=importNo.split(",");
		String[] times=time.split(",");
		String SImportNo="N";
		List<String>  outList=new ArrayList<String>();
		List  resultList=new ArrayList();
		outList.add("S");
		outList.add("S");
		for (int i=0;i<importNos.length;i++) {
			String sql="select a.article_no from idata_import_d a where  a.import_no='"+importNos[i]+"'";
			List list = genDao.getListByNativeSql(sql);
			
			if(list.size()!=0){
				List<Comparable>  inList=new ArrayList<Comparable>();
				inList.add(enterpriseNo);
				inList.add(warehouseNo);
				inList.add(importNos[i]);
				inList.add(new Date(times[0]));
				inList.add(times[1]);
				inList.add(times[2]);
				
				//flag为判断是否集单，0为不集单，1为集单。
				if(flag.equals("0")){
					inList.add(2);
				}else{
					inList.add(i==0?1:0);//是否第一张单	
				}
				/*if(flag.equals("0")){ 
					inList.add(1);
				}else{
					inList.add(i==importNos.length-1?1:0);//是否最后一张单
				}*/
				inList.add(SImportNo);//进货汇总单号 第一次默认为N 
				inList.add(workerNo);
				inList.add(strWorkSpaceNo);
				inList.add(strDockNo);
				inList.add(printFlag);
				System.out.println(inList);
				resultList=genDao.execProc(inList, outList, "PKLG_IDATA.p_Idata_Order_Time");				
				if(resultList.get(1).toString().indexOf("Y")==-1){
					throw new Exception(resultList.get(1).toString());
				}else{
					if(i==0){
						SImportNo=resultList.get(0).toString();
					}
				}
			}else{
				order=1;
			}
			
			if(i==0){
				tmp_importNo+="'"+importNos[i]+"'";
			}else{
				tmp_importNo+=",'"+importNos[i]+"'";
			}
		}
				
		if(order==0){
			String sql=" select distinct iim.serial_no " +
					   "   from idata_import_mm iim, " +
					   "        idata_import_sm iis " +
					   "  where iim.enterprise_no = iis.enterprise_no " +
					   "    and iim.warehouse_no=iis.warehouse_no " +
					   "    and iim.s_import_no=iis.s_import_no " +
					   "    and iis.enterprise_no='"+enterpriseNo+"' " +
					   "    and iis.warehouse_no='"+warehouseNo+"' " +
					   "    and iis.import_no in("+tmp_importNo+")" ;
			
			List<String> list = genDao.getListByNativeSql(sql);
			String result="";
			for(int i=0; i<list.size();i++){
				if(i==0){
					result+=list.get(i);
				}else{
					result+=","+list.get(i);
				}
			}
			return new MsgRes(true,"保存成功！",result);
		}else{
			return new MsgRes(true,"有订单的商品为新品，请先添加商品！","");
		}
	}
	
	//修改保存
	@Override
	public MsgRes editSave(String enterpriseNo, String warehouseNo,
			String workerNo, String strWorkSpaceNo, String strDockNo,
			String time,String str) throws Exception {
		String[] times=time.split(",");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date(times[0]));
		String sql = "update idata_order_status t set t.dock_no='"+strDockNo+"'," +
				"t.request_date=to_date('"+date+"','yyyy/mm/dd')," +
				"t.start_time='"+times[1]+"'," +"t.end_time='"+times[2]+"'," +
				"t.updt_name='"+workerNo+"',t.updt_date=sysdate " +
				"where t.enterprise_no='"+enterpriseNo+"' and t.warehouse_no='"+warehouseNo+"' " +
				"and t.order_serial='"+str+"' ";
		genDao.updateBySql(sql);
				
		return new MsgRes(true,"保存成功！","");
	}
	
	@Override
	public ExtListDataBo<Idata_OrderStatusModel> getIdata_Order_StatusList(
			String enterpriseNo,
			String warehouseNo, 
			String owner,
			String flag, 
			String str, 
			PageBo pageBo)
			throws Exception {
		String sql="";
		String totsql="";
		System.out.println(flag);
		if(flag!=null && flag.equals("2")){
			sql="select a.order_serial, a.s_import_no,b.supplier_no,b.supplier_name, " +
					" count(distinct d.article_no) articleItems," +
					" nvl(sum(f.unit_volumn + (d.po_qty - 1) * f.cumulative_volumn), 0) as volumn," +
					" nvl(sum(f.unit_weight * d.po_qty), 0) as weight," +
					" nvl(sum(d.po_qty), 0) pkQty, " +
					"'['|| ltrim(a.dock_no)||']'||c.dock_name dockText "+
					"from idata_order_status a,bdef_defsupplier b ,bdef_defDock c, " +
					"     (select * from idata_import_sd  " +
					"       union " +
					"      select * from idata_import_sdhty)d," +
					"     bdef_article_packing e, bdef_defarticle f " +
					"where a.supplier_no=b.supplier_no " +
					"and a.owner_no=b.owner_no " +
					"and a.enterprise_no=b.enterprise_no " +
					"and a.enterprise_no=c.enterprise_no(+) " +
					"and a.warehouse_no=c.warehouse_no(+) " +
					"and a.enterprise_no = d.enterprise_no(+) " +
					"and a.warehouse_no = d.warehouse_no(+) " +
					"and a.s_import_no = d.s_import_no(+) " +
					"and d.enterprise_no=f.enterprise_no(+) " +
					"and d.article_no=f.article_no(+) " +
					"and d.enterprise_no = e.enterprise_no(+) " +
					"and d.article_no = e.article_no(+) " +
					"and d.packing_qty= e.packing_qty(+) " +
					"and a.dock_no=c.dock_no(+) " +
					"and a.warehouse_no='"+warehouseNo+"' " +
					"and a.enterprise_no='"+enterpriseNo+"' " ;		
			
			if(owner!=null && !owner.equals(""))
			{
				sql=sql+" and a.owner_no in("+owner+") ";
			}else{
				sql=sql+" and 1=2";
			}
			if(str!=null && !str.equals("")){
					String ws=CommUtil.covtCollectionToWhereSql(str);
					sql=sql+ws;
			}
		    sql=sql+" group by a.order_serial,a.s_import_no,b.supplier_no, b.supplier_name,a.dock_no,c.dock_name " +
		            " order by a.dock_no,a.start_time ";
		}else{
			sql="select distinct a.dock_no, a.start_time,a.end_time,a.owner_no,  " +
					"'['|| ltrim(a.dock_no)||']'||nvl(c.dock_name,'N') dockText, "+
					"'['|| ltrim(a.owner_no)||']'||d.owner_name ownerNoText "+
					"from idata_order_status a ,bdef_defDock c,bdef_defowner d " +
					"where 1=1 " +
					"and a.enterprise_no=c.enterprise_no(+) " +
					"and a.warehouse_no=c.warehouse_no(+) " +
					"and a.dock_no=c.dock_no(+) " +
					"and a.enterprise_no=d.enterprise_no " +
					"and a.owner_no=d.owner_no " +
					"and a.warehouse_no='"+warehouseNo+"' "+
					"and a.enterprise_no='"+enterpriseNo+"' " ;
			
			 if(owner!=null && !owner.equals(""))
			 {
			 	sql=sql+" and a.owner_no in("+owner+") ";
			 }else{
			 	sql=sql+" and 1=2";
		  	 }
			 if(str!=null && !str.equals("")){
					String ws=CommUtil.covtCollectionToWhereSql(str);
					sql=sql+ws;
			 }
			 sql=sql+" order by a.dock_no,a.start_time ";
		}
			
        totsql = "select count(*) from (" + sql + ") " ;	
		List<Idata_OrderStatusModel> list = genDao.getListByNativeSql(sql,Idata_OrderStatusModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_OrderStatusModel> extListBo= new ExtListDataBo<Idata_OrderStatusModel>(list,count);
        return extListBo;
	}
	@Override
	public ExtListDataBo<Idata_OrderSheetModel> getIdata_Order_SheetList(
			String enterpriseNo,String warehouseNo, String owner, String str, PageBo pageBo)
			throws Exception {
		String sql="select a.*,b.po_no " +
				"from idata_order_sheet a,idata_import_m b " +
				"where a.import_no=b.import_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;			
        String totsql = "select count(1) " +
        		"from idata_order_sheet a,idata_import_m b " +
				"where a.import_no=b.import_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.warehouse_no='"+warehouseNo+"' "+
				"and a.enterprise_no='"+enterpriseNo+"' " ;			
        
        if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			totsql=totsql+ws;
		}
        sql=sql+" order by a.import_no ";
		List<Idata_OrderSheetModel> list = genDao.getListByNativeSql(sql,Idata_OrderSheetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_OrderSheetModel> extListBo= new ExtListDataBo<Idata_OrderSheetModel>(list,count);
        return extListBo;
	}

}
