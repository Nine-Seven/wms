package com.sealinkin.bdef.po;

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
 * DeviceDivideM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEVICE_DIVIDE_M")
public class Device_DivideM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Device_DivideMId id;
	private String deviceGroupNo;
	private String operateType;
	private String deviceType;
	private String deviceName;
	private String deviceAlias;
	private Integer maxQty;
	private String status;
	private Byte grade;
	private Integer boxItems;
	private Integer useTimes;
	private Integer custQty;
	private String AStockNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private Double produceCapacity;
	private String produceCapacityType;
	private String diviceRate;
	private Integer strategyId;
	private String refRateFlag;
	
	// Constructors

	/** default constructor */
	public Device_DivideM() {
	}

	/** minimal constructor */
	public Device_DivideM(Device_DivideMId id, String deviceGroupNo,
			String operateType, String deviceType, String status, Byte grade,
			Integer boxItems, String rgstName, Date rgstDate) {
		this.id = id;
		this.deviceGroupNo = deviceGroupNo;
		this.operateType = operateType;
		this.deviceType = deviceType;
		this.status = status;
		this.grade = grade;
		this.boxItems = boxItems;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Device_DivideM(Device_DivideMId id, String deviceGroupNo,
			String operateType, String deviceType, String deviceName,
			String deviceAlias, Integer maxQty, String status, Byte grade,
			Integer boxItems, Integer useTimes, Integer custQty,
			String AStockNo, String rgstName, Date rgstDate, String updtName,
			Date updtDate, Double produceCapacity,
			String produceCapacityType, String diviceRate, Integer strategyId,
			String refRateFlag) {
		this.id = id;
		this.deviceGroupNo = deviceGroupNo;
		this.operateType = operateType;
		this.deviceType = deviceType;
		this.deviceName = deviceName;
		this.deviceAlias = deviceAlias;
		this.maxQty = maxQty;
		this.status = status;
		this.grade = grade;
		this.boxItems = boxItems;
		this.useTimes = useTimes;
		this.custQty = custQty;
		this.AStockNo = AStockNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.produceCapacity = produceCapacity;
		this.produceCapacityType = produceCapacityType;
		this.diviceRate = diviceRate;
		this.strategyId = strategyId;
		this.refRateFlag = refRateFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "deviceNo", column = @Column(name = "DEVICE_NO", nullable = false, length = 20)) })
	public Device_DivideMId getId() {
		return this.id;
	}

	public void setId(Device_DivideMId id) {
		this.id = id;
	}

	@Column(name = "DEVICE_GROUP_NO", nullable = false, length = 5)
	public String getDeviceGroupNo() {
		return this.deviceGroupNo;
	}

	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "DEVICE_TYPE", nullable = false, length = 2)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "DEVICE_NAME", length = 40)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "DEVICE_ALIAS", length = 40)
	public String getDeviceAlias() {
		return this.deviceAlias;
	}

	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}

	@Column(name = "MAX_QTY", precision = 8, scale = 0)
	public Integer getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "GRADE", nullable = false, precision = 2, scale = 0)
	public Byte getGrade() {
		return this.grade;
	}

	public void setGrade(Byte grade) {
		this.grade = grade;
	}

	@Column(name = "BOX_ITEMS", nullable = false, precision = 22, scale = 0)
	public Integer getBoxItems() {
		return this.boxItems;
	}

	public void setBoxItems(Integer boxItems) {
		this.boxItems = boxItems;
	}

	@Column(name = "USE_TIMES", precision = 22, scale = 0)
	public Integer getUseTimes() {
		return this.useTimes;
	}

	public void setUseTimes(Integer useTimes) {
		this.useTimes = useTimes;
	}

	@Column(name = "CUST_QTY", precision = 22, scale = 0)
	public Integer getCustQty() {
		return this.custQty;
	}

	public void setCustQty(Integer custQty) {
		this.custQty = custQty;
	}

	@Column(name = "A_STOCK_NO", length = 10)
	public String getAStockNo() {
		return this.AStockNo;
	}

	public void setAStockNo(String AStockNo) {
		this.AStockNo = AStockNo;
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

	@Column(name = "PRODUCE_CAPACITY", precision = 10, scale = 0)
	public Double getProduceCapacity() {
		return this.produceCapacity;
	}

	public void setProduceCapacity(Double produceCapacity) {
		this.produceCapacity = produceCapacity;
	}

	@Column(name = "PRODUCE_CAPACITY_TYPE", length = 1)
	public String getProduceCapacityType() {
		return this.produceCapacityType;
	}

	public void setProduceCapacityType(String produceCapacityType) {
		this.produceCapacityType = produceCapacityType;
	}

	@Column(name = "DIVICE_RATE", length = 3)
	public String getDiviceRate() {
		return this.diviceRate;
	}

	public void setDiviceRate(String diviceRate) {
		this.diviceRate = diviceRate;
	}

	@Column(name = "STRATEGY_ID", precision = 5, scale = 0)
	public Integer getStrategyId() {
		return this.strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	@Column(name = "REF_RATE_FLAG", length = 1)
	public String getRefRateFlag() {
		return this.refRateFlag;
	}

	public void setRefRateFlag(String refRateFlag) {
		this.refRateFlag = refRateFlag;
	}
}