package com.sealinkin.ridata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Ridata_CheckPalTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Ridata_CheckPalTmpId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
     private String warehouseNo;
     private String SCheckNo;
     private Short rowId;
     private String labelNo;


    // Constructors

    /** default constructor */
    public Ridata_CheckPalTmpId() {
    }

    
    /** full constructor */
    public Ridata_CheckPalTmpId(String enterpriseNo, String warehouseNo, String SCheckNo, Short rowId, String labelNo) {
        this.enterpriseNo = enterpriseNo;
        this.warehouseNo = warehouseNo;
        this.SCheckNo = SCheckNo;
        this.rowId = rowId;
        this.labelNo = labelNo;
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

    @Column(name="S_CHECK_NO", nullable=false, length=20)

    public String getSCheckNo() {
        return this.SCheckNo;
    }
    
    public void setSCheckNo(String SCheckNo) {
        this.SCheckNo = SCheckNo;
    }

    @Column(name="ROW_ID", nullable=false, precision=4, scale=0)

    public Short getRowId() {
        return this.rowId;
    }
    
    public void setRowId(Short rowId) {
        this.rowId = rowId;
    }

    @Column(name="LABEL_NO", nullable=false, length=24)

    public String getLabelNo() {
        return this.labelNo;
    }
    
    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Ridata_CheckPalTmpId) ) return false;
		 Ridata_CheckPalTmpId castOther = ( Ridata_CheckPalTmpId ) other; 
         
		 return ( (this.getEnterpriseNo()==castOther.getEnterpriseNo()) || ( this.getEnterpriseNo()!=null && castOther.getEnterpriseNo()!=null && this.getEnterpriseNo().equals(castOther.getEnterpriseNo()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getSCheckNo()==castOther.getSCheckNo()) || ( this.getSCheckNo()!=null && castOther.getSCheckNo()!=null && this.getSCheckNo().equals(castOther.getSCheckNo()) ) )
 && ( (this.getRowId()==castOther.getRowId()) || ( this.getRowId()!=null && castOther.getRowId()!=null && this.getRowId().equals(castOther.getRowId()) ) )
 && ( (this.getLabelNo()==castOther.getLabelNo()) || ( this.getLabelNo()!=null && castOther.getLabelNo()!=null && this.getLabelNo().equals(castOther.getLabelNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEnterpriseNo() == null ? 0 : this.getEnterpriseNo().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getSCheckNo() == null ? 0 : this.getSCheckNo().hashCode() );
         result = 37 * result + ( getRowId() == null ? 0 : this.getRowId().hashCode() );
         result = 37 * result + ( getLabelNo() == null ? 0 : this.getLabelNo().hashCode() );
         return result;
   }   





}