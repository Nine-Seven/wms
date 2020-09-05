package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bset_RoleModuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_RoleModuleId implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal roleId;
	private String moduleId;
	private String butId;

	// Constructors

	/** default constructor */
	public Bset_RoleModuleId() {
	}

	/** full constructor */
	public Bset_RoleModuleId(BigDecimal roleId, String moduleId, String butId) {
		this.roleId = roleId;
		this.moduleId = moduleId;
		this.butId = butId;
	}

	// Property accessors

	@Column(name = "ROLE_ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MODULE_ID", nullable = false, length = 24)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "BUT_ID", nullable = false, length = 64)
	public String getButId() {
		return this.butId;
	}

	public void setButId(String butId) {
		this.butId = butId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_RoleModuleId))
			return false;
		Bset_RoleModuleId castOther = (Bset_RoleModuleId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this
						.getModuleId() != null
						&& castOther.getModuleId() != null && this
						.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getButId() == castOther.getButId()) || (this
						.getButId() != null && castOther.getButId() != null && this
						.getButId().equals(castOther.getButId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result
				+ (getButId() == null ? 0 : this.getButId().hashCode());
		return result;
	}

}