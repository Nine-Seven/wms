package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 按线路装车--获取指定线路信息
 * @author lich
 *
 */
public class OdataDeliverGetLineInfoModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	 private String LineNo;//线路编码
	 private String LineSeqNo;//路顺
	 private String WaveNo;//波次号
	 private String BufferName;//暂存区编码
	 private String CustNo;//客户编码
	 private String CustName;//客户名称 
	 private String LoadProposeNo;//装车建议单号 （扫码头后自动获取或创建）
	
	 private String ScanNo;//扫描数据
	private String warehouseNo;//仓别
	private String enterpriseNo;//企业编码  
	private String NewCustNo;//新客户信息，如果传此值，表示当前的客户信息已被确认（当扫描的箱标签信息与建议客户不一致时用）
	
	private Integer BoxCount;//物流箱个数
	private String ScanCount;//已扫板数
	private String WaitScanCount;//未扫板数

	private String UserID;//用户ID
	private String CarNo;//车辆编码

	private String DeliverObj;//配送对象
	
	private String deliverArea;//暂存区 huangb 20160707
	private Integer labelNum;//标签数 huangb 20160707
	private Integer cLabelNum;//整件数 huangb 20160707
	private Integer bLabelNum;//拆零数 huangb 20160707
	private String carPlate;//车牌号 huangb 20160708
	private String driverWorker;//司机 huangb 20160708
	
	public String getDeliverArea() {
		return deliverArea;
	}
	public void setDeliverArea(String deliverArea) {
		this.deliverArea = deliverArea;
	}
	public Integer getLabelNum() {
		return labelNum;
	}
	public void setLabelNum(Integer labelNum) {
		this.labelNum = labelNum;
	}
	public Integer getcLabelNum() {
		return cLabelNum;
	}
	public void setcLabelNum(Integer cLabelNum) {
		this.cLabelNum = cLabelNum;
	}
	public Integer getbLabelNum() {
		return bLabelNum;
	}
	public void setbLabelNum(Integer bLabelNum) {
		this.bLabelNum = bLabelNum;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public String getDriverWorker() {
		return driverWorker;
	}
	public void setDriverWorker(String driverWorker) {
		this.driverWorker = driverWorker;
	}
	
	public String getDeliverObj() {
		return DeliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		DeliverObj = deliverObj;
	}
	public String getCarNo() {
		return CarNo;
	}
	public void setCarNo(String carNo) {
		CarNo = carNo;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getLoadProposeNo() {
		return LoadProposeNo;
	}
	public void setLoadProposeNo(String loadProposeNo) {
		LoadProposeNo = loadProposeNo;
	}
	public Integer getBoxCount() {
		return BoxCount;
	}
	public void setBoxCount(Integer boxCount) {
		BoxCount = boxCount;
	}
	public String getScanCount() {
		return ScanCount;
	}
	public void setScanCount(String scanCount) {
		ScanCount = scanCount;
	}
	public String getWaitScanCount() {
		return WaitScanCount;
	}
	public void setWaitScanCount(String waitScanCount) {
		WaitScanCount = waitScanCount;
	}
	public String getNewCustNo() {
		return NewCustNo;
	}
	public void setNewCustNo(String newCustNo) {
		NewCustNo = newCustNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getScanNo() {
		return ScanNo;
	}
	public void setScanNo(String scanNo) {
		ScanNo = scanNo;
	}
	public String getLineNo() {
		return LineNo;
	}
	public void setLineNo(String lineNo) {
		LineNo = lineNo;
	}
	public String getLineSeqNo() {
		return LineSeqNo;
	}
	public void setLineSeqNo(String lineSeqNo) {
		LineSeqNo = lineSeqNo;
	}
	public String getWaveNo() {
		return WaveNo;
	}
	public void setWaveNo(String waveNo) {
		WaveNo = waveNo;
	}
	public String getBufferName() {
		return BufferName;
	}
	public void setBufferName(String bufferName) {
		BufferName = bufferName;
	}
	public String getCustNo() {
		return CustNo;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
}
