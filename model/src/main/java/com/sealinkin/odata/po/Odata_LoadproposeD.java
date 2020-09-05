package com.sealinkin.odata.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Odata_LoadproposeD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_LOADPROPOSE_D")
public class Odata_LoadproposeD implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Odata_LoadproposeDId id;
	private String custNo;
	private String containerType;
	private short loadOrder;
	private String expNo;
	private String status;
	private String deliverObj;
	private String loadName;
	private Date loadDate;
	private String assignName;
	private Date expDate;

	// Constructors

	/** default constructor */
	public Odata_LoadproposeD() {
	}

	/** minimal constructor */
	public Odata_LoadproposeD(Odata_LoadproposeDId id, String custNo,
			String containerType, String expNo, String status,
			String deliverObj, String assignName, Date expDate) {
		this.id = id;
		this.custNo = custNo;
		this.containerType = containerType;
		this.expNo = expNo;
		this.status = status;
		this.deliverObj = deliverObj;
		this.assignName = assignName;
		this.expDate = expDate;
	}

	/** full constructor */
	public Odata_LoadproposeD(Odata_LoadproposeDId id, String custNo,
			String containerType, short loadOrder, String expNo, String status,
			String deliverObj, String loadName, Date loadDate,
			String assignName, Date expDate) {
		this.id = id;
		this.custNo = custNo;
		this.containerType = containerType;
		this.loadOrder = loadOrder;
		this.expNo = expNo;
		this.status = status;
		this.deliverObj = deliverObj;
		this.loadName = loadName;
		this.loadDate = loadDate;
		this.assignName = assignName;
		this.expDate = expDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "loadproposeNo", column = @Column(name = "LOADPROPOSE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "containerNo", column = @Column(name = "CONTAINER_NO", nullable = false, length = 24)) })
	public Odata_LoadproposeDId getId() {
		return this.id;
	}

	public void setId(Odata_LoadproposeDId id) {
		this.id = id;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "CONTAINER_TYPE", nullable = false, length = 1)
	public String getContainerType() {
		return this.containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	@Column(name = "LOAD_ORDER", precision = 4, scale = 0)
	public short getLoadOrder() {
		return this.loadOrder;
	}

	public void setLoadOrder(short loadOrder) {
		this.loadOrder = loadOrder;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DELIVER_OBJ", nullable = false, length = 24)
	public String getDeliverObj() {
		return this.deliverObj;
	}

	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}

	@Column(name = "LOAD_NAME", length = 20)
	public String getLoadName() {
		return this.loadName;
	}

	public void setLoadName(String loadName) {
		this.loadName = loadName;
	}

	@Column(name = "LOAD_DATE", length = 7)
	public Date getLoadDate() {
		return this.loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	@Column(name = "ASSIGN_NAME", nullable = false, length = 20)
	public String getAssignName() {
		return this.assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

}