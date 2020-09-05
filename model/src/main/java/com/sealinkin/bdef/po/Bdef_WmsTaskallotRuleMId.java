package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 模块名称：任务切分规则配置MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
@Embeddable
public class Bdef_WmsTaskallotRuleMId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private Short taskRuleid;

	// Constructors

	/** default constructor */
	public Bdef_WmsTaskallotRuleMId() {
	}

	/** full constructor */
	public Bdef_WmsTaskallotRuleMId(String enterpriseNo, Short taskRuleid) {
		this.enterpriseNo = enterpriseNo;
		this.taskRuleid = taskRuleid;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "TASK_RULEID", nullable = false, precision = 4, scale = 0)
	public Short getTaskRuleid() {
		return this.taskRuleid;
	}

	public void setTaskRuleid(Short taskRuleid) {
		this.taskRuleid = taskRuleid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_WmsTaskallotRuleMId))
			return false;
		Bdef_WmsTaskallotRuleMId castOther = (Bdef_WmsTaskallotRuleMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getTaskRuleid() == castOther.getTaskRuleid()) || (this
						.getTaskRuleid() != null
						&& castOther.getTaskRuleid() != null && this
						.getTaskRuleid().equals(castOther.getTaskRuleid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getTaskRuleid() == null ? 0 : this.getTaskRuleid()
						.hashCode());
		return result;
	}

}