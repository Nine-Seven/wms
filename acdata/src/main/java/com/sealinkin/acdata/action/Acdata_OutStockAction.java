package com.sealinkin.acdata.action;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.model.Acdata_OutstockDModel;
import com.sealinkin.acdata.model.Acdata_OutstockMModel;
import com.sealinkin.acdata.model.Acdata_StockcontentModel;
import com.sealinkin.acdata.service.Acdata_OutStockService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Acdata_OutStockAction extends CommAction{

	private static final long serialVersionUID = -2931588073741422208L;
	
	private Acdata_OutStockService acdata_OutStockServiceImpl;
	private String strQuery;
	private String sourceNo;
	private String orderNo;
	private String wheresql;
	private String OUTM;
	private String OUTD;
	private String strSourceNo;
	private String strWheresql;
	private String strOutStockNO;
	// ��ȡ�ӱ��list
	public void getOut_DList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Acdata_OutstockDModel> list = acdata_OutStockServiceImpl.getOut_DList(wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
             e.printStackTrace();
		}
	}
	//�������list
	public void getOut_MList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Acdata_OutstockMModel> list = acdata_OutStockServiceImpl.getOut_MList(
					super.getMdBdef_DefWorker().getWarehouseNo(),
					//super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
             e.printStackTrace();
		}
	}
	
	// ��ȡ�ӵ������list
	public void getOrder_MList() {
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Acdata_OrderMModel> list = acdata_OutStockServiceImpl
					.getOrder_MList(super.getMdBdef_DefWorker()
							.getWarehouseNo(),
					// super.getMdBdef_DefWorker().getWorkerOwner(),
							pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ����ʱ�Ĵӱ�
	public void getContent_List() {
		try {
			ExtListDataBo<Acdata_StockcontentModel> list = acdata_OutStockServiceImpl
					.getContent_List(super.getMdBdef_DefWorker()
							.getWarehouseNo(),
							sourceNo,orderNo
					// super.getMdBdef_DefWorker().getWorkerOwner(),
							);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ��������
	public void saveOut() {
		try {
			MsgRes msg=acdata_OutStockServiceImpl.saveOut(OUTM, OUTD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"����ʧ�ܣ�",""));
		}
	}
	//��ѯ��������
	public void getsourceNo(){
		try 
		{
			List<Acdata_OrderMModel> list=acdata_OutStockServiceImpl.getsourceNo(
					super.getMdBdef_DefWorker().getWarehouseNo(),strSourceNo,strWheresql
					);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	// ��ݻ�����Ż�ȡ�ӵ������
		public void getOutMBySourceNo() {
			try {
				ExtListDataBo<Acdata_OrderMModel> list = acdata_OutStockServiceImpl
						.getOutMBySourceNo(super.getMdBdef_DefWorker()
								.getWarehouseNo(),sourceNo);
				super.writeStr(covtObjectToJson(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//���
		public void saveTrues(){
			try {
				this.acdata_OutStockServiceImpl.saveTrues(super.getMdBdef_DefWorker()
						.getWarehouseNo(),orderNo,sourceNo,strOutStockNO);
				super.writeObj(new MsgRes(true, "��˳ɹ�", ""));
			} catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false, "���ʧ��", ""));
			} 
		}	
		
	

	public Acdata_OutStockService getAcdata_OutStockServiceImpl() {
			return acdata_OutStockServiceImpl;
		}
		public void setAcdata_OutStockServiceImpl(
				Acdata_OutStockService acdata_OutStockServiceImpl) {
			this.acdata_OutStockServiceImpl = acdata_OutStockServiceImpl;
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
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOUTM() {
		return OUTM;
	}
	public void setOUTM(String oUTM) {
		OUTM = oUTM;
	}
	public String getOUTD() {
		return OUTD;
	}
	public void setOUTD(String oUTD) {
		OUTD = oUTD;
	}
	public String getStrSourceNo() {
		return strSourceNo;
	}
	public void setStrSourceNo(String strSourceNo) {
		this.strSourceNo = strSourceNo;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrOutStockNO() {
		return strOutStockNO;
	}
	public void setStrOutStockNO(String strOutStockNO) {
		this.strOutStockNO = strOutStockNO;
	}
	
}
