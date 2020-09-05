package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Idata_ImportAllotId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Idata_ImportAllotId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String warehouseNo;
     private String importNo;
     private String ownerNo;
     private String articleNo;
     private Double packingQty;
     private String subCustNo;
     private String custNo;
     private String poNo;


    // Constructors

    /** default constructor */
    public Idata_ImportAllotId() {
    }

    
    /** full constructor */
    public Idata_ImportAllotId(String enterpriseNo, String warehouseNo, String importNo, String ownerNo, String articleNo, Double packingQty, String subCustNo, String custNo, String poNo) {
        this.enterpriseNo = enterpriseNo;
        this.warehouseNo = warehouseNo;
        this.importNo = importNo;
        this.ownerNo = ownerNo;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.subCustNo = subCustNo;
        this.custNo = custNo;
        this.poNo = poNo;
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

    @Column(name="IMPORT_NO", nullable=false, length=20)

    public String getImportNo() {
        return this.importNo;
    }
    
    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    @Column(name="OWNER_NO", nullable=false, length=3)

    public String getOwnerNo() {
        return this.ownerNo;
    }
    
    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }

    @Column(name="ARTICLE_NO", nullable=false, length=15)

    public String getArticleNo() {
        return this.articleNo;
    }
    
    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }

    @Column(name="PACKING_QTY", nullable=false, precision=18, scale=5)

    public Double getPackingQty() {
        return this.packingQty;
    }
    
    public void setPackingQty(Double packingQty) {
        this.packingQty = packingQty;
    }

    @Column(name="SUB_CUST_NO", nullable=false, length=10)

    public String getSubCustNo() {
        return this.subCustNo;
    }
    
    public void setSubCustNo(String subCustNo) {
        this.subCustNo = subCustNo;
    }

    @Column(name="CUST_NO", nullable=false, length=10)

    public String getCustNo() {
        return this.custNo;
    }
    
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Column(name="PO_NO", nullable=false, length=50)

    public String getPoNo() {
        return this.poNo;
    }
    
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Idata_ImportAllotId) ) return false;
		 Idata_ImportAllotId castOther = ( Idata_ImportAllotId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getImportNo()==castOther.getImportNo()) || ( this.getImportNo()!=null && castOther.getImportNo()!=null && this.getImportNo().equals(castOther.getImportNo()) ) )
 && ( (this.getOwnerNo()==castOther.getOwnerNo()) || ( this.getOwnerNo()!=null && castOther.getOwnerNo()!=null && this.getOwnerNo().equals(castOther.getOwnerNo()) ) )
 && ( (this.getArticleNo()==castOther.getArticleNo()) || ( this.getArticleNo()!=null && castOther.getArticleNo()!=null && this.getArticleNo().equals(castOther.getArticleNo()) ) )
 && ( (this.getPackingQty()==castOther.getPackingQty()) || ( this.getPackingQty()!=null && castOther.getPackingQty()!=null && this.getPackingQty().equals(castOther.getPackingQty()) ) )
 && ( (this.getSubCustNo()==castOther.getSubCustNo()) || ( this.getSubCustNo()!=null && castOther.getSubCustNo()!=null && this.getSubCustNo().equals(castOther.getSubCustNo()) ) )
 && ( (this.getCustNo()==castOther.getCustNo()) || ( this.getCustNo()!=null && castOther.getCustNo()!=null && this.getCustNo().equals(castOther.getCustNo()) ) )
 && ( (this.getPoNo()==castOther.getPoNo()) || ( this.getPoNo()!=null && castOther.getPoNo()!=null && this.getPoNo().equals(castOther.getPoNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getImportNo() == null ? 0 : this.getImportNo().hashCode() );
         result = 37 * result + ( getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode() );
         result = 37 * result + ( getArticleNo() == null ? 0 : this.getArticleNo().hashCode() );
         result = 37 * result + ( getPackingQty() == null ? 0 : this.getPackingQty().hashCode() );
         result = 37 * result + ( getSubCustNo() == null ? 0 : this.getSubCustNo().hashCode() );
         result = 37 * result + ( getCustNo() == null ? 0 : this.getCustNo().hashCode() );
         result = 37 * result + ( getPoNo() == null ? 0 : this.getPoNo().hashCode() );
         return result;
   }   





}