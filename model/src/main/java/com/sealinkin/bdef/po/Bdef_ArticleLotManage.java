package com.sealinkin.bdef.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bdef_ArticleLotManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_ARTICLE_LOT_MANAGE")
public class Bdef_ArticleLotManage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Bdef_ArticleLotManageId id;

	// Constructors

	/** default constructor */
	public Bdef_ArticleLotManage() {
	}

	/** full constructor */
	public Bdef_ArticleLotManage(Bdef_ArticleLotManageId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", length = 15)),
			@AttributeOverride(name = "lotNo", column = @Column(name = "LOT_NO", length = 20)),
			@AttributeOverride(name = "produceDate", column = @Column(name = "PRODUCE_DATE", length = 7)),
			@AttributeOverride(name = "expireDate", column = @Column(name = "EXPIRE_DATE", length = 7)),
			@AttributeOverride(name = "expiryDays", column = @Column(name = "EXPIRY_DAYS", precision = 5, scale = 0)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", length = 7)),
			@AttributeOverride(name = "price", column = @Column(name = "PRICE", scale = 5)) })
	public Bdef_ArticleLotManageId getId() {
		return this.id;
	}

	public void setId(Bdef_ArticleLotManageId id) {
		this.id = id;
	}

}