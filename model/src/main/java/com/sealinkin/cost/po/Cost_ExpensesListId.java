package com.sealinkin.cost.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CostExpensesListId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_ExpensesListId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String billingType;
	private String familyNo;
	private String sourceNo;
	private Long serialNo;
	private Date buildDate;
	private Double area;
	private Double tray;
	private Double qty;
	private Double volume;
	private Double weight;
	private Double reserved1;
	private Double reserved2;
	private Double reserved3;
	private Double reserved4;
	private Double reserved5;
	private Double reserved6;
	private String status;
	private String flag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_ExpensesListId() {
	}

	/** minimal constructor */
	public Cost_ExpensesListId(String warehouseNo, String ownerNo,
			String billingProject, String billingType, Long serialNo,
			String rgstName, Date rgstDate) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.billingType = billingType;
		this.serialNo = serialNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_ExpensesListId(String enterpriseNo, String warehouseNo,
			String ownerNo, String billingProject, String billingType,
			String familyNo, String sourceNo, Long serialNo, Date buildDate,
			Double area, Double tray, Double qty, Double volume, Double weight,
			Double reserved1, Double reserved2, Double reserved3,
			Double reserved4, Double reserved5, Double reserved6,
			String status, String flag, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.billingType = billingType;
		this.familyNo = familyNo;
		this.sourceNo = sourceNo;
		this.serialNo = serialNo;
		this.buildDate = buildDate;
		this.area = area;
		this.tray = tray;
		this.qty = qty;
		this.volume = volume;
		this.weight = weight;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
		this.reserved6 = reserved6;
		this.status = status;
		this.flag = flag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", length = 15)
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

	@Column(name = "BILLING_PROJECT", nullable = false, length = 8)
	public String getBillingProject() {
		return this.billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Column(name = "FAMILY_NO", length = 10)
	public String getFamilyNo() {
		return this.familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	@Column(name = "SOURCE_NO", length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "SERIAL_NO", nullable = false, precision = 10, scale = 0)
	public Long getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BUILD_DATE", length = 7)
	public Date getBuildDate() {
		return this.buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	@Column(name = "AREA", precision = 12, scale = 3)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "TRAY", precision = 12, scale = 3)
	public Double getTray() {
		return this.tray;
	}

	public void setTray(Double tray) {
		this.tray = tray;
	}

	@Column(name = "QTY", precision = 12, scale = 3)
	public Double getQty() {
		return this.qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	@Column(name = "VOLUME", precision = 12, scale = 3)
	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	@Column(name = "WEIGHT", precision = 12, scale = 3)
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "RESERVED1", precision = 12, scale = 3)
	public Double getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(Double reserved1) {
		this.reserved1 = reserved1;
	}

	@Column(name = "RESERVED2", precision = 12, scale = 3)
	public Double getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(Double reserved2) {
		this.reserved2 = reserved2;
	}

	@Column(name = "RESERVED3", precision = 12, scale = 3)
	public Double getReserved3() {
		return this.reserved3;
	}

	public void setReserved3(Double reserved3) {
		this.reserved3 = reserved3;
	}

	@Column(name = "RESERVED4", precision = 12, scale = 3)
	public Double getReserved4() {
		return this.reserved4;
	}

	public void setReserved4(Double reserved4) {
		this.reserved4 = reserved4;
	}

	@Column(name = "RESERVED5", precision = 12, scale = 3)
	public Double getReserved5() {
		return this.reserved5;
	}

	public void setReserved5(Double reserved5) {
		this.reserved5 = reserved5;
	}

	@Column(name = "RESERVED6", precision = 12, scale = 3)
	public Double getReserved6() {
		return this.reserved6;
	}

	public void setReserved6(Double reserved6) {
		this.reserved6 = reserved6;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_ExpensesListId))
			return false;
		Cost_ExpensesListId castOther = (Cost_ExpensesListId) other;

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
				&& ((this.getBillingProject() == castOther.getBillingProject()) || (this
						.getBillingProject() != null
						&& castOther.getBillingProject() != null && this
						.getBillingProject().equals(
								castOther.getBillingProject())))
				&& ((this.getBillingType() == castOther.getBillingType()) || (this
						.getBillingType() != null
						&& castOther.getBillingType() != null && this
						.getBillingType().equals(castOther.getBillingType())))
				&& ((this.getFamilyNo() == castOther.getFamilyNo()) || (this
						.getFamilyNo() != null
						&& castOther.getFamilyNo() != null && this
						.getFamilyNo().equals(castOther.getFamilyNo())))
				&& ((this.getSourceNo() == castOther.getSourceNo()) || (this
						.getSourceNo() != null
						&& castOther.getSourceNo() != null && this
						.getSourceNo().equals(castOther.getSourceNo())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getBuildDate() == castOther.getBuildDate()) || (this
						.getBuildDate() != null
						&& castOther.getBuildDate() != null && this
						.getBuildDate().equals(castOther.getBuildDate())))
				&& ((this.getArea() == castOther.getArea()) || (this.getArea() != null
						&& castOther.getArea() != null && this.getArea()
						.equals(castOther.getArea())))
				&& ((this.getTray() == castOther.getTray()) || (this.getTray() != null
						&& castOther.getTray() != null && this.getTray()
						.equals(castOther.getTray())))
				&& ((this.getQty() == castOther.getQty()) || (this.getQty() != null
						&& castOther.getQty() != null && this.getQty().equals(
						castOther.getQty())))
				&& ((this.getVolume() == castOther.getVolume()) || (this
						.getVolume() != null && castOther.getVolume() != null && this
						.getVolume().equals(castOther.getVolume())))
				&& ((this.getWeight() == castOther.getWeight()) || (this
						.getWeight() != null && castOther.getWeight() != null && this
						.getWeight().equals(castOther.getWeight())))
				&& ((this.getReserved1() == castOther.getReserved1()) || (this
						.getReserved1() != null
						&& castOther.getReserved1() != null && this
						.getReserved1().equals(castOther.getReserved1())))
				&& ((this.getReserved2() == castOther.getReserved2()) || (this
						.getReserved2() != null
						&& castOther.getReserved2() != null && this
						.getReserved2().equals(castOther.getReserved2())))
				&& ((this.getReserved3() == castOther.getReserved3()) || (this
						.getReserved3() != null
						&& castOther.getReserved3() != null && this
						.getReserved3().equals(castOther.getReserved3())))
				&& ((this.getReserved4() == castOther.getReserved4()) || (this
						.getReserved4() != null
						&& castOther.getReserved4() != null && this
						.getReserved4().equals(castOther.getReserved4())))
				&& ((this.getReserved5() == castOther.getReserved5()) || (this
						.getReserved5() != null
						&& castOther.getReserved5() != null && this
						.getReserved5().equals(castOther.getReserved5())))
				&& ((this.getReserved6() == castOther.getReserved6()) || (this
						.getReserved6() != null
						&& castOther.getReserved6() != null && this
						.getReserved6().equals(castOther.getReserved6())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getUpdtName() == castOther.getUpdtName()) || (this
						.getUpdtName() != null
						&& castOther.getUpdtName() != null && this
						.getUpdtName().equals(castOther.getUpdtName())))
				&& ((this.getUpdtDate() == castOther.getUpdtDate()) || (this
						.getUpdtDate() != null
						&& castOther.getUpdtDate() != null && this
						.getUpdtDate().equals(castOther.getUpdtDate())));
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
		result = 37
				* result
				+ (getBillingProject() == null ? 0 : this.getBillingProject()
						.hashCode());
		result = 37
				* result
				+ (getBillingType() == null ? 0 : this.getBillingType()
						.hashCode());
		result = 37 * result
				+ (getFamilyNo() == null ? 0 : this.getFamilyNo().hashCode());
		result = 37 * result
				+ (getSourceNo() == null ? 0 : this.getSourceNo().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37 * result
				+ (getBuildDate() == null ? 0 : this.getBuildDate().hashCode());
		result = 37 * result
				+ (getArea() == null ? 0 : this.getArea().hashCode());
		result = 37 * result
				+ (getTray() == null ? 0 : this.getTray().hashCode());
		result = 37 * result
				+ (getQty() == null ? 0 : this.getQty().hashCode());
		result = 37 * result
				+ (getVolume() == null ? 0 : this.getVolume().hashCode());
		result = 37 * result
				+ (getWeight() == null ? 0 : this.getWeight().hashCode());
		result = 37 * result
				+ (getReserved1() == null ? 0 : this.getReserved1().hashCode());
		result = 37 * result
				+ (getReserved2() == null ? 0 : this.getReserved2().hashCode());
		result = 37 * result
				+ (getReserved3() == null ? 0 : this.getReserved3().hashCode());
		result = 37 * result
				+ (getReserved4() == null ? 0 : this.getReserved4().hashCode());
		result = 37 * result
				+ (getReserved5() == null ? 0 : this.getReserved5().hashCode());
		result = 37 * result
				+ (getReserved6() == null ? 0 : this.getReserved6().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getUpdtName() == null ? 0 : this.getUpdtName().hashCode());
		result = 37 * result
				+ (getUpdtDate() == null ? 0 : this.getUpdtDate().hashCode());
		return result;
	}

}