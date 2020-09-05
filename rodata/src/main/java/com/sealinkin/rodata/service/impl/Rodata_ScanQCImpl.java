package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.service.IRodata_ScanQCService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_ScanQCImpl implements IRodata_ScanQCService {
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
	@Override
	public MsgRes  getRodata_ScanM(String enterpriseNo,
			String warehouseNo, String workerOwner, String strQuery) throws Exception {
		String sql="select a.recede_no,a.owner_no,a.supplier_no " +
				   "  from rodata_recede_m a " +
			       " where a.po_no='"+strQuery+"' " +
			       "   and a.enterprise_no='"+enterpriseNo+"' "+
				   "   and a.warehouse_no='"+warehouseNo+"' " +
				   "   and a.class_type='0' " +
				   "   and a.status='10' ";
		
		List<String> list = genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return new MsgRes(false,"原单号不存在","");
		}
	   	
//		String getSum ="select sum(a.recede_qty) from rodata_recede_d  a " +
//				       " where a.recede_no='"+list.get(0)+"' " +
//				       "   and a.enterprise_no='"+enterpriseNo+"' "+
//					   "   and a.warehouse_no='"+warehouseNo+"' ";
//		List<String> qty=genDao.getListByNativeSql(getSum);
//		
//		if(qty.size()==0){
//			return new MsgRes(false,"该单号明细不存在","");
//		}else{
//			return new MsgRes(true,qty.get(0),"");
//		}
		return new MsgRes(true,"",list);
}
	
	//未扫描商品
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_ScanD(String enterpriseNo, String warehouseNo,
			String strQuery, Integer start, Integer limit) throws Exception {
		String sql="select c.recede_no,c.article_name,c.barcode,rrd.packing_qty," +
				   "       (c.recede_qty - c.budget_qty -" +
				   "       nvl((select sum(rrds.budget_qty) from rodata_recede_d_scanlog rrds " +
				   "         where rrds.enterprise_no = c.enterprise_no " +
				   "           and rrds.warehouse_no = c.warehouse_no " +
				   "           and rrds.recede_no = c.recede_no " +
				   "           and rrds.article_no = c.article_no),0)) as unCheckQty, " +
				   "           (sum(recede_qty - budget_qty -nvl((select sum(rrds.budget_qty)"+
				   "           		                 from rodata_recede_d_scanlog rrds"+
				   "                           where rrds.enterprise_no = c.enterprise_no"+
				   "                         and rrds.warehouse_no = c.warehouse_no"+
				   "                         and rrds.recede_no = c.recede_no"+
				   "                         and rrds.article_no = c.article_no),"+
				   "                     0)) over(partition by c.recede_no)) as scanQty"+
				   
//				   "           sum(recede_qty - budget_qty - nvl((select sum(rrds.budget_qty) "+
//				   "           from rodata_recede_d_scanlog rrds "+
//				   "           where rrds.enterprise_no = c.enterprise_no "+
//				   "           and rrds.warehouse_no = c.warehouse_no "+
//				   "           and rrds.recede_no = c.recede_no),0)) over(partition by recede_no) as scanQty " +
				   
				   " from (select rrm.enterprise_no, rrm.warehouse_no,rrm.recede_no,bda.article_name,bda.article_no,rrd.packing_qty,bda.barcode, " +
				   "              sum(rrd.recede_qty) as recede_qty, " +
				   "               nvl(sum(rrd.budget_qty), 0) as budget_qty " +
				   "        from bdef_defarticle bda, rodata_recede_d rrd, rodata_recede_m rrm " +
				   " where bda.enterprise_no = rrd.enterprise_no "             +
				   "           and bda.article_no=rrd.article_no " +
				   "           and rrd.enterprise_no=rrm.enterprise_no " +
				   "           and rrd.warehouse_no=rrm.warehouse_no " +
				   "           and rrd.recede_no=rrm.recede_no " +
				   "           and rrm.enterprise_no='"+enterpriseNo+"' " +
				   "           and rrm.warehouse_no='"+warehouseNo+"' " +
				   "           and rrm.po_no='"+strQuery+"' " +
				   "         group by rrm.enterprise_no,rrm.warehouse_no,rrd.packing_qty,rrm.recede_no,bda.article_name,bda.article_no,bda.barcode ) c " +
				   " order by  recede_no,barcode " ;
		
		String totsql ="select count(*) " +
				   "      from  " +
				   "       (select rrm.recede_no,bda.article_name,rrd.packing_qty,  bda.barcode,sum(rrd.recede_qty)  as recede_qty ," +
				   "               nvl(sum(rrd.budget_qty),0) as budget_qty  " +
				   "          from bdef_defarticle bda,rodata_recede_d rrd,rodata_recede_m rrm " +
				   "         where bda.enterprise_no=rrd.enterprise_no " +
				   "           and bda.article_no=rrd.article_no " +
				   "           and rrd.enterprise_no=rrm.enterprise_no " +
				   "           and rrd.warehouse_no=rrm.warehouse_no " +
				   "           and rrd.recede_no=rrm.recede_no " +
				   "           and rrm.enterprise_no='"+enterpriseNo+"' " +
				   "           and rrm.warehouse_no='"+warehouseNo+"' " +
				   "           and rrm.po_no='"+strQuery+"' " +
				   "         group by rrm.recede_no,bda.article_name,rrd.packing_qty,bda.barcode) " +
				   " order by  recede_no,barcode " ;
		Integer count = genDao.getCountByNativeSql(totsql);
		
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class,start,limit);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, count);
	  	return extListBo;
	}
	
	//已扫描
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> ScanDNot(String enterpriseNo,
			String warehouseNo, String strQuery, String strWorker,
			Integer start, Integer limit) throws Exception {

		String sql="select recede_no,article_name,barcode,budget_qty as checkQty, " +
				   "       sum(budget_qty) over (partition by recede_no) as scanQty " +
				   "  from  " +
				   "       (select rrm.recede_no,bda.article_name," +
				   "               bda.barcode, nvl(sum(rrds.budget_qty),0) as budget_qty  " +
				   "          from bdef_defarticle bda,rodata_recede_d_scanlog rrds,rodata_recede_m rrm " +
				   "         where bda.enterprise_no=rrds.enterprise_no " +
				   "           and bda.article_no=rrds.article_no " +
				   "           and rrds.enterprise_no=rrm.enterprise_no " +
				   "           and rrds.warehouse_no=rrm.warehouse_no " +
				   "           and rrds.recede_no=rrm.recede_no " +
				   "           and rrds.rgst_name='"+strWorker+"' " +
				   "           and rrm.enterprise_no='" +enterpriseNo+"'" +
				   "           and rrm.warehouse_no='"+warehouseNo+"' " +
				   "           and rrm.po_no='"+strQuery+"' " +
				   "         group by rrm.recede_no,bda.article_name,bda.barcode) " +
				   " order by  recede_no,barcode " ;
				
		String totsql ="select count(*) " +
				   "  from  " +
				   "       (select rrm.recede_no,bda.article_name," +
				   "               bda.barcode, nvl(sum(rrds.budget_qty),0) as budget_qty  " +
				   "          from bdef_defarticle bda,rodata_recede_d_scanlog rrds,rodata_recede_m rrm " +
				   "         where bda.enterprise_no=rrds.enterprise_no " +
				   "           and bda.article_no=rrds.article_no " +
				   "           and rrds.enterprise_no=rrm.enterprise_no " +
				   "           and rrds.warehouse_no=rrm.warehouse_no " +
				   "           and rrds.recede_no=rrm.recede_no " +
				   "           and rrds.rgst_name='"+strWorker+"' " +
				   "           and rrm.enterprise_no='" +enterpriseNo+"'" +
				   "           and rrm.warehouse_no='"+warehouseNo+"' " +
				   "           and rrm.po_no='"+strQuery+"' " +
				   "         group by rrm.recede_no,bda.article_name,bda.barcode) ";
		
		List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class,start,limit);
		Integer count = genDao.getCountByNativeSql(totsql);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, count);
	  	return extListBo;
	
	}
	
	//检验条码并带出相关信息
	@Override
	public MsgRes checkArticleNo(String enterpriseNo, String barcode,
			String poNo, String warehouseNo) throws Exception {

		MsgRes msg=null;
		msg=getArticleForBarcodeImpl.checkBarcode(warehouseNo, barcode,enterpriseNo);
		if(msg.getIsSucc()){
			String sql="select c.supplier_no,b.article_no,b.article_name,c.owner_no,c.recede_no,a.packing_qty  " +
					   "  from rodata_recede_d a,bdef_defarticle b,rodata_recede_m c "+
				       " where c.enterprise_no='"+enterpriseNo+"' "+
				       "   and c.warehouse_no='"+warehouseNo+"' " +
				       "   and c.po_no='"+poNo+"' " +
				       "   and a.enterprise_no=c.enterprise_no " +
				       "   and a.warehouse_no=c.warehouse_no " +
				       "   and a.recede_no=c.recede_no " +
				       "   and a.enterprise_no=b.enterprise_no " +
				       "   and a.article_no=b.article_no" +		       
				       "   and b.article_no="+(msg.getObj().toString())+" ";		

			List<String> list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				msg.setObj(list);
				msg.setIsSucc(true);
				msg.setMsg("成功");
			}else{
				msg.setIsSucc(false);
				msg.setMsg("该商品不在此张表内或已验完");
			}
		}else{
			msg.setIsSucc(false);
			msg.setMsg("该条码不存在");
		}
		return msg;
	
	
	}
	@Override
	public MsgRes tscScan(String enterpriseNo, String warehouseNo,
			String ownerNo, String recedeNo, String articleNo,String scanNum, String workerNo)
			throws Exception {
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			List  inList=new ArrayList();
			outList.add("S");
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(ownerNo);
			inList.add(recedeNo);
			inList.add(articleNo);
			inList.add(scanNum);
			inList.add(workerNo);
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_rodata.p_save_scan_recede");
		
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true,"",null);
	}
	@Override
	public List<ComboxBo> getPoNoQC(String enterpriseNo,
			String warehouseNo, String strQuery, String pageSql)
			throws Exception {
		String sql="select distinct a.po_no value,a.po_no text,a.po_no dropValue  " +
				" from rodata_recede_m a " +
				"where  a.enterprise_no='"+enterpriseNo+"' " +
				"  and a.warehouse_no='" +warehouseNo+"' "+
				"  and a.class_type='0' " +
				"  and a.status='10' ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ft;
		}
		//货主编码、助记码、名称
		if(pageSql!=null && pageSql!=""){
		    sql+=" and a.po_no like '%"+pageSql+"%' ";
		}
		
		sql=sql+" order by a.po_no ";
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//换箱
	@Override
	public MsgRes recede(String enterpriseNo, String warehouseNo,
			String ownerNo, String recedeNo, String strWorker) throws Exception {
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		List  inList=new ArrayList();
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(recedeNo);
		inList.add(strWorker);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_rodata.p_cut_scan_recede");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",null);
	}
	
	//重新扫描
	@Override
	public MsgRes recedeAgain(String enterpriseNo, String warehouseNo,
			String ownerNo, String recedeNo, String strWorker) throws Exception {
		String sql="delete from rodata_recede_d_scanlog rrds " +
			       " where rrds.enterprise_no = '"+enterpriseNo+"' " +
			       "   and rrds.warehouse_no = '"+warehouseNo+"' " +
			       "   and rrds.recede_no = '"+recedeNo+"' " +
			       "   and rrds.rgst_name = '"+strWorker+"'  ";
		genDao.exceuteSql(sql);
		
		return new MsgRes(true,"",null);
	}	
}
