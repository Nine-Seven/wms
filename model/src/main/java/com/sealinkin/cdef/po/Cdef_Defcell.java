package com.sealinkin.cdef.po;
// default package

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
 * CdefDefcell entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CDEF_DEFCELL")
public class Cdef_Defcell implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cdef_DefcellId id;
	private String ownerNo;
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String stockX;
	private String bayX;
	private String stockY;
	private Integer mixFlag;
	private String mixSupplier;
	private Double maxQty;
	private Double maxWeight;
	private Double maxVolume;
	private Double maxCase;
	private String limitType;
	private Integer limitRate;
	private String BPick;
	private String cellStatus;
	private String checkStatus;
	private String AFlag;
	private String pickFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String prefix;
	private Integer pickOrder;
	private String dispCellNo;
	private String mixOwner;
	private String keepLabelFlag;
	  
	// Constructors

	/** default constructor */
	public Cdef_Defcell() {
	}

	/** minimal constructor */
	public Cdef_Defcell(Cdef_DefcellId id, String ownerNo, String wareNo,
			String areaNo, String stockNo, String stockX, String bayX,
			String stockY, Integer mixFlag, String mixSupplier, Double maxQty,
			Double maxWeight, Double maxVolume, Double maxCase, String BPick,
			String cellStatus, String checkStatus, String AFlag,
			String pickFlag, String rgstName, Date rgstDate, String dispCellNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.stockX = stockX;
		this.bayX = bayX;
		this.stockY = stockY;
		this.mixFlag = mixFlag;
		this.mixSupplier = mixSupplier;
		this.maxQty = maxQty;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.maxCase = maxCase;
		this.BPick = BPick;
		this.cellStatus = cellStatus;
		this.checkStatus = checkStatus;
		this.AFlag = AFlag;
		this.pickFlag = pickFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.dispCellNo = dispCellNo;
	}

	/** full constructor */
	public Cdef_Defcell(Cdef_DefcellId id, String ownerNo, String wareNo,
			String areaNo, String stockNo, String stockX, String bayX,
			String stockY, Integer mixFlag, String mixSupplier, Double maxQty,
			Double maxWeight, Double maxVolume, Double maxCase,
			String limitType, Integer limitRate, String BPick, String cellStatus,
			String checkStatus, String AFlag, String pickFlag, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String prefix,
			Integer pickOrder, String dispCellNo, String mixOwner,String keepLabelFlag) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.stockX = stockX;
		this.bayX = bayX;
		this.stockY = stockY;
		this.mixFlag = mixFlag;
		this.mixSupplier = mixSupplier;
		this.maxQty = maxQty;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.maxCase = maxCase;
		this.limitType = limitType;
		this.limitRate = limitRate;
		this.BPick = BPick;
		this.cellStatus = cellStatus;
		this.checkStatus = checkStatus;
		this.AFlag = AFlag;
		this.pickFlag = pickFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.prefix = prefix;
		this.pickOrder = pickOrder;
		this.dispCellNo = dispCellNo;
		this.mixOwner = mixOwner;
		this.keepLabelFlag=keepLabelFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)) })
	public Cdef_DefcellId getId() {
		return this.id;
	}

	public void setId(Cdef_DefcellId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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

	@Column(name = "STOCK_X", nullable = false, length = 2)
	public String getStockX() {
		return this.stockX;
	}

	public void setStockX(String stockX) {
		this.stockX = stockX;
	}

	@Column(name = "BAY_X", nullable = false, length = 2)
	public String getBayX() {
		return this.bayX;
	}

	public void setBayX(String bayX) {
		this.bayX = bayX;
	}

	@Column(name = "STOCK_Y", nullable = false, length = 2)
	public String getStockY() {
		return this.stockY;
	}

	public void setStockY(String stockY) {
		this.stockY = stockY;
	}

	@Column(name = "MIX_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getMixFlag() {
		return this.mixFlag;
	}

	public void setMixFlag(Integer mixFlag) {
		this.mixFlag = mixFlag;
	}

	@Column(name = "MIX_SUPPLIER", nullable = false, length = 1)
	public String getMixSupplier() {
		return this.mixSupplier;
	}

	public void setMixSupplier(String mixSupplier) {
		this.mixSupplier = mixSupplier;
	}

	@Column(name = "MAX_QTY", nullable = false, precision = 3, scale = 0)
	public Double getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Double maxQty) {
		this.maxQty = maxQty;
	}

	@Column(name = "MAX_WEIGHT", nullable = false, precision = 13, scale = 5)
	public Double getMaxWeight() {
		return this.maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Column(name = "MAX_VOLUME", nullable = false, precision = 13, scale = 5)
	public Double getMaxVolume() {
		return this.maxVolume;
	}

	public void setMaxVolume(Double maxVolume) {
		this.maxVolume = maxVolume;
	}

	@Column(name = "MAX_CASE", nullable = false, precision = 13, scale = 5)
	public Double getMaxCase() {
		return this.maxCase;
	}

	public void setMaxCase(Double maxCase) {
		this.maxCase = maxCase;
	}

	@Column(name = "LIMIT_TYPE", length = 1)
	public String getLimitType() {
		return this.limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	@Column(name = "LIMIT_RATE", precision = 3, scale = 0)
	public Integer getLimitRate() {
		return this.limitRate;
	}

	public void setLimitRate(Integer limitRate) {
		this.limitRate = limitRate;
	}

	@Column(name = "B_PICK", nullable = false, length = 1)
	public String getBPick() {
		return this.BPick;
	}

	public void setBPick(String BPick) {
		this.BPick = BPick;
	}

	@Column(name = "CELL_STATUS", nullable = false, length = 1)
	public String getCellStatus() {
		return this.cellStatus;
	}

	public void setCellStatus(String cellStatus) {
		this.cellStatus = cellStatus;
	}

	@Column(name = "CHECK_STATUS", nullable = false, length = 1)
	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "A_FLAG", nullable = false, length = 1)
	public String getAFlag() {
		return this.AFlag;
	}

	public void setAFlag(String AFlag) {
		this.AFlag = AFlag;
	}

	@Column(name = "PICK_FLAG", nullable = false, length = 1)
	public String getPickFlag() {
		return this.pickFlag;
	}

	public void setPickFlag(String pickFlag) {
		this.pickFlag = pickFlag;
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

	@Column(name = "PREFIX", length = 20)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "PICK_ORDER", precision = 5, scale = 0)
	public Integer getPickOrder() {
		return this.pickOrder;
	}

	public void setPickOrder(Integer pickOrder) {
		this.pickOrder = pickOrder;
	}

	@Column(name = "DISP_CELL_NO", nullable = false, length = 30)
	public String getDispCellNo() {
		return this.dispCellNo;
	}

	public void setDispCellNo(String dispCellNo) {
		this.dispCellNo = dispCellNo;
	}

	@Column(name = "MIX_OWNER", length = 1)
	public String getMixOwner() {
		return this.mixOwner;
	}

	public void setMixOwner(String mixOwner) {
		this.mixOwner = mixOwner;
	}
    @Column(name="KEEP_LABEL_FLAG", length=1)

    public String getKeepLabelFlag() {
        return this.keepLabelFlag;
    }
    
    public void setKeepLabelFlag(String keepLabelFlag) {
        this.keepLabelFlag = keepLabelFlag;
    }
   

}