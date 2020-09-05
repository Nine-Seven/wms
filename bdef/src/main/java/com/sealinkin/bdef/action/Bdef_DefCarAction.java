package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefcarModel;
import com.sealinkin.bdef.model.Bdef_DefcartypeModel;
import com.sealinkin.bdef.service.IBdef_DefCarService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

/**
 * @车辆信息维护Action
 * @author hcx
 *
 */
public class Bdef_DefCarAction extends CommAction{

	private static final long serialVersionUID = 1375518802613896868L;
	private IBdef_DefCarService bdef_DefCarImpl;
	private String strQuery;
	private String str;
	private String strWheresql;
	
	//获取车辆类型列表
	public void getCartypeList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefcartypeModel> list=bdef_DefCarImpl.getCartypeList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//车辆类型下拉
	public void getCarTypeQuery(){
		try 
		{
			List<ComboxBo> list = bdef_DefCarImpl.getCarTypeQuery(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//判断车辆类型代码是否唯一
	public void carTypeCheck(){
		try 
		{
			List<String> list = bdef_DefCarImpl.carTypeCheck(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					strQuery);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//车辆类型保存
	public void saveCarType(){
		try {
			MsgRes msg=bdef_DefCarImpl.saveCarType(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除车辆类型
	public void deleteCarType(){
		try {
			MsgRes msg = bdef_DefCarImpl.deleteCarType(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取车辆信息列表
	public void getCarList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefcarModel> list=bdef_DefCarImpl.getCarList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery,str, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//判断车辆代码是否唯一
	public void carCheck(){
		try 
		{
			List<String> list = bdef_DefCarImpl.carCheck(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//车辆信息保存
	public void saveCar(){
		try {
			MsgRes msg=bdef_DefCarImpl.saveCar(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//填充车辆类型代码下拉控件 7-9添加  hj
	public void querycarTypeNoCombo()
	{
		try 
		{
			System.out.println("strWheresql:"+strWheresql);
			List<ComboxBo> list = bdef_DefCarImpl.querycarTypeNoCombo(
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
	//开始
	//填充车辆名称下拉控件 7-9添加  hj  
	public void querycarNameCombo()
	{
		try 
		{
			String newStrWheresql = new String(strWheresql.getBytes("ISO-8859-1"),"UTF-8");
			List<ComboxBo> list = bdef_DefCarImpl.querycarNameCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					newStrWheresql, 
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}		
		
	//填充车牌号下拉控件 7-9添加  hj  
	public void querycarPlateCombo()
	{
		try 
		{
			String newStrWheresql = new String(strWheresql.getBytes("ISO-8859-1"),"UTF-8");
			List<ComboxBo> list = bdef_DefCarImpl.querycarPlateCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					newStrWheresql,
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}				
		
	//填充司机名称下拉控件 7-9添加  hj  
	public void querydeiverWorkerCombo()
	{
		try 
		{
			String newStrWheresql = new String(strWheresql.getBytes("ISO-8859-1"),"UTF-8");
			List<ComboxBo> list = bdef_DefCarImpl.querydeiverWorkerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					newStrWheresql, 
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}				
	
	
	public IBdef_DefCarService getBdef_DefCarImpl() {
		return bdef_DefCarImpl;
	}
	public void setBdef_DefCarImpl(IBdef_DefCarService bdef_DefCarImpl) {
		this.bdef_DefCarImpl = bdef_DefCarImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	
}
