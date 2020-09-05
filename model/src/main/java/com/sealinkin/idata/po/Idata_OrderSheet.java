package com.sealinkin.idata.po;
// default package

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Idata_OrderSheet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_ORDER_SHEET")
public class Idata_OrderSheet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Idata_OrderSheetId id;
	private BigDecimal skuQty;
	private BigDecimal palQty;
	private double totalVolumn;
	private double totalWeight;
	private double totalPacking;
	private String cancelSerial;
	private String printFlag;
	private Date rgstDate;
	private String rgstName;

	// Constructors

	/** default constructor */
	public Idata_OrderSheet() {
	}

	/** minimal constructor */
	public Idata_OrderSheet(Idata_OrderSheetId id, BigDecimal skuQty,
			BigDecimal palQty, double totalVolumn, double totalWeight,
			double totalPacking, String printFlag) {
		this.id = id;
		this.skuQty = skuQty;
		this.palQty = palQty;
		this.totalVolumn = totalVolumn;
		this.totalWeight = totalWeight;
		this.totalPacking = totalPacking;
		this.printFlag = printFlag;
	}

	/** full constructor */
	public Idata_OrderSheet(Idata_OrderSheetId id, BigDecimal skuQty,
			BigDecimal palQty, double totalVolumn, double totalWeight,
			double totalPacking, String cancelSerial, String printFlag,
			Date rgstDate, String rgstName) {
		this.id = id;
		this.skuQty = skuQty;
		this.palQty = palQty;
		this.totalVolumn = totalVolumn;
		this.totalWeight = totalWeight;
		this.totalPacking = totalPacking;
		this.cancelSerial = cancelSerial;
		this.printFlag = printFlag;
		this.rgstDate = rgstDate;
		this.rgstName = rgstName;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "orderSerial", column = @Column(name = "ORDER_SERIAL", nullable = false, length = 20)),
			@AttributeOverride(name = "importNo", column = @Column(name = "IMPORT_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "poType", column = @Column(name = "PO_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "cancelStatus", column = @Column(name = "CANCEL_STATUS", nullable = false, length = 1)) })
	public Idata_OrderSheetId getId() {
		return this.id;
	}

	public void setId(Idata_OrderSheetId id) {
		this.id = id;
	}

	@Column(name = "SKU_QTY", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSkuQty() {
		return this.skuQty;
	}

	public void setSkuQty(BigDecimal skuQty) {
		this.skuQty = skuQty;
	}

	@Column(name = "PAL_QTY", nullable = false, precision = 22, scale = 0)
	public BigDecimal getPalQty() {
		return this.palQty;
	}

	public void setPalQty(BigDecimal palQty) {
		this.palQty = palQty;
	}

	@Column(name = "TOTAL_VOLUMN", nullable = false, precision = 15)
	public double getTotalVolumn() {
		return this.totalVolumn;
	}

	public void setTotalVolumn(double totalVolumn) {
		this.totalVolumn = totalVolumn;
	}

	@Column(name = "TOTAL_WEIGHT", nullable = false, precision = 15)
	public double getTotalWeight() {
		return this.totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	@Column(name = "TOTAL_PACKING", nullable = false, precision = 15)
	public double getTotalPacking() {
		return this.totalPacking;
	}

	public void setTotalPacking(double totalPacking) {
		this.totalPacking = totalPacking;
	}

	@Column(name = "CANCEL_SERIAL", length = 20)
	public String getCancelSerial() {
		return this.cancelSerial;
	}

	public void setCancelSerial(String cancelSerial) {
		this.cancelSerial = cancelSerial;
	}

	@Column(name = "PRINT_FLAG", nullable = false, length = 1)
	public String getPrintFlag() {
		return this.printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

}