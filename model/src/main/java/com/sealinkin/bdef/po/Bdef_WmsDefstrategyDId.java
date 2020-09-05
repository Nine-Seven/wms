package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefstrategyDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_WmsDefstrategyDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String strategyType;
	private Double strategyId;
	private Double ruleOrder;

	// Constructors

	/** default constructor */
	public Bdef_WmsDefstrategyDId() {
	}

	/** full constructor */
	public Bdef_WmsDefstrategyDId(String strategyType, Double strategyId,
			Double ruleOrder) {
		this.strategyType = strategyType;
		this.strategyId = strategyId;
		this.ruleOrder = ruleOrder;
	}

	// Property accessors

	@Column(name = "STRATEGY_TYPE", nullable = false, length = 2)
	public String getStrategyType() {
		return this.strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	@Column(name = "STRATEGY_ID", nullable = false, precision = 3, scale = 0)
	public Double getStrategyId() {
		return this.strategyId;
	}

	public void setStrategyId(Double strategyId) {
		this.strategyId = strategyId;
	}

	@Column(name = "RULE_ORDER", nullable = false, precision = 3, scale = 0)
	public Double getRuleOrder() {
		return this.ruleOrder;
	}

	public void setRuleOrder(Double ruleOrder) {
		this.ruleOrder = ruleOrder;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_WmsDefstrategyDId))
			return false;
		Bdef_WmsDefstrategyDId castOther = (Bdef_WmsDefstrategyDId) other;

		return ((this.getStrategyType() == castOther.getStrategyType()) || (this
				.getStrategyType() != null
				&& castOther.getStrategyType() != null && this
				.getStrategyType().equals(castOther.getStrategyType())))
				&& ((this.getStrategyId() == castOther.getStrategyId()) || (this
						.getStrategyId() != null
						&& castOther.getStrategyId() != null && this
						.getStrategyId().equals(castOther.getStrategyId())))
				&& ((this.getRuleOrder() == castOther.getRuleOrder()) || (this
						.getRuleOrder() != null
						&& castOther.getRuleOrder() != null && this
						.getRuleOrder().equals(castOther.getRuleOrder())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getStrategyType() == null ? 0 : this.getStrategyType()
						.hashCode());
		result = 37
				* result
				+ (getStrategyId() == null ? 0 : this.getStrategyId()
						.hashCode());
		result = 37 * result
				+ (getRuleOrder() == null ? 0 : this.getRuleOrder().hashCode());
		return result;
	}

}