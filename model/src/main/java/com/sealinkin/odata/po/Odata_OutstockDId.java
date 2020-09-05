package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Odata_OutstockDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Odata_OutstockDId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String outstockNo;
     private String warehouseNo;
     private String ownerNo;
     private Integer divideId;


    // Constructors

    /** default constructor */
    public Odata_OutstockDId() {
    }

    
    /** full constructor */
    public Odata_OutstockDId(String enterpriseNo, String outstockNo, String warehouseNo, String ownerNo, Integer divideId) {
        this.enterpriseNo = enterpriseNo;
        this.outstockNo = outstockNo;
        this.warehouseNo = warehouseNo;
        this.ownerNo = ownerNo;
        this.divideId = divideId;
    }

   
    // Property accessors

    @Column(name="ENTERPRISE_NO", nullable=false, length=15)

    public String getEnterpriseNo() {
        return this.enterpriseNo;
    }
    
    public void setEnterpriseNo(String enterpriseNo) {
        this.enterpriseNo = enterpriseNo;
    }

    @Column(name="OUTSTOCK_NO", nullable=false, length=20)

    public String getOutstockNo() {
        return this.outstockNo;
    }
    
    public void setOutstockNo(String outstockNo) {
        this.outstockNo = outstockNo;
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

    @Column(name="DIVIDE_ID", nullable=false, precision=8, scale=0)

    public Integer getDivideId() {
        return this.divideId;
    }
    
    public void setDivideId(Integer divideId) {
        this.divideId = divideId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Odata_OutstockDId) ) return false;
		 Odata_OutstockDId castOther = ( Odata_OutstockDId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getOutstockNo()==castOther.getOutstockNo()) || ( this.getOutstockNo()!=null && castOther.getOutstockNo()!=null && this.getOutstockNo().equals(castOther.getOutstockNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getOwnerNo()==castOther.getOwnerNo()) || ( this.getOwnerNo()!=null && castOther.getOwnerNo()!=null && this.getOwnerNo().equals(castOther.getOwnerNo()) ) )
 && ( (this.getDivideId()==castOther.getDivideId()) || ( this.getDivideId()!=null && castOther.getDivideId()!=null && this.getDivideId().equals(castOther.getDivideId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getOutstockNo() == null ? 0 : this.getOutstockNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode() );
         result = 37 * result + ( getDivideId() == null ? 0 : this.getDivideId().hashCode() );
         return result;
   }   





}