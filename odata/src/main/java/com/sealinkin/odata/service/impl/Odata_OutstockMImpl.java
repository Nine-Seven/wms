package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_OutstockDirectModel;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_PntsetmoduleReportModel;

/**
 * 模块名称：拣货发单
 * 模块编码：3301和3401
 * 创建：周欢
 * 修改：贺康利
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_OutstockMImpl implements IOdata_OutstockM
{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/**
	 * 拣货发单》按客户或客户商品明细信息
	 * strFlag=1:自动成单发单储区
	 * strFlag=2:自动成单发单明细
	 * strFlag=3:自动成单波次号
	 * strFlag=4:手动成单未发单客户信息
	 * strFlag=5:按配送对象发单取配送对象
	 * strFlag=6:作业类型 huangb 20160706
	 * strFlag=7:未发单下架指示  huangb 20160805
	 * @throws Exception 
	 */
	public ExtListDataBo<Odata_OutstockDirectModel> getOdataOutStockDirect(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strFlag,
			String str,
			String strOwnerNo2,
			String strSendFlag,
			String strIndustryFlag) throws Exception 
	{//check
		String strSql = "";
		if(strFlag != null && strFlag.equals("1"))
		{//strFlag=1
			strSql = "select distinct cdc.ware_no||cdc.area_no as wareaNo," +
					" cda.area_name,cdc.ware_no,cdc.area_no,  " +
					//获取同一个物流箱是否存在不同状态 huangb 20160805
					" (case when exists (select 'x' " +
					"    from odata_outstock_direct oddi " +
					"   where oddi.enterprise_no = ood.enterprise_no " +
					"     and oddi.warehouse_no = ood.warehouse_no " +
					"     and oddi.wave_no = ood.wave_no " +
					"     and oddi.batch_no = ood.batch_no " +
					"     and oddi.s_container_no = ood.s_container_no " +
					"     and oddi.status <> ood.status " +
					"     and oddi.s_container_no <> 'N') then '不可发单' else '可以发单' end) isSengflag " +
					"from odata_outstock_direct ood,cdef_defcell cdc,cdef_defarea cda, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.status='10' and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.enterprise_no=cdc.enterprise_no and ood.enterprise_no=cda.enterprise_no " +
					" and ood.warehouse_no=cdc.warehouse_no and ood.s_cell_no=cdc.cell_no " +
					" and cdc.warehouse_no=cda.warehouse_no and cdc.area_no=cda.area_no and cdc.ware_no=cda.ware_no " +
					" AND OOD.OUTSTOCK_TYPE<>'2' AND OOD.OUTSTOCK_TYPE<>'5'" +
				    " AND OOD.OUTSTOCK_TYPE<>'6' AND OOD.OUTSTOCK_TYPE<>'3'" +
				    " AND OOD.OUTSTOCK_TYPE<>'4' " ;					
		}//strFlag=1
		else if(strFlag != null && strFlag.equals("2"))
		{//strFlag=2
			//获取批属性字段
			String strAttributeFields=getArticleAttrString("1").get(0);
			String strAttributeGroupByFields=getArticleAttrString("2").get(0);
			if(!"".equals(strAttributeFields))
			{
				strAttributeFields+=", ";
				if(strAttributeFields.indexOf("QUALITY")>0)
				{
					strAttributeFields+="f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality, ";
				}
			}
			
			if(!"".equals(strAttributeGroupByFields))
			{
				strAttributeGroupByFields=","+strAttributeGroupByFields;
			}
			
			strSql = "select  ood.warehouse_no,ood.outstock_type,ood.exp_no," +
					"f_get_fieldtext('N', 'outstock_type',ood.outstock_type) outstockTypeText," +
					//"nvl(pk.packing_unit, decode(ood.packing_qty,vbda.qmin_operate_packing,vbda.qmin_operate_packing_unit,vbda.unit)) packing_unit,"+
					"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingUnit,"+
	                "f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.qmin_operate_packing) packingUnitQmin,"+
	                "f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.unit_packing) Unit,"+
	                "f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingSpec,"+
	                "f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.qmin_operate_packing) packingSpecQmin,"+
	                "f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.unit_packing) spec,"+
					"ood.stock_type," +
					"ood.operate_date,ood.batch_no,ood.operate_type," +
					"ood.EXP_TYPE,f_get_fieldtext('N', 'EXP_TYPE',ood.EXP_TYPE) expTypeText," +
					" ood.s_cell_no,ood.s_cell_id,ood.locate_qty ,ood.packing_qty," +
					"vbda.barcode, vbda.ARTICLE_NAME,vbda.OWNER_ARTICLE_NO as ownerArticleNo,sai.* "+
					"from odata_outstock_direct ood," +
					"cdef_defcell cdc, cdef_defarea cda," +
					"bdef_defarticle vbda," +
					"( select sai.article_no," +
					strAttributeFields+
					"sai.article_id,sai.enterprise_no " +					
					/*getArticleAttrString("1").get(0)+*/ 
					/*", f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality "+*/
					/**/
					" from odata_outstock_direct ood," +
					" stock_article_info sai " +
					" where  ood.article_no=sai.article_no " +
					"and ood.article_id=sai.article_id " +
					"group By sai.article_no,sai.article_id,sai.enterprise_no,sai.QUALITY " + 
					/*getArticleAttrString("2").get(0) +*/
					strAttributeGroupByFields+
					") sai,bdef_article_packing pk, odata_locate_batch olb  " +
					" where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.article_no=vbda.article_no " +
					" and ood.s_cell_no=cdc.cell_no " +
					" and vbda.enterprise_no='"+enterpriseNo+"' " +
					" and ood.enterprise_no=cdc.enterprise_no " +
					" and ood.enterprise_no=cda.enterprise_no " +
					" and ood.enterprise_no=vbda.enterprise_no " +
					" and ood.enterprise_no=sai.enterprise_no " +
					" and cda.warehouse_no=ood.warehouse_no" +
					" and cda.warehouse_no=cdc.warehouse_no" +
					" and cda.ware_no=cdc.ware_no" +
					" and cda.area_no=cdc.area_no " +
					" and ood.owner_no=vbda.OWNER_NO " +
					" and ood.status='10' and ood.article_no=sai.article_no  " +
					" and ood.article_id=sai.article_id   " +
					" and ood.article_no=pk.article_no(+)" +
					" and ood.packing_qty=pk.packing_qty(+)" +
					" and ood.enterprise_no=pk.enterprise_no(+) " +
					" and not exists (select 'x' " +
					"                   from odata_outstock_direct oddi " +
					"                  where oddi.enterprise_no = ood.enterprise_no " +
					"                    and oddi.warehouse_no = ood.warehouse_no " +
					"                    and oddi.wave_no = ood.wave_no " +
					"                    and oddi.batch_no = ood.batch_no " +
					"                    and oddi.s_container_no = ood.s_container_no " +
					"                    and oddi.status <> ood.status " +
					"                    and oddi.s_container_no <> 'N') " +
					" and ood.warehouse_no='"+strWarehouseNo+"' " +
					" and ood.owner_no in("+strOwnerNo+")" ;		
			if(strSendFlag != null && strSendFlag.equals("auto"))
			{
				strSql += " and (ood.outstock_type = '0' or ood.outstock_type = '1' )" ;
			}else if(strSendFlag != null && strSendFlag.equals("man"))
			{
				strSql += " and ood.outstock_type in ('0','1') and ood.pick_type = '0' ";
			}
		}//strFlag=2
		else if(strFlag != null && strFlag.equals("3"))
		{
			strSql = "select distinct ood.wave_no,ood.batch_no,outstock_type,ood.source_type," +
					" f_get_fieldtext('N','OUTSTOCK_TYPE',ood.outstock_type) outstockTypeText,  " +
					" f_get_fieldtext('ODATA_OUTSTOCK_M','SOURCE_TYPE',ood.source_type) sourceTypeText  " +
					"from odata_outstock_direct ood,ODATA_WAVE_TRACE owt, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.warehouse_no=owt.warehouse_no " +
					" and ood.enterprise_no=owt.enterprise_no" +
					" and ood.wave_no=owt.wave_no and ood.enterprise_no='"+enterpriseNo+"'" +
					" and ood.status='10' and ood.warehouse_no='" + strWarehouseNo + "' " ;		
			if(strSendFlag != null && strSendFlag.equals("auto"))
			{
				strSql += " AND OOD.OUTSTOCK_TYPE<>'2' AND OOD.OUTSTOCK_TYPE<>'5'" +
						  " AND OOD.OUTSTOCK_TYPE<>'6' AND OOD.OUTSTOCK_TYPE<>'3'" +
						  " AND OOD.OUTSTOCK_TYPE<>'4'" ;
			}else if(strSendFlag != null && strSendFlag.equals("man"))
			{
				strSql += " AND OOD.OUTSTOCK_TYPE<>'2' AND OOD.OUTSTOCK_TYPE<>'5'" +
						  " AND OOD.OUTSTOCK_TYPE<>'6' AND OOD.OUTSTOCK_TYPE<>'3'" +
						  " AND OOD.OUTSTOCK_TYPE<>'1' AND OOD.OUTSTOCK_TYPE<>'4'" +
						  "  and ood.pick_type = '0' " ;
			}
		}
		else if(strFlag != null && strFlag.equals("4"))
		{
			strSql = "select " +
					"    distinct ood.cust_no,bdc.cust_name," +
					"    case when exists(   "+
					"              select    "+
					"               '1'    "+
					"              from    "+
					"                odata_outstock_direct a,   "+
					"                cdef_defcell b,   "+
					"                cdef_defarea c     "+
					"              where                                            "+
					"                a.status='11' and a.enterprise_no=b.enterprise_no " +
					"                and b.enterprise_no=c.enterprise_no   "+
					"                and a.warehouse_no=b.warehouse_no   "+
					"                and a.s_cell_no=b.cell_no    "+
					"                and b.warehouse_no=c.warehouse_no   "+
					"                and b.area_no=c.area_no    "+
					"                and b.ware_no=c.ware_no    "+
					"                and a.warehouse_no=ood.warehouse_no    "+
					"                and a.owner_no=ood.owner_no                   "+
					"                and a.exp_type=ood.exp_type   "+
					"                and a.wave_no=ood.wave_no   "+
					"                and a.outstock_type=ood.outstock_type    "+
					"                and b.ware_no||b.area_no=cdc.ware_no||cdc.area_no   "+
					"                and a.operate_type = ood.operate_type     "+
					"                and a.cust_no=ood.cust_no   "+
					"              ) then '1'  else '0' end flag " +	
					"from odata_outstock_direct ood," +
					"bdef_defcust bdc," +
					"cdef_defcell cdc," +
					"cdef_defarea cda, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.cust_no=bdc.cust_no(+) " +
					" and ood.owner_no=bdc.owner_no(+) " +
					" and ood.enterprise_no=bdc.enterprise_no(+) " +
					" and ood.enterprise_no=cdc.enterprise_no " +
					" and ood.warehouse_no=cdc.warehouse_no " +
					" and ood.s_cell_no=cdc.cell_no " +
					" and cdc.enterprise_no=cda.enterprise_no " +
					" and cdc.warehouse_no=cda.warehouse_no " +
					" and cdc.area_no=cda.area_no " +
					" and cdc.ware_no=cda.ware_no " +
					" and ood.status='10' " +
					" and ood.warehouse_no='" + strWarehouseNo + "' " +
					" and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.owner_no in(" + strOwnerNo + ")" ;		
		}
		else if(strFlag != null && strFlag.equals("5")){
			strSql ="select distinct ood.deliver_obj,"+
		           "(case when ood.deliver_obj = ood.cust_no then bdc.cust_name else 'N' END) cust_name "+
		       " from odata_outstock_direct ood,bdef_defcust bdc, odata_locate_batch olb "+
		       " where 1=1 " +
		       " and olb.enterprise_no = ood.enterprise_no" +
			   " and olb.warehouse_no = ood.warehouse_no" +
			 //有混合业主的情况 不能关联委托业主 huangb 20160803
			   //" and olb.owner_no = ood.owner_no" +
			   " and olb.wave_no = ood.wave_no" +
			   " and olb.batch_no = ood.batch_no" +
			   " and olb.industry_flag = '" + strIndustryFlag + "'" +
		       " and ood.cust_no=bdc.cust_no and ood.owner_no=bdc.owner_no "+
		       " and ood.enterprise_no=bdc.enterprise_no and ood.status='10' "+
		       " and ood.warehouse_no='" + strWarehouseNo + "' " +
		       " and ood.owner_no in(" + strOwnerNo + ")" +	
		       " and ood.enterprise_no='"+enterpriseNo+"' ";
		}
		else if(strFlag != null && strFlag.equals("6"))
		{
			strSql =" select distinct '['|| ltrim(ood.OPERATE_TYPE)||']'||wdf.text operate_type " +
					"from odata_outstock_direct ood,wms_deffieldval wdf ,odata_locate_batch olb  " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.status='10' " +
					" and ood.OPERATE_TYPE=wdf.value " +
					" and WDF.COLNAME='OPERATE_TYPE' "+
					" and wdf.table_name='N' ";
		}
		//未发单下架指示 huangb 20160805
		else if(strFlag != null && strFlag.equals("7"))
		{//strFlag=2
			//获取批属性字段
			String strAttributeFields=getArticleAttrString("1").get(0);
			String strAttributeGroupByFields=getArticleAttrString("2").get(0);
			if(!"".equals(strAttributeFields))
			{
				strAttributeFields+=", ";
				if(strAttributeFields.indexOf("QUALITY")>0)
				{
					strAttributeFields+="f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality, ";
				}
			}
			
			if(!"".equals(strAttributeGroupByFields))
			{
				strAttributeGroupByFields=","+strAttributeGroupByFields;
			}
			
			strSql = "select  ood.warehouse_no,ood.outstock_type,ood.exp_no," +
					"f_get_fieldtext('N', 'outstock_type',ood.outstock_type) outstockTypeText," +
					//"nvl(pk.packing_unit, decode(ood.packing_qty,vbda.qmin_operate_packing,vbda.qmin_operate_packing_unit,vbda.unit)) packing_unit,"+
					"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingUnit,"+
	                "f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.qmin_operate_packing) packingUnitQmin,"+
	                "f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.unit_packing) Unit,"+
	                "f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingSpec,"+
	                "f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.qmin_operate_packing) packingSpecQmin,"+
	                "f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,vbda.unit_packing) spec,"+
					"ood.stock_type," +
					"ood.operate_date,ood.batch_no,ood.operate_type," +
					"ood.EXP_TYPE,f_get_fieldtext('N', 'EXP_TYPE',ood.EXP_TYPE) expTypeText," +
					" ood.s_cell_no,ood.s_cell_id,ood.locate_qty ,ood.packing_qty," +
					" vbda.barcode, vbda.ARTICLE_NAME,vbda.OWNER_ARTICLE_NO as ownerArticleNo," +
					" sai.*, " +
					" (case when exists (select 'x' " +
					"                      from odata_outstock_direct oddi " +
					"                     where oddi.enterprise_no = ood.enterprise_no " +
					"                      and oddi.warehouse_no = ood.warehouse_no " +
					"                      and oddi.wave_no = ood.wave_no " +
					"                      and oddi.batch_no = ood.batch_no " +
					"                      and oddi.s_container_no = ood.s_container_no " +
					"                      and oddi.status <> ood.status " +
					"                      and oddi.s_container_no <> 'N') then '不可发单' else '可以发单' end) isSengflag "+
					"from odata_outstock_direct ood," +
					"cdef_defcell cdc, cdef_defarea cda," +
					"bdef_defarticle vbda," +
					"( select sai.article_no," +
					strAttributeFields+
					"sai.article_id,sai.enterprise_no " +					
					/*getArticleAttrString("1").get(0)+*/ 
					/*", f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality "+*/
					/**/
					" from odata_outstock_direct ood," +
					" stock_article_info sai " +
					" where  ood.article_no=sai.article_no " +
					"and ood.article_id=sai.article_id " +
					"group By sai.article_no,sai.article_id,sai.enterprise_no,sai.QUALITY " + 
					/*getArticleAttrString("2").get(0) +*/
					strAttributeGroupByFields+
					") sai,bdef_article_packing pk, odata_locate_batch olb  " +
					" where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.article_no=vbda.article_no " +
					" and ood.s_cell_no=cdc.cell_no " +
					" and vbda.enterprise_no='"+enterpriseNo+"' " +
					" and ood.enterprise_no=cdc.enterprise_no " +
					" and ood.enterprise_no=cda.enterprise_no " +
					" and ood.enterprise_no=vbda.enterprise_no " +
					" and ood.enterprise_no=sai.enterprise_no " +
					" and cda.warehouse_no=ood.warehouse_no" +
					" and cda.warehouse_no=cdc.warehouse_no" +
					" and cda.ware_no=cdc.ware_no" +
					" and cda.area_no=cdc.area_no " +
					" and ood.owner_no=vbda.OWNER_NO " +
					" and ood.status < '13' " +
					" and ood.article_no=sai.article_no  " +
					" and ood.article_id=sai.article_id   " +
					" and ood.article_no=pk.article_no(+)" +
					" and ood.packing_qty=pk.packing_qty(+)" +
					" and ood.enterprise_no=pk.enterprise_no(+) " +
					" and ood.warehouse_no='"+strWarehouseNo+"' " +
					" and ood.owner_no in("+strOwnerNo+")" ;		
			if(strSendFlag != null && strSendFlag.equals("auto"))
			{
				strSql += " and (ood.outstock_type = '0' or ood.outstock_type = '1' )" ;
			}else if(strSendFlag != null && strSendFlag.equals("man"))
			{
				strSql += " and ood.outstock_type in ('0','1') and ood.pick_type = '0' ";
			}
		}
				
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		
		//排序
		if(strFlag != null && strFlag.equals("1"))
		{
			strSql = strSql + "order by  cdc.ware_no||cdc.area_no " ;
		}
		if(strFlag != null && strFlag.equals("2"))
		{
			strSql = strSql + "order by  ood.s_cell_no,ood.article_no,ood.exp_no " ;
		}
		if(strFlag != null && strFlag.equals("3"))
		{
			strSql = strSql + "order by ood.wave_no,ood.batch_no " ;
		}
		if(strFlag != null && strFlag.equals("4"))
		{
			strSql = strSql + "order by  ood.wave_no " ;
		}
		if(strFlag != null && strFlag.equals("5"))
		{
			strSql = strSql + "order by  ood.deliver_obj " ;
		}
		if(strFlag != null && strFlag.equals("6"))
		{
			strSql = strSql + " order by '[' || ltrim(ood.OPERATE_TYPE) || ']' || wdf.text " ;
		}
		List<Odata_OutstockDirectModel> list = genDao.getListByNativeSql(strSql,Odata_OutstockDirectModel.class);
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+strSql+")");
		ExtListDataBo<Odata_OutstockDirectModel> extListBo = new ExtListDataBo<Odata_OutstockDirectModel>(list, count);
        return extListBo;
	}//check
	
	/**
	 * 拣货发单》下拉选项
	 * strFlag=1:货主
	 * strFlag=2:出货单别
	 * strFlag=3:波次号
	 * strFlag=4:下架类型
	 * strFlag=5:储区
	 * strFlag=6:作业类型
	 * strFlag=7：source_type
	 * strFlag=8:批次号（带ALL）
	 * strFlag=9:批次号
	 */
	public List<ComboxBo> getOdata_GetCombo(String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner,
			String strFlag,
			String str, 
			String strSendFlag,
			int intStart, int intPagesize,
			String strIndustryFlag) 
		{//cmb
		String strSql="";
		if(strFlag != null && strFlag.equals("1"))
		{
			//修改获取委托业主方式 huangb 20160803
			strSql  = " select distinct olb.owner_no value, " + 
					  "                 nvl(t2.owner_name,'ALL') text, " + 
					  "                 '[' || ltrim(olb.owner_no) || ']' || nvl(t2.owner_name,'ALL') dropValue " + 
					  "            from odata_outstock_direct ood, " + 
					  "                 bdef_defowner         t2, " + 
					  "                 odata_locate_batch    olb " + 
					  "           where 1 = 1 " + 
					  "             and olb.enterprise_no = ood.enterprise_no " + 
					  "             and olb.warehouse_no = ood.warehouse_no " + 
					  "             and olb.wave_no = ood.wave_no " + 
					  "             and olb.batch_no = ood.batch_no " + 
					  "             and olb.industry_flag = '" + strIndustryFlag + "' " + 
					  "             and t2.enterprise_no(+) = olb.enterprise_no " + 
					  "             and t2.owner_no(+) = olb.owner_no " +
					  "             and ood.status = '10' " + 
					  "             and ood.enterprise_no = '" + enterpriseNo + "' " + 
					  "              %s1 %s2 %s3  " + 
					  "           order by olb.owner_no desc ";
			
			/*strSql  =  "select distinct ood.owner_no value,t2.owner_name text,"  + 
					" '['|| ltrim(ood.owner_no)||']'||t2.owner_name dropValue "  + 
					"from odata_outstock_direct ood, bdef_defowner t2, odata_locate_batch olb " +
					" where 1 = 1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.status='10' and t2.enterprise_no='"+enterpriseNo+"' " +
					" and t2.enterprise_no=ood.enterprise_no " +
					" and ood.owner_no = t2.owner_no %s1 %s2 %s3 order by ood.owner_no ";	*/
			/*if(strSendFlag != null && strSendFlag.trim().equals("auto"))
			{
				strSql += "AND OOD.OUTSTOCK_TYPE in ('0','1') order by ood.owner_no" ;
			}else if(strSendFlag != null && strSendFlag.trim().equals("man"))
			{
				strSql += " and OOD.OUTSTOCK_TYPE = '0' " +
						 " and ood.pick_type = '0' order by ood.owner_no" ;
			}*/
		}else if(strFlag != null && strFlag.equals("2"))
		{
			strSql = "select distinct ood.EXP_TYPE value,wdf.text text," +
					"'['|| ltrim(ood.EXP_TYPE)||']'||wdf.text dropValue " +
					"from odata_outstock_direct ood,wms_deffieldval wdf, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.status='10' and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.exp_type=wdf.value " +
					" and wdf.table_name='N' and wdf.colname='EXP_TYPE' %s2 %s3 " ;
			if(strSendFlag != null && strSendFlag.trim().equals("auto"))
			{
				strSql += "AND OOD.OUTSTOCK_TYPE<>'4' " +
						" AND OOD.OUTSTOCK_TYPE<>'2' AND OOD.OUTSTOCK_TYPE<>'5'" +
						" AND OOD.OUTSTOCK_TYPE<>'6' AND OOD.OUTSTOCK_TYPE<>'3'" ;
			}else if(strSendFlag != null && strSendFlag.trim().equals("man"))
			{
				strSql += " AND OOD.OUTSTOCK_TYPE<>'4' " +
						 " AND OOD.OUTSTOCK_TYPE<>'2' AND OOD.OUTSTOCK_TYPE<>'5'" +
						 " AND OOD.OUTSTOCK_TYPE<>'6' AND OOD.OUTSTOCK_TYPE<>'3'" +
						 " and ood.pick_type = '0' " ;
			}
				
							
		}else if(strFlag != null && strFlag.equals("3"))
		{
			strSql ="select * from (select distinct ood.WAVE_No value,ood.WAVE_No text,ood.WAVE_No dropValue " +
					"from odata_outstock_direct ood, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.status='10' %s1  %s2 %s3 "+ 
					" order by ood.wave_no) "+
					" union all select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from dual " ;/*+
					"order by value" ;*/
		}else if(strFlag != null && strFlag.equals("4"))
		{
			/*strSql = "select distinct ood.batch_no value,ood.batch_no text,ood.batch_no dropValue " +
					"from odata_outstock_direct ood " +
					"where ood.status='10' %s1  %s2 %s3 " +
					"order by ood.batch_no " ;*/
			strSql = "select distinct ood.outstock_type as value,ood.outstock_type as text," +
					"'['||ood.outstock_type||']'||f_get_fieldtext('N','OUTSTOCK_TYPE',ood.outstock_type) as dropValue  " +
					"from odata_outstock_direct ood, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.status='10' %s1 %s2 %s3 " +
					" order by ood.outstock_type desc";
		}else if(strFlag != null && strFlag.equals("5"))
		{
			strSql = 
					"select 'ALL' as value,'ALL' as dropValue,'ALL' as text from odata_outstock_direct "+
					"union select distinct cdc.ware_no||cdc.area_no value," +
					"'['||cdc.ware_no||cdc.area_no||']'||cda.area_name dropValue,cdc.ware_no text " +
					"from odata_outstock_direct ood,cdef_defcell cdc,cdef_defarea cda, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.status='10' and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.enterprise_no=cdc.enterprise_no and ood.enterprise_no=cda.enterprise_no " +
					" and ood.warehouse_no=cdc.warehouse_no and ood.s_cell_no=cdc.cell_no " +
					" and cdc.warehouse_no=cda.warehouse_no and cdc.area_no=cda.area_no" +
					" AND OOD.OUTSTOCK_TYPE<>'2' AND OOD.OUTSTOCK_TYPE<>'5'" +
				    " AND OOD.OUTSTOCK_TYPE<>'6' AND OOD.OUTSTOCK_TYPE<>'3'" +
				    " AND OOD.OUTSTOCK_TYPE<>'4' " +
				    " and ood.pick_type = '0' " +
				    " and cdc.ware_no=cda.ware_no %s1  %s2 %s3 " +
					" order by  value " ;
		}else if(strFlag != null && strFlag.equals("6"))
		{
			strSql ="select 'ALL' as value,'ALL' as text,'ALL' as dropValue from odata_outstock_direct "+ 
					"union select distinct ood.OPERATE_TYPE value,wdf.text text," +
					"'['|| ltrim(ood.OPERATE_TYPE)||']'||wdf.text dropValue " +
					"from odata_outstock_direct ood,wms_deffieldval wdf ," +
					"cdef_defcell cdc,cdef_defarea cda, odata_locate_batch olb  " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.status='10' and ood.OPERATE_TYPE=wdf.value and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.enterprise_no=cdc.enterprise_no and ood.enterprise_no=cda.enterprise_no " +
					" and ood.warehouse_no=cdc.warehouse_no and ood.s_cell_no=cdc.cell_no " +
					" and cdc.warehouse_no=cda.warehouse_no and cdc.area_no=cda.area_no " +
					" and cdc.ware_no=cda.ware_no AND WDF.COLNAME='OPERATE_TYPE' "+
					" and wdf.table_name='N' %s1  %s2 %s3  " +
					" order by value desc " ;
		}else if(strFlag != null && strFlag.equals("7"))
		{
			strSql = "select distinct ood.source_type value,wdf.text text," +
					"'['|| ltrim(ood.SOURCE_TYPE)||']'||wdf.text dropValue " +
					"from odata_outstock_direct ood,wms_deffieldval wdf," +
					"cdef_defcell cdc,cdef_defarea cda, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.status='10' and ood.SOURCE_TYPE=wdf.value " +
					" and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.enterprise_no=cdc.enterprise_no and ood.enterprise_no=cda.enterprise_no " +
					" and ood.warehouse_no=cdc.warehouse_no and ood.s_cell_no=cdc.cell_no " +
					" and cdc.warehouse_no=cda.warehouse_no and cdc.area_no=cda.area_no" +
					" and cdc.ware_no=cda.ware_no "+
					" and wdf.colname='SOURCE_TYPE' " +
					" and wdf.table_name='ODATA_OUTSTOCK_M' %s1  %s2 %s3  " +
					" order by ood.SOURCE_TYPE " ;
		}else if(strFlag != null && strFlag.equals("8"))
		{
			strSql ="select * from (select distinct ood.BATCH_No value,ood.BATCH_No text,ood.BATCH_No dropValue " +
					"from odata_outstock_direct ood, odata_locate_batch olb " +
					"where 1=1 " +
					" and olb.enterprise_no = ood.enterprise_no" +
					" and olb.warehouse_no = ood.warehouse_no" +
					//有混合业主的情况 不能关联委托业主 huangb 20160803
					//" and olb.owner_no = ood.owner_no" +
					" and olb.wave_no = ood.wave_no" +
					" and olb.batch_no = ood.batch_no" +
					" and olb.industry_flag = '" + strIndustryFlag + "'" +
					" and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.status='10' %s1  %s2 %s3 "+ 
					" order by ood.batch_no) "+
					" union all select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from dual " ;
		}else if(strFlag != null && strFlag.equals("9")){
			strSql ="select distinct ood.BATCH_No value,ood.BATCH_No text,ood.BATCH_No dropValue " +
					"from odata_outstock_direct ood, odata_locate_batch olb " +
					"where ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.status='10' %s1  %s2 %s3 "+ 
					" order by ood.batch_no ";
	
		}
		if(str != null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
		}
		if(strWarehouseNo != null && !strWarehouseNo.equals(""))
		{
			strSql = strSql.replace("%s2", " and ood.warehouse_No = '" + strWarehouseNo + "' ");
		}else
		{
			strSql = strSql.replace("%s2", "");
		}
		if(strWorkerOwner!=null && !strWorkerOwner.equals(""))
		{
			strSql = strSql.replace("%s3", " and ood.owner_no in("+strWorkerOwner+") ");
		}else{
			strSql = strSql.replace("%s3", " and 1=2 ");
		}
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}//cmb
	
	/**
	 * 拣货按客户发单发单》发单
	 * warehouseNo：仓别代码
	 * owner：委托业主
	 * workerNo:系统操作人员
	 * pickWorker：拣货人员
	 * str:条件(目前只有客户编码)
	 */
	public MsgRes send(
			String enterpriseNo,
			String warehouseNo,
			String workerNo,
			String dockNo,
			String strOutStockType,
			String strPrintPaperType,
			String pickWorker,
			String str,String ownerNo,String expType,String waveNo,
			String areaNo,String operateType,String batchNo) throws Exception 
		{//sendBody
			String sql="";
			sql="select distinct ood.cust_no " +
					//"ood.wave_no,cdc.ware_no||cdc.area_no area_no,ood.operate_type,ood.exp_type,ood.owner_no,ood.warehouse_no " +
					"from odata_outstock_direct ood,cdef_defcell cdc " +
					"where ood.warehouse_no=cdc.warehouse_no and ood.s_cell_no=cdc.cell_no " +
					"and ood.enterprise_no=cdc.enterprise_no " +
					"and ood.enterprise_no='"+enterpriseNo+"' " +
					" and ood.warehouse_No='"+warehouseNo+"' ";
			if(str!=null && !str.equals(""))//客户编码
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql+ws;
			}
			
			List<Odata_OutstockDirectModel> ood=genDao.getListByNativeSql(sql, Odata_OutstockDirectModel.class, 0, 10000);
	
			List  outList=new ArrayList();
			List  resultList=new ArrayList();
			
			outList.add("S");
			for (Odata_OutstockDirectModel i : ood) {
				if(strOutStockType.equals("0")){//按客户
					List  inList=new ArrayList();
					inList.add(enterpriseNo);//strEnterpriseNo
					inList.add(warehouseNo);//v_warehouse_No
					inList.add(ownerNo);//v_Owner_No
					inList.add(expType);//v_Exp_Type
					inList.add(waveNo);//v_WAVE_No
					inList.add(batchNo);//v_Batch_No
					inList.add(areaNo);//v_Area_No
					inList.add(i.getCustNo());//v_Cust_No
					inList.add("N");//v_deliverObj
					inList.add(dockNo);//码头//v_Dock_No
					inList.add(pickWorker);//拣货人员//v_PickWorker
					inList.add(operateType);//v_Operate_Type
					inList.add(workerNo);//系统操作人员
					inList.add(strPrintPaperType);//v_strPrintPaperType
					
					inList.add("0");//strPrintWayBill
					inList.add("0");//strPrintPackList
					inList.add("0");//strPrintInVoice
					
					System.out.println(strPrintPaperType);
					resultList=genDao.execProc(inList, outList, "pklg_odata.p_pick_by_ec");
					if(resultList.get(0).toString().indexOf("N|")!=-1){
						throw new Exception(resultList.get(0).toString());
					}
					System.out.println(resultList.get(0).toString());
				}else if(strOutStockType.equals("1")){//补货
					List  inList=new ArrayList();
					inList.add(enterpriseNo);//strEnterpriseNo
					inList.add(warehouseNo);//v_warehouse_No
					inList.add(ownerNo);//v_Owner_No
					inList.add(expType);//v_Exp_Type
					inList.add(waveNo);//v_WAVE_No
					inList.add(batchNo);//v_Batch_No
					inList.add(areaNo);//v_Area_No
					inList.add(dockNo);//v_Dock_No
					inList.add(operateType);//v_Operate_Type
					inList.add(pickWorker);//v_strUserID
					inList.add(strPrintPaperType);//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印
					System.out.println(strPrintPaperType);
					resultList=genDao.execProc(inList, outList, "pklg_odata.p_Hm_by_Area");
					if(resultList.get(0).toString().indexOf("N|")!=-1){
						throw new Exception(resultList.get(0).toString());
					}
					System.out.println(resultList.get(0).toString());
				}
				
			}
			return new MsgRes(true,"发单成功！",null);
	}//sendBody
	
	/*
	 * @func 获得批属性字符串（公用方法）
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<String> getArticleAttrString(String strFlag) throws Exception 
	{
		String strSql  =  "";
		if(strFlag.equals("1"))//strFlag = 1:查询字段批属性字段
		{
			 strSql  =  "select to_char(wmsys.wm_concat(a.field_id)) "  + 
					" from (select case when group_flag  =  1  then  "  + 
					" 'sai.'||a.field_id else 'min(' ||  'sai.'||a.field_id || ')' || ' as '|| a.field_id "  + 
					" end as field_id from wms_deffielddesc a "  + 
					" where a.use_type  = '3' and a.desc_flag = '1') a where 1 = 1  ";
		}else if(strFlag.equals("2"))//strFlag = 2:group by字段批属性字段
		{
			 strSql  =  " select to_char(wmsys.wm_concat('sai.' || a.field_id)) from wms_deffielddesc a "  + 
					" where a.use_type  = '3' and a.desc_flag = '1' and a.group_flag = '1' ";
		}
		List strFindAtt1  =  genDao.getListByNativeSql(strSql);
		System.out.println(strFindAtt1.get(0));
		if(strFindAtt1.get(0)==null)
		{
			strFindAtt1.set(0, "");
		}
		return (List<String>) strFindAtt1;
	}
	
	/**
	 * 拣货批量发单过程
	 */
	@Override
	public MsgRes sendTscAuto(
			String strDetail,
			String enterpriseNo,
			String strDockNo,
			String strWorkerNo) throws Exception {
		List<Odata_OutstockDirectModel> list=JSON.parseArray(strDetail,Odata_OutstockDirectModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		for(int i=0;i<list.size();i++){
			List inList=new ArrayList();
			if(list.get(i).getOutstockType().equals("0")){//出货拣货
//				inList.add(enterpriseNo);//strEnterpriseNo
//				inList.add(list.get(0).getWarehouseNo());//v_warehouse_No
//				inList.add(list.get(i).getOwnerNo());//v_Owner_No
//				inList.add(list.get(i).getExpType());//v_Exp_Type
//				inList.add(list.get(i).getSourceType());//v_Source_Type
//				inList.add(list.get(i).getWaveNo());//v_WAVE_No
//				inList.add(list.get(i).getWareaNo());//v_Area_No
//				inList.add(strDockNo);//v_Dock_No
//				inList.add(list.get(i).getOperateType());//v_Operate_Type
//				inList.add(strWorkerNo);//v_strUserID
//				inList.add(list.get(0).getPrintPaperType());//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印    
//				resultList=genDao.execProc(inList, outList, "pklg_odata.p_pick_by_Area");
//				if(resultList.get(0).toString().indexOf("N|")!=-1){
//					throw new Exception(resultList.get(0).toString());
//				}
				
				//电商调用
				inList.add(enterpriseNo);//strEnterpriseNo
				inList.add(list.get(0).getWarehouseNo());//v_warehouse_No
				inList.add(list.get(i).getOwnerNo());//v_Owner_No
				inList.add(list.get(i).getExpType());//v_Exp_Type
				inList.add(list.get(i).getWaveNo());//v_WAVE_No
				inList.add(list.get(i).getBatchNo());//v_BATCH_No
				inList.add(list.get(i).getWareaNo());//v_Area_No
				inList.add("N");//v_Cust_No
				inList.add("N");//v_deliverObj
				inList.add(strDockNo);//v_Dock_No
				inList.add(strWorkerNo);//v_PickWorker
				inList.add(list.get(i).getOperateType());//v_Operate_Type
				inList.add(strWorkerNo);//v_strUserID
				inList.add(list.get(0).getPrintPaperType());//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印    
				inList.add(list.get(0).getPrintFaceSheet());//strPrintWayBill  是否打印面单标识，0：不打印，1，打印    
				inList.add(list.get(0).getPrintBuilt());//strPrintPackList  是否打印内置清单标识，0：不打印，1，打印    
				inList.add(list.get(0).getPrintInvoice());//strPrintInVoice  是否打印发票标识，0：不打印，1，打印    
				System.out.println(inList);
				resultList=genDao.execProc(inList, outList, "pklg_odata.p_pick_by_ec");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
				System.out.println(resultList.get(0).toString());
			}else if(list.get(i).getOutstockType().equals("1")){//出货补货
				inList.add(enterpriseNo);//strEnterpriseNo
				inList.add(list.get(0).getWarehouseNo());//v_warehouse_No
				inList.add(list.get(i).getOwnerNo());//v_Owner_No
				inList.add(list.get(i).getExpType());//v_Exp_Type
				inList.add(list.get(i).getWaveNo());//v_WAVE_No
				inList.add(list.get(i).getBatchNo());//v_BATCH_No
				inList.add(list.get(i).getWareaNo());//v_Area_No
				inList.add(strDockNo);//v_Dock_No
				inList.add(list.get(i).getOperateType());//v_Operate_Type
				inList.add(strWorkerNo);//v_strUserID
				inList.add(list.get(0).getPrintPaperType());//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印
				System.out.println(inList);
				resultList=genDao.execProc(inList, outList, "pklg_odata.p_Hm_by_Area");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
				System.out.println(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"发单成功","");
	}
	//获取批次发单信息
	public List<Odata_OutstockDirectModel> getBatchSendOrder(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String str){
		String ws=CommUtil.covtCollectionToWhereSql(str);
		String sql = "select distinct ood.wave_no,ood.enterprise_no,ood.warehouse_no,ood.owner_no,ood.Exp_Type,ood.batch_no,ood.outstock_type from odata_outstock_direct ood where ood.status='10' "+
				" and ood.warehouse_No = '" + strWarehouseNo + "' "+
				"and ood.enterprise_no='"+enterpriseNo+"' " +
				" and ood.owner_no in("+strOwnerNo+") ";
				;
		sql = sql+ws;
		sql = sql+" order by ood.wave_no,ood.batch_no";
		List<Odata_OutstockDirectModel> list=genDao.getListByNativeSql(sql,Odata_OutstockDirectModel.class);
		return list;
	}
	
	/**
	 * 自动发单过程
	 */
	@Override
	public MsgRes sendOrderAuto(
			String strDetail,
			String enterpriseNo,
			String strDockNo,
			String strWorkerNo) throws Exception {
		List<Odata_OutstockDirectModel> list=JSON.parseArray(strDetail,Odata_OutstockDirectModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		for(int i=0;i<list.size();i++){
			List inList=new ArrayList();
			if(list.get(i).getOutstockType().equals("0")){			
				//电商调用
				inList.add(enterpriseNo);//strEnterpriseNo
				inList.add(list.get(0).getWarehouseNo());//v_warehouse_No
				inList.add(list.get(i).getOwnerNo());//v_Owner_No
				inList.add(list.get(i).getExpType());//v_Exp_Type
				inList.add(list.get(i).getWaveNo());//v_WAVE_No
				inList.add(list.get(i).getBatchNo());//v_Batch_No
				inList.add("ALL");//v_Area_No
				inList.add("N");//v_Cust_No
				inList.add("N");//v_deliverObj
				inList.add(strDockNo);//v_Dock_No
				inList.add(strWorkerNo);//v_PickWorker
				inList.add("ALL");//v_Operate_Type
				inList.add(strWorkerNo);//v_strUserID
				inList.add(list.get(0).getPrintPaperType());//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印    
				inList.add(list.get(0).getPrintFaceSheet());//strPrintWayBill  是否打印面单标识，0：不打印，1，打印    
				inList.add(list.get(0).getPrintBuilt());//strPrintPackList  是否打印内置清单标识，0：不打印，1，打印    
				inList.add(list.get(0).getPrintInvoice());//strPrintInVoice  是否打印发票标识，0：不打印，1，打印    
				System.out.println(inList);
				resultList=genDao.execProc(inList, outList, "pklg_odata.p_pick_by_ec");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
				System.out.println(resultList.get(0).toString());
			}else if(list.get(i).getOutstockType().equals("1")){//出货补货
				inList.add(enterpriseNo);//strEnterpriseNo
				inList.add(list.get(0).getWarehouseNo());//v_warehouse_No
				inList.add(list.get(i).getOwnerNo());//v_Owner_No
				inList.add(list.get(i).getExpType());//v_Exp_Type
				inList.add(list.get(i).getWaveNo());//v_WAVE_No
				inList.add(list.get(i).getBatchNo());//v_Batch_No
				inList.add("ALL");//v_Area_No
				inList.add(strDockNo);//v_Dock_No
				inList.add("ALL");//v_Operate_Type
				inList.add(strWorkerNo);//v_strUserID
				inList.add(list.get(0).getPrintPaperType());//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印
				resultList=genDao.execProc(inList, outList, "pklg_odata.p_Hm_by_Area");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
				System.out.println(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"发单成功","");
	}
	
	//按配对象发单
	@Override
	public MsgRes sendObj(String enterpriseNo, String warehouseNo,
			String owner, String workerNo, String dockNo,
			String strOutStockType, String strPrintPaperType,
			String pickWorker, String str) throws Exception {
		//sendBody
		String sql="";
		sql="select distinct ood.warehouse_no,ood.owner_no,ood.exp_type,ood.deliver_obj," +
				"ood.wave_no,ood.batch_no,ood.cust_no,cdc.ware_no||cdc.area_no area_no,ood.operate_type " +
				"from odata_outstock_direct ood,cdef_defcell cdc " +
				"where ood.warehouse_no=cdc.warehouse_no and ood.s_cell_no=cdc.cell_no " +
				"and ood.enterprise_no=cdc.enterprise_no " +
				"and ood.enterprise_no='"+enterpriseNo+"' ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			sql=sql+" and ood.warehouse_No='"+warehouseNo+"' ";
		}
		if(owner!=null && !owner.equals(""))
		{
			sql=sql+" and ood.owner_no in("+owner+") ";
		}else{
			sql=sql+" and 1=2";
		}
		List<Odata_OutstockDirectModel> ood=genDao.getListByNativeSql(sql, Odata_OutstockDirectModel.class, 0, 10000);

		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		for (Odata_OutstockDirectModel i : ood) {
			if(strOutStockType.equals("0")){//按配送对象
				List  inList=new ArrayList();
				inList.add(enterpriseNo);//strEnterpriseNo
				inList.add(i.getWarehouseNo());//v_warehouse_No
				inList.add(i.getOwnerNo());//v_Owner_No
				inList.add(i.getExpType());//v_Exp_Type
				inList.add(i.getWaveNo());//v_WAVE_No
				inList.add(i.getBatchNo());//v_Batch_No
				inList.add("ALL");//v_Area_No
				inList.add("N");//v_Cust_No
				inList.add(i.getDeliverObj());//v_DeliverOBJ
				inList.add(dockNo);//码头//v_Dock_No
				inList.add(pickWorker);//拣货人员//v_PickWorker
				inList.add("ALL");//v_Operate_Type
				
				inList.add(workerNo);//系统操作人员
				inList.add(strPrintPaperType);//v_strPrintPaperType
				
				inList.add("0");//strPrintWayBill
				inList.add("0");//strPrintPackList
				inList.add("0");//strPrintInVoice
				
				System.out.println(strPrintPaperType);
				resultList=genDao.execProc(inList, outList, "pklg_odata.p_pick_by_ec");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
				System.out.println(resultList.get(0).toString());
			}else if(strOutStockType.equals("1")){//补货
				List  inList=new ArrayList();
				inList.add(enterpriseNo);//strEnterpriseNo
				inList.add(i.getWarehouseNo());//v_warehouse_No
				inList.add(i.getOwnerNo());//v_Owner_No
				inList.add(i.getExpType());//v_Exp_Type
				inList.add(i.getWaveNo());//v_WAVE_No
				inList.add(i.getBatchNo());//v_Batch_No
				inList.add(i.getAreaNo());//v_Area_No
				inList.add(dockNo);//v_Dock_No
				inList.add(i.getOperateType());//v_Operate_Type
				inList.add(pickWorker);//v_strUserID
				inList.add(strPrintPaperType);//v_strPrintPaperType  是否打印表单标识，0：不打印，1，打印
				System.out.println(strPrintPaperType);
				resultList=genDao.execProc(inList, outList, "pklg_odata.p_Hm_by_Area");
				if(resultList.get(0).toString().indexOf("Y")==-1){
					throw new Exception(resultList.get(0).toString());
				}
				System.out.println(resultList.get(0).toString());
			}
			
		}
		return new MsgRes(true,"发单成功！",null);

	}
}
