package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefstrategyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_WmsDefstrategyId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String strategyType;
	private Double strategyId;

	// Constructors

	/** default constructor */
	public Bdef_WmsDefstrategyId() {
	}

	/** full constructor */
	public Bdef_WmsDefstrategyId(String strategyType, Double strategyId) {
		this.strategyType = strategyType;
		this.strategyId = strategyId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_WmsDefstrategyId))
			return false;
		Bdef_WmsDefstrategyId castOther = (Bdef_WmsDefstrategyId) other;

		return ((this.getStrategyType() == castOther.getStrategyType()) || (this
				.getStrategyType() != null
				&& castOther.getStrategyType() != null && this
				.getStrategyType().equals(castOther.getStrategyType())))
				&& ((this.getStrategyId() == castOther.getStrategyId()) || (this
						.getStrategyId() != null
						&& castOther.getStrategyId() != null && this
						.getStrategyId().equals(castOther.getStrategyId())));
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
		return result;
	}

}