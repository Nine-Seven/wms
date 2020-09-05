package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.model.Device_DivideDModel;
import com.sealinkin.bdef.model.Device_DivideMModel;
import com.sealinkin.cdef.model.Cdef_DefcellModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.odata.model.Odata_DivideMModel;
import com.sealinkin.odata.service.IOdata_DivideWallService;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_DivideWallImpl implements IOdata_DivideWallService{
	
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

	@Override
	public List getOdata_DivideWallCell(String strEnterpriseNo,
			String strWarehouseNo,String strDeviceNo) throws Exception {
		
		String sql = "select a.DEVICE_CELL_NO deviceCellNo,a.bay_x bayX,a.stock_y stockY " +
				"from device_divide_d a " +
				"where a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.DEVICE_NO='"+strDeviceNo+"' " +
				"order by a.stock_y desc , a.bay_x";
		List<Device_DivideDModel> list=genDao.getListByNativeSql(sql,Device_DivideDModel.class);
		String sql1 = "select count(*) countBayX from (SELECT distinct t.bay_x " +
				"FROM DEVICE_DIVIDE_D T " +
				"where t.warehouse_no='"+strWarehouseNo+"' " +
				"and t.DEVICE_NO='"+strDeviceNo+"' " +
				"and t.enterprise_no='"+strEnterpriseNo+"') ";
		String sql2 = "select count(*) countStockY from (SELECT distinct t.stock_y  " +
				"FROM DEVICE_DIVIDE_D T " +
				"where t.warehouse_no='"+strWarehouseNo+"' " +
				"and t.DEVICE_NO='"+strDeviceNo+"' " +
				"and t.enterprise_no='"+strEnterpriseNo+"') ";
	    List<Device_DivideDModel>  countBayX = genDao.getListByNativeSql(sql1,Device_DivideDModel.class);
	    List<Device_DivideDModel>  countStockY = genDao.getListByNativeSql(sql2,Device_DivideDModel.class);
	    System.out.println("BayX:"+countBayX.get(0).getCountBayX());
	    System.out.println("StockY:"+countStockY.get(0).getCountStockY());
	    list.get(0).setCountBayX(countBayX.get(0).getCountBayX());
	    list.get(0).setCountStockY(countStockY.get(0).getCountStockY());
	    
		if(list.size() == 0){
        	return null;
        }else{
        	return list;
        }
		/*
		String sql="";
        sql="select " +
        		"max(to_number(stock_x)) stock_x " +
    		"from " +
    			"cdef_defcell a,cdef_defarea b " +
    		"where " +
	    		"a.enterprise_no=b.enterprise_no " +
	    		"and a.warehouse_no=b.warehouse_no " +
	    		"and a.ware_no=b.ware_no " +
	    		"and a.area_no=b.area_no " +
	    		"and b.AREA_ATTRIBUTE='1' " +
	    		"and b.ATTRIBUTE_type='9' " +
	    		"and b.AREA_USETYPE='1' " +
	        	"and a.warehouse_no='"+strWarehouseNo+"' " +
	    		"and a.enterprise_no='"+strEnterpriseNo+"' ";
        List<Cdef_DefcellModel> listStock_x=genDao.getListByNativeSql(sql, Cdef_DefcellModel.class, 0, 100000);
        sql="select max(to_number(bay_x)) bay_x " +
    		"from " +
    			"cdef_defcell a,cdef_defarea b " +
    		"where " +
	    		"a.enterprise_no=b.enterprise_no " +
	    		"and a.warehouse_no=b.warehouse_no " +
	    		"and a.ware_no=b.ware_no " +
	    		"and a.area_no=b.area_no " +
	    		"and b.AREA_ATTRIBUTE='1' " +
	    		"and b.ATTRIBUTE_type='9' " +
	    		"and b.AREA_USETYPE='1' " +
	        	"and a.warehouse_no='"+strWarehouseNo+"' " +
	    		"and a.enterprise_no='"+strEnterpriseNo+"' ";
        List<Cdef_DefcellModel> listBay_x=genDao.getListByNativeSql(sql, Cdef_DefcellModel.class, 0, 100000);
        
        if(listStock_x.size()==0 || listBay_x.size()==0){
        	return null;
        }
        StringBuilder sb=new StringBuilder("");
        for(int i=1;i<=Integer.parseInt(listStock_x.get(0).getStockX());i++){
        	for(int j=1;j<=Integer.parseInt(listBay_x.get(0).getBayX());j++){
        		sb.append("max(case  when to_number(stock_x)='"+i+"' and to_number(bay_x)='"+j+"' then cell_no else null end) col"+i+j+",");
        	}
        }
		sql=" select "+sb.toString().substring(0, sb.toString().length()-1)+
			" from " +
    			"cdef_defcell a,cdef_defarea b " +
    		" where " +
	    		"a.enterprise_no=b.enterprise_no " +
	    		"and a.warehouse_no=b.warehouse_no " +
	    		"and a.ware_no=b.ware_no " +
	    		"and a.area_no=b.area_no " +
	    		"and b.AREA_ATTRIBUTE='1' " +
	    		"and b.ATTRIBUTE_type='9' " +
	    		"and b.AREA_USETYPE='1' " +
	        	"and a.warehouse_no='"+strWarehouseNo+"' " +
	    		"and a.enterprise_no='"+strEnterpriseNo+"' "+
			" group by " +
				"a.enterprise_no," +
				"a.warehouse_no," +
				"a.ware_no," +
				"a.area_no," +
				"a.stock_no," +
				"a.stock_y " +
			"order by " +
				"a.stock_y";
		List list=genDao.getListByNativeSql(sql);		
		return list;	
	*/}

	@Override
	public List<Stock_LabelMModel> getOdata_Divide_Direct(String strEnterpriseNo,
			String strWarehouseNo, String strOwnerNo, String str)
			throws Exception {
		String strSql=" select * " +
		  " from " +
		  		"stock_label_m a " +
		  " where " +
		   		"a.enterprise_no='"+strEnterpriseNo+"' " +
		   		"and a.warehouse_no='"+strWarehouseNo+"' " +
		   		//"and a.owner_no in(" +strOwnerNo+") "+
		   		"and (a.LABEL_NO='"+str+"' or a.SOURCE_NO='"+str+"')";
		
		List list=genDao.getListByNativeSql(strSql,Stock_LabelMModel.class);
		return  (List<Stock_LabelMModel>)list;
	}

	@Override
	public List<Odata_DivideDModel> tscSelectEquipmentNo(String strEnterpriseNo,
			String strWarehouseNo, 
			String strEquipmentNo, 
			String strDeviceNo,
			String strWrokNo,
			String strDockNo)
			throws Exception {
		System.out.println("strDeviceNo:"+strDeviceNo+"  strEquipmentNo:"+strEquipmentNo);
		/*String strSql="select * " +
				"from " +
				"odata_divide_m a " +
				"where a.enterprise_no='" +strEnterpriseNo+"' "+
				"and a.warehouse_no='" +strWarehouseNo+"' "+
				//"and a.DEVICE_NO='"+strEquipmentNo+"' "+
				"and a.DEVICE_NO='"+strDeviceNo+"' "+
				"and a.status='10'";
		List<Odata_DivideMModel> listM=genDao.getListByNativeSql(strSql,Odata_DivideMModel.class);*/
		//if(listM.size()==0){
			//调用发单
		MsgRes msg = tscSendDivide(strEnterpriseNo, 
					strWarehouseNo, 
					strEquipmentNo, 
					strDeviceNo,
					strWrokNo, 
					strDockNo);
		System.out.println("msg: " +msg.getObj());
		//}		
		//
		List<Odata_DivideDModel> list=getOdata_Divide1(strEnterpriseNo, 
				strWarehouseNo, 
				strDeviceNo,(String)msg.getObj());	
		//Odata_DivideDModel test = new Odata_DivideDModel();
		//test.setDivideNo((String)msg.getObj());
		//list.add(test);
		return list;		
	}

	@Override
	public MsgRes getOdata_Divide(String strEnterpriseNo,
			String strWarehouseNo, String strEquipmentNo,
			String str) throws Exception {
		
		//根据扫描的商品条码查找商品信息
		MsgRes msg =getArticleForBarcodeImpl.checkBarcode(strWarehouseNo,
				str,
				strEnterpriseNo);
		if(!msg.getIsSucc())
		{
			return msg;
		}
		
		String strSql=" select " +
							"b.divide_no," +
							"b.exp_no," +
							"b.dps_cell_no," +
							"b.article_no," +
							"c.article_name," +
							"sum(b.article_qty) article_qty, " +
							"sum(b.real_qty) real_qty " +
						"from " +
							"odata_divide_m a," +
							"odata_divide_d b," +
							"bdef_defarticle c  " +
						"where " +
							"a.enterprise_no=b.enterprise_no " +
							"and a.divide_no=b.divide_no " +
							"and b.enterprise_no=c.enterprise_no and b.article_no=c.article_no " +
							"and a.enterprise_no='"+strEnterpriseNo+"' " +
					   		"and a.warehouse_no='"+strWarehouseNo+"' " +
							"and a.DEVICE_NO='"+strEquipmentNo+"' " +
							"and (b.article_qty-b.real_qty)>0 " +
							"and b.article_no in(" +msg.getObj().toString()+") "+
						"group by " +
							"b.exp_no,b.dps_cell_no,b.article_no,c.article_name,b.divide_no ";
		List<Odata_DivideDModel> list=genDao.getListByNativeSql(strSql,Odata_DivideDModel.class);
		if(list.size()==0){
			msg.setIsSucc(false);
			msg.setMsg("该分播单不存在该商品或已分播完毕，请核实！");
			return msg;
		}else{
			msg.setObj(list);
			return  msg;
		}		
	}

	@Override
	public MsgRes tscSendDivide(String strEnterpriseNo, String strWarehouseNo,
			String strEquipmentNo,String strDeviceNo, String strWrokNo, String strDockNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);//strEnterPriseNo
		inList.add(strWarehouseNo);//strWarehouseNo
		inList.add(strDockNo);//strDockNo
		inList.add(strDeviceNo);//strDeviceNo  墙号
		inList.add(strEquipmentNo);//strEquipmentNo  label_no  分播组号（标签号）
		inList.add(strWrokNo);//strAssign_No
		inList.add(strWrokNo);//strUserID
		inList.add("0");//strPrintWayBill
		inList.add("0");//strPrintPackList
		inList.add("0");//strPrintInVoice
		System.out.println(inList);
		
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.p_Wall_Send_odata_Divide");
		System.out.println("测试: "+resultList.get(1).toString());
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"发单成功！",resultList.get(0).toString());
	}

	@Override
	public MsgRes tscSaveDivide(String strEnterpriseNo, String strWarehouseNo,
			String strEquipmentNo,String strLabelNo,String strArticleNo,			
			String strAssign_No,
			String strWrokNo) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);		
		inList.add(strEquipmentNo);
		
		inList.add(strArticleNo);
		
		inList.add(strWrokNo);//strAssign_No
		inList.add(strWrokNo);//strUserID
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.p_Wall_Save_odata_Divide");
		System.out.println("resultList测试: " + resultList.get(2).toString());
		if(resultList.get(2).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(2).toString());
		}
		return new MsgRes(true,"回单成功！",resultList);
	}

	@Override
	public MsgRes tscSureDivide(String strEnterpriseNo, String strWarehouseNo,
			String strEquipmentNo, String strAssign_No, String strWrokNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);		
		inList.add(strEquipmentNo);
		inList.add(strWrokNo);//strAssign_No
		inList.add(strWrokNo);//strUserID
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.p_Wall_Sure_odata_Divide");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"释放单号成功！","");
	}

	@Override
	public List<ComboxBo> getOdata_Divide_Art(String strEnterpriseNo,
			String strWarehouseNo, String strOwnerNo,String strEquipmentNo, String str)
			throws Exception {
		String strSql="select distinct " +
							"c.article_no value," +
							"c.article_name text," +
							"'['|| ltrim(c.owner_article_no)||']'||c.article_name dropValue " +
						"from " +
							"odata_divide_m a," +
							"odata_divide_d b," +
							"bdef_defarticle c " +
						"where " +
							"a.enterprise_no=b.enterprise_no " +
							"and a.divide_no=b.divide_no " +
							"and b.enterprise_no=c.enterprise_no " +
							"and b.article_no=c.article_no " +
					   		"and a.enterprise_no='"+strEnterpriseNo+"' " +
					   		"and a.warehouse_no='"+strWarehouseNo+"' " +
					   		"and a.owner_no in(" +strOwnerNo+") " +
					   		"and a.DEVICE_NO='"+strEquipmentNo+"' " +
					   		"and (b.article_qty-b.real_qty)>0 " +
			   				"and (c.article_no like '%"+str+"%' " +
			   						"or c.article_name like '%"+str+"%' " +
			   								"or c.owner_article_no like '%"+str+"%' " +
			   										"or c.barcode like '%"+str+"%' ) ";

		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 10);
		return  (List<ComboxBo>)list;
	}

	@Override
	public List<Odata_DivideDModel> getOdata_Divide1(String strEnterpriseNo,
			String strWarehouseNo, String strDeviceNo,String strDivideNo) throws Exception {
		
		//7-20添加
		String strSql=" select a.divide_no," +
				"c.qmin_operate_packing," +
				"oem.shipper_deliver_no," +
				"b.dps_cell_no," +
				"sum(b.article_qty) article_qty, " +
				"sum(b.real_qty) real_qty " +
			"from odata_exp_m oem , " +
				"odata_divide_m a," +
				"odata_divide_d b, " +
				"bdef_defarticle c " +
			"where a.enterprise_no=oem.enterprise_no " +
			" and a.warehouse_no=oem.warehouse_no " +
			" and b.EXP_NO=oem.EXP_NO " +
			" and b.EXP_TYPE=oem.EXP_TYPE " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.divide_no=b.divide_no " +
				"and b.enterprise_no=c.enterprise_no " +
				"and b.article_no=c.article_no " +
				"and a.divide_no='"+strDivideNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
		   		"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.DEVICE_NO='"+strDeviceNo+"' " +
			"group by oem.shipper_deliver_no,a.divide_no," +
				"b.dps_cell_no,c.qmin_operate_packing";	
			List<Odata_DivideDModel> list=genDao.getListByNativeSql(strSql,Odata_DivideDModel.class);
			return  list;
	}

	//检查分播人员编号是否存在hj
	@Override
	public MsgRes checkWorkerNo(String strEnterpriseNo, String strWorkerNo)
			throws Exception {
		MsgRes msg = new MsgRes();
		String strSql=" select b.* ," +
				"b.worker_no," +
				"b.worker_name " +
			"from " +
				"bdef_defworker b " +
			"where " +
				" b.enterprise_no='"+strEnterpriseNo+"' " +
		   		" and b.worker_no='"+strWorkerNo+"'";
		List<Bdef_DefWorkerModel> list=genDao.getListByNativeSql(strSql,Bdef_DefWorkerModel.class);
		//System.out.println("测试: " + list.get(0).getWorkerName() + "  " + list.get(0).getStatus());
		//判断
		if(list.size() == 0){
			msg.setIsSucc(true);
			msg.setMsg("该员工不存在");
			msg.setObj("");
		}else{
			if(list.get(0).getStatus().intValue() == 0){
				msg.setIsSucc(true);
				msg.setMsg("该员工的状态为停用");
				msg.setObj("");
			}else{
				msg.setIsSucc(true);
				msg.setMsg("该员工可以使用");
				msg.setObj(list.get(0));
			}
		}
		return msg;
	}

	@Override
	public MsgRes checkdeviceNo(String enterpriseNo, String warehouseNo,
			String deviceNo) throws Exception {
		String strSql="select a.DEVICE_NO " +
				"from device_divide_d  a " +
				"where a.warehouse_no= '"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.DEVICE_NO = '"+deviceNo+"' ";
		
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0){
			return new MsgRes(false,"该分播墙号不存在","该分播墙号不存在");
		}else{
			return new MsgRes(true,"",JSON.toJSONString(list));
		}
	}

	//从device_divide_m 获得 分播墙号device_no集合  
	@Override
	public MsgRes getOdata_DivideWallDeviceNo(String strEnterpriseNo, String strWarehouseNo)throws Exception {
		MsgRes msg = new MsgRes();
		String strSql="select * " +
				"from " +
				"device_divide_m a " +
				"where a.enterprise_no='" +strEnterpriseNo+"' "+
				"and a.warehouse_no='" +strWarehouseNo+"' "+
				"and a.device_type='05' and a.STATUS='0' " +
				" and exists (select 'x' "+
				" from device_divide_d d "+
				" where d.enterprise_no = a.enterprise_no "+
				" and d.warehouse_no = a.warehouse_no "+
				" and d.device_no = a.device_no) ";
		List<Device_DivideMModel> list = genDao.getListByNativeSql(strSql,Device_DivideMModel.class);
		//判断
		if(list.size() == 0){
			msg.setIsSucc(true);
			msg.setMsg("device_divide_m表中没有数据！");
			msg.setObj("");
		}else{
			msg.setIsSucc(true);
			msg.setMsg("请求成功！");
			msg.setObj(list);
		}
		return msg;		
	}

	//通过标签号获得对应的商品信息  hj 7-1
	@Override
	public ExtListDataBo<Odata_DivideDModel> queryDivideDetailList(
			String enterpriseNo, String strWarehouseNo, String strDivide_No,
			PageBo poPageBo) throws Exception {
		String strSql = "select oem.shipper_deliver_no,sld.ADVANCE_CELL_NO advanCellNO," +
				"d.dps_cell_no dpsCellNo,sum(d.article_qty) articleQty,sum(d.real_qty) realQty," +
				"d.article_no articleNo,art.article_name articleName,d.deliver_obj deliverObj,art.barcode barCode " +
				"from odata_exp_m oem,stock_label_d sld, " +
				" odata_divide_d d,bdef_defarticle art " +
				"where d.enterprise_no=oem.enterprise_no " +
				" and d.warehouse_no=oem.warehouse_no " +
				" and d.EXP_NO=oem.EXP_NO " +
				" and d.EXP_TYPE=oem.EXP_TYPE " +
				
				" and d.enterprise_no=sld.enterprise_no " +
				" and d.warehouse_no=sld.warehouse_no " +
				
				" and d.S_CONTAINER_NO=sld.CONTAINER_NO " +
				" and d.DIVIDE_ID=sld.DIVIDE_ID " +
				" and d.enterprise_no ='"+enterpriseNo+"' " +
				" and d.warehouse_no='"+strWarehouseNo+"' " +
				" and d.divide_no='"+strDivide_No+"' " +				
				" and d.enterprise_no = art.enterprise_no" +
				" and d.article_no = art.article_no " +
				" group by oem.shipper_deliver_no,sld.ADVANCE_CELL_NO,"+
				" d.exp_no,d.dps_cell_no,d.article_no,art.article_name,d.deliver_obj,art.barcode " +
				" order by d.dps_cell_no ";
				
		String strTotsql = "select count(*) from (" + strSql +")";
		List<Odata_DivideDModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Odata_DivideDModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Odata_DivideDModel.class,poPageBo.getStart(),poPageBo.getPagesize());
		intCount = genDao.getCountByNativeSql(strTotsql);
		extListBo= new ExtListDataBo<Odata_DivideDModel>(list, intCount);
		
        return extListBo;
	}
}


















