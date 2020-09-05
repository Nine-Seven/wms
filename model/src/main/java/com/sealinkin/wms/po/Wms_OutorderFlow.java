package com.sealinkin.wms.po;
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
 * Wms_OutorderFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTORDER_FLOW")
public class Wms_OutorderFlow implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_OutorderFlowId id;
	private short flowValue;
	private String flowFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Wms_OutorderFlow() {
	}

	/** minimal constructor */
	public Wms_OutorderFlow(Wms_OutorderFlowId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_OutorderFlow(Wms_OutorderFlowId id, short flowValue,
			String flowFlag, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.flowValue = flowValue;
		this.flowFlag = flowFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "expType", column = @Column(name = "EXP_TYPE", nullable = false, length = 2)),
			@AttributeOverride(name = "flowOrder", column = @Column(name = "FLOW_ORDER", nullable = false, precision = 3, scale = 0)) })
	public Wms_OutorderFlowId getId() {
		return this.id;
	}

	public void setId(Wms_OutorderFlowId id) {
		this.id = id;
	}

	@Column(name = "FLOW_VALUE", precision = 3, scale = 0)
	public short getFlowValue() {
		return this.flowValue;
	}

	public void setFlowValue(short flowValue) {
		this.flowValue = flowValue;
	}

	@Column(name = "FLOW_FLAG", length = 1)
	public String getFlowFlag() {
		return this.flowFlag;
	}

	public void setFlowFlag(String flowFlag) {
		this.flowFlag = flowFlag;
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