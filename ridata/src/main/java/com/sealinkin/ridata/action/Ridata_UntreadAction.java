package com.sealinkin.ridata.action;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_UntreadDModel;
import com.sealinkin.ridata.model.Ridata_UntreadMModel;
import com.sealinkin.ridata.service.IRidata_UntreadService;
import com.sealinkin.util.ExceptionUtil;


public class Ridata_UntreadAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IRidata_UntreadService ridata_UntreadImpl;
	private String wheresql;
	private String jsonMaster;
	private String jsonDetail;
	private String articleNo;
	private String untreadNo;
	private String SUntreadNo;
	private String poNo;
	private String strPageSql;
	private String strQuery;
	private String strOwnerNo;
	private String poType;
	private File file;
	/**
	 * 返配手建单列
	 * @throws Exception
	 */
	public void getRidata_UntreadMList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_UntreadMModel> list=ridata_UntreadImpl.
			getRidata_UntreadMList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取消订单			 
	public void cancelOrder(){
		try {
			MsgRes msg = ridata_UntreadImpl.cancelOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),super.getMdBdef_DefWorker().getWorkerNo(),untreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获得验收量  8-17
	public void getCheckNumber(){
		try {
			MsgRes msg = ridata_UntreadImpl.getCheckNumber(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					this.getUntreadNo());
				super.writeObj(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"计算失败！",""));
		}
	}	
	
	
	/**
	 * 返配手建单明细
	 * @throws Exception
	 */
	public void getRidata_UntreadDList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_UntreadDModel> list=ridata_UntreadImpl.getRidata_UntreadDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveRidata_Untread(){
		try {
			MsgRes msg=ridata_UntreadImpl.saveRidata_Untread(jsonMaster, jsonDetail,
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	
	public void deleteUntread(){
		try {
			ridata_UntreadImpl.deleteUntread(untreadNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(new MsgRes(true,"数据删除成功！",""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"数据删除失败！",""));
		}
	}
	
	public void getUntreadArticle(){
		try {
			List<Ridata_UntreadDModel> list=ridata_UntreadImpl.getUntreadArticle(
					articleNo,super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获得原返配单号（返配扫描验收中有调用）
	public void getUntreadNoList(){
		try {
			List<ComboxBo> list=ridata_UntreadImpl.getUntreadNoList(
					strOwnerNo,
					super.getMdBdef_DefWorker().getWarehouseNo(),strPageSql,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),poType);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//打印后改状态
	/*public void updatePrinter(){
		try {
			System.out.println(SUntreadNo);
			System.out.println(untreadNo);
			ridata_UntreadImpl.updatePrinter(
					untreadNo,SUntreadNo,
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo()
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void print(){
		MsgRes msgRes=null;
		try
		{	
			msgRes =ridata_UntreadImpl.print(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),poNo,untreadNo);
		}catch (Exception e) {
			msgRes = new MsgRes(false,e.getMessage(),"");
			e.printStackTrace();
		}	
		super.writeObj(msgRes);
	}
	//打印返配预计到货单
	public void printCheckPlan(){
		MsgRes msgRes=null;
		try
		{	
			msgRes =ridata_UntreadImpl.printCheckPlan(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					poNo,
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msgRes);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}	
	}
	
	public void checkUntreadNo(){

		try {
			String po=ridata_UntreadImpl.checkUntreadNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),poNo);
			super.writeStr(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	//上传
	public void upLoad(){
		try {
			MsgRes msg = ridata_UntreadImpl.upLoad(
					file,super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	//审空单
	public void tscEmptyList(){
		try {
			MsgRes msg=ridata_UntreadImpl.tscEmptyList(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void setRidata_UntreadImpl(IRidata_UntreadService ridataUntreadImpl) {
		ridata_UntreadImpl = ridataUntreadImpl;
	}
	
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getUntreadNo() {
		return untreadNo;
	}
	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}

	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}

	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}

	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getStrPageSql() {
		return strPageSql;
	}

	public void setStrPageSql(String strPageSql) {
		this.strPageSql = strPageSql;
	}

	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
