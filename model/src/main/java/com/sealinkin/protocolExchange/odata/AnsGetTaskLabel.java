package com.sealinkin.protocolExchange.odata;
import java.io.Serializable;
/**
 *  读取用户当前区域任务应答（ StuAnsGetTaskLabel）
 * @author wyf
 *
 */
public class AnsGetTaskLabel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String LabelNo;          //

	public String getLabelNo(){
   	 return LabelNo;
    }
    public void setLabelNo(String LabelNo){
   	 this.LabelNo=LabelNo;
    }
}
