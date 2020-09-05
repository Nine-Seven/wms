package com.sealinkin.cdef.po;
// default package
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CdefDefareaLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cdef_DefareaLogId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String wareNo;
	private String areaNo;
	private String areaName;
	private String areaRemark;
	private String OType;
	private String areaType;
	private String areaUsetype;
	private String areaQuality;
	private Integer mixFlag;
	private String mixSupplier;
	private Short maxQty;
	private Byte stockNum;
	private String divideFlag;
	private String BDivideFlag;
	private String areaAttribute;
	private String attributeType;
	private String limitType;
	private Short limitRate;
	private Short palOutRate;
	private String BPick;
	private String areaPick;
	private String AFlag;
	private String ioBufferFlag;
	private String pickFlag;
	private String floor;
	private String advancerPickFlag;
	private Double maxCase;
	private String itemType;
	private String divideLineFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String BReplenishType;
	private String BReplenishRule;
	private String CReplenishType;
	private String CReplenishRule;
	private String replenishTaskRule;
	private String locateTime;
	private Short taskId;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Cdef_DefareaLogId() {
	}

	/** minimal constructor */
	public Cdef_DefareaLogId(String warehouseNo, String wareNo, String areaNo,
			String areaName, String OType, String areaType, String areaUsetype,
			String areaQuality, Integer mixFlag, String mixSupplier,
			Short maxQty, Byte stockNum, String divideFlag, String BDivideFlag,
			String areaAttribute, String attributeType, String limitType,
			Short limitRate, Short palOutRate, String BPick, String areaPick,
			String AFlag, String ioBufferFlag, String pickFlag, String floor,
			String advancerPickFlag, String itemType, String divideLineFlag,
			String rgstName, Date rgstDate, String BReplenishType,
			String BReplenishRule, String CReplenishType,
			String CReplenishRule, String replenishTaskRule, String locateTime) {
		this.warehouseNo = warehouseNo;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.areaName = areaName;
		this.OType = OType;
		this.areaType = areaType;
		this.areaUsetype = areaUsetype;
		this.areaQuality = areaQuality;
		this.mixFlag = mixFlag;
		this.mixSupplier = mixSupplier;
		this.maxQty = maxQty;
		this.stockNum = stockNum;
		this.divideFlag = divideFlag;
		this.BDivideFlag = BDivideFlag;
		this.areaAttribute = areaAttribute;
		this.attributeType = attributeType;
		this.limitType = limitType;
		this.limitRate = limitRate;
		this.palOutRate = palOutRate;
		this.BPick = BPick;
		this.areaPick = areaPick;
		this.AFlag = AFlag;
		this.ioBufferFlag = ioBufferFlag;
		this.pickFlag = pickFlag;
		this.floor = floor;
		this.advancerPickFlag = advancerPickFlag;
		this.itemType = itemType;
		this.divideLineFlag = divideLineFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.BReplenishType = BReplenishType;
		this.BReplenishRule = BReplenishRule;
		this.CReplenishType = CReplenishType;
		this.CReplenishRule = CReplenishRule;
		this.replenishTaskRule = replenishTaskRule;
		this.locateTime = locateTime;
	}

	/** full constructor */
	public Cdef_DefareaLogId(String warehouseNo, String wareNo, String areaNo,
			String areaName, String areaRemark, String OType, String areaType,
			String areaUsetype, String areaQuality, Integer mixFlag,
			String mixSupplier, Short maxQty, Byte stockNum, String divideFlag,
			String BDivideFlag, String areaAttribute, String attributeType,
			String limitType, Short limitRate, Short palOutRate, String BPick,
			String areaPick, String AFlag, String ioBufferFlag,
			String pickFlag, String floor, String advancerPickFlag,
			Double maxCase, String itemType, String divideLineFlag,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String BReplenishType, String BReplenishRule,
			String CReplenishType, String CReplenishRule,
			String replenishTaskRule, String locateTime, Short taskId,
			String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.areaName = areaName;
		this.areaRemark = areaRemark;
		this.OType = OType;
		this.areaType = areaType;
		this.areaUsetype = areaUsetype;
		this.areaQuality = areaQuality;
		this.mixFlag = mixFlag;
		this.mixSupplier = mixSupplier;
		this.maxQty = maxQty;
		this.stockNum = stockNum;
		this.divideFlag = divideFlag;
		this.BDivideFlag = BDivideFlag;
		this.areaAttribute = areaAttribute;
		this.attributeType = attributeType;
		this.limitType = limitType;
		this.limitRate = limitRate;
		this.palOutRate = palOutRate;
		this.BPick = BPick;
		this.areaPick = areaPick;
		this.AFlag = AFlag;
		this.ioBufferFlag = ioBufferFlag;
		this.pickFlag = pickFlag;
		this.floor = floor;
		this.advancerPickFlag = advancerPickFlag;
		this.maxCase = maxCase;
		this.itemType = itemType;
		this.divideLineFlag = divideLineFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.BReplenishType = BReplenishType;
		this.BReplenishRule = BReplenishRule;
		this.CReplenishType = CReplenishType;
		this.CReplenishRule = CReplenishRule;
		this.replenishTaskRule = replenishTaskRule;
		this.locateTime = locateTime;
		this.taskId = taskId;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
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

	@Column(name = "AREA_NAME", nullable = false, length = 30)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "AREA_REMARK", length = 50)
	public String getAreaRemark() {
		return this.areaRemark;
	}

	public void setAreaRemark(String areaRemark) {
		this.areaRemark = areaRemark;
	}

	@Column(name = "O_TYPE", nullable = false, length = 1)
	public String getOType() {
		return this.OType;
	}

	public void setOType(String OType) {
		this.OType = OType;
	}

	@Column(name = "AREA_TYPE", nullable = false, length = 1)
	public String getAreaType() {
		return this.areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	@Column(name = "AREA_USETYPE", nullable = false, length = 2)
	public String getAreaUsetype() {
		return this.areaUsetype;
	}

	public void setAreaUsetype(String areaUsetype) {
		this.areaUsetype = areaUsetype;
	}

	@Column(name = "AREA_QUALITY", nullable = false, length = 2)
	public String getAreaQuality() {
		return this.areaQuality;
	}

	public void setAreaQuality(String areaQuality) {
		this.areaQuality = areaQuality;
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
	public Short getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Short maxQty) {
		this.maxQty = maxQty;
	}

	@Column(name = "STOCK_NUM", nullable = false, precision = 2, scale = 0)
	public Byte getStockNum() {
		return this.stockNum;
	}

	public void setStockNum(Byte stockNum) {
		this.stockNum = stockNum;
	}

	@Column(name = "DIVIDE_FLAG", nullable = false, length = 1)
	public String getDivideFlag() {
		return this.divideFlag;
	}

	public void setDivideFlag(String divideFlag) {
		this.divideFlag = divideFlag;
	}

	@Column(name = "B_DIVIDE_FLAG", nullable = false, length = 1)
	public String getBDivideFlag() {
		return this.BDivideFlag;
	}

	public void setBDivideFlag(String BDivideFlag) {
		this.BDivideFlag = BDivideFlag;
	}

	@Column(name = "AREA_ATTRIBUTE", nullable = false, length = 1)
	public String getAreaAttribute() {
		return this.areaAttribute;
	}

	public void setAreaAttribute(String areaAttribute) {
		this.areaAttribute = areaAttribute;
	}

	@Column(name = "ATTRIBUTE_TYPE", nullable = false, length = 1)
	public String getAttributeType() {
		return this.attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	@Column(name = "LIMIT_TYPE", nullable = false, length = 1)
	public String getLimitType() {
		return this.limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	@Column(name = "LIMIT_RATE", nullable = false, precision = 3, scale = 0)
	public Short getLimitRate() {
		return this.limitRate;
	}

	public void setLimitRate(Short limitRate) {
		this.limitRate = limitRate;
	}

	@Column(name = "PAL_OUT_RATE", nullable = false, precision = 3, scale = 0)
	public Short getPalOutRate() {
		return this.palOutRate;
	}

	public void setPalOutRate(Short palOutRate) {
		this.palOutRate = palOutRate;
	}

	@Column(name = "B_PICK", nullable = false, length = 1)
	public String getBPick() {
		return this.BPick;
	}

	public void setBPick(String BPick) {
		this.BPick = BPick;
	}

	@Column(name = "AREA_PICK", nullable = false, length = 1)
	public String getAreaPick() {
		return this.areaPick;
	}

	public void setAreaPick(String areaPick) {
		this.areaPick = areaPick;
	}

	@Column(name = "A_FLAG", nullable = false, length = 1)
	public String getAFlag() {
		return this.AFlag;
	}

	public void setAFlag(String AFlag) {
		this.AFlag = AFlag;
	}

	@Column(name = "IO_BUFFER_FLAG", nullable = false, length = 1)
	public String getIoBufferFlag() {
		return this.ioBufferFlag;
	}

	public void setIoBufferFlag(String ioBufferFlag) {
		this.ioBufferFlag = ioBufferFlag;
	}

	@Column(name = "PICK_FLAG", nullable = false, length = 1)
	public String getPickFlag() {
		return this.pickFlag;
	}

	public void setPickFlag(String pickFlag) {
		this.pickFlag = pickFlag;
	}

	@Column(name = "FLOOR", nullable = false, length = 1)
	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Column(name = "ADVANCER_PICK_FLAG", nullable = false, length = 1)
	public String getAdvancerPickFlag() {
		return this.advancerPickFlag;
	}

	public void setAdvancerPickFlag(String advancerPickFlag) {
		this.advancerPickFlag = advancerPickFlag;
	}

	@Column(name = "MAX_CASE", precision = 13, scale = 5)
	public Double getMaxCase() {
		return this.maxCase;
	}

	public void setMaxCase(Double maxCase) {
		this.maxCase = maxCase;
	}

	@Column(name = "ITEM_TYPE", nullable = false, length = 20)
	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Column(name = "DIVIDE_LINE_FLAG", nullable = false, length = 1)
	public String getDivideLineFlag() {
		return this.divideLineFlag;
	}

	public void setDivideLineFlag(String divideLineFlag) {
		this.divideLineFlag = divideLineFlag;
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

	@Column(name = "B_REPLENISH_TYPE", nullable = false, length = 1)
	public String getBReplenishType() {
		return this.BReplenishType;
	}

	public void setBReplenishType(String BReplenishType) {
		this.BReplenishType = BReplenishType;
	}

	@Column(name = "B_REPLENISH_RULE", nullable = false, length = 1)
	public String getBReplenishRule() {
		return this.BReplenishRule;
	}

	public void setBReplenishRule(String BReplenishRule) {
		this.BReplenishRule = BReplenishRule;
	}

	@Column(name = "C_REPLENISH_TYPE", nullable = false, length = 1)
	public String getCReplenishType() {
		return this.CReplenishType;
	}

	public void setCReplenishType(String CReplenishType) {
		this.CReplenishType = CReplenishType;
	}

	@Column(name = "C_REPLENISH_RULE", nullable = false, length = 1)
	public String getCReplenishRule() {
		return this.CReplenishRule;
	}

	public void setCReplenishRule(String CReplenishRule) {
		this.CReplenishRule = CReplenishRule;
	}

	@Column(name = "REPLENISH_TASK_RULE", nullable = false, length = 1)
	public String getReplenishTaskRule() {
		return this.replenishTaskRule;
	}

	public void setReplenishTaskRule(String replenishTaskRule) {
		this.replenishTaskRule = replenishTaskRule;
	}

	@Column(name = "LOCATE_TIME", nullable = false, length = 1)
	public String getLocateTime() {
		return this.locateTime;
	}

	public void setLocateTime(String locateTime) {
		this.locateTime = locateTime;
	}

	@Column(name = "TASK_ID", precision = 3, scale = 0)
	public Short getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Short taskId) {
		this.taskId = taskId;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cdef_DefareaLogId))
			return false;
		Cdef_DefareaLogId castOther = (Cdef_DefareaLogId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getWareNo() == castOther.getWareNo()) || (this
						.getWareNo() != null && castOther.getWareNo() != null && this
						.getWareNo().equals(castOther.getWareNo())))
				&& ((this.getAreaNo() == castOther.getAreaNo()) || (this
						.getAreaNo() != null && castOther.getAreaNo() != null && this
						.getAreaNo().equals(castOther.getAreaNo())))
				&& ((this.getAreaName() == castOther.getAreaName()) || (this
						.getAreaName() != null
						&& castOther.getAreaName() != null && this
						.getAreaName().equals(castOther.getAreaName())))
				&& ((this.getAreaRemark() == castOther.getAreaRemark()) || (this
						.getAreaRemark() != null
						&& castOther.getAreaRemark() != null && this
						.getAreaRemark().equals(castOther.getAreaRemark())))
				&& ((this.getOType() == castOther.getOType()) || (this
						.getOType() != null && castOther.getOType() != null && this
						.getOType().equals(castOther.getOType())))
				&& ((this.getAreaType() == castOther.getAreaType()) || (this
						.getAreaType() != null
						&& castOther.getAreaType() != null && this
						.getAreaType().equals(castOther.getAreaType())))
				&& ((this.getAreaUsetype() == castOther.getAreaUsetype()) || (this
						.getAreaUsetype() != null
						&& castOther.getAreaUsetype() != null && this
						.getAreaUsetype().equals(castOther.getAreaUsetype())))
				&& ((this.getAreaQuality() == castOther.getAreaQuality()) || (this
						.getAreaQuality() != null
						&& castOther.getAreaQuality() != null && this
						.getAreaQuality().equals(castOther.getAreaQuality())))
				&& ((this.getMixFlag() == castOther.getMixFlag()) || (this
						.getMixFlag() != null && castOther.getMixFlag() != null && this
						.getMixFlag().equals(castOther.getMixFlag())))
				&& ((this.getMixSupplier() == castOther.getMixSupplier()) || (this
						.getMixSupplier() != null
						&& castOther.getMixSupplier() != null && this
						.getMixSupplier().equals(castOther.getMixSupplier())))
				&& ((this.getMaxQty() == castOther.getMaxQty()) || (this
						.getMaxQty() != null && castOther.getMaxQty() != null && this
						.getMaxQty().equals(castOther.getMaxQty())))
				&& ((this.getStockNum() == castOther.getStockNum()) || (this
						.getStockNum() != null
						&& castOther.getStockNum() != null && this
						.getStockNum().equals(castOther.getStockNum())))
				&& ((this.getDivideFlag() == castOther.getDivideFlag()) || (this
						.getDivideFlag() != null
						&& castOther.getDivideFlag() != null && this
						.getDivideFlag().equals(castOther.getDivideFlag())))
				&& ((this.getBDivideFlag() == castOther.getBDivideFlag()) || (this
						.getBDivideFlag() != null
						&& castOther.getBDivideFlag() != null && this
						.getBDivideFlag().equals(castOther.getBDivideFlag())))
				&& ((this.getAreaAttribute() == castOther.getAreaAttribute()) || (this
						.getAreaAttribute() != null
						&& castOther.getAreaAttribute() != null && this
						.getAreaAttribute()
						.equals(castOther.getAreaAttribute())))
				&& ((this.getAttributeType() == castOther.getAttributeType()) || (this
						.getAttributeType() != null
						&& castOther.getAttributeType() != null && this
						.getAttributeType()
						.equals(castOther.getAttributeType())))
				&& ((this.getLimitType() == castOther.getLimitType()) || (this
						.getLimitType() != null
						&& castOther.getLimitType() != null && this
						.getLimitType().equals(castOther.getLimitType())))
				&& ((this.getLimitRate() == castOther.getLimitRate()) || (this
						.getLimitRate() != null
						&& castOther.getLimitRate() != null && this
						.getLimitRate().equals(castOther.getLimitRate())))
				&& ((this.getPalOutRate() == castOther.getPalOutRate()) || (this
						.getPalOutRate() != null
						&& castOther.getPalOutRate() != null && this
						.getPalOutRate().equals(castOther.getPalOutRate())))
				&& ((this.getBPick() == castOther.getBPick()) || (this
						.getBPick() != null && castOther.getBPick() != null && this
						.getBPick().equals(castOther.getBPick())))
				&& ((this.getAreaPick() == castOther.getAreaPick()) || (this
						.getAreaPick() != null
						&& castOther.getAreaPick() != null && this
						.getAreaPick().equals(castOther.getAreaPick())))
				&& ((this.getAFlag() == castOther.getAFlag()) || (this
						.getAFlag() != null && castOther.getAFlag() != null && this
						.getAFlag().equals(castOther.getAFlag())))
				&& ((this.getIoBufferFlag() == castOther.getIoBufferFlag()) || (this
						.getIoBufferFlag() != null
						&& castOther.getIoBufferFlag() != null && this
						.getIoBufferFlag().equals(castOther.getIoBufferFlag())))
				&& ((this.getPickFlag() == castOther.getPickFlag()) || (this
						.getPickFlag() != null
						&& castOther.getPickFlag() != null && this
						.getPickFlag().equals(castOther.getPickFlag())))
				&& ((this.getFloor() == castOther.getFloor()) || (this
						.getFloor() != null && castOther.getFloor() != null && this
						.getFloor().equals(castOther.getFloor())))
				&& ((this.getAdvancerPickFlag() == castOther
						.getAdvancerPickFlag()) || (this.getAdvancerPickFlag() != null
						&& castOther.getAdvancerPickFlag() != null && this
						.getAdvancerPickFlag().equals(
								castOther.getAdvancerPickFlag())))
				&& ((this.getMaxCase() == castOther.getMaxCase()) || (this
						.getMaxCase() != null && castOther.getMaxCase() != null && this
						.getMaxCase().equals(castOther.getMaxCase())))
				&& ((this.getItemType() == castOther.getItemType()) || (this
						.getItemType() != null
						&& castOther.getItemType() != null && this
						.getItemType().equals(castOther.getItemType())))
				&& ((this.getDivideLineFlag() == castOther.getDivideLineFlag()) || (this
						.getDivideLineFlag() != null
						&& castOther.getDivideLineFlag() != null && this
						.getDivideLineFlag().equals(
								castOther.getDivideLineFlag())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getUpdtName() == castOther.getUpdtName()) || (this
						.getUpdtName() != null
						&& castOther.getUpdtName() != null && this
						.getUpdtName().equals(castOther.getUpdtName())))
				&& ((this.getUpdtDate() == castOther.getUpdtDate()) || (this
						.getUpdtDate() != null
						&& castOther.getUpdtDate() != null && this
						.getUpdtDate().equals(castOther.getUpdtDate())))
				&& ((this.getBReplenishType() == castOther.getBReplenishType()) || (this
						.getBReplenishType() != null
						&& castOther.getBReplenishType() != null && this
						.getBReplenishType().equals(
								castOther.getBReplenishType())))
				&& ((this.getBReplenishRule() == castOther.getBReplenishRule()) || (this
						.getBReplenishRule() != null
						&& castOther.getBReplenishRule() != null && this
						.getBReplenishRule().equals(
								castOther.getBReplenishRule())))
				&& ((this.getCReplenishType() == castOther.getCReplenishType()) || (this
						.getCReplenishType() != null
						&& castOther.getCReplenishType() != null && this
						.getCReplenishType().equals(
								castOther.getCReplenishType())))
				&& ((this.getCReplenishRule() == castOther.getCReplenishRule()) || (this
						.getCReplenishRule() != null
						&& castOther.getCReplenishRule() != null && this
						.getCReplenishRule().equals(
								castOther.getCReplenishRule())))
				&& ((this.getReplenishTaskRule() == castOther
						.getReplenishTaskRule()) || (this
						.getReplenishTaskRule() != null
						&& castOther.getReplenishTaskRule() != null && this
						.getReplenishTaskRule().equals(
								castOther.getReplenishTaskRule())))
				&& ((this.getLocateTime() == castOther.getLocateTime()) || (this
						.getLocateTime() != null
						&& castOther.getLocateTime() != null && this
						.getLocateTime().equals(castOther.getLocateTime())))
				&& ((this.getTaskId() == castOther.getTaskId()) || (this
						.getTaskId() != null && castOther.getTaskId() != null && this
						.getTaskId().equals(castOther.getTaskId())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getWareNo() == null ? 0 : this.getWareNo().hashCode());
		result = 37 * result
				+ (getAreaNo() == null ? 0 : this.getAreaNo().hashCode());
		result = 37 * result
				+ (getAreaName() == null ? 0 : this.getAreaName().hashCode());
		result = 37
				* result
				+ (getAreaRemark() == null ? 0 : this.getAreaRemark()
						.hashCode());
		result = 37 * result
				+ (getOType() == null ? 0 : this.getOType().hashCode());
		result = 37 * result
				+ (getAreaType() == null ? 0 : this.getAreaType().hashCode());
		result = 37
				* result
				+ (getAreaUsetype() == null ? 0 : this.getAreaUsetype()
						.hashCode());
		result = 37
				* result
				+ (getAreaQuality() == null ? 0 : this.getAreaQuality()
						.hashCode());
		result = 37 * result
				+ (getMixFlag() == null ? 0 : this.getMixFlag().hashCode());
		result = 37
				* result
				+ (getMixSupplier() == null ? 0 : this.getMixSupplier()
						.hashCode());
		result = 37 * result
				+ (getMaxQty() == null ? 0 : this.getMaxQty().hashCode());
		result = 37 * result
				+ (getStockNum() == null ? 0 : this.getStockNum().hashCode());
		result = 37
				* result
				+ (getDivideFlag() == null ? 0 : this.getDivideFlag()
						.hashCode());
		result = 37
				* result
				+ (getBDivideFlag() == null ? 0 : this.getBDivideFlag()
						.hashCode());
		result = 37
				* result
				+ (getAreaAttribute() == null ? 0 : this.getAreaAttribute()
						.hashCode());
		result = 37
				* result
				+ (getAttributeType() == null ? 0 : this.getAttributeType()
						.hashCode());
		result = 37 * result
				+ (getLimitType() == null ? 0 : this.getLimitType().hashCode());
		result = 37 * result
				+ (getLimitRate() == null ? 0 : this.getLimitRate().hashCode());
		result = 37
				* result
				+ (getPalOutRate() == null ? 0 : this.getPalOutRate()
						.hashCode());
		result = 37 * result
				+ (getBPick() == null ? 0 : this.getBPick().hashCode());
		result = 37 * result
				+ (getAreaPick() == null ? 0 : this.getAreaPick().hashCode());
		result = 37 * result
				+ (getAFlag() == null ? 0 : this.getAFlag().hashCode());
		result = 37
				* result
				+ (getIoBufferFlag() == null ? 0 : this.getIoBufferFlag()
						.hashCode());
		result = 37 * result
				+ (getPickFlag() == null ? 0 : this.getPickFlag().hashCode());
		result = 37 * result
				+ (getFloor() == null ? 0 : this.getFloor().hashCode());
		result = 37
				* result
				+ (getAdvancerPickFlag() == null ? 0 : this
						.getAdvancerPickFlag().hashCode());
		result = 37 * result
				+ (getMaxCase() == null ? 0 : this.getMaxCase().hashCode());
		result = 37 * result
				+ (getItemType() == null ? 0 : this.getItemType().hashCode());
		result = 37
				* result
				+ (getDivideLineFlag() == null ? 0 : this.getDivideLineFlag()
						.hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getUpdtName() == null ? 0 : this.getUpdtName().hashCode());
		result = 37 * result
				+ (getUpdtDate() == null ? 0 : this.getUpdtDate().hashCode());
		result = 37
				* result
				+ (getBReplenishType() == null ? 0 : this.getBReplenishType()
						.hashCode());
		result = 37
				* result
				+ (getBReplenishRule() == null ? 0 : this.getBReplenishRule()
						.hashCode());
		result = 37
				* result
				+ (getCReplenishType() == null ? 0 : this.getCReplenishType()
						.hashCode());
		result = 37
				* result
				+ (getCReplenishRule() == null ? 0 : this.getCReplenishRule()
						.hashCode());
		result = 37
				* result
				+ (getReplenishTaskRule() == null ? 0 : this
						.getReplenishTaskRule().hashCode());
		result = 37
				* result
				+ (getLocateTime() == null ? 0 : this.getLocateTime()
						.hashCode());
		result = 37 * result
				+ (getTaskId() == null ? 0 : this.getTaskId().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}