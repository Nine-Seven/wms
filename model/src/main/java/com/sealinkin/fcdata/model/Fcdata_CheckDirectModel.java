package com.sealinkin.fcdata.model;

import java.util.Date;

import com.sealinkin.fcdata.po.Fcdata_CheckDirect;

public class Fcdata_CheckDirectModel extends Fcdata_CheckDirect{
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String articleName;
	private String spec;
	private String packingUnit;
	private String qualityText;
	private String wareNo;
	private String wareName;
    private String areaNo;
    private String areaName;
    private String stockNo;
    private String cutFlag;
    private String moveType;
    private String checkNo;
    private String fcdataType;
	private Date beginDate;
	private Date endDate;
    
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getQualityText() {
		return qualityText;
	}
	public void setQualityText(String qualityText) {
		this.qualityText = qualityText;
	}
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getCutFlag() {
		return cutFlag;
	}
	public void setCutFlag(String cutFlag) {
		this.cutFlag = cutFlag;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getFcdataType() {
		return fcdataType;
	}
	public void setFcdataType(String fcdataType) {
		this.fcdataType = fcdataType;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
