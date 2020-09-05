package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.model.Odata_LocateMModel;
import com.sealinkin.odata.service.IOdata_LocateAutoService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_LocateAutoImpl implements IOdata_LocateAutoService{
		
	private IGenericManager genDao;
		
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 根据筛选的条件写临时表（自动公用）
	 * B2Cflag。1传统；2电商
	 */
	@Override
	public MsgRes saveLocateSelectTem(String enterpriseNo, String warehouseNo,
			String strWheresql, String strFlag,String B2Cflag) throws Exception {
		String strSql="";
		String b2c="";
		String ip = HttpService.getIpAddr();
	    //插入前先删除临时表的数据
		String sql ="delete odata_tmp_locate_select t " +
				"where t.tmp_id='"+ip+"' and t.enterprise_no='"+enterpriseNo+"' " +
				"and t.warehouse_no='"+warehouseNo+"'"; 
		genDao.updateBySql(sql);
		if(B2Cflag!=null && B2Cflag.equals("2")){
			b2c="1";
		}else{
			b2c="0";
		}
		
		strSql="insert into odata_tmp_locate_select " +
				"(tmp_id,warehouse_no,owner_no,line_no,batch_no,exp_no,volume,weight,qbox,enterprise_no,status)  "+
				"select '"+ip+"',a.warehouse_no,a.owner_no, " +
				"case when vcl.LINE_NO is null then 'N' else vcl.LINE_NO end line_no," +
				"case when vcl.BATCH_NO is null then 'N' else vcl.BATCH_NO end batch_no," +
				"a.exp_no," +
				"sum(b.article_qty * d.unit_volumn) volume," +
				"sum(b.article_qty * d.unit_weight) weight," +
				"sum(b.article_qty/b.packing_qty) qbox,a.enterprise_no, '"+b2c +"' " +
				"from odata_exp_m a,odata_exp_d b,bdef_defarticle d,v_cust_line_batch_cycle vcl " +
				"where a.exp_no=b.exp_no " +
				"and a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no "+
				"and b.enterprise_no=d.enterprise_no "+
				"and a.status='10' " +
				"and b.article_no=d.article_no " +
				"and a.cust_no=vcl.CUST_NO(+) " +
				"and a.warehouse_no=vcl.WAREHOUSE_NO(+) " +
				"and a.enterprise_no=vcl.enterprise_no(+) "+
				"and a.warehouse_no='"+warehouseNo+"' "+
				"and a.enterprise_no='"+enterpriseNo+"' " ;
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
			strSql=strSql+ft;
		}
		strSql+="group by a.enterprise_no,a.warehouse_no,a.owner_no," +
				"a.exp_no,vcl.line_no,vcl.batch_no  " +
		"order by a.enterprise_no,a.warehouse_no,a.exp_no ";
		genDao.updateBySql(strSql);
		
		return new MsgRes(true,"","");
	}
	
	/**
	 * 获取临时表数据(自动、手动公用)
	 */
	@Override
	public ExtListDataBo<Odata_ExpMModel> getLocateM(String enterpriseNo,
			String strWarehouseNo, String strFlag, PageBo poPageBo)
			throws Exception {
		String sql="";
		String ip = HttpService.getIpAddr();
		if(strFlag.equals("1")){//按单
		 sql = "select tmp.exp_no,tmp.volume,tmp.weight,tmp.qbox,m.sourceexp_no,m.cust_no,tmp.owner_no," +
				"  tmp.line_no,m.skucount as articleItems,tmp.status,t.cust_name,t.cust_alias,t.cust_phone1,t.cust_address,t.contactor_name1, "+
				"  count(distinct tmp.exp_no) expNoCount,case  when li.line_name is null then 'N'  else li.line_name end lineName, "+
				"  (select count(*) from oset_buffer bu where bu.enterprise_no=m.enterprise_no and bu.warehouse_no=m.warehouse_no  "+
				"      and bu.cell_no not in (select distinct temp.cell_no from odata_temp_send_area_calculate temp where temp.tmp_id='"+ip+"' "+
				"     and temp.enterprise_no=bu.enterprise_no and temp.warehouse_no=bu.warehouse_no "+
				"     union select send.cell_no from odata_send_area_calculate send)) calculateCount "+
				" from odata_tmp_locate_select tmp,odata_exp_m m,oset_defline li,bdef_defcust t  "+
				" where tmp.enterprise_no=m.enterprise_no and tmp.warehouse_no=m.warehouse_no and tmp.exp_no=m.exp_no "+
				" and tmp.line_no=li.line_no(+) and tmp.enterprise_no=li.enterprise_no(+) and tmp.warehouse_no=li.warehouse_no(+) "+
				" and m.cust_no=t.cust_no and m.enterprise_no=t.enterprise_no and m.owner_no=t.owner_no " +
				" and tmp.enterprise_no='"+enterpriseNo+"' and tmp.warehouse_no='"+strWarehouseNo+"' "+
				" and tmp.tmp_id='"+ip+"' "+
				" group by tmp.exp_no,tmp.volume,tmp.weight,tmp.qbox,m.sourceexp_no,tmp.line_no,m.skucount,tmp.status,m.cust_no,tmp.owner_no," +
				"li.line_name,m.enterprise_no,m.warehouse_no, t.cust_name,t.cust_alias,t.cust_phone1,t.cust_address,t.contactor_name1 ";
		}else{//按客户
			sql = "select sum(tmp.volume) as volume,sum(tmp.weight) as weight,sum(tmp.qbox) as sumBoxQty,sum(m.skucount) as articleItems, "+
				" tmp.line_no,tmp.status,t.cust_name,t.cust_alias,t.cust_phone1,t.cust_address,t.contactor_name1,m.cust_no, tmp.owner_no,"+
				" count(distinct tmp.exp_no) expNoCount,case  when li.line_name is null then 'N'  else li.line_name end lineName, "+
				" (select count(*) from oset_buffer bu where bu.enterprise_no=m.enterprise_no and bu.warehouse_no=m.warehouse_no  "+
				"	   and bu.cell_no not in (select distinct temp.cell_no from odata_temp_send_area_calculate temp where temp.tmp_id='"+ip+"'  "+
				"	   and temp.enterprise_no=bu.enterprise_no and temp.warehouse_no=bu.warehouse_no "+
				"	   union select send.cell_no from odata_send_area_calculate send)) calculateCount "+
				" from odata_tmp_locate_select tmp,odata_exp_m m,oset_defline li,bdef_defcust t  "+
				" where tmp.enterprise_no=m.enterprise_no and tmp.warehouse_no=m.warehouse_no and tmp.exp_no=m.exp_no "+
				" and tmp.line_no=li.line_no(+) and tmp.enterprise_no=li.enterprise_no(+) and tmp.warehouse_no=li.warehouse_no(+) "+
				" and m.cust_no=t.cust_no and m.enterprise_no=t.enterprise_no and m.owner_no=t.owner_no "+
				" and tmp.enterprise_no='"+enterpriseNo+"' and tmp.warehouse_no='"+strWarehouseNo+"' " +
				" and tmp.tmp_id='"+ip+"' "+
				" group by tmp.line_no,tmp.status,li.line_name,m.enterprise_no, m.cust_no,tmp.owner_no,"+
				" m.warehouse_no, t.cust_name,t.cust_alias,t.cust_phone1,t.cust_address,t.contactor_name1 ";
		}
		//String totsql = "select count(*) from ("+sql+")";
		//Integer count = genDao.getCountByNativeSql(totsql);

		List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql,Odata_ExpMModel.class);
		ExtListDataBo<Odata_ExpMModel> extListBo=new ExtListDataBo<Odata_ExpMModel>(list,0);
		return extListBo;
	}
	
	
	/**
	 * 修改临时表的数据为选中(自动、手动公用)
	 */
	@Override
	public MsgRes updateTmpLocateSelect1(String enterpriseNo,
			String warehouseNo, String strFlag, String calFlag,
			String expNo,String custNo,String expType,String ownerNo)
			throws Exception {
		String ip = HttpService.getIpAddr();
		MsgRes msg2=new MsgRes();

		if(strFlag.equals("1")){//按单
			String sql = "update odata_tmp_locate_select t set t.status='1' " +
					"where t.enterprise_no='"+enterpriseNo+"' and t.warehouse_no='"+warehouseNo+"' " +
							"and t.tmp_id='"+ip+"' ";
			if(!expNo.equals("N")){
				sql += "and t.exp_no='"+expNo+"' ";
			}
			
			genDao.updateBySql(sql);
			//调用资源试算
			if(!calFlag.equals("")&&calFlag.equals("1")){
				msg2 = tsccalculation(enterpriseNo,warehouseNo,ownerNo,expType,
						ip,expNo,"","0","0",calFlag);
				if(!msg2.getIsSucc())
				{
					return new MsgRes(false,msg2.getMsg(),"");
				}
			}
			MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
			return new MsgRes(true,"",msg.getObj());
			
		}else{//按客户
			String sql = "update odata_tmp_locate_select t set t.status='1' "+
			         " where t.enterprise_no='"+enterpriseNo+"' " +
			         "    and t.warehouse_no='"+warehouseNo+"' and t.tmp_id='"+ip+"'  "+
			         "    and t.exp_no in (select m.exp_no from odata_exp_m m "+
			         "       where  t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no and t.exp_no=m.exp_no ";
			         
			if(!custNo.equals("N")){
				sql += " and m.cust_no='"+custNo+"' ";
			}
			sql += "     ) ";
			genDao.updateBySql(sql);
			
			  //调用资源试算
			  if(calFlag.equals("1")){
				  msg2= tsccalculation(enterpriseNo,warehouseNo,ownerNo,expType,
							ip,"",custNo,"0","0",calFlag);
				  if(!msg2.getIsSucc())
					{
						return new MsgRes(false,msg2.getMsg(),"");
					}
			  }
			  MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
			  return new MsgRes(true,"",msg.getObj());
		}
	
	}	
	/**
	 * 修改临时表的数据为不选中(自动、手动公用)
	 */
	@Override
	public MsgRes updateTmpLocateSelect0(String enterpriseNo,
			String warehouseNo, String strFlag, String calFlag,
			String expNo,String custNo,String expType,String ownerNo)
			throws Exception {
		String ip = HttpService.getIpAddr();
		MsgRes msg2=new MsgRes();

		if(strFlag.equals("1")){//按单
			String sql = "update odata_tmp_locate_select t set t.status='0' " +
					"where t.enterprise_no='"+enterpriseNo+"' and t.warehouse_no='"+warehouseNo+"' " +
							"and t.tmp_id='"+ip+"' ";
			
			if(!expNo.equals("N")){
				sql += "and t.exp_no='"+expNo+"' "; 
			}
			genDao.updateBySql(sql);
			msg2=tsccalculation(enterpriseNo,warehouseNo,ownerNo,expType,
					ip,expNo,"","1","0",calFlag);
			if(!msg2.getIsSucc())
			{
				return new MsgRes(false,msg2.getMsg(),"");
			}
			MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
			return new MsgRes(true,"",msg.getObj());
		}else{//按客户
			String sql = "update odata_tmp_locate_select t set t.status='0' "+
			         " where t.enterprise_no='"+enterpriseNo+"' " +
			         "    and t.warehouse_no='"+warehouseNo+"' and t.tmp_id='"+ip+"'  "+
			         "    and t.exp_no in (select m.exp_no from odata_exp_m m "+
			         "       where m.cust_no='"+custNo+"' and t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no and t.exp_no=m.exp_no ";
			
			if(!custNo.equals("N")){
				sql += " and m.cust_no='"+custNo+"' ";
			}
			sql += "     ) ";
			genDao.updateBySql(sql);
			msg2=tsccalculation(enterpriseNo,warehouseNo,ownerNo,expType,
					ip,"",custNo,"1","0",calFlag);
			if(!msg2.getIsSucc())
			{
				return new MsgRes(false,msg2.getMsg(),"");
			}
			MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
			return new MsgRes(true,"",msg.getObj());
		}
	
	}	
	//月台试算(自动、手动公用)
	private MsgRes tsccalculation(String enterpriseNo,String warehouseNo,String ownerNo,
			String expType,String ip,String expNo,String custNo,String delFlag,
			String retryFlag,String calFlag ) throws Exception{
		 List inList=new ArrayList();
	     List outList=new ArrayList();
		 List resultList=new ArrayList();
		 outList.add("S");
		 inList.add(enterpriseNo);
		 inList.add(warehouseNo);
		 inList.add(ownerNo);
		 inList.add(expType);
		 inList.add(ip);
		 inList.add(expNo);
		 inList.add(custNo);
		 inList.add(calFlag);//strSendBufFlag；1是选中；0是没有选中
		 inList.add(delFlag);
		 inList.add(retryFlag);		            

		  System.out.println(inList);
		  resultList = genDao.execProc(inList, outList, "PKLG_BUFFER_CALCULATE.P_DeliverArea_Cal_Tmp");
		  System.out.println(resultList);
		  if(resultList.get(0).toString().indexOf("Y")==-1)
		  {
			//  throw new Exception(resultList.get(0).toString());
			  return new MsgRes(false,resultList.get(0).toString(),"");
		  }
		  return new MsgRes(true,resultList.get(0).toString(),"");
	}
		
		// 获取月台可用货位数v2(自动、手动公用)
		@Override
		public MsgRes tscBufferQty(String enterpriseNo, String warehouseNo,
				String strFlag) throws Exception {
		    String ip = HttpService.getIpAddr();
			String sql = "select count(*) from oset_buffer t " +
					"where t.enterprise_no='"+enterpriseNo+"' " +
					"  and t.warehouse_no='"+warehouseNo+"'  " +
			        "  and t.cell_no not in (select distinct tmp.cell_no from odata_temp_send_area_calculate tmp where tmp.tmp_id='"+ip+"' " +
			        "  and tmp.enterprise_no=t.enterprise_no and tmp.warehouse_no=t.warehouse_no " +
			        "  union select send.cell_no from odata_send_area_calculate send) ";
			List<String> list = genDao.getListByNativeSql(sql);
			return new MsgRes(true,"",list.get(0));
		}	
		//重算月台资源(自动、手动公用)
		public MsgRes tscBackrollres(String enterpriseNo, String warehouseNo,
				String strOwnerNo, String strExpType, String strFlag)
				throws Exception {
		     String ip = HttpService.getIpAddr();
			 List inList=new ArrayList();
		     List outList=new ArrayList();
			 List resultList=new ArrayList();
			 outList.add("S");
			 inList.add(enterpriseNo);
			 inList.add(warehouseNo);
			 inList.add(strOwnerNo);
			 inList.add(strExpType);
			 inList.add(ip);
			 inList.add("");
			 inList.add("");
			 inList.add("0");
			 inList.add("1");		            

			  System.out.println(inList);
			  resultList = genDao.execProc(inList, outList, "PKLG_BUFFER_CALCULATE.P_DeliverArea_Cal_Tmp");
			  System.out.println(resultList);
			  if(resultList.get(0).toString().indexOf("Y")==-1)
			  {
				  throw new Exception(resultList.get(0).toString());
			  }
			  MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
			  return new MsgRes(true,"重算成功",msg.getObj());
		}
		/**
		 * 计算勾选的单据详情(自动、手动公用)
		 */
		@Override
		public List<Odata_ExpMModel> countDetail(String enterpriseNo,String strWarehouseNo) throws Exception {
			String ip = HttpService.getIpAddr();
			String strSql="select sum(t.volume) as sumVolumn," +
					 " sum(t.weight) as sumWeight," +
					 " sum(t.qbox) as sumBoxQty," +
					 " count(distinct m.cust_no) custCount "+
                     " from odata_tmp_locate_select t,odata_exp_m m " +
                     " where t.tmp_id='"+ip+"' and t.status='1' "+
                     " and t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no " +
                     " and t.exp_no=m.exp_no " +
                 	 " and t.warehouse_no='"+strWarehouseNo+"' " +
    				 " and t.enterprise_no='"+enterpriseNo+"' ";
			List<Odata_ExpMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpMModel.class);
			return list;
		}
		
		//删除临时表状态为0的数据(自动、手动公用)
		@Override
		public MsgRes deleteLocateSelect(String enterpriseNo, String warehouseNo)
				throws Exception {
			String ip = HttpService.getIpAddr();
			String sql ="delete odata_tmp_locate_select  t " +
					"where t.status='0' and t.enterprise_no='"+enterpriseNo+"' " +
					"and t.warehouse_no='"+warehouseNo+"' and t.tmp_id='"+ip+"'";
			
			genDao.updateBySql(sql);
			return new MsgRes(true, "", "");
		}
		
		//定位-切批次；集单；定位(自动)
		@Override
		public MsgRes tscFixed(String strDetail) throws Exception {
			Odata_LocateMModel poOl = (Odata_LocateMModel)JSONObject.toBean(
					JSONObject.fromObject(strDetail),Odata_LocateMModel.class);
			String ip = HttpService.getIpAddr();
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			
			inList.add(poOl.getEnterpriseNo());
			inList.add(poOl.getWarehouseNo());
			inList.add(poOl.getOwnerNo());
			inList.add(poOl.getExpType());
			inList.add(poOl.getLocateName());
			inList.add(poOl.getDivideFlag());
			inList.add(poOl.getSendbufCompute());
			inList.add("N");//strSpecifyCell
			inList.add(poOl.getSourceType());//strSourceType
			inList.add(poOl.getStrdivideComputeFlag());//STRDIVIDE_COMPUTE_FLAG
			inList.add(poOl.getStrcheckComputeFlag());//STRCHECK_COMPUTE_FLAG
			inList.add(poOl.getOrgNo());
			inList.add("1");//nTaskBatch
			inList.add("0");//STRATEGY_ID
			inList.add("0");//STRBATCH_RULE_ID
			
		//	inList.add(poOl.getTaskAllotRuleID());//nTask_AllotRuleID 
			inList.add(ip);
			
			System.out.println(inList);	
			resultList = genDao.execProc(inList, outList, "PKLG_ODISPATCH.P_FRONTAUTO_LOCATE");
			System.out.println(resultList);	
			if(resultList.get(0).toString().indexOf("Y")==-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			return new MsgRes(true, "定位成功！","");//定位成功
			
		}
		//批量修改状态(暂未用到)
		@Override
		public MsgRes updateTmpLocateSelectAll(String enterpriseNo,
				String warehouseNo, String strWheresql,String strFlag,String calFlag) throws Exception {
			String ip = HttpService.getIpAddr();
			MsgRes msg2=new MsgRes();
			
			if(strFlag.equals("1")){//按单
				if(strWheresql.equals("0")){//不选中
					String sql = "update odata_tmp_locate_select t set t.status='0' " +
							"where t.enterprise_no='"+enterpriseNo+"' and t.warehouse_no='"+warehouseNo+"' " +
									"and t.tmp_id='"+ip+"' ";
					genDao.updateBySql(sql);
					
					//循环临时表
					String sql11 = "select distinct t.owner_no,t.warehouse_no,t.exp_no," +
							"m.exp_type from odata_tmp_locate_select t,odata_exp_m m " +
							"where t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no " +
							"and t.exp_no=m.exp_no and t.enterprise_no='"+enterpriseNo+"' " +
							"and t.warehouse_no='"+warehouseNo+"' " +
							"and t.tmp_id='"+ip+"' ";
					List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql11,Odata_ExpMModel.class);
                    
					if(list.size()>0){
						for(int i = 0;i<list.size();i++){
							if(!calFlag.equals("")&&calFlag.equals("1")){
								msg2=tsccalculation(enterpriseNo,warehouseNo,list.get(i).getOwnerNo(),
										list.get(i).getExpType(),ip,list.get(i).getExpNo(),"","1","0",calFlag);
								if(!msg2.getIsSucc())
								{
									return new MsgRes(false,msg2.getMsg(),"");
								}
							}
						}
					}
					MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
					return new MsgRes(true,"",msg.getObj());
				}else{
					String sql = "update odata_tmp_locate_select t set t.status='1' " +
							"where t.enterprise_no='"+enterpriseNo+"' and t.warehouse_no='"+warehouseNo+"' " +
									"and t.tmp_id='"+ip+"' ";
					genDao.updateBySql(sql);
					

					//循环临时表
					String sql11 = "select distinct t.owner_no,t.warehouse_no,t.exp_no," +
							"m.exp_type from odata_tmp_locate_select t,odata_exp_m m " +
							"where t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no " +
							"and t.exp_no=m.exp_no and t.enterprise_no='"+enterpriseNo+"' " +
							"and t.warehouse_no='"+warehouseNo+"' " +
							"and t.tmp_id='"+ip+"' ";
					List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql11,Odata_ExpMModel.class);
                    
					if(list.size()>0){
						for(int i = 0;i<list.size();i++){
							//调用资源试算
							if(!calFlag.equals("")&&calFlag.equals("1")){
								msg2 = tsccalculation(enterpriseNo,warehouseNo,list.get(i).getOwnerNo(),
										list.get(i).getExpType(),ip,list.get(i).getExpNo(),
										"","0","0",calFlag);
								if(!msg2.getIsSucc())
								{
									return new MsgRes(false,msg2.getMsg(),"");
								}
							}
						}
					}
					MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
					return new MsgRes(true,"",msg.getObj());
				}
				
			}else{//按客户
				if(strWheresql.equals("0")){//不选中
					String sql = "update odata_tmp_locate_select t set t.status='0' "+
					         " where t.enterprise_no='"+enterpriseNo+"' " +
					         "    and t.warehouse_no='"+warehouseNo+"' and t.tmp_id='"+ip+"' ";
					genDao.updateBySql(sql);
					//循环临时表
					String sql11 = "select distinct t.owner_no,t.warehouse_no,t.exp_no," +
							"m.exp_type from odata_tmp_locate_select t,odata_exp_m m " +
							"where t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no " +
							"and t.exp_no=m.exp_no and t.enterprise_no='"+enterpriseNo+"' " +
							"and t.warehouse_no='"+warehouseNo+"' " +
							"and t.tmp_id='"+ip+"' ";
					List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql11,Odata_ExpMModel.class);
                    
					if(list.size()>0){
						for(int i = 0;i<list.size();i++){
							//调用资源试算
							if(!calFlag.equals("")&&calFlag.equals("1")){
								msg2=tsccalculation(enterpriseNo,warehouseNo,list.get(i).getOwnerNo(),
										list.get(i).getExpType(),
										ip,"",list.get(i).getCustNo(),"1","0",calFlag);
								if(!msg2.getIsSucc())
								{
									return new MsgRes(false,msg2.getMsg(),"");
								}
							}
						}
					}
					
					
					MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
					return new MsgRes(true,"",msg.getObj());
				}else{
					String sql = "update odata_tmp_locate_select t set t.status='1' "+
					         " where t.enterprise_no='"+enterpriseNo+"' " +
					         "    and t.warehouse_no='"+warehouseNo+"' and t.tmp_id='"+ip+"'  ";
					genDao.updateBySql(sql);
					//循环临时表
					String sql11 = "select distinct t.owner_no,t.warehouse_no,t.exp_no," +
							"m.exp_type from odata_tmp_locate_select t,odata_exp_m m " +
							"where t.enterprise_no=m.enterprise_no and t.warehouse_no=m.warehouse_no " +
							"and t.exp_no=m.exp_no and t.enterprise_no='"+enterpriseNo+"' " +
							"and t.warehouse_no='"+warehouseNo+"' " +
							"and t.tmp_id='"+ip+"' ";
					List<Odata_ExpMModel> list = genDao.getListByNativeSql(sql11,Odata_ExpMModel.class);
                    
					if(list.size()>0){
						for(int i = 0;i<list.size();i++){
							//调用资源试算
							if(!calFlag.equals("")&&calFlag.equals("1")){
								  msg2= tsccalculation(enterpriseNo,warehouseNo,list.get(i).getOwnerNo(),
											list.get(i).getExpType(),
											ip,"",list.get(i).getCustNo(),"0","0",calFlag);
								  if(!msg2.getIsSucc())
									{
										return new MsgRes(false,msg2.getMsg(),"");
									}
							}
						}
					}
					  MsgRes msg = tscBufferQty(enterpriseNo,warehouseNo,"");
					  return new MsgRes(true,"",msg.getObj());
					
				}
			
			}
		}
		/**
		 * 根据筛选的条件写临时表（手动公用）
		 * B2Cflag.1传统；2电商
		 */
		@Override
		public MsgRes saveLocateSelectTem_manual(String enterpriseNo,
				String warehouseNo, String strWheresql, String strFlag,String B2Cflag)
				throws Exception {
				String strSql="";
				String b2c="";
				String ip = HttpService.getIpAddr();
			    //插入前先删除临时表的数据
				String sql ="delete odata_tmp_locate_select t " +
						"where t.tmp_id='"+ip+"' and t.enterprise_no='"+enterpriseNo+"' " +
						"and t.warehouse_no='"+warehouseNo+"'"; 
				genDao.updateBySql(sql);
				if(B2Cflag!=null && B2Cflag.equals("2")){
					b2c="1";//默认选中
				}else{
					b2c="0";
				}
				
				strSql="insert into odata_tmp_locate_select " +
						"(tmp_id,warehouse_no,owner_no,line_no,batch_no,exp_no,volume,weight,qbox,enterprise_no,status)  "+
						"select '"+ip+"',a.warehouse_no,a.owner_no, " +
						"case when vcl.LINE_NO is null then 'N' else vcl.LINE_NO end line_no," +
						"case when vcl.BATCH_NO is null then 'N' else vcl.BATCH_NO end batch_no," +
						"a.exp_no," +
						"sum(b.article_qty * d.unit_volumn) volume," +
						"sum(b.article_qty * d.unit_weight) weight," +
						"sum(b.article_qty/b.packing_qty) qbox,a.enterprise_no,'"+b2c+"' " +
						"from odata_exp_m a,odata_exp_d b,bdef_defarticle d," +
						"v_cust_line_batch_cycle vcl,tmp_odata_exp_m m " +
						"where a.exp_no=b.exp_no " +
						"and a.warehouse_no=b.warehouse_no " +
						"and a.enterprise_no=b.enterprise_no "+
						"and b.enterprise_no=d.enterprise_no "+
						"and a.status='10' " +
						"and b.article_no=d.article_no " +
						"and a.cust_no=vcl.CUST_NO(+) " +
						"and a.warehouse_no=vcl.WAREHOUSE_NO(+) " +
						"and a.enterprise_no=vcl.enterprise_no(+) " +
						"and a.enterprise_no=m.enterprise_no " +
						"and a.warehouse_no=m.warehouse_no " +
						"and a.exp_no=m.exp_no "+
						"and a.warehouse_no='"+warehouseNo+"' "+
						"and a.enterprise_no='"+enterpriseNo+"' " ;
				if(strWheresql!=null && !strWheresql.equals(""))
				{
					String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
					strSql=strSql+ft;
				}
				strSql+="group by a.enterprise_no,a.warehouse_no,a.owner_no," +
						"a.exp_no,vcl.line_no,vcl.batch_no  " +
				"order by a.enterprise_no,a.warehouse_no,a.exp_no ";
				genDao.updateBySql(strSql);
				
				return new MsgRes(true,"","");
		}			
	
}
