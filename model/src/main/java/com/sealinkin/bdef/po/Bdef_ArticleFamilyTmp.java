package com.sealinkin.bdef.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BdefArticleFamilyTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_ARTICLE_FAMILY_TMP")
public class Bdef_ArticleFamilyTmp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bdef_ArticleFamilyTmpId id;

	// Constructors

	/** default constructor */
	public Bdef_ArticleFamilyTmp() {
	}

	/** full constructor */
	public Bdef_ArticleFamilyTmp(Bdef_ArticleFamilyTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "familyNo", column = @Column(name = "FAMILY_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "familyName", column = @Column(name = "FAMILY_NAME", length = 45)),
			@AttributeOverride(name = "ownerArticleNo", column = @Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "useType", column = @Column(name = "USE_TYPE", length = 1)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)) })
	public Bdef_ArticleFamilyTmpId getId() {
		return this.id;
	}

	public void setId(Bdef_ArticleFamilyTmpId id) {
		this.id = id;
	}

}