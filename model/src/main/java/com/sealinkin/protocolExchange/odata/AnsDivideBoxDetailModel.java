package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 返回当前箱最少分播区域及条码列表 对应StuDivideBoxDetail
 * @author lich
 *
 */
public class AnsDivideBoxDetailModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Short ctrlno;
	private Short address;
    private String sourceno;
    private String custno;
    private String batchno;
    private String dpsCellNo;
    private String asorterchuteno;
    private String articleno;
    private String barcode;
    private Float packingqty;
    private Integer celldisplength;
    private Integer cellstartpos;
    private Integer dpsdisplength;
    private String deliverobj;
    private Integer qminoperatepacking;
    private Double qty;
	public Short getCtrlno() {
		return ctrlno;
	}
	public void setCtrlno(Short ctrlno) {
		this.ctrlno = ctrlno;
	}
	public Short getAddress() {
		return address;
	}
	public void setAddress(Short address) {
		this.address = address;
	}
	public String getSourceno() {
		return sourceno;
	}
	public void setSourceno(String sourceno) {
		this.sourceno = sourceno;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getAsorterchuteno() {
		return asorterchuteno;
	}
	public void setAsorterchuteno(String asorterchuteno) {
		this.asorterchuteno = asorterchuteno;
	}
	public String getArticleno() {
		return articleno;
	}
	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Float getPackingqty() {
		return packingqty;
	}
	public void setPackingqty(Float packingqty) {
		this.packingqty = packingqty;
	}
	public Integer getCelldisplength() {
		return celldisplength;
	}
	public void setCelldisplength(Integer celldisplength) {
		this.celldisplength = celldisplength;
	}
	public Integer getCellstartpos() {
		return cellstartpos;
	}
	public void setCellstartpos(Integer cellstartpos) {
		this.cellstartpos = cellstartpos;
	}
	public Integer getDpsdisplength() {
		return dpsdisplength;
	}
	public void setDpsdisplength(Integer dpsdisplength) {
		this.dpsdisplength = dpsdisplength;
	}
	public String getDeliverobj() {
		return deliverobj;
	}
	public void setDeliverobj(String deliverobj) {
		this.deliverobj = deliverobj;
	}
	public Integer getQminoperatepacking() {
		return qminoperatepacking;
	}
	public void setQminoperatepacking(Integer qminoperatepacking) {
		this.qminoperatepacking = qminoperatepacking;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getDpsCellNo() {
		return dpsCellNo;
	}
	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}    
	
}
