package com.sealinkin.auth.service.impl;

import java.util.Collection;
import java.util.List;
import java.io.InputStream;
import java.sql.Blob;

import com.sealinkin.bset.po.Bset_User_Role;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.auth.service.IAuthService;
import com.sealinkin.bset.model.Bset_MenuFolderModel;
import com.sealinkin.bset.model.Bset_RightListModel;
import com.sealinkin.bset.model.Bset_Role_ListModel;
import com.sealinkin.bset.model.Bset_User_RoleModel;
import com.sealinkin.bset.po.Bset_RoleList;
import com.sealinkin.cdef.model.Cdef_DefwareModel;
import com.sealinkin.comm.model.CountModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.ExtTreeLeafBo;
import com.sealinkin.comm.model.Home_ItemDModel;
import com.sealinkin.comm.model.Home_ItemListModel;
import com.sealinkin.comm.model.Home_ItemMModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.bdef.WMSRpogInfo;
import com.sealinkin.protocolExchange.bdef.WmsDbConfig;
import com.sealinkin.protocolExchange.comm.CommShowQtyModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.idata.IdataCheckRequestModel;
import com.sealinkin.protocolExchange.idata.IdataGetArticleInfoIDAnswerModel;
import com.sealinkin.util.algorithm.MysqlPasswd;
import com.sealinkin.bset.po.Bset_User_List;
import com.sealinkin.bset.po.Bset_RoleModule;

@SuppressWarnings({"unchecked","rawtypes"})
public class AuthImpl implements IAuthService{
	private IGenericManager genDao;
    
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 获取主菜单
	 */
	@Override
	public List<Bset_MenuFolderModel> getMenu(String strWorkerNo,String enterpriseNo)
			throws RuntimeException {
		String sql="select b.* from (  "+
				"select   "+
				"distinct c.PARENTFOLDERID,a.enterprise_no  "+
				"from bset_user_role a,  "+
				"bset_role_module b,  "+
				"bset_menu_folder c  "+
				"where a.worker_No='"+strWorkerNo+"'   "+
				"and a.role_id=b.role_id and a.enterprise_no=c.enterprise_no   "+
				"and b.flag=1   " +
				"and c.enable_flag='1' "+
				"and b.module_id=c.folder_id   "+
				")a,  "+
				"bset_menu_folder b  "+
				"where a.PARENTFOLDERID=b.folder_id " +
				"and a.enterprise_no=b.enterprise_no and b.enterprise_no='"+enterpriseNo+"' " +
				"and b.order_no not in '12' " +
				"and b.enable_flag='1' "+
				"order by b.ORDER_NO";
		List<Bset_MenuFolderModel> list=genDao.getListByNativeSql(sql, Bset_MenuFolderModel.class);
		return  list;
	}
	
	/**
	 * 获取子菜单
	 */
	@Override
	public List<ExtTreeLeafBo> getMenuItem(String strWorkerNo,String enterpriseNo,
			String strParentfolderid) throws RuntimeException {
		String strSql="select  "+
				"c.folder_id as id,  "+
				"c.menu_caption as text,  "+
				"(select  "+
				"case       "+
				"when COUNT(*)>0 then  'false'    "+
				"else 'true'   "+
				"end  as isfile     "+
				"from  "+
				"bset_menu_folder b,  "+
				"bset_module     "+
				"where  "+
				"b.parentfolderid=a.folder_id and b.enterprise_no='"+enterpriseNo+"'  )as leaf,  "+
				"b.MODULE_URL url,  "+
				"b.MODULE_CONTROLLER qtitle    "+
				"from  "+
				"(select  "+
				"distinct c.folder_id,c.parentfolderid,c.enterprise_no  "+
				"from  "+
				"bset_user_role a,  "+
				"bset_role_module b,  "+
				"bset_menu_folder c    "+
				"where  "+
				"a.worker_No='"+strWorkerNo+"'     "+
				"and a.role_id=b.role_id     "+
				"and b.flag=1     "+
				"and b.module_id=c.folder_id  " +
				"and a.enterprise_no=c.enterprise_no "+
				"and c.parentfolderid='"+strParentfolderid+"' "+
				")a,  "+
				"bset_module b,  "+
				"bset_menu_folder c    "+
				"where  "+
				"a.folder_id=b.module_id    "+
				"and a.folder_id=c.folder_id  " +
				"and a.parentfolderid=c.parentfolderid " +
				"and a.enterprise_no=c.enterprise_no " +
				"and b.module_enabled='1' and c.enterprise_no='"+enterpriseNo+"'  "+
				"order by c.order_no  ";
		List<ExtTreeLeafBo> list=genDao.getListByNativeSql(strSql, ExtTreeLeafBo.class);
		return  list;
	}
	
