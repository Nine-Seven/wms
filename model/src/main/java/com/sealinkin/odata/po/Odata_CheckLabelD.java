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
 * OdataCheckLabelD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_CHECK_LABEL_D")
public class Odata_CheckLabelD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_CheckLabelDId id;
	private String lableNo;
	private Integer divideId;
	private Integer containerId;
	private String expNo;
	private String expType;
	private Date expDate;
	private String articleNo;
	private Double packingQty;
	private Long articleId;
	private Double articleQty;
	private Double realQty;
	private String status;
	private String checkName;
	private Date checkDate;

	// Constructors

	/** default constructor */
	public Odata_CheckLabelD() {
	}

	/** minimal constructor */
	public Odata_CheckLabelD(Odata_CheckLabelDId id, String lableNo,
			Integer divideId, Integer containerId, String expNo,
			String expType, Date expDate, String articleNo, Double packingQty,
			Long articleId, Double articleQty, Double realQty, String status) {
		this.id = id;
		this.lableNo = lableNo;
		this.divideId = divideId;
		this.containerId = containerId;
		this.expNo = expNo;
		this.expType = expType;
		this.expDate = expDate;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.articleId = articleId;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.status = status;
	}

	/** full constructor */
	public Odata_CheckLabelD(Odata_CheckLabelDId id, String lableNo,
			Integer divideId, Integer containerId, String expNo,
			String expType, Date expDate, String articleNo, Double packingQty,
			Long articleId, Double articleQty, Double realQty, String status,
			String checkName, Date checkDate) {
		this.id = id;
		this.lableNo = lableNo;
		this.divideId = divideId;
		this.containerId = containerId;
		this.expNo = expNo;
		this.expType = expType;
		this.expDate = expDate;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.articleId = articleId;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.status = status;
		this.checkName = checkName;
		this.checkDate = checkDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "containerNo", column = @Column(name = "CONTAINER_NO", nullable = false, length = 24)) })
	public Odata_CheckLabelDId getId() {
		return this.id;
	}

	public void setId(Odata_CheckLabelDId id) {
		this.id = id;
	}

	@Column(name = "LABLE_NO", nullable = false, length = 24)
	public String getLableNo() {
		return this.lableNo;
	}

	public void setLableNo(String lableNo) {
		this.lableNo = lableNo;
	}

	@Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)
	public Integer getDivideId() {
		return this.divideId;
	}

	public void setDivideId(Integer divideId) {
		this.divideId = divideId;
	}

	@Column(name = "CONTAINER_ID", nullable = false, precision = 8, scale = 0)
	public Integer getContainerId() {
		return this.containerId;
	}

	public void setContainerId(Integer containerId) {
		this.containerId = containerId;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 3)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "ARTICLE_ID", nullable = false, precision = 12, scale = 0)
	public Long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "REAL_QTY", nullable = false, precision = 18, scale = 5)
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

	@Column(name = "CHECK_NAME", length = 20)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE", length = 7)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

}