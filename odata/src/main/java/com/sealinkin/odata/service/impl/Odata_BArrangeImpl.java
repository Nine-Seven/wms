/**
 * 模块名称：拆零容器整理打包（小嘴，按客户复核）
 * 模块代码：3928
 * @author hkl
 */
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
import com.sealinkin.odata.service.IOdata_BArrangeService;
import com.sealinkin.odata.service.IOdata_OutstockM;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_BArrangeImpl implements IOdata_BArrangeService{
	
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
		
        //该sql取得量是以最下操作包装换算（包括单位和规格）。
		String sql=" select a.lable_no,a.owner_no,vbd.article_no,vbd.article_name,sld.advance_cell_no," +
				"sum(a.article_qty-a.real_qty)/vbd.qmin_operate_packing as uncheckQty," +
				   strAttributeFields+" vbd.barcode,  vbd.qmin_operate_packing, " +
				   "nvl(pk.spec, '1*' || vbd.qmin_operate_packing || '*'|| vbd.unit) qmin_operate_spec," +
				   "nvl(vbd.qmin_operate_packing_unit,vbd.unit) qminOperateUnit,a.packing_qty " +
				   " from odata_check_label_d a,stock_label_d sld,bdef_defarticle vbd," +
				   "  stock_article_info sai,bdef_article_packing pk " +
				   " where a.enterprise_no='"+currEnterpriseNo+"' " +
				   " and a.warehouse_no='"+warehouseNo+"' " +
				   " and a.lable_no='"+str+"' " +
				   " and a.enterprise_no=vbd.enterprise_no " +
				   " and a.article_no=vbd.article_no " +
				   " and a.article_qty-a.real_qty>0 " +
				   " and sai.enterprise_no=a.enterprise_no " +
				   " and sai.article_id=a.article_id " +
				   " and sai.article_no=a.article_no " +
				   " and a.enterprise_no=sld.enterprise_no " +
				   " and a.warehouse_no=sld.warehouse_no " +
				   " and a.container_no=sld.container_no " +
				   " and a.article_no=sld.article_no " +
				   " and a.article_id=sld.article_id" +
				   " and a.divide_id = sld.divide_id" +
				   " and vbd.enterprise_no=pk.enterprise_no(+)" +
				   " and vbd.article_no=pk.article_no(+)" +
				   " and vbd.qmin_operate_packing=pk.packing_qty(+)" +
				   " group by a.lable_no,a.owner_no,vbd.article_no,sld.advance_cell_no, " +
				   "vbd.article_name, vbd.barcode,vbd.qmin_operate_packing,vbd.unit," +
				   "pk.spec,vbd.qmin_operate_packing_unit,vbd.unit,a.packing_qty " +
				   strAttributeGroupByFields;
		
		List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql, Odata_CheckLabelDModel.class,0,20);
		ExtListDataBo<Odata_CheckLabelDModel> extListBo=new ExtListDataBo<Odata_CheckLabelDModel>(list,0);
		return extListBo;
	}
	
	// 获得标签明细数据(已复核)
	@Override
	public List<Odata_CheckLabelDModel> getStockLabelD(String strEnterpriseNo,String strWarehouseNo,
			String strLabelNo, String strFlag) throws Exception {
		String strSql="  select"+
			       " b.article_no,"+
			       " sum(b.real_qty)/c.qmin_operate_packing as realQty,"+
			       " c.OWNER_ARTICLE_NO,"+
			       " c.barcode, " +
			       " nvl(c.qmin_operate_packing_unit,c.unit) qminOperateUnit," +
			       " nvl(pk.spec, '1*' || c.qmin_operate_packing || '*'|| c.unit) qmin_operate_spec, "+
			       " c.ARTICLE_NAME     "+
			   " from"+
			       " odata_check_label_d b,"+
			       " bdef_defarticle c,"+
			       " bdef_article_packing pk "+
			   " where"+
			       " b.article_no=c.ARTICLE_NO "+
			       " and b.enterprise_no=c.enterprise_no "+
			      " and c.enterprise_no=pk.enterprise_no(+)  "+
	              " and c.qmin_operate_packing=pk.packing_qty(+) "+
	              " and c.article_no=pk.article_no(+)  "+
			       " and b.real_qty > 0 "+
			       " and b.enterprise_no='" +strEnterpriseNo+"' "+
			       " and b.warehouse_no='" +strWarehouseNo+"' "+
			       " and b.d_label_no='"+strLabelNo+"' "+
			   " group by"+
			   "    b.article_no,"+
			   "     c.OWNER_ARTICLE_NO,"+
			   "     c.barcode,c.qmin_operate_packing," +
			   "c.qmin_operate_packing_unit,c.unit,pk.spec,"+
			   "     c.ARTICLE_NAME    ";
		List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(strSql, Odata_CheckLabelDModel.class);
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
	
	//出货整理打包》输入数量或者条码回车
	@Override
	public MsgRes tscCheckBarcodeAndSave(String currEnterpriseNo, String warehouseNo,
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
		
		//考虑一码多品的情况
		
		//如果sacnNum小于0则是扣量，判断且扣量的数量是否大于目的标签的该商品扫描数量，是则拦截
		if(sacnNum<0){
			//只能扣未封箱的量
			String sql3=" select a.lable_no,a.owner_no,vbd.article_no,vbd.article_name,vbd.qmin_operate_packing," +
					   " vbd.barcode, a.packing_qty,sum(a.article_qty) article_qty,sum(a.real_qty) real_qty," +
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
					   " and a.article_no in("+strArticleNo+") " +
					   "    and a.status ='11' " +
					   "    and a.d_label_no='"+strDlabel+"' "+
					   "group by a.lable_no,a.owner_no,vbd.article_no,vbd.qmin_operate_packing," +
					   "vbd.article_name, vbd.barcode, a.packing_qty,  "+
					   "sai.rsv_batch1,sai.rsv_batch2,sai.rsv_batch3,sai.rsv_batch4," +
					   "sai.rsv_batch5,sai.rsv_batch6,sai.rsv_batch7,sai.rsv_batch8,  "+
					   "sai.quality,sai.produce_date,sai.expire_date,sai.lot_no  ";
				
			List<Odata_CheckLabelDModel> list3=genDao.getListByNativeSql(sql3,Odata_CheckLabelDModel.class);
			int b = Math.abs(sacnNum);//将负数改为正数
			if(list3.size()==0){
				return new MsgRes(false,"商品不存在该标签内，请重新扫描！","");
			}
			if(b*list3.get(0).getQminOperatePacking()>list3.get(0).getRealQty()){
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
			inList.add(sacnNum*list3.get(0).getQminOperatePacking());//数量
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
			String sql2=" select a.lable_no,a.owner_no,vbd.article_no,vbd.article_name,vbd.qmin_operate_packing," +
					   " vbd.barcode, a.packing_qty,a.status," +
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
					   " and a.article_no in ("+strArticleNo+") " +
					   "group by a.lable_no,a.owner_no,vbd.article_no," +
					   "vbd.article_name, vbd.barcode, a.packing_qty,a.status,vbd.qmin_operate_packing,  "+
					   "sai.rsv_batch1,sai.rsv_batch2,sai.rsv_batch3,sai.rsv_batch4," +
					   "sai.rsv_batch5,sai.rsv_batch6,sai.rsv_batch7,sai.rsv_batch8,  "+
					   "sai.quality,sai.produce_date,sai.expire_date,sai.lot_no  ";
			
			List<Odata_CheckLabelDModel> list2=genDao.getListByNativeSql(sql2,Odata_CheckLabelDModel.class);
			if(list2.size()==0){
				return new MsgRes(false,"商品不存在或者已经扫完，请重新扫描！","");
			}
			
			
			//判断扫描基准量
			if(sacnNum*list2.get(0).getQminOperatePacking()>list2.get(0).getArticleQty()-list2.get(0).getRealQty()){
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
							strSlabel,strWorkerNo);
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
				inList.add(sacnNum*list2.get(0).getQminOperatePacking());//数量
				inList.add(strWorkerNo);//操作人
				inList.add(strPrinterGroupNo);
				
			}else{
				//选中
				inList.add(currEnterpriseNo);
				inList.add(warehouseNo);
				inList.add(list.get(0).toString());			
				inList.add(checkD.getArticleNo());
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
				inList.add(sacnNum*checkD.getQminOperatePacking());//数量
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
                "sum(d.real_qty/bda.qmin_operate_packing) from"+
          "  odata_check_label_d d,bdef_defarticle bda where "+
          "   d.d_label_no='"+str+"' " +
          "   and d.enterprise_no=bda.enterprise_no " +
          "   and d.article_no=bda.article_no "+
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
			String strUserId,double strWeight) throws Exception {
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
		
		//将重量写入该标签hkl
		String sql ="update stock_label_m t " +
				"set t.weight ='"+strWeight+"' " +
				"where t.label_no='"+strDLabelNo+"' " +
				"and t.enterprise_no='"+strEnterpriseNo+"' " +
				"and t.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(sql);
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
					"(select count(t.label_no) from stock_label_m t " +
					"where t.deliver_obj=m.deliver_obj and t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no and t.status='52') boxsQty," +
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
			
			List<Odata_CheckMModel> list=genDao.getListByNativeSql(sql,Odata_CheckMModel.class, 0, 10);
			
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
						   " m.status,'[' || ltrim(m.cust_no) || ']' || c.cust_name as custNo," +
						   "(select count(t.label_no) from stock_label_m t where t.deliver_obj=m.deliver_obj " +
						   "  and t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no and t.status='52') boxsQty 　 　"+
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
				
				list=genDao.getListByNativeSql(sql2,Odata_CheckMModel.class, 0, 10);
		   }else{
				String check = list.get(0).getCheckChuteNo();
				if(!check.equals(strQuery)){
					return new MsgRes(false,"您扫描的标签号和该码头【"+check+"】不一致，请重新扫描！","");	
				}
		   }
			//判断是否还有其他未封箱的客户（换客户）
			String sqll = 
					"select *  "+
					"  from odata_check_label_d d, odata_check_m mm  "+
					" where exists (select 'x'  "+
					"          from odata_check_label_d t, odata_check_m m  "+
					"         where t.lable_no = '"+strSLabelNo+"'  "+
					"           and t.check_no = m.check_no  "+
					"           and t.enterprise_no = m.enterprise_no  "+
					"           and t.warehouse_no=m.warehouse_no  "+
					"           and m.deliver_obj <> mm.deliver_obj)  "+
					"   and mm.check_chute_no ='"+strQuery+"'  "+
					"   and mm.enterprise_no = '"+strEnterpriseNo+"'  "+
					"   and mm.warehouse_no = '"+strWarehouseNo+"'  "+
					"   and d.d_label_no <> 'N' and d.status<'13' "+
					"   and d.check_no = mm.check_no  "+
					"  and d.warehouse_no=mm.warehouse_no  "+
					"   and d.enterprise_no = mm.enterprise_no ";
			
			List<Odata_CheckMModel> listt=genDao.getListByNativeSql(sqll,Odata_CheckMModel.class);
		    if(listt.size()>0){//如果大于0则还有其他需要封箱的箱子
		    	 list.get(0).setBatchNo("1");//借用BatchNo字段标识是否有其他未封箱的客户
		    }
		   return new MsgRes(true,"操作成功！",list.get(0));	
    }
	//取新标签号
	@Override
	public MsgRes tscGetNewLabel(String strEnterpriseNo,String strWarehouseNo, 
			String strLabelNo,String strWorkerNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(strLabelNo);//strLabelNo
		inList.add("B");//strContainerType
		inList.add(strWorkerNo);
		
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
		

       return new MsgRes(true,"回单成功","");
	}
	
	//获取未封箱明细
	@Override
	public List<Odata_CheckLabelDModel> getCheckLabelDList(
			String strEnterpriseNo, String strWarehouseNo) throws Exception {
		String sql="select t.*,m.cust_no,m.check_chute_no," +
				"ct.cust_name,ct.cust_alias,bda.article_name,bda.barcode," +
				"(T.REAL_QTY/BDA.QMIN_OPERATE_PACKING) AS realQminQty," +
				"bda.qmin_operate_packing_unit as qminOperateUnit " +
				"from odata_check_label_d t,odata_check_m m,bdef_defcust ct,BDEF_DEFARTICLE BDA " +
				"where t.enterprise_no='"+strEnterpriseNo+"' " +
				"and t.warehouse_no='"+strWarehouseNo+"' " +
				"and t.d_label_no<>'N' and t.status<'13' " +
				"and t.check_no=m.check_no " +
				"and t.enterprise_no=m.enterprise_no " +
				"and t.warehouse_no=m.warehouse_no " +
				"and m.enterprise_no=ct.enterprise_no " +
				"and m.owner_no=ct.owner_no " +
				"and m.cust_no=ct.cust_no " +
				"AND T.ENTERPRISE_NO=BDA.ENTERPRISE_NO " +
				"AND T.ARTICLE_NO=BDA.ARTICLE_NO " +
				"order by t.check_no,t.d_label_no,t.article_no ";
		List<Odata_CheckLabelDModel> list=genDao.getListByNativeSql(sql,Odata_CheckLabelDModel.class);
		return list;
	}
	
	
	
	

}
