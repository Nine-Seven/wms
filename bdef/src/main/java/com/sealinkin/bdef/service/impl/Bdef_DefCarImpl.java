package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefcarModel;
import com.sealinkin.bdef.model.Bdef_DefcartypeModel;
import com.sealinkin.bdef.po.Bdef_Defcar;
import com.sealinkin.bdef.po.Bdef_Defcartype;
import com.sealinkin.bdef.service.IBdef_DefCarService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.po.Odata_CarReceipt;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Bdef_DefCarImpl implements IBdef_DefCarService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取车辆类型列表
	@Override
	public ExtListDataBo<Bdef_DefcartypeModel> getCartypeList(
			String strEnterpriseNo,String strQuery, PageBo pageBo) throws Exception {
		String strSql="select a.enterprise_no,a.cartype_no," +
				"a.cartype_name,a.cartype_weight,a.cartype_length," +
				"a.cartype_width,a.cartype_height,a.max_layer," +
				"a.rgst_name,a.rgst_date,a.updt_name,a.updt_date " +
		   "from bdef_defcartype a "+
          "where a.enterprise_no='"+strEnterpriseNo+"' ";
	
	    if(strQuery!=null && !strQuery.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		    strSql=strSql+strWs;
	    }
	
	    strSql+=" order by a.cartype_no ";
	    List<Bdef_DefcartypeModel> list = genDao.getListByNativeSql(strSql,Bdef_DefcartypeModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	    ExtListDataBo<Bdef_DefcartypeModel> extListBo= new ExtListDataBo<Bdef_DefcartypeModel>(list, intCount);
	    return extListBo;
    }
	//车辆类型下拉
	@Override
	public List<ComboxBo> getCarTypeQuery(String strEnterpriseNo,
			String queryStr) throws Exception {
		String strSql="select distinct a.cartype_no value,cartype_name text," +	
				"'['|| ltrim(a.cartype_no)||']'||cartype_name dropValue " +
				"from bdef_defcartype a " +
				"where 1=1  "+
				"and a.enterprise_no ='"+strEnterpriseNo+"' ";
		
	List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	return (List<ComboxBo>) list;
    } 
	//判断车辆类型代码是否唯一
	@Override
	public List<String> carTypeCheck(String enterpriseNo, String queryStr)
			throws Exception {
		String strSql="select a.cartype_no "+
				"from bdef_defcartype a where 1=1  "+
				"and a.cartype_no='"+queryStr+
				"' and a.enterprise_no='"+enterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	//车辆类型保存
	@Override
	public MsgRes saveCarType(String queryStr) throws Exception {
		Bdef_Defcartype carType=(Bdef_Defcartype)JSON.parseObject(queryStr,Bdef_Defcartype.class);
		genDao.saveOrUpdateObj(carType);	
		return new MsgRes(true, "数据保存成功！", "");
	}
	//删除车辆类型
	@Override
	public MsgRes deleteCarType(String enterpriseNo, String queryStr)
			throws Exception {

		String sql=" select a.cartype_no from bdef_defcar a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.cartype_no='"+queryStr+"' ";
		
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该车辆类型有车辆信息数据，不能删除","");
		}
		
		String deleteSql="delete from bdef_defcartype where cartype_no='"+queryStr+"' " +
				"and enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	//获取车辆信息列表
	@Override
	public ExtListDataBo<Bdef_DefcarModel> getCarList(String strEnterpriseNo,
			String strWarehouseNo,String strQuery,String str,
			PageBo pageBo) throws Exception {
		String strSql="select a.enterprise_no,a.warehouse_no," +
				"(case " +
			    "      when nvl(a.status , '0') = '0' then '禁用' " +
			    "      when nvl(a.status, '0') = '1' then '可用' " +
			    "       end) strStatus, " +
				"a.car_no,a.cartype_no,a.car_name,a.car_plate," +
				"a.oil_consume,a.oil_consume,a.care_mile," +
				"a.mile,a.care_date,a.care_worker,a.sanpl_no," +
				"a.car_class,a.driver_worker,a.rgst_name," +
				"a.rgst_date,a.updt_name,a.updt_date,b.cartype_name,c.worker_name,  " +
                "to_char(a.care_date,'yyyy-mm-dd')careDateText, "+
                " '['|| ltrim(a.driver_worker)||']'||c.worker_name driverWorkerText, "+
                " '['|| ltrim(a.car_class)||']'||f_get_fieldtext('BDEF_DEFCAR','CAR_CLASS',a.car_class)carClassText "+
		   "from bdef_defcar a,bdef_defcartype b,bdef_defworker c   "+
          "where a.enterprise_no=b.enterprise_no " +
            "and a.cartype_no=b.cartype_no " +
            "and a.enterprise_no=c.enterprise_no(+) " +
            "and a.driver_worker=c.worker_no(+) " +
            "and a.enterprise_no='"+strEnterpriseNo+"' " +
			"and a.warehouse_no='"+strWarehouseNo+"' ";
	
	    if(strQuery!=null && !strQuery.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		    strSql=strSql+strWs;
	    }
	    if(str!=null && !str.equals(""))
	    {
		    String strWs2=CommUtil.covtCollectionToWhereSql(str);
		    strSql=strSql+strWs2;
	    }
	    strSql+=" order by a.cartype_no,a.car_no ";
	    List<Bdef_DefcarModel> list = genDao.getListByNativeSql(strSql,Bdef_DefcarModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	    ExtListDataBo<Bdef_DefcarModel> extListBo= new ExtListDataBo<Bdef_DefcarModel>(list, intCount);
	    return extListBo;
    }
	//判断车辆代码是否唯一
	@Override
	public List<String> carCheck(String enterpriseNo, String strWarehouseNo,
			String queryStr) throws Exception {
		String strSql="select a.car_no "+
				"from bdef_defcar a where 1=1  "+
				"and a.car_no='"+queryStr+"' "+
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+strWarehouseNo+"' ";
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	//车辆信息保存
	@Override
	public MsgRes saveCar(String queryStr) throws Exception {
		Bdef_Defcar car=(Bdef_Defcar)JSON.parseObject(queryStr,Bdef_Defcar.class);
		genDao.saveOrUpdateObj(car);	
		return new MsgRes(true, "数据保存成功！", "");
	}
	
	//填充车辆类型代码下拉控件 7-9添加  hj   ---还没修改
	@Override
	public List<ComboxBo> querycarTypeNoCombo(String enterpriseNo,
			String strOwnerNo, String strWheresql, String strQuery)
			throws Exception {
		String strSql="select distinct t1.cartype_no value,t1.cartype_name text, " +
				"'['|| ltrim(t1.cartype_no)||']'||t1.cartype_name dropValue " +
				" from bdef_defcartype t1 " +
				" where 1=1 " +
				" and t1.enterprise_no='"+enterpriseNo+"'";

		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and t1.cartype_no like '%"+strWheresql+"%' ";
		}
		
		strSql=strSql+" order by t1.cartype_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//填充车辆类型名称下拉控件 7-9添加  hj  
	@Override
	public List<ComboxBo> querycarTypeNameCombo(String enterpriseNo,
			String strOwnerNo, String strWheresql, String strQuery)
			throws Exception {
		String strSql="select distinct t1.cartype_no,t1.cartype_name value,t1.cartype_name text, " +
				"'['|| ltrim(t1.cartype_no)||']'||t1.cartype_name dropValue " +
				" from bdef_defcartype t1 " +
				" where 1=1 " +
				" and t1.enterprise_no='"+enterpriseNo+"'";

		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and t1.cartype_name like '%"+strWheresql+"%' ";
		}
		
		strSql=strSql+" order by t1.cartype_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	
	//开始
	//填充车辆名称下拉控件 7-9添加  hj  
	@Override
	public List<ComboxBo> querycarNameCombo(String enterpriseNo,
			String strWarehouseNo, String strWheresql, String strQuery)
			throws Exception {
		String strSql="select distinct t1.car_no, t1.car_name value,t1.car_name text, " +
				"'['|| ltrim(t1.car_no)||']'||t1.car_name dropValue " +
				" from bdef_defcar t1 " +
				" where 1=1 " +
				" and t1.enterprise_no='"+enterpriseNo+"'" +
				" and t1.warehouse_no='"+strWarehouseNo+"'";

		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and t1.car_name like '%"+strWheresql+"%' ";
		}
		
		strSql=strSql+" order by t1.car_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//填充车牌号下拉控件 7-9添加  hj  
	@Override
	public List<ComboxBo> querycarPlateCombo(String enterpriseNo,
			String strWarehouseNo, String strWheresql, String strQuery)
			throws Exception {
		String strSql="select distinct t1.car_no, t1.car_plate value,t1.car_plate text, " +
				"'['|| ltrim(t1.car_no)||']'||t1.car_plate dropValue " +
				" from bdef_defcar t1 " +
				" where 1=1 " +
				" and t1.enterprise_no='"+enterpriseNo+"'" +
				" and t1.warehouse_no='"+strWarehouseNo+"'";

		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and t1.car_plate like '%"+strWheresql+"%' ";
		}
		
		strSql=strSql+" order by t1.car_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//填充司机名称下拉控件 7-9添加  hj  
	@Override
	public List<ComboxBo> querydeiverWorkerCombo(String enterpriseNo,
			String strWarehouseNo, String strWheresql, String strQuery)
			throws Exception {
		String strSql="select distinct t1.car_no, t1.driver_worker value,t1.driver_worker text, " +
				"'['|| ltrim(t1.car_no)||']'||t1.driver_worker dropValue " +
				" from bdef_defcar t1 " +
				" where 1=1 " +
				" and t1.enterprise_no='"+enterpriseNo+"'" +
				" and t1.warehouse_no='"+strWarehouseNo+"'";

		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and t1.driver_worker like '%"+strWheresql+"%' ";
		}
		
		strSql=strSql+" order by t1.car_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
}























