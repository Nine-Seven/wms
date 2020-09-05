package com.sealinkin.ridata.action;



import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.model.Ridata_CheckPalModel;
import com.sealinkin.ridata.service.IRidata_CheckConfirmService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 返配验收确认
 * @author hkl
 */
public class Ridata_CheckConfirmAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IRidata_CheckConfirmService Ridata_CheckConfirmImpl;
	private String strWheresql;
	private String strLotNo;
	private String strArticleNo;
	private String strJsonMaster;
	private String strJsonDetail1;
	private String strJsonDetail2;
	private String strLabelNo;
	private String strFlag;
	private String strOwnerNo;
	private String strQuery;
	private String strSUntreadNo;

	
	
	
	/**
	 * 获取验收确认单头档
	 */
	public void queryCheckM()
	{
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_CheckMModel> list=Ridata_CheckConfirmImpl.queryCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWheresql,
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取验收确认单明细
	 */
	public void queryCheckD()
	{
		try 
		{
			ExtListDataBo<Ridata_CheckDModel> list=Ridata_CheckConfirmImpl.queryCheckD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取验收确认箱明细
	 */
	public void queryCheckPal()
	{
		try 
		{
			ExtListDataBo<Ridata_CheckPalModel> list=Ridata_CheckConfirmImpl.queryCheckPal(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取验收确认未封箱明细
	 */
	public void queryUnCheckPal()
	{
		try 
		{
			ExtListDataBo<Ridata_CheckPalModel> list=Ridata_CheckConfirmImpl.queryUnCheckPal(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 验收确认
	 */
	public void checkConfirm()
	{
		try 
		{
			MsgRes msg=Ridata_CheckConfirmImpl.tscConfirm(strJsonMaster);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 *  校验临时表中是否还有数据
	 */
	public void checkPalTmp(){
		try {
			MsgRes msg = Ridata_CheckConfirmImpl.checkPalTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strSUntreadNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  校验计划数量和验收数量是否相等
	 */
	public void checkQty(){
		try {
			MsgRes msg = Ridata_CheckConfirmImpl.checkQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strWheresql);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	
	public String getStrSUntreadNo() {
		return strSUntreadNo;
	}

	public void setStrSUntreadNo(String strSUntreadNo) {
		this.strSUntreadNo = strSUntreadNo;
	}

	public String getStrLotNo() {
		return strLotNo;
	}
	public void setStrLotNo(String strLotNo) {
		this.strLotNo = strLotNo;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}
	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}

	public String getStrJsonMaster() {
		return strJsonMaster;
	}
	public void setStrJsonMaster(String strJsonMaster) {
		this.strJsonMaster = strJsonMaster;
	}

	public String getStrJsonDetail1() {
		return strJsonDetail1;
	}
	public void setStrJsonDetail1(String strJsonDetail1) {
		this.strJsonDetail1 = strJsonDetail1;
	}

	public String getStrJsonDetail2() {
		return strJsonDetail2;
	}
	public void setStrJsonDetail2(String strJsonDetail2) {
		this.strJsonDetail2 = strJsonDetail2;
	}

	public String getStrLabelNo() {
		return strLabelNo;
	}
	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
	}

	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public IRidata_CheckConfirmService getRidata_CheckConfirmImpl() {
		return Ridata_CheckConfirmImpl;
	}

	public void setRidata_CheckConfirmImpl(
			IRidata_CheckConfirmService ridata_CheckConfirmImpl) {
		Ridata_CheckConfirmImpl = ridata_CheckConfirmImpl;
	}

}
