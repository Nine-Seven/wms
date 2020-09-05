package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefsearchDSetId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_DefsearchDSetId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String pgmId;
	private String name;
	private String fieldId;
	private String rgstName;

	/** default constructor */
	public Wms_DefsearchDSetId() {
	}

	/** full constructor */
	public Wms_DefsearchDSetId(String pgmId, String name, String fieldId,
			String rgstName) {
		this.pgmId = pgmId;
		this.name = name;
		this.fieldId = fieldId;
		this.rgstName = rgstName;
	}

	// Property accessors

	@Column(name = "PGM_ID", nullable = false, length = 20)
	public String getPgmId() {
		return this.pgmId;
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	@Column(name = "NAME", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "FIELD_ID", nullable = false, length = 30)
	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_DefsearchDSetId))
			return false;
		Wms_DefsearchDSetId castOther = (Wms_DefsearchDSetId) other;

		return ((this.getPgmId() == castOther.getPgmId()) || (this.getPgmId() != null
				&& castOther.getPgmId() != null && this.getPgmId().equals(
				castOther.getPgmId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getFieldId() == castOther.getFieldId()) || (this
						.getFieldId() != null && castOther.getFieldId() != null && this
						.getFieldId().equals(castOther.getFieldId())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPgmId() == null ? 0 : this.getPgmId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getFieldId() == null ? 0 : this.getFieldId().hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		return result;
	}

}