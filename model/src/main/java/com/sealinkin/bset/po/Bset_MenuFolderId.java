package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bset_MenuFolderId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_MenuFolderId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal menugroupId;
	private String folderId;

	// Constructors

	/** default constructor */
	public Bset_MenuFolderId() {
	}

	/** full constructor */
	public Bset_MenuFolderId(BigDecimal menugroupId, String folderId) {
		this.menugroupId = menugroupId;
		this.folderId = folderId;
	}

	// Property accessors

	@Column(name = "MENUGROUP_ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getMenugroupId() {
		return this.menugroupId;
	}

	public void setMenugroupId(BigDecimal menugroupId) {
		this.menugroupId = menugroupId;
	}

	@Column(name = "FOLDER_ID", nullable = false, length = 24)
	public String getFolderId() {
		return this.folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_MenuFolderId))
			return false;
		Bset_MenuFolderId castOther = (Bset_MenuFolderId) other;

		return ((this.getMenugroupId() == castOther.getMenugroupId()) || (this
				.getMenugroupId() != null && castOther.getMenugroupId() != null && this
				.getMenugroupId().equals(castOther.getMenugroupId())))
				&& ((this.getFolderId() == castOther.getFolderId()) || (this
						.getFolderId() != null
						&& castOther.getFolderId() != null && this
						.getFolderId().equals(castOther.getFolderId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMenugroupId() == null ? 0 : this.getMenugroupId()
						.hashCode());
		result = 37 * result
				+ (getFolderId() == null ? 0 : this.getFolderId().hashCode());
		return result;
	}

}