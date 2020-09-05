package com.sealinkin.fcdata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_ArticleInfoModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.fcdata.model.Fcdata_CheckDModel;
import com.sealinkin.fcdata.model.Fcdata_CheckMModel;
import com.sealinkin.fcdata.po.Fcdata_CheckD;
import com.sealinkin.fcdata.service.IFcdata_RequestService;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.fcdata.AnsDpsCellLabel;
import com.sealinkin.protocolExchange.fcdata.AnsFCScanSerialModel;
import com.sealinkin.protocolExchange.fcdata.AnsGetDpsCellInfo;
import com.sealinkin.protocolExchange.fcdata.AnsInPutBarcodeModel;
import com.sealinkin.protocolExchange.fcdata.AnsPackingModel;
import com.sealinkin.protocolExchange.fcdata.AnsStockContentInfoModel;
import com.sealinkin.protocolExchange.fcdata.ArticleInfoModel;
import com.sealinkin.protocolExchange.fcdata.FCParameterRequestModel;
import com.sealinkin.protocolExchange.fcdata.ReqFCScanBarcodeModel;
import com.sealinkin.protocolExchange.fcdata.ReqGetDpsCellNo;
import com.sealinkin.protocolExchange.fcdata.ReqStockContentQueryModel;
import com.sealinkin.util.TipUtil;

