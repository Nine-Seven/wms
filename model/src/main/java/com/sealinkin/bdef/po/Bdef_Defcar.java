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
 * BdefDefcar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFCAR")
public class Bdef_Defcar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bdef_DefcarId id;
	private String cartypeNo;
	private String carName;
	private String carPlate;
	private Double oilConsume;
	private Double careMile;
	private Double mile;
	private Date careDate;
	private String careWorker;
	private String sanplNo;
	private String carClass;
	private String driverWorker;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bdef_Defcar() {
	}

	/** minimal constructor */
	public Bdef_Defcar(Bdef_DefcarId id, String cartypeNo, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.cartypeNo = cartypeNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_Defcar(Bdef_DefcarId id, String cartypeNo, String carName,
			String carPlate, Double oilConsume, Double careMile, Double mile,
			Date careDate, String careWorker, String sanplNo, String carClass,
			String driverWorker, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.cartypeNo = cartypeNo;
		this.carName = carName;
		this.carPlate = carPlate;
		this.oilConsume = oilConsume;
		this.careMile = careMile;
		this.mile = mile;
		this.careDate = careDate;
		this.careWorker = careWorker;
		this.sanplNo = sanplNo;
		this.carClass = carClass;
		this.driverWorker = driverWorker;
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
			@AttributeOverride(name = "carNo", column = @Column(name = "CAR_NO", nullable = false, length = 10)) })
	public Bdef_DefcarId getId() {
		return this.id;
	}

	public void setId(Bdef_DefcarId id) {
		this.id = id;
	}

	@Column(name = "CARTYPE_NO", nullable = false, length = 10)
	public String getCartypeNo() {
		return this.cartypeNo;
	}

	public void setCartypeNo(String cartypeNo) {
		this.cartypeNo = cartypeNo;
	}

	@Column(name = "CAR_NAME", length = 20)
	public String getCarName() {
		return this.carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	@Column(name = "CAR_PLATE", length = 20)
	public String getCarPlate() {
		return this.carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	@Column(name = "OIL_CONSUME", precision = 13, scale = 5)
	public Double getOilConsume() {
		return this.oilConsume;
	}

	public void setOilConsume(Double oilConsume) {
		this.oilConsume = oilConsume;
	}

	@Column(name = "CARE_MILE", precision = 13, scale = 5)
	public Double getCareMile() {
		return this.careMile;
	}

	public void setCareMile(Double careMile) {
		this.careMile = careMile;
	}

	@Column(name = "MILE", precision = 13, scale = 5)
	public Double getMile() {
		return this.mile;
	}

	public void setMile(Double mile) {
		this.mile = mile;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CARE_DATE", length = 7)
	public Date getCareDate() {
		return this.careDate;
	}

	public void setCareDate(Date careDate) {
		this.careDate = careDate;
	}

	@Column(name = "CARE_WORKER", length = 50)
	public String getCareWorker() {
		return this.careWorker;
	}

	public void setCareWorker(String careWorker) {
		this.careWorker = careWorker;
	}

	@Column(name = "SANPL_NO", length = 15)
	public String getSanplNo() {
		return this.sanplNo;
	}

	public void setSanplNo(String sanplNo) {
		this.sanplNo = sanplNo;
	}

	@Column(name = "CAR_CLASS", length = 20)
	public String getCarClass() {
		return this.carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	@Column(name = "DRIVER_WORKER", length = 50)
	public String getDriverWorker() {
		return this.driverWorker;
	}

	public void setDriverWorker(String driverWorker) {
		this.driverWorker = driverWorker;
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