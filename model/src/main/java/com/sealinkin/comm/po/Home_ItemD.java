package com.sealinkin.comm.po;

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
 * HomeItemD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HOME_ITEM_D")
public class Home_ItemD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Home_ItemDId id;
	private String subItemName;
	private String totalSql;
	private String detailSql;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private int seq;

	// Constructors

	/** default constructor */
	public Home_ItemD() {
	}

	/** minimal constructor */
	public Home_ItemD(Home_ItemDId id, String subItemName, int seq) {
		this.id = id;
		this.subItemName = subItemName;
		this.seq = seq;
	}

	/** full constructor */
	public Home_ItemD(Home_ItemDId id, String subItemName, String totalSql,
			String detailSql, String status, String rgstName, Date rgstDate,
			String updtName, Date updtDate, int seq) {
		this.id = id;
		this.subItemName = subItemName;
		this.totalSql = totalSql;
		this.detailSql = detailSql;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.seq = seq;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "itemId", column = @Column(name = "ITEM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "subItemId", column = @Column(name = "SUB_ITEM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Home_ItemDId getId() {
		return this.id;
	}

	public void setId(Home_ItemDId id) {
		this.id = id;
	}

	@Column(name = "SUB_ITEM_NAME", nullable = false, length = 50)
	public String getSubItemName() {
		return this.subItemName;
	}

	public void setSubItemName(String subItemName) {
		this.subItemName = subItemName;
	}

	@Column(name = "TOTAL_SQL", length = 4000)
	public String getTotalSql() {
		return this.totalSql;
	}

	public void setTotalSql(String totalSql) {
		this.totalSql = totalSql;
	}

	@Column(name = "DETAIL_SQL", length = 4000)
	public String getDetailSql() {
		return this.detailSql;
	}

	public void setDetailSql(String detailSql) {
		this.detailSql = detailSql;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", length = 7)
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

	@Column(name = "SEQ", nullable = false, precision = 22, scale = 0)
	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}