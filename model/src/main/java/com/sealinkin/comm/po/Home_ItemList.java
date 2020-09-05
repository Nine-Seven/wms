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
 * HomeItemList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HOME_ITEM_LIST")
public class Home_ItemList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Home_ItemListId id;
	private String fieldName;
	private int seq;
	private int width;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String fieldType;

	// Constructors

	/** default constructor */
	public Home_ItemList() {
	}

	/** minimal constructor */
	public Home_ItemList(Home_ItemListId id, String fieldName) {
		this.id = id;
		this.fieldName = fieldName;
	}

	/** full constructor */
	public Home_ItemList(Home_ItemListId id, String fieldName, int seq,
			int width, String status, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String fieldType) {
		this.id = id;
		this.fieldName = fieldName;
		this.seq = seq;
		this.width = width;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.fieldType = fieldType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "fieldId", column = @Column(name = "FIELD_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "subItemId", column = @Column(name = "SUB_ITEM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Home_ItemListId getId() {
		return this.id;
	}

	public void setId(Home_ItemListId id) {
		this.id = id;
	}

	@Column(name = "FIELD_NAME", nullable = false, length = 30)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "SEQ", precision = 22, scale = 0)
	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Column(name = "WIDTH", precision = 22, scale = 0)
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
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

	@Column(name = "FIELD_TYPE", length = 10)
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

}