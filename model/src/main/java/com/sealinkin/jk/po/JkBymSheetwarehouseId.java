package com.sealinkin.jk.po;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JkBymSheetwarehouseId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class JkBymSheetwarehouseId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sheetNo;
	private String warehouseNo;
	private String rgstName;
	private Date rgstDate;
	private String corpkey;
	private String sheetType;

	// Constructors

	/** default constructor */
	public JkBymSheetwarehouseId() {
	}

	/** minimal constructor */
	public JkBymSheetwarehouseId(String sheetNo, String warehouseNo,
			String rgstName, Date rgstDate) {
		this.sheetNo = sheetNo;
		this.warehouseNo = warehouseNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public JkBymSheetwarehouseId(String sheetNo, String warehouseNo,
			String rgstName, Date rgstDate, String corpkey, String sheetType) {
		this.sheetNo = sheetNo;
		this.warehouseNo = warehouseNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.corpkey = corpkey;
		this.sheetType = sheetType;
	}

	// Property accessors

	@Column(name = "SHEET_NO", nullable = false, length = 20)
	public String getSheetNo() {
		return this.sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "CORPKEY", length = 64)
	public String getCorpkey() {
		return this.corpkey;
	}

	public void setCorpkey(String corpkey) {
		this.corpkey = corpkey;
	}

	@Column(name = "SHEET_TYPE", length = 20)
	public String getSheetType() {
		return this.sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JkBymSheetwarehouseId))
			return false;
		JkBymSheetwarehouseId castOther = (JkBymSheetwarehouseId) other;

		return ((this.getSheetNo() == castOther.getSheetNo()) || (this
				.getSheetNo() != null && castOther.getSheetNo() != null && this
				.getSheetNo().equals(castOther.getSheetNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getCorpkey() == castOther.getCorpkey()) || (this
						.getCorpkey() != null && castOther.getCorpkey() != null && this
						.getCorpkey().equals(castOther.getCorpkey())))
				&& ((this.getSheetType() == castOther.getSheetType()) || (this
						.getSheetType() != null
						&& castOther.getSheetType() != null && this
						.getSheetType().equals(castOther.getSheetType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSheetNo() == null ? 0 : this.getSheetNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getCorpkey() == null ? 0 : this.getCorpkey().hashCode());
		result = 37 * result
				+ (getSheetType() == null ? 0 : this.getSheetType().hashCode());
		return result;
	}

}