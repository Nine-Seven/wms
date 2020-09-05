package com.sealinkin.mdata.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MdataPlanTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MDATA_PLAN_TMP")
public class Mdata_PlanTmp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 983446547527857360L;
	private Mdata_PlanTmpId id;

	// Constructors

	/** default constructor */
	public Mdata_PlanTmp() {
	}

	/** full constructor */
	public Mdata_PlanTmp(Mdata_PlanTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "ownerArticleNo", column = @Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "originQty", column = @Column(name = "ORIGIN_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "SCellNo", column = @Column(name = "S_CELL_NO", length = 10)),
			@AttributeOverride(name = "DCellNo", column = @Column(name = "D_CELL_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "planRemark", column = @Column(name = "PLAN_REMARK")),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "produceDate", column = @Column(name = "PRODUCE_DATE",  length = 7)),
			@AttributeOverride(name = "expireDate", column = @Column(name = "EXPIRE_DATE",length = 7)),
			@AttributeOverride(name = "orgNo", column = @Column(name = "ORG_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "quality", column = @Column(name = "QUALITY", length = 2)),
			@AttributeOverride(name = "lotNo", column = @Column(name = "LOT_NO",  length = 32)),
			@AttributeOverride(name = "rsvBatch1", column = @Column(name = "RSV_BATCH1", length = 20)),
			@AttributeOverride(name = "rsvBatch2", column = @Column(name = "RSV_BATCH2", length = 20)),
			@AttributeOverride(name = "rsvBatch3", column = @Column(name = "RSV_BATCH3", length = 20)),
			@AttributeOverride(name = "rsvBatch4", column = @Column(name = "RSV_BATCH4", length = 20)),
			@AttributeOverride(name = "rsvBatch5", column = @Column(name = "RSV_BATCH5", length = 20)),
			@AttributeOverride(name = "rsvBatch6", column = @Column(name = "RSV_BATCH6", length = 20)),
			@AttributeOverride(name = "rsvBatch7", column = @Column(name = "RSV_BATCH7", length = 20)),
			@AttributeOverride(name = "rsvBatch8", column = @Column(name = "RSV_BATCH8", length = 20)) })
	public Mdata_PlanTmpId getId() {
		return this.id;
	}

	public void setId(Mdata_PlanTmpId id) {
		this.id = id;
	}

}