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
 * DeviceDivideD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEVICE_DIVIDE_D")
public class Device_DivideD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Device_DivideDId id;
	private String deviceGroupNo;
	private String bayX;
	private String stockY;
	private Integer mixFlag;
	private String mixSupplier;
	private Integer maxQty;
	private Double maxWeight;
	private Double maxVolume;
	private Double maxCase;
	private String status;
	private String checkStatus;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private Integer pickOrder;

	// Constructors

	/** default constructor */
	public Device_DivideD() {
	}

	/** minimal constructor */
	public Device_DivideD(Device_DivideDId id, String deviceGroupNo, String bayX,
			String stockY, Integer mixFlag, String mixSupplier, Integer maxQty,
			Double maxWeight, Double maxVolume, Double maxCase, String status,
			String checkStatus, String rgstName, Date rgstDate,
			Integer pickOrder) {
		this.id = id;
		this.deviceGroupNo = deviceGroupNo;
		this.bayX = bayX;
		this.stockY = stockY;
		this.mixFlag = mixFlag;
		this.mixSupplier = mixSupplier;
		this.maxQty = maxQty;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.maxCase = maxCase;
		this.status = status;
		this.checkStatus = checkStatus;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.pickOrder = pickOrder;
	}

	/** full constructor */
	public Device_DivideD(Device_DivideDId id, String deviceGroupNo, String bayX,
			String stockY, Integer mixFlag, String mixSupplier, Integer maxQty,
			Double maxWeight, Double maxVolume, Double maxCase, String status,
			String checkStatus, String rgstName, Date rgstDate,
			String updtName, Date updtDate, Integer pickOrder) {
		this.id = id;
		this.deviceGroupNo = deviceGroupNo;
		this.bayX = bayX;
		this.stockY = stockY;
		this.mixFlag = mixFlag;
		this.mixSupplier = mixSupplier;
		this.maxQty = maxQty;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.maxCase = maxCase;
		this.status = status;
		this.checkStatus = checkStatus;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.pickOrder = pickOrder;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "deviceNo", column = @Column(name = "DEVICE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "deviceCellNo", column = @Column(name = "DEVICE_CELL_NO", nullable = false, length = 24)) })
	public Device_DivideDId getId() {
		return this.id;
	}

	public void setId(Device_DivideDId id) {
		this.id = id;
	}

	@Column(name = "DEVICE_GROUP_NO", nullable = false, length = 5)
	public String getDeviceGroupNo() {
		return this.deviceGroupNo;
	}

	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
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
	public Integer getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Integer maxQty) {
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

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CHECK_STATUS", nullable = false, length = 1)
	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
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

	@Column(name = "PICK_ORDER", nullable = false, precision = 6, scale = 0)
	public Integer getPickOrder() {
		return this.pickOrder;
	}

	public void setPickOrder(Integer pickOrder) {
		this.pickOrder = pickOrder;
	}

}