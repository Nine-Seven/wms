package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 验收检验应答 StuImCheckExistsAnswer
 * @author lich
 *
 */
public class IdataCheckExistsAnswer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warnType; //提示类型（0:成功 ;1：拦截，不允许验入；2：提醒，但可强制验入；3：授权 ）
    private String promptType;//提示类型（1-报警；2-冻结；3-超量）	
	public String getWarnType() {
		return warnType;
	}
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
	public String getPromptType() {
		return promptType;
	}
	public void setPromptType(String promptType) {
		this.promptType = promptType;
	}
    
    
}