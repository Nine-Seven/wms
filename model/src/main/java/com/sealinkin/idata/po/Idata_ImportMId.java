package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Idata_ImportMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Idata_ImportMId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String importNo;
     private String warehouseNo;
     private String ownerNo;


    // Constructors

    /** default constructor */
    public Idata_ImportMId() {
    }

    
    /** full constructor */
    public Idata_ImportMId(String enterpriseNo, String importNo, String warehouseNo, String ownerNo) {
        this.enterpriseNo = enterpriseNo;
        this.importNo = importNo;
        this.warehouseNo = warehouseNo;
        this.ownerNo = ownerNo;
    }

   
    // Property accessors

    @Column(name="ENTERPRISE_NO", nullable=false, length=15)

    public String getEnterpriseNo() {
        return this.enterpriseNo;
    }
    
    public void setEnterpriseNo(String enterpriseNo) {
        this.enterpriseNo = enterpriseNo;
    }

    @Column(name="IMPORT_NO", nullable=false, length=20)

    public String getImportNo() {
        return this.importNo;
    }
    
    public void setImportNo(String importNo) {
        this.importNo = importNo;
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
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Idata_ImportMId) ) return false;
		 Idata_ImportMId castOther = ( Idata_ImportMId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getImportNo()==castOther.getImportNo()) || ( this.getImportNo()!=null && castOther.getImportNo()!=null && this.getImportNo().equals(castOther.getImportNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getOwnerNo()==castOther.getOwnerNo()) || ( this.getOwnerNo()!=null && castOther.getOwnerNo()!=null && this.getOwnerNo().equals(castOther.getOwnerNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getImportNo() == null ? 0 : this.getImportNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode() );
         return result;
   }   





}