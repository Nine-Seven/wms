package com.sealinkin.mdata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.constant.CLabelStatus;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.mdata.service.IOdata_OutstockDService;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.idata.IdataDockIDModel;
import com.sealinkin.protocolExchange.mdata.HMGetDCellNoRequestModel;
import com.sealinkin.protocolExchange.mdata.HMMoveBarcodeAnswerModel;
import com.sealinkin.protocolExchange.mdata.HMMoveBarcodeRequestModel;
import com.sealinkin.protocolExchange.mdata.HMMoveLabelGetLabelInfo;
import com.sealinkin.protocolExchange.mdata.HMMoveLabelNoAnswerModel;
import com.sealinkin.protocolExchange.mdata.HMSaveMoveCellRequestModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes","unused"})
public class Odata_OutstockDImpl implements IOdata_OutstockDService{
	private static final Logger logger = Logger.getLogger(Odata_OutstockDImpl.class);
		
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}	

	/*
	 * 移库回单明细信息
	 * zhouhuan
	 */
	@Override
	public ExtListDataBo<Odata_OutstockDModel> getOdata_OutstockD(
			String enterpriseNo,
			String warehouseNo,
			String ownerNo,
			String flag,String str,Integer start, Integer pagesize) {
		String sql=""; 	    		
        sql = "select a.*,bd.QMIN_OPERATE_PACKING,   "+
        	"trunc(a.article_qty / a.packing_qty) as planBox,"+
            "trunc(mod(a.article_qty, a.packing_qty) / bd.QMIN_OPERATE_PACKING) as planQmin,"+
            "(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/bd.QMIN_OPERATE_PACKING) * bd.QMIN_OPERATE_PACKING) as planDis,"+
            
			"trunc(a.article_qty / a.packing_qty) as realBox,"+
			"trunc(mod(a.article_qty, a.packing_qty) / bd.QMIN_OPERATE_PACKING) as realQmin,"+
			"(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/bd.QMIN_OPERATE_PACKING) * bd.QMIN_OPERATE_PACKING) as realDis,"+
        		
			"bd.article_name, bd.owner_article_no as ownerArticleNo," +
			//"nvl(bab.packing_unit, decode(a.packing_qty,bd.qmin_operate_packing,bd.qmin_operate_packing_unit,bd.unit)) packing_unit,"+
            //"nvl(bab.spec, '1*' || a.packing_qty || bd.unit) spec " +
            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bd.qmin_operate_packing) packingUnitQmin," +
            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bd.unit_packing) Unit," +
            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bd.qmin_operate_packing) packingSpecQmin," +
            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bd.unit_packing) spec " +
            "from (  "+
			"select  "+
			"sai.enterprise_no,sai.article_no,  "+
			"sai.barcode,  "+
			"ood.warehouse_no,  "+
			"ood.owner_no,  "+
			"ood.packing_qty,  "+
			"ood.outstock_no,  "+
			"sai.produce_date,  "+
			"ood.status,  "+
			"sai.expire_date,  "+
			"sai.quality,  " +
			" f_get_fieldtext('N', 'quality', sai.QUALITY) textQuality,   " +
			" sai.LOT_NO," +
			" sai.RSV_BATCH1," +
			" sai.RSV_BATCH2," +
			" sai.RSV_BATCH3," +
			" sai.RSV_BATCH4," +
			" sai.RSV_BATCH5," +
			" sai.RSV_BATCH6," +
			" sai.RSV_BATCH7," +
			" sai.RSV_BATCH8,"+
			"ood.s_cell_no,  "+
			"ood.d_cell_no, " +
			//"ood.divide_id, "+
			"sum(ood.article_qty)article_qty,  "+
			
			"sum(ood.article_qty)realQty,  "+
			
			//"(sum(ood.article_qty)/ood.packing_qty) planBox,  "+
			//"(sum(ood.article_qty)/ood.packing_qty) realBox,  "+
			" m.label_no "+
			"from  "+
			"odata_outstock_d ood " +
				"left join stock_label_m m " +
					"on  M.SOURCE_NO = ood.OUTSTOCK_NO  " +
					"AND ood.warehouse_no = M.warehouse_no  " +
					"and ood.enterprise_no=m.enterprise_no " +
				"left join stock_label_d d    " +
					"on  ood.OWNER_NO = D.OWNER_NO  " +
					"AND ood.DIVIDE_ID = D.DIVIDE_ID " +
					"and ood.enterprise_no=d.enterprise_no ,  "+
			"stock_article_info sai    "+
			"where  "+
			"ood.article_no=sai.article_no     "+
			"and ood.article_id=sai.article_id     " +
			"and ood.enterprise_no=sai.enterprise_no  " +
			"and ood.enterprise_no='"+enterpriseNo+"' "+
			"and ood.warehouse_no='"+warehouseNo+"'   "+
			"and ood.owner_no in("+ownerNo+")  "+
			"and ood.status=10  " +
			"%s1 "+
			"group by  "+
			"sai.enterprise_no,sai.produce_date,  "+
			"sai.expire_date,  "+
			"sai.quality,  "+
			//"sai.IMPORT_BATCH_NO," +
			" sai.LOT_NO," +
			" sai.RSV_BATCH1," +
			" sai.RSV_BATCH2," +
			" sai.RSV_BATCH3," +
			" sai.RSV_BATCH4," +
			" sai.RSV_BATCH5," +
			" sai.RSV_BATCH6," +
			" sai.RSV_BATCH7," +
			" sai.RSV_BATCH8,"+
			"ood.s_cell_no,  "+
			"ood.d_cell_no,  "+
			"ood.outstock_no,  "+
			"ood.warehouse_no,  "+
			"ood.owner_no,  "+
			"ood.status,  "+
			"sai.article_no,  "+
			"sai.barcode, " +
			//"ood.divide_id, "+
			"ood.packing_qty," +
			"m.label_no  "+
			") a  "+
			" inner join " +
			" bdef_defarticle bd " +
			" on a.article_no=bd.article_no " +
			" and a.enterprise_no=bd.enterprise_no " +
			" left join  bdef_article_packing bab " +
			" on a.article_no=bab.article_no   " +
			" and a.packing_qty=bab.packing_qty  " +
			" and a.enterprise_no=bab.enterprise_no " +
			" order by a.label_no,a.s_cell_no,a.article_no " ;

			if(str!=null && !str.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql.replace("%s1", ws);
			}else{
				sql=sql.replace("%s1", "");
			}
	    List<Odata_OutstockDModel> list=genDao.getListByNativeSql(sql, Odata_OutstockDModel.class, start,10000);
	    Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
	    ExtListDataBo<Odata_OutstockDModel> extListBo = new ExtListDataBo<Odata_OutstockDModel>(list, count);
	    return extListBo;
	}

	/*@Override
	public ExtListDataBo<Odata_OutstockDModel> getOdata_OutstockDDetail(
			String enterpriseNo,
			String warehouseNo,
			String ownerNo,
			String str, Integer start, Integer limit) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("outStockNo", str+"%");
    	System.out.println(str);
    	   
    	   String sql=" select"
    		   +" a.WAREHOUSE_NO,a.OWNER_NO,a.OUTSTOCK_NO,a.DIVIDE_ID," +
    		   "a.OPERATE_DATE,a.BATCH_NO,a.EXP_TYPE,a.EXP_NO," +
    		   "a.WAVE_NO,a.CUST_NO,a.SUB_CUST_NO,a.ARTICLE_NO," +
    		   "a.ARTICLE_ID,a.PACKING_QTY,a.S_CELL_NO,a.S_CELL_ID," +
    		   "a.S_CONTAINER_NO,a.D_CELL_NO,a.D_CELL_ID,a.PICK_CONTAINER_NO," +
    		   "a.CUST_CONTAINER_NO,a.ARTICLE_QTY,a.REAL_QTY,a.DELIVER_AREA," +
    		   "a.STATUS,a.LINE_NO,a.TRUNCK_CELL_NO,a.A_SORTER_CHUTE_NO," +
    		   "a.CHECK_CHUTE_NO,a.DELIVER_OBJ,a.ADVANCE_CELL_NO," +
    		   "a.ASSIGN_NAME,a.OUTSTOCK_NAME,a.INSTOCK_NAME," +
    		   "a.OUTSTOCK_DATE,a.INSTOCK_DATE,a.DPS_CELL_NO," +
    		   "a.DEVICE_NO,a.PRIORITY,a.EXP_DATE,a.PICK_DEVICE," +
    		   "a.ADVANCE_PICK_NAME,a.ADVANCE_PICK_DATE," +
    		   "a.LABEL_NO,a.STOCK_TYPE,a.EMPTY_FLAG,a.UNBOX_FLAG,"
    		   +"trunc(a.ARTICLE_QTY/a.packing_qty) planBox," 
    		   +"mod(a.ARTICLE_QTY,a.packing_qty) planDis," 
    		   +"a.ARTICLE_QTY  realQty," 
    		   +"trunc(a.ARTICLE_QTY/a.packing_qty) realBox," 
    		   +"mod(a.ARTICLE_QTY,a.packing_qty) realDis,"
    		   +" b.BARCODE,"
    		   +" b.ARTICLE_NAME," 
    		   +" b.spec,"
    		   +" c.produce_date,"
    		   +" c.expire_date , "
    		   +" c.quality  "
    		   +" from"
    		   +" odata_outstock_d a,"
    		   +" bdef_defarticle b,"
    		   +" stock_article_info c "
    		   +" where"
    		   +" a.owner_no=b.OWNER_NO and"
    		   +" a.article_no=b.ARTICLE_NO  " +
    		   "  and a.enterprise_no=b.enterprise_no " +
    		   "  and a.enterprise_no=c.enterprise_no " +
    		   "  and a.enterprise_no='"+enterpriseNo+"'"
    		   +" and a.article_no=c.article_no"
    		   +" and a.article_id=c.article_id "
               +" AND A.OUTSTOCK_NO="+str;
    	   List<Odata_OutstockDModel> list=genDao.getListByNativeSql(sql, Odata_OutstockDModel.class, start,limit);
    	   ExtListDataBo<Odata_OutstockDModel> extListBo = new ExtListDataBo<Odata_OutstockDModel>(list, 0);
    	   logger.info("leval queryOdata_OutStock_DDetailModel");
    	   return extListBo;
	}
*/
	/**
	 * 移库回单》》头档信息
	 * lich
	 */
	@Override
	public ExtListDataBo<Odata_OutstockMModel> getOdata_OutstockM(
			String enterpriseNo,String warehouseNo,
			String ownerNo,
			String flag, String str, Integer start, Integer pagesize) {
		String sql="";
		String totsql="";
		sql="select distinct b.*,f_get_fieldtext('N','STATUS',b.status)statusText," +
				"f_get_fieldtext('N','OUTSTOCK_TYPE',b.outstock_type)outstock_typeText " +
				//"a.wave_no " +
				"from odata_outstock_d a,odata_outstock_m b " +
				"where  b.outstock_type in(1,3,4) " +
				"and a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.outstock_no=b.outstock_no " +
				"and a.warehouse_no='"+warehouseNo+"'   " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.owner_no in("+ownerNo+")  "+
				"and b.status=10 " ;		
        totsql = "select count(1) " +
				"from odata_outstock_d a,odata_outstock_m b " +
				"where  b.outstock_type in(1,3,4) " +
				"and a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.outstock_no=b.outstock_no " +
				"and a.warehouse_no='"+warehouseNo+"'   "+
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.owner_no in("+ownerNo+")  "+
				"and b.status=10 " ;	
				
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		
		List<Odata_OutstockMModel> list = genDao.getListByNativeSql(sql,Odata_OutstockMModel.class,start,10000);
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Odata_OutstockMModel> extListBo= new ExtListDataBo<Odata_OutstockMModel>(list, count);
        return extListBo;
	}
	
	/*
	 * 移库回单-->回单
	 * zhouhuan
	 */
	@Override
	public MsgRes save(String workerNo,String str,String strTaskType) throws Exception {
		List<Odata_OutstockDModel> ood=JSON.parseArray(str,Odata_OutstockDModel.class);
		for (Odata_OutstockDModel i : ood) {
			if(!strTaskType.equals("")&&strTaskType.equals("1"))
			{
				tscFormSaveOdataOutstock(
						i.getEnterpriseNo(),
						i.getWarehouseNo(),
						i.getOwnerNo(),
						i.getOutstockNo(),
						i.getArticleNo(),
						i.getBarcode(),
						i.getProduceDate(),
						i.getExpireDate(),
						i.getQuality(),
						i.getLotNo(),
						i.getRsvBatch1(),
						i.getRsvBatch2(),
						i.getRsvBatch3(),
						i.getRsvBatch4(),
						i.getRsvBatch5(),
						i.getRsvBatch6(),
						i.getRsvBatch7(),
						i.getRsvBatch8(),
						i.getSCellNo(),
						i.getDCellNo(),
						i.getArticleQty(),
						i.getRealQty(),
						i.getUserId(),
						i.getInstockName(),
						i.getOutstockName());
			}else if(!strTaskType.equals("")&&strTaskType.equals("2"))
			{
				tscPTaskLabelSaveOdataOutstock(
						i.getEnterpriseNo(),
						i.getWarehouseNo()   ,
						i.getOutstockNo()   ,
						i.getLabelNo() ,
						i.getArticleNo()   ,
						i.getPackingQty() ,
						i.getSCellNo()   ,
						i.getArticleQty(),
						i.getRealQty()   ,
						i.getQuality()   ,
						i.getProduceDate()   ,
						i.getExpireDate()   ,
						i.getLotNo(),
						i.getRsvBatch1(),
						i.getRsvBatch2(),
						i.getRsvBatch3(),
						i.getRsvBatch4(),
						i.getRsvBatch5(),
						i.getRsvBatch6(),
						i.getRsvBatch7(),
						i.getRsvBatch8(),
						i.getDockNo() ,
						i.getUserId()   ,
						i.getOutstockName(), 
						i.getInstockName() 
						);
			}else{
				return new MsgRes(true,"发单方式错误！",null);
			}
		}
		return new MsgRes(true,"回单成功！",null);
	}
	
	//表单回单过程调用
	@Override
	public MsgRes tscFormSaveOdataOutstock(
			String strEnterpriseNo,
			String strWarehoseNo  ,
			String strOwnerNo,
			String strOutstockNo  ,
			String strArticleNo  ,
			String strBarcode  ,
			Date dtProduceDate  ,
			Date dtExpireDate  ,
			String strQuality  ,
			String strLotNo  ,
			String strRsvBatch1  ,
			String strRsvBatch2  ,
			String strRsvBatch3  ,
			String strRsvBatch4  ,
			String strRsvBatch5  ,
			String strRsvBatch6  ,
			String strRsvBatch7  ,
			String strRsvBatch8  ,
			String strSCellNo  ,
			String strDCellNo  ,
			Double articleQTY, 
			Double nRealQTY  ,
			String strUserID  ,
			String strInstockID,
			String strOutstockID 
			) throws Exception {
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehoseNo);
		inList.add(strOwnerNo);
		inList.add(strOutstockNo);
		inList.add(strArticleNo);	
		inList.add(strBarcode);	
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRsvBatch1)?"N":strRsvBatch1);
		inList.add(StringUtil.isStrEmpty(strRsvBatch2)?"N":strRsvBatch2);
		inList.add(StringUtil.isStrEmpty(strRsvBatch3)?"N":strRsvBatch3);
		inList.add(StringUtil.isStrEmpty(strRsvBatch4)?"N":strRsvBatch4);
		inList.add(StringUtil.isStrEmpty(strRsvBatch5)?"N":strRsvBatch5);
		inList.add(StringUtil.isStrEmpty(strRsvBatch6)?"N":strRsvBatch6);
		inList.add(StringUtil.isStrEmpty(strRsvBatch7)?"N":strRsvBatch7);
		inList.add(StringUtil.isStrEmpty(strRsvBatch8)?"N":strRsvBatch8);		
		inList.add(strSCellNo);
		inList.add(strDCellNo);
		inList.add(articleQTY);
		inList.add(nRealQTY);
		inList.add(strUserID);//回单人--系统登录人
		inList.add(strInstockID);
		inList.add(strOutstockID);//拣货人员strUserID
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_mdata.p_HmOutstock_Return");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		System.out.println(resultList.get(0).toString());
		return new MsgRes(true,"",resultList);
	}
	//移库、补货作业扫描标签
	@Override
	public MsgRes HMScanLabelNo(String strEnterpriseNo,String strWarehouseNo,
			String strOwnerNo, 
			String strLabelNo,
			String strStatus)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		//检验标签状态
		String strSql="select * from stock_label_m slm " +
				"where  slm.warehouse_no='"+strWarehouseNo+"' " +
						"and slm.enterprise_no='"+strEnterpriseNo+"' " +
						"and slm.label_no='"+strLabelNo+"'";
		String strOrder=" ";
		String strDisp=" ";//用于存储当前需要显示的dispcellno
		List<Stock_LabelMModel> listslm=genDao.getListByNativeSql(strSql, Stock_LabelMModel.class);
		if(listslm.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该标签号不存在！");
			return msgRes;
		}else
		{
			if(!listslm.get(0).getUseType().equals("3"))
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("该标签号不是移库标签！");
				return msgRes;
			}
			if(!listslm.get(0).getStatus().equals(CLabelStatus.MOVE_HAND_OUT) && 
					!listslm.get(0).getStatus().equals(CLabelStatus.OUTSTOCKING) )
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("该标签号状态不对！");
				return msgRes;
			}
			
			if(listslm.get(0).getStatus().equals(CLabelStatus.OUTSTOCKING))
			{
				strOrder="  and a.inStockCellNo=cdc.cell_no " +
			            //新增。需要同时在查出上下架对应的dispcellno
						"  inner join cdef_defcell cdc1 on cdc1.enterprise_no=a.enterprise_no and cdc1.warehouse_no=a.warehouse_no and a.outStockCellNo=cdc1.cell_no " +
						"  ORDER BY cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,A.OUTSTOCKCELLNO ";
				
				strDisp = " ,cdc.disp_cell_no inStockdispCellNo,cdc1.disp_cell_no outStockdispCellNo ";
			}else
			{
				strOrder="  and a.OutStockCellNo=cdc.cell_no " +
						//新增。需要同时在查出上下架对应的dispcellno
						"  inner join cdef_defcell cdc1 on cdc1.enterprise_no=a.enterprise_no and cdc1.warehouse_no=a.warehouse_no and a.inStockCellNo=cdc1.cell_no " +
						"  ORDER BY cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,A.inStockCellNo ";
				
				strDisp = " ,cdc.disp_cell_no OutStockdispCellNo,cdc1.disp_cell_no InStockdispCellNo ";
			}
		}
		//取拣货数据		
		strSql="select   "+
				"        a.*,   "+
				"        bd.article_name,   "+
				"        m.label_no,bd.unit_packing as unitPacking,bd.qmin_operate_packing as qminOperatePacking, "+
				//" nvl(bab.packing_unit, decode(a.packing_qty,bd.qmin_operate_packing,bd.qmin_operate_packing_unit,bd.unit)) packing_unit,"+
                //" nvl(bab.spec, '1*' || a.packing_qty || bd.unit) spec," +
                "        f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit, "+
                "        f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bd.qmin_operate_packing) packingUnitQmin, "+
                "        f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bd.unit_packing) Unit, "+
                "        f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec, "+
                "        f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bd.qmin_operate_packing) packingSpecQmin, "+
                "        f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bd.unit_packing) spec, "+
				"        bd.expiry_days,   "+
				"        bab.pal_base_qbox||'*'||bab.pal_height_qbox as qpalette,   "+
				"        bab.pal_base_qbox,   "+
				"        bab.pal_height_qbox,   "+
				"        bd.qmin_operate_packing,oom.operate_type,oom.outstock_type,  " +
				//"		 cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order  "+
				"		 cdc.ware_no,cdc.area_no,cdc.pick_order  "+ strDisp+ 
				"    from   "+
				"        ( select ood.enterprise_no,ood.warehouse_no,ood.owner_no," +
				"			 ood.outstock_no as stockNo,   "+
				"            sai.article_no,   "+
				"            sai.barcode,   "+
				"            ood.packing_qty,   "+
				"            to_char(sai.produce_date,   "+
				"            'yyyymmdd') as produce_date,   "+
				"            to_char(sai.expire_date,   "+
				"            'yyyymmdd') as expire_date,   "+
				"            sai.quality,   "+
				"            sai.LOT_NO,   "+
				"            sai.RSV_BATCH1,   "+
				"            sai.RSV_BATCH2,   "+
				"            sai.RSV_BATCH3,   "+
				"            sai.RSV_BATCH4,   "+
				"            sai.RSV_BATCH5,   "+
				"            sai.RSV_BATCH6,   "+
				"            sai.RSV_BATCH7,   "+
				"            sai.RSV_BATCH8,   "+
				"            ood.s_cell_no as OutStockCellNo,   "+
				"            ood.d_cell_no as inStockCellNo,   "+
				"            sum(ood.article_qty) as OutStockQty,   "+
				"            sum(ood.article_qty) as InStockQty           "+
				"        from   "+
				"            odata_outstock_d ood,   "+
				"            stock_article_info sai,stock_label_d sld   "+
				"        where ood.article_no=sai.article_no                   "+
				"            and ood.article_id=sai.article_id                    "+
				"            and ood.warehouse_no='"+strWarehouseNo+"'       "+
				"            and ood.warehouse_no=sld.warehouse_no   " +
				"            and ood.enterprise_no=sai.enterprise_no " +
				"            and ood.enterprise_no=sld.enterprise_no " +
				"            and ood.enterprise_no='"+strEnterpriseNo+"'"+
				"            and ood.owner_no=sld.owner_no   "+
				"            and ood.divide_id=sld.divide_id   "+
				"            and ood.article_no=sld.article_no   "+
				"            and ood.article_id=ood.article_id       "+
				/*"            and ood.outstock_no='HS00114072900001'   "+*/
				"            and sld.status='"+strStatus+"'     "+
				"        group by ood.enterprise_no,ood.warehouse_no,ood.owner_no,ood.outstock_no,   "+
				"            sai.produce_date,   "+
				"            sai.expire_date,   "+
				"            sai.quality,   "+
				"            sai.LOT_NO,   "+
				"            sai.RSV_BATCH1,   "+
				"            sai.RSV_BATCH2,   "+
				"            sai.RSV_BATCH3,   "+
				"            sai.RSV_BATCH4,   "+
				"            sai.RSV_BATCH5,   "+
				"            sai.RSV_BATCH6,   "+
				"            sai.RSV_BATCH7,   "+
				"            sai.RSV_BATCH8,   "+
				"            ood.s_cell_no,   "+
				"            ood.d_cell_no,   "+
				"            sai.article_no,   "+
				"            sai.barcode,   "+
				"            ood.packing_qty ) a   "+
				"    inner join odata_outstock_m oom on oom.warehouse_no=a.warehouse_no   "+
				"     and oom.outstock_no=a.stockNo   " +
				"     and a.enterprise_no=oom.enterprise_no "+
				"    inner join   "+
				"        bdef_defarticle bd                "+
				"            on a.article_no=bd.article_no        " +
				"            and a.enterprise_no=bd.enterprise_no"+
				"    left join   "+
				"        bdef_article_packing bab                "+
				"            on a.article_no=bab.article_no                  "+
				"            and a.packing_qty=bab.packing_qty" +
				"            and a.enterprise_no=bab.enterprise_no"+
				"    inner join   "+
				"        stock_label_m m               "+
				"            on  M.SOURCE_NO = a.stockNo                "+
				"            AND a.warehouse_no = M.warehouse_no " +
				"            and a.enterprise_no=m.enterprise_no           "+
				"            and m.label_no='"+strLabelNo+"' " +
				"  inner join cdef_defcell cdc " +
				"  on cdc.enterprise_no=a.enterprise_no " +
				"  and cdc.warehouse_no=a.warehouse_no "+strOrder;
		List<HMMoveLabelNoAnswerModel> list = genDao.getListByNativeSql(strSql, HMMoveLabelNoAnswerModel.class);
		
		
		if(list.size()>0)
		{
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONObject.toJSON(list));
		}else
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("没有获取到移库明细数据！");
		}

		return msgRes;
	}
	
	//任务标签回单过程调用
	    @Override
		public MsgRes tscPTaskLabelSaveOdataOutstock(
				String strEnterpriseNo,
				String strWarehoseNo  ,
				String strOutstockNo  ,
				String strLabelNo,
				String strArticleNo  ,
				Double nPackingQty,
				String strSCellNo  ,
				Double articleQTY,
				Double nRealQTY  ,
				String strQuality  ,
				Date dtProduceDate  ,
				Date dtExpireDate  ,
				String strLotNo  ,
				String strRsvBatch1  ,
				String strRsvBatch2  ,
				String strRsvBatch3  ,
				String strRsvBatch4  ,
				String strRsvBatch5  ,
				String strRsvBatch6  ,
				String strRsvBatch7  ,
				String strRsvBatch8  ,
				String strDockNo,
				String strUserID  ,
				String strOutstockID, 
				String strInstockID
				) throws Exception {
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			List  inList=new ArrayList();
			
			inList.add(strEnterpriseNo);
			inList.add(strWarehoseNo);
			inList.add(strOutstockNo);
			inList.add(strLabelNo);
			inList.add(strArticleNo);
			inList.add(nPackingQty);
			inList.add(strSCellNo);
			inList.add(articleQTY);
			inList.add(nRealQTY);			
			inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
			inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
			inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);			
			inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
			inList.add(StringUtil.isStrEmpty(strRsvBatch1)?"N":strRsvBatch1);
			inList.add(StringUtil.isStrEmpty(strRsvBatch2)?"N":strRsvBatch2);
			inList.add(StringUtil.isStrEmpty(strRsvBatch3)?"N":strRsvBatch3);
			inList.add(StringUtil.isStrEmpty(strRsvBatch4)?"N":strRsvBatch4);
			inList.add(StringUtil.isStrEmpty(strRsvBatch5)?"N":strRsvBatch5);
			inList.add(StringUtil.isStrEmpty(strRsvBatch6)?"N":strRsvBatch6);
			inList.add(StringUtil.isStrEmpty(strRsvBatch7)?"N":strRsvBatch7);
			inList.add(StringUtil.isStrEmpty(strRsvBatch8)?"N":strRsvBatch8);
			inList.add(strDockNo);
			inList.add(strUserID);//回单人--系统登录人
			inList.add(strOutstockID);//拣货人员strUserID
			inList.add(strInstockID);
			
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_mdata.P_TaskLabel_Outstock_Move");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			System.out.println(resultList.get(0).toString());
			return new MsgRes(true,"",resultList);
		}
		@Override
		public MsgRes tscRfOutstock(
				String strEnterpriseNo,
				String strWarehoseNo, 
				String strOutstockNo,
				String strArticleNo,
				Double nPackingQty, 
				Date dtProduceDate, 
				Date dtExpireDate,
				String strQuality, 
				String strLotNo, 
				String strRsvBatch1,
				String strRsvBatch2, 
				String strRsvBatch3, 
				String strRsvBatch4,
				String strRsvBatch5, 
				String strRsvBatch6, 
				String strRsvBatch7,
				String strRsvBatch8, 
				Double nQTY, 
				String strSCellNo,
				String strDCellNo, 
				String strLabelNo, 
				String strUserID)
				throws Exception {
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			List  inList=new ArrayList();
			
			inList.add(strEnterpriseNo);
			inList.add(strWarehoseNo);
			inList.add(strOutstockNo);			
			inList.add(strArticleNo);
			inList.add(nPackingQty);			
			inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
			inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
			inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);			
			inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
			inList.add(StringUtil.isStrEmpty(strRsvBatch1)?"N":strRsvBatch1);
			inList.add(StringUtil.isStrEmpty(strRsvBatch2)?"N":strRsvBatch2);
			inList.add(StringUtil.isStrEmpty(strRsvBatch3)?"N":strRsvBatch3);
			inList.add(StringUtil.isStrEmpty(strRsvBatch4)?"N":strRsvBatch4);
			inList.add(StringUtil.isStrEmpty(strRsvBatch5)?"N":strRsvBatch5);
			inList.add(StringUtil.isStrEmpty(strRsvBatch6)?"N":strRsvBatch6);
			inList.add(StringUtil.isStrEmpty(strRsvBatch7)?"N":strRsvBatch7);
			inList.add(StringUtil.isStrEmpty(strRsvBatch8)?"N":strRsvBatch8);			
			inList.add(nQTY);
			inList.add(strSCellNo);
			inList.add(strDCellNo);
			inList.add(strLabelNo);
			inList.add(strUserID);//回单人--系统登录人
			
			
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKOBJ_MDATA.P_mdata_RfOut");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			System.out.println(resultList.get(0).toString());
			return new MsgRes(true,"",resultList);
		}
		@Override
		public MsgRes HMOutCellNo(String strRecvData) throws Exception {
			MsgRes res=new MsgRes();
			CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			String strSql="select b.*,a.disp_cell_no dispCellNo " +
					" from stock_content c," +
						" cdef_defcell a," +
						" cdef_defarea b " +
					" where a.warehouse_no=b.warehouse_no " +
					    " and a.enterprise_no=b.enterprise_no  " +
						" and a.ware_no=b.ware_no " +
						" and a.area_no=b.area_no  " +
						" and a.enterprise_no=c.enterprise_no " +
						" and a.warehouse_no=c.warehouse_no " +
						" and a.cell_no=c.cell_no " +
						" and c.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
						" and c.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
						" and ((c.cell_no='"+reqMod.getReqObj()+"' and c.label_no='N')or " +
						" c.label_no='"+reqMod.getReqObj()+"')";
			List<Cdef_DefareaModel> list = genDao.getListByNativeSql(strSql,Cdef_DefareaModel.class);
			if(list.size()>0)
			{
				if("1".equals(list.get(0).getAreaAttribute()))
				{
					res.setIsSucc(false);
					res.setMsg("储位不能是暂存区储位，请重新扫描");
				}else
				{
					res.setObj(list.get(0).getDispCellNo().toString());
					res.setIsSucc(true);					
				}
			}else
			{
				res.setIsSucc(false);
				res.setMsg("储位（标签）不存在或者没有可移库库存，请重新扫描");
			}
			return res;
		}
		@Override
		public MsgRes HMBarcode(String strRecvData) throws Exception {
			MsgRes msg=new MsgRes();
			HMMoveBarcodeRequestModel reqMod=JSON.parseObject(strRecvData, HMMoveBarcodeRequestModel.class);
			//根据扫描的商品条码查找商品信息
			msg =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),
															  reqMod.getBarcode(),reqMod.getEnterpriseNo());
			if(!msg.getIsSucc())
			{
				return msg;
			}
			
			String strSql="select A.enterprise_no,a.warehouse_no,   "+
							"       a.cell_no as outStockCellNo,  "+
							"       a.owner_no,   "+
							"       a.article_no,   "+
							"       c.OWNER_ARTICLE_NO,   "+
							"       c.ARTICLE_NAME,   " +
							"       b.barcode,"+
							"       a.packing_qty,c.unit_packing as unitPacking,c.qmin_operate_packing as qminOperatePacking," +
							//"       nvl(d.packing_unit, decode(a.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit," +
							"       f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
							"       f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin,"+
							"       f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit,"+
							"       f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
							"       f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin,"+
							"       f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec,"+
						    "       c.unit,"+
						    "       nvl(d.pal_base_qbox,999)||'*'||nvl(d.pal_height_qbox,999) as qpalette,"+
						    "       nvl(d.pal_base_qbox,999) pal_base_qbox,"+
						    "       nvl(d.pal_height_qbox,999) pal_height_qbox,"+
							"       sum(a.qty) qty,   "+
							"       sum(a.qty - a.outstock_qty) canUseQty,   "+
							"       to_char(b.produce_date,'yyyy-mm-dd') as produce_date,"+
							"		to_char(b.expire_date,'yyyy-mm-dd') expire_date,"+
							"       b.quality,   "+
							"       b.lot_no,   " +
							//"       b.import_batch_no,"+
							"       b.rsv_batch1,   "+
							"       b.rsv_batch2,   "+
							"       b.rsv_batch3,   "+
							"       b.rsv_batch4,   "+
							"       b.rsv_batch5,   "+
							"       b.rsv_batch6,   "+
							"       b.rsv_batch7,   "+
							"       b.rsv_batch8   "+
							"  from    "+
							"    stock_content a,    "+
							"    stock_article_info b,    "+
							"    bdef_defarticle c," +
							"    bdef_article_packing d  "+
							" where    "+
							"   a.article_no = b.article_no" +
							"   and a.enterprise_no=b.enterprise_no " +
							"   and a.enterprise_no=c.enterprise_no " +
							"   and a.enterprise_no=d.enterprise_no(+) " +
							"   and a.enterprise_no='"+reqMod.getEnterpriseNo()+"'"+
							"   and a.article_id = b.article_id   "+
							"   and a.article_no = c.ARTICLE_NO   " +
							"	and a.article_no=d.article_no(+) " +
							"	and a.packing_qty=d.packing_qty(+) " +
							"   and a.warehouse_no ='" +reqMod.getWarehouseNo()+"' "+
							"   and ((a.cell_no='"+reqMod.getCellNo()+"' and a.label_no = 'N' ) or a.label_no='"+reqMod.getCellNo()+"')  "+
							"   and a.article_no in("+msg.getObj().toString()+")  " +
							"group by    "+
							"   A.enterprise_no,a.warehouse_no,   "+
							"       a.cell_no,   "+
							"       a.owner_no,   "+
							"       a.article_no,   "+
							"       c.OWNER_ARTICLE_NO,   "+
							"       c.ARTICLE_NAME,   " +
							"       b.barcode,"+
							"       a.packing_qty,   "+
							"       d.packing_unit,c.qmin_operate_packing,c.qmin_operate_packing_unit," +
							"       c.unit,c.unit_packing," +							
							"       d.pal_base_qbox||'*'||d.pal_height_qbox,   "+
							"       d.pal_base_qbox,   "+
							"       d.pal_height_qbox,   "+							
							"       to_char(b.produce_date,'yyyy-mm-dd'), "+
							"       to_char(b.expire_date,'yyyy-mm-dd'),   "+
							"       b.quality,   "+
							"       b.lot_no,   " +
							//"       b.import_batch_no,"+
							"       b.rsv_batch1,   "+
							"       b.rsv_batch2,   "+
							"       b.rsv_batch3,   "+
							"       b.rsv_batch4,   "+
							"       b.rsv_batch5,   "+
							"       b.rsv_batch6,   "+
							"       b.rsv_batch7,   "+
							"       b.rsv_batch8";
			List<HMMoveBarcodeAnswerModel> list = genDao.getListByNativeSql(strSql,HMMoveBarcodeAnswerModel.class);
			if(list.size()>0)
			{
				msg.setIsSucc(true);
				msg.setObj(JSONObject.toJSON(list));
			}else
			{
				msg.setIsSucc(false);
				msg.setMsg("不存在该商品库存信息，请核实！");
			}
			return msg;
		}
		@Override
		public MsgRes HMInCellNo(String strRecvData) throws Exception {			
			MsgRes res=new MsgRes();
			CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			//校验目的储位是否存在
			String strSql = "select 1  from cdef_defarea cda  join cdef_defcell cdc " +
					"    on cda.enterprise_no = cdc.enterprise_no and cda.warehouse_no = cdc.warehouse_no" +
					"   and cda.ware_no = cdc.ware_no and cda.area_no = cdc.area_no" +
					" where cda.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
					" and cda.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
					" and cdc.cell_no='"+reqMod.getReqObj()+"'" +
					" and cdc.CELL_STATUS <> '1' " +
					" and cdc.CHECK_STATUS <> '3'" +
					" and cda.area_attribute<>'1'";
			List list=genDao.getListByNativeSql(strSql);
			if(list.size() != 1 )
			{
				strSql = "select distinct 1 from stock_content c " +
						" where c.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
						" and c.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
						" and c.label_no='"+reqMod.getReqObj()+"'"+
						" and not exists (select 1 from stock_label_m a " +
						" where a.enterprise_no = c.enterprise_no " +
						" and a.warehouse_no = c.warehouse_no " +
						" and a.label_no = c.label_no)";
				List list1=genDao.getListByNativeSql(strSql);
				if(list1.size() == 0)
				{
					return new MsgRes(false,"目的储位(标签)不存在，或者目的储位（标签）不可用，请检查！","");
				}
			}

			return new MsgRes(true,"","");
		}
		@Override
		public MsgRes tscSaveMoveCell(String strEnterpriseNo,
				String strWarehouseNo,
				String strArticleNo, 
				String strSCellNo, 
				String strLabelNo,
				String strDCellNo, 
				Double nPackingQty, 
				Date dtProduceDate,
				Date dtExpireDate, 
				String strQuality, 
				String strLotNo,
				String strRsvBatch1, 
				String strRsvBatch2, 
				String strRsvBatch3,
				String strRsvBatch4, 
				String strRsvBatch5, 
				String strRsvBatch6,
				String strRsvBatch7, 
				String strRsvBatch8, 
				Double nRealQty,
				String strTerminalFlag, 
				String strSourceType, 
				String strUserID)
				throws Exception {
			//校验目的储位是否存在
			String strSql = "select 1  from cdef_defarea cda  join cdef_defcell cdc " +
					"    on cda.enterprise_no = cdc.enterprise_no and cda.warehouse_no = cdc.warehouse_no" +
					"   and cda.ware_no = cdc.ware_no and cda.area_no = cdc.area_no" +
					" where cda.enterprise_no='"+strEnterpriseNo+"'" +
					" and cda.warehouse_no='"+strWarehouseNo+"'" +
					" and cdc.cell_no='"+strDCellNo+"'" +
					" and cdc.CELL_STATUS <> '1' " +
					" and cdc.CHECK_STATUS <> '3'" +
					" and cda.area_attribute<>'1'";
			List list=genDao.getListByNativeSql(strSql);
			if(list.size() != 1 )
			{
				strSql = "select distinct 1 from stock_content c " +
						" where c.enterprise_no='"+strEnterpriseNo+"'" +
						" and c.warehouse_no='"+strWarehouseNo+"'" +
						" and c.label_no='"+strDCellNo+"'"+
						" and not exists (select 1 from stock_label_m a " +
						" where a.enterprise_no = c.enterprise_no " +
						" and a.warehouse_no = c.warehouse_no " +
						" and a.label_no = c.label_no)";
				List list1=genDao.getListByNativeSql(strSql);
				if(list1.size() == 0)
				{
					return new MsgRes(false,"目的储位(标签)不存在，或者目的储位（标签）不可用，请检查！","");
				}
			}
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			List  inList=new ArrayList();
			
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(strArticleNo);
			inList.add(strSCellNo);
			inList.add(strLabelNo);
			inList.add(strDCellNo);
			inList.add(nPackingQty);			
			inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
			inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
			inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
			inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
			inList.add(StringUtil.isStrEmpty(strRsvBatch1)?"N":strRsvBatch1);
			inList.add(StringUtil.isStrEmpty(strRsvBatch2)?"N":strRsvBatch2);
			inList.add(StringUtil.isStrEmpty(strRsvBatch3)?"N":strRsvBatch3);
			inList.add(StringUtil.isStrEmpty(strRsvBatch4)?"N":strRsvBatch4);
			inList.add(StringUtil.isStrEmpty(strRsvBatch5)?"N":strRsvBatch5);
			inList.add(StringUtil.isStrEmpty(strRsvBatch6)?"N":strRsvBatch6);
			inList.add(StringUtil.isStrEmpty(strRsvBatch7)?"N":strRsvBatch7);
			inList.add(StringUtil.isStrEmpty(strRsvBatch8)?"N":strRsvBatch8);			
			inList.add(nRealQty);
			inList.add(strTerminalFlag);
			inList.add(strSourceType);
			inList.add(strUserID);//回单人--系统登录人
			
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.proc_SaveMoveCell");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"",resultList);
		}		
		@Override
		public MsgRes tscRfSave(String strRecvData) throws Exception {
			HMSaveMoveCellRequestModel reqMod=JSON.parseObject(strRecvData, HMSaveMoveCellRequestModel.class);
			return tscSaveMoveCell(
					reqMod.getEnterpriseNo(),
					reqMod.getWarehouseNo(),//strWarehouseNo, 
					reqMod.getArticleNo(), 
					reqMod.getOutStockCellNo(), 
					reqMod.getLabelNo(), 
					reqMod.getInStockCellNo(), 
					Double.parseDouble(reqMod.getPackingQty()), 
					DateUtil.GetDate(reqMod.getProduceDate(), "yyyy-MM-dd"),
					DateUtil.GetDate(reqMod.getExpireDate(), "yyyy-MM-dd"),
					reqMod.getQuality(),
					reqMod.getLotNo(), 
					reqMod.getRsvBatch1(),
					reqMod.getRsvBatch2(),
					reqMod.getRsvBatch3(),
					reqMod.getRsvBatch4(),
					reqMod.getRsvBatch5(),
					reqMod.getRsvBatch6(),
					reqMod.getRsvBatch7(),
					reqMod.getRsvBatch8(), 
					Double.parseDouble(reqMod.getRealQty()),
					reqMod.getTerminalFlag(),
					reqMod.getSourceType(),
					reqMod.getUserId());
		}
		@Override
		public MsgRes HMGetRIDCellNo(String strRecvData)throws Exception
		{
			CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			outList.add("S");
			outList.add("S");
			List  inList=new ArrayList();
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWarehouseNo());
			inList.add(reqMod.getReqObj());

			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.proc_LabelMoveScanLabelNo");
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
			return new MsgRes(true,"",resultList.get(0).toString());
		}
		@Override
		public MsgRes tscSaveRIMoveCell(String strEnterpriseNo,
				String strWarehouseNo,
				String strLabelNo,
				String strDCellNo, 
				String strTerminalFlag, 
				String strSourceType, 
				String strUserID)
						throws Exception {
			String strFlag="";
			//校验目的储位是否存在
			String strSql = "select 1  from cdef_defarea cda  join cdef_defcell cdc " +
					"    on cda.enterprise_no = cdc.enterprise_no and cda.warehouse_no = cdc.warehouse_no" +
					"   and cda.ware_no = cdc.ware_no and cda.area_no = cdc.area_no" +
					" where cda.enterprise_no='"+strEnterpriseNo+"'" +
					" and cda.warehouse_no='"+strWarehouseNo+"'" +
					" and cdc.cell_no='"+strDCellNo+"'" +
					" and cdc.CELL_STATUS <> '1' " +
					" and cdc.CHECK_STATUS <> '3'" +
					" and cda.area_attribute<>'1'";
			List list=genDao.getListByNativeSql(strSql);
			if(list.size() != 1 )
			{
				strSql = "select distinct 1 from stock_content c " +
						" where c.enterprise_no='"+strEnterpriseNo+"'" +
						" and c.warehouse_no='"+strWarehouseNo+"'" +
						" and c.label_no='"+strDCellNo+"'" +
						" and not exists (select 1 from stock_label_m a " +
						" where a.enterprise_no = c.enterprise_no " +
						" and a.warehouse_no = c.warehouse_no " +
						" and a.label_no = c.label_no)" +
						" and c.outstock_qty = 0 and c.instock_qty=0 ";
				List list1=genDao.getListByNativeSql(strSql);
				if(list1.size() != 1 )
				{
					return new MsgRes(false,"目的储位(标签)不存在，或者目的储位（标签）不可用，请检查！","");
				}
				else
				{
					strFlag = "2";
				}
			}
			else
			{
				strFlag = "1";
			}
			List  outList=new ArrayList();
			List  resultList=new ArrayList();

			outList.add("S");
			List  inList=new ArrayList();

			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(strLabelNo);
			inList.add(strDCellNo);
			inList.add(strFlag);
			inList.add(strTerminalFlag);
			inList.add(strSourceType);
			inList.add(strUserID);//回单人--系统登录人

			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.proc_RISaveLabelMoveCell");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"",resultList);
		}
		@Override
		public MsgRes tscRfRISave(String strRecvData) throws Exception {
			HMSaveMoveCellRequestModel reqMod=JSON.parseObject(strRecvData, HMSaveMoveCellRequestModel.class);
			return tscSaveRIMoveCell(
					reqMod.getEnterpriseNo(),
					reqMod.getWarehouseNo(),//strWarehouseNo, 
					reqMod.getLabelNo(), 
					reqMod.getInStockCellNo(), 
					reqMod.getTerminalFlag(),
					reqMod.getSourceType(),
					reqMod.getUserId());
		}
		@Override
		public MsgRes tscGetHMDCell(String strRecvData) throws Exception {
			HMGetDCellNoRequestModel reqMod=JSON.parseObject(strRecvData, HMGetDCellNoRequestModel.class);
			List  outList=new ArrayList();			
			List  inList=new ArrayList();
			List  resultList=new ArrayList();
			outList.add("S");
			outList.add("S");
			
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWarehouseNo());
			inList.add(reqMod.getArticleNo());
			inList.add(reqMod.getSCellNo());
			inList.add(StringUtil.isStrEmpty(reqMod.getLabelNo())?"N":reqMod.getLabelNo());
			inList.add(Double.parseDouble(reqMod.getPackingQty()));
			inList.add(Double.parseDouble(reqMod.getMoveQty()));
			inList.add(reqMod.getProduceDate());
			inList.add(reqMod.getExpireDate());
			inList.add(reqMod.getQuality());
			inList.add(reqMod.getLotNo());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());
			inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());
			inList.add(reqMod.getAreaUseType());

			System.out.println(inList);			
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.proc_HM_MoveCell_GetDCellNo");
			System.out.println(resultList.get(1).toString());
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
			//System.out.println(resultList.get(0).toString());
			String strSql = " SELECT C.DISP_CELL_NO DISPCELLNO FROM CDEF_DEFCELL C " +
					"WHERE C.CELL_NO = '"+resultList.get(0).toString()+"' " +
					"AND C.WAREHOUSE_NO = '"+reqMod.getWarehouseNo()+"' " +
					"AND C.ENTERPRISE_NO ='"+reqMod.getEnterpriseNo()+"'";
			List list = genDao.getListByNativeSql(strSql);
			if(list.size()==0){
				return new MsgRes(true,"","N");
			}
			return new MsgRes(true,"",list.get(0).toString());
		}
		/***
		 * 过季转应季标签整理获取标签信息
		 */
		@Override
		public MsgRes tscGetHMLabelInfo(String strRecvData) throws Exception {
			MsgRes res=new MsgRes();
			//获取标签库存信息
			HMMoveLabelGetLabelInfo reqMod=JSON.parseObject(strRecvData, HMMoveLabelGetLabelInfo.class);
			List  outList=new ArrayList();			
			List  inList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWarehouseNo());
			inList.add(reqMod.getLabelNo());
			inList.add(reqMod.getPrinterGroupNo());
			inList.add(reqMod.getDockNo());
			inList.add(reqMod.getUserID());
			
			System.out.println(inList);			
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.P_HM_SEASON_OUT2IN_GETDCELLNO");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			String strSql="select c.enterprise_no,c.warehouse_no,c.owner_no,sum(c.qty) move_qty,c.packing_qty,v.unit_packing as unitPacking,v.qmin_operate_packing as qminOperatePacking," +
					//"nvl(pk.packing_unit, decode(c.packing_qty,v.qmin_operate_packing,v.qmin_operate_packing_unit,v.unit)) packing_unit," +
					"f_get_packingunit(v.enterprise_no,c.owner_no,v.article_no,c.packing_qty) packingUnit,"+
					"f_get_packingunit(v.enterprise_no,c.owner_no,v.article_no,v.qmin_operate_packing) packingUnitQmin,"+
					"f_get_packingunit(v.enterprise_no,c.owner_no,v.article_no,v.unit_packing) Unit,"+
					"f_get_spec(v.enterprise_no,c.owner_no,v.article_no,c.packing_qty) packingSpec,"+
					"f_get_spec(v.enterprise_no,c.owner_no,v.article_no,v.qmin_operate_packing) packingSpecQmin,"+
					"f_get_spec(v.enterprise_no,c.owner_no,v.article_no,v.unit_packing) spec,"+
					" v.ARTICLE_NAME, v.BARCODE,       v.ARTICLE_NO,       c.cell_no s_cell_no, " +
					" to_char(cai.produce_date,'yyyy-mm-dd') produce_date,to_char(cai.expire_date,'yyyy-mm-dd') expire_date,       cai.lot_no,       cai.quality,       c.label_no," +
					" l.source_no,    l.row_id,   l.d_cell_no, cdc.disp_cell_no DispDCellNo,      cdc.stock_no,       cdc.area_no,       cdc.ware_no,       cdc.pick_order,       l.qty " +
					" from stock_content c  join stock_article_info cai    on c.article_no = cai.article_no   and c.article_id = cai.article_id   and c.enterprise_no = cai.enterprise_no " +
					" join STOCK_LABEL_ARRANGE_LOG l    on c.enterprise_no = l.enterprise_no   and c.warehouse_no = l.warehouse_no" +
					" and c.owner_no = l.owner_no   and c.cell_no = l.s_cell_no   and c.label_no = l.s_label_no   and c.Article_no = l.Article_no" +
					" and c.packing_qty = l.packing_qty   and cai.produce_date = l.produce_date   and cai.expire_date = l.expire_date" +
					" and cai.quality = l.quality   and cai.lot_no = l.lot_no" +
					" join cdef_defcell cdc    on l.warehouse_no = cdc.warehouse_no   and cdc.enterprise_no = l.enterprise_no   and cdc.cell_no = l.d_cell_no" +
					" join v_bdef_defarticle v    on c.article_no = v.ARTICLE_NO and c.enterprise_no = v.ENTERPRISE_NO   and c.owner_no = v.OWNER_NO" +
					" left join bdef_article_packing pk on pk.enterprise_no=c.enterprise_no and pk.article_no=c.article_no and pk.packing_qty=c.packing_qty "+
					" where c.label_no='"+reqMod.getLabelNo()+"' "+
					" and c.enterprise_no='"+reqMod.getEnterpriseNo()+"' "+
					" and c.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					" and l.arrange_type='1' " +
					" group by c.enterprise_no,       c.warehouse_no,       c.owner_no,       c.packing_qty,v.enterprise_no,v.unit_packing, v.ARTICLE_NAME,       pk.packing_unit,v.qmin_operate_packing,v.qmin_operate_packing_unit,v.unit, " +
					" v.BARCODE,       v.ARTICLE_NO,       c.cell_no,       cai.produce_date,       cai.expire_date,       cai.lot_no,       cai.quality,       c.label_no,       l.source_no,   l.row_id,    l.d_cell_no, cdc.disp_cell_no, " +
					" cdc.stock_no,       cdc.area_no,       cdc.ware_no,       cdc.pick_order, cdc.cell_no,l.qty " +
					" order by cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,cdc.cell_no desc"; 					

			List<HMMoveLabelGetLabelInfo> list = genDao.getListByNativeSql(strSql,HMMoveLabelGetLabelInfo.class);
			if(list.size()==0){
				return new MsgRes(false,"标签不存在,请重新扫描！","");
			}
			//if(list.get(0).getSourceNo().equals("N"))
			//{
			//	strSql= "delete from mdata_arrange_log c "+
			//			" where c.label_no='"+reqMod.getLabelNo()+"' "+
			//			" and c.enterprise_no='"+reqMod.getEnterpriseNo()+"' "+
			//			" and c.warehouse_no='"+reqMod.getWarehouseNo()+"' ";
			//	genDao.updateBySql(strSql);
			//}
			return new MsgRes(true,"",JSONArray.fromObject(list));
		}
		/***
		 * 过季转应季标签整理商品整理
		 */
		@Override
		public MsgRes tscHMArticleArrange(String strRecvData) throws Exception {
			MsgRes res=new MsgRes();			
			HMMoveLabelGetLabelInfo reqMod=JSON.parseObject(strRecvData, HMMoveLabelGetLabelInfo.class);
			
			//根据扫描的商品条码查找商品信息
			res =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getBarcode(),reqMod.getEnterpriseNo());			
			if(!res.getIsSucc())
			{
				res.setIsSucc(false);
				res.setMsg("输入条码有误！");
				return res;
			}
			if(res.getObj().toString().equals("") || res.getObj() == null)
			{
				res.setIsSucc(false);
				res.setMsg("输入条码不存在！");
				return res;
			}
			if(res.getObj().toString().equals(reqMod.getArticleNo()))
			{
				res.setIsSucc(false);
				res.setMsg("输入条码不是当前需要整理的商品！");
				return res;
			}
			List  outList=new ArrayList();			
			List  inList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWarehouseNo());
			inList.add(reqMod.getOwnerNo());
			inList.add(reqMod.getSCellNo());
			inList.add(reqMod.getLabelNo());
			inList.add(reqMod.getArticleNo());
			inList.add(reqMod.getPackingQty());
			
			inList.add(reqMod.getProduceDate());
			inList.add(reqMod.getExpireDate());
			inList.add(reqMod.getQuality());
			inList.add(reqMod.getLotNo());
						
			inList.add(reqMod.getMoveQty());
			inList.add(reqMod.getQty());
			inList.add(reqMod.getDCellNo());
			inList.add(reqMod.getPrinterGroupNo());
			inList.add(reqMod.getDockNo());
			inList.add(reqMod.getUserID());
			
			System.out.println(inList);			
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.P_HM_SEASON_OUT2IN_ARRANGE");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"",resultList.get(0).toString());
		}
		/**
		 * 获取码头和标签下已打包总数量*
		 */
		@Override
		public MsgRes GetHMStockSetLabelInfo(String strRecvData)
				throws Exception {
			MsgRes res=new MsgRes();
			
			HMMoveLabelGetLabelInfo reqMod=JSON.parseObject(strRecvData, HMMoveLabelGetLabelInfo.class);
			String strSql=" select distinct t.owner_no,t.label_no,t.source_no,t.s_cell_no,t.s_label_no,t.packing_qty, " +
					" sum(case t.packing_qty when 1 then 0 else (t.qty/t.packing_qty) end) qty," +
					//" sum(case t.packing_qty when 1 then t.qty else mod(t.qty,t.packing_qty) end) moveQty " +
					" from STOCK_LABEL_ARRANGE_LOG t " +
					" where t.enterprise_no='"+reqMod.getEnterpriseNo()+"' "+
					" and t.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					" and t.label_no='"+reqMod.getLabelNo()+"' " +
					" and t.dock_no='"+reqMod.getDockNo()+"' " +
					" and t.arrange_type = '2' " +
					" and t.status = '10'" +
					" group by t.owner_no,t.label_no,t.source_no,t.s_cell_no,t.s_label_no,t.packing_qty " ;				

			List<HMMoveLabelGetLabelInfo> list = genDao.getListByNativeSql(strSql,HMMoveLabelGetLabelInfo.class);
			if(list.size()==0){
				res.setIsSucc(false);
				res.setMsg("标签不存在！");
				return  res;
			}
			return new MsgRes(true,"",JSONArray.fromObject(list));
		}
		/**
		 * 扫描保存*
		 */
		@Override
		public MsgRes saveHMScaninfo(String strRecvData) throws Exception {
			MsgRes res=new MsgRes();			
			HMMoveLabelGetLabelInfo reqMod=JSON.parseObject(strRecvData, HMMoveLabelGetLabelInfo.class);
			//根据扫描的商品条码查找商品信息
			res =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getBarcode(),reqMod.getEnterpriseNo());			
			if(!res.getIsSucc())
			{
				res.setIsSucc(false);
				res.setMsg("输入条码有误！");
				return res;
			}
			if(res.getObj().toString().equals("") || res.getObj() == null)
			{
				res.setIsSucc(false);
				res.setMsg("输入条码不存在！");
				return res;
			}
			/***************************************
			 * ************注意：一码多品 存在bug ******
			 * ***********************************/
			String strSql=" select distinct sai.article_no,sai.article_id,sai.barcode, " +
					" to_char(sai.produce_date,'yyyy-mm-dd') produce_date, to_char(sai.expire_date,'yyyy-mm-dd')expire_date, " +
					" sai.quality, sai.import_batch_no, sai.lot_no," +
					" sai.rsv_batch1, sai.rsv_batch2, sai.rsv_batch3, sai.rsv_batch4, sai.rsv_batch5, sai.rsv_batch6, sai.rsv_batch7, sai.rsv_batch8," +
					" sai.enterprise_no,sc.PACKING_QTY,v.ARTICLE_NAME,v.unit_packing as unitPacking,v.qmin_operate_packing as qminOperatePacking," +
					//" nvl(pk.packing_unit, decode(sc.packing_qty,v.qmin_operate_packing,v.qmin_operate_packing_unit,v.unit)) packing_unit," +
					" f_get_packingunit(sc.enterprise_no,sc.owner_no,sc.article_no,sc.packing_qty) packingUnit,"+
					" f_get_packingunit(sc.enterprise_no,sc.owner_no,sc.article_no,v.qmin_operate_packing) packingUnitQmin,"+
					" f_get_packingunit(sc.enterprise_no,sc.owner_no,sc.article_no,v.unit_packing) Unit,"+
					" f_get_spec(sc.enterprise_no,sc.owner_no,sc.article_no,sc.packing_qty) packingSpec,"+
					" f_get_spec(sc.enterprise_no,sc.owner_no,sc.article_no,v.qmin_operate_packing) packingSpecQmin,"+
					" f_get_spec(sc.enterprise_no,sc.owner_no,sc.article_no,v.unit_packing) spec,"+
					" v.OWNER_NO " +
					" from stock_content sc inner join stock_article_info sai " +
					" on sc.Article_no = sai.Article_no and sc.Article_id = sai.Article_id and sc.enterprise_no = sai.enterprise_no " +
					" inner join v_bdef_defarticle v on v.ENTERPRISE_NO=sai.enterprise_no and v.OWNER_NO=sc.owner_no and v.ARTICLE_NO=sai.article_no" +
					" left join bdef_article_packing pk on pk.enterprise_no=sc.enterprise_no and pk.article_no=sc.article_no and pk.packing_qty=sc.packing_qty "+
					" where sai.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
					" and sc.warehouse_no='"+reqMod.getWarehouseNo()+"' " ;
					if(!reqMod.getOwnerNo().equals(""))
					{
						strSql+=" and sc.owner_no='"+reqMod.getOwnerNo()+"'" ;
					}
					strSql+=" and sc.cell_no='"+reqMod.getSCellNo()+"'"+
					" and sc.label_no='"+reqMod.getSLabelNo()+"'"+
					//" and v.barcode='"+reqMod.getBarcode()+"'" +
					" and v.article_no in ("+ res.getObj().toString() +")" +
					" and sc.qty - sc.outstock_qty > 0" +
					" and sc.status<>'1' ";			
			
			List<HMMoveLabelGetLabelInfo> listArt = genDao.getListByNativeSql(strSql,HMMoveLabelGetLabelInfo.class);
			if(listArt.size()==0){
				res.setIsSucc(false);
				res.setMsg("不存在该商品库存信息，请核实！");
				return  res;
			}
			List  outList=new ArrayList();			
			List  inList=new ArrayList();
			List  resultList=new ArrayList();
			//预留换算数量			
			
			outList.add("S");
			outList.add("S");
			outList.add("S");
			
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWarehouseNo());
			inList.add(listArt.get(0).getOwnerNo());
			
			inList.add(reqMod.getSCellNo());
			inList.add(reqMod.getSourceNo());
			inList.add(reqMod.getLabelNo());
			inList.add(reqMod.getSLabelNo());
			inList.add(listArt.get(0).getArticleNo());
			inList.add(listArt.get(0).getPackingQty());
			inList.add(listArt.get(0).getProduceDate());
			inList.add(listArt.get(0).getExpireDate());
			inList.add(listArt.get(0).getQuality());
			inList.add(listArt.get(0).getLotNo());
			inList.add(reqMod.getQty());
			inList.add(reqMod.getPrinterGroupNo());
			inList.add(reqMod.getDockNo());
			inList.add(reqMod.getUserID());
						
			System.out.println(inList);			
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.P_HM_STOCKSETLABEL_SCAN");
			if(resultList.get(2).toString().indexOf("N|")!=-1){
				res.setIsSucc(false);
				res.setMsg(resultList.get(2).toString());
				return  res;
			}
			
			String strLabelNo=resultList.get(0).toString();
			String strSourceNo=resultList.get(1).toString();
			
			//to_char(t.produce_date,'yyyy-mm-dd')
			
			strSql=" select t.*,v.ARTICLE_NAME," +
					//"nvl(pk.packing_unit, decode(t.packing_qty,v.qmin_operate_packing,v.qmin_operate_packing_unit,v.unit)) packing_unit," +
					"f_get_packingunit(t.enterprise_no,t.owner_no,t.article_no,t.packing_qty) packingUnit,"+
					"f_get_packingunit(t.enterprise_no,t.owner_no,t.article_no,v.qmin_operate_packing) packingUnitQmin,"+
					"f_get_packingunit(t.enterprise_no,t.owner_no,t.article_no,v.unit_packing) Unit,"+
					"f_get_spec(t.enterprise_no,t.owner_no,t.article_no,t.packing_qty) packingSpec,"+
					"f_get_spec(t.enterprise_no,t.owner_no,t.article_no,v.qmin_operate_packing) packingSpecQmin,"+
					"f_get_spec(t.enterprise_no,t.owner_no,t.article_no,v.unit_packing) spec,"+
					"v.barcode,(select nvl(sum(qty), 0) from STOCK_LABEL_ARRANGE_LOG c " +
				" where c.enterprise_no = t.enterprise_no and c.warehouse_no = t.warehouse_no " +
				" and c.owner_no = t.owner_no and c.dock_no = t.dock_no and c.label_no=t.label_no " +
				" and c.arrange_type = '2' and c.status = '10') Moveqty " +
				" from STOCK_LABEL_ARRANGE_LOG t join v_bdef_defarticle v " +
				" on t.enterprise_no=v.enterprise_no and t.owner_no=v.owner_no and t.article_no=v.article_no " +
				" left join bdef_article_packing pk on pk.enterprise_no=t.enterprise_no and pk.article_no=t.article_no and pk.packing_qty=t.packing_qty "+
				" where t.enterprise_no='"+reqMod.getEnterpriseNo()+"' "+
				" and t.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				" and t.owner_no ='"+listArt.get(0).getOwnerNo()+"'" +
				" and t.label_no='"+strLabelNo+"' " +
				" and t.dock_no='"+reqMod.getDockNo()+"' " +
				" and t.source_no = '"+strSourceNo+"'" +
				" and t.article_no = '"+listArt.get(0).getArticleNo()+"'" +

				" and t.packing_qty = '"+listArt.get(0).getPackingQty()+"'" +
				" and to_char(t.produce_date,'yyyy-mm-dd') = '"+listArt.get(0).getProduceDate()+"' " +
				" and to_char(t.expire_date,'yyyy-mm-dd') = '"+listArt.get(0).getExpireDate()+"' " +
				" and t.quality = '"+listArt.get(0).getQuality()+"'" +
				" and t.lot_no = '"+listArt.get(0).getLotNo()+"'" +
				
				" and t.arrange_type = '2' " +
				" and t.status = '10'" ;
			
			List<HMMoveLabelGetLabelInfo> list = genDao.getListByNativeSql(strSql,HMMoveLabelGetLabelInfo.class);
			if(list.size()==0){
				res.setIsSucc(false);
				res.setMsg("获取不到库存录标签数据！");
				return  res;
			}		
			return new MsgRes(true,"",JSONArray.fromObject(list));
			
		}
		/**
		 * 打包封箱
		 */
		@Override
		public MsgRes tscHMCloseBox(String strRecvData) throws Exception {
			MsgRes res=new MsgRes();			
			HMMoveLabelGetLabelInfo reqMod=JSON.parseObject(strRecvData, HMMoveLabelGetLabelInfo.class);
			
			List  outList=new ArrayList();			
			List  inList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWarehouseNo());
			inList.add(reqMod.getOwnerNo());
			inList.add(reqMod.getSourceNo());
			inList.add(reqMod.getLabelNo());
			inList.add("2");
			inList.add(reqMod.getUserID());
			
			System.out.println(inList);			
			resultList=genDao.execProc(inList, outList, "PKLG_MDATA.P_HM_STOCKSETLABEL_CLOSEBOX");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"",resultList.get(0).toString());
		}
		/**
		 * 校验码头，并获取码头对应的标签数
		 */
		@Override
		public MsgRes checkDockValidate(String strRecvData) throws Exception {
			MsgRes msgRes=new MsgRes();
			CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			
			String strSql="select * " +
						  "from " +
								"PNTSET_PRINTER_WORKSTATION a " +
						  "where " +
								" a.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
								" and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
								" and a.workstation_no='"+reqMod.getReqObj()+"'";
			List list = genDao.getListByNativeSql(strSql);
			if(list.size()==0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("输入的码头不存在！");
			}else
			{
				strSql="select distinct t.label_no from STOCK_LABEL_ARRANGE_LOG t " +
						" where t.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
						" and t.warehouse_no ='"+reqMod.getWarehouseNo()+"'" +
						" and t.dock_no='"+reqMod.getReqObj()+"'" +
						" and t.arrange_type='2'" +
						" and t.status = '10'";
				
				list= genDao.getListByNativeSql(strSql);	
				
				msgRes.setIsSucc(true);
				msgRes.setObj(list.size());
			}
			return msgRes;
		}
		/**
		 * 库存录标签校验储位
		 */
		@Override
		public MsgRes checkCellNoValidate(String strRecvData)
				throws Exception {
			MsgRes msgRes=new MsgRes();
			CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			
			String strSql="select 1 from stock_content sc where sc.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
					" and sc.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
					" and sc.cell_no='"+reqMod.getReqObj()+"'" +
					" and sc.qty - sc.outstock_qty > 0" +
					" and sc.status<>'1' ";
			List list = genDao.getListByNativeSql(strSql);
			if(list.size()==0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("输入的储位不存在或者没有库存！");
			}else
			{
			    strSql = " SELECT C.DISP_CELL_NO DISPCELLNO FROM CDEF_DEFCELL C " +
				  "WHERE C.CELL_NO = '"+reqMod.getReqObj()+"' " +
				  "AND C.WAREHOUSE_NO = '"+reqMod.getWarehouseNo()+"' " +
				  "AND C.ENTERPRISE_NO ='"+reqMod.getEnterpriseNo()+"'";
		        list = genDao.getListByNativeSql(strSql);
		        msgRes.setObj(list.get(0).toString());
			    msgRes.setIsSucc(true);
			}
			return msgRes;
		}
		/**
		 * 库存录标签校验标签
		 */
		@Override
		public MsgRes checkLabelNoValidate(String strRecvData)
				throws Exception {
			MsgRes msgRes=new MsgRes();
			/*CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			
			String strSql="select * " +
						  "from " +
								"PNTSET_PRINTER_WORKSTATION a " +
						  "where " +
								" a.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
								" and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
								" and a.workstation_no='"+reqMod.getReqObj()+"'";
			List list = genDao.getListByNativeSql(strSql);
			if(list.size()==0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("输入的码头不存在！");
			}else
			{
				strSql="select distinct t.label_no from STOCK_LABEL_ARRANGE_LOG t " +
						" where t.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
						" and t.warehouse_no ='"+reqMod.getWarehouseNo()+"'" +
						" and t.dock_no='"+reqMod.getReqObj()+"'" +
						" and t.arrange_type='2'" +
						" and t.status = '10'";
				
				list= genDao.getListByNativeSql(strSql);	
				
				msgRes.setIsSucc(true);
				msgRes.setObj(list.size());
			}*/
			return msgRes;
		}
}