/**
 * 盘点回单实现
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Fcdata_RequestImpl implements IFcdata_RequestService
{
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
	
    ////////////////////////////////////////初盘回单///////////////////////////////////////////////////
	
	//获取初盘回单头档	 
	@Override
	public ExtListDataBo<Fcdata_CheckMModel> queryFcdataCheckM(
			String enterpriseNo,String strWarehouseNo, String strOwnerNo, String strFcdataType,
			PageBo poPageBo) throws Exception 
	{
		String strSql="select distinct c.ware_no||c.area_no||c.stock_no as ware," +
				"a.enterprise_no,a.warehouse_no,a.owner_no,a.plan_no,a.request_no,a.check_no,a.check_date," +
				"a.request_date,a.assign_no,a.real_no,a.status,a.check_remark,a.serial_no,a.fcdata_type," +
				"a.check_type,a.rgst_name,a.rgst_date,a.updt_name,a.updt_date," +
				"f_get_fieldtext('N','STATUS',a.status)statusText," +
				"f_get_fieldtext('N','CHECK_TYPE',a.check_type)checktypeText," +
				"f_get_fieldtext('N','FCDATA_TYPE',a.fcdata_type)fcdatatypeText " +
				"from fcdata_check_m a,fcdata_check_d b,cdef_defcell c " +
				"where a.check_type='1' and a.status=10 and b.check_no=a.check_no " +
				"and a.warehouse_no='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+
				"' and a.fcdata_type='"+strFcdataType+"' "+
				"  and a.enterprise_no=b.enterprise_no "+
				"  and a.warehouse_no=b.warehouse_no "+
				"  and b.enterprise_no=c.enterprise_no "+
				"  and b.warehouse_no=c.warehouse_no " +
				" and b.cell_no=c.cell_no ";
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{//取有权限的货主和为N的货主
			strSql=strSql+" and a.owner_no in("+strOwnerNo+",'N') ";
		}else{
			strSql=strSql+" and 1=2";
		}
		strSql=strSql+" group by b.check_no,a.enterprise_no,a.warehouse_no,a.owner_no,a.plan_no,a.request_no," +
				"a.check_no,a.check_date,a.request_date,a.assign_no,a.real_no,a.status,a.check_remark," +
				"a.serial_no,a.fcdata_type,a.check_type,a.rgst_name,a.rgst_date,a.updt_name,a.updt_date," +
				"c.ware_no||c.area_no||c.stock_no";
		strSql=strSql+" order by a.check_no desc ";
		String strTotsql="select count(1) from ("+strSql+")";
        List<Fcdata_CheckMModel> list = genDao.getListByNativeSql(
        		strSql,Fcdata_CheckMModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Fcdata_CheckMModel> extListBo= new ExtListDataBo<Fcdata_CheckMModel>(list, count);
        return extListBo;
	}
	
	//盘点回单--新增品项--根据货位找货主
	@Override
	public MsgRes queryOwnerCellNo(String enterpriseNo, String strWarehouseNo,
			String strWheresql) throws Exception {
        String sql = "select t.owner_no " +
        		"from cset_owner_cell t where " +
        		"t.enterprise_no='"+enterpriseNo+"' " +
        		"and t.warehouse_no='"+strWarehouseNo+"' " +
        		"and t.cell_no='"+strWheresql+"' ";
        List<String> s = genDao.getListByNativeSql(sql);
        if(s.size()>0){
        	return new MsgRes(true,"",s.get(0));
        }else{
        	return new MsgRes(true,"","");
        }
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////复盘、三盘/////////////////////////////////////////////////////////
    // 获取复盘/三盘回单头档	
	@Override
	public ExtListDataBo<Fcdata_CheckMModel> querySecondFcdataCheckM(
			String enterpriseNo,String strWarehouseNo, String strOwnerNo, String strCheckType,
			PageBo poPageBo) throws Exception {
		
		String strSql="select distinct c.ware_no||c.area_no||c.stock_no as ware," +
				"a.warehouse_no,a.owner_no,a.plan_no,a.request_no,a.check_no,a.check_date," +
				"a.request_date,a.assign_no,a.real_no,a.status,a.check_remark,a.serial_no,a.fcdata_type," +
				"a.check_type,a.rgst_name,a.rgst_date,a.updt_name,a.updt_date," +
				"f_get_fieldtext('N','STATUS',a.status)statusText," +
				"f_get_fieldtext('FCDATA_CHECK_M','CHECK_TYPE',a.check_type)checktypeText," +
				"f_get_fieldtext('N','FCDATA_TYPE',a.fcdata_type)fcdatatypeText " +
				"from fcdata_check_m a,fcdata_check_d b,cdef_defcell c " +
				"where a.check_type='"+strCheckType+"' and a.status='10' " +
				" and b.enterprise_no=a.enterprise_no "+
				" and b.warehouse_no=a.warehouse_no  "+
				" and b.check_no=a.check_no " +
				" and a.warehouse_no='"+strWarehouseNo+"' " +
				" and a.enterprise_no='"+enterpriseNo+"' "+
				" and b.enterprise_no=c.enterprise_no "+
				" and b.warehouse_no=c.warehouse_no " +
				" and b.cell_no=c.cell_no ";
		
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+",'N') ";
		}else{
			strSql=strSql+" and 1=2";
		}
		strSql=strSql+" group by b.check_no,a.warehouse_no,a.owner_no,a.plan_no,a.request_no," +
				"a.check_no,a.check_date,a.request_date,a.assign_no,a.real_no,a.status,a.check_remark," +
				"a.serial_no,a.fcdata_type,a.check_type,a.rgst_name,a.rgst_date,a.updt_name,a.updt_date," +
				"c.ware_no||c.area_no||c.stock_no";
		strSql=strSql+" order by a.check_no desc ";
		String strTotsql="select count(1) from ("+strSql+")";
        List<Fcdata_CheckMModel> list = genDao.getListByNativeSql(
        		strSql,Fcdata_CheckMModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Fcdata_CheckMModel> extListBo= new ExtListDataBo<Fcdata_CheckMModel>(list, count);
        return extListBo;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////回单公用///////////////////////////////////////////////////////
	//回单明细
	@Override
	public ExtListDataBo<Fcdata_CheckDModel> queryFcdataCheckD(
			String enterpriseNo,String strWarehouseNo, String strOwnerNo, String strWheresql)
			throws Exception {
		String strSql="select fcd.owner_no,fcd.cell_no,fcd.article_no,bda.article_name,bda.owner_article_no as ownerArticleNo,fcd.barcode,bda.qmin_operate_packing,fcd.packing_qty," +
				"f_get_packingunit(fcd.enterprise_no,fcd.owner_no,fcd.article_no,fcd.packing_qty) packingUnit," +
				"f_get_packingunit(fcd.enterprise_no,fcd.owner_no,fcd.article_no,bda.qmin_operate_packing) packingUnitQmin," +
				"f_get_packingunit(fcd.enterprise_no,fcd.owner_no,fcd.article_no,bda.unit_packing) Unit," +
				"f_get_spec(fcd.enterprise_no,fcd.owner_no,fcd.article_no,fcd.packing_qty) packingSpec," +
				"f_get_spec(fcd.enterprise_no,fcd.owner_no,fcd.article_no,bda.qmin_operate_packing) packingSpecQmin," +
				"f_get_spec(fcd.enterprise_no,fcd.owner_no,fcd.article_no,bda.unit_packing) spec," +
				"fcd.produce_date,fcd.expire_date,add_flag,order_id,sub_order_id," +
				"fcd.article_qty,fcd.label_no,fcd.sub_label_no,fcd.stock_type,fcd.stock_value,fcd.lot_no,fcd.row_id," +
				"fcd.recheck_qty,fcd.quality," +
				"f_get_fieldtext('N','QUALITY',fcd.quality) as qualityText," +
				
				"fcd.recheck_qty," +
				"case when fcd.article_no='N' then 0 else trunc((fcd.recheck_qty)/fcd.PACKING_QTY) end rePlanBox," +
				"case when fcd.article_no='N' then 0 else trunc(mod(fcd.recheck_qty,fcd.PACKING_QTY)/bda.qmin_operate_packing) end rePlanQmin," +
				"case when fcd.article_no='N' then 0 else (fcd.recheck_qty - trunc(fcd.recheck_qty/fcd.PACKING_QTY) * fcd.PACKING_QTY - trunc(mod(fcd.recheck_qty,fcd.PACKING_QTY)/bda.qmin_operate_packing) * BDA.QMIN_OPERATE_PACKING) end rePlanDis," +
				"fcd.third_qty," +
				"case when fcd.article_no='N' then 0 else trunc((fcd.third_qty)/fcd.PACKING_QTY) end thirdPlanBox," +
				"case when fcd.article_no='N' then 0 else trunc(mod(fcd.third_qty,fcd.PACKING_QTY)/bda.qmin_operate_packing) end thirdPlanqQmin," +
				"case when fcd.article_no='N' then 0 else (fcd.third_qty - trunc(fcd.third_qty/fcd.PACKING_QTY) * fcd.PACKING_QTY - trunc(mod(fcd.third_qty,fcd.PACKING_QTY)/bda.qmin_operate_packing) * BDA.QMIN_OPERATE_PACKING) end thirdPlanDis,"+
				"fcd.check_qty," +
				"case when fcd.article_no='N' then 0 else trunc((fcd.check_qty)/fcd.PACKING_QTY) end planBox," +
				"case when fcd.article_no='N' then 0 else trunc(mod(fcd.check_qty,fcd.PACKING_QTY)/bda.qmin_operate_packing) end planQmin," +
				"case when fcd.article_no='N' then 0 else (fcd.check_qty - trunc(fcd.check_qty/fcd.PACKING_QTY) * fcd.PACKING_QTY - trunc(mod(fcd.check_qty,fcd.PACKING_QTY)/bda.qmin_operate_packing) * BDA.QMIN_OPERATE_PACKING) end planDis,"+
			
				"fcd.rsv_batch1, fcd.rsv_batch2, fcd.rsv_batch3, fcd.rsv_batch4," +
				"fcd.rsv_batch5, fcd.rsv_batch6, fcd.rsv_batch7, fcd.rsv_batch8 " +
				"from fcdata_check_d fcd,bdef_defarticle bda,bdef_article_packing bap " +
				"where fcd.enterprise_no='"+enterpriseNo+"' "+
				"and fcd.warehouse_no='"+strWarehouseNo+"' "+
				"and fcd.enterprise_no=bda.enterprise_no(+) "+
				"and fcd.article_no=bda.article_no(+) " +
				"and fcd.enterprise_no=bap.enterprise_no(+) "+
				"and fcd.article_no=bap.article_no(+) " +
				"and fcd.packing_qty=bap.packing_qty(+) " +
				"and fcd.status='10' " +
				"and check_no='"+strWheresql+"' ";
		strSql+="order by fcd.order_id,fcd.label_no,fcd.sub_order_id,fcd.produce_date,fcd.lot_no ";
		List<Fcdata_CheckDModel> list = genDao.getListByNativeSql(
        		strSql,Fcdata_CheckDModel.class);
		ExtListDataBo<Fcdata_CheckDModel> extListBo= new ExtListDataBo<Fcdata_CheckDModel>(list, 0);
        return extListBo;
	}
	
	// 盘点回单》保存
	@Override
	public MsgRes tscSaveFcdataCheckD(String strDetail) throws Exception {
		List<Fcdata_CheckD> fcd=JSON.parseArray(strDetail,Fcdata_CheckD.class);
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		for(Fcdata_CheckD f :fcd)
		{
		saveFcdateD(f.getId().getEnterpriseNo(),f.getId().getCheckNo(),
			f.getCheckType(), f.getCheckQty(),
			 f.getCellNo(), f.getOwnerNo(), 
			 f.getArticleNo(),  f.getExpireDate(),
			 f.getLotNo(), f.getProduceDate(), f.getQuality(), f.getRsvBatch1(),
			 f.getRsvBatch2(), f.getRsvBatch3(), f.getRsvBatch4(),
			 f.getRsvBatch5(), f.getRsvBatch6(), f.getRsvBatch7(),
			 f.getRsvBatch8(),  f.getId().getWarehouseNo(),
			 f.getPackingQty(),f.getCheckWorker(),f.getLabelNo(),
			 f.getSubLabelNo(),f.getStockType(),f.getStockValue(),f.getAddFlag());
		}
		String sql = "select d.row_id " +
				"from fcdata_check_d d where " +
				"d.enterprise_no='"+fcd.get(0).getId().getEnterpriseNo()+"' " +
				"and d.warehouse_no='"+fcd.get(0).getId().getWarehouseNo()+"' " +
				"and d.check_no='"+fcd.get(0).getId().getCheckNo()+"' " +
				"and d.article_no='"+fcd.get(0).getArticleNo()+"' " +
				"and d.packing_qty='"+fcd.get(0).getPackingQty()+"' " +
				"and d.cell_no='"+fcd.get(0).getCellNo()+"' " +
				"and to_char(d.produce_date,'yyyy-mm-dd')='"+dateFormater.format(fcd.get(0).getProduceDate())+"' " +
				"and to_char(d.expire_date,'yyyy-mm-dd')='"+dateFormater.format(fcd.get(0).getExpireDate())+"' " +
				"and d.quality='"+fcd.get(0).getQuality()+"' " +
				"and d.lot_no='"+fcd.get(0).getLotNo()+"'   " +
				"and d.check_type='"+fcd.get(0).getCheckType()+"' " +
				"and d.rsv_batch1='"+fcd.get(0).getRsvBatch1()+"' " +
                "and d.rsv_batch2='"+fcd.get(0).getRsvBatch2()+"' " +
                "and d.rsv_batch3='"+fcd.get(0).getRsvBatch3()+"' " +
                "and d.rsv_batch4='"+fcd.get(0).getRsvBatch4()+"' " +
                "and d.rsv_batch5='"+fcd.get(0).getRsvBatch5()+"' " +
                "and d.rsv_batch6='"+fcd.get(0).getRsvBatch6()+"' " +
                "and d.rsv_batch7='"+fcd.get(0).getRsvBatch7()+"' " +
                "and d.rsv_batch8='"+fcd.get(0).getRsvBatch8()+"' ";
		List<String> list = genDao.getListByNativeSql(sql); 
		if(list.size()>0){
			return new MsgRes(true,TipUtil.getTipsByKey("E30404"),list.get(0));//保存成功
		}else{
			return new MsgRes(true,"保存成功，但是获取rowid失败！","");
		}
	}
	
	
	//盘点新增保存	
	public MsgRes saveFcdateD(String enterpriseNo,String checkNo, String checkType, Double checkQty,
			String cellNo, String ownerNo,  
			String articleNo, Date expireDate,
			String lotNo, Date produceDate, String quality, String rsvBatch1,
			String rsvBatch2, String rsvBatch3, String rsvBatch4,
			String rsvBatch5, String rsvBatch6, String rsvBatch7,
			String rsvBatch8, String warehouseNo,
			Double packQty,String checkWorker,String labelNo,
			String subLabelNo,String stockType,String stockValue,String AddFlag)throws Exception{
		MsgRes msgRes=new MsgRes();
		List  inList = new ArrayList();
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		outList.add("S");
			
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(checkType);
		inList.add(checkWorker);//strUserId
		inList.add(checkNo);//strCheckNo
		inList.add(cellNo);//strCellNo
		inList.add(articleNo);//strArticleNo
		inList.add(packQty);//nPackingQty
		inList.add(produceDate);//dtProduceDate
		inList.add(expireDate);//dtExpireDate
		inList.add((quality == null || quality.trim().equals("")) ? "0":quality);//strQuality
		inList.add((lotNo == null || lotNo.trim().equals("")) ? "N":lotNo);//strLotNo
		inList.add(checkQty);//nCheckQty
		inList.add((labelNo == null || labelNo.trim().equals("")) ? "N":labelNo);//strLabelNo
		inList.add((subLabelNo == null || subLabelNo.trim().equals("")) ? "N":subLabelNo);//strSubLabelNo
		inList.add((stockType == null || stockType.trim().equals("")) ? "1":stockType);//strStockType
		inList.add((stockValue == null || stockValue.trim().equals("")) ? "N":stockValue);//strStockValue
		inList.add((rsvBatch1 == null || rsvBatch1.trim().equals("")) ? "N":rsvBatch1);
		inList.add((rsvBatch2 == null || rsvBatch2.trim().equals("")) ? "N":rsvBatch2);
		inList.add((rsvBatch3 == null || rsvBatch3.trim().equals("")) ? "N":rsvBatch3);
		inList.add((rsvBatch4 == null || rsvBatch4.trim().equals("")) ? "N":rsvBatch4);
		inList.add((rsvBatch5 == null || rsvBatch5.trim().equals("")) ? "N":rsvBatch5);
		inList.add((rsvBatch6 == null || rsvBatch6.trim().equals("")) ? "N":rsvBatch6);
		inList.add((rsvBatch7 == null || rsvBatch7.trim().equals("")) ? "N":rsvBatch7);
		inList.add((rsvBatch8 == null || rsvBatch8.trim().equals("")) ? "N":rsvBatch8);
		inList.add(AddFlag);//1：按总量盘点覆盖数量 ；2：累加数量盘点
		
		System.out.println(inList);	
		resultList=genDao.execProc(inList, outList, "PKOBJ_FCDATA.P_Fcdata_SaveCheck");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(fcDataGetCheckQty(enterpriseNo,warehouseNo,checkType,
 				cellNo,labelNo,checkNo,ownerNo).getMsg());
		return msgRes;
	}
	
	// 根据单号填充储位
	@Override
	public List<ComboxBo> queryCdefDefCellCombo(String enterpriseNo,String strWarehouseNo,
			String strFlag,String strCheckNo, String strWheresql) {
		String strSql="select distinct a.cell_no value,a.cell_no text,a.cell_no dropValue  " +
				"from fcdata_check_d a,fcdata_check_m b " +
				"where a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no "+
				"and a.check_no=b.check_no " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_no='"+strWarehouseNo+"'"+
				"and a.check_no='"+strCheckNo+"' " +
				"and b.check_type in("+strFlag+")" ;		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql=strSql+"and a.cell_no like '%"+strWheresql+"%'";
		}
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class);
		return  (List<ComboxBo>)list;
	}
	
	//商品下拉选择 
	@Override
	public List<Fcdata_CheckDModel> queryArticleInfo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)
			throws Exception {
		String strSql="select a.owner_no,a.article_no,a.article_name,a.qmin_operate_packing,a.barcode,a.spec,a.UNIT packingUnit,a.expiry_days guarantee,a.lot_type lotType," +
				"to_char(wmsys.wm_concat(c.field_id)) as field " +
				"from bdef_defarticle a,bset_article_batch_d c " +
				" where a.batch_id=c.batch_id " +
				" and a.article_no='"+strWheresql+"' " +
				" and a.enterprise_no='"+enterpriseNo+"' "+
				" group by a.owner_no,a.article_no,a.article_name,a.qmin_operate_packing,a.barcode,a.spec,a.UNIT ,a.expiry_days,a.lot_type";
		
		
		List<Fcdata_CheckDModel> list=genDao.getListByNativeSql(strSql, Fcdata_CheckDModel.class);
		return list;
	}
	// 填充批次属性
	@Override
	public List<Fcdata_CheckDModel> queryLot(
			String enterpriseNo,String warehouseNo,
			String articleNo,String produceDate)
			throws Exception {
		String strSql="select lot_no " +
				"from BDEF_ARTICLE_LOT_MANAGE " +
				"where article_no='"+articleNo+"'  " +
				"and enterprise_no='"+enterpriseNo+"' "+
				"and warehouse_no='"+warehouseNo+"' "+
				"and to_char(produce_date,'yyyy/mm/dd') = '"+produceDate+"' ";
		List<Fcdata_CheckDModel> list=genDao.getListByNativeSql(strSql, Fcdata_CheckDModel.class);
		return list;
	}
	
	
	//校验板号是否存在库存表，并且是否与储位匹配
	@Override
	public MsgRes checkLabelNo(String enterpriseNo,
			String strWarehouseNo,String strLabelNo, String strCellNo)
			throws Exception {
		String strSql="select cell_no from stock_content " +
				" where label_no='"+strLabelNo+"' " +
				" and enterprise_no='"+enterpriseNo+"' " +
				" and warehouse_no='"+strWarehouseNo+"' ";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()>0)
		{
			strSql="select label_no from stock_content" +
					" where cell_no='"+strCellNo+"' " +
					" and label_no='"+strLabelNo+"' "+
					" and enterprise_no='"+enterpriseNo+"' " +
					" and warehouse_no='"+strWarehouseNo+"' ";
			List<String> list2=genDao.getListByNativeSql(strSql);
			if(list2.size()>0)
			{
				return new MsgRes(true,"","");
			}else{
				return new MsgRes(false,"该标签不在该货位上，请重新输入！该标签在【"+list.get(0)+"】","");
			}
		}else
		{
			return new MsgRes(false,"该标签不存在，请重新输入","");
		}
	}
	
	//校验主板号与子板号是否存在库存表，并且是否与储位匹配
	@Override
	public MsgRes checkLabelNoAndSubLabelNo(String enterpriseNo,
			String strWarehouseNo,String strLabelNo,
			String strSubLabelNo, String strCellNo) throws Exception {
		String strSql="select cell_no from stock_content " +
				"where label_no='"+strLabelNo+"' "+
				" and enterprise_no='"+enterpriseNo+"' " +
				" and warehouse_no='"+strWarehouseNo+"' ";
	
		List<String> list=genDao.getListByNativeSql(strSql);
		
		if(list.size()>0)
		{
			strSql="select label_no from stock_content where label_no='"+strLabelNo+"' " +
					" and cell_no='"+strCellNo+"' " +
					" and enterprise_no='"+enterpriseNo+"' " +
					" and warehouse_no='"+strWarehouseNo+"' ";
			if(!strSubLabelNo.equals("")){
				strSql=strSql+" and sub_label_no='"+strSubLabelNo+"' ";
			}
			List<String> list2 =genDao.getListByNativeSql(strSql);
			if(list2.size()>0)
			{
				return new MsgRes(true,"","");
			}else
			{
				return new MsgRes(false,"该子标签不在主标签上，请重新输入","");
			}
			
		}else
		{
			return new MsgRes(false,"该标签不存在！","");
		}
	}
	
	
	// 回单确认
	@Override
	public MsgRes tscFcdataconfirm(String enterpriseNo,
			String strWarehouseNo,String strWheresql) throws Exception 
	{
		String strSql="select * from fcdata_check_m " +
				"where check_NO='"+strWheresql.split(",")[0]+"' " +
				" and warehouse_no='"+strWarehouseNo+"' "+
				" and enterprise_no='"+enterpriseNo+"' ";
		List<Fcdata_CheckMModel> fcd=genDao.getListByNativeSql(strSql, Fcdata_CheckMModel.class, 0, 1);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(fcd.get(0).getEnterpriseNo());
		inList.add(fcd.get(0).getWarehouseNo());
		inList.add(fcd.get(0).getOwnerNo());
		inList.add(fcd.get(0).getCheckType());
		inList.add(strWheresql.split(",")[2]);//strUserId作业人
		inList.add(fcd.get(0).getCheckNo());//strCheckNo需求单号
		
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "PKOBJ_FCDATA.P_fcdata_comfireCheck");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}

		return new MsgRes(true,TipUtil.getTipsByKey("E30405"),null);//操作成功
	}
	
	// 无差异保存	 
	@Override
	public MsgRes tscNoDifferenceSave(String enterpriseNo,String strWarehouseNo,String strWheresql) throws Exception {
		String strSql="select * from fcdata_check_d  " +
				"where check_NO='"+strWheresql.split(",")[0]+
				"' and check_type='"+strWheresql.split(",")[1]+
				"' and enterprise_no='"+enterpriseNo+
				"' and warehouse_no='"+strWarehouseNo+"' ";

		List<Fcdata_CheckDModel> list=genDao.getListByNativeSql(strSql, Fcdata_CheckDModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		Double CheckQty = (double) 0;
		outList.add("S");
		for (Fcdata_CheckDModel f : list) {
			if(f.getCheckType().equals("1")){
				CheckQty = f.getArticleQty();
			}else if(f.getCheckType().equals("2")){
				CheckQty = f.getCheckQty();
			}else if(f.getCheckType().equals("3")){
				CheckQty = f.getRecheckQty();
			}
			List inList=new ArrayList();
			inList.add(f.getEnterpriseNo());
			inList.add(f.getWarehouseNo());
			inList.add(f.getOwnerNo());
			inList.add(f.getCheckType());
			inList.add(strWheresql.split(",")[2]);//strUserId
			inList.add(f.getCheckNo());//strCheckNo
			inList.add(f.getCellNo());//strCellNo
			inList.add(f.getArticleNo());//strArticleNo
			inList.add(f.getPackingQty());//nPackingQty
			inList.add(f.getProduceDate());//dtProduceDate
			inList.add(f.getExpireDate());//dtExpireDate
			inList.add(f.getQuality());//strQuality
			inList.add(f.getLotNo());//strLotNo
			inList.add(CheckQty);//nCheckQty
			inList.add(f.getLabelNo());//strLabelNo
			inList.add(f.getSubLabelNo());//strSubLabelNo
			inList.add(f.getStockType());//strStockType
			inList.add(f.getStockValue());//strStockValue
			inList.add(f.getRsvBatch1());
			inList.add(f.getRsvBatch2());
			inList.add(f.getRsvBatch3());
			inList.add(f.getRsvBatch4());
			inList.add(f.getRsvBatch5());
			inList.add(f.getRsvBatch6());
			inList.add(f.getRsvBatch7());
			inList.add(f.getRsvBatch8());
			inList.add("1");//1：按总量盘点覆盖数量 ；2：按件扫描盘点
			
			resultList=genDao.execProc(inList, outList, "PKOBJ_FCDATA.P_Fcdata_SaveCheck");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E30405"),null);//操作成功
	}
	
	//零回单
	@Override
	public MsgRes tscZeroReceiptSave(String enterpriseNo,String strWarehouseNo,String strWheresql) throws Exception {
		String strSql="select * from fcdata_check_d  " +
				"where check_NO='"+strWheresql.split(",")[0]+
				"' and check_type='"+strWheresql.split(",")[1]+
				"' and enterprise_no='"+enterpriseNo+
				"' and warehouse_no='"+strWarehouseNo+"' ";

		List<Fcdata_CheckDModel> list=genDao.getListByNativeSql(strSql, Fcdata_CheckDModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		for (Fcdata_CheckDModel f : list) {
			List inList=new ArrayList();
			inList.add(f.getEnterpriseNo());
			inList.add(f.getWarehouseNo());
			inList.add(f.getOwnerNo());
			inList.add(f.getCheckType());
			inList.add(strWheresql.split(",")[2]);//strUserId
			inList.add(f.getCheckNo());//strCheckNo
			inList.add(f.getCellNo());//strCellNo
			inList.add(f.getArticleNo());//strArticleNo
			inList.add(f.getPackingQty());//nPackingQty
			inList.add(f.getProduceDate());//dtProduceDate
			inList.add(f.getExpireDate());//dtExpireDate
			inList.add(f.getQuality());//strQuality
			inList.add(f.getLotNo());//strLotNo
			inList.add(0);//nCheckQty
			inList.add(f.getLabelNo());//strLabelNo
			inList.add(f.getSubLabelNo());//strSubLabelNo
			inList.add(f.getStockType());//strStockType
			inList.add(f.getStockValue());//strStockValue
			inList.add(f.getRsvBatch1());
			inList.add(f.getRsvBatch2());
			inList.add(f.getRsvBatch3());
			inList.add(f.getRsvBatch4());
			inList.add(f.getRsvBatch5());
			inList.add(f.getRsvBatch6());
			inList.add(f.getRsvBatch7());
			inList.add(f.getRsvBatch8());
			inList.add("1");//1：按总量盘点覆盖数量 ；2：按件扫描盘点
			
			resultList=genDao.execProc(inList, outList, "PKOBJ_FCDATA.P_Fcdata_SaveCheck");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E30405"),null);//操作成功
				
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public MsgRes saveFcdataSure(String strEnterpriseNo,String strCheckNo, String strCheckType,
			String strWarehouseNo,String strOwnerNo,String strWorkerNo,String strLabelNo,String flag) throws Exception {
		MsgRes msgRes=new MsgRes();
		if(flag.equals("1"))
		{
			String strSql="update fcdata_check_d t set t.check_flag='1' " +
					" where t.enterprise_no='"+strEnterpriseNo+"' " +
	                " and t.warehouse_no='"+strWarehouseNo+"' " +
	                " and t.check_no='"+strCheckNo+"' " +
	                " and t.check_flag='0' " +
	                " and t.check_type='"+strCheckType+"' " +
	        		" and t.label_no = '"+strLabelNo+"'";
			genDao.updateBySql(strSql);
			
		}

		//获取是否存在未盘点的标签
		String strSql="select distinct t.cell_no,t.label_no,t.order_id,sub_order_id from fcdata_check_d t " +
                  " where t.enterprise_no='"+strEnterpriseNo+"' " +
                  " and t.warehouse_no='"+strWarehouseNo+"' " +
                  " and t.check_no='"+strCheckNo+"' " +
                  " and t.check_flag='0' " +
                  " and t.check_type='"+strCheckType+"' " +
          		  " and t.label_no <> 'N'" +
                  " order by t.order_id,label_no,sub_order_id ";
		
		List<ReqFCScanBarcodeModel> list = genDao.getListByNativeSql(strSql,ReqFCScanBarcodeModel.class);
		if(list.size() > 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setObj("Q");
			msgRes.setMsg("Q|储位[" + list.get(0).getCellNo() + "]标签[" +
					list.get(0).getLabelNo()+"]还未盘点,是否回单?|" +
					list.get(0).getLabelNo());
			return msgRes;
		}
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strCheckType);
		inList.add(strWorkerNo);
		inList.add(strCheckNo);
		
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "PKOBJ_FCDATA.P_fcdata_comfireCheck");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		msgRes.setIsSucc(true);
		msgRes.setObj("保存成功");
		return msgRes;
	}
	// RF扫描流水号
	public MsgRes fcdataCheckSerialNo(String strRecvData)throws Exception
	{
		//转换请求对象
		ReqFCScanBarcodeModel reqMod=JSON.parseObject(strRecvData, ReqFCScanBarcodeModel.class);
		String enterpriseNo=reqMod.getEnterpriseNo();
		String warehouseNo=reqMod.getWarehouseNo();
		String serialNo=reqMod.getSerialNo();
		String checkType=reqMod.getCheckType();
		
		MsgRes msgRes=new MsgRes();
		//检查流水号是否存在
		//取建议未盘点储位号
		//如果所有储位都已盘点，则取最小建议储位
		String strSql="select * from (select a.check_no,b.cell_no,a.owner_no ,c.disp_cell_no " +
				"from fcdata_check_m a, fcdata_check_d b,cdef_defcell c " +
				"where a.warehouse_no=b.warehouse_no " +
				"and a.check_no=b.check_no " +
				" AND a.enterprise_no = b.enterprise_no AND b.warehouse_no = c.warehouse_no AND b.enterprise_no = c.enterprise_no AND b.cell_no = c.cell_no " +				
				"and a.STATUS='10' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_no='"+warehouseNo +"' " +
				"and a.serial_no='"+serialNo+"' " +
				"and a.check_type='"+checkType+"' " +
				"and b.check_type='"+checkType+"' " +
				"and b.check_flag=0 " +				
				"order by b.order_id,b.label_no,b.sub_order_id,b.produce_date,b.lot_no) c " +
				"where rownum<2 ";
		List list = genDao.getListByNativeSql(strSql, AnsFCScanSerialModel.class);
		if(list.size()==0)
		{
			strSql="select c.cell_no,c.check_no,c.owner_no,cdc.disp_cell_no " +
					" from (select a.check_no,min(b.cell_no) as cell_no,a.owner_no,a.warehouse_no,a.enterprise_no " +
					"from fcdata_check_m a, fcdata_check_d b " +
					"where a.warehouse_no=b.warehouse_no  AND a.enterprise_no = b.enterprise_no  " +
					"and a.check_no=b.check_no " +				
					"and a.STATUS='10' " +
					"and a.enterprise_no='"+enterpriseNo+"' "+
					"and a.warehouse_no='"+warehouseNo +"' " +
					"and a.serial_no='"+serialNo+"' " +
					"and a.check_type='"+checkType+"' " +		
					"and b.check_type='"+checkType+"' " +
					"group by a.check_no,b.cell_no, a.owner_no,a.warehouse_no,a.enterprise_no,b.order_id,b.label_no,b.sub_order_id,b.produce_date,b.lot_no " +
					"order by b.order_id,b.label_no,b.sub_order_id,b.produce_date,b.lot_no) c " +
					" ,cdef_defcell cdc WHERE c.cell_no = cdc.cell_no AND c.warehouse_no = cdc.warehouse_no AND c.enterprise_no = cdc.enterprise_no " +
					" AND rownum<2";
			list = genDao.getListByNativeSql(strSql, AnsFCScanSerialModel.class);
			if(list.size()==0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("流水号不存在！");
			}else
			{
				msgRes.setIsSucc(true);
				msgRes.setObj(JSON.toJSONString(list.get(0)));
			}
		}else
		{
			msgRes.setIsSucc(true);
			msgRes.setObj(JSON.toJSONString(list.get(0)));
		}
		return msgRes;
	}
	
	//扫描储位
	public MsgRes fcdataCheckCellNo(String strRecvData) throws Exception {
		//转换请求对象
		ReqFCScanBarcodeModel reqMod=JSON.parseObject(strRecvData, ReqFCScanBarcodeModel.class);
		String enterpriseNo = reqMod.getEnterpriseNo();
		String warehouseNo = reqMod.getWarehouseNo();
		String checkType = reqMod.getCheckType();
		String cellNo = reqMod.getCellNo();
		String checkNo = reqMod.getCheckNo();
		String ownerNo = reqMod.getOwnerNo();
		String Addflag = reqMod.getAddFlag();
		
		System.out.println(Addflag);	
		String strMsg = "";
		String strCellNo = "";
		String strDispCellNo = "";
		String LabelNo = "";
		String strCheckQty="";
		MsgRes msgRes = new MsgRes();
		String strSql="select count(*) from fcdata_check_m a," +
				" fcdata_check_d b " +
				" where a.warehouse_no=b.warehouse_no " +
				" and a.enterprise_no=b.enterprise_no "+
				" and a.check_no=b.check_no " +
				" and a.check_type=b.check_type " +
				" and b.cell_no='"+cellNo+"'" +
				" and a.enterprise_no='"+enterpriseNo+"'"+
				" and a.warehouse_no='"+warehouseNo+"'"+
				" and a.check_type='"+checkType+"'" +
				" and a.check_no='"+checkNo+"'" +
				" and a.owner_no='"+ownerNo+"'";
		List list = genDao.getListByNativeSql(strSql);
		if(list.get(0).toString().equals("0"))
		{
			strSql="select distinct b.cell_no,b.label_no,c.disp_cell_no from fcdata_check_m a," +
					" fcdata_check_d b ,cdef_defcell c " +
					" where a.warehouse_no=b.warehouse_no " +
					" and a.enterprise_no=b.enterprise_no "+
					" and a.check_no=b.check_no " +
					" and a.check_type=b.check_type " +
					" AND b.cell_no = c.cell_no  AND b.warehouse_no =c.warehouse_no AND b.enterprise_no = c.enterprise_no " +
					" and b.label_no='"+cellNo+"'" +
					" and a.enterprise_no='"+enterpriseNo+"'"+
					" and a.warehouse_no='"+warehouseNo+"'"+
					" and a.check_type='"+checkType+"'" +
					" and a.check_no='"+checkNo+"'" +
					" and a.owner_no='"+ownerNo+"'";
			List<ReqFCScanBarcodeModel> list1 = genDao.getListByNativeSql(strSql,ReqFCScanBarcodeModel.class);
			if(list1.size() <= 0)
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("储位或者标签不存在！");
			}
			else
			{
				strDispCellNo = list1.get(0).getDispCellNo().toString();
				strCellNo = list1.get(0).getCellNo().toString();
				LabelNo = list1.get(0).getLabelNo().toString();
				
				if(Addflag.equals("1"))
				{
					//更新盘点状态
					if(checkType.equals("1"))
					{
						strSql="update fcdata_check_d a set a.check_flag='1',CHECK_QTY=0,real_qty=0 " +
								" where a.check_no='"+checkNo+"' " +
								" and a.enterprise_no = '"+enterpriseNo+"'" +
								" and a.warehouse_no = '"+warehouseNo+"'" +
								" and (a.label_no='"+cellNo+"' or " +
								"    (a.label_no='N' and a.cell_no='" + strCellNo + "')) " +
								" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'" +
								" and a.status<'13'";
					}
					if(checkType.equals("2"))
					{
						strSql="update fcdata_check_d a set a.check_flag='1', RECHECK_QTY=0,real_qty=0" +
							" where a.check_no='"+checkNo+"' " +
							" and a.enterprise_no = '"+enterpriseNo+"'" +
							" and a.warehouse_no = '"+warehouseNo+"'" +
							" and (a.label_no='"+cellNo+"' or " +
							"    (a.label_no='N' and a.cell_no='" + strCellNo + "')) " +
							" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
							" and a.status<'13'";
					}
					if(checkType.equals("3"))
					{
						strSql="update fcdata_check_d a set a.check_flag='1', THIRD_QTY=0,real_qty=0" +
								" where a.check_no='"+checkNo+"' " +
								" and a.enterprise_no = '"+enterpriseNo+"'" +
								" and a.warehouse_no = '"+warehouseNo+"'" +
								" and (a.label_no='"+cellNo+"' or " +
								"    (a.label_no='N' and a.cell_no='" + strCellNo + "')) " +
								" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
								" and a.status<'13'";
					}
								
					genDao.updateBySql(strSql);
				}
				else
				{
					//更新盘点状态
					if(checkType.equals("1"))
					{
						strSql="update fcdata_check_d a set a.check_flag='1' " +
								" where a.check_no='"+checkNo+"' " +
								" and a.enterprise_no = '"+enterpriseNo+"'" +
								" and a.warehouse_no = '"+warehouseNo+"'" +
								" and (a.label_no='"+cellNo+"' or " +
								"    (a.label_no='N' and a.cell_no='" + strCellNo + "')) " +
								" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'" +
								" and a.check_qty is null " +
								" and a.status<'13'";
					}
					if(checkType.equals("2"))
					{
						strSql="update fcdata_check_d a set a.check_flag='1' " +
							" where a.check_no='"+checkNo+"' " +
							" and a.enterprise_no = '"+enterpriseNo+"'" +
							" and a.warehouse_no = '"+warehouseNo+"'" +
							" and (a.label_no='"+cellNo+"' or " +
							"    (a.label_no='N' and a.cell_no='" + strCellNo + "')) " +
							" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
							" and a.RECHECK_QTY is null " +
							" and a.status<'13'";
					}
					if(checkType.equals("3"))
					{
						strSql="update fcdata_check_d a set a.check_flag='1' " +
								" where a.check_no='"+checkNo+"' " +
								" and a.enterprise_no = '"+enterpriseNo+"'" +
								" and a.warehouse_no = '"+warehouseNo+"'" +
								" and (a.label_no='"+cellNo+"' or " +
								"    (a.label_no='N' and a.cell_no='" + strCellNo + "')) " +
								" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
								" and a.THIRD_QTY is null " +
								" and a.status<'13'";
					}
					
					genDao.updateBySql(strSql);
				}
				
				//if(list1.get(0).toString()==SCellNo)
				//{
				strDispCellNo = list1.get(0).getDispCellNo().toString();
				strCellNo = list1.get(0).getCellNo().toString();
				LabelNo = list1.get(0).getLabelNo().toString();
				strMsg = strDispCellNo+ "|" + strCellNo+ "|" + LabelNo;
				msgRes.setIsSucc(true);
				//}
				//else
				//{
				//	msgRes.setIsSucc(false);
				//	msgRes.setMsg("标签[" + cellNo + "]属于[" + list1.get(0).toString() + "]不在当前储位上！");
				//}
			}
		}else
		{
			if(Addflag.equals("1"))
			{
				//更新盘点状态
				if(checkType.equals("1"))
				{
					strSql="update fcdata_check_d a set a.check_flag='1',CHECK_QTY=0,real_qty=0 " +
							" where a.check_no='"+checkNo+"' " +
							" and a.enterprise_no = '"+enterpriseNo+"'" +
							" and a.warehouse_no = '"+warehouseNo+"'" +
							" and a.cell_no='"+cellNo+"' " +
							" and a.label_no='N'" +
							" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'" +
							" and a.status<'13'";
				}
				if(checkType.equals("2"))
				{
					strSql="update fcdata_check_d a set a.check_flag='1', RECHECK_QTY=0,real_qty=0" +
						" where a.check_no='"+checkNo+"' " +
						" and a.enterprise_no = '"+enterpriseNo+"'" +
						" and a.warehouse_no = '"+warehouseNo+"'" +
						" and a.cell_no='"+cellNo+"' " +
						" and a.label_no='N'" +
						" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
						" and a.status<'13'";
				}
				if(checkType.equals("3"))
				{
					strSql="update fcdata_check_d a set a.check_flag='1', THIRD_QTY=0,real_qty=0" +
							" where a.check_no='"+checkNo+"' " +
							" and a.enterprise_no = '"+enterpriseNo+"'" +
							" and a.warehouse_no = '"+warehouseNo+"'" +
							" and a.cell_no='"+cellNo+"' " +
							" and a.label_no='N'" +
							" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
							" and a.status<'13'";
				}
							
				genDao.updateBySql(strSql);
			}
			else
			{
				//更新盘点状态
				if(checkType.equals("1"))
				{
					strSql="update fcdata_check_d a set a.check_flag='1'  " +
							" where a.check_no='"+checkNo+"' " +
							" and a.enterprise_no = '"+enterpriseNo+"'" +
							" and a.warehouse_no = '"+warehouseNo+"'" +
							" and a.cell_no='"+cellNo+"' " +
							" and a.label_no='N'" +
							" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'" +
							" and a.check_qty is null " +
							" and a.status<'13'";
				}
				if(checkType.equals("2"))
				{
					strSql="update fcdata_check_d a set a.check_flag='1' " +
						" where a.check_no='"+checkNo+"' " +
						" and a.enterprise_no = '"+enterpriseNo+"'" +
						" and a.warehouse_no = '"+warehouseNo+"'" +
						" and a.cell_no='"+cellNo+"' " +
						" and a.label_no='N'" +
						" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
						" and a.RECHECK_QTY is null " +
						" and a.status<'13'";
				}
				if(checkType.equals("3"))
				{
					strSql="update fcdata_check_d a set a.check_flag='1' " +
							" where a.check_no='"+checkNo+"' " +
							" and a.enterprise_no = '"+enterpriseNo+"'" +
							" and a.warehouse_no = '"+warehouseNo+"'" +
							" and a.cell_no='"+cellNo+"' " +
							" and a.label_no='N'" +
							" and a.owner_no='"+ownerNo+"' and a.check_type='"+checkType+"'"+
							" and a.THIRD_QTY is null " +
							" and a.status<'13'";
				}
				
				genDao.updateBySql(strSql);
			}
			//这里需要取当前储位对应的disp_cell_no在RF上显示
			strSql="select distinct c.disp_cell_no from fcdata_check_m a," +
					" fcdata_check_d b,cdef_defcell c  " +
					" where a.warehouse_no=b.warehouse_no " +
					" and a.enterprise_no=b.enterprise_no "+
					" and a.check_no=b.check_no " +
					" AND b.cell_no = c.cell_no  AND b.warehouse_no =c.warehouse_no AND b.enterprise_no = c.enterprise_no" +
					" and b.cell_no='"+cellNo+"'" +
					" and a.enterprise_no='"+enterpriseNo+"'"+
					" and a.warehouse_no='"+warehouseNo+"'"+
					" and a.check_type='"+checkType+"'" +
					" and a.check_no='"+checkNo+"'"/* +
					" and a.owner_no='"+ownerNo+"'"*/;
			List list_dis = genDao.getListByNativeSql(strSql);
			strDispCellNo = list_dis.get(0).toString();
			strCellNo = cellNo;
			LabelNo = "N";
			strMsg = strDispCellNo+ "|" +strCellNo+ "|" + LabelNo;	
			msgRes.setIsSucc(true);
		}
		if(msgRes.getIsSucc())
		{
			 MsgRes msg = fcDataGetCheckQty(enterpriseNo,warehouseNo,checkType,
					 strCellNo,LabelNo,checkNo,ownerNo);
			 strCheckQty = msg.getMsg();
			 msgRes.setObj(strMsg + "|" + strCheckQty);
		}
		return msgRes;
	}
	/**
	 * 获取已盘点数量
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param checkType
	 * @param cellNo
	 * @param LabelNo
	 * @param checkNo
	 * @param ownerNo
	 * @return
	 */
	public MsgRes fcDataGetCheckQty(String enterpriseNo, String warehouseNo, String checkType,
	String cellNo,String LabelNo, String checkNo, String ownerNo)
	{
		MsgRes msgRes = new MsgRes();
		//待优化，有箱包装的未做换算
		String strSql="select (case '"+ checkType +"' " +
				" when '1' then nvl(sum(c.CHECK_QTY),0) " +
				" when '2' then nvl(sum(c.RECHECK_QTY),0) " +
				" else nvl(sum(c.THIRD_QTY),0) end) CheckCQty " +
				" from fcdata_check_d c " +
				" where c.enterprise_no='"+enterpriseNo+"'" +
				"   and c.warehouse_no='"+warehouseNo+"'" +
				"   and c.check_no='"+checkNo+"'" +
				"	and c.check_type='"+checkType+"'" +
				"	and c.cell_no='"+cellNo+"'" +
				"	and c.label_no='"+LabelNo+"'";
		List list = genDao.getListByNativeSql(strSql);
		
		msgRes.setIsSucc(true);
		msgRes.setMsg(list.get(0).toString());
		return msgRes;
	}
    //扫描条码
	public MsgRes fcdataCheckBarcode(ReqFCScanBarcodeModel resMod) {
		MsgRes msgRes=new MsgRes();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strSql = "";
		if(resMod.getProduceDate()!=null && resMod.getProduceDate()!=null		  
		   && !resMod.getPackQty().equals("") && resMod.getLotNo()!=null 
		   && !resMod.getLotNo().equals(""))
		{
			strSql = "select b.*," +
					" f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit," +
					" f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin," +
					" f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit," +
					" f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec," +
					" f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin," +
					" f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec," +
					" d.pal_base_qbox as palBaseBox," +
					" b.article_qty as stockQty," +
					" d.pal_height_qbox as palHeightBox," +
					" c.article_name," +
					" c.expiry_days " +
					"from " +
					" fcdata_check_m a," +
					" fcdata_check_d b," +
					" bdef_defarticle c," +
					" bdef_article_packing d  " +
					"where " +
					" a.warehouse_no=b.warehouse_no " +
					" and b.enterprise_no=a.enterprise_no "+
					" and b.article_no=c.article_no " +
					" and a.check_no=b.check_no " +
					" and b.cell_no='"+resMod.getCellNo()+"' " +
					" and b.warehouse_no='"+resMod.getWarehouseNo()+"'"+
					" and b.enterprise_no='"+resMod.getEnterpriseNo()+"'"+
					" and a.check_type='"+resMod.getCheckType()+"' " +
					" and a.check_no='"+resMod.getCheckNo()+"'" +
					" and b.barcode='"+resMod.getBarcode()+"' " +
					" and a.owner_no='"+resMod.getOwnerNo()+"'" +
					" and b.produce_date= to_date('"+format.format(resMod.getProduceDate())+"', 'yyyy-mm-dd')  " +
					" and b.packing_qty='"+resMod.getPackQty()+"'"+
					" and b.enterprise_no=d.enterprise_no(+)  " +
					" and b.article_no=d.article_no(+) " +
					" and b.packing_qty=d.packing_qty(+)" ;;
		}else
		{
			strSql ="select x.*, " +
					" f_get_packingunit(x.enterprise_no,x.owner_no,x.article_no,x.packing_qty) packingUnit," +
					" f_get_packingunit(x.enterprise_no,x.owner_no,x.article_no,x.qmin_operate_packing) packingUnitQmin," +
					" f_get_packingunit(x.enterprise_no,x.owner_no,x.article_no,x.unit_packing) Unit," +
					" f_get_spec(x.enterprise_no,x.owner_no,x.article_no,x.packing_qty) packingSpec," +
					" f_get_spec(x.enterprise_no,x.owner_no,x.article_no,x.qmin_operate_packing) packingSpecQmin," +
					" f_get_spec(x.enterprise_no,x.owner_no,x.article_no,x.unit_packing) spec," +
					" d.pal_height_qbox as palHeightBox," +
					" d.pal_base_qbox as palBaseBox " +
					"from ( " +
					"	select b.*, " +
					"	  b.article_qty as stockQty, " +
					" 	  c.article_name," +
					"     c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit,c.unit_packing," +
					"     c.expiry_days  " +
					"   from " +
					"     fcdata_check_m a, " +
					"     fcdata_check_d b," +
					"     bdef_defarticle c " +
					"   where a.warehouse_no=b.warehouse_no  " +
					"     and b.enterprise_no=a.enterprise_no "+
					"     and b.article_no=c.article_no  and a.check_no=b.check_no " +
					" 	  and b.cell_no='"+resMod.getCellNo()+"' " +
					"     and b.enterprise_no='"+resMod.getEnterpriseNo()+"'"+
					" 	  and b.warehouse_no='"+resMod.getWarehouseNo()+"'"+
					"     and a.check_type='"+resMod.getCheckType()+"' " +
					"     and a.check_no='"+resMod.getCheckNo()+"'" +
					"     and b.barcode='"+resMod.getBarcode()+"' " +
					"     and a.owner_no='"+resMod.getOwnerNo()+"' " +
					") x " +
					" left join bdef_article_packing d on x.article_no=d.article_no " +
					" and x.packing_qty=d.packing_qty " +
					" and x.enterprise_no=d.enterprise_no ";
		}
		
		List<ReqFCScanBarcodeModel> list = genDao.getListByNativeSql(strSql, ReqFCScanBarcodeModel.class);
		
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("商品不存在,请新增！");
		}else
		{
			resMod.setStockQty(list.get(0).getSecondQty());
			resMod.setPackQty(list.get(0).getPackQty());
			resMod.setAddFlag(list.get(0).getAddFlag());
			resMod.setUserID(list.get(0).getUserID());
			resMod.setUnit(list.get(0).getUnit());
			resMod.setArticleNo(list.get(0).getArticleNo());
			resMod.setArticleName(list.get(0).getArticleName());
			resMod.setSpec(list.get(0).getSpec());
			resMod.setLotNo(list.get(0).getLotNo());
			resMod.setProduceDate(list.get(0).getProduceDate());
			resMod.setExpireDate(list.get(0).getExpireDate());
			resMod.setExpiryDays(list.get(0).getExpiryDays());
			resMod.setQuality(list.get(0).getQuality());
			resMod.setImportBatchNo(list.get(0).getImportBatchNo());
			resMod.setRsvBatch1(list.get(0).getRsvBatch1());
			resMod.setRsvBatch2(list.get(0).getRsvBatch2());
			resMod.setRsvBatch3(list.get(0).getRsvBatch3());
			resMod.setRsvBatch4(list.get(0).getRsvBatch4());
			resMod.setRsvBatch5(list.get(0).getRsvBatch5());
			resMod.setRsvBatch6(list.get(0).getRsvBatch6());
			resMod.setRsvBatch7(list.get(0).getRsvBatch7());
			resMod.setRsvBatch8(list.get(0).getRsvBatch8());
			msgRes.setIsSucc(true);
			msgRes.setObj(JSON.toJSONString(resMod));
		}
		return msgRes;
	}
	

	


	/*
	 * 扫描商品条码获取包装信息
	 */	
	public MsgRes fcdataScanBarcode(String strRecvData) throws Exception {
		
		FCParameterRequestModel reqMod=JSON.parseObject(strRecvData, FCParameterRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strBarcode=reqMod.getBarcode();
		String strOwnerNo=reqMod.getOwnerNo();
		String strCheckNo=reqMod.getCheckNo();
		String strCellNo=reqMod.getCellNo();
		String strCheckType=reqMod.getCheckType();
		String strAddFlag=reqMod.getAddFlag();
		
		MsgRes msg =getArticleForBarcodeImpl.checkBarcode(strWarehouseNo,strBarcode,strOwnerNo,strEnterpriseNo);
		if(!msg.getIsSucc())
		{
			return msg;
		}
		String strSql="";
		if(strAddFlag.equals("True"))
		{
			strSql="  select distinct a.article_no," +
					" a.packing_qty as packQty," +
					" c.article_name," +
					" a.packing_unit as packunit," +
					" a.pal_base_qbox as palBaseBox," +
					" a.pal_height_qbox as palHeightBox," +
					" c.expiry_days " +
				" from bdef_article_packing a," +
					" bdef_defarticle c " +
				" where a.article_no=c.article_no " +
				    " and a.enterprise_no=c.enterprise_no " +
				    " and a.enterprse_no='" +strEnterpriseNo+"' "+
					" and a.article_no='"+((Bdef_ArticleInfoModel)msg.getObj()).getArticleNo()+"'  " +
					" order by a.packing_qty ";
		}else
		{
			strSql="select distinct " +
					"a.article_no," +
					"b.article_name," +
					"b.expiry_days," +
					"a.packing_qty as packQty," +
					"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
					"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin," +
					"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit," +
					"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
					"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin," +
					"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec," +
					"c.pal_base_qbox," +
					"c.pal_height_qbox " +
				"from fcdata_check_d a," +
					"bdef_defarticle b," +
					"bdef_article_packing c " +
				"where a.article_no=b.article_no " +
				    "and a.enterprise_no=b.enterprise_no " +
					"and a.article_no=c.article_no(+) " +
					"and a.packing_qty=c.packing_qty(+) " +
					"and a.enterprise_no=c.enterprise_no(+) " +
					"and a.warehouse_no='"+strWarehouseNo+"' " +
					"and a.enterprise_no='"+strEnterpriseNo+"' " +
					"and a.check_no='"+strCheckNo+"' " +
					"and a.check_type='"+strCheckType+"' " +
					"and a.cell_no='"+strCellNo+"' " +
					"and a.article_no='"+((Bdef_ArticleInfoModel)msg.getObj()).getArticleNo()+"' " +
					"order by a.packing_qty ";
		}
		List<AnsPackingModel> list = genDao.getListByNativeSql(strSql, AnsPackingModel.class);
		if(list.size()>0){
			msg.setIsSucc(true);
			msg.setObj(JSONArray.fromObject(list));
			return msg;
		}else{
			msg.setIsSucc(false);
			msg.setMsg("找不到该条码");
			return msg;
		}
	}
	@Override
	public MsgRes GetArticleInfoByBarcode(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		
		FCParameterRequestModel reqMod=JSON.parseObject(strRecvData, FCParameterRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strBarcode=reqMod.getBarcode();
		String strOwnerNo=reqMod.getOwnerNo();
		String strCheckNo=reqMod.getCheckNo();
		String strCellNo=reqMod.getCellNo();
		String strLabelNo=reqMod.getLabelNo();
		String strCheckType=reqMod.getCheckType();
		String strArticleNo="";
		String strSql="";/*"select (case '"+ strCheckType +"' " +
				" when '1' then nvl(sum(c.CHECK_QTY),0) " +
				" when '2' then nvl(sum(c.RECHECK_QTY),0) " +
				" else nvl(sum(c.THIRD_QTY),0) end) CheckQty " +
				" from fcdata_check_d c " +
				" where c.enterprise_no='"+strEnterpriseNo+"'" +
				"   and c.warehouse_no='"+strWarehouseNo+"'" +
				"   and c.check_no='"+strCheckNo+"'" +
				"	and c.check_type='"+strCheckType+"'" +
				"	and c.cell_no='"+strCellNo+"'" +
				"	and c.label_no='"+strLabelNo+"'";
		List list = genDao.getListByNativeSql(strSql);*/
		
		//应答结构
		AnsInPutBarcodeModel ansMod=new AnsInPutBarcodeModel();
		
		ansMod.setEnterpriseNo(strEnterpriseNo);
		ansMod.setWarehouseNo(strWarehouseNo);
		ansMod.setCellNo(reqMod.getCellNo());		
		ansMod.setSerialNo(reqMod.getSerialNo());
		ansMod.setCheckNo(reqMod.getCheckNo());
		ansMod.setCellNo(strCellNo);
		ansMod.setCheckType(reqMod.getCheckType());
		//ansMod.setCheckQty(list.get(0).toString());
				
		//根据条码找商品
		if(strOwnerNo.equals("N")){
			msg =getArticleForBarcodeImpl.checkBarcode(strWarehouseNo,
					strBarcode,
					strEnterpriseNo);
		}
		else
		{
			msg =getArticleForBarcodeImpl.checkBarcode(strWarehouseNo,
					strBarcode,
					strOwnerNo,
					strEnterpriseNo);
		}
		
		if(!msg.getIsSucc())
		{
			return msg;
		}

		strArticleNo=msg.getObj().toString();
		
		List<ArticleInfoModel> listArt=new ArrayList<ArticleInfoModel>();
		//if("True".equals(reqMod.getAddFlag()))			//新增

		strSql="select distinct " +
				"A.ARTICLE_NO," +
				"b.ARTICLE_NAME," +
				"b.BARCODE," +
				"b.EXPIRY_DAYS," +
				"b.SPEC," +
				"b.owner_no," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin," +
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit," +
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin," +
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec," +
				"b.lot_type,b.QMIN_OPERATE_PACKING ,B.UNIT_PACKING,a.packing_qty packQty " +
			"from fcdata_check_d a," +
				"bdef_defarticle b " +
			"where " +
				"a.article_no=b.article_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' "+
				"and a.check_no='"+strCheckNo+"' " +
				"and a.check_type='"+strCheckType+"' " +
				"and a.cell_no='"+strCellNo+"' " +
				"and a.label_no='"+ strLabelNo +"'" +
				"and a.article_no in("+strArticleNo+")" +
				"order by a.ARTICLE_NO ";
		listArt=genDao.getListByNativeSql(strSql, ArticleInfoModel.class);					
		
		if(listArt.size()==0)
		{
			//取商品基础信息
			strSql="select distinct " +
						"a.ARTICLE_NO," +
						"a.ARTICLE_NAME," +
						"a.BARCODE," +
						"a.EXPIRY_DAYS," +
						"a.SPEC," +
						"a.owner_no, " +
		                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.qmin_operate_packing) packingUnitQmin," +
		                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.unit_packing) Unit," +
						"a.lot_type,a.QMIN_OPERATE_PACKING ,a.UNIT_PACKING " +
					"from " +
						"bdef_defarticle a " +
					"where " +
						"a.STATUS='0' " +
						"and a.article_no in("+strArticleNo+") " +
						"and a.enterprise_no='"+strEnterpriseNo+"' ";
	
			listArt=genDao.getListByNativeSql(strSql, ArticleInfoModel.class);	
		}
		
		if(listArt.size()==1)
		{
			//只有一个品项，则取包装信息
			ansMod.setArtcicleNum("2");
			List<AnsPackingModel> listArtPack=GetArticlePacking(strEnterpriseNo,listArt.get(0).getArticleNo());
			ansMod.setListPacking(listArtPack);
		}else
		{
			//只返回商品信息
			ansMod.setArtcicleNum("3");
		}
		ansMod.setListArticlinfo(listArt);
		msg.setIsSucc(true);
		msg.setObj(JSON.toJSONString(ansMod));
		
		return msg;
	}
	@Override
	public MsgRes GetArticlePacking(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strArticleNo=reqMod.getReqObj();		
		MsgRes msg=new MsgRes();
		List<AnsPackingModel> listArtPack=GetArticlePacking(strEnterpriseNo,strArticleNo);			
		msg.setIsSucc(true);
		msg.setObj(JSON.toJSONString(listArtPack));
		return msg;
	}
	@Override
	public List<AnsPackingModel> GetArticlePacking(String strEnterpriseNo,String strArticleNo) throws Exception {
		String strSql="select " +
					"a.packing_unit as packunit," +
					"a.packing_qty as packQty," +
					"a.pal_base_qbox as palBaseBox," +
					"a.pal_height_qbox  as palHeightBox " +
				"from " +
					"bdef_article_packing a " +
				"where " +
					"a.ARTICLE_NO='"+strArticleNo+"' " +
					"and a.enterprise_no='"+strEnterpriseNo+"' ";
		List<AnsPackingModel> listArtPack=genDao.getListByNativeSql(strSql, AnsPackingModel.class);	
		
		if(listArtPack.size()==0){
			strSql="select " +
					"b.unit as packunit, " +
					"1 as packQty, " +
					"'999' as palBaseBox, " +
					"'999' as palHeightBox  " +
				"from " +
					"bdef_defarticle b " +
				"where " +
					"b.ARTICLE_NO='"+strArticleNo+"' " +
					"and b.enterprise_no='"+strEnterpriseNo+"' ";
			
			listArtPack=genDao.getListByNativeSql(strSql, AnsPackingModel.class);				
		}
		return listArtPack;
	}	
	@Override
	public MsgRes StockContentQuery(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		ReqStockContentQueryModel reqMod=JSON.parseObject(strRecvData, ReqStockContentQueryModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strSql="";
		String strArticleNo="";
		
		strSql = "select "+
			       "b.article_no, " +
			       "b.barcode, c.label_no,"+
			       //"'"+ reqMod.getBarcode()+"' as barcode, "+
			       "b.OWNER_ARTICLE_NO,b.owner_no, b.supplier_no, "+
			       "b.ARTICLE_NAME, "+
			       "c.packing_qty as packQty,  "+
			       //"nvl(d.packing_unit, decode(c.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packunit," +
			       "f_get_packingunit(c.enterprise_no,c.owner_no,c.article_no,c.packing_qty) packUnit," +
			       "f_get_packingunit(c.enterprise_no,c.owner_no,c.article_no,b.qmin_operate_packing) packingUnitQmin," +
			       "f_get_packingunit(c.enterprise_no,c.owner_no,c.article_no,b.unit_packing) Unit," +
			       "f_get_spec(c.enterprise_no,c.owner_no,c.article_no,c.packing_qty) packingSpec," +
			       "f_get_spec(c.enterprise_no,c.owner_no,c.article_no,b.qmin_operate_packing) packingSpecQmin," +
			       "f_get_spec(c.enterprise_no,c.owner_no,c.article_no,b.unit_packing) spec," +
			       " c.qty, "+
			       "c.outstock_qty as outqty, "+
			       "c.instock_qty as inqty,  "+
			       "c.qty - NVL(c.outstock_qty, 0) as canuseqty,"+
			       "to_char(c.produce_date,'yyyyMMdd') as produce_date, "+
			       "to_char(c.expire_date,'yyyyMMdd') as expire_date,   "+
			       "c.quality,c.cell_no, "+
			       "(select min(cell_no) from cset_cell_article e " +
			       "where c.enterprise_no=e.enterprise_no "+
			       "  and c.warehouse_no=e.warehouse_no "+
			       "  and c.article_no=e.article_no ) as pick_cell_no, "+
			       //"nvl(d.packing_unit, decode(c.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
			       "nvl(bw.pal_base_qbox,d.pal_base_qbox)||'*'|| nvl(bw.pal_height_qbox,d.pal_height_qbox) as qpalette "+
			       " from bdef_defarticle b " +
			       " join (" +
			       "select sc.enterprise_no,sc.warehouse_no,sc.owner_no,sc.article_no," +
			       " sc.packing_qty,sai.quality,sai.produce_date,sai.expire_date,sc.cell_no, "+
			       "   sum(sc.qty) qty,sum(sc.instock_qty) instock_qty,sum(sc.outstock_qty) outstock_qty,sc.label_no " +
			       "    from stock_content sc,stock_article_info sai "+
			       "  where sc.enterprise_no=sai.enterprise_no "+
			       "	and sc.article_no=sai.article_no "+
			       "	and sc.article_id=sai.article_id "+
			       "  group by sc.enterprise_no,sc.warehouse_no,sc.owner_no," +
		       	   "   sc.article_no,sc.packing_qty,sai.quality," +
		       	   "   sai.produce_date,sai.expire_date,sc.cell_no,sc.label_no " +
		       	   ")c on  b.article_no = c.article_no and b.owner_no=c.owner_no and b.enterprise_no=c.enterprise_no " +
				   " left join bdef_article_packing d " +
				   "   on c.article_no=d.ARTICLE_NO "+
				   "  and c.enterprise_no=d.enterprise_no "+
				   "  and c.packing_qty=d.packing_qty "+
	       		   " left join bdef_warehouse_packing bw " +
	       		   "   on c.enterprise_no=bw.enterprise_no " +
	       		   "  and c.article_no=bw.article_no " +
	       		   "  and c.packing_qty=bw.packing_qty " +
	       		   "  and c.warehouse_no=bw.warehouse_no ";
		
		if(reqMod.getFlag()==1)//按条码
		{
			//根据条码找商品
			msg =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),
					reqMod.getBarcode(),reqMod.getEnterpriseNo());
			if(!msg.getIsSucc())
			{
				return msg;
			}
			strArticleNo=msg.getObj().toString();
			strSql = strSql + " where c.WAREHOUSE_NO='"+reqMod.getWarehouseNo()+"' "+
			       "   and c.enterprise_no='"+strEnterpriseNo+"' "+
			       "   and c.article_no in(" +strArticleNo+") "+
			       " order by c.article_no, c.cell_no, c.produce_date ";
		}
		else
		{
			strSql= strSql +
					" where c.WAREHOUSE_NO='" +reqMod.getWarehouseNo()+"' "+
					" and c.enterprise_no='"+strEnterpriseNo+"' "+
					" and (c.cell_no='" +reqMod.getCellNo()+"'" +
					"  or c.label_no='"+reqMod.getCellNo()+"' )"+
			    "order by  c.article_no,  c.cell_no,  c.produce_date ";
		
		}
		
		List<AnsStockContentInfoModel> list=genDao.getListByNativeSql(strSql, AnsStockContentInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else
		{			
			if(reqMod.getFlag()==1)
			{
				strSql=" select    "+
				"    a.ARTICLE_NO,   "+
				"    a.OWNER_ARTICLE_NO, a.owner_no,  "+
				"    a.ARTICLE_NAME, a.supplier_no,  " +
				"	 a.barcode,"+
				//"'"+ reqMod.getBarcode()+"' as barcode, " +
				"    nvl(b.packing_qty,a.unit_packing) as packQty,   "+
				"    b.packing_unit as packunit,   "+
				"    '无库存' as cell_no," +
				"    0 as qty,   "+
				"    0 as outqty,   "+
				"    0 as inqty,   "+
				"    0 as canuseqty,    "+
				"    (select min(cell_no) from cset_cell_article c   "+
				"     where c.warehouse_no = '"+reqMod.getWarehouseNo()+"'   " +
				"         and c.enterprise_no='"+strEnterpriseNo+"' "+
				"         and c.article_no = a.article_no) as pick_cell_no,    "+
				"	  nvl(bw.pal_base_qbox,b.pal_base_qbox)||'*'|| nvl(bw.pal_height_qbox,b.pal_height_qbox) as qpalette "+
				"    from    "+
				"      bdef_defarticle a   "+
				"      left join bdef_article_packing b " +
			    "     	 on a.article_no=b.ARTICLE_NO "+
			    "    	and a.enterprise_no=b.enterprise_no "+
				"	   left join bdef_warehouse_packing bw " +
       			"	     on b.enterprise_no=bw.enterprise_no " +
       			"		and b.article_no=bw.article_no " +
       			"		and b.packing_qty=bw.packing_qty " +
       			"		and bw.warehouse_no='"+reqMod.getWarehouseNo()+"' "+ 
				"    where a.article_no in(" +strArticleNo+") " +
				"      and a.enterprise_no='"+strEnterpriseNo+"' ";
				list=genDao.getListByNativeSql(strSql, AnsStockContentInfoModel.class);
				msg.setIsSucc(true);
				msg.setObj(JSON.toJSONString(list));
			}else
			{
				msg.setIsSucc(false);
				msg.setMsg(reqMod.getFlag()==1?"该条码没有找到相应的库存信息！":"该储位没有库存信息！");
			}
		}
		return msg;
		
	}
	//根据批号获取日期
	@Override
	public List<Fcdata_CheckDModel> getExpireDateByLot(String enterpriseNo,String warehouse,String strWheresql)
			throws Exception {
		String strSql="select lot_no,expire_date " +
		"from BDEF_ARTICLE_LOT_MANAGE " +
		"where article_no='"+strWheresql.split(",")[0]+"'  " +
				"and lot_no='"+strWheresql.split(",")[1]+"'	" +
				"and produce_date ='"+strWheresql.split(",")[2]+"'" +
				"and enterprise_no='"+enterpriseNo+"' " +
				"and warehouse_no='"+warehouse+"' " ;
      List<Fcdata_CheckDModel> list=genDao.getListByNativeSql(strSql, Fcdata_CheckDModel.class);
      return list;
	}
	/**
	 * 扫描标签获取商品
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes DpsScanLabelNo(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strSql="select distinct sld.article_no,bda.article_name,bda.barcode,slm.container_no " +
				" from (select * from stock_label_m union" +
				" select * from stock_label_mhty) slm" +
				" join (select * from odata_divide_d union" +
				" select * from odata_divide_dhty) sld" +
				" on slm.enterprise_no = sld.enterprise_no" +
				" and slm.warehouse_no = sld.warehouse_no" +
				" and slm.container_no = sld.s_container_no" +
				" join bdef_defarticle bda on sld.owner_no = bda.owner_no" +
				" and sld.enterprise_no = bda.enterprise_no" +
				" and sld.article_no = bda.article_no" +
				" where slm.warehouse_no = '"+reqMod.getWarehouseNo()+"'" +
				" and slm.label_no = '"+reqMod.getReqObj()+"'" +
				" and slm.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" ;
		
		List<AnsDpsCellLabel> list=genDao.getListByNativeSql(strSql, AnsDpsCellLabel.class);
		if(list.size()==0)
		{	//查询返配
			strSql="select distinct rid.article_no,bda.article_name,bda.barcode,rid.label_no as container_no " +
					" from (select enterprise_no,warehouse_no,owner_no,article_no,label_no,wave_no from ridata_instock_d " +
					" union select enterprise_no,warehouse_no,owner_no,article_no,label_no,wave_no from ridata_instock_dhty) rid" +
					" join bdef_defarticle bda on rid.owner_no = bda.owner_no " +
					" and rid.enterprise_no=bda.enterprise_no and rid.article_no=bda.article_no " +
					" where rid.warehouse_no = '"+reqMod.getWarehouseNo()+"'" +
					" and rid.label_no = '"+reqMod.getReqObj()+"'" +
					" and rid.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
					" and exists(select 1 from BSET_WAVE_MANAGE c where rid.enterprise_no=c.enterprise_no and rid.warehouse_no=c.warehouse_no "+
					" and rid.wave_no=c.wave_no and c.status<>'15')" ;
			list=genDao.getListByNativeSql(strSql, AnsDpsCellLabel.class);
			if(list.size()==0)
			{
				msg.setIsSucc(false);
				msg.setMsg("未获取到标签信息");
			}
		}
		else
		{
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}
		return msg;
	}
	/**
	 * 获取商品电子标签分播储位
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes DpsGetCell(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		ReqGetDpsCellNo reqMod=JSON.parseObject(strRecvData, ReqGetDpsCellNo.class);
		String strSql="select dps_cell_no from " +
				" (select distinct cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,cdc.cell_no,odd.dps_cell_no " +
				" from (select * from odata_divide_d union" +
				" select * from odata_divide_dhty) odd " +
				" join cdef_defcell cdc on odd.enterprise_no=cdc.enterprise_no " +
				"  and odd.warehouse_no=cdc.warehouse_no and cdc.cell_no=odd.dps_cell_no " +
				" where odd.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
				" and odd.warehouse_no = '"+reqMod.getWarehouseNo()+"'" +
				" and odd.article_no = '"+reqMod.getArticleNo()+"'" +
				" and odd.s_container_no = '"+reqMod.getContainerNo()+"'" +
				" order by cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,cdc.cell_no) " ;
  
		List list=genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			//返配
			strSql="select DEST_CELL_NO from " +
					" (select distinct cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,cdc.cell_no,odd.DEST_CELL_NO  " +
					" from (select enterprise_no,warehouse_no,owner_no,WAVE_NO,article_no,label_no,DEST_CELL_NO from ridata_instock_d " +
					" union select enterprise_no,warehouse_no,owner_no,WAVE_NO,article_no,label_no,DEST_CELL_NO from ridata_instock_dhty) odd" +
					" join cdef_defcell cdc on odd.enterprise_no=cdc.enterprise_no " +
					"  and odd.warehouse_no=cdc.warehouse_no and cdc.cell_no=odd.DEST_CELL_NO " +
					" where odd.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
					" and odd.warehouse_no = '"+reqMod.getWarehouseNo()+"'" +
					" and odd.article_no = '"+reqMod.getArticleNo()+"'" +
					" and odd.label_no = '"+reqMod.getContainerNo()+"'" +
					" and exists(select 1 from BSET_WAVE_MANAGE c where odd.enterprise_no=c.enterprise_no and odd.warehouse_no=c.warehouse_no "+
					" and odd.wave_no=c.wave_no and c.status<>'15')" +
					" order by cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.pick_order,cdc.cell_no) " ;
	  
			list=genDao.getListByNativeSql(strSql);
			if(list.size()==0)
			{
				msg.setIsSucc(false);
				msg.setMsg("未获取到电子标签储位");
			}
		}
		else
		{
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}
		return msg;
	}
	/**
	 * 获取商品分播信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes DpsGetCellInfo(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		ReqGetDpsCellNo reqMod=JSON.parseObject(strRecvData, ReqGetDpsCellNo.class);
		String strSql="select sld.article_no,bda.article_name,bda.barcode,sld.cust_no,cust.cust_name," +
				"sum(sld.real_qty) QTY,nvl((select distinct dsl.label_no from dps_stock_labelhty dsl " +
				" join stock_label_m dslm on dsl.enterprise_no=dslm.enterprise_no " +
				" and dsl.warehouse_no=dslm.warehouse_no and dsl.label_no=dslm.label_no " +
				" where dsl.enterprise_no=sld.enterprise_no and dslm.container_no=sld.cust_container_no" +
				"  and dsl.warehouse_no=sld.warehouse_no  and dsl.dps_cell_no=sld.dps_cell_no),sld.dps_cell_no) as LabelNo " +
				" from (select * from odata_divide_d union  select * from odata_divide_dhty) sld " +
				//" join (select * from stock_label_m union select * from stock_label_mhty) slm " +
				//" on slm.enterprise_no = sld.enterprise_no and slm.warehouse_no = sld.warehouse_no and slm.container_no = sld.s_container_no " +
				" join bdef_defarticle bda on sld.owner_no = bda.owner_no  and sld.enterprise_no = bda.enterprise_no and sld.article_no = bda.article_no " +
				" join bdef_defcust cust on sld.owner_no = cust.owner_no  and sld.enterprise_no = cust.enterprise_no and sld.cust_no = cust.cust_no " +
				" where sld.warehouse_no = '"+reqMod.getWarehouseNo()+"'" +
				" and sld.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
				" and sld.article_no='"+reqMod.getArticleNo()+"'" +
				" and sld.dps_cell_no='"+reqMod.getCellNo()+"' " +
				" and sld.status<>'16'" +
				" and sld.real_qty>0 " +
				" and exists (select 1 from stock_label_m a where a.container_no = sld.cust_container_no " +
				" and a.enterprise_no=sld.enterprise_no and a.warehouse_no=sld.warehouse_no) " +
				" group by sld.article_no,bda.article_name,sld.cust_no,cust.cust_name,bda.barcode ," +
				" sld.dps_cell_no ,sld.enterprise_no,sld.warehouse_no,sld.cust_container_no" ;
		
		List<AnsGetDpsCellInfo> list=genDao.getListByNativeSql(strSql,AnsGetDpsCellInfo.class);
		if(list.size()==0)
		{
			//strSql="select sld.article_no,bda.article_name,bda.barcode,sld.cust_no," +
			//		"sum(sld.real_qty) QTY,nvl((select distinct dsl.label_no from dps_stock_labelhty dsl " +
			//		" join stock_label_m dslm on dsl.enterprise_no=dslm.enterprise_no " +
			//		" and dsl.warehouse_no=dslm.warehouse_no and dsl.label_no=dslm.label_no " +
			//		" where dsl.enterprise_no=sld.enterprise_no and dslm.container_no=sld.cust_container_no" +
			//		"  and dsl.warehouse_no=sld.warehouse_no  and dsl.dps_cell_no=sld.dps_cell_no),sld.dps_cell_no) as LabelNo " +
			//		" from (select * from odata_divide_d union  select * from odata_divide_dhty) sld " +
			//		" join (select * from stock_label_m union select * from stock_label_mhty) slm " +
			//		" on slm.enterprise_no = sld.enterprise_no and slm.warehouse_no = sld.warehouse_no and slm.container_no = sld.s_container_no " +
			//		" join bdef_defarticle bda on sld.owner_no = bda.owner_no  and sld.enterprise_no = bda.enterprise_no and sld.article_no = bda.article_no " +
			//		" where sld.warehouse_no = '"+reqMod.getWarehouseNo()+"'" +
			//		" and sld.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
			//		" and sld.article_no='"+reqMod.getArticleNo()+"'" +
			//		" and sld.dps_cell_no='"+reqMod.getCellNo()+"' " +
			//		" and sld.status<>'16'" +
			//		" and sld.real_qty>0 " +
			//		" group by sld.article_no,bda.article_name,sld.cust_no,bda.barcode ," +
			//		" sld.dps_cell_no ,sld.enterprise_no,sld.warehouse_no,sld.cust_container_no" ;
			
			//list=genDao.getListByNativeSql(strSql,AnsGetDpsCellInfo.class);
			//if(list.size()==0)
			//{
				msg.setIsSucc(false);
				msg.setMsg("未获取到电子标签储位信息");
			//}
		}
		else
		{
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}
		return msg;
	}
	
	//根据商品编码获取生产日期 add by sunl 2016年8月4日
	@Override
	public MsgRes GetProductDateByArticleNO(String strRecvData)
			throws Exception {

		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strArticleNo=reqMod.getReqObj();		
		String strCellNO=reqMod.getFieldEX1();	
		
		MsgRes msg=new MsgRes();
		String strSql="SELECT TO_CHAR(MIN(I.PRODUCE_DATE),'YYYYMMDD') produceDate FROM STOCK_CONTENT C ,STOCK_ARTICLE_INFO I " +
				" WHERE C.ENTERPRISE_NO = I.ENTERPRISE_NO AND C.ARTICLE_NO = I.ARTICLE_NO AND C.ARTICLE_ID = I.ARTICLE_ID " +
				" AND C.CELL_NO = '"+strCellNO+"' AND C.ARTICLE_NO = '"+strArticleNo+"'" +
				" AND C.ENTERPRISE_NO = '"+strEnterpriseNo+"' AND C.WAREHOUSE_NO = '"+strWarehouseNo+"' ";
		
		List<ArticleInfoModel> list = genDao.getListByNativeSql(strSql,ArticleInfoModel.class);
		if(list.size() == 0) 
		{
			msg.setObj("");
		}
		else
		{
			msg.setObj(list.get(0).getProduceDate());
		}
		msg.setIsSucc(true); 
		return msg;
	}
	
	
}
