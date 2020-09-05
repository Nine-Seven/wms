package com.sealinkin.acdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.model.Acdata_OutstockDModel;
import com.sealinkin.acdata.model.Acdata_OutstockMModel;
import com.sealinkin.acdata.model.Acdata_StockcontentModel;
import com.sealinkin.acdata.po.Acdata_OutstockD;
import com.sealinkin.acdata.po.Acdata_OutstockM;
import com.sealinkin.acdata.service.Acdata_OutStockService;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Acdata_OutStockServiceImpl implements Acdata_OutStockService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
    //��ȡ�����list
	public ExtListDataBo<Acdata_OutstockMModel> getOut_MList(String warehouseNo,String queryStr,PageBo pageBo) {
		String sql="select t.warehouse_no,t.outstock_no,t.order_no," +
				"t.source_no,t.owner_alias,t.cust_alias," +
				"f_get_fieldtext('N','STATUS',t.status)statusText," +
				"t.remark,t.rgst_name,t.rgst_date,t.updt_name,t.updt_date " +
				"from ACDATA_OUTSTOCK_M t " +
				"where t.warehouse_no='"+warehouseNo+"'";
		String totsql="select count(1) " +
		              "from ACDATA_OUTSTOCK_M t " +
		              "where t.warehouse_no='"+warehouseNo+"'";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" order by t.outstock_no desc ";
		List<Acdata_OutstockMModel> list = genDao.getListByNativeSql(sql,Acdata_OutstockMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Acdata_OutstockMModel> extListBo= new ExtListDataBo<Acdata_OutstockMModel>(list, count);
        return extListBo;
	}
	//��ȡ�ӱ��list
	@Override
	public ExtListDataBo<Acdata_OutstockDModel> getOut_DList(String wheresql,PageBo pageBo) {
		String sql="select d.warehouse_no,d.outstock_no,d.article_name,d.barcode_no," +
				"d.in_qty,d.in_wt,d.in_vl,d.already_qty,d.already_wt,"+
                "d.already_vl,d.ostock_qty,d.ostock_wt,d.ostock_vl,d.remark from ACDATA_OUTSTOCK_D d where"+
				" d.outstock_no='"+wheresql+"'";
		List<Acdata_OutstockDModel> list=genDao.getListByNativeSql(sql, Acdata_OutstockDModel.class, pageBo.getStart(), 1000);
		ExtListDataBo<Acdata_OutstockDModel> extListBo=new ExtListDataBo<Acdata_OutstockDModel>(list,0);
		return extListBo;
	}
	// ��ȡ�ӵ������list
	@Override
	public ExtListDataBo<Acdata_OrderMModel> getOrder_MList(String warehouseNo,
			PageBo pageBo) {
		String sql="select o.order_no," +
				"o.source_no,o.owner_alias," +
				"o.cust_alias,o.status " +
				"from acdata_order_m o,acdata_stockcontent t " +
				"where o.status != 13 " +
				"and o.order_no = t.order_no " +
				"and o.source_no = t.source_no " +
				"and t.warehouse_no='"+warehouseNo+"'";
		List<Acdata_OrderMModel> list=genDao.getListByNativeSql(sql, Acdata_OrderMModel.class, pageBo.getStart(), 1000);
		ExtListDataBo<Acdata_OrderMModel> extListBo=new ExtListDataBo<Acdata_OrderMModel>(list,0);
		return extListBo;
		
	}
	// ��ȡ����ʱ�����
	@Override
	public ExtListDataBo<Acdata_StockcontentModel> getContent_List(
			String warehouseNo, String sourceNo, String orderNo) {
		String sql=" select Article_Name,BarCode_NO,IN_Qty,Qty,IN_wt,IN_VL," +
				"(IN_Qty-Qty) alreadyQty,(IN_wt-wt) alreadyWt,(IN_VL-vl) alreadyVl " +
				"from ACData_StockContent" +
				" where warehouse_no='"+warehouseNo+"' and source_no='"+sourceNo+"' and order_no='"+orderNo+"'";
		List<Acdata_StockcontentModel> list=genDao.getListByNativeSql(sql, Acdata_StockcontentModel.class);
		ExtListDataBo<Acdata_StockcontentModel> extListBo=new ExtListDataBo<Acdata_StockcontentModel>(list,0);
		return extListBo;
	}
	//��������
	@Override
	public MsgRes saveOut(String OUTM, String OUTD) throws Exception {
		Acdata_OutstockM om=(Acdata_OutstockM)JSON.parseObject(OUTM,Acdata_OutstockM.class);
		List<Acdata_OutstockD> list=JSON.parseArray(OUTD,Acdata_OutstockD.class);
		om.setUpdtDate(null);
		om.setUpdtName(null);
		String outStockNO="";
		if(om.getId().getOutstockNo().equals("�����Զ����")){
			List inList2=new ArrayList();
			List outList2=new ArrayList();
			List resultList2=new ArrayList();
			
			inList2.add(list.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.ACDATAAC);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(1).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			outStockNO=resultList2.get(0).toString();
			om.getId().setOutstockNo(outStockNO);
			for(Acdata_OutstockD d:list){
				d.getId().setOutstockNo(outStockNO);
			}
		}
		this.genDao.saveOrUpdateObj(om);
		this.genDao.saveList(list);
		return new MsgRes(true, "��ݱ���ɹ���",outStockNO);
		
	}
	//��ȡ��������
	@Override
	public List<Acdata_OrderMModel> getsourceNo(String warehouseNo,String strSourceNo,String strWheresql) {
		String sql="";
		sql="select m.source_no " +
				"from acdata_order_m m,acdata_stockcontent t " +
				"where m.status != 13 " +
				"and m.order_no = t.order_no " +
				"and m.source_no = t.source_no " +
				"and t.warehouse_no = '"+warehouseNo+"' ";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			sql+=" and m.source_no like '%"+strWheresql+"%'";
		}
		if(strSourceNo!=null && !strSourceNo.equals(""))
		{
			sql=sql+" and m.source_no in('"+strSourceNo+"')";
		}
		List list=genDao.getListByNativeSql(sql, Acdata_OrderMModel.class);
		return (List<Acdata_OrderMModel>)list;
	}
	//��ݻ������Ų�ѯ����
	@Override
	public ExtListDataBo<Acdata_OrderMModel> getOutMBySourceNo(
			String warehouseNo, String sourceNo) {
		String sql="select o.order_no,o.source_no," +
				"o.owner_alias,o.cust_alias,o.status " +
				"from acdata_order_m o " +
				"where o.status != 13 " +
				"and o.source_no='"+sourceNo+"'";
		List<Acdata_OrderMModel> list=genDao.getListByNativeSql(sql, Acdata_OrderMModel.class);
		ExtListDataBo<Acdata_OrderMModel> extListBo=new ExtListDataBo<Acdata_OrderMModel>(list,0);
		return extListBo;
	}
	//�������
	@Override
	public MsgRes saveTrues(String strHouseNo, String strOrderNo,
			String strSourceNo,String strOutStockNO) throws Exception {
		List outList=new ArrayList();
		List inList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(strHouseNo);
		inList.add(strOrderNo);
		inList.add(strSourceNo);
		inList.add(strOutStockNO);
		outList.add("S");
		
		
		resultList=genDao.execProc(inList, outList, "PKOBJ_ACDATA.PROC_AC_OUTSTOCK_AFFIRM");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(Boolean.valueOf(true),
				TipUtil.getTipsByKey("E40204"), null);
	}
}
