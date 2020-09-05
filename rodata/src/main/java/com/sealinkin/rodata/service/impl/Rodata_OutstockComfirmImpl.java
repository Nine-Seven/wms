/*
 * 退厂确认
 * hekl
 */
package com.sealinkin.rodata.service.impl;


import java.util.ArrayList;
import java.util.List;


import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;
import com.sealinkin.rodata.service.IRodata_OutstockComfirmService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_OutstockComfirmImpl implements IRodata_OutstockComfirmService{
    //private static final Logger logger = Logger.getLogger(Rodata_OutstockComfirmImpl.class);
		
	private IGenericManager genDao;
	
	//退货确认
	@Override
	public MsgRes tscRoComfirm(String enterpriseNo,String strWarehouseNo, String strOwnerNo,
			String strRecedeNo,String strGrossWeight, String strUserID, String strDock_No)
			throws Exception {
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(enterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strRecedeNo); 
		inList.add(strUserID);
		inList.add(strDock_No);
				
		resultList=genDao.execProc(inList, outList, "pklg_rodata.P_RO_Confirm");
	
		System.out.println(resultList.get(0).toString());
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		

	    //回写退货毛重信息
		String strSql	 =	"UPDATE rodata_recede_m m SET m.gross_weight = '"+strGrossWeight+"' " +
	  		" WHERE m.RECEDE_NO =  '"+strRecedeNo+"'  AND m.enterprise_no ='"+enterpriseNo+"' " +
	  		" AND m.warehouse_no = '"+strWarehouseNo+"' AND m.owner_no = '"+strOwnerNo+"'";
	    genDao.exceuteSql(strSql);
		
		return new MsgRes(true,"确认成功",null);//确认成功
	}
	/**
	 * 退厂确认 查询头档信息
	 * @param warehouseNo
	 * @author lich
	 * @param start
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	@Override
	public ExtListDataBo<Rodata_OutstockMModel> getRodata_OutstockMComfirm(
			String enterpriseNo,
			String strWarehouseNo, 
			Integer start, 
			Integer limit, 
			String strSuppliersNo) {
		String sql="select distinct  "+
		            " a.po_no,   "+
		            "a.recede_no,   "+
		            "a.class_type," +
		            "f_get_fieldtext('N','CLASS_TYPE',a.class_type) as classType ,   "+
		            "d.supplier_no,   "+
		            "d.supplier_name,   "+
		            "f_get_fieldtext('N','STATUS',a.STATUS) as statusText ,   "+
		            "a.rgst_date,   "+
		            "a.rgst_name,   "+
		            "a.owner_no    "+
		        "from   "+
		            "rodata_box_m m,      "+
		            "rodata_recede_m a,   "+
		            "rodata_recede_d b,   "+
		            "bdef_defsupplier d    "+
		        "where   "+
		            "m.enterprise_no = a.enterprise_no   "+
		            "and m.warehouse_no=a.warehouse_no   "+
		            "and m.recede_no=a.recede_no   "+
		            "and a.enterprise_no=b.enterprise_no    "+
		            "and a.warehouse_no=b.warehouse_no    "+
		            "and a.recede_no=b.recede_no   "+
		            "and a.status=b.status   "+
		            "and a.status='14'   "+
		            "and a.enterprise_no=d.enterprise_no   "+
		            "and a.owner_no=d.owner_no   "+
		            "and a.supplier_no=d.supplier_no    "+
		            "and m.recede_no not in(    "+
		            "select source_no from rodata_outstock_direct   "+
		            "where enterprise_no='"+enterpriseNo+"' " +
		            "and warehouse_no='"+strWarehouseNo+"'  " +
		            "and status<>'13') "+
		            "and m.recede_no not in( "+
		            "select source_no from rodata_outstock_d "+
		            "where enterprise_no='"+enterpriseNo+"' and warehouse_no='"+strWarehouseNo+"') "+       
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+strWarehouseNo+"' ";
			
	    if(!StringUtil.isEmpty(strSuppliersNo))
		{
			String ws="and a.supplier_no='"+strSuppliersNo+"' ";
			sql=sql+ws;
		}
		sql+=" order by a.rgst_date desc " ;
  	   	List<Rodata_OutstockMModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockMModel.class, start,10000);
  	   	Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
  	   	ExtListDataBo<Rodata_OutstockMModel> extListBo = new ExtListDataBo<Rodata_OutstockMModel>(list, count);
  	   	return extListBo;
	}
	/**
	 * 退厂确认 查询明细信息
	 * @author lich
	 * @param string
	 * @param start
	 * @param limit
	 * @return
	 * @update czh 2016.5.30
	 */
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockDComfirm(
			String strPoNo,String enterpriseNo,
			String warehouseNo,  Integer start, Integer limit) {
		String sql="  select   "+
	               "a.enterprise_no,   "+  
	               "a.warehouse_no,    "+  
	               "a.owner_no,     "+
	              //" nvl(pk.packing_unit, decode(b.packing_qty,d.qmin_operate_packing,d.qmin_operate_packing_unit,d.unit)) packing_unit,"+
                //"  nvl(pk.spec, '1*' || a.packing_qty || d.unit) spec,"+
                "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
                "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,d.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,d.unit_packing) Unit,"+
                "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
                "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,d.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,d.unit_packing) spec,"+    
                "b.label_no,        "+       
	                "b.packing_qty,     "+ 
	                "b.article_no,  " +
	                "d.article_name,  " +
	                "d.owner_article_no,          "+
	                "d.barcode," +
	                " d.QMIN_OPERATE_PACKING,"+
	                " nvl(D.SELL_PRICE,0) as unitCost,nvl(d.sell_price,0) * sum(b.article_qty) as qtyPrice,     "+             
	                /*"(sum(b.article_qty)/ b.packing_qty) realwholenum     "+*/
	                "trunc(sum(b.article_qty)/b.packing_qty) as planBox,"+
	    			"trunc(mod(sum(b.article_qty),b.packing_qty)/d.QMIN_OPERATE_PACKING) as planQmin,"+
	    			"(b.article_qty - trunc(b.article_qty/b.packing_qty) * b.packing_qty - trunc(mod(b.article_qty,b.packing_qty)/d.QMIN_OPERATE_PACKING) * d.QMIN_OPERATE_PACKING) as planDis"+
	           " from      "+
	           "    rodata_box_m a,rodata_box_d b,rodata_recede_m c,bdef_defarticle d,bdef_article_packing pk     "+            
	           " where      "+
	             "   a.enterprise_no=b.enterprise_no   "+
	             "   and a.warehouse_no=b.warehouse_no   " +
	             "  and a.label_no = b.label_no  "+
	             "   and a.recede_no=b.recede_no   "+
	             "   and a.enterprise_no=c.enterprise_no   "+
	             "   and a.warehouse_no=c.warehouse_no   "+
	             "   and a.recede_no=c.recede_no   "+
	             "   and c.po_no='"+strPoNo+"'   "+
	             "   and b.enterprise_no=d.enterprise_no   "+
	             "   and b.article_no=d.article_no      "+
	             " and b.article_no=pk.article_no(+)" +
				 " and b.packing_qty=pk.packing_qty(+)" +
				 " and b.enterprise_no=pk.enterprise_no(+) "+
	             "   and a.enterprise_no='"+enterpriseNo+"'   "+
	             "   and a.warehouse_no='"+warehouseNo+"'                "+   
	           " group by      "+
	            "    a.enterprise_no,     "+
	             "   a.warehouse_no,      "+
	             "   a.owner_no,      "+
	            "    b.label_no,            "+   
	             "   b.packing_qty,      "+
	            "    d.barcode, D.SELL_PRICE,      "+
	             "   b.article_no,"+
                 "   d.article_name," +
                 "   b.enterprise_no,b.owner_no,d.qmin_operate_packing,d.unit_packing,b.article_qty,"+
                 "  d.owner_article_no              "+        
	           " order by      "+
	             "    b.label_no,b.article_no   ";
		
	  	List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
	  	return extListBo;
	}
	
	/**
     * 退厂确认 货主、供应商……下拉
     * @author lich
	 * @param  
     */
	@Override
	public List<ComboxBo> getRodata_OutstockComfirmCombo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str) {
		String strSql="";
		if(!StringUtil.isEmpty(strFlag) && strFlag.equals("1"))//1:货主
		{
			strSql = "select distinct " +
						"b.owner_no as value," +
						"c.owner_name  as text," +
						"'['||b.owner_no||']' ||c.owner_name as dropValue " +
					"from " +
						"rodata_outstock_m a," +
						"rodata_outstock_d b," +
						"bdef_defowner c " +
					"where " +
						"a.enterprise_no=b.enterprise_no "+
					    "and a.warehouse_no=b.warehouse_no "+
						"and a.outstock_no=b.outstock_no " +
					    "and b.enterprise_no=c.enterprise_no "+
						"and b.owner_no=c.owner_no " +
						"and a.status='13' " ;			
		}else if(!StringUtil.isEmpty(strFlag) && strFlag.equals("2"))//2:供应商
		{
			strSql = "select distinct  "+
		          "d.supplier_no as value,  "+ 
		          "d.supplier_name as text,  "+ 
		          "'['||d.supplier_no||']' ||d.supplier_name as dropValue  "+  
		          "from    "+
		           "rodata_box_m a,   "+
		           "rodata_recede_m c,   "+
		           "bdef_defsupplier d    "+
		          "where    "+
		            "a.enterprise_no=c.enterprise_no  "+
		            "and a.warehouse_no=c.warehouse_no  "+
		            "and a.recede_no=c.recede_no  "+
		            "and c.enterprise_no=d.enterprise_no  "+
		            "and c.owner_no=d.owner_no  "+
		            "and c.supplier_no=d.supplier_no    "+       
		            "and a.status='0'    "+
		            "and c.status='14'  ";  
		}
		if(!StringUtil.isEmpty(str) && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql += ws;
		}
		if(!StringUtil.isEmpty(enterpriseNo) && !enterpriseNo.equals(""))
		{
			strSql +=" and a.enterprise_No ='" + enterpriseNo + "' ";
		}else
		{
			strSql += " and 1=2 ";
		}
		if(!StringUtil.isEmpty(strWarehouseNo) && !strWarehouseNo.equals(""))
		{
			strSql +=" and a.warehouse_No ='" + strWarehouseNo + "' ";
		}else
		{
			strSql += " and 1=2 ";
		}
		if(!StringUtil.isEmpty(strWorkerOwner) && !strWorkerOwner.equals(""))
		{
			strSql += " and c.owner_no in("+strWorkerOwner+") ";
		}else{
			strSql += " and 1=2 ";
		}
		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}


}
