package com.sealinkin.wms.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WmsInterfaceLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_INTERFACE_LOG")
public class Wms_InterfaceLog implements java.io.Serializable {

	// Fields

	private Wms_InterfaceLogId id;

	// Constructors

	/** default constructor */
	public Wms_InterfaceLog() {
	}

	/** full constructor */
	public Wms_InterfaceLog(Wms_InterfaceLogId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "seqNo", column = @Column(name = "SEQ_NO", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "platType", column = @Column(name = "PLAT_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "platNo", column = @Column(name = "PLAT_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "platDesc", column = @Column(name = "PLAT_DESC", nullable = false, length = 30)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehourceNo", column = @Column(name = "WAREHOURCE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "apiNo", column = @Column(name = "API_NO", nullable = false, length = 300)),
			@AttributeOverride(name = "execTime", column = @Column(name = "EXEC_TIME", nullable = false, length = 7)),
			@AttributeOverride(name = "execStatus", column = @Column(name = "EXEC_STATUS", nullable = false, length = 1)),
			@AttributeOverride(name = "execDesc", column = @Column(name = "EXEC_DESC", length = 300)),
			@AttributeOverride(name = "apiDesc", column = @Column(name = "API_DESC", length = 300)) })
	public Wms_InterfaceLogId getId() {
		return this.id;
	}

	public void setId(Wms_InterfaceLogId id) {
		this.id = id;
	}

}