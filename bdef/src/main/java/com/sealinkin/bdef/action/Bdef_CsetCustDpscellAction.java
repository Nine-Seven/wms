/**
 * 客户与电子标签储位对应关系action
 * @author hcx
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.bdef.service.IBdef_CsetCustDpscellService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;
@SuppressWarnings("rawtypes")
public class Bdef_CsetCustDpscellAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBdef_CsetCustDpscellService bdef_CsetCustDpscellImpl;
	private String strCustNo;
	private String strDpsCellNo;
	private String jsonMaster;
	private String workerOwner;
	private String strAssignedCustomer;
	private String str;
	private String wheresql;
	private String strFlag;
	private String strOwnerNo;
	//获取储位网格
	public void getCdef_DefCell(){
	    try{
        	
			List list=bdef_CsetCustDpscellImpl.getCdef_DefCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strCustNo,str);
        	if(list==null){
        		super.writeObj(new MsgRes(false, "", ""));
        	}else{
        		StringBuilder sbColumModle=new StringBuilder("{'columModle':[");
            	StringBuilder sbData=new StringBuilder("'data':[");
            	StringBuilder sbFieldsNames=new StringBuilder("'fieldsNames':[");
        	    for (int i=0;i<list.size();i++) {
        		if(list.get(i) instanceof  Object[])   
        		{
        			Object[] obj=(Object[])list.get(i);
        			for(int j=0;j<obj.length;j++){
        				if(i==0){
        					sbColumModle.append("{'header': 'col");
        					sbColumModle.append(j);
        					sbColumModle.append("','width': 50");
        					sbColumModle.append(",'dataIndex': 'col");
        					sbColumModle.append(j);
        					if(j<5){
        						sbColumModle.append("','hidden':true");
        					}
        					else{
        						sbColumModle.append("',renderer:" +
        								" function(value, metaData, record, rowIndex, colIndex, store) " +
        								"{" +
        								"if (value.indexOf(',1') !=-1) " +
        								"{metaData.style='background-color:#F78709';}" +
        								" if(value.indexOf(',2') !=-1)" +
        								"{metaData.style='background-color:#42E61A';} " +
        								"if(value.indexOf(',3') !=-1)" +
        								"{metaData.style='background-color:#F3939';}" +
        								"return value.substring(-1,value.length-2);}");
        					}
        					if(j==obj.length-1){
        						sbColumModle.append("}]");            
        					}else{
        						sbColumModle.append("},");
        					}
        					sbColumModle.append("\r\n");
        					sbFieldsNames.append("{'name': 'col");
        					sbFieldsNames.append(j);
        					if(j==obj.length-1){
        						sbFieldsNames.append("'}]");
        					}else{
        						sbFieldsNames.append("'},");
        					}
        					sbColumModle.append("\r\n");
        				}
        				if(j==0){
        					sbData.append("{'col");
        				}else{
        					sbData.append("'col");	 
        				}
        				sbData.append(j);
        				sbData.append("':'");
        				sbData.append(obj[j]);
        				if(i==list.size()-1 && j==obj.length-1){
        					sbData.append("'}]");
        				}else if(j==obj.length-1){
        					sbData.append("'},");
        				}else if(j!=obj.length-1){
        					sbData.append("',");
        				}
        			}
        			sbData.append("\r\n");
        		}
			}
        	super.writeObj(new MsgRes(true, "", sbColumModle.append(",").toString()+sbFieldsNames.append(",")+sbData.append("}").toString()));
        	}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    }
	//新增客户与储位对应关系
	public void add(){
		try {
			MsgRes msg=bdef_CsetCustDpscellImpl.add(jsonMaster) ;
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//解除客户与储位对应关系
	public void delete(){
		try {
			MsgRes msg=bdef_CsetCustDpscellImpl.delete(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strCustNo,str) ;
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//检索已分配储位客户
	public void checkCustNo(){
	    try{
			String custList = bdef_CsetCustDpscellImpl.checkCustNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strCustNo,wheresql);
			super.writeStr(custList);
		}catch (Exception e) {
		     e.printStackTrace();
		}
	}
	//检索已分配客户储位
	public void checkDpsCellNo(){
	    try{
			String custList = bdef_CsetCustDpscellImpl.checkDpsCellNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strDpsCellNo);
			super.writeStr(custList);
		}catch (Exception e) {
		     e.printStackTrace();
		}
	}
	//获取客户列表
	public void getCsetCustList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_DefCustModel> list=bdef_CsetCustDpscellImpl.getBdefCsetCust(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					workerOwner,strAssignedCustomer,
					wheresql,str,strFlag,pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //获取货主下拉列表
	public void getOwnerComboList()
	{
		try {
			List<ComboxBo> list=bdef_CsetCustDpscellImpl.getOwnerComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//获取设备分播组
	public void getDeviceGroupNoList()
	{
		try {
			List<ComboxBo> list=bdef_CsetCustDpscellImpl.getDeviceGroupNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//获取设备分播线
	public void getDeviceNoList()
	{
		try {
			List<ComboxBo> list=bdef_CsetCustDpscellImpl.getDeviceNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//检索未分播完成客户
		public void checkDeliverCustNo(){
		    try{
		    	MsgRes msg = bdef_CsetCustDpscellImpl.checkDeliverCustNo(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						strOwnerNo,
						strCustNo);
		    	super.writeObj(msg);
			}catch (Exception e) {
			     e.printStackTrace();
			     super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
		}
	public IBdef_CsetCustDpscellService getBdef_CsetCustDpscellImpl() {
		return bdef_CsetCustDpscellImpl;
	}
	public void setBdef_CsetCustDpscellImpl(
			IBdef_CsetCustDpscellService bdef_CsetCustDpscellImpl) {
		this.bdef_CsetCustDpscellImpl = bdef_CsetCustDpscellImpl;
	}
	public String getStrCustNo() {
		return strCustNo;
	}
	public void setStrCustNo(String strCustNo) {
		this.strCustNo = strCustNo;
	}
	public String getStrDpsCellNo() {
		return strDpsCellNo;
	}
	public void setStrDpsCellNo(String strDpsCellNo) {
		this.strDpsCellNo = strDpsCellNo;
	}
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getWorkerOwner() {
		return workerOwner;
	}
	public void setWorkerOwner(String workerOwner) {
		this.workerOwner = workerOwner;
	}
	public String getStrAssignedCustomer() {
		return strAssignedCustomer;
	}
	public void setStrAssignedCustomer(String strAssignedCustomer) {
		this.strAssignedCustomer = strAssignedCustomer;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
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
}
