/*
 * 出货自提确认
 * hkl
 * 3919
 */

package com.sealinkin.odata.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_OJComfirmService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial","unused"})
public class Odata_OJComfirmAction extends CommAction{
	
	private static final Logger logger = Logger.getLogger(Odata_OJComfirmAction.class);
	private IOdata_OJComfirmService odata_OJComfirmImpl;
	private MsgRes msgRes;
	private String str;
	private String strQuery;
	private String strWheresql;
	private String strWorkerNo;

	
	
	
	/*
	 * 获得自提头档数据
	 *
	 */
	public void getOdata_OJComfirmM()
	{
		try{	
			List<Odata_ExpMModel> list=odata_OJComfirmImpl.getOdata_OJComfirmM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();

		}
	}	
	
	/**
	 * 获取自提明细数据
	 */
	public void getOdata_OJComfirmDComfirm(){
		try{
			List<Odata_ExpDModel> list = odata_OJComfirmImpl.getOdata_OJComfirmDComfirm(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			writeArray(list);
		}catch(Exception e)
		{
			e.printStackTrace();

		}
	}

	/**
	 * 自提确认
	 */
	public void tscOComfirm(){
		try{	
			MsgRes msg=odata_OJComfirmImpl.tscOComfirm(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strQuery);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}


	public IOdata_OJComfirmService getOdata_OJComfirmImpl() {
		return odata_OJComfirmImpl;
	}


	public void setOdata_OJComfirmImpl(IOdata_OJComfirmService odata_OJComfirmImpl) {
		this.odata_OJComfirmImpl = odata_OJComfirmImpl;
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


	public String getStrWheresql() {
		return strWheresql;
	}


	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}


	public String getStrWorkerNo() {
		return strWorkerNo;
	}


	public void setStrWorkerNo(String strWorkerNo) {
		this.strWorkerNo = strWorkerNo;
	}


	public static Logger getLogger() {
		return logger;
	}
	
	


	
}
