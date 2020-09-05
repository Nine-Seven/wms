package com.sealinkin.auth.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.sealinkin.auth.service.IAuthService;
import com.sealinkin.bset.model.Bset_MenuFolderModel;
import com.sealinkin.bset.model.Bset_RightListModel;
import com.sealinkin.bset.model.Bset_User_RoleModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.CountModel;
import com.sealinkin.comm.model.ExtTreeLeafBo;
import com.sealinkin.comm.model.Home_ItemListModel;
import com.sealinkin.comm.model.Home_ItemMModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.bset.model.Bset_Role_ListModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.HttpService;

@SuppressWarnings({"rawtypes","unchecked"})
public class AuthAction extends CommAction{

	private static final long serialVersionUID = -4879515750756634174L;
	private IAuthService authImpl;
	private String node;
	private String strRole;
	private String strRoleId;
	private String strDetail;
	private String strWhereSql;
	private String strModuleId;
	private String strQuery;
	private String strOldPass;
	private String strNewPass;
	private String subItemId;
	private String itemId;
	
	private String strWorkerNo;   //员工代码
	
	public String login(){
		return "success";
	}
	
	/**
	 * 获取菜单
	 */
	public void getMenu(){
		try{
	        List<Bset_MenuFolderModel> list = authImpl.getMenu(super.getMdBdef_DefWorker().getWorkerNo(),super.getMdBdef_DefWorker().getCurrEnterpriseNo());
	        String[] menu=new String[list.size()];
	        for(int i=0;i<list.size();i++){
	    	    List<ExtTreeLeafBo> etl = authImpl.getMenuItem(
	    	    		super.getMdBdef_DefWorker().getWorkerNo(),
	    	    		super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
	    	    		list.get(i).getFolderId().toString());
	    	    menu[i]=JSONArray.fromObject(etl).toString();
	        }
			super.writeObj(new MsgRes(true,JSONArray.fromObject(list).toString(),JSONArray.fromObject(menu).toString()));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取一级菜单
	 */
	public void getOneLevelMenu(){
		try{
	        List<Bset_MenuFolderModel> list = authImpl.getMenu(super.getMdBdef_DefWorker().getWorkerNo(),
	        		super.getMdBdef_DefWorker().getCurrEnterpriseNo());	       
			super.writeObj(new MsgRes(true,JSONArray.fromObject(list).toString(),""));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	

	/**
	 * 获取权限树
	 */
	public void getBset_ModuleTree(){
		try {
			List<ExtTreeLeafBo> list =authImpl.getBset_ModuleTree(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					node);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存角色
	 */
	public void saveBset_Role_List(){
		try{					
			MsgRes msg=authImpl.saveRole(strRole);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败！", ""));
		}
	}
	
	/**
	 * 获取角色信息
	 */
	public void getBset_Role_List(){
		try{		
			PageBo poPageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_Role_ListModel> list=authImpl.getBset_Role_List(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户，角色信息
	 */
	public void getBset_Role_User(){
		try{		
			PageBo poPageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_Role_ListModel> list=authImpl.getBset_Role_User(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWhereSql,poPageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取角色，用户信息
	 */
	public void getBset_User_Role(){
		try{		
			PageBo poPageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_User_RoleModel> list=authImpl.getBset_User_Role(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strRoleId,poPageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据员工代码获得员工列表   8-15
	public void getWorkerListByWorkerNo(){
		try{		
			PageBo poPageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_User_RoleModel> list=authImpl.getWorkerListByWorkerNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strRoleId,
					this.getStrWorkerNo(), 
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存角色、用户信息
	 */
	public void saveBset_User_Role(){
		try{		
			System.out.println("strDetail:"+strDetail);
			MsgRes msg = authImpl.saveBset_User_Role(strDetail);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除角色、用户信息
	 */
	public void deleteUserRole(){
		try{					
			authImpl.deleteUserRole(strDetail);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	/**
	 * 保存用户角色信息
	 */
	public void saveBset_Role_User(){
		try{					
			MsgRes msg = authImpl.saveBset_Role_User(strDetail);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除用户角色信息
	 */
	public void deleteRoleUser(){
		try{					
		 authImpl.deleteRoleUser(strDetail);
		 super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	/**
	 * 获取模块信息
	 */
	public void getBset_RightList() {
		try{
	        List<Bset_RightListModel> list =authImpl.getBset_RightList(
	        		super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
	        		strWhereSql);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存角色、模块权限信息
	 */
	public void saveBset_Role_Module(){
		try{					
			MsgRes msg = authImpl.saveBset_Role_Module(strDetail);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取登录用户模块权限信息
	 */
	public void getWorkerModuleRight() {
		try{
	        List<Bset_RightListModel> list = authImpl.getWorkerModuleRight(
	        		super.getMdBdef_DefWorker().getWorkerNo(),
	        		super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
	        		strModuleId);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注销系统
	 */
	public void loginOut(){
		try {
			authImpl.updateLoginOut(super.getMdBdef_DefWorker().getWorkerNo());
			HttpService.removeToSession("loginUser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码
	 */
	public void updateWorkerPass(){
		try {
			MsgRes msg=authImpl.updateWorkerPass(super.getMdBdef_DefWorker().getWorkerNo(), strOldPass, strNewPass);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "修改失败！", ""));
		}
	}
	
	//统计各个模块单的数量
	public void getCount(){			
		try{
		       List<CountModel> list = authImpl.getCount(
		        	super.getMdBdef_DefWorker().getEnterpriseNo(),
		        	super.getMdBdef_DefWorker().getWarehouseNo(),
		        	super.getMdBdef_DefWorker().getOwnerNo(),
		        	this.getItemId());
			   super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//获取home_item_m
	public void getHomeData(){
		try{
			System.out.println("EnterpriseNo:"+super.getMdBdef_DefWorker().getEnterpriseNo());
			System.out.println("WarehouseNo:"+super.getMdBdef_DefWorker().getWarehouseNo());
	        List<Home_ItemMModel> list = authImpl.getHomeData(
	        		super.getMdBdef_DefWorker().getEnterpriseNo(),
	        		super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	//获取网格头
	public void getGridColumModle(){
		try {
			List<Home_ItemListModel> list = authImpl.getGridColumModle(
					this.subItemId);
			if (list == null) {
				super.writeObj(new MsgRes(false, "", ""));
			} else {
				StringBuilder sbColumModle = new StringBuilder("'columModle':[");
				StringBuilder sbFieldsNames = new StringBuilder(
						"'fieldsNames':[");
				for (int i = 0; i < list.size(); i++) {
					sbColumModle.append("{'header': '"
							+ list.get(i).getFieldName() + "'");
					sbColumModle.append(",'width': " + list.get(i).getWidth()
							+ "");
					sbColumModle.append(",'dataIndex': 'col" + i + "'");
					sbColumModle.append(",'type': '"
							+ list.get(i).getFieldType() + "'");

					sbFieldsNames.append("{'name': 'col" + i + "'");
					if (i == list.size() - 1) {
						sbColumModle.append("}]");
						sbFieldsNames.append("}]");
					} else {
						sbColumModle.append("},");
						sbFieldsNames.append("},");
					}
				}
				super.writeObj(new MsgRes(true, "", sbColumModle.append(",")
						.toString() + sbFieldsNames.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	//获取网格内容
	public void getGridData() {
		PageBo pageBo = new PageBo(getStart(), getLimit());
		
		ExtListDataBo extListBo = authImpl.getGridData(
				super.getMdBdef_DefWorker().getEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				super.getMdBdef_DefWorker().getOwnerNo(),
				this.subItemId,
				pageBo);
	
		List list = new ArrayList(extListBo.getRootList());
		if (list == null || list.size() == 0) {
			super.writeObj(new MsgRes(false, "", ""));
		}else{

			// super.writeStr(covtObjectToJson(list));
			StringBuilder sbData = new StringBuilder("{'rootList':[");
			StringBuilder sbTotalCount = new StringBuilder(
					"'totalCount':" + extListBo.getTotalCount());
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof Object[]) {
					Object[] obj = (Object[]) list.get(i);
					for (int j = 0; j < obj.length; j++) {
						if (j == 0) {
							sbData.append("{'col");
						} else {
							sbData.append("'col");
						}
						sbData.append(j);
						sbData.append("':'");
						sbData.append(obj[j] == null ? "" : obj[j]);

						if (i == list.size() - 1 && j == obj.length - 1) {
							sbData.append("'}]");
						} else if (j == obj.length - 1) {
							sbData.append("'},");
						} else if (j != obj.length - 1) {
							sbData.append("',");
						}
					}
					sbData.append("\r\n");
				}
			}
			String aa = sbData.toString() + ","
					+ sbTotalCount.toString() + "}";
			super.writeObj(aa);
		
		}
	}
	
	public void setAuthImpl(IAuthService authImpl) {
		this.authImpl = authImpl;
	}

	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}

	public String getStrRole() {
		return strRole;
	}
	public void setStrRole(String strRole) {
		this.strRole = strRole;
	}

	public String getStrRoleId() {
		return strRoleId;
	}
	public void setStrRoleId(String strRoleId) {
		this.strRoleId = strRoleId;
	}

	public String getStrDetail() {
		return strDetail;
	}
	public void setStrDetail(String strDetail) {
		this.strDetail = strDetail;
	}

	public String getStrWhereSql() {
		return strWhereSql;
	}
	public void setStrWhereSql(String strWhereSql) {
		this.strWhereSql = strWhereSql;
	}

	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrOldPass() {
		return strOldPass;
	}
	public void setStrOldPass(String strOldPass) {
		this.strOldPass = strOldPass;
	}

	public String getStrNewPass() {
		return strNewPass;
	}
	public void setStrNewPass(String strNewPass) {
		this.strNewPass = strNewPass;
	}

	public String getSubItemId() {
		return subItemId;
	}

	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getStrWorkerNo() {
		return strWorkerNo;
	}

	public void setStrWorkerNo(String strWorkerNo) {
		this.strWorkerNo = strWorkerNo;
	}
	
}
