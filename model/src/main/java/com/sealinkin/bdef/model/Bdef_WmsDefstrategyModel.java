package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_WmsDefstrategy;

public class Bdef_WmsDefstrategyModel extends Bdef_WmsDefstrategy{

	private static final long serialVersionUID = 1L;
	private String strategyType;
	private Double strategyId;
	private String defaultFlagText;
	
	public String getStrategyType() {
		return strategyType;
	}
	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	public Double getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(Double strategyId) {
		this.strategyId = strategyId;
	}
	public String getDefaultFlagText() {
		return defaultFlagText;
	}
	public void setDefaultFlagText(String defaultFlagText) {
		this.defaultFlagText = defaultFlagText;
	}
	
}
