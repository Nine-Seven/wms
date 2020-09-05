package com.sealinkin.comm.service.impl;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleInfoModel;
import com.sealinkin.comm.model.AnsArticlePackingInfoModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;

@SuppressWarnings({"unchecked","rawtypes"})
public class GetArticleForBarcodeImpl implements IGetArticleForBarcodeService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	
	/**
	 * 校验商品条码
	 */
	@Override
	public MsgRes checkBarcode(String strWarehouseNo, String strBarcode,
			String strOwnerNo,String strEnterpriseNo) throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="";
		strSql="select bda.article_no " +
				"from bdef_defarticle bda " +
				"where bda.enterprise_no = '"+strEnterpriseNo+"' " +
				"and bda.owner_no = '"+strOwnerNo+"' " +
				"and bda.barcode = '"+strBarcode+"' " ;
		List<Bdef_ArticleInfoModel> list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}
		//货位商品编码
		strSql="select bda.article_no " +
				"from bdef_defarticle bda " +
				"where bda.owner_article_no = '"+strBarcode+"' " +
				"and bda.owner_no = '"+strOwnerNo+"' " +
				"and bda.status = '0' " +
				"and bda.enterprise_no = '"+strEnterpriseNo+"' ";
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}
		strSql="select bda.article_no " +
				"from bdef_defarticle bda, bdef_article_barcode bab " +
				"where bda.owner_no = bab.owner_no " +
				"and bda.enterprise_no = bab.enterprise_no " +
				"and bda.article_no = bab.article_no " +
				"and bab.barcode = '"+strBarcode+"' " +
				"and bab.owner_no = '"+strOwnerNo+"' " +
				"and bab.enterprise_no = '"+strEnterpriseNo+"' " ;
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;					
		}
		strSql="select bda.article_no " +
				"from bdef_defarticle bda, stock_box_d cbd, stock_box_m cbm " +
				"where bda.enterprise_no = cbd.enterprise_no " +
				"and bda.owner_no = cbd.owner_no " +
				"and bda.article_no = cbd.article_no " +
				"and cbd.warehouse_no = cbm.warehouse_no " +
				"and cbd.owner_no = cbm.owner_no " +
				"and cbd.enterprise_no = cbm.enterprise_no " +
				"and cbd.box_no = cbm.box_no " +
				"and CBM.warehouse_no = '"+strWarehouseNo+"' " +
				"and CBM.enterprise_no = '"+strEnterpriseNo+"' " +
				"and CBM.BOX_NO = '"+strBarcode+"' " +
				"and CBM.OWNER_NO = '"+strOwnerNo+"' " ;

		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);//箱码查询
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}
		strSql="select bda.article_no " +
				"from bdef_defarticle bda " +
				"where upper(bda.article_identifier) = upper('"+strBarcode+"') " +
				"and bda.status = '0' " +
				"and bda.enterprise_no = '"+strEnterpriseNo+"' " +
				"and bda.owner_no = '"+strOwnerNo+"' " ;
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}

		msg.setIsSucc(false);
		msg.setMsg("找不到相应的条码");
		return msg;
	}
	@Override
	public MsgRes checkBarcode(String strWarehouseNo, String strBarcode,String strEnterpriseNO)
			throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="";

		strSql="select bda.article_no " +
				"from bdef_defarticle bda " +
				"where bda.enterprise_no = '"+strEnterpriseNO+"' " +
				"and bda.barcode = '"+strBarcode+"' " ;
		List<Bdef_ArticleInfoModel> list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			System.out.println("strArticleNo:"+strArticleNo);
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}
		strSql="select bda.article_no " +
				"from bdef_defarticle bda, bdef_article_barcode bab " +
				"where bda.owner_no = bab.owner_no " +
				"and bda.enterprise_no = bab.enterprise_no " +
				"and bda.article_no = bab.article_no " +
				"and bab.barcode = '"+strBarcode+"' " +
				"and bab.enterprise_no = '"+strEnterpriseNO+"' " ;
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;					
		}

		//货位商品编码
		strSql="select bda.article_no " +
				"from bdef_defarticle bda " +
				"where bda.owner_article_no = '"+strBarcode+"' " +
				"and bda.status = '0' " +
				"and bda.enterprise_no = '"+strEnterpriseNO+"' ";
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}
		strSql="select bda.article_no " +
				"from bdef_defarticle bda, stock_box_d cbd, stock_box_m cbm " +
				"where bda.enterprise_no = cbd.enterprise_no " +
				"and bda.owner_no = cbd.owner_no " +
				"and bda.article_no = cbd.article_no " +
				"and cbd.warehouse_no = cbm.warehouse_no " +
				"and cbd.owner_no = cbm.owner_no " +
				"and cbd.enterprise_no = cbm.enterprise_no " +
				"and cbd.box_no = cbm.box_no " +
				"and CBM.warehouse_no = '"+strWarehouseNo+"' " +
				"and CBM.enterprise_no = '"+strEnterpriseNO+"' " +
				"and CBM.BOX_NO = '"+strBarcode+"' " ;

		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);//箱码查询
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}
		strSql="select bda.article_no " +
				"from bdef_defarticle bda " +
				"where upper(bda.article_identifier) = upper('"+strBarcode+"') " +
				"and bda.status = '0' " +
				"and bda.enterprise_no = '"+strEnterpriseNO+"' ";
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			msg.setIsSucc(true);
			String strArticleNo="";
			for(Bdef_ArticleInfoModel m:list)
			{
				strArticleNo+="'"+m.getArticleNo()+"',";
			}
			msg.setObj(strArticleNo.substring(0, strArticleNo.length()-1));			
			return msg;
		}

		msg.setIsSucc(false);
		msg.setMsg("找不到相应的条码");
		return msg;
	}
    
	/**
	 * 根据扫描码获取商品信息 huangb 20160514
	 * 返回信息：
	 * 商品编码，商品包装
	 */
	@Override
	public List<Bdef_ArticleInfoModel> getArticleByScanNo(String strWarehouseNo, String strBarcode,
			String strOwnerNo,String strEnterpriseNo) throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="";
		strSql="select bda.article_no,bda.UNIT_PACKING as packing_Qty " +
				"from bdef_defarticle bda " +
				"where bda.enterprise_no = '"+strEnterpriseNo+"' " +
				"and bda.owner_no = '"+strOwnerNo+"' " +
				"and bda.barcode = '"+strBarcode+"' " ;
		List<Bdef_ArticleInfoModel> list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			//msg.setIsSucc(true);	
			//msg.setObj(list);
			return list;
		}
		//货位商品编码
		strSql="select bda.article_no,bda.UNIT_PACKING as packing_Qty " +
				"from bdef_defarticle bda " +
				"where bda.owner_article_no = '"+strBarcode+"' " +
				"and bda.owner_no = '"+strOwnerNo+"' " +
				"and bda.status = '0' " +
				"and bda.enterprise_no = '"+strEnterpriseNo+"' ";
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			//msg.setIsSucc(true);
			//sg.setObj(list);	
			return list;
		}
		strSql="select bda.article_no,bab.packing_qty " +
				"from bdef_defarticle bda, bdef_article_barcode bab " +
				"where bda.owner_no = bab.owner_no " +
				"and bda.enterprise_no = bab.enterprise_no " +
				"and bda.article_no = bab.article_no " +
				"and bab.barcode = '"+strBarcode+"' " +
				"and bab.owner_no = '"+strOwnerNo+"' " +
				"and bab.enterprise_no = '"+strEnterpriseNo+"' " ;
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			//msg.setIsSucc(true);
			//msg.setObj(list);			
			return list;					
		}
		strSql="select bda.article_no,cbm.QTY as packing_Qty " +
				"from bdef_defarticle bda, stock_box_d cbd, stock_box_m cbm " +
				"where bda.enterprise_no = cbd.enterprise_no " +
				"and bda.owner_no = cbd.owner_no " +
				"and bda.article_no = cbd.article_no " +
				"and cbd.warehouse_no = cbm.warehouse_no " +
				"and cbd.owner_no = cbm.owner_no " +
				"and cbd.enterprise_no = cbm.enterprise_no " +
				"and cbd.box_no = cbm.box_no " +
				"and CBM.warehouse_no = '"+strWarehouseNo+"' " +
				"and CBM.enterprise_no = '"+strEnterpriseNo+"' " +
				"and CBM.BOX_NO = '"+strBarcode+"' " +
				"and CBM.OWNER_NO = '"+strOwnerNo+"' " ;

		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);//箱码查询
		if(list.size()>0)
		{
			//msg.setIsSucc(true);
			//msg.setObj(list);			
			return list;
		}
		strSql="select bda.article_no,bda.UNIT_PACKING as packing_Qty " +
				"from bdef_defarticle bda " +
				"where upper(bda.article_identifier) = upper('"+strBarcode+"') " +
				"and bda.status = '0' " +
				"and bda.enterprise_no = '"+strEnterpriseNo+"' " +
				"and bda.owner_no = '"+strOwnerNo+"' " ;
		list = genDao.getListByNativeSql(strSql, Bdef_ArticleInfoModel.class);
		if(list.size()>0)
		{
			//msg.setIsSucc(true);
			//msg.setObj(list);		
			return list;
		}

		//msg.setIsSucc(false);
		//msg.setMsg("找不到相应的条码");
		return list;
	}
	
	/**
	 * 根据商品编号获取商品信息 huangb 20160525
	 * 返回信息：商品包装，单位，规格，委托业主信息
	 * 商品编码，商品包装
	 */
	@Override
	public List<AnsArticlePackingInfoModel> getArticlePackingInfoByScanNo(String strEnterpriseNo, String strArticleNo) 
			throws Exception {
		String strSql="";
		strSql=" SELECT a.owner_no     as ownerNo, " +
				   "        a.barcode      as barCode, " + 
	               "        a.article_no   as articleNo, " +
		           "        a.article_name as articleName, " +
	               "        b.packing_qty  as packingQty, " + 
	               "        b.packing_unit as unit, " + 
	               "        b.spec " + 
	               "  from bdef_defarticle a, bdef_article_packing b " + 
	               " where a.article_no = b.article_no " + 
	               "   and a.enterprise_no = b.enterprise_no " + 
	               "   and a.article_no in ("+ strArticleNo +") " + 
	               "   and a.enterprise_no = '" + strEnterpriseNo + "'" +
	               "  order by a.article_no, b.packing_qty desc " ;
		List<AnsArticlePackingInfoModel> list = genDao.getListByNativeSql(strSql, AnsArticlePackingInfoModel.class);
		
		return list;
	}

}
