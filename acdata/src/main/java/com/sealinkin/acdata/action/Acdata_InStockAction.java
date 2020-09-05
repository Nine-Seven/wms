package com.sealinkin.acdata.action;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_InstockDModel;
import com.sealinkin.acdata.model.Acdata_InstockMModel;
import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.service.Acdata_InStockService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public class Acdata_InStockAction extends CommAction{

	private static final long serialVersionUID = -2931588073741422208L;
	
	private Acdata_InStockService acdata_InStockServiceImpl;
	private String strQuery;
	private String wheresql;
	private String strWheresql;
	private String strWarehouseNo;
	private String strInstockNo;
	private String strOrderNo;
	private String strSourceNo;
	private String strArticleName;
	private String  strBarcodeNo;
	private String strIstockQty;
	private String strIstockWt;
	private String strIstockVl;
	private String jsonMaster;
	private String jsonDetail;
	// ��ȡ�ӱ��list
	public void getAcdataInstockDList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Acdata_InstockDModel> list = acdata_InStockServiceImpl.getAcdataInstockDList(
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
             e.printStackTrace();
		}
	}
	//�������list
	public void getAcdataInstockMList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Acdata_InstockMModel> list = acdata_InStockServiceImpl.getAcdataInstockMList(
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
             e.printStackTrace();
		}
	}
	//�񵥺�ѡ���list
	public void getAcdataCheckSourceNoList(){
			try{
				PageBo pageBo=new PageBo(getStart(),getLimit());
				ExtListDataBo<Acdata_OrderMModel> list = acdata_InStockServiceImpl.getAcdataCheckSourceNoList(
						strQuery, pageBo);
				super.writeStr(covtObjectToJson(list));
			}catch (Exception e) {
	             e.printStackTrace();
			}
		}		
	//���������ŵ�list
	public void queryAcdataSImport(){
		    try{
				List<Acdata_OrderMModel> list = acdata_InStockServiceImpl.queryAcdataSImport(strWheresql);
				super.writeArray(list);
			}catch (Exception e) {
			     e.printStackTrace();
			}
		}
	//�������Ļ������Ż�ȡ�ӵ�ͷ��
	public void queryAcdataOrderMList(){
			try{
				List<Acdata_OrderMModel> list = acdata_InStockServiceImpl.queryAcdataOrderMList(strSourceNo);
					super.writeArray(list);
				}catch (Exception e) {
			            e.printStackTrace();
				}
			}	
	// ��ȡ�ӵ�����ϸ
	public void getAcdataOrderDList(){
			try{
				List<Acdata_OrderDModel> list = acdata_InStockServiceImpl.getAcdataOrderDList(strOrderNo);
				super.writeArray(list);
			}catch (Exception e) {
		            e.printStackTrace();
			}
		}	
	//����
	public void save(){
			try {
				MsgRes msg=acdata_InStockServiceImpl.save(jsonMaster,jsonDetail) ;
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//���
	public void send(){
			try {
				MsgRes msg=acdata_InStockServiceImpl.send(strWarehouseNo,strOrderNo, strSourceNo, strInstockNo) ;
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//�޸�
	public void edit(){
			try {
				MsgRes msg=acdata_InStockServiceImpl.edit(strInstockNo,strArticleName,strBarcodeNo,strIstockQty, strIstockWt, strIstockVl) ;
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	
	public Acdata_InStockService getAcdata_InStockServiceImpl() {
			return acdata_InStockServiceImpl;
		}
	public void setAcdata_InStockServiceImpl(
			Acdata_InStockService acdata_InStockServiceImpl) {
			this.acdata_InStockServiceImpl = acdata_InStockServiceImpl;
		}
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrWarehouseNo() {
		return strWarehouseNo;
	}
	public void setStrWarehouseNo(String strWarehouseNo) {
		this.strWarehouseNo = strWarehouseNo;
	}
	public String getStrInstockNo() {
		return strInstockNo;
	}
	public void setStrInstockNo(String strInstockNo) {
		this.strInstockNo = strInstockNo;
	}
	public String getStrOrderNo() {
		return strOrderNo;
	}
	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}	
	public String getStrSourceNo() {
		return strSourceNo;
	}
	public void setStrSourceNo(String strSourceNo) {
		this.strSourceNo = strSourceNo;
	}	
	public String getStrArticleName() {
		return strArticleName;
	}
	public void setStrArticleName(String strArticleName) {
		this.strArticleName = strArticleName;
	}
	public String getStrBarcodeNo() {
		return strBarcodeNo;
	}
	public void setStrBarcodeNo(String strBarcodeNo) {
		this.strBarcodeNo = strBarcodeNo;
	}
	public String getStrIstockQty() {
		return strIstockQty;
	}
	public void setStrIstockQty(String strIstockQty) {
		this.strIstockQty = strIstockQty;
	}
	public String getStrIstockWt() {
		return strIstockWt;
	}
	public void setStrIstockWt(String strIstockWt) {
		this.strIstockWt = strIstockWt;
	}
	public String getStrIstockVl() {
		return strIstockVl;
	}
	public void setStrIstockVl(String strIstockVl) {
		this.strIstockVl = strIstockVl;
	}
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}
	
}
