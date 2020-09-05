package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_OJComfirmService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_OJComfirmImpl implements IOdata_OJComfirmService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager
			genDao) {
		this.genDao = genDao;
	}
	

	/**
	 * 出货自提确认头档
	 */
	@Override
	public List<Odata_ExpMModel> getOdata_OJComfirmM(
			String strEnterpriseNo, String strWarehouseNo, String strStr)
			throws Exception {
		String sql ="select  distinct mm.exp_type,mm.cust_no,t.cust_name,mm.sourceexp_no," +
				"mm.status ,f_get_fieldtext('ODATA_EXP_M', 'STATUS',mm.status ) as statusText " +
				"from stock_label_m m,odata_exp_m mm,bdef_defcust t  " + 
				"where m.enterprise_no=mm.enterprise_no " +
				"and m.warehouse_no=mm.warehouse_no " +
				" and (m.deliver_obj=mm.exp_no or m.deliver_obj=mm.cust_no) " +
				"and m.cust_no=mm.cust_no and mm.cust_no=t.cust_no " +
				"and mm.enterprise_no=t.enterprise_no " +
				"and mm.owner_no=t.owner_no " +
				"and m.status='A0' AND mm.exp_type='OJ' " +
				"and m.enterprise_no='"+strEnterpriseNo+"' " +
				"and m.warehouse_no='"+strWarehouseNo+"' ";
		if(strStr!=null && !strStr.equals("") ){
			String ws=CommUtil.covtCollectionToWhereSql(strStr);
			sql=sql+ws;
		}
		
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql, Odata_ExpMModel.class);
		return list;
	}
	
	/**
	 * 获取自提明细数据
	 */
	@Override
	public List<Odata_ExpDModel> getOdata_OJComfirmDComfirm(
			String strEnterpriseNo, String strWarehouseNo, String strQuery)
			throws Exception {
		String sql = "select m.label_no,mm.sourceexp_no," +
				"d.article_no,e.article_name,e.barcode,D.PACKING_QTY," +
				
				"trunc(sum(D.QTY) / d.packing_qty) as realBox,"+
				"trunc(mod(sum(D.QTY), d.packing_qty) / e.QMIN_OPERATE_PACKING) as realQmin,"+
				"(sum(D.QTY) - trunc(sum(D.QTY)/d.packing_qty) * d.packing_qty - trunc(mod(sum(D.QTY),d.packing_qty)/e.QMIN_OPERATE_PACKING) * e.QMIN_OPERATE_PACKING) as realDis,"+
				
				///"nvl(pk.packing_unit, decode(d.packing_qty,e.qmin_operate_packing,e.qmin_operate_packing_unit,e.unit)) packing_unit," +
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
                "f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,e.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,e.unit_packing) Unit,"+
                "f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
                "f_get_spec(d.enterprise_no,d.owner_no,d.article_no,e.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(d.enterprise_no,d.owner_no,d.article_no,e.unit_packing) spec,"+
				"sum(D.QTY) as realQty,e.owner_article_no " +
				"from stock_label_m m,stock_label_d d,odata_exp_m mm," +
				"bdef_defarticle e " +
				"where m.enterprise_no=d.enterprise_no and  m.warehouse_no=d.warehouse_no "+ 
				"and m.container_no=d.container_no and mm.exp_no=d.exp_no " +
			//	"and (d.deliver_obj=mm.exp_no or d.deliver_obj=mm.cust_no) " +
				"and mm.wave_no=m.wave_no " +
				"and d.enterprise_no=mm.enterprise_no and d.warehouse_no=mm.warehouse_no "+
				"and mm.sourceexp_no='"+strQuery+"' and d.enterprise_no='"+strEnterpriseNo+"' " +
				"and d.warehouse_no='"+strWarehouseNo+"' and d.enterprise_no=e.enterprise_no " +
				"and d.article_no=e.article_no and d.status='A0' "+
		" group by  m.label_no, mm.sourceexp_no,mm.exp_no, d.article_no, e.article_name, e.barcode,d.enterprise_no, d.owner_no, e.unit_packing, "+
	     "   D.PACKING_QTY, e.QMIN_OPERATE_PACKING,e.owner_article_no ";
		List<Odata_ExpDModel> list = genDao.getListByNativeSql(sql, Odata_ExpDModel.class);
		return list;
	}
	//确认
	@Override
	public MsgRes tscOComfirm(String strEnterpriseNo, String strWarehouseNo,
			String workerNo,String workSpaceNo,String strQuery) throws Exception {
		String ip = HttpService.getIpAddr();
		List outList=new ArrayList();
		//取车辆编号
		String sql="select m.car_no from bdef_defcar m where rownum=1";
		List<String> carNo = genDao.getListByNativeSql(sql);
		
		outList.add("S");
		outList.add("S");
		List resultList=new ArrayList();
		List inList=new ArrayList();
			
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strQuery);//strDeliverObj
		inList.add("N");//strShipperNo
		inList.add("N");//strLineNo
		inList.add(carNo.get(0));//strCarNo
		inList.add(workerNo);//strUserId
		inList.add(workSpaceNo);//strDockNo
		inList.add("N");//strDivideTrunk
		inList.add("N");//strcarPlanNo
		inList.add("N");//strSealNo
		inList.add("3");//strLoadType
		inList.add(workerNo);//strPaperUserId
		inList.add(ip);//tmpID
		
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_odata_CloseCar_One");

		if(resultList.get(1).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(1).toString());
		}	
    	
		String strLoadProposeNo="N";
		List outList1=new ArrayList();
		outList1.add("S");
		outList1.add("S");
		List resultList1=new ArrayList();
	
		if(!strLoadProposeNo.equals(resultList.get(0).toString()))
		{
			strLoadProposeNo=resultList.get(0).toString();
			
			List inList1=new ArrayList();
			inList1.add(strEnterpriseNo);
			inList1.add(strWarehouseNo);
			inList1.add(strLoadProposeNo);
			inList1.add(workerNo);
			inList1.add(workerNo);
			resultList1=genDao.execProc(inList1, outList1, "PKLG_ODATA_DELIVER.P_odata_deliver");
			System.out.println(resultList1);
			if(resultList1.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList1.get(1).toString());
			}
		}			
	
	   return new MsgRes(true,"确认成功","");
	
	
	}

}
