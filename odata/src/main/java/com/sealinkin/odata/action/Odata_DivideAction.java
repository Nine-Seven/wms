/*
 * 分播回单
 * zhouhuan
 */

package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.odata.model.Odata_DivideMModel;
import com.sealinkin.odata.service.IOdata_DivideService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("serial")
public class Odata_DivideAction extends CommAction{
	//private static final Logger logger = Logger.getLogger(Odata_DivideAction.class);
	private IOdata_DivideService odata_DivideImpl;
	private MsgRes msgRes;
	
	private String str;
	private String strQuery;
	private String strFlag;
	private String updtName;
	private String strWheresql;
	private String strCheckFlag;
	private String strBarcode;
	
	public MsgRes getMsgRes() {
		return msgRes;
	}
	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}
	
	public String GetStr() {
		return str;
	}
	public void setStr(String str) 
	{
		this.str = str;
	}
	
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getUpdtName() {
		return updtName;
	}
	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}
	public IOdata_DivideService getOdata_DivideImpl() {
		return odata_DivideImpl;
	}
	public void setOdata_DivideImpl(IOdata_DivideService odata_DivideImpl) {
		this.odata_DivideImpl = odata_DivideImpl;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	
	public String getStrCheckFlag() {
		return strCheckFlag;
	}
	public void setStrCheckFlag(String strCheckFlag) {
		this.strCheckFlag = strCheckFlag;
	}
	
	
	
	public String getStrBarcode() {
		return strBarcode;
	}
	public void setStrBarcode(String strBarcode) {
		this.strBarcode = strBarcode;
	}
	//取商品条码
	public void  getBarcodeCombo(){
		try 
		{
			List<ComboxBo> list = odata_DivideImpl.getBarcodeCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str, strWheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
     * 分播回单 波次，批号下拉
     *  周欢
     */
	public void getOdata_DivideCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_DivideImpl.getOdata_DivideCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),str,strWheresql,strCheckFlag,0,100);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得分播单头信息
	 * zhouhuan
	 */
	public void getOdata_DivideM()
	{
		try{	
			ExtListDataBo<Odata_DivideMModel> list=odata_DivideImpl.getOdata_DivideM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrQuery(),getStrFlag(),getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得分播回单明细
	 */
	public void getOdata_DivideD()
	{
		try{	
			ExtListDataBo<Odata_DivideDModel> list = odata_DivideImpl.getOdata_DivideD(
				    super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					GetStr(),
				    getStrFlag(),0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * 保存分播回单
	 */
	public void save()
	{
		try {
			MsgRes msg=odata_DivideImpl.save(str,getUpdtName());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			super.writeObj(new MsgRes(false,"保存失败！",e.getMessage()));
			e.printStackTrace();
		}
	}
	//取目的标签号（打印）
	public void printDContainerNo()
	{
		try {
			MsgRes msg=odata_DivideImpl.tscDivideGetNO(str);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			e.printStackTrace();
		}
	}
	/**
	 * 检查不同客户是否有相同标签号
	 */
	public void check()
	{
		try {
			MsgRes msg=odata_DivideImpl.check(super.getMdBdef_DefWorker().getWarehouseNo(),str);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			super.writeObj(new MsgRes(false,"已有客户是使用该标签号！",e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消分播
	 */
	public void tscCancelDivide()
	{
		try {
			MsgRes msg=odata_DivideImpl.tscCancelDivide(str);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			super.writeObj(new MsgRes(false,"取消分播失败！",e.getMessage()));
			e.printStackTrace();
		}
	}
	
}
