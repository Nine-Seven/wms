package com.sealinkin.odata.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ODATA_CAR_RECEIPT")
public class Odata_CarReceipt implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Odata_CarReceiptId id;
	private String custNo;
	private BigDecimal deliverBox;
	private BigDecimal backBox;
	private String carNo;
	private String driverWorker;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_CarReceipt() {
	}

	/** minimal constructor */
	public Odata_CarReceipt(Odata_CarReceiptId id, String custNo,
			BigDecimal deliverBox, BigDecimal backBox, String carNo,
			String status, String rgstName, Date rgstDate) {
		this.id = id;
		this.custNo = custNo;
		this.deliverBox = deliverBox;
		this.backBox = backBox;
		this.carNo = carNo;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_CarReceipt(Odata_CarReceiptId id, String custNo,
			BigDecimal deliverBox, BigDecimal backBox, String carNo,
			String driverWorker, String status, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.custNo = custNo;
		this.deliverBox = deliverBox;
		this.backBox = backBox;
		this.carNo = carNo;
		this.driverWorker = driverWorker;
		this.status = status;
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
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "waveNo", column = @Column(name = "WAVE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "deliverNo", column = @Column(name = "DELIVER_NO", nullable = false, length = 20)) })
	public Odata_CarReceiptId getId() {
		return this.id;
	}

	public void setId(Odata_CarReceiptId id) {
		this.id = id;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "DELIVER_BOX", nullable = false, precision = 22, scale = 0)
	public BigDecimal getDeliverBox() {
		return this.deliverBox;
	}

	public void setDeliverBox(BigDecimal deliverBox) {
		this.deliverBox = deliverBox;
	}

	@Column(name = "BACK_BOX", nullable = false, precision = 22, scale = 0)
	public BigDecimal getBackBox() {
		return this.backBox;
	}

	public void setBackBox(BigDecimal backBox) {
		this.backBox = backBox;
	}

	@Column(name = "CAR_NO", nullable = false, length = 10)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "DRIVER_WORKER", length = 50)
	public String getDriverWorker() {
		return this.driverWorker;
	}

	public void setDriverWorker(String driverWorker) {
		this.driverWorker = driverWorker;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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