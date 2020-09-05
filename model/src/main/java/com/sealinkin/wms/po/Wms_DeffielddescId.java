package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Wms_DeffielddescId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_DeffielddescId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String tableName;
	private String fieldId;

	// Constructors

	/** default constructor */
	public Wms_DeffielddescId() {
	}

	/** full constructor */
	public Wms_DeffielddescId(String tableName, String fieldId) {
		this.tableName = tableName;
		this.fieldId = fieldId;
	}

	// Property accessors

	@Column(name = "TABLE_NAME", nullable = false, length = 50)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "FIELD_ID", nullable = false, length = 50)
	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_DeffielddescId))
			return false;
		Wms_DeffielddescId castOther = (Wms_DeffielddescId) other;

		return ((this.getTableName() == castOther.getTableName()) || (this
				.getTableName() != null && castOther.getTableName() != null && this
				.getTableName().equals(castOther.getTableName())))
				&& ((this.getFieldId() == castOther.getFieldId()) || (this
						.getFieldId() != null && castOther.getFieldId() != null && this
						.getFieldId().equals(castOther.getFieldId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTableName() == null ? 0 : this.getTableName().hashCode());
		result = 37 * result
				+ (getFieldId() == null ? 0 : this.getFieldId().hashCode());
		return result;
	}

}