package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defcartype;

public class Bdef_DefcartypeModel extends Bdef_Defcartype{

	private static final long serialVersionUID = 1L;
	
	private String cartypeNo;
	private String enterpriseNo;
	public String getCartypeNo() {
		return cartypeNo;
	}
	public void setCartypeNo(String cartypeNo) {
		this.cartypeNo = cartypeNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}
