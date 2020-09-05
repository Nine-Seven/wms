package com.sealinkin.cdef.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CdefDefareaLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CDEF_DEFAREA_LOG")
public class Cdef_DefareaLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cdef_DefareaLogId id;

	// Constructors

	/** default constructor */
	public Cdef_DefareaLog() {
	}

	/** full constructor */
	public Cdef_DefareaLog(Cdef_DefareaLogId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaNo", column = @Column(name = "AREA_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaName", column = @Column(name = "AREA_NAME", nullable = false, length = 30)),
			@AttributeOverride(name = "areaRemark", column = @Column(name = "AREA_REMARK", length = 50)),
			@AttributeOverride(name = "OType", column = @Column(name = "O_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "areaType", column = @Column(name = "AREA_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "areaUsetype", column = @Column(name = "AREA_USETYPE", nullable = false, length = 2)),
			@AttributeOverride(name = "areaQuality", column = @Column(name = "AREA_QUALITY", nullable = false, length = 2)),
			@AttributeOverride(name = "mixFlag", column = @Column(name = "MIX_FLAG", nullable = false, precision = 1, scale = 0)),
			@AttributeOverride(name = "mixSupplier", column = @Column(name = "MIX_SUPPLIER", nullable = false, length = 1)),
			@AttributeOverride(name = "maxQty", column = @Column(name = "MAX_QTY", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "stockNum", column = @Column(name = "STOCK_NUM", nullable = false, precision = 2, scale = 0)),
			@AttributeOverride(name = "divideFlag", column = @Column(name = "DIVIDE_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "BDivideFlag", column = @Column(name = "B_DIVIDE_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "areaAttribute", column = @Column(name = "AREA_ATTRIBUTE", nullable = false, length = 1)),
			@AttributeOverride(name = "attributeType", column = @Column(name = "ATTRIBUTE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "limitType", column = @Column(name = "LIMIT_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "limitRate", column = @Column(name = "LIMIT_RATE", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "palOutRate", column = @Column(name = "PAL_OUT_RATE", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "BPick", column = @Column(name = "B_PICK", nullable = false, length = 1)),
			@AttributeOverride(name = "areaPick", column = @Column(name = "AREA_PICK", nullable = false, length = 1)),
			@AttributeOverride(name = "AFlag", column = @Column(name = "A_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "ioBufferFlag", column = @Column(name = "IO_BUFFER_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "pickFlag", column = @Column(name = "PICK_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "floor", column = @Column(name = "FLOOR", nullable = false, length = 1)),
			@AttributeOverride(name = "advancerPickFlag", column = @Column(name = "ADVANCER_PICK_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "maxCase", column = @Column(name = "MAX_CASE", precision = 13, scale = 5)),
			@AttributeOverride(name = "itemType", column = @Column(name = "ITEM_TYPE", nullable = false, length = 20)),
			@AttributeOverride(name = "divideLineFlag", column = @Column(name = "DIVIDE_LINE_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "BReplenishType", column = @Column(name = "B_REPLENISH_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "BReplenishRule", column = @Column(name = "B_REPLENISH_RULE", nullable = false, length = 1)),
			@AttributeOverride(name = "CReplenishType", column = @Column(name = "C_REPLENISH_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "CReplenishRule", column = @Column(name = "C_REPLENISH_RULE", nullable = false, length = 1)),
			@AttributeOverride(name = "replenishTaskRule", column = @Column(name = "REPLENISH_TASK_RULE", nullable = false, length = 1)),
			@AttributeOverride(name = "locateTime", column = @Column(name = "LOCATE_TIME", nullable = false, length = 1)),
			@AttributeOverride(name = "taskId", column = @Column(name = "TASK_ID", precision = 3, scale = 0)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)) })
	public Cdef_DefareaLogId getId() {
		return this.id;
	}

	public void setId(Cdef_DefareaLogId id) {
		this.id = id;
	}

}