package com.sealinkin.bdef.po;

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
 * DeviceDivideGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEVICE_DIVIDE_GROUP")
public class Device_Divide_Group implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Device_Divide_GroupId id;
	private String deviceGroupName;
	private String defaultFlag;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Device_Divide_Group() {
	}

	/** minimal constructor */
	public Device_Divide_Group(Device_Divide_GroupId id, String deviceGroupName,
			String defaultFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.deviceGroupName = deviceGroupName;
		this.defaultFlag = defaultFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Device_Divide_Group(Device_Divide_GroupId id, String deviceGroupName,
			String defaultFlag, String status, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.deviceGroupName = deviceGroupName;
		this.defaultFlag = defaultFlag;
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
			@AttributeOverride(name = "deviceGroupNo", column = @Column(name = "DEVICE_GROUP_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "useType", column = @Column(name = "USE_TYPE", nullable = false, length = 1)) })
	public Device_Divide_GroupId getId() {
		return this.id;
	}

	public void setId(Device_Divide_GroupId id) {
		this.id = id;
	}

	@Column(name = "DEVICE_GROUP_NAME", nullable = false, length = 20)
	public String getDeviceGroupName() {
		return this.deviceGroupName;
	}

	public void setDeviceGroupName(String deviceGroupName) {
		this.deviceGroupName = deviceGroupName;
	}

	@Column(name = "DEFAULT_FLAG", nullable = false, length = 1)
	public String getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Column(name = "STATUS", length = 1)
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

	@Column(name = "UPDT_NAME", length = 10)
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