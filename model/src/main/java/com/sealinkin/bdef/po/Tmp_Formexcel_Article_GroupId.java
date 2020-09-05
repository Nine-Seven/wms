package com.sealinkin.bdef.po;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tmp_Formexcel_Article_GroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_Formexcel_Article_GroupId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ownerNo;
	private String groupNo;
	private String groupName;
	private String MGroupNo;
	private String MGroupName;
	private String LGroupNo;
	private String LGroupName;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private Date operateDate;
	private String enterpriseNo;
    private Double rowId;
	// Constructors

	/** default constructor */
	public Tmp_Formexcel_Article_GroupId() {
	}

	/** minimal constructor */
	public Tmp_Formexcel_Article_GroupId(String ownerNo, String groupNo,
			String groupName, String MGroupNo, String MGroupName,
			String LGroupNo, String LGroupName, String status, String rgstName,
			Date rgstDate, Date operateDate, String enterpriseNo,Double rowId) {
		this.ownerNo = ownerNo;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.MGroupNo = MGroupNo;
		this.MGroupName = MGroupName;
		this.LGroupNo = LGroupNo;
		this.LGroupName = LGroupName;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.operateDate = operateDate;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
	}

	/** full constructor */
	public Tmp_Formexcel_Article_GroupId(String ownerNo, String groupNo,
			String groupName, String MGroupNo, String MGroupName,
			String LGroupNo, String LGroupName, String status, String rgstName,
			Date rgstDate, String updtName, Date updtDate, Date operateDate,
			String enterpriseNo,Double rowId) {
		this.ownerNo = ownerNo;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.MGroupNo = MGroupNo;
		this.MGroupName = MGroupName;
		this.LGroupNo = LGroupNo;
		this.LGroupName = LGroupName;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.operateDate = operateDate;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
	}

	// Property accessors

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "GROUP_NO", nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "GROUP_NAME", nullable = false, length = 45)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "M_GROUP_NO", nullable = false, length = 20)
	public String getMGroupNo() {
		return this.MGroupNo;
	}

	public void setMGroupNo(String MGroupNo) {
		this.MGroupNo = MGroupNo;
	}

	@Column(name = "M_GROUP_NAME", nullable = false, length = 45)
	public String getMGroupName() {
		return this.MGroupName;
	}

	public void setMGroupName(String MGroupName) {
		this.MGroupName = MGroupName;
	}

	@Column(name = "L_GROUP_NO", nullable = false, length = 20)
	public String getLGroupNo() {
		return this.LGroupNo;
	}

	public void setLGroupNo(String LGroupNo) {
		this.LGroupNo = LGroupNo;
	}

	@Column(name = "L_GROUP_NAME", nullable = false, length = 45)
	public String getLGroupName() {
		return this.LGroupName;
	}

	public void setLGroupName(String LGroupName) {
		this.LGroupName = LGroupName;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 20)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

    @Column(name="ROW_ID", nullable=false, precision=22, scale=0)
    
    public Double getRowId() {
 		return rowId;
 	}

 	public void setRowId(Double rowId) {
 		this.rowId = rowId;
 	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((LGroupName == null) ? 0 : LGroupName.hashCode());
		result = prime * result
				+ ((LGroupNo == null) ? 0 : LGroupNo.hashCode());
		result = prime * result
				+ ((MGroupName == null) ? 0 : MGroupName.hashCode());
		result = prime * result
				+ ((MGroupNo == null) ? 0 : MGroupNo.hashCode());
		result = prime * result
				+ ((enterpriseNo == null) ? 0 : enterpriseNo.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((groupNo == null) ? 0 : groupNo.hashCode());
		result = prime * result
				+ ((operateDate == null) ? 0 : operateDate.hashCode());
		result = prime * result + ((ownerNo == null) ? 0 : ownerNo.hashCode());
		result = prime * result
				+ ((rgstDate == null) ? 0 : rgstDate.hashCode());
		result = prime * result
				+ ((rgstName == null) ? 0 : rgstName.hashCode());
		result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updtDate == null) ? 0 : updtDate.hashCode());
		result = prime * result
				+ ((updtName == null) ? 0 : updtName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tmp_Formexcel_Article_GroupId other = (Tmp_Formexcel_Article_GroupId) obj;
		if (LGroupName == null) {
			if (other.LGroupName != null)
				return false;
		} else if (!LGroupName.equals(other.LGroupName))
			return false;
		if (LGroupNo == null) {
			if (other.LGroupNo != null)
				return false;
		} else if (!LGroupNo.equals(other.LGroupNo))
			return false;
		if (MGroupName == null) {
			if (other.MGroupName != null)
				return false;
		} else if (!MGroupName.equals(other.MGroupName))
			return false;
		if (MGroupNo == null) {
			if (other.MGroupNo != null)
				return false;
		} else if (!MGroupNo.equals(other.MGroupNo))
			return false;
		if (enterpriseNo == null) {
			if (other.enterpriseNo != null)
				return false;
		} else if (!enterpriseNo.equals(other.enterpriseNo))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (groupNo == null) {
			if (other.groupNo != null)
				return false;
		} else if (!groupNo.equals(other.groupNo))
			return false;
		if (operateDate == null) {
			if (other.operateDate != null)
				return false;
		} else if (!operateDate.equals(other.operateDate))
			return false;
		if (ownerNo == null) {
			if (other.ownerNo != null)
				return false;
		} else if (!ownerNo.equals(other.ownerNo))
			return false;
		if (rgstDate == null) {
			if (other.rgstDate != null)
				return false;
		} else if (!rgstDate.equals(other.rgstDate))
			return false;
		if (rgstName == null) {
			if (other.rgstName != null)
				return false;
		} else if (!rgstName.equals(other.rgstName))
			return false;
		if (rowId == null) {
			if (other.rowId != null)
				return false;
		} else if (!rowId.equals(other.rowId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updtDate == null) {
			if (other.updtDate != null)
				return false;
		} else if (!updtDate.equals(other.updtDate))
			return false;
		if (updtName == null) {
			if (other.updtName != null)
				return false;
		} else if (!updtName.equals(other.updtName))
			return false;
		return true;
	}


}