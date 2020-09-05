package com.sealinkin.ridata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Ridata_CheckMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Ridata_CheckMId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String checkNo;
     private String warehouseNo;
     private String ownerNo;


    // Constructors

    /** default constructor */
    public Ridata_CheckMId() {
    }

    
    /** full constructor */
    public Ridata_CheckMId(String enterpriseNo, String checkNo, String warehouseNo, String ownerNo) {
        this.enterpriseNo = enterpriseNo;
        this.checkNo = checkNo;
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

    @Column(name="CHECK_NO", nullable=false, length=20)

    public String getCheckNo() {
        return this.checkNo;
    }
    
    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
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
		 if ( !(other instanceof Ridata_CheckMId) ) return false;
		 Ridata_CheckMId castOther = ( Ridata_CheckMId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getCheckNo()==castOther.getCheckNo()) || ( this.getCheckNo()!=null && castOther.getCheckNo()!=null && this.getCheckNo().equals(castOther.getCheckNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getOwnerNo()==castOther.getOwnerNo()) || ( this.getOwnerNo()!=null && castOther.getOwnerNo()!=null && this.getOwnerNo().equals(castOther.getOwnerNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getCheckNo() == null ? 0 : this.getCheckNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode() );
         return result;
   }   





}