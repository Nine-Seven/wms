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
 * Cdef_Defarea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CDEF_DEFAREA")
public class Cdef_Defarea  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cdef_DefareaId id;
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
     private String mixOwner;
     private Double maxWeight;
     private Double maxVolume;
     private Double maxqtyStrategyId;
     private Double pickLevel;
     private String keepLabelFlag;


    // Constructors

    /** default constructor */
    public Cdef_Defarea() {
    }

	/** minimal constructor */
    public Cdef_Defarea(Cdef_DefareaId id, String areaName, String OType, String areaType, String areaUsetype, String areaQuality, Integer mixFlag, String mixSupplier, Short maxQty, Byte stockNum, String divideFlag, String BDivideFlag, String areaAttribute, String attributeType, String limitType, Short limitRate, Short palOutRate, String BPick, String areaPick, String AFlag, String ioBufferFlag, String pickFlag, String floor, String advancerPickFlag, String itemType, String divideLineFlag, String rgstName, Date rgstDate, String BReplenishType, String BReplenishRule, String CReplenishType, String CReplenishRule, String replenishTaskRule, String locateTime) {
        this.id = id;
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
    public Cdef_Defarea(Cdef_DefareaId id, String areaName, String areaRemark, String OType, String areaType, String areaUsetype, String areaQuality, Integer mixFlag, String mixSupplier, Short maxQty, Byte stockNum, String divideFlag, String BDivideFlag, String areaAttribute, String attributeType, String limitType, Short limitRate, Short palOutRate, String BPick, String areaPick, String AFlag, String ioBufferFlag, String pickFlag, String floor, String advancerPickFlag, Double maxCase, String itemType, String divideLineFlag, String rgstName, Date rgstDate, String updtName, Date updtDate, String BReplenishType, String BReplenishRule, String CReplenishType, String CReplenishRule, String replenishTaskRule, String locateTime, Short taskId, String mixOwner, Double maxWeight, Double maxVolume, Double maxqtyStrategyId, Double pickLevel, String keepLabelFlag) {
        this.id = id;
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
        this.mixOwner = mixOwner;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
        this.maxqtyStrategyId = maxqtyStrategyId;
        this.pickLevel = pickLevel;
        this.keepLabelFlag = keepLabelFlag;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="wareNo", column=@Column(name="WARE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="areaNo", column=@Column(name="AREA_NO", nullable=false, length=5) ) } )

    public Cdef_DefareaId getId() {
        return this.id;
    }
    
    public void setId(Cdef_DefareaId id) {
        this.id = id;
    }
    
    @Column(name="AREA_NAME", nullable=false, length=30)

    public String getAreaName() {
        return this.areaName;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    @Column(name="AREA_REMARK", length=50)

    public String getAreaRemark() {
        return this.areaRemark;
    }
    
    public void setAreaRemark(String areaRemark) {
        this.areaRemark = areaRemark;
    }
    
    @Column(name="O_TYPE", nullable=false, length=1)

    public String getOType() {
        return this.OType;
    }
    
    public void setOType(String OType) {
        this.OType = OType;
    }
    
    @Column(name="AREA_TYPE", nullable=false, length=1)

    public String getAreaType() {
        return this.areaType;
    }
    
    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }
    
    @Column(name="AREA_USETYPE", nullable=false, length=2)

    public String getAreaUsetype() {
        return this.areaUsetype;
    }
    
    public void setAreaUsetype(String areaUsetype) {
        this.areaUsetype = areaUsetype;
    }
    
    @Column(name="AREA_QUALITY", nullable=false, length=2)

    public String getAreaQuality() {
        return this.areaQuality;
    }
    
    public void setAreaQuality(String areaQuality) {
        this.areaQuality = areaQuality;
    }
    
    @Column(name="MIX_FLAG", nullable=false, precision=1, scale=0)

    public Integer getMixFlag() {
        return this.mixFlag;
    }
    
    public void setMixFlag(Integer mixFlag) {
        this.mixFlag = mixFlag;
    }
    
    @Column(name="MIX_SUPPLIER", nullable=false, length=1)

    public String getMixSupplier() {
        return this.mixSupplier;
    }
    
    public void setMixSupplier(String mixSupplier) {
        this.mixSupplier = mixSupplier;
    }
    
    @Column(name="MAX_QTY", nullable=false, precision=3, scale=0)

    public Short getMaxQty() {
        return this.maxQty;
    }
    
    public void setMaxQty(Short maxQty) {
        this.maxQty = maxQty;
    }
    
    @Column(name="STOCK_NUM", nullable=false, precision=2, scale=0)

    public Byte getStockNum() {
        return this.stockNum;
    }
    
    public void setStockNum(Byte stockNum) {
        this.stockNum = stockNum;
    }
    
    @Column(name="DIVIDE_FLAG", nullable=false, length=1)

    public String getDivideFlag() {
        return this.divideFlag;
    }
    
    public void setDivideFlag(String divideFlag) {
        this.divideFlag = divideFlag;
    }
    
    @Column(name="B_DIVIDE_FLAG", nullable=false, length=1)

    public String getBDivideFlag() {
        return this.BDivideFlag;
    }
    
    public void setBDivideFlag(String BDivideFlag) {
        this.BDivideFlag = BDivideFlag;
    }
    
    @Column(name="AREA_ATTRIBUTE", nullable=false, length=1)

    public String getAreaAttribute() {
        return this.areaAttribute;
    }
    
    public void setAreaAttribute(String areaAttribute) {
        this.areaAttribute = areaAttribute;
    }
    
    @Column(name="ATTRIBUTE_TYPE", nullable=false, length=2)

    public String getAttributeType() {
        return this.attributeType;
    }
    
    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }
    
    @Column(name="LIMIT_TYPE", nullable=false, length=1)

    public String getLimitType() {
        return this.limitType;
    }
    
    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }
    
    @Column(name="LIMIT_RATE", nullable=false, precision=3, scale=0)

    public Short getLimitRate() {
        return this.limitRate;
    }
    
    public void setLimitRate(Short limitRate) {
        this.limitRate = limitRate;
    }
    
    @Column(name="PAL_OUT_RATE", nullable=false, precision=3, scale=0)

    public Short getPalOutRate() {
        return this.palOutRate;
    }
    
    public void setPalOutRate(Short palOutRate) {
        this.palOutRate = palOutRate;
    }
    
    @Column(name="B_PICK", nullable=false, length=1)

    public String getBPick() {
        return this.BPick;
    }
    
    public void setBPick(String BPick) {
        this.BPick = BPick;
    }
    
    @Column(name="AREA_PICK", nullable=false, length=1)

    public String getAreaPick() {
        return this.areaPick;
    }
    
    public void setAreaPick(String areaPick) {
        this.areaPick = areaPick;
    }
    
    @Column(name="A_FLAG", nullable=false, length=1)

    public String getAFlag() {
        return this.AFlag;
    }
    
    public void setAFlag(String AFlag) {
        this.AFlag = AFlag;
    }
    
    @Column(name="IO_BUFFER_FLAG", nullable=false, length=1)

    public String getIoBufferFlag() {
        return this.ioBufferFlag;
    }
    
    public void setIoBufferFlag(String ioBufferFlag) {
        this.ioBufferFlag = ioBufferFlag;
    }
    
    @Column(name="PICK_FLAG", nullable=false, length=1)

    public String getPickFlag() {
        return this.pickFlag;
    }
    
    public void setPickFlag(String pickFlag) {
        this.pickFlag = pickFlag;
    }
    
    @Column(name="FLOOR", nullable=false, length=1)

    public String getFloor() {
        return this.floor;
    }
    
    public void setFloor(String floor) {
        this.floor = floor;
    }
    
    @Column(name="ADVANCER_PICK_FLAG", nullable=false, length=1)

    public String getAdvancerPickFlag() {
        return this.advancerPickFlag;
    }
    
    public void setAdvancerPickFlag(String advancerPickFlag) {
        this.advancerPickFlag = advancerPickFlag;
    }
    
    @Column(name="MAX_CASE", precision=13, scale=5)

    public Double getMaxCase() {
        return this.maxCase;
    }
    
    public void setMaxCase(Double maxCase) {
        this.maxCase = maxCase;
    }
    
    @Column(name="ITEM_TYPE", nullable=false, length=20)

    public String getItemType() {
        return this.itemType;
    }
    
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    @Column(name="DIVIDE_LINE_FLAG", nullable=false, length=1)

    public String getDivideLineFlag() {
        return this.divideLineFlag;
    }
    
    public void setDivideLineFlag(String divideLineFlag) {
        this.divideLineFlag = divideLineFlag;
    }
    
    @Column(name="RGST_NAME", nullable=false, length=20)

    public String getRgstName() {
        return this.rgstName;
    }
    
    public void setRgstName(String rgstName) {
        this.rgstName = rgstName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RGST_DATE", nullable=false, length=7)

    public Date getRgstDate() {
        return this.rgstDate;
    }
    
    public void setRgstDate(Date rgstDate) {
        this.rgstDate = rgstDate;
    }
    
    @Column(name="UPDT_NAME", length=20)

    public String getUpdtName() {
        return this.updtName;
    }
    
    public void setUpdtName(String updtName) {
        this.updtName = updtName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="UPDT_DATE", length=7)

    public Date getUpdtDate() {
        return this.updtDate;
    }
    
    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }
    
    @Column(name="B_REPLENISH_TYPE", nullable=false, length=1)

    public String getBReplenishType() {
        return this.BReplenishType;
    }
    
    public void setBReplenishType(String BReplenishType) {
        this.BReplenishType = BReplenishType;
    }
    
    @Column(name="B_REPLENISH_RULE", nullable=false, length=1)

    public String getBReplenishRule() {
        return this.BReplenishRule;
    }
    
    public void setBReplenishRule(String BReplenishRule) {
        this.BReplenishRule = BReplenishRule;
    }
    
    @Column(name="C_REPLENISH_TYPE", nullable=false, length=1)

    public String getCReplenishType() {
        return this.CReplenishType;
    }
    
    public void setCReplenishType(String CReplenishType) {
        this.CReplenishType = CReplenishType;
    }
    
    @Column(name="C_REPLENISH_RULE", nullable=false, length=1)

    public String getCReplenishRule() {
        return this.CReplenishRule;
    }
    
    public void setCReplenishRule(String CReplenishRule) {
        this.CReplenishRule = CReplenishRule;
    }
    
    @Column(name="REPLENISH_TASK_RULE", nullable=false, length=1)

    public String getReplenishTaskRule() {
        return this.replenishTaskRule;
    }
    
    public void setReplenishTaskRule(String replenishTaskRule) {
        this.replenishTaskRule = replenishTaskRule;
    }
    
    @Column(name="LOCATE_TIME", nullable=false, length=1)

    public String getLocateTime() {
        return this.locateTime;
    }
    
    public void setLocateTime(String locateTime) {
        this.locateTime = locateTime;
    }
    
    @Column(name="TASK_ID", precision=3, scale=0)

    public Short getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(Short taskId) {
        this.taskId = taskId;
    }
    
    @Column(name="MIX_OWNER", length=1)

    public String getMixOwner() {
        return this.mixOwner;
    }
    
    public void setMixOwner(String mixOwner) {
        this.mixOwner = mixOwner;
    }
    
    @Column(name="MAX_WEIGHT", precision=13, scale=5)

    public Double getMaxWeight() {
        return this.maxWeight;
    }
    
    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    @Column(name="MAX_VOLUME", precision=13, scale=5)

    public Double getMaxVolume() {
        return this.maxVolume;
    }
    
    public void setMaxVolume(Double maxVolume) {
        this.maxVolume = maxVolume;
    }
    
    @Column(name="MAXQTY_STRATEGY_ID", precision=3, scale=0)

    public Double getMaxqtyStrategyId() {
        return this.maxqtyStrategyId;
    }
    
    public void setMaxqtyStrategyId(Double maxqtyStrategyId) {
        this.maxqtyStrategyId = maxqtyStrategyId;
    }
    
    @Column(name="PICK_LEVEL", precision=3, scale=0)

    public Double getPickLevel() {
        return this.pickLevel;
    }
    
    public void setPickLevel(Double pickLevel) {
        this.pickLevel = pickLevel;
    }
    
    @Column(name="KEEP_LABEL_FLAG", length=1)

    public String getKeepLabelFlag() {
        return this.keepLabelFlag;
    }
    
    public void setKeepLabelFlag(String keepLabelFlag) {
        this.keepLabelFlag = keepLabelFlag;
    }
   








}