package com.sealinkin.bdef.po;
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
 * BdefArticlePacking entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_ARTICLE_PACKING")
public class Bdef_ArticlePacking implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_ArticlePackingId id;
	private String packingUnit;
	private String spec;
	private Double mpackingQty;
	private String mpackingUnit;
	private Double ALength;
	private Double AWidth;
	private Double AHeight;
	private Double packingWeight;
	private Double palBaseQbox;
	private Double palHeightQbox;
	private Boolean sorterFlag;
	private String ruleFlag;
	private Double qpalette;
	private String createFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String rsvPking1;
	private String rsvPking2;
	private String rsvPking3;
	private String rsvPking4;
	private String rsvPking5;

	// Constructors

	/** default constructor */
	public Bdef_ArticlePacking() {
	}

	/** minimal constructor */
	public Bdef_ArticlePacking(Bdef_ArticlePackingId id, String packingUnit,
			Double ALength, Double AWidth, Double AHeight, String ruleFlag,
			Double qpalette, String createFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.packingUnit = packingUnit;
		this.ALength = ALength;
		this.AWidth = AWidth;
		this.AHeight = AHeight;
		this.ruleFlag = ruleFlag;
		this.qpalette = qpalette;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_ArticlePacking(Bdef_ArticlePackingId id, String packingUnit,
			String spec, Double mpackingQty, String mpackingUnit,
			Double ALength, Double AWidth, Double AHeight,
			Double packingWeight, Double palBaseQbox, Double palHeightQbox,
			Boolean sorterFlag, String ruleFlag, Double qpalette,
			String createFlag, String rgstName, Date rgstDate, String updtName,
			Date updtDate,String rsvPking1,String rsvPking2,String rsvPking3,String rsvPking4,String rsvPking5) {
		this.id = id;
		this.packingUnit = packingUnit;
		this.spec = spec;
		this.mpackingQty = mpackingQty;
		this.mpackingUnit = mpackingUnit;
		this.ALength = ALength;
		this.AWidth = AWidth;
		this.AHeight = AHeight;
		this.packingWeight = packingWeight;
		this.palBaseQbox = palBaseQbox;
		this.palHeightQbox = palHeightQbox;
		this.sorterFlag = sorterFlag;
		this.ruleFlag = ruleFlag;
		this.qpalette = qpalette;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.rsvPking1 = rsvPking1;
		this.rsvPking2 = rsvPking2;
		this.rsvPking3 = rsvPking3;
		this.rsvPking4 = rsvPking4;
		this.rsvPking5 = rsvPking5;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)) })
	public Bdef_ArticlePackingId getId() {
		return this.id;
	}

	public void setId(Bdef_ArticlePackingId id) {
		this.id = id;
	}

	@Column(name = "PACKING_UNIT", nullable = false, length = 20)
	public String getPackingUnit() {
		return this.packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	@Column(name = "SPEC", length = 100)
	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name = "MPACKING_QTY", precision = 18, scale = 5)
	public Double getMpackingQty() {
		return this.mpackingQty;
	}

	public void setMpackingQty(Double mpackingQty) {
		this.mpackingQty = mpackingQty;
	}

	@Column(name = "MPACKING_UNIT", length = 20)
	public String getMpackingUnit() {
		return this.mpackingUnit;
	}

	public void setMpackingUnit(String mpackingUnit) {
		this.mpackingUnit = mpackingUnit;
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

	@Column(name = "PACKING_WEIGHT", precision = 12, scale = 5)
	public Double getPackingWeight() {
		return this.packingWeight;
	}

	public void setPackingWeight(Double packingWeight) {
		this.packingWeight = packingWeight;
	}

	@Column(name = "PAL_BASE_QBOX", precision = 18, scale = 5)
	public Double getPalBaseQbox() {
		return this.palBaseQbox;
	}

	public void setPalBaseQbox(Double palBaseQbox) {
		this.palBaseQbox = palBaseQbox;
	}

	@Column(name = "PAL_HEIGHT_QBOX", precision = 18, scale = 5)
	public Double getPalHeightQbox() {
		return this.palHeightQbox;
	}

	public void setPalHeightQbox(Double palHeightQbox) {
		this.palHeightQbox = palHeightQbox;
	}

	@Column(name = "SORTER_FLAG", precision = 1, scale = 0)
	public Boolean getSorterFlag() {
		return this.sorterFlag;
	}

	public void setSorterFlag(Boolean sorterFlag) {
		this.sorterFlag = sorterFlag;
	}

	@Column(name = "RULE_FLAG", nullable = false, length = 1)
	public String getRuleFlag() {
		return this.ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	@Column(name = "QPALETTE", nullable = false, precision = 18, scale = 5)
	public Double getQpalette() {
		return this.qpalette;
	}

	public void setQpalette(Double qpalette) {
		this.qpalette = qpalette;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
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

	@Column(name = "RSV_PKING1", length = 50)
	public String getRsvPking1() {
		return rsvPking1;
	}

	public void setRsvPking1(String rsvPking1) {
		this.rsvPking1 = rsvPking1;
	}

	@Column(name = "RSV_PKING2", length = 50)
	public String getRsvPking2() {
		return rsvPking2;
	}

	public void setRsvPking2(String rsvPking2) {
		this.rsvPking2 = rsvPking2;
	}

	@Column(name = "RSV_PKING3", length = 50)
	public String getRsvPking3() {
		return rsvPking3;
	}

	public void setRsvPking3(String rsvPking3) {
		this.rsvPking3 = rsvPking3;
	}

	@Column(name = "RSV_PKING4", length = 50)
	public String getRsvPking4() {
		return rsvPking4;
	}

	public void setRsvPking4(String rsvPking4) {
		this.rsvPking4 = rsvPking4;
	}

	@Column(name = "RSV_PKING5", length = 50)
	public String getRsvPking5() {
		return rsvPking5;
	}

	public void setRsvPking5(String rsvPking5) {
		this.rsvPking5 = rsvPking5;
	}

}