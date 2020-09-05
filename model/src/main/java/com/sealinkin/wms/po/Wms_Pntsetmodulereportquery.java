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
 * PntsetModuleReportQuery entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTSET_MODULE_REPORT_QUERY")
public class Wms_Pntsetmodulereportquery implements java.io.Serializable {

	// Fields

	private Wms_PntsetmodulereportqueryId id;
	private String columnname;
	private String xtype;
	private BigDecimal orderno;
	private String fieldtable;
	private String fieldcolumn;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Wms_Pntsetmodulereportquery() {
	}

	/** minimal constructor */
	public Wms_Pntsetmodulereportquery(Wms_PntsetmodulereportqueryId id,
			BigDecimal orderno) {
		this.id = id;
		this.orderno = orderno;
	}

	/** full constructor */
	public Wms_Pntsetmodulereportquery(Wms_PntsetmodulereportqueryId id,
			String columnname, String xtype, BigDecimal orderno,
			String fieldtable, String fieldcolumn, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.columnname = columnname;
		this.xtype = xtype;
		this.orderno = orderno;
		this.fieldtable = fieldtable;
		this.fieldcolumn = fieldcolumn;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false, length = 24)),
			@AttributeOverride(name = "columnid", column = @Column(name = "COLUMNID", nullable = false, length = 64)) })
	public Wms_PntsetmodulereportqueryId getId() {
		return this.id;
	}

	public void setId(Wms_PntsetmodulereportqueryId id) {
		this.id = id;
	}

	@Column(name = "COLUMNNAME", length = 128)
	public String getColumnname() {
		return this.columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	@Column(name = "XTYPE", length = 32)
	public String getXtype() {
		return this.xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	@Column(name = "ORDERNO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getOrderno() {
		return this.orderno;
	}

	public void setOrderno(BigDecimal orderno) {
		this.orderno = orderno;
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

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}
