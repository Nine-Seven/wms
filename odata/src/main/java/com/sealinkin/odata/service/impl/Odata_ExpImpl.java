package com.sealinkin.odata.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.po.Odata_ExpD;
import com.sealinkin.odata.po.Odata_ExpM;
import com.sealinkin.odata.po.Odata_ExpTmp;
import com.sealinkin.odata.po.Odata_ExpTmpId;
import com.sealinkin.odata.service.IOdata_ExpService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_ExpImpl implements IOdata_ExpService{
	
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 出货手建单M
	 */
	@Override
	public ExtListDataBo<Odata_ExpMModel> getExp_MList(String currEnterpriseNo,String warehouseNo,String workerOwner,
			String queryStr,PageBo pageBo,String strExpNothy,String expNo,String poNo,
			String strDeliverNo,
			String strShopNo,
			String strSkucount,
			String strCustExpNo,
			String strSendName,
			String strReceiveName,
			String strTakeName,
			String strErpoperateDate1,
			String strErpoperateDate2,
			String strCustsendDate1,
			String strCustsendDate2,
			String strLastCustsendDate1,
			String strLastCustsendDate2) throws Exception {
		String sql="select a.*," +
			//	"s.curr_status," +
			//	"a.exp_no,a.sourceexp_type,a.sourceexp_no,a.dept_no,a.cust_no,a.org_no," +
				"b.cust_name," +
				//"a.status,a.owner_no," +
				"c.owner_name," +
				//"a.exp_type,a.take_type," +
				//"a.cust_phone,a.cust_mail,a.cust_address,a.contactor_name," +
			//	"a.priority,a.fast_flag,a.exp_remark,a.cust_exp_no," +
				"f_get_fieldtext('ODATA_EXP_M','STATUS',a.status)statusText, " +
			//	"a.shipper_no," +
				"d.shipper_name " +
				//"a.create_flag,a.wave_no, " +
			//	"a.shipper_deliver_no, a.deliver_address,a.print_bill_flag,a.order_source,a.CUSTSEND_DATE,a.LAST_CUSTSEND_DATE  " +
				"from odata_exp_m a,bdef_defcust b,bdef_defowner c,bdef_defshipper d " +
			//	",odata_exp_status s " +
				"where a.cust_no=b.cust_no and a.owner_no=c.owner_no and a.owner_no=b.owner_no " +
				" %s1 " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+currEnterpriseNo+"' " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.enterprise_no=d.enterprise_no(+) " +
				"and a.warehouse_no=d.warehouse_no(+) " +
				"and a.shipper_no=d.shipper_no(+) " +
			//	"and a.enterprise_no=s.enterprise_no and a.exp_no=s.exp_no and a.warehouse_no=s.warehouse_no " +
				"";
		
		if(strExpNothy != null && strExpNothy.equals("1"))//是否历史单据0否，1是
		{
			sql = sql.replace("%s1", " and a.status in ('13','16')  ");
		}else if(strExpNothy != null && strExpNothy.equals("0")){
			sql = sql.replace("%s1", " and a.status not in ('13','16')  ");
		}
		
		if(expNo!=null && !expNo.equals("")){
			sql=sql+" and a.exp_no like '%"+expNo+"%' ";
		}
		if(poNo!=null && !poNo.equals("")){
			sql=sql+" and a.sourceexp_no like '%"+poNo+"%' ";
		}
		if(strDeliverNo!=null && !strDeliverNo.equals("")){//快递单号
			sql=sql+" and a.shipper_deliver_no like '%"+strDeliverNo+"%' ";
		}
		if(strShopNo!=null && !strShopNo.equals("")){//店铺号
			sql=sql+" and a.shop_no like '%"+strShopNo+"%' ";
		}
		if(strSkucount!=null && !strSkucount.equals("")){//品项数
			sql=sql+" and a.SKUCOUNT like '%"+strSkucount+"%' ";
		}
		if(strCustExpNo!=null && !strCustExpNo.equals("")){//平台订单号
			sql=sql+" and a.cust_exp_no like '%"+strCustExpNo+"%' ";
		}
		if(strSendName!=null && !strSendName.equals("")){//发货人
			sql=sql+" and a.send_name like '%"+strSendName+"%' ";
		}
		if(strReceiveName!=null && !strReceiveName.equals("")){//收货人
			sql=sql+" and a.CONTACTOR_NAME like '%"+strReceiveName+"%' ";
		}
		if(strTakeName!=null && !strTakeName.equals("")){//提货人
			sql=sql+" and a.take_name like '%"+strTakeName+"%' ";
		}
		
		if(strErpoperateDate1!=null && !strErpoperateDate1.equals("") && strErpoperateDate2!=null && !strErpoperateDate2.equals("")){
			sql=sql+" and a.erpoperate_date >=to_date('"+strErpoperateDate1+"','yyyy-mm-dd hh24:mi:ss') " +
					" and a.erpoperate_date <=to_date('"+strErpoperateDate2+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
		if(strCustsendDate1!=null && !strCustsendDate1.equals("") && strCustsendDate2!=null && !strCustsendDate2.equals("")){
			sql=sql+" and a.custsend_date >=to_date('"+strCustsendDate1+"','yyyy-mm-dd hh24:mi:ss') " +
					" and a.custsend_date <=to_date('"+strCustsendDate2+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
		if(strLastCustsendDate1!=null && !strLastCustsendDate1.equals("") && strLastCustsendDate2!=null && !strLastCustsendDate2.equals("")){
			sql=sql+" and a.last_custsend_date >=to_date('"+strLastCustsendDate1+"','yyyy-mm-dd hh24:mi:ss') " +
					" and a.last_custsend_date <=to_date('"+strLastCustsendDate2+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
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
		sql=sql+" order by a.exp_type,a.exp_no desc ";
		String totsql = "select count(*) from ("+sql+")";
		
		List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql,Odata_ExpMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Odata_ExpMModel> extListBo= new ExtListDataBo<Odata_ExpMModel>(list, count);
        return extListBo;
	}
	
	
	/**
	 * 出货手建单D
	 */
	@Override
	public ExtListDataBo<Odata_ExpDModel> getExp_DList(String currEnterpriseNo,String wheresql,
			PageBo pageBo) throws Exception {
		String sql="select a.exp_no,a.article_no,c.barcode,c.article_name," +
				"a.packing_qty," +
				"c.qmin_operate_packing,c.unit_packing, " +//add by huangcx at 20150528
				//"nvl(d.packing_unit, decode(a.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"+
                //" nvl(d.spec, '1*' || a.packing_qty || c.unit) spec,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec,"+
				"a.owner_article_no," +
				"a.article_qty,a.unit_cost," +
				//add by huangcx at 20160528
				"trunc(a.article_qty/a.packing_qty) as planBox," +
				"trunc(mod(a.article_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin," +
				"(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis," +
				//end add
				"a.produce_condition as produceCond," +
				"a.produce_value1 as produceV1," +
				"a.produce_value2 as produceV2, " +
				"a.lotno_condition as lotnoCondition,"+
                "a.lotno_value1 as lotnoValue1,"+
                "a.lotno_value2 as lotnoValue2, " +
                "A.SPECIFY_FIELD,A.SPECIFY_CONDITION,A.SPECIFY_VALUE1,A.SPECIFY_VALUE2 "+
				"from Odata_Exp_D a,bdef_defarticle c," +
				"bdef_article_packing d " +
				"where a.enterprise_no='"+currEnterpriseNo+"' and " +
				" a.enterprise_no=c.enterprise_no and a.enterprise_no=d.enterprise_no(+) and " +
				"a.article_no=c.article_no and " +
				"a.article_no=d.article_no(+) and " +
				"a.packing_qty=d.packing_qty(+) and a.exp_no='"+wheresql+"'";
		List<Odata_ExpDModel> list=genDao.getListByNativeSql(sql, Odata_ExpDModel.class, pageBo.getStart(), 1000);
		ExtListDataBo<Odata_ExpDModel> extListBo=new ExtListDataBo<Odata_ExpDModel>(list,0);
		return extListBo;
	}
	//修改
	@Override
	public MsgRes changeExp(String expM, String expD)
			throws Exception {
		Odata_ExpM om=(Odata_ExpM)JSON.parseObject(expM,Odata_ExpM.class);
		List<Odata_ExpD> list=JSON.parseArray(expD,Odata_ExpD.class);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	//	String LastCustsendDate=sdf.format(om.getLastCustsendDate());
		String s1 = null;
		if(om.getCustsendDate()!=null){  //to_date('1980-12-13','yyyy-mm-dd')
			String CustsendDate=sdf.format(om.getCustsendDate());
			s1=" m.CUSTSEND_DATE=to_date('"+CustsendDate+"' ,'yyyy-mm-dd'),";
		}else{
			s1=" m.status=m.status,";

		}
		//7-12添加
		String s2 = null;
		if(om.getLastCustsendDate()!=null){
			String LastCustsendDate=sdf2.format(om.getLastCustsendDate());
			s2=" m.LAST_CUSTSEND_DATE=to_date('"+LastCustsendDate+"' ,'yyyy-mm-dd'),";
		}else{
			s2=" m.LAST_CUSTSEND_DATE='' ,";

		}
		String sql="update odata_exp_m m set m.cust_mail='"+om.getCustMail()+"',m.cust_address='"+om.getCustAddress()+"' " +
				",m.contactor_name='"+om.getContactorName()+"',m.cust_phone='"+om.getCustPhone()+
				"',m.exp_remark='"+om.getExpRemark()+"',"+s1 + s2 +
				"m.SHIPPER_NO='"+om.getShipperNo()+"', " +
				
				"m.send_address='"+om.getSendAddress()+"', m.send_name='"+om.getSendName()+"' ," +
				"m.send_company_name='"+om.getSendCompanyName()+"' ,m.send_postcode='"+om.getSendPostcode()+"' ," +
				"m.send_mobile_phone='"+om.getSendMobilePhone()+"' ,m.send_telephone='"+om.getSendTelephone()+"' , " +
				"m.send_jpn='"+om.getSendJpn()+"' ,m.send_province='"+om.getSendPostcode()+"'," +
				"m.send_city='"+om.getSendCity()+"' ,m.send_zone='"+om.getSendZone()+"' ,m.send_country='"+om.getSendCountry()+"', " +
			
				"m.take_address='"+om.getTakeAddress()+"', m.take_name='"+om.getTakeName()+"' ," +
				"m.take_company_name='"+om.getTakeCompanyName()+"' ,m.take_postcode='"+om.getTakePostcode()+"' ," +
				"m.take_mobile_phone='"+om.getTakeMobilePhone()+"' ,m.take_telephone='"+om.getTakeTelephone()+"' , " +
				"m.take_jpn='"+om.getTakeJpn()+"' ,m.take_province='"+om.getTakePostcode()+"'," +
				"m.take_city='"+om.getTakeCity()+"' ,m.take_zone='"+om.getTakeZone()+"' ,m.take_country='"+om.getTakeCountry()+"', " +

				"m.receive_company_name='"+om.getReceiveCompanyName()+"',m.receive_telephone='"+om.getReceiveTelephone()+"'," +
				"m.receive_jpn='"+om.getReceiveJpn()+"',m.receive_province='"+om.getReceiveProvince()+"'," +
				"m.receive_city='"+om.getReceiveCity()+"',m.receive_zone='"+om.getReceiveZone()+"',m.receive_country='"+om.getReceiveCountry()+"'," +
				//7-11添加   还没测试
				"m.owner_no='"+om.getOwnerNo()+"',m.exp_type='"+om.getId().getExpType()+"'," +
				"m.take_type='"+om.getTakeType()+"',m.sourceexp_no='"+om.getSourceexpNo()+"'," +
				"m.cust_no='"+om.getCustNo()+"',m.priority='"+om.getPriority()+"'," +
				"m.fast_flag='"+om.getFastFlag()+"',m.shipper_deliver_no='"+om.getShipperDeliverNo()+"'," +
				"m.deliver_address='"+om.getDeliverAddress()+"',m.print_bill_flag='"+om.getPrintBillFlag()+"'," +
				"m.order_source='"+om.getOrderSource()+"',m.org_no='"+om.getOrgNo()+"'," +
				"m.cust_exp_no='"+om.getCustExpNo()+"'," +
				
				
				"m.updt_name='"+om.getUpdtName()+"',m.updt_date=sysdate " +
				"where m.exp_no='"+om.getId().getExpNo()+"' and m.enterprise_no='"+om.getId().getEnterpriseNo()+"'";  
		String expNo="";
		
		if(om.getStatus().equals("10")){
			String sqlBackUpTwo="insert into odata_exp_d_log(enterprise_no,warehouse_no,owner_no,exp_no,article_no," +
									"packing_qty,article_qty,schedule_qty,locate_qty,real_qty,unit_cost," +
									"owner_article_no,produce_condition,produce_value1,produce_value2," +
									"expire_condition,expire_value1,expire_value2,quality_condition,quality_value1," +
									"quality_value2,lotno_condition,lotno_value1,lotno_value2,rsvbatch1_condition," +
									"rsvbatch1_value1,rsvbatch1_value2,rsvbatch2_condition,rsvbatch2_value1," +
									"rsvbatch2_value2,rsvbatch3_condition,rsvbatch3_value1,rsvbatch3_value2," +
									"rsvbatch4_condition,rsvbatch4_value1,rsvbatch4_value2,rsvbatch5_condition," +
									"rsvbatch5_value1,rsvbatch5_value2,rsvbatch6_condition,rsvbatch6_value1," +
									"rsvbatch6_value2,rsvbatch7_condition,rsvbatch7_value1,rsvbatch7_value2," +
									"rsvbatch8_condition,rsvbatch8_value1,rsvbatch8_value2,specify_field," +
									"specify_condition,specify_value1,specify_value2,status," +
									"error_status,rgst_date,exp_date)" +
									"select enterprise_no,warehouse_no,owner_no,exp_no,article_no," +
									"packing_qty,article_qty,schedule_qty,locate_qty,real_qty,unit_cost," +
									"owner_article_no,produce_condition,produce_value1,produce_value2," +
									"expire_condition,expire_value1,expire_value2,quality_condition,quality_value1," +
									"quality_value2,lotno_condition,lotno_value1,lotno_value2,rsvbatch1_condition," +
									"rsvbatch1_value1,rsvbatch1_value2,rsvbatch2_condition,rsvbatch2_value1," +
									"rsvbatch2_value2,rsvbatch3_condition,rsvbatch3_value1,rsvbatch3_value2," +
									"rsvbatch4_condition,rsvbatch4_value1,rsvbatch4_value2,rsvbatch5_condition," +
									"rsvbatch5_value1,rsvbatch5_value2,rsvbatch6_condition,rsvbatch6_value1," +
									"rsvbatch6_value2,rsvbatch7_condition,rsvbatch7_value1,rsvbatch7_value2," +
									"rsvbatch8_condition,rsvbatch8_value1,rsvbatch8_value2,specify_field," +
									"specify_condition,specify_value1,specify_value2,status," +
									"error_status,rgst_date,exp_date from odata_exp_d d where d.exp_no='"+om.getId().getExpNo()+"' " +
									"and d.enterprise_no='"+om.getId().getEnterpriseNo()+"'";

			this.genDao.updateBySql(sqlBackUpTwo);
			deleteExp(om.getId().getEnterpriseNo(),om.getId().getExpNo());
			expNo=om.getId().getExpNo();
			this.genDao.saveList(list);
		}
		String sqlBackUp="insert into odata_exp_m_log(enterprise_no,exp_type,warehouse_no,exp_no,owner_no,owner_cust_no," +
				"cust_no,sub_cust_no,sourceexp_type,sourceexp_no,exp_date,fast_flag," +
				"status,priority,add_exp_no,import_no,deliver_type,transport_type,batch_no," +
				"line_no,full_line_name,cust_address,cust_address_code,contactor_name," +
				"cust_phone,cust_mail,error_status,create_flag,return_flag,exp_remark," +
				"belong_flag,send_flag,buffer_line_no,special_article_group,exp_status," +
				"stock_type,order_period,finance_type,kick_flag,real_cust_no,real_cust_name," +
				"dept_name,agent_no,payment_term,dept_no,cust_exp_no,erpoperate_date," +
				"custsend_date,print_flag,erp_no,diff_reason,rgst_name,rgst_date,updt_name," +
				"updt_date,wave_no,shipper_no) " +
				"select enterprise_no,exp_type,warehouse_no,exp_no,owner_no,owner_cust_no" +
				",cust_no,sub_cust_no,sourceexp_type,sourceexp_no,exp_date,fast_flag," +
				"status,priority,add_exp_no,import_no,deliver_type,transport_type,batch_no," +
				"line_no,full_line_name,cust_address,cust_address_code,contactor_name," +
				"cust_phone,cust_mail,error_status,create_flag,return_flag,exp_remark," +
				"belong_flag,send_flag,buffer_line_no,special_article_group,exp_status," +
				"stock_type,order_period,finance_type,kick_flag,real_cust_no,real_cust_name," +
				"dept_name,agent_no,payment_term,dept_no,cust_exp_no,erpoperate_date," +
				"custsend_date,print_flag,erp_no,diff_reason,rgst_name,rgst_date,updt_name," +
				"updt_date,wave_no,shipper_no from odata_exp_m m where m.exp_no='"+om.getId().getExpNo()+"' " +
				"and m.enterprise_no='"+om.getId().getEnterpriseNo()+"'";
        this.genDao.updateBySql(sqlBackUp);
		this.genDao.updateBySql(sql);
		
		//回写手建单品项数
		updateExpM(om.getId().getEnterpriseNo(),
				   om.getId().getWarehouseNo(),
				   om.getOwnerNo(),
				   om.getId().getExpNo());

		return new MsgRes(true, "数据保存成功！", expNo);
	}
	
	//保存手建单
	public MsgRes saveExp(String expM, String expD)
			throws Exception {
		Odata_ExpM om=(Odata_ExpM)JSON.parseObject(expM,Odata_ExpM.class);
		List<Odata_ExpD> list=JSON.parseArray(expD,Odata_ExpD.class);
		om.setUpdtDate(null);
		om.setUpdtName(null);
		String expNo="";
		if(om.getId().getExpNo().equals("保存时自动生成")){
			List inList2=new ArrayList();
			List outList2=new ArrayList();
			List resultList2=new ArrayList();
			
			inList2.add(list.get(0).getId().getEnterpriseNo());
			inList2.add(list.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.ODATAOE);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			expNo=resultList2.get(0).toString();
			om.getId().setExpNo(expNo);
			for(Odata_ExpD d:list){
				d.getId().setExpNo(expNo);
			}
		}else{
			deleteExp(om.getId().getEnterpriseNo(),om.getId().getExpNo());
			expNo=om.getId().getExpNo();
		}
		this.genDao.saveOrUpdateObj(om);
		this.genDao.saveList(list);
	
		return new MsgRes(true, "数据保存成功！", expNo);
	}
	@Override
	public void deleteExp(String currEnterpriseNo,String expNo) throws Exception {
		String delsql="delete Odata_Exp_D where exp_no='"+expNo+"' " +
			         "and enterprise_no='"+currEnterpriseNo+"'";
		genDao.updateBySql(delsql);
		
	}
	//回写出货货单头档品项数（总数）
	@Override
	public MsgRes updateExpM(String strEnterpriseNo, String strWarehouseNo,
			String strOwner, String strExpNo) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwner);
		inList.add(strExpNo);
		resultList=genDao.execProc(inList, outList, "PKLG_ODISPATCH.P_LOCATECHECK_UPDATESKU");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"","");
	}
	//校验该单是否已经定过位
	@Override
	public MsgRes editExp(String currEnterpriseNo,String expNo) throws Exception {
		String sql="select m.wave_no from odata_exp_m m " +
				"where m.enterprise_no = '"+currEnterpriseNo+"' " +
				"and m.exp_no='"+expNo+"' ";
		List<String> list = genDao.getListByNativeSql(sql);
		if(list.get(0) == null){
			return new MsgRes(true,"","");
		}else{
			return new MsgRes(false,"该出货单已经定过位，不能修改！","");
		}
		
	}
	@Override
	public String checkPoNo(String currEnterpriseNo,String warehouseNo,String poNo) throws Exception {
		String sql="select a.sourceexp_no from Odata_Exp_M a where a.sourceexp_no='"+poNo+"' " +
				" and a.warehouse_no='"+warehouseNo+"'" +
				" and a.enterprise_no='"+currEnterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	

	@Override
	public List<Bdef_ArticlePackingModel> getPackingUnit(String currEnterpriseNo,String articleNo,String packingQty,String type)throws Exception {
		System.out.println(type);
		if(type==null){
			String sql="select a.packing_unit,a.spec from bdef_article_packing a " +
					"where a.enterprise_no='"+currEnterpriseNo+"' and a.article_no='"+articleNo+"' and a.packing_qty="+packingQty+"";
			List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(sql,Bdef_ArticlePackingModel.class,0,100);
			return (List<Bdef_ArticlePackingModel>)list;
		}else{
			String sql="select a.packing_unit,a.spec,b.owner_article_no " +
					"from bdef_article_packing a,bdef_defarticle b " +
					"where a.article_no='"+articleNo+"' and a.packing_qty="+packingQty+" and " +
					"a.article_no=b.article_no and a.enterprise_no='"+currEnterpriseNo+"' and a.enterprise_no=b.enterprise_no";
			List<Bdef_ArticlePackingModel> list=genDao.getListByNativeSql(sql,Bdef_ArticlePackingModel.class,0,100);
			return (List<Bdef_ArticlePackingModel>)list;
		}
	}
	
	/**
	 * 选择客户时加载数据
	 * @param strCustNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Odata_ExpMModel> queryCust(String currEnterpriseNo,String strCustNo) throws Exception {
		String strSql="select bdc.contactor_name1 as contactorName," +
					  		  "bdc.cust_phone1 as custPhone," +
					  		  "bdc.cust_email1 as custMail," +
					  		  "bdc.delivery_address as custAddress," +
					  		  "bdc.owner_cust_no as ownerCustNo " +
				"from bdef_defcust bdc " +
				"where bdc.cust_no='"+strCustNo+"' " +
				"and bdc.enterprise_no='"+currEnterpriseNo+"'";
		List<Odata_ExpMModel> list=genDao.getListByNativeSql(strSql,Odata_ExpMModel.class);
		return list;
	}
	@Override
	public List<ComboxBo> getExp_MCombo(String currEnterpriseNo,String warehouseNo, String pageSql)
			throws Exception {
		String sql="select distinct r1.shipper_no value,r1.shipper_name text,'['|| ltrim(r1.shipper_no)||']'||r1.shipper_name dropValue " +
				"from bdef_defshipper r1 " +
				"where 1=1  and r1.status='1'";
		if(warehouseNo!=null && warehouseNo!=""){
		    sql+="and r1.warehouse_no='"+warehouseNo+"' ";
		}
		if(currEnterpriseNo!=null && currEnterpriseNo!=""){
		    sql+="and r1.enterprise_no='"+currEnterpriseNo+"' ";
		}
		if(pageSql!=null && pageSql!=""){
		    sql+="and r1.shipper_no like '%"+pageSql+"%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	
	/**
	 * 上传Excel导入数据库
	 */
	@Override
	public MsgRes upLoad(File file, String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo) throws Exception {
		FileUtilSys.writeFile(file, "OdataTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		List<Odata_ExpTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"OdataTmp.xlsx",strWarehouseNo,strCurrEnterpriseNo);
		
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strCurrEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "pkobj_Create_base.p_odata_exp");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"导入成功","");
	}
	
	/**
	 * Excel数据转List
	 */
	@Override
	public List<Odata_ExpTmp> changeexcelBean(String fileName,
			String strWarehouseNo,String strCurrEnterpriseNo) throws Exception {
		List<Odata_ExpTmp> iitList = new ArrayList<Odata_ExpTmp>();
		//导入前删除临时表数据
		String delsql="delete odata_exp_tmp a where a.enterprise_no='"+strCurrEnterpriseNo+"' ";
		genDao.updateBySql(delsql);
				
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Odata_ExpTmp po = new Odata_ExpTmp();
				Odata_ExpTmpId id = new Odata_ExpTmpId();
				id.setEnterpriseNo(strCurrEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);	
				
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setSourceexpNo(excelList.get(i).get(1).toString());
				id.setOwnerNo(excelList.get(i).get(2).toString());
				id.setCustNo(excelList.get(i).get(3).toString());
				id.setExpType(excelList.get(i).get(4).toString());
				
				id.setTakeType(excelList.get(i).get(5).toString());
				id.setFastFlag(excelList.get(i).get(6).toString());
				id.setOwnerArticleNo(excelList.get(i).get(7).toString());
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(8).toString()));
			
				id.setArticleQty(Double.parseDouble(excelList.get(i).get(9).toString().equals("")?"0":excelList.get(i).get(9).toString())*Double.parseDouble(excelList.get(i).get(8).toString().equals("")?"1":excelList.get(i).get(8).toString())+Double.parseDouble(excelList.get(i).get(10).toString().equals("")?"0":excelList.get(i).get(10).toString()));
				id.setUnitCost(Double.parseDouble(excelList.get(i).get(11).toString().equals("")?"0":excelList.get(i).get(11).toString()));
				
				if(excelList.get(i).get(12).toString().equals("")){
					id.setExpDate(new Date());
				}else{
					id.setExpDate(sdf.parse(excelList.get(i).get(12).toString()));
				}
				
				id.setShipperNo(excelList.get(i).get(13).toString().equals("")?"":excelList.get(i).get(13).toString());
				//订单类型(电商平台代码)
				id.setOrderSource(excelList.get(i).get(14).toString().equals("")?"0":excelList.get(i).get(14).toString());
				//快递单号
				id.setShipperDeliverNo(excelList.get(i).get(15).toString().equals("")?"":excelList.get(i).get(15).toString());
				if(excelList.get(i).get(16).toString().equals("")){
					throw new Exception("第"+(i+1)+"条数据发货人不能为空");
				}
				id.setSendName(excelList.get(i).get(16).toString().equals("")?"":excelList.get(i).get(16).toString());//发货人
				if(excelList.get(i).get(16).toString().equals("")){
					throw new Exception("第"+(i+1)+"条数据发货人手机不能为空");
				}
				id.setSendMobilePhone(excelList.get(i).get(17).toString().equals("")?"":excelList.get(i).get(17).toString());//发货人手机
				id.setSendTelephone(excelList.get(i).get(18).toString().equals("")?"":excelList.get(i).get(18).toString());//发货人固定电话
				id.setSendAddress(excelList.get(i).get(19).toString().equals("")?"":excelList.get(i).get(19).toString());//发货人地址
				if(excelList.get(i).get(20).toString().equals("")){
					throw new Exception("第"+(i+1)+"条数据收货人不能为空");
				}
				id.setContactorName(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(20).toString());//收货人
				id.setCustAddress(excelList.get(i).get(21).toString().equals("")?"":excelList.get(i).get(21).toString());//收货人地址
				if(excelList.get(i).get(22).toString().equals("")){
					throw new Exception("第"+(i+1)+"条数据收货人电话不能为空");
				}
				id.setCustPhone(excelList.get(i).get(22).toString().equals("")?"":excelList.get(i).get(22).toString());//收货人电话
				id.setReceiveProvince(excelList.get(i).get(23).toString().equals("")?"":excelList.get(i).get(24).toString());
				id.setReceiveCity(excelList.get(i).get(24).toString().equals("")?"":excelList.get(i).get(24).toString());
				id.setReceiveZone(excelList.get(i).get(25).toString().equals("")?"":excelList.get(i).get(25).toString());
				
				id.setLotnoCondition(excelList.get(i).get(26).toString().equals("")?"":excelList.get(i).get(26).toString());
				id.setLotnoValue1(excelList.get(i).get(27).toString().equals("")?"":excelList.get(i).get(27).toString());
				id.setLotnoValue2(excelList.get(i).get(28).toString().equals("")?"":excelList.get(i).get(28).toString());
				id.setSpecifyCondition(excelList.get(i).get(29).toString().equals("")?"":excelList.get(i).get(29).toString());
				id.setSpecifyValue1(excelList.get(i).get(30).toString().equals("")?"":excelList.get(i).get(30).toString());
				id.setSpecifyValue2(excelList.get(i).get(31).toString().equals("")?"":excelList.get(i).get(31).toString());
				id.setExpRemark(excelList.get(i).get(32).toString().equals("")?"":excelList.get(i).get(32).toString());
				
				id.setRsvVarod1(excelList.get(i).get(33).toString().equals("")?"":excelList.get(i).get(33).toString());
				id.setRsvVarod2(excelList.get(i).get(34).toString().equals("")?"":excelList.get(i).get(34).toString());
				id.setRsvVarod3(excelList.get(i).get(35).toString().equals("")?"0":excelList.get(i).get(35).toString());
				id.setRsvVarod4(excelList.get(i).get(36).toString());//海关订单批次号  不可为空   2016.8.3  update by czh
				id.setRsvVarod5(excelList.get(i).get(37).toString().equals("")?"":excelList.get(i).get(37).toString());
				id.setRsvVarod6(excelList.get(i).get(38).toString().equals("")?"":excelList.get(i).get(38).toString());
				id.setRsvVarod7(excelList.get(i).get(39).toString().equals("")?"":excelList.get(i).get(39).toString());
				id.setRsvVarod8(excelList.get(i).get(40).toString().equals("")?"":excelList.get(i).get(40).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(41).toString().equals("")?"0":excelList.get(i).get(41).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(42).toString().equals("")?"0":excelList.get(i).get(42).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(43).toString().equals("")?"0":excelList.get(i).get(43).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(44).toString().equals("")?"1900-01-01":excelList.get(i).get(44).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(45).toString().equals("")?"1900-01-01":excelList.get(i).get(45).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(46).toString().equals("")?"1900-01-01":excelList.get(i).get(46).toString()));

				id.setStatus("10");
				po.setId(id);
				iitList.add(po);
			}	
		}
		return iitList;
	}
	
	/**
	 * 解析Excel
	 */
	@Override
	public List<List> impExcel(String execelFile) throws Exception {
		List<List> inputlist = new ArrayList<List>();
		try {
			// 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)
			Workbook book = null;
			try {
				// Excel 2007获取方法
				book = new XSSFWorkbook(new FileInputStream(execelFile));
			} catch (Exception ex) {
				// Excel 2003获取方法
				book = new HSSFWorkbook(new FileInputStream(execelFile));
			}
			// 读取表格的第一个sheet页
			Sheet sheet = book.getSheetAt(0);
			// 定义 row、cell
			Row row;
			String cell;
			// 总共有多少行,从0开始
			int totalRows = sheet.getLastRowNum();
			// 总共有多少列
			int totalCells = sheet.getRow(0).getLastCellNum();
			
			// 循环输出表格中的内容,首先循环取出行,再根据行循环取出列
			for (int i = 1; i <= totalRows; i++) {
				row = sheet.getRow(i);
				List object = new ArrayList();
				// 处理空行
				if (row == null) {
					// object.add("");
					continue;
				}
				// 总共有多少列,从0开始
				for (int j = row.getFirstCellNum(); j < totalCells; j++) {
					// 处理空列
					if (row.getCell(j) == null) {
						object.add("");
						continue;
					}
					//获取单元格内容
					try 
					{
						if(j==8 || j==9 || j==10 || j==11){	
							
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
					}catch (Exception e) 
					{  
						cell = row.getCell(j).toString();
					}
					object.add(cell.trim());
				}
				inputlist.add(object);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return inputlist;
	}
	
	//Excel格式转换
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell xssfCell, int length) {
		String style = "0";
		for(int i=0; i<length;i++){
			if(i==0){
				style=style+".";
			}
			style=style+"0";
		}
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat(style);
			String whatYourWant = df.format(xssfCell.getNumericCellValue());
			return whatYourWant;
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
			return String.valueOf(xssfCell.getStringCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	//关单
	@Override
	public MsgRes closeOrder(String currEnterpriseNo,String warehouseNo, String workerNo, String expNo,String flag)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(expNo);
		inList.add(flag);
		inList.add(workerNo);
		System.out.println("inList: "+inList.toString());
		resultList=genDao.execProc(inList, outList, "PKLG_OLOCATE.p_close_exp");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"操作成功","");
	}
	@Override
	public List<Idata_ImportDModel> getArticle(String articleNo,String currEnterpriseNo)
			throws Exception {
		String sql="select a.article_no,a.article_name,a.owner_article_no,a.barcode,a.qmin_operate_packing,a.unit_packing from " +
				"bdef_defarticle a where " +
				" a.article_no='"+articleNo+"' and a.enterprise_no='"+currEnterpriseNo+"' ";
		List<Idata_ImportDModel> list=genDao.getListByNativeSql(sql,Idata_ImportDModel.class,0,1000);
		return (List<Idata_ImportDModel>)list;
	}
	
	//打印拣货单
	@Override
	public MsgRes tscPrintPickingNo(String currEnterpriseNo,String warehouseNo,
			String workSpaceNo, String workerNo,
			String expNo,String type,String ownerNo,String strQuery) throws Exception {
		
		String sql1=" select a.exp_no from odata_exp_m a " +
				    " where a.enterprise_no='"+currEnterpriseNo+"' " +
				   	" and a.warehouse_no='"+warehouseNo+"' " +
				   	" and a.owner_no='"+ownerNo+"' " +
				    " and a.exp_no='"+expNo+"' " +
				    " and a.status<>'10' ";
		
		List list = genDao.getListByNativeSql(sql1);
		
		if(list.size()>0){
			return new MsgRes(false,"该单据不是建单状态！","");
		}

		List outList=new ArrayList();
		List resultList=new ArrayList();
		List inList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(type);
		inList.add(workerNo);
		//inList.add("1");
		inList.add(expNo);
		inList.add(workSpaceNo);
		//inList.add(strQuery);
		//inList.add("N");
	//	System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_OLOCATE.P_LocateAndGetTask");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"打印成功！","");	
   }
	//校验该货主是否为大货位管理
	@Override
	public MsgRes checkCell(String enterpriseNo, String ownerNo)
			throws Exception {
		String sql ="select ow.cell_manager_type " +
				"from bdef_defowner ow " +
				"where ow.enterprise_no='"+enterpriseNo+"' " +
				"and ow.owner_no='"+ownerNo+"' " +
				"and ow.cell_manager_type='1'";
		
		List<String> list = genDao.getListByNativeSql(sql);
		if(list.size()>0){
			return new MsgRes(true,"","");
		}else{
			return new MsgRes(false,"","");
		}
		
	}
	
	//获得出货申请单商品详细信息列表
	@Override
	public ExtListDataBo<Odata_ExpDModel> getOrder_GoodsDetailList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery, PageBo poPageBo)
			throws Exception {
		String strSql = "select bda.OWNER_ARTICLE_NO,bda.ARTICLE_NO, bda.article_name,bda.BARCODE,d.plan_qty,d.scan_qty,d.plan_qty - d.scan_qty as diffqty " + 
				" from odata_exp_check_m m, odata_exp_check_d d, v_bdef_defarticle bda " + 
				" where m.enterprise_no = d.enterprise_no " +
				" and m.owner_no = d.owner_no " +
				" and m.warehouse_no = d.warehouse_no " +
				" and m.exp_no = d.exp_no " +
				" and bda.ENTERPRISE_NO = d.enterprise_no " +
				" and bda.owner_no = d.owner_no " +
				" and bda.article_no = d.article_no " +
				" and m.status = '12' " +
				" and m.enterprise_no='" + strEnterpriseNo + "' "+
				" and m.warehouse_No='" + strWarehouseNo + "' "+
				" and m.owner_no in(" + strWorkerOwner + ") ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		List<Odata_ExpDModel> list = genDao.getListByNativeSql(strSql,Odata_ExpDModel.class);
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");	
		ExtListDataBo<Odata_ExpDModel> extListBo= new ExtListDataBo<Odata_ExpDModel>(list, intCount);
		return extListBo;
	}
	
	//获得出货查询所有出货申请单列表
	@Override
	public ExtListDataBo<Odata_ExpDModel> getAllOrderList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery, PageBo poPageBo)
			throws Exception {
		String strSql = " select t.*,to_char(t.scan_date,'yyyy-mm-dd') strRgstDate2 " +
				 " from (select m.enterprise_no, " +
			     " m.warehouse_no, " +
			     " m.sourceexp_no, " +
			     " m.cust_no, " +
			     " bdc.cust_name, " +
			     " null as scan_date, " +
			     " bda.OWNER_ARTICLE_NO, " +
			     " bda.article_no, " +
			     " bda.article_name, " +
			     " bda.BARCODE, " +
			     " '未扫描' statusDesc, " +
			     " d.article_qty as plan_qty, " +
			     " 0 as scan_qty, " +
			     " 0 as diffqty " +
			     " from odata_exp_m m, " +
			     " odata_exp_d d, " +
			     " bdef_defcust bdc, " +
			     " v_bdef_defarticle bda " +
			     " where m.enterprise_no = d.enterprise_no " +
			     " and m.owner_no = d.owner_no " +
			     " and m.warehouse_no = d.warehouse_no " +
			     " and m.exp_no = d.exp_no " +
			     " and bdc.enterprise_no = m.enterprise_no " +
			     " and bdc.owner_no = m.owner_no " +
			     " and bdc.cust_no = m.cust_no " +
			     " and bda.ENTERPRISE_NO = d.enterprise_no " +
			     " and bda.owner_no = d.owner_no " +
			     " and bda.article_no = d.article_no " +
			     " and not exists (select 'X' " +
			     " from odata_exp_check_m odec " +
			     " where m.enterprise_no = odec.enterprise_no " +
			     " and m.owner_no = odec.owner_no " +
			     " and m.warehouse_no = odec.warehouse_no " +
			     " and m.exp_no = odec.exp_no) " +
			     " union all " +
			     " select m.enterprise_no, " +
			     " m.warehouse_no, " +
			     " m.sourceexp_no, " +
			     " m.cust_no, " +
			     " bdc.cust_name, " +
			     " trunc(m.rgst_date) as scan_date, " +
			     " bda.OWNER_ARTICLE_NO, " +
			     " bda.article_no, " +
			     " bda.article_name, " +
			     " bda.BARCODE, " +
			     " wdv.text as statusDesc, " +
			     " d.plan_qty, " +
			     " d.scan_qty, " +
			     " d.plan_qty - d.scan_qty as diffqty " +
			     " from odata_exp_check_m m, " +
			     " odata_exp_check_d d, " +
			     " bdef_defcust bdc, " +
			     " v_bdef_defarticle bda, " +
			     " wms_deffieldval wdv " +
			     " where m.enterprise_no = d.enterprise_no " +
			     " and m.owner_no = d.owner_no " +
			     " and m.warehouse_no = d.warehouse_no " +
			     " and m.exp_no = d.exp_no " +
			     " and bdc.enterprise_no = m.enterprise_no " +
			     " and bdc.owner_no = m.owner_no " +
			     " and bdc.cust_no = m.cust_no " +
			     " and bda.ENTERPRISE_NO = d.enterprise_no " +
			     " and bda.owner_no = d.owner_no " +
			     " and bda.article_no = d.article_no " +
			     " and upper(wdv.table_name(+)) = upper('odata_exp_check_m') " +
			     " and upper(wdv.colname(+)) = upper('status') " +
			     " and wdv.value(+) = m.status) t " +
			     " where t.enterprise_no = '" + strEnterpriseNo + "' " +
			     " and t.warehouse_no = '" + strWarehouseNo + "' ";
			     //" order by t.sourceexp_no, t.article_no ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		List<Odata_ExpDModel> list = genDao.getListByNativeSql(strSql,Odata_ExpDModel.class);
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");	
		ExtListDataBo<Odata_ExpDModel> extListBo= new ExtListDataBo<Odata_ExpDModel>(list, intCount);
		return extListBo;
	}
	
	@Override
	public MsgRes checkNoEnoght(String enterpriseNo, String strWarehouseNo,
			String strQuery) throws Exception {
		String strSql="SELECT a.owner_no from " +
				"(" +
					"SELECT  b.enterprise_no,b.warehouse_no,b.owner_no,a.stock_type," +
					"da.article_no,b.packing_qty," +
					"SUM(b.article_qty - NVL(b.locate_qty, 0)) AS exp_qty," +
					"ROUND(SUM((b.article_qty - NVL(b.locate_qty, 0)) / b.packing_qty),3) AS pack_qty," +
					"SUM((b.article_qty - NVL(b.locate_qty, 0)) * da.unit_volumn) AS volumn," +
					"SUM((b.article_qty - NVL(b.locate_qty, 0)) * da.unit_weight) AS weight " +
					"FROM odata_exp_m a,odata_exp_d b,bdef_defarticle da " +
					"WHERE b.article_no = da.article_no " +
					"and a.enterprise_no='"+enterpriseNo+"' "+
					"and a.warehouse_no='"+strWarehouseNo+"' "+
					"and a.exp_no='"+strQuery+"' "+
					"and a.enterprise_no=b.enterprise_no " +
					"and a.warehouse_no = b.warehouse_no " +
					"and a.exp_no = b.exp_no " +
					"and a.status = 10 " +
					"GROUP BY   b.enterprise_no, b.warehouse_no," +
					"b.owner_no," +
					"a.stock_type," +
					"da.ARTICLE_NO," +
					"b.packing_qty" +
				") a," +
				"(" +
					"SELECT  a.enterprise_no,a.warehouse_no,a.owner_no,a.article_no,a.stock_type,a.stock_value," +
					"SUM(a.qty - NVL(a.outstock_qty, 0) + DECODE(a.instock_type, '1', NVL(a.instock_qty, 0), 0)) AS available_qty " +
					"FROM stock_content a,stock_article_info b,cdef_defcell c," +
					"cdef_defarea d " +
					"WHERE a.article_no = b.article_no " +
					"AND a.article_id = b.article_id " +
					"AND a.enterprise_no=c.enterprise_no "+
					"AND a.warehouse_no = c.warehouse_no " +
					"AND a.cell_no = c.cell_no " +
					"and c.warehouse_no = d.warehouse_no " +
					"and c.enterprise_no=d.enterprise_no  "+
					"AND c.ware_no = d.ware_no " +
					"AND c.area_no = d.area_no " +
					"AND a.flag <> '2' " +
					"AND a.status = '0' " +
					"AND a.warehouse_no = '"+strWarehouseNo+"' " +
					"AND a.enterprise_no = '"+enterpriseNo+"' " +
					"AND (a.instock_qty + a.qty - a.outstock_qty + a.unusual_qty) > 0 " +
					"and (d.area_usetype = '1' or d.area_usetype = '5' or d.area_usetype = '6') " +
					"AND c.cell_status = '0' " +
					"AND c.check_status = '0' AND d.Area_Attribute in ('0') " +
					"GROUP BY  a.enterprise_no,a.warehouse_no," +
					"a.owner_no," +
					"a.stock_type," +
					"a.stock_value," +
					"a.article_no" +
				") b," +
				"bdef_defarticle c " +
				"WHERE a.warehouse_no = b.warehouse_no(+) " +
				"AND a.enterprise_no= b.enterprise_no(+) "+
				"AND a.owner_no = b.owner_no(+) " +
				"AND a.article_no = b.article_no(+) " +
				"and a.stock_type = b.stock_type(+) " +
				"and a.article_no = c.article_no " +
				"and a.warehouse_no = '"+strWarehouseNo+"' " +
				"and a.enterprise_no = '"+enterpriseNo+"' " +
				"and DECODE(SIGN(NVL(b.available_qty, 0) - a.exp_qty),-1,a.exp_qty - NVL(b.available_qty, 0),0) > 0";
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(false,"","");
		}else{
			return new MsgRes(true,"","");
		}
	}
	
	//获取单据类型下拉（公用）
	@Override
	public List<ComboxBo> queryExpTypeCombo(String enterpriseNo, String flag)
			throws Exception {
		String strSql="select t1.value value,t1.text text," +
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1," +
				" (SELECT M.VALUE EXP_TYPE," +
				" CASE WHEN WWO.ENTERPRISE_NO IS NULL THEN CASE " +
				" WHEN WOO.ENTERPRISE_NO IS NULL THEN WO.ENTERPRISE_NO " +
				" ELSE WOO.ENTERPRISE_NO END ELSE WWO.ENTERPRISE_NO END ENTERPRISE_NO ," +
				" CASE WHEN WWO.INDUSTRY_FLAG IS NULL THEN CASE  " +
				" WHEN WOO.INDUSTRY_FLAG IS NULL THEN WO.INDUSTRY_FLAG " +
				" ELSE WOO.INDUSTRY_FLAG END ELSE WWO.INDUSTRY_FLAG END INDUSTRY_FLAG " +
				" FROM " +
				" WMS_DEFFIELDVAL M " +
				" LEFT JOIN WMS_WAREHOUSE_OUTORDER WWO ON  M.VALUE = WWO.EXP_TYPE " +
				" LEFT JOIN WMS_OWNER_OUTORDER WOO ON M.VALUE = WOO.EXP_TYPE " +
				" LEFT JOIN WMS_OUTORDER WO ON M.VALUE = WO.EXP_TYPE  " +
				" WHERE 1=1 AND M.TABLE_NAME='N' AND M.COLNAME='EXP_TYPE') t " +
				"where t1.table_name='N' and t1.colName='EXP_TYPE' " +
				"and t1.value =t.exp_type  " +
				"and t.enterprise_no='"+enterpriseNo+"' " +
				"  %s1 ";
		if(flag.equals("1")){//传统
			strSql = strSql.replace("%s1", " and t.industry_flag='1' ");
		}else if(flag.equals("2")){//电商
			strSql = strSql.replace("%s1", " and t.industry_flag='2' ");
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//取消订单		7-12
	@Override
	public MsgRes cancelOrder(String currEnterpriseNo, String warehouseNo,
			String workerNo, String expNo, String flag) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(expNo);
		inList.add("1");
		inList.add(workerNo);
		System.out.println("inList: "+inList.toString());
		resultList=genDao.execProc(inList, outList, "PKLG_OLOCATE.p_close_exp");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"操作成功","");
	}
	
	//写出货单状态跟踪（公用）
	@Override
	public MsgRes tscExpStatus(String currEnterpriseNo, String warehouseNo,
			String workerNo, String expNo) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(expNo);
		inList.add("00");
		inList.add(workerNo);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKOBJ_ODISPATCH.P_Insert_Odata_Exp_Trace");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"","");
	}
	
	/*public String changeType(String var) throws Exception {
		String result="";
		try{	
			Float temp =Float.parseFloat(var);
			int tempInt =temp.intValue();
			if(var.charAt(0)=='0'){
				result=var;
			}else{
				if(var.length()!=String.valueOf(temp).length()){
				
					result=var;
				}else{
					result=String.valueOf(tempInt);	
				}	
			}		
		}catch(Exception e){
			result=var;
		}		
		return result;
	}*/
}
