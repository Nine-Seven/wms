package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsWarehouseOutorderFlowId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_WarehouseOutorderFlowId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String expType;
	private Short flowOrder;
	private String ownerNo;
	private String warehouseNo;
	// Constructors

	/** default constructor */
	public Wms_WarehouseOutorderFlowId() {
	}

	/** full constructor */
	public Wms_WarehouseOutorderFlowId(String enterpriseNo, String expType,
			Short flowOrder, String ownerNo,String warehouseNo) {
		this.enterpriseNo = enterpriseNo;
		this.expType = expType;
		this.flowOrder = flowOrder;
		this.ownerNo = ownerNo;
		this.warehouseNo=warehouseNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
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

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result + ((expType == null) ? 0 : expType.hashCode());
		result = prime * result
				+ ((flowOrder == null) ? 0 : flowOrder.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((warehouseNo == null) ? 0 : warehouseNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wms_WarehouseOutorderFlowId other = (Wms_WarehouseOutorderFlowId) obj;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (expType == null) {
			if (other.expType != null)
				return false;
		} else if (!expType.equals(other.expType))
			return false;
		if (flowOrder == null) {
			if (other.flowOrder != null)
				return false;
		} else if (!flowOrder.equals(other.flowOrder))
			return false;
		if (ownerNo == null) {
			if (other.ownerNo != null)
				return false;
		} else if (!ownerNo.equals(other.ownerNo))
			return false;
		if (warehouseNo == null) {
			if (other.warehouseNo != null)
				return false;
		} else if (!warehouseNo.equals(other.warehouseNo))
			return false;
		return true;
	}

	

}