package com.sealinkin.bset.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BsetUserList entity.
 * 用户�? * @author lich
 */
@Entity
@Table(name = "BDEF_DEFWORKER")
public class Bset_User_List implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterPriseNo;
	private String workerNo;
	private String workerName;
	private Integer status;
	private String pwd;
	private String ownerNo;
	private String warehouseNo;
	private Integer sex;
	private String tel;
	private String address;
	private String title;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String workerRfid;
	private String deptName;
	private String operateStatus;

	// Constructors

	/** default constructor */
	public Bset_User_List() {
	}

	/** minimal constructor */
	public Bset_User_List(String enterPriseNo,String workerNo, String workerName, Integer status,
			String pwd, String workerRfid) {
		this.enterPriseNo = enterPriseNo;
		this.workerNo = workerNo;
		this.workerName = workerName;
		this.status = status;
		this.pwd = pwd;
		this.workerRfid = workerRfid;
	}

	/** full constructor */
	public Bset_User_List(String enterPriseNo,String workerNo, String workerName, Integer status,
			String pwd, String ownerNo, String warehouseNo, Integer sex,
			String tel, String address, String title, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String workerRfid,
			String deptName, String operateStatus) {
		this.enterPriseNo = enterPriseNo;
		this.workerNo = workerNo;
		this.workerName = workerName;
		this.status = status;
		this.pwd = pwd;
		this.ownerNo = ownerNo;
		this.warehouseNo = warehouseNo;
		this.sex = sex;
		this.tel = tel;
		this.address = address;
		this.title = title;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.workerRfid = workerRfid;
		this.deptName = deptName;
		this.operateStatus = operateStatus;
	}

	// Property accessors
	@Id
	@Column(name = "enterPrise_no", unique = true, nullable = false, length = 10)
	public String getEnterPriseNo() {
		return this.enterPriseNo;
	}

	public void setEnterPriseNo(String enterPriseNo) {
		this.enterPriseNo = enterPriseNo;
	}
	@Id
	@Column(name = "WORKER_NO", unique = true, nullable = false, length = 10)
	public String getWorkerNo() {
		return this.workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	@Column(name = "WORKER_NAME", nullable = false, length = 50)
	public String getWorkerName() {
		return this.workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	@Column(name = "STATUS", nullable = false, precision = 22, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "PWD", nullable = false, length = 64)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "OWNER_NO", length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "WAREHOUSE_NO", length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "SEX", precision = 22, scale = 0)
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "TEL", length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "ADDRESS", length = 90)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TITLE", length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Column(name = "WORKER_RFID", nullable = false, length = 20)
	public String getWorkerRfid() {
		return this.workerRfid;
	}

	public void setWorkerRfid(String workerRfid) {
		this.workerRfid = workerRfid;
	}

	@Column(name = "DEPT_NAME", length = 200)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "OPERATE_STATUS", length = 1)
	public String getOperateStatus() {
		return this.operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

}