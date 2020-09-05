package com.sealinkin.bdef.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.oset.model.Oset_LineCustModel;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.bdef.service.IOset_DefLineService;

public class Oset_DefLineAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private IOset_DefLineService oset_DefLineImpl;
	private String wheresql;
	private String str;
	private String warehouseNo;
	private String lineNo;
	private String custNo;
	private String strQuery;
	private Integer requestFlag = 1;//1：查询2：导出
	private String lineSeqNo;//路顺

	public void getOset_DefLine()throws Exception{
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Oset_DeflineModel> list=oset_DefLineImpl.getDefLine(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo,requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				//map.put("cdate", "yyyy-MM-dd");
				String title = "线路资料";
				String[] threads = new String[]{"sheet1","线路资料",
						"lineNo,delivertypeText,transporttypeText,lineName,lineFname",
						"线路代码,配送方式,运输方式,线路名称,线路全名"
						};
				commExportAction(title, threads, map, list.getRootList());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void saveOset_DefLine()throws Exception{
		try {
			oset_DefLineImpl.saveOset_DefLine(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void deleteOset_DefLine()throws Exception{
		try {
			oset_DefLineImpl.deleteOset_DefLine(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),warehouseNo, lineNo);
			super.writeObj(new MsgRes(true, "deleteSuccess", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void getOset_Line_Cust()throws Exception{
		try {
			
			ExtListDataBo<Oset_LineCustModel> list=oset_DefLineImpl.getLineCust(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void getCust()throws Exception{
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Oset_LineCustModel> list=oset_DefLineImpl.getCust(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), pageBo,super.getMdBdef_DefWorker().getWorkerOwner(),strQuery);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void saveOset_Line_Cust()throws Exception{
		try {
			oset_DefLineImpl.saveOset_Line_Cust(str);
			super.writeObj(new MsgRes(true, "saveOrUpdateSuccess", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void deleteOSet_Line_Cust()throws Exception{
		try {
			oset_DefLineImpl.deleteOSet_Line_Cust(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					warehouseNo, lineNo, custNo);
			super.writeObj(new MsgRes(true,"deleteLine_CustSuccess",""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void checkLineNo()throws Exception{
		try {
			String no=oset_DefLineImpl.checkLineNo(
			super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
			super.getMdBdef_DefWorker().getWarehouseNo(), lineNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	public void updateSearchD()throws Exception{
		try {
			MsgRes msg = oset_DefLineImpl.updateSearchD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					lineNo, custNo,lineSeqNo,str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	public void setOset_DefLineImpl(IOset_DefLineService osetDefLineImpl) {
		oset_DefLineImpl = osetDefLineImpl;
	}
	
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
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

	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getLineSeqNo() {
		return lineSeqNo;
	}

	public void setLineSeqNo(String lineSeqNo) {
		this.lineSeqNo = lineSeqNo;
	}
	
}
