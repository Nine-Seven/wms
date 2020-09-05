package com.sealinkin.cdef.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.cdef.model.Cdef_DefcellModel;
import com.sealinkin.cdef.model.Cdef_DefwareModel;
import com.sealinkin.cdef.service.ICdef_DefWare;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Cdef_DefWareAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	private ICdef_DefWare cdef_DefWareImpl;
	private String str;
	private String strLog;
	private String[] strArr;
	private String jsonStr;
	private String queryStr;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strWareNo;
	private String strAreaNo;
	private String strQuery;
	///////////////////////////////
	private String minStockNo;
	private String maxStockNo;
	private String minStockY;
	private String maxStockY;
	private String minStockX;
	private String maxStockX;
	private String minBayX;
	private String maxBayX;
	private String strCodePrefix;
	private String ownerNo;
	private File file;
	private String perf1;
	private String perf2;
	private String perf3;
	private String perf4;
	private String flag;
	private String strWheresql;

	
	// 保存、修改仓库
	public void saveCdef_DefWare(){
		try{					
			cdef_DefWareImpl.saveCdef_DefWare(getStr(),strLog,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	// 查询仓库信息	
    public void getCdef_DefWare(){
    	try{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cdef_DefwareModel> list=cdef_DefWareImpl.getCdef_DefWare(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getQueryStr(),
					pageBo,
					requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    }
    
    // 删除库区    
    public void deleteCdefDefware(){
    	try {
			MsgRes msg=cdef_DefWareImpl.deleteCdefDefware(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWareNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
    }
    //库区编码是否存在
    public void existsWareList(){
  		try{		
  			ExtListDataBo<Cdef_DefwareModel> list=cdef_DefWareImpl.existsWareList(
  					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
  					getQueryStr(), 
  					super.getMdBdef_DefWorker().getWarehouseNo());
  			if(list.getRootList().size()>0){
  				super.writeObj(new MsgRes(false, "该库区已存在，请重新输入！", ""));
  			}else{
  				super.writeObj(new MsgRes(true, "", ""));
  			}
  		}catch (Exception e) {
  			e.printStackTrace();
  			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
  		}
  	}
    
    //获取库区的长度
    public void getWareNoOfLength(){
    	try {
			String str=cdef_DefWareImpl.getWareNoOfLength(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
      
    ///////////////////////////////////////////////////////////////////////////////////
    
    // 保存、修改储区	
	public void saveCdef_DefArea(){
		try{					
			cdef_DefWareImpl.saveCdef_DefArea(getStr(),strLog,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	// 查询储区信息
    public void getCdef_DefArea(){
    	try{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cdef_DefareaModel> list=cdef_DefWareImpl.getCdef_DefArea(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getQueryStr(),
					pageBo,
					requestFlag);
			if(requestFlag==1){
				super.writeStr(covtObjectToJson(list));
			}
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    }
    	
	//获取储区代码长度
    public void getAreaNoOfLength(){
    	try {
			String str=cdef_DefWareImpl.getAreaNoOfLength(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeStr(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
    //从wms_replenish_formula_m表中获取拆零补货算法下拉列表
	public void getReplenishRuleCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getReplenishRuleCombo(0, 100);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	  
    // 删除储区
    public void deleteCdefDefarea(){
    	try {
			MsgRes msg=cdef_DefWareImpl.deleteCdefDefarea(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWareNo, 
					strAreaNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
    }

    public void existsAreaList(){
  		try{	
  			ExtListDataBo<Cdef_DefareaModel> list=cdef_DefWareImpl.existsAreaList(
  					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
  					getQueryStr(), 
  					super.getMdBdef_DefWorker().getWarehouseNo());
  			if(list.getRootList().size()>0){
  				super.writeObj(new MsgRes(false, "该储区已存在，请重新输入！", ""));
  			}else{
  				super.writeObj(new MsgRes(true, "", ""));
  			}
  		}catch (Exception e) {
  			e.printStackTrace();
  			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
  		}
  	}
    
	//////////////////////////////////////////////////////////////////////////////
    
    
    
	// 保存、修改储位	
	public void saveCdef_DefCell(){
		try{					
			MsgRes msg=cdef_DefWareImpl.saveCdef_DefCell(getStr());
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e), ""));
		}
	}
    /**
	 * 查询储位详细信息
	 */
    public void getCdef_DefCellDetails(){
    	try{
			List<Cdef_DefcellModel> list=cdef_DefWareImpl.getCdef_DefCellDetails(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    }
    /**
     * 确认转区hkl
     */
    public void updateCellWareArea(){
		try{					
			MsgRes msg=cdef_DefWareImpl.updateCellWareArea(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,strWareNo,
					strAreaNo
					);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e), ""));
		}
	}
    
    
    /**
	 * 查询储位信息
	 * @author lich 2013.09.13
	 */
	
	public void getCdef_DefCell(){
    	try {
        	List list=cdef_DefWareImpl.getCdef_DefCell(str,
        			super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
        			super.getMdBdef_DefWorker().getWarehouseNo());
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
        				if(obj[j]!=null && obj[j]!=""){
        				if(i==0){
        					sbColumModle.append("{'header': 'col");
        					sbColumModle.append(j);
        					sbColumModle.append("','width': 100");
        					sbColumModle.append(",'dataIndex': 'col");
        					sbColumModle.append(j);
        					if(j<5){
        						sbColumModle.append("','hidden':true");
        					}else{
        						sbColumModle.append("',renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {if (value.indexOf(',1') !=-1) {metaData.style='background-color:#F78709';} if(value.indexOf(',2') !=-1){metaData.style='background-color:#42E61A;';}if(value.indexOf(',3') !=-1){metaData.style='background-color:#F3939';}if(value=='null'){metaData.style='background-color:#0000EE';} if(value=='null') return '';else return value.substring(0,value.length-2);}");
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
    
    /**
     * 仓库下拉
     */
	public void getCdef_DefWareCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefWareCombo(
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
     * @author lich 2013.09.13
     */
	public void getCdef_DefAreaCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefAreaCombo(
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
     * 储位下拉（未禁用的储位）
     * @author lich 2013.09.13
     */
	public void getCdef_DefCellCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getJsonStr(),
					getStr(), 0, 10);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	 /**
     * 获取所有储位下拉
     * @author hkl 2016.05.23
     */
	public void getCdef_DefCellAllCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefCellAllCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getJsonStr(),
					strWheresql);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
    /**
     * 通道下拉
     */
	
	public void getCdef_DefStockCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefStockCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//格下拉
	public void getCdef_DefStockXCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefStockXCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//位下拉
	public void getCdef_DefbayXCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefbayXCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//层下拉
	public void getCdef_DefStockYCombo(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefStockYCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取属性
	 */
	public void getCdef_GetCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCombo(getStr().split(","),0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	
	/**
	 * 查询储位库存信息
	 */
    public void getStock_Content(){
    	try{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Stock_ContentModel> list=cdef_DefWareImpl.getStock_Content(getStr(),pageBo);
	    	super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    }
    
    //获取储区属性，继承给储位
    public void getAttribute(){
    	try{	
    		ExtListDataBo<Cdef_DefareaModel> defArea=cdef_DefWareImpl.getAttribute(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrWareNo(),
					this.getStrAreaNo());
	    	super.writeStr(covtObjectToJson(defArea));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    
    }
    
    
    //生成储位
    public void produceCell(){
    	try{	
    		
			MsgRes msg=cdef_DefWareImpl.produceCell(
					getStr(),
					this.getMinStockNo(),
					this.getMaxStockNo(),
					this.getMinStockY(),
					this.getMaxStockY(),
					this.getMinStockX(),
					this.getMaxStockX(),
					this.getMinBayX(),
					this.getMaxBayX(),
					this.getStrCodePrefix(),
					this.getPerf1(),
					this.getPerf2(),
					this.getPerf3(),
					this.getPerf4());
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e), ""));
		}
    }
    
    //检查储位是否重复
    public void checkCell(){
    	try{	
    		
			MsgRes msg=cdef_DefWareImpl.checkCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrWareNo(),
					this.getStrAreaNo(),
					this.getMinStockNo(),
					this.getMaxStockNo(),
					this.getMinStockY(),
					this.getMaxStockY(),
					this.getMinStockX(),
					this.getMaxStockX(),
					this.getMinBayX(),
					this.getMaxBayX());
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
    
    }
    
   //获取库区（储位添加窗口）
    public void getCdef_DefWareComboforWindow(){

    	try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefWareComboforWindow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
    }
    
    //获取储区（储位添加窗口）
    public void getCdef_DefAreaComboforWindow(){
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cdef_DefWareImpl.getCdef_DefAreaComboforWindow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 0, 100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
    }
    //储位导入
	public void defcellUpLoad(){
		try {
			MsgRes msg = cdef_DefWareImpl.tscDefcellUpLoad(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),				
					this.getOwnerNo(),
					file);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
    //批量修改储位
	public void updateCellStatus(){
		try {
			MsgRes msg = cdef_DefWareImpl.updateCellStatus(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						this.getStr(),
						this.getFlag()
					);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
    public ICdef_DefWare getCdef_DefWareImpl() {
		return cdef_DefWareImpl;
	}
	public void setCdef_DefWareImpl(ICdef_DefWare cdef_DefWareImpl) {
		this.cdef_DefWareImpl = cdef_DefWareImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public String[] getStrArr() {
		return strArr;
	}
	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}
	
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	
	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	public String getStrWareNo() {
		return strWareNo;
	}
	public void setStrWareNo(String strWareNo) {
		this.strWareNo = strWareNo;
	}
	public String getStrAreaNo() {
		return strAreaNo;
	}
	public void setStrAreaNo(String strAreaNo) {
		this.strAreaNo = strAreaNo;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrLog() {
		return strLog;
	}
	public void setStrLog(String strLog) {
		this.strLog = strLog;
	}
	public String getMinStockNo() {
		return minStockNo;
	}

	public void setMinStockNo(String minStockNo) {
		this.minStockNo = minStockNo;
	}

	public String getMaxStockNo() {
		return maxStockNo;
	}

	public void setMaxStockNo(String maxStockNo) {
		this.maxStockNo = maxStockNo;
	}

	public String getMinStockY() {
		return minStockY;
	}

	public void setMinStockY(String minStockY) {
		this.minStockY = minStockY;
	}

	public String getMaxStockY() {
		return maxStockY;
	}

	public void setMaxStockY(String maxStockY) {
		this.maxStockY = maxStockY;
	}

	public String getMinStockX() {
		return minStockX;
	}

	public void setMinStockX(String minStockX) {
		this.minStockX = minStockX;
	}

	public String getMaxStockX() {
		return maxStockX;
	}

	public void setMaxStockX(String maxStockX) {
		this.maxStockX = maxStockX;
	}

	public String getMinBayX() {
		return minBayX;
	}

	public void setMinBayX(String minBayX) {
		this.minBayX = minBayX;
	}

	public String getMaxBayX() {
		return maxBayX;
	}

	public void setMaxBayX(String maxBayX) {
		this.maxBayX = maxBayX;
	}

	public String getStrCodePrefix() {
		return strCodePrefix;
	}

	public void setStrCodePrefix(String strCodePrefix) {
		this.strCodePrefix = strCodePrefix;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getPerf1() {
		return perf1;
	}

	public void setPerf1(String perf1) {
		this.perf1 = perf1;
	}

	public String getPerf2() {
		return perf2;
	}

	public void setPerf2(String perf2) {
		this.perf2 = perf2;
	}

	public String getPerf3() {
		return perf3;
	}

	public void setPerf3(String perf3) {
		this.perf3 = perf3;
	}

	public String getPerf4() {
		return perf4;
	}

	public void setPerf4(String perf4) {
		this.perf4 = perf4;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}	
	
}
