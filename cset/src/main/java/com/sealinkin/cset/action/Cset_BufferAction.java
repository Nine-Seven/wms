package com.sealinkin.cset.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_BufferModel;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.po.Cset_CellArticle;
import com.sealinkin.cset.service.ICset_BufferService;
import com.sealinkin.cset.service.ICset_CellArticleService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"rawtypes","unused"})
public class Cset_BufferAction extends CommAction{
	private ICset_BufferService cset_BufferImpl;
	
	private String str;
	private String strQuery;
	private String strwareNo;
	private String strareaNo;
	private String strstockNo;
	private String strcellNo;
	private String strbufferName;
	public String getStrbufferName() {
		return strbufferName;
	}

	public void setStrbufferName(String strbufferName) {
		this.strbufferName = strbufferName;
	}

	public String getStrstatus() {
		return strstatus;
	}

	public void setStrstatus(String strstatus) {
		this.strstatus = strstatus;
	}
	private String strstatus;
	

	public String getStrwareNo() {
		return strwareNo;
	}

	public void setStrwareNo(String strwareNo) {
		this.strwareNo = strwareNo;
	}

	public String getStrareaNo() {
		return strareaNo;
	}

	public void setStrareaNo(String strareaNo) {
		this.strareaNo = strareaNo;
	}

	public String getStrstockNo() {
		return strstockNo;
	}

	public void setStrstockNo(String strstockNo) {
		this.strstockNo = strstockNo;
	}

	public String getStrcellNo() {
		return strcellNo;
	}

	public void setStrcellNo(String strcellNo) {
		this.strcellNo = strcellNo;
	}
	private int  requestFlag;
	//获取暂存区维护信息
	public void getBufferList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			 ExtListDataBo<Cset_BufferModel> list =cset_BufferImpl.getBufferList(
					         super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							 super.getMdBdef_DefWorker().getWarehouseNo(),
						     super.getMdBdef_DefWorker().getWorkerOwner(),
						     getStrQuery(), 
						     pageBo,
						     requestFlag);
			
					super.writeStr(covtObjectToJson(list));
			
		}catch(Exception e){
            e.printStackTrace();
		}
	}

	// 删除库区    
    public void deleteBuffer(){
    	try {
			MsgRes msg=cset_BufferImpl.deleteBuffer(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					getStr(),getStrwareNo(),getStrareaNo(),getStrstockNo(),getStrcellNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
    }
    
	/**
     * 库区下拉
     */
	public void getBufferWareCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_BufferImpl.getBufferWareCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
     * 储区下拉
     * @author panzx 2016.03.15
     */
	public void getBufferAreaCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_BufferImpl.getBufferAreaCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	 
    /**
     * 通道下拉
     */
	
	public void getBufferStockCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_BufferImpl.getBufferStockCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 新增界面-》库区下拉
     */
	public void getBufferAddWareCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_BufferImpl.getBufferAddWareCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
     * 新增界面-》储区下拉
     * @author panzx 2016.03.15
     */
	public void getBufferAddAreaCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_BufferImpl.getBufferAddAreaCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	 
    /**
     * 新增界面-》通道下拉
     */
	
	public void getBufferAddStockCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cset_BufferImpl.getBufferAddStockCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 储位下拉
	 */
	public void getBufferAddCellCombo() {
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = cset_BufferImpl.getBufferAddCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),getStr(),getStrQuery());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 保存、修改暂存区	
		public void saveBuffer_Ware(){
			try{					
				MsgRes msg=cset_BufferImpl.saveBuffer_Ware(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						getStr(),super.getMdBdef_DefWorker().getWorkerNo(),
						getStrwareNo(),getStrareaNo(),getStrbufferName(),getStrstatus());
				super.writeObj(msg);
			}catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false,e.getMessage(),""));
			}
		}
		//判断暂存区货位是否唯一
		public void cellCheck(){
			try 
			{
				List<String> list = cset_BufferImpl.cellCheck(
						this.getMdBdef_DefWorker().getEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						getStrwareNo(),getStrareaNo(),getStrstockNo(),
						getStrQuery());	
				super.writeArray(list);
			} catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false,e.getMessage(),""));
			}
		}
	public ICset_BufferService getCset_BufferImpl() {
		return cset_BufferImpl;
	}

	public void setCset_BufferImpl(ICset_BufferService cset_BufferImpl) {
		this.cset_BufferImpl = cset_BufferImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public int getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(int requestFlag) {
		this.requestFlag = requestFlag;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
}
