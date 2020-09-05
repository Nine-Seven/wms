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
 * Bdef_ArticleFamilyM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_ARTICLE_FAMILY_M")
public class Bdef_ArticleFamilyM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Bdef_ArticleFamilyMId id;
	private String familyName;
	private String rgstName;
	private Date rgstDate;

	// Constructors

	/** default constructor */
	public Bdef_ArticleFamilyM() {
	}

	/** minimal constructor */
	public Bdef_ArticleFamilyM(Bdef_ArticleFamilyMId id) {
		this.id = id;
	}

	/** full constructor */
	public Bdef_ArticleFamilyM(Bdef_ArticleFamilyMId id, String familyName,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.familyName = familyName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "familyNo", column = @Column(name = "FAMILY_NO", nullable = false, length = 20)) })
	public Bdef_ArticleFamilyMId getId() {
		return this.id;
	}

	public void setId(Bdef_ArticleFamilyMId id) {
		this.id = id;
	}

	@Column(name = "FAMILY_NAME", length = 45)
	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
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

}