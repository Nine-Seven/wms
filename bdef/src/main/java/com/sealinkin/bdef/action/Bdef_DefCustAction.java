package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.bdef.service.IBdef_DefCustService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;


public class Bdef_DefCustAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_DefCustService bdef_DefCustImpl;
	private String str;
	private String custId;
	private String pageSql;
	private String custNo;
	private String strQuery;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strOwnerNo;
	private String strOwnerCustNo;
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public void setBdef_DefCustImpl(IBdef_DefCustService bdef_DefCustImpl) {
		this.bdef_DefCustImpl = bdef_DefCustImpl;
	}
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public String getPageSql() {
		return pageSql;
	}
	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
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
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrOwnerCustNo() {
		return strOwnerCustNo;
	}
	public void setStrOwnerCustNo(String strOwnerCustNo) {
		this.strOwnerCustNo = strOwnerCustNo;
	}
	/**
	 * 跳转方法
	 */
	public void init(){
		super.writeStr("cms.view.bdef.defCustUI");
	}
	
	/**
	 * 获得客户资料列
	 * @throws Exception
	 */
	public void getDefCustList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefCustModel> list=bdef_DefCustImpl.getBdef_DefCust(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),strQuery,pageBo,requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				//map.put("cdate", "yyyy-MM-dd");
				String title = "客户资料";
				String[] threads = new String[]{"sheet1","客户资料",
						"ownerNo,ownerName,custtypeText,custAlias,ownerCustNo,custNo," +
						"custName,custProvince,custCity,custZone,custflagText," +
						"boxdeliverText,statusText,priotypeText,shippingmethodText,containermaterialText," +
						"collectflagText,onlydateflagText,warnflagText,deliveryAddress,custPhone1," +
						"custFax1,invoiceNo,contactorName1,maxCarTonnage,custEmail1," +
						"invoiceHeader,receivingHours,custPostcode,custaddress,invoiceAddr," +
						"remark",
						"委托业主编号,委托业主名称,客户类型,客户简称,委托业主客户代码,客户编号," +
						"客户名称,省份,城市,区域,客户标识," +
						"物流箱发货标记,状态,优先方式,客户出货方式,容器材质," +
						"是否采集材积,要求单一到期日,标签是否有特殊标记,送货地址,联系电话," +
						"客户传真,发票号,联系人,允许车辆最大吨位,客户EMAIL," +
						"发票抬头,收货时段,客户邮编,客户地址,发票地址," +
						"备注"};
				
				commExportAction(title, threads, map, list.getRootList());*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存或修改客户
	 * @throws Exception
	 */
	public void saveOrUpdateTactics(){
		try {
			MsgRes msg = bdef_DefCustImpl.saveOrUpdateCust(str,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//删除客户
	public void deleteCust(){
		try {
			MsgRes msg = bdef_DefCustImpl.deleteCust(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),custId);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "删除失败", ""));
		}
	}
	//获取客户下拉
	public void getBdef_DefCustCombo(){
		try {
			List<ComboxBo> list=bdef_DefCustImpl.getBdef_DefCustComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrQuery(),
					getPageSql());
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取客户名称
	public void getCustName(){
		try {
			List<String> list=bdef_DefCustImpl.getCustName(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),custNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getOwnerComboList(){
		try {
			List<ComboxBo> list=bdef_DefCustImpl.getOwnerComboList(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),pageSql);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getCarCombo(){
		try {
			List<ComboxBo> list=bdef_DefCustImpl.getCarCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					pageSql);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkCustNo(){
		try {
			String no=bdef_DefCustImpl.checkCustNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),custNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//货主客户编码校验
	public void checkOwnerCustNo(){
		try {
			String no=bdef_DefCustImpl.checkOwnerCustNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strOwnerNo,strOwnerCustNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//客户编码模糊查询下拉
	public void getCustInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefCustImpl.getCustInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
}
