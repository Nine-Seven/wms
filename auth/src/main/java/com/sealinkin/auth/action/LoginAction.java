package com.sealinkin.auth.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.auth.service.ILogin;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.util.algorithm.MysqlPasswd;
import com.sealinkin.bset.model.Bset_Worker_LocModel;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户登录、注销、取该用户对应模块菜单及相关操作
 * @author lich 20140403
 */
public class LoginAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	
	private ILogin loginImpl;
	private String workerNo;
	private String pwd;
	private String str;
	private String enterpriseNo;
	
	public ILogin getLoginImpl() {
		return loginImpl;
	}
	public void setLoginImpl(ILogin loginImpl) {
		this.loginImpl = loginImpl;
	}

	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	/**
	 * 用户登录
	 * @author lich
	 *
	 */
	public void login()
	{
		if(StringUtils.isAnyBlank(workerNo, pwd)) {
			super.writeObj(new MsgRes(false,"请输入用户名和密码!",null));
		}
		else
		{
			try 
			{	
				MsgRes msg= loginImpl.login(workerNo, MysqlPasswd.MySQLPassword(pwd),enterpriseNo);
				if(msg.getIsSucc())
				{
					HttpService.pushToSession("loginUser", msg.getObj());
					setMdBdef_DefWorker(HttpService.getRequest(),(Bdef_DefWorkerModel) msg.getObj());
					super.writeObj(msg);
				}else				//登录失败
				{
					super.writeObj(msg);
				}
			}catch (Exception e) 
			{
				e.printStackTrace();
				super.writeObj(new MsgRes(false,"登录失败!",null));			
			}
		}
	}
	
	public void getBset_Worker_Loc(){
		try {
			Bdef_DefWorkerModel us=(Bdef_DefWorkerModel)HttpService.getRequest().getSession().getAttribute("loginUser");
			List<Bset_Worker_LocModel> list=new ArrayList<Bset_Worker_LocModel>();
			list=loginImpl.getBset_Worker_Loc(
					us.getWorkerNo(),us.getCurrEnterpriseNo());
			if(list.size() == 0)
			{
				super.writeObj(new MsgRes(false,"暂未分配仓别访问权限",list));
			}
			else if(list.size()==1)
			{
				super.getMdBdef_DefWorker().setWarehouseNo(list.get(0).getWarehouseNo());
				super.writeObj(new MsgRes(false,"",list));
			}
			else if(list.size()>1)
			{
				super.writeObj(new MsgRes(true,"",list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setSelectWarehouseNo(){
		super.getMdBdef_DefWorker().setWarehouseNo(str);
	}
	
	public void logout(){
		loginImpl.updateLoginOut(super.getMdBdef_DefWorker().getWorkerNo());
		HttpService.removeToSession("loginUser");
	}
}