	/**
	 * 获取树
	 */
	@Override
	public List<ExtTreeLeafBo> getBset_ModuleTree(String strEnterpriseNo,String strNode)throws Exception {
		String sql="select " +
						"a.folder_id as id,  "+
						"a.menu_caption as text,  "+
						"(select case     "+
							"when COUNT(*)>0 then  'false'  "+
							"else 'true' end  as isfile   "+
						  "from " +
						  	"bset_menu_folder b,  "+
							"bset_module   "+
						  "where " +
						  	"b.parentfolderid=a.folder_id  " +
						  	"and b.enterprise_no='"+strEnterpriseNo+"' "+
						")as leaf,  "+
						"b.MODULE_URL url,   "+
						"MODULE_CONTROLLER qtitle  "+
					"from  (" +
						"select b.* " +
						"from " +
							"bset_menu_folder b " +
						"where " +
							"b.parentfolderid='0' " +
							"and b.enable_flag='1' " +
							"and b.enterprise_no='"+strEnterpriseNo+"' "+
						"union all " +
						"select b.* " +
						"from " +
							"bset_menu_folder b ," +
							"bset_module c " +
						"where " +
							"b.folder_id=c.module_id " +
							"and c.module_enabled='1' " +
							"and b.enterprise_no='"+strEnterpriseNo+"' " +
							"and b.enable_flag='1' "+
						") a   "+
						"left join bset_module b on a.folder_id=b.module_id  "+
					"where " +
						"1=1   "+
						"and a.parentfolderid='"+strNode+"' "+
					" order by a.order_no";
	    List<ExtTreeLeafBo> list = genDao.getListByNativeSql(sql,ExtTreeLeafBo.class);
	    return list;
	}
	
	/**
	 * 保存角色
	 */
	@Override
	public MsgRes saveRole(String strRole) throws Exception {
		Bset_RoleList brl=(Bset_RoleList) JSONObject.toBean(JSONObject.fromObject(strRole),Bset_RoleList.class);
		this.genDao.saveOrUpdateObj(brl);
		return new MsgRes(true,"保存成功","");
	}
	
