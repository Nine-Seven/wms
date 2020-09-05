package com.sealinkin.odata.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.po.Cost_Formulaset;
import com.sealinkin.dao.service.IGenericManager;

import com.sealinkin.odata.model.Odata_CarReceiptModel;
import com.sealinkin.odata.po.Odata_CarReceipt;
import com.sealinkin.odata.service.IOdata_CarReceiptService;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_CarReceiptImpl implements IOdata_CarReceiptService{
		
	private IGenericManager genDao;
	private IOdata_OutstockM odata_OutstockMImpl;
	
	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	public IOdata_OutstockM getOdata_OutstockMImpl() {
		return odata_OutstockMImpl;
	}
	public void setOdata_OutstockMImpl(IOdata_OutstockM odata_OutstockMImpl) {
		this.odata_OutstockMImpl = odata_OutstockMImpl;
	}
	//取配送物流箱信息列表
	@Override
	public ExtListDataBo<Odata_CarReceiptModel> getCarReceiptList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String queryStr, PageBo pageBo)
			throws Exception {
		String sql="select a.enterprise_no,a.warehouse_no,a.owner_no," +
				          "a.wave_no,a.deliver_no,a.cust_no," +
				          "a.deliver_box,a.back_box,a.car_no," +
				          "a.driver_worker,a.status,a.rgst_name," +
				          "a.rgst_date,a.updt_name,a.updt_date, " +
				          "b.owner_name,c.cust_name," +
						  "'['|| ltrim(c.cust_no)||']'||c.cust_name custNoText, " +
						  "'['|| ltrim(a.status)||']'||f_get_fieldtext('ODATA_CAR_RECEIPT','STATUS',a.status) statusText " +
				     "from odata_car_receipt a ,bdef_defowner b,bdef_defcust c " +
				    "where a.enterprise_no=b.enterprise_no " +
				      "and a.owner_no=b.owner_no " +
				      "and a.enterprise_no=c.enterprise_no " +
				      "and a.owner_no=c.owner_no " +
				      "and a.cust_no=c.cust_no " +
				      "and a.enterprise_no='"+strEnterpriseNo+"' " +
			          "and a.warehouse_no='"+strWarehouseNo+"' " +
			          "and a.owner_no in("+strWorkerOwner+")" ;				
        
        if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
        sql=sql+" order by a.owner_no,a.wave_no,a.deliver_no desc ";
		String totsql = "select count(*) from (" + sql + ") " ;	
		List<Odata_CarReceiptModel> list = genDao.getListByNativeSql(sql,Odata_CarReceiptModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Odata_CarReceiptModel> extListBo= new ExtListDataBo<Odata_CarReceiptModel>(list, count);
        return extListBo;
	}
   //保存
	@Override
	public MsgRes save(String queryStr) throws Exception {
		Odata_CarReceipt carReceipt=(Odata_CarReceipt)JSON.parseObject(queryStr,Odata_CarReceipt.class);
		genDao.saveOrUpdateObj(carReceipt);	
		return new MsgRes(true, "数据保存成功！", "");
	}
}
