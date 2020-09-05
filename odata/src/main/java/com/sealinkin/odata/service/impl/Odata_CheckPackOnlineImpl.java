package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.odata.model.Odata_CheckMModel;
import com.sealinkin.odata.service.IOdata_CheckPackOnlineService;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_CheckPackOnlineImpl implements IOdata_CheckPackOnlineService {
	private IGenericManager genDao;
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
	
	
	//校验任务号或者快递面单
	//checkNo但按任务号复核时该参数的值为任务号；当为快递面单时值为面单号
	//strCheckType复核方式
	@Override
	public MsgRes tscCheckTaskNo(String currEnterpriseNo, String warehouseNo,
			String strScanNo,String strWorkerNo,String strPrinterGroupNo,
			String strCheckType,String strAutoOutstock) throws Exception {
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
	//	if(str.equals("1")){//复核方式按任务号
			inList.add(currEnterpriseNo);
			inList.add(warehouseNo);//strWareHouseNo
			inList.add(strPrinterGroupNo);
			inList.add(strScanNo);//strTaksNo        
			inList.add("N");//strBoxNo         
			inList.add(strCheckType);//strCheckType     
			inList.add(strWorkerNo);
			inList.add(strAutoOutstock);//strAutoOutstock是否可自动下架回单:Y-可以,N-不可
			/*}else if(str.equals("2")){//复核方式按快递面单
			inList.add(currEnterpriseNo);
			inList.add(warehouseNo);//strWareHouseNo
			inList.add(strPrinterGroupNo);
			inList.add(checkNo);//strTaksNo        
			inList.add("N");//strBoxNo      
			inList.add(str);//strCheckType     
			inList.add(strWorkerNo);
			inList.add(strAutoOutstock);//strAutoOutstock是否可自动下架回单:Y-可以,N-不可
		}*/
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_CheckAndCreate");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"操作成功！",resultList.get(0));	
	}
	
	//获取未复核商品信息列表
	@Override
	public List<Odata_CheckLabelDModel> getCheckD(String currEnterpriseNo,
			String warehouseNo, String checkNo,String strCheckType,
			String strScanNo) throws Exception {
		String sql = "";
	    sql=" select a.check_no,a.owner_no,a.article_no,vbd.article_name,sld.advance_cell_no, "+
				"	sum(a.article_qty-a.real_qty) as uncheckQty, "+
				"trunc(sum(a.article_qty-a.real_qty)/a.packing_qty) as planBox," +
				"trunc(mod(sum(a.article_qty-a.real_qty),a.packing_qty)/vbd.QMIN_OPERATE_PACKING) as planQmin," +
				"(sum(a.article_qty-a.real_qty) - trunc(sum(a.article_qty-a.real_qty)/a.packing_qty) * a.packing_qty - trunc(mod(sum(a.article_qty-a.real_qty),a.packing_qty)/vbd.QMIN_OPERATE_PACKING) * vbd.QMIN_OPERATE_PACKING) as planDis," +
	           " vbd.barcode,  a.packing_qty,  "+
	           " f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit, "+
			   "  f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec "+
	           " from odata_check_label_d a,stock_label_d sld,bdef_defarticle vbd, stock_label_m slm "+
	           " where a.enterprise_no='" +currEnterpriseNo+"' "+
	           " and a.warehouse_no='" +warehouseNo+"'  %s1  %s2 "+
	           " and a.enterprise_no=vbd.enterprise_no  "+
	           
	           " and sld.enterprise_no=slm.enterprise_no  "+	           
	           " and sld.WAREHOUSE_NO=slm.WAREHOUSE_NO  "+
	           " and sld.CONTAINER_TYPE=slm.CONTAINER_TYPE  "+
	           " and sld.CONTAINER_NO=slm.CONTAINER_NO  "+
	           
	           " and a.enterprise_no=vbd.enterprise_no  "+
	           " and a.article_no=vbd.article_no  "+
	           " and a.article_qty-a.real_qty>0  "+
	          "  and a.enterprise_no=sld.enterprise_no  "+
	           " and a.warehouse_no=sld.warehouse_no  "+
	          "  and a.container_no=sld.container_no  "+
	           " and a.article_no=sld.article_no  "+
	          "  and a.article_id=sld.article_id " +
	          "  and a.divide_id=sld.divide_id "+
	          "  group by a.check_no,a.enterprise_no,a.owner_no,a.article_no,sld.advance_cell_no,  "+
	          " vbd.article_name,vbd.unit, vbd.barcode, a.packing_qty,vbd.QMIN_OPERATE_PACKING ";
		
		 //按面单号取得时候是传的复核单号（一个复核单号就只有一个配送对象）,按快递单号复核的第一次会传复核单号进来
	    sql = sql.replace("%s1", " and a.check_no='" +checkNo+"' ");
	    
	    //按面单号
	    if(strCheckType.equals("2") )
	    {
	    	sql = sql.replace("%s2", " and a.LABLE_NO='" +strScanNo+"' ");
		}
	    
	   //按任务单号
	    if(strCheckType.equals("1") )
	    {
	    	sql = sql.replace("%s2", " and (slm.SOURCE_NO='" +strScanNo+"' OR slm.label_no='"+strScanNo+"') ");
		}
	    
		List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql, Odata_CheckLabelDModel.class);
		//ExtListDataBo<Odata_CheckLabelDModel> extListBo=new ExtListDataBo<Odata_CheckLabelDModel>(list,0);
		return list;
	}
	
	//获取标签明细（已复核商品信息）
	@Override
	public List<Stock_LabelDModel> getStockLabelD(String currEnterpriseNo,
			String warehouseNo, String checkNo,String strCheckType,
			String strScanNo) throws Exception {
		String strSql="  select"+
			       " a.article_no,a.packing_qty,"+
			       " sum(a.real_qty) as qty,"+
			        "trunc(sum(a.real_qty)/a.packing_qty) as realBox," +
					"trunc(mod(sum(a.real_qty),a.packing_qty)/vbd.QMIN_OPERATE_PACKING) as realQmin," +
					"(sum(a.real_qty) - trunc(sum(a.real_qty)/a.packing_qty) * a.packing_qty - trunc(mod(sum(a.real_qty),a.packing_qty)/vbd.QMIN_OPERATE_PACKING) * vbd.QMIN_OPERATE_PACKING) as realDis," +
		        
			       " vbd.OWNER_ARTICLE_NO,"+
			       " vbd.barcode,"+
			       " vbd.ARTICLE_NAME     "+
			       " from odata_check_label_d a,stock_label_d sld,bdef_defarticle vbd, stock_label_m slm "+
		           " where a.enterprise_no='" +currEnterpriseNo+"' "+
		           " and a.warehouse_no='" +warehouseNo+"'  %s1 %s2 "+
		           " and a.enterprise_no=vbd.enterprise_no  "+
		           
		           " and sld.enterprise_no=slm.enterprise_no  "+	           
		           " and sld.WAREHOUSE_NO=slm.WAREHOUSE_NO  "+
		           " and sld.CONTAINER_TYPE=slm.CONTAINER_TYPE  "+
		           " and sld.CONTAINER_NO=slm.CONTAINER_NO  "+
		           
		           " and a.enterprise_no=vbd.enterprise_no  "+
		           " and a.article_no=vbd.article_no  "+
		           " and a.real_qty>0  "+
		          "  and a.enterprise_no=sld.enterprise_no  "+
		           " and a.warehouse_no=sld.warehouse_no  "+
		          "  and a.container_no=sld.container_no  "+
		           " and a.article_no=sld.article_no  "+
		          "  and a.article_id=sld.article_id " +
		          "  and a.divide_id=sld.divide_id "+
		          "  group by a.article_no,a.packing_qty, "+
		          " vbd.article_name,vbd.barcode,vbd.OWNER_ARTICLE_NO,vbd.QMIN_OPERATE_PACKING ";
			
			 //按面单号取得时候是传的复核单号（一个复核单号就只有一个配送对象）,按快递单号复核的第一次会传复核单号进来
			strSql = strSql.replace("%s1", " and a.check_no='" +checkNo+"' ");
		    
		    //按面单号
		    if(strCheckType.equals("2") )
		    {
		    	strSql = strSql.replace("%s2", " and a.LABLE_NO='" +strScanNo+"' ");
			}
		    
		   //按任务单号
		    if(strCheckType.equals("1") )
		    {
		    	strSql = strSql.replace("%s2", " and (slm.SOURCE_NO='" +strScanNo+"' OR slm.label_no='"+strScanNo+"') ");
			}
		    

		List<Stock_LabelDModel> list=genDao.getListByNativeSql(strSql, Stock_LabelDModel.class);
		return list;
	}
	
	/*//获取复核单头档配送对象的箱码
	@Override
	public List<Stock_LabelMModel> getCheckPackLabel(String currEnterpriseNo,
			String warehouseNo, String str, String checkNo) throws Exception {
		
		String strSql="select distinct a.*,d.status_name statusText, d.status_type  " +
				    "from " +
					"stock_label_m a," +
					"stock_label_m b," +
					"wms_deflabel_status d  " +
				"where " +
					"a.enterprise_no=b.enterprise_no " +
					"and a.warehouse_no=b.warehouse_no " +					
					"and a.deliver_obj=b.deliver_obj " +
					"and a.status=d.status_type  " +
					"and b.enterprise_no='"+currEnterpriseNo+"' " +
					"and b.warehouse_no='"+warehouseNo+"' " +
					"and b.deliver_obj='"+str+"' " +
				"order by a.status ";
		List<Stock_LabelMModel> list=genDao.getListByNativeSql(strSql, Stock_LabelMModel.class);
		return list;
	}
	*/
	
	
	
	@Override
	public MsgRes tscArrangeCutbox(String currEnterpriseNo, String warehouseNo,
			String strDlabel, String strPrinterGroupNo, String strWorkerNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(strWorkerNo);//操作人员
		inList.add(strDlabel);//strDLabelNo
		inList.add(strPrinterGroupNo);//工作站
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_ODATA_CHECK_CUTBOX");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"操作成功！","");	
	}
	
	@Override
	public MsgRes tscCheckBarcode(String str, String strCheckType, 
			String strScanNo,
			String sacnNum, String strWorkerNo, String strPrinterGroupNo,
			String strPrintWayBill,String strPrintPackList,String strPrintInVoice) throws Exception {
		Odata_CheckLabelDModel checkD=(Odata_CheckLabelDModel) JSON.parseObject(str, Odata_CheckLabelDModel.class);
		
		   /* String	sql=" select t.article_no,T.Article_Name from bdef_defarticle t " +
				"where t.barcode='"+checkD.getBarcode()+"' " +
				"and t.enterprise_no='"+checkD.getEnterpriseNo()+"' ";

		    List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql,Odata_CheckLabelDModel.class);	   
			if (list.size()==0){
				return new MsgRes(true,"该条码不存在","");
			}*/
			MsgRes msgRes1=getArticleForBarcodeImpl.checkBarcode(checkD.getWarehouseNo(), 
			    		checkD.getBarcode(), 
			    		checkD.getEnterpriseNo());
			
			if(!msgRes1.getIsSucc()){
				return new MsgRes(false,"该条码不存在","");
			}
			String	sql=" select t.article_no,T.Article_Name from bdef_defarticle t " +
					"where t.article_no in ("+msgRes1.getObj().toString()+") " +
					"and t.enterprise_no='"+checkD.getEnterpriseNo()+"' ";

		    List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql,Odata_CheckLabelDModel.class);	   
		    
		    
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
				
			outList.add("S");
			outList.add("S");
			outList.add("S");
			outList.add("S");
			outList.add("S");
			outList.add("S");
				
			inList.add(checkD.getEnterpriseNo());
			inList.add(checkD.getWarehouseNo());
			inList.add(checkD.getCheckNo());
			inList.add(strScanNo);
			
			inList.add(checkD.getDeliverObj().equals("")?"N":checkD.getDeliverObj());
			inList.add(list.get(0).getArticleNo());//strArticle_No
		    //inList.add("N");//strsLabelNo
		    //inList.add(strDlabelNo.equals("")?"N":strDlabelNo);//第一次传N
			inList.add(sacnNum);//nRealQty
			inList.add(strWorkerNo);//操作人
			inList.add(strPrinterGroupNo);
			
			inList.add(strPrintWayBill);
			inList.add(strPrintPackList);
			inList.add(strPrintInVoice);
			inList.add(strCheckType);			
			
			System.out.println(inList);
			
			resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_scanArticle_cutBox_sickOrder");
			if(resultList.get(5).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(5).toString());
			}
			
		    Odata_CheckLabelDModel checkLabelD = new  Odata_CheckLabelDModel();
		    checkLabelD.setArticleName(list.get(0).getArticleName());
		    checkLabelD.setStrCloseFlag(resultList.get(0).toString());
		    checkLabelD.setDeliverObj(resultList.get(1).toString());
		    checkLabelD.setLableNo(resultList.get(2).toString());
		    
		    checkLabelD.setStrPackMateName(resultList.get(3)==null ? "":resultList.get(3).toString());
		    //是否完成
		    checkLabelD.setStrFinishFlag(resultList.get(4).toString());
		    
			return new MsgRes(true,"",checkLabelD);
	}
	
	//取箱号
	public MsgRes tscGetNewLabel(String strEnterpriseNo,String strWarehouseNo, String strLabelNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
				
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strLabelNo);//strLabelNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_getnew_containerNo");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		return new MsgRes(true,"",resultList.get(0).toString());
	}
	
	//转病单  8-12修改
	@Override
	public MsgRes tscChangeSickOrder(String currEnterpriseNo, String warehouseNo,
			String checkNo,String strDeliverObj,String strWorkerNo) throws Exception {
	
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
				
			outList.add("S");
			inList.add(currEnterpriseNo);
			inList.add(warehouseNo);
			inList.add(checkNo);
			inList.add(strDeliverObj);
			inList.add(strWorkerNo);
				
			System.out.println(inList);
				
			resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_DiffCheckSave");
				
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}				
			return new MsgRes(true,"","");
	}
	
	//包材出货
	@Override
	public MsgRes tscOutPackMete(String currEnterpriseNo, String warehouseNo,
				String strLabelNo,String str2,
				String articleQTY,
				String strWorkerNo) throws Exception {
			
			Odata_CheckLabelDModel checkD=(Odata_CheckLabelDModel) JSON.parseObject(str2, Odata_CheckLabelDModel.class);
			MsgRes msgRes1=getArticleForBarcodeImpl.checkBarcode(checkD.getWarehouseNo(), 
		    		checkD.getBarcode(), 
		    		checkD.getEnterpriseNo());
		
			if(!msgRes1.getIsSucc()){
				return new MsgRes(false,"该条码不存在","");
			}
			String	sql=" select t.article_no,T.Article_Name from bdef_defarticle t " +
					"where t.article_no in ("+msgRes1.getObj().toString()+") " +
					"and t.enterprise_no='"+checkD.getEnterpriseNo()+"' ";
	
		    List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql,Odata_CheckLabelDModel.class);	
		
				List inList=new ArrayList();
				List outList=new ArrayList();
				List resultList=new ArrayList();
					
				outList.add("S");
				inList.add(currEnterpriseNo);
				inList.add(warehouseNo);
				inList.add(strLabelNo);				
				inList.add(list.get(0).getArticleNo());
				inList.add(articleQTY);
				inList.add(strWorkerNo);
					
				System.out.println(inList);
					
				resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_strPackMateDeal");
					
				if(resultList.get(0).toString().indexOf("N|")!=-1)
				{
					throw new Exception(resultList.get(0).toString());
				}				
				return new MsgRes(true,"","");
		}
	
	////获取未复核列表（弹出框）
	@Override
	public List<Stock_LabelMModel> getUnCheckObjList(String currEnterpriseNo,
			String warehouseNo, 
			String strCheckNo,
			String strCheckType,
			String strScanNo) throws Exception {
		//8-11修改
		String sql ="select distinct  ocd.deliver_obj,ocd.check_no, "+
				"(select count(distinct t.article_no) from odata_check_d t  "+
				"		  where t.deliver_obj=ocd.deliver_obj and t.enterprise_no=ocd.enterprise_no  "+
				"		  and t.warehouse_no=ocd.warehouse_no)  countArticleNo," +
				"       a.article_name articleName,a.article_no articleNo,a.barcode barcode, " +
				"       ocd.article_qty articleQty,ocd.real_qty realQty,oxm.shipper_deliver_no shipperDeliverNo  "+
				"		from odata_check_label_d ocd,bdef_defarticle a, odata_exp_m oxm ,stock_label_m slm"+				
				"		where ocd.enterprise_no='"+currEnterpriseNo+"' "+
				"		and ocd.warehouse_no='"+warehouseNo+"' "+
				"		and ocd.enterprise_no = a.enterprise_no "+
				"		and ocd.enterprise_no = oxm.enterprise_no "+
				"		and ocd.warehouse_no = oxm.warehouse_no %s2 "+
				
				"		and ocd.enterprise_no = slm.enterprise_no "+
				"		and ocd.warehouse_no = slm.warehouse_no "+
				"		and ocd.container_no = slm.container_no "+
				
				"		and ocd.exp_no = oxm.exp_no "+
				"		and ocd.exp_type = oxm.exp_type "+
				"		and ocd.article_no = a.article_no "+
				"		and ocd.check_no='"+strCheckNo+"' "+
				"		and ocd.status < '13' ";
		
		 //按面单号
	    if(strCheckType.equals("2") )
	    {
	    	sql = sql.replace("%s2", " and ocd.LABLE_NO='" +strScanNo+"' ");
		}
	    
	   //按任务单号
	    if(strCheckType.equals("1") )
	    {
	    	sql = sql.replace("%s2", " and (slm.SOURCE_NO='" +strScanNo+"' OR slm.label_no='"+strScanNo+"') ");
		}
						  
		
		List<Stock_LabelMModel> list = genDao.getListByNativeSql(sql, Stock_LabelMModel.class);
		return list;
	}
	
	//获取未复核单数/配送对象
	@Override
	public MsgRes getUnCheckObj(String currEnterpriseNo, String warehouseNo,
			String checkNo,
			String strCheckType,
			String strScanNo) throws Exception {
		String sql ="select count(distinct  ocd.deliver_obj) from odata_check_label_d ocd,stock_label_m slm "+
				"where ocd.enterprise_no='"+currEnterpriseNo+"' "+
				"  and ocd.warehouse_no='"+warehouseNo+"' "+
				"  and ocd.check_no='"+checkNo+"'  %s2 "+
				"		and ocd.enterprise_no = slm.enterprise_no "+
				"		and ocd.warehouse_no = slm.warehouse_no "+
				"		and ocd.container_no = slm.container_no "+
				
				"  and ocd.status < '13' ";
		 //按面单号
	    if(strCheckType.equals("2") )
	    {
	    	sql = sql.replace("%s2", " and ocd.LABLE_NO='" +strScanNo+"' ");
		}
	    
	   //按任务单号
	    if(strCheckType.equals("1") )
	    {
	    	sql = sql.replace("%s2", " and (slm.SOURCE_NO='" +strScanNo+"' OR slm.label_no='"+strScanNo+"') ");
		}
	    
		Integer count = genDao.getCountByNativeSql(sql);
		
		return new MsgRes(true,"",count);
	}
	
	
	//将复核一半的配送对象/单信息显示出来,并校验
	@Override
	public MsgRes tscCheckUnfinished(
									String currEnterpriseNo,
									String warehouseNo, 
									String strCheckNo,
									String strCheckType,
									String strScanNo) throws Exception {
		String sql ="select T.DELIVER_OBJ,T.D_LABEL_NO from odata_check_label_d t ," +
				" (select ocd.enterprise_no,ocd.warehouse_no,ocd.check_no, ocd.deliver_obj "+
			   " from odata_check_label_d ocd   "+
		      "  where ocd.enterprise_no='"+currEnterpriseNo+"'      "+
		      "  and ocd.warehouse_no='"+warehouseNo+"'      "+
		      "  and ocd.check_no='"+strCheckNo+"'     "+
		      "  and (ocd.status='11' or  "+
		      "  (ocd.status='10' and  exists (select  'x'  "+
		      "      from odata_check_label_d iocd     "+
		      "      where iocd.enterprise_no = ocd.enterprise_no   "+    
		      "          and iocd.warehouse_no=ocd.warehouse_no    "+   
		      "          and iocd.check_no=ocd.check_no     "+  
		      "          and iocd.deliver_obj=ocd.deliver_obj   "+    
		      "          and iocd.status='13'))))b "+
				 " where t.enterprise_no=b.enterprise_no "+
				 " and t.warehouse_no=b.warehouse_no "+
				 " and t.check_no=b.check_no "+
				 " and t.deliver_obj=b.deliver_obj "+
				 " and t.d_label_no<>'N' ";
		
		List<Odata_CheckLabelDModel> list  = genDao.getListByNativeSql(sql, Odata_CheckLabelDModel.class);
		if(list.size()==0){
			return new MsgRes(false,"","");
		}else{
			return new MsgRes(true,"",list);
		}
		
	}
	
	//根据快递单号或者配送对象取布控标志
	//strCheckType复核方式：1任务号/箱号；2快递单号
	//str快递单号或者配送对象（根据复核方式）
	@Override
	public MsgRes getECbukong(String currEnterpriseNo,
			String warehouseNo,
			String strCheckNo,
			String strDeliverObj) throws Exception {
		String sql ="";
		
		sql="select f.text from " +
				"odata_exp_m t,wms_deffieldval f " +
				"where t.enterprise_no='"+currEnterpriseNo+"' " +
				"and t.warehouse_no='"+warehouseNo+"' " +
				
				"and nvl(t.rsv_varod3,'0')=f.value and f.table_name='ODATA_EXP_M' and f.colname='RSV_VAROD3'"+
				"   and (exists(select 'x' from odata_check_d ocd "+
				" where ocd.enterprise_no=t.enterprise_no "+
				" and ocd.warehouse_no=t.warehouse_no "+
				" and ocd.CHECK_NO='"+ strCheckNo +"'" +
				" and ocd.DELIVER_OBJ='"+ strDeliverObj +"'" +
				" and ocd.exp_no=t.exp_no) "+
				" or exists(select 'x' from odata_check_dhty ocd "+
				" where ocd.enterprise_no=t.enterprise_no "+
				" and ocd.warehouse_no=t.warehouse_no "+
				" and ocd.CHECK_NO='"+ strCheckNo +"'" +
				" and ocd.DELIVER_OBJ='"+ strDeliverObj +"'" +
				" and ocd.exp_no=t.exp_no)) ";
		
		
		List<String> list = genDao.getListByNativeSql(sql);
		return new MsgRes(true, "", list.get(0));
	}
	
		
}
