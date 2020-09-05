package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefDefcartypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_DefcartypeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String cartypeNo;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Bdef_DefcartypeId() {
	}

	/** full constructor */
	public Bdef_DefcartypeId(String cartypeNo, String enterpriseNo) {
		this.cartypeNo = cartypeNo;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "CARTYPE_NO", nullable = false, length = 10)
	public String getCartypeNo() {
		return this.cartypeNo;
	}

	public void setCartypeNo(String cartypeNo) {
		this.cartypeNo = cartypeNo;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_DefcartypeId))
			return false;
		Bdef_DefcartypeId castOther = (Bdef_DefcartypeId) other;

		return ((this.getCartypeNo() == castOther.getCartypeNo()) || (this
				.getCartypeNo() != null && castOther.getCartypeNo() != null && this
				.getCartypeNo().equals(castOther.getCartypeNo())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCartypeNo() == null ? 0 : this.getCartypeNo().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}