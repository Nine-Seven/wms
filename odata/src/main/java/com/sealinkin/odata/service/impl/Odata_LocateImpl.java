package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.model.Odata_LocateMModel;
import com.sealinkin.odata.model.Odata_LocateShortLogModel;
import com.sealinkin.odata.service.IOdata_LocataService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

/**
 * 出货调度
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_LocateImpl implements IOdata_LocataService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 获取货主下拉
	 */
	@Override
	public List<ComboxBo> queryOwnerCombo(String strWarehouseNo,String enterpriseNo) throws Exception {

		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 " +
				"where t1.owner_no in(select owner_no from odata_exp_m where warehouse_no='"+strWarehouseNo+
				"' and enterprise_no='"+enterpriseNo+
				"' and status=10)" +
				"  and t1.enterprise_no='"+enterpriseNo+"' ";
		strSql += "order by value desc ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 获取出货单别下拉(公用)
	 */
	@Override
	public List<ComboxBo> queryExpTypeCombo(String strWarehouseNo,
			String enterpriseNo,String strFlag) throws Exception {
		String strSql="select t1.value value,t1.text text," +
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 " +
				"where t1.table_name='N' and t1.colName='EXP_TYPE' " +
				"and t1.value in(select m.exp_type from odata_exp_m m,wms_outorder t where m.warehouse_no='"+strWarehouseNo+
				"' and m.enterprise_no='"+enterpriseNo+
				"' and m.status='10' %s1 ) ";
		if(strFlag.equals("1")){//传统
			strSql = strSql.replace("%s1", " and t.industry_flag='1' and m.exp_type=t.exp_type and m.enterprise_no=t.enterprise_no ");
		}else if(strFlag.equals("2")){//电商
			strSql = strSql.replace("%s1", " and t.industry_flag='2' and m.exp_type=t.exp_type and m.enterprise_no=t.enterprise_no ");
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//获取配送方式
	@Override
	public List<ComboxBo> queryDeliverTypeCombo(String warehouseNo,
			String enterpriseNo) throws Exception {
		String strSql="select t1.value value,t1.text text," +
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 " +
				"where t1.table_name='N' and t1.colName='DELIVER_TYPE' " +
				"and t1.value in(select deliver_type from odata_exp_m where warehouse_no='"+warehouseNo+
				"' and enterprise_no='"+enterpriseNo+
				"' and status=10) order by t1.value";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 获取集单头档（无用）
	 */
	@Override
	public ExtListDataBo<Odata_ExpMModel> queryLocateM(String enterpriseNo,String strWarehouseNo,
			String strWheresql,String strFlag,
			PageBo poPageBo, String linkValue,String str) throws Exception {
		String strSql="";
		String ip = HttpService.getIpAddr();
		if(strFlag.equals("1"))//按单号调度
		{
			strSql="select a.enterprise_no,a.warehouse_no,a.owner_no,a.CUSTSEND_DATE," +
					"a.cust_no,c.cust_name,c.cust_alias,c.cust_phone1,c.cust_address,c.contactor_name1," +
					"a.sourceexp_no,a.exp_no," +
					"count(distinct b.article_no) articleItems,sum(b.ARTICLE_QTY) totalQty," +
					"sum(b.article_qty * d.unit_volumn) sumVolumn," +
					"sum(b.article_qty * d.unit_weight) sumWeight," +
					"sum(b.article_qty/b.packing_qty) sumBoxQty," +
					"count(distinct a.exp_no) expNoCount," +
					"(select count(*) from oset_buffer t where t.enterprise_no=a.enterprise_no and t.warehouse_no=a.warehouse_no  " +
			        "  and t.cell_no not in (select distinct tmp.cell_no from odata_temp_send_area_calculate tmp where tmp.tmp_id='"+ip+"' " +
			        "  and tmp.enterprise_no=t.enterprise_no and tmp.warehouse_no=t.warehouse_no " +
			        "  union select send.cell_no from odata_send_area_calculate send)) calculateCount," +
					"case  when t.line_name is null then 'N'  else t.line_name end lineName,"+
					"case when vcl.LINE_NO is null then 'N' else vcl.LINE_NO end line_no," +
					"case when vcl.BATCH_NO is null then 'N' else vcl.BATCH_NO end batch_no " +
					"from odata_exp_m a,odata_exp_d b,bdef_defcust c," +
					"bdef_defarticle d,v_cust_line_batch_cycle vcl,oset_defline t,TMP_ODATA_EXP_M os " +
					"where a.exp_no=b.exp_no " +
					"and a.warehouse_no=b.warehouse_no " +
					"and a.enterprise_no=b.enterprise_no "+
					"and a.enterprise_no=c.enterprise_no "+
					"and a.enterprise_no=d.enterprise_no "+
					"and a.status='10' " +
					"and a.cust_no=c.cust_no " +
					"and a.owner_no=c.owner_no " +
					"and b.article_no=d.article_no " +
					"and a.cust_no=vcl.CUST_NO(+) " +
					"and a.warehouse_no=vcl.WAREHOUSE_NO(+) " +
					"and a.enterprise_no=vcl.enterprise_no(+) "+
					"and vcl.LINE_NO=t.line_no(+) "+
					"and vcl.WAREHOUSE_NO = t.warehouse_no(+) "+
					"and vcl.ENTERPRISE_NO=t.enterprise_no(+) " +
					"and a.exp_no=os.exp_no and a.org_no=os.org_no " +
					"and a.enterprise_no=os.enterprise_no " +
					"and a.warehouse_no=os.warehouse_no "+
					"and a.warehouse_no='"+strWarehouseNo+"' "+
					"and a.enterprise_no='"+enterpriseNo+"' " ;
					
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
				strSql=strSql+ft;
			}
			if(linkValue!=null && !linkValue.equals(""))
			{
				strSql=strSql+" and vcl.line_no='"+linkValue+"' ";
			}
			
			if(str!=null && !str.equals("")){
				strSql=strSql+" and a.CUSTSEND_DATE=to_date('"+str+"','yyyy/mm/dd') ";
			}
		/*	if(strOrdertype!=null && !strOrdertype.equals("")){
				if(strOrdertype.equals("1")){
						strSql +=" and a.skucount=1 ";
				}else if(strOrdertype.equals("2")){
						strSql +=" and a.skucount>1 ";
				}
			}*/
			strSql+="group by a.enterprise_no,a.warehouse_no,a.owner_no,a.CUSTSEND_DATE,a.cust_no,c.cust_name,c.cust_alias," +
					"c.cust_phone1,c.cust_address,c.contactor_name1,a.sourceexp_no,a.exp_no,vcl.line_no,vcl.batch_no,t.line_name  " +
			"order by a.warehouse_no,a.cust_no";
		}
		else
		{
			strSql="select a.enterprise_no, a.warehouse_no,a.owner_no," +
					"a.cust_no,c.cust_name,c.cust_alias,c.cust_phone1,c.cust_address,c.contactor_name1," +
					"count(distinct b.article_no) articleItems," +
					"sum(b.ARTICLE_QTY) totalQty," +
					"sum(b.article_qty * d.unit_volumn) sumVolumn," +
					"sum(b.article_qty * d.unit_weight) sumWeight," +
					"sum(b.article_qty/b.packing_qty) sumBoxQty," +
					"count(distinct a.exp_no) expNoCount, " +
					"(select count(*) from oset_buffer t where t.enterprise_no=a.enterprise_no and t.warehouse_no=a.warehouse_no  " +
			        "  and t.cell_no not in (select distinct tmp.cell_no from odata_temp_send_area_calculate tmp where tmp.tmp_id='"+ip+"' " +
			        "  and tmp.enterprise_no=t.enterprise_no and tmp.warehouse_no=t.warehouse_no " +
			        "  union select send.cell_no from odata_send_area_calculate send)) calculateCount," +
					"case  when t.line_name is null then 'N'  else t.line_name end lineName,"+
					"case when vcl.LINE_NO is null then 'N' else vcl.LINE_NO end lineNo," +
					"case when vcl.BATCH_NO is null then 'N' else vcl.BATCH_NO end batch_no " +
					"from odata_exp_m a,odata_exp_d b,bdef_defcust c," +
					"bdef_defarticle d,v_cust_line_batch_cycle vcl,oset_defline t,TMP_ODATA_EXP_M os " +
					"where a.exp_no=b.exp_no " +
					"and a.warehouse_no=b.warehouse_no " +
					"and a.enterprise_no=b.enterprise_no " +
					"and a.enterprise_no=c.enterprise_no "+
					"and a.enterprise_no=d.enterprise_no "+
					"and a.status=10 " +
					"and a.cust_no=c.cust_no " +
					"and a.owner_no=c.owner_no " +
					"and b.article_no=d.article_no " +
					"and a.cust_no=vcl.CUST_NO(+)" +
					"and a.warehouse_no=vcl.WAREHOUSE_NO(+) " +
					"and a.enterprise_no=vcl.ENTERPRISE_NO(+) "+
					"and vcl.LINE_NO=t.line_no(+) "+
					"and vcl.WAREHOUSE_NO = t.warehouse_no(+) "+
					"and vcl.ENTERPRISE_NO=t.enterprise_no(+) " +
					"and a.exp_no=os.exp_no  and a.org_no=os.org_no " +
					"and a.enterprise_no=os.enterprise_no " +
					"and a.warehouse_no=os.warehouse_no  "+
					"and a.warehouse_no='"+strWarehouseNo+"' "+
					"and a.enterprise_no='"+enterpriseNo+"' ";
					
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
				strSql=strSql+ft;
			}
			
			if(linkValue!=null && !linkValue.equals(""))
			{
				strSql=strSql+" and vcl.line_no='"+linkValue+"' ";
			}
			if(str!=null && !str.equals("")){
				strSql=strSql+" and a.CUSTSEND_DATE=to_date('"+str+"','yyyy/mm/dd') ";
			}
			/*if(strOrdertype!=null && !strOrdertype.equals("")){
				if(strOrdertype.equals("1")){
						strSql +=" and a.skucount=1 ";
				}else if(strOrdertype.equals("2")){
						strSql +=" and a.skucount>1 ";
				}
			}*/
			strSql+="group by a.enterprise_no, a.warehouse_no,a.owner_no,a.cust_no,c.cust_name,c.cust_alias," +
					"c.cust_phone1,c.cust_address,c.contactor_name1,vcl.line_no,vcl.batch_no, t.line_name  " +
			"order by a.warehouse_no,a.cust_no";
		}
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(
				strSql,Odata_ExpMModel.class);
		ExtListDataBo<Odata_ExpMModel> extListBo=new ExtListDataBo<Odata_ExpMModel>(list,0);
		return extListBo;
	}
	
	/**
	 * 获取集单明细（公用方法）
	 */
	@Override
	public ExtListDataBo<Odata_ExpDModel> queryLocateD(String enterpriseNo,String strWarehouseNo,
			String strFlag,String strExpNo,String strCustNo,String strIsNotEnought) throws Exception 
	{
		String ip = HttpService.getIpAddr();
		String strSql="SELECT a.enterprise_no,a.warehouse_no,a.owner_no,a.article_no,b.packing_qty,a.exp_qty,a.pack_qty,a.volumn,a.weight,c.article_name,a.owner_article_no," +
				"c.barcode," +
				//"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"+
                //"nvl(e.spec, '1*' || b.packing_qty || c.unit) spec," +
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec,"+
				"a.exp_qty as totalQty,NVL(b.available_qty, 0) AS available_qty," +
				"DECODE(SIGN(NVL(b.available_qty, 0) - a.exp_qty),-1,a.exp_qty - NVL(b.available_qty, 0),0) AS no_enough_qty " +
				"from " +
				"(SELECT b.enterprise_no,da.OWNER_ARTICLE_NO ,b.warehouse_no," +
				"b.owner_no,a.stock_type,da.article_no," +
				"SUM(b.article_qty - NVL(b.locate_qty, 0)) AS exp_qty," +
				"ROUND(SUM((b.article_qty - NVL(b.locate_qty, 0))),3)  AS pack_qty," +
				"SUM((b.article_qty - NVL(b.locate_qty, 0)) * da.unit_volumn) AS volumn," +
				"SUM((b.article_qty - NVL(b.locate_qty, 0)) * da.unit_weight) AS weight " +
				"FROM odata_exp_m a,odata_exp_d b,bdef_defarticle da,odata_tmp_locate_select tmp " +
				"WHERE b.article_no = da.article_no and b.enterprise_no=da.enterprise_no and a.warehouse_no = b.warehouse_no " +
				"and a.warehouse_no = tmp.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no "+
				"and a.enterprise_no=tmp.enterprise_no "+
				"and tmp.tmp_id='"+ip+"'  and tmp.status='1' "+
				"and a.exp_no = b.exp_no and a.status = '10' " +
				"and a.exp_no=tmp.exp_no " +
				"GROUP BY b.enterprise_no,da.OWNER_ARTICLE_NO,b.warehouse_no, b.owner_no, a.stock_type, da.ARTICLE_NO) a," +
				"(SELECT a.enterprise_no,a.warehouse_no,a.owner_no,a.article_no,max(a.packing_qty) as packing_qty,a.stock_type,a.stock_value," +
				"SUM(a.qty - NVL(a.outstock_qty, 0) + DECODE(a.instock_type, '1', NVL(a.instock_qty, 0), 0)) AS available_qty " +
				"FROM stock_content a,stock_article_info b,cdef_defcell c,cdef_defarea d " +
				"WHERE a.article_no = b.article_no AND a.article_id = b.article_id " +
				"AND a.warehouse_no = c.warehouse_no AND a.cell_no = c.cell_no " +
				"and c.warehouse_no = d.warehouse_no AND c.ware_no = d.ware_no " +
				"AND a.enterprise_no = c.enterprise_no "+
				"AND a.enterprise_no = b.enterprise_no "+
				"AND a.enterprise_no = d.enterprise_no "+
				"AND c.area_no = d.area_no AND a.flag <> '2' AND a.status = '0' " +
				"AND a.warehouse_no = '"+strWarehouseNo+"' " +
				"AND a.enterprise_no='"+enterpriseNo+"' " +
				"AND (a.instock_qty + a.qty - a.outstock_qty + a.unusual_qty) > 0 " +
				"and (d.area_usetype = '1' or d.area_usetype = '5' or d.area_usetype = '6') " +
				"AND c.cell_status = '0' AND c.check_status = '0' AND d.Area_Attribute in ('0') " +
				"GROUP BY a.enterprise_no,a.warehouse_no,a.owner_no,a.stock_type,a.stock_value,a.article_no) b," +
				"bdef_defarticle c " +
				//",bdef_article_packing e " +hkl
				"WHERE a.warehouse_no = b.warehouse_no(+) AND a.owner_no = b.owner_no(+) " +
				"AND a.enterprise_no=b.enterprise_no(+) "+
				"AND a.article_no = b.article_no(+)  " +
				//"and a.packing_qty=b.packing_qty(+) " +
				"and a.stock_type = b.stock_type(+)  " +
				//"and a.packing_qty= b.packing_qty(+) " +
				"and a.article_no=c.article_no and a.enterprise_no=c.enterprise_no " +
			/*	"and a.article_no=e.article_no(+) and a.enterprise_no=e.enterprise_no(+) " +
				"and a.packing_qty=e.packing_qty(+) " +*/
				"and a.warehouse_no = '"+strWarehouseNo+"' "+
				"and a.enterprise_no='"+enterpriseNo+"' "  +
				"%1 " +
				"ORDER BY a.article_no";			
		if(strIsNotEnought.equals("1"))
		{
			strSql=strSql.replace("%1", " and DECODE(SIGN(NVL(b.available_qty/a.packing_qty,0) - a.exp_qty),-1,a.exp_qty - NVL(b.available_qty/a.packing_qty,0),0)>0 ");
		}else if(strIsNotEnought.equals("0"))
		{
			strSql=strSql.replace("%1", "");
		}
		//自动调度和手动调度时，该参数为是否为自动调度和手动调度strFlag
		/*if(strFlag.equals("3202"))
		{
			strSql=strSql.replace("%2", " and tmp.status='1'  ");
		}else if(!strFlag.equals("3202"))
		{
			strSql=strSql.replace("%2", "");
		}*/
		
		List<Odata_ExpDModel> list = genDao.getListByNativeSql(
				strSql,Odata_ExpDModel.class);
		ExtListDataBo<Odata_ExpDModel> extListBo=new ExtListDataBo<Odata_ExpDModel>(list,0);
		return extListBo;
	}
	
	/**
	 * 获取客户线路策略
	 */
	@Override
	public List<String> queryTactics(String enterpriseNo) throws Exception {
		                                     
		String strSql="select line_flag from wms_warehouse_outorder where enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()==0){	
			strSql="select line_flag from wms_warehouse_outorder where enterprise_no='"+enterpriseNo+"'";
			list=genDao.getListByNativeSql(strSql);
			
			if(list.size()==0){
				strSql="select line_flag from wms_outorder where enterprise_no='"+enterpriseNo+"'";
				list=genDao.getListByNativeSql(strSql);
			}		
		}	
		return list;
	}
	
	/**
	 * 保存集单临时表
	 */
	@Override
	public MsgRes saveOdataTmpLocateSelect(String enterpriseNo,String strDetail, String strFlag, String expDate, String linkValue)
			throws Exception {
		Odata_ExpMModel expM = (Odata_ExpMModel)JSONObject.toBean(
				JSONObject.fromObject(strDetail),Odata_ExpMModel.class);
		String ip = HttpService.getIpAddr();
		
	  if(strFlag.equals("1")){
		    List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			inList.add(ip);
			inList.add(enterpriseNo);
			inList.add(expM.getWarehouseNo());
			inList.add(expM.getOwnerNo());
			inList.add(expM.getCustNo());
			inList.add(expM.getLineNo());
			inList.add(expM.getBatchNo());
			inList.add(expM.getExpNo().equals("")?"N":expM.getExpNo());
			inList.add(expM.getSumVolumn());
			inList.add(expM.getSumWeight());
			inList.add(expM.getSumBoxQty());
			inList.add(strFlag);
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "pklg_odata.insert_tmp_locate_select");
			System.out.println(resultList);
			if(resultList.get(0).toString().indexOf("Y")==-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"","");
	  }else{
		  String strSql="select a.CUSTSEND_DATE," +
					"a.cust_no,a.exp_no," +
					"count(distinct b.article_no) articleItems,sum(b.ARTICLE_QTY) totalQty," +
					"sum(b.article_qty * d.unit_volumn) sumVolumn," +
					"sum(b.article_qty * d.unit_weight) sumWeight," +
					"sum(b.article_qty/b.packing_qty) sumBoxQty," +
					"count(distinct a.exp_no) expNoCount," +
					"case when vcl.LINE_NO is null then 'N' else vcl.LINE_NO end line_no," +
					"case when vcl.BATCH_NO is null then 'N' else vcl.BATCH_NO end batch_no " +
					"from odata_exp_m a,odata_exp_d b,bdef_defarticle d,v_cust_line_batch_cycle vcl " +
					"where a.exp_no=b.exp_no " +
					"and a.warehouse_no=b.warehouse_no " +
					"and a.enterprise_no=b.enterprise_no "+
					"and a.enterprise_no=d.enterprise_no "+
					"and a.status='10' " +
					"and b.article_no=d.article_no " +
					"and a.cust_no=vcl.CUST_NO(+) " +
					"and a.warehouse_no=vcl.WAREHOUSE_NO(+) " +
					"and a.enterprise_no=vcl.enterprise_no(+) "+
					"and a.warehouse_no='"+expM.getWarehouseNo()+"' "+
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.owner_no='" +expM.getOwnerNo()+"' "+
					"and a.cust_no='"+expM.getCustNo()+"' " +
					"and a.exp_type='"+expM.getExpType()+"' " +
					"and a.org_no='"+expM.getOrgNo()+"' " ;
		  
		  if(expDate!=null && !expDate.equals("")){
			  strSql=strSql+" and a.CUSTSEND_DATE=to_date('"+expDate+"','yyyy/mm/dd') ";
		  }
		  
		  if(linkValue!=null && !linkValue.equals("")){
			  strSql=strSql+" and vcl.line_no='"+linkValue+"' ";
		  }
		  
		  strSql+="group by a.CUSTSEND_DATE,a.cust_no,a.exp_no,vcl.line_no,vcl.batch_no  ";
		  List<Odata_ExpMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpMModel.class);
		  
		  for(int i=0;i<list.size();i++){
			  List inList=new ArrayList();
			  List outList=new ArrayList();
			  List resultList=new ArrayList();
				
			  outList.add("S");
			  inList.add(ip);
			  inList.add(enterpriseNo);
			  inList.add(expM.getWarehouseNo());
			  inList.add(expM.getOwnerNo());
			  inList.add(expM.getCustNo());
			  inList.add(expM.getLineNo());
			  inList.add(expM.getBatchNo());
			  inList.add(list.get(i).getExpNo());
			  inList.add(list.get(i).getSumVolumn());		            
			  inList.add(list.get(i).getSumWeight());			  
			  inList.add(list.get(i).getSumBoxQty());
			  inList.add("1");
			  System.out.println(inList);
			  resultList = genDao.execProc(inList, outList, "pklg_odata.insert_tmp_locate_select");
			  System.out.println(resultList);
			  if(resultList.get(0).toString().indexOf("Y")==-1)
			  {
				  throw new Exception(resultList.get(0).toString());
			  }
		  }		  
		  return new MsgRes(true,"","");
	  }
	}
	
	/**
	 * 删除集单明细
	 */
	@Override
	public MsgRes deleteOdataTmpLocateSelect(String enterpriseNo,String strDetail, String strFlag)
			throws Exception {
		Odata_ExpMModel expM = (Odata_ExpMModel)JSONObject.toBean(
				JSONObject.fromObject(strDetail),Odata_ExpMModel.class);
		String ip = HttpService.getIpAddr();
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(ip);
		inList.add(enterpriseNo);
		inList.add(expM.getWarehouseNo());
		inList.add(expM.getOwnerNo());
		inList.add(expM.getCustNo());
		inList.add(expM.getLineNo());
		inList.add(expM.getBatchNo());
		inList.add(expM.getExpNo().equals("")?"N":expM.getExpNo());
		inList.add(strFlag);
			
		resultList = genDao.execProc(inList, outList, "pklg_odata.delete_tmp_locate_select");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E21705"),"");
	}
	
	/**
	 * 定位(手动)
	 */
	@Override
	public MsgRes tscFixed(String strDetail) throws Exception {
		Odata_LocateMModel poOl = (Odata_LocateMModel)JSONObject.toBean(
				JSONObject.fromObject(strDetail),Odata_LocateMModel.class);
		String ip = HttpService.getIpAddr();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();

		outList.add("S");
		outList.add("S");
		inList.add(poOl.getEnterpriseNo());
		inList.add(poOl.getWarehouseNo());
		inList.add(poOl.getOwnerNo());
		inList.add(poOl.getExpType());
		inList.add(poOl.getLocateName());
		inList.add(poOl.getDivideFlag());
		inList.add(poOl.getSendbufCompute());
		inList.add("N");//strSpecifyCell
		inList.add("1");//nTaskBatch
		inList.add(poOl.getSourceType());//strSourceType
		
		inList.add(poOl.getStrdivideComputeFlag());//STRDIVIDE_COMPUTE_FLAG
		inList.add(poOl.getStrcheckComputeFlag());//STRCHECK_COMPUTE_FLAG
		
		inList.add(poOl.getOrgNo());
		inList.add(poOl.getBatchRuleI());//strbatch_rule_id规则号
		inList.add("2");
		inList.add(ip);
	//	inList.add(poOl.getOrderType());//nRule_ID
		inList.add(poOl.getTaskAllotRuleID());//nTask_AllotRuleID 
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODISPATCH.p_Ins_O_LOCATE_Task");
		System.out.println(resultList);
		if(resultList.get(1).toString().contains("N|"))
		{
			throw new Exception(resultList.get(1).toString());
		}
		
		/*List inList2=new ArrayList();
		List outList2=new ArrayList();
		outList2.add("S");
		List resultList2=new ArrayList();
		
		inList2.add(poOl.getEnterpriseNo()); //strEnterpriseNo
		inList2.add(poOl.getWarehouseNo());//strWareHouseNo
		inList2.add(resultList.get(0).toString());//strWaveNo
		inList2.add(poOl.getLocateName());//strWorkNo
		
		resultList2=genDao.execProc(inList2, outList2, "PKLG_OLOCATE.p_set_transgroup");
		if(resultList2.get(0).toString().contains("N|"))
		{
			throw new Exception(resultList2.get(0).toString());
		}*/
		//System.out.println(resultList2);
		return new MsgRes(true, TipUtil.getTipsByKey("E21704"),poOl.getWarehouseNo()+","+resultList.get(0).toString());//定位成功
	}
	
	/**
	 * 定位，过程管理事务（续调公用）
	 */
	@Override
	public MsgRes fixed(String enterpriseNo,String strWorkerNo,String strWheresql) throws Exception {
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		inList.add(enterpriseNo);
		inList.add(strWheresql.split(",")[0]);
		inList.add(strWheresql.split(",")[1]);
		inList.add(strWorkerNo);
		
		outList.add("S");
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_OLOCATE.p_locate_main");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E21704"),null);
	}
	
	/**
	 * 删除临时表
	 */
	@Override
	public MsgRes deleteTmpTable(String enterpriseNo,String warehouseNo) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql="delete odata_tmp_locate_select where warehouse_no='" +warehouseNo+"' "+
				"and enterprise_no='"+enterpriseNo+"' "+
				"and tmp_id='"+ip+"' ";
		genDao.updateBySql(strSql);
		
		
		String delStr="delete odata_temp_send_area_calculate where warehouse_no='" +warehouseNo+"' "+
				"and enterprise_no='"+enterpriseNo+"' "+
				"and tmp_id='"+ip+"'";
		genDao.updateBySql(delStr);
		return new MsgRes(true, TipUtil.getTipsByKey("E21705"),"");//删除成功;
	}
	
	
	@Override
	public MsgRes queryTmpTableLength(String enterpriseNo,String warehouseNo) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql="select tmp_id from odata_tmp_locate_select where warehouse_no='" +warehouseNo+"' "+
				"and enterprise_no='"+enterpriseNo+"' "+
				"and tmp_id='"+ip+"'  and status='1' ";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(true,"","");
		}else{
			return new MsgRes(false,"","");
		}
	}
	
	/**
	 * 计算勾选的单据详情
	 */
	@Override
	public List<Odata_ExpMModel> countDetail(String enterpriseNo,String strWarehouseNo) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql="select count(distinct oed.article_no)as articleItems," +
					"sum(oed.article_qty) totalQty," +
					"sum(oed.article_qty * bda.unit_volumn) sumVolumn," +
					"sum(oed.article_qty * bda.unit_weight) sumWeight," +
					"sum(oed.article_qty/oed.packing_qty) sumBoxQty," +
					"count(distinct oem.exp_no) expNoCount," +
					"count(distinct oem.cust_no) custCount " +
					"from odata_exp_m oem,odata_exp_d oed,bdef_defarticle bda " +
				"where oem.exp_no=oed.exp_no " +
				"and oem.warehouse_no='"+strWarehouseNo+"' " +
				"and oem.enterprise_no='"+enterpriseNo+"' " +
				"and oed.article_no=bda.article_no " +
				"and oem.exp_no in " +
				"(select exp_no from odata_tmp_locate_select where warehouse_no='"+strWarehouseNo+"' " +
				"and enterprise_no='"+enterpriseNo+"' "+
				"and tmp_id='"+ip+"' and status='1' )";
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpMModel.class);
		return list;
	}
	
	/**
	 * 检查是否有商品缺量（公用）
	 */
	@Override
	public MsgRes checkNoEnoght(String enterpriseNo,String strWarehouseNo,String strFlag) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql="SELECT a.owner_no from " +
				"(" +
					"SELECT  b.enterprise_no,b.warehouse_no,b.owner_no,a.stock_type," +
					"da.article_no,b.packing_qty," +
					"SUM(b.article_qty - NVL(b.locate_qty, 0)) AS exp_qty," +
					"ROUND(SUM((b.article_qty - NVL(b.locate_qty, 0)) / b.packing_qty),3) AS pack_qty," +
					"SUM((b.article_qty - NVL(b.locate_qty, 0)) * da.unit_volumn) AS volumn," +
					"SUM((b.article_qty - NVL(b.locate_qty, 0)) * da.unit_weight) AS weight " +
					"FROM odata_exp_m a,odata_exp_d b,bdef_defarticle da " +
					"WHERE b.article_no = da.article_no " +
					"and a.enterprise_no='"+enterpriseNo+"' "+
					"and a.warehouse_no='"+strWarehouseNo+"' "+
					"and a.enterprise_no=b.enterprise_no " +
					"and a.warehouse_no = b.warehouse_no " +
					"and a.exp_no = b.exp_no " +
					"and a.status = 10 " +
					"and a.exp_no in " +
					"(select exp_no from odata_tmp_locate_select where warehouse_no='"+strWarehouseNo+"' " +
					" and enterprise_no='"+enterpriseNo+"'  and status='1' "+
					"and tmp_id='"+ip+"' )"+
					"GROUP BY   b.enterprise_no, b.warehouse_no," +
					"b.owner_no," +
					"a.stock_type," +
					"da.ARTICLE_NO," +
					"b.packing_qty" +
				") a," +
				"(" +
					"SELECT  a.enterprise_no,a.warehouse_no,a.owner_no,a.article_no,a.stock_type,a.stock_value," +
					"SUM(a.qty - NVL(a.outstock_qty, 0) + DECODE(a.instock_type, '1', NVL(a.instock_qty, 0), 0)) AS available_qty " +
					"FROM stock_content a,stock_article_info b,cdef_defcell c," +
					"cdef_defarea d " +
					"WHERE a.article_no = b.article_no " +
					"AND a.article_id = b.article_id " +
					"AND a.enterprise_no=c.enterprise_no "+
					"AND a.warehouse_no = c.warehouse_no " +
					"AND a.cell_no = c.cell_no " +
					"and c.warehouse_no = d.warehouse_no " +
					"and c.enterprise_no=d.enterprise_no  "+
					"AND c.ware_no = d.ware_no " +
					"AND c.area_no = d.area_no " +
					"AND a.flag <> '2' " +
					"AND a.status = '0' " +
					"AND a.warehouse_no = '"+strWarehouseNo+"' " +
					"AND a.enterprise_no = '"+enterpriseNo+"' " +
					"AND (a.instock_qty + a.qty - a.outstock_qty + a.unusual_qty) > 0 " +
					"and (d.area_usetype = '1' or d.area_usetype = '5' or d.area_usetype = '6') " +
					"AND c.cell_status = '0' " +
					"AND c.check_status = '0' AND d.Area_Attribute in ('0') " +
					"GROUP BY  a.enterprise_no,a.warehouse_no," +
					"a.owner_no," +
					"a.stock_type," +
					"a.stock_value," +
					"a.article_no" +
				") b," +
				"bdef_defarticle c " +
				"WHERE a.warehouse_no = b.warehouse_no(+) " +
				"AND a.enterprise_no= b.enterprise_no(+) "+
				"AND a.owner_no = b.owner_no(+) " +
				"AND a.article_no = b.article_no(+) " +
				"and a.stock_type = b.stock_type(+) " +
				"and a.article_no = c.article_no " +
				"and a.warehouse_no = '"+strWarehouseNo+"' " +
				"and a.enterprise_no = '"+enterpriseNo+"' " +
				"and DECODE(SIGN(NVL(b.available_qty, 0) - a.exp_qty),-1,a.exp_qty - NVL(b.available_qty, 0),0) > 0";
		
		//自动调度和手动调度时，该参数为是否为自动调度和手动调度strFlag
	/*	if(strFlag==null)
		{
			strSql=strSql.replace("%2", "");

		}else if(strFlag!=null && strFlag.equals("3202"))
		{
			strSql=strSql.replace("%2", " and tmp.status='1'  ");

		}*/
		
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(false,"","");
		}else{
			return new MsgRes(true,"","");
		}
	}
	@Override
	public List<Odata_LocateMModel> getOdata_Locate_M(String enterpriseNo,String warehouseNo,
			String owner, String str, int start, int pagesize) {
		String sql="select distinct a.* " +
				   "  from odata_locate_m a,odata_locate_d b " +
				   " where a.enterprise_no = b.enterprise_no(+) " +
				   "   and a.warehouse_no=b.warehouse_no(+) " +
				   "   and a.wave_no=b.wave_no(+) " +
				   "   and a.warehouse_no='"+warehouseNo+"' " +
				   "   and a.enterprise_no='"+enterpriseNo+"' " +
				   "   and a.owner_no in("+owner+")";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		List list= genDao.getListByNativeSql(sql,Odata_LocateMModel.class,start, pagesize);
		return (List<Odata_LocateMModel>)list;	
	}
	//获取机构下拉
	@Override
	public List<ComboxBo> queryOrgNoCombo(String warehouseNo,
		String currEnterpriseNo) throws Exception {
		String strSql="select t1.value value,t1.text text," +
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 " +
				"where t1.table_name='N' and t1.colName='ORG_NO' " +
				"and t1.status='1' " +
				"and t1.value in(select a.org_no from odata_exp_m a where a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+currEnterpriseNo+
				"' and a.status=10) ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	//获取路线
	@Override
	public List<ComboxBo> getLink(String enterpriseNo,String warehouseNo, String strQuery) throws Exception {
		String strSql="select a.line_no value, a.line_name text," +
				      "       '['|| ltrim(a.line_no)||']'||a.line_name dropValue " +
				      "  from oset_defline a "+
				      " where a.warehouse_no='"+warehouseNo+
				      "'  and a.enterprise_no='"+enterpriseNo+"' ";
		
		if(strQuery!=null && !strQuery.equals("")){
			strSql=strSql+ "and a.line_no like '%"+strQuery+"%' ";
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 1000);
		return (List<ComboxBo>) list;
	}
	@Override
	public ExtListDataBo<Odata_ExpMModel> checkOrder(String enterpriseNo,String warehouseNo,
			PageBo poPageBo,  String articleNo)
			throws Exception {
	
		String ip = HttpService.getIpAddr();
		
		
		
		String	strSql="select a.sourceexp_no,a.exp_no,(b.article_qty-b.locate_qty ) as totalQty " +
					"from odata_exp_m a,odata_exp_d b " +
					" where a.exp_no=b.exp_no " +
					" and a.warehouse_no=b.warehouse_no " +
					" and a.enterprise_no=b.enterprise_no " +
					" and a.status=10 " +
					" and b.article_no='"+articleNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					" and a.enterprise_no='"+enterpriseNo+"' "+
					" and a.exp_no in(" +
					"                 select exp_no from odata_tmp_locate_select " +
					"                 where warehouse_no='" +warehouseNo+"' "+
				    "                 and enterprise_no='"+enterpriseNo+"' "+
				    "                 and tmp_id='"+ip+"'  and status='1' )" ;	
		
		
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpMModel.class,poPageBo.getStart(),1000);
		ExtListDataBo<Odata_ExpMModel> extListBo=new ExtListDataBo<Odata_ExpMModel>(list,0);
		return extListBo;
	}
	//为要定位的商品添加储位(公用)
	@Override
	public MsgRes changeArticle(String enterpriseNo,String warehouseNo, String ownerNo,
			String strCell) throws Exception {
		String ip = HttpService.getIpAddr();
		String sql="select t.exp_no from odata_tmp_locate_select t " +
				" where t.warehouse_no='"+warehouseNo+"'" +
				" and t.enterprise_no='"+enterpriseNo+"'" +
				" and t.tmp_id='"+ip+"'  and t.status='1' ";
		if(ownerNo!=null && !ownerNo.equals("")){
			sql=sql+ " and t.owner_no='"+ownerNo+"'";
		}
		List<String> list =genDao.getListByNativeSql(sql);
		
		String sql1="";
		for (int i=0; i<list.size(); i++){
			sql1="update odata_exp_d t " +
				 " set t.specify_field='CELL_NO', " +
				 " t.specify_condition='8', " +
				 " t.specify_value1='"+strCell+"'," +
				 " t.specify_value2='"+strCell+"'" +
				 " where t.warehouse_no='"+warehouseNo+"'" +
				 " and t.enterprise_no='"+enterpriseNo+"'" +
//				 " and t.owner_no='"+ownerNo+"'" +
				 " and t.exp_no='"+list.get(i)+"'";
			if(ownerNo!=null && !ownerNo.equals("")){
				sql1=sql1+ " and t.owner_no='"+ownerNo+"'";
			}
			System.out.println(sql1);
			this.genDao.updateBySql(sql1);
		}
		return new MsgRes(true, "", "");
	}
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String warehouseNo, String strJson, String str, int i, int j) {
		String sql = "select a.cell_no value,a.cell_no text,a.cell_no dropValue  "
				+ "from Cdef_Defcell a ,Cdef_Defarea b "
				+ " where a.cell_status<>1 "
				+ " and a.ware_no = b.ware_no "
				+ " and a.area_no=b.area_no "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.warehouse_no=b.warehouse_no "
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.enterprise_no=b.enterprise_no "
				+ " and b.area_attribute=0 "
				+ " and b.area_usetype in ('1','5','6') ";
				
		if (strJson != null && !strJson.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strJson);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + "and a.cell_no like '%" + str + "%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	
	}
	@Override
	public ExtListDataBo<Odata_LocateShortLogModel> getLocateFail(
			String currEnterpriseNo, String warehouseNo,String strQuery, PageBo poPageBo)
			throws Exception {
		
		String sql=" select distinct * from odata_locate_short_log a " +
				   "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"'  ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		sql += " order by a.wave_no desc ";
		String totSql="select count(*) from (" + sql + ") " ;	
		List list= genDao.getListByNativeSql(sql,Odata_LocateShortLogModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totSql);
		ExtListDataBo<Odata_LocateShortLogModel> extListBo= new ExtListDataBo<Odata_LocateShortLogModel>(list, count);
		return extListBo;	
	
	}
	
	//获取商品编码
	@Override
	public List<ComboxBo> getlocateArticleNo(String warehouseNo,
			String enterpriseNo, String articleNo, String strQuery)
			throws Exception {
		String strSql="select distinct b.article_no value,c.article_name text, " +
			          "       '['|| ltrim(b.article_no)||']'||c.article_name dropValue " +
			          "  from odata_exp_m a,odata_exp_d b,bdef_defarticle c  "+
			          " where a.warehouse_no='"+warehouseNo+"' "+
			          "   and a.enterprise_no='"+enterpriseNo+"' " +
			          "   and a.status='10' " +
			          "   and a.enterprise_no=b.enterprise_no " +
			          "   and a.warehouse_no=b.warehouse_no " +
			          "   and a.exp_no=b.exp_no " +
			          "   and b.enterprise_no=c.enterprise_no " +
			          "   and b.article_no=c.article_no ";
	
		if(articleNo!=null && !articleNo.equals("")){
			strSql=strSql+ "and c.article_no like '%"+articleNo+"%' ";
		}
		
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql + ws;
		}
