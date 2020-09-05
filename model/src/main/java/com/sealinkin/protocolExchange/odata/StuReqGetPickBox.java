package com.sealinkin.protocolExchange.odata;

public class StuReqGetPickBox {
    private String strSessionID;     
    private String EnterpriseNo;     
    private String WarehouseNo;      
    private int UseType;             
    private int CtrlNo;             
    private int AreaNo;              
    private int StockNo;             
    private String BoxNo;      
    private String Barcode;          
    private String SourceNo;   //任务号      
    
    
	public String getSourceNo() {
		return SourceNo;
	}
	public void setSourceNo(String sourceNo) {
		SourceNo = sourceNo;
	}
	public String getStrSessionID() {
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
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
	public int getUseType() {
		return UseType;
	}
	public void setUseType(int useType) {
		UseType = useType;
	}
	public int getCtrlNo() {
		return CtrlNo;
	}
	public void setCtrlNo(int ctrlNo) {
		CtrlNo = ctrlNo;
	}
	public int getAreaNo() {
		return AreaNo;
	}
	public void setAreaNo(int areaNo) {
		AreaNo = areaNo;
	}
	public int getStockNo() {
		return StockNo;
	}
	public void setStockNo(int stockNo) {
		StockNo = stockNo;
	}
	public String getBoxNo() {
		return BoxNo;
	}
	public void setBoxNo(String boxNo) {
		BoxNo = boxNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}   
}
