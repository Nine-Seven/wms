package com.sealinkin.acdata.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.po.Acdata_OrderD;
import com.sealinkin.acdata.po.Acdata_OrderM;
import com.sealinkin.acdata.service.IAcdata_OrderService;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Acdata_OrderImpl implements IAcdata_OrderService {
	private IGenericManager genDao;

	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public ExtListDataBo<Acdata_OrderMModel> getOrderMList(String warehouseNo,
			String strQuery, PageBo pageBo) throws Exception {

		String sql="select t.*, "+
				"f_get_fieldtext('N','STATUS',t.status) statusText, "+
				"to_char(t.sdate,'yyyy-MM-dd') sdateText "+
				"from acdata_order_m t where 1=1 " ;
		String totsql="select count(1) from acdata_order_m t where 1=1 " ;
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" order by t.sdate ";
		List<Acdata_OrderMModel> list = genDao.getListByNativeSql(sql,Acdata_OrderMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Acdata_OrderMModel> extListBo= new ExtListDataBo<Acdata_OrderMModel>(list, count);
		return extListBo;
	
	}
	@Override
	public List<Acdata_OrderDModel> getOrderDList(String strOrderNo)
			throws Exception {

		String strSql="select d.*"+
				"from acdata_order_d d "+
				"where d.order_no = '"+strOrderNo+"'" +
				"order by d.article_name";
		List<Acdata_OrderDModel> list = genDao.getListByNativeSql(strSql,Acdata_OrderDModel.class);	
		return list;
	
	}
	@Override
	public MsgRes saveOrUpdate(String orderD, String orderM, String flag)
			throws Exception {
		
		
		
		Acdata_OrderM acdateOrderM=(Acdata_OrderM)JSON.parseObject(orderM,Acdata_OrderM.class);
		
		List<Acdata_OrderD> list=JSON.parseArray(orderD,Acdata_OrderD.class);
	
		
		String sql="update acdata_order_m m set m.owner_alias='"+acdateOrderM.getOwnerAlias()+
				"',m.owner_addr='"+acdateOrderM.getOwnerAddr()+
				"',m.owner_linkman='"+acdateOrderM.getOwnerLinkman()+
				"',m.owner_phone='"+acdateOrderM.getOwnerPhone()+
				"',m.cust_alias='"+acdateOrderM.getCustAlias()+
				"',m.cust_addr='"+acdateOrderM.getCustAddr()+
			    "',m.cust_linkman='"+acdateOrderM.getCustLinkman()+
			    "',m.cust_phone='"+acdateOrderM.getCustPhone()+"'";
				 
		String sql1=",m.status='"+flag+"'";
		
		String sql2=" where m.order_no='"+acdateOrderM.getOrderNo()+"'"; 
		
		if(flag.equals("10")){
			sql=sql+sql2;
		}else{
			sql=sql+sql1+sql2;
		}
		
		this.genDao.updateBySql(sql);
		
		/////////////////////////////////////////////////////////////////////
		
		String sqliam="update if_acorder_m m set m.owner_alias='"+acdateOrderM.getOwnerAlias()+
				"',m.owner_addr='"+acdateOrderM.getOwnerAddr()+
				"',m.owner_linkman='"+acdateOrderM.getOwnerLinkman()+
				"',m.owner_phone='"+acdateOrderM.getOwnerPhone()+
				"',m.cust_alias='"+acdateOrderM.getCustAlias()+
				"',m.cust_addr='"+acdateOrderM.getCustAddr()+
			    "',m.cust_linkman='"+acdateOrderM.getCustLinkman()+
			    "',m.cust_phone='"+acdateOrderM.getCustPhone()+"'";
				 
		String sqliam1=",m.status='"+flag+"'";
		
		String sqliam2=" where m.order_no='"+acdateOrderM.getOrderNo()+"'"; 
		
		if(flag.equals("10")){
			sqliam=sqliam+sqliam2;
		}else{
			sqliam=sqliam+sqliam1+sqliam2;
		}
		
		this.genDao.updateBySql(sqliam);
		
		
		//////////////////////////////////////////////////////////////////////
		if(flag.equals("10")){
			String delsql="delete acdata_order_d where order_no='"+acdateOrderM.getOrderNo()+"'";
			genDao.updateBySql(delsql);
			
			this.genDao.saveList(list);
		}
		
		
		
		
		String updateSql="";
		if(flag.equals("12")){
			updateSql="update IF_ACOrder_D iad "+
		              " set in_qty = (select in_qty from acdata_order_d aod where iad.order_no = aod.order_no and iad.barcode_no = aod.barcode_no) "+
                      " where iad.order_no ='"+acdateOrderM.getOrderNo()+"'";
		}else if(flag.equals("13")){
			updateSql="update IF_ACOrder_D iad "+
		              " set sign_qty=(select sign_qty from acdata_order_d aod where iad.order_no = aod.order_no and iad.barcode_no = aod.barcode_no) "+
                      " where iad.order_no ='"+acdateOrderM.getOrderNo()+"'";
		}
		if(updateSql!="" &&updateSql!=null){
			this.genDao.updateBySql(updateSql);	
		}
		return new MsgRes(true, "数据保存成功！",flag);
	}

}
