/*
 * 工作站维护
 * zhouhuan 
 */
package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.bdef.service.IBdef_DefWorkstationService;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("serial")
public class Bdef_DefWorkstationAction extends CommAction {

	private IBdef_DefWorkstationService bdef_DefWorkstationImpl;

	private String strQuery;
	private MsgRes msgRes;
	private Integer requestFlag = 1;// 1：查询2：导出
	private String str;
	private String warehouseNo;
	private String workstationNo;
	private String printerGroupNo;
	private String workspaceNo;
	private String workSpaceName;//
	private String pageSql;

	/*
	 * 保存工作站
	 */
	public void saveOrUpdateWorkstation() {
		try {
			bdef_DefWorkstationImpl.add(getStr());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}

	/*
	 * * 获得工作站资料 * zhouhuan
	 */

	public void getWorkstationList() {
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_Printer_WorkstationModel> list = bdef_DefWorkstationImpl
					.getBdef_defWorkstationList(super.getMdBdef_DefWorker()
							.getCurrEnterpriseNo(), super.getMdBdef_DefWorker()
							.getWarehouseNo(), strQuery, pageBo, requestFlag);
			if (requestFlag == 1) {
				super.writeStr(covtObjectToJson(list));
			} else if (requestFlag == 2) {
				/*
				 * Map<String, String> map = new HashMap<String, String>();
				 * //map.put("cdate", "yyyy-MM-dd"); String title = "工作站资料";
				 * String[] threads = new String[]{"sheet1","工作站资料",
				 * "warehouseNo,workstationNo,workstationName,printerGroupNo,printerGroupName"
				 * , "仓别,工作站编号,工作站名称,打印机群组代码,打印机群组名称"}; commExportAction(title,
				 * threads, map, list.getRootList());
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除工作站 zhouhuan
	 */
	public void delete() {
		try {
			bdef_DefWorkstationImpl.delete(super.getMdBdef_DefWorker()
					.getCurrEnterpriseNo(), warehouseNo, workstationNo);
			msgRes = new MsgRes(true, "删除成功", getId());
		} catch (Exception e) {
			e.printStackTrace();
			msgRes = new MsgRes(false, "删除有误", getId());
		} finally {
			writeObj(msgRes);
		}
	}

	public void saveBset_Printer_Workstation() {
		try {
			bdef_DefWorkstationImpl.saveBset_Printer_Workstation(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}

	public void deleteBset_Printer_Workstation() {
		try {
			bdef_DefWorkstationImpl.deleteBset_Printer_Workstation(super
					.getMdBdef_DefWorker().getCurrEnterpriseNo(), warehouseNo,
					workstationNo, printerGroupNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkWorkstationNo() {
		try {
			String no = bdef_DefWorkstationImpl.checkWorkstationNo(super
					.getMdBdef_DefWorker().getCurrEnterpriseNo(), super
					.getMdBdef_DefWorker().getWarehouseNo(), workstationNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUserWorkSpaceNo() {
		Bdef_DefWorkerModel us = (Bdef_DefWorkerModel) HttpService.getRequest()
				.getSession().getAttribute("loginUser");
		if (workspaceNo != null) {
			us.setWorkSpaceNo(workspaceNo);
			us.setWorkSpaceName(workSpaceName);
			HttpService.pushToSession("loginUser", us);
			super.writeObj(new MsgRes(true, "工作站设置成功.", ""));
		}
	}

	// 获取工作站combo
	public void getWorkstationCombo() {
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_Printer_WorkstationModel> list = bdef_DefWorkstationImpl
					.getWorkstationCombo(super.getMdBdef_DefWorker()
							.getCurrEnterpriseNo(), super.getMdBdef_DefWorker()
							.getWarehouseNo(), strQuery, pageBo, requestFlag);

			super.writeStr(covtObjectToJson(list));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 工作站扫描校验 6-25
	public void checkWorkStation() {
		try {
			MsgRes msg = bdef_DefWorkstationImpl.checkWorkStation(super
					.getMdBdef_DefWorker().getCurrEnterpriseNo(), super
					.getMdBdef_DefWorker().getWarehouseNo(), this.getStr());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),
					""));
		}
	}
	//工作站编码模糊查询工作站
	public void getWorkStationInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefWorkstationImpl.getWorkStationInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),					
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//打印机组编码模糊查询
	public void getprinterGroupInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefWorkstationImpl.getprinterGroupInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),					
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getWorkstationNo() {
		return workstationNo;
	}

	public void setWorkstationNo(String workstationNo) {
		this.workstationNo = workstationNo;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public IBdef_DefWorkstationService getBdef_DefWorkstationImpl() {
		return bdef_DefWorkstationImpl;
	}

	public void setBdef_DefWorkstationImpl(
			IBdef_DefWorkstationService bdef_DefWorkstationImpl) {
		this.bdef_DefWorkstationImpl = bdef_DefWorkstationImpl;
	}

	public Integer getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getPrinterGroupNo() {
		return printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	public String getWorkspaceNo() {
		return workspaceNo;
	}

	public void setWorkspaceNo(String workspaceNo) {
		this.workspaceNo = workspaceNo;
	}

	public String getWorkSpaceName() {
		return workSpaceName;
	}

	public void setWorkSpaceName(String workSpaceName) {
		this.workSpaceName = workSpaceName;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getPageSql() {
		return pageSql;
	}

	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}

}
