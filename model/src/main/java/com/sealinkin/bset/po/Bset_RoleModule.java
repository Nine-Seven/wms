package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bset_RoleModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_ROLE_MODULE")
public class Bset_RoleModule implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Bset_RoleModuleId id;
	private String rightName;
	private BigDecimal flag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_RoleModule() {
	}

	/** minimal constructor */
	public Bset_RoleModule(Bset_RoleModuleId id, String rightName, BigDecimal flag) {
		this.id = id;
		this.rightName = rightName;
		this.flag = flag;
	}

	/** full constructor */
	public Bset_RoleModule(Bset_RoleModuleId id, String rightName,
			BigDecimal flag, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.rightName = rightName;
		this.flag = flag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false, length = 24)),
			@AttributeOverride(name = "butId", column = @Column(name = "BUT_ID", nullable = false, length = 64)) })
	public Bset_RoleModuleId getId() {
		return this.id;
	}

	public void setId(Bset_RoleModuleId id) {
		this.id = id;
	}

	@Column(name = "RIGHT_NAME", nullable = false, length = 32)
	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	@Column(name = "FLAG", nullable = false, precision = 22, scale = 0)
	public BigDecimal getFlag() {
		return this.flag;
	}

	public void setFlag(BigDecimal flag) {
		this.flag = flag;
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

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}