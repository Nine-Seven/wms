package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.service.IBset_Article_BarcodeService;
import com.sealinkin.bset.model.Bset_ArticleBarcodeDModel;
import com.sealinkin.bset.model.Bset_ArticleBarcodeMModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bset_Article_BarcodeAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IBset_Article_BarcodeService bset_Article_BarcodeImpl;
	private String wheresql;
	private String strOwnerNo;
	private String jsonMaster;
	private String jsonDetail;
	private String strWheresql;
	private String strQuery;// 查找时用得字段

	

	/**
	 * 获得条码信息采集头档
	 * @throws Exception
	 */
	public void getBset_Article_Barcode_MList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_ArticleBarcodeMModel> list=bset_Article_BarcodeImpl.getBset_Article_Barcode_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, 
					pageBo,
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得条码信息采集明细
	 * @throws Exception
	 */
	public void getBset_Article_Barcode_DList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_ArticleBarcodeDModel> list=bset_Article_BarcodeImpl.getBset_Article_Barcode_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存
	public void save(){
		try {
			MsgRes msg=bset_Article_BarcodeImpl.save(this.getJsonMaster(),this.getJsonDetail()) ;
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"","保存失败！"));
		}
	}

	//获取商品
	public void getArticle(){

		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = bset_Article_BarcodeImpl.getArticle(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					getStrQuery(), 
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	
	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	
	
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}
	

	public void setBset_Article_BarcodeImpl(
			IBset_Article_BarcodeService bset_Article_BarcodeImpl) {
		this.bset_Article_BarcodeImpl = bset_Article_BarcodeImpl;
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

	public IBset_Article_BarcodeService getBset_Article_BarcodeImpl() {
		return bset_Article_BarcodeImpl;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	
}	
