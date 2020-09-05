package com.sealinkin.stock.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.sodata.po.Sodata_WasteD;
import com.sealinkin.sodata.po.Sodata_WasteM;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.stock.model.Stock_PlanDModel;
import com.sealinkin.stock.model.Stock_PlanMModel;
import com.sealinkin.stock.po.Stock_PlanD;
import com.sealinkin.stock.po.Stock_PlanM;
import com.sealinkin.stock.service.IStock_PlanImportService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Stock_PlanImportServiceImpl implements IStock_PlanImportService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//��ȡͷ����Ϣ�б�
	@Override
	public ExtListDataBo<Stock_PlanMModel> getStockPlanMList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String str,String queryStr,PageBo pageBo) throws Exception {
		String strTotsql="";
		String strSql = "select a.enterprise_no,a.warehouse_no,a.remark," +
				"a.owner_no,a.plan_type,a.plan_no,a.po_no,a.plan_date," +
				"a.status,a.create_flag,a.send_flag,a.org_no,a.rgst_name,a.rgst_date , " +
				"f_get_fieldtext('STOCK_PLAN_M','PLAN_TYPE',a.plan_type) planTypeText, " +
				"f_get_fieldtext('STOCK_PLAN_M','STATUS',a.status) statusText, " +
				"f_get_fieldtext('N','ORG_NO',a.org_no) orgNoText " +
              "from stock_plan_m a  " +
             "where a.enterprise_no='"+strEnterpriseNo+"' " +
               "and a.warehouse_no='"+strWarehouseNo+"' " +
               "and a.owner_no in("+strOwnerNo+") " +
               "and a.status<>'16'  and a.status<>'13' ";
		
       if(str!=null && !str.equals(""))
       {
	        String strW=CommUtil.covtCollectionToWhereSql(str);
	        strSql+=strW;
       }
       if(queryStr!=null && !queryStr.equals(""))
       {
	        String s=CommUtil.covtCollectionToWhereSql(queryStr);
	        strSql+=s;
       }
      strSql=strSql+" order by a.status, a.plan_no desc ";
      
      strTotsql = "select count(*) from (" + strSql + ")";
      List<Stock_PlanMModel> list = genDao.getListByNativeSql(strSql,Stock_PlanMModel.class,pageBo.getStart(), pageBo.getPagesize());
      Integer count = genDao.getCountByNativeSql(strTotsql);
	  ExtListDataBo<Stock_PlanMModel> extListBo= new ExtListDataBo<Stock_PlanMModel>(list, count);
      return extListBo;
	}
	//��ȡ��Ʒȱ����Ϣ
	@Override
	public ExtListDataBo<Stock_ContentModel> getStockPlanArticleList(
			String enterpriseNo, String warehouseNo, String str)
			throws Exception {
		List<Stock_ContentModel> list=new ArrayList<Stock_ContentModel>();
		int count=0;
		
		
		if(str!=null && str!=""){
			String str1[]=str.split(",");
			for(int i=0; i<str1.length; i++){
				
				String sql="select rrd.article_no,bd.owner_article_no," +
						//"nvl(pk.packing_unit, decode(rrd.packing_qty,bd.qmin_operate_packing,bd.qmin_operate_packing_unit,bd.unit)) packing_unit," +
						"f_get_packingunit(rrd.enterprise_no,rrd.owner_no,rrd.article_no,rrd.packing_qty) packingUnit,"+
						"f_get_packingunit(rrd.enterprise_no,rrd.owner_no,rrd.article_no,bd.qmin_operate_packing) packingUnitQmin,"+
						"f_get_packingunit(rrd.enterprise_no,rrd.owner_no,rrd.article_no,bd.unit_packing) Unit,"+
						"f_get_spec(rrd.enterprise_no,rrd.owner_no,rrd.article_no,rrd.packing_qty) packingSpec,"+
						"f_get_spec(rrd.enterprise_no,rrd.owner_no,rrd.article_no,bd.qmin_operate_packing) packingSpecQmin,"+
						"f_get_spec(rrd.enterprise_no,rrd.owner_no,rrd.article_no,bd.unit_packing) spec,"+
						"bd.barcode,bd.article_name,rrd.plan_qty recedeQty,rrd.warehouse_no,rrd.owner_no,"+
				"nvl(a.qty,0) available_qty,"+
//				"case when nvl(a.qty,0)-rrd.plan_qty<0 then rrd.plan_qty-nvl(a.qty,0)  else 0 end as no_enough_qty from  "+
                "case "+
                     "when rrd.plan_qty + nvl(a.qty, 0) < 0 then "+
                          "- (rrd.plan_qty + nvl(a.qty, 0)) "+
                     "else "+
                          "0 "+
                "end as no_enough_qty from  "+
				"(select sc.enterprise_no,sc.warehouse_no, sc.article_no,sc.packing_qty,sum(sc.qty-sc.outstock_qty) qty " +
				" from stock_content sc,cdef_defcell cd,cdef_defarea t,cdef_defware cdw,stock_plan_m rrm "+
				" where sc.enterprise_no=cd.enterprise_no " +
				" and sc.warehouse_no=cd.warehouse_no " +
				" and sc.cell_no=cd.cell_no "+
				" and cd.enterprise_no=t.enterprise_no "+
				" and cd.warehouse_no=t.warehouse_no " +
				" and t.enterprise_no=cdw.enterprise_no " +
				" and t.warehouse_no=cdw.warehouse_no " +
				" and t.ware_no=cdw.ware_no " +
				" and cdw.enterprise_no=rrm.enterprise_no " +
				" and cdw.warehouse_no=rrm.warehouse_no " +
				" and cdw.org_no=rrm.org_no  and t.area_attribute<>'1'  " +
				" and rrm.plan_no='"+str1[i].trim()+"' "  +
				" and cd.enterprise_no='" +enterpriseNo+"' "+
				" and cd.warehouse_no='"+warehouseNo+"' "+
				" and cd.ware_no||cd.area_no=t.ware_no||t.area_no "+
				" and cd.cell_status<>'1' and sc.qty-sc.outstock_qty>0 "+
				" group by sc.enterprise_no, sc.warehouse_no,sc.article_no,sc.packing_qty) a,stock_plan_d rrd,bdef_defarticle bd,bdef_article_packing pk  "+
				" where a.enterprise_no(+)=rrd.enterprise_no and rrd.enterprise_no = bd.enterprise_no " +
				" and a.warehouse_no(+)=rrd.warehouse_no and a.article_no(+)=rrd.article_no and rrd.packing_qty=a.packing_qty(+) "+
				" and rrd.article_no=bd.article_no " +
				" and rrd.enterprise_no=pk.enterprise_no(+) and rrd.article_no=pk.article_no(+) and rrd.packing_qty=pk.packing_qty(+)"+
				" and rrd.plan_no='"+str1[i].trim()+"' " +
				" and rrd.enterprise_no='"+enterpriseNo+"' ";				
				List<Stock_ContentModel> list1=genDao.getListByNativeSql(sql, Stock_ContentModel.class);	
			 
				for(int j=0; j<list1.size();j++){	
					list.add(count++, list1.get(j));
				} 
			}	
			
		  	 ExtListDataBo<Stock_ContentModel> extListBo = new ExtListDataBo<Stock_ContentModel>(list, 0);
			 return extListBo;
		}else{
			return null;
		}		
	}
	//��ȡ��ϸ��Ϣ�б�
	@Override
	public List<Stock_PlanDModel> getStockPlanDList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String str, PageBo pageBo) throws Exception {
		String strSql = "select a.*," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
	            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,v.qmin_operate_packing) packingUnitQmin,"+
	            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,v.unit_packing) Unit,"+
	            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
	            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,v.qmin_operate_packing) packingSpecQmin,"+
	            "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,v.unit_packing) spec,"+
				"v.owner_article_no,a.plan_qty articleQty,b.real_qty,v.qmin_operate_packing, " +
				"trunc(a.plan_qty/a.packing_qty) as planBox,"+
				"trunc(mod(a.plan_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) as planQmin,"+
				"(a.plan_qty - trunc(a.plan_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.plan_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) * v.QMIN_OPERATE_PACKING) as planDis,"+
				
				"trunc(b.real_qty/a.packing_qty) as realBox,"+
				"trunc(mod(b.real_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) as realQmin,"+
				"(b.real_qty - trunc(b.real_qty/a.packing_qty) * a.packing_qty - trunc(mod(b.real_qty,a.packing_qty)/v.QMIN_OPERATE_PACKING) * v.QMIN_OPERATE_PACKING) as realDis,"+
				
				"v.article_name,v.barcode," +
			    "to_char(a.produce_date,'yyyy-mm-dd')produceDateText,"+
			    "to_char(a.expire_date,'yyyy-mm-dd')expireDateText " + 
              "from stock_plan_d a,v_bdef_defarticle v,  " +
                   "(select enterprise_no,warehouse_no,owner_no, source_no," +
                        "article_no, produce_date,expire_date,lot_no," +
                        "quality,packing_qty,sum(real_qty) real_qty " +
                     "from stock_confirm_d " +
                     "group by enterprise_no,warehouse_no,owner_no, " +
                              "source_no,article_no,produce_date,expire_date,lot_no, " +
                              "quality,rsv_batch1,rsv_batch2,rsv_batch3,rsv_batch4, " +
                              "rsv_batch5,rsv_batch6,rsv_batch7,rsv_batch8, " +
                              "cell_no,packing_qty) b " +
             "where a.enterprise_no=v.enterprise_no " +
               "and a.owner_no=v.owner_no " +
               "and a.article_no=v.article_no " +
               "and a.enterprise_no = b.enterprise_no(+) " +
               "and a.warehouse_no = b.warehouse_no(+) " +
               "and a.owner_no = b.owner_no(+) " +
               "and a.plan_no=b.source_no(+) " +
               "and a.article_no = b.article_no(+) " +
               "and a.packing_qty = b.packing_qty(+) " +
               "and a.produce_date = b.produce_date(+) " +
               "and a.lot_no = b.lot_no(+) " +
               "and a.enterprise_no='"+strEnterpriseNo+"' " +
               "and a.warehouse_no='"+strWarehouseNo+"' " +
               "and a.owner_no in("+strOwnerNo+") " +
               "and a.status<>16 ";
		
        if(str!=null && !str.equals(""))
        {
	        String strW=CommUtil.covtCollectionToWhereSql(str);
	        strSql+=strW;
        }
            
	    List<Stock_PlanDModel> list=genDao.getListByNativeSql(strSql, Stock_PlanDModel.class);
	    return list;
	}
	//У��ԭ���˵����Ƿ����
	@Override
	public String checkPoNo(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String poNo) throws Exception {
		String sql="select a.po_no from stock_plan_m a where a.po_no='"+poNo+"' " +
				" and a.warehouse_no='"+warehouseNo+"' and a.enterprise_no='"+enterpriseNo+"' " +
						"and a.owner_no in("+strOwnerNo+") ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//ȡ��Ʒ����
	@Override
	public List<Stock_PlanDModel> getArticle(String articleNo,
			String strEnterpriseNo) throws Exception {
		String sql="select a.article_no,a.article_name,a.owner_article_no,a.barcode,a.expiry_days guarantee,a.lot_type lotType,a.qmin_operate_packing from " +
				"bdef_defarticle a where " +
				" a.article_no='"+articleNo+"' and a.enterprise_no='"+strEnterpriseNo+"' ";
		List<Stock_PlanDModel> list=genDao.getListByNativeSql(sql,Stock_PlanDModel.class);
		return (List<Stock_PlanDModel>)list;
	}
	//�������� 
	@Override
	public List<ComboxBo> queryLot(String strEnterpriseNo,
			String articleNo, String produceDate,String lotNo) throws Exception {
		String strSql="select distinct lot_no value,lot_no text,lot_no dropValue " +
				"from stock_article_info " +
				"where enterprise_no='" + strEnterpriseNo + "' " +
				"and article_no='"+articleNo+"'  " +
				"and to_char(produce_date,'yyyy/mm/dd') = '"+produceDate+"' ";
		if(lotNo!=null && !lotNo.equals(""))
		{
			strSql=strSql+" and lot_no like '"+lotNo+"' ";
		}
		
		List<ComboxBo> list=genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	
	}	
	//��λ����
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,
			String strWarehouseNo, String strJson, String str, 
			String strQuery, int i, int j) {
		String sql= "select a.cell_no value,a.cell_no text,a.cell_no dropValue  " +
				"from Cdef_Defcell a ,Cdef_Defarea b,cdef_defware c " +
			"where a.cell_status<>1 " +
				"and a.ware_no = b.ware_no "+ 
				"and a.area_no=b.area_no "+
				"and a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
			    "and b.area_attribute<>'1' " +
			    "and b.enterprise_no=c.enterprise_no " +
			    "and b.warehouse_no=c.warehouse_no " +
			    "and b.ware_no=c.ware_no "+
//			    "and b.area_usetype in ('1','5','6') "+
			    "and a.warehouse_No='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' " ;	
		if(strQuery!=null && !strQuery.equals("")){
			sql=sql+" and c.org_no = '"+strQuery+"' ";
		}
		if(strJson!=null && !strJson.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strJson);
			sql=sql+ws;
		}
		if(str!=null && !str.equals(""))
		{
			sql=sql+" and a.cell_no like '%"+str+"%' ";
			}
			List list=genDao.getListByNativeSql(sql,ComboxBo.class);
			return  (List<ComboxBo>)list;
		}
	//У�����䴢λ��Ʒ�Ƿ��п��
	@Override
	public String checkCellNo(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String cellNo,String articleNo) throws Exception {
		String sql="select a.cell_no from stock_content a where " +
				"a.qty-a.outstock_qty>0 " +
				"and a.cell_no='"+cellNo+"' " +
				"and a.article_no='"+articleNo+"' " +
				" and a.warehouse_no='"+warehouseNo+"' and a.enterprise_no='"+enterpriseNo+"' " +
						"and a.owner_no in("+strOwnerNo+") ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}

	//����
	@Override
	public MsgRes save(String strJsonMaster, String strJsonDetail)
			throws Exception {
		Stock_PlanM stockPlanM=(Stock_PlanM)JSON.parseObject(strJsonMaster, Stock_PlanM.class);
		List<Stock_PlanD> stockPlanD=JSON.parseArray(strJsonDetail, Stock_PlanD.class);
		String planNo="N";
		List  resultList=new ArrayList();
		List  outList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		for (Stock_PlanD i : stockPlanD) {
			
			List  inList = new ArrayList();
			inList.add(stockPlanM.getId().getEnterpriseNo());//strEnterpriseNo
			inList.add(stockPlanM.getId().getWarehouseNo());//strWareHouseNo
			inList.add(stockPlanM.getId().getOwnerNo());//strOwnerNo
			inList.add(stockPlanM.getPlanType());//strPlanType
			inList.add(stockPlanM.getPoNo());//strPoNo
			inList.add(i.getArticleNo());//strArticleNo
			inList.add(i.getPackingQty());//nPackingQty
			inList.add(i.getProduceDate());//dtProduceDate
			inList.add(i.getExpireDate());//dtExpireDate
			inList.add(i.getQuality());//strQuality
			inList.add(i.getLotNo());//strLotNo
			inList.add(i.getImportBatchNo());//strImportBatchNo
			inList.add(i.getRsvBatch1());//strRsvBatch1
			inList.add(i.getRsvBatch2());//strRsvBatch2
			inList.add(i.getRsvBatch3());//strRsvBatch3
			inList.add(i.getRsvBatch4());//strRsvBatch4
			inList.add(i.getRsvBatch5());//strRsvBatch5
			inList.add(i.getRsvBatch6());//strRsvBatch6
			inList.add(i.getRsvBatch7());//strRsvBatch7
			inList.add(i.getRsvBatch8());//strRsvBatch8
			inList.add(i.getCellNo());//strCellNo
			inList.add(i.getPlanQty());//nPlanQty
			inList.add(i.getStockType());//strStockType
			inList.add(i.getStockValue());//strStockValue
			inList.add(i.getLabelNo());//strLabelNo
			inList.add(i.getRgstName());//strUserId
			inList.add(stockPlanM.getCreateFlag());//strCreateFlag
			inList.add(stockPlanM.getOrgNo());//strOrgNo
			inList.add(planNo);//strsPlanNo
			inList.add(stockPlanM.getRemark());//strsRemark
			
			resultList = genDao.execProc(inList, outList, "PKLG_ADJ.P_saveStockPlan");
			if(resultList.get(1).toString().indexOf("N|") != -1)
			{
				throw new Exception(resultList.get(2).toString());
			}
			planNo=resultList.get(0).toString();
		}
		return new MsgRes(true, "���ݱ���ɹ���", resultList.get(0).toString());
	}
	//��λ
	@Override
	public MsgRes send(String enterpriseNo, String workerNo,
			String warehouseNo,  String strDockNo,String str,String strQuery) throws Exception {
		List<Stock_PlanDModel> str1=JSON.parseArray(str, Stock_PlanDModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		

		List  inList=new ArrayList();
		
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(str1.get(0).getOwnerNo());//strOwnerNo
		inList.add(str1.get(0).getPlanNo());//strPlanNo
		inList.add(str1.get(0).getCellNo());//strCellNo
		inList.add(workerNo);
		inList.add(strDockNo);
		inList.add(strQuery);//strPrintFlag
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ADJ.P_stockLocateComfire");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
	
		return new MsgRes(true,TipUtil.getTipsByKey("E25001"),null);//��λ�ɹ���

	}
	@Override
	public MsgRes closeOrder(String strQuery) throws Exception {
		List<Stock_PlanDModel> str=JSON.parseArray(strQuery, Stock_PlanDModel.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		
		inList.add(str.get(0).getEnterpriseNo());
		inList.add(str.get(0).getWarehouseNo());
		inList.add(str.get(0).getOwnerNo());
		inList.add(str.get(0).getPlanNo());
		inList.add(str.get(0).getRgstName());
		resultList=genDao.execProc(inList, outList, "PKLG_ADJ.P_PlanCancel");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"ȡ���ɹ�","");
	}
	@Override
	public MsgRes changeStock(String strJsonMaster, String strJsonDetail)
			throws Exception {
		Stock_PlanM stockPlanM=(Stock_PlanM)JSON.parseObject(strJsonMaster, Stock_PlanM.class);
		List<Stock_PlanD> stockPlanD=JSON.parseArray(strJsonDetail, Stock_PlanD.class);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String s1 = null;
		if(stockPlanM.getPlanDate()!=null){  //to_date('1980-12-13','yyyy-mm-dd')
			String planDate=sdf.format(stockPlanM.getPlanDate());
			s1=" m.plan_date=to_date('"+planDate+"' ,'yyyy-mm-dd')";
		}else{
			s1=" m.status=m.status ";
		}
	
		String sql="update stock_plan_m m set m.plan_type='"+stockPlanM.getPlanType() + "'" +
				",m.po_no='"+stockPlanM.getPoNo()+"',"+ s1 +
				",m.updt_name='"+stockPlanM.getUpdtName() + "', m.updt_date=sysdate " + ", m.remark= '" +stockPlanM.getRemark()+"' " +
				"where m.plan_no='"+stockPlanM.getId().getPlanNo()+"' and m.enterprise_no='"+stockPlanM.getId().getEnterpriseNo()+"'";  
		String planNo="";
		if(stockPlanM.getStatus().equals("10")){
			this.deleteStock(stockPlanM.getId().getEnterpriseNo(), stockPlanM.getId().getPlanNo());
			planNo=stockPlanM.getId().getPlanNo();
			this.genDao.saveList(stockPlanD);
		}
		this.genDao.updateBySql(sql);
		return new MsgRes(true, "���ݱ���ɹ���", planNo);
	}
	@Override
	public void deleteStock(String currEnterpriseNo, String planNo)
			throws Exception {
		String delsql="delete stock_plan_d where plan_no='"+planNo+"' " +
		         "and enterprise_no='"+currEnterpriseNo+"'";
		genDao.updateBySql(delsql);
	}
}




















