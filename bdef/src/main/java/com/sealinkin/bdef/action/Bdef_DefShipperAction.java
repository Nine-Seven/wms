package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefShipperModel;
import com.sealinkin.bdef.service.IBdef_DefShipperService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.oset.model.Oset_ShipperLineModel;
import com.sealinkin.util.ExceptionUtil;

/**
 * 承运商资料维护action
 * @author zhouhuan
 */
public class Bdef_DefShipperAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_DefShipperService bdef_DefShipperImpl;
	private String str;
	private String pageSql;
	private String strShipperNo;
	private String strLineNo;
	private String strQuery;
	private String strDetail;
	private Integer strRequestFlag = 1;//1：查询2：导出
	
	private String shipperNo;
	private MsgRes msgRes;
	
	private String strWheresql;
	
	/**
	 * 获得承运商资料维护列
	 * @throws Exception
	 */
	public void getDefShipperList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefShipperModel> list=bdef_DefShipperImpl.getDefShipperList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strQuery,pageBo,strRequestFlag);
			if(strRequestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(strRequestFlag==2){
				/*Map<String, String> map = new HashMap<String, String>();
				//map.put("cdate", "yyyy-MM-dd");
				String title = "客户资料";
				String[] threads = new String[]{"sheet1","客户资料",
						"ownerNo,ownerName,custtypeText,custAlias,ownerCustNo,custNo," +
						"custName,custProvince,custCity,custZone,custflagText," +
						"boxdeliverText,statusText,priotypeText,shippingmethodText,containermaterialText," +
						"collectflagText,onlydateflagText,warnflagText,deliveryAddress,custPhone1," +
						"custFax1,invoiceNo,contactorName1,maxCarTonnage,custEmail1," +
						"invoiceHeader,receivingHours,custPostcode,custaddress,invoiceAddr," +
						"remark",
						"委托业主编号,委托业主名称,客户类型,客户简称,委托业主客户代码,客户编号," +
						"客户名称,省份,城市,区域,客户标识," +
						"物流箱发货标记,状态,优先方式,客户出货方式,容器材质," +
						"是否采集材积,要求单一到期日,标签是否有特殊标记,送货地址,联系电话," +
						"客户传真,发票号,联系人,允许车辆最大吨位,客户EMAIL," +
						"发票抬头,收货时段,客户邮编,客户地址,发票地址," +
						"备注"};
				
				commExportAction(title, threads, map, list.getRootList());*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除承运商资料
	 * hj
	 */
	public void delete(){
		try{
			msgRes = bdef_DefShipperImpl.delete(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),super.getMdBdef_DefWorker().getWarehouseNo(),getShipperNo());
		}catch(Exception e){
			e.printStackTrace();
			msgRes=new MsgRes(false,"删除有误",getId());
		}finally{
			writeObj(msgRes);
		}
	}
	
	//填充承运商下拉控件 7-7添加  hj
	public void queryBdefDefShipperCombo()
	{
		try 
		{
			System.out.println("strWheresql:"+strWheresql);
			List<ComboxBo> list = bdef_DefShipperImpl.queryBdefDefShipperCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strWheresql, 
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得承运商资料列表
	 * @throws Exception
	 * hj
	 */
	public void getDefShipperMaintainList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			System.out.println("strQuery:"+strQuery);
			ExtListDataBo<Bdef_DefShipperModel> list=bdef_DefShipperImpl.getDefShipperMaintainList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strQuery,pageBo,strRequestFlag);
			if(strRequestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}else if(strRequestFlag==2){
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存或修改承运商
	 * @throws Exception
	 */
	public void saveOrUpdateShipper(){
		try {
			MsgRes msg = bdef_DefShipperImpl.saveOrUpdateShipper(str,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	/**
	 * 保存或修改承运商
	 * @throws Exception
	 * hj
	 */
	public void saveOrUpdateShipperMaintain(){
		try {
			MsgRes msg = bdef_DefShipperImpl.saveOrUpdateShipperMaintain(str,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	/**
	 * 填充报表id下拉
	 * hj
	 */
	public void queryReportIdCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefShipperImpl.queryReportIdCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),super.getMdBdef_DefWorker().getWorkerOwner(), "", "");
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void getCarCombo(){
		try {
			List<ComboxBo> list=bdef_DefShipperImpl.getCarCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					pageSql);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 承运商编号校验
	 */
	public void checkShipperNo(){
		try {
			String no = bdef_DefShipperImpl.checkShipperNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strShipperNo,super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取未分配的线路列表
	 */
	public void queryLineList(){
		try {
			ExtListDataBo<Oset_DeflineModel> list=bdef_DefShipperImpl.queryLineList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取承运商下的线路
	 */
	public void queryShipperLine(){
		try {
			ExtListDataBo<Oset_ShipperLineModel> list=bdef_DefShipperImpl.queryShipperLine(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strShipperNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除承运商关系
	 */
	public void deleteShipperLine(){
		try {
			MsgRes msg=bdef_DefShipperImpl.deleteShipperLine(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strShipperNo, strLineNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增承运商关系
	 */
	public void insertShipperLine(){
		try {
			MsgRes msg=bdef_DefShipperImpl.insertShipperLine(strDetail);
			super.writeObj(msg);
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
	
	public String getPageSql() {
		return pageSql;
	}
	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}
	
	public String getStrShipperNo() {
		return strShipperNo;
	}
	public void setStrShipperNo(String strShipperNo) {
		this.strShipperNo = strShipperNo;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public Integer getStrRequestFlag() {
		return strRequestFlag;
	}
	public void setStrRequestFlag(Integer strRequestFlag) {
		this.strRequestFlag = strRequestFlag;
	}
	public IBdef_DefShipperService getBdef_DefShipperImpl() {
		return bdef_DefShipperImpl;
	}
	public void setBdef_DefShipperImpl(IBdef_DefShipperService bdef_DefShipperImpl) {
		this.bdef_DefShipperImpl = bdef_DefShipperImpl;
	}

	public String getStrLineNo() {
		return strLineNo;
	}
	public void setStrLineNo(String strLineNo) {
		this.strLineNo = strLineNo;
	}

	public String getStrDetail() {
		return strDetail;
	}
	public void setStrDetail(String strDetail) {
		this.strDetail = strDetail;
	}

	public String getShipperNo() {
		return shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	
	
}
