package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Wms_OutorderandflowId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OutorderandflowId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private short flowValue;
	private String flowText;

	// Constructors

	/** default constructor */
	public Wms_OutorderandflowId() {
	}

	/** full constructor */
	public Wms_OutorderandflowId(short flowValue, String flowText) {
		this.flowValue = flowValue;
		this.flowText = flowText;
	}

	// Property accessors

	@Column(name = "FLOW_VALUE", precision = 3, scale = 0)
	public short getFlowValue() {
		return this.flowValue;
	}

	public void setFlowValue(short flowValue) {
		this.flowValue = flowValue;
	}

	@Column(name = "FLOW_TEXT", length = 24)
	public String getFlowText() {
		return this.flowText;
	}

	public void setFlowText(String flowText) {
		this.flowText = flowText;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OutorderandflowId))
			return false;
		Wms_OutorderandflowId castOther = (Wms_OutorderandflowId) other;

		return (this.getFlowValue() == castOther.getFlowValue())
				&& ((this.getFlowText() == castOther.getFlowText()) || (this
						.getFlowText() != null
						&& castOther.getFlowText() != null && this
						.getFlowText().equals(castOther.getFlowText())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getFlowValue();
		result = 37 * result
				+ (getFlowText() == null ? 0 : this.getFlowText().hashCode());
		return result;
	}

}