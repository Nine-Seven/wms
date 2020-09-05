package com.sealinkin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.report.service.IAuto_Service;
import com.sealinkin.wms.model.Wms_Defcustom_DModel;

@SuppressWarnings({"unchecked","rawtypes"})
public class Auto_Impl implements IAuto_Service {
private IGenericManager genDao;
    
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }
	
	//获取显示明细字段
	@Override
	public List<Wms_Defcustom_DModel> getCombo(String enterpriseNo,
			String customId) throws Exception {
		String sql=" select a.custom_id,a.param_name,a.param_type " +
				   "   from wms_defcustom_d a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.custom_id='"+customId+"' ";
		List<Wms_Defcustom_DModel> list = genDao.getListByNativeSql(sql, Wms_Defcustom_DModel.class);
		return list;
	}
	@Override
	public MsgRes implement(String enterpriseNo, String warehouseNo,
			String customId, String value0, String value1, String value2,
			String value3, String value4, String value5, String value6,
			String value7, String value8, String value9) throws Exception {
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
			
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(customId);
		inList.add(value0);
		inList.add(value1);
		inList.add(value2);
		inList.add(value3);
		inList.add(value4);
		inList.add(value5);
		inList.add(value6);
		inList.add(value7);
		inList.add(value8);
		inList.add(value9);
				
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_USER_CUSTOM.p_main");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"执行成功！","");
	}
}
