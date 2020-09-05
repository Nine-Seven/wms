package com.sealinkin.odata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OdataLocateShortLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_LOCATE_SHORT_LOG")
public class Odata_LocateShortLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_LocateShortLogId id;

	// Constructors

	/** default constructor */
	public Odata_LocateShortLog() {
	}

	/** full constructor */
	public Odata_LocateShortLog(Odata_LocateShortLogId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "waveNo", column = @Column(name = "WAVE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "logSerial", column = @Column(name = "LOG_SERIAL", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "expNo", column = @Column(name = "EXP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "subCustNo", column = @Column(name = "SUB_CUST_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "articleId", column = @Column(name = "ARTICLE_ID", nullable = false, precision = 12, scale = 0)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "stockQty", column = @Column(name = "STOCK_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "locateQty", column = @Column(name = "LOCATE_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "operateType", column = @Column(name = "OPERATE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "shortReason", column = @Column(name = "SHORT_REASON", length = 100)),
			@AttributeOverride(name = "transGroupNo", column = @Column(name = "TRANS_GROUP_NO", nullable = false, precision = 10, scale = 0)) })
	public Odata_LocateShortLogId getId() {
		return this.id;
	}

	public void setId(Odata_LocateShortLogId id) {
		this.id = id;
	}

}