package com.sealinkin.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sealinkin.auth.service.ILogin;
import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.bset.model.Bset_Worker_LocModel;
import com.sealinkin.bset.po.Bset_User_List;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.biz.role.StuPermission;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.LoginDataChange.LoginAnswerModel;
import com.sealinkin.protocolExchange.comm.EmployeeOwnerPermissions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({"rawtypes","unchecked"})
@WebService
public class LoginImpl implements ILogin {
	
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public MsgRes login(String name, String pass,String enterpriseNo) 
	{
		/*String sql="select a.*,(" +
				"select " +
				"case when sdefine='1' " +
				"then (select wmsys.wm_concat(owner_no) " +
				"from bset_worker_owner " +
				"where worker_no=a.worker_no) " +
				"else (select wmsys.wm_concat(owner_no) " +
				"from bdef_defowner) end " +
				"from WMS_DEFBASE " +
				"where colname='LotNewDefined') as worker_owner " +
				"from bdef_defworker a " +
				"where worker_no='"+name+"' and pwd='"+pass+"'";*/
		/*用户登录时调整，判断是否是公有员工，是则取公有货主和已经维护权限的私有货主合集。否则取已经维护权限的私有货主*/
		String sql="select a.*, a.enterprise_no as currenterpriseno, " +
				"case when a.authority_type='1' " +
				"then (" +
					"select to_char(wmsys.wm_concat(''''||owner_no||'''')) owner_no " +
					"from (" +
						"select distinct b.owner_no " +
						"from bdef_defowner b,bdef_defworker d " +
						"where b.enterprise_no=d.enterprise_no " +
						"and d.worker_no='"+name+"'" +
						"and b.authority_type=1 " +
						"union " +
						"select distinct b.owner_no owner_no " +
						"from bset_worker_owner b,bdef_defworker d " +
						"where b.enterprise_no=d.enterprise_no " +
						"and d.worker_no=d.worker_no " +
						"and b.worker_no='"+name+"'" +
					")" +
				") else (" +
					"select to_char(wmsys.wm_concat(''''||owner_no||'''')) owner_no " +
					"from bset_worker_owner " +
					"where worker_no=a.worker_no and enterprise_no=a.enterprise_no" +
				") end as workerowner " +
				"from bdef_defworker a " +
				"where worker_no='"+name+"' and pwd='"+pass+"' %s1 ";
		if(StringUtils.isNotBlank(enterpriseNo)){
			sql = sql.replace("%s1", " and a.enterprise_no='"+enterpriseNo+"' ");
		}else{//如果为空就取企业表默认的企业号
			sql = sql.replace("%s1", " and a.enterprise_no=(select t.enterprise_no from bdef_defenterprise t where t.default_flag='0') ");
		}
		List<Bdef_DefWorkerModel> workerModelList = genDao.getListByNativeSql(sql, Bdef_DefWorkerModel.class);

		if(workerModelList == null || workerModelList.isEmpty())
		{
			return new MsgRes(false,"用户名或密码错误，请重新输入！",null);
		}else{
            Bdef_DefWorkerModel workerModel = workerModelList.get(0);
            String status = workerModel.getStatus().toString();
			if(Integer.parseInt(status)==0)
			{
				return new MsgRes(false,"该用户被禁用，请联系管理员！",null);
			}
			
			List<Bset_Worker_LocModel> listLoc = getBset_Worker_Loc(name,enterpriseNo);
			if(listLoc.isEmpty())
			{
				return new MsgRes(false,"暂未分配仓别访问权限",workerModelList);
			}
			updateLoginLog(name);
			return new MsgRes(true,"登录成功！", workerModel);
		}
	}
	/**
	 * 更新登录日志
	 * @param workerNo
	 */
	@WebMethod
    @Override
	public void updateLoginLog(String workerNo) {
		String sql="insert into " +
				"bdef_deflogin_log(" +
				"worker_no," +
				"login_type," +
				"login_time," +
				"remarks) " +
				"values ('"+workerNo+"','1',sysdate,'')";
		genDao.updateBySql(sql);
	}

	@Override
	public List<Bset_Worker_LocModel> getBset_Worker_Loc(String name,String strEnterpriseNo) {
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);*/
		//List list = genDao.getListByParm("bset_getBset_Worker_Loc", map, Bset_Worker_LocModel.class);
		String strSql="select a.*,'['||a.warehouse_no||']'||b.warehouse_name warehouse_name " +
				"from bset_worker_loc a,bdef_defloc b " +
				"where a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.worker_no = '" + name + "' %s1 ";
		
