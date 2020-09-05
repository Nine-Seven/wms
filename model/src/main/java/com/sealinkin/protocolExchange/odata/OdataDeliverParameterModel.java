package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 装车参数结构 对应StuOMDeliverParameter
 * @author lich
 *
 */
public class OdataDeliverParameterModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	 private String CustName;//客户名称
	
	private String EnterpriseNo;//企业
	private String WarehouseNo;//仓别
    private String custNo;//客户编号
    private String dockNo;//码头号
    private String carNo;//车辆编号
    private String userID;//操作人员
    private String loadproposeNo;//装车单号
    private String shipperNo;//承运商
    private String lineNo;//承运商
    private String carPlate;//车牌号
    private String sealNo;//封条号
    private String labelNo;//标签号
    private String loadCarLevel;//建装车建议单的级别：1：按承运商；2：按线路；3：配送对象		
    private String MatchNo;//派车单号
    private String ExpNo;  //出货单号
    private String Deliver;//分车标识
    private String  DeliverObj;//配送对象
    private String  Recust;//
    private String loadtype;//封车标识1：可封车；0：不可封车   
    private String LabelQty;//已扫标签数
    private String Qty;//未扫标签数
    private String updtname;//单据人
    private String DeliverNo;//配送人
    
    public String getDeliverNo() {
		return DeliverNo;
	}
	public void setDeliverNo(String deliverNo) {
		DeliverNo = deliverNo;
	}
	public String getUpdtname() {
		return updtname;
	}
	public void setUpdtname(String updtname) {
		this.updtname = updtname;
	}
	public String getLabelQty() {
		return LabelQty;
	}
	public void setLabelQty(String labelQty) {
		LabelQty = labelQty;
	}
	public String getQty() {
		return Qty;
	}
	public void setQty(String qty) {
		Qty = qty;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	public String getRecust() {
		return Recust;
	}
	public String getLoadtype() {
		return loadtype;
	}
	public void setLoadtype(String loadtype) {
		this.loadtype = loadtype;
	}
	public void setRecust(String recust) {
		Recust = recust;
	}
	public String getDeliverObj() {
		return DeliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		DeliverObj = deliverObj;
	}
	public String getDeliver() {
		return Deliver;
	}
	public void setDeliver(String deliver) {
		Deliver = deliver;
	}
	public String getExpNo() {
		return ExpNo;
	}
	public void setExpNo(String expNo) {
		ExpNo = expNo;
	}

    public String getMatchNo() {
		return MatchNo;
	}
	public void setMatchNo(String matchNo) {
		MatchNo = matchNo;
	}
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getLoadproposeNo() {
		return loadproposeNo;
	}
	public void setLoadproposeNo(String loadproposeNo) {
		this.loadproposeNo = loadproposeNo;
	}
	public String getShipperNo() {
		return shipperNo;
	}
	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public String getSealNo() {
		return sealNo;
	}
	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getLoadCarLevel() {
		return loadCarLevel;
	}
	public void setLoadCarLevel(String loadCarLevel) {
		this.loadCarLevel = loadCarLevel;
	}
	
}
