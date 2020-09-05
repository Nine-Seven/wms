package com.sealinkin.idata.po;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IdataImportTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Idata_ImportTmpId implements java.io.Serializable {

	// Fields

	private String warehouseNo;
	private String ownerNo;
	private String poType;
	private String poNo;
	private String supplierNo;
	private Date orderDate;
	private Date requestDate;
	private Short endDate;
	private String ownerArticleNo;
	private Double packingQty;
	private Double poQty;
	private String importRemark;
	private String status;
	private String enterpriseNo;
	private Double rowId;
	private Double unitCost;
	private String takeType;
	private String rsvVarod1;
	private String rsvVarod2;
	private String rsvVarod3;
	private String rsvVarod4;
	private String rsvVarod5;
	private String rsvVarod6;
	private String rsvVarod7;
	private String rsvVarod8;
	private Double rsvNum1;
	private Double rsvNum2;
	private Double rsvNum3;
	private Date rsvDate1;
	private Date rsvDate2;
	private Date rsvDate3;
	private String custNo;
	private String odataexpNo;
	private Double allotQty;
	private String allotTakeType;
	
	// Constructors

	/** default constructor */
	public Idata_ImportTmpId() {
	}

	/** minimal constructor */
	public Idata_ImportTmpId(String warehouseNo, String ownerNo, String poType,
			String poNo, String supplierNo, Date orderDate,
			Date requestDate, Short endDate, String ownerArticleNo,
			Double packingQty, Double poQty, String takeType) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poType = poType;
		this.poNo = poNo;
		this.supplierNo = supplierNo;
		this.orderDate = orderDate;
		this.requestDate = requestDate;
		this.endDate = endDate;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.poQty = poQty;
		this.takeType = takeType;
	}

	/** full constructor */
	public Idata_ImportTmpId(String warehouseNo, String ownerNo, String poType,
			String poNo, String supplierNo, Date orderDate,
			Date requestDate, Short endDate, String ownerArticleNo,
			Double packingQty, Double poQty, String importRemark,
			String status, String enterpriseNo, Double rowId,
			Double unitCost, String takeType, String rsvVarod1,
			String rsvVarod2, String rsvVarod3, String rsvVarod4,
			String rsvVarod5, String rsvVarod6, String rsvVarod7,
			String rsvVarod8, Double rsvNum1, Double rsvNum2,
			Double rsvNum3, Date rsvDate1, Date rsvDate2,
			Date rsvDate3, String custNo, String odataexpNo,Double allotQty,String allotTakeType) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poType = poType;
		this.poNo = poNo;
		this.supplierNo = supplierNo;
		this.orderDate = orderDate;
		this.requestDate = requestDate;
		this.endDate = endDate;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.poQty = poQty;
		this.importRemark = importRemark;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
		this.unitCost = unitCost;
		this.takeType = takeType;
		this.rsvVarod1 = rsvVarod1;
		this.rsvVarod2 = rsvVarod2;
		this.rsvVarod3 = rsvVarod3;
		this.rsvVarod4 = rsvVarod4;
		this.rsvVarod5 = rsvVarod5;
		this.rsvVarod6 = rsvVarod6;
		this.rsvVarod7 = rsvVarod7;
		this.rsvVarod8 = rsvVarod8;
		this.rsvNum1 = rsvNum1;
		this.rsvNum2 = rsvNum2;
		this.rsvNum3 = rsvNum3;
		this.rsvDate1 = rsvDate1;
		this.rsvDate2 = rsvDate2;
		this.rsvDate3 = rsvDate3;
		this.custNo = custNo;
		this.odataexpNo = odataexpNo;
		this.allotQty=allotQty;
		this.allotTakeType=allotTakeType;
	}

	// Property accessors

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

	@Column(name = "PO_TYPE", nullable = false, length = 5)
	public String getPoType() {
		return this.poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ORDER_DATE", nullable = false, length = 7)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false, length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "END_DATE", nullable = false, precision = 3, scale = 0)
	public Short getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Short endDate) {
		this.endDate = endDate;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "PO_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPoQty() {
		return this.poQty;
	}

	public void setPoQty(Double poQty) {
		this.poQty = poQty;
	}
	@Column(name = "ALLOT_QTY", precision = 18, scale = 5)
	public Double getAllotQty() {
		return allotQty;
	}

	public void setAllotQty(Double allotQty) {
		this.allotQty = allotQty;
	}

	@Column(name = "IMPORT_REMARK")
	public String getImportRemark() {
		return this.importRemark;
	}

	public void setImportRemark(String importRemark) {
		this.importRemark = importRemark;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "ROW_ID", precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "UNIT_COST", precision = 16, scale = 5)
	public Double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	@Column(name = "TAKE_TYPE", nullable = false, length = 1)
	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}
	
	@Column(name = "ALLOT_TAKE_TYPE", length = 1)
	public String getAllotTakeType() {
		return allotTakeType;
	}

	public void setAllotTakeType(String allotTakeType) {
		this.allotTakeType = allotTakeType;
	}

	@Column(name = "RSV_VAROD1", length = 50)
	public String getRsvVarod1() {
		return this.rsvVarod1;
	}

	public void setRsvVarod1(String rsvVarod1) {
		this.rsvVarod1 = rsvVarod1;
	}

	@Column(name = "RSV_VAROD2", length = 50)
	public String getRsvVarod2() {
		return this.rsvVarod2;
	}

	public void setRsvVarod2(String rsvVarod2) {
		this.rsvVarod2 = rsvVarod2;
	}

	@Column(name = "RSV_VAROD3", length = 50)
	public String getRsvVarod3() {
		return this.rsvVarod3;
	}

	public void setRsvVarod3(String rsvVarod3) {
		this.rsvVarod3 = rsvVarod3;
	}

	@Column(name = "RSV_VAROD4", length = 50)
	public String getRsvVarod4() {
		return this.rsvVarod4;
	}

	public void setRsvVarod4(String rsvVarod4) {
		this.rsvVarod4 = rsvVarod4;
	}

	@Column(name = "RSV_VAROD5", length = 50)
	public String getRsvVarod5() {
		return this.rsvVarod5;
	}

	public void setRsvVarod5(String rsvVarod5) {
		this.rsvVarod5 = rsvVarod5;
	}

	@Column(name = "RSV_VAROD6", length = 50)
	public String getRsvVarod6() {
		return this.rsvVarod6;
	}

	public void setRsvVarod6(String rsvVarod6) {
		this.rsvVarod6 = rsvVarod6;
	}

	@Column(name = "RSV_VAROD7", length = 50)
	public String getRsvVarod7() {
		return this.rsvVarod7;
	}

	public void setRsvVarod7(String rsvVarod7) {
		this.rsvVarod7 = rsvVarod7;
	}

	@Column(name = "RSV_VAROD8", length = 50)
	public String getRsvVarod8() {
		return this.rsvVarod8;
	}

	public void setRsvVarod8(String rsvVarod8) {
		this.rsvVarod8 = rsvVarod8;
	}

	@Column(name = "RSV_NUM1", precision = 22, scale = 0)
	public Double getRsvNum1() {
		return this.rsvNum1;
	}

	public void setRsvNum1(Double rsvNum1) {
		this.rsvNum1 = rsvNum1;
	}

	@Column(name = "RSV_NUM2", precision = 22, scale = 0)
	public Double getRsvNum2() {
		return this.rsvNum2;
	}

	public void setRsvNum2(Double rsvNum2) {
		this.rsvNum2 = rsvNum2;
	}

	@Column(name = "RSV_NUM3", precision = 22, scale = 0)
	public Double getRsvNum3() {
		return this.rsvNum3;
	}

	public void setRsvNum3(Double rsvNum3) {
		this.rsvNum3 = rsvNum3;
	}

	@Column(name = "RSV_DATE1", length = 7)
	public Date getRsvDate1() {
		return this.rsvDate1;
	}

	public void setRsvDate1(Date rsvDate1) {
		this.rsvDate1 = rsvDate1;
	}

	@Column(name = "RSV_DATE2", length = 7)
	public Date getRsvDate2() {
		return this.rsvDate2;
	}

	public void setRsvDate2(Date rsvDate2) {
		this.rsvDate2 = rsvDate2;
	}

	@Column(name = "RSV_DATE3", length = 7)
	public Date getRsvDate3() {
		return this.rsvDate3;
	}

	public void setRsvDate3(Date rsvDate3) {
		this.rsvDate3 = rsvDate3;
	}

	@Column(name = "CUST_NO", length = 10)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "ODATAEXP_NO", length = 20)
	public String getOdataexpNo() {
		return this.odataexpNo;
	}

	public void setOdataexpNo(String odataexpNo) {
		this.odataexpNo = odataexpNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allotQty == null) ? 0 : allotQty.hashCode());
		result = prime * result + ((custNo == null) ? 0 : custNo.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result
				+ ((importRemark == null) ? 0 : importRemark.hashCode());
		result = prime * result
				+ ((odataexpNo == null) ? 0 : odataexpNo.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result
				+ ((ownerArticleNo == null) ? 0 : ownerArticleNo.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((packingQty == null) ? 0 : packingQty.hashCode());
		result = prime * result + ((poNo == null) ? 0 : poNo.hashCode());
		result = prime * result + ((poQty == null) ? 0 : poQty.hashCode());
		result = prime * result + ((poType == null) ? 0 : poType.hashCode());
		result = prime * result
				+ ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
		result = prime * result
				+ ((rsvDate1 == null) ? 0 : rsvDate1.hashCode());
		result = prime * result
				+ ((rsvDate2 == null) ? 0 : rsvDate2.hashCode());
		result = prime * result
				+ ((rsvDate3 == null) ? 0 : rsvDate3.hashCode());
		result = prime * result + ((rsvNum1 == null) ? 0 : rsvNum1.hashCode());
		result = prime * result + ((rsvNum2 == null) ? 0 : rsvNum2.hashCode());
		result = prime * result + ((rsvNum3 == null) ? 0 : rsvNum3.hashCode());
		result = prime * result
				+ ((rsvVarod1 == null) ? 0 : rsvVarod1.hashCode());
		result = prime * result
				+ ((rsvVarod2 == null) ? 0 : rsvVarod2.hashCode());
		result = prime * result
				+ ((rsvVarod3 == null) ? 0 : rsvVarod3.hashCode());
		result = prime * result
				+ ((rsvVarod4 == null) ? 0 : rsvVarod4.hashCode());
		result = prime * result
				+ ((rsvVarod5 == null) ? 0 : rsvVarod5.hashCode());
		result = prime * result
				+ ((rsvVarod6 == null) ? 0 : rsvVarod6.hashCode());
		result = prime * result
				+ ((rsvVarod7 == null) ? 0 : rsvVarod7.hashCode());
		result = prime * result
				+ ((rsvVarod8 == null) ? 0 : rsvVarod8.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((supplierNo == null) ? 0 : supplierNo.hashCode());
		result = prime * result
				+ ((takeType == null) ? 0 : takeType.hashCode());
		result = prime * result
				+ ((unitCost == null) ? 0 : unitCost.hashCode());
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
		Idata_ImportTmpId other = (Idata_ImportTmpId) obj;
		if (allotQty == null) {
			if (other.allotQty != null)
				return false;
		} else if (!allotQty.equals(other.allotQty))
			return false;
		if (custNo == null) {
			if (other.custNo != null)
				return false;
		} else if (!custNo.equals(other.custNo))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (importRemark == null) {
			if (other.importRemark != null)
				return false;
		} else if (!importRemark.equals(other.importRemark))
			return false;
		if (odataexpNo == null) {
			if (other.odataexpNo != null)
				return false;
		} else if (!odataexpNo.equals(other.odataexpNo))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (ownerArticleNo == null) {
			if (other.ownerArticleNo != null)
				return false;
		} else if (!ownerArticleNo.equals(other.ownerArticleNo))
			return false;
		if (ownerNo == null) {
			if (other.ownerNo != null)
				return false;
		} else if (!ownerNo.equals(other.ownerNo))
			return false;
		if (packingQty == null) {
			if (other.packingQty != null)
				return false;
		} else if (!packingQty.equals(other.packingQty))
			return false;
		if (poNo == null) {
			if (other.poNo != null)
				return false;
		} else if (!poNo.equals(other.poNo))
			return false;
		if (poQty == null) {
			if (other.poQty != null)
				return false;
		} else if (!poQty.equals(other.poQty))
			return false;
		if (poType == null) {
			if (other.poType != null)
				return false;
		} else if (!poType.equals(other.poType))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (rowId == null) {
			if (other.rowId != null)
				return false;
		} else if (!rowId.equals(other.rowId))
			return false;
		if (rsvDate1 == null) {
			if (other.rsvDate1 != null)
				return false;
		} else if (!rsvDate1.equals(other.rsvDate1))
			return false;
		if (rsvDate2 == null) {
			if (other.rsvDate2 != null)
				return false;
		} else if (!rsvDate2.equals(other.rsvDate2))
			return false;
		if (rsvDate3 == null) {
			if (other.rsvDate3 != null)
				return false;
		} else if (!rsvDate3.equals(other.rsvDate3))
			return false;
		if (rsvNum1 == null) {
			if (other.rsvNum1 != null)
				return false;
		} else if (!rsvNum1.equals(other.rsvNum1))
			return false;
		if (rsvNum2 == null) {
			if (other.rsvNum2 != null)
				return false;
		} else if (!rsvNum2.equals(other.rsvNum2))
			return false;
		if (rsvNum3 == null) {
			if (other.rsvNum3 != null)
				return false;
		} else if (!rsvNum3.equals(other.rsvNum3))
			return false;
		if (rsvVarod1 == null) {
			if (other.rsvVarod1 != null)
				return false;
		} else if (!rsvVarod1.equals(other.rsvVarod1))
			return false;
		if (rsvVarod2 == null) {
			if (other.rsvVarod2 != null)
				return false;
		} else if (!rsvVarod2.equals(other.rsvVarod2))
			return false;
		if (rsvVarod3 == null) {
			if (other.rsvVarod3 != null)
				return false;
		} else if (!rsvVarod3.equals(other.rsvVarod3))
			return false;
		if (rsvVarod4 == null) {
			if (other.rsvVarod4 != null)
				return false;
		} else if (!rsvVarod4.equals(other.rsvVarod4))
			return false;
		if (rsvVarod5 == null) {
			if (other.rsvVarod5 != null)
				return false;
		} else if (!rsvVarod5.equals(other.rsvVarod5))
			return false;
		if (rsvVarod6 == null) {
			if (other.rsvVarod6 != null)
				return false;
		} else if (!rsvVarod6.equals(other.rsvVarod6))
			return false;
		if (rsvVarod7 == null) {
			if (other.rsvVarod7 != null)
				return false;
		} else if (!rsvVarod7.equals(other.rsvVarod7))
			return false;
		if (rsvVarod8 == null) {
			if (other.rsvVarod8 != null)
				return false;
		} else if (!rsvVarod8.equals(other.rsvVarod8))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (supplierNo == null) {
			if (other.supplierNo != null)
				return false;
		} else if (!supplierNo.equals(other.supplierNo))
			return false;
		if (takeType == null) {
			if (other.takeType != null)
				return false;
		} else if (!takeType.equals(other.takeType))
			return false;
		if (unitCost == null) {
			if (other.unitCost != null)
				return false;
		} else if (!unitCost.equals(other.unitCost))
			return false;
		if (warehouseNo == null) {
			if (other.warehouseNo != null)
				return false;
		} else if (!warehouseNo.equals(other.warehouseNo))
			return false;
		return true;
	}



}