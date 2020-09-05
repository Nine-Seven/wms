package com.sealinkin.acdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.acdata.model.Acdata_InstockDModel;
import com.sealinkin.acdata.model.Acdata_InstockMModel;
import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.po.Acdata_InstockD;
import com.sealinkin.acdata.po.Acdata_InstockM;
import com.sealinkin.acdata.service.Acdata_InStockService;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Acdata_InStockServiceImpl implements Acdata_InStockService{

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//��ȡ�����list
	public ExtListDataBo<Acdata_InstockMModel> getAcdataInstockMList(String warehouseNo,String queryStr,PageBo pageBo) {
		String sql=" select a.wareHouse_no,a.instock_no,a.order_no,a.source_no,a.owner_alias,a.cust_alias,a.remark,a.status, "+
				"f_get_fieldtext('N','STATUS',a.status) statusText "+
				"from ACDATA_INSTOCK_M a " +
				"where a.wareHouse_no='"+warehouseNo+"' ";
		String totsql="select count(1) " +
				"from ACDATA_INSTOCK_M a " +
				"where a.wareHouse_no='"+warehouseNo+"'";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" order by a.instock_no desc ";
		List<Acdata_InstockMModel> list = genDao.getListByNativeSql(sql,Acdata_InstockMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Acdata_InstockMModel> extListBo= new ExtListDataBo<Acdata_InstockMModel>(list, count);
		return extListBo;
	}
	//��ȡ�ӱ��list
	@Override
	public ExtListDataBo<Acdata_InstockDModel> getAcdataInstockDList(
			String wheresql,PageBo pageBo) {
		String sql="select a.WAREHOUSE_NO,a.INSTOCK_NO,a.ARTICLE_NAME,a.BARCODE_NO,a.IN_QTY,a.IN_WT,a.IN_VL,a.ISTOCK_QTY,a.ISTOCK_WT,a.ISTOCK_VL,a.REMARK from ACDATA_INSTOCK_D a " +
				"where a.instock_no='"+wheresql+"'";
		List<Acdata_InstockDModel> list = genDao.getListByNativeSql(sql,Acdata_InstockDModel.class,pageBo.getStart(), pageBo.getPagesize());
		ExtListDataBo<Acdata_InstockDModel> extListBo= new ExtListDataBo<Acdata_InstockDModel>(list, 0);
		return extListBo;
	}
	//��ȡ����ѡ���list
	@Override
	public ExtListDataBo<Acdata_OrderMModel> getAcdataCheckSourceNoList(
			String queryStr, PageBo pageBo) {
		String sql="select a.order_no,a.source_no,a.owner_alias,a.cust_alias "+
				"from ACDATA_ORDER_M a where status<>13";
		String totsql="select count(1) " +
				"from ACDATA_ORDER_M where status<>13";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		List<Acdata_OrderMModel> list = genDao.getListByNativeSql(sql,Acdata_OrderMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Acdata_OrderMModel> extListBo= new ExtListDataBo<Acdata_OrderMModel>(list, count);
		return extListBo;
	}
	//����������
	@Override
	public List<Acdata_OrderMModel> queryAcdataSImport(String strWheresql)
			throws Exception {
		String strSql="";
		strSql="select a.source_no "+
				"from ACDATA_ORDER_M a where a.source_no like '%"+strWheresql+"%'" +
				" and a.status<>13";	
		List list=genDao.getListByNativeSql(strSql,Acdata_OrderMModel.class);
		return (List<Acdata_OrderMModel>) list;
	}
	//�������Ļ������Ż�ȡ�ӵ�ͷ��
	@Override
	public List<Acdata_OrderMModel> queryAcdataOrderMList(String strSourceNo)
			throws Exception {
		String strSql="";
		strSql="select a.order_no,a.source_no,a.owner_alias,a.cust_alias "+
				"from ACDATA_ORDER_M a where a.source_no = '"+strSourceNo+"'";	
		List list=genDao.getListByNativeSql(strSql,Acdata_OrderMModel.class);
		return list;
	}
	@Override
	public List<Acdata_OrderDModel> getAcdataOrderDList(String strOrderNo) {
		String strSql="select d.order_no,d.article_name,d.barcode_no," +
				"d.order_qty,d.order_wt,d.order_vl," +
				"d.in_qty,d.in_wt,d.in_vl," +
				"d.sign_qty,d.sign_wt,d.sign_vl," +
				"d.lost_qty "+
				"from acdata_order_d d "+
				"where d.order_no = '"+strOrderNo+"'" +
				"order by d.article_name";
		List<Acdata_OrderDModel> list = genDao.getListByNativeSql(strSql,Acdata_OrderDModel.class);	
		return list;
	}
	//����
	@Override
	public MsgRes save(String jsonMaster, String jsonDetail) throws Exception {
		Acdata_InstockM acdataInstockM=(Acdata_InstockM)JSON.parseObject(jsonMaster,Acdata_InstockM.class);
		List<Acdata_InstockD> acdataInstockD=JSON.parseArray(jsonDetail,Acdata_InstockD.class);
		acdataInstockM.setUpdtDate(null);
		acdataInstockM.setUpdtName(null);
		String instockNo = "";
		if(acdataInstockM.getId().getInstockNo().equals("����ʱ�Զ����")){

			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			inList.add(acdataInstockD.get(0).getId().getWarehouseNo());
			inList.add(DocumentTypeModel.ACDATAAC);
			outList.add("S");
			outList.add("S");
			resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
			instockNo=resultList.get(0).toString();
			acdataInstockM.getId().setInstockNo(instockNo);
			for(Acdata_InstockD d:acdataInstockD){
				d.getId().setInstockNo(instockNo);
			}	
		}
		this.genDao.saveOrUpdateObj(acdataInstockM);
		this.genDao.saveList(acdataInstockD);
		return new MsgRes(true, "����ɹ���", instockNo);
	}
	//���
	@Override
	public MsgRes send(String strWarehouseNo,String strOrderNo,String strSourceNo,String strInstockNo) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		inList.add(strWarehouseNo);
		inList.add(strOrderNo);
		inList.add(strSourceNo);
		inList.add(strInstockNo);
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "pkobj_acdata.proc_ac_instock_affirm");
		return new MsgRes(true, "��˳ɹ���", resultList);
	}
	//�޸�
	@Override
	public MsgRes edit(String strInstockNo, String strArticleName,String strBarcodeNo,String strIstockQty,
			String strIstockWt, String strIstockVl) throws Exception {
		String wheresql1[]=strArticleName.split(",");
		String wheresql2[]=strBarcodeNo.split(",");
		String wheresql3[]=strIstockQty.split(",");
		String wheresql4[]=strIstockWt.split(",");
		String wheresql5[]=strIstockVl.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="update acdata_instock_d d " +
					"set d.istock_qty='"+wheresql3[i].trim()+"', "+
					"d.istock_wt='"+wheresql4[i].trim()+"', "+
					"d.istock_vl='"+wheresql5[i].trim()+"' "+
					"where d.instock_no='"+strInstockNo+"' "+
					"and d.article_name='"+wheresql1[i].trim()+"' "+
					"and d.barcode_no='"+wheresql2[i].trim()+"' ";
			genDao.updateBySql(sql);
		}
		return new MsgRes(true, "�޸ĳɹ���", "");
	}
}
