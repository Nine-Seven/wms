package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_DefOwner;

public class Bdef_DefOwnerModel extends Bdef_DefOwner{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
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
	private String scanFlagText;
	private String cellType;
	private boolean flag;
	

	private String wareHouseNO;//仓别
	private String wcellManagerType;//仓别级 储位管理类型
	private String wtypeValue;//仓别级 储位
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getScanFlagText() {
		return scanFlagText;
	}
	public void setScanFlagText(String scanFlagText) {
		this.scanFlagText = scanFlagText;
	}
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
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getCellType() {
		return cellType;
	}
	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
	public String getWareHouseNO() {
		return wareHouseNO;
	}
	public void setWareHouseNO(String wareHouseNO) {
		this.wareHouseNO = wareHouseNO;
	}
	public String getWcellManagerType() {
		return wcellManagerType;
	}
	public void setWcellManagerType(String wcellManagerType) {
		this.wcellManagerType = wcellManagerType;
	}
	public String getWtypeValue() {
		return wtypeValue;
	}
	public void setWtypeValue(String wtypeValue) {
		this.wtypeValue = wtypeValue;
	}


}
