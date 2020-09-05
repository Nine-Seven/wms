package com.sealinkin.wms.po;

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
 * WmsOwnerRiordertype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OWNER_RIORDERTYPE")
public class Wms_OwnerRiordertype implements java.io.Serializable {

	// Fields

	private Wms_OwnerRiordertypeId id;
	private String overQtyFlag;
	private String autoInstock;
	private String advanceLocate;
	private String deviceCompute;
	private String orderRsv01;
	private String orderRsv02;
	private String orderRsv03;
	private String orderRsv04;
	private String orderRsv05;
	private String orderRsv06;
	private String orderRsv07;
	private String orderRsv08;
	private Integer strategyId;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String mixOrdercheck;
	private String rsvLabelFlag;
	private String directCellFlag;
	private String printCheckFlag;
	private String autoCheckComfirFlag;
	private String directCellNo;

	// Constructors

	/** default constructor */
	public Wms_OwnerRiordertype() {
	}

	/** minimal constructor */
	public Wms_OwnerRiordertype(Wms_OwnerRiordertypeId id, String overQtyFlag,
			String autoInstock, String advanceLocate, String deviceCompute) {
		this.id = id;
		this.overQtyFlag = overQtyFlag;
		this.autoInstock = autoInstock;
		this.advanceLocate = advanceLocate;
		this.deviceCompute = deviceCompute;
	}

