package com.sealinkin.rodata.po;
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
 * Rodata_BoxD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_BOX_D")
public class Rodata_BoxD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_BoxDId id;
	private String ownerNo;
	private String outstockNo;
	private String status;
	private short poId;
	private String articleNo;
	private long articleId;
	private double packingQty;
	private double articleQty;
	private String subLabelNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Rodata_BoxD() {
	}

	/** minimal constructor */
	public Rodata_BoxD(Rodata_BoxDId id, String ownerNo, String outstockNo,
			String status, short poId, String articleNo, long articleId,
			double packingQty, double articleQty, String rgstName, Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.outstockNo = outstockNo;
		this.status = status;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Rodata_BoxD(Rodata_BoxDId id, String ownerNo, String outstockNo,
			String status, short poId, String articleNo, long articleId,
			double packingQty, double articleQty, String subLabelNo,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.outstockNo = outstockNo;
		this.status = status;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.subLabelNo = subLabelNo;
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
			@AttributeOverride(name = "labelNo", column = @Column(name = "LABEL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, length = 10)),
			@AttributeOverride(name = "recedeNo", column = @Column(name = "RECEDE_NO", nullable = false, length = 20)) })
	public Rodata_BoxDId getId() {
		return this.id;
	}

	public void setId(Rodata_BoxDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "OUTSTOCK_NO", nullable = false, length = 24)
	public String getOutstockNo() {
		return this.outstockNo;
	}

	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)
	public short getPoId() {
		return this.poId;
	}

	public void setPoId(short poId) {
		this.poId = poId;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "ARTICLE_ID", nullable = false, precision = 12, scale = 0)
	public long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 14, scale = 5)
	public double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "SUB_LABEL_NO", length = 24)
	public String getSubLabelNo() {
		return this.subLabelNo;
	}

	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
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