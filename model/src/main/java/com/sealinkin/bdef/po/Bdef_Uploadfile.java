package com.sealinkin.bdef.po;

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
 * BdefUploadfile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_UPLOADFILE")
public class Bdef_Uploadfile implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_UploadfileId id;
	private String rgstName;
	private Date rgstDate;
	private String filePath;

	// Constructors

	/** default constructor */
	public Bdef_Uploadfile() {
	}

	/** full constructor */
	public Bdef_Uploadfile(Bdef_UploadfileId id, String rgstName, Date rgstDate,
			String filePath) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.filePath = filePath;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "fileName", column = @Column(name = "FILE_NAME", nullable = false, length = 40)) })
	public Bdef_UploadfileId getId() {
		return this.id;
	}

	public void setId(Bdef_UploadfileId id) {
		this.id = id;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "FILE_PATH", nullable = false, length = 60)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}