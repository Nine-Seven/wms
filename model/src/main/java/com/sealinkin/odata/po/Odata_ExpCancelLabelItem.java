package com.sealinkin.odata.po;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * OdataExpCancelLabelItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_EXP_CANCEL_LABEL_ITEM")
public class Odata_ExpCancelLabelItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_ExpCancelLabelItemId id;
	private String ownerNo;
	private String expType;
	private Date operateDate;
	private Date expDate;
	private String articleNo;
	private String articleId;
	private Double packingQty;
	private Double realQty;
	private String DLabelNo;
	private String status;
	private String sourceNo;
	private String containerNo;
	private Integer divideId;
	private Long rowId;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String ownerCellNo;

	// Constructors

	/** default constructor */
	public Odata_ExpCancelLabelItem() {
	}

	/** minimal constructor */
	public Odata_ExpCancelLabelItem(Odata_ExpCancelLabelItemId id,
			String ownerNo, String expType, Date operateDate,
			Date expDate, String articleNo, String articleId,
			Double packingQty, Double realQty, String DLabelNo, String status,
			String sourceNo, String containerNo, Integer divideId, Long rowId,
			String rgstName, Date rgstDate, String ownerCellNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.operateDate = operateDate;
		this.expDate = expDate;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.realQty = realQty;
		this.DLabelNo = DLabelNo;
		this.status = status;
		this.sourceNo = sourceNo;
		this.containerNo = containerNo;
		this.divideId = divideId;
		this.rowId = rowId;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.ownerCellNo = ownerCellNo;
	}

	/** full constructor */
	public Odata_ExpCancelLabelItem(Odata_ExpCancelLabelItemId id,
			String ownerNo, String expType, Date operateDate,
			Date expDate, String articleNo, String articleId,
			Double packingQty, Double realQty, String DLabelNo, String status,
			String sourceNo, String containerNo, Integer divideId, Long rowId,
			String rgstName, Date rgstDate, String updtName,
			Date updtDate, String ownerCellNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.operateDate = operateDate;
		this.expDate = expDate;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.realQty = realQty;
		this.DLabelNo = DLabelNo;
		this.status = status;
		this.sourceNo = sourceNo;
		this.containerNo = containerNo;
		this.divideId = divideId;
		this.rowId = rowId;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.ownerCellNo = ownerCellNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "cancelNo", column = @Column(name = "CANCEL_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "expNo", column = @Column(name = "EXP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "labelNo", column = @Column(name = "LABEL_NO", nullable = false, length = 24)) })
	public Odata_ExpCancelLabelItemId getId() {
		return this.id;
	}

	public void setId(Odata_ExpCancelLabelItemId id) {
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

	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

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

	@Column(name = "ARTICLE_ID", nullable = false, length = 15)
	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "REAL_QTY", nullable = false, precision = 18, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "D_LABEL_NO", nullable = false, length = 24)
	public String getDLabelNo() {
		return this.DLabelNo;
	}

	public void setDLabelNo(String DLabelNo) {
		this.DLabelNo = DLabelNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "CONTAINER_NO", nullable = false, length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	@Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)
	public Integer getDivideId() {
		return this.divideId;
	}

	public void setDivideId(Integer divideId) {
		this.divideId = divideId;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)
	public Long getRowId() {
		return this.rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "OWNER_CELL_NO", nullable = false, length = 24)
	public String getOwnerCellNo() {
		return this.ownerCellNo;
	}

	public void setOwnerCellNo(String ownerCellNo) {
		this.ownerCellNo = ownerCellNo;
	}

}