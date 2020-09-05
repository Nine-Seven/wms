package com.sealinkin.odata.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.odata.model.odata_WmsOutlocateStrategyDModel;
import com.sealinkin.odata.model.odata_WmsOutlocateStrategyMModel;
import com.sealinkin.odata.po.odata_WmsOutlocateStrategyM;
import com.sealinkin.odata.po.odata_WmsOutlocateStrategyD;
import com.sealinkin.odata.service.IOdata_outLocateStrategyService;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Odata_outLocateStrategyImpl implements IOdata_outLocateStrategyService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取策略头档列表   (已修改 成功)
	@Override
	public ExtListDataBo<odata_WmsOutlocateStrategyMModel> getOutLocateStrategyMList(
			String strQuery, PageBo pageBo) throws Exception {
		String strSql="select a.enterprise_no,a.locate_strategy_id,a.strategy_name," +
				      "a.defalut_flag,a.rgst_name,a.rgst_date, " +
		                " '['|| ltrim(a.defalut_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','DEFALUT_FLAG',a.defalut_flag)defalutFlagText "+
		              "from wms_outlocate_strategy_m a " +
		              "where 1=1 ";
	
	    if(strQuery!=null && !strQuery.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		    strSql=strSql+strWs;
	    }
	
	    strSql+=" order by a.locate_strategy_id ";
	    List<odata_WmsOutlocateStrategyMModel> list = genDao.getListByNativeSql(strSql,odata_WmsOutlocateStrategyMModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	    ExtListDataBo<odata_WmsOutlocateStrategyMModel> extListBo= new ExtListDataBo<odata_WmsOutlocateStrategyMModel>(list, intCount);
	    return extListBo;
    }
	//获取策略明细列表 (已修改 成功)
	@Override
	public ExtListDataBo<odata_WmsOutlocateStrategyDModel> getOutLocateStrategyDList1(
			String strQuery, PageBo pageBo) throws Exception {                                  //
		String strSql="select a.*, " +
				" '['|| ltrim(a.deliver_Obj_Level)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','DELIVER_OBJ_LEVEL',a.deliver_Obj_Level)deliverObjLevelText, "+
                " '['|| ltrim(a.p_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','P_FLAG',a.p_flag)PFlagText, "+
                " '['|| ltrim(a.w_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','W_FLAG',a.w_flag)WFlagText, "+
                " '['|| ltrim(a.m_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','M_FLAG',a.m_flag)MFlagText, "+
                " '['|| ltrim(a.s_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','S_FLAG',a.s_flag)SFlagText, "+
                " '['|| ltrim(a.d_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','D_FLAG',a.d_flag)DFlagText, "+
                " '['|| ltrim(a.commit_type)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','COMMIT_TYPE',a.commit_type)commitTypeText, "+
                " '['|| ltrim(a.shortqty_type)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','SHORTQTY_TYPE',a.shortqty_type)shortqtyTypeText "+
                "from wms_outlocate_strategy_d a, wms_outlocate_strategy_m b " +
	              "where a.locate_strategy_id=b.locate_strategy_id " ;

        if(strQuery!=null && !strQuery.equals(""))
        {
	         String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
	         strSql=strSql+strWs;
        }

        strSql+=" order by a.locate_strategy_id";
        List<odata_WmsOutlocateStrategyDModel> list = genDao.getListByNativeSql(strSql,odata_WmsOutlocateStrategyDModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
        Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
        ExtListDataBo<odata_WmsOutlocateStrategyDModel> extListBo= new ExtListDataBo<odata_WmsOutlocateStrategyDModel>(list, intCount);
        return extListBo;
    }
	//获取UI策略名下拉  (成功)
	@Override
	public List<ComboxBo> getStrategyNameForUI(String strQuery)
			throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_outlocate_strategy_m "+
				"union "+
				"select distinct a.strategy_name value,a.strategy_name text," +	
				"'['|| ltrim(a.strategy_name)||']'||f_get_fieldtext('OUT_LOCATA_STRATEGY','STRATEGY_NAME',a.strategy_name) dropValue " +  //里面码表要改
				"from wms_outlocate_strategy_m a " +
				"where 1=1 " ;
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
    }
	//获取UI默认标识下拉(cg)
	@Override
	public List<ComboxBo> getDefaultFlagForUI(String strQuery) throws Exception {
		String strSql="select distinct a.defalut_flag value,a.defalut_flag text," +	
				"'['|| ltrim(a.defalut_flag)||']'||f_get_fieldtext('OUT_LOCATE_STRATEGY','DEFALUT_FLAG',a.defalut_flag) dropValue " +  //里面码表要改
				"from wms_outlocate_strategy_m a " +
				"where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
        {
	         String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
	         strSql=strSql+strWs;
        }
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
	}
	//获取UI策略id下拉(cg)
	@Override
	public List<ComboxBo> getStrategyId(String strQuery, String str)
			throws Exception {
		String sql = "select distinct  a.locate_strategy_id value ,a.strategy_name text," +
			     "'['|| ltrim(a.locate_strategy_id)||']'||a.strategy_name dropValue " +
	             "from wms_outlocate_strategy_m a "+
               "where 1=1 ";
		
		 // String sql = "select distinct  a.locate_strategy_id value ," +
			//    "'['|| ltrim(a.locate_strategy_id)||']' dropValue " +
	        //     "from wms_outlocate_strategy_m a "+
            //    "where 1=1 ";
		 
       if (strQuery != null && !strQuery.equals("")) {
	      String ws = CommUtil.covtCollectionToWhereSql(strQuery);
	      sql = sql + ws;
       }
       if (str != null && !str.equals("")) {
 	      sql = sql + "and ( a.locate_strategy_id like '%" + str + "%' " +
 					"or a.strategy_name like '%" + str + "%') ";
        }
       
      // if (str != null && !str.equals("")) {
	   //  sql = sql + "and  a.locate_strategy_id like '%" + str + "%' ";
     //}
       
       List list = genDao.getListByNativeSql(sql, ComboxBo.class);
       return (List<ComboxBo>) list;
    }
	//保存策略头档(cg)
	@Override
	public MsgRes saveStrategy(String strQuery) throws Exception {
		odata_WmsOutlocateStrategyM strategy=(odata_WmsOutlocateStrategyM)JSON.parseObject(strQuery,odata_WmsOutlocateStrategyM.class);
		Integer locateStrategyId=0;
		if(strategy.getId().getLocateStrategyId()==null || strategy.getId().getLocateStrategyId().equals(""))
        {
			String sql="select nvl(max(a.locate_strategy_id),0) from wms_outlocate_strategy_m a where 1=1 " ;
			List list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				locateStrategyId =(int) (Double.parseDouble(list.get(0).toString())+1) ;
			}
        }else{
        	locateStrategyId=strategy.getId().getLocateStrategyId();
        }
		strategy.getId().setLocateStrategyId(locateStrategyId);
		genDao.saveOrUpdateObj(strategy);	
		return new MsgRes(true, "数据保存成功！", locateStrategyId);
	}
	//删除策略头档(cg)
	@Override
	public MsgRes deleteStrategy(String strQuery) throws Exception {
		String sql=" select a.locate_strategy_id from wms_outlocate_strategy_d a " +
				   "  where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+strWs;
		}
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该策略已有明细，不能删除","");
		}
		String deleteSql="delete from wms_outlocate_strategy_m a where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
			{
				String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
				deleteSql=deleteSql+strWs;
			}
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	//获取策略明细窗口规则ID下拉
	@Override
	public List<ComboxBo> getRuleIdQuery(String strQuery,String str)
		throws Exception {
		String strSql="select distinct a.locate_rule_id value,a.strategy_name text," +	
					"'['|| ltrim(a.locate_rule_id)||']'||a.strategy_name dropValue " +
					"from wms_outlocate_strategy_d a " +
					"where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
			 {
				 String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
				 strSql=strSql+strWs;
			 }
//		String strSql2="and a.rule_id not in(select a.rule_id from wms_defstrategy_d a where 1=1 ";
//		if(str!=null && !str.equals(""))
//		 {
//			 String Ws=CommUtil.covtCollectionToWhereSql(str);
//			 strSql2=strSql2+Ws;
//		 }
//		strSql2 +=" ) ";
		strSql += "  order by a.locate_rule_id ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//保存策略明细
	@Override
	public MsgRes saveStrategyD(String strQuery) throws Exception {
		odata_WmsOutlocateStrategyD strategyD=(odata_WmsOutlocateStrategyD)JSON.parseObject(strQuery,odata_WmsOutlocateStrategyD.class);
		Integer locateStrategyId=0;
		if(strategyD.getId().getLocateStrategyId()==null || strategyD.getId().getLocateStrategyId().equals(""))
        {
			String sql="select nvl(max(a.locate_strategy_id),0) from wms_outlocate_strategy_d a where 1=1 " +
					"and a.enterprise_no='"+strategyD.getId().getEnterpriseNo()+"' " +
					"and a.locate_rule_id='"+strategyD.getId().getLocateRuleId()+"' ";
			List list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				locateStrategyId =(int) Double.parseDouble(list.get(0).toString())+1 ;
			}
        }else{
        	locateStrategyId=strategyD.getId().getLocateStrategyId();
        }
		strategyD.getId().setLocateStrategyId(locateStrategyId);
		genDao.saveOrUpdateObj(strategyD);	
		return new MsgRes(true, "数据保存成功！", locateStrategyId);  
	}  
	//删除策略明细
	@Override
	public MsgRes deleteStrategyD(String strQuery) throws Exception {

		String deleteSql="delete from wms_outlocate_strategy_d a where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
		 {
			 String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			 deleteSql=deleteSql+strWs;
		 }
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	
	
	@Override
	public ExtListDataBo<odata_WmsOutlocateStrategyDModel> getOutLocateStrategyDList(
			String strQuery, PageBo pageBo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
