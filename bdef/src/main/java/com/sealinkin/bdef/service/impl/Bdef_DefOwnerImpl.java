package com.sealinkin.bdef.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.po.Bdef_DefOwner;
import com.sealinkin.bdef.service.IBdef_DefOwnerService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Bdef_DefOwnerImpl implements IBdef_DefOwnerService{
	
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//获得货主资料
	@Override
	public ExtListDataBo<Bdef_DefOwnerModel> getBdef_DefOwner(String enterpriseNo,
			String strWorkerOwner,String strWorkerNo,String strQuery, PageBo pageBo,
			Integer requestFlag) throws Exception {
	String strSql="select distinct a.rsv_var2, a.rsv_var3, a.OWNER_NO,"
				+"a.OWNER_NAME,"
				+"a.OWNER_ALIAS,"
				+"a.OWNER_ADDRESS,"
				+"a.OWNER_PHONE,"
				+"a.OWNER_FAX,"
				+"a.OWNER_CONTACT,"
				+"a.OWNER_REMARK,"
				+"a.INVOICE_NO,"
				+"a.INVOICE_ADDR,"
				+"a.INVOICE_HEADER,"
				+"a.STATUS,"
				+"a.FIXEDCELL_FLAG,"
				+"a.AUTHORITY_TYPE,"
				+"a.turn_over_rule,"
				+"a.I_STRATEGY,"
				+"a.O_STRATEGY,"
				+"a.M_STRATEGY,"
				+"a.RI_STRATEGY,"
				+"a.RO_STRATEGY,"
				+"a.FC_STRATEGY," 
				+"a.RSV_STRATEGY1,"
				+"a.RSV_STRATEGY2,"
				+"a.RSV_STRATEGY3,"
				+"a.RSV_STRATEGY4,"
				+"a.RSV_STRATEGY5,"
				+"a.RSV_STRATEGY6,"
				+"a.RGST_NAME,"
				+"a.RGST_DATE,"
				+"a.UPDT_NAME,"
				+"a.UPDT_DATE,"
				+"a.SCAN_FLAG," 
				+"a.cell_manager_type," 
				+"a.type_value," 
				+"b.cell_manager_type wcellManagerType," 
				+"b.type_value wtypeValue," 
				+"f_get_fieldtext('BDEF_DEFOWNER','CELL_MANAGERT_TYPE',a.cell_manager_type) cellType,"
				+"f_get_fieldtext('N','DEF_STATUS',a.STATUS) STATUSTEXT,"
				+"f_get_fieldtext('N','SCAN_FLAG',a.SCAN_FLAG) SCAN_FLAGTEXT,"
				+"f_get_fieldtext('BDEF_DEFOWNER','FIXEDCELL_FLAG',a.FIXEDCELL_FLAG) FIXEDCELL_FLAGTEXT,"
				+"f_get_fieldtext('BDEF_DEFOWNER','AUTHORITY_TYPE',a.AUTHORITY_TYPE) AUTHORITY_TYPETEXT,"
				+"f_get_fieldtext('N','I_STRATEGY',a.I_STRATEGY) I_STRATEGYTEXT,"
				+"f_get_fieldtext('N','O_STRATEGY',a.O_STRATEGY) O_STRATEGYTEXT,"
				+"f_get_fieldtext('N','M_STRATEGY',a.M_STRATEGY) M_STRATEGYTEXT,"
				+"f_get_fieldtext('N','RI_STRATEGY',a.RI_STRATEGY) RI_STRATEGYTEXT,"
				+"f_get_fieldtext('N','RO_STRATEGY',a.RO_STRATEGY) RO_STRATEGYTEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','FC_STRATEGY',a.FC_STRATEGY) FC_STRATEGYTEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','RSV_STRATEGY1',a.RSV_STRATEGY1) RSV_STRATEGY1TEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','RSV_STRATEGY2',a.RSV_STRATEGY2) RSV_STRATEGY2TEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','RSV_STRATEGY3',a.RSV_STRATEGY3) RSV_STRATEGY3TEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','RSV_STRATEGY4',a.RSV_STRATEGY4) RSV_STRATEGY4TEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','RSV_STRATEGY5',a.RSV_STRATEGY5) RSV_STRATEGY5TEXT,"
				+"f_get_fieldtext('BDEF_ARTICLE_GROUP','RSV_STRATEGY5',a.RSV_STRATEGY6) RSV_STRATEGY6TEXT "
				+"from bdef_defowner a,bset_owner_cell_manage b where " +
				" a.enterprise_no = b.enterprise_no(+) and a.owner_no = b.owner_no(+) and" +
				" a.enterprise_no='"+enterpriseNo+"' " ;
//        String strTotSql = "select count(1) " +
//        		"from bdef_defowner a where enterprise_no='"+enterpriseNo+"' " ;			
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
//			strTotSql=strTotSql+ws;
		}
		
		if(strWorkerOwner!=null && !strWorkerOwner.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strWorkerOwner+") ";
//			strTotSql=strTotSql+" and a.owner_no in("+strWorkerOwner+")";
		}
		else{
			strSql=strSql+" and 1=2";
//			strTotSql=strTotSql+" and 1=2";
		}
		strSql += " order by a.owner_no ";
		String strTotSql = "select count(*) from (" + strSql + ") " ;	
		List<Bdef_DefOwnerModel> list = null;
		Integer intCount = 0;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(strSql,Bdef_DefOwnerModel.class,pageBo.getStart(), pageBo.getPagesize());
			intCount = genDao.getCountByNativeSql(strTotSql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(strSql,Bdef_DefOwnerModel.class);
			intCount = list.size();
		}
		ExtListDataBo<Bdef_DefOwnerModel> extListBo= new ExtListDataBo<Bdef_DefOwnerModel>(list, intCount);
        return extListBo;
	}

	/**
	 * 保存或修改货主
	 * @param strW
	 * @return
	 * @throws Exception
	 */

	@Override
	public MsgRes saveOrUpdateOwner(String strBo, String strWorkNo,String strWareHouseNO,String strWcellManagerType,String strWtypeValue)
			throws Exception {
		
		String strSql="";
		Bdef_DefOwner ownerBo=(Bdef_DefOwner)JSONObject.toBean(JSONObject.fromObject(strBo),Bdef_DefOwner.class);
		strSql="select * from bdef_defowner a where a.owner_no='"+ownerBo.getId().getOwnerNo()+"' " +
				"and enterprise_no='"+ownerBo.getId().getEnterpriseNo()+"'";
		List<Bdef_DefOwner> list=genDao.getListByNativeSql(strSql, Bdef_DefOwner.class);
		if(list.size()==0){
			ownerBo.setRgstDate(new Date());
			ownerBo.setRgstName(strWorkNo);
		}else{
			ownerBo.setRgstDate(list.get(0).getRgstDate());
			ownerBo.setRgstName(list.get(0).getRgstName());
			ownerBo.setUpdtDate(new Date());
			ownerBo.setUpdtName(strWorkNo);
			//更新商品类别
			strSql="update bdef_article_group a set "
					+"a.i_strategy=(case when a.i_strategy='"+list.get(0).getIStrategy()+"' then '"+ownerBo.getIStrategy()+"' else a.i_strategy end),"
					+"a.o_strategy=(case when a.o_strategy='"+list.get(0).getOStrategy()+"' then '"+ownerBo.getOStrategy()+"' else a.o_strategy end),"
					+"a.m_strategy=(case when a.m_strategy='"+list.get(0).getMStrategy()+"' then '"+ownerBo.getMStrategy()+"' else a.m_strategy end),"
					+"a.ri_strategy=(case when a.ri_strategy='"+list.get(0).getRiStrategy()+"' then '"+ownerBo.getRiStrategy()+"' else a.ri_strategy end),"
					+"a.ro_strategy=(case when a.ro_strategy='"+list.get(0).getRoStrategy()+"' then '"+ownerBo.getRoStrategy()+"' else a.ro_strategy end),"
					+"a.fc_strategy=(case when a.fc_strategy='"+list.get(0).getFcStrategy()+"' then '"+ownerBo.getFcStrategy()+"' else a.fc_strategy end),"
					+"a.rsv_strategy1=(case when a.rsv_strategy1='"+list.get(0).getRsvStrategy1()+"' then '"+ownerBo.getRsvStrategy1()+"' else a.rsv_strategy1 end),"
					+"a.rsv_strategy2=(case when a.rsv_strategy2='"+list.get(0).getRsvStrategy2()+"' then '"+ownerBo.getRsvStrategy2()+"' else a.rsv_strategy2 end),"
					+"a.rsv_strategy3=(case when a.rsv_strategy3='"+list.get(0).getRsvStrategy3()+"' then '"+ownerBo.getRsvStrategy3()+"' else a.rsv_strategy3 end),"
					+"a.rsv_strategy4=(case when a.rsv_strategy4='"+list.get(0).getRsvStrategy4()+"' then '"+ownerBo.getRsvStrategy4()+"' else a.rsv_strategy4 end),"
					+"a.rsv_strategy5=(case when a.rsv_strategy5='"+list.get(0).getRsvStrategy5()+"' then '"+ownerBo.getRsvStrategy5()+"' else a.rsv_strategy5 end),"
					+"a.rsv_strategy6=(case when a.rsv_strategy6='"+list.get(0).getRsvStrategy6()+"' then '"+ownerBo.getRsvStrategy6()+"' else a.rsv_strategy6 end),"
					+"a.turn_over_rule=(case when a.turn_over_rule='"+list.get(0).getTurnOverRule()+"' then '"+ownerBo.getTurnOverRule()+"' else a.turn_over_rule end)"
					+"where a.owner_no='"+ownerBo.getId().getOwnerNo()+"' " 
					+" and enterprise_no='"+ownerBo.getId().getEnterpriseNo()+"'";
			genDao.updateBySql(strSql);
			
			//更新商品信息
			strSql="update bdef_defarticle a set "
					+"a.i_strategy=(case when a.i_strategy='"+list.get(0).getIStrategy()+"' then '"+ownerBo.getIStrategy()+"' else a.i_strategy end),"
					+"a.o_strategy=(case when a.o_strategy='"+list.get(0).getOStrategy()+"' then '"+ownerBo.getOStrategy()+"' else a.o_strategy end),"
					+"a.m_strategy=(case when a.m_strategy='"+list.get(0).getMStrategy()+"' then '"+ownerBo.getMStrategy()+"' else a.m_strategy end),"
					+"a.ri_strategy=(case when a.ri_strategy='"+list.get(0).getRiStrategy()+"' then '"+ownerBo.getRiStrategy()+"' else a.ri_strategy end),"
					+"a.ro_strategy=(case when a.ro_strategy='"+list.get(0).getRoStrategy()+"' then '"+ownerBo.getRoStrategy()+"' else a.ro_strategy end),"
					+"a.fc_strategy=(case when a.fc_strategy='"+list.get(0).getFcStrategy()+"' then '"+ownerBo.getFcStrategy()+"' else a.fc_strategy end),"
					+"a.rsv_strategy1=(case when a.rsv_strategy1='"+list.get(0).getRsvStrategy1()+"' then '"+ownerBo.getRsvStrategy1()+"' else a.rsv_strategy1 end),"
					+"a.rsv_strategy2=(case when a.rsv_strategy2='"+list.get(0).getRsvStrategy2()+"' then '"+ownerBo.getRsvStrategy2()+"' else a.rsv_strategy2 end),"
					+"a.rsv_strategy3=(case when a.rsv_strategy3='"+list.get(0).getRsvStrategy3()+"' then '"+ownerBo.getRsvStrategy3()+"' else a.rsv_strategy3 end),"
					+"a.rsv_strategy4=(case when a.rsv_strategy4='"+list.get(0).getRsvStrategy4()+"' then '"+ownerBo.getRsvStrategy4()+"' else a.rsv_strategy4 end),"
					+"a.rsv_strategy5=(case when a.rsv_strategy5='"+list.get(0).getRsvStrategy5()+"' then '"+ownerBo.getRsvStrategy5()+"' else a.rsv_strategy5 end),"
					+"a.rsv_strategy6=(case when a.rsv_strategy6='"+list.get(0).getRsvStrategy6()+"' then '"+ownerBo.getRsvStrategy6()+"' else a.rsv_strategy6 end),"
					+"a.turn_over_rule=(case when a.turn_over_rule='"+list.get(0).getTurnOverRule()+"' then '"+ownerBo.getTurnOverRule()+"' else a.turn_over_rule end)"
					+"where a.owner_no='"+ownerBo.getId().getOwnerNo()+"' " 
					+" and a.enterprise_no='"+ownerBo.getId().getEnterpriseNo()+"'";
			genDao.updateBySql(strSql);
		}
		genDao.saveOrUpdateObj(ownerBo);
		
		if(!strWcellManagerType.equals(""))
		{
			//刷bset_owner_cell_manage表记录 Add by sunl 2016年8月3日
			String strSql0 = "";
			//先校验当前的配置在bset_owner_cell_manage表是否存在
			String strSql1 = "select m.owner_no from bset_owner_cell_manage m " +
					" where m.enterprise_no = '" +ownerBo.getId().getEnterpriseNo() +
					"' AND m.owner_no = '" +ownerBo.getId().getOwnerNo() + 
					"' AND m.warehouse_no='"+strWareHouseNO+"'";
	
			List<Bdef_DefOwner> list1 = genDao.getListByNativeSql(strSql1, Bdef_DefOwner.class);
			if(list1.size() == 0)
			{
				//不存在，新增
				strSql0 = "INSERT INTO bset_owner_cell_manage(enterprise_no, owner_no, warehouse_no," +
						" cell_manager_type, type_value, rgst_name, rgst_date) " +
						" VALUES ('" +ownerBo.getId().getEnterpriseNo() + 
						 "','" +ownerBo.getId().getOwnerNo() + 
						 "','"+strWareHouseNO +
						 "','"+strWcellManagerType+
						 "','"+strWtypeValue+
						 "','"+strWorkNo+"',SYSDATE)";
			}
			else
			{
				//存在，更新
				strSql0 = "UPDATE bset_owner_cell_manage m SET " +
						"m.cell_manager_type = '"+strWcellManagerType+
						"',m.type_value = '"+strWtypeValue+
						"',m.updt_name='"+strWorkNo+"',m.updt_date=SYSDATE " +
						" WHERE m.enterprise_no = '" +ownerBo.getId().getEnterpriseNo() + 
						"' AND m.owner_no = '" +ownerBo.getId().getOwnerNo() + 
						"' AND m.warehouse_no='"+strWareHouseNO+"'";
			}
			genDao.updateBySql(strSql0);
		}
		return new MsgRes(true,"savesuccess",null);
	}


	/**
	 * 获取货主下拉数据	
	 * @param strWorkerOwner
	 * @param strW
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ComboxBo> getBdef_DefOwnerCombo(String strWorkerOwner,
			String strW) throws Exception {
		return null;
	}

	/**
	 * 新增时检查货主编码是否存在
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */

	@Override
	public MsgRes existsOwnerNo(String enterpriseNo,String strOwnerNo) throws Exception {
		String strSql="select * from bdef_defowner a where a.owner_no='"+strOwnerNo+"' "+
				      " and enterprise_no='"+enterpriseNo+"'";
		List<Bdef_DefOwner> list=genDao.getListByNativeSql(strSql, Bdef_DefOwner.class);
		if(list.size()!=0)
		{
			return new MsgRes(false,"no_exists",null);
		}
		return new MsgRes(true,"",null);
	}
	
	/**
	 * 填充货主下拉
	 */
	
	@Override
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String strOwnerNo, String strFlag,
			String strWheresql) throws Exception {
		String strSql="select a.* from  (select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.status='1' ";
		
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql = strSql+"order by t1.owner_no)a union all select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from dual " ;
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 填充承运商下拉
	 */
	@Override
	public List<ComboxBo> queryShipperCombo(String enterpriseNo,String strOwnerNo, String strFlag,
			String strWheresql) throws Exception {
		String strSql="select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from bdef_defshipper t1 union " +
				"select t1.SHIPPER_NO value,t1.SHIPPER_NAME text," +
				"'['|| ltrim(t1.SHIPPER_NO)||']'||t1.SHIPPER_NAME dropValue " +
				"from bdef_defshipper t1 where t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.status='1' ";
		
		/*if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}*/
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	@Override
	public MsgRes deleteOwner(String currEnterpriseNo, String str)
			throws Exception {
		String sql=" select a.article_no from bdef_defarticle a " +
				   "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "    and a.owner_no='"+str+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该货主不可删除","");
		}
		
		String deleteSql=" delete from bdef_defowner a " +
				         "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				         "    and a.owner_no='"+str+"' ";
		genDao.updateBySql(deleteSql);
		
		//删除bset_owner_cell_manage表 Add by sunl 2016年8月3日
		String deleteSql0=" delete from bset_owner_cell_manage a " +
				         "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				         "    and a.owner_no='"+str+"' ";
		genDao.updateBySql(deleteSql0);
		return new MsgRes(true,"删除成功","");
	}

	//判断是否可以修改货主状态
	@Override
	public MsgRes isCanChange(String currEnterpriseNo,String warehouseNo, String str)
			throws Exception {
		String sql=" select distinct a.owner_no from stock_content a " +
				   "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' " +
				   "    and a.owner_no='"+str+"' ";
		
		List list = genDao.getListByNativeSql(sql);
		if(list.size()>0){
			return new MsgRes(false,"","");
		}
		return new MsgRes(true,"","");
	}
	//填充状态下拉
	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,String strOwnerNo,String strQuery)throws Exception {
		String strSql="select distinct a.status value,a.status text,"+
				" f_get_fieldtext('N','DEF_STATUS',a.status) dropValue "+
				" from bdef_defowner a " + 
				"where 1=1 " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				// "and a.warehouse_no='"+strWarehouseNo+"' " +
			     "and a.owner_no in("+strOwnerNo+") ";
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql + ws;
		}
			
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
	
}
