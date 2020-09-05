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
 * BdefDefappendix entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFAPPENDIX")
public class Bdef_Defappendix implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_DefappendixId id;
	private String type;
	private String filePath;
	private String relateOrderno;
	private String relateClass;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String meno;

	// Constructors

	/** default constructor */
	public Bdef_Defappendix() {
	}

	/** minimal constructor */
	public Bdef_Defappendix(Bdef_DefappendixId id, String type, String filePath,
			String relateOrderno, String relateClass, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.type = type;
		this.filePath = filePath;
		this.relateOrderno = relateOrderno;
		this.relateClass = relateClass;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_Defappendix(Bdef_DefappendixId id, String type, String filePath,
			String relateOrderno, String relateClass, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String meno) {
		this.id = id;
		this.type = type;
		this.filePath = filePath;
		this.relateOrderno = relateOrderno;
		this.relateClass = relateClass;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.meno = meno;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "fileName", column = @Column(name = "FILE_NAME", nullable = false, length = 120)) })
	public Bdef_DefappendixId getId() {
		return this.id;
	}

	public void setId(Bdef_DefappendixId id) {
		this.id = id;
	}

	@Column(name = "TYPE", nullable = false, length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "FILE_PATH", nullable = false, length = 120)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "RELATE_ORDERNO", nullable = false, length = 20)
	public String getRelateOrderno() {
		return this.relateOrderno;
	}

	public void setRelateOrderno(String relateOrderno) {
		this.relateOrderno = relateOrderno;
	}

	@Column(name = "RELATE_CLASS", nullable = false, length = 1)
	public String getRelateClass() {
		return this.relateClass;
	}

	public void setRelateClass(String relateClass) {
		this.relateClass = relateClass;
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

	@Column(name = "MENO", length = 120)
	public String getMeno() {
		return this.meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

}