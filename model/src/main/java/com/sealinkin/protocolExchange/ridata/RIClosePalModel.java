package com.sealinkin.protocolExchange.ridata;

/**
 * RF返配验收 扫描条码请求
 * @author sunl
 *
 */
public class RIClosePalModel {
	   /// <summary>
    /// 企业编码
    /// </summary>
    private String EnterPriseNo;
    public String getEnterPriseNo() {
		return EnterPriseNo;
	}
	public void setEnterPriseNo(String enterPriseNo) {
		EnterPriseNo = enterPriseNo;
	}
	public String getWareHouseNo() {
		return WareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		WareHouseNo = wareHouseNo;
	}
	public String getOwnerNo() {
		return OwnerNo;
	}
	public void setOwnerNo(String ownerNo) {
		OwnerNo = ownerNo;
	}
	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}
	public String getSCheckNo() {
		return SCheckNo;
	}
	public void setSCheckNo(String sCheckNo) {
		SCheckNo = sCheckNo;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getWorkerNo() {
		return WorkerNo;
	}
	public void setWorkerNo(String workerNo) {
		WorkerNo = workerNo;
	}
	public String getDockNo() {
		return DockNo;
	}
	public void setDockNo(String dockNo) {
		DockNo = dockNo;
	}
	/// <summary>
    /// 仓别
    /// </summary>
    private String WareHouseNo;
    /// <summary>
    /// 委托业主
    /// </summary>
    private String OwnerNo;
    /// <summary>
    /// 反配汇总单号
    /// </summary>
    private String SUntreadNo;
    /// <summary>
    /// 反配验收汇总单号
    /// </summary>
    private String SCheckNo;
    /// <summary>
    /// 标签号
    /// </summary>
    private String LabelNo;
    /// <summary>
    /// 操作人
    /// </summary>
    private String WorkerNo;
    /// <summary>
    /// 码头
    /// </summary>
    private String DockNo;

    /// <summary>
    /// 类型
    /// </summary>
    private String ClassType;
    public String getClassType() {
		return ClassType;
	}
	public void setClassType(String classType) {
		ClassType = classType;
	}
	public String getQualityFlag() {
		return QualityFlag;
	}
	public void setQualityFlag(String qualityFlag) {
		QualityFlag = qualityFlag;
	}
	public String getUntreadType() {
		return UntreadType;
	}
	public void setUntreadType(String untreadType) {
		UntreadType = untreadType;
	}
	public String getPrintFlag() {
		return PrintFlag;
	}
	public void setPrintFlag(String printFlag) {
		PrintFlag = printFlag;
	}
	/// <summary>
    /// 品质--单
    /// </summary>
	private String QualityFlag;
    /// <summary>
    /// 反配单类型
    /// </summary>
    private String UntreadType;
    /// <summary>
    /// 打印标识，0：不打印，1：打印表单，2：打印标签
    /// </summary>
    private String PrintFlag;
}
