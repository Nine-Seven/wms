package com.sealinkin.sodata.po;

// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * SodataWasteTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SODATA_WASTE_TMP")
public class SodataWasteTmp  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SodataWasteTmpId id;


    // Constructors

    /** default constructor */
    public SodataWasteTmp() {
    }

    
    /** full constructor */
    public SodataWasteTmp(SodataWasteTmpId id) {
        this.id = id;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="poNo", column=@Column(name="PO_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="orgNo", column=@Column(name="ORG_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="ownerArticleNo", column=@Column(name="OWNER_ARTICLE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="packingQty", column=@Column(name="PACKING_QTY", nullable=false, precision=18, scale=5) ), 
        @AttributeOverride(name="wasteQty", column=@Column(name="WASTE_QTY", nullable=false, precision=14, scale=5) ), 
        @AttributeOverride(name="status", column=@Column(name="STATUS", nullable=false, length=2) ), 
        @AttributeOverride(name="remark", column=@Column(name="REMARK") ), 
        @AttributeOverride(name="rowId", column=@Column(name="ROW_ID", nullable=false, precision=22, scale=0) ) } )

    public SodataWasteTmpId getId() {
        return this.id;
    }
    
    public void setId(SodataWasteTmpId id) {
        this.id = id;
    }
   








}