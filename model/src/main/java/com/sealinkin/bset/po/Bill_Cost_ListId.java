package com.sealinkin.bset.po;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BillCostListId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bill_Cost_ListId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private Date billingDate;
	private Double amount;
	private String flag;
	private Integer serialNo;
	private String appendCondition;
	private Double appendValue1;
	private Double appendValue2;
	private String billingUnit;
	private Double unitPrice;

	// Constructors

	/** default constructor */
	public Bill_Cost_ListId() {
	}

	/** minimal constructor */
	public Bill_Cost_ListId(String warehouseNo, String ownerNo,
			String billingProject, Integer serialNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.serialNo = serialNo;
	}

	/** full constructor */
	public Bill_Cost_ListId(String warehouseNo, String ownerNo,
			String billingProject, Date billingDate, Double amount,
			String flag, Integer serialNo, String appendCondition,
			Double appendValue1, Double appendValue2, String billingUnit,
			Double unitPrice) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.billingDate = billingDate;
		this.amount = amount;
		this.flag = flag;
		this.serialNo = serialNo;
		this.appendCondition = appendCondition;
		this.appendValue1 = appendValue1;
		this.appendValue2 = appendValue2;
		this.billingUnit = billingUnit;
		this.unitPrice = unitPrice;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "BILLING_DATE", length = 7)
	public Date getBillingDate() {
		return this.billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	@Column(name = "AMOUNT", precision = 12, scale = 3)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)
	public Integer getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "APPEND_CONDITION", length = 1)
	public String getAppendCondition() {
		return this.appendCondition;
	}

	public void setAppendCondition(String appendCondition) {
		this.appendCondition = appendCondition;
	}

	@Column(name = "APPEND_VALUE1", precision = 12, scale = 3)
	public Double getAppendValue1() {
		return this.appendValue1;
	}

	public void setAppendValue1(Double appendValue1) {
		this.appendValue1 = appendValue1;
	}

	@Column(name = "APPEND_VALUE2", precision = 12, scale = 3)
	public Double getAppendValue2() {
		return this.appendValue2;
	}

	public void setAppendValue2(Double appendValue2) {
		this.appendValue2 = appendValue2;
	}

	@Column(name = "BILLING_UNIT", length = 3)
	public String getBillingUnit() {
		return this.billingUnit;
	}

	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}

	@Column(name = "UNIT_PRICE", precision = 12, scale = 3)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bill_Cost_ListId))
			return false;
		Bill_Cost_ListId castOther = (Bill_Cost_ListId) other;

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
				&& ((this.getBillingDate() == castOther.getBillingDate()) || (this
						.getBillingDate() != null
						&& castOther.getBillingDate() != null && this
						.getBillingDate().equals(castOther.getBillingDate())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getAppendCondition() == castOther
						.getAppendCondition()) || (this.getAppendCondition() != null
						&& castOther.getAppendCondition() != null && this
						.getAppendCondition().equals(
								castOther.getAppendCondition())))
				&& ((this.getAppendValue1() == castOther.getAppendValue1()) || (this
						.getAppendValue1() != null
						&& castOther.getAppendValue1() != null && this
						.getAppendValue1().equals(castOther.getAppendValue1())))
				&& ((this.getAppendValue2() == castOther.getAppendValue2()) || (this
						.getAppendValue2() != null
						&& castOther.getAppendValue2() != null && this
						.getAppendValue2().equals(castOther.getAppendValue2())))
				&& ((this.getBillingUnit() == castOther.getBillingUnit()) || (this
						.getBillingUnit() != null
						&& castOther.getBillingUnit() != null && this
						.getBillingUnit().equals(castOther.getBillingUnit())))
				&& ((this.getUnitPrice() == castOther.getUnitPrice()) || (this
						.getUnitPrice() != null
						&& castOther.getUnitPrice() != null && this
						.getUnitPrice().equals(castOther.getUnitPrice())));
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
		result = 37
				* result
				+ (getBillingDate() == null ? 0 : this.getBillingDate()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37
				* result
				+ (getAppendCondition() == null ? 0 : this.getAppendCondition()
						.hashCode());
		result = 37
				* result
				+ (getAppendValue1() == null ? 0 : this.getAppendValue1()
						.hashCode());
		result = 37
				* result
				+ (getAppendValue2() == null ? 0 : this.getAppendValue2()
						.hashCode());
		result = 37
				* result
				+ (getBillingUnit() == null ? 0 : this.getBillingUnit()
						.hashCode());
		result = 37 * result
				+ (getUnitPrice() == null ? 0 : this.getUnitPrice().hashCode());
		return result;
	}

}