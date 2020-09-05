/*
 * 分播墙回单
 * lich
 */

package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.odata.service.IOdata_DivideWallService;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial","rawtypes"})
public class Odata_DivideWallAction extends CommAction{
	private IOdata_DivideWallService odata_DivideWallImpl;
	private String str;
	private String strQuery;
	private String strEquipmentNo;
	private String strDeviceNo;		//分播墙号
	private String strWorkSpace;	//工作空间
	private String strArticleNo;
	private Double realQty;
	private String strDpsCellNo;
	private String strSource_No;	
	private String strLabelNo;		//标签号   7-18添加
		
	public IOdata_DivideWallService getOdata_DivideWallImpl() {
		return odata_DivideWallImpl;
	}
	
	public void setOdata_DivideWallImpl(
			IOdata_DivideWallService odata_DivideWallImpl) {
		this.odata_DivideWallImpl = odata_DivideWallImpl;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrEquipmentNo() {
		return strEquipmentNo;
	}

	public void setStrEquipmentNo(String strEquipmentNo) {
		this.strEquipmentNo = strEquipmentNo;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}

	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}

	public Double getRealQty() {
		return realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	public String getStrDpsCellNo() {
		return strDpsCellNo;
	}

	public void setStrDpsCellNo(String strDpsCellNo) {
		this.strDpsCellNo = strDpsCellNo;
	}

	public String getStrSource_No() {
		return strSource_No;
	}

	public void setStrSource_No(String strSource_No) {
		this.strSource_No = strSource_No;
	}

	public String getStrDeviceNo() {
		return strDeviceNo;
	}

	public void setStrDeviceNo(String strDeviceNo) {
		this.strDeviceNo = strDeviceNo;
	}

	public String getStrWorkSpace() {
		return strWorkSpace;
	}

	public void setStrWorkSpace(String strWorkSpace) {
		this.strWorkSpace = strWorkSpace;
	}
	
	public String getStrLabelNo() {
		return strLabelNo;
	}

	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
	}

	/**
     * 获取分播墙储位
     * lich
     */
	public void getOdata_DivideWallCell() 
	{
		try{
			System.out.println("str： " +this.getStr() );
			List list=odata_DivideWallImpl.getOdata_DivideWallCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
     * 获取分播组号
     * lich
     */
	public void getOdata_Divide_Direct() 
	{
		try{
			List<Stock_LabelMModel> list=odata_DivideWallImpl.getOdata_Divide_Direct(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
     * 根据条码获取分播单下品项
     * lich
     */
	public void getOdata_Divide_Art() 
	{
		try{
			List<ComboxBo> list=odata_DivideWallImpl.getOdata_Divide_Art(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrEquipmentNo(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	/**
     * 选择分播组号发单并返回分播单数据
     * lich
     */
	public void tscSelectEquipmentNo() 
	{
		try{
			System.out.println("墙号:"+this.getStrDeviceNo()+" 组号:"+getStrEquipmentNo()+" 工作空间:"+this.getStrWorkSpace() );
			//检查分播组号是否存在
			List<Stock_LabelMModel> list=odata_DivideWallImpl.getOdata_Divide_Direct(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrEquipmentNo());
			
			if(list.size()==0){
				super.writeObj(new MsgRes(false,"分播任务号不存在，请重新扫描！",""));
			}else{
				//发单并返回分播数据
				List<Odata_DivideDModel> listDivideD=odata_DivideWallImpl.tscSelectEquipmentNo(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),					
						getStrEquipmentNo(),
						this.getStrDeviceNo(),
						super.getMdBdef_DefWorker().getWorkerNo(),
						this.getStrWorkSpace());
				super.writeObj(new MsgRes(true,"",listDivideD));
			}
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
     * 选择商品后加载数据
     * lich
     */
	public void getOdata_Divide() 
	{
		try{
			//发单
			MsgRes msg=odata_DivideWallImpl.getOdata_Divide(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrEquipmentNo(),
					getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
     * 检查分播人员编号是否存在
     * hj
     */
	public void checkWorkerNo() 
	{
		try{
			MsgRes msg=odata_DivideWallImpl.checkWorkerNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
     * 从device_divide_m 获得 分播墙号device_no集合 
     * hj
     */
	public void getOdata_DivideWallDeviceNo() 
	{
		try{
			MsgRes msg=odata_DivideWallImpl.getOdata_DivideWallDeviceNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 通过标签号获得对应的商品信息  hj 7-1
	 */
	public void queryDivideDetailList()
	{
		try 
		{
			System.out.println("标签号:"+this.getStr());
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Odata_DivideDModel> pageListBo = odata_DivideWallImpl.queryDivideDetailList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(), 
					poPagebo);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
     * 成功验证储位后回单
     * lich
     */
	public void tscSaveDivide() 
	{
		try{
			//回单
			//System.out.println("strLabelNo: "+this.getStrLabelNo() );
			//System.out.println("分播单号: "+this.getStrEquipmentNo() );
			MsgRes msg=odata_DivideWallImpl.tscSaveDivide(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrEquipmentNo(),
					getStrLabelNo(),
					getStrArticleNo(),					
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			//System.out.println("msg:"+msg.getObj().toString());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
     * 释放单号
     * lich
     */
	public void tscSureDivide() 
	{
		try{
			//回单
			MsgRes msg=odata_DivideWallImpl.tscSureDivide(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrEquipmentNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	
	//分播墙号校验 6-25
	public void checkdeviceNo()
	{
		try{	
			MsgRes msg=odata_DivideWallImpl.checkdeviceNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	
}























