package com.sealinkin.bdef.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TmpFormexcelArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FORMEXCEL_ARTICLE")
public class Tmp_Formexcel_Article implements java.io.Serializable {

	// Fields

	private Tmp_Formexcel_ArticleId id;

	// Constructors

	/** default constructor */
	public Tmp_Formexcel_Article() {
	}

	/** full constructor */
	public Tmp_Formexcel_Article(Tmp_Formexcel_ArticleId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "ownerArticleNo", column = @Column(name = "OWNER_ARTICLE_NO", length = 25)),
			@AttributeOverride(name = "articleName", column = @Column(name = "ARTICLE_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "articleAlias", column = @Column(name = "ARTICLE_ALIAS", length = 60)),
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "groupName", column = @Column(name = "GROUP_NAME", nullable = false, length = 45)),
			@AttributeOverride(name = "unit", column = @Column(name = "UNIT", nullable = false, length = 10)),
			@AttributeOverride(name = "spec", column = @Column(name = "SPEC", nullable = false, length = 50)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY",  precision = 18, scale = 5)),
			@AttributeOverride(name = "expiryDays", column = @Column(name = "EXPIRY_DAYS", nullable = false, precision = 5, scale = 0)),
			@AttributeOverride(name = "unitVolumn", column = @Column(name = "UNIT_VOLUMN", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "unitWeight", column = @Column(name = "UNIT_WEIGHT", nullable = false, precision = 17, scale = 5)),
			@AttributeOverride(name = "barcode", column = @Column(name = "BARCODE", length = 25)),
			@AttributeOverride(name = "boxNo", column = @Column(name = "BOX_NO", length = 25)),
			@AttributeOverride(name = "palBaseQbox", column = @Column(name = "PAL_BASE_QBOX", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "palHeightQbox", column = @Column(name = "PAL_HEIGHT_QBOX", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "ALength", column = @Column(name = "A_LENGTH", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "AWidth", column = @Column(name = "A_WIDTH", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "AHeight", column = @Column(name = "A_HEIGHT", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "AWeight", column = @Column(name = "A_WEIGHT", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 25)),
			@AttributeOverride(name = "operateType", column = @Column(name = "OPERATE_TYPE", length = 1)),
			@AttributeOverride(name = "articleIdentifier", column = @Column(name = "ARTICLE_IDENTIFIER", length = 50)),
			@AttributeOverride(name = "lotType", column = @Column(name = "LOT_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "scanFlag", column = @Column(name = "SCAN_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "supplierNo", column = @Column(name = "SUPPLIER_NO", length = 10)),
			@AttributeOverride(name = "temperatureFlag", column = @Column(name = "TEMPERATURE_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "packingSpec", column = @Column(name = "PACKING_SPEC", length = 100)),
			@AttributeOverride(name = "packingUnit", column = @Column(name = "PACKING_UNIT", length = 20)),
			@AttributeOverride(name = "rsvAttr1", column = @Column(name = "RSV_ATTR1", length = 50)),
			@AttributeOverride(name = "rsvAttr2", column = @Column(name = "RSV_ATTR2", length = 50)),
			@AttributeOverride(name = "rsvAttr3", column = @Column(name = "RSV_ATTR3", length = 50)),
			@AttributeOverride(name = "rsvAttr4", column = @Column(name = "RSV_ATTR4", length = 50)),
			@AttributeOverride(name = "rsvAttr5", column = @Column(name = "RSV_ATTR5", length = 50)),
			@AttributeOverride(name = "rsvAttr6", column = @Column(name = "RSV_ATTR6", length = 50)),
			@AttributeOverride(name = "rsvNum1", column = @Column(name = "RSV_NUM1", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvNum2", column = @Column(name = "RSV_NUM2", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvNum3", column = @Column(name = "RSV_NUM3", precision = 22, scale = 0)) ,
			@AttributeOverride(name = "qminOperatePacking", column = @Column(name = "QMIN_OPERATE_PACKING", precision = 9, scale = 5)),
			@AttributeOverride(name = "qminOperatePackingUnit", column = @Column(name = "QMIN_OPERATE_PACKING_UNIT", length = 10)),
			@AttributeOverride(name = "unitPacking", column = @Column(name = "UNIT_PACKING", nullable = false, precision = 18, scale = 5))
			
	
	})
	public Tmp_Formexcel_ArticleId getId() {
		return this.id;
	}

	public void setId(Tmp_Formexcel_ArticleId id) {
		this.id = id;
	}

}