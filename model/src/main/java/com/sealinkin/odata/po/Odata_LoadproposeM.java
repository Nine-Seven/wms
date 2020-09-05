package com.sealinkin.odata.po;
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
 * OdataLoadproposeM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_LOADPROPOSE_M")
public class Odata_LoadproposeM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_LoadproposeMId id;
	private Date operateDate;
	private String cartypeNo;
	private String lineNo;
	private String dockNo;
	private String status;
	private String carPlate;
	private String sealNo;
	private String custNo;
	private String divideTruck;
	private String sendFlag;
	private String shipperNo;
	private String realShipperNo;
	private Date expDate;
	private String carPlanNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String loadtype;

	// Constructors

	/** default constructor */
	public Odata_LoadproposeM() {
	}

	/** minimal constructor */
	public Odata_LoadproposeM(Odata_LoadproposeMId id, Date operateDate,
			String cartypeNo, String lineNo, String dockNo, String status,
			String custNo, String sendFlag, String shipperNo, Date expDate,
			String rgstName, Date rgstDate, String loadtype) {
		this.id = id;
		this.operateDate = operateDate;
		this.cartypeNo = cartypeNo;
		this.lineNo = lineNo;
		this.dockNo = dockNo;
		this.status = status;
		this.custNo = custNo;
		this.sendFlag = sendFlag;
		this.shipperNo = shipperNo;
		this.expDate = expDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.loadtype = loadtype;
	}

	/** full constructor */
	public Odata_LoadproposeM(Odata_LoadproposeMId id, Date operateDate,
			String cartypeNo, String lineNo, String dockNo, String status,
			String carPlate, String sealNo, String custNo, String divideTruck,
			String sendFlag, String shipperNo, String realShipperNo,
			Date expDate, String carPlanNo, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String loadtype) {
		this.id = id;
		this.operateDate = operateDate;
		this.cartypeNo = cartypeNo;
		this.lineNo = lineNo;
		this.dockNo = dockNo;
		this.status = status;
		this.carPlate = carPlate;
		this.sealNo = sealNo;
		this.custNo = custNo;
		this.divideTruck = divideTruck;
		this.sendFlag = sendFlag;
		this.shipperNo = shipperNo;
		this.realShipperNo = realShipperNo;
		this.expDate = expDate;
		this.carPlanNo = carPlanNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.loadtype = loadtype;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "loadproposeNo", column = @Column(name = "LOADPROPOSE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Odata_LoadproposeMId getId() {
		return this.id;
	}

	public void setId(Odata_LoadproposeMId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "CARTYPE_NO", nullable = false, length = 10)
	public String getCartypeNo() {
		return this.cartypeNo;
	}

	public void setCartypeNo(String cartypeNo) {
		this.cartypeNo = cartypeNo;
	}

	@Column(name = "LINE_NO", nullable = false, length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "DOCK_NO", nullable = false, length = 3)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CAR_PLATE", length = 20)
	public String getCarPlate() {
		return this.carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	@Column(name = "SEAL_NO", length = 30)
	public String getSealNo() {
		return this.sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "DIVIDE_TRUCK", length = 20)
	public String getDivideTruck() {
		return this.divideTruck;
	}

	public void setDivideTruck(String divideTruck) {
		this.divideTruck = divideTruck;
	}

	@Column(name = "SEND_FLAG", nullable = false, length = 2)
	public String getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Column(name = "SHIPPER_NO", nullable = false, length = 15)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	@Column(name = "REAL_SHIPPER_NO", length = 15)
	public String getRealShipperNo() {
		return this.realShipperNo;
	}

	public void setRealShipperNo(String realShipperNo) {
		this.realShipperNo = realShipperNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "CAR_PLAN_NO", length = 20)
	public String getCarPlanNo() {
		return this.carPlanNo;
	}

	public void setCarPlanNo(String carPlanNo) {
		this.carPlanNo = carPlanNo;
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

	@Column(name = "LOADTYPE", nullable = false, length = 1)
	public String getLoadtype() {
		return this.loadtype;
	}

	public void setLoadtype(String loadtype) {
		this.loadtype = loadtype;
	}

}