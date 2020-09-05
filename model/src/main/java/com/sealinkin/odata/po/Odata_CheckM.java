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
 * OdataCheckM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_CHECK_M")
public class Odata_CheckM implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Odata_CheckMId id;
	private Date operateDate;
	private String batchNo;
	private String custNo;
	private String status;
	private String checkChuteNo;
	private String deliverObj;
	private String lineNo;
	private String currArea;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_CheckM() {
	}

	/** minimal constructor */
	public Odata_CheckM(Odata_CheckMId id, Date operateDate, String batchNo,
			String status, String rgstName, Date rgstDate) {
		this.id = id;
		this.operateDate = operateDate;
		this.batchNo = batchNo;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_CheckM(Odata_CheckMId id, Date operateDate, String batchNo,
			String custNo, String status, String checkChuteNo,
			String deliverObj, String lineNo, String currArea, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.operateDate = operateDate;
		this.batchNo = batchNo;
		this.custNo = custNo;
		this.status = status;
		this.checkChuteNo = checkChuteNo;
		this.deliverObj = deliverObj;
		this.lineNo = lineNo;
		this.currArea = currArea;
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
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)) })
	public Odata_CheckMId getId() {
		return this.id;
	}

	public void setId(Odata_CheckMId id) {
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

	@Column(name = "BATCH_NO", nullable = false, length = 20)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "CUST_NO", length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CHECK_CHUTE_NO", length = 3)
	public String getCheckChuteNo() {
		return this.checkChuteNo;
	}

	public void setCheckChuteNo(String checkChuteNo) {
		this.checkChuteNo = checkChuteNo;
	}

	@Column(name = "DELIVER_OBJ", length = 20)
	public String getDeliverObj() {
		return this.deliverObj;
	}

	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}

	@Column(name = "LINE_NO", length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "CURR_AREA", length = 24)
	public String getCurrArea() {
		return this.currArea;
	}

	public void setCurrArea(String currArea) {
		this.currArea = currArea;
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