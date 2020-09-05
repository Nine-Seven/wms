package com.sealinkin.jk.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * JkBymSheetwarehouse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JK_BYM_SHEETWAREHOUSE")
public class JkBymSheetwarehouse implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JkBymSheetwarehouseId id;

	// Constructors

	/** default constructor */
	public JkBymSheetwarehouse() {
	}

	/** full constructor */
	public JkBymSheetwarehouse(JkBymSheetwarehouseId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "sheetNo", column = @Column(name = "SHEET_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "corpkey", column = @Column(name = "CORPKEY", length = 64)),
			@AttributeOverride(name = "sheetType", column = @Column(name = "SHEET_TYPE", length = 20)) })
	public JkBymSheetwarehouseId getId() {
		return this.id;
	}

	public void setId(JkBymSheetwarehouseId id) {
		this.id = id;
	}

}