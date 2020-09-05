package com.sealinkin.protocolExchange.odata;

public class OdataSkuLabelCancelReq {
	private String enterpriseNo;//企业编码
	private String wareHouseNo; //仓别号
	   private String labelNo;// 标签号
	   private String barcode; //商品条码
	    private String labelProduceFlag;//标签生产日期标记
	    
		public String getEnterpriseNo() {
			return enterpriseNo;
		}
		public void setEnterpriseNo(String enterpriseNo) {
			this.enterpriseNo = enterpriseNo;
		}
		public String getWareHouseNo() {
			return wareHouseNo;
		}
		public void setWareHouseNo(String wareHouseNo) {
			this.wareHouseNo = wareHouseNo;
		}
		public String getLabelNo() {
			return labelNo;
		}
		public void setLabelNo(String labelNo) {
			this.labelNo = labelNo;
		}
		public String getBarcode() {
			return barcode;
		}
		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}
		public String getLabelProduceFlag() {
			return labelProduceFlag;
		}
		public void setLabelProduceFlag(String labelProduceFlag) {
			this.labelProduceFlag = labelProduceFlag;
		}
}
