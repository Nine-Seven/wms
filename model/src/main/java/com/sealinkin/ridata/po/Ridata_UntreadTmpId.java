package com.sealinkin.ridata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RidataUntreadTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Ridata_UntreadTmpId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String poType;
	private String poNo;
	private String classType;
	private String custNo;
	private Date untreadDate;
	private Date requestDate;
	private String status;
	private String untreadRemark;
	private String untreadFlag;
	private String stockType;
	private String stockValue;
	private String expNo;
	private String quality;
	private String orgNo;
	private String takeType;
	private String ownerArticleNo;
	private Double packingQty;
	private Double poQty;
	private Double unitCost;
	private Double rowId;
	private String rsvVarod1;
	private String rsvVarod2;
	private String rsvVarod3;
	private String rsvVarod4;
	private String rsvVarod5;
	private String rsvVarod6;
	private String rsvVarod7;
	private String rsvVarod8;
	private Double rsvNum1;
	private Double rsvNum2;
	private Double rsvNum3;
	private Date rsvDate1;
	private Date rsvDate2;
	private Date rsvDate3;
	private String carPlanNo;

	// Constructors

	/** default constructor */
	public Ridata_UntreadTmpId() {
	}

	/** minimal constructor */
	public Ridata_UntreadTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String poType, String poNo, String classType,
			String custNo, Date untreadDate, Date requestDate, String status,
			String untreadFlag, String stockType, String stockValue,
			String expNo, String quality, String orgNo, String takeType,
			String ownerArticleNo, Double packingQty, Double poQty) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poType = poType;
		this.poNo = poNo;
		this.classType = classType;
		this.custNo = custNo;
		this.untreadDate = untreadDate;
		this.requestDate = requestDate;
		this.status = status;
		this.untreadFlag = untreadFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.expNo = expNo;
		this.quality = quality;
		this.orgNo = orgNo;
		this.takeType = takeType;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.poQty = poQty;
	}

	/** full constructor */
	public Ridata_UntreadTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String poType, String poNo, String classType,
			String custNo, Date untreadDate, Date requestDate, String status,
			String untreadRemark, String untreadFlag, String stockType,
			String stockValue, String expNo, String quality, String orgNo,
			String takeType, String ownerArticleNo, Double packingQty,
			Double poQty, Double unitCost, Double rowId, String rsvVarod1,
			String rsvVarod2, String rsvVarod3, String rsvVarod4,
			String rsvVarod5, String rsvVarod6, String rsvVarod7,
			String rsvVarod8, Double rsvNum1, Double rsvNum2,
			Double rsvNum3, Date rsvDate1, Date rsvDate2, Date rsvDate3,
			String carPlanNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poType = poType;
		this.poNo = poNo;
		this.classType = classType;
		this.custNo = custNo;
		this.untreadDate = untreadDate;
		this.requestDate = requestDate;
		this.status = status;
		this.untreadRemark = untreadRemark;
		this.untreadFlag = untreadFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.expNo = expNo;
		this.quality = quality;
		this.orgNo = orgNo;
		this.takeType = takeType;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.poQty = poQty;
		this.unitCost = unitCost;
		this.rowId = rowId;
		this.rsvVarod1 = rsvVarod1;
		this.rsvVarod2 = rsvVarod2;
		this.rsvVarod3 = rsvVarod3;
		this.rsvVarod4 = rsvVarod4;
		this.rsvVarod5 = rsvVarod5;
		this.rsvVarod6 = rsvVarod6;
		this.rsvVarod7 = rsvVarod7;
		this.rsvVarod8 = rsvVarod8;
		this.rsvNum1 = rsvNum1;
		this.rsvNum2 = rsvNum2;
		this.rsvNum3 = rsvNum3;
		this.rsvDate1 = rsvDate1;
		this.rsvDate2 = rsvDate2;
		this.rsvDate3 = rsvDate3;
		this.carPlanNo = carPlanNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PO_TYPE", nullable = false, length = 5)
	public String getPoType() {
		return this.poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Column(name = "CLASS_TYPE", nullable = false, length = 1)
	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UNTREAD_DATE", nullable = false, length = 7)
	public Date getUntreadDate() {
		return this.untreadDate;
	}

	public void setUntreadDate(Date untreadDate) {
		this.untreadDate = untreadDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false, length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "UNTREAD_REMARK")
	public String getUntreadRemark() {
		return this.untreadRemark;
	}

	public void setUntreadRemark(String untreadRemark) {
		this.untreadRemark = untreadRemark;
	}

	@Column(name = "UNTREAD_FLAG", nullable = false, length = 1)
	public String getUntreadFlag() {
		return this.untreadFlag;
	}

	public void setUntreadFlag(String untreadFlag) {
		this.untreadFlag = untreadFlag;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Column(name = "STOCK_VALUE", nullable = false, length = 20)
	public String getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "QUALITY", nullable = false, length = 2)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "ORG_NO", nullable = false, length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	@Column(name = "TAKE_TYPE", nullable = false, length = 1)
	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "PO_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPoQty() {
		return this.poQty;
	}

	public void setPoQty(Double poQty) {
		this.poQty = poQty;
	}

	@Column(name = "UNIT_COST", precision = 18, scale = 5)
	public Double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	@Column(name = "ROW_ID", precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "RSV_VAROD1", length = 50)
	public String getRsvVarod1() {
		return this.rsvVarod1;
	}

	public void setRsvVarod1(String rsvVarod1) {
		this.rsvVarod1 = rsvVarod1;
	}

	@Column(name = "RSV_VAROD2", length = 50)
	public String getRsvVarod2() {
		return this.rsvVarod2;
	}

	public void setRsvVarod2(String rsvVarod2) {
		this.rsvVarod2 = rsvVarod2;
	}

	@Column(name = "RSV_VAROD3", length = 50)
	public String getRsvVarod3() {
		return this.rsvVarod3;
	}

	public void setRsvVarod3(String rsvVarod3) {
		this.rsvVarod3 = rsvVarod3;
	}

	@Column(name = "RSV_VAROD4", length = 50)
	public String getRsvVarod4() {
		return this.rsvVarod4;
	}

	public void setRsvVarod4(String rsvVarod4) {
		this.rsvVarod4 = rsvVarod4;
	}

	@Column(name = "RSV_VAROD5", length = 50)
	public String getRsvVarod5() {
		return this.rsvVarod5;
	}

	public void setRsvVarod5(String rsvVarod5) {
		this.rsvVarod5 = rsvVarod5;
	}

	@Column(name = "RSV_VAROD6", length = 50)
	public String getRsvVarod6() {
		return this.rsvVarod6;
	}

	public void setRsvVarod6(String rsvVarod6) {
		this.rsvVarod6 = rsvVarod6;
	}

	@Column(name = "RSV_VAROD7", length = 50)
	public String getRsvVarod7() {
		return this.rsvVarod7;
	}

	public void setRsvVarod7(String rsvVarod7) {
		this.rsvVarod7 = rsvVarod7;
	}

	@Column(name = "RSV_VAROD8", length = 50)
	public String getRsvVarod8() {
		return this.rsvVarod8;
	}

	public void setRsvVarod8(String rsvVarod8) {
		this.rsvVarod8 = rsvVarod8;
	}

	@Column(name = "RSV_NUM1", precision = 22, scale = 0)
	public Double getRsvNum1() {
		return this.rsvNum1;
	}

	public void setRsvNum1(Double rsvNum1) {
		this.rsvNum1 = rsvNum1;
	}

	@Column(name = "RSV_NUM2", precision = 22, scale = 0)
	public Double getRsvNum2() {
		return this.rsvNum2;
	}

	public void setRsvNum2(Double rsvNum2) {
		this.rsvNum2 = rsvNum2;
	}

	@Column(name = "RSV_NUM3", precision = 22, scale = 0)
	public Double getRsvNum3() {
		return this.rsvNum3;
	}

	public void setRsvNum3(Double rsvNum3) {
		this.rsvNum3 = rsvNum3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RSV_DATE1", length = 7)
	public Date getRsvDate1() {
		return this.rsvDate1;
	}

	public void setRsvDate1(Date rsvDate1) {
		this.rsvDate1 = rsvDate1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RSV_DATE2", length = 7)
	public Date getRsvDate2() {
		return this.rsvDate2;
	}

	public void setRsvDate2(Date rsvDate2) {
		this.rsvDate2 = rsvDate2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RSV_DATE3", length = 7)
	public Date getRsvDate3() {
		return this.rsvDate3;
	}

	public void setRsvDate3(Date rsvDate3) {
		this.rsvDate3 = rsvDate3;
	}

	@Column(name = "CAR_PLAN_NO", length = 20)
	public String getCarPlanNo() {
		return this.carPlanNo;
	}

	public void setCarPlanNo(String carPlanNo) {
		this.carPlanNo = carPlanNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Ridata_UntreadTmpId))
			return false;
		Ridata_UntreadTmpId castOther = (Ridata_UntreadTmpId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getPoType() == castOther.getPoType()) || (this
						.getPoType() != null && castOther.getPoType() != null && this
						.getPoType().equals(castOther.getPoType())))
				&& ((this.getPoNo() == castOther.getPoNo()) || (this.getPoNo() != null
						&& castOther.getPoNo() != null && this.getPoNo()
						.equals(castOther.getPoNo())))
				&& ((this.getClassType() == castOther.getClassType()) || (this
						.getClassType() != null
						&& castOther.getClassType() != null && this
						.getClassType().equals(castOther.getClassType())))
				&& ((this.getCustNo() == castOther.getCustNo()) || (this
						.getCustNo() != null && castOther.getCustNo() != null && this
						.getCustNo().equals(castOther.getCustNo())))
				&& ((this.getUntreadDate() == castOther.getUntreadDate()) || (this
						.getUntreadDate() != null
						&& castOther.getUntreadDate() != null && this
						.getUntreadDate().equals(castOther.getUntreadDate())))
				&& ((this.getRequestDate() == castOther.getRequestDate()) || (this
						.getRequestDate() != null
						&& castOther.getRequestDate() != null && this
						.getRequestDate().equals(castOther.getRequestDate())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getUntreadRemark() == castOther.getUntreadRemark()) || (this
						.getUntreadRemark() != null
						&& castOther.getUntreadRemark() != null && this
						.getUntreadRemark()
						.equals(castOther.getUntreadRemark())))
				&& ((this.getUntreadFlag() == castOther.getUntreadFlag()) || (this
						.getUntreadFlag() != null
						&& castOther.getUntreadFlag() != null && this
						.getUntreadFlag().equals(castOther.getUntreadFlag())))
				&& ((this.getStockType() == castOther.getStockType()) || (this
						.getStockType() != null
						&& castOther.getStockType() != null && this
						.getStockType().equals(castOther.getStockType())))
				&& ((this.getStockValue() == castOther.getStockValue()) || (this
						.getStockValue() != null
						&& castOther.getStockValue() != null && this
						.getStockValue().equals(castOther.getStockValue())))
				&& ((this.getExpNo() == castOther.getExpNo()) || (this
						.getExpNo() != null && castOther.getExpNo() != null && this
						.getExpNo().equals(castOther.getExpNo())))
				&& ((this.getQuality() == castOther.getQuality()) || (this
						.getQuality() != null && castOther.getQuality() != null && this
						.getQuality().equals(castOther.getQuality())))
				&& ((this.getOrgNo() == castOther.getOrgNo()) || (this
						.getOrgNo() != null && castOther.getOrgNo() != null && this
						.getOrgNo().equals(castOther.getOrgNo())))
				&& ((this.getTakeType() == castOther.getTakeType()) || (this
						.getTakeType() != null
						&& castOther.getTakeType() != null && this
						.getTakeType().equals(castOther.getTakeType())))
				&& ((this.getOwnerArticleNo() == castOther.getOwnerArticleNo()) || (this
						.getOwnerArticleNo() != null
						&& castOther.getOwnerArticleNo() != null && this
						.getOwnerArticleNo().equals(
								castOther.getOwnerArticleNo())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getPoQty() == castOther.getPoQty()) || (this
						.getPoQty() != null && castOther.getPoQty() != null && this
						.getPoQty().equals(castOther.getPoQty())))
				&& ((this.getUnitCost() == castOther.getUnitCost()) || (this
						.getUnitCost() != null
						&& castOther.getUnitCost() != null && this
						.getUnitCost().equals(castOther.getUnitCost())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())))
				&& ((this.getRsvVarod1() == castOther.getRsvVarod1()) || (this
						.getRsvVarod1() != null
						&& castOther.getRsvVarod1() != null && this
						.getRsvVarod1().equals(castOther.getRsvVarod1())))
				&& ((this.getRsvVarod2() == castOther.getRsvVarod2()) || (this
						.getRsvVarod2() != null
						&& castOther.getRsvVarod2() != null && this
						.getRsvVarod2().equals(castOther.getRsvVarod2())))
				&& ((this.getRsvVarod3() == castOther.getRsvVarod3()) || (this
						.getRsvVarod3() != null
						&& castOther.getRsvVarod3() != null && this
						.getRsvVarod3().equals(castOther.getRsvVarod3())))
				&& ((this.getRsvVarod4() == castOther.getRsvVarod4()) || (this
						.getRsvVarod4() != null
						&& castOther.getRsvVarod4() != null && this
						.getRsvVarod4().equals(castOther.getRsvVarod4())))
				&& ((this.getRsvVarod5() == castOther.getRsvVarod5()) || (this
						.getRsvVarod5() != null
						&& castOther.getRsvVarod5() != null && this
						.getRsvVarod5().equals(castOther.getRsvVarod5())))
				&& ((this.getRsvVarod6() == castOther.getRsvVarod6()) || (this
						.getRsvVarod6() != null
						&& castOther.getRsvVarod6() != null && this
						.getRsvVarod6().equals(castOther.getRsvVarod6())))
				&& ((this.getRsvVarod7() == castOther.getRsvVarod7()) || (this
						.getRsvVarod7() != null
						&& castOther.getRsvVarod7() != null && this
						.getRsvVarod7().equals(castOther.getRsvVarod7())))
				&& ((this.getRsvVarod8() == castOther.getRsvVarod8()) || (this
						.getRsvVarod8() != null
						&& castOther.getRsvVarod8() != null && this
						.getRsvVarod8().equals(castOther.getRsvVarod8())))
				&& ((this.getRsvNum1() == castOther.getRsvNum1()) || (this
						.getRsvNum1() != null && castOther.getRsvNum1() != null && this
						.getRsvNum1().equals(castOther.getRsvNum1())))
				&& ((this.getRsvNum2() == castOther.getRsvNum2()) || (this
						.getRsvNum2() != null && castOther.getRsvNum2() != null && this
						.getRsvNum2().equals(castOther.getRsvNum2())))
				&& ((this.getRsvNum3() == castOther.getRsvNum3()) || (this
						.getRsvNum3() != null && castOther.getRsvNum3() != null && this
						.getRsvNum3().equals(castOther.getRsvNum3())))
				&& ((this.getRsvDate1() == castOther.getRsvDate1()) || (this
						.getRsvDate1() != null
						&& castOther.getRsvDate1() != null && this
						.getRsvDate1().equals(castOther.getRsvDate1())))
				&& ((this.getRsvDate2() == castOther.getRsvDate2()) || (this
						.getRsvDate2() != null
						&& castOther.getRsvDate2() != null && this
						.getRsvDate2().equals(castOther.getRsvDate2())))
				&& ((this.getRsvDate3() == castOther.getRsvDate3()) || (this
						.getRsvDate3() != null
						&& castOther.getRsvDate3() != null && this
						.getRsvDate3().equals(castOther.getRsvDate3())))
				&& ((this.getCarPlanNo() == castOther.getCarPlanNo()) || (this
						.getCarPlanNo() != null
						&& castOther.getCarPlanNo() != null && this
						.getCarPlanNo().equals(castOther.getCarPlanNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getPoType() == null ? 0 : this.getPoType().hashCode());
		result = 37 * result
				+ (getPoNo() == null ? 0 : this.getPoNo().hashCode());
		result = 37 * result
				+ (getClassType() == null ? 0 : this.getClassType().hashCode());
		result = 37 * result
				+ (getCustNo() == null ? 0 : this.getCustNo().hashCode());
		result = 37
				* result
				+ (getUntreadDate() == null ? 0 : this.getUntreadDate()
						.hashCode());
		result = 37
				* result
				+ (getRequestDate() == null ? 0 : this.getRequestDate()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getUntreadRemark() == null ? 0 : this.getUntreadRemark()
						.hashCode());
		result = 37
				* result
				+ (getUntreadFlag() == null ? 0 : this.getUntreadFlag()
						.hashCode());
		result = 37 * result
				+ (getStockType() == null ? 0 : this.getStockType().hashCode());
		result = 37
				* result
				+ (getStockValue() == null ? 0 : this.getStockValue()
						.hashCode());
		result = 37 * result
				+ (getExpNo() == null ? 0 : this.getExpNo().hashCode());
		result = 37 * result
				+ (getQuality() == null ? 0 : this.getQuality().hashCode());
		result = 37 * result
				+ (getOrgNo() == null ? 0 : this.getOrgNo().hashCode());
		result = 37 * result
				+ (getTakeType() == null ? 0 : this.getTakeType().hashCode());
		result = 37
				* result
				+ (getOwnerArticleNo() == null ? 0 : this.getOwnerArticleNo()
						.hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37 * result
				+ (getPoQty() == null ? 0 : this.getPoQty().hashCode());
		result = 37 * result
				+ (getUnitCost() == null ? 0 : this.getUnitCost().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		result = 37 * result
				+ (getRsvVarod1() == null ? 0 : this.getRsvVarod1().hashCode());
		result = 37 * result
				+ (getRsvVarod2() == null ? 0 : this.getRsvVarod2().hashCode());
		result = 37 * result
				+ (getRsvVarod3() == null ? 0 : this.getRsvVarod3().hashCode());
		result = 37 * result
				+ (getRsvVarod4() == null ? 0 : this.getRsvVarod4().hashCode());
		result = 37 * result
				+ (getRsvVarod5() == null ? 0 : this.getRsvVarod5().hashCode());
		result = 37 * result
				+ (getRsvVarod6() == null ? 0 : this.getRsvVarod6().hashCode());
		result = 37 * result
				+ (getRsvVarod7() == null ? 0 : this.getRsvVarod7().hashCode());
		result = 37 * result
				+ (getRsvVarod8() == null ? 0 : this.getRsvVarod8().hashCode());
		result = 37 * result
				+ (getRsvNum1() == null ? 0 : this.getRsvNum1().hashCode());
		result = 37 * result
				+ (getRsvNum2() == null ? 0 : this.getRsvNum2().hashCode());
		result = 37 * result
				+ (getRsvNum3() == null ? 0 : this.getRsvNum3().hashCode());
		result = 37 * result
				+ (getRsvDate1() == null ? 0 : this.getRsvDate1().hashCode());
		result = 37 * result
				+ (getRsvDate2() == null ? 0 : this.getRsvDate2().hashCode());
		result = 37 * result
				+ (getRsvDate3() == null ? 0 : this.getRsvDate3().hashCode());
		result = 37 * result
				+ (getCarPlanNo() == null ? 0 : this.getCarPlanNo().hashCode());
		return result;
	}

}