	/**
	 * 获取角色列表
	 */
	@Override
	public ExtListDataBo<Bset_Role_ListModel> getBset_Role_List(String strEnterpriseNo,PageBo poPageBo)throws Exception {
		String strSql="select a.* " +
						"from " +
							"bset_role_list a " +
						"where " +
							"a.ENTERPRISE_NO='"+strEnterpriseNo+"' " +
						"order by role_id ";
		List<Bset_Role_ListModel> list = genDao.getListByNativeSql(
				strSql,Bset_Role_ListModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+strSql+")");
		ExtListDataBo<Bset_Role_ListModel> extListBo= new ExtListDataBo<Bset_Role_ListModel>(list, count);
        return extListBo;
	}
	/**
	 * 获取用户的角色列表
	 */
	@Override
	public ExtListDataBo<Bset_Role_ListModel> getBset_Role_User(String strEnterpriseNo,String whereSql,PageBo poPageBo)throws Exception {
		String strSql="select case when b.role_id is null then 'false' else 'true' end flag," +
				"a.role_id roleId,a.role_name roleName " +
				"from bset_user_role b , bset_role_list a  " +
				"where a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.role_id=b.role_id(+) " +
				"and b.worker_no(+)='"+whereSql+"' " +
				"order by a.role_id";
		List<Bset_Role_ListModel> list = genDao.getListByNativeSql(
				strSql,Bset_Role_ListModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+strSql+")");
		ExtListDataBo<Bset_Role_ListModel> extListBo= new ExtListDataBo<Bset_Role_ListModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 获取角色，用户列表
	 */
	@Override
	public ExtListDataBo<Bset_User_RoleModel> getBset_User_Role(String enterpriseNo,String strRoleId,PageBo poPageBo)throws Exception {
		String strSql="select case when b.worker_no is null then 'false' else 'true' end flag," +
				"a.worker_no,a.worker_name " +
				"from bdef_defworker a,bset_user_role b  " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.WORKER_NO=b.WORKER_No(+)  " +
				"and a.enterprise_no = b.enterprise_no(+) " +
				"and b.role_id(+)='"+strRoleId+"' ";
		String strTotsql="select count(1) " +
				"from bdef_defworker a,bset_user_role b  " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.WORKER_NO=b.WORKER_No(+)  " +
				"and a.enterprise_no = b.enterprise_no(+) " +
				"and b.role_id(+)='"+strRoleId+"' ";
		
		/*List<Bset_User_RoleModel> list = genDao.getListByNativeSql(
				strSql,Bset_User_RoleModel.class);*/
		List<Bset_User_RoleModel> list = genDao.getListByNativeSql(
				strSql,Bset_User_RoleModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		
		Integer count = genDao.getCountByNativeSql(strTotsql);
		
		ExtListDataBo<Bset_User_RoleModel> extListBo= new ExtListDataBo<Bset_User_RoleModel>(list, count);
		
        return extListBo;
	}
	
	/**
	 * 保存角色、用户信息
	 */
	@Override
	public MsgRes saveBset_User_Role(String strDetail) throws Exception {
		Collection<Bset_User_Role> bur=JSONArray.toCollection(JSONArray.fromObject(strDetail),Bset_User_Role.class);
		List<Bset_User_Role> list=(List<Bset_User_Role>)bur;
		deleteUserRole(list.get(0).getId().getRoleId().toString());
		this.genDao.saveList(list);
		return new MsgRes(true,"保存成功","");	
	}
	/**
	 * 保存用户角色信息
	 */
	@Override
	public MsgRes saveBset_Role_User(String strDetail) throws Exception{
		Collection<Bset_User_Role> bur = JSONArray.toCollection(JSONArray.fromObject(strDetail),Bset_User_Role.class);
		List<Bset_User_Role> list=(List<Bset_User_Role>)bur;
		deleteRoleUser(list.get(0).getId().getWorkerNo().toString());
		this.genDao.saveList(list);
		return new MsgRes(true,"保存成功",""); 
	}
	
	/**
	 * 删除用户角色信息
	 * @param strDetail
	 * @throws RuntimeException
	 */
	public void deleteRoleUser(String strDetail) throws RuntimeException{
		String sql = "delete from bset_user_role where worker_no ='"+strDetail+"'";
		genDao.updateBySql(sql);
	}
	
	/**
	 * 删除角色，用户信息
	 */
	@Override
	public void deleteUserRole(String strDetail) throws RuntimeException {
		String sql = "delete from bset_user_role where role_id ='"+strDetail+"'";
		genDao.updateBySql(sql);
	}
	
	/**
	 * 获取模块信息
	 */
	@Override
	public List<Bset_RightListModel> getBset_RightList(String strEnterpriseNo,String strWhereSql)throws Exception {
		String[] str_r_m=strWhereSql.split(",");
        String sql="select " +
	        			"a.*," +
	        			"decode(  "+
		        		"(  "+
		        		"select " +
		        		"distinct 1   "+
		        		"from " +
		        		"bset_role_module   "+
		        		"where " +
		        		"role_id="+str_r_m[0]+
		        		" and module_id=a.module_id   "+
		        		"and a.right_name = right_name   "+
		        		"and flag=1  "+
		        		"),null,'false','true') flag   "+
	        		"from " +
	        			"bset_rightlist a,  "+
		        		"(select " +
		        			"b.* " +
		        		"from " +
		        			"bset_menu_folder b " +
		        		"where " +
			        		"b.parentfolderid='0' " +
			        		"and b.enterprise_no='"+strEnterpriseNo+"' " +
		        		"union all " +
		        		"select " +
		        			"b.* " +
		        		"from " +
			        		"bset_menu_folder b ," +
			        		"bset_module c " +
		        		"where " +
			        		"b.folder_id=c.module_id " +
			        		"and b.enterprise_no='"+strEnterpriseNo+"' " +
			        		"and c.module_enabled='1'" +
		        		") b   "+
		        		"where " +
			        		"a.module_id =b.folder_id   "+
			        		"and a.status != 0    "+
			        		"and (b.folder_id = '"+str_r_m[1]+"' or b.parentfolderid = '"+str_r_m[1]+"')    "+
			        	"order by " +
			        		"b.order_no,  "+
			        		"a.order_no";
		List<Bset_RightListModel> list=genDao.getListByNativeSql(sql, Bset_RightListModel.class, 0, 1000);
		return  (List<Bset_RightListModel>)list;
	}
	
	/**
	 * 保存角色、模块权限信息
	 */
	@Override
	public MsgRes saveBset_Role_Module(String strDetail) throws Exception {
		Collection<Bset_RoleModule> bum=JSONArray.toCollection(JSONArray.fromObject(strDetail),Bset_RoleModule.class);
		List<Bset_RoleModule> list=(List<Bset_RoleModule>)bum;
		this.genDao.saveList(list);
		return new MsgRes(true,"保存成功","");
	}
	
	/**
	 * 获取登录用户模块权限信息
	 */
	@Override
	public List<Bset_RightListModel> getWorkerModuleRight(String strWorkerNo,String enterpriseNo,
			String strModuleId) throws Exception {
		String strSql="select a.MODULE_ID,"+
        		"       a.MODULE_NAME,"+
        		"       a.NAME,"+
        		"       a.RIGHT_NAME,"+
        		"       a.BUT_ID,"+
        		"       a.BUT_NAME,"+
        		"       case"+
        		"         when a.STATUS = 0 then"+
        		"          'false'"+
        		"         else"+
        		"          decode((select distinct 1"+
        		"                   from bset_role_module b, bset_user_role c"+
        		"                  where b.flag = 1"+
        		"                    and b.role_id = c.role_id"+
        		"                    and b.module_id = a.module_id"+
        		"                    and b.but_id = a.but_id"+
        		"                    and c.worker_no = '"+strWorkerNo+"' and c.enterprise_no='"+enterpriseNo+"' ),"+
        		"                 1,"+
        		"                 'true',"+
        		"                 'false')"+
        		"       end flag,"+
        		"       a.PARENT_ID"+
        		"  from bset_rightlist a"+
        		" where module_id ='"+strModuleId+"'";               
		List<Bset_RightListModel> list=genDao.getListByNativeSql(strSql, Bset_RightListModel.class, 0, 10000);
		return  (List<Bset_RightListModel>)list;
	}
	
	/**
	 * 记录登出日志表
	 */
	@Override
	public void updateLoginOut(String strWorkerNo) throws Exception {
		String sql="insert into bdef_deflogin_log(worker_no, login_type, login_time, remarks) " +
		"values ('"+strWorkerNo+"','-1',sysdate,'')";
		genDao.updateBySql(sql);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public MsgRes updateWorkerPass(String workerNo,String oldPass, String newPass)
			throws Exception {
		String sql="select * from bdef_defworker where worker_no='"+workerNo+"'";
		List<Bset_User_List> list=genDao.getListByNativeSql(sql,Bset_User_List.class);
		if(list.get(0).getPwd().equals(MysqlPasswd.MySQLPassword(oldPass))){
			list.get(0).setPwd(MysqlPasswd.MySQLPassword(newPass));
			genDao.saveOrUpdateObj(list.get(0));
		}else{
			return new MsgRes(false,"旧密码输入不对，修改失败！",null);
		}
		return new MsgRes(true,"修改成功！",null);
	}
	@Override
	public MsgRes getProcUpdateList() throws Exception {
		MsgRes msg=new MsgRes();
		String sql="select * from wms_proginfo where 1=1 ";
		List<WMSRpogInfo> list=genDao.getListByNativeSql(sql, WMSRpogInfo.class);
		if(list.size()>0){
			
			for(int i = 0; i<list.size(); i++)
			{
				Blob blob=  list.get(i).getProgbyte();
				InputStream in = blob.getBinaryStream();
				long nLen = blob.length();
                int nSize = (int) nLen;
                byte[] buf = new byte[nSize];
				in.read(buf);
				in.close();
				list.get(i).setByteInfo(buf);
				list.get(i).setProgbyte(null);
			}
			
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else{
			msg.setIsSucc(true);
			msg.setObj("没有需要更新的文件！");
		}
		return  msg;
	}
	@Override
	public MsgRes getProcUpdateListForRF() throws Exception {
		MsgRes msg=new MsgRes();
		String sql="select * from wms_proginfo where PROG_NAME='RF' ";
		List<WMSRpogInfo> list=genDao.getListByNativeSql(sql, WMSRpogInfo.class);
		if(list.size()>0){	
			for(int i = 0; i<list.size(); i++)
			{
				Blob blob=  list.get(i).getProgbyte();
				InputStream in = blob.getBinaryStream();
				long nLen = blob.length();
                int nSize = (int) nLen;
                byte[] buf = new byte[nSize];
				in.read(buf);
				in.close();
				list.get(i).setByteInfo(buf);
				list.get(i).setProgbyte(null);
			}
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else{
			msg.setIsSucc(true);
			msg.setObj("没有需要更新的文件！");
		}
		return  msg;
	}
	@Override
	public MsgRes GetDbConfig() throws Exception {
		MsgRes msg=new MsgRes();
		String sql="select * from wms_dbconfig a where a.connet_type like 'FTP%' ";
		List<WmsDbConfig> list=genDao.getListByNativeSql(sql, WmsDbConfig.class);
		if(list.size()>0){
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else{
			msg.setIsSucc(false);
			msg.setObj("缺少FTP配置信息！");
		}
		return  msg;
	}
	@Override
	public MsgRes getProgVerList() throws Exception {
		MsgRes msg=new MsgRes();
		String sql="select * from wms_proginfo where 1=1 ";
		List<WMSRpogInfo> list=genDao.getListByNativeSql(sql, WMSRpogInfo.class);
		if(list.size()>0){
			
			for(int i = 0; i<list.size(); i++)
			{
				list.get(i).setProgbyte(null);
			}
			
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else{
			msg.setIsSucc(true);
			msg.setObj("没有需要更新的文件！");
		}
		return  msg;
	}
	@Override
	public MsgRes getProgVerListForRF() throws Exception {
		MsgRes msg=new MsgRes();
		String sql="select * from wms_proginfo where PROG_NAME='RF' ";
		List<WMSRpogInfo> list=genDao.getListByNativeSql(sql, WMSRpogInfo.class);
		if(list.size()>0){	
			for(int i = 0; i<list.size(); i++)
			{
				list.get(i).setProgbyte(null);
			}
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else{
			msg.setIsSucc(true);
			msg.setObj("没有需要更新的文件！");
		}
		return  msg;
	}
	
		
	//获取home_item_m
	@Override
	public List<Home_ItemMModel> getHomeData(String enterpriseNo,
				String warehouseNo) {
			
		String sql="select a.item_id,a.item_name " +
				   "  from home_item_m a" +
				   " where a.enterprise_no='"+enterpriseNo+"' ";
		List<Home_ItemMModel> list =genDao.getListByNativeSql(sql,Home_ItemMModel.class);
		return list;
	}
		
	//统计各个模块单的数量
	@Override
	public List<CountModel> getCount(String enterpriseNo,
			String warehouseNo, String ownerNo, String itemId) {
		String sql="select a.item_sql " +
				   "  from home_item_m a" +
				   " where a.enterprise_no='"+enterpriseNo+"'" +
				   "   and a.item_id='"+itemId+"' ";
			
		List<String> list =genDao.getListByNativeSql(sql);
			
		String countSql=list.get(0);
		countSql=countSql.replace("{0}", "enterprise_no='"+enterpriseNo+"'");
		countSql=countSql.replace("{1}", "warehouse_no='"+warehouseNo+"'");
		countSql=countSql.replace("{2}", "owner_no in ("+ownerNo+")");
			
		List<CountModel> result =genDao.getListByNativeSql(countSql, CountModel.class);	
		return result;
	}
		
	@Override
	public List<Home_ItemListModel> getGridColumModle(String subItemId)
			throws Exception {
		 String sql="select regexp_replace(lower(field_id),'_','') field_id,field_name,FIELD_TYPE," +
						"width from home_item_list where sub_item_id='"+subItemId+"' order by seq";
			List<Home_ItemListModel> list=genDao.getListByNativeSql(sql, Home_ItemListModel.class);
			return list;
	}
		
	@Override
	public ExtListDataBo getGridData(String enterpriseNo,
			String warehouseNo, String ownerNo, String subItemId,
			PageBo pageBo) {
			
		String preparedSql="select * from home_item_d a where a.sub_item_id ='"+subItemId+"'";
		List<Home_ItemDModel> listM= genDao.getListByNativeSql(preparedSql, Home_ItemDModel.class);
			
		String sql = listM.get(0).getDetailSql();
		sql=sql.replace("{0}", "enterprise_no='"+enterpriseNo+"'");
		sql=sql.replace("{1}", "warehouse_no='"+warehouseNo+"'");
		sql=sql.replace("{2}", "owner_no in ("+ownerNo+")");
			
		List list =null; 
		Integer count = 0; 
		ExtListDataBo<Cdef_DefwareModel> extListBo=null;
			
		list = genDao.getListByNativeSql(sql);
		count = list.size();
		extListBo= new ExtListDataBo<Cdef_DefwareModel>(list, count);	
		return extListBo;
	}

	//获取RF系统允许显示的数量信息 Add by sunl
	@Override
	public MsgRes GetQtyShow(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
	
		//查询出配置信息,能查询出的都是不用显示的信息。
		String strSql=" SELECT V.FIELD_NAME FieldEX1 FROM WMS_MODULE_FIELD_VAULE V " +
				"WHERE V.ENTERPRISE_NO = '"+reqMod.getEnterpriseNo()+"'" +
				" AND V.WAREHOUSE_NO = '"+reqMod.getWarehouseNo()+"' " +
				" AND V.MODULE_ID = '"+reqMod.getReqObj()+"' AND V.FLAG = '0' ";
		
		List<CommSingleDataRequestModel> list = genDao.getListByNativeSql(strSql,CommSingleDataRequestModel.class);	
		
		CommShowQtyModel sh = new CommShowQtyModel();
		sh.setShowBox(true);
		sh.setShowQmin(true);
		sh.setShowBase(true);
		if(list.size() > 0){
			for(int i = 0; i<list.size(); i++)
			{
				String s1 = list.get(i).getFieldEX1().toUpperCase();
				if(s1.equals("PLANBOX"))//整箱
				{
					sh.setShowBox(false);
				}
				if(s1.equals("PLANQMIN"))//最小操作包装
				{
					sh.setShowQmin(false);
				}
				if(s1.equals("PLANDIS"))//基本包装
				{
					sh.setShowBase(false);
				}
			}
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(sh));
		msgRes.setMsg("配置获取成功");
		return msgRes;
	}
	
	//根据员工代码获得员工列表   8-15
	@Override 
	public ExtListDataBo<Bset_User_RoleModel> getWorkerListByWorkerNo(
			String enterpriseNo, String strRoleId,String strWorkerNo, PageBo poPageBo)
			throws Exception {
		String strSql="select case when b.worker_no is null then 'false' else 'true' end flag," +
				"a.worker_no,a.worker_name " +
				"from bdef_defworker a,bset_user_role b  " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				"and a.WORKER_NO=b.WORKER_No(+)  " +
				"and a.enterprise_no = b.enterprise_no(+) " +
				"and b.role_id(+)='"+strRoleId+"' ";
		if(strWorkerNo != null && !strWorkerNo.equals("")){
			strSql = strSql + "and a.worker_no like '%" + strWorkerNo + "%'";
		}
		String strTotsql="select count(1) " +
				"from (" + strSql + ")"; 
				
		List<Bset_User_RoleModel> list = genDao.getListByNativeSql(
				strSql,Bset_User_RoleModel.class);
		
		Integer count = genDao.getCountByNativeSql(strTotsql);
		
		ExtListDataBo<Bset_User_RoleModel> extListBo= new ExtListDataBo<Bset_User_RoleModel>(list, count);
		
        return extListBo;
	}	
}































