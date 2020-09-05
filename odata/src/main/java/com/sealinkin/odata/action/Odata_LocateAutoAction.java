/**
 * 模块名称：自动出货调度
 * 模块代码：3202
 * @author hkl
 */

package com.sealinkin.odata.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_ArrangeService;
import com.sealinkin.odata.service.IOdata_LocateAutoService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial","unused"})
public class Odata_LocateAutoAction extends CommAction{
	
	private static final Logger logger = Logger.getLogger(Odata_LocateAutoAction.class);
	private IOdata_LocateAutoService odata_LocateAutoServiceImpl;
	private String strWheresql;
	private String strFlag;
	private String calFlag;
	private String expNo;
	private String custNo;
	private String expType;
	private String ownerNo;
	private String strDetail;
	private String B2Cflag;
	/**
	 * 根据筛选的条件写临时表
	 */
	public void saveLocateSelectTem()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.saveLocateSelectTem(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql,strFlag,B2Cflag);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 获取临时表数据
	 */
	public void getLocateM()
	{
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpMModel> list=odata_LocateAutoServiceImpl.getLocateM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strFlag, poPageBo
					);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 修改临时表的数据为选中
	 */
	public void updateTmpLocateSelect1()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.updateTmpLocateSelect1(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strFlag,calFlag,expNo,custNo,expType,ownerNo);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 修改临时表的数据为不选中
	 */
	public void updateTmpLocateSelect0()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.updateTmpLocateSelect0(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strFlag,calFlag,expNo,custNo,expType,ownerNo);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 计算勾选的单据详情
	 */
	public void countDetail(){
		try {
			List<Odata_ExpMModel> list = odata_LocateAutoServiceImpl.countDetail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
			super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取月台可用货位数
	 */
	public void tscBufferQty()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.tscBufferQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strFlag);//strFlag为IP
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//删除临时表状态为0的数据
	public void deleteLocateSelect()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.deleteLocateSelect(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo()
					);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	//定位-切批次；集单；定位
	public void tscFixed(){
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.tscFixed(strDetail);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//批量修改状态
	public void updateTmpLocateSelectAll()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.updateTmpLocateSelectAll(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strWheresql,strFlag,calFlag
					
					);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	} 
	
	
	/**
	 * 根据筛选的条件写临时表(手动)
	 */
	public void saveLocateSelectTem_manual()
	{
		try 
		{
			MsgRes msg=odata_LocateAutoServiceImpl.saveLocateSelectTem_manual(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql,strFlag,B2Cflag);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	public IOdata_LocateAutoService getOdata_LocateAutoServiceImpl() {
		return odata_LocateAutoServiceImpl;
	}

	public void setOdata_LocateAutoServiceImpl(
			IOdata_LocateAutoService odata_LocateAutoServiceImpl) {
		this.odata_LocateAutoServiceImpl = odata_LocateAutoServiceImpl;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getCalFlag() {
		return calFlag;
	}
	public void setCalFlag(String calFlag) {
		this.calFlag = calFlag;
	}
	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}


	public String getStrDetail() {
		return strDetail;
	}


	public void setStrDetail(String strDetail) {
		this.strDetail = strDetail;
	}


	public String getB2Cflag() {
		return B2Cflag;
	}


	public void setB2Cflag(String b2Cflag) {
		B2Cflag = b2Cflag;
	}

	
}
