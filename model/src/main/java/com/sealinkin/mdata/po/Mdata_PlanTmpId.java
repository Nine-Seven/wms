package com.sealinkin.mdata.po;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MdataPlanTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Mdata_PlanTmpId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -305354329658330280L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String ownerArticleNo;
	private Double originQty;
	private Double packingQty;
	private String SCellNo;
	private String DCellNo;
	private String planRemark;
	private String status;
	private Double rowId;
	private Date produceDate;
	private Date expireDate;
	private String orgNo;
	private String quality;
	private String lotNo;
	private String rsvBatch1;
	private String rsvBatch2;
	private String rsvBatch3;
	private String rsvBatch4;
	private String rsvBatch5;
	private String rsvBatch6;
	private String rsvBatch7;
	private String rsvBatch8;

	// Constructors

	/** default constructor */
	public Mdata_PlanTmpId() {
	}

	/** minimal constructor */
	public Mdata_PlanTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String ownerArticleNo, Double originQty,
			Double packingQty, String DCellNo, String status,
			Double rowId, String orgNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.ownerArticleNo = ownerArticleNo;
		this.originQty = originQty;
		this.packingQty = packingQty;
		this.DCellNo = DCellNo;
		this.status = status;
		this.rowId = rowId;
		this.orgNo = orgNo;
	}

	/** full constructor */
	public Mdata_PlanTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String ownerArticleNo, Double originQty,
			Double packingQty, String SCellNo, String DCellNo,
			String planRemark, String status, Double rowId,
			Date produceDate, Date expireDate, String orgNo,
			String quality, String lotNo, String rsvBatch1, String rsvBatch2,
			String rsvBatch3, String rsvBatch4, String rsvBatch5,
			String rsvBatch6, String rsvBatch7, String rsvBatch8) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.ownerArticleNo = ownerArticleNo;
		this.originQty = originQty;
		this.packingQty = packingQty;
		this.SCellNo = SCellNo;
		this.DCellNo = DCellNo;
		this.planRemark = planRemark;
		this.status = status;
		this.rowId = rowId;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.orgNo = orgNo;
		this.quality = quality;
		this.lotNo = lotNo;
		this.rsvBatch1 = rsvBatch1;
		this.rsvBatch2 = rsvBatch2;
		this.rsvBatch3 = rsvBatch3;
		this.rsvBatch4 = rsvBatch4;
		this.rsvBatch5 = rsvBatch5;
		this.rsvBatch6 = rsvBatch6;
		this.rsvBatch7 = rsvBatch7;
		this.rsvBatch8 = rsvBatch8;
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

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "ORIGIN_QTY", nullable = false, precision = 18, scale = 5)
	public Double getOriginQty() {
		return this.originQty;
	}

	public void setOriginQty(Double originQty) {
		this.originQty = originQty;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "S_CELL_NO", length = 10)
	public String getSCellNo() {
		return this.SCellNo;
	}

	public void setSCellNo(String SCellNo) {
		this.SCellNo = SCellNo;
	}

	@Column(name = "D_CELL_NO", nullable = false, length = 10)
	public String getDCellNo() {
		return this.DCellNo;
	}

	public void setDCellNo(String DCellNo) {
		this.DCellNo = DCellNo;
	}

	@Column(name = "PLAN_REMARK")
	public String getPlanRemark() {
		return this.planRemark;
	}

	public void setPlanRemark(String planRemark) {
		this.planRemark = planRemark;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "PRODUCE_DATE",length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	@Column(name = "EXPIRE_DATE", length = 7)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "ORG_NO", nullable = false, length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	@Column(name = "QUALITY", length = 2)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "LOT_NO",length = 32)
	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	@Column(name = "RSV_BATCH1", length = 20)
	public String getRsvBatch1() {
		return this.rsvBatch1;
	}

	public void setRsvBatch1(String rsvBatch1) {
		this.rsvBatch1 = rsvBatch1;
	}

	@Column(name = "RSV_BATCH2", length = 20)
	public String getRsvBatch2() {
		return this.rsvBatch2;
	}

	public void setRsvBatch2(String rsvBatch2) {
		this.rsvBatch2 = rsvBatch2;
	}

	@Column(name = "RSV_BATCH3", length = 20)
	public String getRsvBatch3() {
		return this.rsvBatch3;
	}

	public void setRsvBatch3(String rsvBatch3) {
		this.rsvBatch3 = rsvBatch3;
	}

	@Column(name = "RSV_BATCH4", length = 20)
	public String getRsvBatch4() {
		return this.rsvBatch4;
	}

	public void setRsvBatch4(String rsvBatch4) {
		this.rsvBatch4 = rsvBatch4;
	}

	@Column(name = "RSV_BATCH5", length = 20)
	public String getRsvBatch5() {
		return this.rsvBatch5;
	}

	public void setRsvBatch5(String rsvBatch5) {
		this.rsvBatch5 = rsvBatch5;
	}

	@Column(name = "RSV_BATCH6", length = 20)
	public String getRsvBatch6() {
		return this.rsvBatch6;
	}

	public void setRsvBatch6(String rsvBatch6) {
		this.rsvBatch6 = rsvBatch6;
	}

	@Column(name = "RSV_BATCH7", length = 20)
	public String getRsvBatch7() {
		return this.rsvBatch7;
	}

	public void setRsvBatch7(String rsvBatch7) {
		this.rsvBatch7 = rsvBatch7;
	}

	@Column(name = "RSV_BATCH8", length = 20)
	public String getRsvBatch8() {
		return this.rsvBatch8;
	}

	public void setRsvBatch8(String rsvBatch8) {
		this.rsvBatch8 = rsvBatch8;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DCellNo == null) ? 0 : DCellNo.hashCode());
		result = prime * result + ((SCellNo == null) ? 0 : SCellNo.hashCode());
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result
				+ ((expireDate == null) ? 0 : expireDate.hashCode());
		result = prime * result + ((lotNo == null) ? 0 : lotNo.hashCode());
		result = prime * result + ((orgNo == null) ? 0 : orgNo.hashCode());
		result = prime * result
				+ ((originQty == null) ? 0 : originQty.hashCode());
		result = prime * result
				+ ((ownerArticleNo == null) ? 0 : ownerArticleNo.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((packingQty == null) ? 0 : packingQty.hashCode());
		result = prime * result
				+ ((planRemark == null) ? 0 : planRemark.hashCode());
		result = prime * result
				+ ((produceDate == null) ? 0 : produceDate.hashCode());
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
		result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
		result = prime * result
				+ ((rsvBatch1 == null) ? 0 : rsvBatch1.hashCode());
		result = prime * result
				+ ((rsvBatch2 == null) ? 0 : rsvBatch2.hashCode());
		result = prime * result
				+ ((rsvBatch3 == null) ? 0 : rsvBatch3.hashCode());
		result = prime * result
				+ ((rsvBatch4 == null) ? 0 : rsvBatch4.hashCode());
		result = prime * result
				+ ((rsvBatch5 == null) ? 0 : rsvBatch5.hashCode());
		result = prime * result
				+ ((rsvBatch6 == null) ? 0 : rsvBatch6.hashCode());
		result = prime * result
				+ ((rsvBatch7 == null) ? 0 : rsvBatch7.hashCode());
		result = prime * result
				+ ((rsvBatch8 == null) ? 0 : rsvBatch8.hashCode());
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
		Mdata_PlanTmpId other = (Mdata_PlanTmpId) obj;
		if (DCellNo == null) {
			if (other.DCellNo != null)
				return false;
		} else if (!DCellNo.equals(other.DCellNo))
			return false;
		if (SCellNo == null) {
			if (other.SCellNo != null)
				return false;
		} else if (!SCellNo.equals(other.SCellNo))
			return false;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (expireDate == null) {
			if (other.expireDate != null)
				return false;
		} else if (!expireDate.equals(other.expireDate))
			return false;
		if (lotNo == null) {
			if (other.lotNo != null)
				return false;
		} else if (!lotNo.equals(other.lotNo))
			return false;
		if (orgNo == null) {
			if (other.orgNo != null)
				return false;
		} else if (!orgNo.equals(other.orgNo))
			return false;
		if (originQty == null) {
			if (other.originQty != null)
				return false;
		} else if (!originQty.equals(other.originQty))
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
		if (planRemark == null) {
			if (other.planRemark != null)
				return false;
		} else if (!planRemark.equals(other.planRemark))
			return false;
		if (produceDate == null) {
			if (other.produceDate != null)
				return false;
		} else if (!produceDate.equals(other.produceDate))
			return false;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		if (rowId == null) {
			if (other.rowId != null)
				return false;
		} else if (!rowId.equals(other.rowId))
			return false;
		if (rsvBatch1 == null) {
			if (other.rsvBatch1 != null)
				return false;
		} else if (!rsvBatch1.equals(other.rsvBatch1))
			return false;
		if (rsvBatch2 == null) {
			if (other.rsvBatch2 != null)
				return false;
		} else if (!rsvBatch2.equals(other.rsvBatch2))
			return false;
		if (rsvBatch3 == null) {
			if (other.rsvBatch3 != null)
				return false;
		} else if (!rsvBatch3.equals(other.rsvBatch3))
			return false;
		if (rsvBatch4 == null) {
			if (other.rsvBatch4 != null)
				return false;
		} else if (!rsvBatch4.equals(other.rsvBatch4))
			return false;
		if (rsvBatch5 == null) {
			if (other.rsvBatch5 != null)
				return false;
		} else if (!rsvBatch5.equals(other.rsvBatch5))
			return false;
		if (rsvBatch6 == null) {
			if (other.rsvBatch6 != null)
				return false;
		} else if (!rsvBatch6.equals(other.rsvBatch6))
			return false;
		if (rsvBatch7 == null) {
			if (other.rsvBatch7 != null)
				return false;
		} else if (!rsvBatch7.equals(other.rsvBatch7))
			return false;
		if (rsvBatch8 == null) {
			if (other.rsvBatch8 != null)
				return false;
		} else if (!rsvBatch8.equals(other.rsvBatch8))
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