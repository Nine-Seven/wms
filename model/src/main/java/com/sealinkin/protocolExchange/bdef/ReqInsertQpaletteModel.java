package com.sealinkin.protocolExchange.bdef;
import java.io.Serializable;
@SuppressWarnings("serial")

/**
 * 堆叠采集》新增商品条码
 */
public class ReqInsertQpaletteModel implements Serializable {

	private static final long serialVersionUID = 5379409405903908717L;
	private String barcode;
	private String ownerNo;
	private String articleNo;
	private String primaryFlag;
	private Double packingQty;
	private String createFlag;
	private String rgstName;
	private String rgstdate;
	private String updtName;
	private String updtDate;
	private String enterpriseNo;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getPrimaryFlag() {
		return primaryFlag;
	}
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public Double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}
	public String getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getRgstdate() {
		return rgstdate;
	}
	public void setRgstdate(String rgstdate) {
		this.rgstdate = rgstdate;
	}
	public String getUpdtName() {
		return updtName;
	}
	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}
	public String getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
