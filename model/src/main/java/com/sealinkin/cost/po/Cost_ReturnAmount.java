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
 * CostReturnAmount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_RETURN_AMOUNT")
public class Cost_ReturnAmount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Cost_ReturnAmountId id;
	private Double realAmount;
	private String rgstName;
	private Date rgstDate;
	private String costFlag;

	// Constructors

	/** default constructor */
	public Cost_ReturnAmount() {
	}

	/** minimal constructor */
	public Cost_ReturnAmount(Cost_ReturnAmountId id, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_ReturnAmount(Cost_ReturnAmountId id, Double realAmount,
			String rgstName, Date rgstDate, String costFlag) {
		this.id = id;
		this.realAmount = realAmount;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.costFlag = costFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 4, scale = 0)) })
	public Cost_ReturnAmountId getId() {
		return this.id;
	}

	public void setId(Cost_ReturnAmountId id) {
		this.id = id;
	}

	@Column(name = "REAL_AMOUNT", precision = 12, scale = 3)
	public Double getRealAmount() {
		return this.realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
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

	@Column(name = "COST_FLAG", length = 1)
	public String getCostFlag() {
		return this.costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}

}