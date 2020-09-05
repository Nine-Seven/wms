package com.sealinkin.cost.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostOtherSetId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_OtherSetId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String costNo;

	// Constructors

	/** default constructor */
	public Cost_OtherSetId() {
	}

	/** full constructor */
	public Cost_OtherSetId(String enterpriseNo, String costNo) {
		this.enterpriseNo = enterpriseNo;
		this.costNo = costNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "COST_NO", nullable = false, length = 8)
	public String getCostNo() {
		return this.costNo;
	}

	public void setCostNo(String costNo) {
		this.costNo = costNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_OtherSetId))
			return false;
		Cost_OtherSetId castOther = (Cost_OtherSetId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getCostNo() == castOther.getCostNo()) || (this
						.getCostNo() != null && castOther.getCostNo() != null && this
						.getCostNo().equals(castOther.getCostNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getCostNo() == null ? 0 : this.getCostNo().hashCode());
		return result;
	}

}