package com.sealinkin.ridata.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Ridata_CheckPalTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="RIDATA_CHECK_PAL_TMP")

public class Ridata_CheckPalTmp  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_CheckPalTmpId id;
     private String ownerNo;
     private String SUntreadNo;
     private String articleNo;
     private Double packingQty;
     private Double checkQty;
     private String status;
     private String printerGroupNo;
     private String dockNo;
     private String subLabelNo;
     private String barcode;
     private Date produceDate;
     private Date expireDate;
     private String quality;
     private String lotNo;
     private String rsvBatch1;
     private String rsvBatch2;
     private String rsvBatch3;
     private String rsvBatch4;
     private String rsvBatch5;
     private String rsvBatch6;
     private String rsvBatch7;
     private String rsvBatch8;
     private String stockType;
     private String stockValue;
     private String checkTools;
     private String untreadType;
     private String supplierNo;
     private String fixpalFlag;
     private String businessType;
     private String deptNo;
     private String cellNo;
     private String batchNo;
     private String rgstName;
     private Date rgstDate;
     private Date operateDate;
     private String labelId;


    // Constructors

    /** default constructor */
    public Ridata_CheckPalTmp() {
    }

	/** minimal constructor */
    public Ridata_CheckPalTmp(Ridata_CheckPalTmpId id, String ownerNo, String SUntreadNo, String articleNo, Double packingQty, Double checkQty, String status, String printerGroupNo, String dockNo, String subLabelNo, String barcode, Date produceDate, Date expireDate, String quality, String lotNo, String stockType, String stockValue, String checkTools, String untreadType, String supplierNo, String fixpalFlag, String businessType, String deptNo, String batchNo, Date operateDate) {
        this.id = id;
        this.ownerNo = ownerNo;
        this.SUntreadNo = SUntreadNo;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.checkQty = checkQty;
        this.status = status;
        this.printerGroupNo = printerGroupNo;
        this.dockNo = dockNo;
        this.subLabelNo = subLabelNo;
        this.barcode = barcode;
        this.produceDate = produceDate;
        this.expireDate = expireDate;
        this.quality = quality;
        this.lotNo = lotNo;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.checkTools = checkTools;
        this.untreadType = untreadType;
        this.supplierNo = supplierNo;
        this.fixpalFlag = fixpalFlag;
        this.businessType = businessType;
        this.deptNo = deptNo;
        this.batchNo = batchNo;
        this.operateDate = operateDate;
    }
    
    /** full constructor */
    public Ridata_CheckPalTmp(Ridata_CheckPalTmpId id, String ownerNo, String SUntreadNo, String articleNo, Double packingQty, Double checkQty, String status, String printerGroupNo, String dockNo, String subLabelNo, String barcode, Date produceDate, Date expireDate, String quality, String lotNo, String rsvBatch1, String rsvBatch2, String rsvBatch3, String rsvBatch4, String rsvBatch5, String rsvBatch6, String rsvBatch7, String rsvBatch8, String stockType, String stockValue, String checkTools, String untreadType, String supplierNo, String fixpalFlag, String businessType, String deptNo, String cellNo, String batchNo, String rgstName, Date rgstDate, Date operateDate, String labelId) {
        this.id = id;
        this.ownerNo = ownerNo;
        this.SUntreadNo = SUntreadNo;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.checkQty = checkQty;
        this.status = status;
        this.printerGroupNo = printerGroupNo;
        this.dockNo = dockNo;
        this.subLabelNo = subLabelNo;
        this.barcode = barcode;
        this.produceDate = produceDate;
        this.expireDate = expireDate;
        this.quality = quality;
        this.lotNo = lotNo;
        this.rsvBatch1 = rsvBatch1;
        this.rsvBatch2 = rsvBatch2;
        this.rsvBatch3 = rsvBatch3;
        this.rsvBatch4 = rsvBatch4;
        this.rsvBatch5 = rsvBatch5;
        this.rsvBatch6 = rsvBatch6;
        this.rsvBatch7 = rsvBatch7;
        this.rsvBatch8 = rsvBatch8;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.checkTools = checkTools;
        this.untreadType = untreadType;
        this.supplierNo = supplierNo;
        this.fixpalFlag = fixpalFlag;
        this.businessType = businessType;
        this.deptNo = deptNo;
        this.cellNo = cellNo;
        this.batchNo = batchNo;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.operateDate = operateDate;
        this.labelId = labelId;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="SCheckNo", column=@Column(name="S_CHECK_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="rowId", column=@Column(name="ROW_ID", nullable=false, precision=4, scale=0) ), 
        @AttributeOverride(name="labelNo", column=@Column(name="LABEL_NO", nullable=false, length=24) ) } )

    public Ridata_CheckPalTmpId getId() {
        return this.id;
    }
    
    public void setId(Ridata_CheckPalTmpId id) {
        this.id = id;
    }
    
    @Column(name="OWNER_NO", nullable=false, length=3)

    public String getOwnerNo() {
        return this.ownerNo;
    }
    
    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }
    
    @Column(name="S_UNTREAD_NO", nullable=false, length=20)

    public String getSUntreadNo() {
        return this.SUntreadNo;
    }
    
    public void setSUntreadNo(String SUntreadNo) {
        this.SUntreadNo = SUntreadNo;
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
    
    @Column(name="CHECK_QTY", nullable=false, precision=18, scale=5)

    public Double getCheckQty() {
        return this.checkQty;
    }
    
    public void setCheckQty(Double checkQty) {
        this.checkQty = checkQty;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="PRINTER_GROUP_NO", nullable=false, length=5)

    public String getPrinterGroupNo() {
        return this.printerGroupNo;
    }
    
    public void setPrinterGroupNo(String printerGroupNo) {
        this.printerGroupNo = printerGroupNo;
    }
    
    @Column(name="DOCK_NO", nullable=false, length=3)

    public String getDockNo() {
        return this.dockNo;
    }
    
    public void setDockNo(String dockNo) {
        this.dockNo = dockNo;
    }
    
    @Column(name="SUB_LABEL_NO", nullable=false, length=24)

    public String getSubLabelNo() {
        return this.subLabelNo;
    }
    
    public void setSubLabelNo(String subLabelNo) {
        this.subLabelNo = subLabelNo;
    }
    
    @Column(name="BARCODE", nullable=false, length=25)

    public String getBarcode() {
        return this.barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="PRODUCE_DATE", nullable=false, length=7)

    public Date getProduceDate() {
        return this.produceDate;
    }
    
    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXPIRE_DATE", nullable=false, length=7)

    public Date getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
    @Column(name="QUALITY", nullable=false, length=2)

    public String getQuality() {
        return this.quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
    }
    
    @Column(name="LOT_NO", nullable=false, length=32)

    public String getLotNo() {
        return this.lotNo;
    }
    
    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }
    
    @Column(name="RSV_BATCH1", length=20)

    public String getRsvBatch1() {
        return this.rsvBatch1;
    }
    
    public void setRsvBatch1(String rsvBatch1) {
        this.rsvBatch1 = rsvBatch1;
    }
    
    @Column(name="RSV_BATCH2", length=20)

    public String getRsvBatch2() {
        return this.rsvBatch2;
    }
    
    public void setRsvBatch2(String rsvBatch2) {
        this.rsvBatch2 = rsvBatch2;
    }
    
    @Column(name="RSV_BATCH3", length=20)

    public String getRsvBatch3() {
        return this.rsvBatch3;
    }
    
    public void setRsvBatch3(String rsvBatch3) {
        this.rsvBatch3 = rsvBatch3;
    }
    
    @Column(name="RSV_BATCH4", length=20)

    public String getRsvBatch4() {
        return this.rsvBatch4;
    }
    
    public void setRsvBatch4(String rsvBatch4) {
        this.rsvBatch4 = rsvBatch4;
    }
    
    @Column(name="RSV_BATCH5", length=20)

    public String getRsvBatch5() {
        return this.rsvBatch5;
    }
    
    public void setRsvBatch5(String rsvBatch5) {
        this.rsvBatch5 = rsvBatch5;
    }
    
    @Column(name="RSV_BATCH6", length=20)

    public String getRsvBatch6() {
        return this.rsvBatch6;
    }
    
    public void setRsvBatch6(String rsvBatch6) {
        this.rsvBatch6 = rsvBatch6;
    }
    
    @Column(name="RSV_BATCH7", length=20)

    public String getRsvBatch7() {
        return this.rsvBatch7;
    }
    
    public void setRsvBatch7(String rsvBatch7) {
        this.rsvBatch7 = rsvBatch7;
    }
    
    @Column(name="RSV_BATCH8", length=20)

    public String getRsvBatch8() {
        return this.rsvBatch8;
    }
    
    public void setRsvBatch8(String rsvBatch8) {
        this.rsvBatch8 = rsvBatch8;
    }
    
    @Column(name="STOCK_TYPE", nullable=false, length=1)

    public String getStockType() {
        return this.stockType;
    }
    
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }
    
    @Column(name="STOCK_VALUE", nullable=false, length=20)

    public String getStockValue() {
        return this.stockValue;
    }
    
    public void setStockValue(String stockValue) {
        this.stockValue = stockValue;
    }
    
    @Column(name="CHECK_TOOLS", nullable=false, length=1)

    public String getCheckTools() {
        return this.checkTools;
    }
    
    public void setCheckTools(String checkTools) {
        this.checkTools = checkTools;
    }
    
    @Column(name="UNTREAD_TYPE", nullable=false, length=2)

    public String getUntreadType() {
        return this.untreadType;
    }
    
    public void setUntreadType(String untreadType) {
        this.untreadType = untreadType;
    }
    
    @Column(name="SUPPLIER_NO", nullable=false, length=10)

    public String getSupplierNo() {
        return this.supplierNo;
    }
    
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }
    
    @Column(name="FIXPAL_FLAG", nullable=false, length=1)

    public String getFixpalFlag() {
        return this.fixpalFlag;
    }
    
    public void setFixpalFlag(String fixpalFlag) {
        this.fixpalFlag = fixpalFlag;
    }
    
    @Column(name="BUSINESS_TYPE", nullable=false, length=2)

    public String getBusinessType() {
        return this.businessType;
    }
    
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    
    @Column(name="DEPT_NO", nullable=false, length=20)

    public String getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    
    @Column(name="CELL_NO", length=24)

    public String getCellNo() {
        return this.cellNo;
    }
    
    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }
    
    @Column(name="BATCH_NO", nullable=false, length=2)

    public String getBatchNo() {
        return this.batchNo;
    }
    
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    
    @Column(name="RGST_NAME", length=20)

    public String getRgstName() {
        return this.rgstName;
    }
    
    public void setRgstName(String rgstName) {
        this.rgstName = rgstName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RGST_DATE", length=7)

    public Date getRgstDate() {
        return this.rgstDate;
    }
    
    public void setRgstDate(Date rgstDate) {
        this.rgstDate = rgstDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="OPERATE_DATE", nullable=false, length=7)

    public Date getOperateDate() {
        return this.operateDate;
    }
    
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
    
    @Column(name="LABEL_ID", precision=4, scale=0)

    public String getLabelId() {
        return this.labelId;
    }
    
    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
   








}