package com.sealinkin.cdef.po;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CdefDefwareLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cdef_DefwareLogId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String wareNo;
	private String wareName;
	private String wareRemark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Cdef_DefwareLogId() {
	}

	/** minimal constructor */
	public Cdef_DefwareLogId(String warehouseNo, String wareNo, String wareName,
			String rgstName, Date rgstDate) {
		this.warehouseNo = warehouseNo;
		this.wareNo = wareNo;
		this.wareName = wareName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cdef_DefwareLogId(String warehouseNo, String wareNo, String wareName,
			String wareRemark, String rgstName, Date rgstDate, String updtName,
			Date updtDate, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.wareNo = wareNo;
		this.wareName = wareName;
		this.wareRemark = wareRemark;
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

	@Column(name = "WARE_NO", nullable = false, length = 5)
	public String getWareNo() {
		return this.wareNo;
	}

	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}

	@Column(name = "WARE_NAME", nullable = false, length = 30)
	public String getWareName() {
		return this.wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	@Column(name = "WARE_REMARK", length = 50)
	public String getWareRemark() {
		return this.wareRemark;
	}

	public void setWareRemark(String wareRemark) {
		this.wareRemark = wareRemark;
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
		if (!(other instanceof Cdef_DefwareLogId))
			return false;
		Cdef_DefwareLogId castOther = (Cdef_DefwareLogId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getWareNo() == castOther.getWareNo()) || (this
						.getWareNo() != null && castOther.getWareNo() != null && this
						.getWareNo().equals(castOther.getWareNo())))
				&& ((this.getWareName() == castOther.getWareName()) || (this
						.getWareName() != null
						&& castOther.getWareName() != null && this
						.getWareName().equals(castOther.getWareName())))
				&& ((this.getWareRemark() == castOther.getWareRemark()) || (this
						.getWareRemark() != null
						&& castOther.getWareRemark() != null && this
						.getWareRemark().equals(castOther.getWareRemark())))
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
				+ (getWareNo() == null ? 0 : this.getWareNo().hashCode());
		result = 37 * result
				+ (getWareName() == null ? 0 : this.getWareName().hashCode());
		result = 37
				* result
				+ (getWareRemark() == null ? 0 : this.getWareRemark()
						.hashCode());
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