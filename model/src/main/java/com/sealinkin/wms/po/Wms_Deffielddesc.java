package com.sealinkin.wms.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Wms_Deffielddesc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFFIELDDESC")
public class Wms_Deffielddesc implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Wms_DeffielddescId id;
	private String useType;
	private String fieldName;
	private String toolTips;
	private String SFieldName;
	private String SToolTips;
	private String descFlag;
	private String groupFlag;
	private Date rgstDate;

	// Constructors

	/** default constructor */
	public Wms_Deffielddesc() {
	}

	/** minimal constructor */
	public Wms_Deffielddesc(Wms_DeffielddescId id, String useType,
			String descFlag, String groupFlag) {
		this.id = id;
		this.useType = useType;
		this.descFlag = descFlag;
		this.groupFlag = groupFlag;
	}

	/** full constructor */
	public Wms_Deffielddesc(Wms_DeffielddescId id, String useType,
			String fieldName, String toolTips, String SFieldName,
			String SToolTips, String descFlag, String groupFlag, Date rgstDate) {
		this.id = id;
		this.useType = useType;
		this.fieldName = fieldName;
		this.toolTips = toolTips;
		this.SFieldName = SFieldName;
		this.SToolTips = SToolTips;
		this.descFlag = descFlag;
		this.groupFlag = groupFlag;
		this.rgstDate = rgstDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "tableName", column = @Column(name = "TABLE_NAME", nullable = false, length = 50)),
			@AttributeOverride(name = "fieldId", column = @Column(name = "FIELD_ID", nullable = false, length = 50)) })
	public Wms_DeffielddescId getId() {
		return this.id;
	}

	public void setId(Wms_DeffielddescId id) {
		this.id = id;
	}

	@Column(name = "USE_TYPE", nullable = false, length = 1)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "FIELD_NAME", length = 100)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "TOOL_TIPS", length = 200)
	public String getToolTips() {
		return this.toolTips;
	}

	public void setToolTips(String toolTips) {
		this.toolTips = toolTips;
	}

	@Column(name = "S_FIELD_NAME", length = 100)
	public String getSFieldName() {
		return this.SFieldName;
	}

	public void setSFieldName(String SFieldName) {
		this.SFieldName = SFieldName;
	}

	@Column(name = "S_TOOL_TIPS", length = 200)
	public String getSToolTips() {
		return this.SToolTips;
	}

	public void setSToolTips(String SToolTips) {
		this.SToolTips = SToolTips;
	}

	@Column(name = "DESC_FLAG", nullable = false, length = 1)
	public String getDescFlag() {
		return this.descFlag;
	}

	public void setDescFlag(String descFlag) {
		this.descFlag = descFlag;
	}

	@Column(name = "GROUP_FLAG", nullable = false, length = 1)
	public String getGroupFlag() {
		return this.groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

}