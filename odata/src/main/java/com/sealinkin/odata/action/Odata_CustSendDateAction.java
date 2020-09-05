/*
 * 出货日期确认
 * hkl
 * 3922
 */

package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_CustSendDateService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial"})
public class Odata_CustSendDateAction extends CommAction{
	
	private static final Logger logger = Logger.getLogger(Odata_CustSendDateAction.class);
	private IOdata_CustSendDateService odata_CustSendDateImpl;
	private MsgRes msgRes;
	private String str;
	private String strQuery;
	private Date custSendDate;
	
	
	
	/*
	 * 获得出货单据信息
	 *
	 */
	public void getOdata_CustSendDate()
	{
		try{	
			ExtListDataBo<Odata_ExpMModel> list=odata_CustSendDateImpl.getOdata_CustSendDate(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,strQuery);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}	
	
	//获取客户下拉
	public void queryCustCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_CustSendDateImpl.queryCustCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	//根据确认日期获取材积
	public void queryVolumn(){
		try{	
			MsgRes msg=odata_CustSendDateImpl.queryVolumn(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	/**
	 * 日期确认
	 */
	public void tscCustSendComfirm(){
		try{	
			MsgRes msg=odata_CustSendDateImpl.tscCustSendComfirm(str,
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}


	public IOdata_CustSendDateService getOdata_CustSendDateImpl() {
		return odata_CustSendDateImpl;
	}

	public void setOdata_CustSendDateImpl(
			IOdata_CustSendDateService odata_CustSendDateImpl) {
		this.odata_CustSendDateImpl = odata_CustSendDateImpl;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}


	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}


	public String getStr() {
		return str;
	}


	public void setStr(String str) {
		this.str = str;
	}


	public String getStrQuery() {
		return strQuery;
	}


	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}


	public Date getCustSendDate() {
		return custSendDate;
	}



	public void setCustSendDate(Date custSendDate) {
		this.custSendDate = custSendDate;
	}



	public static Logger getLogger() {
		return logger;
	}
	
	


	
}
