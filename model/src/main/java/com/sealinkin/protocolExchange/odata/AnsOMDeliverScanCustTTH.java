package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 * 装车参数结构 对应stuOMDeliverScanCustAns
 * @author wyf
 *
 */
public class AnsOMDeliverScanCustTTH implements Serializable{

	private static final long serialVersionUID = 1L;
    private String CustNo;//客户编码
    private String CustName;//客户名称
    private Integer LabelQty;//已扫标签数
    private Integer Qty;//未扫标签数
    private String MatchNo;//派车单号
    private String  loadtype; //封车标识1：可封车；0：不可封车 
    private String DeliverObj;//配送对象
    private String   resurt;
    private String recust;
    public String getRecust() {
		return recust;
	}
	public void setRecust(String recust) {
		this.recust = recust;
	}
	public String getResurt() {
		return resurt;
	}
	public void setResurt(String resurt) {
		this.resurt = resurt;
	}
	private String loadCarLevel;
    private String loadproposeNo;
    public String getLoadCarLevel() {
		return loadCarLevel;
	}
	public void setLoadCarLevel(String loadCarLevel) {
		this.loadCarLevel = loadCarLevel;
	}
	public String getLoadproposeNo() {
		return loadproposeNo;
	}
	public void setLoadproposeNo(String loadproposeNo) {
		this.loadproposeNo = loadproposeNo;
	}

	public String getDeliverObj() {
		return DeliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		DeliverObj = deliverObj;
	}
	public String getLoadtype() {
		return loadtype;
	}
	public void setLoadtype(String loadtype) {
		this.loadtype = loadtype;
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
	public void setCustName(String CustName) {
		this.CustName = CustName;
	}
	
	public Integer getLabelQty()
	{
		return LabelQty;
	}
	public void setLabelQty(Integer labelQty)
	{
		this.LabelQty = labelQty;
	}
	
	public Integer getQty()
	{
		return Qty;
	}
	public void setQty(Integer Qty)
	{
		this.Qty = Qty;
	}
	public String getMatchNo() {
		return MatchNo;
	}
	public void setMatchNo(String matchNo) {
		MatchNo = matchNo;
	}
}
