package com.sealinkin.idata.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IdataImportTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_IMPORT_TMP")
public class Idata_ImportTmp implements java.io.Serializable {

	// Fields

	private Idata_ImportTmpId id;

	// Constructors

	/** default constructor */
	public Idata_ImportTmp() {
	}

	/** full constructor */
	public Idata_ImportTmp(Idata_ImportTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "poType", column = @Column(name = "PO_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "poNo", column = @Column(name = "PO_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "supplierNo", column = @Column(name = "SUPPLIER_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "orderDate", column = @Column(name = "ORDER_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "requestDate", column = @Column(name = "REQUEST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "endDate", column = @Column(name = "END_DATE", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "ownerArticleNo", column = @Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "poQty", column = @Column(name = "PO_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "importRemark", column = @Column(name = "IMPORT_REMARK")),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", length = 2)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", precision = 22, scale = 0)),
			@AttributeOverride(name = "unitCost", column = @Column(name = "UNIT_COST", precision = 16, scale = 5)),
			@AttributeOverride(name = "takeType", column = @Column(name = "TAKE_TYPE", nullable = false, length = 1)),
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
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", length = 10)),
			@AttributeOverride(name = "odataexpNo", column = @Column(name = "ODATAEXP_NO", length = 20)),
	        @AttributeOverride(name = "allotQty", column = @Column(name = "ALLOT_QTY", precision = 18, scale = 5)),
	        @AttributeOverride(name = "allotTakeType", column = @Column(name = "ALLOT_TAKE_TYPE", length = 1))})

	public Idata_ImportTmpId getId() {
		return this.id;
	}

	public void setId(Idata_ImportTmpId id) {
		this.id = id;
	}

}