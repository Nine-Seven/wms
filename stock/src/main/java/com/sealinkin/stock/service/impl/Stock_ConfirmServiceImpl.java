package com.sealinkin.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.stock.model.Stock_ConfirmDModel;
import com.sealinkin.stock.model.Stock_ConfirmMModel;
import com.sealinkin.stock.service.IStock_ConfirmService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Stock_ConfirmServiceImpl implements IStock_ConfirmService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//填充货主下拉
	@Override
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception {
		String strSql="select distinct t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.owner_no in( " +
				"select owner_no from stock_confirm_m a " + 
				"where "+
				"a.warehouse_no='"+warehouseNo+"' "+
				"and a.owner_no in ("+workerOwner+") "+
				"and a.status not in ('16')) " +
				"and t1.enterprise_no='"+enterpriseNo+"' " ;
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//调整类型下拉
	@Override
	public List<ComboxBo> queryPlanTypeCombo(String enterpriseNo,
			String warehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct t1.plan_type value,t1.plan_type text," +
				"f_get_fieldtext('STOCK_PLAN_M','PLAN_TYPE',t1.plan_type) dropValue " +
				"from stock_plan_m t1 where t1.plan_no in( " +
				"select plan_no from stock_confirm_m a " + 
				"where "+
				"a.warehouse_no='"+warehouseNo+"' "+
				"and a.owner_no in ("+strWorkerOwner+") "+
				"and a.status not in ('16')) " +
				"and t1.enterprise_no='"+enterpriseNo+"' " ;
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//确认单投档信息列表
	@Override
	public ExtListDataBo<Stock_ConfirmMModel> getStockConfirmMList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String str, PageBo pageBo) throws Exception {
		String strTotsql="";
		String strSql = "select a.enterprise_no,a.warehouse_no," +
				"a.owner_no,a.confirm_no,a.plan_no," +
				"a.status,a.confirm_date,a.send_flag,a.rgst_name,a.rgst_date , " +
				"b.plan_type,b.po_no," +
				"f_get_fieldtext('STOCK_PLAN_M','PLAN_TYPE',b.plan_type) planTypeText, " +
				"f_get_fieldtext('STOCK_CONFIRM_M','STATUS',a.status) statusText " +
              "from stock_confirm_m a, stock_plan_m b  " +
             "where a.enterprise_no=b.enterprise_no " +
               "and a.warehouse_no=b.warehouse_no " +
               "and a.owner_no=b.owner_no " +
               "and a.plan_no=b.plan_no " +
               "and a.enterprise_no='"+strEnterpriseNo+"' " +
               "and a.warehouse_no='"+strWarehouseNo+"' " +
               "and a.owner_no in("+strOwnerNo+") " ;
		
       if(str!=null && !str.equals(""))
       {
	        String strW=CommUtil.covtCollectionToWhereSql(str);
	        strSql+=strW;
       }
      
      strSql=strSql+" order by a.status,a.confirm_no desc ";
      
      strTotsql = "select count(*) from (" + strSql + ")";
      List<Stock_ConfirmMModel> list = genDao.getListByNativeSql(strSql,Stock_ConfirmMModel.class,pageBo.getStart(), pageBo.getPagesize());
      Integer count = genDao.getCountByNativeSql(strTotsql);
	  ExtListDataBo<Stock_ConfirmMModel> extListBo= new ExtListDataBo<Stock_ConfirmMModel>(list, count);
      return extListBo;
	}
	//确认单明细信息列表
	@Override
	public List<Stock_ConfirmDModel> getStockConfirmDList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String str, PageBo pageBo) throws Exception {
		String strSql = "select a.*, a.article_qty articleQty," +
				"trunc(a.article_qty/a.packing_qty) as planBox,"+
				"trunc(mod(a.article_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) as planQmin,"+
				"(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) * v.QMIN_OPERATE_PACKING) as planDis,"+
				
				"trunc(a.article_qty/a.packing_qty) as realBox,"+
				"trunc(mod(a.article_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) as realQmin,"+
				"(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) * v.QMIN_OPERATE_PACKING) as realDis,"+
				
				//"nvl(pk.packing_unit, decode(a.packing_qty,v.qmin_operate_packing,v.qmin_operate_packing_unit,v.unit)) unit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,v.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,v.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,v.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,v.unit_packing) spec,"+
				"v.owner_article_no," +
				"v.article_name,v.barcode," +
				"(a.article_qty/a.packing_qty) as pobox, " +
			    "(a.real_qty/a.packing_qty) as realPobox, " +
			    "to_char(a.produce_date,'yyyy-mm-dd')produceDateText,"+
			    "to_char(a.expire_date,'yyyy-mm-dd')expireDateText " + 
              "from (select scd.enterprise_no, scd.warehouse_no,  " +
                       "scd.owner_no,scd.confirm_no,scd.source_no,  " +
                       "scd.article_no,scd.packing_qty,scd.produce_date,  " +
                       "scd.expire_date,sum(scd.article_qty) article_qty,  " +
                       "sum(scd.real_qty) real_qty  " +
                     "from stock_confirm_d scd  " +
                     "group by scd.enterprise_no,scd.warehouse_no,  " +
                       "scd.owner_no,scd.confirm_no,scd.source_no,  " +
                       "scd.article_no,scd.packing_qty,scd.produce_date,  " +
                       "scd.expire_date) a,  " +
                     "v_bdef_defarticle v,BDEF_ARTICLE_PACKING PK    " +
             "where a.enterprise_no=v.enterprise_no " +
               "and a.owner_no=v.owner_no " +
               "and a.article_no=v.article_no " +
               " AND A.packing_qty=PK.PACKING_QTY(+) " +
               "and a.enterprise_no=pk.enterprise_no(+) " +
               "and a.article_no=pk.article_no(+)" +
               "and a.enterprise_no='"+strEnterpriseNo+"' " +
               "and a.warehouse_no='"+strWarehouseNo+"' " +
               "and a.owner_no in("+strOwnerNo+") ";
		
        if(str!=null && !str.equals(""))
        {
	        String strW=CommUtil.covtCollectionToWhereSql(str);
	        strSql+=strW;
        }
            
	    List<Stock_ConfirmDModel> list=genDao.getListByNativeSql(strSql, Stock_ConfirmDModel.class);
	    return list;
	}
	//调帐确认
	@Override
	public MsgRes tscConfirm(String strJsonMaster) throws Exception {
		Stock_ConfirmMModel master=(Stock_ConfirmMModel) JSON.parseObject(strJsonMaster, Stock_ConfirmMModel.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(master.getEnterpriseNo());//strENTERPRISE_NO
		inList.add(master.getWarehouseNo());//strWAREHOUSE_NO
		inList.add(master.getOwnerNo());//strOwnerNo
		inList.add(master.getConfirmNo());//strConfirmNo
		inList.add(master.getRgstName());//strUserId
		resultList=genDao.execProc(inList, outList, "PKLG_ADJ.P_ComfireStockPlan");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"确认成功","");
	}
}
