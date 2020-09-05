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
 * CsetCellArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CSET_CELL_ARTICLE")
public class Cset_CellArticle implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cset_CellArticleId id;
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String AStockNo;
	private String stockX;
	private String cellNo;
	private Double packingQty;
	private Integer lineId;
	private Double maxQtyA;
	private Double alertQtyA;
	private Double suppQtyA;
	private Double maxQtyNa;
	private Double alertQtyNa;
	private Double suppQtyNa;
	private Integer keepCells;
	private Integer keepCellsA;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cset_CellArticle() {
	}

	/** minimal constructor */
	public Cset_CellArticle(Cset_CellArticleId id, String wareNo, String areaNo,
			String stockNo, String AStockNo, String stockX, String cellNo,
			Double packingQty, Integer lineId, Double maxQtyA, Double alertQtyA,
			Double suppQtyA, Double maxQtyNa, Double alertQtyNa,
			Double suppQtyNa, Integer keepCells, Integer keepCellsA,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.AStockNo = AStockNo;
		this.stockX = stockX;
		this.cellNo = cellNo;
		this.packingQty = packingQty;
		this.lineId = lineId;
		this.maxQtyA = maxQtyA;
		this.alertQtyA = alertQtyA;
		this.suppQtyA = suppQtyA;
		this.maxQtyNa = maxQtyNa;
		this.alertQtyNa = alertQtyNa;
		this.suppQtyNa = suppQtyNa;
		this.keepCells = keepCells;
		this.keepCellsA = keepCellsA;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cset_CellArticle(Cset_CellArticleId id, String wareNo, String areaNo,
			String stockNo, String AStockNo, String stockX, String cellNo,
			Double packingQty, Integer lineId, Double maxQtyA, Double alertQtyA,
			Double suppQtyA, Double maxQtyNa, Double alertQtyNa,
			Double suppQtyNa, Integer keepCells, Integer keepCellsA,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.AStockNo = AStockNo;
		this.stockX = stockX;
		this.cellNo = cellNo;
		this.packingQty = packingQty;
		this.lineId = lineId;
		this.maxQtyA = maxQtyA;
		this.alertQtyA = alertQtyA;
		this.suppQtyA = suppQtyA;
		this.maxQtyNa = maxQtyNa;
		this.alertQtyNa = alertQtyNa;
		this.suppQtyNa = suppQtyNa;
		this.keepCells = keepCells;
		this.keepCellsA = keepCellsA;
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
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 13)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "pickType", column = @Column(name = "PICK_TYPE", nullable = false, length = 1)) })
	public Cset_CellArticleId getId() {
		return this.id;
	}

	public void setId(Cset_CellArticleId id) {
		this.id = id;
	}

	@Column(name = "WARE_NO", nullable = false, length = 5)
	public String getWareNo() {
		return this.wareNo;
	}

	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}

	@Column(name = "AREA_NO", nullable = false, length = 5)
	public String getAreaNo() {
		return this.areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	@Column(name = "STOCK_NO", nullable = false, length = 5)
	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	@Column(name = "A_STOCK_NO", nullable = false, length = 10)
	public String getAStockNo() {
		return this.AStockNo;
	}

	public void setAStockNo(String AStockNo) {
		this.AStockNo = AStockNo;
	}

	@Column(name = "STOCK_X", nullable = false, length = 2)
	public String getStockX() {
		return this.stockX;
	}

	public void setStockX(String stockX) {
		this.stockX = stockX;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "LINE_ID", nullable = false, precision = 2, scale = 0)
	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@Column(name = "MAX_QTY_A", nullable = false, precision = 18, scale = 5)
	public Double getMaxQtyA() {
		return this.maxQtyA;
	}

	public void setMaxQtyA(Double maxQtyA) {
		this.maxQtyA = maxQtyA;
	}

	@Column(name = "ALERT_QTY_A", nullable = false, precision = 18, scale = 5)
	public Double getAlertQtyA() {
		return this.alertQtyA;
	}

	public void setAlertQtyA(Double alertQtyA) {
		this.alertQtyA = alertQtyA;
	}

	@Column(name = "SUPP_QTY_A", nullable = false, precision = 18, scale = 5)
	public Double getSuppQtyA() {
		return this.suppQtyA;
	}

	public void setSuppQtyA(Double suppQtyA) {
		this.suppQtyA = suppQtyA;
	}

	@Column(name = "MAX_QTY_NA", nullable = false, precision = 18, scale = 5)
	public Double getMaxQtyNa() {
		return this.maxQtyNa;
	}

	public void setMaxQtyNa(Double maxQtyNa) {
		this.maxQtyNa = maxQtyNa;
	}

	@Column(name = "ALERT_QTY_NA", nullable = false, precision = 18, scale = 5)
	public Double getAlertQtyNa() {
		return this.alertQtyNa;
	}

	public void setAlertQtyNa(Double alertQtyNa) {
		this.alertQtyNa = alertQtyNa;
	}

	@Column(name = "SUPP_QTY_NA", nullable = false, precision = 18, scale = 5)
	public Double getSuppQtyNa() {
		return this.suppQtyNa;
	}

	public void setSuppQtyNa(Double suppQtyNa) {
		this.suppQtyNa = suppQtyNa;
	}

	@Column(name = "KEEP_CELLS", nullable = false, precision = 3, scale = 0)
	public Integer getKeepCells() {
		return this.keepCells;
	}

	public void setKeepCells(Integer keepCells) {
		this.keepCells = keepCells;
	}

	@Column(name = "KEEP_CELLS_A", nullable = false, precision = 3, scale = 0)
	public Integer getKeepCellsA() {
		return this.keepCellsA;
	}

	public void setKeepCellsA(Integer keepCellsA) {
		this.keepCellsA = keepCellsA;
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