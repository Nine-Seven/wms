package com.sealinkin.auth.service;

import java.util.List;

import com.sealinkin.bset.model.Bset_MenuFolderModel;
import com.sealinkin.comm.model.CountModel;
import com.sealinkin.comm.model.ExtTreeLeafBo;
import com.sealinkin.comm.model.Home_ItemListModel;
import com.sealinkin.comm.model.Home_ItemMModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.bset.model.Bset_Role_ListModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.bset.model.Bset_User_RoleModel;
import com.sealinkin.bset.model.Bset_RightListModel;

@SuppressWarnings("rawtypes")
public interface IAuthService {
	
	/**
	 * 获取主菜单
	 * @param strWorkerNo
	 * @return
	 * @throws RuntimeException
	 */
	public List<Bset_MenuFolderModel> getMenu(String strWorkerNo,String enterpriseNo) throws RuntimeException;
	
	/**
	 * 获取菜单的子菜单
	 * @param strWorkerNo
	 * @param strParentfolderid
	 * @return
	 * @throws RuntimeException
	 */
	public List<ExtTreeLeafBo> getMenuItem(String strWorkerNo,String enterpriseNo,
			String strParentfolderid) throws RuntimeException;
	
	/**
	 * 获取树
	 * @param strNode
	 * @return
	 */
	public List<ExtTreeLeafBo> getBset_ModuleTree(String strEnterpriseNo,String strNode)throws Exception;
	
	
	/**
	 * 保存角色
	 * @param strRole
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveRole(String strRole)throws Exception;
	
	/**
	 * 获取用户的角色列表
	 */
	public ExtListDataBo<Bset_Role_ListModel> getBset_Role_User(String strEnterpriseNo,String whereSql,PageBo poPageBo)throws Exception;
	
	//根据员工代码获得员工列表 8-15
	public ExtListDataBo<Bset_User_RoleModel> getWorkerListByWorkerNo(String enterpriseNo, String strRoleId, String strWorkerNo, PageBo poPageBo)throws Exception;
	
	/**
	 * 获取角色列表
	 * @param strQuery 
	 * @param poPageBo
	 * @return
	 */
	public ExtListDataBo<Bset_Role_ListModel> getBset_Role_List(String strEnterpriseNo,PageBo poPageBo)throws Exception;
	
	/**
	 * 获取角色、用户信息
	 * @param strRoleId 
	 * @param poPageBo
	 * @return
	 */
	public ExtListDataBo<Bset_User_RoleModel> getBset_User_Role(String enterpriseNo, String strRoleId, PageBo poPageBo)throws Exception;
	
	/**
	 * 保存角色、用户信息
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveBset_User_Role(String strDetail)throws Exception;
	
	/**
	 * 保存用户角色信息
	 */
	public MsgRes saveBset_Role_User(String strDetail) throws Exception;
	
	/**
	 * 删除角色，用户信息
	 * @param strDetail
	 * @throws RuntimeException
	 */
	public void deleteUserRole(String strDetail) throws RuntimeException;
	
	/**
	 * 删除用户角色信息
	 * @param strDetail
	 * @throws RuntimeException
	 */
	public void deleteRoleUser(String strDetail) throws RuntimeException;
	
	/**
	 * 获取模块信息
	 * @param strWhereSql
	 * @return
	 */
	public List<Bset_RightListModel> getBset_RightList(String strEnterpriseNo,String strWhereSql)throws Exception;
	
	/**
	 * 保存角色、模块权限信息
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveBset_Role_Module(String strDetail)throws Exception;
	
	/**
	 * 获取登录用户模块权限信息
	 * @param strWorker
	 * @param strModuleId
	 * @return
	 * @throws Exception
	 */
	public List<Bset_RightListModel> getWorkerModuleRight(String strWorker,String enterpriseNo,
			String strModuleId)throws Exception;
	
	/**
	 * 记录登出日志表
	 * @param strWorkerNo
	 */
	public void updateLoginOut(String strWorkerNo)throws Exception;
	
	/**
	 * 修改密码
	 * @param strWorkerNo
	 * @param strOldPass
	 * @param strNewPass
	 * @return
	 * @throws Exception
	 */
	public MsgRes updateWorkerPass(String strWorkerNo,String strOldPass,String strNewPass)throws Exception;
	
	public MsgRes getProcUpdateList() throws Exception;
	
	public MsgRes GetDbConfig() throws Exception;

	public MsgRes getProcUpdateListForRF() throws Exception;
	
	public MsgRes getProgVerList() throws Exception;
	
	public MsgRes getProgVerListForRF() throws Exception;
	
	//统计各个模块单的数量
	public List<CountModel> getCount(String enterpriseNo, String warehouseNo,
			String ownerNo, String itemId);
	

	//获取home_item_m
	public List<Home_ItemMModel> getHomeData(String enterpriseNo,
			String warehouseNo);

	//获取网格头
	public List<Home_ItemListModel> getGridColumModle(String subItemId)throws Exception;

	
	public ExtListDataBo getGridData(String enterpriseNo, String warehouseNo,
			String ownerNo, String subItemId, PageBo pageBo);

	


	//获取RF系统允许显示的数量信息 Add by sunl
	public MsgRes GetQtyShow(String strRecvData) throws Exception;

	
}
