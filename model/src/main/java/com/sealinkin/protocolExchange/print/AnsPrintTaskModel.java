package com.sealinkin.protocolExchange.print;

import java.io.Serializable;
import java.util.List;

/**
 * 获取报表详细信息 应答  StuAnsPrintTask
 * @author lich
 *
 */
public class AnsPrintTaskModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PrintTaskInfoModel> lstPrintTask;

	public List<PrintTaskInfoModel> getLstPrintTask() {
		return lstPrintTask;
	}

	public void setLstPrintTask(List<PrintTaskInfoModel> lstPrintTask) {
		this.lstPrintTask = lstPrintTask;
	}	
}