		if(strEnterpriseNo!=null && !strEnterpriseNo.equals("")){
			strSql = strSql.replace("%s1", " and b.enterprise_no='"+strEnterpriseNo+"' ");
		}else{//如果为空就取企业表默认的企业号
			strSql = strSql.replace("%s1", " and b.enterprise_no=(select t.enterprise_no from bdef_defenterprise t where t.default_flag='0') ");
		}
		List<Bset_Worker_LocModel> list=genDao.getListByNativeSql(strSql, Bset_Worker_LocModel.class);
		return list;
	}

	@Override
	public void updateLoginOut(String workerNo) {
		String sql="insert into bdef_deflogin_log(worker_no, login_type, login_time, remarks) " +
				"values ('"+workerNo+"','-1',sysdate,'')";
				genDao.updateBySql(sql);
	}

	@Override
	public MsgRes rfLogin(String name, String pass,String enterpriseNo) {
		MsgRes msgRes=new MsgRes();
		//登录
		msgRes=login(name, pass,enterpriseNo);
		if(!msgRes.getIsSucc())
		{
			return msgRes;
		}
		//将用户货主权限添加到map对象
		Bdef_DefWorkerModel workMod=(Bdef_DefWorkerModel)msgRes.getObj();
		
		if(EmployeeOwnerPermissions.getEomap().containsKey(workMod.getWorkerNo()))
		{
			EmployeeOwnerPermissions.getEomap().remove(workMod.getWorkerNo());
		}
		EmployeeOwnerPermissions.getEomap().put(workMod.getWorkerNo(),workMod.getWorkerOwner());
		//取权限列表
		

		String strSql="select "
						+"  distinct  '001' as LOCNO, "
						+"  c.but_id strFunno, "
						+"  c.but_id nFunID, "
						+"  '1' FUNOWNER, "
						+"  c.right_name strFunname, "
						+"  '2' nPurview_type,c.order_no   "
						+"from "
						+"  bset_user_role a, "
						+"  bset_role_module b, "
						+"  bset_rightlist c "
						+"where "
						+"  a.worker_No='"+name+"'    "
						+"  and a.role_id=b.role_id    "
						+"  and b.flag=1    "
						+"  and b.module_id=c.module_id "
						+"  and b.but_id=c.but_id   "
						+"  and c.terminal_flag=2  " +
						"   and c.status!=0 "
						+"order by c.order_no ";		 
		List<StuPermission> list = genDao.getListByNativeSql(strSql,StuPermission.class);
		if(list.size()>0)
		{
			Integer i=100;
			Map<String, StuPermission> aMap=new HashMap<String, StuPermission>();
	        for (StuPermission stuPer : list) {
	        	aMap.put(i.toString(), stuPer);
	        	i++;
			}
	        LoginAnswerModel loginAnsMod=new LoginAnswerModel();
	        //loginAnsMod.setStrSessionID("ddddddd");
	        loginAnsMod.setStrOwnerPermissions(workMod.getWorkerOwner());
	        loginAnsMod.setArrStuCpermission(aMap);    
	        
	        msgRes.setIsSucc(true);
	        msgRes.setObj(JSONObject.fromObject(loginAnsMod).toString());
		}else
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("未设置权限，请分配权限！");
		}
		return msgRes;
	}
	/**
	 * 修改密码
	 */
	@Override
	public MsgRes updatePWD(String WorkerNo, String passWord, String NewPwd) {
		String checkSql="select *　from BDEF_DEFWORKER a where a.pwd='"+passWord+"'" +
				"and a.worker_no='"+WorkerNo+"' ";
		List<Bset_User_List> list=genDao.getListByNativeSql(checkSql,Bset_User_List.class);
		if(list.size()<=0){
			return new MsgRes(false,"初始密码有误错误，修改失败！",null);
		}
		
		String sqls="update BDEF_DEFWORKER set PWD='"+NewPwd+"' where WORKER_NO='"+WorkerNo+"' ";
		
		/**String Sql="UPDATE" +
				"BDEF_DEFWORKER SET ADDRESS=?," +
				"DEPT_NAME=?," +
				"OPERATE_STATUS=?," +
				"OWNER_NO=?," +
				"PWD=?," +
				"RGST_DATE=?," +
				"RGST_NAME=?," +
				"SEX=?,STATUS=?," +
				"TEL=?,TITLE=?," +
				"UPDT_DATE=?," +
				"UPDT_NAME=?," +
				"WAREHOUSE_NO=?," +
				"WORKER_NAME=?," +
				"WORKER_RFID=? " +
				"WHERE " +
				"WORKER_NO=? " +
				"AND" +
				" enterPrise_no=?";*/
		genDao.exceuteSql(sqls);
		
		
		

			return new MsgRes(true,"修改成功！",list.get(0));
	
  }
}
	