//		strSql += " group by a.enterprise_no,a.warehouse_no,a.owner_no,a.exp_no,c.article_name ";
		/*if(strOrdertype!=null && !strOrdertype.equals("")){
			if(strOrdertype.equals("1")){
					strSql +=" and a.skucount=1 ";
			}else if(strOrdertype.equals("2")){
					strSql +=" and a.skucount>1 ";
			}
		}*/
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 1000);
		return (List<ComboxBo>) list;
	}
	
	//获取客户信息
	@Override
	public List<ComboxBo> getlocateCustNo(String enterpriseNo,
			String warehouseNo,String pageSql, String strQuery) throws Exception {
		String strSql="select  distinct t1.cust_no value,t1.cust_name text, " +
		              "       '['|| ltrim(t1.cust_no)||']'||t1.cust_name dropValue  " +
		              "  from odata_exp_m a ,odata_exp_d b, bdef_defcust t1  "+
		              " where a.warehouse_no='"+warehouseNo+"' "+
		              "   and a.enterprise_no='"+enterpriseNo+"' " +
		              "   and a.status='10' " +
		              "   and a.enterprise_no=b.enterprise_no " +
		              "   and a.warehouse_no=b.warehouse_no " +
		              "   and a.owner_no=b.owner_no " +
		              "   and a.exp_no=b.exp_no " +
		              "   and a.enterprise_no=t1.enterprise_no " +
		              "   and a.cust_no= t1.cust_no " +
		              "  and a.owner_no = t1.owner_no ";
		if(pageSql!=null && pageSql!=""){
			strSql+=" and (t1.cust_no like '%"+pageSql+"%' or t1.CUST_NOTECODE like '%"+pageSql+"%' or t1.cust_name like '%"+pageSql+"%') ";
		}
	
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql + ws;
		}
		strSql += " group by a.enterprise_no,a.warehouse_no,a.owner_no,"+
               "t1.cust_no,t1.cust_name,a.exp_no ";
		/*if(strOrdertype!=null && !strOrdertype.equals("")){
			if(strOrdertype.equals("1")){
				strSql +="having  count(distinct b.article_no)=1 ";
			}else if(strOrdertype.equals("2")){
				strSql +="having  count(distinct b.article_no)>1 ";
			}
		}*/
	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 1000);
		return (List<ComboxBo>) list;
	}
	
	//保存调度临时表V2(电商版) 
	@Override
	public MsgRes saveOdataTmpLocateSelectV2(String enterpriseNo,
			String strDetail, String strFlag, String calFlag, String expDate,
			String linkValue) throws Exception {
		Odata_ExpMModel expM = (Odata_ExpMModel)JSONObject.toBean(
				JSONObject.fromObject(strDetail),Odata_ExpMModel.class);
		String ip = HttpService.getIpAddr();
		MsgRes msg2=new MsgRes();
	  if(strFlag.equals("1")){
		    List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			inList.add(ip);
			inList.add(enterpriseNo);
			inList.add(expM.getWarehouseNo());
			inList.add(expM.getOwnerNo());
			inList.add(expM.getCustNo());
			inList.add(expM.getLineNo());
			inList.add(expM.getBatchNo());
			inList.add(expM.getExpNo().equals("")?"N":expM.getExpNo());
			inList.add(expM.getSumVolumn());
			inList.add(expM.getSumWeight());
			inList.add(expM.getSumBoxQty());
			inList.add(strFlag);
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "pklg_odata.insert_tmp_locate_select");
			System.out.println(resultList);
			if(resultList.get(0).toString().indexOf("Y")==-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			
			//调用资源试算
			if(!calFlag.equals("")&&calFlag.equals("1")){
				msg2 = tsccalculation(enterpriseNo,expM.getWarehouseNo(),expM.getOwnerNo(),expM.getExpType(),
						ip,expM.getExpNo(),"","0","0",calFlag);
				if(!msg2.getIsSucc())
				{
					return new MsgRes(false,msg2.getMsg(),"");
				}
			}
			MsgRes msg = tscBufferQty(enterpriseNo,expM.getWarehouseNo(),"");
			return new MsgRes(true,"",msg.getObj());
	  }else{
		  String strSql="select a.CUSTSEND_DATE," +
					"a.cust_no,a.exp_no," +
					"count(distinct b.article_no) articleItems,sum(b.ARTICLE_QTY) totalQty," +
					"sum(b.article_qty * d.unit_volumn) sumVolumn," +
					"sum(b.article_qty * d.unit_weight) sumWeight," +
					"sum(b.article_qty/b.packing_qty) sumBoxQty," +
					"count(distinct a.exp_no) expNoCount," +
					"case when vcl.LINE_NO is null then 'N' else vcl.LINE_NO end line_no," +
					"case when vcl.BATCH_NO is null then 'N' else vcl.BATCH_NO end batch_no " +
					"from odata_exp_m a,odata_exp_d b,bdef_defarticle d,v_cust_line_batch_cycle vcl " +
					"where a.exp_no=b.exp_no " +
					"and a.warehouse_no=b.warehouse_no " +
					"and a.enterprise_no=b.enterprise_no "+
					"and a.enterprise_no=d.enterprise_no "+
					"and a.status='10' " +
					"and b.article_no=d.article_no " +
					"and a.cust_no=vcl.CUST_NO(+) " +
					"and a.warehouse_no=vcl.WAREHOUSE_NO(+) " +
					"and a.enterprise_no=vcl.enterprise_no(+) "+
					"and a.warehouse_no='"+expM.getWarehouseNo()+"' "+
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.owner_no='" +expM.getOwnerNo()+"' "+
					"and a.cust_no='"+expM.getCustNo()+"' " +
					"and a.exp_type='"+expM.getExpType()+"' " +
					"and a.org_no='"+expM.getOrgNo()+"' " ;
		  
		  if(expDate!=null && !expDate.equals("")){
			  strSql=strSql+" and a.CUSTSEND_DATE=to_date('"+expDate+"','yyyy/mm/dd') ";
		  }
		  
		  if(linkValue!=null && !linkValue.equals("")){
			  strSql=strSql+" and vcl.line_no='"+linkValue+"' ";
		  }
		  
		  strSql+="group by a.CUSTSEND_DATE,a.cust_no,a.exp_no,vcl.line_no,vcl.batch_no  ";
		  List<Odata_ExpMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpMModel.class);
		  
		  for(int i=0;i<list.size();i++){
			  List inList=new ArrayList();
			  List outList=new ArrayList();
			  List resultList=new ArrayList();
				
			  outList.add("S");
			  inList.add(ip);
			  inList.add(enterpriseNo);
			  inList.add(expM.getWarehouseNo());
			  inList.add(expM.getOwnerNo());
			  inList.add(expM.getCustNo());
			  inList.add(expM.getLineNo());
			  inList.add(expM.getBatchNo());
			  inList.add(list.get(i).getExpNo());
			  inList.add(list.get(i).getSumVolumn());		            
			  inList.add(list.get(i).getSumWeight());			  
			  inList.add(list.get(i).getSumBoxQty());
			  inList.add("1");
			  System.out.println(inList);
			  resultList = genDao.execProc(inList, outList, "pklg_odata.insert_tmp_locate_select");
			  System.out.println(resultList);
			  if(resultList.get(0).toString().indexOf("Y")==-1)
			  {
				  throw new Exception(resultList.get(0).toString());
			  }
		  }		
		  //调用资源试算
		  if(calFlag.equals("1")){
			  msg2= tsccalculation(enterpriseNo,expM.getWarehouseNo(),expM.getOwnerNo(),expM.getExpType(),
						ip,"",expM.getCustNo(),"0","0",calFlag);
			  if(!msg2.getIsSucc())
				{
					return new MsgRes(false,msg2.getMsg(),"");
				}
		  }
		  MsgRes msg = tscBufferQty(enterpriseNo,expM.getWarehouseNo(),"");
			return new MsgRes(true,"",msg.getObj());
	  }
	}
	
	//删除调度临时表(电商版)
	@Override
	public MsgRes deleteOdataTmpLocateSelectV2(String enterpriseNo,
			String strDetail, String strFlag, String calFlag) throws Exception {
		Odata_ExpMModel expM = (Odata_ExpMModel)JSONObject.toBean(
				JSONObject.fromObject(strDetail),Odata_ExpMModel.class);
		String ip = HttpService.getIpAddr();
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(ip);
		inList.add(enterpriseNo);
		inList.add(expM.getWarehouseNo());
		inList.add(expM.getOwnerNo());
		inList.add(expM.getCustNo());
		inList.add(expM.getLineNo());
		inList.add(expM.getBatchNo());
		inList.add(expM.getExpNo().equals("")?"N":expM.getExpNo());
		inList.add(strFlag);
			
		resultList = genDao.execProc(inList, outList, "pklg_odata.delete_tmp_locate_select");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		 MsgRes msg2=new MsgRes();
		//调用资源试算
		if(calFlag.equals("1")){
			if(!expM.getCustNo().equals("") && expM.getCustNo()!=null){
				msg2=tsccalculation(enterpriseNo,expM.getWarehouseNo(),expM.getOwnerNo(),expM.getExpType(),
						ip,"",expM.getCustNo(),"1","0",calFlag);
				if(!msg2.getIsSucc())
				{
					return new MsgRes(false,msg2.getMsg(),"");
				}
			}else{
				msg2=tsccalculation(enterpriseNo,expM.getWarehouseNo(),expM.getOwnerNo(),expM.getExpType(),
						ip,expM.getExpNo(),"","1","0",calFlag);
				if(!msg2.getIsSucc())
				{
					return new MsgRes(false,msg2.getMsg(),"");
				}
			}				
		}
		MsgRes msg = tscBufferQty(enterpriseNo,expM.getWarehouseNo(),"");
		return new MsgRes(true,"",msg.getObj());
	}
	
	//重整月台试算(公用)
	@Override
	public MsgRes changeCalculation(String enterpriseNo,
			String warehouseNo, String strOwnerNo, String expType,
			String calFlag) throws Exception {
		String ip = HttpService.getIpAddr();
		 MsgRes msg2=new MsgRes();
		if(calFlag.equals("1")){
			msg2 =tsccalculation(enterpriseNo,warehouseNo,strOwnerNo,expType,
					ip,"","","0","1",calFlag);
			if(!msg2.getIsSucc())
			{
				return new MsgRes(false,msg2.getMsg(),"");
			}
		}else{
			msg2=tsccalculation(enterpriseNo,warehouseNo,strOwnerNo,expType,
					ip,"","","1","1",calFlag);
			if(!msg2.getIsSucc())
			{
				return new MsgRes(false,msg2.getMsg(),"");
			}
		}
		
		MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,strOwnerNo);
		return new MsgRes(true,"",msg.getObj());
	}
	
	//月台试算
	private MsgRes tsccalculation(String enterpriseNo,String warehouseNo,String ownerNo,
			String expType,String ip,String expNo,String custNo,String delFlag,
			String retryFlag,String calFlag ) throws Exception{
		 List inList=new ArrayList();
	     List outList=new ArrayList();
		 List resultList=new ArrayList();
		 outList.add("S");
		 inList.add(enterpriseNo);
		 inList.add(warehouseNo);
		 inList.add(ownerNo);
		 inList.add(expType);
		 inList.add(ip);
		 inList.add(expNo);
		 inList.add(custNo);
		 inList.add(calFlag);//strSendBufFlag；1是选中；0是没有选中
		 inList.add(delFlag);
		 inList.add(retryFlag);		            

		  System.out.println(inList);
		  resultList = genDao.execProc(inList, outList, "PKLG_BUFFER_CALCULATE.P_DeliverArea_Cal_Tmp");
		  System.out.println(resultList);
		  if(resultList.get(0).toString().indexOf("Y")==-1)
		  {
			//  throw new Exception(resultList.get(0).toString());
			  return new MsgRes(false,resultList.get(0).toString(),"");
		  }
		  return new MsgRes(true,resultList.get(0).toString(),"");
	}
	
	//重算月台资源（公用 ）
	@Override
	public MsgRes tscBackrollres(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String strExpType, String strFlag)
			throws Exception {
	     String ip = HttpService.getIpAddr();
		 List inList=new ArrayList();
	     List outList=new ArrayList();
		 List resultList=new ArrayList();
		 outList.add("S");
		 inList.add(enterpriseNo);
		 inList.add(warehouseNo);
		 inList.add(strOwnerNo);
		 inList.add(strExpType);
		 inList.add(ip);
		 inList.add("");
		 inList.add("");
		 inList.add("0");
		 inList.add("1");		            

		  System.out.println(inList);
		  resultList = genDao.execProc(inList, outList, "PKLG_BUFFER_CALCULATE.P_DeliverArea_Cal_Tmp");
		  System.out.println(resultList);
		  if(resultList.get(0).toString().indexOf("Y")==-1)
		  {
			  throw new Exception(resultList.get(0).toString());
		  }
		  MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
		  return new MsgRes(true,"重算成功",msg.getObj());
	}
	
	// 获取月台可用货位数
	@Override
	public MsgRes tscBufferQty(String enterpriseNo, String warehouseNo,
			String strFlag) throws Exception {
	    String ip = HttpService.getIpAddr();
		String sql = "select count(*) from oset_buffer t " +
				"where t.enterprise_no='"+enterpriseNo+"' " +
				"  and t.warehouse_no='"+warehouseNo+"'  " +
		        "  and t.cell_no not in (select distinct tmp.cell_no from odata_temp_send_area_calculate tmp where tmp.tmp_id='"+ip+"' " +
		        "  and tmp.enterprise_no=t.enterprise_no and tmp.warehouse_no=t.warehouse_no " +
		        "  union select send.cell_no from odata_send_area_calculate send) ";
		List<String> list = genDao.getListByNativeSql(sql);
		return new MsgRes(true,"",list.get(0));
	}	
	
	/**
	 * 获取客户下拉
	 */
	@Override
	public List<ComboxBo> getCust(String strWarehouseNo, String enterpriseNo,String strOwnerNo)
			throws Exception {
		String strSql="select distinct m.cust_no value,t.cust_name text, " +
				"'['|| ltrim(m.cust_no)||']'||t.cust_name dropValue " +
				"from bdef_defcust t,odata_exp_m m " +
				"where t.enterprise_no=m.enterprise_no and t.owner_no=m.owner_no " +
				"and t.cust_no=m.cust_no and m.status='10' "+
				"and m.warehouse_no='"+strWarehouseNo+
				"' and m.enterprise_no='"+enterpriseNo+"' ";
		if(strOwnerNo!=null && !strOwnerNo.equals("")){
			strSql =strSql+ " and m.owner_no='"+strOwnerNo+"'";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
		
	}
	
	/**
	 * 获取备注下拉
	 */
	@Override
	public List<ComboxBo> getExpRemark(String strWarehouseNo,
			String enterpriseNo, String strOwnerNo) throws Exception {
		String strSql="select distinct m.exp_remark value,m.exp_remark text,m.exp_remark dropValue " +
				" from odata_exp_m m " +
				"where m.enterprise_no='"+enterpriseNo+"' " +
				"and m.warehouse_no='"+strWarehouseNo+"' " +
				"and m.exp_remark is not null and m.status='10' ";
		if(strOwnerNo!=null && !strOwnerNo.equals("")){
			strSql = strSql+" and m.owner_no='"+strOwnerNo+"'";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取定位失败查询-波次号下拉
	@Override
	public List<ComboxBo> getLocateNoForQuery(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery)
			throws Exception {
		String strSql="select distinct a.wave_no value,a.wave_no text," +	
					"a.wave_no dropValue " +
					"from odata_locate_short_log a where 1=1  "+
					"and a.warehouse_no ='"+strWarehouseNo+"' " +
					"and a.enterprise_no= '"+strEnterpriseNo+"' " +
					"and a.owner_no in("+strWorkerOwner+") ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取定位失败查询-商品编码下拉
	@Override
	public List<ComboxBo> getArticleNoForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str,
			String strQuery) throws Exception {
		String sql = "select distinct a.article_no value,a.article_no text,a.article_no dropValue  "
					+ "from odata_locate_short_log a "
					+ " where a.enterprise_no='" + strEnterpriseNo + "' "
					+ " and a.warehouse_No='" + strWarehouseNo + "' "
					+ " and a.owner_no in(" + strWorkerOwner + ") ";
					
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + "and a.article_no like '%" + str + "%' ";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取定位条件承运商下拉
	@Override
	public List<ComboxBo> getlocateSanplNo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str,
			String strQuery) throws Exception {
		String sql = "select distinct a.shipper_no value,b.shipper_name text," +
				"'['|| ltrim(a.shipper_no)||']'||b.shipper_name dropValue  "
				+ "from odata_exp_m a,bdef_defshipper b"
				+ " where a.enterprise_no=b.enterprise_no " 
				+ " and a.warehouse_No=b.warehouse_No "
				+ " and a.shipper_no=b.shipper_no " 
				+ " and a.status='10' " 
				+ " and a.enterprise_no='" + strEnterpriseNo + "' "
				+ " and a.warehouse_No='" + strWarehouseNo + "' "
				+ " and a.owner_no in(" + strWorkerOwner + ") ";
	 			
       if (strQuery != null && !strQuery.equals("")) {
		   String ws = CommUtil.covtCollectionToWhereSql(strQuery);
		   sql = sql + ws;
	   }
	   if (str != null && !str.equals("")) {
		   sql = sql + "and a.shipper_no like '%" + str + "%' ";
	   }
	   List list = genDao.getListByNativeSql(sql, ComboxBo.class);
	   return (List<ComboxBo>) list;
	}
	//获取波次规则下拉v2
	@Override
	public List<ComboxBo> queryOrdertypeCombo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,String strExpType)
			throws Exception {
		String sql =   "select t.batch_rule_id as value,t.batch_rule_name as text, " +
				"'['|| t.batch_rule_id ||']' || t.batch_rule_name as dropValue " +
                       "from wms_outwaveplan_d t ,wms_outorder a  " +
                       "where t.enterprise_no=a.enterprise_no " +
                       "and t.batch_strategy_id=a.manualbatch_strategy_id " +
                       "and a.exp_type='" + strExpType + "' and t.status='1' " +
                       "and t.enterprise_no='" + strEnterpriseNo + "' ";
   
		if (strQuery != null && !strQuery.equals("")) {
             String ws = CommUtil.covtCollectionToWhereSql(strQuery);
             sql = sql + ws;
        }                
				
	List list = genDao.getListByNativeSql(sql, ComboxBo.class);
	return (List<ComboxBo>) list;
	}
	
	
	//根据波次规则写临时表（过滤）
	@Override
	public MsgRes tscOdataTmpLocateSelect(String currEnterpriseNo,
			String warehouseNo, String strOwnerNo, String strOrdertype,String strExpType)
			throws Exception {
	     String ip = HttpService.getIpAddr();
		 List inList=new ArrayList();
	     List outList=new ArrayList();
		 List resultList=new ArrayList();
		 outList.add("S");
		 
		 inList.add(currEnterpriseNo);
		 inList.add(strOrdertype);
		 inList.add(strExpType);
		 inList.add(ip);
		
		  System.out.println(inList);
		  resultList = genDao.execProc(inList, outList, "PKLG_ODISPATCH.P_WriteOrderByRuleID");
		  System.out.println(resultList);
		  if(resultList.get(0).toString().indexOf("Y")==-1)
		  {
			  throw new Exception(resultList.get(0).toString());
		  }
		  return new MsgRes(true,"","");
	}
}





