package com.sealinkin.bdef.po;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TmpFormexcelArticleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_Formexcel_ArticleId implements java.io.Serializable {

	// Fields

	private String ownerNo;
	private String ownerArticleNo;
	private String articleName;
	private String articleAlias;
	private String groupNo;
	private String groupName;
	private String unit;
	private String spec;
	private Double packingQty;
	private Double expiryDays;
	private Double unitVolumn;
	private Double unitWeight;
	private String barcode;
	private String boxNo;
	private Double palBaseQbox;
	private Double palHeightQbox;
	private Double ALength;
	private Double AWidth;
	private Double AHeight;
	private Double AWeight;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private Date operateDate;
	private String enterpriseNo;
	private String operateType;
	private String articleIdentifier;
	private String lotType;
	private String scanFlag;
	private Double rowId;
	private String supplierNo;
	private String temperatureFlag;
	private String ruleFlag;
	private String packingSpec;
	private String packingUnit;
	private String rsvAttr1;
	private String rsvAttr2;
	private String rsvAttr3;
	private String rsvAttr4;
	private String rsvAttr5;
	private String rsvAttr6;
	private Double rsvNum1;
	private Double rsvNum2;
	private Double rsvNum3;
	private Double qminOperatePacking;
	private String qminOperatePackingUnit;
	private Double unitPacking;
	// Constructors

	/** default constructor */
	public Tmp_Formexcel_ArticleId() {
	}

	/** minimal constructor */
	public Tmp_Formexcel_ArticleId(String ownerNo, String articleName,
			String groupNo, String groupName, String unit, String spec,
			Double expiryDays, Double unitVolumn, Double unitWeight,
			Double palBaseQbox, Double palHeightQbox, Double ALength,
			Double AWidth, Double AHeight, Double AWeight, String status,
			String rgstName, Date rgstDate, Date operateDate,
			String enterpriseNo, String lotType, String scanFlag,
			Double rowId, String temperatureFlag,Double unitPacking,String ruleFlag) {
		this.ownerNo = ownerNo;
		this.articleName = articleName;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.unit = unit;
		this.spec = spec;
		this.expiryDays = expiryDays;
		this.unitVolumn = unitVolumn;
		this.unitWeight = unitWeight;
		this.palBaseQbox = palBaseQbox;
		this.palHeightQbox = palHeightQbox;
		this.ALength = ALength;
		this.AWidth = AWidth;
		this.AHeight = AHeight;
		this.AWeight = AWeight;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.operateDate = operateDate;
		this.enterpriseNo = enterpriseNo;
		this.lotType = lotType;
		this.scanFlag = scanFlag;
		this.rowId = rowId;
		this.temperatureFlag = temperatureFlag;
		this.unitPacking = unitPacking;
		this.ruleFlag = ruleFlag;
	}

	/** full constructor */
	public Tmp_Formexcel_ArticleId(String ownerNo, String ownerArticleNo,
			String articleName, String articleAlias, String groupNo,
			String groupName, String unit, String spec, Double packingQty,
			Double expiryDays, Double unitVolumn, Double unitWeight,
			String barcode, String boxNo, Double palBaseQbox,
			Double palHeightQbox, Double ALength, Double AWidth,
			Double AHeight, Double AWeight, String status, String rgstName,
			Date rgstDate, Date operateDate, String enterpriseNo,
			String operateType, String articleIdentifier, String lotType,
			String scanFlag, Double rowId, String supplierNo,
			String temperatureFlag, String packingUnit,String packingSpec, String rsvAttr1,
			String rsvAttr2, String rsvAttr3, String rsvAttr4, String rsvAttr5,
			String rsvAttr6, Double rsvNum1, Double rsvNum2,
			Double rsvNum3,Double qminOperatePacking,
			String qminOperatePackingUnit, Double unitPacking, String ruleFlag) {
		this.ownerNo = ownerNo;
		this.ownerArticleNo = ownerArticleNo;
		this.articleName = articleName;
		this.articleAlias = articleAlias;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.unit = unit;
		this.spec = spec;
		this.packingQty = packingQty;
		this.expiryDays = expiryDays;
		this.unitVolumn = unitVolumn;
		this.unitWeight = unitWeight;
		this.barcode = barcode;
		this.boxNo = boxNo;
		this.palBaseQbox = palBaseQbox;
		this.palHeightQbox = palHeightQbox;
		this.ALength = ALength;
		this.AWidth = AWidth;
		this.AHeight = AHeight;
		this.AWeight = AWeight;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.operateDate = operateDate;
		this.enterpriseNo = enterpriseNo;
		this.operateType = operateType;
		this.articleIdentifier = articleIdentifier;
		this.lotType = lotType;
		this.scanFlag = scanFlag;
		this.rowId = rowId;
		this.supplierNo = supplierNo;
		this.temperatureFlag = temperatureFlag;
		this.packingUnit = packingUnit;
		this.packingSpec=packingSpec;
		this.rsvAttr1 = rsvAttr1;
		this.rsvAttr2 = rsvAttr2;
		this.rsvAttr3 = rsvAttr3;
		this.rsvAttr4 = rsvAttr4;
		this.rsvAttr5 = rsvAttr5;
		this.rsvAttr6 = rsvAttr6;
		this.rsvNum1 = rsvNum1;
		this.rsvNum2 = rsvNum2;
		this.rsvNum3 = rsvNum3;
		this.qminOperatePacking = qminOperatePacking;
		this.qminOperatePackingUnit = qminOperatePackingUnit;
		this.unitPacking = unitPacking;
		this.ruleFlag = ruleFlag;
	}

	// Property accessors

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "OWNER_ARTICLE_NO", length = 25)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "ARTICLE_NAME", nullable = false, length = 120)
	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	@Column(name = "ARTICLE_ALIAS", length = 60)
	public String getArticleAlias() {
		return this.articleAlias;
	}

	public void setArticleAlias(String articleAlias) {
		this.articleAlias = articleAlias;
	}

	@Column(name = "GROUP_NO", nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "GROUP_NAME", nullable = false, length = 45)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "UNIT", nullable = false, length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "SPEC", nullable = false, length = 50)
	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name = "PACKING_QTY",  precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "EXPIRY_DAYS", nullable = false, precision = 5, scale = 0)
	public Double getExpiryDays() {
		return this.expiryDays;
	}

	public void setExpiryDays(Double expiryDays) {
		this.expiryDays = expiryDays;
	}

	@Column(name = "UNIT_VOLUMN", nullable = false, precision = 18, scale = 5)
	public Double getUnitVolumn() {
		return this.unitVolumn;
	}

	public void setUnitVolumn(Double unitVolumn) {
		this.unitVolumn = unitVolumn;
	}

	@Column(name = "UNIT_WEIGHT", nullable = false, precision = 17, scale = 5)
	public Double getUnitWeight() {
		return this.unitWeight;
	}

	public void setUnitWeight(Double unitWeight) {
		this.unitWeight = unitWeight;
	}

	@Column(name = "BARCODE", length = 25)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "BOX_NO", length = 25)
	public String getBoxNo() {
		return this.boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	@Column(name = "PAL_BASE_QBOX", nullable = false, precision = 18, scale = 5)
	public Double getPalBaseQbox() {
		return this.palBaseQbox;
	}

	public void setPalBaseQbox(Double palBaseQbox) {
		this.palBaseQbox = palBaseQbox;
	}

	@Column(name = "PAL_HEIGHT_QBOX", nullable = false, precision = 18, scale = 5)
	public Double getPalHeightQbox() {
		return this.palHeightQbox;
	}

	public void setPalHeightQbox(Double palHeightQbox) {
		this.palHeightQbox = palHeightQbox;
	}

	@Column(name = "A_LENGTH", nullable = false, precision = 18, scale = 5)
	public Double getALength() {
		return this.ALength;
	}

	public void setALength(Double ALength) {
		this.ALength = ALength;
	}

	@Column(name = "A_WIDTH", nullable = false, precision = 18, scale = 5)
	public Double getAWidth() {
		return this.AWidth;
	}

	public void setAWidth(Double AWidth) {
		this.AWidth = AWidth;
	}

	@Column(name = "A_HEIGHT", nullable = false, precision = 18, scale = 5)
	public Double getAHeight() {
		return this.AHeight;
	}

	public void setAHeight(Double AHeight) {
		this.AHeight = AHeight;
	}

	@Column(name = "A_WEIGHT", nullable = false, precision = 18, scale = 5)
	public Double getAWeight() {
		return this.AWeight;
	}

	public void setAWeight(Double AWeight) {
		this.AWeight = AWeight;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 25)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "OPERATE_TYPE", length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "ARTICLE_IDENTIFIER", length = 50)
	public String getArticleIdentifier() {
		return this.articleIdentifier;
	}

	public void setArticleIdentifier(String articleIdentifier) {
		this.articleIdentifier = articleIdentifier;
	}

	@Column(name = "LOT_TYPE", nullable = false, length = 1)
	public String getLotType() {
		return this.lotType;
	}

	public void setLotType(String lotType) {
		this.lotType = lotType;
	}

	@Column(name = "SCAN_FLAG", nullable = false, length = 1)
	public String getScanFlag() {
		return this.scanFlag;
	}

	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "SUPPLIER_NO", length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "TEMPERATURE_FLAG", nullable = false, length = 1)
	public String getTemperatureFlag() {
		return this.temperatureFlag;
	}

	public void setTemperatureFlag(String temperatureFlag) {
		this.temperatureFlag = temperatureFlag;
	}
	
	@Column(name = "RULE_FLAG", nullable = false, length = 1)
	public String getRuleFlag() {
		return this.ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	@Column(name = "PACKING_UNIT", length = 20)
	public String getPackingUnit() {
		return this.packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	
	@Column(name = "PACKING_SPEC", length = 100)
	public String getPackingSpec() {
		return packingSpec;
	}

	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}

	@Column(name = "RSV_ATTR1", length = 50)
	public String getRsvAttr1() {
		return this.rsvAttr1;
	}

	public void setRsvAttr1(String rsvAttr1) {
		this.rsvAttr1 = rsvAttr1;
	}

	@Column(name = "RSV_ATTR2", length = 50)
	public String getRsvAttr2() {
		return this.rsvAttr2;
	}

	public void setRsvAttr2(String rsvAttr2) {
		this.rsvAttr2 = rsvAttr2;
	}

	@Column(name = "RSV_ATTR3", length = 50)
	public String getRsvAttr3() {
		return this.rsvAttr3;
	}

	public void setRsvAttr3(String rsvAttr3) {
		this.rsvAttr3 = rsvAttr3;
	}

	@Column(name = "RSV_ATTR4", length = 50)
	public String getRsvAttr4() {
		return this.rsvAttr4;
	}

	public void setRsvAttr4(String rsvAttr4) {
		this.rsvAttr4 = rsvAttr4;
	}

	@Column(name = "RSV_ATTR5", length = 50)
	public String getRsvAttr5() {
		return this.rsvAttr5;
	}

	public void setRsvAttr5(String rsvAttr5) {
		this.rsvAttr5 = rsvAttr5;
	}

	@Column(name = "RSV_ATTR6", length = 50)
	public String getRsvAttr6() {
		return this.rsvAttr6;
	}

	public void setRsvAttr6(String rsvAttr6) {
		this.rsvAttr6 = rsvAttr6;
	}

	@Column(name = "RSV_NUM1", precision = 22, scale = 0)
	public Double getRsvNum1() {
		return this.rsvNum1;
	}

	public void setRsvNum1(Double rsvNum1) {
		this.rsvNum1 = rsvNum1;
	}

	@Column(name = "RSV_NUM2", precision = 22, scale = 0)
	public Double getRsvNum2() {
		return this.rsvNum2;
	}

	public void setRsvNum2(Double rsvNum2) {
		this.rsvNum2 = rsvNum2;
	}

	@Column(name = "RSV_NUM3", precision = 22, scale = 0)
	public Double getRsvNum3() {
		return this.rsvNum3;
	}

	public void setRsvNum3(Double rsvNum3) {
		this.rsvNum3 = rsvNum3;
	}

	
	
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}

	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}

	public String getQminOperatePackingUnit() {
		return qminOperatePackingUnit;
	}

	public void setQminOperatePackingUnit(String qminOperatePackingUnit) {
		this.qminOperatePackingUnit = qminOperatePackingUnit;
	}

	public Double getUnitPacking() {
		return unitPacking;
	}

	public void setUnitPacking(Double unitPacking) {
		this.unitPacking = unitPacking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AHeight == null) ? 0 : AHeight.hashCode());
		result = prime * result + ((ALength == null) ? 0 : ALength.hashCode());
		result = prime * result + ((AWeight == null) ? 0 : AWeight.hashCode());
		result = prime * result + ((AWidth == null) ? 0 : AWidth.hashCode());
		result = prime * result
				+ ((articleAlias == null) ? 0 : articleAlias.hashCode());
		result = prime
				* result
				+ ((articleIdentifier == null) ? 0 : articleIdentifier
						.hashCode());
		result = prime * result
				+ ((articleName == null) ? 0 : articleName.hashCode());
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((boxNo == null) ? 0 : boxNo.hashCode());
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result
				+ ((expiryDays == null) ? 0 : expiryDays.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((groupNo == null) ? 0 : groupNo.hashCode());
		result = prime * result + ((lotType == null) ? 0 : lotType.hashCode());
		result = prime * result
				+ ((operateDate == null) ? 0 : operateDate.hashCode());
		result = prime * result
				+ ((operateType == null) ? 0 : operateType.hashCode());
		result = prime * result
				+ ((ownerArticleNo == null) ? 0 : ownerArticleNo.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((packingQty == null) ? 0 : packingQty.hashCode());
		result = prime * result
				+ ((packingSpec == null) ? 0 : packingSpec.hashCode());
		result = prime * result
				+ ((packingUnit == null) ? 0 : packingUnit.hashCode());
		result = prime * result
				+ ((palBaseQbox == null) ? 0 : palBaseQbox.hashCode());
		result = prime * result
				+ ((palHeightQbox == null) ? 0 : palHeightQbox.hashCode());
		result = prime * result
				+ ((rgstDate == null) ? 0 : rgstDate.hashCode());
		result = prime * result
				+ ((rgstName == null) ? 0 : rgstName.hashCode());
		result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
		result = prime * result
				+ ((rsvAttr1 == null) ? 0 : rsvAttr1.hashCode());
		result = prime * result
				+ ((rsvAttr2 == null) ? 0 : rsvAttr2.hashCode());
		result = prime * result
				+ ((rsvAttr3 == null) ? 0 : rsvAttr3.hashCode());
		result = prime * result
				+ ((rsvAttr4 == null) ? 0 : rsvAttr4.hashCode());
		result = prime * result
				+ ((rsvAttr5 == null) ? 0 : rsvAttr5.hashCode());
		result = prime * result
				+ ((rsvAttr6 == null) ? 0 : rsvAttr6.hashCode());
		result = prime * result + ((rsvNum1 == null) ? 0 : rsvNum1.hashCode());
		result = prime * result + ((rsvNum2 == null) ? 0 : rsvNum2.hashCode());
		result = prime * result + ((rsvNum3 == null) ? 0 : rsvNum3.hashCode());
		result = prime * result
				+ ((scanFlag == null) ? 0 : scanFlag.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((supplierNo == null) ? 0 : supplierNo.hashCode());
		result = prime * result
				+ ((temperatureFlag == null) ? 0 : temperatureFlag.hashCode());
		result = prime * result
				+ ((ruleFlag == null) ? 0 : ruleFlag.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result
				+ ((unitVolumn == null) ? 0 : unitVolumn.hashCode());
		result = prime * result
				+ ((unitWeight == null) ? 0 : unitWeight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tmp_Formexcel_ArticleId other = (Tmp_Formexcel_ArticleId) obj;
		if (AHeight == null) {
			if (other.AHeight != null)
				return false;
		} else if (!AHeight.equals(other.AHeight))
			return false;
		if (ALength == null) {
			if (other.ALength != null)
				return false;
		} else if (!ALength.equals(other.ALength))
			return false;
		if (AWeight == null) {
			if (other.AWeight != null)
				return false;
		} else if (!AWeight.equals(other.AWeight))
			return false;
		if (AWidth == null) {
			if (other.AWidth != null)
				return false;
		} else if (!AWidth.equals(other.AWidth))
			return false;
		if (articleAlias == null) {
			if (other.articleAlias != null)
				return false;
		} else if (!articleAlias.equals(other.articleAlias))
			return false;
		if (articleIdentifier == null) {
			if (other.articleIdentifier != null)
				return false;
		} else if (!articleIdentifier.equals(other.articleIdentifier))
			return false;
		if (articleName == null) {
			if (other.articleName != null)
				return false;
		} else if (!articleName.equals(other.articleName))
			return false;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (boxNo == null) {
			if (other.boxNo != null)
				return false;
		} else if (!boxNo.equals(other.boxNo))
			return false;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (expiryDays == null) {
			if (other.expiryDays != null)
				return false;
		} else if (!expiryDays.equals(other.expiryDays))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (groupNo == null) {
			if (other.groupNo != null)
				return false;
		} else if (!groupNo.equals(other.groupNo))
			return false;
		if (lotType == null) {
			if (other.lotType != null)
				return false;
		} else if (!lotType.equals(other.lotType))
			return false;
		if (operateDate == null) {
			if (other.operateDate != null)
				return false;
		} else if (!operateDate.equals(other.operateDate))
			return false;
		if (operateType == null) {
			if (other.operateType != null)
				return false;
		} else if (!operateType.equals(other.operateType))
			return false;
		if (ownerArticleNo == null) {
			if (other.ownerArticleNo != null)
				return false;
		} else if (!ownerArticleNo.equals(other.ownerArticleNo))
			return false;
		if (ownerNo == null) {
			if (other.ownerNo != null)
				return false;
		} else if (!ownerNo.equals(other.ownerNo))
			return false;
		if (packingQty == null) {
			if (other.packingQty != null)
				return false;
		} else if (!packingQty.equals(other.packingQty))
			return false;
		if (packingSpec == null) {
			if (other.packingSpec != null)
				return false;
		} else if (!packingSpec.equals(other.packingSpec))
			return false;
		if (packingUnit == null) {
			if (other.packingUnit != null)
				return false;
		} else if (!packingUnit.equals(other.packingUnit))
			return false;
		if (palBaseQbox == null) {
			if (other.palBaseQbox != null)
				return false;
		} else if (!palBaseQbox.equals(other.palBaseQbox))
			return false;
		if (palHeightQbox == null) {
			if (other.palHeightQbox != null)
				return false;
		} else if (!palHeightQbox.equals(other.palHeightQbox))
			return false;
		if (rgstDate == null) {
			if (other.rgstDate != null)
				return false;
		} else if (!rgstDate.equals(other.rgstDate))
			return false;
		if (rgstName == null) {
			if (other.rgstName != null)
				return false;
		} else if (!rgstName.equals(other.rgstName))
			return false;
		if (rowId == null) {
			if (other.rowId != null)
				return false;
		} else if (!rowId.equals(other.rowId))
			return false;
		if (rsvAttr1 == null) {
			if (other.rsvAttr1 != null)
				return false;
		} else if (!rsvAttr1.equals(other.rsvAttr1))
			return false;
		if (rsvAttr2 == null) {
			if (other.rsvAttr2 != null)
				return false;
		} else if (!rsvAttr2.equals(other.rsvAttr2))
			return false;
		if (rsvAttr3 == null) {
			if (other.rsvAttr3 != null)
				return false;
		} else if (!rsvAttr3.equals(other.rsvAttr3))
			return false;
		if (rsvAttr4 == null) {
			if (other.rsvAttr4 != null)
				return false;
		} else if (!rsvAttr4.equals(other.rsvAttr4))
			return false;
		if (rsvAttr5 == null) {
			if (other.rsvAttr5 != null)
				return false;
		} else if (!rsvAttr5.equals(other.rsvAttr5))
			return false;
		if (rsvAttr6 == null) {
			if (other.rsvAttr6 != null)
				return false;
		} else if (!rsvAttr6.equals(other.rsvAttr6))
			return false;
		if (rsvNum1 == null) {
			if (other.rsvNum1 != null)
				return false;
		} else if (!rsvNum1.equals(other.rsvNum1))
			return false;
		if (rsvNum2 == null) {
			if (other.rsvNum2 != null)
				return false;
		} else if (!rsvNum2.equals(other.rsvNum2))
			return false;
		if (rsvNum3 == null) {
			if (other.rsvNum3 != null)
				return false;
		} else if (!rsvNum3.equals(other.rsvNum3))
			return false;
		if (scanFlag == null) {
			if (other.scanFlag != null)
				return false;
		} else if (!scanFlag.equals(other.scanFlag))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (supplierNo == null) {
			if (other.supplierNo != null)
				return false;
		} else if (!supplierNo.equals(other.supplierNo))
			return false;
		if (temperatureFlag == null) {
			if (other.temperatureFlag != null)
				return false;
		} else if (!temperatureFlag.equals(other.temperatureFlag))
			return false;
		if (ruleFlag == null) {
			if (other.ruleFlag != null)
				return false;
		} else if (!ruleFlag.equals(other.ruleFlag))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (unitVolumn == null) {
			if (other.unitVolumn != null)
				return false;
		} else if (!unitVolumn.equals(other.unitVolumn))
			return false;
		if (unitWeight == null) {
			if (other.unitWeight != null)
				return false;
		} else if (!unitWeight.equals(other.unitWeight))
			return false;
		return true;
	}

	

}