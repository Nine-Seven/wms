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
 * CsetAreaBackupD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CSET_AREA_BACKUP_D")
public class Cset_AreaBackupD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cset_AreaBackupDId id;
	private Double keepCells;
	private Integer mergerFlag;
	private Integer stockFlag;
	private Integer floorFlag;
	private Integer bayFlag;
	private Integer sortFlag;
	private Integer stockxFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cset_AreaBackupD() {
	}

	/** minimal constructor */
	public Cset_AreaBackupD(Cset_AreaBackupDId id, Double keepCells,
			Integer mergerFlag, Integer stockFlag, Integer floorFlag,
			Integer bayFlag, Integer sortFlag, Integer stockxFlag,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.keepCells = keepCells;
		this.mergerFlag = mergerFlag;
		this.stockFlag = stockFlag;
		this.floorFlag = floorFlag;
		this.bayFlag = bayFlag;
		this.sortFlag = sortFlag;
		this.stockxFlag = stockxFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cset_AreaBackupD(Cset_AreaBackupDId id, Double keepCells,
			Integer mergerFlag, Integer stockFlag, Integer floorFlag,
			Integer bayFlag, Integer sortFlag, Integer stockxFlag,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.keepCells = keepCells;
		this.mergerFlag = mergerFlag;
		this.stockFlag = stockFlag;
		this.floorFlag = floorFlag;
		this.bayFlag = bayFlag;
		this.sortFlag = sortFlag;
		this.stockxFlag = stockxFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "lineId", column = @Column(name = "LINE_ID", nullable = false, precision = 2, scale = 0)),
			@AttributeOverride(name = "ALevel", column = @Column(name = "A_LEVEL", nullable = false, precision = 1, scale = 0)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaNo", column = @Column(name = "AREA_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "stockNo", column = @Column(name = "STOCK_NO", nullable = false, length = 5)) })
	public Cset_AreaBackupDId getId() {
		return this.id;
	}

	public void setId(Cset_AreaBackupDId id) {
		this.id = id;
	}

	@Column(name = "KEEP_CELLS", nullable = false, precision = 10, scale = 5)
	public Double getKeepCells() {
		return this.keepCells;
	}

	public void setKeepCells(Double keepCells) {
		this.keepCells = keepCells;
	}

	@Column(name = "MERGER_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getMergerFlag() {
		return this.mergerFlag;
	}

	public void setMergerFlag(Integer mergerFlag) {
		this.mergerFlag = mergerFlag;
	}

	@Column(name = "STOCK_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getStockFlag() {
		return this.stockFlag;
	}

	public void setStockFlag(Integer stockFlag) {
		this.stockFlag = stockFlag;
	}

	@Column(name = "FLOOR_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getFloorFlag() {
		return this.floorFlag;
	}

	public void setFloorFlag(Integer floorFlag) {
		this.floorFlag = floorFlag;
	}

	@Column(name = "BAY_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getBayFlag() {
		return this.bayFlag;
	}

	public void setBayFlag(Integer bayFlag) {
		this.bayFlag = bayFlag;
	}

	@Column(name = "SORT_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getSortFlag() {
		return this.sortFlag;
	}

	public void setSortFlag(Integer sortFlag) {
		this.sortFlag = sortFlag;
	}

	@Column(name = "STOCKX_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getStockxFlag() {
		return this.stockxFlag;
	}

	public void setStockxFlag(Integer stockxFlag) {
		this.stockxFlag = stockxFlag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
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