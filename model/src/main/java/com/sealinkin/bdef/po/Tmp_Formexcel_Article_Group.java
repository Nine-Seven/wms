package com.sealinkin.bdef.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Tmp_Formexcel_Article_Group entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FORMEXCEL_ARTICLE_GROUP")
public class Tmp_Formexcel_Article_Group implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tmp_Formexcel_Article_GroupId id;

	// Constructors

	/** default constructor */
	public Tmp_Formexcel_Article_Group() {
	}

	/** full constructor */
	public Tmp_Formexcel_Article_Group(Tmp_Formexcel_Article_GroupId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "groupName", column = @Column(name = "GROUP_NAME", nullable = false, length = 45)),
			@AttributeOverride(name = "MGroupNo", column = @Column(name = "M_GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "MGroupName", column = @Column(name = "M_GROUP_NAME", nullable = false, length = 45)),
			@AttributeOverride(name = "LGroupNo", column = @Column(name = "L_GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "LGroupName", column = @Column(name = "L_GROUP_NAME", nullable = false, length = 45)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 20)),
            @AttributeOverride(name="rowId", column=@Column(name="ROW_ID", nullable=false, precision=22, scale=0) ) } )

	public Tmp_Formexcel_Article_GroupId getId() {
		return this.id;
	}

	public void setId(Tmp_Formexcel_Article_GroupId id) {
		this.id = id;
	}

}