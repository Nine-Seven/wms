package com.sealinkin.bdef.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_ArticleGroupModel;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.po.Bdef_ArticleGroup;
import com.sealinkin.bdef.service.IBdef_ArticleGroupService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.ExtTreeLeafBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.TipUtil;


@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_ArticleGroupImpl implements IBdef_ArticleGroupService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() 
	{
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) 
	{
		this.genDao = genDao;
	}
	
	/*
	 * @func 保存商品类别信息
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public MsgRes saveGroup(String str) throws Exception 
	{// 匹配开始:36  匹配开始：155
		Bdef_ArticleGroup ba=(Bdef_ArticleGroup)JSONObject.toBean(JSONObject.fromObject(str),Bdef_ArticleGroup.class);
		
	    String strSql = "select * from bdef_article_group a where a.owner_no='"+ba.getId().getOwnerNo()+"' " +
				" and a.group_no='"+ba.getId().getGroupNo()+"' and a.group_level='"+ba.getId().getGroupLevel()+
				"' and a.enterprise_no='"+ba.getId().getEnterpriseNo()+"'";
		List<Bdef_ArticleGroup> list = genDao.getListByNativeSql(strSql,Bdef_ArticleGroup.class);
		String falg = "0";
		if(list.size()!=0)//判断是新增还是修改，1修改
		{
			falg = "1";
		}
			
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(ba.getId().getEnterpriseNo());//strEnterpriseNo
		inList.add(ba.getId().getOwnerNo());//strOwnerNo
		inList.add(ba.getId().getGroupNo());//strGroupNo
		inList.add(ba.getId().getGroupLevel());//strGroupLevel
		inList.add(ba.getGroupName());//strGroupName
		inList.add(ba.getMGroupNo());//strMGroupNo
		inList.add(ba.getMGroupName());//strMGroupName
		inList.add(ba.getLGroupNo());//strLGroupNo
		inList.add(ba.getLGroupName());//strLGroupName
		inList.add(ba.getAlarmrate());//strAlarmrate
		inList.add(ba.getCheckExcess());//strCheckExcess
		inList.add(ba.getCheckQtyFlag());//strCheckQtyFlag
		inList.add(ba.getCheckQtyRate());//strCheckQtyRate
		inList.add(ba.getCheckWeightFlag());//CheckWeightFlag
		inList.add(ba.getCheckWeightRate());//strCheckWeightRate
		inList.add(ba.getDivideExcess());//strDivideExcess
		inList.add(ba.getDoubleCheck());//strDoubleCheck
		inList.add(ba.getFcStrategy());//strFcStrategy
		inList.add(ba.getFreezerate());//strFreezerate
		inList.add(ba.getTurnOverRule());//strTurnOverRule
		inList.add(ba.getUmCheckExcess());//strUmCheckExcess
		inList.add(ba.getPickExcess());//strPickExcess
		inList.add(ba.getTemperatureFlag());//strTemperatureFlag
		inList.add(ba.getScanFlag());//strScanFlag
		inList.add(ba.getMeasureMode());//strMeasureMode
		inList.add(ba.getMixFlag());//strMixFlag
		inList.add(ba.getQcFlag());//strQcFlag
		inList.add(ba.getQcRate());//strQcRate
		inList.add(ba.getIStrategy());//strIStrategy
		inList.add(ba.getOStrategy());//strOStrategy
		inList.add(ba.getMStrategy());//strMStrategy
		inList.add(ba.getRiStrategy());//strRiStrategy
		inList.add(ba.getRoStrategy());//strRoStrategy
		inList.add(ba.getPrintFlag());//strPrintFlag
		inList.add(ba.getRgstName());//strRgstName
		inList.add(ba.getStatus());//strStatus
		inList.add(StringUtil.isStrEmpty(ba.getRsvStrategy1())?"N":ba.getRsvStrategy1());
		inList.add(StringUtil.isStrEmpty(ba.getRsvStrategy2())?"N":ba.getRsvStrategy2());
		inList.add(StringUtil.isStrEmpty(ba.getRsvStrategy3())?"N":ba.getRsvStrategy3());
		inList.add(StringUtil.isStrEmpty(ba.getRsvStrategy4())?"N":ba.getRsvStrategy4());
		inList.add(StringUtil.isStrEmpty(ba.getRsvStrategy5())?"N":ba.getRsvStrategy5());
		inList.add(StringUtil.isStrEmpty(ba.getRsvStrategy6())?"N":ba.getRsvStrategy6());
		inList.add(falg);//strFalg，1修改
		
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_BDEF.saveOrUpdateGroup");
		System.out.println(inList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E21302"),null);//保存成功！
	}// 匹配开始:36  匹配开始：155
	
	/*
	 * @func 删除商品类别
	 */
	@Override
	public MsgRes deleteGroupNo(String strGroupNo, String strGroupLevel,
			String strOwnerNo,String strEnterpriseNo) {
		if(strGroupLevel.equals("")){
			return new MsgRes(false,"请检查类别级别！",null);
		}else{
			if(strGroupLevel.equals("大类")){
				
				String sql1 ="select * from bdef_article_group g " +
						"where g.owner_no='"+strOwnerNo+"' and g.enterprise_no='"+strEnterpriseNo+"' " +
						"and g.l_group_no='"+strGroupNo+"' and g.group_no <>'"+strGroupNo+"'";
				List<String> list = genDao.getListByNativeSql(sql1);
				
				if(list.size()>0){//有中类或者小类不能删除
					return new MsgRes(false,"该大类不能删除，请检查！",null);
				}else{//没有，可以删除该大类
					String sql2 ="delete bdef_article_group g "+
							"where g.owner_no='"+strOwnerNo+"' and g.enterprise_no='"+strEnterpriseNo+"' " +
							"and g.group_level='2' and g.group_no ='"+strGroupNo+"'";
					
					genDao.updateBySql(sql2);
				}
				
			}else if(strGroupLevel.equals("中类")){
	
				String sql1 ="select * from bdef_article_group g " +
						"where g.owner_no='"+strOwnerNo+"' and g.enterprise_no='"+strEnterpriseNo+"' " +
						"and g.m_group_no='"+strGroupNo+"' and g.group_no <>'"+strGroupNo+"'";
				List<String> list = genDao.getListByNativeSql(sql1);
				
				if(list.size()>0){//有小类不能删除
					return new MsgRes(false,"该中类不能删除，请检查！",null);
				}else{//没有，可以删除该中类
					String sql2 ="delete bdef_article_group g "+
							"where g.owner_no='"+strOwnerNo+"' and g.enterprise_no='"+strEnterpriseNo+"' " +
							"and g.group_level='1' and g.group_no ='"+strGroupNo+"'";
					
					genDao.updateBySql(sql2);
				}
			}else if(strGroupLevel.equals("小类")){
				
				String sql1 ="select * from bdef_defarticle g " +
						"where g.owner_no='"+strOwnerNo+"' and g.enterprise_no='"+strEnterpriseNo+"' " +
						"and g.group_no='"+strGroupNo+"' ";
				List<String> list = genDao.getListByNativeSql(sql1);
				
				if(list.size()>0){//有商品不能删除
					return new MsgRes(false,"该小类不能删除，请检查！",null);
				}else{//没有，可以删除该小类
					String sql2 ="delete bdef_article_group g "+
							"where g.owner_no='"+strOwnerNo+"' and g.enterprise_no='"+strEnterpriseNo+"' " +
							"and g.group_level='0' and g.group_no ='"+strGroupNo+"'";
					
					genDao.updateBySql(sql2);
				}
				
			}
		}
		return new MsgRes(true,"删除成功",null);
	}
	
	/*
	 * @func 获得商品类别树信息
	 * @param node 节点编号
	 * @return void
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ExtTreeLeafBo> getBdef_ArticleModuleTree(String enterpriseNo,
			String str,String strOwnerNo) throws Exception 
	{
		String strSql="select a.id as id ,a.text||'['||a.id||']' as text," +
				" (select case when COUNT(*)>0 then 'false' else 'true' end as isfile " +
				" from v_bdef_article_group_tree b where b.ParentID=a.id and b.owner_no='"+strOwnerNo
				+ "') as leaf " +
				" from v_bdef_article_group_tree a  where  a.ParentID='"+str+
				"' and a.owner_no ='"+strOwnerNo+ 
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<ExtTreeLeafBo> list = genDao.getListByNativeSql(strSql,ExtTreeLeafBo.class);
        return list;
	}
	
	/*
	 * @func 获得相应类别下的商品类别明细
	 * @param start 起始数，limit 每页最多显示数目
	 * @return list  查询获得的商品
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public ExtListDataBo<Bdef_ArticleGroupModel> getGroupList(String enterpriseNo,String strWheresql,String strQuery,
			PageBo pageBo) throws Exception 
	{
		String str[]=strWheresql.split(",");
		String group_no  = str[1].trim();
		String strSql;
		String strTotsql;
		//点击全部（类别树）
		if(group_no.equals("0")){
			strSql="select a.*,c.owner_name," +
					"f_get_fieldtext('BDEF_ARTICLE_GROUP','GROUP_LEVEL',a.group_level) grouplevelText, " +
					"f_get_fieldtext('N','DEF_STATUS',a.status) statusText, " +
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='FC' and t1.strategy_id=a.fc_strategy) fcText ,"+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='I' and t1.strategy_id=a.i_strategy) iText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='O' and t1.strategy_id=a.o_strategy) oText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='M' and t1.strategy_id=a.m_strategy) mText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='RI' and t1.strategy_id=a.ri_strategy) riText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='RO' and t1.strategy_id=a.ro_strategy) roText "+
					"from bdef_article_group a,bdef_defowner c where " +
					" a.owner_no='"+str[2].trim()+
					"' and a.enterprise_no=c.enterprise_no " +
					"  and a.enterprise_no='"+enterpriseNo+
					"' and a.owner_no=c.owner_no %s1 " +
					" order  by a.group_no ";
			strTotsql = "select count(1) from bdef_article_group a,bdef_defowner c where " +
					" a.owner_no='"+str[2].trim()+
					"' and a.enterprise_no=c.enterprise_no " +
					"  and a.enterprise_no='"+enterpriseNo+
					"' and a.owner_no=c.owner_no %s1 order by a.group_no ";		
		}else{
			strSql="select a.*,c.owner_name," +
					"f_get_fieldtext('BDEF_ARTICLE_GROUP','GROUP_LEVEL',a.group_level) grouplevelText, " +
					"f_get_fieldtext('N','DEF_STATUS',a.status) statusText, " +
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='FC' and t1.strategy_id=a.fc_strategy) fcText ,"+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='I' and t1.strategy_id=a.i_strategy) iText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='O' and t1.strategy_id=a.o_strategy) oText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='M' and t1.strategy_id=a.m_strategy) mText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='RI' and t1.strategy_id=a.ri_strategy) riText, "+
					"(select t1.strategy_name from wms_defstrategy t1 where t1.strategy_type='RO' and t1.strategy_id=a.ro_strategy) roText "+
					
					"from bdef_article_group a,bdef_defowner c where a.group_no " +
					"in(" +
					"select b.group_no " +
					"from bdef_article_group b " +
					"where b.group_no<>'"+group_no+"' " +
							" and (b.l_group_no='"+group_no+"' " +
									"or b.m_group_no='"+group_no+"')" +
							"and b.owner_no='"+str[2].trim()+"'"+
					") and a.owner_no='"+str[2].trim()+
					"' and a.enterprise_no=c.enterprise_no " +
					"  and a.enterprise_no='"+enterpriseNo+
					"' and a.owner_no=c.owner_no %s1 " +
					" order  by a.group_no ";
		        strTotsql = "select count(1) from bdef_article_group a,bdef_defowner c where a.group_no " +
					"in(" +
					"select b.group_no " +
					"from bdef_article_group b " +
					"where b.group_no<>'"+group_no+"' " +
							" and (b.l_group_no='"+group_no+"' " +
									"or b.m_group_no='"+group_no+"') " +
							"and b.owner_no='"+str[2].trim()+
					"') and a.owner_no='"+str[2].trim()+
					"' and a.enterprise_no=c.enterprise_no " +
					"  and a.enterprise_no='"+enterpriseNo+
					"' and a.owner_no=c.owner_no %s1 order by a.group_no ";		
		}
		
		
        if(strQuery!=null && !strQuery.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			strSql = strSql.replace("%s1", ws);
			strTotsql = strTotsql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
			strTotsql = strTotsql.replace("%s1", "");
		}
		List<Bdef_ArticleGroupModel> list = null;
		Integer intCount = 0;
		ExtListDataBo<Bdef_ArticleGroupModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Bdef_ArticleGroupModel.class,pageBo.getStart(), pageBo.getPagesize());
		intCount = genDao.getCountByNativeSql(strTotsql);
		extListBo= new ExtListDataBo<Bdef_ArticleGroupModel>(list, intCount);
        return extListBo;
	}
	
	/*
	 * @func 获得大类商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ComboxBo> getLGroupComboList(String enterpriseNo,String strOwnerNo,int intStart, int intPagesize)
			throws Exception 
	{
		String strSql="select a.group_no value,a.group_name text," +
				"'['|| ltrim(a.group_no)||']'||a.group_name dropValue " +
				"from bdef_article_group a where a.group_level='2'" +
				" and a.owner_no='"+strOwnerNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	/*
	 * @func 根据输入的值获得中类商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的中类商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ComboxBo> getMGroupComboList(String enterpriseNo,String strWheresql,String strOwnerNo, int intStart,
			int intPagesize) throws Exception 
	{
		String strSql="select a.group_no value,a.group_name text," +
				"'['|| ltrim(a.group_no)||']'||a.group_name dropValue " +
				"from bdef_article_group a " +
				"where a.group_level='1' and a.l_group_no='"+strWheresql+
				"' and a.owner_no='"+strOwnerNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return (List<ComboxBo>)list;
	}
	
	/*
	 * @func 根据输入的值获得商品类别
	 * @param 0,100
	 * @return list  查询获得的商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ComboxBo> getGroupNoList(
			String enterpriseNo,String strWheresql, String strOwnerNo,int intStart,int intPagesize)throws Exception 
    {
    	String sql="select distinct group_no value,group_name text," +
    		"'['|| ltrim(group_no)||']'||group_name dropValue " +
    		" from bdef_article_group " +
    		"where owner_no in("+strOwnerNo+") " +
    		"and enterprise_no='"+enterpriseNo+
    		"'and group_level=0 and group_no like '%"
    		+strWheresql+"%' and rownum < 10";
    	List list=genDao.getListByNativeSql(sql, ComboxBo.class);
    	return (List<ComboxBo>)list;
	}
	
	/*
	 * @func 查询数据库中是否已存在新增的类别
	 * @param 0,100
	 * @return 0：不存在，1：存在
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public String checkGroupNo(String enterpriseNo,String strOwnerNo,String strGroupNo) throws Exception 
	{
		String strSql="select a.group_no from bdef_article_group a " +
				" where a.group_no='"+strGroupNo+
				"' and a.owner_no='"+strOwnerNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
		
	/*
	 * @func 从Wms_deffieldval表中获取下拉列表
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<ComboxBo> getCombo(String[] str, int intStart, int intPagesize) 
	{
        String varSql = "select value,text,'['||value||']'||text dropValue from wms_deffieldval  " +
        		" where table_name='"+str[0]+"' and colname='"+str[1]+"' and status='1' order by value ";
		List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,100);		 
		return  (List<ComboxBo>)list;
	}

	/*
	 * @func 根据货主编号获得对于的策略
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<Bdef_DefOwnerModel> getStrategyByOwnerNo(String enterpriseNo,String strOwnerNo) 
	{
		 String varSql = "select * from bdef_defowner a " +
		 		"where a.owner_no='"+strOwnerNo+
		 		"' and a.enterprise_no='"+enterpriseNo+"' ";
			List<Bdef_DefOwnerModel> list= genDao.getListByNativeSql(varSql,Bdef_DefOwnerModel.class,0,10);		 
			return  (List<Bdef_DefOwnerModel>)list;
	}

	/*
	 * @func 根据类别编码获得级别、大、中类编码
	 * @return list 
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<Bdef_ArticleGroupModel> getParentNo(String enterpriseNo,String strGroupNo) 
	{
		String strSql = "select * from bdef_article_group a " +
				"where a.group_no='"+strGroupNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<Bdef_ArticleGroupModel> list= genDao.getListByNativeSql(strSql,Bdef_ArticleGroupModel.class,0,10);		 
		return  (List<Bdef_ArticleGroupModel>)list;
	}
	
	/**
	 * 商品类别小类COMBO
	 */
	@Override
	public List<ComboxBo> query_GroupCombo(String enterpriseNo,String strOwnerNo, String strFlag,
			String strWheresql) throws Exception {
		String strSql="select t1.group_no value,t1.group_name text,'['|| ltrim(t1.group_no)||']'||t1.group_name dropValue from bdef_article_group t1 " +
				"where t1.group_level='0' " +
				" and t1.enterprise_no='"+enterpriseNo+"' ";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and  t1.group_no like '%"+strWheresql+"%' ";
		}
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in('"+strOwnerNo+"') ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 获取商品类别控制信息和策略管理
	 */
	@Override
	public List<Bdef_ArticleGroupModel> query_ControlAndTactics(
			String enterpriseNo,
			String strOwnerNo,
			String strGroupNo) throws Exception {
		String strSql = "select * from bdef_article_group a " +
				"where a.group_no='"+strGroupNo+
				"' and owner_no in ("+strOwnerNo+") " +
				" and enterprise_no='"+enterpriseNo+"' ";
		List list= genDao.getListByNativeSql(strSql,Bdef_ArticleGroupModel.class,0,1);		 
		return  (List<Bdef_ArticleGroupModel>)list;
	}
	
	/**
	 * 填充策略下拉
	 */
	@Override
	public List<ComboxBo> query_WmsDefStrategyCombo(String strWheresql)throws Exception {
		String strSql="select t1.strategy_id value,t1.strategy_name text," +
				"'['|| ltrim(t1.strategy_id)||']'||t1.strategy_name dropValue " +
				"from wms_defstrategy t1 where t1.strategy_type='"+strWheresql+"'";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 商品类别下拉
	 */
	public List<ComboxBo> getGroupNoComboList(String enterpriseNo,String wheresql,String strOwnerNo, int start, int pagesize) {
		String sql="select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from bdef_article_group a union " +
				"select a.group_no as value,a.group_name as text,'['|| ltrim(a.group_no)||']'||a.group_name dropValue from bdef_article_group a where 1=1 " +
				   " and a.owner_no ='"+strOwnerNo+
				   "' and a.enterprise_no='"+enterpriseNo+"' ";
		if(wheresql!=null && wheresql!=""){
		    sql+="and a.group_no like '%"+wheresql+"%'";
		}
		List list= (List<Object>) genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);		 
		return  (List<ComboxBo>)list;
	}
	/*
	 * @func 获取批次下拉列表
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<ComboxBo> getBatchIdCombo(int intStart, int intPagesize) 
	{
        String varSql = "select distinct a.batch_id value,a.batch_name text," +
                "'['|| ltrim(a.batch_id)||']'||a.batch_name dropValue " +
                "from bset_article_batch_m a " +
            		" where 1=1 ";
		List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);		 
		return  (List<ComboxBo>)list;
	}
	/*@Override
	public List<ComboxBo> getOwnerComboList(String enterpriseNo,String workerOwner,String strOwnerNo)
			throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where 1=1 and t1.enterprise_no='"+enterpriseNo+"' ";
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
		}else{
			strSql=strSql+" and 1=2";
		}
		if(strOwnerNo!=null && !strOwnerNo.equals("")){
			strSql=strSql+" and t1.owner_no ='"+strOwnerNo+"' ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	*/
	//获取大类信息
	@Override
	public Bdef_ArticleGroupModel getLOrMgroup(String enterpriseNo,String strGroupNo,
			String strOwnerNo, String strGroupLevel) {
		String sql="select * from bdef_article_group t where t.group_no='"+strGroupNo+
					"' and t.owner_no='"+strOwnerNo+
					"' and group_level='"+strGroupLevel+
					"' and enterprise_no='"+enterpriseNo+"' ";
		
		List<Bdef_ArticleGroupModel> articleGroup =  genDao.getListByNativeSql(sql, Bdef_ArticleGroupModel.class);
		if(articleGroup.size()!=0){
			return articleGroup.get(0);
		}else{
			return null;
		}
	}
	
	//判断商品编码是否唯一
	@Override
	public List<String> groupNo1301Check(String enterpriseNo,String strOwnerNo,
			String strGroupLevel, String strGroupNo) throws Exception {
		String strSql="select t.group_no "+
				"from bdef_article_group t where 1=1  "+
				"and t.owner_no='"+strOwnerNo+
				"' and t.group_no='"+strGroupNo+
				"' and t.enterprise_no='"+enterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	@Override
	public List<ComboxBo> getGroupInfo(String strEnterpriseNo,
			String strOwnerNo, String strFlag, String str, String strQuery)
			throws Exception {
		String sql="select distinct a.group_no value,a.group_name text," +
	    		"'['|| ltrim(a.group_no)||']'||a.group_name dropValue " +
	    		" from bdef_article_group a  where 1=1 " +
	    		"and a.enterprise_no='"+strEnterpriseNo+
	    		"' and a.owner_no in("+strOwnerNo+") " +
	    		"and a.group_level='"+strFlag+"'";
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
		    sql = sql + 
			" and (a.group_no like '%"+str+"%' " +
			"or a.group_name like '%"+str+"%' )";
        }
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
}