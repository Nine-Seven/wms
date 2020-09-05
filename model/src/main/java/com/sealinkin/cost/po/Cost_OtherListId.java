package com.sealinkin.cost.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CostOtherListId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_OtherListId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String costNo;
	private Date costDate;
	private String sourceNo;

	// Constructors

	/** default constructor */
	public Cost_OtherListId() {
	}

	/** full constructor */
	public Cost_OtherListId(String enterpriseNo, String warehouseNo,
			String ownerNo, String costNo, Date costDate, String sourceNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.costNo = costNo;
		this.costDate = costDate;
		this.sourceNo = sourceNo;
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

	@Column(name = "COST_NO", nullable = false, length = 8)
	public String getCostNo() {
		return this.costNo;
	}

	public void setCostNo(String costNo) {
		this.costNo = costNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COST_DATE", nullable = false, length = 7)
	public Date getCostDate() {
		return this.costDate;
	}

	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}
	
	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_OtherListId))
			return false;
		Cost_OtherListId castOther = (Cost_OtherListId) other;

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
				&& ((this.getCostNo() == castOther.getCostNo()) || (this
						.getCostNo() != null && castOther.getCostNo() != null && this
						.getCostNo().equals(castOther.getCostNo())))
				&& ((this.getCostDate() == castOther.getCostDate()) || (this
						.getCostDate() != null
						&& castOther.getCostDate() != null && this
						.getCostDate().equals(castOther.getCostDate())))
				&& ((this.getSourceNo() == castOther.getSourceNo()) || (this
						.getSourceNo() != null
						&& castOther.getSourceNo() != null && this
						.getSourceNo().equals(castOther.getSourceNo())));
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
				+ (getCostNo() == null ? 0 : this.getCostNo().hashCode());
		result = 37 * result
				+ (getCostDate() == null ? 0 : this.getCostDate().hashCode());
		result = 37 * result
				+ (getSourceNo() == null ? 0 : this.getSourceNo().hashCode());
		return result;
	}

}