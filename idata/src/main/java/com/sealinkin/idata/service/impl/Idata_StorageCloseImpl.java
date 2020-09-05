package com.sealinkin.idata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_CheckDModel;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.model.Idata_ImportSdModel;
import com.sealinkin.idata.service.Iidata_StorageCloseService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Idata_StorageCloseImpl implements Iidata_StorageCloseService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//查找储位下拉
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String warehouseNo, String strJson, 
			String str, int i, int j) throws Exception {
		 
		String sql = "select a.cell_no value,a.cell_no text,a.cell_no dropValue  "
				+ "from Cdef_Defcell a ,Cdef_Defarea b "
				+ " where a.cell_status<>1 "
				+ " and a.ware_no = b.ware_no "
				+ " and a.area_no=b.area_no "
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.warehouse_no=b.warehouse_no "
				+ " and a.enterprise_no=b.enterprise_no "
				+ " and b.area_attribute=0 "
				+ " and b.area_usetype in ('1','5','6') ";
				
		if (strJson != null && !strJson.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strJson);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + "and a.cell_no like '%" + str + "%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	
	}
	
	//获取进货汇总单号
	//update by czh 2016.08.08  增加单据类别筛选
	@Override
	public List<Idata_ImportSdModel> queryIdataImportMMCombo(
			String enterpriseNo,String warehouseNo, String strOwnerNo, 
			String strFlag,String strWheresql,String strQuery) throws Exception {
		String strSql="";
		strSql="select a.warehouse_no, a.import_type, a.s_import_no, a.owner_no, a.dept_no, " +
				"a.po_type,a.supplier_no, a.status, a.serial_no, a.stock_type, a.stock_value, " +
				"a.printer_name,a.printer_date, a.rgst_name, a.rgst_date, a.updt_name, a.updt_date " +
				"from idata_import_mm a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				  "and a.WAREHOUSE_NO='"+warehouseNo+"' " +
			      "and a.status not in(13,16) " +
				  "and a.class_type='"+strFlag+"' ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and a.s_import_no like '%"+strWheresql+"%'";
		}
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql + ws;
		}
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in('"+strOwnerNo+"')";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list=genDao.getListByNativeSql(strSql,Idata_ImportSdModel.class, 0, 10);
		return (List<Idata_ImportSdModel>) list;
	}
	
	//获取货主
	@Override
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String workerOwner) throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.owner_no in(select owner_no from idata_import_mm) " +
				"and t1.owner_no in ("+workerOwner+") " +
				"and t1.enterprise_no='"+enterpriseNo+"' ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	//获取指定储位验收明细
	@Override
	public ExtListDataBo<Idata_ImportSdModel> queryIdataImportSd(
			String enterpriseNo,String warehouseNo, 
			String workerOwner, String strWheresql)
			throws Exception {	
		String strSql="select a.warehouse_no,a.owner_no,a.s_import_no,a.row_id,a.article_no," +
				"a.packing_qty,a.po_qty,a.import_qty,a.qc_qty,a.check_name,a.status,a.check_date," +
				"a.qc_status,a.out_stock_flag,a.outpace_article_flag,a.item_type,a.plan_across_qty," +
				"a.check_across_qty,a.qc_flag," +
				"(a.po_qty-a.import_qty) as inQty," +
				"(a.po_qty-a.import_qty) as checkQty," +
				"(a.PO_QTY*b.check_excess*0.01+a.po_qty-a.IMPORT_QTY) as noCheckQty," +
				"trunc((a.po_qty-a.import_qty)/a.packing_qty) as planBox," +
				"trunc(mod((a.po_qty-a.import_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,  " +
				"((a.po_qty-a.import_qty) - trunc((a.po_qty-a.import_qty)/a.packing_qty) * a.packing_qty - trunc(mod((a.po_qty-a.import_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis," +
				"b.QMIN_OPERATE_PACKING,b.unit_packing," +
				
				"case when c.class_type='1' then to_date('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and (b.lot_type=1 or b.lot_type=4) then to_date ('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and a.po_qty-a.import_qty=0 then to_date ('1900-01-01','yyyy-mm-dd')	 "+			
                "when c.class_type='0' and (b.lot_type=2 or b.lot_type=3)  and (a.po_qty-a.import_qty<>0) then null " +
                "else null " +
				"end produceDate," +
				"case when c.class_type='1' then to_date('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and (b.lot_type=1 or b.lot_type=4) then to_date ('1900-01-01', 'yyyy-mm-dd') " +
				"when c.class_type='0' and a.po_qty-a.import_qty=0 then to_date ('1900-01-01','yyyy-mm-dd')	 "+			
				"when c.class_type='0' and (b.lot_type=2 or b.lot_type=3) and (a.po_qty-a.import_qty<>0) then null " +
				"else null " +
				"end expireDate," +
				"case when g.ware_no is null then 'N' else g.ware_no||g.area_no  end wareArea," +
				"case when c.class_type='1' then 'N' " +
				"when c.class_type='0' and  (b.lot_type=2 or b.lot_type=4) then 'N'" +	
				"else null " +
				"end lotNo,b.expiry_days,b.article_name, b.owner_article_no as ownerArticleNo,b.barcode," +
				//"nvl(d.spec, '1*' || a.packing_qty || b.unit) spec,"+
				//"nvl(d.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"
				 + "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"
				 + "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"
				 + "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"
				 + "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"
				 + "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec," + 
				"b.alarmrate,b.freezerate,b.check_excess,nvl(d.qpalette,999) as qpalette," +
				"g.pick_type as boxPickType, " +
				"h.pick_type as disPickType, " +
				"b.lot_type as lotType "+
				"from idata_import_sd a,bdef_defarticle b,idata_import_mm c," +
				"bdef_article_packing d," +
				"(select article_no,ware_no,area_no,pick_type from cset_cell_article " +
				        "where warehouse_no='"+warehouseNo+"' " +
				          "and enterprise_no='"+enterpriseNo+"' " +
						  "and pick_type='B') g," +
				"(select article_no,ware_no,area_no,pick_type from cset_cell_article " +
				        "where warehouse_no='"+warehouseNo+"' " +
				          "and enterprise_no='"+enterpriseNo+"' " +
						  "and pick_type='C') h " +
				"where a.enterprise_no=b.enterprise_no and a.owner_no=b.owner_no and a.article_no=b.article_no " +
				"and a.enterprise_no=c.enterprise_no and a.s_import_no=c.s_import_no and a.warehouse_no=c.warehouse_no " +
				"and a.enterprise_no=d.enterprise_no(+) and a.article_no=d.article_no(+) and a.packing_qty=d.packing_qty(+) " +
				"and a.article_no=g.article_no(+) and a.article_no=h.article_no(+) " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
			strSql=strSql+ft;
		}
		strSql+="order by a.article_no";
		List<Idata_ImportSdModel> list = genDao.getListByNativeSql(strSql,Idata_ImportSdModel.class,0,10000);
		ExtListDataBo<Idata_ImportSdModel> extListBo=new ExtListDataBo<Idata_ImportSdModel>(list,0);
		return extListBo;
	}
	@Override
	public MsgRes tscSaveCheck(String strJsonMaster, String strJsonDetail1,String cellNo)
			throws Exception {
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
		List<Idata_CheckDModel> list=JSON.parseArray(strJsonDetail1,Idata_CheckDModel.class);
		
		List saveCheckDataISResult=new ArrayList();//普通保存验收数据返回结果
		
		for(int i=0;i<list.size();i++){
			saveCheckDataISResult=
					tscSaveCheckDataIS(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),poMaster.getSImportNo(),list.get(i).getArticleNo(),
					list.get(i).getBarcode(),list.get(i).getPackingQty(),list.get(i).getCheckQty(),"N",poMaster.getDockNo(),
					poMaster.getCheckWorker(),poMaster.getCheckTools(),i==0 ? 0:1,list.get(i).getQuality(),
					list.get(i).getProduceDate(),list.get(i).getExpireDate(),list.get(i).getLotNo(),
					list.get(i).getRsvBatch1(),list.get(i).getRsvBatch2(),list.get(i).getRsvBatch3(),list.get(i).getRsvBatch4(),
					list.get(i).getRsvBatch5(),list.get(i).getRsvBatch6(),list.get(i).getRsvBatch7(),list.get(i).getRsvBatch8(),
					list.get(i).getTemperature(),"N","N","2","0");
			if(saveCheckDataISResult.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(saveCheckDataISResult.get(1).toString());
			}
		}
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		outList.add("S");
		
