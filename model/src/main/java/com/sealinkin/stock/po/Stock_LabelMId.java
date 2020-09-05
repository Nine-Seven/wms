package com.sealinkin.stock.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StockLabelMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Stock_LabelMId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String labelNo;
	private String containerNo;
	private String containerType;

	// Constructors

	/** default constructor */
	public Stock_LabelMId() {
	}

	/** full constructor */
	public Stock_LabelMId(String warehouseNo, String labelNo,
			String containerNo, String containerType) {
		this.warehouseNo = warehouseNo;
		this.labelNo = labelNo;
		this.containerNo = containerNo;
		this.containerType = containerType;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "CONTAINER_NO", nullable = false, length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	@Column(name = "CONTAINER_TYPE", nullable = false, length = 1)
	public String getContainerType() {
		return this.containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Stock_LabelMId))
			return false;
		Stock_LabelMId castOther = (Stock_LabelMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getLabelNo() == castOther.getLabelNo()) || (this
						.getLabelNo() != null && castOther.getLabelNo() != null && this
						.getLabelNo().equals(castOther.getLabelNo())))
				&& ((this.getContainerNo() == castOther.getContainerNo()) || (this
						.getContainerNo() != null
						&& castOther.getContainerNo() != null && this
						.getContainerNo().equals(castOther.getContainerNo())))
				&& ((this.getContainerType() == castOther.getContainerType()) || (this
						.getContainerType() != null
						&& castOther.getContainerType() != null && this
						.getContainerType()
						.equals(castOther.getContainerType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getLabelNo() == null ? 0 : this.getLabelNo().hashCode());
		result = 37
				* result
				+ (getContainerNo() == null ? 0 : this.getContainerNo()
						.hashCode());
		result = 37
				* result
				+ (getContainerType() == null ? 0 : this.getContainerType()
						.hashCode());
		return result;
	}

}