package com.sealinkin.bset.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bset_MenuFolder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_MENU_FOLDER")
public class Bset_MenuFolder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Bset_MenuFolderId id;
	private String menuCaption;
	private String parentfolderid;
	private String orderNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
    private String levelType;
	// Constructors

	/** default constructor */
	public Bset_MenuFolder() {
	}

	/** minimal constructor */
	public Bset_MenuFolder(Bset_MenuFolderId id, String orderNo) {
		this.id = id;
		this.orderNo = orderNo;
	}

	/** full constructor */
	public Bset_MenuFolder(Bset_MenuFolderId id, String menuCaption,
			String parentfolderid, String orderNo, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.menuCaption = menuCaption;
		this.parentfolderid = parentfolderid;
		this.orderNo = orderNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "menugroupId", column = @Column(name = "MENUGROUP_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "folderId", column = @Column(name = "FOLDER_ID", nullable = false, length = 24)) })
	public Bset_MenuFolderId getId() {
		return this.id;
	}

	public void setId(Bset_MenuFolderId id) {
		this.id = id;
	}

	@Column(name = "MENU_CAPTION", length = 64)
	public String getMenuCaption() {
		return this.menuCaption;
	}

	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}

	@Column(name = "PARENTFOLDERID", length = 24)
	public String getParentfolderid() {
		return this.parentfolderid;
	}

	public void setParentfolderid(String parentfolderid) {
		this.parentfolderid = parentfolderid;
	}

	@Column(name = "ORDER_NO", nullable = false, length = 24)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

}