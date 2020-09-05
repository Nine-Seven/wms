/*
 * 退厂回单
 * zhouhuan
 */
package com.sealinkin.rodata.action;


import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;
import com.sealinkin.rodata.service.IRodata_OutstockMService;
import com.sealinkin.util.ExceptionUtil;


/**
 * @author zhouhuan
 *
 */
public class Rodata_OutstockMAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IRodata_OutstockMService rodata_OutstockMImpl;
	private String str;
	private String outUserId;
	private String strQuery;
	private String strOwnerNo;
	private String strRecedeNo;
	private String strFlag;
	
	/*
	 * 获取退厂回单单头
	 * zhouhuan
	 */
	public void getRodata_OutstockM(){
		try{
			ExtListDataBo<Rodata_OutstockMModel> list = 
					rodata_OutstockMImpl.getRodata_OutstockM(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							getStart(),getLimit(),strQuery);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获取退厂回单明细
	 * zhouhuan
	 */
	public void getRodata_OutstockD(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=rodata_OutstockMImpl.getRodata_OutstockD(
					getStr(),getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 保存退厂回单
	 */
	public void save(){
		try {
			MsgRes msg = rodata_OutstockMImpl.save(
					super.getMdBdef_DefWorker().getWorkerNo(),
					outUserId,getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//保存失败			
		}
	}
	
	/*
	 * 退厂确认 查询头档信息
	 * lich
	 
	public void getRodata_OutstockMComfirm(){
		try{
			ExtListDataBo<Rodata_OutstockMModel> list = 
					rodata_OutstockMImpl.getRodata_OutstockMComfirm(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							getStart(),
							getLimit(),
							strQuery);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 * 退厂确认 查询明细信息
	 * lich
	 
	public void getRodata_OutstockDComfirm(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=rodata_OutstockMImpl.getRodata_OutstockDComfirm(getStr(),
					getStart(),
					getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	 * 退厂确认 货主、供应商……下拉
	 * lich
	 
	public void getRodata_OutstockComfirmCombo(){
		try{
			List<ComboxBo> list=
					rodata_OutstockMImpl.getRodata_OutstockComfirmCombo(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							super.getMdBdef_DefWorker().getWorkerOwner(),
							getStrFlag(),
							getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	 * 退厂确认
	 
	public void tscRoComfirm(){
		try {
			MsgRes msg=new MsgRes();
			
				msg = rodata_OutstockMImpl.tscRoComfirm(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), 
						getStrOwnerNo(), 
						this.getStrRecedeNo(), 
						super.getMdBdef_DefWorker().getWorkerNo(), 
						super.getMdBdef_DefWorker().getWorkSpaceNo());
				
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	*/
	
	public IRodata_OutstockMService getRodata_OutstockMImpl() {
		return rodata_OutstockMImpl;
	}
	public void setRodata_OutstockMImpl(
			IRodata_OutstockMService rodata_OutstockMImpl) {
		this.rodata_OutstockMImpl = rodata_OutstockMImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getOutUserId() {
		return outUserId;
	}
	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrRecedeNo() {
		return strRecedeNo;
	}
	public void setStrRecedeNo(String strRecedeNo) {
		this.strRecedeNo = strRecedeNo;
	}
	
}
