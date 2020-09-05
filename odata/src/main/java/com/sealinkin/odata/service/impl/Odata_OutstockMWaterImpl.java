package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.util.JSONUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.odata.service.IOdata_OutstockMReceipt;
import com.sealinkin.odata.service.IOdata_OutstockMWaterService;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.odata.ArticleDateModel;
import com.sealinkin.protocolExchange.odata.ListOutStockAnswerModel;
import com.sealinkin.protocolExchange.odata.ODataGetOutStockInfoModel;
import com.sealinkin.protocolExchange.odata.OdataOutStockRequestModel;
import com.sealinkin.protocolExchange.odata.OutStockDateAnswerModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.MyDateMorpher;
import com.sealinkin.util.StringUtil;

/**
 * 流水标签回单
 * @author 潘振兴
 *
 */
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class Odata_OutstockMWaterImpl implements IOdata_OutstockMWaterService{
	
	private IGenericManager genDao;
	private IOdata_OutstockMWaterService odata_OutstockMWaterServiceImpl;
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	public IOdata_OutstockMWaterService getOdata_OutstockMWaterServiceImpl() {
		return odata_OutstockMWaterServiceImpl;
	}
	public void setOdata_OutstockMWaterServiceImpl(
			IOdata_OutstockMWaterService odata_OutstockMWaterServiceImpl) {
		this.odata_OutstockMWaterServiceImpl = odata_OutstockMWaterServiceImpl;
	}
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
	/**
	 * 获取下架单头档信息
	 */
	@Override
	public ExtListDataBo<Odata_OutstockMModel> getWaterM(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String strWave, 
			String strBatch,
			int intStart, int intPagesize) 
	{
		String sql="select distinct oom.* , " +
				" f_get_fieldtext('N',"
				+" 'STATUS',"
				+" oom.status)statusText,k.worker_name "+
				" from " + 
		           "odata_outstock_m oom , odata_outstock_d ood,bdef_defworker k   " +
				   " where 1=1 and oom.enterprise_no=k.enterprise_no(+) and oom.warehouse_no=k.warehouse_no(+) and oom.handout_name=k.worker_no(+) " +
				   " and oom.warehouse_No=ood.warehouse_No and oom.enterprise_no=ood.enterprise_no " +
				   " and oom.outstock_no =ood.outstock_no "+
		           " and oom.task_type='0' " +
		           "and oom.operate_type='C' " +
		           "and oom.outstock_type='0' "+
				   " and oom.batch_no='"+strBatch+"' " +
				   "and ood.wave_no='"+strWave+"' "+
				  " and oom.enterprise_no='"+enterpriseNo+"' " +
					"and oom.warehouse_no='"+strWarehouseNo+"' "
				   ;  
		
		sql =sql +  "order by oom.enterprise_no desc ";
		String strTotsql="select count(*) from (" + sql + ") " ;
		List<Odata_OutstockMModel> list = genDao.getListByNativeSql(sql,Odata_OutstockMModel.class,intStart,intPagesize);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_OutstockMModel> extListBo= new ExtListDataBo<Odata_OutstockMModel>(list, count);
        return extListBo;
	}
	/**
	 * 获取标签列表
	 */
	@Override
	public ExtListDataBo<Odata_OutstockDModel> getWaterDLabel(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str,
			String strSendFlag,
			int intstart, int intpagesize) throws Exception {
		

		String sql="select distinct sl.label_no  " +
				" from " + 
		           "stock_label_m sl " +
		          "where sl.SOURCE_NO='"+str+"' "+
				  " and sl.enterprise_no='"+enterpriseNo+"' " +
					"and sl.warehouse_no='"+strWarehouseNo+"' "
				   ;  
		
		sql =sql +  "order by sl.enterprise_no desc ";
		String strTotsql="select count(*) from (" + sql + ") " ;
		List<Odata_OutstockDModel> list = genDao.getListByNativeSql(sql,Odata_OutstockDModel.class,intstart,intpagesize);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_OutstockDModel> extListBo= new ExtListDataBo<Odata_OutstockDModel>(list, count);
        return extListBo;
	
		
		}
	/**
	 * 获取下架单明细列表
	 */
	@Override
	public ExtListDataBo<Odata_OutstockDModel> getWaterD(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str,
			String strSendFlag,
			int intstart, int intpagesize) throws Exception {
		
		String sql="select  t.cust_name,ood.s_cell_no ,ood.deliver_area,ood.article_no,b.barcode,b.article_name, " +
				//add by huangcx at 20160601
				"b.qmin_operate_packing,b.unit_packing, " +
				"sum(ood.article_Qty) as articleQty , " +
				"trunc(sum(ood.article_Qty)/ood.packing_qty) as planBox," +
				"trunc(mod(sum(ood.article_Qty),ood.packing_qty)/b.qmin_operate_packing) as planQmin," +
				"(sum(ood.article_Qty) - trunc(sum(ood.article_Qty)/ood.packing_qty) * ood.packing_qty - trunc(mod(sum(ood.article_Qty),ood.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis," +
				//end add
				//"nvl(p.packing_unit, decode(ood.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) unit," +
				"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingUnit,"+
				"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,b.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,b.unit_packing) Unit,"+
				"f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingSpec,"+
				"f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,b.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,b.unit_packing) spec,"+
				"s.produce_date ,ood.packing_qty " +
				" from " + 
		           " odata_outstock_d ood ,bdef_defarticle b , stock_article_info s ,bdef_article_packing p ,bdef_defcust t " +
		           " where ood.enterprise_no=b.enterprise_no  " +
		           "  and ood.article_no = b.article_no  " +
		           " and ood.owner_no=t.owner_no " +
		           " and ood.enterprise_no=t.enterprise_no  " +
		           "  and ood.cust_no = t.cust_no  " +
		           " and ood.owner_no=b.owner_no " +
		           "  and ood.article_no = s.article_no  " +
		           "  and ood.article_id = s.article_id  " +
		           "  and ood.enterprise_no = s.enterprise_no  " +
		           
		           "  and ood.enterprise_no = p.enterprise_no(+)  " +
		           "  and ood.article_no = p.article_no(+)  " +
		           "  and ood.packing_qty = p.packing_qty(+)  " +
		          // " and oom.task_type='0' and oom.operate_type='c' and oom.outstock_type='0' "+
				   "and ood.outstock_no='"+str+"' "+
				  " and ood.enterprise_no='"+enterpriseNo+"' " +
					"and ood.warehouse_no='"+strWarehouseNo+"' " +
				  
				  " group by ood.enterprise_no,ood.owner_no,b.unit_packing," +
				  "  t.cust_name, ood.s_cell_no, ood.deliver_area,b.barcode,b.article_name,p.packing_unit,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit,ood.article_no,s.produce_date,ood.packing_qty " +
				  "order by ood.s_cell_no,ood.article_no " ;
	
		String strTotsql="select count(*) from (" + sql + ") " ;
		List<Odata_OutstockDModel> list = genDao.getListByNativeSql(sql,Odata_OutstockDModel.class,intstart,intpagesize);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_OutstockDModel> extListBo= new ExtListDataBo<Odata_OutstockDModel>(list, count);
        return extListBo;
	
		

}
	

	/**
	 * 流水标签回单》波次下拉事件
	 */
	@Override
	public List<ComboxBo> getWaveWaterCombo(String enterpriseNo,String strWarehouseNo,String strWorkerOwner,
			String strFlag, String str,String strSendFlag,String strCheckFlag, 
			String strB2CYesOrNo,int intStart, int intPagesize) {
		
		String sql = " select distinct oom.wave_no value,oom.wave_no text," +
				" oom.wave_no dropValue " +
				" from " + 
		           "odata_outstock_m oom ,odata_locate_batch t   " +
				   " where oom.enterprise_no=t.enterprise_no" +
					" and oom.warehouse_no=t.warehouse_no" +
					" and oom.wave_no=t.wave_no %s0 " +
		          " and oom.task_type='0' and oom.operate_type='C' and oom.outstock_type='0' "+
				  " and oom.enterprise_no='"+enterpriseNo+"' " +
					"and oom.warehouse_no='"+strWarehouseNo+"' "
				   ;  
		if(strB2CYesOrNo!= null && strB2CYesOrNo.equals("2")){//电商
			sql = sql.replace("%s0", " and t.industry_flag='2' ");
		}else{//普通
			sql = sql.replace("%s0", " and t.industry_flag='1' ");
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	/**
	 * 流水标签回单》批次下拉事件
	 */
	@Override
	public List<ComboxBo> getBatchWaterCombo(String enterpriseNo,String strWarehouseNo,String strWorkerOwner,
			String strFlag, String strWave,String strSendFlag,String strCheckFlag, int intStart, int intPagesize) {
		
		String sql = "select distinct oom.batch_no value,oom.batch_no text," +
				"oom.batch_no dropValue " +
				" from " + 
		           "odata_outstock_m oom , odata_outstock_d ood  " +
				   " where 1=1 " +
				   " and oom.warehouse_No=ood.warehouse_No and oom.enterprise_no=ood.enterprise_no " +
		          // " and oom.task_type='0' and oom.operate_type='c' and oom.outstock_type='0' "+
				 //  " and oom.batch_no='"+strBatch+"' " +
				 " and oom.outstock_no =ood.outstock_no "+
				   "and ood.wave_no='"+strWave+"' "+
				  " and oom.enterprise_no='"+enterpriseNo+"' " +
					"and oom.warehouse_no='"+strWarehouseNo+"' "
				   ;  
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
		
	}
	//流水标签回单保存方法
		@Override
		public MsgRes saveLabel(String enterpriseNo,
				String strWarehouseNo,String strOutstockName,
				 String strOutstockNo)throws Exception
		{
			List  inList=new ArrayList();
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			outList.add("S");
				
			inList.add(enterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(strOutstockNo);//下架单号
			inList.add(strOutstockName);//回单人--系统登录人
			inList.add(strOutstockName);//下架人
			inList.add(strOutstockName);//上架人

			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_odata_lich.p_Save_all_outstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}

			return new MsgRes(true,"回单成功！",null);
		}
		
		//流水标签零回单保存方法
		@Override
		public MsgRes saveLabelZero(String enterpriseNo, String strWarehouseNo,
				String workSpaceNo, String strOutstockName, String strOutstockNo)
				throws Exception {
			
			List  inList=new ArrayList();
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			outList.add("S");
				
			inList.add(enterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(strOutstockNo);//下架单号
			inList.add(workSpaceNo);//
			inList.add(strOutstockName);//下架人

			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_odata_lich.P_SaveLabelCancelOutStock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}

			return new MsgRes(true,"回单成功！",null);
		}
}
