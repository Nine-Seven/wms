package com.sealinkin.report.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.report.service.IReport_Service;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.report.model.ArticleInvAccReportModel;
import com.sealinkin.report.model.Idata_Check_DSourceReportModel;
import com.sealinkin.wms.model.Wms_DefSearch_DModel;
import com.sealinkin.wms.model.Wms_DefsearchDSetModel;

@SuppressWarnings({ "rawtypes", "unchecked" ,"unused"})
public class Report_Action extends CommAction {

	private static final long serialVersionUID = 1L;
	private IReport_Service report_Impl;
	private String str;
	private String strQuery;
	private String moduleId;
	private Integer requestFlag = 1;
	private String strReferenceItem;
	private String strName;
	private String strNewName;
	private String strOldName;
	private String strPgmId;
	private String strReportId;
	private String strProjectId;
	private int beginYear;
	private int beginMonth;
	private int beginDay;
	private int endYear;
	private int endMonth;
	private int endDay;
	
	private String beginDate;
	private String endDate;
	

	public void getSourceReportList() {
		try {
			ExtListDataBo<Idata_Check_DSourceReportModel> list = report_Impl
					.getSourceReportList();
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getGridData() {
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo extListBo = report_Impl.getGridData(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					getModuleId(),
					getStrQuery(), 
					pageBo, 
					getRequestFlag(), 
					strName);
			List list = new ArrayList(extListBo.getRootList());

			/*
			 * Object[] b=new Object[10]; b[0]="合计："; b[7]="244";
			 */
			MsgRes msgRes = report_Impl.getTotalData(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), getModuleId(),
					strQuery,
					strName);
			if (msgRes.getIsSucc()) {
				list.add(msgRes.getObj());
			}

			if (requestFlag == 1) {
				if (list == null || list.size() == 0) {
					super.writeObj(new MsgRes(false, "", ""));
				} else {
					// super.writeStr(covtObjectToJson(list));
					StringBuilder sbData = new StringBuilder("{'rootList':[");
					StringBuilder sbTotalCount = new StringBuilder(
							"'totalCount':" + extListBo.getTotalCount());
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i) instanceof Object[]) {
							Object[] obj = (Object[]) list.get(i);
							for (int j = 0; j < obj.length; j++) {
								if (j == 0) {
									sbData.append("{'col");
								} else {
									sbData.append("'col");
								}
								sbData.append(j);
								sbData.append("':'");
								sbData.append(obj[j] == null ? "" : obj[j].toString().replaceAll("'","&#39;"));

								if (i == list.size() - 1 && j == obj.length - 1) {
									sbData.append("'}]");
								} else if (j == obj.length - 1) {
									sbData.append("'},");
								} else if (j != obj.length - 1) {
									sbData.append("',");
								}
							}
							sbData.append("\r\n");
						}
					}
					String aa = sbData.toString() + ","
							+ sbTotalCount.toString() + "}";
					super.writeObj(aa);
				}
			} else if (requestFlag == 2) {
			   List<Wms_DefSearch_DModel>
			   titleList=report_Impl.getGridColumModle(moduleId,getStrName());
			   String ColumName=""; 
			   for(int i=0;i<titleList.size();i++){
				   ColumName+=titleList.get(i).getFieldName()+","; 
			   }
			   if(ColumName.length()!=0){ 
				   ColumName= ColumName.substring(0,ColumName.length()-1); 
			   }
			   String[] threads = new String[]{"sheet1","title",ColumName};
			   commExportAction("title", threads, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"报表设置有误！",null));
		}
	}

	public void getGridColumModle() {
		try {
			List<Wms_DefSearch_DModel> list = report_Impl.getGridColumModle(
					strReportId, strProjectId);
			if (list == null) {
				super.writeObj(new MsgRes(false, "", ""));
			} else {
				StringBuilder sbColumModle = new StringBuilder("'columModle':[");
				StringBuilder sbFieldsNames = new StringBuilder(
						"'fieldsNames':[");
				for (int i = 0; i < list.size(); i++) {
					sbColumModle.append("{'header': '"
							+ list.get(i).getFieldName() + "'");
					sbColumModle.append(",'width': " + list.get(i).getWidth()
							+ "");
					sbColumModle.append(",'dataIndex': 'col" + i + "'");
					sbColumModle.append(",'type': '"
							+ list.get(i).getFieldType() + "'");

					sbFieldsNames.append("{'name': 'col" + i + "'");
					if (i == list.size() - 1) {
						sbColumModle.append("}]");
						sbFieldsNames.append("}]");
					} else {
						sbColumModle.append("},");
						sbFieldsNames.append("},");
					}
				}
				super.writeObj(new MsgRes(true, "", sbColumModle.append(",")
						.toString() + sbFieldsNames.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得对应模块下的报表
	public void getReportList() {
		try {
			List<ComboxBo> list = report_Impl.getReportList(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),moduleId);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得对应模块下的参考项
	public void getReferenceItemList() {
		try {
			List<ComboxBo> list = report_Impl
					.getReferenceItemList(strReferenceItem);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setReport_Impl(IReport_Service report_Impl) {
		this.report_Impl = report_Impl;
	}

	// 或取高阶查询设置项
	public void getWmsDefsearchDSetList() {
		try {
			ExtListDataBo<Wms_DefsearchDSetModel> list = report_Impl
					.getWmsDefsearckDSetList(strProjectId, strReportId);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 参数设置保存
	 */
	public void SaveReportSet() {
		try {
			MsgRes msg = report_Impl.SaveReportSet(str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//另存为
	public void SaveOtherReportSet() {
		try {
			System.out.println("2");
			MsgRes msg = report_Impl.SaveOtherReportSet(str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 重命名
	public void SaveRename() {
		try {
			MsgRes msg = report_Impl.SaveRename(strNewName, strOldName,strPgmId);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//删除项目设置
	public void deleteProject(){
		try {
			System.out.println("2");
			MsgRes msg = report_Impl.deleteProject( strPgmId,strOldName);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//检验参考项目名称是否已经存在
	public void checkProjectName()
    {
    	try {
			MsgRes msg = report_Impl.checkProjectName(strPgmId,strName);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	//获取商品库存报表表model
	public void getArticleInvAccGridColumModle()
	{
		StringBuilder sbColumModle = new StringBuilder("'columModle':[");
		StringBuilder sbFieldsNames = new StringBuilder("'fieldsNames':[");		
		sbColumModle.append(
				"{'header': '货主编码','width': 60,'dataIndex': 'ownerNo','type': 'STRING'}," +
				"{'header': '货主名称','width': 120,'dataIndex': 'ownerName','type': 'STRING'}," +
				"{'header': '商品编码','width': 120,'dataIndex': 'ownerArticleNo','type': 'STRING'}," +
				"{'header': '商品名称','width': 200,'dataIndex': 'articleName','type': 'STRING'}," +
				"{'header': '期初库存(数量)','width': 90,'dataIndex': 'qcqty','type': 'STRING'}," +
				"{'header': '期初库存(重量)','width': 90,'dataIndex': 'qcweight','type': 'STRING'},");
		
		sbFieldsNames.append(
				"{'name': 'ownerNo'}," +
				"{'name': 'ownerName'}," +
				"{'name': 'ownerArticleNo'}," +
				"{'name': 'articleName'}," +
				"{'name': 'qcqty'}," +
				"{'name': 'qcweight'},");
		
		
	
		//同一年
		if(this.beginYear==this.endYear){
			if(this.getBeginMonth()==this.getEndMonth()){
				for(int i=this.getBeginDay(); i<=this.getEndDay();i++){
					sbColumModle.append("{'header': '"+this.getBeginYear()+"."+this.getBeginMonth()+"."+i+"','width': 80,'columns': [" +
					"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}, " +
					"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}, " +
					"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}," +
					"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}]},");
			
					sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
					sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
					sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
					sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
				}
			}else{
				for(int i=this.getBeginMonth(); i<=this.getEndMonth();i++){
					int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),i);
					if(i==this.getBeginMonth()){
						for(int j=this.beginDay; j<=maxDay;j++){
							sbColumModle.append("{'header': '"+this.getBeginYear()+"."+i+"."+j+"','width': 80,'columns': [" +
									"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
									"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
									"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}," +
									"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}]},");
							
									sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
						}			
					}else if(i==this.endMonth){
						for(int j=1; j<=this.endDay;j++){
							sbColumModle.append("{'header': '"+this.getBeginYear()+"."+i+"."+j+"','width': 80,'columns': [" +
									"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
									"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
									"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}," +
									"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}]},");
							
									sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
						}			
					}else{
						for(int j=1; j<=maxDay;j++){
							sbColumModle.append("{'header': '"+this.getBeginYear()+"."+i+"."+j+"','width': 80,'columns': [" +
									"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
									"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
									"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}," +
									"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}]},");
							
									sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
									sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
						}	
						
					}
					
				}			
			}		
		}else{  //跨年
			for(int i=this.getBeginYear();i<=this.getEndYear();i++){
				if(i==this.getBeginYear()){
					for(int j=this.getBeginMonth(); j<=12;j++){
						int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),j);
						if(j==this.getBeginMonth()){
							for(int x=this.beginDay; x<=maxDay;x++){
								sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
										"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
										"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
								
										sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
							}
						}else{
							for(int x=1; x<=maxDay;x++){
								sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
										"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
										"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
								
										sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
							}
						
						}
					}
				}else if(i==this.getEndYear()){
					for(int j=1; j<=this.getEndMonth();j++){
						int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),j);
						
						if(j==this.getEndMonth()){
							for(int x=1; x<=this.getEndDay();x++){
								sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
										"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
										"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
								
										sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
							}					
						}else{
							for(int x=1; x<=maxDay;x++){
								sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
										"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
										"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
										"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
								
										sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
										sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
							}
						}
					}
				}else{
					for(int j=1; j<=12;j++){
						int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),j);
						for(int x=1; x<=maxDay;x++){
							sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
								"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
								"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
								"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
								"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
								
								sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
								sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
								sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
								sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
							}											
					}
				
				}
				
			}			
		}
		                   
		sbColumModle.append("{'header': '结余库存','width': 60,'dataIndex': 'qty','type': 'STRING'},"+
		                    "{'header': '重量','width': 60,'dataIndex': 'weight','type': 'STRING'}]");
		sbFieldsNames.append("{'name': 'qty'},{'name': 'weight'}]");
		super.writeObj(new MsgRes(true, "", sbColumModle.append(",")
				.toString() + sbFieldsNames.toString()));
	}
    
	/**
	 * 查询仓库信息
	 * @author lich 2013.09.13
	 */
    public void getArticleInvAccData(){
    	try{
    		if(getRequestFlag()==2){
    			List list=report_Impl.exportArticleInvAccData(
    					super.getMdBdef_DefWorker().getWarehouseNo(),
    					super.getMdBdef_DefWorker().getWorkerOwner(),
    					getModuleId(), 
    					getStrQuery(),
    					beginYear,
    					beginMonth,
    					beginDay,
    					endYear,
    					endMonth,
    					endDay,
    					getStr());
    			
    			Map<String, String> map = new HashMap<String, String>();
				String title = "商品库存账";
				
				StringBuilder sbColumModle = new StringBuilder();
				StringBuilder sbFieldsNames = new StringBuilder();	
				sbColumModle.append(
						"货主编码," +
						"货主名称," +
						"商品编码," +
						"商品名称," +
						"期初库存(数量)," +
						"期初库存(重量),");
				
				sbFieldsNames.append(
						"ownerNo," +
						"ownerName," +
						"ownerArticleNo," +
						"articleName," +
						"qcqty," +
						"qcweight,");
/////////////////////////////////////////////////////////////////////////
//				for(int i=1;i<=Integer.parseInt(getStr());i++)
//				{
//					sbColumModle.append(i+"号入(数量),");
//					sbColumModle.append(i+"号出(数量),");
//					sbColumModle.append(i+"号入(重量),");
//					sbColumModle.append(i+"号出(重量),");
//					
//					
//					sbFieldsNames.append("inqtyday"+i+",");
//					sbFieldsNames.append("outqtyday"+i+",");
//					sbFieldsNames.append("inweight"+i+",");
//					sbFieldsNames.append("outweight"+i+",");
//				}
////////////////////////////////////////////////////////////////////////
				//同一年
				if(this.beginYear==this.endYear){
					//同一月
					if(this.getBeginMonth()==this.getEndMonth()){
						for(int i=this.getBeginDay(); i<=this.getEndDay();i++){
							String date=this.getBeginYear()+"."+this.getBeginMonth()+"."+i;
							String day=String.format("%02d", this.getEndMonth())+String.format("%02d", i);
							this.addColum(sbColumModle,sbFieldsNames,date,day);
						}
					}else{
						//不同月
						for(int i=this.getBeginMonth(); i<=this.getEndMonth();i++){
							//取当月最大天数
							int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),i);
							//开始月
							if(i==this.getBeginMonth()){
								for(int j=this.beginDay; j<=maxDay;j++){
									String date=this.getBeginYear()+"."+i+"."+j;
									String day=String.format("%02d", i)+String.format("%02d", j);
									this.addColum(sbColumModle,sbFieldsNames,date,day);
								}			
							}else if(i==this.endMonth){
								//结束月
								for(int j=1; j<=this.endDay;j++){
									String date=this.getBeginYear()+"."+i+"."+j;
									String day=String.format("%02d", i)+String.format("%02d", j);
									this.addColum(sbColumModle,sbFieldsNames,date,day);
								}			
							}else{
								//中间月
								for(int j=1; j<=maxDay;j++){
									String date=this.getBeginYear()+"."+i+"."+j;
									String day=String.format("%02d", i)+String.format("%02d", j);
									this.addColum(sbColumModle,sbFieldsNames,date,day);
								}	
							}
						}			
					}		
				}else{  //跨年
					for(int i=this.getBeginYear();i<=this.getEndYear();i++){
						//开始日期所在年
						if(i==this.getBeginYear()){
							//循环开始日期所在年月份
							for(int j=this.getBeginMonth(); j<=12;j++){
								int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),j);
								if(j==this.getBeginMonth()){
									for(int x=this.beginDay; x<=maxDay;x++){
										String date=i+"."+j+"."+x;
										String day=String.format("%02d", j)+String.format("%02d", x);
										this.addColum(sbColumModle,sbFieldsNames,date,day);
									}
								}else{
									for(int x=1; x<=maxDay;x++){
										String date=i+"."+j+"."+x;
										String day=String.format("%02d", j)+String.format("%02d", x);
										this.addColum(sbColumModle,sbFieldsNames,date,day);
									}
								}
							}
						}else if(i==this.getEndYear()){
							for(int j=1; j<=this.getEndMonth();j++){
								int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),j);
								
								if(j==this.getEndMonth()){
									for(int x=1; x<=this.getEndDay();x++){
										String date=i+"."+j+"."+x;
										String day=String.format("%02d", j)+String.format("%02d", x);
										this.addColum(sbColumModle,sbFieldsNames,date,day);
									}					
								}else{
									for(int x=1; x<=maxDay;x++){
										String date=i+"."+j+"."+x;
										String day=String.format("%02d", j)+String.format("%02d", x);
										this.addColum(sbColumModle,sbFieldsNames,date,day);
									}
								}
							}
						}else{
							for(int j=1; j<=12;j++){
								int maxDay = report_Impl.getDaysByYearMonth(this.getBeginYear(),j);
								for(int x=1; x<=maxDay;x++){
									String date=i+"."+j+"."+x;
									String day=String.format("%02d", j)+String.format("%02d", x);
									this.addColum(sbColumModle,sbFieldsNames,date,day);
								}											
							}
						}
					}			
				}
//				//同一年
//				if(this.beginYear==this.endYear){
//					if(this.getBeginMonth()==this.getEndMonth()){
//						for(int i=this.getBeginDay(); i<=this.getEndDay();i++){
//							sbColumModle.append("{'header': '"+this.getBeginYear()+"."+this.getBeginMonth()+"."+i+"','width': 80,'columns': [" +
//							"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}, " +
//							"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}, " +
//							"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}," +
//							"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"','type': 'STRING'}]},");
//					
//							sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
//							sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
//							sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
//							sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", this.getEndMonth())+String.format("%02d", i)+"'},");
//						}
//					}else{
//						for(int i=this.getBeginMonth(); i<=this.getEndMonth();i++){
//							int maxDay = getDaysByYearMonth(this.getBeginYear(),i);
//							if(i==this.getBeginMonth()){
//								for(int j=this.beginDay; j<=maxDay;j++){
//									sbColumModle.append("{'header': '"+this.getBeginYear()+"."+i+"."+j+"','width': 80,'columns': [" +
//											"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
//											"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
//											"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}," +
//											"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}]},");
//									
//											sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//								}			
//							}else if(i==this.endMonth){
//								for(int j=1; j<=this.endDay;j++){
//									sbColumModle.append("{'header': '"+this.getBeginYear()+"."+i+"."+j+"','width': 80,'columns': [" +
//											"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
//											"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
//											"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}," +
//											"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}]},");
//									
//											sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//								}			
//							}else{
//								for(int j=1; j<=maxDay;j++){
//									sbColumModle.append("{'header': '"+this.getBeginYear()+"."+i+"."+j+"','width': 80,'columns': [" +
//											"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
//											"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}, " +
//											"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}," +
//											"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"','type': 'STRING'}]},");
//									
//											sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//											sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", i)+String.format("%02d", j)+"'},");
//								}	
//								
//							}
//							
//						}			
//					}		
//				}else{  //跨年
//					for(int i=this.getBeginYear();i<=this.getEndYear();i++){
//						if(i==this.getBeginYear()){
//							for(int j=this.getBeginMonth(); j<=12;j++){
//								int maxDay = getDaysByYearMonth(this.getBeginYear(),j);
//								if(j==this.getBeginMonth()){
//									for(int x=this.beginDay; x<=maxDay;x++){
//										sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
//												"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
//												"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
//										
//												sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
//									}
//								}else{
//									for(int x=1; x<=maxDay;x++){
//										sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
//												"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
//												"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
//										
//												sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
//									}
//								
//								}
//							}
//						}else if(i==this.getEndYear()){
//							for(int j=1; j<=this.getEndMonth();j++){
//								int maxDay = getDaysByYearMonth(this.getBeginYear(),j);
//								
//								if(j==this.getEndMonth()){
//									for(int x=1; x<=this.getEndDay();x++){
//										sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
//												"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
//												"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
//										
//												sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
//									}					
//								}else{
//									for(int x=1; x<=maxDay;x++){
//										sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
//												"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//												"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
//												"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
//										
//												sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//												sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
//									}
//								}
//							}
//						}else{
//							for(int j=1; j<=12;j++){
//								int maxDay = getDaysByYearMonth(this.getBeginYear(),j);
//								for(int x=1; x<=maxDay;x++){
//									sbColumModle.append("{'header': '"+i+"."+j+"."+x+"','width': 80,'columns': [" +
//										"{text:'入(数量)',width:60,dataIndex: 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//										"{text:'入(重量)',width:60,dataIndex: 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}, " +
//										"{text:'出(数量)',width:60,dataIndex: 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}," +
//										"{text:'出(重量)',width:60,dataIndex: 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"','type': 'STRING'}]},");
//										
//										sbFieldsNames.append("{'name': 'inqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//										sbFieldsNames.append("{'name': 'inweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//										sbFieldsNames.append("{'name': 'outqtyday"+String.format("%02d", j)+String.format("%02d", x)+"'},");
//										sbFieldsNames.append("{'name': 'outweight"+String.format("%02d", j)+String.format("%02d", x)+"'},");					
//									}											
//							}
//						
//						}
//						
//					}			
//				}
				
				sbColumModle.append("结余库存数量,");
				sbFieldsNames.append("qty");
				sbColumModle.append("结余库存重量");
				sbFieldsNames.append("weight");
				String[] threads = new String[]{"sheet1","商品库存账",
						sbColumModle.toString(),sbFieldsNames.toString()
						};
				
				commExportAction(title, threads,list);
    		}else
    		{
    			PageBo pageBo = new PageBo(getStart(), getLimit());
    			ExtListDataBo<ArticleInvAccReportModel> list=report_Impl.getArticleInvAccData(
    					super.getMdBdef_DefWorker().getWarehouseNo(),
    					super.getMdBdef_DefWorker().getWorkerOwner(),
    					beginDate, 
    					endDate,
    					getStrQuery(),
    					pageBo, 
    					requestFlag);
    				super.writeStr(covtObjectToJson(list));
    		}								
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    //添加表格列
   	public void addColum(StringBuilder sbColumModle,StringBuilder sbFieldsNames,String date,String day){
   		sbColumModle.append(
   			    ""+date+"入(数量), " +
   				""+date+"入(重量), " +
   				""+date+"出(数量), " +
   				""+date+"出(重量), ");
   		
   		sbFieldsNames.append("{'name': 'inqtyday"+day+"'},");
   		sbFieldsNames.append("{'name': 'inweight"+day+"'},");
   		sbFieldsNames.append("{'name': 'outqtyday"+day+"'},");
   		sbFieldsNames.append("{'name': 'outweight"+day+"'},");
   		
   	}
	public Integer getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrNewName() {
		return strNewName;
	}

	public void setStrNewName(String strNewName) {
		this.strNewName = strNewName;
	}

	public String getStrOldName() {
		return strOldName;
	}

	public void setStrOldName(String strOldName) {
		this.strOldName = strOldName;
	}

	public String getStrPgmId() {
		return strPgmId;
	}

	public void setStrPgmId(String strPgmId) {
		this.strPgmId = strPgmId;
	}

	public String getStrReferenceItem() {
		return strReferenceItem;
	}

	public void setStrReferenceItem(String strReferenceItem) {
		this.strReferenceItem = strReferenceItem;
	}

	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	public String getStrProjectId() {
		return strProjectId;
	}
	public void setStrProjectId(String strProjectId) {
		this.strProjectId = strProjectId;
	}

	public int getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}

	public int getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(int beginMonth) {
		this.beginMonth = beginMonth;
	}

	public int getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(int beginDay) {
		this.beginDay = beginDay;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public int getEndDay() {
		return endDay;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
