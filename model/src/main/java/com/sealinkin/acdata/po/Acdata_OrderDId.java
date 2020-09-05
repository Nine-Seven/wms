package com.sealinkin.acdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AcdataOrderDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Acdata_OrderDId implements java.io.Serializable {

	// Fields

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String orderNo;
		private String barcodeNo;
		private String articleName;

		// Constructors

		/** default constructor */
		public Acdata_OrderDId() {
		}

		/** full constructor */
		public Acdata_OrderDId(String orderNo, String barcodeNo, String articleName) {
			this.orderNo = orderNo;
			this.barcodeNo = barcodeNo;
			this.articleName = articleName;
		}

		// Property accessors

		@Column(name = "ORDER_NO", nullable = false, length = 20)
		public String getOrderNo() {
			return this.orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		@Column(name = "BARCODE_NO", nullable = false, length = 50)
		public String getBarcodeNo() {
			return this.barcodeNo;
		}

		public void setBarcodeNo(String barcodeNo) {
			this.barcodeNo = barcodeNo;
		}

		@Column(name = "ARTICLE_NAME", nullable = false, length = 128)
		public String getArticleName() {
			return this.articleName;
		}

		public void setArticleName(String articleName) {
			this.articleName = articleName;
		}

		public boolean equals(Object other) {
			if ((this == other))
				return true;
			if ((other == null))
				return false;
			if (!(other instanceof Acdata_OrderDId))
				return false;
			Acdata_OrderDId castOther = (Acdata_OrderDId) other;

			return ((this.getOrderNo() == castOther.getOrderNo()) || (this
					.getOrderNo() != null && castOther.getOrderNo() != null && this
					.getOrderNo().equals(castOther.getOrderNo())))
					&& ((this.getBarcodeNo() == castOther.getBarcodeNo()) || (this
							.getBarcodeNo() != null
							&& castOther.getBarcodeNo() != null && this
							.getBarcodeNo().equals(castOther.getBarcodeNo())))
					&& ((this.getArticleName() == castOther.getArticleName()) || (this
							.getArticleName() != null
							&& castOther.getArticleName() != null && this
							.getArticleName().equals(castOther.getArticleName())));
		}

		public int hashCode() {
			int result = 17;

			result = 37 * result
					+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
			result = 37 * result
					+ (getBarcodeNo() == null ? 0 : this.getBarcodeNo().hashCode());
			result = 37
					* result
					+ (getArticleName() == null ? 0 : this.getArticleName()
							.hashCode());
			return result;
		}

	}