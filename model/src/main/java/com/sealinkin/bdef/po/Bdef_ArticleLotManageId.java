package com.sealinkin.bdef.po;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bdef_ArticleLotManageId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_ArticleLotManageId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String articleNo;
	private String lotNo;
	private Date produceDate;
	private Date expireDate;
	private Integer expiryDays;
	private String rgstName;
	private Date rgstDate;
	private double price;

	// Constructors

	/** default constructor */
	public Bdef_ArticleLotManageId() {
	}

	/** full constructor */
	public Bdef_ArticleLotManageId(String articleNo, String lotNo,
			Date produceDate, Date expireDate, Integer expiryDays,
			String rgstName, Date rgstDate, double price) {
		this.articleNo = articleNo;
		this.lotNo = lotNo;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.expiryDays = expiryDays;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.price = price;
	}

	// Property accessors

	@Column(name = "ARTICLE_NO", length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "LOT_NO", length = 20)
	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	@Column(name = "PRODUCE_DATE", length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	@Column(name = "EXPIRE_DATE", length = 7)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "EXPIRY_DAYS", precision = 5, scale = 0)
	public Integer getExpiryDays() {
		return this.expiryDays;
	}

	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
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

	@Column(name = "PRICE", scale = 5)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_ArticleLotManageId))
			return false;
		Bdef_ArticleLotManageId castOther = (Bdef_ArticleLotManageId) other;

		return ((this.getArticleNo() == castOther.getArticleNo()) || (this
				.getArticleNo() != null && castOther.getArticleNo() != null && this
				.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getLotNo() == castOther.getLotNo()) || (this
						.getLotNo() != null && castOther.getLotNo() != null && this
						.getLotNo().equals(castOther.getLotNo())))
				&& ((this.getProduceDate() == castOther.getProduceDate()) || (this
						.getProduceDate() != null
						&& castOther.getProduceDate() != null && this
						.getProduceDate().equals(castOther.getProduceDate())))
				&& ((this.getExpireDate() == castOther.getExpireDate()) || (this
						.getExpireDate() != null
						&& castOther.getExpireDate() != null && this
						.getExpireDate().equals(castOther.getExpireDate())))
				&& ((this.getExpiryDays() == castOther.getExpiryDays()) || (this
						.getExpiryDays() != null
						&& castOther.getExpiryDays() != null && this
						.getExpiryDays().equals(castOther.getExpiryDays())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& (this.getPrice() == castOther.getPrice());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37 * result
				+ (getLotNo() == null ? 0 : this.getLotNo().hashCode());
		result = 37
				* result
				+ (getProduceDate() == null ? 0 : this.getProduceDate()
						.hashCode());
		result = 37
				* result
				+ (getExpireDate() == null ? 0 : this.getExpireDate()
						.hashCode());
		result = 37
				* result
				+ (getExpiryDays() == null ? 0 : this.getExpiryDays()
						.hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result + (int) this.getPrice();
		return result;
	}

}