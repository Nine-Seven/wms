package com.sealinkin.cdef.po;
// default package

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
 * CdefDefware entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CDEF_DEFWARE")
public class Cdef_Defware implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cdef_DefwareId id;
	private String wareName;
	private String wareRemark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String orgNo;
	
	// Constructors

	/** default constructor */
	public Cdef_Defware() {
	}

	/** minimal constructor */
	public Cdef_Defware(Cdef_DefwareId id, String wareName, String rgstName,
			Date rgstDate,String orgNo) {
		this.id = id;
		this.wareName = wareName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.orgNo = orgNo;
	}

	/** full constructor */
	public Cdef_Defware(Cdef_DefwareId id, String wareName, String wareRemark,
			String rgstName, Date rgstDate, String updtName, Date updtDate,String orgNo) {
		this.id = id;
		this.wareName = wareName;
		this.wareRemark = wareRemark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.orgNo = orgNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)) })
	public Cdef_DefwareId getId() {
		return this.id;
	}

	public void setId(Cdef_DefwareId id) {
		this.id = id;
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

	@Column(name = "ORG_NO", length = 20)
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	
}