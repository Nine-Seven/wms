package com.sealinkin.bdef.action;


import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Pntdef_PrinterGrpgatherModel;
import com.sealinkin.bdef.model.Pntset_GrpgatherPrinterGroupModel;
import com.sealinkin.bdef.service.IPntdef_PrinterGrpgatherService;
import com.sealinkin.bset.model.Bset_GroupModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Pntdef_PrinterGrpgatherAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IPntdef_PrinterGrpgatherService pntdef_PrinterGrpgatherImpl;
	private String str;
	private String strQuery;
	private String wheresql;
	private Integer requestFlag = 1;//1：查询2：导出
	private String warehouseNo;
	private String printerGroupNo;
	private String grpgatherNo;

	//获取打印机群组信息
	public void getPntdefPrinterGrpgatherList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Pntdef_PrinterGrpgatherModel> list=pntdef_PrinterGrpgatherImpl.getPntdefPrinterGrpgatherList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strQuery, pageBo, requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				String title = "打印机群组与打印机组关系维护";
				String[] threads = new String[]{"sheet1","打印机群组与打印机组关系维护",
						"warehouseNo,grpgatherNo,grpgatherName",
						"仓别,打印机群组代码,打印机群组名称"};
				commExportAction(title, threads, map, list.getRootList());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取打印机组信息列表
	public void getBset_GroupList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_GroupModel> list=pntdef_PrinterGrpgatherImpl.getBset_GroupList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), wheresql,str, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//打印机群组列表
	public void getPntsetGrpgatherPrinterGroupList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Pntset_GrpgatherPrinterGroupModel> list=pntdef_PrinterGrpgatherImpl.getPntsetGrpgatherPrinterGroupList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),str, wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存或修改打印机群组信息
	public void saveOrUpdateGroup(){
		try {
			pntdef_PrinterGrpgatherImpl.saveOrUpdateGroup(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//群组添加打印机组
	public void savePrinter_Group(){
		try {
			pntdef_PrinterGrpgatherImpl.savePrinter_Group(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}
	//群组移除打印机组
	public void deletePrinter_Group(){
		try {
			pntdef_PrinterGrpgatherImpl.deletePrinter_Group(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),warehouseNo,grpgatherNo, printerGroupNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//校验打印机群组是否已存在
	public void checkGrpgatherNo(){
		try {
			String no=pntdef_PrinterGrpgatherImpl.checkGrpgatherNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), grpgatherNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getPrinterGroupInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=pntdef_PrinterGrpgatherImpl.getPrinterGroupInfo(
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
	public IPntdef_PrinterGrpgatherService getPntdef_PrinterGrpgatherImpl() {
		return pntdef_PrinterGrpgatherImpl;
	}

	public void setPntdef_PrinterGrpgatherImpl(
			IPntdef_PrinterGrpgatherService pntdef_PrinterGrpgatherImpl) {
		this.pntdef_PrinterGrpgatherImpl = pntdef_PrinterGrpgatherImpl;
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

	public String getGrpgatherNo() {
		return grpgatherNo;
	}

	public void setGrpgatherNo(String grpgatherNo) {
		this.grpgatherNo = grpgatherNo;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}	
	
}
