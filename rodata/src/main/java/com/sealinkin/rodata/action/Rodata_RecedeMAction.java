/*
 * 手建退厂单
 * zhouhuan
 */
package com.sealinkin.rodata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_RecedeDModel;
import com.sealinkin.rodata.model.Rodata_RecedeMModel;
import com.sealinkin.rodata.service.IRodata_RecedeMService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.ExceptionUtil;

public class Rodata_RecedeMAction extends CommAction {
	private static final long serialVersionUID = 1L;
	private IRodata_RecedeMService rodata_RecedeMImpl;
	private String str;
	private String strArticleNo;
	private String strRecedeNo;
	private String jsonMaster;
	private String jsonDetail;
	private String[] wheresql;
	private String strQuery;
	private String strPoNo;
	private String strWheresql;
	private String recedeNo;
	private String ownerNo;
	private String cellNo;

	
	//获取手键退厂单明细信息（标准版和服装版公用）
	public void getRodata_RecedeD() {
		try {
			ExtListDataBo<Rodata_RecedeDModel> list = rodata_RecedeMImpl.getRodata_RecedeD(
					str, 
					getStrRecedeNo(),
					getStart(),
					getLimit());
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//通过商品编号自动获得明细信息（标准版和服装版公用）
	public void getRodata_RecedeMByArticleNO() {
		try {
			List<String> list = rodata_RecedeMImpl.getRodata_RecedeMByArticleNO(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					getStrArticleNo(),getStrRecedeNo(), getStart(), getLimit());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 保存退厂单（标准版和服装版公用）
	 */
	public void saverodata_recede() throws Exception {
		try {
			MsgRes msg = rodata_RecedeMImpl.saverodata_recede(jsonMaster,
					jsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));
		}
	}


	/*
	 * 定位
	 */
	public void send() throws Exception {
		try {
			MsgRes msg = rodata_RecedeMImpl.send(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(), 
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),
					""));// 定位失败！
			e.printStackTrace();
		}
	}

	/**
	 * 校验原单号是否已存在（标准版和服装版公用）
	 */
	public void checkPoNo(){
		try {
			MsgRes msg = rodata_RecedeMImpl.checkPoNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strPoNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public IRodata_RecedeMService getRodata_RecedeMImpl() {
		return rodata_RecedeMImpl;
	}
	public void setRodata_RecedeMImpl(IRodata_RecedeMService rodata_RecedeMImpl) {
		this.rodata_RecedeMImpl = rodata_RecedeMImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrArticleNo() {
		return strArticleNo;
	}
	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}
	public String getStrRecedeNo() {
		return strRecedeNo;
	}
	public void setStrRecedeNo(String strRecedeNo) {
		this.strRecedeNo = strRecedeNo;
	}
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}
	public String[] getWheresql() {
		return wheresql;
	}
	public void setWheresql(String[] wheresql) {
		this.wheresql = wheresql;
	}
	public String getStrPoNo() {
		return strPoNo;
	}
	public void setStrPoNo(String strPoNo) {
		this.strPoNo = strPoNo;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getRecedeNo() {
		return recedeNo;
	}

	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
}
