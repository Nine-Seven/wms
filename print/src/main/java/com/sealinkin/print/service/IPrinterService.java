package com.sealinkin.print.service;

import java.util.List;

import com.sealinkin.comm.model.DefReportBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.model.PrintArgsBo;
import com.sealinkin.comm.model.RepairReportInfoBo;

public interface IPrinterService {

	/**
	 * 发送打印命令
	 * @param warehouseNo			仓储No
	 * @param workStationNo			工作站No
	 * @param sourceNo				单据编号
	 * @param reportId				报表ID
	 * @param rgstName				打印人
	 * @return
	 */
	public MsgRes sendPrintService(List<PrintArgsBo> list) throws Exception;
	
	/**
	 * 查询补印报表集合
	 * @return
	 */
	public List<DefReportBo> queryReportList(String reportType);
	
	/**
	 * 查询补印单据信息
	 * @param reportId
	 * @param reportType
	 * @param sheetId
	 * @param date
	 * @param hty 
	 * @param page
	 * @return
	 */
	public ExtListDataBo<RepairReportInfoBo> queryRepairSheetInfoBoList(
			String enterpriseNo,String wareHouseNo, String reportId ,String  reportType ,String sheetId,String date, String hty, PageBo page);
	
	/**
	 * 查询补印单据信息
	 * @param reportId
	 * @param reportType
	 * @param sheetId
	 * @param date
	 * @param page
	 * @return
	 */
	public ExtListDataBo<RepairReportInfoBo> queryLabelList(
			String enterpriseNo,
			String wareHouseNo, 
			String reportId ,
			String sheetId,
			String hty);

	//根据labelNo打印
	public MsgRes printLabel(String currEnterpriseNo, String warehouseNo,
			String dockNo, String workerNo,String labelNo)throws Exception;
}
