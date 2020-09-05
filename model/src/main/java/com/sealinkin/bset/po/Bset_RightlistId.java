package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bset_RightlistId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_RightlistId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String moduleId;
	private String moduleName;
	private String name;
	private String rightName;
	private String butId;
	private String butName;
	private BigDecimal status;
	private BigDecimal orderNo;
	private String parentId;
	private String terminalFlag;

	// Constructors

	/** default constructor */
	public Bset_RightlistId() {
	}

	/** minimal constructor */
	public Bset_RightlistId(String moduleId, String moduleName, String name,
			String rightName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.name = name;
		this.rightName = rightName;
	}

	/** full constructor */
	public Bset_RightlistId(String moduleId, String moduleName, String name,
			String rightName, String butId, String butName, BigDecimal status,
			BigDecimal orderNo, String parentId, String terminalFlag) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.name = name;
		this.rightName = rightName;
		this.butId = butId;
		this.butName = butName;
		this.status = status;
		this.orderNo = orderNo;
		this.parentId = parentId;
		this.terminalFlag = terminalFlag;
	}

	// Property accessors

	@Column(name = "MODULE_ID", nullable = false, length = 24)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "MODULE_NAME", nullable = false, length = 128)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "NAME", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RIGHT_NAME", nullable = false, length = 32)
	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	@Column(name = "BUT_ID", length = 64)
	public String getButId() {
		return this.butId;
	}

	public void setButId(String butId) {
		this.butId = butId;
	}

	@Column(name = "BUT_NAME", length = 32)
	public String getButName() {
		return this.butName;
	}

	public void setButName(String butName) {
		this.butName = butName;
	}

	@Column(name = "STATUS", precision = 22, scale = 0)
	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Column(name = "ORDER_NO", precision = 22, scale = 0)
	public BigDecimal getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "PARENT_ID", length = 32)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "TERMINAL_FLAG", length = 1)
	public String getTerminalFlag() {
		return this.terminalFlag;
	}

	public void setTerminalFlag(String terminalFlag) {
		this.terminalFlag = terminalFlag;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_RightlistId))
			return false;
		Bset_RightlistId castOther = (Bset_RightlistId) other;

		return ((this.getModuleId() == castOther.getModuleId()) || (this
				.getModuleId() != null && castOther.getModuleId() != null && this
				.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getModuleName() == castOther.getModuleName()) || (this
						.getModuleName() != null
						&& castOther.getModuleName() != null && this
						.getModuleName().equals(castOther.getModuleName())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getRightName() == castOther.getRightName()) || (this
						.getRightName() != null
						&& castOther.getRightName() != null && this
						.getRightName().equals(castOther.getRightName())))
				&& ((this.getButId() == castOther.getButId()) || (this
						.getButId() != null && castOther.getButId() != null && this
						.getButId().equals(castOther.getButId())))
				&& ((this.getButName() == castOther.getButName()) || (this
						.getButName() != null && castOther.getButName() != null && this
						.getButName().equals(castOther.getButName())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this
						.getOrderNo() != null && castOther.getOrderNo() != null && this
						.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getParentId() == castOther.getParentId()) || (this
						.getParentId() != null
						&& castOther.getParentId() != null && this
						.getParentId().equals(castOther.getParentId())))
				&& ((this.getTerminalFlag() == castOther.getTerminalFlag()) || (this
						.getTerminalFlag() != null
						&& castOther.getTerminalFlag() != null && this
						.getTerminalFlag().equals(castOther.getTerminalFlag())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37
				* result
				+ (getModuleName() == null ? 0 : this.getModuleName()
						.hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getRightName() == null ? 0 : this.getRightName().hashCode());
		result = 37 * result
				+ (getButId() == null ? 0 : this.getButId().hashCode());
		result = 37 * result
				+ (getButName() == null ? 0 : this.getButName().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result
				+ (getParentId() == null ? 0 : this.getParentId().hashCode());
		result = 37
				* result
				+ (getTerminalFlag() == null ? 0 : this.getTerminalFlag()
						.hashCode());
		return result;
	}

}