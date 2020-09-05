package com.sealinkin.bdef.po;
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
 * BdefDefloc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFLOC")
public class Bdef_Defloc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_DeflocId id;
	private String warehouseName;
	private String memo;
	private String createFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String adress;
	private String linkman;
	private String tel;
	private String manageName;
	private String province;
	private String city;
	private String zone;
	private String companyNo;
	private String scanFlag;

	// Constructors

	/** default constructor */
	public Bdef_Defloc() {
	}

	/** minimal constructor */
	public Bdef_Defloc(Bdef_DeflocId id, String warehouseName, String createFlag,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.warehouseName = warehouseName;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_Defloc(Bdef_DeflocId id, String warehouseName, String memo,
			String createFlag, String rgstName, Date rgstDate, String updtName,
			Date updtDate, String adress, String linkman, String tel,
			String manageName, String province, String city, String zone,
			String companyNo, String scanFlag) {
		this.id = id;
		this.warehouseName = warehouseName;
		this.memo = memo;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.adress = adress;
		this.linkman = linkman;
		this.tel = tel;
		this.manageName = manageName;
		this.province = province;
		this.city = city;
		this.zone = zone;
		this.companyNo = companyNo;
		this.scanFlag = scanFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)) })
	public Bdef_DeflocId getId() {
		return this.id;
	}

	public void setId(Bdef_DeflocId id) {
		this.id = id;
	}

	@Column(name = "WAREHOUSE_NAME", nullable = false, length = 20)
	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@Column(name = "MEMO", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
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

	@Column(name = "ADRESS", length = 128)
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "LINKMAN", length = 10)
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "TEL", length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "MANAGE_NAME", length = 20)
	public String getManageName() {
		return this.manageName;
	}

	public void setManageName(String manageName) {
		this.manageName = manageName;
	}

	@Column(name = "PROVINCE", length = 10)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 20)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "ZONE", length = 20)
	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	@Column(name = "COMPANY_NO", length = 20)
	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	@Column(name = "SCAN_FLAG", length = 1)
	public String getScanFlag() {
		return this.scanFlag;
	}

	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}

}