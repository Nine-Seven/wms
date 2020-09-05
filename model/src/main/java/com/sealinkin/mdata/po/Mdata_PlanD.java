package com.sealinkin.mdata.po;

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
 * MdataPlanD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MDATA_PLAN_D" )
public class Mdata_PlanD implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Mdata_PlanDId id;
	private String articleNo;
	private String articleId;
	private Double originQty;
	private String SCellNo;
	private String SContainerNo;
	private String DContainerNo;
	private String DCellNo;
	private Date moveDate;
	private String labelNo;
	private String stockType;
	private String stockValue;

	// Constructors

	/** default constructor */
	public Mdata_PlanD() {
	}

	/** full constructor */
	public Mdata_PlanD(Mdata_PlanDId id, String articleNo, String articleId,
			Double originQty, String SCellNo, String SContainerNo,
			String DContainerNo, String DCellNo, Date moveDate, String labelNo,
			String stockType, String stockValue) {
		this.id = id;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.originQty = originQty;
		this.SCellNo = SCellNo;
		this.SContainerNo = SContainerNo;
		this.DContainerNo = DContainerNo;
		this.DCellNo = DCellNo;
		this.moveDate = moveDate;
		this.labelNo = labelNo;
		this.stockType = stockType;
		this.stockValue = stockValue;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "planNo", column = @Column(name = "PLAN_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)) })
	public Mdata_PlanDId getId() {
		return this.id;
	}

	public void setId(Mdata_PlanDId id) {
		this.id = id;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "ARTICLE_ID", nullable = false, length = 25)
	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Column(name = "ORIGIN_QTY", nullable = false, precision = 15, scale = 5)
	public Double getOriginQty() {
		return this.originQty;
	}

	public void setOriginQty(Double originQty) {
		this.originQty = originQty;
	}

	@Column(name = "S_CELL_NO", nullable = false, length = 10)
	public String getSCellNo() {
		return this.SCellNo;
	}

	public void setSCellNo(String SCellNo) {
		this.SCellNo = SCellNo;
	}

	@Column(name = "S_CONTAINER_NO", nullable = false, length = 25)
	public String getSContainerNo() {
		return this.SContainerNo;
	}

	public void setSContainerNo(String SContainerNo) {
		this.SContainerNo = SContainerNo;
	}

	@Column(name = "D_CONTAINER_NO", nullable = false, length = 25)
	public String getDContainerNo() {
		return this.DContainerNo;
	}

	public void setDContainerNo(String DContainerNo) {
		this.DContainerNo = DContainerNo;
	}

	@Column(name = "D_CELL_NO", nullable = false, length = 10)
	public String getDCellNo() {
		return this.DCellNo;
	}

	public void setDCellNo(String DCellNo) {
		this.DCellNo = DCellNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MOVE_DATE", nullable = false, length = 7)
	public Date getMoveDate() {
		return this.moveDate;
	}

	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Column(name = "STOCK_VALUE", nullable = false, length = 20)
	public String getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

}