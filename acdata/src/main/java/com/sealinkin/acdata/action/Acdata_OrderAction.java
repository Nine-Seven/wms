package com.sealinkin.acdata.action;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.service.IAcdata_OrderService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public class Acdata_OrderAction extends CommAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAcdata_OrderService acdata_OrderImpl;
	private String strQuery;
	private String strOrderNo;
	private String orderD;
	private String orderM;
	private String flag;
	
	//获取投档信息
	public void getOrderMList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Acdata_OrderMModel> list = acdata_OrderImpl.getOrderMList(
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
             e.printStackTrace();
		}
	}

	//获取商品明细
	public void getOrderDList(){
		try{
			List<Acdata_OrderDModel> list = acdata_OrderImpl.getOrderDList(strOrderNo);
			super.writeArray(list);
		}catch (Exception e) {
	            e.printStackTrace();
		}
	}
	
	//保存或修改
	public void saveOrUpdate(){
		try {
			MsgRes msg=acdata_OrderImpl.saveOrUpdate(this.getOrderD(),this.getOrderM(), this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	
	public IAcdata_OrderService getAcdata_OrderImpl() {
		return acdata_OrderImpl;
	}

	public void setAcdata_OrderImpl(IAcdata_OrderService acdata_OrderImpl) {
		this.acdata_OrderImpl = acdata_OrderImpl;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrOrderNo() {
		return strOrderNo;
	}

	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}

	public String getOrderD() {
		return orderD;
	}

	public void setOrderD(String orderD) {
		this.orderD = orderD;
	}

	public String getOrderM() {
		return orderM;
	}

	public void setOrderM(String orderM) {
		this.orderM = orderM;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}	
}
