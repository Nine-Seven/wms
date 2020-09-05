package com.sealinkin.fcdata.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Fcdata_RequestD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_REQUEST_D")
public class Fcdata_RequestD implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Fcdata_RequestDId id;
	private String groupNo;

	// Constructors

	/** default constructor */
	public Fcdata_RequestD() {
	}

	/** full constructor */
	public Fcdata_RequestD(Fcdata_RequestDId id, String groupNo) {
		this.id = id;
		this.groupNo = groupNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "requestNo", column = @Column(name = "REQUEST_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaNo", column = @Column(name = "AREA_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "stockNo", column = @Column(name = "STOCK_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)) })
	public Fcdata_RequestDId getId() {
		return this.id;
	}

	public void setId(Fcdata_RequestDId id) {
		this.id = id;
	}

	@Column(name = "GROUP_NO", nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

}