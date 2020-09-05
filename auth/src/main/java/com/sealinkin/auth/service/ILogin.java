package com.sealinkin.auth.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.bset.model.Bset_Worker_LocModel;

@WebService
public interface ILogin {
	
	@WebMethod
	MsgRes login(String name,String pass,String enterpriseNo);
	//public MsgRes update(String WorkerNo,String passWord,String strNewPwd);
	@WebMethod
	public void updateLoginLog(@WebParam String workerNo);
	
	public List<Bset_Worker_LocModel> getBset_Worker_Loc(String name,String strEnterpriseNo);
	
	/**
	 * 记录登出日志表
	 * @param workerNo
	 */
	public void updateLoginOut(String workerNo);
	
	public MsgRes rfLogin(String name,String pass,String enterpriseNo);
	/**
	 * 修改密码
	 * @param str_WorkNo
	 * @param passWord
	 * @param strNewPwd
	 * @return
	 */
	public MsgRes updatePWD(String WorkerNo,String passWord,String NewPwd);
}
