package com.sealinkin.ridata.model;

import java.util.Date;

import com.sealinkin.ridata.po.Ridata_CheckPal;

public class Ridata_CheckPalModel extends Ridata_CheckPal{

	private static final long serialVersionUID = 1L;
	private String labelNo;
	private String warehouseNo;
	private String ownerNo;
	private String checkNo;
	private short checkRowId;
	private String containerNo;
	private String cellNo;
	private String firstcheckLabelNo;
	private String ownerArticleNo;
	private String checkBox;
	private String checkDis;
	
	private String articleName;
	//private String spec;
	private double packingQty;
	private double checkQty;
	//private String packingUnit;
	private String barCode;
	private String articleNo;
	
	private Date produceDate;
    private Date expireDate;
	
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	
	
	
	
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Double getRealBox() {
		return realBox;
	}

	public void setRealBox(Double realBox) {
		this.realBox = realBox;
	}

	public Double getRealDis() {
		return realDis;
	}

	public void setRealDis(Double realDis) {
		this.realDis = realDis;
	}

	public Double getRealQmin() {
		return realQmin;
	}

	public void setRealQmin(Double realQmin) {
		this.realQmin = realQmin;
	}

	public Double getPlanBox() {
		return planBox;
	}

	public void setPlanBox(Double planBox) {
		this.planBox = planBox;
	}

	public Double getPlanDis() {
		return planDis;
	}

	public void setPlanDis(Double planDis) {
		this.planDis = planDis;
	}

	public Double getPlanQmin() {
		return planQmin;
	}

	public void setPlanQmin(Double planQmin) {
		this.planQmin = planQmin;
	}

	public String getPackingUnitQmin() {
		return packingUnitQmin;
	}

	public void setPackingUnitQmin(String packingUnitQmin) {
		this.packingUnitQmin = packingUnitQmin;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPackingSpec() {
		return packingSpec;
	}

	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}

	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}

	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}

	public Double getUnitPacking() {
		return unitPacking;
	}

	public void setUnitPacking(Double unitPacking) {
		this.unitPacking = unitPacking;
	}

	public String getPackingSpecQmin() {
		return packingSpecQmin;
	}

	public void setPackingSpecQmin(String packingSpecQmin) {
		this.packingSpecQmin = packingSpecQmin;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public double getCheckQty() {
		return checkQty;
	}

	public void setCheckQty(double checkQty) {
		this.checkQty = checkQty;
	}

	public String getLabelNo() {
		return labelNo;
	}
	
	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public String getCheckDis() {
		return checkDis;
	}

	public void setCheckDis(String checkDis) {
		this.checkDis = checkDis;
	}

	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public short getCheckRowId() {
		return checkRowId;
	}
	public void setCheckRowId(short checkRowId) {
		this.checkRowId = checkRowId;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getFirstcheckLabelNo() {
		return firstcheckLabelNo;
	}
	public void setFirstcheckLabelNo(String firstcheckLabelNo) {
		this.firstcheckLabelNo = firstcheckLabelNo;
	}
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
	public double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	
}
