package com.sealinkin.cset.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.po.Cset_CellArticle;
import com.sealinkin.cset.service.ICset_CellArticleService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"rawtypes","unused"})
public class Cset_CellArticleAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private ICset_CellArticleService cset_CellArticleImpl;
	private String str;
	private MsgRes msgRes;
	private Cset_CellArticle cset_Cell_Article;
	private String strEnterpriseNo;
	private String strWarehouseNo;
	private String strOwnerNo;
	private String strArticleNo;
	private String strPickType;
	private String wheresql;
	private String strWheresql;
	private String strQuery;
	private Integer requestFlag = 1;//1：查询2：导出
	
	/*
	 * 保存、修改商品储位对应关系
	 */
	public void saveOrUpdateCset_Cell_Article(){
		try{					
			MsgRes msg=cset_CellArticleImpl.saveCset_Cell_Article(super.getMdBdef_DefWorker().getWorkerNo(),getStr());
			super.writeObj(msg);
		}catch (Exception  e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/*
	 * 获取商品储位对应关系列表
	 */
	public void getCset_Cell_ArticleList(){
		
		try{
			 if(requestFlag==1){
				 PageBo pageBo=new PageBo(getStart(),getLimit());
				 ExtListDataBo<Cset_CellArticleModel> list =cset_CellArticleImpl.getCset_Cell_ArticleList(
						         super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
								 super.getMdBdef_DefWorker().getWarehouseNo(),
							     super.getMdBdef_DefWorker().getWorkerOwner(),
							     strQuery, 
							     pageBo,
							     requestFlag);
				 super.writeStr(covtObjectToJson(list));
			 }else if(requestFlag==2){
				 List list= cset_CellArticleImpl.getCset_Cell_ArticleList2(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							super.getMdBdef_DefWorker().getWorkerOwner(),
							strQuery, 
							requestFlag);
					String title = "商品储位对应关系";
					String[] threads = new String[]{"sheet1","商品货位对应关系",
							"仓别,货主,货主商品编码,商品编码,商品名称,库区编码,储位编码," +
							"通道编码,储区编码,线路代码,包装数量,最大存储量（箱）,补货警示量（箱）," +
							"循环补货触发量（箱）,可用货位数,拣货类型"};			
					commExportAction(title, threads,list);
					/*Map<String, String> map = new HashMap<String, String>();
					//map.put("cdate", "yyyy-MM-dd");
					String title = "商品储位对应关系";
					String[] threads = new String[]{"sheet1","商品储位对应关系",
							"warehouseNo,ownerNo,ownerName,wareNo,areaNo," +
							"stockNo,cellNo,articleNo,articleName," +
							"packingQty,maxQtyA,alertQtyA,suppQtyA,keepCellsA," +
							"maxQtyNa,alertQtyNa,suppQtyNa,keepCells," +
							"pickType,rgstName,rgstDate,updtName," +
							"updtDate,ownerArticleNo",
							"仓别,委托业主编码,委托业主名称,库区编码,储区编码,通道编码," +
							"储位编码,商品编码,商品名称,包装数量,A类补货警示量," +
							"A类循环补货触发量,A类循环补货触发量,A类最大可用储位数,非A类最大存储量," +
							"非A类补货警示量,非A类循环补货触发量,非A类最大可用储位数," +
							"拣货位类型,建立人员,建立日期,更新人员," +
							"更新日期,业主商品编码"};
					commExportAction(title, threads, map, list.getRootList());*/
				}
		}catch(Exception e){
            e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 删除
	 * @throws Exception
	 */
	public void delete(){
		try{
			cset_CellArticleImpl.delete(
					getStrEnterpriseNo(),
					getStrWarehouseNo(),
					strOwnerNo,
					getStrArticleNo(),
					getStrPickType());
			msgRes = new MsgRes(true, "删除成功", getId());
		}catch(Exception e){
			e.printStackTrace();
			msgRes=new MsgRes(false,"删除有误",getId());
		}finally{
			writeObj(msgRes);
		}
	}
	
	/*
	 * 获得商品条码和商品编码
	 */
	public void getCset_BarcodeAndArticleNoCombo(){
		try{
			 List<Cset_CellArticleModel> list =
					 cset_CellArticleImpl.getCset_BarcodeAndArticleNoCombo(
						 super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						 super.getMdBdef_DefWorker().getWorkerOwner(),getWheresql());
			 super.writeArray(list);
		}catch(Exception e){
            e.printStackTrace();
		}
	}
	
	/*
	 * 获得拣货类型
	 */
	public void getOtypeo(){
		try{
			List list=cset_CellArticleImpl.getOtypeo(
						 super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						 super.getMdBdef_DefWorker().getWarehouseNo());
			 super.writeArray(list);
		}catch(Exception e){
            e.printStackTrace();
		}
	}
	
	/**
	 * 校验新增商品储位对应关系是否重复
	 */
	public void existsCsetCellArticle(){
		try{		
			ExtListDataBo<Cset_CellArticleModel> list=cset_CellArticleImpl.existsCsetCellArticle(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			if(list.getRootList().size()>0){
				super.writeObj(new MsgRes(false, "该商品储位对应关系已存在，请重新输入！", ""));
			}else{
				super.writeObj(new MsgRes(true, "", ""));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验对应的拣货类型下是否有储区
	 */
	public void existsAreaList(){
		try{		
			ExtListDataBo<ComboxBo> list=cset_CellArticleImpl.existsAreaList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			if(list.getRootList().size()<=0){
				super.writeObj(new MsgRes(false, "在对应的拣货类型下没有储区，请重新选择！", ""));
			}else{
				super.writeObj(new MsgRes(true, "", ""));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 线路下拉
	 */
	public void queryLineCombo(){
		try {
			List<ComboxBo> list = cset_CellArticleImpl.queryLineCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取STOCK_X
	 */
	public void queryStoctX(){
		try {
			List<String> list = cset_CellArticleImpl.queryStoctX(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), "", wheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取货主商品编码下拉
	 */
	public void queryOwnerArticleNoList(){
		try {
			List<ComboxBo> list = cset_CellArticleImpl.queryOwnerArticleNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strOwnerNo, strWheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public Cset_CellArticle getCset_Cell_Article() {
		return cset_Cell_Article;
	}

	public void setCset_Cell_Article(Cset_CellArticle cset_Cell_Article) {
		this.cset_Cell_Article = cset_Cell_Article;
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

	public Integer getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public void setCset_CellArticleImpl(
			ICset_CellArticleService cset_CellArticleImpl) {
		this.cset_CellArticleImpl = cset_CellArticleImpl;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrWarehouseNo() {
		return strWarehouseNo;
	}

	public void setStrWarehouseNo(String strWarehouseNo) {
		this.strWarehouseNo = strWarehouseNo;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}

	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}

	public String getStrPickType() {
		return strPickType;
	}

	public void setStrPickType(String strPickType) {
		this.strPickType = strPickType;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrEnterpriseNo() {
		return strEnterpriseNo;
	}

	public void setStrEnterpriseNo(String strEnterpriseNo) {
		this.strEnterpriseNo = strEnterpriseNo;
	}
	
}
