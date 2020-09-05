package com.sealinkin.sodata.po;

// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * SodataWasteTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class SodataWasteTmpId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String warehouseNo;
     private String ownerNo;
     private String poNo;
     private String orgNo;
     private String ownerArticleNo;
     private Double packingQty;
     private Double wasteQty;
     private String status;
     private String remark;
     private Double rowId;


    // Constructors

    /** default constructor */
    public SodataWasteTmpId() {
    }

	/** minimal constructor */
    public SodataWasteTmpId(String enterpriseNo, String warehouseNo, String ownerNo, String poNo, String orgNo, String ownerArticleNo, Double packingQty, Double wasteQty, String status, Double rowId) {
        this.enterpriseNo = enterpriseNo;
        this.warehouseNo = warehouseNo;
        this.ownerNo = ownerNo;
        this.poNo = poNo;
        this.orgNo = orgNo;
        this.ownerArticleNo = ownerArticleNo;
        this.packingQty = packingQty;
        this.wasteQty = wasteQty;
        this.status = status;
        this.rowId = rowId;
    }
    
    /** full constructor */
    public SodataWasteTmpId(String enterpriseNo, String warehouseNo, String ownerNo, String poNo, String orgNo, String ownerArticleNo, Double packingQty, Double wasteQty, String status, String remark, Double rowId) {
        this.enterpriseNo = enterpriseNo;
        this.warehouseNo = warehouseNo;
        this.ownerNo = ownerNo;
        this.poNo = poNo;
        this.orgNo = orgNo;
        this.ownerArticleNo = ownerArticleNo;
        this.packingQty = packingQty;
        this.wasteQty = wasteQty;
        this.status = status;
        this.remark = remark;
        this.rowId = rowId;
    }

   
    // Property accessors

    @Column(name="ENTERPRISE_NO", nullable=false, length=15)

    public String getEnterpriseNo() {
        return this.enterpriseNo;
    }
    
    public void setEnterpriseNo(String enterpriseNo) {
        this.enterpriseNo = enterpriseNo;
    }

    @Column(name="WAREHOUSE_NO", nullable=false, length=5)

    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    @Column(name="OWNER_NO", nullable=false, length=3)

    public String getOwnerNo() {
        return this.ownerNo;
    }
    
    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }

    @Column(name="PO_NO", nullable=false, length=20)

    public String getPoNo() {
        return this.poNo;
    }
    
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    @Column(name="ORG_NO", nullable=false, length=20)

    public String getOrgNo() {
        return this.orgNo;
    }
    
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    @Column(name="OWNER_ARTICLE_NO", nullable=false, length=15)
    public String getOwnerArticleNo() {
		return ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

   

    @Column(name="PACKING_QTY", nullable=false, precision=18, scale=5)

    public Double getPackingQty() {
        return this.packingQty;
    }
    

	public void setPackingQty(Double packingQty) {
        this.packingQty = packingQty;
    }

    @Column(name="WASTE_QTY", nullable=false, precision=14, scale=5)

    public Double getWasteQty() {
        return this.wasteQty;
    }
    
    public void setWasteQty(Double wasteQty) {
        this.wasteQty = wasteQty;
    }

    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="REMARK")

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name="ROW_ID", nullable=false, precision=22, scale=0)
    
    public Double getRowId() {
 		return rowId;
 	}

 	public void setRowId(Double rowId) {
 		this.rowId = rowId;
 	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result + ((orgNo == null) ? 0 : orgNo.hashCode());
		result = prime * result
				+ ((ownerArticleNo == null) ? 0 : ownerArticleNo.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((packingQty == null) ? 0 : packingQty.hashCode());
		result = prime * result + ((poNo == null) ? 0 : poNo.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((warehouseNo == null) ? 0 : warehouseNo.hashCode());
		result = prime * result
				+ ((wasteQty == null) ? 0 : wasteQty.hashCode());
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
		SodataWasteTmpId other = (SodataWasteTmpId) obj;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (orgNo == null) {
			if (other.orgNo != null)
				return false;
		} else if (!orgNo.equals(other.orgNo))
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
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
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
		if (wasteQty == null) {
			if (other.wasteQty != null)
				return false;
		} else if (!wasteQty.equals(other.wasteQty))
			return false;
		return true;
	}



  



}