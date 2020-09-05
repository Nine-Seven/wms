package com.sealinkin.mina.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.SessionKey;
import com.sealinkin.util.algorithm.MysqlPasswd;
/**
*@文件名:Session操作
*@创建人:王翔
*@日期:2014-6-10
*@修改人:王翔
*@日期:2014-6-10
*@描述:
 */
public class SessionUtil implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Session Map
	public static Map<String,SessionKey> sessionMap = new HashMap<String,SessionKey>();
	
	/**
	 * 保存Session
	 * @param ip
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public static MsgRes saveSession(String ip) throws Exception{
		MsgRes msgRes = null;
		String sessionId = makeSessionId(ip);
		String key = sessionId;
		/*if(sessionMap.get(key)==null){*/
			SessionKey sessionKey = new SessionKey();
			sessionKey.setCreateDate(new Date());
			sessionKey.setIp(ip);
			sessionKey.setSessionId(sessionId);
			sessionMap.put(key, sessionKey);
			msgRes = new MsgRes(true,"",key);
		/*}else{
			msgRes = new MsgRes(false,"","");
		}*/
		return msgRes;
	};
	
	/**
	 * 根据key获取map
	 * @param key
	 * @return
	 */
	public static SessionKey getSession(String key){
		return sessionMap.get(key);
	}
	
	/**
	 * 删除Session
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static MsgRes removeSession(String key) throws Exception{
		MsgRes msgRes = null;
		if(sessionMap.get(key)==null){
			msgRes = new MsgRes(true,"","");
		}else{
			sessionMap.remove(key);
			msgRes = new MsgRes(true,"","");
		}
		return msgRes;
	};
	
	/**
	 * 根据IP生成秘钥
	 * @param ip
	 * @return
	 */
	public static String makeSessionId(String ip) throws Exception{
		return MysqlPasswd.MySQLPassword(ip+System.currentTimeMillis());
	};
	
	
}
