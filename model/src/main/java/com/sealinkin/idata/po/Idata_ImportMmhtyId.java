package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Idata_ImportMmhtyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Idata_ImportMmhtyId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String SImportNo;
     private String warehouseNo;
     private String ownerNo;


    // Constructors

    /** default constructor */
    public Idata_ImportMmhtyId() {
    }

    
    /** full constructor */
    public Idata_ImportMmhtyId(String enterpriseNo, String SImportNo, String warehouseNo, String ownerNo) {
        this.enterpriseNo = enterpriseNo;
        this.SImportNo = SImportNo;
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

    @Column(name="S_IMPORT_NO", nullable=false, length=20)

    public String getSImportNo() {
        return this.SImportNo;
    }
    
    public void setSImportNo(String SImportNo) {
        this.SImportNo = SImportNo;
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
		 if ( !(other instanceof Idata_ImportMmhtyId) ) return false;
		 Idata_ImportMmhtyId castOther = ( Idata_ImportMmhtyId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getSImportNo()==castOther.getSImportNo()) || ( this.getSImportNo()!=null && castOther.getSImportNo()!=null && this.getSImportNo().equals(castOther.getSImportNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getOwnerNo()==castOther.getOwnerNo()) || ( this.getOwnerNo()!=null && castOther.getOwnerNo()!=null && this.getOwnerNo().equals(castOther.getOwnerNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getSImportNo() == null ? 0 : this.getSImportNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode() );
         return result;
   }   





}