package com.sealinkin.bset.po;

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
 * BsetUserRole entity.
 * 用户角色�? * @author lich
 */
@Entity
@Table(name = "BSET_USER_ROLE")
public class Bset_User_Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bset_User_RoleId id;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_User_Role() {
	}

	/** minimal constructor */
	public Bset_User_Role(Bset_User_RoleId id) {
		this.id = id;
	}

	/** full constructor */
	public Bset_User_Role(Bset_User_RoleId id, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "workerNo", column = @Column(name = "WORKER_NO", nullable = false, length = 50)),
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, precision = 22, scale = 0)) })
	public Bset_User_RoleId getId() {
		return this.id;
	}

	public void setId(Bset_User_RoleId id) {
		this.id = id;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}