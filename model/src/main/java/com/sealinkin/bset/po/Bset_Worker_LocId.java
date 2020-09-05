package com.sealinkin.bset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BsetWorkerLocId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_Worker_LocId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String workerNo;

	// Constructors

	/** default constructor */
	public Bset_Worker_LocId() {
	}

	/** full constructor */
	public Bset_Worker_LocId(String enterpriseNo, String warehouseNo,
			String workerNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
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

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
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
		if (!(other instanceof Bset_Worker_LocId))
			return false;
		Bset_Worker_LocId castOther = (Bset_Worker_LocId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
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
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getWorkerNo() == null ? 0 : this.getWorkerNo().hashCode());
		return result;
	}

}