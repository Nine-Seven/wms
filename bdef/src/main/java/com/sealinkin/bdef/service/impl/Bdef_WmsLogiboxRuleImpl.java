package com.sealinkin.bdef.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.bdef.model.Wms_LogiboxRuleModel;
import com.sealinkin.bdef.po.Wms_LogiboxRule;
import com.sealinkin.bdef.service.IBdef_WmsLogiboxRuleService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Bdef_WmsLogiboxRuleImpl implements IBdef_WmsLogiboxRuleService{
	
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//获得物流箱规则资料
	@Override
	public ExtListDataBo<Wms_LogiboxRuleModel> getWms_LogiboxRule(String enterpriseNo,
			String strWorkerOwner,String strWorkerNo,String strQuery, PageBo pageBo,
			Integer requestFlag) throws Exception {
		String strSql="select a.* ,a.Rule_Id ruleId "+
				", '['|| ltrim(a.ALLOT_RULE)||']'||f_get_fieldtext('WMS_LOGIBOX_RULE','ALLOT_RULE',a.ALLOT_RULE)allotRuleText "+
				", '['|| ltrim(a.AREA_RULE)||']'||f_get_fieldtext('WMS_LOGIBOX_RULE','AREA_RULE',a.AREA_RULE)areaRuleText "+
				", '['|| ltrim(a.VOL_FLAG)||']'||f_get_fieldtext('WMS_LOGIBOX_RULE','VOL_FLAG',a.VOL_FLAG)volFlagText "+
				", '['|| ltrim(a.WEIGHT_FLAG)||']'||f_get_fieldtext('WMS_LOGIBOX_RULE','WEIGHT_FLAG',a.WEIGHT_FLAG)weightFlagText "+
				", '['|| ltrim(a.ONEDELIVERONEPICK)||']'||f_get_fieldtext('WMS_LOGIBOX_RULE','ONEDELIVERONEPICK',a.ONEDELIVERONEPICK)onedeliveronepickText"+ 
				", '['|| ltrim(a.SPLITBOX_FLAG)||']'||f_get_fieldtext('WMS_LOGIBOX_RULE','SPLITBOX_FLAG',a.SPLITBOX_FLAG)splitboxFlagText "+
				"from wms_logibox_rule a"+
				"  where enterprise_no='"+enterpriseNo+"' " ;
 
	String strTotSql = "select count(*) from (" + strSql + ") " ;	
	List<Wms_LogiboxRuleModel> list = null;
	list = genDao.getListByNativeSql(strSql,Wms_LogiboxRuleModel.class,pageBo.getStart(), pageBo.getPagesize());
	
	Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		

	ExtListDataBo<Wms_LogiboxRuleModel> extListBo= new ExtListDataBo<Wms_LogiboxRuleModel>(list, intCount);
	return extListBo;
	}

	/**
	 * 保存或修改物流箱规则
	 * @param strW
	 * @return
	 * @throws Exception
	 */

	@Override
	public MsgRes saveOrUpdateWmsLogiboxRule(String strBo, String strWorkNo)
			throws Exception {
		String strSql="";
		Wms_LogiboxRule ownerBo=(Wms_LogiboxRule)JSONObject.toBean(JSONObject.fromObject(strBo),Wms_LogiboxRule.class);
		//List<Wms_LogiboxRuleModel> list=genDao.getListByNativeSql(strSql, Wms_LogiboxRuleModel.class);
		genDao.saveOrUpdateObj(ownerBo);
		return new MsgRes(true,"savesuccess",null);
	}


	/**
	 * 获取物流箱规则下拉数据	
	 * @param strWorkerOwner
	 * @param strW
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ComboxBo> getWms_LogiboxRuleModelCombo(String strWorkerOwner,
			String strW) throws Exception {
		return null;
	}

	/**
	 * 新增时检查物流箱规则编码是否存在
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */

	@Override
	public MsgRes existsOwnerNo(String enterpriseNo,String strOwnerNo) throws Exception {
		String strSql="select * from Wms_LogiboxRuleModel a where a.owner_no='"+strOwnerNo+"' "+
				      " and enterprise_no='"+enterpriseNo+"'";
		List<Wms_LogiboxRuleModel> list=genDao.getListByNativeSql(strSql, Wms_LogiboxRuleModel.class);
		if(list.size()!=0)
		{
			return new MsgRes(false,"no_exists",null);
		}
		return new MsgRes(true,"",null);
	}
	
 
	@Override
	public MsgRes deleteWmsLogiboxRule(String currEnterpriseNo, String str)
			throws Exception {
		 
		String deleteSql=" delete from wms_logibox_rule a " +
				         "  where a.enterprise_no='"+currEnterpriseNo+"' " +
				         "    and a.RULE_ID='"+str+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

 
	 
	
}
