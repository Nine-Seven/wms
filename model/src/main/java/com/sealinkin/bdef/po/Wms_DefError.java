package com.sealinkin.bdef.po;
// default package

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Wms_DefError entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFERROR")
public class Wms_DefError  implements
		java.io.Serializable
{

	// Fields

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String errorid;
		private BigDecimal moduleId;
		private String errordesc;
		private String memo;

		// Constructors

		/** default constructor */
		public Wms_DefError()
		{
		}

		/** minimal constructor */
		public Wms_DefError(String errorid, BigDecimal moduleId)
		{
			this.errorid = errorid;
			this.moduleId = moduleId;
		}

		/** full constructor */
		public Wms_DefError(String errorid, BigDecimal moduleId,
				String errordesc, String memo)
		{
			this.errorid = errorid;
			this.moduleId = moduleId;
			this.errordesc = errordesc;
			this.memo = memo;
		}

		// Property accessors
		@Id
		@Column(name = "ERRORID", unique = true, nullable = false, length = 6)
		public String getErrorid()
		{
			return this.errorid;
		}

		public void setErrorid(String errorid)
		{
			this.errorid = errorid;
		}

		@Column(name = "MODULE_ID", nullable = false, precision = 22, scale = 0)
		public BigDecimal getModuleId()
		{
			return this.moduleId;
		}

		public void setModuleId(BigDecimal moduleId)
		{
			this.moduleId = moduleId;
		}

		@Column(name = "ERRORDESC", length = 4000)
		public String getErrordesc()
		{
			return this.errordesc;
		}

		public void setErrordesc(String errordesc)
		{
			this.errordesc = errordesc;
		}

		@Column(name = "MEMO", length = 4000)
		public String getMemo()
		{
			return this.memo;
		}

		public void setMemo(String memo)
		{
			this.memo = memo;
		}
}
