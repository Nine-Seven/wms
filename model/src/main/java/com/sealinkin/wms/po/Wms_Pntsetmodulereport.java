package com.sealinkin.wms.po;

import java.math.BigDecimal;
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
 * PntsetModuleReport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTSET_MODULE_REPORT")
public class Wms_Pntsetmodulereport implements java.io.Serializable {

	// Fields

	private Wms_PntsetmodulereportId id;
	private String reportName;
	private BigDecimal orderNo;
	private String status;
	private String rsvVarod1;
	private String rsvVarod2;
	private String rsvVarod3;
	private String rsvVarod4;
	private String rsvVarod5;
	private String rsvVarod6;
	private String rsvVarod7;
	private String rsvVarod8;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Wms_Pntsetmodulereport() {
	}

	/** minimal constructor */
	public Wms_Pntsetmodulereport(Wms_PntsetmodulereportId id, BigDecimal orderNo,
			String status) {
		this.id = id;
		this.orderNo = orderNo;
		this.status = status;
	}

	/** full constructor */
	public Wms_Pntsetmodulereport(Wms_PntsetmodulereportId id, String reportName,
			BigDecimal orderNo, String status, String rsvVarod1,
			String rsvVarod2, String rsvVarod3, String rsvVarod4,
			String rsvVarod5, String rsvVarod6, String rsvVarod7,
			String rsvVarod8, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.reportName = reportName;
		this.orderNo = orderNo;
		this.status = status;
		this.rsvVarod1 = rsvVarod1;
		this.rsvVarod2 = rsvVarod2;
		this.rsvVarod3 = rsvVarod3;
		this.rsvVarod4 = rsvVarod4;
		this.rsvVarod5 = rsvVarod5;
		this.rsvVarod6 = rsvVarod6;
		this.rsvVarod7 = rsvVarod7;
		this.rsvVarod8 = rsvVarod8;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "reportId", column = @Column(name = "REPORT_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "paperType", column = @Column(name = "PAPER_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "taskType", column = @Column(name = "TASK_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "pickType", column = @Column(name = "PICK_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "operateType", column = @Column(name = "OPERATE_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "reportType", column = @Column(name = "REPORT_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "orderType", column = @Column(name = "ORDER_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "deliverObjLevel", column = @Column(name = "DELIVER_OBJ_LEVEL", nullable = false, length = 5)),
			@AttributeOverride(name = "useType", column = @Column(name = "USE_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "shipperNo", column = @Column(name = "SHIPPER_NO", nullable = false, length = 15)) })
	public Wms_PntsetmodulereportId getId() {
		return this.id;
	}

	public void setId(Wms_PntsetmodulereportId id) {
		this.id = id;
	}

	@Column(name = "REPORT_NAME", length = 50)
	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Column(name = "ORDER_NO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RSV_VAROD1", length = 50)
	public String getRsvVarod1() {
		return this.rsvVarod1;
	}

	public void setRsvVarod1(String rsvVarod1) {
		this.rsvVarod1 = rsvVarod1;
	}

	@Column(name = "RSV_VAROD2", length = 50)
	public String getRsvVarod2() {
		return this.rsvVarod2;
	}

	public void setRsvVarod2(String rsvVarod2) {
		this.rsvVarod2 = rsvVarod2;
	}

	@Column(name = "RSV_VAROD3", length = 50)
	public String getRsvVarod3() {
		return this.rsvVarod3;
	}

	public void setRsvVarod3(String rsvVarod3) {
		this.rsvVarod3 = rsvVarod3;
	}

	@Column(name = "RSV_VAROD4", length = 50)
	public String getRsvVarod4() {
		return this.rsvVarod4;
	}

	public void setRsvVarod4(String rsvVarod4) {
		this.rsvVarod4 = rsvVarod4;
	}

	@Column(name = "RSV_VAROD5", length = 50)
	public String getRsvVarod5() {
		return this.rsvVarod5;
	}

	public void setRsvVarod5(String rsvVarod5) {
		this.rsvVarod5 = rsvVarod5;
	}

	@Column(name = "RSV_VAROD6", length = 50)
	public String getRsvVarod6() {
		return this.rsvVarod6;
	}

	public void setRsvVarod6(String rsvVarod6) {
		this.rsvVarod6 = rsvVarod6;
	}

	@Column(name = "RSV_VAROD7", length = 50)
	public String getRsvVarod7() {
		return this.rsvVarod7;
	}

	public void setRsvVarod7(String rsvVarod7) {
		this.rsvVarod7 = rsvVarod7;
	}

	@Column(name = "RSV_VAROD8", length = 50)
	public String getRsvVarod8() {
		return this.rsvVarod8;
	}

	public void setRsvVarod8(String rsvVarod8) {
		this.rsvVarod8 = rsvVarod8;
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