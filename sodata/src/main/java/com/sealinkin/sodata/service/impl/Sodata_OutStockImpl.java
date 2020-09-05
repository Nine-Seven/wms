package com.sealinkin.sodata.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.sodata.model.Sodata_OutStockDModel;
import com.sealinkin.sodata.model.Sodata_OutStockMModel;
import com.sealinkin.sodata.po.Sodata_OutstockD;
import com.sealinkin.sodata.po.Sodata_WasteD;
import com.sealinkin.sodata.service.ISodata_OutStockService;
import com.sealinkin.util.CommUtil;
/**
 *报损回单
 * @author hekangli
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Sodata_OutStockImpl implements ISodata_OutStockService{
	
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 报损手建单M
	 */
	@Override
	public ExtListDataBo<Sodata_OutStockMModel> getOutStock_MList(String currEnterpriseNo,String warehouseNo,String workerOwner,
			String queryStr) throws Exception {
		String sql="select distinct a.enterprise_no,"+
			       "a.warehouse_no,"+
			       "a.owner_no,"+
			       "a.outstock_no,"+
			       "a.operate_date,"+
			       "a.status,  "+
			       "m.po_no,m.org_no,f_get_fieldtext('N','ORG_NO',m.org_no)orgNoText,"+
			       "f_get_fieldtext('N', 'STATUS', a.status) statusText "+
			  "from sodata_outstock_m a,sodata_outstock_d d,sodata_waste_m m "+
			  "where a.enterprise_no=d.enterprise_no " +
			    "and a.warehouse_no=d.warehouse_no  " +
			    "and a.outstock_no=d.outstock_no "+
			    "and a.enterprise_no=m.enterprise_no " +
			    "and a.warehouse_no=m.warehouse_no " +
			    "and d.source_no=m.waste_no "+
			    "and a.warehouse_no = '"+warehouseNo+"' "+
			    "and a.enterprise_no = '"+currEnterpriseNo+"' ";

		
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+")";
		}else{
			sql=sql+" and 1=2";
		}
		sql=sql+" order by a.owner_no, a.outstock_no desc ";
		List<Sodata_OutStockMModel> list = genDao.getListByNativeSql(sql,Sodata_OutStockMModel.class);
		ExtListDataBo<Sodata_OutStockMModel> extListBo= new ExtListDataBo<Sodata_OutStockMModel>(list, 0);
        return extListBo;
	}
	
	
	
	/**
	 *  回单D
	 */
	@Override
	public ExtListDataBo<Sodata_OutStockDModel> getOutStock_DList(String currEnterpriseNo,
			String warehouseNo,String wheresql) throws Exception {
		String sql="SELECT "+
	            "A.enterprise_no,"+
	            "A.warehouse_no,"+
	           " A.OWNER_NO,"+
	            "A.OUTSTOCK_NO,a.assign_name,"+
	            "a.source_no,a.article_no,a.packing_qty, " +
	            //"nvl(pk.packing_unit, decode(a.packing_qty,cle.qmin_operate_packing,cle.qmin_operate_packing_unit,cle.unit)) packing_unit,"+
	            "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,cle.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,cle.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,cle.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,cle.unit_packing) spec,"+
	            "sum(a.article_qty) as article_qty,sum(a.real_qty) as real_qty,"+
	            /*"(sum(a.article_qty)) as planWholenum,"+
	            "(sum(a.article_qty)) as realWholenum,"+*/
	            "trunc(sum(a.article_qty)/a.packing_qty) as planBox,"+
				"trunc(mod(sum(a.article_qty),a.packing_qty)/cle.QMIN_OPERATE_PACKING) as planQmin,"+
				"(sum(a.article_qty) - trunc(sum(a.article_qty)/a.packing_qty) * a.packing_qty - trunc(mod(sum(a.article_qty),a.packing_qty)/cle.QMIN_OPERATE_PACKING) * cle.QMIN_OPERATE_PACKING) as planDis,"
				+"trunc(sum(a.article_qty)/a.packing_qty) as realBox,"+
				"trunc(mod(sum(a.article_qty),a.packing_qty)/cle.QMIN_OPERATE_PACKING) as realQmin,"+
				"(sum(a.article_qty) - trunc(sum(a.article_qty)/a.packing_qty) * a.packing_qty - trunc(mod(sum(a.article_qty),a.packing_qty)/cle.QMIN_OPERATE_PACKING) * cle.QMIN_OPERATE_PACKING) as realDis,"+
	            
	            "a.s_cell_no,a.d_cell_no ,cle.barcode,cle.article_name "+
	        "FROM "+
	          "  sodata_outstock_d A,bdef_defarticle cle,bdef_article_packing pk   "+
	        "WHERE  a.enterprise_no=cle.enterprise_no " +
	        "    and a.article_no=cle.article_no " +
	        "    and a.ARTICLE_NO=pk.ARTICLE_NO(+) " +
	        "    and a.packing_qty=pk.packing_qty(+) " +
	        "    and a.ENTERPRISE_NO = pk.ENTERPRISE_NO(+)  " +
	        "    and a.outstock_no='"+wheresql+"' "+
	        "   AND A.warehouse_no = '"+warehouseNo+"'  "+
	        "   AND A.enterprise_no = '"+currEnterpriseNo+"'  "+
	        "group by  A.enterprise_no,  "+
	           " A.warehouse_no,  "+
	           "A.OWNER_NO,  "+
	           " A.OUTSTOCK_NO,  "+
	           " a.assign_name,  "+
	           " a.source_no,  "+
	           " a.article_no,pk.packing_unit,cle.qmin_operate_packing,cle.qmin_operate_packing_unit,cle.unit,  a.s_cell_no,  "+
	           " a.d_cell_no ,  "+
	           " cle.barcode,a.packing_qty,cle.unit_packing,  "+
	           " cle.article_name   "+
	       " ORDER BY "+
	        "    A.OUTSTOCK_NO,a.article_no desc";
		List<Sodata_OutStockDModel> list=genDao.getListByNativeSql(sql, Sodata_OutStockDModel.class);
		ExtListDataBo<Sodata_OutStockDModel> extListBo=new ExtListDataBo<Sodata_OutStockDModel>(list,0);
		return extListBo;
	}


	//回单
	@Override
	public MsgRes save(String workerNo, String strDockNo,
			String strQuery,String type)
			throws Exception {
		List<Sodata_OutstockD> list=JSON.parseArray(strQuery,Sodata_OutstockD.class);
		
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		
		for(int i=0;i<list.size();i++){
			List inList=new ArrayList();
			
			inList.add(list.get(i).getId().getEnterpriseNo());
			inList.add(list.get(i).getId().getWarehouseNo());
			inList.add(list.get(i).getId().getOwnerNo());
			inList.add(list.get(i).getId().getOutstockNo());
			inList.add(list.get(i).getArticleNo());
			inList.add(list.get(i).getPackingQty());
			inList.add(list.get(i).getSCellNo());
			inList.add(list.get(i).getRealQty());
			inList.add(workerNo);
			inList.add(list.get(i).getAssignName());
			inList.add("1");//操作设备
			inList.add(strDockNo);
			inList.add(type);//打印标识
			
			resultList=genDao.execProc(inList, outList, "PKLG_SODATA.P_SaveOutstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
        }
		return new MsgRes(true,"操作成功","");
	}


}
