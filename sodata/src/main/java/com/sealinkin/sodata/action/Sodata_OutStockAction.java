package com.sealinkin.sodata.action;

import java.io.File;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.sodata.model.Sodata_OutStockDModel;
import com.sealinkin.sodata.model.Sodata_OutStockMModel;
import com.sealinkin.sodata.model.Sodata_WasteDModel;
import com.sealinkin.sodata.model.Sodata_WasteMModel;
import com.sealinkin.sodata.service.ISodata_OutStockService;
import com.sealinkin.sodata.service.ISodata_WasteService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.ExceptionUtil;

/**
 * 报损回单
 * @author hekangli
 *
 */

public class Sodata_OutStockAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private ISodata_OutStockService sodata_OutStockServiceImpl;
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
	

   
    //获取报损回单头档
	public void getOutStock_MList(){
		try {
			ExtListDataBo<Sodata_OutStockMModel> list=
					sodata_OutStockServiceImpl.getOutStock_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取报损明细
	public void getOutStock_DList(){
		try {
			ExtListDataBo<Sodata_OutStockDModel> list=sodata_OutStockServiceImpl.getOutStock_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//回单
	public void save(){
		try {
			MsgRes msg = sodata_OutStockServiceImpl.save(
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strQuery,type
					);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	public ISodata_OutStockService getSodata_OutStockServiceImpl() {
		return sodata_OutStockServiceImpl;
	}

	public void setSodata_OutStockServiceImpl(
			ISodata_OutStockService sodata_OutStockServiceImpl) {
		this.sodata_OutStockServiceImpl = sodata_OutStockServiceImpl;
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
