package com.sealinkin.protocolExchange.bdef;


/**储位采集-判断拣货位的合法性返回参数
 * huangb 20160526
 */
public class AnsCheckPickCellModel {
	private String cellArticleNo;//储位对应的商品
	private String lineNo;//拣货线
	private String lineName;//拣货线名称
	private String deleteFlag;//是否删除当前商品储位对应关系标识
	
	public String getCellArticleNo() {
		return cellArticleNo;
	}
	public void setCellArticleNo(String cellArticleNo) {
		this.cellArticleNo = cellArticleNo;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
