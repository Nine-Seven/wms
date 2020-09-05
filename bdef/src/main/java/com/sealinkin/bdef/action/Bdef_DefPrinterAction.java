package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.bdef.service.IBdef_DefPrinterService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;
import net.sf.json.JSONObject;

public class Bdef_DefPrinterAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IBdef_DefPrinterService bdef_defprinterImpl;
	private String str;
	private String strQuery;
	private String wheresql;
	private Integer requestFlag = 1;//1：查询2：导出
	private String printerNo;

	
	public void getPrinterList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefprinterModel> list=bdef_defprinterImpl.getPrinterList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo,requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				//Map<String, String> map = new HashMap<String, String>();
				String title = "打印机资料";
				String[] threads = new String[]{"sheet1","打印机资料",
						"仓别,打印机代码,打印机名称,打印机类型,驱动程序名," +
								"打印机端口,状态",
				"warehouseNo,printerNo,printerName,printertypeText,printerDriver," +
				"printerPort,statusText"};
				List<Object[]> list1 = new ArrayList<>();
				for (Bdef_DefprinterModel model:list.getRootList()) {
					Object[] objects = new Object[7];
					objects[0] = model.getWarehouseNo();
					objects[1] = model.getPrinterNo();
					objects[2] = model.getPrinterName();
					objects[3] = model.getPrintertypeText();
					objects[4] = model.getPrinterDriver();
					objects[5] = model.getPrinterPort();
					objects[6] = model.getStatusText();
					list1.add(objects);
				}
				commExportAction(title, threads, list1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void savePrinter(){
		try {
			bdef_defprinterImpl.saveOrUpdatePrinter(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}
	
	public void checkPrinterNo(){
		try {
			String no=bdef_defprinterImpl.checkPrinterNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), printerNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 填充打印机类型下拉
	 */
	public void getPrinterTypeList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_defprinterImpl.getPrinterTypeList(
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
	public void getPrinterInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_defprinterImpl.getPrinterInfo(
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
	//获取打印机类型
	public void  getTypeList(){
		try 
		{
			List<ComboxBo> list = bdef_defprinterImpl.getTypeList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),				
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery
					);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取状态
	public void  getStatusList(){
		try 
		{
			List<ComboxBo> list = bdef_defprinterImpl.getStatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
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

	public String getPrinterNo() {
		return printerNo;
	}
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}
	
	public void setBdef_defprinterImpl(IBdef_DefPrinterService bdefDefprinterImpl) {
		bdef_defprinterImpl = bdefDefprinterImpl;
	}
	
}
