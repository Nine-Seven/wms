package com.sealinkin.cset.po;

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
 * CsetAreaBackupM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CSET_AREA_BACKUP_M")
public class Cset_AreaBackupM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cset_AreaBackupMId id;
	private String lineName;
	private String defaultFlag;
	private String rgstName;
	private Date rgstDate;
	private String SWareNo;
	private String SAreaNo;
	private String SStockNo;

	// Constructors

	/** default constructor */
	public Cset_AreaBackupM() {
	}

	/** full constructor */
	public Cset_AreaBackupM(Cset_AreaBackupMId id, String lineName,
			String defaultFlag, String rgstName, Date rgstDate, String SWareNo,
			String SAreaNo, String SStockNo) {
		this.id = id;
		this.lineName = lineName;
		this.defaultFlag = defaultFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.SWareNo = SWareNo;
		this.SAreaNo = SAreaNo;
		this.SStockNo = SStockNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "lineId", column = @Column(name = "LINE_ID", nullable = false, precision = 2, scale = 0)) })
	public Cset_AreaBackupMId getId() {
		return this.id;
	}

	public void setId(Cset_AreaBackupMId id) {
		this.id = id;
	}

	@Column(name = "LINE_NAME", nullable = false, length = 50)
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "DEFAULT_FLAG", nullable = false, length = 1)
	public String getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "S_WARE_NO", nullable = false, length = 5)
	public String getSWareNo() {
		return this.SWareNo;
	}

	public void setSWareNo(String SWareNo) {
		this.SWareNo = SWareNo;
	}

	@Column(name = "S_AREA_NO", nullable = false, length = 5)
	public String getSAreaNo() {
		return this.SAreaNo;
	}

	public void setSAreaNo(String SAreaNo) {
		this.SAreaNo = SAreaNo;
	}

	@Column(name = "S_STOCK_NO", nullable = false, length = 5)
	public String getSStockNo() {
		return this.SStockNo;
	}

	public void setSStockNo(String SStockNo) {
		this.SStockNo = SStockNo;
	}

}