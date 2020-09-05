package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefUploadfileId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_UploadfileId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String fileName;

	// Constructors

	/** default constructor */
	public Bdef_UploadfileId() {
	}

	/** full constructor */
	public Bdef_UploadfileId(String enterpriseNo, String warehouseNo,
			String fileName) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.fileName = fileName;
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

	@Column(name = "FILE_NAME", nullable = false, length = 40)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_UploadfileId))
			return false;
		Bdef_UploadfileId castOther = (Bdef_UploadfileId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getFileName() == castOther.getFileName()) || (this
						.getFileName() != null
						&& castOther.getFileName() != null && this
						.getFileName().equals(castOther.getFileName())));
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
				+ (getFileName() == null ? 0 : this.getFileName().hashCode());
		return result;
	}

}