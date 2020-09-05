package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Wms_OutorderFlowId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OutorderFlowId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String expType;
	private short flowOrder;

	// Constructors

	/** default constructor */
	public Wms_OutorderFlowId() {
	}

	/** full constructor */
	public Wms_OutorderFlowId(String enterpriseNo, String expType,
			short flowOrder) {
		this.enterpriseNo = enterpriseNo;
		this.expType = expType;
		this.flowOrder = flowOrder;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 2)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@Column(name = "FLOW_ORDER", nullable = false, precision = 3, scale = 0)
	public short getFlowOrder() {
		return this.flowOrder;
	}

	public void setFlowOrder(short flowOrder) {
		this.flowOrder = flowOrder;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OutorderFlowId))
			return false;
		Wms_OutorderFlowId castOther = (Wms_OutorderFlowId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getExpType() == castOther.getExpType()) || (this
						.getExpType() != null && castOther.getExpType() != null && this
						.getExpType().equals(castOther.getExpType())))
				&& (this.getFlowOrder() == castOther.getFlowOrder());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getExpType() == null ? 0 : this.getExpType().hashCode());
		result = 37 * result + this.getFlowOrder();
		return result;
	}

}