package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Odata_ExpMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Odata_ExpMId  implements java.io.Serializable {


    // Fields    

     private String enterpriseNo;
     private String warehouseNo;
     private String expNo;
     private String expType;


    // Constructors

    /** default constructor */
    public Odata_ExpMId() {
    }

    
    /** full constructor */
    public Odata_ExpMId(String enterpriseNo, String warehouseNo, String expNo, String expType) {
        this.enterpriseNo = enterpriseNo;
        this.warehouseNo = warehouseNo;
        this.expNo = expNo;
        this.expType = expType;
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

    @Column(name="EXP_NO", nullable=false, length=20)

    public String getExpNo() {
        return this.expNo;
    }
    
    public void setExpNo(String expNo) {
        this.expNo = expNo;
    }

    @Column(name="EXP_TYPE", nullable=false, length=5)

    public String getExpType() {
        return this.expType;
    }
    
    public void setExpType(String expType) {
        this.expType = expType;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Odata_ExpMId) ) return false;
		 Odata_ExpMId castOther = ( Odata_ExpMId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getExpNo()==castOther.getExpNo()) || ( this.getExpNo()!=null && castOther.getExpNo()!=null && this.getExpNo().equals(castOther.getExpNo()) ) )
 && ( (this.getExpType()==castOther.getExpType()) || ( this.getExpType()!=null && castOther.getExpType()!=null && this.getExpType().equals(castOther.getExpType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getExpNo() == null ? 0 : this.getExpNo().hashCode() );
         result = 37 * result + ( getExpType() == null ? 0 : this.getExpType().hashCode() );
         return result;
   }   





}