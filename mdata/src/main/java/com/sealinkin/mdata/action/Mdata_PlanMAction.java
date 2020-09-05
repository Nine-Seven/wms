package com.sealinkin.mdata.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.cset.po.Cset_CellArticle;
import com.sealinkin.mdata.model.Mdata_PlanMTmpModel;
import com.sealinkin.mdata.service.IMdata_PlanMService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.ExceptionUtil;


@SuppressWarnings("serial")
public class Mdata_PlanMAction extends CommAction{
	//private static final Logger logger = Logger.getLogger(Mdata_Plan_MAction.class);
	private IMdata_PlanMService mdata_PlanMImpl;
	private MsgRes msgRes;
	private Cset_CellArticle cset_CellAriticle;
	private String wheresql;
	private String str;
	private String workerNo;
	private String flag;
	private String warehouseNo;
	private Boolean str1 ;
	private String str2;
	private String barcode;
    private String ownerArticleNo;
    private Integer requestFlag = 1;//1：查询2：导出
    private String strWheresql;
    private String oldData;
    private File file;
    
	

	/*
	 * 获取安全量补货信息
	 * zhouhuan
	 */
	public void getCset_CellArticleList(){
		try{
			ExtListDataBo<Stock_ContentModel> list=mdata_PlanMImpl.getCset_CellArticleList(
					getStr(),getStr1(),getStart(),getLimit(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得人工移库信息
	 * zhouhuan
	 */
	public void getStock_ContentList(){
		try{
			ExtListDataBo<Stock_ContentModel> list=mdata_PlanMImpl.getStockContentList(
					str,ownerArticleNo,barcode,getStart(),getLimit(),requestFlag,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getOldData());
			 if(requestFlag==1){
					super.writeStr(covtObjectToJson(list));
				}else if(requestFlag==2){
					/*Map<String, String> map = new HashMap<String, String>();
					//map.put("cdate", "yyyy-MM-dd");
					String title = "人工移库信息";
					String[] threads = new String[]{"sheet1","人工移库信息",
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
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得移库导入失败信息
	 * hkl
	 */
	public void movefailList(){
		try{
			ExtListDataBo<Mdata_PlanMTmpModel> list=mdata_PlanMImpl.movefailList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 移库手建单委托业主，拣货类型，仓区，储区代码,通道下拉
	 */
	public void getOdata_GetCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=mdata_PlanMImpl.getOdata_GetCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),getFlag(),getStr(),0,100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 移库手建单弹出窗口业主商品编码，来源储位，商品条码下拉
	 */
	public void getMdata_GetCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=mdata_PlanMImpl.getMdata_GetCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),getFlag(),str2,strWheresql,0,10);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 安全量补货，人工补货建单
	 * zhouhuan
	 */
	public void tscSend(){
		try {
			MsgRes msg=mdata_PlanMImpl.tscSend(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					workerNo,str,flag,
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false,"建单失败！",e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传Excel导入数据库
	 */
	public void upload(){
		try {
			MsgRes msg = mdata_PlanMImpl.cfgUpLoad(file,
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 储位下拉
	 */
	public void getCdef_DefCellCombo() {
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = mdata_PlanMImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), str, wheresql,
					0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Integer getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getWorkerNo() {
		return workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public IMdata_PlanMService getMdata_PlanMImpl() {
		return mdata_PlanMImpl;
	}

	public void setMdata_PlanMImpl(IMdata_PlanMService mdata_PlanMImpl) {
		this.mdata_PlanMImpl = mdata_PlanMImpl;
	}

	public Cset_CellArticle getCset_CellAriticle() {
		return cset_CellAriticle;
	}

	public void setCset_CellAriticle(Cset_CellArticle cset_CellAriticle) {
		this.cset_CellAriticle = cset_CellAriticle;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getWheresql() {
		return wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public Boolean getStr1() {
		return str1;
	}

	public void setStr1(Boolean str1) {
		this.str1 = str1;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getOldData() {
		return oldData;
	}

	public void setOldData(String oldData) {
		this.oldData = oldData;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
