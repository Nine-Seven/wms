package com.sealinkin.ridata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;


import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.ridata.RIGetArrangeInfoModel;
import com.sealinkin.ridata.model.Ridata_InstockDModel;
import com.sealinkin.ridata.model.Ridata_InstockMModel;
import com.sealinkin.ridata.service.IRidata_InstockService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_InstockImpl implements IRidata_InstockService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 返配上架回单M
	 */
	@Override
	public ExtListDataBo<Ridata_InstockMModel> getRidata_InstockMList(
			String enterpriseNo,String warehouseNo,String workerOwner,
			String queryStr, PageBo pageBo) {
		String sql="select distinct  a.instock_no,a.quality_flag," +
				"f_get_fieldtext('N','STATUS',a.status) statusText, " +
				"a.rgst_name,a.rgst_date,a.updt_name,a.updt_date,d.wave_no  " +
				"from ridata_instock_m a,ridata_instock_d d " +
				" where a.status<>'13'  " +
				"and a.enterprise_no=d.enterprise_no " +
				"and a.warehouse_no=d.warehouse_no " +
				"and a.instock_no=d.instock_no " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
		}else{
			sql=sql+" and 1=2";
		}
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		
		sql+="order by d.wave_no,a.instock_no desc";
		List<Ridata_InstockMModel> list=genDao.getListByNativeSql(sql,Ridata_InstockMModel.class, pageBo.getStart(), 10000);
		ExtListDataBo<Ridata_InstockMModel> extListBo=new ExtListDataBo<Ridata_InstockMModel>(list,0);
		return extListBo;
	}
	
	/**
	 * 返配上架回单D
	 */
	@Override
	public ExtListDataBo<Ridata_InstockDModel> getRidata_InstockDList(
			String enterpriseNo,String warehouseNo,String wheresql, PageBo pageBo) {
		String sql="select a.dest_cell_no,a.article_no,b.article_name,b.barcode,a.packing_qty," +
				"trunc(sum(a.article_qty)/a.packing_qty) as planBox,"+
				"trunc(mod(sum(a.article_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
				"(sum(a.article_qty) - trunc(sum(a.article_qty)/a.packing_qty) * a.packing_qty - trunc(mod(sum(a.article_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis,"+
				"b.qmin_operate_packing,"+
				
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
				
				//"nvl(e.spec, '1*' || a.packing_qty || b.unit) spec,"+
				//"nvl(e.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
				"a.real_cell_no," +
				"sum(a.article_qty)/a.packing_qty as articleQty," +
				//"sum(a.article_qty)/a.packing_qty as realBox," +
				"trunc(sum(a.article_qty)/a.packing_qty) as realBox,"+
				"trunc(mod(sum(a.article_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) as realQmin,"+
				"(sum(a.article_qty) - trunc(sum(a.article_qty)/a.packing_qty) * a.packing_qty - trunc(mod(sum(a.article_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as realDis,"+
				
				"a.label_no,a.warehouse_no,a.instock_no," +
				"sum(a.real_qty) as realQty,d.produce_date,d.lot_no, " +
				"f_get_fieldtext('N','QUALITY',d.quality)as qualityText "+
				"from ridata_instock_d a,bdef_defarticle b," +
				"stock_article_info d,bdef_article_packing e " +
				"where a.article_no=b.article_no  " +
				"and d.article_id=a.article_id and a.article_no=d.article_no " +
				"and a.packing_qty=e.packing_qty(+) and a.article_no=e.article_no(+) " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=d.enterprise_no and a.enterprise_no=e.enterprise_no(+) " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.instock_no='"+wheresql+"' and a.warehouse_no='"+warehouseNo+"' " +
				"group by a.enterprise_no,a.owner_no,a.dest_cell_no,a.article_no,b.article_name,b.barcode,a.packing_qty," +
				
//				"trunc(a.article_qty/a.packing_qty),"+
//				"trunc(mod(a.article_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING),"+
//				"mod(a.article_qty,b.QMIN_OPERATE_PACKING),"+
				"b.spec,"+
				"b.unit_packing,"+
				"b.qmin_operate_packing,"+
				
				"a.real_cell_no,d.produce_date,a.label_no," +
				"a.warehouse_no,a.instock_no,d.lot_no,d.quality"+
				" order by a.instock_no,a.dest_cell_no,d.quality,a.article_no,d.lot_no,d.produce_date";
		List<Ridata_InstockDModel> list=genDao.getListByNativeSql(sql,Ridata_InstockDModel.class, pageBo.getStart(), 1000);
		ExtListDataBo<Ridata_InstockDModel> extListBo=new ExtListDataBo<Ridata_InstockDModel>(list,0);
		return extListBo;
	}
	
	@Override
	public boolean save(String enterpriseNo,String workerNo,String jsonDetail1) throws Exception {
		List<Ridata_InstockDModel> instockD =  JSON.parseArray(jsonDetail1, Ridata_InstockDModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		for(int i=0;i<instockD.size();i++){
			List inList=new ArrayList();
			inList.add(enterpriseNo);//strEnterpriseNo
			inList.add(instockD.get(0).getWarehouseNo());//strWareHouseNo
			inList.add(instockD.get(i).getInstockNo());//strInstockNo
			inList.add(instockD.get(i).getDestCellNo());//strDestCellNo
			inList.add(instockD.get(i).getRealCellNo());//strInstockCellNo
			inList.add(instockD.get(i).getLabelNo());//strLabelNo
			inList.add(instockD.get(i).getArticleNo());//strArticleNo
			inList.add(instockD.get(i).getProduceDate());//dtProduceDate
			inList.add(instockD.get(i).getPackingQty());//nPackingQty
			inList.add(instockD.get(i).getArticleQty());//nRealQty
			inList.add(instockD.get(0).getUserId());//strUserId
			inList.add(workerNo);//strPaperUserId
			inList.add("1");//strTools
			resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_SaveInstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return true;
	}
	
	/**
	 * 储位下拉
	 */
	@Override
	public List<ComboxBo> queryCell(String enterpriseNo,String strWarehouseNo,String strWheresql) throws Exception {
		String strSql="select a.cell_no value,a.cell_no text,a.cell_no dropValue " +
				"from Cdef_Defcell a ,cdef_defarea b " +
				"where a.warehouse_no=b.warehouse_no and a.ware_no=b.ware_no "+
                 " and a.area_no=b.area_no and b.area_usetype in('1','3','5','6') "+
                 " and b.area_attribute='0' and a.cell_status<>1 and a.check_status = '0' " +
				 " and a.warehouse_No='"+strWarehouseNo+"' " +
				 " and a.enterprise_no=b.enterprise_no " +
				 " and a.enterprise_no='"+enterpriseNo+"' ";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+="and a.cell_no like '%"+strWheresql+"%'";
		}
		List<ComboxBo> list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	}
	
	//波次下拉
	@Override
	public List<ComboxBo> queryWaveNo(String enterpriseNo,
			String strWarehouseNo, String strWheresql) throws Exception {
		String strSql="select distinct d.wave_no value,d.wave_no text,d.wave_no dropValue " +
				"from ridata_instock_d d " +
				"where d.enterprise_no='"+enterpriseNo+"' " +
				"and d.warehouse_no='"+strWarehouseNo+"' " +
				"and d.status<>'13'";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+="and d.wave_no like '%"+strWheresql+"%'";
		}
		List<ComboxBo> list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	}
	
	
	@Override
	public MsgRes tscCheckLabelAndGetInfo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		//rial
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strSql=" select distinct rid.article_no,rid.label_no,rid.packing_qty, bda.barcode,rid.owner_no,cdc.ware_no,cdc.area_no," +
			      "   bda.article_name,rid.dest_cell_no as cellNo," +
			      "nvl(bap.packing_unit, decode(rid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
			      "   sum(rid.article_qty) article_qty,cdc.stock_no,cdc.pick_order," +
			      "   case when rid.packing_qty=1 then 0 " +
			      "   else (sum(rid.article_qty)/rid.packing_qty) end box, " +
			      "   case when rid.packing_qty=1 then (sum(rid.article_qty)/rid.packing_qty) " +
			      "   else mod(sum(rid.article_qty),rid.packing_qty) end pecs, " +
			      "   case when rid.packing_qty=1 then 0 " +
			      "   else (select nvl(sum(a.qty),0) from stock_label_arrange_log a " +
			      "  where rid.enterprise_no = a.enterprise_no " +
			      "  and rid.warehouse_no = a.warehouse_no " +
			      "  and rid.label_no = a.label_no " +
			      "  and rid.article_no = a.article_no " +
			      "  and rid.packing_qty = a.packing_qty)/rid.packing_qty end comfigBox, " +
			      "   case when rid.packing_qty=1 then" +
			      " (select nvl(sum(a.qty),0) from stock_label_arrange_log a " +
			      "  where rid.enterprise_no = a.enterprise_no " +
			      "  and rid.warehouse_no = a.warehouse_no " +
			      "  and rid.label_no = a.label_no " +
			      "  and rid.article_no = a.article_no " +
			      "  and rid.packing_qty = a.packing_qty)/rid.packing_qty " +
			      "   else mod((select nvl(sum(a.qty),0) from stock_label_arrange_log a " +
			      "  where rid.enterprise_no = a.enterprise_no " +
			      "  and rid.warehouse_no = a.warehouse_no " +
			      "  and rid.label_no = a.label_no " +
			      "  and rid.article_no = a.article_no " +
			      "  and rid.packing_qty = a.packing_qty),rid.packing_qty) end comfigPecs," +
			      "   nvl(slal.row_id,-1) as rowText" +
			      " from stock_content sc " +
			      " inner join idata_instock_d rid " +
			      "    on rid.enterprise_no = sc.enterprise_no " +
			      "   and rid.warehouse_no = sc.warehouse_no " +
			      "   and rid.label_no = sc.label_no " +
			      "   and rid.owner_no = sc.owner_no " +
			      "   and rid.cell_id=sc.cell_id " +
			      "   and rid.cell_no=sc.cell_no " +
	              "   and rid.article_no=sc.article_no " +
	              "   and rid.article_id=sc.article_id " +
	              "   inner join cdef_defcell cdc " +
	              "   on rid.enterprise_no=cdc.enterprise_no " + 
	              "   and rid.warehouse_no=cdc.warehouse_no " +
	              "   and rid.real_cell_no=cdc.cell_no " +
			      " inner join bdef_defarticle  bda " +
			      "    on bda.enterprise_no=rid.enterprise_no " +
			      "   and bda.article_no=rid.article_no " +
			      "   and bda.owner_no=rid.owner_no " +			
			      "  left join bdef_article_packing bap " +
			      "    on rid.enterprise_no = bap.enterprise_no " +
			      "   and rid.article_no = bap.article_no " +
			      "   and rid.packing_qty =bap.packing_qty " +
			      "  left join stock_label_arrange_log slal " +
			      "    on rid.enterprise_no=slal.enterprise_no " +
			      "   and rid.warehouse_no=slal.warehouse_no " +
			      "   and rid.label_no=slal.label_no " +
			      "   and rid.article_no=slal.article_no " +
			      "   and rid.packing_qty=slal.packing_qty " +
			      "   and slal.ARRANGE_TYPE='0' " + //hb 20160509
			      " where sc.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
			      "   and sc.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
			      "   and sc.label_no='"+reqMod.getReqObj()+"' " +
	      		  " group by rid.enterprise_no,rid.warehouse_no,rid.article_no,rid.label_no,rid.packing_qty,bda.barcode, rid.owner_no," +
	      		  " cdc.ware_no,cdc.area_no,bda.article_name,rid.dest_cell_no, " +
	      		  " nvl(bap.packing_unit, decode(rid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit))," +
	      		  "cdc.stock_no,cdc.pick_order, nvl(slal.row_id, -1) " +				      
			      " order by cdc.ware_no,cdc.area_no desc,cdc.stock_no desc,cdc.pick_order desc, rid.dest_cell_no desc,rid.article_no ";
		List<RIGetArrangeInfoModel> list = genDao.getListByNativeSql(strSql,RIGetArrangeInfoModel.class);
		
		if(list.size()==0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("标签不存在");
		}else{
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONArray.fromObject(list));
		}		
		return msgRes;
	}

	/***
	 * 返配上架整理确认（天天惠） 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscArrangeComfire(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		RIGetArrangeInfoModel reqMod=JSON.parseObject(strRecvData, RIGetArrangeInfoModel.class);
		
		if(reqMod.getRowText().equals("-1")){
			String sql=" select nvl(max(a.row_id),0)+1 " +
					   "   from stock_label_arrange_log a" +
					   "  where a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
					   "    and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					   "    and a.label_no='"+reqMod.getLabelNo()+"'";
					 //  "    and a.article_no='"+reqMod.getArticleNo()+"' ";
		    List list = genDao.getListByNativeSql(sql);
		    
		    String rowid = "";
		    if(list.size()==0){
		    	rowid="1";
		    }else{
		      rowid=list.get(0).toString();
		    }
            
		    //判断当前商品是否已经插入 如果已经插入 则直接返回成功 huangb20160518
		    sql = " select * from stock_label_arrange_log a " + 
		          "  where a.enterprise_no = '" + reqMod.getEnterpriseNo() + "' " +
		          "    and a.warehouse_no = '" + reqMod.getWarehouseNo() + "' " + 
		          "    and a.label_no = '" + reqMod.getLabelNo() + "' " + 
		          "    and a.article_no = '" + reqMod.getArticleNo() + "' " +
		          "    and a.packing_qty = '"+reqMod.getPackingQty()+"' " + 
		          "    and a.arrange_type='0' ";
		    List list1 = genDao.getListByNativeSql(sql);
		    if(list1.size() > 0){
		    	msgRes.setIsSucc(true);
				return msgRes;
		    } 
		    
			//写绩效表
			sql=" insert into stock_label_arrange_log( " +
				"   enterprise_no,warehouse_no,owner_no,source_no,s_label_no,label_no,row_id," +
				"   article_no,packing_qty,qty,produce_date,expire_date,quality,lot_no,s_cell_no," +
				"   d_cell_no,printer_group_no,dock_no,status,rgst_date,rgst_name,arrange_type) " +
				" select distinct rid.enterprise_no,rid.warehouse_no,rid.owner_no,'N','N',rid.label_no,"+rowid+"," +
				"   rid.article_no,rid.packing_qty,"+reqMod.getQty()+",sai.produce_date,sai.expire_date,sai.quality," +
				"   sai.lot_no,'N','N','N','N',rid.status,sysdate,'"+reqMod.getUserId()+"','0' " +
				"   from idata_instock_d rid "+
				"  inner join stock_article_info sai " +
				"     on sai.enterprise_no=rid.enterprise_no " +
				"    and sai.article_no=rid.article_no " +
				"    and sai.article_id=rid.article_id " +
				"  where rid.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
				"    and rid.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				"    and rid.article_no='"+reqMod.getArticleNo()+"' " +
				"    and rid.label_no='"+reqMod.getLabelNo()+"' " +
				"    and rid.packing_qty='"+reqMod.getPackingQty()+"' ";
			genDao.exceuteSql(sql);
			   		
		}else{			
			//更新绩效表
			String updateSql=" update stock_label_arrange_log a " +
					  "    set a.qty="+reqMod.getQty()+"," +
					  "        a.updt_date=sysdate," +
					  "        a.updt_name='"+reqMod.getUserId()+"' " +
					  "  where a.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
					  "    and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					  "    and a.label_no='"+reqMod.getLabelNo()+"' " +
					  "    and a.row_id='"+reqMod.getRowText()+"' " +
					  "    and a.arrange_type='0' ";
			genDao.updateBySql(updateSql);
		}
		
		msgRes.setIsSucc(true);
		return msgRes;
	}
	
	//取消上架
	@Override
	public MsgRes tscCloseInstock(String enterpriseNo, String warehouseNo,
			String workerOwner,String wheresql) throws Exception {
			List resultList=new ArrayList();   
			List outList=new ArrayList();
			List inList=new ArrayList();
			
			outList.add("S");
			inList.add(enterpriseNo);//strEnterpriseNo
			inList.add(warehouseNo);
			inList.add(wheresql);
			inList.add(workerOwner);
			inList.add(workerOwner);
			inList.add("1");//strTools
			
			resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_InstockCancel");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			
			return new MsgRes(true,"取消成功!","");
	}
	@Override
	public MsgRes saveInstock(String enterpriseNo, String warehouseNo,
			String instockNo, String workerNo, String cellNo) throws Exception {
		List resultList=new ArrayList();   
		List outList=new ArrayList();
		List inList=new ArrayList();
		
		outList.add("S");
		inList.add(enterpriseNo);//strEnterpriseNo
		inList.add(warehouseNo);
		inList.add(instockNo);
		inList.add(workerNo);
		inList.add(workerNo);
		inList.add(cellNo);
		inList.add("1");//strTools
		System.out.println(inList);
		resultList=genDao.execProc(inList,outList,"pklg_ridata.p_locatecellsaveinstockall");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,"回单成功!","");
	}
	
}
