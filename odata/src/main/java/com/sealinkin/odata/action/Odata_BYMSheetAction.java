package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.jk.model.JkBymSheetwarehouseModel;
import com.sealinkin.odata.service.IOdata_BYMSheetService;
import com.sealinkin.util.ExceptionUtil;

public class Odata_BYMSheetAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IOdata_BYMSheetService odata_BYMSheetImpl;
	private String str;
	private String warehouseNo;
	private String sheetNo;
	private String rgstName;
	private String rgstDate;
	private String corpkey;
	private String sheettype;
	
	//获取BYM出货订单
	public void getBYMSheet(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<JkBymSheetwarehouseModel> BYMSheetlist = odata_BYMSheetImpl.getBYMSheet(this.getStr(),pageBo,super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(BYMSheetlist);		
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	//获取登陆人拥有的仓别
	public void getWarehouse(){
		try 
		{
			List<ComboxBo> list = odata_BYMSheetImpl.getWarehouse(super.getMdBdef_DefWorker().getWorkerNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获取所属公司
	public void getCompany(){
		try 
		{
			List<ComboxBo> list = odata_BYMSheetImpl.getCompany();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取单据类型
	public void getsheetTypeOfJK(){
		try 
		{
			List<ComboxBo> list = odata_BYMSheetImpl.getsheetType();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存BYM出货订单
	
	public void saveBYMSheet(){
		try
		{	
			this.odata_BYMSheetImpl.saveBYMSheet(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	
	public void deleteBYMSheet(){
	
		try {
			MsgRes msg = this.odata_BYMSheetImpl.deleteBYMSheet(this.getWarehouseNo(),
					this.getSheetNo(),this.getRgstName(), this.getRgstDate(),this.getCorpkey(),this.getSheettype());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
					.getCacseMessage(e), ""));
		} 
	}
	
	public void createData(){
		try {
			this.odata_BYMSheetImpl.createData(super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(new MsgRes(true, "生成成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "生成失败", ""));
		} 
	}
	
	public void getBymSheetErrorInfo()
	{
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<JkBymSheetwarehouseModel> BYMSheetlist = odata_BYMSheetImpl.getBymSheetErrorInfo(getStr(), 
					pageBo, 
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(BYMSheetlist);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public IOdata_BYMSheetService getOdata_BYMSheetImpl() {
		return odata_BYMSheetImpl;
	}

	public void setOdata_BYMSheetImpl(IOdata_BYMSheetService odata_BYMSheetImpl) {
		this.odata_BYMSheetImpl = odata_BYMSheetImpl;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public String getRgstName() {
		return rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	public String getRgstDate() {
		return rgstDate;
	}

	public void setRgstDate(String rgstDate) {
		this.rgstDate = rgstDate;
	}

	public String getCorpkey() {
		return corpkey;
	}

	public void setCorpkey(String corpkey) {
		this.corpkey = corpkey;
	}

	public String getSheettype() {
		return sheettype;
	}

	public void setSheettype(String sheettype) {
		this.sheettype = sheettype;
	}
	
	
}