	/** full constructor */
	public Wms_OwnerRiordertype(Wms_OwnerRiordertypeId id, String overQtyFlag,
			String autoInstock, String advanceLocate, String deviceCompute,
			String orderRsv01, String orderRsv02, String orderRsv03,
			String orderRsv04, String orderRsv05, String orderRsv06,
			String orderRsv07, String orderRsv08, Integer strategyId,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String mixOrdercheck, String rsvLabelFlag, String directCellFlag,
			String printCheckFlag, String autoCheckComfirFlag,
			String directCellNo) {
		this.id = id;
		this.overQtyFlag = overQtyFlag;
		this.autoInstock = autoInstock;
		this.advanceLocate = advanceLocate;
		this.deviceCompute = deviceCompute;
		this.orderRsv01 = orderRsv01;
		this.orderRsv02 = orderRsv02;
		this.orderRsv03 = orderRsv03;
		this.orderRsv04 = orderRsv04;
		this.orderRsv05 = orderRsv05;
		this.orderRsv06 = orderRsv06;
		this.orderRsv07 = orderRsv07;
		this.orderRsv08 = orderRsv08;
		this.strategyId = strategyId;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.mixOrdercheck = mixOrdercheck;
		this.rsvLabelFlag = rsvLabelFlag;
		this.directCellFlag = directCellFlag;
		this.printCheckFlag = printCheckFlag;
		this.autoCheckComfirFlag = autoCheckComfirFlag;
		this.directCellNo = directCellNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "untreadType", column = @Column(name = "UNTREAD_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "classType", column = @Column(name = "CLASS_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "qualityFlag", column = @Column(name = "QUALITY_FLAG", nullable = false, length = 1)) })
	public Wms_OwnerRiordertypeId getId() {
		return this.id;
	}

	public void setId(Wms_OwnerRiordertypeId id) {
		this.id = id;
	}

	@Column(name = "OVER_QTY_FLAG", nullable = false, length = 1)
	public String getOverQtyFlag() {
		return this.overQtyFlag;
	}

	public void setOverQtyFlag(String overQtyFlag) {
		this.overQtyFlag = overQtyFlag;
	}

	@Column(name = "AUTO_INSTOCK", nullable = false, length = 1)
	public String getAutoInstock() {
		return this.autoInstock;
	}

	public void setAutoInstock(String autoInstock) {
		this.autoInstock = autoInstock;
	}

	@Column(name = "ADVANCE_LOCATE", nullable = false, length = 2)
	public String getAdvanceLocate() {
		return this.advanceLocate;
	}

	public void setAdvanceLocate(String advanceLocate) {
		this.advanceLocate = advanceLocate;
	}

	@Column(name = "DEVICE_COMPUTE", nullable = false, length = 1)
	public String getDeviceCompute() {
		return this.deviceCompute;
	}

	public void setDeviceCompute(String deviceCompute) {
		this.deviceCompute = deviceCompute;
	}

	@Column(name = "ORDER_RSV01", length = 20)
	public String getOrderRsv01() {
		return this.orderRsv01;
	}

	public void setOrderRsv01(String orderRsv01) {
		this.orderRsv01 = orderRsv01;
	}

	@Column(name = "ORDER_RSV02", length = 20)
	public String getOrderRsv02() {
		return this.orderRsv02;
	}

	public void setOrderRsv02(String orderRsv02) {
		this.orderRsv02 = orderRsv02;
	}

	@Column(name = "ORDER_RSV03", length = 20)
	public String getOrderRsv03() {
		return this.orderRsv03;
	}

	public void setOrderRsv03(String orderRsv03) {
		this.orderRsv03 = orderRsv03;
	}

	@Column(name = "ORDER_RSV04", length = 20)
	public String getOrderRsv04() {
		return this.orderRsv04;
	}

	public void setOrderRsv04(String orderRsv04) {
		this.orderRsv04 = orderRsv04;
	}

	@Column(name = "ORDER_RSV05", length = 1)
	public String getOrderRsv05() {
		return this.orderRsv05;
	}

	public void setOrderRsv05(String orderRsv05) {
		this.orderRsv05 = orderRsv05;
	}

	@Column(name = "ORDER_RSV06", length = 1)
	public String getOrderRsv06() {
		return this.orderRsv06;
	}

	public void setOrderRsv06(String orderRsv06) {
		this.orderRsv06 = orderRsv06;
	}

	@Column(name = "ORDER_RSV07", length = 1)
	public String getOrderRsv07() {
		return this.orderRsv07;
	}

	public void setOrderRsv07(String orderRsv07) {
		this.orderRsv07 = orderRsv07;
	}

	@Column(name = "ORDER_RSV08", length = 1)
	public String getOrderRsv08() {
		return this.orderRsv08;
	}

	public void setOrderRsv08(String orderRsv08) {
		this.orderRsv08 = orderRsv08;
	}

	@Column(name = "STRATEGY_ID", precision = 5, scale = 0)
	public Integer getStrategyId() {
		return this.strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
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

	@Column(name = "MIX_ORDERCHECK", length = 1)
	public String getMixOrdercheck() {
		return this.mixOrdercheck;
	}

	public void setMixOrdercheck(String mixOrdercheck) {
		this.mixOrdercheck = mixOrdercheck;
	}

	@Column(name = "RSV_LABEL_FLAG", length = 1)
	public String getRsvLabelFlag() {
		return this.rsvLabelFlag;
	}

	public void setRsvLabelFlag(String rsvLabelFlag) {
		this.rsvLabelFlag = rsvLabelFlag;
	}

	@Column(name = "DIRECT_CELL_FLAG", length = 1)
	public String getDirectCellFlag() {
		return this.directCellFlag;
	}

	public void setDirectCellFlag(String directCellFlag) {
		this.directCellFlag = directCellFlag;
	}

	@Column(name = "PRINT_CHECK_FLAG", length = 1)
	public String getPrintCheckFlag() {
		return this.printCheckFlag;
	}

	public void setPrintCheckFlag(String printCheckFlag) {
		this.printCheckFlag = printCheckFlag;
	}

	@Column(name = "AUTO_CHECK_COMFIR_FLAG", length = 1)
	public String getAutoCheckComfirFlag() {
		return this.autoCheckComfirFlag;
	}

	public void setAutoCheckComfirFlag(String autoCheckComfirFlag) {
		this.autoCheckComfirFlag = autoCheckComfirFlag;
	}

	@Column(name = "DIRECT_CELL_NO", length = 50)
	public String getDirectCellNo() {
		return this.directCellNo;
	}

	public void setDirectCellNo(String directCellNo) {
		this.directCellNo = directCellNo;
	}

}