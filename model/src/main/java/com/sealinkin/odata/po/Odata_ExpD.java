package com.sealinkin.odata.po;
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
 * Odata_ExpD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_EXP_D")
public class Odata_ExpD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_ExpDId id;
	private String ownerNo;
	private String articleNo;
	private double packingQty;
	private double articleQty;
	private double scheduleQty;
	private double locateQty;
	private double realQty;
	private double unitCost;
	private String ownerArticleNo;
	private String produceCondition;
	private Date produceValue1;
	private Date produceValue2;
	private String expireCondition;
	private Date expireValue1;
	private Date expireValue2;
	private String qualityCondition;
	private String qualityValue1;
	private String qualityValue2;
	private String lotnoCondition;
	private String lotnoValue1;
	private String lotnoValue2;
	private String rsvbatch1Condition;
	private String rsvbatch1Value1;
	private String rsvbatch1Value2;
	private String rsvbatch2Condition;
	private String rsvbatch2Value1;
	private String rsvbatch2Value2;
	private String rsvbatch3Condition;
	private String rsvbatch3Value1;
	private String rsvbatch3Value2;
	private String rsvbatch4Condition;
	private String rsvbatch4Value1;
	private String rsvbatch4Value2;
	private String rsvbatch5Condition;
	private String rsvbatch5Value1;
	private String rsvbatch5Value2;
	private String rsvbatch6Condition;
	private String rsvbatch6Value1;
	private String rsvbatch6Value2;
	private String rsvbatch7Condition;
	private String rsvbatch7Value1;
	private String rsvbatch7Value2;
	private String rsvbatch8Condition;
	private String rsvbatch8Value1;
	private String rsvbatch8Value2;
	private String specifyField;
	private String specifyCondition;
	private String specifyValue1;
	private String specifyValue2;
	private String status;
	private String errorStatus;
	private Date rgstDate;
	private Date expDate;

	// Constructors

	/** default constructor */
	public Odata_ExpD() {
	}

	/** minimal constructor */
	public Odata_ExpD(Odata_ExpDId id, String ownerNo, String articleNo,
			double packingQty, double articleQty, double locateQty,
			String ownerArticleNo, String status, String errorStatus,
			Date rgstDate, Date expDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.locateQty = locateQty;
		this.ownerArticleNo = ownerArticleNo;
		this.status = status;
		this.errorStatus = errorStatus;
		this.rgstDate = rgstDate;
		this.expDate = expDate;
	}

	/** full constructor */
	public Odata_ExpD(Odata_ExpDId id, String ownerNo, String articleNo,
			double packingQty, double articleQty, double scheduleQty,
			double locateQty, double realQty, double unitCost,
			String ownerArticleNo, String produceCondition, Date produceValue1,
			Date produceValue2, String expireCondition, Date expireValue1,
			Date expireValue2, String qualityCondition, String qualityValue1,
			String qualityValue2, String lotnoCondition, String lotnoValue1,
			String lotnoValue2, String rsvbatch1Condition,
			String rsvbatch1Value1, String rsvbatch1Value2,
			String rsvbatch2Condition, String rsvbatch2Value1,
			String rsvbatch2Value2, String rsvbatch3Condition,
			String rsvbatch3Value1, String rsvbatch3Value2,
			String rsvbatch4Condition, String rsvbatch4Value1,
			String rsvbatch4Value2, String rsvbatch5Condition,
			String rsvbatch5Value1, String rsvbatch5Value2,
			String rsvbatch6Condition, String rsvbatch6Value1,
			String rsvbatch6Value2, String rsvbatch7Condition,
			String rsvbatch7Value1, String rsvbatch7Value2,
			String rsvbatch8Condition, String rsvbatch8Value1,
			String rsvbatch8Value2, String specifyField,
			String specifyCondition, String specifyValue1,
			String specifyValue2, String status, String errorStatus,
			Date rgstDate, Date expDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.scheduleQty = scheduleQty;
		this.locateQty = locateQty;
		this.realQty = realQty;
		this.unitCost = unitCost;
		this.ownerArticleNo = ownerArticleNo;
		this.produceCondition = produceCondition;
		this.produceValue1 = produceValue1;
		this.produceValue2 = produceValue2;
		this.expireCondition = expireCondition;
		this.expireValue1 = expireValue1;
		this.expireValue2 = expireValue2;
		this.qualityCondition = qualityCondition;
		this.qualityValue1 = qualityValue1;
		this.qualityValue2 = qualityValue2;
		this.lotnoCondition = lotnoCondition;
		this.lotnoValue1 = lotnoValue1;
		this.lotnoValue2 = lotnoValue2;
		this.rsvbatch1Condition = rsvbatch1Condition;
		this.rsvbatch1Value1 = rsvbatch1Value1;
		this.rsvbatch1Value2 = rsvbatch1Value2;
		this.rsvbatch2Condition = rsvbatch2Condition;
		this.rsvbatch2Value1 = rsvbatch2Value1;
		this.rsvbatch2Value2 = rsvbatch2Value2;
		this.rsvbatch3Condition = rsvbatch3Condition;
		this.rsvbatch3Value1 = rsvbatch3Value1;
		this.rsvbatch3Value2 = rsvbatch3Value2;
		this.rsvbatch4Condition = rsvbatch4Condition;
		this.rsvbatch4Value1 = rsvbatch4Value1;
		this.rsvbatch4Value2 = rsvbatch4Value2;
		this.rsvbatch5Condition = rsvbatch5Condition;
		this.rsvbatch5Value1 = rsvbatch5Value1;
		this.rsvbatch5Value2 = rsvbatch5Value2;
		this.rsvbatch6Condition = rsvbatch6Condition;
		this.rsvbatch6Value1 = rsvbatch6Value1;
		this.rsvbatch6Value2 = rsvbatch6Value2;
		this.rsvbatch7Condition = rsvbatch7Condition;
		this.rsvbatch7Value1 = rsvbatch7Value1;
		this.rsvbatch7Value2 = rsvbatch7Value2;
		this.rsvbatch8Condition = rsvbatch8Condition;
		this.rsvbatch8Value1 = rsvbatch8Value1;
		this.rsvbatch8Value2 = rsvbatch8Value2;
		this.specifyField = specifyField;
		this.specifyCondition = specifyCondition;
		this.specifyValue1 = specifyValue1;
		this.specifyValue2 = specifyValue2;
		this.status = status;
		this.errorStatus = errorStatus;
		this.rgstDate = rgstDate;
		this.expDate = expDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "expNo", column = @Column(name = "EXP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 4, scale = 0)) })
	public Odata_ExpDId getId() {
		return this.id;
	}

	public void setId(Odata_ExpDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)
	public double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "SCHEDULE_QTY", precision = 18, scale = 5)
	public double getScheduleQty() {
		return this.scheduleQty;
	}

	public void setScheduleQty(double scheduleQty) {
		this.scheduleQty = scheduleQty;
	}

	@Column(name = "LOCATE_QTY", nullable = false, precision = 18, scale = 5)
	public double getLocateQty() {
		return this.locateQty;
	}

	public void setLocateQty(double locateQty) {
		this.locateQty = locateQty;
	}

	@Column(name = "REAL_QTY", precision = 18, scale = 5)
	public double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "UNIT_COST", precision = 11, scale = 5)
	public double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 25)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "PRODUCE_CONDITION", length = 100)
	public String getProduceCondition() {
		return this.produceCondition;
	}

	public void setProduceCondition(String produceCondition) {
		this.produceCondition = produceCondition;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCE_VALUE1", length = 7)
	public Date getProduceValue1() {
		return this.produceValue1;
	}

	public void setProduceValue1(Date produceValue1) {
		this.produceValue1 = produceValue1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCE_VALUE2", length = 7)
	public Date getProduceValue2() {
		return this.produceValue2;
	}

	public void setProduceValue2(Date produceValue2) {
		this.produceValue2 = produceValue2;
	}

	@Column(name = "EXPIRE_CONDITION", length = 100)
	public String getExpireCondition() {
		return this.expireCondition;
	}

	public void setExpireCondition(String expireCondition) {
		this.expireCondition = expireCondition;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_VALUE1", length = 7)
	public Date getExpireValue1() {
		return this.expireValue1;
	}

	public void setExpireValue1(Date expireValue1) {
		this.expireValue1 = expireValue1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_VALUE2", length = 7)
	public Date getExpireValue2() {
		return this.expireValue2;
	}

	public void setExpireValue2(Date expireValue2) {
		this.expireValue2 = expireValue2;
	}

	@Column(name = "QUALITY_CONDITION", length = 100)
	public String getQualityCondition() {
		return this.qualityCondition;
	}

	public void setQualityCondition(String qualityCondition) {
		this.qualityCondition = qualityCondition;
	}

	@Column(name = "QUALITY_VALUE1", length = 50)
	public String getQualityValue1() {
		return this.qualityValue1;
	}

	public void setQualityValue1(String qualityValue1) {
		this.qualityValue1 = qualityValue1;
	}

	@Column(name = "QUALITY_VALUE2", length = 50)
	public String getQualityValue2() {
		return this.qualityValue2;
	}

	public void setQualityValue2(String qualityValue2) {
		this.qualityValue2 = qualityValue2;
	}

	@Column(name = "LOTNO_CONDITION", length = 100)
	public String getLotnoCondition() {
		return this.lotnoCondition;
	}

	public void setLotnoCondition(String lotnoCondition) {
		this.lotnoCondition = lotnoCondition;
	}

	@Column(name = "LOTNO_VALUE1", length = 50)
	public String getLotnoValue1() {
		return this.lotnoValue1;
	}

	public void setLotnoValue1(String lotnoValue1) {
		this.lotnoValue1 = lotnoValue1;
	}

	@Column(name = "LOTNO_VALUE2", length = 50)
	public String getLotnoValue2() {
		return this.lotnoValue2;
	}

	public void setLotnoValue2(String lotnoValue2) {
		this.lotnoValue2 = lotnoValue2;
	}

	@Column(name = "RSVBATCH1_CONDITION", length = 100)
	public String getRsvbatch1Condition() {
		return this.rsvbatch1Condition;
	}

	public void setRsvbatch1Condition(String rsvbatch1Condition) {
		this.rsvbatch1Condition = rsvbatch1Condition;
	}

	@Column(name = "RSVBATCH1_VALUE1", length = 50)
	public String getRsvbatch1Value1() {
		return this.rsvbatch1Value1;
	}

	public void setRsvbatch1Value1(String rsvbatch1Value1) {
		this.rsvbatch1Value1 = rsvbatch1Value1;
	}

	@Column(name = "RSVBATCH1_VALUE2", length = 50)
	public String getRsvbatch1Value2() {
		return this.rsvbatch1Value2;
	}

	public void setRsvbatch1Value2(String rsvbatch1Value2) {
		this.rsvbatch1Value2 = rsvbatch1Value2;
	}

	@Column(name = "RSVBATCH2_CONDITION", length = 100)
	public String getRsvbatch2Condition() {
		return this.rsvbatch2Condition;
	}

	public void setRsvbatch2Condition(String rsvbatch2Condition) {
		this.rsvbatch2Condition = rsvbatch2Condition;
	}

	@Column(name = "RSVBATCH2_VALUE1", length = 50)
	public String getRsvbatch2Value1() {
		return this.rsvbatch2Value1;
	}

	public void setRsvbatch2Value1(String rsvbatch2Value1) {
		this.rsvbatch2Value1 = rsvbatch2Value1;
	}

	@Column(name = "RSVBATCH2_VALUE2", length = 50)
	public String getRsvbatch2Value2() {
		return this.rsvbatch2Value2;
	}

	public void setRsvbatch2Value2(String rsvbatch2Value2) {
		this.rsvbatch2Value2 = rsvbatch2Value2;
	}

	@Column(name = "RSVBATCH3_CONDITION", length = 100)
	public String getRsvbatch3Condition() {
		return this.rsvbatch3Condition;
	}

	public void setRsvbatch3Condition(String rsvbatch3Condition) {
		this.rsvbatch3Condition = rsvbatch3Condition;
	}

	@Column(name = "RSVBATCH3_VALUE1", length = 50)
	public String getRsvbatch3Value1() {
		return this.rsvbatch3Value1;
	}

	public void setRsvbatch3Value1(String rsvbatch3Value1) {
		this.rsvbatch3Value1 = rsvbatch3Value1;
	}

	@Column(name = "RSVBATCH3_VALUE2", length = 50)
	public String getRsvbatch3Value2() {
		return this.rsvbatch3Value2;
	}

	public void setRsvbatch3Value2(String rsvbatch3Value2) {
		this.rsvbatch3Value2 = rsvbatch3Value2;
	}

	@Column(name = "RSVBATCH4_CONDITION", length = 100)
	public String getRsvbatch4Condition() {
		return this.rsvbatch4Condition;
	}

	public void setRsvbatch4Condition(String rsvbatch4Condition) {
		this.rsvbatch4Condition = rsvbatch4Condition;
	}

	@Column(name = "RSVBATCH4_VALUE1", length = 50)
	public String getRsvbatch4Value1() {
		return this.rsvbatch4Value1;
	}

	public void setRsvbatch4Value1(String rsvbatch4Value1) {
		this.rsvbatch4Value1 = rsvbatch4Value1;
	}

	@Column(name = "RSVBATCH4_VALUE2", length = 50)
	public String getRsvbatch4Value2() {
		return this.rsvbatch4Value2;
	}

	public void setRsvbatch4Value2(String rsvbatch4Value2) {
		this.rsvbatch4Value2 = rsvbatch4Value2;
	}

	@Column(name = "RSVBATCH5_CONDITION", length = 100)
	public String getRsvbatch5Condition() {
		return this.rsvbatch5Condition;
	}

	public void setRsvbatch5Condition(String rsvbatch5Condition) {
		this.rsvbatch5Condition = rsvbatch5Condition;
	}

	@Column(name = "RSVBATCH5_VALUE1", length = 50)
	public String getRsvbatch5Value1() {
		return this.rsvbatch5Value1;
	}

	public void setRsvbatch5Value1(String rsvbatch5Value1) {
		this.rsvbatch5Value1 = rsvbatch5Value1;
	}

	@Column(name = "RSVBATCH5_VALUE2", length = 50)
	public String getRsvbatch5Value2() {
		return this.rsvbatch5Value2;
	}

	public void setRsvbatch5Value2(String rsvbatch5Value2) {
		this.rsvbatch5Value2 = rsvbatch5Value2;
	}

	@Column(name = "RSVBATCH6_CONDITION", length = 100)
	public String getRsvbatch6Condition() {
		return this.rsvbatch6Condition;
	}

	public void setRsvbatch6Condition(String rsvbatch6Condition) {
		this.rsvbatch6Condition = rsvbatch6Condition;
	}

	@Column(name = "RSVBATCH6_VALUE1", length = 50)
	public String getRsvbatch6Value1() {
		return this.rsvbatch6Value1;
	}

	public void setRsvbatch6Value1(String rsvbatch6Value1) {
		this.rsvbatch6Value1 = rsvbatch6Value1;
	}

	@Column(name = "RSVBATCH6_VALUE2", length = 50)
	public String getRsvbatch6Value2() {
		return this.rsvbatch6Value2;
	}

	public void setRsvbatch6Value2(String rsvbatch6Value2) {
		this.rsvbatch6Value2 = rsvbatch6Value2;
	}

	@Column(name = "RSVBATCH7_CONDITION", length = 100)
	public String getRsvbatch7Condition() {
		return this.rsvbatch7Condition;
	}

	public void setRsvbatch7Condition(String rsvbatch7Condition) {
		this.rsvbatch7Condition = rsvbatch7Condition;
	}

	@Column(name = "RSVBATCH7_VALUE1", length = 50)
	public String getRsvbatch7Value1() {
		return this.rsvbatch7Value1;
	}

	public void setRsvbatch7Value1(String rsvbatch7Value1) {
		this.rsvbatch7Value1 = rsvbatch7Value1;
	}

	@Column(name = "RSVBATCH7_VALUE2", length = 50)
	public String getRsvbatch7Value2() {
		return this.rsvbatch7Value2;
	}

	public void setRsvbatch7Value2(String rsvbatch7Value2) {
		this.rsvbatch7Value2 = rsvbatch7Value2;
	}

	@Column(name = "RSVBATCH8_CONDITION", length = 100)
	public String getRsvbatch8Condition() {
		return this.rsvbatch8Condition;
	}

	public void setRsvbatch8Condition(String rsvbatch8Condition) {
		this.rsvbatch8Condition = rsvbatch8Condition;
	}

	@Column(name = "RSVBATCH8_VALUE1", length = 50)
	public String getRsvbatch8Value1() {
		return this.rsvbatch8Value1;
	}

	public void setRsvbatch8Value1(String rsvbatch8Value1) {
		this.rsvbatch8Value1 = rsvbatch8Value1;
	}

	@Column(name = "RSVBATCH8_VALUE2", length = 50)
	public String getRsvbatch8Value2() {
		return this.rsvbatch8Value2;
	}

	public void setRsvbatch8Value2(String rsvbatch8Value2) {
		this.rsvbatch8Value2 = rsvbatch8Value2;
	}

	@Column(name = "SPECIFY_FIELD", length = 50)
	public String getSpecifyField() {
		return this.specifyField;
	}

	public void setSpecifyField(String specifyField) {
		this.specifyField = specifyField;
	}

	@Column(name = "SPECIFY_CONDITION", length = 100)
	public String getSpecifyCondition() {
		return this.specifyCondition;
	}

	public void setSpecifyCondition(String specifyCondition) {
		this.specifyCondition = specifyCondition;
	}

	@Column(name = "SPECIFY_VALUE1", length = 50)
	public String getSpecifyValue1() {
		return this.specifyValue1;
	}

	public void setSpecifyValue1(String specifyValue1) {
		this.specifyValue1 = specifyValue1;
	}

	@Column(name = "SPECIFY_VALUE2", length = 50)
	public String getSpecifyValue2() {
		return this.specifyValue2;
	}

	public void setSpecifyValue2(String specifyValue2) {
		this.specifyValue2 = specifyValue2;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ERROR_STATUS", nullable = false, length = 3)
	public String getErrorStatus() {
		return this.errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

}