package com.sealinkin.fcdata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FcdataPlanD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_PLAN_D")
public class Fcdata_PlanD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fcdata_PlanDId id;
	private Long planId;

	// Constructors

	/** default constructor */
	public Fcdata_PlanD() {
	}

	/** full constructor */
	public Fcdata_PlanD(Fcdata_PlanDId id, Long planId) {
		this.id = id;
		this.planId = planId;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "planNo", column = @Column(name = "PLAN_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaNo", column = @Column(name = "AREA_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "stockNo", column = @Column(name = "STOCK_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)) })
	public Fcdata_PlanDId getId() {
		return this.id;
	}

	public void setId(Fcdata_PlanDId id) {
		this.id = id;
	}

	@Column(name = "PLAN_ID", nullable = false, precision = 10, scale = 0)
	public Long getPlanId() {
		return this.planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

}