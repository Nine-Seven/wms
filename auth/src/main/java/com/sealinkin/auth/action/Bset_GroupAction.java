package com.sealinkin.auth.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.bset.model.Bset_GroupModel;
import com.sealinkin.bset.model.Bset_Printer_GroupModel;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.auth.service.IBset_GroupService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bset_GroupAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBset_GroupService bset_groupImpl;
	private String str;
	private String strQuery;
	private String wheresql;
	private Integer requestFlag = 1;//1：查询2：导出
	private String warehouseNo;
	private String printerGroupNo;
	private String printerNo;
	private String printerType;

	public void getBset_GroupList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_GroupModel> list=bset_groupImpl.getBset_GroupList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strQuery, pageBo, requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				String title = "打印机组与打印机关系维护";
				String[] threads = new String[]{"sheet1","打印机组与打印机关系维护",
						"warehouseNo,printerGroupNo,printerGroupName",
						"仓别,打印机群组代码,打印机群组名称"};
				commExportAction(title, threads, map, list.getRootList());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBset_GroupList2(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_GroupModel> list=bset_groupImpl.getBset_GroupList2(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getBset_Printer_GroupList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_Printer_GroupModel> list=bset_groupImpl.getBset_Printer_GroupList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBdef_DefPrinterList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefprinterModel> list=bset_groupImpl.getBdef_DefPrinterList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), wheresql,str,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveOrUpdateGroup(){
		try {
			bset_groupImpl.saveOrUpdateGroup(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}

	public void savePrinter_Group(){
		try {
			bset_groupImpl.savePrinter_Group(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}
	
	public void deletePrinter_Group(){
		try {
			bset_groupImpl.deletePrinter_Group(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),warehouseNo, printerGroupNo, printerNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBset_Printer_WorkstationList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_Printer_WorkstationModel> list=bset_groupImpl.getBset_Printer_WorkstationList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBset_GroupComboList(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bset_groupImpl.getBset_GroupComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					"", 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkBset_GroupCombo(){
		try {
			String flag=bset_groupImpl.checkBset_GroupCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),printerGroupNo);
			super.writeStr(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkPrinterGroupNo(){
		try {
			String no=bset_groupImpl.checkPrinterGroupNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), printerGroupNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//检查打印机组是否有相同来类型
	public void checkPrinterType(){
		try 
		{
			List<String> list = bset_groupImpl.checkPrinterType(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getWarehouseNo(),this.getPrinterGroupNo(),this.getPrinterType());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getPrinterGroupInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bset_groupImpl.getPrinterGroupInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),					
					str,strQuery,wheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void setBset_groupImpl(IBset_GroupService bsetGroupImpl) {
		bset_groupImpl = bsetGroupImpl;
	}
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getPrinterGroupNo() {
		return printerGroupNo;
	}
	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	public String getPrinterNo() {
		return printerNo;
	}
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}
	
	
}
