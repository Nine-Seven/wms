package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Odata_DivideDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Odata_DivideDId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String divideNo;
     private String warehouseNo;
     private String ownerNo;
     private Long divideId;
     private String custContainerNo;
     private String status;


    // Constructors

    /** default constructor */
    public Odata_DivideDId() {
    }

    
    /** full constructor */
    public Odata_DivideDId(String enterpriseNo, String divideNo, String warehouseNo, String ownerNo, Long divideId, String custContainerNo, String status) {
        this.enterpriseNo = enterpriseNo;
        this.divideNo = divideNo;
        this.warehouseNo = warehouseNo;
        this.ownerNo = ownerNo;
        this.divideId = divideId;
        this.custContainerNo = custContainerNo;
        this.status = status;
    }

   
    // Property accessors

    @Column(name="ENTERPRISE_NO", nullable=false, length=15)

    public String getEnterpriseNo() {
        return this.enterpriseNo;
    }
    
    public void setEnterpriseNo(String enterpriseNo) {
        this.enterpriseNo = enterpriseNo;
    }

    @Column(name="DIVIDE_NO", nullable=false, length=20)

    public String getDivideNo() {
        return this.divideNo;
    }
    
    public void setDivideNo(String divideNo) {
        this.divideNo = divideNo;
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

    @Column(name="DIVIDE_ID", nullable=false, precision=10, scale=0)

    public Long getDivideId() {
        return this.divideId;
    }
    
    public void setDivideId(Long divideId) {
        this.divideId = divideId;
    }

    @Column(name="CUST_CONTAINER_NO", nullable=false, length=24)

    public String getCustContainerNo() {
        return this.custContainerNo;
    }
    
    public void setCustContainerNo(String custContainerNo) {
        this.custContainerNo = custContainerNo;
    }

    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Odata_DivideDId) ) return false;
		 Odata_DivideDId castOther = ( Odata_DivideDId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getDivideNo()==castOther.getDivideNo()) || ( this.getDivideNo()!=null && castOther.getDivideNo()!=null && this.getDivideNo().equals(castOther.getDivideNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getOwnerNo()==castOther.getOwnerNo()) || ( this.getOwnerNo()!=null && castOther.getOwnerNo()!=null && this.getOwnerNo().equals(castOther.getOwnerNo()) ) )
 && ( (this.getDivideId()==castOther.getDivideId()) || ( this.getDivideId()!=null && castOther.getDivideId()!=null && this.getDivideId().equals(castOther.getDivideId()) ) )
 && ( (this.getCustContainerNo()==castOther.getCustContainerNo()) || ( this.getCustContainerNo()!=null && castOther.getCustContainerNo()!=null && this.getCustContainerNo().equals(castOther.getCustContainerNo()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getDivideNo() == null ? 0 : this.getDivideNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode() );
         result = 37 * result + ( getDivideId() == null ? 0 : this.getDivideId().hashCode() );
         result = 37 * result + ( getCustContainerNo() == null ? 0 : this.getCustContainerNo().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }   





}