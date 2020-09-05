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
 * HomeItemM entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "HOME_ITEM_M")
public class Home_ItemM implements java.io.Serializable {

	// Fields

	private Home_ItemMId id;
	private String itemName;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String itemSql;

	// Constructors

	/** default constructor */
	public Home_ItemM() {
	}

	/** minimal constructor */
	public Home_ItemM(Home_ItemMId id, String itemName, String itemSql) {
		this.id = id;
		this.itemName = itemName;
		this.itemSql = itemSql;
	}

	/** full constructor */
	public Home_ItemM(Home_ItemMId id, String itemName, String status,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String itemSql) {
		this.id = id;
		this.itemName = itemName;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.itemSql = itemSql;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "itemId", column = @Column(name = "ITEM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Home_ItemMId getId() {
		return this.id;
	}

	public void setId(Home_ItemMId id) {
		this.id = id;
	}

	@Column(name = "ITEM_NAME", nullable = false, length = 50)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	@Column(name = "ITEM_SQL", nullable = false, length = 4000)
	public String getItemSql() {
		return this.itemSql;
	}

	public void setItemSql(String itemSql) {
		this.itemSql = itemSql;
	}

}