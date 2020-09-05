package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;
import com.sealinkin.odata.service.IOdata_OutstockMReceipt;

/**
 * 拣货回单
 * @author 周欢
 *
 */
public class Odata_OutstockMReceiptAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IOdata_OutstockMReceipt odata_OutstockMReceiptImpl;
	private String str;
	private String strFlag;
	private String strQuery;
	private String strOutstockNo;
	private String strSendFlag;
	private String strPickType;
	private String strCheckFlag;
	private String strB2CYesOrNo;
	
	public IOdata_OutstockMReceipt getOdata_OutstockMReceiptImpl() {
		return odata_OutstockMReceiptImpl;
	}
	public void setOdata_OutstockMReceiptImpl(
			IOdata_OutstockMReceipt odata_OutstockMReceiptImpl) {
		this.odata_OutstockMReceiptImpl = odata_OutstockMReceiptImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrOutstockNo() {
		return strOutstockNo;
	}
	public void setStrOutstockNo(String strOutstockNo) {
		this.strOutstockNo = strOutstockNo;
	}
	public String getStrSendFlag() {
		return strSendFlag;
	}
	public void setStrSendFlag(String strSendFlag) {
		this.strSendFlag = strSendFlag;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrPickType() {
		return strPickType;
	}
	public void setStrPickType(String strPickType) {
		this.strPickType = strPickType;
	}
	public String getStrCheckFlag() {
		return strCheckFlag;
	}
	public void setStrCheckFlag(String strCheckFlag) {
		this.strCheckFlag = strCheckFlag;
	}
	
	
	public String getStrB2CYesOrNo() {
		return strB2CYesOrNo;
	}
	public void setStrB2CYesOrNo(String strB2CYesOrNo) {
		this.strB2CYesOrNo = strB2CYesOrNo;
	}
	/**
	 * 获取拣货回单单据信息
	 * @author 周欢
	 */
	public void getOdata_OutstockM(){
		try{		
			ExtListDataBo<Odata_OutstockMModel> list=odata_OutstockMReceiptImpl.getOdata_OutstockM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStrQuery(),
					strSendFlag,0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取拣货回单商品信息
	 * @author 周欢
	 */
	public void getOdata_OutstockD(){
		try{		
			ExtListDataBo<Odata_OutstockDModel> list=odata_OutstockMReceiptImpl.getOdata_OutstockD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					strSendFlag,0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 拣货回单》回单
     * @author 周欢
     */
	public void save(){
		try{		
			MsgRes msg=odata_OutstockMReceiptImpl.save(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					getStrOutstockNo(),
					getStrPickType(),
					getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"回单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
     * 拣货回单》标签回单
     * @author 周欢
     */
	public void saveLabel(){
		try{		
			MsgRes msg=odata_OutstockMReceiptImpl.saveLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					getStrOutstockNo(),
					getStrPickType(),
					getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"回单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	/**
     * 拣货回单 出货单别、波次……下拉
     * @author 周欢
     */
	public void getOdata_OutstockMReceiptCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_OutstockMReceiptImpl.getOdata_OutstockMReceiptCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					strSendFlag,
					strCheckFlag,strB2CYesOrNo,0,100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 拣货回单》输入标签号码校验事件
     * @author 周欢
     */
	/*public void labelNoEdit(){
		try{		
			System.out.println(super.getMdBdef_DefWorker().getWarehouseNo());
			MsgRes msg=odata_OutstockMReceiptImpl.labelNoEdit(
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr());
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"标签号码校验失败！",e.getMessage()));
			e.printStackTrace();
		}
	}*/
}
