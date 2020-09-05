package com.sealinkin.bset.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bset_Rightlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_RIGHTLIST")
public class Bset_Rightlist implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Bset_RightlistId id;

	// Constructors

	/** default constructor */
	public Bset_Rightlist() {
	}

	/** full constructor */
	public Bset_Rightlist(Bset_RightlistId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false, length = 24)),
			@AttributeOverride(name = "moduleName", column = @Column(name = "MODULE_NAME", nullable = false, length = 128)),
			@AttributeOverride(name = "name", column = @Column(name = "NAME", nullable = false, length = 128)),
			@AttributeOverride(name = "rightName", column = @Column(name = "RIGHT_NAME", nullable = false, length = 32)),
			@AttributeOverride(name = "butId", column = @Column(name = "BUT_ID", length = 64)),
			@AttributeOverride(name = "butName", column = @Column(name = "BUT_NAME", length = 32)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", precision = 22, scale = 0)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "ORDER_NO", precision = 22, scale = 0)),
			@AttributeOverride(name = "parentId", column = @Column(name = "PARENT_ID", length = 32)),
			@AttributeOverride(name = "terminalFlag", column = @Column(name = "TERMINAL_FLAG", length = 1)) })
	public Bset_RightlistId getId() {
		return this.id;
	}

	public void setId(Bset_RightlistId id) {
		this.id = id;
	}

}