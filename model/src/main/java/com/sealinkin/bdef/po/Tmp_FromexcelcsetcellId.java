package com.sealinkin.bdef.po;

// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tmp_FromexcelcsetcellId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_FromexcelcsetcellId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String barcode;
	private String articleName;
	private double packingQty;
	private String cellNo;
	private double maxQty;
	private double alertQty;
	private double keepCells;
	private double lineId;
	private String status;
	private Date operateDate;
	private Double rowId;

	// Constructors

	/** default constructor */
	public Tmp_FromexcelcsetcellId() {
	}

	/** full constructor */
	public Tmp_FromexcelcsetcellId(String enterpriseNo, String warehouseNo,
			String ownerNo, String barcode, String articleName,
			double packingQty, String cellNo, double maxQty, double alertQty,
			Integer keepCells, Integer lineId, String status, Date operateDate,Double rowId) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.barcode = barcode;
		this.articleName = articleName;
		this.packingQty = packingQty;
		this.cellNo = cellNo;
		this.maxQty = maxQty;
		this.alertQty = alertQty;
		this.keepCells = keepCells;
		this.lineId = lineId;
		this.status = status;
		this.operateDate = operateDate;
		this.rowId = rowId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "BARCODE", nullable = false, length = 13)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "ARTICLE_NAME", nullable = false, length = 100)
	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "MAX_QTY", nullable = false, precision = 18, scale = 5)
	public double getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(double maxQty) {
		this.maxQty = maxQty;
	}

	@Column(name = "ALERT_QTY", nullable = false, precision = 18, scale = 5)
	public double getAlertQty() {
		return this.alertQty;
	}

	public void setAlertQty(double alertQty) {
		this.alertQty = alertQty;
	}

	@Column(name = "KEEP_CELLS", nullable = false, precision = 3, scale = 0)
	public double getKeepCells() {
		return this.keepCells;
	}

	public void setKeepCells(double keepCells) {
		this.keepCells = keepCells;
	}

	@Column(name = "LINE_ID",precision = 2, scale = 0)
	public double getLineId() {
		return this.lineId;
	}

	public void setLineId(double lineId) {
		this.lineId = lineId;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alertQty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((articleName == null) ? 0 : articleName.hashCode());
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((cellNo == null) ? 0 : cellNo.hashCode());
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		temp = Double.doubleToLongBits(keepCells);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lineId);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxQty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((operateDate == null) ? 0 : operateDate.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		temp = Double.doubleToLongBits(packingQty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((warehouseNo == null) ? 0 : warehouseNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tmp_FromexcelcsetcellId other = (Tmp_FromexcelcsetcellId) obj;
		if (Double.doubleToLongBits(alertQty) != Double
				.doubleToLongBits(other.alertQty))
			return false;
		if (articleName == null) {
			if (other.articleName != null)
				return false;
		} else if (!articleName.equals(other.articleName))
			return false;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (cellNo == null) {
			if (other.cellNo != null)
				return false;
		} else if (!cellNo.equals(other.cellNo))
			return false;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (Double.doubleToLongBits(keepCells) != Double
				.doubleToLongBits(other.keepCells))
			return false;
		if (Double.doubleToLongBits(lineId) != Double
				.doubleToLongBits(other.lineId))
			return false;
		if (Double.doubleToLongBits(maxQty) != Double
				.doubleToLongBits(other.maxQty))
			return false;
		if (operateDate == null) {
			if (other.operateDate != null)
				return false;
		} else if (!operateDate.equals(other.operateDate))
			return false;
		if (ownerNo == null) {
			if (other.ownerNo != null)
				return false;
		} else if (!ownerNo.equals(other.ownerNo))
			return false;
		if (Double.doubleToLongBits(packingQty) != Double
				.doubleToLongBits(other.packingQty))
			return false;
		if (rowId == null) {
			if (other.rowId != null)
				return false;
		} else if (!rowId.equals(other.rowId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (warehouseNo == null) {
			if (other.warehouseNo != null)
				return false;
		} else if (!warehouseNo.equals(other.warehouseNo))
			return false;
		return true;
	}

	
}