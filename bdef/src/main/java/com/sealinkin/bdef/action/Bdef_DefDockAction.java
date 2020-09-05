/*
 * 码头资料维护
 * zhouhuan 
 */
package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefdockModel;
import com.sealinkin.bdef.service.IBdef_DefDockService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

@SuppressWarnings("serial")
public class Bdef_DefDockAction extends CommAction{
	//private static final Logger logger = Logger.getLogger(Bdef_DefDockAction.class);
	private IBdef_DefDockService bdef_DefDockImpl;
	private String str;
	private String strQuery;
	private MsgRes msgRes;
	private Integer requestFlag=1;
	private String dockNo;
	
	
	/*
	 * 保存码头
	 */
	public void saveOrUpdatedock(){
		try{	
			bdef_DefDockImpl.add(getStr());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	/*
	 * 获得码头资料
	 * zhouhuan
	 */
	public void getBdef_defDockList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefdockModel> list=bdef_DefDockImpl.getBdef_DefDockList(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strQuery, pageBo,requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				//map.put("cdate", "yyyy-MM-dd");
				String title = "码头资料";
				String[] threads = new String[]{"sheet1","码头资料",
					"warehouseNo,dockNo,dockName,dockTypeText," +
					"adjustBoardText,rgstName,rgstDate," +
					"updtName,updtDate,locateTypeText",
					"仓别编码,码头编码,码头名称,码头类型," +
					"是否有调节板,建立人员,建立日期," +
					"更新人员,更新日期定位类型"};
				commExportAction(title, threads, map, list.getRootList());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除码头资料
	 * zhouhuan
	 */
	public void delete(){
		try{
			bdef_DefDockImpl.delete(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),getDockNo());
			msgRes = new MsgRes(true, "删除成功", getId());
		}catch(Exception e){
			e.printStackTrace();
			msgRes=new MsgRes(false,"删除有误",getId());
		}finally{
			writeObj(msgRes);
		}
	}
	
	/*
	 * 获得码头下拉
	 * zhouhuan
	 */
	public void getDockComboList()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefDockImpl.getDockComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void existsDockNo(){
		try 
		{
			List<String> list = bdef_DefDockImpl.existsDockNo(this.getStr(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public MsgRes getMsgRes() {
		return msgRes;
	}
	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}
	public void init()
    {
        super.writeStr("cms.view.bdef.bdef_DefDockUI");
    }
	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	public void setBdef_DefDockImpl(IBdef_DefDockService bdef_DefDockImpl) {
		this.bdef_DefDockImpl = bdef_DefDockImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
}
