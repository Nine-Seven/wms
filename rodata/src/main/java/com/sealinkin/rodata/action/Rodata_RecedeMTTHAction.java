package com.sealinkin.rodata.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_RecedeMModel;
import com.sealinkin.rodata.service.IRodata_RecedeMTTHService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.ExceptionUtil;


public class Rodata_RecedeMTTHAction extends CommAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRodata_RecedeMTTHService rodata_RecedeMTTHImpl;
	private String str;
	private String strQuery;
	private File file;
	private String jsonMaster;
	private String cellNo;
	private String strWheresql;
	private String ownerNo;
	private String recedeNo;
	private String strRecedeNo;
	
	//获取头档（标准版和服装版公用）
	public void getRodata_RecedeM() {
		try {
			ExtListDataBo<Rodata_RecedeMModel> list = rodata_RecedeMTTHImpl.getRodata_RecedeM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strQuery,str);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //获取退货单类型（标准版和服装版公用）
	public void  getRecede_type(){
		try 
		{
			List<ComboxBo> list = rodata_RecedeMTTHImpl.getRecede_type(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 //获取供应商（标准版和服装版公用）
	public void  getSupplierNo(){
		try 
		{
			List<ComboxBo> list = rodata_RecedeMTTHImpl.getSupplierNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//取消订单    7-14
	public void cancelOrder(){
		try {
			MsgRes msg = rodata_RecedeMTTHImpl.cancelOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					strRecedeNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}		
	
	//获取定位次数
	public void getLocateTime(){
		try 
		{
			List<ComboxBo> list = rodata_RecedeMTTHImpl.getLocateTime(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 获得商品缺量信息（标准版和服装版公用）
		public void getRodateRecedeList() {
			try {
				ExtListDataBo<Stock_ContentModel> list = rodata_RecedeMTTHImpl.getRodateRecedeList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						str);
				super.writeStr(covtObjectToJson(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
//定位
		public void send(){
			try {
				MsgRes msg = rodata_RecedeMTTHImpl.send(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					str,
					this.getCellNo());
				super.writeObj(msg);
			} catch (Exception e) {
				super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));// 定位失败！
				e.printStackTrace();
			}
		}
		
	//退单审核
	public void reviewComfir(){
		try {
			MsgRes msg = rodata_RecedeMTTHImpl.tscReviewComfir(
					
				super.getMdBdef_DefWorker().getWorkerNo(),
				super.getMdBdef_DefWorker().getWorkSpaceNo(),
				jsonMaster);
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));// 定位失败！
			e.printStackTrace();
		}
	}
	
	//获取储位
	public void getCdef_DefCellCombo(){
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = rodata_RecedeMTTHImpl.getCdef_DefCellCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), str, strWheresql,0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//校验储位
	public void checkCell(){
		try {
			MsgRes msg = rodata_RecedeMTTHImpl.checkCell(
				super.getMdBdef_DefWorker().getEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				this.getOwnerNo(),
				this.getRecedeNo(),
				this.getCellNo());
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));// 定位失败！
			e.printStackTrace();
		}
	}
	//上传（标准版和服装版公用）
	public void upLoad(){
		try {
			MsgRes msg = rodata_RecedeMTTHImpl.upLoad(
					file,super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
			
	}
	
	
	public IRodata_RecedeMTTHService getRodata_RecedeMTTHImpl() {
		return rodata_RecedeMTTHImpl;
	}
	public void setRodata_RecedeMTTHImpl(
			IRodata_RecedeMTTHService rodata_RecedeMTTHImpl) {
		this.rodata_RecedeMTTHImpl = rodata_RecedeMTTHImpl;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
		public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getRecedeNo() {
		return recedeNo;
	}
	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}
	public String getStrRecedeNo() {
		return strRecedeNo;
	}
	public void setStrRecedeNo(String strRecedeNo) {
		this.strRecedeNo = strRecedeNo;
	}
	
	
}













