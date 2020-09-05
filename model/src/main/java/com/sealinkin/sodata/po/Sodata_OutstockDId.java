package com.sealinkin.sodata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SodataOutstockDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Sodata_OutstockDId implements java.io.Serializable {

	// Fields
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String outstockNo;
	private Integer divideId;

	// Constructors

	/** default constructor */
	public Sodata_OutstockDId() {
	}

	/** full constructor */
	public Sodata_OutstockDId(String warehouseNo, String ownerNo,
			String outstockNo, Integer divideId,String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.outstockNo = outstockNo;
		this.divideId = divideId;
		this.enterpriseNo=enterpriseNo;
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

	@Column(name = "OUTSTOCK_NO", nullable = false, length = 20)
	public String getOutstockNo() {
		return this.outstockNo;
	}

	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}

	@Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)
	public Integer getDivideId() {
		return this.divideId;
	}

	public void setDivideId(Integer divideId) {
		this.divideId = divideId;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((divideId == null) ? 0 : divideId.hashCode());
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result
				+ ((outstockNo == null) ? 0 : outstockNo.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((warehouseNo == null) ? 0 : warehouseNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sodata_OutstockDId other = (Sodata_OutstockDId) obj;
		if (divideId == null) {
			if (other.divideId != null)
				return false;
		} else if (!divideId.equals(other.divideId))
			return false;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (outstockNo == null) {
			if (other.outstockNo != null)
				return false;
		} else if (!outstockNo.equals(other.outstockNo))
			return false;
		if (ownerNo == null) {
			if (other.ownerNo != null)
				return false;
		} else if (!ownerNo.equals(other.ownerNo))
			return false;
		if (warehouseNo == null) {
			if (other.warehouseNo != null)
				return false;
		} else if (!warehouseNo.equals(other.warehouseNo))
			return false;
		return true;
	}


}