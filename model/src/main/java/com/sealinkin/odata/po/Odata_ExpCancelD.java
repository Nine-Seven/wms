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
 * OdataExpCancelD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_EXP_CANCEL_D")
public class Odata_ExpCancelD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_ExpCancelDId id;
	private String ownerNo;
	private String expType;
	private Date operateDate;
	private Date expDate;
	private Double packingQty;
	private Double articleQty;
	private Double realQty;
	private String status;
	private String handleflag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_ExpCancelD() {
	}

	/** minimal constructor */
	public Odata_ExpCancelD(Odata_ExpCancelDId id, String ownerNo,
			String expType, Date operateDate, Date expDate,
			Double packingQty, Double articleQty, String status,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.operateDate = operateDate;
		this.expDate = expDate;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_ExpCancelD(Odata_ExpCancelDId id, String ownerNo,
			String expType, Date operateDate, Date expDate,
			Double packingQty, Double articleQty, Double realQty,
			String status, String handleflag, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.operateDate = operateDate;
		this.expDate = expDate;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.status = status;
		this.handleflag = handleflag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "cancelNo", column = @Column(name = "CANCEL_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "expNo", column = @Column(name = "EXP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)) })
	public Odata_ExpCancelDId getId() {
		return this.id;
	}

	public void setId(Odata_ExpCancelDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 5)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "REAL_QTY", precision = 18, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "HANDLE_FLAG", length = 1)
	public String getHandleflag() {
		return this.handleflag;
	}

	public void setHandleflag(String handleflag) {
		this.handleflag = handleflag;
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