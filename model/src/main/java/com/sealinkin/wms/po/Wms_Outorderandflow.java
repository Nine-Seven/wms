package com.sealinkin.wms.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Wms_Outorderandflow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTORDERANDFLOW")
public class Wms_Outorderandflow implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Wms_OutorderandflowId id;

	// Constructors

	/** default constructor */
	public Wms_Outorderandflow() {
	}

	/** full constructor */
	public Wms_Outorderandflow(Wms_OutorderandflowId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "flowValue", column = @Column(name = "FLOW_VALUE", precision = 3, scale = 0)),
			@AttributeOverride(name = "flowText", column = @Column(name = "FLOW_TEXT", length = 24)) })
	public Wms_OutorderandflowId getId() {
		return this.id;
	}

	public void setId(Wms_OutorderandflowId id) {
		this.id = id;
	}

}