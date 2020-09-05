/*
 * 退厂拼箱打包
 * hekl
 */
package com.sealinkin.rodata.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;


import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_BoxDModel;
import com.sealinkin.rodata.model.Rodata_BoxMModel;
import com.sealinkin.rodata.service.IRodata_LabelPackService;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_LabelPackImpl implements IRodata_LabelPackService{
    //private static final Logger logger = Logger.getLogger(Rodata_OutstockComfirmImpl.class);
		
	private IGenericManager genDao;
	
	//单品转移SlabelNo
	@Override
	public MsgRes saveArticleMoveLabel(String str) throws Exception {
		Collection<Rodata_BoxDModel> box=JSONArray.toCollection(JSONArray.fromObject(str),Rodata_BoxDModel.class);
		List<Rodata_BoxDModel> boxList=(List)box;
	
		for(Rodata_BoxDModel i : boxList){
			List  inList=new ArrayList();
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			
			inList.add(i.getEnterpriseNo());
			inList.add(i.getWarehouseNo());
			inList.add(i.getOwnerNo());
			inList.add(i.getsLabelNo());
			inList.add(i.getdLabelNo());
			inList.add(i.getArticleNo());
			inList.add(i.getPackingQty());
			inList.add(i.getProduceDate());
			inList.add(i.getExpireDate());
			inList.add(i.getQuality());
			
			inList.add(i.getLotNo());
			inList.add(i.getArticleQty());
			inList.add(i.getRgstName());
					
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_ArticleMoveLabel");
		
			System.out.println(inList);
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25401"),null);//确认成功
	}
	//整箱转移
		@Override
		public MsgRes saveMoveLabel(String str) throws Exception {
			String label[]=str.split(",");
			List  inList=new ArrayList();
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			
			inList.add(label[0].trim());//strEnterPriseNo 
			inList.add(label[1].trim());//strWarehose_No 
			inList.add(label[2].trim());// strOwnerNo 
			inList.add(label[3].trim());//strsLabelNo
			inList.add(label[4].trim());//strdLabelNo
			inList.add(label[5].trim());// strUserId
			
					
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_MoveLabel");
		
			System.out.println(inList);
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		
			
			return new MsgRes(true,TipUtil.getTipsByKey("E25401"),null);//确认成功
		}
	/**
	  退厂拼箱打包源标签商品列表
	 * @return
	 */
	@Override
	public ExtListDataBo<Rodata_BoxDModel> getRodata_sLabelNoArticleList(
			String enterpriseNo,
			String strWarehouseNo, String strRecedeNo,String strLabelNo,
			Integer start, 
			Integer limit) {
		String sql="select d.enterprise_no,d.owner_no,d.label_no," +
				//"nvl(pk.packing_unit, decode(d.packing_qty,a.qmin_operate_packing,a.qmin_operate_packing_unit,a.unit)) packing_unit,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,a.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,a.unit_packing) Unit,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,a.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,a.unit_packing) spec,"+
				"d.outstock_no,d.recede_no,d.article_no,"+
				"a.article_name,a.barcode,sum(d.article_qty)/d.packing_qty as article_qty,"+
                "sai.produce_date,sai.expire_date,sai.quality,sai.lot_no,d.packing_qty "+
				"from rodata_box_d d,bdef_defarticle a,stock_article_info sai,bdef_article_packing pk  "+
				"where d.enterprise_no=a.enterprise_no "+
                "and d.enterprise_no=sai.enterprise_no "+
                "and d.article_id=sai.article_id "+
                "and d.article_no=sai.article_no "+
				"and d.owner_no=a.owner_no  "+
				"and d.article_no=a.article_no  " +
				"and d.enterprise_no=pk.enterprise_no(+) " +
				"and d.article_no=pk.article_no(+)  " +
				"and d.packing_qty=pk.packing_qty(+) "+
				"and d.label_no='"+strLabelNo+"' " +
				"and d.recede_no='"+strRecedeNo+"' "+
                "group by d.enterprise_no,d.owner_no,d.label_no, "+
				"d.outstock_no,d.recede_no,d.article_no,d.packing_qty, "+
				"a.article_name,a.barcode,sai.produce_date," +
				"a.qmin_operate_packing,a.unit_packing," +
				"sai.expire_date,sai.quality,sai.lot_no,d.packing_qty  ";
	
		sql+=" order by d.article_no desc " ;
  	   	List<Rodata_BoxDModel> list=genDao.getListByNativeSql(sql, Rodata_BoxDModel.class, start,limit);
  	   	Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
  	   	ExtListDataBo<Rodata_BoxDModel> extListBo = new ExtListDataBo<Rodata_BoxDModel>(list, count);
  	   	return extListBo;
	}
	/**
	  退厂拼箱打包目的标签商品列表
	 * @return
	 */
	@Override
	public ExtListDataBo<Rodata_BoxDModel> getRodata_dLabelNoArticleList(
			String enterpriseNo,
			String strWarehouseNo, String strRecedeNo,String strLabelNo) {
		String sql="select d.enterprise_no,d.owner_no,d.label_no," +
				"d.recede_no,d.article_no,d.packing_qty," +
				//"nvl(pk.packing_unit, decode(d.packing_qty,a.qmin_operate_packing,a.qmin_operate_packing_unit,a.unit)) packing_unit," +
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,a.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,a.unit_packing) Unit,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,a.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,a.unit_packing) spec,"+
				"a.article_name,a.barcode,sum(d.article_qty)/d.packing_qty as article_qty " +
				"from rodata_box_d d,bdef_defarticle a,bdef_article_packing pk  " +
				"where d.enterprise_no=a.enterprise_no " +
				"and d.owner_no=a.owner_no " +
				"and d.article_no=a.article_no " +
				"and d.enterprise_no=pk.enterprise_no(+) " +
				"and d.article_no=pk.article_no(+)  " +
				"and d.packing_qty=pk.packing_qty(+) "+
				"and d.label_no='"+strLabelNo+"' " +
				"and d.recede_no='"+strRecedeNo+"' "+
			    "group by d.enterprise_no,d.owner_no,d.label_no," +
			    "a.qmin_operate_packing,a.unit_packing," +
				"d.recede_no,d.article_no,d.packing_qty, "+
				"a.article_name,a.barcode ";
		sql+=" order by d.article_no desc " ;
	   	List<Rodata_BoxDModel> list=genDao.getListByNativeSql(sql, Rodata_BoxDModel.class);
	   	Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
	   	ExtListDataBo<Rodata_BoxDModel> extListBo = new ExtListDataBo<Rodata_BoxDModel>(list, count);
	   	return extListBo;
	}
	/**
	 * 退厂拼箱打包标签列表
	 * @author hkl
	 * @param string
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public ExtListDataBo<Rodata_BoxMModel> getRodata_sLabelList(
			String strRecedeNo,String enterpriseNo,
			String warehouseNo) {
		String sql="select a.enterprise_no,a.warehouse_no," +
				"a.owner_no,a.label_no,a.status," +
				"a.recede_no,a.owner_cell_no " +
				"from rodata_box_m a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.recede_no='"+strRecedeNo+"' ";
		
		
	  	List<Rodata_BoxMModel> list=genDao.getListByNativeSql(sql, Rodata_BoxMModel.class);
	  	ExtListDataBo<Rodata_BoxMModel> extListBo = new ExtListDataBo<Rodata_BoxMModel>(list, 0);
	  	return extListBo;
	}
	
	/**
     * 	 *退货单号下拉
	 * @param  
     */
	@Override
	public List<ComboxBo> getRecedeNo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strRecedeNo) {
		
		String	strSql = "select distinct a.recede_no value," +
				" a.recede_no text, a.recede_no dropValue   " +
				"from rodata_box_m a,bdef_defowner c " +
				"where a.enterprise_no=c.enterprise_no " +
				"and a.owner_no=c.owner_no " +
				"and a.enterprise_No ='" + enterpriseNo + "' " +
				"and a.warehouse_No ='" + strWarehouseNo + "' " +
				" and c.owner_no in("+strWorkerOwner+") " ;			
	
	    if(!StringUtil.isEmpty(strRecedeNo))
			{
				String ws="and a.recede_no like '%"+strRecedeNo+"%' ";
				strSql=strSql+ws;
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
	@Override
	public MsgRes getLoadBox(String enterpriseNo, String warehouseNo,
			String strQuery) throws Exception {
		   List  outList=new ArrayList();
			List  resultList=new ArrayList();
			List  inList=new ArrayList();
			outList.add("S");
			outList.add("S");
			outList.add("S");
			outList.add("S");
				
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add("P");
			inList.add(strQuery);
			inList.add("D");
			inList.add(1);
			inList.add("2");
			inList.add("31");
			resultList=genDao.execProc(inList, outList, "pklg_wms_base.p_get_containernobase");
			if(resultList.get(3).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(3).toString());
			}
			
			return new MsgRes(true,resultList.get(0).toString(),null);
	}
	@Override
	public MsgRes saveMoveQty(String enterpriseNo, String warehouseNo,
			String WorkerNo,String str)
			throws Exception {
		    List  outList=new ArrayList();
			List  resultList=new ArrayList();
			List  inList=new ArrayList();
			outList.add("S");
				
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(str);
			inList.add(WorkerNo);
			
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_LabelGetCell");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			
			return new MsgRes(true,resultList.get(0).toString(),null);
	}


}
