package com.sealinkin.idata.po;
// default package

import java.math.BigDecimal;
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
 * IdataCheckM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_CHECK_M")
public class Idata_CheckM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_CheckMId id;
	private String SImportNo;
	private String SCheckNo;
	private String importType;
	private String importNo;
	private String supplierNo;
	private String dockNo;
	private String checkWorker;
	private String qcWorker;
	private String unloadWorker;
	private String status;
	private Date checkStartDate;
	private Date checkEndDate;
	private String printerGroupNo;
	private String receivingNo;
	private String checkTools;
	private BigDecimal sendFlag;
	private BigDecimal printTimes;
	private String downType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String memo;
	// Constructors

	/** default constructor */
	public Idata_CheckM() {
	}

	/** minimal constructor */
	public Idata_CheckM(Idata_CheckMId id, String SImportNo, String SCheckNo,
			String importType, String importNo, String supplierNo,
			String dockNo, String status, String printerGroupNo,
			String checkTools, BigDecimal sendFlag, BigDecimal printTimes,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.SImportNo = SImportNo;
		this.SCheckNo = SCheckNo;
		this.importType = importType;
		this.importNo = importNo;
		this.supplierNo = supplierNo;
		this.dockNo = dockNo;
		this.status = status;
		this.printerGroupNo = printerGroupNo;
		this.checkTools = checkTools;
		this.sendFlag = sendFlag;
		this.printTimes = printTimes;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Idata_CheckM(Idata_CheckMId id, String SImportNo, String SCheckNo,
			String importType, String importNo, String supplierNo,
			String dockNo, String checkWorker, String qcWorker,
			String unloadWorker, String status, Date checkStartDate,
			Date checkEndDate, String printerGroupNo, String receivingNo,
			String checkTools, BigDecimal sendFlag, BigDecimal printTimes,
			String downType, String rgstName, Date rgstDate, String updtName,
			Date updtDate,String memo) {
		this.id = id;
		this.SImportNo = SImportNo;
		this.SCheckNo = SCheckNo;
		this.importType = importType;
		this.importNo = importNo;
		this.supplierNo = supplierNo;
		this.dockNo = dockNo;
		this.checkWorker = checkWorker;
		this.qcWorker = qcWorker;
		this.unloadWorker = unloadWorker;
		this.status = status;
		this.checkStartDate = checkStartDate;
		this.checkEndDate = checkEndDate;
		this.printerGroupNo = printerGroupNo;
		this.receivingNo = receivingNo;
		this.checkTools = checkTools;
		this.sendFlag = sendFlag;
		this.printTimes = printTimes;
		this.downType = downType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.memo = memo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)) })
	public Idata_CheckMId getId() {
		return this.id;
	}

	public void setId(Idata_CheckMId id) {
		this.id = id;
	}

	@Column(name = "S_IMPORT_NO", nullable = false, length = 20)
	public String getSImportNo() {
		return this.SImportNo;
	}

	public void setSImportNo(String SImportNo) {
		this.SImportNo = SImportNo;
	}

	@Column(name = "S_CHECK_NO", nullable = false, length = 20)
	public String getSCheckNo() {
		return this.SCheckNo;
	}

	public void setSCheckNo(String SCheckNo) {
		this.SCheckNo = SCheckNo;
	}

	@Column(name = "IMPORT_TYPE", nullable = false, length = 2)
	public String getImportType() {
		return this.importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	@Column(name = "IMPORT_NO", nullable = false, length = 20)
	public String getImportNo() {
		return this.importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "DOCK_NO", nullable = false, length = 3)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Column(name = "CHECK_WORKER", length = 20)
	public String getCheckWorker() {
		return this.checkWorker;
	}

	public void setCheckWorker(String checkWorker) {
		this.checkWorker = checkWorker;
	}

	@Column(name = "QC_WORKER", length = 20)
	public String getQcWorker() {
		return this.qcWorker;
	}

	public void setQcWorker(String qcWorker) {
		this.qcWorker = qcWorker;
	}

	@Column(name = "UNLOAD_WORKER", length = 20)
	public String getUnloadWorker() {
		return this.unloadWorker;
	}

	public void setUnloadWorker(String unloadWorker) {
		this.unloadWorker = unloadWorker;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_START_DATE", length = 7)
	public Date getCheckStartDate() {
		return this.checkStartDate;
	}

	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_END_DATE", length = 7)
	public Date getCheckEndDate() {
		return this.checkEndDate;
	}

	public void setCheckEndDate(Date checkEndDate) {
		this.checkEndDate = checkEndDate;
	}

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	@Column(name = "RECEIVING_NO", length = 20)
	public String getReceivingNo() {
		return this.receivingNo;
	}

	public void setReceivingNo(String receivingNo) {
		this.receivingNo = receivingNo;
	}

	@Column(name = "CHECK_TOOLS", nullable = false, length = 1)
	public String getCheckTools() {
		return this.checkTools;
	}

	public void setCheckTools(String checkTools) {
		this.checkTools = checkTools;
	}

	@Column(name = "SEND_FLAG", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Column(name = "PRINT_TIMES", nullable = false, precision = 22, scale = 0)
	public BigDecimal getPrintTimes() {
		return this.printTimes;
	}

	public void setPrintTimes(BigDecimal printTimes) {
		this.printTimes = printTimes;
	}

	@Column(name = "DOWN_TYPE", length = 20)
	public String getDownType() {
		return this.downType;
	}

	public void setDownType(String downType) {
		this.downType = downType;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	
	@Column(name = "MEMO", length = 220)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}