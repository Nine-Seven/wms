package com.sealinkin.idata.action;


import java.io.File;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.service.Iidata_ImPort;
import com.sealinkin.util.ExceptionUtil;

public class Idata_ImPortAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private Iidata_ImPort idata_ImPortImpl;
	private String wheresql;
	private String importNo;
	private String importType;
	private String articleNo;
	private String poNo;
	private String strQuery;
	private String jsonMaster;
	private String jsonDetail1;
	private String jsonDetail2;
	private String jsonDetail3;
	private String saveType;
	private String strOwnerNo;
	private File file;
    private String ownerNo;
    private String printFlag;
    private String newPoNo;//海关下传的新单号 huangb 20160721
    private String userId;//huangb 20160721
	/**
	 * 获得进货通知单
	 * @throws Exception
	 */
	public void getImPort_MList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportMModel> list=idata_ImPortImpl.getImPort_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获得验收量  8-17
	public void getCheckNumber(){
		try {
			MsgRes msg = idata_ImPortImpl.getCheckNumber(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					this.getImportNo());
				super.writeObj(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"计算失败！",""));
		}
	}
	
	
	/**
	 * 获得进货通知单明细
	 * @throws Exception
	 */
	public void getImPort_DList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportDModel> list=idata_ImPortImpl.getImPort_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得进货通知单明细(天天惠)
	 * @throws Exception
	 */
	public void getImPortTth_DList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportDModel> list=idata_ImPortImpl.getImPortTth_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得进货直通配量（天天惠）
	 * @throws Exception
	 */
	public void getImPortAllot(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportDModel> list=idata_ImPortImpl.getImPortAllot(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql,articleNo,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存（进货手建单）
	 * @throws Exception
	 */
	public void saveImPort(){
		try {
			if(saveType.equals("0")){
				MsgRes msg=idata_ImPortImpl.saveImPort(jsonMaster, jsonDetail1);
				super.writeObj(msg);
			}else if(saveType.equals("1")){
				MsgRes msg=idata_ImPortImpl.saveImPort2(jsonMaster, jsonDetail2,jsonDetail3);
				super.writeObj(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	
	/**
	 * 天天惠保存（直通进货手建单）
	 * @throws Exception
	 */
	public void saveImPortTth(){
		try {
			if(saveType.equals("0")){
				MsgRes msg=idata_ImPortImpl.saveImPort(jsonMaster, jsonDetail1);
				super.writeObj(msg);
			}else if(saveType.equals("1")){
				MsgRes msg=idata_ImPortImpl.saveImPortTth2(jsonMaster, jsonDetail2,jsonDetail3,
						super.getMdBdef_DefWorker().getWorkSpaceNo());
				super.writeObj(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 天天惠保存分播明细
	 * @throws Exception
	 */
	public void saveImPortAllotTth(){
		try {			
				MsgRes msg=idata_ImPortImpl.saveImPortAllotTth(importNo,jsonDetail3);
				super.writeObj(msg);			
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 删除
	 * @throws Exception
	 */
	public void deleteImPort(){
		try {
			idata_ImPortImpl.deleteImPort(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					importNo);
			super.writeObj(new MsgRes(true,"数据删除成功！",""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"数据删除失败！",""));
		}
	}
	
	/**差异确认
	 * huangb 20160721
	 */
	public void tscDiffConfirm(){
		try {
			MsgRes msg = idata_ImPortImpl.tscDiffConfirm(
										super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
										super.getMdBdef_DefWorker().getWarehouseNo(),
										strOwnerNo,importNo,newPoNo,userId
										);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"差异确认异常",e.getMessage()));
		}
	}
	
	public void getImportArticle(){
		try {
			List<Idata_ImportDModel> list=idata_ImPortImpl.getImportArticle(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					articleNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkPoNo(){
		try {
			String po=idata_ImPortImpl.checkPoNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),poNo);
			super.writeStr(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrinter(){
		try {
			idata_ImPortImpl.updatePrinter(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					importNo, importType, super.getMdBdef_DefWorker().getWorkerNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void upLoad(){
		try {
			MsgRes msg = idata_ImPortImpl.upLoad(importType,
					file,super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	//取消订单
	public void closeOrder(){
		try {
			MsgRes msg = idata_ImPortImpl.closeOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					this.getImportNo(),this.getOwnerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	//集单打印
	public void setAndPrint(){
		try {
			MsgRes msg=idata_ImPortImpl.tscSetAndPrint(
				 super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				 super.getMdBdef_DefWorker().getWarehouseNo(),
				 super.getMdBdef_DefWorker().getWorkerNo(),
				 super.getMdBdef_DefWorker().getWorkSpaceNo(),
		         this.getImportNo(),
		         this.getPrintFlag());
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public void setIdata_ImPortImpl(Iidata_ImPort idataImPortImpl) {
		idata_ImPortImpl = idataImPortImpl;
	}
	
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}
	
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	public String getImportType() {
		return importType;
	}
	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}


	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}

	public String getJsonDetail1() {
		return jsonDetail1;
	}
	public void setJsonDetail1(String jsonDetail1) {
		this.jsonDetail1 = jsonDetail1;
	}

	public String getJsonDetail2() {
		return jsonDetail2;
	}
	public void setJsonDetail2(String jsonDetail2) {
		this.jsonDetail2 = jsonDetail2;
	}

	public String getJsonDetail3() {
		return jsonDetail3;
	}
	public void setJsonDetail3(String jsonDetail3) {
		this.jsonDetail3 = jsonDetail3;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getNewPoNo() {
		return newPoNo;
	}

	public void setNewPoNo(String newPoNo) {
		this.newPoNo = newPoNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}	
