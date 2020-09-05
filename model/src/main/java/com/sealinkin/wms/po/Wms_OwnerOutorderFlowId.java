package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOwnerOutorderFlowId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OwnerOutorderFlowId implements java.io.Serializable {

	// Fields

	private String ownerNo;
	private String expType;
	private Short flowOrder;
	private String enterpriseNo;
	// Constructors

	/** default constructor */
	public Wms_OwnerOutorderFlowId() {
	}

	/** full constructor */
	public Wms_OwnerOutorderFlowId(String ownerNo, String expType,
			Short flowOrder,String enterpriseNo) {
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.flowOrder = flowOrder;
		this.enterpriseNo=enterpriseNo;
	}

	// Property accessors
	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 5)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@Column(name = "FLOW_ORDER", nullable = false, precision = 3, scale = 0)
	public Short getFlowOrder() {
		return this.flowOrder;
	}

	public void setFlowOrder(Short flowOrder) {
		this.flowOrder = flowOrder;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OwnerOutorderFlowId))
			return false;
		Wms_OwnerOutorderFlowId castOther = (Wms_OwnerOutorderFlowId) other;

		return ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
				.getOwnerNo() != null && castOther.getOwnerNo() != null && this
				.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getExpType() == castOther.getExpType()) || (this
						.getExpType() != null && castOther.getExpType() != null && this
						.getExpType().equals(castOther.getExpType())))
				&& ((this.getFlowOrder() == castOther.getFlowOrder()) || (this
						.getFlowOrder() != null
						&& castOther.getFlowOrder() != null && this
						.getFlowOrder().equals(castOther.getFlowOrder())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getExpType() == null ? 0 : this.getExpType().hashCode());
		result = 37 * result
				+ (getFlowOrder() == null ? 0 : this.getFlowOrder().hashCode());
		return result;
	}

}