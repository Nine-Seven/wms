package com.sealinkin.mina.handler;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.BizRequestModel;
import com.sealinkin.comm.model.socket.BizResponseModel;
import com.sealinkin.comm.model.socket.SessionKey;
import com.sealinkin.mina.util.SessionUtil;
import com.sealinkin.process.ICaseProcessService;
import com.sealinkin.process.impl.CaseProcessServiceImpl;

public class RfServerHandler extends IoHandlerAdapter
{
	//通信类型
	private final int COMM_BEAN_LOGIN =  0;
    private final int COMM_BEAN_BIZ =    1;
    private final int COMM_BEAN_LOGOUT = 2;
    
    //公钥
    //private static final String PUBLIC_KEY = ReadSysConfig.getBundByKey("public_key") ;
    private static final String PUBLIC_KEY = "wms_rf" ;
    
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception
	{
		cause.printStackTrace();
	}

	/**
	 * 信息处理方法
	 */
	public void messageReceived(IoSession session, Object message)
			throws Exception
	{
		BizResponseModel commRespBo = new BizResponseModel();
		try
		{			
			//请求对象
			JSON json = (JSON) JSONObject.parse(message.toString());
			BizRequestModel commReqBo = (BizRequestModel) JSONObject.toJavaObject(json, BizRequestModel.class);
			
			//传输来的公钥/秘钥
			String sessionId = commReqBo.getM_strSessionID();
			//来源IP
			String ip = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
			
			/**
			 * 根据通信类型处理对应逻辑
			 */
			switch (commReqBo.getM_nBeanName())
			{
			case COMM_BEAN_LOGIN:
				/**
				 * 1.判断公钥是否正确 不正确返回错误
				 * 2.生成IP+秘钥+时间对象保存 服务器内存中
				 * 3.返回数据
				 */
				//判断公钥
				if(sessionId==null || !sessionId.equals(PUBLIC_KEY)){
					commRespBo.setIsSucc(false);
					commRespBo.setIntErrror(-1101);//通信握手失败(公钥错误)
				}else{
					MsgRes msgRes = SessionUtil.saveSession(ip);
					String strSessionID = msgRes.getObj().toString();
					
					//封装返回对象
					commRespBo.setIsSucc(true);
					commRespBo.setIntErrror(0);//通信成功
					commRespBo.setStrSessionID(strSessionID);//真实Sessionid
				}
				break;
			case COMM_BEAN_BIZ:
				/**
				 * 1.判断秘钥是否正确  
				 * 不正确返回错误	正确但是内存对象为空返回超时
				 * 2.正确调用业务处理实现类
				 * 
				 */
				//获取秘钥
				SessionKey sessionKey = SessionUtil.getSession(sessionId);
				if(sessionKey==null){
					commRespBo.setIsSucc(false);
					commRespBo.setIntErrror(-1001);//通信超时(秘钥错误 或者 秘钥为空)
				}else{
					//解析请求数据对象
					//JSON jsonCBizReq = (JSON) JSONObject.parse(commReqBo.getM_strData().toString());
					//CBizRequestModel cBizRequestModel = (CBizRequestModel)JSONObject.toJavaObject(jsonCBizReq , CBizRequestModel.class);
					
					/**
					 * 调用业务处理实现类
					 * 传入业务数据对象字符串
					 * @return 返回业务数据字符串
					 */
					ICaseProcessService socketProcessService = new CaseProcessServiceImpl();
					String strData = socketProcessService.caseMethod(commReqBo.getM_strData().toString(),ip);
					
					//封装返回对象
					commRespBo.setIsSucc(true);
					commRespBo.setIntErrror(0);//通信成功
					commRespBo.setStrSessionID(sessionId);//真实Sessionid
					commRespBo.setStrData(strData);
					commRespBo.setnDataLength(commRespBo.getStrData().length());
				}
				break;
			case COMM_BEAN_LOGOUT:
				/**
				 * 1.删除内存对象
				 */
				SessionUtil.removeSession(sessionId);
				commRespBo.setIsSucc(true);
				commRespBo.setIntErrror(0);//通信成功
				break;
			default:
				break;
			}
			
			//返回客户端通信数据对象
			String writeStr = JSONObject.toJSONString(commRespBo);
			session.write(writeStr);
			
		} catch (Exception e)
		{
			//异常返回错误信息
			e.printStackTrace();
			commRespBo.setIsSucc(false);
			commRespBo.setIntErrror(-1100);//发送数据失败
			commRespBo.setStrData(e.getMessage());
			commRespBo.setStrSessionID("");
			commRespBo.setnDataLength(commRespBo.getStrData().length());
			String writeStr = JSONObject.toJSONString(commRespBo);
			session.write(writeStr);
		} finally
		{
			//每次请求是短连接 必须关闭session
			session.close(true);
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception
	{
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception
	{
		super.sessionClosed(session);
		// System.out.println("sessin close");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception
	{
		super.sessionCreated(session);
		// System.out.println("sessin created");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception
	{
		super.sessionOpened(session);
		// session.write("create");
		// System.out.println("sessin open");
	}

	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception
	{
		// System.out.println("IDLE " + session.getIdleCount(status)
		// + ";sessionId=" + session.getId());
	}

}
