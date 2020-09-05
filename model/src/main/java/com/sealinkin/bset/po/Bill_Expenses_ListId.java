package com.sealinkin.bset.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BillExpensesListId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bill_Expenses_ListId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String groupNo;
	private Date beginDate;
	private Date endDate;
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
	private Integer serialNo;
	private String billingType;
	private String flag;

	// Constructors

	/** default constructor */
	public Bill_Expenses_ListId() {
	}

	/** minimal constructor */
	public Bill_Expenses_ListId(String warehouseNo, String ownerNo,
			String billingProject, Integer serialNo, String billingType) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.serialNo = serialNo;
		this.billingType = billingType;
	}

	/** full constructor */
	public Bill_Expenses_ListId(String warehouseNo, String ownerNo,
			String billingProject, String groupNo, Date beginDate,
			Date endDate, Double area, Double tray, Double qty, Double volume,
			Double weight, Double reserved1, Double reserved2,
			Double reserved3, Double reserved4, Double reserved5,
			Double reserved6, Integer serialNo, String billingType, String flag) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.groupNo = groupNo;
		this.beginDate = beginDate;
		this.endDate = endDate;
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
		this.serialNo = serialNo;
		this.billingType = billingType;
		this.flag = flag;
	}

	// Property accessors

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

	@Column(name = "GROUP_NO", length = 10)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_DATE", length = 7)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	@Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)
	public Integer getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bill_Expenses_ListId))
			return false;
		Bill_Expenses_ListId castOther = (Bill_Expenses_ListId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getBillingProject() == castOther.getBillingProject()) || (this
						.getBillingProject() != null
						&& castOther.getBillingProject() != null && this
						.getBillingProject().equals(
								castOther.getBillingProject())))
				&& ((this.getGroupNo() == castOther.getGroupNo()) || (this
						.getGroupNo() != null && castOther.getGroupNo() != null && this
						.getGroupNo().equals(castOther.getGroupNo())))
				&& ((this.getBeginDate() == castOther.getBeginDate()) || (this
						.getBeginDate() != null
						&& castOther.getBeginDate() != null && this
						.getBeginDate().equals(castOther.getBeginDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
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
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getBillingType() == castOther.getBillingType()) || (this
						.getBillingType() != null
						&& castOther.getBillingType() != null && this
						.getBillingType().equals(castOther.getBillingType())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())));
	}

	public int hashCode() {
		int result = 17;

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
		result = 37 * result
				+ (getGroupNo() == null ? 0 : this.getGroupNo().hashCode());
		result = 37 * result
				+ (getBeginDate() == null ? 0 : this.getBeginDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
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
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37
				* result
				+ (getBillingType() == null ? 0 : this.getBillingType()
						.hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		return result;
	}

}