package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 模块名称：任务切分规则配置MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
@Embeddable
public class Bdef_WmsTaskallotRuleId implements java.io.Serializable {

	// Fields

	private String operateType;
	private String outstockType;

	// Constructors

	/** default constructor */
	public Bdef_WmsTaskallotRuleId() {
	}

	/** full constructor */
	public Bdef_WmsTaskallotRuleId(String operateType, String outstockType) {
		this.operateType = operateType;
		this.outstockType = outstockType;
	}

	// Property accessors

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)
	public String getOutstockType() {
		return this.outstockType;
	}

	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_WmsTaskallotRuleId))
			return false;
		Bdef_WmsTaskallotRuleId castOther = (Bdef_WmsTaskallotRuleId) other;

		return ((this.getOperateType() == castOther.getOperateType()) || (this
				.getOperateType() != null && castOther.getOperateType() != null && this
				.getOperateType().equals(castOther.getOperateType())))
				&& ((this.getOutstockType() == castOther.getOutstockType()) || (this
						.getOutstockType() != null
						&& castOther.getOutstockType() != null && this
						.getOutstockType().equals(castOther.getOutstockType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOperateType() == null ? 0 : this.getOperateType()
						.hashCode());
		result = 37
				* result
				+ (getOutstockType() == null ? 0 : this.getOutstockType()
						.hashCode());
		return result;
	}

}