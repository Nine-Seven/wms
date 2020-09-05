package com.sealinkin.bset.po;

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
 * BsetWorkstationDivide entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_WORKSTATION_DIVIDE")
public class Bset_WorkstationDivide implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bset_WorkstationDivideId id;
	private String deviceGroupName;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_WorkstationDivide() {
	}

	/** minimal constructor */
	public Bset_WorkstationDivide(Bset_WorkstationDivideId id,
			String deviceGroupName, String rgstName, Date rgstDate) {
		this.id = id;
		this.deviceGroupName = deviceGroupName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bset_WorkstationDivide(Bset_WorkstationDivideId id,
			String deviceGroupName, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.deviceGroupName = deviceGroupName;
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
			@AttributeOverride(name = "workstationNo", column = @Column(name = "WORKSTATION_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "deviceGroupNo", column = @Column(name = "DEVICE_GROUP_NO", nullable = false, length = 5)) })
	public Bset_WorkstationDivideId getId() {
		return this.id;
	}

	public void setId(Bset_WorkstationDivideId id) {
		this.id = id;
	}

	@Column(name = "DEVICE_GROUP_NAME", nullable = false, length = 20)
	public String getDeviceGroupName() {
		return this.deviceGroupName;
	}

	public void setDeviceGroupName(String deviceGroupName) {
		this.deviceGroupName = deviceGroupName;
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

}