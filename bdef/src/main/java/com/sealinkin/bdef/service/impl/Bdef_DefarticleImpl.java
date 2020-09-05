/**
 * 商品资料
 * @author JUN
 * 修改 By Panzx
 */
package com.sealinkin.bdef.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_ArticleBarcodeModel;
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.model.Bdef_WarehousePackingModel;
import com.sealinkin.bdef.po.Bdef_ArticleBarcode;
import com.sealinkin.bdef.po.Bdef_ArticlePacking;
import com.sealinkin.bdef.po.Bdef_Defarticle;
import com.sealinkin.bdef.po.Bdef_WarehousePacking;
import com.sealinkin.bdef.service.IBdef_DefarticleService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.bdef.AnsQpaletteInPutBarcodeModel;
import com.sealinkin.protocolExchange.bdef.ReqUpdateQpaletteModel;
import com.sealinkin.protocolExchange.bdef.bdefArticlePackingInfoModel;

import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.fcdata.AnsPackingModel;
import com.sealinkin.protocolExchange.fcdata.ArticleInfoModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefarticleImpl implements IBdef_DefarticleService{
	
	private IGenericManager genDao;
	
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	
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
	
	/**
	 * 获取商品资料列
	 */
	@Override
	public ExtListDataBo<Bdef_DefarticleModel> query_BdefDefarticleList(
			String enterpriseNo,String strOwnerNo, String strQuery, PageBo poPageBo,
			Integer intRequestFlag) throws Exception 
	{
		String strSql="select a.*,b.group_name,c.owner_name, " +
				"wdf.text statusText, "+
				"a.qmin_operate_packing_unit qminUnit,a.unit_packing basePacking "+
				"from bdef_defarticle a,bdef_article_group b,bdef_defowner c,wms_deffieldval wdf " +
				" where a.group_no=b.group_no " +
				" and a.enterprise_no=b.enterprise_no " +
				" and a.owner_no=b.owner_no " +
				" and b.group_level='0' " +
				" and a.enterprise_no=c.enterprise_no "+
				" and a.owner_no=c.owner_no "+
				
				" and table_name = upper('BDEF_DEFARTICLE') " +
				" and wdf.colname = upper('STATUS') " +
				" and wdf.value = a.status "+
				" and wdf.status = '1' "+
				
				" and a.enterprise_no='"+enterpriseNo+"' ";
		String strTotsql="select count(1) from bdef_defarticle a where 1=1 " +
				" and a.enterprise_no='"+enterpriseNo+"' ";
		
		
		 if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
			strTotsql=strTotsql+strWs;
		}
		 if(strOwnerNo!=null && !strOwnerNo.equals(""))
			{
				strSql=strSql+" and a.owner_no in("+strOwnerNo+") ";
				strTotsql=strTotsql+" and a.owner_no in("+strOwnerNo+")";
			}
		/*else
		{
			strSql=strSql+" and 1=2";
			strTotsql=strTotsql+" and 1=2";
		}*/
		
		List<Bdef_DefarticleModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bdef_DefarticleModel> extListBo=null;
		strSql = strSql+" order by a.enterprise_no, a.owner_no, a.article_no "; 
		strTotsql = "select count(*) from ("+strSql+")a";
		if(intRequestFlag==1)
		{
			list = genDao.getListByNativeSql(strSql,Bdef_DefarticleModel.class,poPageBo.getStart(),poPageBo.getPagesize());
			intCount = genDao.getCountByNativeSql(strTotsql);
		}
		else
		{
			list = genDao.getListByNativeSql(strSql,Bdef_DefarticleModel.class);
			intCount = list.size();
		}
		
		extListBo= new ExtListDataBo<Bdef_DefarticleModel>(list, intCount);
        return extListBo;
	}
	
	/**
	 * 保存商品主档
	 */
	@Override
	public MsgRes saveArticle(String strArticle, String strSaveType, String strWorkerNo) throws Exception {
		Bdef_Defarticle poBd = (Bdef_Defarticle)JSONObject.toBean(
				JSONObject.fromObject(strArticle),Bdef_Defarticle.class);
		if(strSaveType.equals("0")){
			if(poBd.getArticleNo().equals("保存时自动生成")){
				List inList=new ArrayList();
				List outList=new ArrayList();
				List resultList=new ArrayList();
				outList.add("S");
				outList.add("S");
				inList.add(poBd.getEnterpriseNo());
				inList.add(poBd.getOwnerNo());
				inList.add("SKU");
				System.out.println(inList);
				resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getBaseNo");
				if(resultList.get(1).toString().indexOf("Y")==-1)
				{
					throw new Exception(resultList.get(1).toString());
				}
				
			    String strArticleNo =resultList.get(0).toString();
			    poBd.setArticleNo(strArticleNo);

				if(poBd.getOwnerArticleNo().equals("可自动生成")){
					 poBd.setOwnerArticleNo(strArticleNo);
				}
				
				if(poBd.getBarcode().equals("")){
					poBd.setBarcode(strArticleNo);
				}
			}

			this.genDao.saveOrUpdateObj(poBd);
			return new MsgRes(true,poBd.getArticleNo(),"");
		}else{
			String strSql="insert into bdef_defarticle_log(enterprise_no,owner_no, owner_article_no, article_no, article_name, " +
					"article_ename, article_oname, article_alias, group_no, article_identifier, unit, " +
					"qmin_operate_packing, expiry_days, alarmrate, freezerate, abc, unit_volumn, " +
					"unit_weight, cumulative_volumn, a_out, rule_flag, spec, sell_price, status, " +
					"create_flag, virtual_flag, turn_over_rule, check_excess, um_check_excess, " +
					"pick_excess, divide_excess, temperature_flag, measure_mode, scan_flag, check_qty_flag, " +
					"check_qty_rate, check_weight_flag, check_weight_rate, qc_flag, qc_rate, mix_flag, " +
					"double_check, code_type, divide_box, deliver_type, dept_no, i_strategy, o_strategy, " +
					"m_strategy, ri_strategy, ro_strategy, fc_strategy, rsv_strategy1, rsv_strategy2, " +
					"rsv_strategy3, rsv_strategy4, rsv_strategy5, rsv_strategy6, rsv_attr1, rsv_attr2, " +
					"rsv_attr3, rsv_attr4, rsv_attr5, rsv_attr6, rsv_attr7, rsv_attr8, rsv_attr9, " +
					"rsv_attr10, rsv_attr11, rsv_attr12, rsv_attr13, rsv_attr14, rsv_attr15, rsv_attr16, " +
					"rsv_attr17, rsv_attr18, rsv_attr19, rsv_attr20, rgst_name, rgst_date, updt_name, updt_date, print_flag, lot_type," +
					"supplier_no,barcode,operate_type ,row_id ,batch_id,rsv_control1,rsv_control2,rsv_control3,rsv_control4,rsv_control5,qmin_operate_packing_unit ,unit_packing ) " +
					"select enterprise_No, owner_no, owner_article_no, article_no, article_name, article_ename, article_oname, " +
					"article_alias, group_no, article_identifier, unit, qmin_operate_packing, expiry_days, " +
					"alarmrate, freezerate, abc, unit_volumn, unit_weight, cumulative_volumn, a_out, " +
					"rule_flag, spec, sell_price, status, create_flag, virtual_flag, turn_over_rule, " +
					"check_excess, um_check_excess, pick_excess, divide_excess, temperature_flag, measure_mode, " +
					"scan_flag, check_qty_flag, check_qty_rate, check_weight_flag, check_weight_rate, " +
					"qc_flag, qc_rate, mix_flag, double_check, code_type, divide_box, deliver_type, " +
					"dept_no, i_strategy, o_strategy, m_strategy, ri_strategy, ro_strategy, fc_strategy, " +
					"rsv_strategy1, rsv_strategy2, rsv_strategy3, rsv_strategy4, rsv_strategy5, rsv_strategy6, " +
					"rsv_attr1, rsv_attr2, rsv_attr3, rsv_attr4, rsv_attr5, rsv_attr6, rsv_attr7, rsv_attr8, " +
					"rsv_attr9, rsv_attr10, rsv_attr11, rsv_attr12, rsv_attr13, rsv_attr14, rsv_attr15, " +
					"rsv_attr16, rsv_attr17, rsv_attr18, rsv_attr19, rsv_attr20, rgst_name, rgst_date," +
					"'"+strWorkerNo+"', sysdate,  print_flag, lot_type, " +
					"supplier_no,barcode,operate_type ,row_id ,batch_id,rsv_control1,rsv_control2,rsv_control3,rsv_control4,rsv_control5,   " +
					" qmin_operate_packing_unit ,unit_packing "+
					"from bdef_defarticle " +
					"where article_no='"+poBd.getArticleNo()+
					"' and enterprise_no='"+poBd.getEnterpriseNo()+"'";
			genDao.updateBySql(strSql);
			this.genDao.saveOrUpdateObj(poBd);
			return new MsgRes(true,poBd.getArticleNo(),"");
		}
	}
	
	/**
	 * 获取商品包装列
	 */
	@Override
	public ExtListDataBo<Bdef_ArticlePackingModel> query_BdefArticlePackingList(String enterpriseNo,
			String strArticleNo, PageBo poPageBo) throws Exception 
	{
		String strSql = "select a.article_no, a.packing_qty, a.packing_unit, a.spec, a.mpacking_qty, a.mpacking_unit, " +
				"a.a_length, a.a_width, a.a_height, a.packing_weight, a.pal_base_qbox, a.pal_height_qbox, a.sorter_flag, " +
				"a.rule_flag, a.qpalette, a.create_flag,b.article_name,a.RSV_PKING1,a.RSV_PKING2,a.RSV_PKING3,a.RSV_PKING4,a.RSV_PKING5," +
				"f_get_fieldtext('BDEF_ARTICLE_PACKING','SORTER_FLAG',a.sorter_flag)sorterflagText," +
				"f_get_fieldtext('N','RULE_FLAG',a.rule_flag)ruleflagText " +
				"from bdef_article_packing a,bdef_defarticle b " +
				"where a.article_no=b.article_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.article_no='"+strArticleNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		String strTotsql = "select count(1) from bdef_article_packing where article_no='"+strArticleNo+"' and enterprise_no='"+enterpriseNo+"'";
		strSql = strSql + "order by a.packing_qty";
		List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(
				strSql, Bdef_ArticlePackingModel.class, 0, 100);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bdef_ArticlePackingModel> extListBo = new ExtListDataBo<Bdef_ArticlePackingModel>(list,intCount);
		return extListBo;
	}
	
	/**
	 * 保存商品包装
	 */
	@Override
	public MsgRes saveOrUpdateArticlePacking(String strPacking, String strWorkerNo)
			throws Exception {
		Bdef_ArticlePacking poBa=(Bdef_ArticlePacking)JSONObject.toBean(JSONObject.fromObject(strPacking),Bdef_ArticlePacking.class);
		String strSql="insert into BDEF_ARTICLE_PACKING_LOG(ENTERPRISE_NO,ARTICLE_NO, PACKING_QTY, PACKING_UNIT, SPEC, MPACKING_QTY, " +
				"MPACKING_UNIT, A_LENGTH, A_WIDTH, A_HEIGHT, PACKING_WEIGHT, PAL_BASE_QBOX, PAL_HEIGHT_QBOX, " +
				"SORTER_FLAG, RULE_FLAG, QPALETTE, CREATE_FLAG, RGST_NAME, RGST_DATE, UPDT_NAME, UPDT_DATE,row_id) " +
				"select ENTERPRISE_NO, ARTICLE_NO, PACKING_QTY, PACKING_UNIT, SPEC, MPACKING_QTY, MPACKING_UNIT, A_LENGTH, " +
				"A_WIDTH, A_HEIGHT, PACKING_WEIGHT, PAL_BASE_QBOX, PAL_HEIGHT_QBOX, SORTER_FLAG, RULE_FLAG, " +
				"QPALETTE, CREATE_FLAG, RGST_NAME, RGST_DATE, '"+strWorkerNo+"', sysdate,row_id " +
				"from bdef_article_packing " +
				"where article_no='"+poBa.getId().getArticleNo()+
				"' and packing_qty='"+poBa.getId().getPackingQty()+
				"' and enterprise_no='"+poBa.getId().getEnterpriseNo()+"' ";
		genDao.updateBySql(strSql);
		this.genDao.saveOrUpdateObj(poBa);
		return new MsgRes(true,"保存成功","");
	}
	
	/**
	 * 获取商品条码列
	 */
	@Override
	public ExtListDataBo<Bdef_ArticleBarcodeModel> query_BdefArticleBarcodeList(
			String enterpriseNo,String strWheresql,String strOwnerNo, PageBo poPageBo) throws Exception {
		String strSql="select a.owner_no,b.owner_article_no,a.article_no,b.article_name," +
				"a.barcode,a.packing_qty " +
				//"f_get_fieldtext('N','PRIMARY_FLAG',a.primary_flag)primaryflagText " +
				"from bdef_article_barcode a,bdef_defarticle b " +
				"where a.article_no=b.article_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.article_no='"+strWheresql+"' " +
				"and a.owner_no='"+strOwnerNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		
		String strTotsql="select count(1) " +
				"from bdef_article_barcode a,bdef_defarticle b " +
				"where a.article_no=b.article_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.article_no='"+strWheresql+"' " +
				"and a.owner_no='"+strOwnerNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		
		//strSql = strSql+"order by primary_flag desc";
		List<Bdef_ArticleBarcodeModel> list = genDao.getListByNativeSql(
				strSql,Bdef_ArticleBarcodeModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bdef_ArticleBarcodeModel> extListBo= new ExtListDataBo<Bdef_ArticleBarcodeModel>(list, intCount);
		return extListBo;
	}
	
	/**
	 * 保存商品条码
	 */
	@Override
	public MsgRes saveOrUpdateArticleBarcode(String strBarcode)
			throws Exception {
		Bdef_ArticleBarcode poBa=(Bdef_ArticleBarcode)JSONObject.toBean(JSONObject.fromObject(strBarcode),Bdef_ArticleBarcode.class);
	
		String strSql="select a.owner_no from bdef_defarticle a" +
				" where article_no='"+poBa.getId().getArticleNo()+
				"' and enterprise_no='"+poBa.getId().getEnterpriseNo()+"' ";
		List<String> list=genDao.getListByNativeSql(strSql);
		poBa.getId().setOwnerNo(list.get(0));
		this.genDao.saveOrUpdateObj(poBa);
		return new MsgRes(true,"保存成功","");
	}
	
	/**
	 * 获取商品下拉
	 */
	@Override
	public List<ComboxBo> query_BdefArticleCombo(String enterpriseNo,String strOwnerNo,String strQuery,String strWheresql) {
		String strSql="select t1.article_no value,t1.article_name text," +
				"'['|| t1.owner_no || '][' || ltrim(t1.owner_article_no)||']'||t1.article_name dropValue " +
				"from bdef_defarticle t1 " +
				"where t1.enterprise_no='"+enterpriseNo+"' ";
		//商品编码、条码、名称、货主商品编码
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and (t1.article_no like '%"+strWheresql+"%' " +
					"or t1.article_name like '%"+strWheresql+"%' " +
							"or t1.barcode like '%"+strWheresql+"%' " +
							"or t1.owner_article_no like '%"+strWheresql+"%' " +
							"or t1.article_identifier like '%"+strWheresql+"%' )";
		}
		//货主权限
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		//货主过滤等条件
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strW=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql+=strW;
		}
		strSql=strSql+" order by  t1.owner_no ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class,0,50);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 获取包装下拉
	 */
	@Override
	public List<ComboxBo> qyery_ArticlePackingCombo(String enterpriseNo,String strWheresql)
			throws Exception 
	{
		String strSql="select distinct a.Packing_Qty value,a.Packing_Qty text,Packing_Qty dropValue " +
				"from bdef_article_packing a " +
				"where a.article_no='"+strWheresql+
				"' and a.enterprise_no='"+enterpriseNo+
				"' order by a.Packing_Qty desc";
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		if(list.size()==0){
			 strSql="select 1 value ,1 text, 1 dropValue from dual";
			 list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		}
		return  (List<ComboxBo>)list;
	}
	
	/**
	 * 验证商品编码
	 */
	@Override
	public MsgRes check_ArticleNo(String enterpriseNo,String strArticleNo) throws Exception 
	{
		String strSql="select a.article_no from bdef_defarticle a where " +
				"a.article_no='"+strArticleNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			return new MsgRes(true,"","");
		}else
		{
			return new MsgRes(false,"该商品编码己有,请重新录入","");
		}	
	}
	
	//判断生产的编码是否唯一
	public int checkOnly(String enterpeiseNo,String ownerNo,String strArticleNo){

		String strSql="select a.article_no from bdef_defarticle a " +
				" where a.article_no='"+strArticleNo+
				" 'and a.enterprise_no='"+enterpeiseNo+"' " +
				"  and a.owner_no='"+ownerNo+"'";
		
		List<String> list=genDao.getListByNativeSql(strSql);
		
		return list.size();
	}
	
	/**
	 * 下拉获取商品名称
	 */
	@Override
	public List<String> get_ArticleName(String enterpriseNo,String strWheresql) throws Exception 
	{
		String strSql="select a.article_name from bdef_defarticle a " +
				"where a.article_no='"+strWheresql+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<String> list=genDao.getListByNativeSql(strSql);
		return list;
	}
	
	/**
	 * 根据商品编码和包装获取规格和单位
	 */
	@Override
	public List<Bdef_ArticlePackingModel> queryPackingUnitAndSpec(
			String enterpriseNo,String strArticleNo, String strPackingQty, String strType)
			throws Exception {
		String strSql="select a.packing_unit,a.spec from bdef_article_packing a where a.article_no='"+strArticleNo+"'" +
				" and a.packing_qty="+strPackingQty+
				" and a.enterprise_no='"+enterpriseNo+"' ";
		List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(strSql,Bdef_ArticlePackingModel.class,0,100);
		if(list.size()==0){
			 strSql="select a.unit packingUnit,a.spec from bdef_defarticle a where a.article_no='"+strArticleNo+"'" +
				" and a.enterprise_no='"+enterpriseNo+"' ";
			 list=genDao.getListByNativeSql(strSql,Bdef_ArticlePackingModel.class, 0, 1000);
		}
		return (List<Bdef_ArticlePackingModel>)list;
	}
	/**
	 * 根据商品编码获取包装数量、规格和单位
	 */
	@Override
	public List<Bdef_ArticlePackingModel> queryPackingQtyAndUnitAndSpec(
			String enterpriseNo, String strArticleNo, String str)
			throws Exception {
		String strSql="select b.packing_qty, b.packing_unit,b.spec "+
                      "from bdef_defarticle a , bdef_article_packing b "+
                      "where a.article_no=b.article_no "+
                        "and a.enterprise_no=b.enterprise_no "+
                        "and a.article_no='"+strArticleNo+"' " +
				        "and a.enterprise_no='"+enterpriseNo+"' ";
		List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(strSql,Bdef_ArticlePackingModel.class,0,100);
		if(list.size()==0){
			 strSql="select ''packing_qty,''packing_unit,''spec from dual";
			 list=genDao.getListByNativeSql(strSql,Bdef_ArticlePackingModel.class, 0, 1000);
		}
		return (List<Bdef_ArticlePackingModel>)list;
	}
	/**
	 * 
	 */
	@Override
	public MsgRes checkSaveBarcode(String enterpriseNo,String strArticleNo, String strPackingQty,
			String strBarcode) throws Exception {
		String strSql="select barcode from bdef_article_barcode" +
				" where article_no='"+strArticleNo+
				"' and primary_flag='1' " +
				" and enterprise_no='"+enterpriseNo+"' ";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			return new MsgRes(true,"","");
		}else{
			return new MsgRes(false,"已有主条码信息不允许保存","");
		}
		
	}

	
	/**
	 * 获取最大包装数
	 */
	@Override
	public String getMaxArticlePacking(String enterpriseNo,String strWheresql) {

		String strSql="select a.Packing_Qty " +
				"from bdef_article_packing a " +
				"where a.article_no='"+strWheresql+
				"' and a.enterprise_no='"+enterpriseNo+
				"' order by a.Packing_Qty desc";
		
		List list=genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return (String) list.get(0).toString();
		}else{
			return "1";
		}
	
	}
	//货主编码校验
	@Override
	public MsgRes checkOwnerNo(String strEnterpriseNo, String strArticleNo)
				throws Exception {
		String sql="select distinct a.owner_no from bdef_defarticle a where a.article_no='"+strArticleNo+"' " +
				" and a.enterprise_no='"+strEnterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==1){
			return new MsgRes(true,list.get(0),"");
		}else{
			return new MsgRes(true,strArticleNo,"");
		}
	}
	@Override
	public MsgRes ArticleQpaletteBarcodeInput(String strRecvData)
			throws Exception {
		MsgRes msg=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		//根据条码找商品
		msg =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getReqObj(),reqMod.getEnterpriseNo());
		if(!msg.getIsSucc())
		{
			return msg;
		}
		String strArticleNo=msg.getObj().toString();
		
		String strSql="select a.* from bdef_defarticle a where a.article_no in("+strArticleNo+")";
		List<ArticleInfoModel> list=genDao.getListByNativeSql(strSql, ArticleInfoModel.class);
		AnsQpaletteInPutBarcodeModel ansMod=new AnsQpaletteInPutBarcodeModel();
		ansMod.setListArticlinfo(list);		
		if(list.size()==1)
		{
			//只有一个品项，则取包装信息
			ansMod.setArtcicleNum("2");
			List<AnsPackingModel> listArtPack=(List<AnsPackingModel>)GetArticlePacking(list.get(0).getArticleNo()).getObj();
			ansMod.setListPacking(listArtPack);
		}else
		{
			//只返回商品信息
			ansMod.setArtcicleNum("3");
		}
		msg.setIsSucc(true);
		msg.setObj(JSON.toJSONString(ansMod));
		return msg;
	}
	@Override
	public MsgRes GetArticlePacking(String strArticleNo) throws Exception {
		MsgRes msg=new MsgRes();
		String strSql="select " +
					"a.packing_unit as packunit," +
					"a.packing_qty as packQty," +
					"a.pal_base_qbox as palBaseBox," +
					"a.pal_height_qbox  as palHeightBox," +
					"a.a_length as packingLength ,a.a_width as packingWidth," +
					"a.a_height as packingHeight,a.packing_weight " +
				"from " +
					"bdef_article_packing a " +
				"where " +
					//"a.packing_qty<>1 and" +
					" a.ARTICLE_NO='"+strArticleNo+"'";
		List<AnsPackingModel> listArtPack=genDao.getListByNativeSql(strSql, AnsPackingModel.class);
		if(listArtPack.size()==0){
			msg.setIsSucc(false);
			msg.setMsg("该商品没有包装，请新增商品信息");
			return msg;
		}
		msg.setIsSucc(true);
		msg.setObj(listArtPack);
		return msg;
	}
	@Override
	public MsgRes updateArticleQpalette(String strRecvData) throws Exception {
		ReqUpdateQpaletteModel reqMod=JSON.parseObject(strRecvData, ReqUpdateQpaletteModel.class);	
		//取商品资料
		
		/*String sqlStr1="select a.unit  from " +
				"bdef_defarticle a " +
				"left join BDEF_DEFOWNER b"+
				" on a.owner_no=b.owner_no" +
				" LEFT JOIN BDEF_ARTICLE_BARCODE c "+
				" on a.barcode=c.barcode"+
				" where  b.OWNER_NO='"+reqMod.getOwnerNo()+"'"+
				" and a.article_no='"+reqMod.getArticleNo()+"'" +
				//" and a.article_identifier='"+reqMod.getArticle_identifier()+"') " +
				" and a.enterprise_no='" + reqMod.getEnterpriseNo() + "'" ;
		List listArtPack=genDao.getListByNativeSql(sqlStr1);*/
	
		String strSql="update " +
				" bdef_article_packing a " +
				" set a.pal_base_qbox="+reqMod.getPalBaseBox()+"," +
				" a.pal_height_qbox="+reqMod.getPalHeightBox()+"," +
				" a.qpalette="+reqMod.getQpalette()+"," +
				" a.a_length="+reqMod.getA_length()+"," +
				" a.a_width="+reqMod.getA_width()+"," +
				" a.a_height="+reqMod.getA_height()+"," +
				" a.packing_weight="+reqMod.getPackingWeight()+
				" where a.article_no='" +reqMod.getArticleNo()+"' "+
				" and a.packing_qty="+reqMod.getPackQty();
		genDao.updateBySql(strSql);
		String updatebarCodeSql="UPDATE bdef_defarticle SET barcode='"+reqMod.getBarcode()+"' " +
				"WHERE article_no='"+reqMod.getArticleNo()+"'";
		genDao.updateBySql(updatebarCodeSql);
		//保存商品条码
		//ArticleInfoModel repMod=JSON.parseObject(strRecvData, ArticleInfoModel.class);
		
		/*String strSql1="insert into BDEF_ARTICLE_BARCODE(BARCODE,OWNER_NO,ARTICLE_NO,PRIMARY_FLAG,PACKING_QTY,CREATE_FLAG,RGST_NAME,RGST_DATE,UPDT_NAME,UPDT_DATE,enterprise_no,ROW_ID) " +
				"values('"
				+reqMod.getBarcode()
				+ "','"
				+reqMod.getOwnerNo()
				+ "','"		
				+reqMod.getArticleNo()
				+ "','0',"	
				/*+repMod.getPrimaryFlag()
				+ "','"	*/
			  /*	+reqMod.getPackQty()
				+ ",'1','"	
				/*+repMod.getCreateFlag()*/
				//+ "','1','"	
			/*	+reqMod.getRgstName()
				+ "',sysdate,'','','"
				/*+repMod.getUpdtName()
				+ "','"	
				+repMod.getUpdtDate()
				+ "','"	*/
				/*+reqMod.getEnterpriseNo()+ "','')";
	/*	genDao.updateBySql(strSql1);*/
		//保存商品包装saveOrUpdateArticleBarcode
		//ArticleInfoModel repsMod=JSON.parseObject(strRecvData, ArticleInfoModel.class);
		/*String strSql2="insert into  bdef_article_packing (ARTICLE_NO,PACKING_QTY,PACKING_UNIT,SPEC," +
				"MPACKING_QTY,MPACKING_UNIT,A_LENGTH,A_WIDTH," +
				"A_HEIGHT,PACKING_WEIGHT,PAL_BASE_QBOX,PAL_HEIGHT_QBOX," +
				"SORTER_FLAG,RULE_FLAG,QPALETTE,CREATE_FLAG," +
				"RGST_NAME,RGST_DATE,UPDT_NAME,UPDT_DATE,enterprise_no, row_id)" +
				"values('"+reqMod.getArticleNo()+ "'," +
						"'"+reqMod.getPackQty()+ "',"+
						"'"+listArtPack.get(0)+ "'," +
						"'"+reqMod.getSpec()+ "'," +
						"''," +
						"''," +
						/*"'"+reqMod.getMpackingUnit()+ "'," +
						"'"+reqMod.getMpackingUnit()+ "'," +*/
					/*	"'"	+reqMod.getA_length()+ "'," +
						"'"	+reqMod.getA_width()+ "'," +
						"'"	+reqMod.getA_height()+ "'," +
						"'"	+reqMod.getPackingWeight()+ "'," +
						"'"	+reqMod.getPalBaseBox()+ "','','0','1'," +
						"'"+reqMod.getQpalette()+"','0'," +
						"'"+reqMod.getRgstName()+"'," +
						"sysdate,'',''," +
					/*"'"+reqMod.getPackingUnit()	+"',*/
					/*	"'"+reqMod.getEnterpriseNo()+"' ,'')";*/
						

		/*genDao.updateBySql(strSql2);*/
		return new MsgRes(true,"","保存成功！");
	}
	//获取仓别堆叠
	@Override
	public List<Bdef_WarehousePackingModel> queryBdefWarehousePacking(
			String enterpriseNo,String warehouseNo, String strArticleNo, String strPackingQty)
			throws Exception {
		String strSql = "select w.* from " +
				"bdef_warehouse_packing w " +
				"where w.warehouse_no='"+warehouseNo+"' " +
				"and w.article_no='"+strArticleNo+"' " +
				"and w.packing_qty='"+strPackingQty+"' "+
				"and w.enterprise_no='"+enterpriseNo+"'";
		List<Bdef_WarehousePackingModel> list = genDao.getListByNativeSql(
				strSql,Bdef_WarehousePackingModel.class);
		return (List<Bdef_WarehousePackingModel>)list;	
		
	}
	//保存商品仓别堆叠
	@Override
	public MsgRes saveOrUpdateWarehousePacking(String strPacking,
			String WarehouseNo) throws Exception {
		Bdef_WarehousePacking poBa=(Bdef_WarehousePacking)JSONObject.toBean(JSONObject.fromObject(strPacking),Bdef_WarehousePacking.class);
		poBa.setWarehouseNo(WarehouseNo);
		genDao.saveOrUpdateObj(poBa);
		return new MsgRes(true,"保存成功","");
	}
	
	/**
	 * 删除商品
	 */
	@Override
	public MsgRes deleteArticleNo(String strArticleNo, String strOwnerNo,
			String enterpriseNo) throws Exception {
		//查询库存是否有该商品
		String sql = "select * from stock_content m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list = genDao.getListByNativeSql(sql);
		if(list.size()>0){
			return new MsgRes(false,"该商品有库存，不能删除，请检查！",null);
		}
		//查询进货单是否有该商品
		String sql2 = "select * from idata_import_d m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list2 = genDao.getListByNativeSql(sql2);
		if(list2.size()>0){
			return new MsgRes(false,"该商品有进货单，不能删除，请检查！",null);
		}
		//查询出货单是否有该商品
		String sql3 = "select * from odata_exp_d  m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list3 = genDao.getListByNativeSql(sql3);
		if(list3.size()>0){
			return new MsgRes(false,"该商品有出货单，不能删除，请检查！",null);
		}
		//查询返配单是否有该商品
		String sql4 = "select * from ridata_untread_d  m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list4 = genDao.getListByNativeSql(sql4);
		if(list4.size()>0){
			return new MsgRes(false,"该商品有返配单，不能删除，请检查！",null);
		}
		//查询退货单是否有该商品
		String sql5 = "select * from rodata_recede_d  m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list5 = genDao.getListByNativeSql(sql5);
		if(list5.size()>0){
			return new MsgRes(false,"该商品有退货单，不能删除，请检查！",null);
		}
		//查询盘点单是否有该商品
		String sql6 = "select * from fcdata_plan_d  m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list6 = genDao.getListByNativeSql(sql6);
		if(list6.size()>0){
			return new MsgRes(false,"该商品有盘点单，不能删除，请检查！",null);
		}
		//查询调账单是否有该商品
		String sql7 = "select * from stock_plan_d  m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list7 = genDao.getListByNativeSql(sql7);
		if(list7.size()>0){
			return new MsgRes(false,"该商品有调账单，不能删除，请检查！",null);
		}
		//查询报损单是否有该商品
		String sql8 = "select * from sodata_waste_d  m " +
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		List<String> list8 = genDao.getListByNativeSql(sql8);
		if(list8.size()>0){
			return new MsgRes(false,"该商品有报损单，不能删除，请检查！",null);
		}
		//删除商品
		String sql9 = "delete bdef_defarticle m "+
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		genDao.updateBySql(sql9);
		
		String sql10 = "delete bdef_article_packing m "+
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(sql10);
		
		String sql11 = "delete bdef_article_barcode m "+
				"where m.article_no='"+strArticleNo+"' " +
				"and m.enterprise_no='"+enterpriseNo+"' " +
				"and m.owner_no='"+strOwnerNo+"'";
		genDao.updateBySql(sql11);
		
		return new MsgRes(true,"删除成功！",null);
	}
    //新增商品包装/条码
	@Override
	public MsgRes InsertArticleQpalette(String strRecvData) throws Exception {
		ReqUpdateQpaletteModel reqMod=JSON.parseObject(strRecvData, ReqUpdateQpaletteModel.class);	
		//取商品包装单位
//		String sqlStr1="select a.unit  from " +
//				"bdef_defarticle a " +
//				"left join BDEF_DEFOWNER b"+
//				" on a.owner_no=b.owner_no" +
//				" LEFT JOIN BDEF_ARTICLE_BARCODE c "+
//				" on a.barcode=c.barcode"+
//				" where  b.OWNER_NO='"+reqMod.getOwnerNo()+"'"+
//				" and a.article_no='"+reqMod.getArticleNo()+"'" +
//				//" and a.article_identifier='"+reqMod.getArticle_identifier()+"') " +
//				" and a.enterprise_no='" + reqMod.getEnterpriseNo() + "'" ;
//		List listArtPack=genDao.getListByNativeSql(sqlStr1);
//		String strSql1="insert into BDEF_ARTICLE_BARCODE(BARCODE,OWNER_NO,ARTICLE_NO,PRIMARY_FLAG,PACKING_QTY,CREATE_FLAG,RGST_NAME,RGST_DATE,UPDT_NAME,UPDT_DATE,enterprise_no,ROW_ID) " +
//				"values('"
//				+reqMod.getBarcode()
//				+ "','"
//				+reqMod.getOwnerNo()
//				+ "','"		
//				+reqMod.getArticleNo()
//				+ "','0',"	
//				/*+repMod.getPrimaryFlag()
//				+ "','"	*/
//			  	+reqMod.getPackQty()
//				+ ",'1','"	
//				/*+repMod.getCreateFlag()*/
//				//+ "','1','"	
//				+reqMod.getRgstName()
//				+ "',sysdate,'','','"
//				/*+repMod.getUpdtName()
//				+ "','"	
//				+repMod.getUpdtDate()
//				+ "','"	*/
//				+reqMod.getEnterpriseNo()+ "','')";
		String strSql1="UPDATE bdef_defarticle SET barcode='"+reqMod.getBarcode()+"' " +
				"WHERE article_no='"+reqMod.getArticleNo()+"'";
		genDao.updateBySql(strSql1);
		//保存商品包装saveOrUpdateArticleBarcode
		//ArticleInfoModel repsMod=JSON.parseObject(strRecvData, ArticleInfoModel.class);
		String strSql2="insert into  bdef_article_packing (ARTICLE_NO,PACKING_QTY,PACKING_UNIT,SPEC," +
				"MPACKING_QTY,MPACKING_UNIT,A_LENGTH,A_WIDTH," +
				"A_HEIGHT,PACKING_WEIGHT,PAL_BASE_QBOX,PAL_HEIGHT_QBOX," +
				"SORTER_FLAG,RULE_FLAG,QPALETTE,CREATE_FLAG," +
				"RGST_NAME,RGST_DATE,UPDT_NAME,UPDT_DATE,enterprise_no, row_id)" +
				"values('"+reqMod.getArticleNo()+ "'," +
						"'"+reqMod.getPackQty()+ "',"+		 
						"'箱'," +
						"'"+reqMod.getSpec()+ "'," +
						"''," +
						"''," +
						/*"'"+reqMod.getMpackingUnit()+ "'," +
						"'"+reqMod.getMpackingUnit()+ "'," +*/
						"'"	+reqMod.getA_length()+ "'," +
						"'"	+reqMod.getA_width()+ "'," +
						"'"	+reqMod.getA_height()+ "'," +
						"'"	+reqMod.getPackingWeight()+ "'," +
						"'"	+reqMod.getPalBaseBox()+ "'," +
						"'"+reqMod.getPalHeightBox()+"','0','1'," +
						"'"+reqMod.getQpalette()+"','0'," +
						"'"+reqMod.getRgstName()+"'," +
						"sysdate,'',''," +
					/*"'"+reqMod.getPackingUnit()	+"',*/
						"'"+reqMod.getEnterpriseNo()+"' ,'')";
						

		genDao.updateBySql(strSql2);
		return new MsgRes(true,"","保存成功！");
		
		
	}
	
	
	


	//堆叠采集》新增商品信息资料
	@Override
	public MsgRes GetPackingArticleQpalette(String strRecvData)
			throws Exception {	
			MsgRes msgRes = new MsgRes();
			CommSingleDataRequestModel ReqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
			AnsQpaletteInPutBarcodeModel ansMod=new AnsQpaletteInPutBarcodeModel();
			//根据扫描内容找商品资料信息，
			String strSql="select DISTINCT a.article_no atno,a.*,a.unit units, nvl(a.BARCODE, c.BARCODE) BARCODE from " +
					"bdef_defarticle a " +
					 " LEFT JOIN BDEF_ARTICLE_BARCODE c "+
					" on a.owner_no=c.owner_no" +
					 " and  a.article_no=c.article_no" +
					 " and a.enterprise_no=c.enterprise_no"	+
					" where ( c.barcode='"+ReqMod.getReqObj()+"'"+
					"or a.OWNER_article_no='"+ReqMod.getReqObj()+"'"+
					"or a.article_no='"+ReqMod.getReqObj()+"'" +
					"or a.barcode='"+ReqMod.getReqObj()+"'" +
					"or a.article_identifier='"+ReqMod.getReqObj()+"') " +
					"and a.enterprise_no='" + ReqMod.getEnterpriseNo() + "'" ;
		    		List<ArticleInfoModel> list=genDao.getListByNativeSql(strSql, ArticleInfoModel.class);
		    		
		    		ansMod.setListArticlinfo(list);	
		    		if(list.size()==0)
		    		{
		    			msgRes.setIsSucc(false);
		    			msgRes.setMsg("扫描的商品条码不存在！");
		    			return msgRes;
		    		}
		    		
		    		if(list.size()==1)
		    		{
		    			//只有一个品项，则取商品信息
		    			ansMod.setArtcicleNum("2");
		    			List<AnsPackingModel> listArtPack=(List<AnsPackingModel>)GetArticlePacking(list.get(0).getArticleNo()).getObj();
		    			ansMod.setListPacking(listArtPack);
		    		}else
		    		{
		    			//只返回商品信息
		    			ansMod.setArtcicleNum("3");
		    		}
		    		msgRes.setIsSucc(true);
		    		msgRes.setObj(JSON.toJSONString(ansMod));
		    		return msgRes;
	}
	//商品编码扫描
	@Override
	public MsgRes InsertArticleNOQpalette(String strRecvData) throws Exception {
		
		MsgRes msg=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
        // String strArticleNo=msg.getObj().toString();
		
		String strSql="select a.* from bdef_defarticle a " +
				"where a.article_no='"+reqMod.getReqObj()+"' ";
		List<ArticleInfoModel> list=genDao.getListByNativeSql(strSql, ArticleInfoModel.class);
		AnsQpaletteInPutBarcodeModel ansMod=new AnsQpaletteInPutBarcodeModel();
		ansMod.setListArticlinfo(list);		
		if(list.size()==1)
		{
			//只有一个品项，则取包装信息
			ansMod.setArtcicleNum("2");
			//List<AnsPackingModel> listArtPack=(List<AnsPackingModel>)GetArticlePacking(list.get(0).getArticleNo()).getObj();
			//ansMod.setListPacking(listArtPack);
		}else
		{
			//只返回商品信息
			ansMod.setArtcicleNum("3");
		}
		msg.setIsSucc(true);
		msg.setObj(JSON.toJSONString(ansMod));
		return msg;
	}
	
	@Override
	public List<ComboxBo> getArticleInfo(String strEnterpriseNo,String strOwnerNo,
			 String str,
			String strQuery) throws Exception {
		String sql = "select distinct a.article_no value,a.article_no text,"
				+"'['|| a.owner_no || '][' || ltrim(a.owner_article_no)||']'||a.article_name dropValue "
					+ "from bdef_defarticle a "
					+ " where a.enterprise_no='" + strEnterpriseNo + "' "
					//+ " and a.warehouse_No='" + strWarehouseNo + "' "
					+ " and a.owner_no in(" + strOwnerNo + ") ";
					
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + 
					" and (a.article_no like '%"+str+"%' " +
					"or a.article_name like '%"+str+"%' " +
							"or a.barcode like '%"+str+"%' " +
							"or a.owner_article_no like '%"+str+"%' " +
							"or a.article_identifier like '%"+str+"%' )";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//填充状态下拉
	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
			 String strOwnerNo, String str)
			throws Exception {
		String strSql="select distinct a.value value,a.text text,a.text dropValue"+
				" from wms_deffieldval a " + 
				"where 1=1 and a.table_name='BDEF_DEFARTICLE' and a.colname='STATUS' " ;
				 //"and a.enterprise_no='"+strEnterpriseNo+"' " +
				// "and a.warehouse_no='"+strWarehouseNo+"' " +
			     //"and a.owner_no in("+strOwnerNo+") ";
			
			/*if (str != null && !str.equals("")) {
				String ws = CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql + ws;
			}*/
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes SelectArticlePackingInfo(String strRecvData)
			throws Exception {
		bdefArticlePackingInfoModel reqMod=JSON.parseObject(strRecvData, bdefArticlePackingInfoModel.class);		

		MsgRes resultRes=new MsgRes();		
		String sql = " SELECT DISTINCT b.article_no, b.packing_qty,b.packing_unit,b.spec " +
				" from bdef_defarticle a , bdef_article_packing b " +
				" where a.article_no=b.article_no" +
				" and a.enterprise_no=b.enterprise_no" +
				" and a.article_no='"+reqMod.getArticleNo()+"' and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
				" UNION ALL" +
				" SELECT a.article_no,a.qmin_operate_packing,a.qmin_operate_packing_unit, '1*' || a.qmin_operate_packing || a.unit" +
				" FROM bdef_defarticle a " +
				" WHERE a.article_no='"+reqMod.getArticleNo()+"' and a.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
				" UNION ALL" +
				" SELECT a.article_no,a.unit_packing,a.unit, a.spec" +
				" FROM bdef_defarticle a " +
				" WHERE a.article_no='"+reqMod.getArticleNo()+"' and a.enterprise_no='"+reqMod.getEnterpriseNo()+"'  ";
	 
	    List<bdefArticlePackingInfoModel> result=genDao.getListByNativeSql(sql,bdefArticlePackingInfoModel.class,0, 1000);
		 
		if(result.size()==0)
		{			
			return new MsgRes(false,"未找到条码信息","未找到条码信息");			 
		}

		resultRes.setIsSucc(true);
		resultRes.setMsg("成功");
		resultRes.setObj(JSON.toJSON(result));
		return resultRes;
	}
}
