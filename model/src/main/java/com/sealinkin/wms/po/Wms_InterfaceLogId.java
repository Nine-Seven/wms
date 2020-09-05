package com.sealinkin.wms.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WmsInterfaceLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_InterfaceLogId implements java.io.Serializable {

	// Fields

	private Date operateDate;
	private BigDecimal seqNo;
	private String platType;
	private String platNo;
	private String platDesc;
	private String enterpriseNo;
	private String warehourceNo;
	private String apiNo;
	private Date execTime;
	private String execStatus;
	private String execDesc;
	private String apiDesc;

	// Constructors

	/** default constructor */
	public Wms_InterfaceLogId() {
	}

	/** minimal constructor */
	public Wms_InterfaceLogId(Date operateDate, BigDecimal seqNo,
			String platType, String platNo, String platDesc,
			String enterpriseNo, String warehourceNo, String apiNo,
			Date execTime, String execStatus) {
		this.operateDate = operateDate;
		this.seqNo = seqNo;
		this.platType = platType;
		this.platNo = platNo;
		this.platDesc = platDesc;
		this.enterpriseNo = enterpriseNo;
		this.warehourceNo = warehourceNo;
		this.apiNo = apiNo;
		this.execTime = execTime;
		this.execStatus = execStatus;
	}

	/** full constructor */
	public Wms_InterfaceLogId(Date operateDate, BigDecimal seqNo,
			String platType, String platNo, String platDesc,
			String enterpriseNo, String warehourceNo, String apiNo,
			Date execTime, String execStatus, String execDesc, String apiDesc) {
		this.operateDate = operateDate;
		this.seqNo = seqNo;
		this.platType = platType;
		this.platNo = platNo;
		this.platDesc = platDesc;
		this.enterpriseNo = enterpriseNo;
		this.warehourceNo = warehourceNo;
		this.apiNo = apiNo;
		this.execTime = execTime;
		this.execStatus = execStatus;
		this.execDesc = execDesc;
		this.apiDesc = apiDesc;
	}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "SEQ_NO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	@Column(name = "PLAT_TYPE", nullable = false, length = 1)
	public String getPlatType() {
		return this.platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	@Column(name = "PLAT_NO", nullable = false, length = 15)
	public String getPlatNo() {
		return this.platNo;
	}

	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}

	@Column(name = "PLAT_DESC", nullable = false, length = 30)
	public String getPlatDesc() {
		return this.platDesc;
	}

	public void setPlatDesc(String platDesc) {
		this.platDesc = platDesc;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WAREHOURCE_NO", nullable = false, length = 5)
	public String getWarehourceNo() {
		return this.warehourceNo;
	}

	public void setWarehourceNo(String warehourceNo) {
		this.warehourceNo = warehourceNo;
	}

	@Column(name = "API_NO", nullable = false, length = 300)
	public String getApiNo() {
		return this.apiNo;
	}

	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXEC_TIME", nullable = false, length = 7)
	public Date getExecTime() {
		return this.execTime;
	}

	public void setExecTime(Date execTime) {
		this.execTime = execTime;
	}

	@Column(name = "EXEC_STATUS", nullable = false, length = 1)
	public String getExecStatus() {
		return this.execStatus;
	}

	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus;
	}

	@Column(name = "EXEC_DESC", length = 300)
	public String getExecDesc() {
		return this.execDesc;
	}

	public void setExecDesc(String execDesc) {
		this.execDesc = execDesc;
	}

	@Column(name = "API_DESC", length = 300)
	public String getApiDesc() {
		return this.apiDesc;
	}

	public void setApiDesc(String apiDesc) {
		this.apiDesc = apiDesc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_InterfaceLogId))
			return false;
		Wms_InterfaceLogId castOther = (Wms_InterfaceLogId) other;

		return ((this.getOperateDate() == castOther.getOperateDate()) || (this
				.getOperateDate() != null && castOther.getOperateDate() != null && this
				.getOperateDate().equals(castOther.getOperateDate())))
				&& ((this.getSeqNo() == castOther.getSeqNo()) || (this
						.getSeqNo() != null && castOther.getSeqNo() != null && this
						.getSeqNo().equals(castOther.getSeqNo())))
				&& ((this.getPlatType() == castOther.getPlatType()) || (this
						.getPlatType() != null
						&& castOther.getPlatType() != null && this
						.getPlatType().equals(castOther.getPlatType())))
				&& ((this.getPlatNo() == castOther.getPlatNo()) || (this
						.getPlatNo() != null && castOther.getPlatNo() != null && this
						.getPlatNo().equals(castOther.getPlatNo())))
				&& ((this.getPlatDesc() == castOther.getPlatDesc()) || (this
						.getPlatDesc() != null
						&& castOther.getPlatDesc() != null && this
						.getPlatDesc().equals(castOther.getPlatDesc())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehourceNo() == castOther.getWarehourceNo()) || (this
						.getWarehourceNo() != null
						&& castOther.getWarehourceNo() != null && this
						.getWarehourceNo().equals(castOther.getWarehourceNo())))
				&& ((this.getApiNo() == castOther.getApiNo()) || (this
						.getApiNo() != null && castOther.getApiNo() != null && this
						.getApiNo().equals(castOther.getApiNo())))
				&& ((this.getExecTime() == castOther.getExecTime()) || (this
						.getExecTime() != null
						&& castOther.getExecTime() != null && this
						.getExecTime().equals(castOther.getExecTime())))
				&& ((this.getExecStatus() == castOther.getExecStatus()) || (this
						.getExecStatus() != null
						&& castOther.getExecStatus() != null && this
						.getExecStatus().equals(castOther.getExecStatus())))
				&& ((this.getExecDesc() == castOther.getExecDesc()) || (this
						.getExecDesc() != null
						&& castOther.getExecDesc() != null && this
						.getExecDesc().equals(castOther.getExecDesc())))
				&& ((this.getApiDesc() == castOther.getApiDesc()) || (this
						.getApiDesc() != null && castOther.getApiDesc() != null && this
						.getApiDesc().equals(castOther.getApiDesc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOperateDate() == null ? 0 : this.getOperateDate()
						.hashCode());
		result = 37 * result
				+ (getSeqNo() == null ? 0 : this.getSeqNo().hashCode());
		result = 37 * result
				+ (getPlatType() == null ? 0 : this.getPlatType().hashCode());
		result = 37 * result
				+ (getPlatNo() == null ? 0 : this.getPlatNo().hashCode());
		result = 37 * result
				+ (getPlatDesc() == null ? 0 : this.getPlatDesc().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehourceNo() == null ? 0 : this.getWarehourceNo()
						.hashCode());
		result = 37 * result
				+ (getApiNo() == null ? 0 : this.getApiNo().hashCode());
		result = 37 * result
				+ (getExecTime() == null ? 0 : this.getExecTime().hashCode());
		result = 37
				* result
				+ (getExecStatus() == null ? 0 : this.getExecStatus()
						.hashCode());
		result = 37 * result
				+ (getExecDesc() == null ? 0 : this.getExecDesc().hashCode());
		result = 37 * result
				+ (getApiDesc() == null ? 0 : this.getApiDesc().hashCode());
		return result;
	}

}