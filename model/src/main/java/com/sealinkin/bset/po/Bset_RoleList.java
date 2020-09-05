package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bset_RoleList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_ROLE_LIST")
public class Bset_RoleList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal roleId;
	private String roleName;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String enterpriseNo;//企业
	// Constructors

	/** default constructor */
	public Bset_RoleList() {
	}

	/** minimal constructor */
	public Bset_RoleList(String roleName) {
		this.roleName = roleName;
	}

	/** full constructor */
	public Bset_RoleList(String roleName, String rgstName, Date rgstDate,
			String updtName, Date updtDate,String enterpriseNo) {
		this.roleName = roleName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", nullable = false, length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
}