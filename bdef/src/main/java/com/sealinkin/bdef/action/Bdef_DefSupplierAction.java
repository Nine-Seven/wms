package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefsupplierModel;
import com.sealinkin.bdef.service.IBdef_DefSupplierService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bdef_DefSupplierAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private IBdef_DefSupplierService bdef_DefSupplierImpl;
	private String strWheresql;
	private String str;
	private String strQuery;
	private MsgRes msgRes;
	private String ownerNo;
	private String supplierNo;
	private String wheresql;
	private Integer requestFlag = 1;//1：查询2：导出
	
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public void queryBdefDefSupplierCombo()
	{
		try 
		{
			System.out.println("strWheresql:"+strWheresql);
			List<ComboxBo> list = bdef_DefSupplierImpl.queryBdefDefSupplierCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), strWheresql,this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 保存供应商
	 */
	public void saveOrUpdatesupplier(){
		try{	
			System.out.println("str:"+this.getStr());
			MsgRes msg = bdef_DefSupplierImpl.save(getStr(),super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	/*
	 * 获得供应商资料
	 * zhouhuan
	 */
	public void getBdef_defSupplierList(){
		try {
			System.out.println("strQuery:"+strQuery);
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefsupplierModel> list=bdef_DefSupplierImpl.getBdef_DefSupplierList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),strQuery, pageBo,requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				//map.put("cdate", "yyyy-MM-dd");
				String title = "供应商资料";
				String[] threads = new String[]{"sheet1","供应商资料",
		"ownerNo,supplierNo,realSupplierNo,realSupplierName," +
		"supplierName,supplierAlias,supplierAddress1,supplierPhone1," +
		"supplierFax1,supplier1,supplierAddress2,supplierPhone2," +
		"supplierFax2,supplier2,supplierRemark,invoiceNo,invoiceAddr," +
		"invoiceHeader,statusText,unloadflagText,createFlagText,rgstName,rgstDate," +
		"updtName,updtDate,supplierNoteCode",
		"委托业主编码,供应商编码,实体供应商编码," +
		"实体供应商名称,供应商名称,供应商简称,供应商地址1,供应商电话1,供应商传真1,供应商联系人1," +
		"供应商地址2,供应商电话2,供应商传真2,供应商联系人2,供应商备注,发票号,发票地址,发票抬头," +
		"状态,卸货标志,是否ERP下传,建立人员,建立日期,更新人员,更新日期,供应商助记码"};
				commExportAction(title, threads, map, list.getRootList());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除供应商资料
	 * zhouhuan
	 */
	public void delete(){
		try{
			msgRes = bdef_DefSupplierImpl.delete(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),getOwnerNo(),getSupplierNo());
		}catch(Exception e){
			e.printStackTrace();
			msgRes=new MsgRes(false,"删除有误",getId());
		}finally{
			writeObj(msgRes);
		}
	}
	
	/*
	 * 获得供应商下拉
	 * zhouhuan
	 */
	public void getSupplierComboList()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefSupplierImpl.getSupplierComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),str,wheresql,0,10);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 判断供应商保存时，主键是否已存在
	 */
	public void existsSupplierNo(){
		try{		
			String no =bdef_DefSupplierImpl.existsSupplierNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),ownerNo, supplierNo);
			super.writeStr(no);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBdef_DefSupplierImpl(
			IBdef_DefSupplierService bdef_DefSupplierImpl) {
		this.bdef_DefSupplierImpl = bdef_DefSupplierImpl;
	}

	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}
	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	
	
	
	
}
