package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_DefWorker;

public class Bdef_DefWorkerModel extends Bdef_DefWorker{

	/**
	 * lich于2015-01-12增加currEnterpriseNo\currEnterpriseName属性字段
	 */
	private static final long serialVersionUID = 1L;
	private String statusText;
	private String fixedcellFlagText;
	private String authorityTypeText;
	private String IStrategyText;
	private String OStrategyText;
	private String MStrategyText;
	private String riStrategyText;
	private String roStrategyText;
	private String fcStrategyText;
	private String rsvStrategy1Text;
	private String rsvStrategy2Text;
	private String rsvStrategy3Text;
	private String rsvStrategy4Text;
	private String rsvStrategy5Text;
	private String rsvStrategy6Text;
	
	private String enterpriseNo;
	private String workerNo;
	
	private String ownerName;
	private String warehouseName;
	private String workerOwner;//当前登录员工所拥有的货主权限
	private String workSpaceNo;//工作站
	private String workSpaceName;//工作站名
	private String currEnterpriseNo;//当前登录所选择企业编码
	private String currEnterpriseName;//当前登录所选择企业名称
	
	private boolean flag;
	
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getFixedcellFlagText() {
		return fixedcellFlagText;
	}
	public void setFixedcellFlagText(String fixedcellFlagText) {
		this.fixedcellFlagText = fixedcellFlagText;
	}
	public String getAuthorityTypeText() {
		return authorityTypeText;
	}
	public void setAuthorityTypeText(String authorityTypeText) {
		this.authorityTypeText = authorityTypeText;
	}
	public String getIStrategyText() {
		return IStrategyText;
	}
	public void setIStrategyText(String iStrategyText) {
		IStrategyText = iStrategyText;
	}
	public String getOStrategyText() {
		return OStrategyText;
	}
	public void setOStrategyText(String oStrategyText) {
		OStrategyText = oStrategyText;
	}
	public String getMStrategyText() {
		return MStrategyText;
	}
	public void setMStrategyText(String mStrategyText) {
		MStrategyText = mStrategyText;
	}
	public String getRiStrategyText() {
		return riStrategyText;
	}
	public void setRiStrategyText(String riStrategyText) {
		this.riStrategyText = riStrategyText;
	}
	public String getRoStrategyText() {
		return roStrategyText;
	}
	public void setRoStrategyText(String roStrategyText) {
		this.roStrategyText = roStrategyText;
	}
	public String getFcStrategyText() {
		return fcStrategyText;
	}
	public void setFcStrategyText(String fcStrategyText) {
		this.fcStrategyText = fcStrategyText;
	}
	public String getRsvStrategy1Text() {
		return rsvStrategy1Text;
	}
	public void setRsvStrategy1Text(String rsvStrategy1Text) {
		this.rsvStrategy1Text = rsvStrategy1Text;
	}
	public String getRsvStrategy2Text() {
		return rsvStrategy2Text;
	}
	public void setRsvStrategy2Text(String rsvStrategy2Text) {
		this.rsvStrategy2Text = rsvStrategy2Text;
	}
	public String getRsvStrategy3Text() {
		return rsvStrategy3Text;
	}
	public void setRsvStrategy3Text(String rsvStrategy3Text) {
		this.rsvStrategy3Text = rsvStrategy3Text;
	}
	public String getRsvStrategy4Text() {
		return rsvStrategy4Text;
	}
	public void setRsvStrategy4Text(String rsvStrategy4Text) {
		this.rsvStrategy4Text = rsvStrategy4Text;
	}
	public String getRsvStrategy5Text() {
		return rsvStrategy5Text;
	}
	public void setRsvStrategy5Text(String rsvStrategy5Text) {
		this.rsvStrategy5Text = rsvStrategy5Text;
	}
	public String getRsvStrategy6Text() {
		return rsvStrategy6Text;
	}
	public void setRsvStrategy6Text(String rsvStrategy6Text) {
		this.rsvStrategy6Text = rsvStrategy6Text;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWorkerOwner() {
		return workerOwner;
	}
	public void setWorkerOwner(String workerOwner) {
		this.workerOwner = workerOwner;
	}
	public String getWorkSpaceNo() {
		return workSpaceNo;
	}
	public void setWorkSpaceNo(String workSpaceNo) {
		this.workSpaceNo = workSpaceNo;
	}
	public String getWorkSpaceName() {
		return workSpaceName;
	}
	public void setWorkSpaceName(String workSpaceName) {
		this.workSpaceName = workSpaceName;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCurrEnterpriseNo() {
		return currEnterpriseNo;
	}
	public void setCurrEnterpriseNo(String currEnterpriseNo) {
		this.currEnterpriseNo = currEnterpriseNo;
	}
	public String getCurrEnterpriseName() {
		return currEnterpriseName;
	}
	public void setCurrEnterpriseName(String currEnterpriseName) {
		this.currEnterpriseName = currEnterpriseName;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	
}