//		poMaster.getSCheckNo()
		inList.add(poMaster.getEnterpriseNo());
		inList.add(poMaster.getWarehouseNo());
		inList.add(poMaster.getOwnerNo());
		inList.add(poMaster.getSImportNo());
		inList.add(saveCheckDataISResult.get(0).toString());
		inList.add(cellNo);
		inList.add(poMaster.getDockNo());
		inList.add(poMaster.getCheckWorker());
	
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_checkDirectCell");
		System.out.println(inList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		} 
		
		
		return new MsgRes(true, "保存成功","");//保存成功
	}
	
	/**
	 * 普通保存验收数据
	 */
	public List tscSaveCheckDataIS(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strsImportNo,
			String strArticleNo,
			String strBarcode,
			Double nPackingQty,
			Double nCheckQty,
			String strPrinterGroupNo,
			String strDockNo,
			String strWorkerNo,
			String strCheckTools,
			Integer nIsAdd,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRSV_BATCH1,
			String strRSV_BATCH2,
			String strRSV_BATCH3,
			String strRSV_BATCH4,
			String strRSV_BATCH5,
			String strRSV_BATCH6,
			String strRSV_BATCH7,
			String strRSV_BATCH8,
			String strTemperature,
			String strLabelNo,
			String strSubLabelNo,
			String strFixPalFlag,
			String strBusinessType
			)throws Exception
	{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strsImportNo);//strsImportNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strBarcode);//strBarcode
		inList.add(nPackingQty);//nPackingQty
		inList.add(nCheckQty);//nCheckQty
		inList.add(strPrinterGroupNo);//strPrinterGroupNo
		inList.add(strDockNo);//strDockNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strCheckTools);//strCheckTools
		inList.add(nIsAdd);//nIsAdd
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH1)?"N":strRSV_BATCH1);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH2)?"N":strRSV_BATCH2);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH3)?"N":strRSV_BATCH3);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH4)?"N":strRSV_BATCH4);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH5)?"N":strRSV_BATCH5);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH6)?"N":strRSV_BATCH6);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH7)?"N":strRSV_BATCH7);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH8)?"N":strRSV_BATCH8);		
		inList.add(strTemperature==null?"N":strTemperature);//温度
		inList.add(strLabelNo);//strLabelNo
		inList.add(strSubLabelNo);//strSupLabelNo
		inList.add(strFixPalFlag);//strFixPalFlag
		inList.add(strBusinessType);//strBusinessType
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_SaveCheckDataIS");
		System.out.println(inList);
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		return resultList;
	}
	@Override
	public List<String> queryLotProduceDate(String enterpriseNo,String warehouseNo, 
			String date, String sImportNo,String articleNo) throws Exception {
		String strSql="select    "+
				"distinct   "+
				"    lot_no               "+
				"from   "+
				"    bdef_article_lot_manage    "+
				"where   warehouse_no='"+warehouseNo+"' " +
				"    and enterprise_no='"+enterpriseNo+"' "+
				"    and article_no='"+articleNo+"'    "+
				"    and to_char(produce_date,'yyyy-mm-dd')='"+date+"'   "+
				"union    "+
				"select distinct    "+
				"	iil.lot_no   "+
				"from    "+
				"	idata_import_sm ism,   "+
				"	Idata_import_Lot iil   "+
				"where ism.enterprise_no=iil.enterprise_no   " +
				"  and ism.warehouse_no=iil.warehouse_no" +
				"  and ism.owner_no=iil.owner_no "+
				"  and ism.import_no=iil.import_no   " +
				"  and ism.enterprise_no='"+enterpriseNo+"' " +
				"  and ism.warehouse_no='"+warehouseNo+"' "+
				"  and iil.article_no='"+articleNo+"'   "+
				"  and ism.s_import_no='"+sImportNo+"'";	
		
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()==0){
			return null;
		}
		
		return (List<String>) list;
	}
}
