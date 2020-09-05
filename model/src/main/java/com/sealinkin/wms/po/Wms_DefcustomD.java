package com.sealinkin.wms.po;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WmsDefcustomD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFCUSTOM_D")
public class Wms_DefcustomD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_DefcustomDId id;
	private String paramType;
	private String tablename;
	private String columnname;
	private BigDecimal seq;

	// Constructors

	/** default constructor */
	public Wms_DefcustomD() {
	}

	/** minimal constructor */
	public Wms_DefcustomD(Wms_DefcustomDId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_DefcustomD(Wms_DefcustomDId id, String paramType,
			String tablename, String columnname, BigDecimal seq) {
		this.id = id;
		this.paramType = paramType;
		this.tablename = tablename;
		this.columnname = columnname;
		this.seq = seq;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "customId", column = @Column(name = "CUSTOM_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "paramName", column = @Column(name = "PARAM_NAME", nullable = false, length = 50)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_DefcustomDId getId() {
		return this.id;
	}

	public void setId(Wms_DefcustomDId id) {
		this.id = id;
	}

	@Column(name = "PARAM_TYPE", length = 10)
	public String getParamType() {
		return this.paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	@Column(name = "TABLENAME", length = 30)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	@Column(name = "COLUMNNAME", length = 30)
	public String getColumnname() {
		return this.columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	@Column(name = "SEQ", precision = 22, scale = 0)
	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

}