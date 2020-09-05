package com.sealinkin.wms.po;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WmsDefmodulequerycolumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFMODULEQUERYCOLUMN")
public class Wms_Defmodulequerycolumn implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_DefmodulequerycolumnId id;
	private String columnname;
	private String xtype;
	private String fieldtable;
	private String fieldcolumn;
	private BigDecimal orderno;

	// Constructors

	/** default constructor */
	public Wms_Defmodulequerycolumn() {
	}

	/** minimal constructor */
	public Wms_Defmodulequerycolumn(Wms_DefmodulequerycolumnId id,
			String columnname, String xtype, BigDecimal orderno) {
		this.id = id;
		this.columnname = columnname;
		this.xtype = xtype;
		this.orderno = orderno;
	}

	/** full constructor */
	public Wms_Defmodulequerycolumn(Wms_DefmodulequerycolumnId id,
			String columnname, String xtype, String fieldtable,
			String fieldcolumn, BigDecimal orderno) {
		this.id = id;
		this.columnname = columnname;
		this.xtype = xtype;
		this.fieldtable = fieldtable;
		this.fieldcolumn = fieldcolumn;
		this.orderno = orderno;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "moduleid", column = @Column(name = "MODULEID", nullable = false, length = 16)),
			@AttributeOverride(name = "columnid", column = @Column(name = "COLUMNID", nullable = false, length = 64)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_DefmodulequerycolumnId getId() {
		return this.id;
	}

	public void setId(Wms_DefmodulequerycolumnId id) {
		this.id = id;
	}

	@Column(name = "COLUMNNAME", nullable = false, length = 128)
	public String getColumnname() {
		return this.columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	@Column(name = "XTYPE", nullable = false, length = 32)
	public String getXtype() {
		return this.xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	@Column(name = "FIELDTABLE", length = 50)
	public String getFieldtable() {
		return this.fieldtable;
	}

	public void setFieldtable(String fieldtable) {
		this.fieldtable = fieldtable;
	}

	@Column(name = "FIELDCOLUMN", length = 50)
	public String getFieldcolumn() {
		return this.fieldcolumn;
	}

	public void setFieldcolumn(String fieldcolumn) {
		this.fieldcolumn = fieldcolumn;
	}

	@Column(name = "ORDERNO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getOrderno() {
		return this.orderno;
	}

	public void setOrderno(BigDecimal orderno) {
		this.orderno = orderno;
	}

}