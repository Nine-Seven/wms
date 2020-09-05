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
 * BdefDefcartype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFCARTYPE")
public class Bdef_Defcartype implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bdef_DefcartypeId id;
	private String cartypeName;
	private Double cartypeWeight;
	private Double cartypeLength;
	private Double cartypeWidth;
	private Double cartypeHeight;
	private Double maxLayer;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bdef_Defcartype() {
	}

	/** minimal constructor */
	public Bdef_Defcartype(Bdef_DefcartypeId id) {
		this.id = id;
	}

	/** full constructor */
	public Bdef_Defcartype(Bdef_DefcartypeId id, String cartypeName,
			Double cartypeWeight, Double cartypeLength, Double cartypeWidth,
			Double cartypeHeight, Double maxLayer, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.cartypeName = cartypeName;
		this.cartypeWeight = cartypeWeight;
		this.cartypeLength = cartypeLength;
		this.cartypeWidth = cartypeWidth;
		this.cartypeHeight = cartypeHeight;
		this.maxLayer = maxLayer;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cartypeNo", column = @Column(name = "CARTYPE_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Bdef_DefcartypeId getId() {
		return this.id;
	}

	public void setId(Bdef_DefcartypeId id) {
		this.id = id;
	}

	@Column(name = "CARTYPE_NAME", length = 50)
	public String getCartypeName() {
		return this.cartypeName;
	}

	public void setCartypeName(String cartypeName) {
		this.cartypeName = cartypeName;
	}

	@Column(name = "CARTYPE_WEIGHT", precision = 13, scale = 5)
	public Double getCartypeWeight() {
		return this.cartypeWeight;
	}

	public void setCartypeWeight(Double cartypeWeight) {
		this.cartypeWeight = cartypeWeight;
	}

	@Column(name = "CARTYPE_LENGTH", precision = 10, scale = 4)
	public Double getCartypeLength() {
		return this.cartypeLength;
	}

	public void setCartypeLength(Double cartypeLength) {
		this.cartypeLength = cartypeLength;
	}

	@Column(name = "CARTYPE_WIDTH", precision = 10, scale = 4)
	public Double getCartypeWidth() {
		return this.cartypeWidth;
	}

	public void setCartypeWidth(Double cartypeWidth) {
		this.cartypeWidth = cartypeWidth;
	}

	@Column(name = "CARTYPE_HEIGHT", precision = 10, scale = 4)
	public Double getCartypeHeight() {
		return this.cartypeHeight;
	}

	public void setCartypeHeight(Double cartypeHeight) {
		this.cartypeHeight = cartypeHeight;
	}

	@Column(name = "MAX_LAYER", precision = 10, scale = 4)
	public Double getMaxLayer() {
		return this.maxLayer;
	}

	public void setMaxLayer(Double maxLayer) {
		this.maxLayer = maxLayer;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}