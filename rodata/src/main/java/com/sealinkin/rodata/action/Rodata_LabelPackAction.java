/*
 * 退厂拼箱打包
 * hekangli
 */
package com.sealinkin.rodata.action;


import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_BoxDModel;
import com.sealinkin.rodata.model.Rodata_BoxMModel;
import com.sealinkin.rodata.service.IRodata_LabelPackService;
import com.sealinkin.util.ExceptionUtil;



public class Rodata_LabelPackAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IRodata_LabelPackService rodata_LabelPackImpl;
	private String str;
	private String strQuery;
	private String strOwnerNo;
	private String strRecedeNo;
	private String strLabelNo;

	/*
	 * 退厂拼箱打包源标签商品列表
	 * 
	 */
	public void getRodata_sLabelNoArticleList(){
		try{
			ExtListDataBo<Rodata_BoxDModel> list = 
					rodata_LabelPackImpl.getRodata_sLabelNoArticleList(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							strRecedeNo,strLabelNo,
							getStart(),
							getLimit()
							);
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 退厂拼箱打包目的标签商品列表
	 * 
	 */
	public void getRodata_dLabelNoArticleList(){
		try{
			ExtListDataBo<Rodata_BoxDModel> list = 
					rodata_LabelPackImpl.getRodata_dLabelNoArticleList(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							strRecedeNo,strLabelNo
							);
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 退厂拼箱打包标签列表
	 * 
	 */
	public void getRodata_sLabelList(){
		try{
			ExtListDataBo<Rodata_BoxMModel> list=
					rodata_LabelPackImpl.getRodata_sLabelList(strRecedeNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	/*
	 *退货单号下拉
	 * 
	 */
	public void getRecedeNo(){
		try{
			List<ComboxBo> list=
					rodata_LabelPackImpl.getRecedeNo(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							super.getMdBdef_DefWorker().getWorkerOwner(),
							strRecedeNo);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	

	//获取标签号
	public void getLoadBox(){
		try 
		{
			MsgRes msg = this.rodata_LabelPackImpl.getLoadBox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 单品转移
	 */
	public void saveArticleMoveLabel(){
		try {
			MsgRes msg=new MsgRes();
				msg = rodata_LabelPackImpl.saveArticleMoveLabel(str);
				
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/*
	 * 整箱转移
	 */
	public void saveMoveLabel(){
		try {
			MsgRes msg=new MsgRes();
			msg = rodata_LabelPackImpl.saveMoveLabel(str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/*
	 * 回库
	 */
	public void saveMoveQty(){
		try {
			MsgRes msg=new MsgRes();
			msg = rodata_LabelPackImpl.saveMoveQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	public IRodata_LabelPackService getRodata_LabelPackImpl() {
		return rodata_LabelPackImpl;
	}
	public void setRodata_LabelPackImpl(
			IRodata_LabelPackService rodata_LabelPackImpl) {
		this.rodata_LabelPackImpl = rodata_LabelPackImpl;
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
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrRecedeNo() {
		return strRecedeNo;
	}
	public void setStrRecedeNo(String strRecedeNo) {
		this.strRecedeNo = strRecedeNo;
	}
	public String getStrLabelNo() {
		return strLabelNo;
	}
	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
	}	
	
	

	
}
