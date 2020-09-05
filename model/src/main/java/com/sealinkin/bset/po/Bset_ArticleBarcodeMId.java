package com.sealinkin.bset.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BsetArticleBarcodeMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_ArticleBarcodeMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String paperNo;
	private Integer serialNo;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Bset_ArticleBarcodeMId() {
	}

	/** minimal constructor */
	public Bset_ArticleBarcodeMId(String warehouseNo, String ownerNo,
			String paperNo, Integer serialNo, String status, String rgstName,
			Date rgstDate) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.paperNo = paperNo;
		this.serialNo = serialNo;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bset_ArticleBarcodeMId(String warehouseNo, String ownerNo,
			String paperNo, Integer serialNo, String status, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.paperNo = paperNo;
		this.serialNo = serialNo;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.enterpriseNo = enterpriseNo;
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

	@Column(name = "PAPER_NO", nullable = false, length = 20)
	public String getPaperNo() {
		return this.paperNo;
	}

	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}

	@Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)
	public Integer getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_ArticleBarcodeMId))
			return false;
		Bset_ArticleBarcodeMId castOther = (Bset_ArticleBarcodeMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getPaperNo() == castOther.getPaperNo()) || (this
						.getPaperNo() != null && castOther.getPaperNo() != null && this
						.getPaperNo().equals(castOther.getPaperNo())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getUpdtName() == castOther.getUpdtName()) || (this
						.getUpdtName() != null
						&& castOther.getUpdtName() != null && this
						.getUpdtName().equals(castOther.getUpdtName())))
				&& ((this.getUpdtDate() == castOther.getUpdtDate()) || (this
						.getUpdtDate() != null
						&& castOther.getUpdtDate() != null && this
						.getUpdtDate().equals(castOther.getUpdtDate())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getPaperNo() == null ? 0 : this.getPaperNo().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getUpdtName() == null ? 0 : this.getUpdtName().hashCode());
		result = 37 * result
				+ (getUpdtDate() == null ? 0 : this.getUpdtDate().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}