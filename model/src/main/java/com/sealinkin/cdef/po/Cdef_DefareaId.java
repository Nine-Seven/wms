package com.sealinkin.cdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Cdef_DefareaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Cdef_DefareaId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String warehouseNo;
     private String wareNo;
     private String areaNo;


    // Constructors

    /** default constructor */
    public Cdef_DefareaId() {
    }

    
    /** full constructor */
    public Cdef_DefareaId(String enterpriseNo, String warehouseNo, String wareNo, String areaNo) {
        this.enterpriseNo = enterpriseNo;
        this.warehouseNo = warehouseNo;
        this.wareNo = wareNo;
        this.areaNo = areaNo;
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

    @Column(name="WARE_NO", nullable=false, length=5)

    public String getWareNo() {
        return this.wareNo;
    }
    
    public void setWareNo(String wareNo) {
        this.wareNo = wareNo;
    }

    @Column(name="AREA_NO", nullable=false, length=5)

    public String getAreaNo() {
        return this.areaNo;
    }
    
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Cdef_DefareaId) ) return false;
		 Cdef_DefareaId castOther = ( Cdef_DefareaId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getWareNo()==castOther.getWareNo()) || ( this.getWareNo()!=null && castOther.getWareNo()!=null && this.getWareNo().equals(castOther.getWareNo()) ) )
 && ( (this.getAreaNo()==castOther.getAreaNo()) || ( this.getAreaNo()!=null && castOther.getAreaNo()!=null && this.getAreaNo().equals(castOther.getAreaNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getWareNo() == null ? 0 : this.getWareNo().hashCode() );
         result = 37 * result + ( getAreaNo() == null ? 0 : this.getAreaNo().hashCode() );
         return result;
   }   





}