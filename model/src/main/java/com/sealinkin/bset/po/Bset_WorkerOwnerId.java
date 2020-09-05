package com.sealinkin.bset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BsetWorkerOwnerId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_WorkerOwnerId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String workerNo;

	// Constructors

	/** default constructor */
	public Bset_WorkerOwnerId() {
	}

	/** full constructor */
	public Bset_WorkerOwnerId(String enterpriseNo, String ownerNo,
			String workerNo) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.workerNo = workerNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 5)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "WORKER_NO", nullable = false, length = 50)
	public String getWorkerNo() {
		return this.workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_WorkerOwnerId))
			return false;
		Bset_WorkerOwnerId castOther = (Bset_WorkerOwnerId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getWorkerNo() == castOther.getWorkerNo()) || (this
						.getWorkerNo() != null
						&& castOther.getWorkerNo() != null && this
						.getWorkerNo().equals(castOther.getWorkerNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getWorkerNo() == null ? 0 : this.getWorkerNo().hashCode());
		return result;
	}

}