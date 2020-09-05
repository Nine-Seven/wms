package com.sealinkin.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.stock.model.Stock_ADjDModel;
import com.sealinkin.stock.po.StockAdjD;
import com.sealinkin.stock.po.StockAdjM;
import com.sealinkin.stock.service.IStock_AdjustAccountsService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Stock_AdjustAccountsServiceImpl implements IStock_AdjustAccountsService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	public List<Stock_ADjDModel> queryArticleInfo(String strEnterpriseNo,String strWheresql,String strOwnerNo)
			throws Exception {
		String strSql="select a.article_no,a.article_name,a.barcode," +
				"nvl(max(d.packing_qty),1) packing_qty,a.expiry_days guarantee,a.lot_type lotType " +
				"from bdef_defarticle a,bset_article_batch_d c,bdef_article_packing d " +
				"where a.batch_id=c.batch_id " +
				"and a.enterprise_no=d.enterprise_no(+) " +
				"and a.article_no=d.article_no(+) " +
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.article_no='"+strWheresql+"' " +
				"and a.owner_no='"+strOwnerNo+"' " +
				"group by a.article_no,a.article_name,a.barcode,a.expiry_days,a.lot_type";
		List<Stock_ADjDModel> list=genDao.getListByNativeSql(strSql, Stock_ADjDModel.class);
		return list;
	}
	//���Ҵ�λ����
		@Override
	public List<ComboxBo> getCdef_DefCellCombo(String strEnterpriseNo,String warehouseNo, String strJson, 
				String str, int i, int j) throws Exception {
			 
			String sql = "select a.cell_no value,a.cell_no text,a.cell_no dropValue  "
					+ "from Cdef_Defcell a ,Cdef_Defarea b "
					+ " where a.cell_status<>1 "
					+ " and a.check_status<>3 "
					+ " and a.ware_no = b.ware_no "
					+ " and a.area_no=b.area_no "
					+ " and a.enterprise_no='" + strEnterpriseNo + "' "
					+ " and a.warehouse_No='" + warehouseNo + "' "
					+ " and a.enterprise_no=b.enterprise_no "
					+ " and a.warehouse_no=b.warehouse_no "
					+ " and b.area_attribute=0  ";
					
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

		@Override
		public List<ComboxBo> getArticleCombo(String strEnterpriseNo,String warehouseNo,String strOwnerNo,
				String strQuery, String strWheresql) throws Exception {
			String strSql="select t1.article_no value,t1.article_name text," +
					"'['|| ltrim(t1.owner_article_no)||']'||t1.article_name dropValue " +
					"from bdef_defarticle t1 ,stock_content t3 " +
					"where t1.enterprise_no=t3.enterprise_no "+
					"and t1.article_no=t3.article_no " +
					"and t3.qty-t3.outstock_qty>0 " +
					"and t1.enterprise_no='" + strEnterpriseNo + "' "+
					"and t3.warehouse_no='" + warehouseNo + "'";
			//��Ʒ���롢���롢���ơ�������Ʒ����
			
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				strSql+=" and (t1.article_no like '%"+strWheresql+"%' " +
						"or t1.article_name like '%"+strWheresql+"%' " +
								"or t1.barcode like '%"+strWheresql+"%' " +
								"or t1.owner_article_no like '%"+strWheresql+"%')";
			}
			//����Ȩ��
			if(strOwnerNo!=null && !strOwnerNo.equals(""))
			{
				strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
			}else
			{
				strSql=strSql+" and 1=2";
			}
			//�������˵�����
			if(strQuery!=null && !strQuery.equals(""))
			{
				String strW=CommUtil.covtCollectionToWhereSql(strQuery);
				strSql+=strW;
			}
			strSql=strSql+" group by t1.article_no ,t1.article_name,t1.owner_article_no ";
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
			return (List<ComboxBo>) list;
		}
		@Override
		public List<ComboxBo> getProductDateAndLotNo(String strEnterpriseNo,String warehouseNo,String strOwnerNo,
				String strQuery) throws Exception {
				String strSql="select to_char(t1.produce_date,'yyyy-mm-dd')||','||t1.lot_no value, "+
				              "'['||to_char(t1.produce_date,'yyyy-mm-dd')||']'||' '||t1.lot_no text, "+
				              "'['||to_char(t1.produce_date,'yyyy-mm-dd')||']'||' '||t1.lot_no dropValue "+
                              "from stock_article_info t1,stock_content t2 "+
                              "where t1.enterprise_no=t2.enterprise_no " +
                              "and t1.article_no=t2.article_no " +
                              "and t1.article_id=t2.article_id " +
                              "and t2.qty-t2.outstock_qty>0 " +
                              "and t1.enterprise_no='" + strEnterpriseNo + "' " +
                              "and t2.warehouse_no='" + warehouseNo + "' ";				
				if(strOwnerNo!=null && !strOwnerNo.equals(""))
				{
					strSql=strSql+" and t2.owner_no in("+strOwnerNo+") ";
				}else
				{
					strSql=strSql+" and 1=2";
				}
				if(strQuery!=null && !strQuery.equals(""))
				{
					String strW=CommUtil.covtCollectionToWhereSql(strQuery);
					strSql+=strW;
				}
				strSql=strSql+" group by t1.produce_date ,t1.lot_no ";
				List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
				return (List<ComboxBo>) list;
		}
		@Override
		public ExtListDataBo<Stock_ADjDModel> getStockADjDList(String strEnterpriseNo,String warehouseNo,
				String strOwnerNo, String strQuery,PageBo pageBo) throws Exception {
			String strSql="select v.cell_no,v.article_no,b.owner_article_no, "+
						"trunc(v.planQty/v.packing_qty) as planBox,"+
						"trunc(mod(v.planQty,v.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
						"(v.planQty - trunc(v.planQty/v.packing_qty) * v.packing_qty - trunc(mod(v.planQty,v.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis,"+
					
						"trunc(v.realQty/v.packing_qty) as realBox,"+
						"trunc(mod(v.realQty,v.packing_qty)/b.QMIN_OPERATE_PACKING) as realQmin,"+
						"(v.realQty - trunc(v.realQty/v.packing_qty) * v.packing_qty - trunc(mod(v.realQty,v.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as realDis,"+
                          
                          "b.barcode,b.article_name, nvl(v.packing_qty,b.unit_packing) as packing_qty, " +
                          //"nvl(a.packing_unit, decode(t1.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit," +
                          "f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,v.packing_qty) packingUnit,"+
                          "f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,b.qmin_operate_packing) packingUnitQmin,"+
                          "f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,b.unit_packing) Unit,"+
                          "f_get_spec(v.enterprise_no,v.owner_no,v.article_no,v.packing_qty) packingSpec,"+
                          "f_get_spec(v.enterprise_no,v.owner_no,v.article_no,b.qmin_operate_packing) packingSpecQmin,"+
                          "f_get_spec(v.enterprise_no,v.owner_no,v.article_no,b.unit_packing) spec,"+
                          "v.planQty,v.realQty,v.produce_date,v.expire_date,v.quality,v.lot_no "+
                           "from (select t1.enterprise_no,t1.warehouse_no,t1.owner_no,t1.cell_no," +
                           "t1.article_no,t1.packing_qty, sum(t1.qty-t1.outstock_qty) as planQty," +
                           "sum(t1.qty-t1.outstock_qty) as realQty,t2.produce_date,t2.expire_date, " +
                           "(case when t2.quality = '0' then '��Ʒ' else " +
                           "case when t2.quality = 'B' then 'DM��Ʒ' else " +
                           "case when t2.quality = 'A' then '����Ʒ' end end end) AS quality, " +
                           "t2.lot_no from  stock_content t1, stock_article_info t2 " +
                           "where t1.enterprise_no=t2.enterprise_no and t1.article_no=t2.article_no " +
                           "and t1.article_id=t2.article_id group by t1.enterprise_no, " +
                           "t1.warehouse_no,t1.owner_no, t1.cell_no,t1.article_no,t1.packing_qty, " +
                           "t2.produce_date, t2.expire_date, t2.quality, t2.lot_no) v ," +
                           "bdef_article_packing a,bdef_defarticle b " +
                           "where v.enterprise_no='" + strEnterpriseNo + "' " +
                           "and v.warehouse_no='" + warehouseNo + "' " +
                           "and v.planQty>0 " +
                           "and v.enterprise_no =a.enterprise_no(+) " +
                           "and v.article_no=a.article_no(+) " +
                           "and v.packing_qty=a.packing_qty(+) " +
                           "and v.enterprise_no=b.enterprise_no " +
                           "and v.article_no=b.article_no " +
                           "and v.owner_no=b.owner_no ";
			
			String totsql="select count(1) from (select t1.enterprise_no,t1.warehouse_no," +
					"t1.owner_no,t1.cell_no,t1.article_no,t1.packing_qty, " +
					"sum(t1.qty-t1.outstock_qty)planQty," +
					"sum(t1.qty-t1.outstock_qty)realQty," +
					"t2.produce_date,t2.expire_date, " +
					"(case when t2.quality = '0' then '��Ʒ' else " +
					"case when t2.quality = 'B' then 'DM��Ʒ' else " +
					"case when t2.quality = 'A' then '����Ʒ' end end end) AS quality, " +
					"t2.lot_no from  stock_content t1, stock_article_info t2 " +
					"where t1.enterprise_no=t2.enterprise_no " +
					"and t1.article_no=t2.article_no " +
					"and t1.article_id=t2.article_id " +
					"group by t1.enterprise_no, t1.warehouse_no," +
					"t1.owner_no, t1.cell_no,t1.article_no,t1.packing_qty, " +
					"t2.produce_date, t2.expire_date, t2.quality, t2.lot_no) v " +
                           "where v.enterprise_no='" + strEnterpriseNo + "' " +
                           "and v.warehouse_no='" + warehouseNo + "' " +
                           "and v.planQty>0 ";
			if(strOwnerNo!=null && !strOwnerNo.equals(""))
			{
				strSql=strSql+" and v.owner_no in("+strOwnerNo+") ";
				totsql=totsql+" and v.owner_no in("+strOwnerNo+") ";
			}else
			{
				strSql=strSql+" and 1=2";
				totsql=totsql+" and 1=2";
			}
			if(strQuery!=null && !strQuery.equals(""))
			{
				String strW=CommUtil.covtCollectionToWhereSql(strQuery);
				strSql+=strW;
				totsql+=strW;
			}
			strSql=strSql +" order by v.article_no,v.produce_date "; 
           	List<Stock_ADjDModel> list = genDao.getListByNativeSql(strSql,Stock_ADjDModel.class,pageBo.getStart(), pageBo.getPagesize());
			Integer count = genDao.getCountByNativeSql(totsql);
			ExtListDataBo<Stock_ADjDModel> extListBo= new ExtListDataBo<Stock_ADjDModel>(list, count);
			return extListBo;
		}

		@Override
		public List<Stock_ADjDModel> queryLot(String strEnterpriseNo,String articleNo,String produceDate)
				throws Exception {
			String strSql="select lot_no " +
					"from BDEF_ARTICLE_LOT_MANAGE " +
					"where enterprise_no='" + strEnterpriseNo + "' " +
					"and article_no='"+articleNo+"'  " +
							"and to_char(produce_date,'yyyy/mm/dd') = '"+produceDate+"' ";
						List<Stock_ADjDModel> list=genDao.getListByNativeSql(strSql, Stock_ADjDModel.class);
			return list;
		}
		public List<Bdef_DefarticleModel> getOwnerArticleNO(String strEnterpriseNo,String articleNo,
				String strOwnerNo) throws Exception {
			
			String strSql="select owner_article_no from bdef_defarticle " +
					      "where enterprise_no='" + strEnterpriseNo + "' " +
					      "and article_no='"+articleNo+"' " +
						  "and owner_no = '"+strOwnerNo+"' ";
						List<Bdef_DefarticleModel> list=genDao.getListByNativeSql(strSql, Bdef_DefarticleModel.class);
			return list;
		}

		@Override
		public MsgRes save(String jsonMaster, String jsonDetail)
				throws Exception {
			StockAdjM stockADjM=(StockAdjM)JSON.parseObject(jsonMaster,StockAdjM.class);
			List<StockAdjD> stockADjD=JSON.parseArray(jsonDetail,StockAdjD.class);
			List outList=new ArrayList();
			List outList1=new ArrayList();
			List resultList=new ArrayList();
			outList.add("S");
			outList.add("S");
			outList1.add("S");
			
			for(int i=0; i<stockADjD.size();i++){
				List inList=new ArrayList();
				List inList1=new ArrayList();
				inList.add(stockADjM.getId().getEnterpriseNo());
				inList.add(stockADjM.getId().getWarehouseNo());
				inList.add(stockADjM.getId().getOwnerNo());
				inList.add(stockADjM.getAdjType());
				inList.add(stockADjD.get(i).getArticleNo());
				inList.add(stockADjD.get(i).getBarcode());
				inList.add(stockADjD.get(i).getPackingQty());
				inList.add(stockADjD.get(i).getProduceDate());
				inList.add(stockADjD.get(i).getExpireDate());
				inList.add(stockADjD.get(i).getQuality());
				inList.add(stockADjD.get(i).getLotNo());
				inList.add(stockADjD.get(i).getRsvBatch1());
				inList.add(stockADjD.get(i).getRsvBatch2());
				inList.add(stockADjD.get(i).getRsvBatch3());
				inList.add(stockADjD.get(i).getRsvBatch4());
				inList.add(stockADjD.get(i).getRsvBatch5());
				inList.add(stockADjD.get(i).getRsvBatch6());
				inList.add(stockADjD.get(i).getRsvBatch7());
				inList.add(stockADjD.get(i).getRsvBatch8());
				inList.add(stockADjD.get(i).getCellNo());
				inList.add(stockADjD.get(i).getRealQty()-stockADjD.get(i).getPlanQty());
				inList.add(stockADjD.get(i).getStockType());
				inList.add(stockADjD.get(i).getStockValue());
				inList.add(stockADjD.get(i).getLabelNo());
				inList.add(stockADjM.getRgstName());
				inList.add("N");
								
				resultList=genDao.execProc(inList, outList, "PKLG_ADJ.P_SaveStockADj");
				
				inList1.add(stockADjM.getId().getEnterpriseNo());
				inList1.add(stockADjM.getId().getWarehouseNo());
				inList1.add(stockADjM.getId().getOwnerNo());
				inList1.add(resultList.get(0).toString());
				inList1.add(stockADjM.getRgstName());
		
				resultList=genDao.execProc(inList1, outList1, "PKLG_ADJ.P_ComfireAdj");
			}
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true, "����ɹ�", " ");
		}
}
