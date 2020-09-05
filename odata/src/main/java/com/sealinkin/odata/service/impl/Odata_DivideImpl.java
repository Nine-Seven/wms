package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.odata.model.Odata_DivideMModel;
import com.sealinkin.odata.service.IOdata_DivideService;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
//import com.sealinkin.protocolExchange.odata.AnsBoxInfoModel;
import com.sealinkin.protocolExchange.odata.AnsDivideBoxDetailModel;
import com.sealinkin.protocolExchange.odata.AnsGetDivideBoxDetailModel;
import com.sealinkin.protocolExchange.odata.AnsGetTaskLabel;
import com.sealinkin.protocolExchange.odata.BoxInfoModel;
import com.sealinkin.protocolExchange.odata.DivideDateAnswerModel;
import com.sealinkin.protocolExchange.odata.DivideGetNOModel;
import com.sealinkin.protocolExchange.odata.ListDivideModel;
import com.sealinkin.protocolExchange.odata.ListOutStockAnswerModel;
import com.sealinkin.protocolExchange.odata.OdataDivideCfmRequestModel;
import com.sealinkin.protocolExchange.odata.OutStockDateAnswerModel;
import com.sealinkin.protocolExchange.odata.ReqGetDivideBoxModel;
import com.sealinkin.protocolExchange.odata.StuAnsBoxUserListModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_DivideImpl implements IOdata_DivideService{
		
	private IGenericManager genDao;
	private IOdata_OutstockM odata_OutstockMImpl;
	
	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	public IOdata_OutstockM getOdata_OutstockMImpl() {
		return odata_OutstockMImpl;
	}

	public void setOdata_OutstockMImpl(IOdata_OutstockM odata_OutstockMImpl) {
		this.odata_OutstockMImpl = odata_OutstockMImpl;
	}

	/**
	 * strFlag=1:获取波次号
	 * strFlag=2:获取批次号
	 * strCheckFlag=history:历史单据查询
	 * strCheckFlag=normal:非历史单据查询
	 */
	@Override
	public List<ComboxBo> getOdata_DivideCombo(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner, 
			String strFlag, String str, String strWheresql,
			String strCheckFlag, int intStart, int intPagesize) throws Exception {
		String strSql = "";
		if(strFlag != null && strFlag.equals("1"))
		{
			strSql = "select distinct odd.wave_no value,odd.wave_no text," +
					"odd.wave_no dropValue from %s1 odd where 1=1 " ;	
			if(strWheresql != null && !strWheresql.equals(""))
			{
				strSql = strSql + "and odd.wave_no like '%" + strWheresql + "%' ";
			}
		}else if(strFlag != null && strFlag.equals("2"))
		{
			strSql = "select distinct odd.batch_no value,odd.batch_no text," +
					"odd.batch_no dropValue from %s1 odd where 1=1 " ;
		}else if(strFlag != null && strFlag.equals("3"))
		{
			strSql = "select distinct clm.label_no value,clm.label_no text," +
					"clm.label_no dropValue FROM" +
					" ODATA_DIVIDE_DHTY ODD inner join " +
					" stock_label_mhty clm on clm.enterprise_no = odd.enterprise_no " +
					" and clm.warehouse_no = ODD.warehouse_no " +
					" and ODD.s_container_no = clm.container_no ";
		}else if(strFlag != null && strFlag.equals("4"))
		{
			/*strSql = "select distinct ODD.divide_no FROM " +
					" ODATA_DIVIDE_MHTY ODM  inner join " +
					" stock_label_mhty clm on clm.warehouse_no = ODM.warehouse_no  " +
					"  and ODM.divide_no=clm.source_no inner join odata_divide_d odd " +
					" on odm.divide_no = odd.divide_no and odm.warehouse_no=odd.warehouse_no";*/			
			strSql = "select distinct ODD.divide_no value,ODD.divide_no text," +
					"ODD.divide_no dropValue FROM " +
					" %s2 ODM inner join %s1 odd " +
					" on odm.divide_no = odd.divide_no and odm.warehouse_no=odd.warehouse_no " +
					" and odm.enterprise_no = odd.enterprise_no ";
			if(strWheresql != null && !strWheresql.equals(""))
			{
				strSql = strSql + "and odd.divide_no like '%" + strWheresql + "%' ";
			}
		}
		if(strEnterpriseNo != null && !strEnterpriseNo.equals("")){
			strSql = strSql + " and odd.enterprise_no='" + strEnterpriseNo +"'";
		}
		if(strWarehouseNo != null && !strWarehouseNo.equals("")){
			strSql = strSql + " and odd.warehouse_no='" + strWarehouseNo +"'";
		}
		if(str != null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		if(strWorkerOwner != null && !strWorkerOwner.equals(""))
		{
			strSql = strSql + " and odd.owner_no in(" + strWorkerOwner + ")";
		}/*else{
			sql=sql+" and 1=2";
		}*/
		if(strCheckFlag != null && strCheckFlag.equals("history"))
		{
			strSql = strSql.replace("%s1", "odata_divide_dhty");
			strSql = strSql.replace("%s2", "ODATA_DIVIDE_MHTY");
		}else
		{
			strSql = strSql.replace("%s1", "odata_divide_d");
			strSql = strSql.replace("%s2", "ODATA_DIVIDE_M");
		}
		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}

	@Override
	public ExtListDataBo<Odata_DivideMModel> getOdata_DivideM(
			String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, String str,
			String strFlag, int start, int pageSize) throws Exception {
			String strSql = " select odm.*, def.text status_desc," 
				+" f_get_fieldtext('N','STATUS',odm.status) as statusText"
				+" from %s1 odm"
				+" inner join wms_deffieldval def "
				+" on def.colname = upper('STATUS')"
				+" and def.table_name = upper('N')"
				+" and def.value = odm.status"
				+" where 1=1 %s4 "
				+" and odm.enterprise_no='"+strEnterpriseNo+"' " +
				"  and odm.warehouse_no='"+strWarehouseNo+"' "
				+" and exists "
				+"    (select 1 "
				+"       from %s6 olb "
				+"      where   olb.WAREHOUSE_NO=odm.WAREHOUSE_NO " 
				+"				and olb.enterprise_no=odm.enterprise_no " 
				+"				and olb.BATCH_NO=odm.BATCH_NO " 
				+"				and olb.WAVE_NO=odm.WAVE_NO " 
				+"				and olb.INDUSTRY_FLAG='1') " 				
				+" and exists "
				+"    (select 1 "
				+"       from %s2 odd"
				+"      where   odm.divide_no=odd.divide_no " 
				+"				and odm.enterprise_no=odd.enterprise_no " 
				+"        %s3 ) order by odm.divide_no desc ";				
			if(strFlag != null && strFlag.equals("2"))
			{
				strSql = strSql.replace("%s1", "ODATA_DIVIDE_MHTY");
				strSql = strSql.replace("%s2", "odata_divide_dhty");
				strSql = strSql.replace("%s6", "odata_locate_batch");
				strSql = strSql.replace("%s4", "");
			}else
			{
				strSql = strSql.replace("%s1", "ODATA_DIVIDE_M");
				strSql = strSql.replace("%s2", "odata_divide_d");
				strSql = strSql.replace("%s6", "ODATA_LOCATE_BATCH");
				strSql = strSql.replace("%s4", "and odm.status='10'");
			}
			if(str!=null && !str.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql.replace("%s3", ws);
			}else{
				strSql = strSql.replace("%s3", "");
			}
			String totsql="select count(1) from ("+strSql+")";
			List<Odata_DivideMModel> list = genDao.getListByNativeSql(strSql,Odata_DivideMModel.class,start,pageSize);
			Integer count = genDao.getCountByNativeSql(totsql);
			ExtListDataBo<Odata_DivideMModel> extListBo= new ExtListDataBo<Odata_DivideMModel>(list, count);
	        return extListBo;
	}

	/*
	 * 获得分播回单明细
	 */
	@Override
	public ExtListDataBo<Odata_DivideDModel> getOdata_DivideD(
			String strEnterpriseNo,
			String strWorkerOwner, String str,
			String strFlag,int intStart, int intPageSize) throws Exception {
		String strAttributeFields=odata_OutstockMImpl.getArticleAttrString("1").get(0);
		String strAttributeGroupByFields=odata_OutstockMImpl.getArticleAttrString("2").get(0);
		if(!"".equals(strAttributeFields))
		{
			strAttributeFields+=", ";
			if(strAttributeFields.indexOf("QUALITY")>0)
			{
				strAttributeFields+="f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality, ";
			}
		}
		
		if(!"".equals(strAttributeGroupByFields))
		{
			strAttributeGroupByFields=","+strAttributeGroupByFields;
		}
		String strSql = " select "
				+" b.*,"
				+" BDA.OWNER_ARTICLE_NO as ownerArticleNo,"
				+" BDA.ARTICLE_NAME,"
				+" BDA.ARTICLE_ENAME,"
				+" BDA.ARTICLE_IDENTIFIER,"
				+" BDA.ARTICLE_ONAME,"
				+" BDA.ARTICLE_ALIAS,  "
				//add by huangcx at 20160528
				+"trunc(b.articleQty/b.packing_qty) as planBox," 
			    +"trunc(mod(b.articleQty,b.packing_qty)/bda.QMIN_OPERATE_PACKING) as planQmin," 
			    +"(b.articleQty - trunc(b.articleQty/b.packing_qty) * b.packing_qty - trunc(mod(b.articleQty,b.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as planDis," 
                +"trunc(b.realQty/b.packing_qty) as realBox," 
			    +"trunc(mod(b.realQty,b.packing_qty)/bda.QMIN_OPERATE_PACKING) as realQmin,"
			    +"(b.realQty - trunc(b.realQty/b.packing_qty) * b.packing_qty - trunc(mod(b.realQty,b.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as realDis,"
			    //end add
				//+"nvl(bap.packing_unit, decode(b.packing_qty,BDA.qmin_operate_packing,BDA.qmin_operate_packing_unit,BDA.unit)) UNIT,"
                //+"nvl(bap.spec, '1*' || b.packing_qty || BDA.unit) spec,"
                +"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"
                +"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,bda.qmin_operate_packing) packingUnitQmin,"
                +"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,bda.unit_packing) Unit,"
                +"f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"
                +"f_get_spec(b.enterprise_no,b.owner_no,b.article_no,bda.qmin_operate_packing) packingSpecQmin,"
                +"f_get_spec(b.enterprise_no,b.owner_no,b.article_no,bda.unit_packing) spec,"
				//+" BDA.SPEC, "
				+" BDA.QMIN_OPERATE_PACKING, " 
				+" BDA.UNIT_PACKING,"
				+" BDA.RULE_FLAG,"
				+" BDA.ABC,"
				+" BDA.QC_FLAG,"
				+" BDA.MEASURE_MODE,"
				+" BDA.TEMPERATURE_FLAG,"
				+" BDA.VIRTUAL_FLAG,"
				+" BDA.SCAN_FLAG,"
				//+" BDA.UNIT,"
				+" bda.DIVIDE_EXCESS,"
				+" BDA.DOUBLE_CHECK, "
				+" bda.BARCODE,"
				+" d.cust_name || '[' || nvl(dps.dps_area, 0) || ']' as cust_name,"
				+" PAL_BASE_QBOX,"
				+" PAL_HEIGHT_QBOX"
				+" from (SELECT a.enterprise_no," +
				"  a.warehouse_no,"
				+" a.dps_cell_no,"
				+" a.BATCH_NO,"
				+" a.LINE_NO,"
				+" a.owner_no,"
				+" a.a_sorter_chute_no,"
				+" a.DIVIDE_NO,"
				+" clm.label_no,"
				+" a.s_container_no,"
				+" a.DELIVER_OBJ,"
				+" a.ARTICLE_NO,  "
				+" a.S_CELL_NO,"
				+" a.wave_no, "
				+strAttributeFields 
			/*	+ odata_OutstockMImpl.getArticleAttrString("1").get(0) 
				+", f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality ,"*/
				+" a.cust_no,"
				+" a.packing_qty,"
				+" sum(a.ARTICLE_QTY) as articleQty,"
				+" sum(a.ARTICLE_QTY) as realQty "
//				+" (sum(a.article_qty)/ a.packing_qty) planWholenum, "
//				+" (sum(a.article_qty)/ a.packing_qty) realWholenum "
				+" FROM %s2 A"
				+" inner join STOCK_ARTICLE_INFO sai"
				+" on a.enterprise_no = sai.enterprise_no"
				+" and a.article_no = sai.article_no"
				+" and a.article_id = sai.article_id"
				+" inner join %s3 clm"
				+" on clm.enterprise_no = a.enterprise_no"
			    +" and clm.warehouse_no = a.warehouse_no"
				+" and a.s_container_no = clm.container_no"
				+" where 1=1 %s1"
				+" and a.enterprise_no = '"+strEnterpriseNo+"' " 				
			    +" %s4 "
				+" group by a.enterprise_no," +
				"  a.warehouse_no,"
				+" a.ARTICLE_NO, "
				+" a.dps_cell_no,"
				+" a.BATCH_NO,"
				+" a.LINE_NO,"
				+" a.owner_no,"
				+" a.a_sorter_chute_no,"
				+" a.s_container_no,"
				+" a.DELIVER_OBJ,"
				+" a.DIVIDE_NO,"
				+" clm.label_no,"
				+" a.cust_no,"
				+" a.packing_qty,"
				+" a.S_CELL_NO, "
				+" a.wave_no ," 
				+" sai.QUALITY "
				/*+ odata_OutstockMImpl.getArticleAttrString("2").get(0) */
				+strAttributeGroupByFields
				+" ) b"
				+" inner join bdef_defarticle bda"
				+" on b.article_no = bda.article_no"
				+" and b.enterprise_no = bda.enterprise_no " +
				"  and b.owner_no = bda.owner_no"
				+" inner join bdef_defcust D"
				+" on b.cust_no = d.cust_no"
				+" and b.enterprise_no = d.enterprise_no " +
				"  and b.owner_no = d.owner_no"
				+" left join BDEF_ARTICLE_PACKING bap"
				+" on bap.ARTICLE_NO = b.ARTICLE_NO "
				+" and bap.enterprise_no = b.enterprise_no " +
				"  and bap.packing_qty = b.packing_qty"
				+" left join cdef_defcell_dps dps"
				+" on b.dps_cell_no = dps.cell_no"
				+" and b.enterprise_no = dps.enterprise_no " +
				"  and b.warehouse_no = dps.warehouse_no"
				+" ORDER BY label_no, nvl(dps.dps_area, 0), b.article_no, b.CUST_NO";

		
		if(strFlag != null && strFlag.equals("2"))
		{
			strSql = strSql.replace("%s2", "odata_divide_dhty");
			strSql = strSql.replace("%s3", "stock_label_mhty");
			strSql = strSql.replace("%s4", "and a.status = '13'");
		}else
		{
			strSql = strSql.replace("%s2", "odata_divide_d");
			strSql = strSql.replace("%s3", "stock_label_m");
			strSql = strSql.replace("%s4", "and a.status='10'");
		}
				
		if(str != null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
		}
		List<Odata_DivideDModel> list = genDao.getListByNativeSql(strSql,Odata_DivideDModel.class,intStart,intPageSize);
		ExtListDataBo<Odata_DivideDModel> extListBo= new ExtListDataBo<Odata_DivideDModel>(list, 0);
        return extListBo;
	}
	@Override
	public MsgRes save(String str,String updtName) throws Exception{
		List<Odata_DivideDModel> odd=JSON.parseArray(str,Odata_DivideDModel.class);
		List saveOdataDivideResult=new ArrayList();
		for (Odata_DivideDModel i : odd) {
			saveOdataDivideResult=tscSaveOdataDivide(
					i.getId().getEnterpriseNo(),
					i.getId().getWarehouseNo(),
					i.getId().getOwnerNo(),
					i.getId().getDivideNo(),
					i.getArticleNo(),
					i.getId().getCustContainerNo(),
					i.getArticleQty(),
					i.getRealQty(),
					i.getDivideName(),
					i.getBatchNo(),
					i.getQuality(),
					i.getProduceDate(),
					i.getExpireDate(),
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
					i.getSContainerNo(),
					i.getCustNo(),
					i.getDeliverObj(),
					updtName);
			if(saveOdataDivideResult.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(saveOdataDivideResult.get(0).toString());
			}
			System.out.println(saveOdataDivideResult.get(0).toString());
		}
		return new MsgRes(true,"保存成功！",null);
	}
		
	
	/**
	 * 表单分播回单
	 * @return
	 * @throws Exception
	 */
	@Override
	public List tscSaveOdataDivide(
			String strEnterPriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strDivideNo,
			String strArticleNo,
			String strDcontainerNo,
			Double articleQty,
			Double nRealQty,
			String strDivideName,
			String strBatchNo,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRsvBatch1,
			String strRsvBatch2,
			String strRsvBatch3,
			String strRsvBatch4,
			String strRsvBatch5,
			String strRsvBatch6,
			String strRsvBatch7,
			String strRsvBatch8,
			String strSCellNo,
			String strSContainerNo,
			String strCustNo,
			String strDelvierObj,
			String strUpdtName)throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterPriseNo);//strEnterPriseNo
		inList.add(strWarehouseNo);//strWarehouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strDivideNo);//strDivideNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strDcontainerNo);//strDcontainerNo
		inList.add(articleQty);
		inList.add(nRealQty);//nRealQty
		inList.add(strDivideName);//strDivideName
		inList.add(strBatchNo);//strBatchNo		
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
		inList.add(strSCellNo);//strSCellNo
		inList.add(strSContainerNo);//strSContainerNo
		inList.add(strCustNo);//strCustNo
		inList.add(strDelvierObj);//strDelvierObj
		
		inList.add(strUpdtName);//strUpdtName
		inList.add("2");//strAddFlag
		inList.add("B");//strContainerType
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.P_SAVE_ODATA_DIVIDE");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return resultList;
	}

	@Override
	public MsgRes check( String warehouseNo,String str) {
		String sql="select * from stock_label_m a " +
				"where a.warehouse_no='"+warehouseNo+"' and a.label_no='"+str.split(",")[1]+"'";
		//输入标签号时需要校验：1、若在标签表存着的号，需检查是不是出货标签，并且是此客户的，若不是，给予拦截；
		List<Stock_LabelMModel> list=genDao.getListByNativeSql(sql, Stock_LabelMModel.class, 0,10);
		if(list.size()>0){
			if(list.get(0).getCustNo().equals(str.split(",")[0]))
			{
				return new MsgRes(true,"",null);
			}else{
				return new MsgRes(false,"标签号码已被使用，请重新输入！",null);
			}
		}else{
			return new MsgRes(true,"",null);
		}
	}
	
	
	
	/**
	 * 获取分播明细集合(RF)
	 */
	public MsgRes queryOdataDivideD(String strRecvData)throws Exception
	{
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strLabelNo=reqMod.getReqObj();

		String strDivideType = reqMod.getFieldEX1();
		//校验标签号
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		List  inList = new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strLabelNo);
		resultList=genDao.execProc(inList, outList, "pkcheck_odata.P_DivideCheckSLabelNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		//取分播数据
		Map<Integer, DivideDateAnswerModel> map=new HashMap<Integer, DivideDateAnswerModel>();
		ListDivideModel listDivdeMod=new ListDivideModel();
		String strSql=" select    "
				+"        b.owner_no," +
				"         b.dps_cell_no,B.WAVE_NO WAVENO, b.Deliverobj_Order DeliverobjOrder,  "
				+"        b.divide_no,   "
				+"        b.deliver_obj,   "
				+"        b.batch_no,   "
				+"        b.cust_no,   "
				+"        d.cust_name,   "
				+"        b.s_cell_no,   "
				+"        b.s_container_no,   "
				+"        b.divideQty,   "
				+"        b.article_no,   "
				+"        bda.BARCODE,   "
				+"        bda.article_name,   "
				+"        b.lot_no,  "
				+"        TO_CHAR(b.produce_date,'yyyyMMdd') as produce_date,   "
				+"        TO_CHAR(b.expire_date,'yyyyMMdd') as expire_date,   "
				+"        b.quality,   "
				+"        b.rsv_batch1,   "
				+"        b.rsv_batch2,   "
				+"        b.rsv_batch3,   "
				+"        b.rsv_batch4,   "
				+"        b.rsv_batch5,   "
				+"        b.rsv_batch6,   "
				+"        b.rsv_batch7,   "
				+"        b.rsv_batch8,   "
				+"        bda.expiry_days,   "
				+"        bda.UNIT,  " +
				"	f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit," +
				"    f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,bda.qmin_operate_packing) packingUnitQmin," +
				"    f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,bda.unit_packing) Unit," +
				 "   f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec," +
				 "   f_get_spec(b.enterprise_no,b.owner_no,b.article_no,bda.qmin_operate_packing) packingSpecQmin," +
				 "   f_get_spec(b.enterprise_no,b.owner_no,b.article_no,bda.unit_packing) spec,"
				+"   bda.unit_packing,bda.qmin_operate_packing,b.packing_qty as packQty   "
				+"    from   "
				+"        (SELECT   "
				+"            a.enterprise_no,a.warehouse_no,a.Deliverobj_Order,   "
				+"            a.dps_cell_no,   "
				+"            a.BATCH_NO,   "
				+"            a.LINE_NO,   "
				+"            a.owner_no,   "
				+"            a.a_sorter_chute_no,   "
				+"            a.DIVIDE_NO,   "
				+"            clm.label_no,   "
				+"            a.s_container_no,   "
				+"            a.DELIVER_OBJ,   "
				+"            a.ARTICLE_NO,   "
				+"            a.S_CELL_NO,   "
				+"            a.wave_no,   "
				+"            min(sai.RSV_BATCH1) as RSV_BATCH1,   "
				+"            min(sai.RSV_BATCH2) as RSV_BATCH2,   "
				+"            min(sai.RSV_BATCH3) as RSV_BATCH3,   "
				+"            min(sai.RSV_BATCH4) as RSV_BATCH4,   "
				+"            min(sai.RSV_BATCH5) as RSV_BATCH5,   "
				+"            min(sai.RSV_BATCH6) as RSV_BATCH6,   "
				+"            min(sai.RSV_BATCH7) as RSV_BATCH7,   "
				+"            min(sai.RSV_BATCH8) as RSV_BATCH8,   "
				+"            sai.EXPIRE_DATE,   "
				+"            sai.LOT_NO,   "
				+"            sai.PRODUCE_DATE,   "
				+"            sai.QUALITY,   "
				+"            f_get_fieldtext('N',   "
				+"            'quality',   "
				+"            sai.QUALITY) textQuality,   "
				+"            a.cust_no,   "
				+"            a.packing_qty,   "
				+"            sum(a.ARTICLE_QTY) as divideQty,   "
				+"            sum(a.REAL_QTY) as RealQty,   "
				+"            sum(a.article_qty) / a.packing_qty planWholenum,   "
				+"            sum(a.article_qty) / a.packing_qty realWholenum   "
				+"        FROM   "
				+"            odata_divide_d A    "
				+"        inner join   "
				+"            STOCK_ARTICLE_INFO sai    "
				+"                on a.article_no = sai.article_no    "
				+"                and a.article_id = sai.article_id    "
				+"                and a.enterprise_no = sai.enterprise_no    "
				+"        inner join   "
				+"            stock_label_m clm    "
				+"                on clm.warehouse_no = a.warehouse_no    "
				+"                and clm.enterprise_no = a.enterprise_no    "
				+"                and a.s_container_no = clm.container_no     "
				+"                and clm.label_no='"+strLabelNo+"'    "
				+"        where   "
				+"            1 = 1    "
				+"            and a.warehouse_no = '"+strWarehouseNo+"'    "
				+"            and a.enterprise_no = '"+strEnterpriseNo+"'    "
				+"            and a.status = '10'    "
				+"        group by   "
				+"            a.enterprise_no,a.warehouse_no, a.Deliverobj_Order,  "
				+"            a.ARTICLE_NO,   "
				+"            a.dps_cell_no,   "
				+"            a.BATCH_NO,   "
				+"            a.LINE_NO,   "
				+"            a.owner_no,   "
				+"            a.a_sorter_chute_no,   "
				+"            a.s_container_no,   "
				+"            a.DELIVER_OBJ,   "
				+"            a.DIVIDE_NO,   "
				+"            clm.label_no,   "
				+"            a.cust_no,   "
				+"            a.packing_qty,   "
				+"            a.S_CELL_NO,   "
				+"            a.wave_no,   "
				+"            sai.EXPIRE_DATE,   "
				+"            sai.LOT_NO,   "
				+"            sai.PRODUCE_DATE,   "
				+"            sai.QUALITY ) b    "
				+"    inner join   "
				+"        bdef_defarticle bda    "
				+"            on b.article_no = bda.article_no    "
				+"            and b.owner_no = bda.owner_no    "
				+"            and b.enterprise_no = bda.enterprise_no    "
				+"    inner join   "
				+"        bdef_defcust D    "
				+"            on b.cust_no = d.cust_no    "
				+"            and b.owner_no = d.owner_no    "
				+"            and b.enterprise_no = d.enterprise_no    "
				+"    left join   "
				+"        cdef_defcell_dps dps    "
				+"            on b.dps_cell_no = dps.cell_no    "
				+"            and b.warehouse_no = dps.warehouse_no    "
				+"            and b.enterprise_no = dps.enterprise_no    "
				+"  left join cdef_defcell cdc	"
				+"  on cdc.enterprise_no = dps.enterprise_no "
				+"	and cdc.warehouse_no = dps.warehouse_no "
				+"	and cdc.cell_no = dps.cell_no"
				+"	ORDER BY cdc.stock_no,"
				+" cdc.pick_order, "
				+"       label_no,   "
				+"       nvl(dps.dps_area,0),    ";
				
				if(strDivideType.equals("1"))
				{//1表示按配送对象
					strSql+="   b.Deliverobj_Order,   b.article_no,   b.CUST_NO ";
				}
				else
				{//2表示按商品
					strSql+="   b.article_no,    b.Deliverobj_Order,   b.CUST_NO "	;
				}
				 
		List<DivideDateAnswerModel> list = genDao.getListByNativeSql(strSql, DivideDateAnswerModel.class);
		
		for (int i=0;i<list.size();i++) 
		{					
			map.put(i, list.get(i));
		}
		
		if(map.size()>0)
		{
			msgRes.setIsSucc(true);
			listDivdeMod.setArrStuDivide(map);
			msgRes.setObj(JSONObject.toJSON(listDivdeMod));
		}else
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("没有获取到分播明细数据！");
		}

		return msgRes;
	}

	@Override
	public MsgRes CheckFlag(String strRecvData) throws Exception {
		ReqGetDivideBoxModel reqMod=JSON.parseObject(strRecvData, ReqGetDivideBoxModel.class);
		MsgRes msg=new MsgRes();
		//检验标签状态
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		List  inList = new ArrayList();
		
		outList.add("S");
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());
		inList.add(reqMod.getBoxNo());
		resultList=genDao.execProc(inList, outList, "pkcheck_odata.P_DivideCheckSLabelNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}		
		//读取箱分播明细
		String strSql="select distinct " +
				" ddm.device_group_no as DeviceGroupNo," +
				" cdd.device_no as DeviceNo," +
				" cdd.dps_area as areaNo," +
				" cdd.stock_no as stockNo," +
				" sai.barcode    "+
				" from " +
					" stock_label_m slm," +
					" odata_divide_d odd," +
					" cdef_defcell_dps cdd," +
					" device_divide_m ddm, " +
					" cdef_defcell cd," +
					" stock_article_info sai   "+
				" where " +
				"  slm.warehouse_no=odd.warehouse_no " +
				" and slm.enterprise_no=odd.enterprise_no" +
				"  and slm.container_no=odd.s_container_no   " +
				" and slm.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
				"  and slm.warehouse_no='"+reqMod.getWarehouseNo()+"' "+
				"  and slm.label_no='"+reqMod.getBoxNo()+"'  " +
				" and cdd.enterprise_no=ddm.enterprise_no and cdd.warehouse_no=ddm.warehouse_no and cdd.device_no=ddm.device_no " +
				" and odd.enterprise_no=cdd.enterprise_no "+
				"  and odd.warehouse_no=cdd.warehouse_no   "+
				"  and odd.dps_cell_no=cdd.cell_no   "+
				"  and cdd.warehouse_no=cd.warehouse_no   "+
				"  and cdd.enterprise_no=cd.enterprise_no   "+
				"  and cdd.cell_no=cd.cell_no  " +
				" and odd.enterprise_no=sai.enterprise_no "+
				"  and odd.article_no=sai.article_no   "+
				"  and odd.article_id=sai.article_id   "+
				"  and odd.status='10'   "+
				"  and odd.article_qty-odd.real_qty>0   "+
				" order by cdd.stock_no,cdd.dps_area ";
		List<BoxInfoModel> list=genDao.getListByNativeSql(strSql, BoxInfoModel.class);
		if(list.size()>0)
		{
			//AnsBoxInfoModel ansMod=new AnsBoxInfoModel();
			//ansMod.setlstBarcode(list);
			
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSON(list));
			return msg;
		}else
		{
			msg.setIsSucc(false);
			msg.setMsg("没有读取到箱分播明细！");
			return msg;
		}				
	}

	@Override
	public MsgRes getDivideDetail(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		ReqGetDivideBoxModel reqMod=JSON.parseObject(strRecvData, ReqGetDivideBoxModel.class);
		
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strLabelNo=reqMod.getBoxNo();
		//校验标签号
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		List  inList = new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strLabelNo);
		resultList=genDao.execProc(inList, outList, "pkcheck_odata.P_DivideCheckSLabelNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}		
		//取分播数据		
		String strSql=" select       "+
				" b.owner_no, " +
				" b.dps_cell_no,     "+
				" b.divide_no,      "+
				" b.deliver_obj,      "+
				" b.batch_no,      "+
				" b.cust_no,      "+
				" d.cust_name,      "+
				" b.s_cell_no,      "+
				" b.s_container_no,      "+
				" b.divideQty as qty,      "+
				" b.article_no,      "+
				" bda.BARCODE,      "+
				" bda.article_name,      "+
				//"nvl(bap.spec, '1*' || b.packing_qty || bda.unit) spec," +
				"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
				"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,bda.unit_packing) Unit,"+
				"f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
				"f_get_spec(b.enterprise_no,b.owner_no,b.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(b.enterprise_no,b.owner_no,b.article_no,bda.unit_packing) spec,"+
				" b.lot_no,      "+
				" TO_CHAR(b.produce_date,'yyyyMMdd') as produce_date,      "+
				" TO_CHAR(b.expire_date,'yyyyMMdd') as expire_date,      "+
				" b.quality,      "+
				" b.import_batch_no,      "+
				" b.rsv_batch1,      "+
				" b.rsv_batch2,      "+
				" b.rsv_batch3,      "+
				" b.rsv_batch4,      "+
				" b.rsv_batch5,      "+
				" b.rsv_batch6,      "+
				" b.rsv_batch7,      "+
				" b.rsv_batch8,      "+
				" bda.expiry_days,      "+
				" bda.UNIT,      "+
				" b.packing_qty as packingqty,      "+
				" nvl(bap.pal_base_qbox,999) as palBaseBox,      "+
				" nvl(bap.pal_height_qbox,999) as palHeightBox   ,   "+
				" b.dps_cell_no," +
				" b.address, " +
				" b.a_sorter_chute_no, " +
				" b.source_no," +
				" b.cell_disp_length," +
				" b.cell_start_pos," +
				" b.dps_disp_length," +
				" b.ctrl_no," +
				" bda.QMIN_OPERATE_PACKING," +
				" b.DELIVER_OBJ     "+
				" from      "+
				"     (SELECT  a.enterprise_no,    "+
				"         a.warehouse_no,      "+
				"         a.dps_cell_no,      "+
				"         a.BATCH_NO,      "+
				"         a.LINE_NO,      "+
				"         a.owner_no,      "+
				"         a.a_sorter_chute_no,      "+
				"         a.DIVIDE_NO,      "+
				"         clm.label_no,      "+
				"         a.s_container_no,      "+
				"         a.DELIVER_OBJ,      "+
				"         a.ARTICLE_NO,      "+
				"         a.S_CELL_NO,      "+
				"         a.wave_no,      "+
				"         min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO,      "+
				"         min(sai.RSV_BATCH1) as RSV_BATCH1,      "+
				"         min(sai.RSV_BATCH2) as RSV_BATCH2,      "+
				"         min(sai.RSV_BATCH3) as RSV_BATCH3,      "+
				"         min(sai.RSV_BATCH4) as RSV_BATCH4,      "+
				"         min(sai.RSV_BATCH5) as RSV_BATCH5,      "+
				"         min(sai.RSV_BATCH6) as RSV_BATCH6,      "+
				"         min(sai.RSV_BATCH7) as RSV_BATCH7,      "+
				"         min(sai.RSV_BATCH8) as RSV_BATCH8,      "+
				"         sai.EXPIRE_DATE,      "+
				"         sai.LOT_NO,      "+
				"         sai.PRODUCE_DATE,      "+
				"         sai.QUALITY,      "+
				"         f_get_fieldtext('N',      "+
				"         'quality',      "+
				"         sai.QUALITY) textQuality,      "+
				"         a.cust_no,      "+
				"         a.packing_qty,      "+
				"         sum(a.ARTICLE_QTY) as divideQty,      "+
				"         sum(a.REAL_QTY) as RealQty,      "+
				"         sum(a.article_qty) / a.packing_qty planWholenum,      "+
				"         sum(a.article_qty) / a.packing_qty realWholenum,      "+
				" 		  cdd.address," +
				" 		  a.divide_no source_no," +
				"		  cdd.cell_disp_length," +
				"		  cdd.cell_start_pos," +
				"		  cdd.dps_disp_length," +
				"		  cdd.ctrl_no        "+
				"     FROM      "+
				"         odata_divide_d A       "+
				"     inner join      "+
				"         STOCK_ARTICLE_INFO sai       "+
				"             on a.article_no = sai.article_no       "+
				"             and a.article_id = sai.article_id       " +
				"			  and a.enterprise_no = sai.enterprise_no "+
				"		      and SAI.BARCODE='"+reqMod.getBarcode()+"'	 "+
				"     inner join      "+
				"         stock_label_m clm       "+
				"             on clm.warehouse_no = a.warehouse_no       "+
				"             and a.s_container_no = clm.container_no     " +
				"			  and a.enterprise_no = clm.enterprise_no   "+
				"             and clm.label_no='"+strLabelNo+"'       "+
				"                "+
				"      inner join    "+
				"            cdef_defcell_dps cdd   "+
				"            on clm.warehouse_no=a.warehouse_no   "+
				"            and a.dps_cell_no=cdd.cell_no   " +
				" 			 and a.enterprise_no = cdd.enterprise_no "+
				"            and cdd.dps_area='"+reqMod.getAreaNo()+"'   "+
				"     where      "+
				"         1 = 1       " +
				"		  and a.enterprise_no = '"+ strEnterpriseNo +"'"+
				"         and a.warehouse_no = '"+strWarehouseNo+"'       "+
				"         and a.status = '10'      " +				
				"     group by   a.enterprise_no,   "+
				"         a.warehouse_no,      "+
				"         a.ARTICLE_NO,      "+
				"         a.dps_cell_no,      "+
				"         a.BATCH_NO,      "+
				"         a.LINE_NO,      "+
				"         a.owner_no,      "+
				"         a.a_sorter_chute_no,      "+
				"         a.s_container_no,      "+
				"         a.DELIVER_OBJ,      "+
				"         a.DIVIDE_NO,      "+
				"         clm.label_no,      "+
				"         a.cust_no,      "+
				"         a.packing_qty,      "+
				"         a.S_CELL_NO,      "+
				"         a.wave_no,      "+
				"         sai.EXPIRE_DATE,      "+
				"         sai.LOT_NO,      "+
				"         sai.PRODUCE_DATE,      "+
				"         sai.QUALITY," +
				"		  cdd.address, " +
				"		  a.source_no," +
				"		  cdd.cell_disp_length," +
				"		  cdd.cell_start_pos," +
				"		  cdd.dps_disp_length," +
				"		  cdd.ctrl_no ) b       "+
				" inner join      "+
				"     bdef_defarticle bda       "+
				"         on b.article_no = bda.article_no       "+
				"         and b.owner_no = bda.owner_no     " +
				"		  and b.enterprise_no=bda.enterprise_no  "+
				" inner join      "+
				"     bdef_defcust D       "+
				"         on b.cust_no = d.cust_no       "+
				"         and b.owner_no = d.owner_no       "+
				"         and b.enterprise_no = d.enterprise_no    "+

				" left join      "+
				"     BDEF_ARTICLE_PACKING bap       "+
				"         on bap.ARTICLE_NO = b.ARTICLE_NO       "+
				"         and bap.packing_qty = b.packing_qty       "+
				"         and b.enterprise_no = bap.enterprise_no    "+
				" left join      "+
				"     cdef_defcell_dps dps       "+
				"         on b.dps_cell_no = dps.cell_no       "+
				"         and b.warehouse_no = dps.warehouse_no       "+
				"         and b.enterprise_no = dps.enterprise_no    "+
				" ORDER BY      "+
				"     label_no,      "+
				"     nvl(dps.dps_area,      "+
				"     0),      "+
				"     b.article_no,      "+
				"     b.CUST_NO ";
		List<AnsDivideBoxDetailModel> list = genDao.getListByNativeSql(strSql, AnsDivideBoxDetailModel.class);
		if(list.size()>0){
			AnsGetDivideBoxDetailModel ansMod=new AnsGetDivideBoxDetailModel();
			ansMod.setLstDetail(list);
			
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSON(ansMod));
			return msg;
		}else
		{
			msg.setIsSucc(false);
			msg.setMsg("没有读取到分播物流箱数据！");
			return msg;
		}
	}
	
	@Override
	public MsgRes tscSaveDivideData(String strEnterpriseNo,
			String strWareHouseNo,
			String strDivideNo,
			String strArticleNo,
			String strDpsCellNo,
			String strBarcode,
			Double nRealQty,
			String strDivideName,
			String strCustNo,
			String strLabelNo,
			Integer strAddFlag
			) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add(strDivideNo);//strDivideNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strDpsCellNo);//strDpsCellNo
		inList.add(strBarcode);//strBarcode
		inList.add(nRealQty);//nRealQty
		inList.add(strDivideName);//strDivideName
		inList.add(strCustNo);//strCustNo
		inList.add(strLabelNo);//strLabelNo
		inList.add(strAddFlag);//strAddFlag
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.p_DpsDiveSave");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"保存成功","");
	}

	@Override
	public MsgRes tscCutBox(String strEnterpriseNo,
			String strWarehouseNo,
			String cellNo,
			String userId,
			String paperUserId,
			Integer  operateTools,
			String pntName) throws Exception {		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(cellNo);//strinstockNo
		inList.add(userId);//strUserId
		inList.add(paperUserId);//strPaperUserId
		inList.add(operateTools);//strTools
		inList.add(pntName);//strPrinterGroupNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.P_ODATA_DIVIDE_CUTBOX");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"保存成功","");
	}

	@Override
	public MsgRes tscCancelDivide(String str) throws Exception {
		
		return null;
	}
	/**
	 * 20150129 lich
	 * RF分播回单	 
	 */
	@Override
	public MsgRes tscSaveDivide(String strRecvData) throws Exception {
		OdataDivideCfmRequestModel reqMod=JSON.parseObject(strRecvData, OdataDivideCfmRequestModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());//strWarehouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getDivideNo());//strDivideNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getDestLabelNo());//strDcontainerNo
		inList.add(reqMod.getArticleQty());//预计量
		inList.add(reqMod.getRealDivideQty());//nRealQty
		inList.add(reqMod.getUserID());//strDivideName
		inList.add(reqMod.getBatchNo());//strBatchNo		
		inList.add(StringUtil.isStrEmpty(reqMod.getQuality())?"0":reqMod.getQuality());
		inList.add(DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"));
		inList.add(DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"));
		inList.add(StringUtil.isStrEmpty(reqMod.getLotNo())?"N":reqMod.getLotNo());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());		
		inList.add(reqMod.getScellNo());//strSCellNo
		inList.add(reqMod.getScontainerNo());//strSContainerNo
		inList.add(reqMod.getCustNo());//strCustNo
		inList.add(reqMod.getDeliverObj());//strDelvierObj
		inList.add(reqMod.getUserID());//strUpdtName
		inList.add("2");//strAddFlag
		inList.add("P");//strContainerType
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.p_SaveDivide");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "", resultList.get(0).toString());
	}
	/**
	 * 20150718 wyf
	 * RF分播回单用于电子标签储位	 
	 */
	@Override
	public MsgRes tscSaveDivide_dps(String strRecvData) throws Exception {
		OdataDivideCfmRequestModel reqMod=JSON.parseObject(strRecvData, OdataDivideCfmRequestModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());//strWarehouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getDivideNo());//strDivideNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getDestLabelNo());//strDcontainerNo  ----strDpsCellNo
		inList.add(reqMod.getArticleQty());//预计量
		inList.add(reqMod.getRealDivideQty());//nRealQty
		inList.add(reqMod.getUserID());//strDivideName
		inList.add(reqMod.getBatchNo());//strBatchNo		
		inList.add(StringUtil.isStrEmpty(reqMod.getQuality())?"0":reqMod.getQuality());
		inList.add(DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"));
		inList.add(DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"));
		inList.add(StringUtil.isStrEmpty(reqMod.getLotNo())?"N":reqMod.getLotNo());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());		
		inList.add(reqMod.getScellNo());//strSCellNo
		inList.add(reqMod.getScontainerNo());//strSContainerNo
		inList.add(reqMod.getCustNo());//strCustNo
		inList.add(reqMod.getDeliverObj());//----strDelvierObj
		inList.add(reqMod.getUserID());//strUpdtName
		inList.add(reqMod.getAddFlag());//strAddFlag
		inList.add(reqMod.getContainerType());//strContainerType
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.p_SaveDivide_dps");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "", resultList.get(0).toString());
	}
	
	/**
	 * 读取用户当前区域任务
	 */
	@Override
	public MsgRes GetUserTask(String enterpriseNo, String wareHouseNo,
			String userId, Integer areaNo, Integer stockNo, Integer ctrlNo,
			Integer useType) throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="select slm.label_no from stock_label_m slm, odata_divide_d odd,cdef_defcell_dps cdp " +
				" where slm.enterprise_no = odd.enterprise_no  and slm.warehouse_no = odd.warehouse_no " +
				"   and slm.container_no = odd.s_container_no  and cdp.enterprise_no = odd.enterprise_no " +
				"   and cdp.warehouse_no = odd.warehouse_no  and cdp.cell_no = odd.dps_cell_no " +
				"   and cdp.stock_no = " + stockNo +
				"   and cdp.dps_area = " + areaNo +
				"   and cdp.use_type = " + useType +
				"   and odd.enterprise_no = '"+ enterpriseNo +"'" +
				"   and odd.warehouse_no = '"+ wareHouseNo +"'" +
				"   and odd.status = '10'" +
				"   and exists(select 'x' from odata_divide_d iodd" +
				"     where iodd.enterprise_no = odd.enterprise_no  and iodd.warehouse_no = odd.warehouse_no " +
				"       and iodd.divide_no = odd.divide_no  and iodd.s_container_no = odd.s_container_no " +
				"       and iodd.divide_name='"+ userId +"'" +
				"       and iodd.status = '13')";
		
		List<AnsGetTaskLabel> list = genDao.getListByNativeSql(strSql, AnsGetTaskLabel.class);
		if(list.size()>0){
			
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSON(list));
			return msg;
		}else
		{
			msg.setIsSucc(false);
			msg.setMsg("没有读取到用户当前区域任务数据！");
			return msg;
		}
	}
	
	/**
	 * 读取物流箱已分播用户
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes GetBoxUserList(String enterpriseNo, String wareHouseNo,
			Integer useType, String labelNo) throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="select distinct ood.divide_name as WorkNo from odata_divide_d ood,stock_label_m olm "+
			"where ood.enterprise_no = olm.enterprise_no "+
			 " and ood.warehouse_no = olm.warehouse_no "+
			 " and ood.s_container_no = olm.container_no "+
			 " and ood.divide_name is not null  "+
			 " and olm.enterprise_no = '"+enterpriseNo+"' "+
			 " and olm.warehouse_no = '"+wareHouseNo+"' "+
			 " and olm.label_no = '"+labelNo+"' ";
		
		List<StuAnsBoxUserListModel> list = genDao.getListByNativeSql(strSql, StuAnsBoxUserListModel.class);
		if(list.size()>0){
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSON(list));
			return msg;
		}else
		{
			msg.setIsSucc(false);
			msg.setMsg("没有读取到流箱已分播用户！");
			return msg;
		}
	}

	/**
	 * 换线
	 */
	@Override
	public MsgRes ChangeGroup(String strRecvData) throws Exception {
		
		ReqGetDivideBoxModel reqMod=JSON.parseObject(strRecvData, ReqGetDivideBoxModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());//strWarehouseNo
		inList.add(reqMod.getBoxNo());//labelNo
		inList.add(reqMod.getDeviceGroupNo());//groupNo
		
		System.out.println(inList);
			
		resultList=genDao.execProc(inList, outList, "PKLG_ALLOT_DIVIDE_DEVICE.P_B_CHG_DIVIDE_DEVICEGRP");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		else
		{
			return CheckFlag(strRecvData);
		}
				
		//return new MsgRes(true, "", resultList.get(0).toString());
	}
	//取商条码
	@Override
	public List<ComboxBo> getBarcodeCombo(String enterpriseNo,
			String workerOwner, String warehouseNo, String str, String strWheresql)
			throws Exception {
		String strSql="select distinct c.barcode value,c.barcode text,"+
					"c.barcode dropValue "+
					" from odata_divide_m a,odata_divide_d odd,bdef_defarticle c       "+
					" where a.enterprise_no=odd.enterprise_no " +
					" and a.owner_no=odd.owner_no " +
					" and a.divide_no=odd.divide_no " +
					" and odd.enterprise_no=c.enterprise_no " +
					" and odd.owner_no=c.owner_no " +
					" and odd.article_no=c.article_no " +
					" and a.enterprise_no ='"+enterpriseNo+
					"' and a.warehouse_no ='"+warehouseNo+
					"' and a.STATUS NOT IN ('13', '16') ";

			if(workerOwner!=null && !workerOwner.equals(""))
			{
				strSql=strSql+" and odd.owner_no in("+workerOwner+") ";
			}
				
			if (str != null && !str.equals("")) {
				String ws = CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql + ws;
			}
			if (strWheresql != null && !strWheresql.equals("")) {
				strSql = strSql + " and  c.barcode like '%" + strWheresql + "%' " ;
			}
			strSql=strSql+" order by c.barcode ";
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 1000);
			return (List<ComboxBo>) list;
	}
	
	
	/**
	 * 20150718 sl
	 * RF分播取号(前台分播回单取号公用)
	 */
	@Override
	public MsgRes tscDivideGetNO(String strRecvData) throws Exception {
		DivideGetNOModel reqMod=JSON.parseObject(strRecvData, DivideGetNOModel.class);
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();

		outList.add("S");
		outList.add("S");
	 
		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(reqMod.getWareHouseNo());//strWareHouseNo
		inList.add(reqMod.getDivideNO());//strDivideNo
		inList.add(reqMod.getLableNO());//strsLabelNo
		inList.add(reqMod.getCustNo());//strCustNo  //---这里的客户需要传配送对象
		inList.add(reqMod.getArticleNO());//strArticleNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getMType());//strContainerType
		inList.add(reqMod.getWorkerNO());//strUserId
		
		System.out.println(inList.get(0));
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.GetCustLabelAndPrint");
		//System.out.println(resultList.get(0).toString());
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true, "打印成功", resultList.get(0).toString());
	}
    
}
