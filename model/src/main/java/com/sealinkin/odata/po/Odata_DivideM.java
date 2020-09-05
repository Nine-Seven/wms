package com.sealinkin.odata.po;

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
 * OdataDivideM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_DIVIDE_M" )
public class Odata_DivideM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_DivideMId id;
	private Date operateDate;
	private String divideType;
	private String batchNo;
	private String status;
	private Date expDate;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_DivideM() {
	}

	/** minimal constructor */
	public Odata_DivideM(Odata_DivideMId id, Date operateDate, String divideType,
			String batchNo, String status, Date expDate, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.operateDate = operateDate;
		this.divideType = divideType;
		this.batchNo = batchNo;
		this.status = status;
		this.expDate = expDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_DivideM(Odata_DivideMId id, Date operateDate, String divideType,
			String batchNo, String status, Date expDate, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.operateDate = operateDate;
		this.divideType = divideType;
		this.batchNo = batchNo;
		this.status = status;
		this.expDate = expDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "divideNo", column = @Column(name = "DIVIDE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)) })
	public Odata_DivideMId getId() {
		return this.id;
	}

	public void setId(Odata_DivideMId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "DIVIDE_TYPE", nullable = false, length = 1)
	public String getDivideType() {
		return this.divideType;
	}

	public void setDivideType(String divideType) {
		this.divideType = divideType;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
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