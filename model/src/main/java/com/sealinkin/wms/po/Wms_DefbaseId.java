package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Wms_DefbaseId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_DefbaseId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String colname;
	private String groupNo;
	private String subGroupNo;
	private String useLevel;
	private String sdefine;
	private double ndefine;
	private String memo;

	// Constructors

	/** default constructor */
	public Wms_DefbaseId() {
	}

	/** minimal constructor */
	public Wms_DefbaseId(String colname, String groupNo, String subGroupNo,
			String useLevel) {
		this.colname = colname;
		this.groupNo = groupNo;
		this.subGroupNo = subGroupNo;
		this.useLevel = useLevel;
	}

	/** full constructor */
	public Wms_DefbaseId(String colname, String groupNo, String subGroupNo,
			String useLevel, String sdefine, double ndefine, String memo) {
		this.colname = colname;
		this.groupNo = groupNo;
		this.subGroupNo = subGroupNo;
		this.useLevel = useLevel;
		this.sdefine = sdefine;
		this.ndefine = ndefine;
		this.memo = memo;
	}

	// Property accessors

	@Column(name = "COLNAME", nullable = false, length = 50)
	public String getColname() {
		return this.colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	@Column(name = "GROUP_NO", nullable = false, length = 10)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "SUB_GROUP_NO", nullable = false, length = 10)
	public String getSubGroupNo() {
		return this.subGroupNo;
	}

	public void setSubGroupNo(String subGroupNo) {
		this.subGroupNo = subGroupNo;
	}

	@Column(name = "USE_LEVEL", nullable = false, length = 4)
	public String getUseLevel() {
		return this.useLevel;
	}

	public void setUseLevel(String useLevel) {
		this.useLevel = useLevel;
	}

	@Column(name = "SDEFINE", length = 500)
	public String getSdefine() {
		return this.sdefine;
	}

	public void setSdefine(String sdefine) {
		this.sdefine = sdefine;
	}

	@Column(name = "NDEFINE", precision = 12, scale = 5)
	public double getNdefine() {
		return this.ndefine;
	}

	public void setNdefine(double ndefine) {
		this.ndefine = ndefine;
	}

	@Column(name = "MEMO", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_DefbaseId))
			return false;
		Wms_DefbaseId castOther = (Wms_DefbaseId) other;

		return ((this.getColname() == castOther.getColname()) || (this
				.getColname() != null && castOther.getColname() != null && this
				.getColname().equals(castOther.getColname())))
				&& ((this.getGroupNo() == castOther.getGroupNo()) || (this
						.getGroupNo() != null && castOther.getGroupNo() != null && this
						.getGroupNo().equals(castOther.getGroupNo())))
				&& ((this.getSubGroupNo() == castOther.getSubGroupNo()) || (this
						.getSubGroupNo() != null
						&& castOther.getSubGroupNo() != null && this
						.getSubGroupNo().equals(castOther.getSubGroupNo())))
				&& ((this.getUseLevel() == castOther.getUseLevel()) || (this
						.getUseLevel() != null
						&& castOther.getUseLevel() != null && this
						.getUseLevel().equals(castOther.getUseLevel())))
				&& ((this.getSdefine() == castOther.getSdefine()) || (this
						.getSdefine() != null && castOther.getSdefine() != null && this
						.getSdefine().equals(castOther.getSdefine())))
				&& (this.getNdefine() == castOther.getNdefine())
				&& ((this.getMemo() == castOther.getMemo()) || (this.getMemo() != null
						&& castOther.getMemo() != null && this.getMemo()
						.equals(castOther.getMemo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getColname() == null ? 0 : this.getColname().hashCode());
		result = 37 * result
				+ (getGroupNo() == null ? 0 : this.getGroupNo().hashCode());
		result = 37
				* result
				+ (getSubGroupNo() == null ? 0 : this.getSubGroupNo()
						.hashCode());
		result = 37 * result
				+ (getUseLevel() == null ? 0 : this.getUseLevel().hashCode());
		result = 37 * result
				+ (getSdefine() == null ? 0 : this.getSdefine().hashCode());
		result = 37 * result + (int) this.getNdefine();
		result = 37 * result
				+ (getMemo() == null ? 0 : this.getMemo().hashCode());
		return result;
	}

}