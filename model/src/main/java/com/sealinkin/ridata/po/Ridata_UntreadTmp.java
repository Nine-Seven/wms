package com.sealinkin.ridata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RidataUntreadTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RIDATA_UNTREAD_TMP")
public class Ridata_UntreadTmp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_UntreadTmpId id;

	// Constructors

	/** default constructor */
	public Ridata_UntreadTmp() {
	}

	/** full constructor */
	public Ridata_UntreadTmp(Ridata_UntreadTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "poType", column = @Column(name = "PO_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "poNo", column = @Column(name = "PO_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "classType", column = @Column(name = "CLASS_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "untreadDate", column = @Column(name = "UNTREAD_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "requestDate", column = @Column(name = "REQUEST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "untreadRemark", column = @Column(name = "UNTREAD_REMARK")),
			@AttributeOverride(name = "untreadFlag", column = @Column(name = "UNTREAD_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "stockType", column = @Column(name = "STOCK_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "stockValue", column = @Column(name = "STOCK_VALUE", nullable = false, length = 20)),
			@AttributeOverride(name = "expNo", column = @Column(name = "EXP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "quality", column = @Column(name = "QUALITY", nullable = false, length = 2)),
			@AttributeOverride(name = "orgNo", column = @Column(name = "ORG_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "takeType", column = @Column(name = "TAKE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "ownerArticleNo", column = @Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "poQty", column = @Column(name = "PO_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "unitCost", column = @Column(name = "UNIT_COST", precision = 18, scale = 5)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvVarod1", column = @Column(name = "RSV_VAROD1", length = 50)),
			@AttributeOverride(name = "rsvVarod2", column = @Column(name = "RSV_VAROD2", length = 50)),
			@AttributeOverride(name = "rsvVarod3", column = @Column(name = "RSV_VAROD3", length = 50)),
			@AttributeOverride(name = "rsvVarod4", column = @Column(name = "RSV_VAROD4", length = 50)),
			@AttributeOverride(name = "rsvVarod5", column = @Column(name = "RSV_VAROD5", length = 50)),
			@AttributeOverride(name = "rsvVarod6", column = @Column(name = "RSV_VAROD6", length = 50)),
			@AttributeOverride(name = "rsvVarod7", column = @Column(name = "RSV_VAROD7", length = 50)),
			@AttributeOverride(name = "rsvVarod8", column = @Column(name = "RSV_VAROD8", length = 50)),
			@AttributeOverride(name = "rsvNum1", column = @Column(name = "RSV_NUM1", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvNum2", column = @Column(name = "RSV_NUM2", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvNum3", column = @Column(name = "RSV_NUM3", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvDate1", column = @Column(name = "RSV_DATE1", length = 7)),
			@AttributeOverride(name = "rsvDate2", column = @Column(name = "RSV_DATE2", length = 7)),
			@AttributeOverride(name = "rsvDate3", column = @Column(name = "RSV_DATE3", length = 7)),
			@AttributeOverride(name = "carPlanNo", column = @Column(name = "CAR_PLAN_NO", length = 20)) })
	public Ridata_UntreadTmpId getId() {
		return this.id;
	}

	public void setId(Ridata_UntreadTmpId id) {
		this.id = id;
	}

}