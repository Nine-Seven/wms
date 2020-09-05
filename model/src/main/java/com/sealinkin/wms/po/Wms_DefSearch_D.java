package com.sealinkin.wms.po;

import java.math.BigDecimal;
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
 * WmsDefsearchD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFSEARCH_D")
public class Wms_DefSearch_D implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_DefSearch_DId id;
	private String fieldName;
	private String fieldType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private BigDecimal subPgmId;
	private String listSql;
	private BigDecimal seq;
	private String orderType;
	private String tableAlias;
	private String tableName;
	private String statisticsFlag;
	private BigDecimal width;

	// Constructors

	/** default constructor */
	public Wms_DefSearch_D() {
	}

	/** minimal constructor */
	public Wms_DefSearch_D(Wms_DefSearch_DId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_DefSearch_D(Wms_DefSearch_DId id, String fieldName,
			String fieldType, String rgstName, Date rgstDate, String updtName,
			Date updtDate, BigDecimal subPgmId, String listSql, BigDecimal seq,
			String orderType, String tableAlias, String tableName,
			String statisticsFlag, BigDecimal width) {
		this.id = id;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.subPgmId = subPgmId;
		this.listSql = listSql;
		this.seq = seq;
		this.orderType = orderType;
		this.tableAlias = tableAlias;
		this.tableName = tableName;
		this.statisticsFlag = statisticsFlag;
		this.width = width;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "pgmId", column = @Column(name = "PGM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "fieldId", column = @Column(name = "FIELD_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_DefSearch_DId getId() {
		return this.id;
	}

	public void setId(Wms_DefSearch_DId id) {
		this.id = id;
	}

	@Column(name = "FIELD_NAME", length = 30)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "FIELD_TYPE", length = 10)
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", length = 7)
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

	@Column(name = "SUB_PGM_ID", precision = 22, scale = 0)
	public BigDecimal getSubPgmId() {
		return this.subPgmId;
	}

	public void setSubPgmId(BigDecimal subPgmId) {
		this.subPgmId = subPgmId;
	}

	@Column(name = "LIST_SQL", length = 500)
	public String getListSql() {
		return this.listSql;
	}

	public void setListSql(String listSql) {
		this.listSql = listSql;
	}

	@Column(name = "SEQ", precision = 22, scale = 0)
	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	@Column(name = "ORDER_TYPE", length = 4)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "TABLE_ALIAS", length = 10)
	public String getTableAlias() {
		return this.tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	@Column(name = "TABLE_NAME", length = 30)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "STATISTICS_FLAG", length = 1)
	public String getStatisticsFlag() {
		return this.statisticsFlag;
	}

	public void setStatisticsFlag(String statisticsFlag) {
		this.statisticsFlag = statisticsFlag;
	}

	@Column(name = "WIDTH", precision = 22, scale = 0)
	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

}