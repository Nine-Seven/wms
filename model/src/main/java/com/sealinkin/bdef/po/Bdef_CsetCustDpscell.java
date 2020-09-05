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
 * CsetCustDpscell entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CSET_CUST_DPSCELL")
public class Bdef_CsetCustDpscell implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_CsetCustDpscellId id;
	private String custNo;
	private String rgstName;
	private Date rgstDate;
	private String status;
	private String deviceGroupNo;
	private String deviceNo;

	// Constructors

	/** default constructor */
	public Bdef_CsetCustDpscell() {
	}

	/** minimal constructor */
	public Bdef_CsetCustDpscell(Bdef_CsetCustDpscellId id) {
		this.id = id;
	}

	/** full constructor */
	public Bdef_CsetCustDpscell(Bdef_CsetCustDpscellId id, String custNo,
			String rgstName, Date rgstDate, String status,
			String deviceGroupNo, String deviceNo) {
		this.id = id;
		this.custNo = custNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.status = status;
		this.deviceGroupNo = deviceGroupNo;
		this.deviceNo = deviceNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "dpsCellNo", column = @Column(name = "DPS_CELL_NO", nullable = false, length = 24)) })
	public Bdef_CsetCustDpscellId getId() {
		return this.id;
	}

	public void setId(Bdef_CsetCustDpscellId id) {
		this.id = id;
	}

	@Column(name = "CUST_NO", length = 10)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "DEVICE_GROUP_NO", length = 5)
	public String getDeviceGroupNo() {
		return this.deviceGroupNo;
	}

	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}

	@Column(name = "DEVICE_NO", length = 20)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
}