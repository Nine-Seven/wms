package com.sealinkin.sodata.action;

import java.io.File;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.sodata.model.Sodata_WasteDModel;
import com.sealinkin.sodata.model.Sodata_WasteMModel;
import com.sealinkin.sodata.service.ISodata_WasteService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.ExceptionUtil;

/**
 * 报损中心
 * @author hekangli
 *
 */

public class Sodata_WasteAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private ISodata_WasteService sodata_WasteServiceImpl;
	private String wheresql;
	private String wasteM;
	private String wasteD;
	private String strQuery;
	private String poNo;
	private String wasteNo;
	private String ownerNo;
	private File file;

	private String articleNo;
	private String packingQty;
	private String type;
	

   
    //获取报损中心手建单头档
	public void getWaste_MList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Sodata_WasteMModel> list=sodata_WasteServiceImpl.getWaste_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//7-14添加      修改
	public void changeWaste(){
		try {
			MsgRes msg=sodata_WasteServiceImpl.changeWaste(wasteM, wasteD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	
	//获取报损明细
	public void getWaste_DList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Sodata_WasteDModel> list=sodata_WasteServiceImpl.getWaste_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取报损手建单库存
	public void getSodataWasteList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Stock_ContentModel> list=sodata_WasteServiceImpl.getSodataWasteList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveWaste(){
		try {
			MsgRes msg=sodata_WasteServiceImpl.saveWaste(wasteM, wasteD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	
	//修改报损手建单，暂不支持
	/*public void changeExp(){
		try {
			MsgRes msg=sodata_WasteServiceImpl.changeExp(expM, expD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}*/

/*	public void deleteExp(){
		try {
			sodata_WasteServiceImpl.deleteExp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					expNo);
			super.writeObj(new MsgRes(true,"数据删除成功！",""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"数据删除失败！",""));
		}
	}
	*/
	//校验单号是否存在
	public void checkWatesNo(){
		try {
			String po=sodata_WasteServiceImpl.checkWatesNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), poNo);
			super.writeStr(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取包装
	public void getPackingUnit(){
		try {
			List<Bdef_ArticlePackingModel> list=sodata_WasteServiceImpl.getPackingUnit(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),articleNo, packingQty,type);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//获取商品
	public void getArticle(){
		try {
			List<Idata_ImportDModel> list=sodata_WasteServiceImpl.getArticle(articleNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//发单
	public void tscBillPrint(){
		try {
			MsgRes msg = sodata_WasteServiceImpl.tscBillPrint(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					wasteNo,ownerNo,type
					);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//导入
	public void upLoad(){
		try {
			MsgRes msg = sodata_WasteServiceImpl.tscUpLoad(
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
	//取消订单
	public void closeOrder(){
		try {
			MsgRes msg = sodata_WasteServiceImpl.closeOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					this.getWasteNo(),this.getOwnerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取系统参数判断WMS手建单是否要ERP审核
	public void getSodataAuditFlag(){
		try 
		{
			String max=sodata_WasteServiceImpl.getSodataAuditFlag(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					ownerNo);
			super.writeStr(max);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public ISodata_WasteService getSodata_WasteServiceImpl() {
		return sodata_WasteServiceImpl;
	}
	public void setSodata_WasteServiceImpl(
			ISodata_WasteService sodata_WasteServiceImpl) {
		this.sodata_WasteServiceImpl = sodata_WasteServiceImpl;
	}
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}
	
	public String getWasteM() {
		return wasteM;
	}

	public void setWasteM(String wasteM) {
		this.wasteM = wasteM;
	}

	public String getWasteD() {
		return wasteD;
	}

	public void setWasteD(String wasteD) {
		this.wasteD = wasteD;
	}


	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getPackingQty() {
		return packingQty;
	}

	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getWasteNo() {
		return wasteNo;
	}

	public void setWasteNo(String wasteNo) {
		this.wasteNo = wasteNo;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
