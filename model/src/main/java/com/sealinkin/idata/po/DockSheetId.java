package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DockSheetId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DockSheetId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String rgstName;
	private String startTime;
	private String endTime;

	// Constructors

	/** default constructor */
	public DockSheetId() {
	}

	/** full constructor */
	public DockSheetId(String rgstName, String startTime, String endTime) {
		this.rgstName = rgstName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// Property accessors

	@Column(name = "RGST_NAME", nullable = false, length = 10)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "START_TIME", nullable = false, length = 5)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", nullable = false, length = 5)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DockSheetId))
			return false;
		DockSheetId castOther = (DockSheetId) other;

		return ((this.getRgstName() == castOther.getRgstName()) || (this
				.getRgstName() != null && castOther.getRgstName() != null && this
				.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getStartTime() == castOther.getStartTime()) || (this
						.getStartTime() != null
						&& castOther.getStartTime() != null && this
						.getStartTime().equals(castOther.getStartTime())))
				&& ((this.getEndTime() == castOther.getEndTime()) || (this
						.getEndTime() != null && castOther.getEndTime() != null && this
						.getEndTime().equals(castOther.getEndTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getStartTime() == null ? 0 : this.getStartTime().hashCode());
		result = 37 * result
				+ (getEndTime() == null ? 0 : this.getEndTime().hashCode());
		return result;
	}

}