package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.odata.po.Odata_CheckM;
import com.sealinkin.odata.service.IOdata_ArrangeService;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.odata.ODataCheckLabelDetailModel;
import com.sealinkin.protocolExchange.odata.ODataCheckLabelSelectModel;
import com.sealinkin.protocolExchange.odata.ODataCheckSubLabelModel;
import com.sealinkin.protocolExchange.odata.ODataLabelWeighModel;
import com.sealinkin.protocolExchange.odata.ODataMergePalAndArrangeConfirm;
import com.sealinkin.protocolExchange.odata.ODataWaitAerrangeInfo;
import com.sealinkin.protocolExchange.odata.OdataArrangeConfirmScanLabelAnswerModel;
import com.sealinkin.protocolExchange.odata.OdataContainerArrangeGetNOModel;
import com.sealinkin.protocolExchange.odata.OdataDeliverGetLineInfoModel;
import com.sealinkin.protocolExchange.odata.OdataGetSLabelNoArticleAnswerModel;
import com.sealinkin.protocolExchange.odata.OdataSkuLabelCancelReq;
import com.sealinkin.protocolExchange.odata.ReqArrangePrintDLabel;
import com.sealinkin.protocolExchange.odata.OdataSkuLabelCancelModel;
import com.sealinkin.protocolExchange.odata.ODataAerrangeConfirmWaitLabel;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.DateUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_ArrangeImpl implements IOdata_ArrangeService{
	
	private IGenericManager genDao;
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	private IOdata_OutstockM odata_OutstockMImpl;
	
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
	public IOdata_OutstockM getOdata_OutstockMImpl() {
		return odata_OutstockMImpl;
	}
	public void setOdata_OutstockMImpl(IOdata_OutstockM odata_OutstockMImpl) {
		this.odata_OutstockMImpl = odata_OutstockMImpl;
	}
	/*RF
	 *  1:Use_Type = 1：（客户标签）
		2:Container_No = Owner_Container_No（不能是并板标签）
		3:Status = （52，61，6B，65，66，63，6C）
		4;标签必须处于出货暂存区和发货暂存区
		5:参数一：如果是 则status = 61
		且 则标签在OS_DEFCHECK_CHUTECHECK_CHUTE_NO表中对应的CHECK_PLATNO（复核台号
		）必须等于界面输入的复核台号
		6:参数二：如果是 则标签必须为C型标签
		7：参数九为否，且参数二为是，则标签不能为C型标签
	 */
	@Override
	public MsgRes ExistsSLabelNo(String strEnterpriseNo,String strWarehouseNo, String strSLabelNo)
			throws Exception {	
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWarehouseNo
		inList.add(strSLabelNo);//strSLabelNo		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_check_strScontainerNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		
		return new MsgRes(true,"操作成功！","");
	}
	
	/*
	 * 获得输入标签对应客户的所有标签(复核打包和拆零复核打包公用方法)
	 * lich
	 */
	@Override
	public List<Stock_LabelMModel> getOdata_CusLabel(String strEnterpriseNo,String strWarehouseNo,String strLabelNo,
			String strQuery,String strPrinterGroupNo) {
		
		//modi20151113 by lizhiping 通过源箱的配送对象获取该配送对象在当前复核台没有封箱的箱号列表
		String strSql="select distinct  "+
           " a.d_label_no as label_no,b.check_chute_no,   "+
           "  '['||c.cust_no|| ']'|| c.cust_name cust_name,   "+
           "  '未封箱' statusText     "+
           " from   "+
           "  odata_check_label_d a,   "+
           "  odata_check_m b,   "+
           "  bdef_defcust c ,  "+
           " stock_label_m m  "+
           " where c.enterprise_no=b.enterprise_no    "+
           "  and c.cust_no=b.cust_no  "+
           "  and a.enterprise_no = b.enterprise_no  "+
           "  and a.warehouse_no = b.warehouse_no  "+
           "  and a.check_no = b.check_no       "+       
           "  and a.d_label_no<>'N' and a.status<'13' and a.real_qty>0   "+
           "  and b.enterprise_no = m.enterprise_no  "+
           "  and b.warehouse_no = m.warehouse_no  "+
           "  and b.deliver_obj = m.deliver_obj "+
           "  and b.check_chute_no='" + strPrinterGroupNo+"'  "+ 
           "  and m.enterprise_no='" + strEnterpriseNo+"'   "+ 
           "  and m.warehouse_no='" + strWarehouseNo+"'   "+ 
           "  and m.label_no='" + strLabelNo+"'    "+
           " order by   "+
           "  a.d_label_no desc  ";
		
		//end log20151113
		List<Stock_LabelMModel> list=genDao.getListByNativeSql(strSql, Stock_LabelMModel.class);
		return list;
	}
	//获取未复合商品明细
	@Override
	public ExtListDataBo<Odata_CheckLabelDModel> getUnCheckLabelD(
			String currEnterpriseNo, String warehouseNo, String str)
			throws Exception {
	
		String strAttributeFields=odata_OutstockMImpl.getArticleAttrString("1").get(0);
		String strAttributeGroupByFields=odata_OutstockMImpl.getArticleAttrString("2").get(0);
		if(!"".equals(strAttributeGroupByFields))
		{
			strAttributeGroupByFields=","+strAttributeGroupByFields;
		}
		
		if(!"".equals(strAttributeFields))
		{
			strAttributeFields+=", ";
			if(strAttributeFields.indexOf("QUALITY")>0)
			{
				strAttributeFields+="f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality, ";
				strAttributeGroupByFields+=", sai.QUALITY";
			}
		}
		
		String sql=" select a.lable_no,a.owner_no,vbd.article_no," +
				  " f_get_packingunit(a.enterprise_no,a.owner_no,vbd.article_no,a.packing_qty) packingUnit, "+
				   "  f_get_spec(a.enterprise_no,a.owner_no,vbd.article_no,a.packing_qty) packingSpec, "+
				"vbd.article_name,sum(a.article_qty-a.real_qty) uncheckQty ," +
				   strAttributeFields+" sai.barcode, a.packing_qty  " +
				   " from odata_check_label_d a,bdef_defarticle vbd,stock_article_info sai " +
				   " where a.enterprise_no='"+currEnterpriseNo+"' " +
				   " and a.warehouse_no='"+warehouseNo+"' " +
				   " and a.lable_no='"+str+"' " +
				   " and a.enterprise_no=vbd.enterprise_no " +
				   " and a.article_no=vbd.article_no " +
				   " and a.article_qty-a.real_qty>0 " +
				   " and sai.enterprise_no=a.enterprise_no " +
				   " and sai.article_id=a.article_id " +
				   " and sai.article_no=a.article_no " +
				   " group by a.enterprise_no,a.lable_no,a.owner_no,vbd.article_no, vbd.article_name, sai.barcode, a.packing_qty  " +
				   strAttributeGroupByFields;
		
		List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql, Odata_CheckLabelDModel.class,0,20);
		ExtListDataBo<Odata_CheckLabelDModel> extListBo=new ExtListDataBo<Odata_CheckLabelDModel>(list,0);
		return extListBo;
	}
	
	// 获得标签明细数据(已复核)
	@Override
	public List<Stock_LabelDModel> getStockLabelD(String strEnterpriseNo,String strWarehouseNo,
			String strLabelNo, String strFlag) throws Exception {
		String strSql="  select"+
			       " b.article_no,b.packing_qty,"+
			       " sum(b.real_qty) as qty,"+
			       " c.OWNER_ARTICLE_NO,"+
			       " d.barcode,"+
			       " f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit, "+
				   " f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec, "+
				
			       " c.ARTICLE_NAME     "+
			   " from"+
			       " odata_check_label_d b,"+
			       " bdef_defarticle c,"+
			       " stock_article_info d  "+
			   " where"+
			       " b.article_no=c.ARTICLE_NO "+
			       " and b.enterprise_no=c.enterprise_no "+
			       " and b.enterprise_no=d.enterprise_no "+
			       " and b.article_id=d.article_id "+
			       " and b.article_no=d.article_no " +
			       " and b.real_qty > 0 "+
			       " and b.enterprise_no='" +strEnterpriseNo+"' "+
			       " and b.warehouse_no='" +strWarehouseNo+"' "+
			       " and b.d_label_no='"+strLabelNo+"' "+
			   " group by"+
			   "    b.article_no,b.enterprise_no,b.owner_no,"+
			   "     c.OWNER_ARTICLE_NO,"+
			   "     d.barcode,b.packing_qty,"+
			   "     c.ARTICLE_NAME    ";
		List<Stock_LabelDModel> list=genDao.getListByNativeSql(strSql, Stock_LabelDModel.class);
		return list;
	}
	//修改复合单头档状态为10
	@Override
	public MsgRes updateCheckM(String currEnterpriseNo, String warehouseNo,
			String str) throws Exception {
		String sql1="select sum(d.article_qty) article_qty,sum(d.real_qty) real_qty,d.check_no  " +
				"from odata_check_label_d d where d.enterprise_no='"+currEnterpriseNo+"' " +
				"and d.warehouse_no='"+warehouseNo+"' and d.lable_no='"+str+"' " +
				" group by d.check_no ";
		List<Odata_CheckLabelDModel> list1=genDao.getListByNativeSql(sql1,Odata_CheckLabelDModel.class);
        if(list1.get(0).getArticleQty().equals(list1.get(0).getRealQty())){
        	return new MsgRes(false,"该单已没有可扫量！","");	
        }
		
		String sql="update odata_check_m m set m.status='10' " +
				"where m.check_no='"+list1.get(0).getCheckNo()+"' " +
				"and m.enterprise_no='"+currEnterpriseNo+"' " +
				"and m.warehouse_no='"+warehouseNo+"'";
	    genDao.updateBySql(sql);
	
		return new MsgRes(true,"","");
	}
	
	//出货整理打包》扫描商品条码	
	@Override
	public MsgRes tscCheckBarcode(String currEnterpriseNo, String warehouseNo,
			String str, String strSlabel, String strDlabel, Integer sacnNum,
			String strWorkerNo,String strFlag,String strPrinterGroupNo
			) throws Exception {
		Odata_CheckLabelDModel checkD=(Odata_CheckLabelDModel) JSON.parseObject(str, Odata_CheckLabelDModel.class);
	
		//根据条码找商品
		MsgRes msg =getArticleForBarcodeImpl.checkBarcode(warehouseNo,checkD.getBarcode(),currEnterpriseNo);
		if(!msg.getIsSucc())
		{
			return msg;
		}
		String strArticleNo=msg.getObj().toString();
		
		String sql=" select a.check_no　from odata_check_label_d a " +
				   " where a.lable_no='"+strSlabel+"' " +
				   " and a.enterprise_no='"+currEnterpriseNo+"' " +
				   " and a.warehouse_no='"+warehouseNo+"' ";
		
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return new MsgRes(false,"写复核标签明细有误","");	
		}
		
		//如果sacnNum小于0则是扣量，判断且扣量的数量是否大于目的标签的该商品扫描数量，是则拦截
		if(sacnNum<0){
			//只能扣未封箱的量
			String sql3=" select a.lable_no,a.owner_no,vbd.article_no,vbd.article_name," +
					   " sai.barcode, a.packing_qty,sum(a.article_qty) article_qty,sum(a.real_qty) real_qty," +
					   "sai.rsv_batch1,sai.rsv_batch2,sai.rsv_batch3,sai.rsv_batch4," +
					   "sai.rsv_batch5,sai.rsv_batch6,sai.rsv_batch7,sai.rsv_batch8," +
					   "sai.quality,sai.produce_date,sai.expire_date,sai.lot_no  " +
					   " from odata_check_label_d a,bdef_defarticle vbd,stock_article_info sai " +
					   " where a.enterprise_no='"+currEnterpriseNo+"' " +
					   " and a.warehouse_no='"+warehouseNo+"' " +
					   " and a.lable_no='"+strSlabel+"' " +
					   " and a.enterprise_no=vbd.enterprise_no " +
					   " and a.article_no=vbd.article_no " +
					   " and sai.enterprise_no=a.enterprise_no " +
					   " and sai.article_id=a.article_id " +
					   " and sai.article_no=a.article_no " +
					   " and a.article_no="+strArticleNo+" " +
					   "    and a.status ='11' " +
					   "    and a.d_label_no='"+strDlabel+"' "+
					   "group by a.lable_no,a.owner_no,vbd.article_no," +
					   "vbd.article_name, sai.barcode, a.packing_qty,  "+
					   "sai.rsv_batch1,sai.rsv_batch2,sai.rsv_batch3,sai.rsv_batch4," +
					   "sai.rsv_batch5,sai.rsv_batch6,sai.rsv_batch7,sai.rsv_batch8,  "+
					   "sai.quality,sai.produce_date,sai.expire_date,sai.lot_no  ";
				
			List<Odata_CheckLabelDModel> list3=genDao.getListByNativeSql(sql3,Odata_CheckLabelDModel.class);
			int b = Math.abs(sacnNum);//将负数改为正数
			if(list3.size()==0){
				return new MsgRes(false,"商品不存在该标签内，请重新扫描！","");
			}
			if(b>list3.get(0).getRealQty()){
				return new MsgRes(false,"扣减量大于扫描量，请重新输入扫描基准量！","");
			}
			
			//保存
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
				
			outList.add("S");
			inList.add(currEnterpriseNo);
			inList.add(warehouseNo);
			inList.add(list.get(0).toString());			
			inList.add(list3.get(0).getArticleNo());
			inList.add(list3.get(0).getPackingQty());
			inList.add(list3.get(0).getQuality());
			inList.add(list3.get(0).getProduceDate());
			inList.add(list3.get(0).getExpireDate());
			inList.add(list3.get(0).getLotNo());
			inList.add(list3.get(0).getRsvBatch1());
			inList.add(list3.get(0).getRsvBatch2());
			inList.add(list3.get(0).getRsvBatch3());
			inList.add(list3.get(0).getRsvBatch4());
			inList.add(list3.get(0).getRsvBatch5());
			inList.add(list3.get(0).getRsvBatch6());
			inList.add(list3.get(0).getRsvBatch7());
			inList.add(list3.get(0).getRsvBatch8());
			inList.add(list3.get(0).getBarcode());
			inList.add(strSlabel);
			inList.add(strDlabel);
			inList.add(sacnNum);
			inList.add(strWorkerNo);//操作人
			inList.add(strPrinterGroupNo);
			
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_scanArticle_by_labelNo");
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"操作成功！",strDlabel);	
		}else{
			//如果界面没有选中界面（未复核列表）的数据(strFlag为0)，就要选择从单据里面取商品属性传入参数
			//取来源标签的该商品的数据
			String sql2=" select a.lable_no,a.owner_no,vbd.article_no,vbd.article_name," +
					   " sai.barcode, a.packing_qty,a.status," +
					   "sum(a.article_qty) article_qty,sum(a.real_qty) real_qty," +
					   "sai.rsv_batch1,sai.rsv_batch2,sai.rsv_batch3,sai.rsv_batch4," +
					   "sai.rsv_batch5,sai.rsv_batch6,sai.rsv_batch7,sai.rsv_batch8," +
					   "sai.quality,sai.produce_date,sai.expire_date,sai.lot_no  " +
					   " from odata_check_label_d a,bdef_defarticle vbd,stock_article_info sai " +
					   " where a.enterprise_no='"+currEnterpriseNo+"' " +
					   " and a.warehouse_no='"+warehouseNo+"' " +
					   " and a.lable_no='"+strSlabel+"' " +
					   " and a.enterprise_no=vbd.enterprise_no " +
					   " and a.article_no=vbd.article_no " +
					   " and a.article_qty-a.real_qty>0 " +
					   " and sai.enterprise_no=a.enterprise_no " +
					   " and sai.article_id=a.article_id " +
					   " and sai.article_no=a.article_no " +
					   " and a.article_no="+strArticleNo+" " +
					   "group by a.lable_no,a.owner_no,vbd.article_no," +
					   "vbd.article_name, sai.barcode, a.packing_qty,a.status,  "+
					   "sai.rsv_batch1,sai.rsv_batch2,sai.rsv_batch3,sai.rsv_batch4," +
					   "sai.rsv_batch5,sai.rsv_batch6,sai.rsv_batch7,sai.rsv_batch8,  "+
					   "sai.quality,sai.produce_date,sai.expire_date,sai.lot_no  ";
			
			List<Odata_CheckLabelDModel> list2=genDao.getListByNativeSql(sql2,Odata_CheckLabelDModel.class);
			if(list2.size()==0){
				return new MsgRes(false,"商品不存在或者已经扫完，请重新扫描！","");
			}
			
			
			//判断扫描基准量
			if(sacnNum>list2.get(0).getArticleQty()-list2.get(0).getRealQty()){
				return new MsgRes(false,"扫描基准量大于计划量，请重新输入！","");	
	    	}
			
			//判断该商品是不是该复核标签的的商品
			if(list2.get(0).getArticleNo().equals(strArticleNo)){
				return new MsgRes(false,"该条码不存在，请重新扫描","");	
			}else{
				//如果封箱标签为空，则取新标签号
				if(strDlabel.equals(""))
				{
					
			       String s1 = "select distinct  "+
			               " a.d_label_no from   "+
			               "  odata_check_label_d a,   "+
			               "  odata_check_m b,   "+
			               " stock_label_m m  "+
			               "  where a.enterprise_no = b.enterprise_no  "+
			               "  and a.warehouse_no = b.warehouse_no  "+
			               "  and a.check_no = b.check_no       "+       
			               "  and a.d_label_no<>'N' and a.status<'13' and a.real_qty>0   "+
			               "  and b.enterprise_no = m.enterprise_no  "+
			               "  and b.warehouse_no = m.warehouse_no  "+
			               "  and b.deliver_obj = m.deliver_obj "+
			               "  and b.check_chute_no='" + strPrinterGroupNo+"'  "+ 
			               "  and m.enterprise_no='" + currEnterpriseNo+"'   "+ 
			               "  and m.warehouse_no='" + warehouseNo+"'   "+ 
			               "  and m.label_no='" + strSlabel+"' " ;
					List<Odata_CheckLabelDModel> li=genDao.getListByNativeSql(s1,Odata_CheckLabelDModel.class);

					//判断是否有未封箱的箱子，如果有则不能新取号
					if(li.size() > 0){
						return new MsgRes(false,"还有箱子未封箱，请先封箱！","");	
					}
					
					MsgRes msgs=tscGetNewLabel(currEnterpriseNo,warehouseNo,
							strSlabel,"B",strWorkerNo);
					strDlabel=msgs.getObj().toString();
				}
			}
			//保存
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
				
			outList.add("S");
			//没有选中
			if(strFlag.equals("0")){
				inList.add(currEnterpriseNo);
				inList.add(warehouseNo);
				inList.add(list.get(0).toString());			
				inList.add(list2.get(0).getArticleNo());
				inList.add(list2.get(0).getPackingQty());
				inList.add(list2.get(0).getQuality());
				inList.add(list2.get(0).getProduceDate());
				inList.add(list2.get(0).getExpireDate());
				inList.add(list2.get(0).getLotNo());
				inList.add(list2.get(0).getRsvBatch1());
				inList.add(list2.get(0).getRsvBatch2());
				inList.add(list2.get(0).getRsvBatch3());
				inList.add(list2.get(0).getRsvBatch4());
				inList.add(list2.get(0).getRsvBatch5());
				inList.add(list2.get(0).getRsvBatch6());
				inList.add(list2.get(0).getRsvBatch7());
				inList.add(list2.get(0).getRsvBatch8());
				inList.add(list2.get(0).getBarcode());
				inList.add(strSlabel);
				inList.add(strDlabel);
				inList.add(sacnNum);
				inList.add(strWorkerNo);//操作人
				inList.add(strPrinterGroupNo);
				
			}else{
				//选中
				inList.add(currEnterpriseNo);
				inList.add(warehouseNo);
				inList.add(list.get(0).toString());			
				inList.add(list2.get(0).getArticleNo());
				inList.add(checkD.getPackingQty());
				inList.add(checkD.getQuality());
				inList.add(checkD.getProduceDate());
				inList.add(checkD.getExpireDate());
				inList.add(checkD.getLotNo());
				inList.add(checkD.getRsvBatch1());
				inList.add(checkD.getRsvBatch2());
				inList.add(checkD.getRsvBatch3());
				inList.add(checkD.getRsvBatch4());
				inList.add(checkD.getRsvBatch5());
				inList.add(checkD.getRsvBatch6());
				inList.add(checkD.getRsvBatch7());
				inList.add(checkD.getRsvBatch8());
				inList.add(list2.get(0).getBarcode());
				inList.add(strSlabel);
				inList.add(strDlabel);
				inList.add(sacnNum);
				inList.add(strWorkerNo);//操作人
				inList.add(strPrinterGroupNo); 
			}	
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_scanArticle_by_labelNo");
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			
			return new MsgRes(true,"操作成功！",strDlabel);	

		}
	
	}
	//取标签的汇总数量
	@Override
	public MsgRes tscBoxQty(String currEnterpriseNo, String warehouseNo,
			String str) throws Exception {
        String sql =" select "+
                "sum(d.real_qty) from"+
          "  odata_check_label_d d where "+
          "   d.d_label_no='"+str+"' "+
          "   and d.enterprise_no='"+currEnterpriseNo+"' "+
          "  and d.warehouse_no='"+warehouseNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		return new MsgRes(true,"操作成功！",list.get(0));	
	}
	// 出货整理打包》封箱
	@Override
	public MsgRes tscArrangeCutbox(String strEnterpriseNo,String strWarehouseNo, 
			String strDLabelNo,
			String strPrinterGroupNo, 
			String strUserId) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strUserId);//操作人员
		inList.add(strDLabelNo);//strDLabelNo
		inList.add(strPrinterGroupNo);//工作站
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_ODATA_CHECK_CUTBOX");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			 throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"操作成功！","");	
	}
	//扫描原标签号
	@Override
	public MsgRes CheckSLabelNo(String strEnterpriseNo,String strWarehouseNo, 
			String strSLabelNo,String workerNo,String strQuery)
			throws Exception {	
		    List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);//strWarehouseNo
			inList.add(strSLabelNo);//strSLabelNo
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_check_SLabelNo");
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}	
			
			String sql=" select m.check_no,m.check_chute_no," +
					   " m.status,'[' || ltrim(m.cust_no) || ']' || c.cust_name as custNo　"+
					   " from odata_check_label_d a, odata_check_m m, bdef_defcust c "+
					   " where a.lable_no='"+strSLabelNo+"' "+
					   " and a.check_no=m.check_no "+
					   " and a.warehouse_no=m.warehouse_no "+
					   " and a.enterprise_no=m.enterprise_no "+
					   " and m.enterprise_no=c.enterprise_no " +
					   " and m.owner_no=c.owner_no " +
					   " and m.cust_no=c.cust_no " +
					   " and a.enterprise_no='"+strEnterpriseNo+"' "+
					   " and a.warehouse_no='"+strWarehouseNo+"' ";
			
			List<Odata_CheckM> list=genDao.getListByNativeSql(sql,Odata_CheckM.class, 0, 10);

		    //如果没有数据则要写复合单
			if(list.size()==0){
				List inList1=new ArrayList();
				List outList1=new ArrayList();
				List resultList1=new ArrayList();
				
				outList1.add("S");
				inList1.add(strEnterpriseNo);
				inList1.add(strWarehouseNo);//strWarehouseNo
				inList1.add(strSLabelNo);//strSLabelNo
				inList1.add(strQuery);//strCheckChuteNo
				inList1.add(workerNo);
				resultList1 = genDao.execProc(inList1, outList1, "PKLG_ODATA_MOVE_JUN.p_creat_odata_check_by_label");
				if(resultList1.get(0).toString().indexOf("N|")!=-1)
				{
					throw new Exception(resultList1.get(0).toString());
				}
				
				//写完复核单数据后取该复合单的数据
				String sql2=" select m.check_no,m.check_chute_no," +
						   " m.status,'[' || ltrim(m.cust_no) || ']' || c.cust_name as custNo　"+
						   " from odata_check_label_d a, odata_check_m m, bdef_defcust c "+
						   " where a.lable_no='"+strSLabelNo+"' "+
						   " and a.check_no=m.check_no "+
						   " and a.warehouse_no=m.warehouse_no "+
						   " and a.enterprise_no=m.enterprise_no "+
						   " and m.enterprise_no=c.enterprise_no " +
						   " and m.owner_no=c.owner_no " +
						   " and m.cust_no=c.cust_no " +
						   " and a.enterprise_no='"+strEnterpriseNo+"' "+
						   " and a.warehouse_no='"+strWarehouseNo+"' ";
				
				list=genDao.getListByNativeSql(sql2,Odata_CheckM.class, 0, 10);
		   }else{
				String check = list.get(0).getCheckChuteNo();
				if(!check.equals(strQuery)){
					return new MsgRes(false,"您扫描的标签号和该码头【"+check+"】不一致，请重新扫描！","");	
				}
		   }
		/*	String sql2="select '[' || ltrim(a.cust_no) || ']' || c.cust_name "+
					"from stock_label_m a,stock_label_d b,bdef_defcust c " +
					 " where a.enterprise_no=b.enterprise_no " +
					   " and a.warehouse_no=b.warehouse_no " +
					   " and a.container_no=b.container_no " +
					   " and b.enterprise_no=c.enterprise_no " +
					   " and b.owner_no=c.owner_no " +
					   " and b.cust_no=c.cust_no " +
					   " and a.label_no='"+strSLabelNo+"' " +
					   " and a.enterprise_no='"+strEnterpriseNo+"' " +
					   " and a.warehouse_no='"+strWarehouseNo+"' ";
		   List<String> list2=genDao.getListByNativeSql(sql2);*/
		   return new MsgRes(true,"操作成功！",list.get(0));	
    }
	//取新标签号
	@Override
	public MsgRes tscGetNewLabel(String strEnterpriseNo,String strWarehouseNo,
			String strLabelNo,String strContainerType,String strWorkerNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strLabelNo);//strLabelNo
		inList.add(strContainerType);//strContainerType
		inList.add(strWorkerNo);//strWorkerNo
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_getnew_containerNo");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		return new MsgRes(true,"操作成功！",resultList.get(0).toString());
	}	
	
	
	//重扫
	@Override
	public MsgRes tscAnewScan(String currEnterpriseNo, String warehouseNo,
			String strDlabel,String strWorkerNo) throws Exception {
		String sql ="select distinct d.check_no from odata_check_label_d d " +
				"where d.d_label_no='"+strDlabel+"' "+
				"and d.enterprise_no='"+currEnterpriseNo+"' "+
		        "and d.warehouse_no='"+warehouseNo+"' " +
		        "and d.status='11' ";
		List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql, Odata_CheckLabelDModel.class);
        if(list.size()==0){
        	return new MsgRes(false,"该标签不存在复核单或已封箱，不能重扫！","");
        }
    	List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(currEnterpriseNo);//strWareHouseNo
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(strWorkerNo);
		inList.add(list.get(0).getCheckNo());
		inList.add(strDlabel);
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_CancelScanLabel");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"操作成功！","");
	}
	
	
	//////////////////////////////////////////////////////////////////////
	@Override
	public MsgRes GetSLabelNoArticle(String strEnterpriseNo,String strWarehouseNo, 
			String strSLabelNo,
			String strBarcode) throws Exception {
		MsgRes msgRes=new MsgRes();
		//根据源标签取相关信息
		String strSql="select distinct d.owner_no from stock_label_m m,stock_label_d d " +
				"where m.enterprise_no=d.enterprise_no " +
				"and m.warehouse_no=d.warehouse_no " +
				"and m.container_no=d.container_no " +
				"and m.enterprise_no='"+strEnterpriseNo+"' " +
				"and m.warehouse_no='"+strWarehouseNo+"' " +
				"and m.label_no='"+strSLabelNo+"'";
		List<Stock_LabelDModel> listsld=genDao.getListByNativeSql(strSql, Stock_LabelDModel.class);
		if(listsld.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该标签号不存在！");
			return msgRes;
		}
		String strOwnerNo=listsld.get(0).getOwnerNo();
		
		//根据扫描的商品条码查找商品信息
		MsgRes msg =getArticleForBarcodeImpl.checkBarcode(strWarehouseNo,
														strBarcode,
														strOwnerNo,
														strEnterpriseNo);
		if(!msg.getIsSucc())
		{
			return msg;
		}		
		
		String strArticleNo=msg.getObj().toString();
		strSql="select    "+
				"        d.warehouse_no,   "+
				"        d.owner_no,   "+
				"        d.article_no,   "+
				"        bab.barcode,   "+
				"        bab.ARTICLE_NAME,   "+
				//"        nvl(bap.spec, '1*' || d.packing_qty || bab.unit) spec,  "+
				"        sai.rsv_batch1,   "+
				"        sai.rsv_batch2,   "+
				"        sai.rsv_batch3,   "+
				"        sai.rsv_batch4,   "+
				"        sai.rsv_batch5,   "+
				"        sai.rsv_batch6,   "+
				"        sai.rsv_batch7,   "+
				"        sai.rsv_batch8,   "+
				"        sai.lot_no,   "+
				"        to_char(sai.expire_date,'yyyymmdd')expire_date,   "+
				"        to_char(sai.produce_date,'yyyymmdd')produce_date,   "+
				"        sai.quality,   "+
				"        sai.import_batch_no,   "+
				"        bab.EXPIRY_DAYS,   "+
				"        nvl(bap.pal_base_qbox,999) ||'*'|| nvl(bap.pal_height_qbox,999) qpalette,   "+
				"        d.packing_qty,   "+
				//"  nvl(bap.packing_unit, decode(d.packing_qty,bab.qmin_operate_packing,bab.qmin_operate_packing_unit,bab.unit)) packing_unit," +
				"        f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit, "+
				"        f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,bab.qmin_operate_packing) packingUnitQmin, "+
				"        f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,bab.unit_packing) Unit, "+
				"        f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec, "+
				"        f_get_spec(d.enterprise_no,d.owner_no,d.article_no,bab.qmin_operate_packing) packingSpecQmin, "+
				"        f_get_spec(d.enterprise_no,d.owner_no,d.article_no,bab.unit_packing) spec, "+
				"        nvl(bap.pal_base_qbox,999) as pal_base_qbox,   "+
				"        nvl(bap.pal_height_qbox,999) as pal_height_qbox," +
				"        bab.QMIN_OPERATE_PACKING, bab.QMIN_OPERATE_PACKING_UNIT,bab.unit_packing,  "+
				"        sum(d.qty) PlanQty   "+
				"    from   "+
				"        stock_label_m m,   "+
				"        stock_label_d d,   "+
				"        stock_article_info sai,   "+
				"        bdef_defarticle bab,   "+
				"        bdef_article_packing bap      "+
				"    where   "+
				"        m.enterprise_no=d.enterprise_no        "+
				"        and m.warehouse_no=d.warehouse_no        "+
				"        and m.container_no=d.container_no        "+
				"        and d.enterprise_no=sai.enterprise_no        "+
				"        and d.article_no=sai.article_no        "+
				"        and d.article_id=sai.article_id       "+
				"        and d.article_no=bab.article_no       "+
				"        and d.article_no=bap.article_no(+)       "+
				"        and d.packing_qty=bap.packing_qty(+)       "+
				"        and d.enterprise_no=bap.enterprise_no(+)       "+
				"  		 and m.enterprise_no='"+strEnterpriseNo+"'   "+
				"  		 and m.warehouse_no='"+strWarehouseNo+"'   "+
				"        and m.label_no='"+strSLabelNo+"'  "+
				"        and d.article_no in ("+strArticleNo+")  "+
				"   group by    "+
				"        d.enterprise_no,bab.unit_packing,d.warehouse_no,   "+
				"        d.owner_no,   "+
				"        d.article_no,   "+
				"        bab.barcode,   "+
				"        bab.ARTICLE_NAME,   "+
				"        bap.spec,  "+
				"        sai.rsv_batch1,   "+
				"        sai.rsv_batch2,   "+
				"        sai.rsv_batch3,   "+
				"        sai.rsv_batch4,   "+
				"        sai.rsv_batch5,   "+
				"        sai.rsv_batch6,   "+
				"        sai.rsv_batch7,   "+
				"        sai.rsv_batch8,   "+
				"        sai.lot_no,   "+
				"        to_char(sai.expire_date,'yyyymmdd'),   "+
				"        to_char(sai.produce_date,'yyyymmdd'),   "+
				"        sai.quality,   "+
				"        sai.import_batch_no,   "+
				"        bab.EXPIRY_DAYS,   "+
				"        nvl(bap.pal_base_qbox,999) ||'*'|| nvl(bap.pal_height_qbox,999),   "+
				"        d.packing_qty,   "+
				"        bap.packing_unit,   "+
				"        nvl(bap.pal_base_qbox,999) ,   "+
				"        nvl(bap.pal_height_qbox,999) ," +
				"        bab.QMIN_OPERATE_PACKING, bab.QMIN_OPERATE_PACKING_UNIT,bab.unit"+
				"    order by   "+
				"        d.article_no,   "+
				"        d.packing_qty,   "+
				"        sai.lot_no,   "+
				"        to_char(sai.expire_date,'yyyymmdd'),   "+
				"        to_char(sai.produce_date,'yyyymmdd')";						
		List<OdataGetSLabelNoArticleAnswerModel> list=genDao.getListByNativeSql(strSql, OdataGetSLabelNoArticleAnswerModel.class);
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
	//容器整理RF
	@Override
	public MsgRes tscArrange(String strEnterpriseNo,String strWarehouseNo,
			String strSLabelNo,
			String strDLabelNo,
			String strArticleNo,
			Double nPackingQty,
			Double nMoveQty,
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
			String strUserId,
			String strTerminalFlag) throws Exception {
		MsgRes msgRes=new MsgRes();
		//取源标签号内部号
		msgRes=GetLabelNoContainerNo(strEnterpriseNo,strWarehouseNo, 
				strSLabelNo);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("取源标签内部号错误！");
		}
		String strSContainerNo=msgRes.getObj().toString();
		//取目的标签号内部号
	/*	msgRes=GetLabelNoContainerNo(strEnterpriseNo,strWarehouseNo, 
				strDLabelNo);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("取目的标签内部号错误！");
		}
		String strDContainerNo=msgRes.getObj().toString();		*/
		//调用过程
		msgRes=tscPContainerArrange(strEnterpriseNo,strWarehouseNo, 
				strSLabelNo, 
				strDLabelNo, 
				strArticleNo, 
				nPackingQty, 
				nMoveQty, 
				strBatchNo, 
				strQuality, 
				dtProduceDate, 
				dtExpireDate, 
				strLotNo, 
				strRsvBatch1, 
				strRsvBatch2, 
				strRsvBatch3, 
				strRsvBatch4, 
				strRsvBatch5, 
				strRsvBatch6, 
				strRsvBatch7, 
				strRsvBatch8, 
				strUserId, 
				strTerminalFlag);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("p_container_arrange发生异常！");
		}
		//检查源标签是否已全整理
		msgRes=isFinishComplete(strEnterpriseNo,strWarehouseNo, 
				strSContainerNo);
		return msgRes;
	}
	//取标签内部号
	@Override
	public MsgRes GetLabelNoContainerNo(String strEnterpriseNo,String strWarehouseNo,
			String strLabelNo) throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="select * from stock_label_m m " +
				"where m.enterprise_no='"+strEnterpriseNo+"' " +
				        "and m.warehouse_no='"+strWarehouseNo+"' " +
						"and m.label_no='"+strLabelNo+"'";
		
		List<Stock_LabelMModel> list=genDao.getListByNativeSql(strSql, Stock_LabelMModel.class);
		if(list.size()==0)
		{
			msg.setIsSucc(false);
			msg.setMsg("该标签号不存在！");
			return msg;
		}
		msg.setIsSucc(true);
		msg.setObj(list.get(0).getContainerNo());
		return msg;
	}
	
	//检查源容器是否已整理完毕
	@Override
	public MsgRes isFinishComplete(String strEnterpriseNo,String strWarehouseNo, String strSLabelNo)
			throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="select nvl(sum(sld.qty),0) qty  from stock_label_d sld " +
				"where sld.enterprise_no='"+strEnterpriseNo+"' " +
				        "and sld.warehouse_no='"+strWarehouseNo+"' " +
						"and sld.container_no='"+strSLabelNo+"'";
		
		List<Stock_LabelDModel> list=genDao.getListByNativeSql(strSql, Stock_LabelDModel.class);
		CommSingleDataRequestModel resMod=new CommSingleDataRequestModel();
		if(list.get(0).getQty()==0)
		{
			resMod.setReqObj("1"); //0-源标签还有未整理完成数据，1-源标签没有未整理完成数据
		}else
		{
			resMod.setReqObj("0"); 
		}
		msg.setIsSucc(true);
		msg.setObj(JSON.toJSONString(resMod));
		return msg;
	}
	
	/**
	 * 整理确认-检查
	 */
	@Override
	public MsgRes tscPOM_AerrangeConfirmScanLabel(String strEnterpriseNo,String strWarehouseNo, String strLabelNo,String strWorkerNo,String custNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(custNo==null?"N":custNo);
		inList.add(strLabelNo);
		resultList = genDao.execProc(inList, outList, "PKOBJ_HB.P_CheckCustLabelScan");
		if(resultList.get(0).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		
		String strSql="select a.deliver_obj,a.line_no,t.*,nvl(t1.article_yes_sumQty, 0) as article_yes_sumQty," +
				"nvl(t1.all_yes_sumQty, 0) as all_yes_sumQty " +
				"from " +
				"(select warehouse_no,enterprise_no, label_no, deliver_obj, line_no " +
				"from stock_label_m " +
				"where enterprise_no='"+strEnterpriseNo+"' " +
				"and warehouse_no = '"+strWarehouseNo+"' " +
				"and label_no = '"+strLabelNo+"') a," +
				"(select deliver_obj as source_no,owner_no,article_no,label_no,warehouse_no,enterprise_no, " +
				"floor(nvl(qty, 0) / packing_qty) article_not_sumQty," +
				"sum(floor(nvl(qty, 0) / packing_qty)) over(partition by deliver_obj) all_not_sumQty " +
				"from " +
				"(select slm.deliver_obj,sld.article_no,sld.owner_no,slm.label_no," +
				"slm.warehouse_no,slm.enterprise_no,sum(nvl(qty, 0)) as qty,sld.packing_qty " +
				"from stock_label_d sld,stock_label_m slmm,stock_label_m slm,bdef_defarticle bda " +
				"where slm.warehouse_no = slmm.warehouse_no " +
				"and slm.container_no = slmm.owner_container_no " +
				"and slmm.warehouse_no = sld.warehouse_no " +
				"and slmm.container_no = sld.container_no " +
				"and sld.enterprise_no = '"+strEnterpriseNo+"' " +
				"and sld.warehouse_no = '"+strWarehouseNo+"' " +
				"and sld.container_type <> 'B' " +
				"and bda.owner_no = sld.owner_no " +
				"and bda.article_no = sld.article_no " +
				"and bda.scan_flag = 1 " +
				"and slm.label_no = '"+strLabelNo+"' " +
				"group by slm.deliver_obj,sld.article_no,sld.owner_no," +
				"slm.label_no,slm.warehouse_no,slm.enterprise_no,sld.packing_qty)) t," +
				"(select article_no,label_no,warehouse_no,enterprise_no,nvl(article_yes_sumQty, 0) as article_yes_sumQty," +
				"sum(nvl(article_yes_sumQty, 0)) over(partition by warehouse_no, label_no) all_yes_sumQty " +
				"from (select count(distinct scan_barcode) as article_yes_sumQty,warehouse_no,label_no," +
				"article_no,enterprise_no from bdef_scan_log " +
				"where enterprise_no = '"+strEnterpriseNo+"' " +
				"and warehouse_no = '"+strWarehouseNo+"' " +
				"and label_no = '"+strLabelNo+"' " +
				"and scan_barcode = merge_box_no group by enterprise_no,warehouse_no, label_no, article_no)) t1 " +
				"where t.enterprise_no(+)=a.enterprise_no and t.warehouse_no(+) = a.warehouse_no and t.label_no(+) = a.label_no " +
				"and t1.enterprise_no(+)=t.enterprise_no and t1.warehouse_no(+) = t.warehouse_no and t1.label_no(+) = t.label_no " +
				"and t1.article_no(+) = t.article_no";
		List<OdataArrangeConfirmScanLabelAnswerModel> list=genDao.getListByNativeSql(strSql,OdataArrangeConfirmScanLabelAnswerModel.class,0, 1000);
		if(list.size()==0){
			return new MsgRes(false,"该标签不存在","该标签不存在");
		}else{
			return new MsgRes(true,"",JSON.toJSONString(list));
		}
	}
	
	/**
	 * 整理确认
	 */
	@Override
	public MsgRes tscPOM_AerrangeConfirm(String strEnterpriseNo,String strWarehouseNo,String strLabelNo,String strWorkerNo)throws Exception{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strLabelNo);
		inList.add(strWorkerNo);
		resultList = genDao.execProc(inList, outList, "PKOBJ_HB.p_OM_AerrangeConfirm");
		if(resultList.get(0).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"","");
	}

	private MsgRes tscPContainerArrange(String strEnterpriseNo,String strWarehouseNo,
			String strSLabelNo, 
			String strDLabelNo, 
			String strArticleNo,
			Double nPackingQty,
			Double nMoveQty,
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
			String strUserId,
			String strTerminalFlag) throws Exception
	{
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strSLabelNo);//strSLabelNo
		inList.add(strDLabelNo);//strDLabelNo
		inList.add("N");//容器整理不需要传配送对象 huangb 20160812
		inList.add(strArticleNo);//strArticleNo
		inList.add(nPackingQty);//nPackingQty
		inList.add(nMoveQty);//nMoveQty
		inList.add(strQuality);//strQuality
		inList.add(dtProduceDate);//dtProduceDate
		inList.add(dtExpireDate);//dtExpireDate
		inList.add(strLotNo);//strWareHouseNo
		inList.add(strRsvBatch1);//strRsvBatch1
		inList.add(strRsvBatch2);//strRsvBatch2
		inList.add(strRsvBatch3);//strRsvBatch3
		inList.add(strRsvBatch4);//strRsvBatch4
		inList.add(strRsvBatch5);//strRsvBatch5
		inList.add(strRsvBatch6);//strRsvBatch6
		inList.add(strRsvBatch7);//strRsvBatch7
		inList.add(strRsvBatch8);//strRsvBatch8
		inList.add(strUserId);//strUserId
		inList.add(strTerminalFlag);//strTerminalFlag
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_container_arrange");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"操作成功！","");
	}
	@Override
	public MsgRes tscOmArrangeBoxs(String strEnterpriseNo,String strWarehouseNo,
			String strSLabelNo,
			String strDLabelNo,
			String strArticleNo,
			Double nPackingQty,
			Double nMoveQty,
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
			String strUserId,
			String strTerminalFlag)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		//取源标签号内部号
	/*	msgRes=GetLabelNoContainerNo(strEnterpriseNo,strWarehouseNo, 
				strSLabelNo);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("取源标签内部号错误！");
		}
		String strSContainerNo=msgRes.getObj().toString();
		//取目的标签号内部号
		msgRes=GetLabelNoContainerNo(strEnterpriseNo,strWarehouseNo, 
				strDLabelNo);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("取目的标签内部号错误！");
		}
		String strDContainerNo=msgRes.getObj().toString();	*/	
		//调用过程
		msgRes=tscPContainerArrange(strEnterpriseNo,strWarehouseNo, 
				strSLabelNo,
				strDLabelNo,
				strArticleNo, 
				nPackingQty, 
				nMoveQty, 
				strBatchNo, 
				strQuality, 
				dtProduceDate, 
				dtExpireDate, 
				strLotNo, 
				strRsvBatch1, 
				strRsvBatch2, 
				strRsvBatch3, 
				strRsvBatch4, 
				strRsvBatch5, 
				strRsvBatch6, 
				strRsvBatch7, 
				strRsvBatch8, 
				strUserId, 
				strTerminalFlag);
		if(!msgRes.getIsSucc())
		{
			throw new Exception("p_container_arrange发生异常！");
		}
		return msgRes;
	}
	@Override
	public MsgRes tscCheckLableCancel(String strEnterpriseNo,String strWarehouseNo, String strLabelNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strLabelNo);//strLabelNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.PROC_Check_LABEL_CANCEL");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"操作成功！","");
	}
	@Override
	public MsgRes tscLableCancel(String strEnterpriseNo,String strWarehouseNo, String strLabelNo,
			String strUserId) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strLabelNo);//strLabelNo
		inList.add(strUserId);//strUserId
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.PROC_LABEL_CANCEL");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"操作成功！","");
	}
	
	/* RF 检验SKU标签销毁  
	 */
	@Override
	public MsgRes tscCheckSkuLableCancel(String strEnterpriseNo,String strWarehouseNo, String strLabelNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strLabelNo);//strLabelNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.Proc_CheckSku_Label_cancel");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		return new MsgRes(true,"操作成功！",resultList.get(0).toString());
	}
	
	/* RF 扫描SKU标签销毁商品 
	 */
	@Override
	public MsgRes tscScanSkuCancel(String strRecvData) throws Exception {
		OdataSkuLabelCancelReq reqMod=JSON.parseObject(strRecvData, OdataSkuLabelCancelReq.class);		
		MsgRes msgRes=new MsgRes();
 
		//根据扫描的商品条码查找商品信息
		msgRes =getArticleForBarcodeImpl.checkBarcode(reqMod.getWareHouseNo(),reqMod.getBarcode(),reqMod.getEnterpriseNo());			
		   if(!msgRes.getIsSucc())
			{
					msgRes.setIsSucc(false);
					msgRes.setMsg("输入条码有误！");
					return msgRes;
			}
			if(msgRes.getObj().toString().equals("") || msgRes.getObj() == null)
			{
					msgRes.setIsSucc(false);
					msgRes.setMsg("输入条码不存在！");
					return msgRes;
			}
 
			MsgRes resultRes=new MsgRes();
		// 2. 
		String sql = " select slm.label_no,sum(sld.qty) qty, atc.article_name,sld.article_no,sld.packing_qty packQty,ROW_NUMBER() OVER(PARTITION BY slm.label_no  ORDER BY slm.label_no) numId,"+
		"to_char(sai.produce_date,'yyyy-mm-dd') produce_date,to_char(sai.expire_date,'yyyy-mm-dd') expire_date " +
		",atc.qmin_operate_packing,atc.Unit_Packing," +
		" f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingUnit," +
		" f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,atc.qmin_operate_packing) packingUnitQmin," +
		" f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,atc.unit_packing) Unit," +
		" f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingSpec," +
		" f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,atc.qmin_operate_packing) packingSpecQmin," +
		" f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,atc.unit_packing) spec" +
				"from stock_label_m slm,stock_label_d sld,stock_article_info sai,bdef_defarticle atc " +
				"where slm.enterprise_no=sld.enterprise_no and   sld.article_no=atc.article_no  " +
				" AND sld.enterprise_no=atc.enterprise_no "+
				"and slm.warehouse_no=sld.warehouse_no and slm.container_no=sld.container_no " +
				"and sld.article_no=sai.article_no and sld.article_id=sai.article_id "+
				"and slm.enterprise_no='"+reqMod.getEnterpriseNo()+"' and sld.warehouse_no='"+reqMod.getWareHouseNo()+"'"+
				"and slm.label_no='"+reqMod.getLabelNo()+"' and (sld.article_no='"+reqMod.getBarcode()+"'  or atc.barcode='"+reqMod.getBarcode()+"'  OR atc.article_identifier='"+reqMod.getBarcode()+"')  "+
				"group by slm.label_no, sld.article_no,sld.packing_qty,sai.produce_date,sai.expire_date,atc.article_name" +
				",sld.enterprise_no,sld.owner_no,atc.qmin_operate_packing,atc.Unit_Packing ";
	 
	    List<OdataSkuLabelCancelModel> result=genDao.getListByNativeSql(sql,OdataSkuLabelCancelModel.class,0, 1000);
		 
		if(result.size()==0)
		{			
				return new MsgRes(false,"该条码不正确或不存在商品","该条码不正确或不存在商品");			 
		}
		 			 
		resultRes.setIsSucc(true); 
		resultRes.setMsg("成功");
		resultRes.setObj(JSON.toJSON(result));		 
		return resultRes;
	}
	
	/* RF 扫描SKU标签销毁商品不管生产日期 
	 */
	@Override
	public MsgRes tscScanSkuCancelWithoutDate(String strRecvData) throws Exception {
		OdataSkuLabelCancelReq reqMod=JSON.parseObject(strRecvData, OdataSkuLabelCancelReq.class);		

			MsgRes resultRes=new MsgRes();		
		String sql = " select slm.label_no,sum(sld.qty) qty, atc.article_name,sld.article_no,sld.packing_qty packQty,ROW_NUMBER() OVER(PARTITION BY slm.label_no  ORDER BY slm.label_no) numId " +
				"  ,atc.qmin_operate_packing,atc.Unit_Packing," +
				" f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingUnit," +
				" f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,atc.qmin_operate_packing) packingUnitQmin," +
				" f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,atc.unit_packing) Unit," +
				" f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingSpec," +
				" f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,atc.qmin_operate_packing) packingSpecQmin," +
				" f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,atc.unit_packing) spec  "+         
         "from stock_label_m slm,stock_label_d sld,bdef_defarticle atc  "+  
         " where slm.enterprise_no=sld.enterprise_no and   sld.article_no=atc.article_no  "+   
         " AND sld.enterprise_no=atc.enterprise_no "+  
         " and slm.warehouse_no=sld.warehouse_no and slm.container_no=sld.container_no  "+           
        " and slm.enterprise_no='"+reqMod.getEnterpriseNo()+"' and sld.warehouse_no='"+reqMod.getWareHouseNo()+"'"+
        " and slm.label_no='"+reqMod.getLabelNo()+"' and (sld.article_no='"+reqMod.getBarcode()+"'  or atc.barcode='"+reqMod.getBarcode()+"'  OR atc.article_identifier='"+reqMod.getBarcode()+"'  ) "+  
        " group by slm.label_no, sld.article_no,sld.packing_qty, atc.article_name " +
        ",atc.qmin_operate_packing,atc.Unit_Packing ,sld.enterprise_no,sld.owner_no  ";
	 
	    List<OdataSkuLabelCancelModel> result=genDao.getListByNativeSql(sql,OdataSkuLabelCancelModel.class,0, 1000);
		 
		if(result.size()==0)
		{			
				return new MsgRes(false,"该条码不正确或不存在商品","该条码不正确或不存在商品");			 
		}
		 			 
		resultRes.setIsSucc(true); 
		resultRes.setMsg("成功");
		resultRes.setObj(JSON.toJSON(result));		 
		return resultRes;
	}
	
	
	/* RF  SKU标签销毁  
	 */
	@Override
	public MsgRes tscSkuLableCancel(OdataSkuLabelCancelModel reqSkuLabelCancel )
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
 
		
		inList.add(reqSkuLabelCancel.getEnterpriseNo());
		inList.add(reqSkuLabelCancel.getWareHouseNo()); 
		inList.add(reqSkuLabelCancel.getLabelNo()); 
		inList.add(reqSkuLabelCancel.getArticleNo());
		inList.add(reqSkuLabelCancel.getPackQty()); 
		inList.add(DateUtil.GetDate(reqSkuLabelCancel.getProduceDate(), "yyyy-MM-dd")==null?
				DateUtil.GetDate("19000101", "yyyy-MM-dd"):DateUtil.GetDate(reqSkuLabelCancel.getProduceDate(), "yyyy-MM-dd"));
		inList.add(DateUtil.GetDate(reqSkuLabelCancel.getExpireDate(), "yyyy-MM-dd")==null?
				DateUtil.GetDate("19000101", "yyyy-MM-dd"):DateUtil.GetDate(reqSkuLabelCancel.getExpireDate(), "yyyy-MM-dd"));
	 
	 
		inList.add(reqSkuLabelCancel.getCancelQty());
		inList.add(reqSkuLabelCancel.getWorkerNo());
		inList.add(reqSkuLabelCancel.getLabelProduceFlag());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_Sku_lab_cancel");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"操作成功！",resultList);
	}
	
	//输入目标标签号校验
	@Override
	public MsgRes tscExistsDLabelNo(String strEnterpriseNo,String strWarehouseNo, 
				String strSLabelNo, 
				String strDLabelNo)
				throws Exception {		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
			
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWarehouseNo
		inList.add(strSLabelNo);//strSLabelNo
		inList.add(strDLabelNo);//strDLabelNo	
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_contrast_containerNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"操作成功！","");		
	}
	
	//出货整理打包》扫描台检验(复核公用)
	@Override
	public MsgRes checkDock(String strEnterpriseNo, String strWarehouseNo,
			String str) throws Exception {
		String strSql="select a.workstation_no " +
				"from PNTSET_PRINTER_WORKSTATION a " +
				"where a.warehouse_no= '"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' "+
				"and a.workstation_no = '"+str+"' ";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0){
			return new MsgRes(false,"该扫描台不存在","该扫描台不存在在");
		}else{
			return new MsgRes(true,"",JSON.toJSONString(list));
		}
	}
	//校验码头号
	@Override
	public MsgRes CheckDock(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		
		String strSql="select * " +
				  "from " +
						"PNTSET_PRINTER_WORKSTATION a " +
				  "where " +
						"a.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
						"and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
						"and a.workstation_no='"+reqMod.getReqObj()+"'";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("码头不存在！");
		}else{
			msgRes.setIsSucc(true);
		}
		
		return msgRes;
	}
	//打印目的容器
	@Override
	public MsgRes tscPrintDLabelNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		ReqArrangePrintDLabel reqMod=JSON.parseObject(strRecvData, ReqArrangePrintDLabel.class);
		//校验目的容器号
		String strSql="select 1 from stock_label_m a " +
				" where a.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
				" and a.warehouse_no = '"+reqMod.getWareHouseNo()+"'" +
				" and a.label_no = '"+reqMod.getDLabelNo()+"'" +
				" and a.use_type = '1'";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("目的容器不存在，或者目的标签有误！");
			return msgRes;
		}
		//写打印任务
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWareHouseNo());//strWarehouseNo
		inList.add(reqMod.getDLabelNo());//strSLabelNo
		inList.add("0");//strSLabelNo
		inList.add("OM7600CM");//reportId
		inList.add(reqMod.getDockNo());//DockNo
		inList.add("0");//
		inList.add(reqMod.getUserID());
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_PRINTTASK.p_insert_PrintGrouptaskmaster");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}	
		else
		{
			inList=new ArrayList();
			outList=new ArrayList();
			resultList=new ArrayList();

			outList.add("S");
			outList.add("S");
			
			inList.add(reqMod.getEnterpriseNo());
			inList.add(reqMod.getWareHouseNo());//strWarehouseNo
			inList.add(reqMod.getDLabelNo());//strSLabelNo
			inList.add("0");//strSLabelNo
			inList.add("OM8000CM");//reportId
			inList.add(reqMod.getDockNo());//DockNo
			inList.add("0");//
			inList.add(reqMod.getUserID());
			
			resultList = genDao.execProc(inList, outList, "PKOBJ_PRINTTASK.p_insert_PrintGrouptaskmaster");
			if(resultList.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(1).toString());
			}	
			else
			{
				msgRes.setIsSucc(true);
				msgRes.setMsg("发送打印任务成功");
				return msgRes;
			}
		}
	}
	@Override
	public MsgRes tscPOM_AerrangeConfirmCheckCustNo(String strRecvData) {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		String sql="select a.cust_no from bdef_defcust a" +
				  "  where a.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
				  "    and a.cust_no='"+reqMod.getReqObj()+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("客户不存在！");
			return msgRes;
		}

		msgRes.setIsSucc(true);
		msgRes.setMsg("");
		return msgRes;
	}
	@Override
	public MsgRes tscReceipt(String currEnterpriseNo, String warehouseNo,
			String strSlabel) throws Exception {
		
		String getCheckNo=" select distinct a.check_no from odata_check_label_d a " +
				          "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				          "    and a.warehouse_no='"+warehouseNo+"' " +
				          "    and a.lable_no='"+strSlabel+"' ";
		
		List list = genDao.getListByNativeSql(getCheckNo);
		
		if(list.size()==0){
			return new MsgRes(false,"该标签找不到对应的单据","");
		}
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(list.get(0));
       
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_PROC_Receipt");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}	
		
//       String insertSql="";
//       
//       insertSql=" insert into odata_check_dhty(enterprise_no,warehouse_no,owner_no,exp_no,exp_type,exp_date,check_no,article_no," +
//       		     "     packing_qty,plan_qty,article_qty,real_qty,status,rgst_name,rgst_date,check_name,check_date) " +
//       		     " select a.enterprise_no,a.warehouse_no,a.owner_no,a.exp_no,a.exp_type,a.exp_date,a.check_no,a.article_no, " +
//       		     "        a.packing_qty,a.plan_qty,a.article_qty,a.real_qty,'13',a.rgst_name,a.rgst_date,a.check_name,a.check_date " +
//       		     "   from odata_check_d a where a.enterprise_no='"+currEnterpriseNo+"' " +
//		         "    and a.warehouse_no='"+warehouseNo+"' " +
//		         "    and a.check_no='"+list.get(0)+"' ";
//       genDao.exceuteSql(insertSql);
//       
//       insertSql=" insert into odata_check_mhty(enterprise_no,warehouse_no,owner_no,check_no,operate_date,batch_no,cust_no, " +
//       		     "     status,check_chute_no,deliver_obj,line_no,curr_area,rgst_name,rgst_date,updt_name,updt_date) " +
//       		     " select a.enterprise_no,a.warehouse_no,a.owner_no,a.check_no,a.operate_date,a.batch_no,a.cust_no, " +
//       		     "     '13',a.check_chute_no,a.deliver_obj,a.line_no,a.curr_area,a.rgst_name,a.rgst_date,a.updt_name,a.updt_date " +
//       		     "  from odata_check_m a where a.enterprise_no='"+currEnterpriseNo+"' " +
//		         "   and a.warehouse_no='"+warehouseNo+"' " +
//		         "   and a.check_no='"+list.get(0)+"' ";
//       genDao.exceuteSql(insertSql);
//       
//       insertSql=" insert into odata_check_label_dhty(enterprise_no,warehouse_no,owner_no,check_no,row_id,lable_no,divide_id, " +
//       		     "   container_no,exp_no,exp_type,exp_date,article_no,packing_qty,article_id,article_qty,real_qty,status, " +
//       		     "   check_name,check_date)" +
//       		     " select a.enterprise_no,a.warehouse_no,a.owner_no,a.check_no,a.row_id,a.lable_no,a.divide_id, " +
//       		     "   a.container_no,a.exp_no,a.exp_type,a.exp_date,a.article_no,a.packing_qty,a.article_id,a.article_qty,a.real_qty,'13', " +
//       		     "   a.check_name,a.check_date " +
//       		     "  from odata_check_label_d a where a.enterprise_no='"+currEnterpriseNo+"' " +
//		         "   and a.warehouse_no='"+warehouseNo+"' " +
//		         "   and a.check_no='"+list.get(0)+"' ";
//       genDao.exceuteSql(insertSql);
//       
//       String deleteSql="";
//       
//       deleteSql=" delete from odata_check_label_d a " +
//       		     "  where a.enterprise_no='"+currEnterpriseNo+"' " +
//       		     "    and a.warehouse_no='"+warehouseNo+"' " +
//    		     "    and a.check_no='"+list.get(0)+"' ";
//       genDao.exceuteSql(deleteSql);
//       
//       deleteSql=" delete from odata_check_d a " +
//		         "  where a.enterprise_no='"+currEnterpriseNo+"' " +
//		         "    and a.warehouse_no='"+warehouseNo+"' " +
//	             "    and a.check_no='"+list.get(0)+"' ";
//       genDao.exceuteSql(deleteSql);
//       
//       deleteSql=" delete from odata_check_m a " +
//		         "  where a.enterprise_no='"+currEnterpriseNo+"' " +
//		         "    and a.warehouse_no='"+warehouseNo+"' " +
//	             "    and a.check_no='"+list.get(0)+"' ";
//       genDao.exceuteSql(deleteSql);
       
       
       return new MsgRes(true,"回单成功","");
	}

	/* 容器整理取号
	 *   参数。企业编号，仓别，源标签号，类型（栈板P或物流箱B），码头号，当前操作人
	 */
	@Override
	public MsgRes GetContainerArrangeNO(String strEnterpriseNo,String strWarehouseNo,String strSLabelNo,
			String strmType,String strDockNo,String strWorkerNo)
			throws Exception {	
		MsgRes msg= new MsgRes();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);//strWarehouseNo
		inList.add(strSLabelNo);//strSLabelNo			
		inList.add(strDockNo);// 		
		inList.add(strmType);// 	
		inList.add(strWorkerNo);//
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.GetDeliverObjLabelAndPrint");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			msg.setIsSucc(false); 
			msg.setMsg(resultList.get(1).toString());
			return msg; 
		}
		
		OdataContainerArrangeGetNOModel md =new OdataContainerArrangeGetNOModel();
		md.setDlabelNo(resultList.get(0).toString());
		msg.setIsSucc(true); 
		msg.setObj(JSON.toJSON(md));			
		return msg;
  
	}
	
	/* RF 标签检查  Add by sunl 2016年2月25日
	 * 传入参数为ODataCheckLabelSelectModel中的LabelNo
	 * 1. 校验当前传入的标签是否有效
	 * 2. 检查当前的标签是否有标签明细
	 *    2.1 没有标签明细则查询出当前标签下所有的子标签信息ODataCheckSubLabelModel
	 *    2.2 有标签明细则查出当前标签下所有的标签明细信息ODataCheckLabelDetailModel
	 * 3. 返回到界面信息为ODataCheckLabelSelectModel
	 */
	@Override
	public MsgRes tscCheckLabel(String strRecvData) {
		ODataCheckLabelSelectModel reqMod=JSON.parseObject(strRecvData, ODataCheckLabelSelectModel.class);
		MsgRes msgRes=new MsgRes();
		List<ODataCheckSubLabelModel> msub = null;//子标签信息
		List<ODataCheckLabelDetailModel> mdetail = null;//标签明细信息
		String usetype = "1";//默认1 类型 1：子标签 ；2.标签明细
		String labelNo = reqMod.getLabelNo();//当前的标签号
		String warehouseNo = reqMod.getWarehouseNo();//当前的仓别
		String enterpriseNo = reqMod.getEnterpriseNo();//当前的企业编码

		// 1. 校验当前的标签是否有效
		String sql1=" SELECT M.LABEL_NO FROM STOCK_LABEL_M M WHERE M.LABEL_NO = '"+labelNo+"' AND M.WAREHOUSE_NO = '"+ warehouseNo+"' AND M.ENTERPRISE_NO = '"+enterpriseNo+"' ";
		List list1 = genDao.getListByNativeSql(sql1);
		
		if(list1.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("标签无效！");
			return msgRes;
		}

		// 2. 检查当前的标签是否有标签明细
		String sql2 = " SELECT M.LABEL_NO FROM STOCK_LABEL_M M,STOCK_LABEL_D D " +
				"WHERE M.WAREHOUSE_NO = D.WAREHOUSE_NO " +
				"AND M.ENTERPRISE_NO = D.ENTERPRISE_NO " +
				"AND M.CONTAINER_NO = D.CONTAINER_NO " +
				"AND M.LABEL_NO = '"+labelNo+"'  AND M.WAREHOUSE_NO = '"+ warehouseNo+"' AND M.ENTERPRISE_NO = '"+enterpriseNo+"' ";
		List list2 = genDao.getListByNativeSql(sql2);
		//List<OdataArrangeConfirmScanLabelAnswerModel> list=genDao.getListByNativeSql(strSql,OdataArrangeConfirmScanLabelAnswerModel.class,0, 1000);
		if(list2.size()==0)
		{
			//2.1 没有标签明细信息
			String sql21 = " SELECT M1.LABEL_NO,M2.LABEL_NO SUBLABELNO,M1.STATUS,V.TEXT STATUSTEXT ,M.SUBLABELCOUNT, ROWNUM NUMID  " +
					" ,COUNT(DISTINCT D.ARTICLE_NO) ITEMCOUNT, SUM(D.QTY) ITEMTOTALQTY  " +
					" FROM  STOCK_LABEL_M M1, STOCK_LABEL_M M2,STOCK_LABEL_D D,WMS_DEFFIELDVAL V  " +
					" ,(SELECT M1.LABEL_NO,M1.WAREHOUSE_NO,M1.ENTERPRISE_NO,COUNT(M2.LABEL_NO) SUBLABELCOUNT FROM STOCK_LABEL_M M1,STOCK_LABEL_M M2 WHERE M1.LABEL_NO = '"+labelNo+"'" +
					" AND  M1.WAREHOUSE_NO = M2.WAREHOUSE_NO AND M1.ENTERPRISE_NO = M2.ENTERPRISE_NO " +
					" AND M1.CONTAINER_NO = M2.OWNER_CONTAINER_NO AND M1.LABEL_NO <> M2.LABEL_NO GROUP BY M1.LABEL_NO,M1.WAREHOUSE_NO,M1.ENTERPRISE_NO ) M" +
					"  WHERE M1.WAREHOUSE_NO = M2.WAREHOUSE_NO AND M1.ENTERPRISE_NO = M2.ENTERPRISE_NO  " +
					"  AND M1.CONTAINER_NO = M2.OWNER_CONTAINER_NO AND M1.LABEL_NO <> M2.LABEL_NO " +
					" AND M1.LABEL_NO = M.LABEL_NO AND M1.WAREHOUSE_NO = M.WAREHOUSE_NO AND M1.ENTERPRISE_NO = M.ENTERPRISE_NO  " +
					"  AND M2.WAREHOUSE_NO = D.WAREHOUSE_NO AND M2.ENTERPRISE_NO = D.ENTERPRISE_NO AND M2.CONTAINER_NO = D.CONTAINER_NO " +
					" AND V.TABLE_NAME = 'STOCK_LABEL_M' AND V.COLNAME = 'STATUS' AND M1.STATUS = V.VALUE " +
					" AND M1.LABEL_NO = '"+labelNo+"'  AND M1.WAREHOUSE_NO = '"+ warehouseNo+"' AND M1.ENTERPRISE_NO = '"+enterpriseNo+"' " +
					" GROUP BY M1.LABEL_NO,M2.LABEL_NO,M.SUBLABELCOUNT,M1.STATUS,V.TEXT,ROWNUM  ";
			msub = genDao.getListByNativeSql(sql21,ODataCheckSubLabelModel.class,0, 1000);
			
			if(msub.size()==0){
				return new MsgRes(false,"该标签不存在","该标签不存在");
			} 
		}
		else
		{
			usetype = "2";
			//2.2 有标签明细信息
			String sql22 =" SELECT r.*,a.BARCODE, a.ARTICLE_IDENTIFIER, a.ARTICLE_NAME,a.qmin_operate_packing,a.qmin_operate_packing_unit,"+
					   " V.TEXT STATUSTEXT," +
					   //"nvl(bap.packing_unit, decode(sld.packing_qty,a.qmin_operate_packing,a.qmin_operate_packing_unit,a.unit)) packing_unit," +
					   "f_get_packingunit(r.enterprise_no,r.owner_no,r.article_no,r.packing_qty) packingUnit,"+
					   "f_get_packingunit(r.enterprise_no,r.owner_no,r.article_no,a.qmin_operate_packing) packingUnitQmin,"+
					   "f_get_packingunit(r.enterprise_no,r.owner_no,r.article_no,a.unit_packing) Unit,"+
					   "f_get_spec(r.enterprise_no,r.owner_no,r.article_no,r.packing_qty) packingSpec,"+
					   "f_get_spec(r.enterprise_no,r.owner_no,r.article_no,a.qmin_operate_packing) packingSpecQmin,"+
					   "f_get_spec(r.enterprise_no,r.owner_no,r.article_no,a.unit_packing) spec,"+
					   "wk.worker_name,ROWNUM NUMID"+
					   " FROM (select slm.label_no,sld.owner_no,slm.warehouse_no,slm.enterprise_no,sld.source_no,sld.article_no,"+
					   " sld.packing_qty,sld.status,sum(sld.qty) ARTICLEQTY,ood.s_cell_no,ood.outstock_name"+
					   " from stock_label_m slm,stock_label_d sld,odata_outstock_dhty ood"+
					   " where slm.enterprise_no=sld.enterprise_no and slm.warehouse_no=sld.warehouse_no"+
//					   "  and slm.source_no=sld.source_no " +
					   " and slm.container_no=sld.container_no "+
					   "  and sld.enterprisE_no=ood.enterprisE_no(+)  and sld.warehouse_no=ood.warehouse_no(+)"+
					   "  and sld.divide_id=ood.divide_id(+) AND sld.source_no=ood.outstock_no(+)"+
					   "  group BY slm.warehouse_no,slm.enterprise_no,sld.owner_no, sld.source_no,slm.label_no,sld.article_no,sld.packing_qty,sld.status,ood.s_cell_no,ood.outstock_name) R,"+
					   "  bdef_defarticle a,WMS_DEFFIELDVAL V,BDEF_ARTICLE_PACKING bap  ,bdef_defworker wk  WHERE r.ENTERPRISE_NO=a.enterprise_no"+
					   " AND r.article_no=a.article_no AND V.TABLE_NAME = 'STOCK_LABEL_M' AND V.COLNAME = 'STATUS'  AND r.STATUS = V.VALUE (+)"+
					   " AND r.Article_No=bap.article_no(+)  AND r.enterprise_no=bap.enterprise_no(+)   AND r.packing_qty=bap.packing_qty(+)"+
					   "  AND r.outstock_name=wk.worker_no(+)   AND r.warehouse_no= wk.warehouse_no(+)  AND r.enterprise_no=wk.enterprise_no(+)"+
					   "  AND r.LABEL_NO = '"+labelNo+"'  AND r.WAREHOUSE_NO = '"+warehouseNo+"' AND r.ENTERPRISE_NO = '"+enterpriseNo+"' ";
			mdetail = genDao.getListByNativeSql(sql22,ODataCheckLabelDetailModel.class,0, 1000);
			
			if(mdetail.size()==0){
				return new MsgRes(false,"该标签不存在","该标签不存在");
			} 
		}
		
		// 3. 用于返回的数据对象
		ODataCheckLabelSelectModel ms =new ODataCheckLabelSelectModel();
		ms.setDetailInfo(mdetail);
		ms.setLabelNo(labelNo);
		ms.setSubLabelInfo(msub);
		ms.setUseType(usetype);
		ms.setEnterpriseNo(enterpriseNo);
		ms.setWarehouseNo(warehouseNo);
		
		msgRes.setIsSucc(true); 
		msgRes.setObj(JSON.toJSON(ms));
		 
		return msgRes;
	}
	
	/* RF容器整理 Add by sunl 2016年4月7日
	 * 扫描板标签后，返回当前板标签下的标签个数
	 */
	@Override
	public MsgRes GetAerrangeConfirmLabelBoxCount(String strRecvData)throws Exception
	{
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		String sql="SELECT COUNT(m2.label_no) FROM stock_label_m m1,stock_label_m m2 " +
				"WHERE m1.warehouse_no = m2.warehouse_no AND m1.enterprise_no = m2.enterprise_no" +
				" AND m1.container_no = m2.owner_container_no AND m1.container_no <> m2.container_no" +
				" AND m1.label_no = '"+reqMod.getReqObj()+"' AND m1.warehouse_no = '"+reqMod.getWarehouseNo()+"' AND m1.enterprise_no = '"+reqMod.getEnterpriseNo()+"'";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("标签不存在！");
			return msgRes;
		}
		
		String sql1="	SELECT M.WAVE_NO FROM STOCK_LABEL_M M  WHERE " +
				" m.label_no = '"+reqMod.getReqObj()+"' AND m.warehouse_no = '"+reqMod.getWarehouseNo()+"' AND m.enterprise_no = '"+reqMod.getEnterpriseNo()+"'";
		List list1 = genDao.getListByNativeSql(sql1);
		
		if(list1.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("标签不存在！");
			return msgRes;
		}

		msgRes.setIsSucc(true);
		msgRes.setObj(list.get(0) + "|" + list1.get(0));
		msgRes.setMsg("数据查询成功");
		return msgRes;
	}
	
	/* RF容器整理 Add by sunl 2016年4月7日
	 * 获取待整理确认的标签信息
	 */
	@Override
	public MsgRes GetAerrangeConfirmWaitLabel(String strRecvData)throws Exception
	{
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		
		//..这里从界面上只接收板标签号.
		//..根据箱标签号，获取当前箱标签对应的波次号和配送对象

		String sql1="	SELECT M.WAVE_NO,M.DELIVER_OBJ FROM STOCK_LABEL_M M  WHERE " +
				" m.label_no = '"+reqMod.getReqObj()+"' AND m.warehouse_no = '"+reqMod.getWarehouseNo()+"' AND m.enterprise_no = '"+reqMod.getEnterpriseNo()+"'";
		List<Stock_LabelMModel> list1 = genDao.getListByNativeSql(sql1,Stock_LabelMModel.class);
		
		if(list1.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("标签不存在！");
			return msgRes;
		}

		
		String strDeliverObj = list1.get(0).getDeliverObj();
		String strWaveNo = list1.get(0).getWaveNo();
		
		String sql="SELECT m.SOURCE_NO OutStockNo,m.label_no,m.status STATUSVALUE,v.text STATUSTEXT,w.worker_name  FROM STOCK_LABEL_M M,wms_deffieldval v,odata_outstock_register r,bdef_defworker w " +
				" WHERE M.Deliver_Obj = '"+strDeliverObj+"' AND (m.wave_no = 'N' OR m.wave_no = '"+strWaveNo+"')" +
				" AND m.status = v.value AND v.table_name = 'STOCK_LABEL_M' AND v.colname = 'STATUS'" +
				" and m.source_no = r.outstock_no(+) and m.warehouse_no = r.warehouse_no(+) and m.enterprise_no = r.enterprise_no(+) and r.worker_no = w.worker_no(+) " +
				" AND M.WAREHOUSE_NO = '"+reqMod.getWarehouseNo()+"' AND M.ENTERPRISE_NO = '"+reqMod.getEnterpriseNo()+"' AND m.status < '61'";
		
		List<ODataAerrangeConfirmWaitLabel>	mdetail = genDao.getListByNativeSql(sql,ODataAerrangeConfirmWaitLabel.class);
		
		if(mdetail.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("标签不存在！");
			return msgRes;
		}
		
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(mdetail));
		msgRes.setMsg("数据查询成功");
		return msgRes;
	}
	
	/* RF容器整理 Add by sunl 2016年4月10日
	 * 整理确认 扫描板标签后，返回当前板标签下的波次号，配送对象，暂存区，总箱数
	 */
	@Override
	public MsgRes tscGetPlateLabelInfo(String strRecvData) throws Exception{
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());//strWarehouseNo
		inList.add(reqMod.getReqObj());//strSLabelNo	 
		inList.add(reqMod.getFieldEX1());//strUserID
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_ArrangeCheck");
		if(resultList.get(5).toString().indexOf("N|")!=-1)
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(5).toString());
			return msgRes; 
		}
       
		ODataMergePalAndArrangeConfirm md =new ODataMergePalAndArrangeConfirm();
		md.setDeliverObj(resultList.get(0).toString());
		md.setDeliverArea(resultList.get(1).toString());
		md.setWaveNo(resultList.get(2).toString());
		md.setCustName(resultList.get(3).toString());
		md.setNnLabelNum(resultList.get(4).toString());
		msgRes.setIsSucc(true); 
		msgRes.setObj(JSON.toJSON(md));		 
		return msgRes;
	}
	
	/* RF容器整理 Add by sunl 2016年4月10日
	 * 自动装并板+整理确认
	 */
	@Override
	public MsgRes tscSaveMergePalAndAerrange(String strRecvData) throws Exception{
		ODataMergePalAndArrangeConfirm reqMod=JSON.parseObject(strRecvData, ODataMergePalAndArrangeConfirm.class);
		MsgRes msgRes=new MsgRes();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");

		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());//strWarehouseNo
		inList.add(reqMod.getLabelNo());//strSLabelNo	 
		inList.add(reqMod.getDeliverObj());//strDeliverObj	 
		inList.add(reqMod.getWaveNo());//strWaveNo	 
		inList.add(reqMod.getWorkerNO());//strUserId	  
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_HB.P_loadPalAndComfire");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(0).toString());
			return msgRes; 
		} 
		msgRes.setIsSucc(true); 
		msgRes.setMsg("回单成功");
		return msgRes;
	}
	
	/* RF容器整理 Add by sunl 2016年4月10日
	 * 获取待整理的数据
	 */
	@Override
	public MsgRes GetWaitAerrangeInfo(String strRecvData) throws Exception{
		//CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		
		String sql="SELECT m.cust_no,c.cust_alias cust_name,m.deliver_area,m.wave_no,COUNT(m.label_no) NnLabelNum FROM stock_label_m m ,bdef_defcust c " +
				" WHERE m.status = '61' " +
				" AND NOT EXISTS(SELECT 1 FROM odata_outstock_direct d " +
				"   WHERE d.wave_no = m.wave_no AND d.deliver_obj = m.deliver_obj AND d.warehouse_no = m.warehouse_no AND d.enterprise_no = m.enterprise_no) " +
				" and m.enterprise_no = c.enterprise_no and m.cust_no = c.cust_no " +
				" GROUP BY m.cust_no,c.cust_alias,m.deliver_area,m.wave_no";
		
		List<ODataWaitAerrangeInfo>	mdetail = genDao.getListByNativeSql(sql,ODataWaitAerrangeInfo.class);
		
		if(mdetail.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("没有待整理的数据！");
			return msgRes;
		}
		
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(mdetail));
		msgRes.setMsg("数据查询成功");
		return msgRes;
	}
	
	/** 称重获取标签信息
	 *  huangb 20160720
	 */
	@Override
	public MsgRes getLabelInfo(String strRecvData) throws Exception {
		ODataLabelWeighModel ReqMod = JSON.parseObject(strRecvData, ODataLabelWeighModel.class);
		
		String Sql = "";
	    Sql = " select slm.status, slm.use_type, wds.status_name, " +
	    	  "        sum(sld.qty * bda.unit_weight) as labelWeigh "+
	    	  "   from stock_label_m       slm, "+
		      "        stock_label_d       sld, "+
		      "        bdef_defarticle     bda, "+
		      "        wms_deflabel_status wds "+
		      "  where sld.enterprise_no = slm.enterprise_no "+
			  "    and sld.warehouse_no = slm.warehouse_no "+
			  "    and sld.container_no = slm.container_no "+
			  "    and bda.owner_no = sld.owner_no "+
			  "    and bda.article_no = sld.article_no "+
			  "    and wds.status_type = slm.status "+
			  "    and slm.enterprise_no = '" + ReqMod.getEnterpriseNo() + "' "+
			  "    and slm.warehouse_no = '" + ReqMod.getWarehouseNo() + "' "+
			  "    and slm.label_no = '" + ReqMod.getLabelNo() + "' " +
			  "  group by slm.status, slm.use_type, wds.status_name ";
		List<ODataLabelWeighModel> list = genDao.getListByNativeSql(Sql, ODataLabelWeighModel.class);
		if(list.size() <= 0){
			return new MsgRes(false, "当前标签不存在","");
		}
		if(!list.get(0).getUseType().equals("1")){
			return new MsgRes(false, "只有客户标签才能称重","");
		}
		return new MsgRes(true, "操作成功",JSON.toJSON(list.get(0)));
	}
	
	/** 保存标签重量信息
	 *  huangb 20160720
	 */
	@Override
	public MsgRes tscSaveLabelWeigh(String strRecvData) throws Exception {
		
		ODataLabelWeighModel reqMod=JSON.parseObject(strRecvData, ODataLabelWeighModel.class);

		List resultList=new ArrayList();
		List inList = new ArrayList();
		List outList=new ArrayList();
		
		inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo
		inList.add(reqMod.getWarehouseNo());//strWarehouseNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getLabelNo());//strLabelNo
		inList.add(reqMod.getWeigh());//nWeigh
		inList.add(reqMod.getUserId());//strUserId
		outList.add("S");
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_Save_LabelWeigh");
		
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "保存成功", " ");
	}

}
