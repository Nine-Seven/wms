package com.sealinkin.bdef.po;

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
 * BdefDefshipper entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFSHIPPER")
public class Bdef_Defshipper implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Bdef_DefshipperId id;
	private String shipperName;
	private String address;
	private String tel;
	private String contact;
	private String status;
	private Double disprice;
	private Double graprice;
	private Date compactDate;
	private Date endDate;
	private BigDecimal multi;
	private String memo;
	private Double volprice;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String reportId;
	private String paperType;
	private String singleLocateFlag;
	private String shipperType;
	private String paperComifireFlag;
	private String getPaperType;

	// Constructors

	/** default constructor */
	public Bdef_Defshipper() {
	}

	/** minimal constructor */
	public Bdef_Defshipper(Bdef_DefshipperId id) {
		this.id = id;
	}

	/** full constructor */
	public Bdef_Defshipper(Bdef_DefshipperId id, String shipperName,
			String address, String tel, String contact, String status,
			Double disprice, Double graprice, Date compactDate, Date endDate,
			BigDecimal multi, String memo, Double volprice, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String reportId,
			String paperType, String singleLocateFlag, String shipperType,
			String paperComifireFlag, String getPaperType) {
		this.id = id;
		this.shipperName = shipperName;
		this.address = address;
		this.tel = tel;
		this.contact = contact;
		this.status = status;
		this.disprice = disprice;
		this.graprice = graprice;
		this.compactDate = compactDate;
		this.endDate = endDate;
		this.multi = multi;
		this.memo = memo;
		this.volprice = volprice;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.reportId = reportId;
		this.paperType = paperType;
		this.singleLocateFlag = singleLocateFlag;
		this.shipperType = shipperType;
		this.paperComifireFlag = paperComifireFlag;
		this.getPaperType = getPaperType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "shipperNo", column = @Column(name = "SHIPPER_NO", nullable = false, length = 15)) })
	public Bdef_DefshipperId getId() {
		return this.id;
	}

	public void setId(Bdef_DefshipperId id) {
		this.id = id;
	}

	@Column(name = "SHIPPER_NAME", length = 100)
	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TEL", length = 15)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "CONTACT", length = 20)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DISPRICE", precision = 5)
	public Double getDisprice() {
		return this.disprice;
	}

	public void setDisprice(Double disprice) {
		this.disprice = disprice;
	}

	@Column(name = "GRAPRICE", precision = 5)
	public Double getGraprice() {
		return this.graprice;
	}

	public void setGraprice(Double graprice) {
		this.graprice = graprice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPACT_DATE", length = 7)
	public Date getCompactDate() {
		return this.compactDate;
	}

	public void setCompactDate(Date compactDate) {
		this.compactDate = compactDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "MULTI", precision = 22, scale = 0)
	public BigDecimal getMulti() {
		return this.multi;
	}

	public void setMulti(BigDecimal multi) {
		this.multi = multi;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "VOLPRICE", precision = 5)
	public Double getVolprice() {
		return this.volprice;
	}

	public void setVolprice(Double volprice) {
		this.volprice = volprice;
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

	@Column(name = "REPORT_ID", length = 20)
	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "PAPER_TYPE", length = 2)
	public String getPaperType() {
		return this.paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	@Column(name = "SINGLE_LOCATE_FLAG", length = 2)
	public String getSingleLocateFlag() {
		return this.singleLocateFlag;
	}

	public void setSingleLocateFlag(String singleLocateFlag) {
		this.singleLocateFlag = singleLocateFlag;
	}

	@Column(name = "SHIPPER_TYPE", length = 2)
	public String getShipperType() {
		return this.shipperType;
	}

	public void setShipperType(String shipperType) {
		this.shipperType = shipperType;
	}

	@Column(name = "PAPER_COMIFIRE_FLAG", length = 2)
	public String getPaperComifireFlag() {
		return this.paperComifireFlag;
	}

	public void setPaperComifireFlag(String paperComifireFlag) {
		this.paperComifireFlag = paperComifireFlag;
	}

	@Column(name = "GET_PAPER_TYPE", length = 2)
	public String getGetPaperType() {
		return this.getPaperType;
	}

	public void setGetPaperType(String getPaperType) {
		this.getPaperType = getPaperType;
	}

}