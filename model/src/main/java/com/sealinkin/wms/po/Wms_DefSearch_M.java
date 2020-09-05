package com.sealinkin.wms.po;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WmsDefsearchM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFSEARCH_M")
public class Wms_DefSearch_M implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_DefSearch_MId id;
	private String preparedSql;
	private String totalSql;
	private BigDecimal needOwner;
	private String ownerExpression;
	private String tableAlias;
	private BigDecimal needLoc;
	private String locExpression;
	private String procName;
	private BigDecimal needEnterpriseNo;
	private String beforeTreatment;
	private String afterTreatment;

	// Constructors

	/** default constructor */
	public Wms_DefSearch_M() {
	}

	/** minimal constructor */
	public Wms_DefSearch_M(Wms_DefSearch_MId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_DefSearch_M(Wms_DefSearch_MId id, String preparedSql,
			String totalSql, BigDecimal needOwner, String ownerExpression,
			String tableAlias, BigDecimal needLoc, String locExpression,
			String procName, BigDecimal needEnterpriseNo,
			String beforeTreatment, String afterTreatment) {
		this.id = id;
		this.preparedSql = preparedSql;
		this.totalSql = totalSql;
		this.needOwner = needOwner;
		this.ownerExpression = ownerExpression;
		this.tableAlias = tableAlias;
		this.needLoc = needLoc;
		this.locExpression = locExpression;
		this.procName = procName;
		this.needEnterpriseNo = needEnterpriseNo;
		this.beforeTreatment = beforeTreatment;
		this.afterTreatment = afterTreatment;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "pgmId", column = @Column(name = "PGM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_DefSearch_MId getId() {
		return this.id;
	}

	public void setId(Wms_DefSearch_MId id) {
		this.id = id;
	}

	@Column(name = "PREPARED_SQL", length = 4000)
	public String getPreparedSql() {
		return this.preparedSql;
	}

	public void setPreparedSql(String preparedSql) {
		this.preparedSql = preparedSql;
	}

	@Column(name = "TOTAL_SQL", length = 4000)
	public String getTotalSql() {
		return this.totalSql;
	}

	public void setTotalSql(String totalSql) {
		this.totalSql = totalSql;
	}

	@Column(name = "NEED_OWNER", precision = 22, scale = 0)
	public BigDecimal getNeedOwner() {
		return this.needOwner;
	}

	public void setNeedOwner(BigDecimal needOwner) {
		this.needOwner = needOwner;
	}

	@Column(name = "OWNER_EXPRESSION", length = 128)
	public String getOwnerExpression() {
		return this.ownerExpression;
	}

	public void setOwnerExpression(String ownerExpression) {
		this.ownerExpression = ownerExpression;
	}

	@Column(name = "TABLE_ALIAS", length = 10)
	public String getTableAlias() {
		return this.tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	@Column(name = "NEED_LOC", precision = 22, scale = 0)
	public BigDecimal getNeedLoc() {
		return this.needLoc;
	}

	public void setNeedLoc(BigDecimal needLoc) {
		this.needLoc = needLoc;
	}

	@Column(name = "LOC_EXPRESSION", length = 128)
	public String getLocExpression() {
		return this.locExpression;
	}

	public void setLocExpression(String locExpression) {
		this.locExpression = locExpression;
	}

	@Column(name = "PROC_NAME", length = 30)
	public String getProcName() {
		return this.procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	@Column(name = "NEED_ENTERPRISE_NO", precision = 22, scale = 0)
	public BigDecimal getNeedEnterpriseNo() {
		return this.needEnterpriseNo;
	}

	public void setNeedEnterpriseNo(BigDecimal needEnterpriseNo) {
		this.needEnterpriseNo = needEnterpriseNo;
	}

	@Column(name = "BEFORE_TREATMENT", length = 4000)
	public String getBeforeTreatment() {
		return this.beforeTreatment;
	}

	public void setBeforeTreatment(String beforeTreatment) {
		this.beforeTreatment = beforeTreatment;
	}

	@Column(name = "AFTER_TREATMENT", length = 4000)
	public String getAfterTreatment() {
		return this.afterTreatment;
	}

	public void setAfterTreatment(String afterTreatment) {
		this.afterTreatment = afterTreatment;
	}

}