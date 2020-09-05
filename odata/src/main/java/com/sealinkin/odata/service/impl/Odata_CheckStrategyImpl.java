package com.sealinkin.odata.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.odata.model.Odata_WmsCheckStrategyModel;
import com.sealinkin.odata.po.Odata_WmsCheckStrategy;
import com.sealinkin.odata.service.IOdata_CheckStrategyService;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Odata_CheckStrategyImpl implements IOdata_CheckStrategyService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取策略头档列表   ()
	@Override   //码表要改
	public ExtListDataBo<Odata_WmsCheckStrategyModel> getCheckStrategyList(
			String strQuery, PageBo pageBo) throws Exception {
		String strSql="select a.*, " +
				" '['|| ltrim(a.check_type)||']'||f_get_fieldtext('CHECK_STRATEGY','CHECK_TYPE',a.check_type)checkTypeText, "+
                " '['|| ltrim(a.skip_pick_flag)||']'||f_get_fieldtext('N','ALLOW',a.skip_pick_flag)skipPickFlagText, "+
                " '['|| ltrim(a.check_level)||']'||f_get_fieldtext('CHECK_STRATEGY','CHECK_LEVEL',a.check_level)checkLevelText, "+
                " '['|| ltrim(a.auto_close_box)||']'||f_get_fieldtext('N','AUTO',a.auto_close_box)autoCloseBoxText, "+
                " '['|| ltrim(a.pack_advance)||']'||f_get_fieldtext('CHECK_STRATEGY','PACK_ADVANCE',a.pack_advance)packAdvanceText, "+
                " '['|| ltrim(a.weight_flag)||']'||f_get_fieldtext('N','SCAN_FLAG',a.weight_flag)weightFlagText, "+
                " '['|| ltrim(a.volum_flag)||']'||f_get_fieldtext('N','SCAN_FLAG',a.volum_flag)volumFlagText "+
		              "from wms_check_strategy a " +
		              "where 1=1 ";
	
	    if(strQuery!=null && !strQuery.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		    strSql=strSql+strWs;
	    }
	
	    strSql+=" order by a.check_strategy_id ";
	    List<Odata_WmsCheckStrategyModel> list = genDao.getListByNativeSql(strSql,Odata_WmsCheckStrategyModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	    ExtListDataBo<Odata_WmsCheckStrategyModel> extListBo= new ExtListDataBo<Odata_WmsCheckStrategyModel>(list, intCount);
	    return extListBo;
    }
	
	//获取策略类型下拉(修改中)
	@Override
	public List<ComboxBo> getCheckTypeForUI(String strQuery)
			throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_check_strategy "+
				"union "+
				"select distinct a.check_type value,a.check_type text," +	
				"'['|| ltrim(a.check_type)||']'||f_get_fieldtext('CHECK_STRATEGY','CHECK_TYPE',a.check_type) dropValue " +  //里面码表要改
				"from wms_check_strategy a " +
				"where 1=1 " ;
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
    }
	//获取UI复核级别下拉(修改中)
	@Override
	public List<ComboxBo> getCheckLevelForUI(String strQuery) throws Exception {
		String strSql="select distinct a.check_level value,a.check_level text," +	
				"'['|| ltrim(a.check_level)||']'||f_get_fieldtext('CHECK_STRATEGY','CHECK_LEVEL',a.check_level) dropValue " +  //里面码表要改
				"from wms_check_strategy a " +
				"where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
        {
	         String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
	         strSql=strSql+strWs;
        }
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
	}
	//获取UI策略id下拉(修改中)
	@Override
	public List<ComboxBo> getCheckStrategyIdAndName(String strQuery, String str)
			throws Exception {
		String sql = "select distinct  a.check_strategy_id value ,a.check_strategy_name text," +
			     "'['|| ltrim(a.check_strategy_id)||']'||a.check_strategy_name dropValue " +
	             "from wms_check_strategy a "+
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
 	      sql = sql + "and ( a.check_strategy_id like '%" + str + "%' " +
 					"or a.check_strategy_name like '%" + str + "%') ";
        }
       
      // if (str != null && !str.equals("")) {
	   //  sql = sql + "and  a.locate_strategy_id like '%" + str + "%' ";
     //}
       
       List list = genDao.getListByNativeSql(sql, ComboxBo.class);
       return (List<ComboxBo>) list;
    }
	//保存策略头档(修改中)
	@Override
	public MsgRes saveStrategy(String strQuery) throws Exception {
		Odata_WmsCheckStrategy strategy=(Odata_WmsCheckStrategy)JSON.parseObject(strQuery,Odata_WmsCheckStrategy.class);
		Integer checkStrategyId=0;
		if(strategy.getId().getCheckStrategyId()==null || strategy.getId().getCheckStrategyId().equals(""))
        {
			String sql="select nvl(max(a.check_strategy_id),0) from wms_check_strategy a where 1=1 " ;
			List list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				checkStrategyId =(int) (Double.parseDouble(list.get(0).toString())+1) ;
			}
        }else{
        	checkStrategyId=strategy.getId().getCheckStrategyId();
        }
		strategy.getId().setCheckStrategyId(checkStrategyId);
		genDao.saveOrUpdateObj(strategy);	
		return new MsgRes(true, "数据保存成功！", checkStrategyId);
	}
	//删除策略头档(修改中)
	@Override
	public MsgRes deleteStrategy(String strQuery) throws Exception {

		String deleteSql="delete from wms_check_strategy a where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
		 {
			 String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			 deleteSql=deleteSql+strWs;
		 }
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

	
}