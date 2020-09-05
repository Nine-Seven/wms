package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;

public class PackInfoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String unit;
    private Double packQty;
    private Double palBaseBox;
    private Double palHeightBox;
    
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}
	public Double getPalBaseBox() {
		return palBaseBox;
	}
	public void setPalBaseBox(Double palBaseBox) {
		this.palBaseBox = palBaseBox;
	}
	public Double getPalHeightBox() {
		return palHeightBox;
	}
	public void setPalHeightBox(Double palHeightBox) {
		this.palHeightBox = palHeightBox;
	}
    
    
}
