package com.sealinkin.idata.po;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IdataGuardRgiste entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_GUARD_RGISTE")
public class Idata_GuardRgiste implements java.io.Serializable {

	// Fields

	private Idata_GuardRgisteId id;
	private String dockNo;
	private Date registeDate;
	private String carNo;
	private String driverNo;
	private String driverLicenseNo;
	private Double guestCardNumber;
	private Date beginUnloadDate;
	private Date endUnloadDate;
	private Date leaveDate;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Idata_GuardRgiste() {
	}

	/** minimal constructor */
	public Idata_GuardRgiste(Idata_GuardRgisteId id, Date registeDate,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.registeDate = registeDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Idata_GuardRgiste(Idata_GuardRgisteId id, String dockNo,
			Date registeDate, String carNo, String driverNo,
			String driverLicenseNo, Double guestCardNumber,
			Date beginUnloadDate, Date endUnloadDate, Date leaveDate,
			String status, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.dockNo = dockNo;
		this.registeDate = registeDate;
		this.carNo = carNo;
		this.driverNo = driverNo;
		this.driverLicenseNo = driverLicenseNo;
		this.guestCardNumber = guestCardNumber;
		this.beginUnloadDate = beginUnloadDate;
		this.endUnloadDate = endUnloadDate;
		this.leaveDate = leaveDate;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "orderSerial", column = @Column(name = "ORDER_SERIAL", nullable = false, length = 20)),
			@AttributeOverride(name = "supplierNo", column = @Column(name = "SUPPLIER_NO", nullable = false, length = 10)) })
	public Idata_GuardRgisteId getId() {
		return this.id;
	}

	public void setId(Idata_GuardRgisteId id) {
		this.id = id;
	}

	@Column(name = "DOCK_NO", length = 20)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTE_DATE", nullable = false, length = 7)
	public Date getRegisteDate() {
		return this.registeDate;
	}

	public void setRegisteDate(Date registeDate) {
		this.registeDate = registeDate;
	}

	@Column(name = "CAR_NO", length = 15)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "DRIVER_NO", length = 20)
	public String getDriverNo() {
		return this.driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	@Column(name = "DRIVER_LICENSE_NO", length = 20)
	public String getDriverLicenseNo() {
		return this.driverLicenseNo;
	}

	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}

	@Column(name = "GUEST_CARD_NUMBER", precision = 18, scale = 5)
	public Double getGuestCardNumber() {
		return this.guestCardNumber;
	}

	public void setGuestCardNumber(Double guestCardNumber) {
		this.guestCardNumber = guestCardNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGIN_UNLOAD_DATE", length = 7)
	public Date getBeginUnloadDate() {
		return this.beginUnloadDate;
	}

	public void setBeginUnloadDate(Date beginUnloadDate) {
		this.beginUnloadDate = beginUnloadDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_UNLOAD_DATE", length = 7)
	public Date getEndUnloadDate() {
		return this.endUnloadDate;
	}

	public void setEndUnloadDate(Date endUnloadDate) {
		this.endUnloadDate = endUnloadDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEAVE_DATE", length = 7)
	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column(name = "STATUS", length = 2)
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

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}