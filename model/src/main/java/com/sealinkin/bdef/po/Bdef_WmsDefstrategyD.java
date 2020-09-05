package com.sealinkin.bdef.po;

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
 * WmsDefstrategyD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFSTRATEGY_D")
public class Bdef_WmsDefstrategyD implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bdef_WmsDefstrategyDId id;
	private Double ruleId;
	private String limmitMaxqty;
	private String limmitMixbatch;
	private String limmitMixarticle;
	private String limmitMaxcase;
	private String limmitCelluse;
	private String limmitMaxweight;
	private String limmitMaxgroupno;
	private String limmitRsv01;
	private String limmitRsv02;
	private String limmitRsv03;
	private String limmitRsv04;
	private String limmitRsv05;
	private String limmitRsv06;
	private String rgstName;
	private Date rgstDate;
	private String memo;

	// Constructors

	/** default constructor */
	public Bdef_WmsDefstrategyD() {
	}

	/** minimal constructor */
	public Bdef_WmsDefstrategyD(Bdef_WmsDefstrategyDId id, Double ruleId,
			String limmitMaxqty, String limmitMixbatch,
			String limmitMixarticle, String limmitMaxcase,
			String limmitCelluse, String limmitMaxweight,
			String limmitMaxgroupno, String limmitRsv01, String limmitRsv02,
			String limmitRsv03, String limmitRsv04, String limmitRsv05,
			String limmitRsv06, String rgstName, Date rgstDate) {
		this.id = id;
		this.ruleId = ruleId;
		this.limmitMaxqty = limmitMaxqty;
		this.limmitMixbatch = limmitMixbatch;
		this.limmitMixarticle = limmitMixarticle;
		this.limmitMaxcase = limmitMaxcase;
		this.limmitCelluse = limmitCelluse;
		this.limmitMaxweight = limmitMaxweight;
		this.limmitMaxgroupno = limmitMaxgroupno;
		this.limmitRsv01 = limmitRsv01;
		this.limmitRsv02 = limmitRsv02;
		this.limmitRsv03 = limmitRsv03;
		this.limmitRsv04 = limmitRsv04;
		this.limmitRsv05 = limmitRsv05;
		this.limmitRsv06 = limmitRsv06;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_WmsDefstrategyD(Bdef_WmsDefstrategyDId id, Double ruleId,
			String limmitMaxqty, String limmitMixbatch,
			String limmitMixarticle, String limmitMaxcase,
			String limmitCelluse, String limmitMaxweight,
			String limmitMaxgroupno, String limmitRsv01, String limmitRsv02,
			String limmitRsv03, String limmitRsv04, String limmitRsv05,
			String limmitRsv06, String rgstName, Date rgstDate, String memo) {
		this.id = id;
		this.ruleId = ruleId;
		this.limmitMaxqty = limmitMaxqty;
		this.limmitMixbatch = limmitMixbatch;
		this.limmitMixarticle = limmitMixarticle;
		this.limmitMaxcase = limmitMaxcase;
		this.limmitCelluse = limmitCelluse;
		this.limmitMaxweight = limmitMaxweight;
		this.limmitMaxgroupno = limmitMaxgroupno;
		this.limmitRsv01 = limmitRsv01;
		this.limmitRsv02 = limmitRsv02;
		this.limmitRsv03 = limmitRsv03;
		this.limmitRsv04 = limmitRsv04;
		this.limmitRsv05 = limmitRsv05;
		this.limmitRsv06 = limmitRsv06;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.memo = memo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "strategyType", column = @Column(name = "STRATEGY_TYPE", nullable = false, length = 2)),
			@AttributeOverride(name = "strategyId", column = @Column(name = "STRATEGY_ID", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "ruleOrder", column = @Column(name = "RULE_ORDER", nullable = false, precision = 3, scale = 0)) })
	public Bdef_WmsDefstrategyDId getId() {
		return this.id;
	}

	public void setId(Bdef_WmsDefstrategyDId id) {
		this.id = id;
	}

	@Column(name = "RULE_ID", nullable = false, precision = 3, scale = 0)
	public Double getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Double ruleId) {
		this.ruleId = ruleId;
	}

	@Column(name = "LIMMIT_MAXQTY", nullable = false, length = 1)
	public String getLimmitMaxqty() {
		return this.limmitMaxqty;
	}

	public void setLimmitMaxqty(String limmitMaxqty) {
		this.limmitMaxqty = limmitMaxqty;
	}

	@Column(name = "LIMMIT_MIXBATCH", nullable = false, length = 1)
	public String getLimmitMixbatch() {
		return this.limmitMixbatch;
	}

	public void setLimmitMixbatch(String limmitMixbatch) {
		this.limmitMixbatch = limmitMixbatch;
	}

	@Column(name = "LIMMIT_MIXARTICLE", nullable = false, length = 1)
	public String getLimmitMixarticle() {
		return this.limmitMixarticle;
	}

	public void setLimmitMixarticle(String limmitMixarticle) {
		this.limmitMixarticle = limmitMixarticle;
	}

	@Column(name = "LIMMIT_MAXCASE", nullable = false, length = 1)
	public String getLimmitMaxcase() {
		return this.limmitMaxcase;
	}

	public void setLimmitMaxcase(String limmitMaxcase) {
		this.limmitMaxcase = limmitMaxcase;
	}

	@Column(name = "LIMMIT_CELLUSE", nullable = false, length = 1)
	public String getLimmitCelluse() {
		return this.limmitCelluse;
	}

	public void setLimmitCelluse(String limmitCelluse) {
		this.limmitCelluse = limmitCelluse;
	}

	@Column(name = "LIMMIT_MAXWEIGHT", nullable = false, length = 1)
	public String getLimmitMaxweight() {
		return this.limmitMaxweight;
	}

	public void setLimmitMaxweight(String limmitMaxweight) {
		this.limmitMaxweight = limmitMaxweight;
	}

	@Column(name = "LIMMIT_MAXGROUPNO", nullable = false, length = 1)
	public String getLimmitMaxgroupno() {
		return this.limmitMaxgroupno;
	}

	public void setLimmitMaxgroupno(String limmitMaxgroupno) {
		this.limmitMaxgroupno = limmitMaxgroupno;
	}

	@Column(name = "LIMMIT_RSV01", nullable = false, length = 1)
	public String getLimmitRsv01() {
		return this.limmitRsv01;
	}

	public void setLimmitRsv01(String limmitRsv01) {
		this.limmitRsv01 = limmitRsv01;
	}

	@Column(name = "LIMMIT_RSV02", nullable = false, length = 1)
	public String getLimmitRsv02() {
		return this.limmitRsv02;
	}

	public void setLimmitRsv02(String limmitRsv02) {
		this.limmitRsv02 = limmitRsv02;
	}

	@Column(name = "LIMMIT_RSV03", nullable = false, length = 1)
	public String getLimmitRsv03() {
		return this.limmitRsv03;
	}

	public void setLimmitRsv03(String limmitRsv03) {
		this.limmitRsv03 = limmitRsv03;
	}

	@Column(name = "LIMMIT_RSV04", nullable = false, length = 1)
	public String getLimmitRsv04() {
		return this.limmitRsv04;
	}

	public void setLimmitRsv04(String limmitRsv04) {
		this.limmitRsv04 = limmitRsv04;
	}

	@Column(name = "LIMMIT_RSV05", nullable = false, length = 1)
	public String getLimmitRsv05() {
		return this.limmitRsv05;
	}

	public void setLimmitRsv05(String limmitRsv05) {
		this.limmitRsv05 = limmitRsv05;
	}

	@Column(name = "LIMMIT_RSV06", nullable = false, length = 1)
	public String getLimmitRsv06() {
		return this.limmitRsv06;
	}

	public void setLimmitRsv06(String limmitRsv06) {
		this.limmitRsv06 = limmitRsv06;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "MEMO", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}