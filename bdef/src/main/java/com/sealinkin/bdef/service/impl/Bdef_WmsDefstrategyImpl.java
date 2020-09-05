package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_WmsDefstrategyDModel;
import com.sealinkin.bdef.model.Bdef_WmsDefstrategyModel;
import com.sealinkin.bdef.po.Bdef_WmsDefstrategy;
import com.sealinkin.bdef.po.Bdef_WmsDefstrategyD;
import com.sealinkin.bdef.service.IBdef_WmsDefstrategyService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Bdef_WmsDefstrategyImpl implements IBdef_WmsDefstrategyService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取策略头档列表
	@Override
	public ExtListDataBo<Bdef_WmsDefstrategyModel> getWmsDefstrategyList(
			String strQuery, PageBo pageBo) throws Exception {
		String strSql="select a.strategy_id,a.strategy_type,a.strategy_name," +
				      "a.default_flag,a.rgst_name,a.rgst_date, " +
		                " '['|| ltrim(a.default_flag)||']'||f_get_fieldtext('N','DEFAULT_FLAG',a.default_flag)defaultFlagText "+
		              "from wms_defstrategy a " +
		              "where 1=1 ";
	
	    if(strQuery!=null && !strQuery.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		    strSql=strSql+strWs;
	    }
	
	    strSql+=" order by a.strategy_type，a.strategy_id ";
	    List<Bdef_WmsDefstrategyModel> list = genDao.getListByNativeSql(strSql,Bdef_WmsDefstrategyModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	    ExtListDataBo<Bdef_WmsDefstrategyModel> extListBo= new ExtListDataBo<Bdef_WmsDefstrategyModel>(list, intCount);
	    return extListBo;
    }
	//获取策略明细列表
	@Override
	public ExtListDataBo<Bdef_WmsDefstrategyDModel> getWmsDefstrategyDList(
			String strQuery, PageBo pageBo) throws Exception {
		String strSql="select a.*,c.strategy_name as ruleName, " +
                " '['|| ltrim(a.limmit_mixbatch)||']'||f_get_fieldtext('N','LIMIT',a.limmit_mixbatch)limmitMixbatchText, "+
                " '['|| ltrim(a.limmit_mixarticle)||']'||f_get_fieldtext('N','MIX',a.limmit_mixarticle)limmitMixarticleText, "+
                " '['|| ltrim(a.limmit_maxqty)||']'||f_get_fieldtext('N','LIMIT',a.limmit_maxqty)limmitMaxqtyText, "+
                " '['|| ltrim(a.limmit_maxcase)||']'||f_get_fieldtext('N','LIMIT',a.limmit_maxcase)limmitMaxcaseText, "+
                " '['|| ltrim(a.limmit_maxweight)||']'||f_get_fieldtext('N','LIMIT',a.limmit_maxweight)limmitMaxweightText, "+
                " '['|| ltrim(a.limmit_maxgroupno)||']'||f_get_fieldtext('N','LIMIT',a.limmit_maxgroupno)limmitMaxgroupnoText, "+
                " '['|| ltrim(a.limmit_celluse)||']'||f_get_fieldtext('N','LIMIT',a.limmit_celluse)limmitCelluseText, "+
                " '['|| ltrim(a.limmit_rsv01)||']'||f_get_fieldtext('N','LIMIT',a.limmit_rsv01)limmitRsv01Text, "+
                " '['|| ltrim(a.limmit_rsv01)||']'||f_get_fieldtext('N','LIMIT',a.limmit_rsv01)limmitRsv02Text, "+
                " '['|| ltrim(a.limmit_rsv01)||']'||f_get_fieldtext('N','LIMIT',a.limmit_rsv01)limmitRsv03Text, "+
                " '['|| ltrim(a.limmit_rsv01)||']'||f_get_fieldtext('N','LIMIT',a.limmit_rsv01)limmitRsv04Text, "+
                " '['|| ltrim(a.limmit_rsv01)||']'||f_get_fieldtext('N','LIMIT',a.limmit_rsv01)limmitRsv05Text, "+
                " '['|| ltrim(a.limmit_rsv01)||']'||f_get_fieldtext('N','LIMIT',a.limmit_rsv01)limmitRsv06Text "+
                "from wms_defstrategy_d a, wms_defstrategy b,wms_defrule c " +
	              "where a.strategy_id=b.strategy_id " +
	              "and a.strategy_type=b.strategy_type " +
	              "and a.strategy_type=c.strategy_type " +
	              "and a.rule_id=c.rule_id ";

        if(strQuery!=null && !strQuery.equals(""))
        {
	         String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
	         strSql=strSql+strWs;
        }

        strSql+=" order by a.strategy_type，a.strategy_id,a.rule_order,a.rule_id ";
        List<Bdef_WmsDefstrategyDModel> list = genDao.getListByNativeSql(strSql,Bdef_WmsDefstrategyDModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
        Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
        ExtListDataBo<Bdef_WmsDefstrategyDModel> extListBo= new ExtListDataBo<Bdef_WmsDefstrategyDModel>(list, intCount);
        return extListBo;
    }
	//获取UI策略类型下拉
	@Override
	public List<ComboxBo> getStrategyTypeForUI(String strQuery)
			throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defstrategy "+
				"union "+
				"select distinct a.strategy_type value,a.strategy_type text," +	
				"'['|| ltrim(a.strategy_type)||']'||f_get_fieldtext('N','STRATEGY',a.strategy_type) dropValue " +
				"from wms_defstrategy a " +
				"where 1=1 " ;
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
    }
	//获取UI默认标识下拉
	@Override
	public List<ComboxBo> getDefaultFlagForUI(String strQuery) throws Exception {
		String strSql="select distinct a.default_flag value,a.default_flag text," +	
				"'['|| ltrim(a.default_flag)||']'||f_get_fieldtext('N','DEFAULT_FLAG',a.default_flag) dropValue " +
				"from wms_defstrategy a " +
				"where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
        {
	         String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
	         strSql=strSql+strWs;
        }
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
	}
	//获取UI策略id/名称下拉
	@Override
	public List<ComboxBo> getStrategyIdOrName(String strQuery, String str)
			throws Exception {
		String sql = "select distinct  a.strategy_id value ,a.strategy_name text," +
				     "'['|| ltrim(a.strategy_id)||']'||a.strategy_name dropValue " +
		             "from wms_defstrategy a "+
                     "where 1=1 ";
		
       if (strQuery != null && !strQuery.equals("")) {
	      String ws = CommUtil.covtCollectionToWhereSql(strQuery);
	      sql = sql + ws;
       }
       if (str != null && !str.equals("")) {
	      sql = sql + "and ( a.strategy_id like '%" + str + "%' " +
					"or a.strategy_name like '%" + str + "%') ";
       }
       List list = genDao.getListByNativeSql(sql, ComboxBo.class);
       return (List<ComboxBo>) list;
    }
	//保存策略头档
	@Override
	public MsgRes saveStrategy(String strQuery) throws Exception {
		Bdef_WmsDefstrategy strategy=(Bdef_WmsDefstrategy)JSON.parseObject(strQuery,Bdef_WmsDefstrategy.class);
		double strategyId=0;
		if(strategy.getId().getStrategyId()==null || strategy.getId().getStrategyId().equals(""))
        {
			String sql="select nvl(max(a.strategy_id),0) from wms_defstrategy a where 1=1 " +
					"and a.strategy_type='"+strategy.getId().getStrategyType()+"' ";
			List list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				strategyId =Double.parseDouble(list.get(0).toString())+1 ;
			}
        }else{
        	strategyId=strategy.getId().getStrategyId();
        }
		strategy.getId().setStrategyId(strategyId);
		genDao.saveOrUpdateObj(strategy);	
		return new MsgRes(true, "数据保存成功！", strategyId);
	}
	//删除策略头档
	@Override
	public MsgRes deleteStrategy(String strQuery) throws Exception {
		String sql=" select a.strategy_id from wms_defstrategy_d a " +
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
		String deleteSql="delete from wms_defstrategy a where 1=1 ";
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
		String strSql="select distinct a.rule_id value,a.strategy_name text," +	
					"'['|| ltrim(a.rule_id)||']'||a.strategy_name dropValue " +
					"from wms_defrule a " +
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
		strSql += "  order by a.rule_id ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//保存策略明细
	@Override
	public MsgRes saveStrategyD(String strQuery) throws Exception {
		Bdef_WmsDefstrategyD strategyD=(Bdef_WmsDefstrategyD)JSON.parseObject(strQuery,Bdef_WmsDefstrategyD.class);
		double ruleOrder=0;
		if(strategyD.getId().getRuleOrder()==null || strategyD.getId().getRuleOrder().equals(""))
        {
			String sql="select nvl(max(a.rule_order),0) from wms_defstrategy_d a where 1=1 " +
					"and a.strategy_type='"+strategyD.getId().getStrategyType()+"' " +
					"and a.strategy_id='"+strategyD.getId().getStrategyId()+"' ";
			List list=genDao.getListByNativeSql(sql);
			if(list.size()>0){
				ruleOrder =Double.parseDouble(list.get(0).toString())+1 ;
			}
        }else{
        	ruleOrder=strategyD.getId().getRuleOrder();
        }
		strategyD.getId().setRuleOrder(ruleOrder);
		genDao.saveOrUpdateObj(strategyD);	
		return new MsgRes(true, "数据保存成功！", ruleOrder);
	}
	//删除策略明细
	@Override
	public MsgRes deleteStrategyD(String strQuery) throws Exception {

		String deleteSql="delete from wms_defstrategy_d a where 1=1 ";
		if(strQuery!=null && !strQuery.equals(""))
		 {
			 String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			 deleteSql=deleteSql+strWs;
		 }
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	//策略明细上移、下移
	@Override
	public MsgRes ruleOrderMove(String strStrategyType, String strStrategyId,
			String strRuleOrder, String strRuleOrderChoice, String flag) throws Exception {
		//将选中的数据改成最大值
		String updateR=" update wms_defstrategy_d a " +
				              " set a.rule_order = 999 " +
				              " where a.strategy_type='"+strStrategyType+"' " +
				              " and a.strategy_id='"+strStrategyId+"' " +
			                  " and a.rule_order='"+strRuleOrderChoice+"' ";
	    genDao.updateBySql(updateR);
		if(flag.equals("1")){                     //选中数据上移
			System.out.println("上移");
			//将选中的数据的上一条数据，顺序号+1，即向下移
			String updatePlanDtop=" update wms_defstrategy_d a " +
					              " set a.rule_order = a.rule_order+1 " +
					              " where a.strategy_type='"+strStrategyType+"' " +
					              " and a.strategy_id='"+strStrategyId+"' " +
				                  " and a.rule_order='"+strRuleOrder+"' ";
		   genDao.updateBySql(updatePlanDtop);	
		}else{                                                          //选中数据下移	
			System.out.println("下移");
			//将选中的数据的下一条数据，顺序号-1，即向上移
			String updatePlanDdown="  update wms_defstrategy_d a " +
					              " set a.rule_order = a.rule_order-1 " +
					              " where a.strategy_type='"+strStrategyType+"' " +
					              " and a.strategy_id='"+strStrategyId+"' " +
				                  " and a.rule_order='"+strRuleOrder+"' ";
		   genDao.updateBySql(updatePlanDdown);
		}	
		 //将选中的数顺序号改成上/下一条数据的顺序号，即向上/下移
		 String updatePlanDown=" update wms_defstrategy_d a " +
					           " set a.rule_order = '"+strRuleOrder+"' " +
					           " where a.strategy_type='"+strStrategyType+"' " +
					            " and a.strategy_id='"+strStrategyId+"' " +
					             "and a.rule_order=999 ";
		   genDao.updateBySql(updatePlanDown);			
		return new MsgRes(Boolean.valueOf(true),"", "");
	}

}
