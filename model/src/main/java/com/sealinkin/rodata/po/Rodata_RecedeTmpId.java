package com.sealinkin.rodata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RodataRecedeTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Rodata_RecedeTmpId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String poType;
	private String poNo;
	private String supplierNo;
	private String classType;
	private Date recedeDate;
	private String status;
	private String stockType;
	private String stockValue;
	private String quality;
	private String recedeRemark;
	private Date requestDate;
	private String deptNo;
	private String takeType;
	private String orgNo;
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

	// Constructors

	/** default constructor */
	public Rodata_RecedeTmpId() {
	}

	/** minimal constructor */
	public Rodata_RecedeTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String poType, String poNo, String supplierNo,
			String classType, Date recedeDate, String status, String stockType,
			String stockValue, String deptNo, String orgNo,String takeType,
			String ownerArticleNo, Double packingQty, Double poQty) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poType = poType;
		this.poNo = poNo;
		this.supplierNo = supplierNo;
		this.classType = classType;
		this.recedeDate = recedeDate;
		this.status = status;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.deptNo = deptNo;
		this.orgNo = orgNo;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.poQty = poQty;
		this.takeType=takeType;
	}

	/** full constructor */
	public Rodata_RecedeTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String poType, String poNo, String supplierNo,
			String classType, Date recedeDate, String status, String stockType,
			String stockValue, String quality, String recedeRemark,
			Date requestDate, String deptNo, String orgNo,String takeType,
			String ownerArticleNo, Double packingQty, Double poQty,
			Double unitCost, Double rowId, String rsvVarod1,
			String rsvVarod2, String rsvVarod3, String rsvVarod4,
			String rsvVarod5, String rsvVarod6, String rsvVarod7,
			String rsvVarod8, Double rsvNum1, Double rsvNum2,
			Double rsvNum3, Date rsvDate1, Date rsvDate2, Date rsvDate3) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poType = poType;
		this.poNo = poNo;
		this.supplierNo = supplierNo;
		this.classType = classType;
		this.recedeDate = recedeDate;
		this.status = status;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.quality = quality;
		this.recedeRemark = recedeRemark;
		this.requestDate = requestDate;
		this.deptNo = deptNo;
		this.orgNo = orgNo;
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
		this.takeType = takeType;
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

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "CLASS_TYPE", nullable = false, length = 1)
	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECEDE_DATE", nullable = false, length = 7)
	public Date getRecedeDate() {
		return this.recedeDate;
	}

	public void setRecedeDate(Date recedeDate) {
		this.recedeDate = recedeDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "QUALITY", length = 1)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "RECEDE_REMARK")
	public String getRecedeRemark() {
		return this.recedeRemark;
	}

	public void setRecedeRemark(String recedeRemark) {
		this.recedeRemark = recedeRemark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "DEPT_NO", nullable = false, length = 10)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "TAKE_TYPE", nullable = false, length = 1)
	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	@Column(name = "ORG_NO", nullable = false, length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rodata_RecedeTmpId))
			return false;
		Rodata_RecedeTmpId castOther = (Rodata_RecedeTmpId) other;

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
				&& ((this.getSupplierNo() == castOther.getSupplierNo()) || (this
						.getSupplierNo() != null
						&& castOther.getSupplierNo() != null && this
						.getSupplierNo().equals(castOther.getSupplierNo())))
				&& ((this.getClassType() == castOther.getClassType()) || (this
						.getClassType() != null
						&& castOther.getClassType() != null && this
						.getClassType().equals(castOther.getClassType())))
				&& ((this.getRecedeDate() == castOther.getRecedeDate()) || (this
						.getRecedeDate() != null
						&& castOther.getRecedeDate() != null && this
						.getRecedeDate().equals(castOther.getRecedeDate())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getStockType() == castOther.getStockType()) || (this
						.getStockType() != null
						&& castOther.getStockType() != null && this
						.getStockType().equals(castOther.getStockType())))
				&& ((this.getStockValue() == castOther.getStockValue()) || (this
						.getStockValue() != null
						&& castOther.getStockValue() != null && this
						.getStockValue().equals(castOther.getStockValue())))
				&& ((this.getQuality() == castOther.getQuality()) || (this
						.getQuality() != null && castOther.getQuality() != null && this
						.getQuality().equals(castOther.getQuality())))
				&& ((this.getRecedeRemark() == castOther.getRecedeRemark()) || (this
						.getRecedeRemark() != null
						&& castOther.getRecedeRemark() != null && this
						.getRecedeRemark().equals(castOther.getRecedeRemark())))
				&& ((this.getRequestDate() == castOther.getRequestDate()) || (this
						.getRequestDate() != null
						&& castOther.getRequestDate() != null && this
						.getRequestDate().equals(castOther.getRequestDate())))
				&& ((this.getDeptNo() == castOther.getDeptNo()) || (this
						.getDeptNo() != null && castOther.getDeptNo() != null && this
						.getDeptNo().equals(castOther.getDeptNo())))
				&& ((this.getOrgNo() == castOther.getOrgNo()) || (this
						.getOrgNo() != null && castOther.getOrgNo() != null && this
						.getOrgNo().equals(castOther.getOrgNo())))
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
						.getRsvDate3().equals(castOther.getRsvDate3())));
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
		result = 37
				* result
				+ (getSupplierNo() == null ? 0 : this.getSupplierNo()
						.hashCode());
		result = 37 * result
				+ (getClassType() == null ? 0 : this.getClassType().hashCode());
		result = 37
				* result
				+ (getRecedeDate() == null ? 0 : this.getRecedeDate()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getStockType() == null ? 0 : this.getStockType().hashCode());
		result = 37
				* result
				+ (getStockValue() == null ? 0 : this.getStockValue()
						.hashCode());
		result = 37 * result
				+ (getQuality() == null ? 0 : this.getQuality().hashCode());
		result = 37
				* result
				+ (getRecedeRemark() == null ? 0 : this.getRecedeRemark()
						.hashCode());
		result = 37
				* result
				+ (getRequestDate() == null ? 0 : this.getRequestDate()
						.hashCode());
		result = 37 * result
				+ (getDeptNo() == null ? 0 : this.getDeptNo().hashCode());
		result = 37 * result
				+ (getOrgNo() == null ? 0 : this.getOrgNo().hashCode());
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
		return result;
	}

}