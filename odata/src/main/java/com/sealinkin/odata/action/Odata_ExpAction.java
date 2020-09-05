package com.sealinkin.odata.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_ExpService;
import com.sealinkin.util.ExceptionUtil;

public class Odata_ExpAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IOdata_ExpService odata_ExpImpl;
	private String wheresql;
	private String expM;
	private String expD;
	private String expNo;
	private String expType;
	private String strQuery;
	private String strOwner;
	private String poNo;
	private String articleNo;
	private String packingQty;
	private String type;
    private String strCustNo;
	private String pageSql;
	private File file;
	private String flag;
	private String sourceexpNo;
	private String ownerNo;
	private String strExpNothy;
	private String strDeliverNo;
	private String strShopNo;
	private String strSkucount;
	private String strCustExpNo;
	private String strSendName;
	private String strReceiveName;
	private String strTakeName;
	
	private String strErpoperateDate1;
	private String strErpoperateDate2;
	private String strCustsendDate1;
	private String strCustsendDate2;
	private String strLastCustsendDate1;
	private String strLastCustsendDate2;
	
	//打印拣货单
	public void tscPrintPickingNo(){
		try
		{	
			MsgRes msgRes =odata_ExpImpl.tscPrintPickingNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					expNo,type,ownerNo,strQuery);
			super.writeObj(msgRes);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
		
	}
	
	
    public void getExp_MCombo(){
    	try {
		List<ComboxBo> list=odata_ExpImpl.getExp_MCombo(
				super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),pageSql);
		writeArray(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    }
	public void getExp_MList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpMModel> list=odata_ExpImpl.getExp_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo,strExpNothy,expNo,poNo,
					strDeliverNo,
					strShopNo,
					strSkucount,
					strCustExpNo,
					strSendName,
					strReceiveName,
					strTakeName,
					strErpoperateDate1,
					strErpoperateDate2,
					strCustsendDate1,
					strCustsendDate2,
					strLastCustsendDate1,
					strLastCustsendDate2
					);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getExp_DList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpDModel> list=odata_ExpImpl.getExp_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveExp(){
		try {
			MsgRes msg=odata_ExpImpl.saveExp(expM, expD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void changeExp(){
		try {
			MsgRes msg=odata_ExpImpl.changeExp(expM, expD);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	//回写进货单头档品项数
	public void updateExpM(){
			try
			{	
				MsgRes msg = odata_ExpImpl.updateExpM(
                 super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
                 super.getMdBdef_DefWorker().getWarehouseNo(),
                 ownerNo,
                 expNo) ;
				super.writeObj(msg);
			}catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}	
		}
	//校验该单是否已经定过位
	public void editExp(){
		try {
			MsgRes msg = odata_ExpImpl.editExp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					expNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteExp(){
		try {
			odata_ExpImpl.deleteExp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					expNo);
			super.writeObj(new MsgRes(true,"数据删除成功！",""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"数据删除失败！",""));
		}
	}
	
	public void checkPoNo(){
		try {
			String po=odata_ExpImpl.checkPoNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), poNo);
			super.writeStr(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getPackingUnit(){
		try {
			List<Bdef_ArticlePackingModel> list=odata_ExpImpl.getPackingUnit(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),articleNo, packingQty,type);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 填充客户
	 */
	public void queryCust(){
		try {
			List<Odata_ExpMModel> list = odata_ExpImpl.queryCust(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strCustNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getArticle(){
		try {
			List<Idata_ImportDModel> list=odata_ExpImpl.getArticle(articleNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传Excel导入数据库
	 */
	public void upload(){
		try {
			MsgRes msg = odata_ExpImpl.upLoad(file,
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//关单或取消订单
	public void closeOrder(){
		try {
			MsgRes msg = odata_ExpImpl.closeOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),super.getMdBdef_DefWorker().getWorkerNo(),this.getExpNo(),this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//取消订单			7-12
	public void cancelOrder(){
		try {
			MsgRes msg = odata_ExpImpl.cancelOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),super.getMdBdef_DefWorker().getWorkerNo(),this.getExpNo(),this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	
	//校验该货主是否为大货位管理
	public void checkCell(){
		try{
			MsgRes msg = odata_ExpImpl.checkCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					ownerNo					
					);
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 检查是否有商品缺量
	 */
	public void checkNoEnoght(){
		try {
			MsgRes msg = odata_ExpImpl.checkNoEnoght(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 写出货单状态跟踪
	 */
	public void tscExpStatus(){
		try {
			MsgRes msg = odata_ExpImpl.tscExpStatus(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),expNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获得出货申请单商品详细信息列表
	public void getOrder_GoodsDetailList(){
		try {
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Odata_ExpDModel> list = odata_ExpImpl.getOrder_GoodsDetailList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), 
						super.getMdBdef_DefWorker().getWorkerOwner(), 
						strQuery, 
						pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获得出货查询所有出货申请单列表
	public void getAllOrderList(){
		try {
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Odata_ExpDModel> list = odata_ExpImpl.getAllOrderList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), 
						super.getMdBdef_DefWorker().getWorkerOwner(), 
						strQuery, 
						pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取单据类型下拉（公用）
	public void queryExpTypeCombo(){
		try{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = odata_ExpImpl.queryExpTypeCombo(
			    	super.getMdBdef_DefWorker().getCurrEnterpriseNo(),flag
			    	);
			super.writeArray(list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void setOdata_ExpImpl(IOdata_ExpService odataExpImpl) {
		odata_ExpImpl = odataExpImpl;
	}

	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getExpM() {
		return expM;
	}
	public void setExpM(String expM) {
		this.expM = expM;
	}

	public String getExpD() {
		return expD;
	}
	public void setExpD(String expD) {
		this.expD = expD;
	}
	
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
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

	public String getStrCustNo() {
		return strCustNo;
	}
	public void setStrCustNo(String strCustNo) {
		this.strCustNo = strCustNo;
	}
	public String getPageSql() {
		return pageSql;
	}
	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}

	public String getStrOwner() {
		return strOwner;
	}
	public void setStrOwner(String strOwner) {
		this.strOwner = strOwner;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSourceexpNo() {
		return sourceexpNo;
	}
	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}


	public String getStrDeliverNo() {
		return strDeliverNo;
	}


	public void setStrDeliverNo(String strDeliverNo) {
		this.strDeliverNo = strDeliverNo;
	}


	public String getStrShopNo() {
		return strShopNo;
	}


	public void setStrShopNo(String strShopNo) {
		this.strShopNo = strShopNo;
	}


	public String getStrSkucount() {
		return strSkucount;
	}


	public void setStrSkucount(String strSkucount) {
		this.strSkucount = strSkucount;
	}


	public String getStrCustExpNo() {
		return strCustExpNo;
	}


	public void setStrCustExpNo(String strCustExpNo) {
		this.strCustExpNo = strCustExpNo;
	}


	public String getStrSendName() {
		return strSendName;
	}


	public void setStrSendName(String strSendName) {
		this.strSendName = strSendName;
	}


	public String getStrReceiveName() {
		return strReceiveName;
	}


	public void setStrReceiveName(String strReceiveName) {
		this.strReceiveName = strReceiveName;
	}


	public String getStrTakeName() {
		return strTakeName;
	}


	public void setStrTakeName(String strTakeName) {
		this.strTakeName = strTakeName;
	}


	public String getStrErpoperateDate1() {
		return strErpoperateDate1;
	}


	public void setStrErpoperateDate1(String strErpoperateDate1) {
		this.strErpoperateDate1 = strErpoperateDate1;
	}


	public String getStrErpoperateDate2() {
		return strErpoperateDate2;
	}


	public void setStrErpoperateDate2(String strErpoperateDate2) {
		this.strErpoperateDate2 = strErpoperateDate2;
	}


	public String getStrCustsendDate1() {
		return strCustsendDate1;
	}


	public void setStrCustsendDate1(String strCustsendDate1) {
		this.strCustsendDate1 = strCustsendDate1;
	}


	public String getStrCustsendDate2() {
		return strCustsendDate2;
	}


	public void setStrCustsendDate2(String strCustsendDate2) {
		this.strCustsendDate2 = strCustsendDate2;
	}


	public String getStrLastCustsendDate1() {
		return strLastCustsendDate1;
	}


	public void setStrLastCustsendDate1(String strLastCustsendDate1) {
		this.strLastCustsendDate1 = strLastCustsendDate1;
	}


	public String getStrLastCustsendDate2() {
		return strLastCustsendDate2;
	}


	public void setStrLastCustsendDate2(String strLastCustsendDate2) {
		this.strLastCustsendDate2 = strLastCustsendDate2;
	}


	public String getStrExpNothy() {
		return strExpNothy;
	}


	public void setStrExpNothy(String strExpNothy) {
		this.strExpNothy = strExpNothy;
	}


	
	
}
