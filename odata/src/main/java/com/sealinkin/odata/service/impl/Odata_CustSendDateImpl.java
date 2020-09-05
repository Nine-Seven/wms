package com.sealinkin.odata.service.impl;
/*
 * 出货日期确认
 * hkl
 * 3922
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.po.Odata_ExpD;
import com.sealinkin.odata.po.Odata_ExpM;
import com.sealinkin.odata.service.IOdata_CustSendDateService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_CustSendDateImpl implements IOdata_CustSendDateService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager
			genDao) {
		this.genDao = genDao;
	}
	
	//获取客户下拉
	@Override
	public List<ComboxBo> queryCustCombo(String strEnterpriseNo,
			String strWarehouseNo, String strStr) throws Exception {

			String strSql="select t1.cust_no value,t1.cust_name text," +
					"'['|| ltrim(t1.cust_no)||']'||t1.cust_name dropValue " +
					"from bdef_defcust t1 " +
					"where t1.cust_no in(select cust_no from odata_exp_m where warehouse_no='"+strWarehouseNo+
					"' and enterprise_no='"+strEnterpriseNo+
					"' and status='10')" +
					"  and t1.enterprise_no='"+strEnterpriseNo+"' ";
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}

	/**
	 * 单据列表查询
	 */
	@Override
	public ExtListDataBo<Odata_ExpMModel> getOdata_CustSendDate(
			String strEnterpriseNo, String strWarehouseNo,
			String strStr,String strQuery)
			throws Exception {
		String sql ="select a.enterprise_no, a.warehouse_no, " +
		  "a.owner_no,a.exp_type,a.sourceexp_no,a.last_custsend_date,a.custsend_date, " +
          "a.cust_no,c.cust_name,count(distinct b.article_no) articleItems, " +
          "sum(b.ARTICLE_QTY) totalQty, " +
          "sum(b.article_qty * d.unit_volumn) sumVolumn, " +
          "sum(b.article_qty * d.unit_weight) sumWeight, " +
          "sum(b.article_qty/b.packing_qty) sumBoxQty " +
          "from odata_exp_m a,odata_exp_d b,bdef_defcust c,bdef_defarticle d " +
          "where a.exp_no=b.exp_no  " +
          "and a.warehouse_no=b.warehouse_no  " +
          "and a.enterprise_no=b.enterprise_no  " +
          "and a.exp_no=b.exp_no  " +
          "and a.enterprise_no=c.enterprise_no  " +
          "and a.cust_no=c.cust_no  " +
          "and a.owner_no=c.owner_no  " +
          "and a.enterprise_no=d.enterprise_no  " +
          "and b.article_no=d.article_no  " +
          "and a.status='10'  " +
          "and a.warehouse_no='"+strWarehouseNo+"'  " +
          "and a.enterprise_no='"+strEnterpriseNo+"'  ";
		if(strQuery.equals("1")){//查未确认数据
			sql=sql+" and a.custsend_date is null ";
		}else{////查已确认数据
			sql=sql+" and a.custsend_date is not null ";
		}
		if(strStr!=null && !strStr.equals("") ){
			String ws=CommUtil.covtCollectionToWhereSql(strStr);
			sql=sql+ws;
		}
		sql=sql+" group by a.enterprise_no, a.warehouse_no,a.owner_no," +
				"a.cust_no,c.cust_name,a.exp_type,a.sourceexp_no,a.last_custsend_date,a.custsend_date " +
				"order by a.warehouse_no,a.cust_no";
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql, Odata_ExpMModel.class);
		ExtListDataBo<Odata_ExpMModel> extListBo= new ExtListDataBo<Odata_ExpMModel>(list, 0);
        return extListBo;
	}
	
	//根据确认日期获取材积
	@Override
	public MsgRes queryVolumn(String strEnterpriseNo, String strWarehouseNo,
			String str) throws Exception {
        String sql = "select "+
               " sum(b.article_qty * d.unit_volumn) sumVolumn, "+
               " sum(b.article_qty * d.unit_weight) sumWeight, "+
               " sum(b.article_qty/b.packing_qty) sumBoxQty  "+
               " from odata_exp_m a,odata_exp_d b,bdef_defarticle d  "+
               " where a.exp_no=b.exp_no and a.warehouse_no=b.warehouse_no   "+
               " and a.enterprise_no=b.enterprise_no and a.exp_no=b.exp_no  "+
               " and a.enterprise_no=d.enterprise_no and b.article_no=d.article_no   "+
               " and a.warehouse_no='"+strWarehouseNo+"'   "+
               " and a.enterprise_no='"+strEnterpriseNo+"'  " +
               " and a.custsend_date=to_date('"+str+"','yyyy/mm/dd') " ;
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql, Odata_ExpMModel.class);
		return new MsgRes(true, "", list.get(0));
	}
	
	
	//确认
	@Override
	public MsgRes tscCustSendComfirm(String str,String workerNo) throws Exception {
		List<Odata_ExpMModel> list=JSON.parseArray(str,Odata_ExpMModel.class);
        for(int i=0;i<list.size();i++){
        	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
        	String sql = "update odata_exp_m m set " +
        			"m.custsend_date=to_date('"+dateFormater.format(list.get(i).getCustsendDate())+"','yyyy/mm/dd')," +
        			"m.updt_name='"+workerNo+"',m.updt_date=sysdate " +
        			"where m.enterprise_no='"+list.get(i).getEnterpriseNo()+"' " +
        			"and m.warehouse_no='"+list.get(i).getWarehouseNo()+"' " +
        			"and m.sourceexp_no='"+list.get(i).getSourceexpNo()+"' ";
        	genDao.updateBySql(sql);
        }
	   return new MsgRes(true,"确认成功","");
	}


}
