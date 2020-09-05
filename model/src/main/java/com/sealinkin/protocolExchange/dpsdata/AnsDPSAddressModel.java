package com.sealinkin.protocolExchange.dpsdata;

import java.io.Serializable;
import java.util.List;

/**
        电子标签数据   StuDPSAddress
 * @author lich
 *
 */
public class AnsDPSAddressModel implements Serializable{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;              //标签数量
    private List<DPSAddressModel> lstDpsAddr;   //标签数组
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<DPSAddressModel> getLstDpsAddr() {
		return lstDpsAddr;
	}
	public void setLstDpsAddr(List<DPSAddressModel> lstDpsAddr) {
		this.lstDpsAddr = lstDpsAddr;
	}    
}
