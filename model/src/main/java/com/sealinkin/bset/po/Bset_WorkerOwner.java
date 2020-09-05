package com.sealinkin.bset.po;
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
 * Bset_WorkerOwner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_WORKER_OWNER")
public class Bset_WorkerOwner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Bset_WorkerOwnerId id;
	private BigDecimal sortOrder;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_WorkerOwner() {
	}

	/** minimal constructor */
	public Bset_WorkerOwner(Bset_WorkerOwnerId id, BigDecimal sortOrder,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.sortOrder = sortOrder;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bset_WorkerOwner(Bset_WorkerOwnerId id, BigDecimal sortOrder,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.sortOrder = sortOrder;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "workerNo", column = @Column(name = "WORKER_NO", nullable = false, length = 50)) })
	public Bset_WorkerOwnerId getId() {
		return this.id;
	}

	public void setId(Bset_WorkerOwnerId id) {
		this.id = id;
	}

	@Column(name = "SORT_ORDER", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(BigDecimal sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 10)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 10)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}