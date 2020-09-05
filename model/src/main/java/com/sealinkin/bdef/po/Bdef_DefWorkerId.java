package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefDefworkerId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_DefWorkerId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String workerNo;

	// Constructors

	/** default constructor */
	public Bdef_DefWorkerId() {
	}

	/** full constructor */
	public Bdef_DefWorkerId(String enterpriseNo, String workerNo) {
		this.enterpriseNo = enterpriseNo;
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

	@Column(name = "WORKER_NO", nullable = false, length = 10)
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
		if (!(other instanceof Bdef_DefWorkerId))
			return false;
		Bdef_DefWorkerId castOther = (Bdef_DefWorkerId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
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
				+ (getWorkerNo() == null ? 0 : this.getWorkerNo().hashCode());
		return result;
	}

}