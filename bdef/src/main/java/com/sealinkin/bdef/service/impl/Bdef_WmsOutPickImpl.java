/**
 * 拣货参数配置service
 * @author MM
 */
package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBdef_WmsOutPickService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_OutpickStrategyModel;
import com.sealinkin.wms.po.Wms_OutpickStrategy;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_WmsOutPickImpl implements IBdef_WmsOutPickService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	 

	/*
	 * @func 获得拣货参数配置信息
	 * @param strQuery 查询条件， pageBo 显示条数
	 * @return list  查询获得的货主参数配置信息
	 */
	@Override
	public ExtListDataBo<Wms_OutpickStrategyModel> getWmsOutPickList(
			String enterpriseNo,String str, PageBo pageBo) {
		String sql="select enterprise_no,pick_strategy_id,pick_strategy_name," +
				"pick_diff_flag as pickDiffName,pick_auto_flag as pickAutoName," +
				"auto_getdivide_flag as autoGetdivideName,auto_dividesave_flag as autoDividesaveName," +
				"case when pick_diff_flag = 0 then '[0]不允许' else '[1]允许' end pick_diff_flag," +
				"case when pick_auto_flag = 0 then '[0]不允许' else '[1]允许' end pick_auto_flag," +
				"case when auto_getdivide_flag = 0 then '[0]不允许' else '[1]允许' end auto_getdivide_flag," +
				"case when auto_dividesave_flag = 0 then '[0]不允许' else '[1]允许' end auto_dividesave_flag," +
				"rsv_value1,rsv_value2," +
				"rsv_value3,rsv_value4,rsv_value5,rgst_name,rgst_date,updt_name,updt_date "+
				"from wms_outpick_strategy wob where 1=1 ";
		String strTotsql = "select count(1) from wms_outpick_strategy wob where 1=1 ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		List<Wms_OutpickStrategyModel> list = genDao.getListByNativeSql(sql,Wms_OutpickStrategyModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OutpickStrategyModel> extListBo = new ExtListDataBo<Wms_OutpickStrategyModel>(list,intCount);
		return extListBo;
	}

	/*
	 * @func 保存货主参数信息
	 * @param str 存放货主信息的json数据 
	 */
	@Override
	public void saveWmsOutPick(String str) throws Exception {
		Wms_OutpickStrategy OutPick=(Wms_OutpickStrategy)JSON.parseObject(str,Wms_OutpickStrategy.class);
		genDao.saveOrUpdateObj(OutPick);
	}
 
	 
}
