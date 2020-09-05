package com.sealinkin.wms.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sealinkin.wms.po.Wms_InterfaceLog;

public class Wms_InterfaceLogModel extends Wms_InterfaceLog{
	
	private static final long serialVersionUID = 1L;
	
	private Date operateDate;
	private BigDecimal seqNo;
	private String platType;
	private String platNo;
	private String platDesc;
	private String enterpriseNo;
	private String warehourceNo;
	private String apiNo;
	private Date execTime;
	private String execStatus;
	private String execDesc;
	private String apiDesc;
	
	private int execTimeQty;
	private int yesTimeQty;
	private int noTimeQty;
	private Date lastTime;
	
	
	public int getExecTimeQty() {
		return execTimeQty;
	}
	public void setExecTimeQty(int execTimeQty) {
		this.execTimeQty = execTimeQty;
	}
	public int getYesTimeQty() {
		return yesTimeQty;
	}
	public void setYesTimeQty(int yesTimeQty) {
		this.yesTimeQty = yesTimeQty;
	}
	public int getNoTimeQty() {
		return noTimeQty;
	}
	public void setNoTimeQty(int noTimeQty) {
		this.noTimeQty = noTimeQty;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public BigDecimal getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}
	public String getPlatType() {
		return platType;
	}
	public void setPlatType(String platType) {
		this.platType = platType;
	}
	public String getPlatNo() {
		return platNo;
	}
	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}
	public String getPlatDesc() {
		return platDesc;
	}
	public void setPlatDesc(String platDesc) {
		this.platDesc = platDesc;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehourceNo() {
		return warehourceNo;
	}
	public void setWarehourceNo(String warehourceNo) {
		this.warehourceNo = warehourceNo;
	}
	public String getApiNo() {
		return apiNo;
	}
	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}
	public Date getExecTime() {
		return execTime;
	}
	public void setExecTime(Date execTime) {
		this.execTime = execTime;
	}
	public String getExecStatus() {
		return execStatus;
	}
	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus;
	}
	public String getExecDesc() {
		return execDesc;
	}
	public void setExecDesc(String execDesc) {
		this.execDesc = execDesc;
	}
	public String getApiDesc() {
		return apiDesc;
	}
	public void setApiDesc(String apiDesc) {
		this.apiDesc = apiDesc;
	}

	

	
	

}
