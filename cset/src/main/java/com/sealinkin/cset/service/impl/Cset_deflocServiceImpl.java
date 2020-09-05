package com.sealinkin.cset.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DeflocModel;
import com.sealinkin.bdef.po.Bdef_Defloc;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.service.ICset_deflocService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class Cset_deflocServiceImpl implements ICset_deflocService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return this.genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	public ExtListDataBo<Bdef_DeflocModel> getLoc(String currEnterpriseNo,
			PageBo pageBo, String strQuery) throws Exception {

		String sql="select a.*," +
				   "f_get_fieldtext('N','CREATE_FLAG',a.create_flag) creatFlagText "+
				   " from bdef_defloc a where 1=1 "+
				   " and a.enterprise_no ='"+currEnterpriseNo+"' ";
				
		String strTotsql = "select count(1) from bdef_defloc a where 1=1 "+
				" and a.enterprise_no ='"+currEnterpriseNo+"' ";
						
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		
		List<Bdef_DeflocModel> list = genDao.getListByNativeSql(sql,Bdef_DeflocModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bdef_DeflocModel> extListBo = new ExtListDataBo<Bdef_DeflocModel>(list,intCount);
		return extListBo;	
	}

	@Override
	public MsgRes saveOrUpdateDefloc(String str) throws Exception {
		
		Bdef_Defloc loc = (Bdef_Defloc) JSON.parseObject(str,Bdef_Defloc.class);
		this.genDao.saveOrUpdateObj(loc);
		return new MsgRes(Boolean.valueOf(true),"", null);
	}

}
