package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpCancelDModel;
import com.sealinkin.odata.model.Odata_ExpCancelMModel;
import com.sealinkin.odata.service.IOdata_ExpCancelService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_ExpCancelImpl implements IOdata_ExpCancelService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//获取货主下拉内容
	@Override
	public List<ComboxBo> getOwnerCombo(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str) throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 " +
				"where t1.enterprise_no='"+strEnterpriseNo+"' " +
				  "and t1.owner_no in (select owner_no from odata_exp_cancel_m " +
				                       "where enterprise_no='"+strEnterpriseNo+"' " +
				  		                 "and warehouse_no='"+strWarehouseNo+"' " +
				  		                 "and status<>14 " +
				  		              "union " +
				  		              "select owner_no from odata_exp_cancel_m_hty " +
				                       "where enterprise_no='"+strEnterpriseNo+"' " +
				  		                 "and warehouse_no='"+strWarehouseNo+"'  ) ";
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql=strSql+" order by t1.owner_no ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//获取单据状态下拉内容
	@Override
	public List<ComboxBo> getStatusTextCombo(String strEnterpriseNo,
			String strWarehouseNo, String str,String strOperateDate) throws Exception {
		String strSql = "select a.status value, f_get_fieldtext('ODATA_EXP_CANCEL_M','STATUS',a.status) text, " +
				"f_get_fieldtext('ODATA_EXP_CANCEL_M','STATUS',a.status) dropValue " +
		        "from odata_exp_cancel_M a " +
		       "where a.enterprise_no='"+strEnterpriseNo+"' " +
		         "and a.warehouse_no='"+strWarehouseNo+"' " +
		         "and a.status<>14 " ;
		if(str!=null && !str.equals(""))
		{
			String strW=CommUtil.covtCollectionToWhereSql(str);
			strSql+=strW;
		}
		strSql=strSql+" group by a.status order by a.status ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//获取病单来源下拉内容
	@Override
	public List<ComboxBo> getSuorceTypeCombo(String strEnterpriseNo,
			String strWarehouseNo, String str,String strFlag)
			throws Exception {
		String strSql = "select a.source_type value, f_get_fieldtext('ODATA_EXP_CANCEL_M','SOURCE_TYPE',a.source_type) text, " +
				        "'['|| ltrim(a.source_type)||']'||f_get_fieldtext('ODATA_EXP_CANCEL_M','SOURCE_TYPE',a.source_type) dropValue " +
				        "from %s1  " +
				       "where a.enterprise_no='"+strEnterpriseNo+"' " +
				         "and a.warehouse_no='"+strWarehouseNo+"' " +
				         "and a.status<>14 ";
		//货主过滤等条件
		if(str!=null && !str.equals(""))
		{
			String strW=CommUtil.covtCollectionToWhereSql(str);
			strSql+=strW;
		}
		strSql=strSql+"group by a.source_type order by a.source_type ";
		if(strFlag.equals("1")){
			strSql = strSql.replace("%s1", "odata_exp_cancel_m_hty a");
        }else if(strFlag.equals("0")){
			strSql = strSql.replace("%s1", "odata_exp_cancel_m a");
        }
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	//获取撕票单号下拉内容
	@Override
	public List<ComboxBo> getCancelNoCombo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strOperateDate,String strFlag)
			throws Exception {
		String strSql = "select a.cancel_no value, a.cancel_no text, a.cancel_no dropValue " +
		                "from %s1 " +
		               "where a.enterprise_no='"+strEnterpriseNo+"' " +
		                 "and a.warehouse_no='"+strWarehouseNo+"' " +
		                 "and a.status<>14 ";
        //货主过滤等条件
        if(str!=null && !str.equals(""))
        {
	        String strW=CommUtil.covtCollectionToWhereSql(str);
	        strSql+=strW;
        }
        if(strOperateDate!=null && !strOperateDate.equals("")){
        	strSql+="and to_date('"+strOperateDate+"','yyyy/mm/dd')=a.operate_date";
        }
        strSql=strSql+" order by a.cancel_no ";
        if(strFlag.equals("1")){
			strSql = strSql.replace("%s1", "odata_exp_cancel_m_hty a");
        }else if(strFlag.equals("0")){
			strSql = strSql.replace("%s1", "odata_exp_cancel_m a");
        }
        List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
        return (List<ComboxBo>) list;
	}
	//获取病单处理单头档信息
	@Override
	public ExtListDataBo<Odata_ExpCancelMModel> getExpCancelMList(
			String strEnterpriseNo, String strWarehouseNo, String str,
			String strOperateDate,String strFlag, PageBo pageBo) throws Exception {
		String strTotsql="";
		String strSql = "select a.enterprise_no,a.cancel_no, " +
				"a.owner_no,a.exp_type,a.exp_no,a.exp_date, " +
				"a.cust_no,a.sub_cust_no,a.operate_date, " +
				"a.status,a.handle_flag,a.send_flag,a.source_type,b.sourceexp_no, " +
				"f_get_fieldtext('ODATA_EXP_CANCEL_M','STATUS',a.status) statusText, " +
				"f_get_fieldtext('ODATA_EXP_CANCEL_M','SOURCE_TYPE',a.source_type) suorceTypeText " +
              "from %s1 ,%s2  " +
             "where a.enterprise_no=b.enterprise_no " +
               "and a.warehouse_no=b.warehouse_no " +
               "and a.exp_no=b.exp_no " +
               "and a.enterprise_no='"+strEnterpriseNo+"' " +
               "and a.warehouse_no='"+strWarehouseNo+"' " +
               "and a.status<>14 ";
		//货主过滤等条件
      if(str!=null && !str.equals(""))
      {
	        String strW=CommUtil.covtCollectionToWhereSql(str);
	        strSql+=strW;
      }
      if(strOperateDate!=null && !strOperateDate.equals("")){
      	strSql+="and to_date('"+strOperateDate+"','yyyy/mm/dd')=a.operate_date";
      }
      strSql=strSql+" order by a.cancel_no ";
      if(strFlag.equals("1")){
			strSql = strSql.replace("%s1", " odata_exp_cancel_m_hty a");
			strSql = strSql.replace("%s2", " odata_exp_m b ");
      }else if(strFlag.equals("0")){
			strSql = strSql.replace("%s1", " odata_exp_cancel_m a");
			strSql = strSql.replace("%s2", " odata_exp_m b ");
      }
      strTotsql = "select count(*) from (" + strSql + ")";
      List<Odata_ExpCancelMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpCancelMModel.class,pageBo.getStart(), pageBo.getPagesize());
      Integer count = genDao.getCountByNativeSql(strTotsql);
	  ExtListDataBo<Odata_ExpCancelMModel> extListBo= new ExtListDataBo<Odata_ExpCancelMModel>(list, count);
      return extListBo;
	}
	@Override
	public ExtListDataBo<Odata_ExpCancelDModel> getExpCancelDList(
			String strEnterpriseNo, String strWarehouseNo, String str,String strFlag,
			 PageBo pageBo) throws Exception {
		String strSql = " SELECT a.enterprise_no, "+
            "a.warehouse_no, "+
            "a.owner_no, "+
            "a.article_no, "+
            "v.article_name, "+
            "v.owner_article_no, "+
            "v.barcode, "+
            "a.packing_qty, "+
            "v.unit, "+
            "a.article_qty as article_qty, "+
            "a.real_qty as real_qty, "+
            "a.differenceQty as differenceQty,  "+
            
			"trunc(a.article_qty / a.packing_qty) as planBox,"+
			"trunc(mod(a.article_qty, a.packing_qty) / a.QMIN_OPERATE_PACKING) as planQmin,"+
			"(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/a.QMIN_OPERATE_PACKING) * a.QMIN_OPERATE_PACKING) as planDis,"+
			
			"trunc(a.real_qty / a.packing_qty) as realBox,"+
			"trunc(mod(a.real_qty, a.packing_qty) / a.QMIN_OPERATE_PACKING) as realQmin,"+
			"(a.real_qty - trunc(a.real_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.real_qty,a.packing_qty)/a.QMIN_OPERATE_PACKING) * a.QMIN_OPERATE_PACKING) as realDis,"+
			
			"trunc(a.differenceQty / a.packing_qty) as differenceBox,"+
			"trunc(mod(a.differenceQty, a.packing_qty) / a.QMIN_OPERATE_PACKING) as differenceQmin,"+
			"(a.differenceQty - trunc(a.differenceQty/a.packing_qty) * a.packing_qty - trunc(mod(a.differenceQty,a.packing_qty)/a.QMIN_OPERATE_PACKING) * a.QMIN_OPERATE_PACKING) as differenceDis,"+
            
            "NVL(b.available_qty/a.packing_qty, "+
            "0) AS available_qty, "+
           "DECODE(SIGN(NVL(b.available_qty/a.packing_qty,0) - a.differenceQty/a.packing_qty), "+
            "-1, "+
            "a.differenceQty/a.packing_qty - NVL(b.available_qty/a.packing_qty, "+
            "0), "+
            "0) AS no_enough_qty "+ 
        "from "+
            "(SELECT " +
                "a.cancel_no, " +
                "b.enterprise_no, "+
                "b.warehouse_no, "+
                "b.owner_no, "+
                "da.article_no, "+
                "da.qmin_operate_packing, "+
                "b.packing_qty, "+
                "sum(b.article_qty)article_qty, "+
                "sum(b.real_qty)real_qty, "+
                "SUM(b.article_qty-b.real_qty) AS differenceQty "+
            "FROM "+
                "%s1, "+
                "%s2, "+
                "bdef_defarticle da "+
            "WHERE "+
                "b.article_no = da.article_no "+ 
                "and a.warehouse_no = b.warehouse_no  "+
                "and a.enterprise_no=b.enterprise_no  " +
                "and a.exp_no = b.exp_no  "+
            "GROUP BY " +
                "a.cancel_no, "+
                "b.enterprise_no, "+
                "da.OWNER_ARTICLE_NO, "+
                "b.warehouse_no, "+
                "da.qmin_operate_packing, "+
                "b.owner_no, "+
                "da.ARTICLE_NO,b.packing_qty) a, "+
                "(SELECT "+
                    "a.enterprise_no, "+
                    "a.warehouse_no, "+
                    "a.owner_no, "+
                    "a.article_no, "+
                    "a.stock_type, "+
                    "a.stock_value,a.packing_qty, "+
                    "SUM(a.qty - NVL(a.outstock_qty, "+
                    "0) + DECODE(a.instock_type, "+
                    "'1', "+
                    "NVL(a.instock_qty, "+
                    "0), "+
                    "0)) AS available_qty  "+
                "FROM "+
                    "stock_content a, "+
                    "stock_article_info b, "+
                    "cdef_defcell c, "+
                    "cdef_defarea d  "+
                "WHERE "+
                    "a.article_no = b.article_no  "+
                    "AND a.article_id = b.article_id  "+
                    "AND a.warehouse_no = c.warehouse_no  "+
                    "AND a.cell_no = c.cell_no  "+
                    "and c.warehouse_no = d.warehouse_no  "+
                    "AND c.ware_no = d.ware_no  "+
                    "AND a.enterprise_no = c.enterprise_no  "+
                    "AND a.enterprise_no = b.enterprise_no  "+
                    "AND a.enterprise_no = d.enterprise_no  "+
                    "AND c.area_no = d.area_no  "+
                    "AND a.flag <> '2'  "+
                    "AND a.status = '0'  "+ 
                    "AND a.warehouse_no = '"+strWarehouseNo+"'  "+
                    "AND a.enterprise_no='"+strEnterpriseNo+"'  "+
                    "AND (a.instock_qty + a.qty - a.outstock_qty + a.unusual_qty ) > 0  "+
                    "and ( "+
                        "d.area_usetype = '1'  "+
                        "or d.area_usetype = '5'  "+
                        "or d.area_usetype = '6' " +
                        ")  "+
                    "AND c.cell_status = '0'  "+
                    "AND c.check_status = '0'  "+
                    "AND d.Area_Attribute in ('0')  "+
                "GROUP BY "+
                    "a.enterprise_no, "+
                    "a.warehouse_no, "+
                    "a.owner_no, "+
                    "a.stock_type, "+
                    "a.stock_value, "+
                    "a.article_no,a.packing_qty) b, "+
                "bdef_defarticle v "+
            "WHERE "+
                "a.warehouse_no = b.warehouse_no(+)  "+
                "AND a.owner_no = b.owner_no(+)  and a.packing_qty = b.packing_qty(+) "+
                "AND a.enterprise_no=b.enterprise_no(+)  "+
                "AND a.article_no = b.article_no(+)  "+
                "and a.article_no=v.article_no  "+ 
                "and a.enterprise_no=v.enterprise_no "+
                "and a.owner_no=v.OWNER_NO  "+
                "and a.warehouse_no = '"+strWarehouseNo+"'  "+
                "and a.enterprise_no='"+strEnterpriseNo+"' " +
                "and a.cancel_no='"+str+"' " +
            "ORDER BY "+
                "a.article_no "; 
		if(strFlag.equals("1")){
			strSql = strSql.replace("%s1", "odata_exp_cancel_m_hty a ");
			strSql = strSql.replace("%s2", "odata_exp_cancel_d_hty b ");
        }else if(strFlag.equals("0")){
			strSql = strSql.replace("%s1", "odata_exp_cancel_m a ");
			strSql = strSql.replace("%s2", "odata_exp_cancel_d b ");
        }
		String totsql = "select count(*) from ("+ strSql +")";
		List<Odata_ExpCancelDModel> list = genDao.getListByNativeSql(strSql,Odata_ExpCancelDModel.class,pageBo.getStart(), pageBo.getPagesize());
        Integer count = genDao.getCountByNativeSql(totsql);
	    ExtListDataBo<Odata_ExpCancelDModel> extListBo= new ExtListDataBo<Odata_ExpCancelDModel>(list, count);
        return extListBo;
	}
	//上传
		@Override
	public MsgRes upLocad(String strEnterpriseNo, String strDetail,
			String strFlag) throws Exception {
		Odata_ExpCancelMModel poOl = (Odata_ExpCancelMModel)JSONObject.toBean(
					JSONObject.fromObject(strDetail),Odata_ExpCancelMModel.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(poOl.getWarehouseNo());//strWarehouseNo
		inList.add(poOl.getOwnerNo());//strOwnerNo
		inList.add(poOl.getCancelNo());//strCancelNo
		inList.add(poOl.getExpNo());//strExpNo
		inList.add(poOl.getUpdtName());//strUserId
			
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_EXPCANCEL.P_UpLocate");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "上传成功", " ");
	}
	
	//补拣
	@Override
	public MsgRes repeatLocate(String strEnterpriseNo, String strDetail,
			String strFlag) throws Exception {
		Odata_ExpCancelMModel poOl = (Odata_ExpCancelMModel)JSONObject.toBean(
				JSONObject.fromObject(strDetail),Odata_ExpCancelMModel.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(poOl.getWarehouseNo());//strWarehouseNo
		inList.add(poOl.getOwnerNo());//strOwnerNo
		inList.add(poOl.getCancelNo());//strCancelNo
		inList.add(poOl.getExpNo());//strExpNo
		inList.add(poOl.getSourceType());//strSourceType
		inList.add(poOl.getUpdtName());//strUserId
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_EXPCANCEL.P_RepeatLocate");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "补拣成功", " ");
	}

}
