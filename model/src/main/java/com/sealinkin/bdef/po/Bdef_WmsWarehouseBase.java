package com.sealinkin.bdef.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WmsWarehouseBase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_WAREHOUSE_BASE")
public class Bdef_WmsWarehouseBase implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_WmsWarehouseBaseId id;
	private String groupNo;
	private String subGroupNo;
	private String sdefine;
	private Double ndefine;
	private String memo;

	// Constructors

	/** default constructor */
	public Bdef_WmsWarehouseBase() {
	}

	/** minimal constructor */
	public Bdef_WmsWarehouseBase(Bdef_WmsWarehouseBaseId id, String groupNo,
			String subGroupNo) {
		this.id = id;
		this.groupNo = groupNo;
		this.subGroupNo = subGroupNo;
	}

	/** full constructor */
	public Bdef_WmsWarehouseBase(Bdef_WmsWarehouseBaseId id, String groupNo,
			String subGroupNo, String sdefine, Double ndefine, String memo) {
		this.id = id;
		this.groupNo = groupNo;
		this.subGroupNo = subGroupNo;
		this.sdefine = sdefine;
		this.ndefine = ndefine;
		this.memo = memo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "colname", column = @Column(name = "COLNAME", nullable = false, length = 50)) })
	public Bdef_WmsWarehouseBaseId getId() {
		return this.id;
	}

	public void setId(Bdef_WmsWarehouseBaseId id) {
		this.id = id;
	}

	@Column(name = "GROUP_NO", nullable = false, length = 10)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "SUB_GROUP_NO", nullable = false, length = 10)
	public String getSubGroupNo() {
		return this.subGroupNo;
	}

	public void setSubGroupNo(String subGroupNo) {
		this.subGroupNo = subGroupNo;
	}

	@Column(name = "SDEFINE", length = 500)
	public String getSdefine() {
		return this.sdefine;
	}

	public void setSdefine(String sdefine) {
		this.sdefine = sdefine;
	}

	@Column(name = "NDEFINE", precision = 12, scale = 5)
	public Double getNdefine() {
		return this.ndefine;
	}

	public void setNdefine(Double ndefine) {
		this.ndefine = ndefine;
	}

	@Column(name = "MEMO", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}