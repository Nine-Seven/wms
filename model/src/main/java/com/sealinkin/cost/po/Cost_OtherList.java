package com.sealinkin.cost.po;

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
 * CostOtherList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_OTHER_LIST")
public class Cost_OtherList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_OtherListId id;
	private String costFlag;
	private Double costValue;
	private String status;
	private String checkNo;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String createFlag;

	// Constructors

	/** default constructor */
	public Cost_OtherList() {
	}

	/** minimal constructor */
	public Cost_OtherList(Cost_OtherListId id, String costFlag, String status,
			String rgstName, Date rgstDate, String createFlag) {
		this.id = id;
		this.costFlag = costFlag;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.createFlag = createFlag;
	}

	/** full constructor */
	public Cost_OtherList(Cost_OtherListId id, String costFlag, Double costValue,
			String status, String checkNo, String remark, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String createFlag) {
		this.id = id;
		this.costFlag = costFlag;
		this.costValue = costValue;
		this.status = status;
		this.checkNo = checkNo;
		this.remark = remark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.createFlag = createFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "costNo", column = @Column(name = "COST_NO", nullable = false, length = 8)),
			@AttributeOverride(name = "costDate", column = @Column(name = "COST_DATE", nullable = false, length = 7)) })
	public Cost_OtherListId getId() {
		return this.id;
	}

	public void setId(Cost_OtherListId id) {
		this.id = id;
	}

	@Column(name = "COST_FLAG", nullable = false, length = 1)
	public String getCostFlag() {
		return this.costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}

	@Column(name = "COST_VALUE", precision = 12, scale = 3)
	public Double getCostValue() {
		return this.costValue;
	}

	public void setCostValue(Double costValue) {
		this.costValue = costValue;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CHECK_NO", length = 25)
	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Column(name = "REMARK", length = 225)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

}