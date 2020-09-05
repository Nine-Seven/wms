package com.sealinkin.bset.po;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bset_User_RoleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_User_RoleId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String workerNo;
	private BigDecimal roleId;

	// Constructors

	/** default constructor */
	public Bset_User_RoleId() {
	}

	/** full constructor */
	public Bset_User_RoleId(String enterpriseNo, String workerNo,
			BigDecimal roleId) {
		this.enterpriseNo = enterpriseNo;
		this.workerNo = workerNo;
		this.roleId = roleId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WORKER_NO", nullable = false, length = 10)
	public String getWorkerNo() {
		return this.workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	@Column(name = "ROLE_ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_User_RoleId))
			return false;
		Bset_User_RoleId castOther = (Bset_User_RoleId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWorkerNo() == castOther.getWorkerNo()) || (this
						.getWorkerNo() != null
						&& castOther.getWorkerNo() != null && this
						.getWorkerNo().equals(castOther.getWorkerNo())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getWorkerNo() == null ? 0 : this.getWorkerNo().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

}