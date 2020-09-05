package com.sealinkin.wms.po;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PntsetModuleReportId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_PntsetmodulereportId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String reportId;
	private String paperType;
	private String taskType;
	private String pickType;
	private String operateType;
	private String reportType;
	private String orderType;
	private String deliverObjLevel;
	private String useType;
	private String ownerNo;
	private String custNo;
	private String shipperNo;

	// Constructors

	/** default constructor */
	public Wms_PntsetmodulereportId() {
	}

	/** full constructor */
	public Wms_PntsetmodulereportId(String enterpriseNo, String reportId,
			String paperType, String taskType, String pickType,
			String operateType, String reportType, String orderType,
			String deliverObjLevel, String useType, String ownerNo,
			String custNo, String shipperNo) {
		this.enterpriseNo = enterpriseNo;
		this.reportId = reportId;
		this.paperType = paperType;
		this.taskType = taskType;
		this.pickType = pickType;
		this.operateType = operateType;
		this.reportType = reportType;
		this.orderType = orderType;
		this.deliverObjLevel = deliverObjLevel;
		this.useType = useType;
		this.ownerNo = ownerNo;
		this.custNo = custNo;
		this.shipperNo = shipperNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "REPORT_ID", nullable = false, length = 20)
	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "PAPER_TYPE", nullable = false, length = 5)
	public String getPaperType() {
		return this.paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	@Column(name = "TASK_TYPE", nullable = false, length = 5)
	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Column(name = "PICK_TYPE", nullable = false, length = 5)
	public String getPickType() {
		return this.pickType;
	}

	public void setPickType(String pickType) {
		this.pickType = pickType;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 5)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "REPORT_TYPE", nullable = false, length = 5)
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "ORDER_TYPE", nullable = false, length = 5)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "DELIVER_OBJ_LEVEL", nullable = false, length = 5)
	public String getDeliverObjLevel() {
		return this.deliverObjLevel;
	}

	public void setDeliverObjLevel(String deliverObjLevel) {
		this.deliverObjLevel = deliverObjLevel;
	}

	@Column(name = "USE_TYPE", nullable = false, length = 5)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 5)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "SHIPPER_NO", nullable = false, length = 15)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_PntsetmodulereportId))
			return false;
		Wms_PntsetmodulereportId castOther = (Wms_PntsetmodulereportId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getReportId() == castOther.getReportId()) || (this
						.getReportId() != null
						&& castOther.getReportId() != null && this
						.getReportId().equals(castOther.getReportId())))
				&& ((this.getPaperType() == castOther.getPaperType()) || (this
						.getPaperType() != null
						&& castOther.getPaperType() != null && this
						.getPaperType().equals(castOther.getPaperType())))
				&& ((this.getTaskType() == castOther.getTaskType()) || (this
						.getTaskType() != null
						&& castOther.getTaskType() != null && this
						.getTaskType().equals(castOther.getTaskType())))
				&& ((this.getPickType() == castOther.getPickType()) || (this
						.getPickType() != null
						&& castOther.getPickType() != null && this
						.getPickType().equals(castOther.getPickType())))
				&& ((this.getOperateType() == castOther.getOperateType()) || (this
						.getOperateType() != null
						&& castOther.getOperateType() != null && this
						.getOperateType().equals(castOther.getOperateType())))
				&& ((this.getReportType() == castOther.getReportType()) || (this
						.getReportType() != null
						&& castOther.getReportType() != null && this
						.getReportType().equals(castOther.getReportType())))
				&& ((this.getOrderType() == castOther.getOrderType()) || (this
						.getOrderType() != null
						&& castOther.getOrderType() != null && this
						.getOrderType().equals(castOther.getOrderType())))
				&& ((this.getDeliverObjLevel() == castOther
						.getDeliverObjLevel()) || (this.getDeliverObjLevel() != null
						&& castOther.getDeliverObjLevel() != null && this
						.getDeliverObjLevel().equals(
								castOther.getDeliverObjLevel())))
				&& ((this.getUseType() == castOther.getUseType()) || (this
						.getUseType() != null && castOther.getUseType() != null && this
						.getUseType().equals(castOther.getUseType())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getCustNo() == castOther.getCustNo()) || (this
						.getCustNo() != null && castOther.getCustNo() != null && this
						.getCustNo().equals(castOther.getCustNo())))
				&& ((this.getShipperNo() == castOther.getShipperNo()) || (this
						.getShipperNo() != null
						&& castOther.getShipperNo() != null && this
						.getShipperNo().equals(castOther.getShipperNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getReportId() == null ? 0 : this.getReportId().hashCode());
		result = 37 * result
				+ (getPaperType() == null ? 0 : this.getPaperType().hashCode());
		result = 37 * result
				+ (getTaskType() == null ? 0 : this.getTaskType().hashCode());
		result = 37 * result
				+ (getPickType() == null ? 0 : this.getPickType().hashCode());
		result = 37
				* result
				+ (getOperateType() == null ? 0 : this.getOperateType()
						.hashCode());
		result = 37
				* result
				+ (getReportType() == null ? 0 : this.getReportType()
						.hashCode());
		result = 37 * result
				+ (getOrderType() == null ? 0 : this.getOrderType().hashCode());
		result = 37
				* result
				+ (getDeliverObjLevel() == null ? 0 : this.getDeliverObjLevel()
						.hashCode());
		result = 37 * result
				+ (getUseType() == null ? 0 : this.getUseType().hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getCustNo() == null ? 0 : this.getCustNo().hashCode());
		result = 37 * result
				+ (getShipperNo() == null ? 0 : this.getShipperNo().hashCode());
		return result;
	}

}