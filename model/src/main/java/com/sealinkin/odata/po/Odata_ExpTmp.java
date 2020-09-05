package com.sealinkin.odata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OdataExpTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_EXP_TMP")
public class Odata_ExpTmp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Odata_ExpTmpId id;

	// Constructors

	/** default constructor */
	public Odata_ExpTmp() {
	}

	/** full constructor */
	public Odata_ExpTmp(Odata_ExpTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "expType", column = @Column(name = "EXP_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "sourceexpNo", column = @Column(name = "SOURCEEXP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "fastFlag", column = @Column(name = "FAST_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "ownerArticleNo", column = @Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "articleQty", column = @Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "lotnoCondition", column = @Column(name = "LOTNO_CONDITION", length = 100)),
			@AttributeOverride(name = "lotnoValue1", column = @Column(name = "LOTNO_VALUE1", length = 50)),
			@AttributeOverride(name = "lotnoValue2", column = @Column(name = "LOTNO_VALUE2", length = 50)),
			@AttributeOverride(name = "expRemark", column = @Column(name = "EXP_REMARK")),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "specifyField", column = @Column(name = "SPECIFY_FIELD", length = 50)),
			@AttributeOverride(name = "specifyCondition", column = @Column(name = "SPECIFY_CONDITION", length = 100)),
			@AttributeOverride(name = "specifyValue1", column = @Column(name = "SPECIFY_VALUE1", length = 50)),
			@AttributeOverride(name = "specifyValue2", column = @Column(name = "SPECIFY_VALUE2", length = 50)),
			@AttributeOverride(name = "unitCost", column = @Column(name = "UNIT_COST", precision = 11, scale = 5)),
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
			@AttributeOverride(name = "expDate", column = @Column(name = "EXP_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "takeType", column = @Column(name = "TAKE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "produceCondition", column = @Column(name = "PRODUCE_CONDITION", length = 100)),
			@AttributeOverride(name = "produceValue1", column = @Column(name = "PRODUCE_VALUE1", length = 7)),
			@AttributeOverride(name = "produceValue2", column = @Column(name = "PRODUCE_VALUE2", length = 7)),
			@AttributeOverride(name = "contactorName", column = @Column(name = "CONTACTOR_NAME", length = 50)),
			@AttributeOverride(name = "shipperNo", column = @Column(name = "SHIPPER_NO", length = 15)),
	        @AttributeOverride(name = "shipperDeliverNo", column = @Column(name = "SHIPPER_DELIVER_NO", length = 30)),
	        @AttributeOverride(name = "orderSource", column = @Column(name = "ORDER_SOURCE", length = 2)) })

	

    
	
	public Odata_ExpTmpId getId() {
		return this.id;
	}

	public void setId(Odata_ExpTmpId id) {
		this.id = id;
	}

}