package com.sealinkin.comm.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.sealinkin.comm.model.socket.BizRequestModel;
import com.sealinkin.comm.model.socket.BizResponseModel;

/**
 * @文件名:第三方调用 通用返回对象模型
 * @创建人:王翔
 * @日期:2014-4-28
 * @修改人:王翔
 * @日期:2014-4-28
 * @描述:
 */
@SuppressWarnings("unused")
public class TransferDataModel implements Serializable
{

	private static final long serialVersionUID = -3373723678410448957L;

	// 通信层属性
	private String version = "v2.0"; // 版本号 由业务层实现
	private int portType = 1; // 通信协议 目前默认都是普通通信协议
	private String token; // 私钥

	// 业务层引用对象
	private BizResponseModel bizRtnMdl;
	private BizRequestModel bizReqMdl;

	
	
	public TransferDataModel()
	{
	}

	public TransferDataModel(String token, BizRequestModel bizReqMdl)
	{
		this.token = token;
		this.bizReqMdl = bizReqMdl;
	}

	public TransferDataModel(String token, BizResponseModel bizRtnMdl)
	{
		this.token = token;
		this.bizRtnMdl = bizRtnMdl;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public int getPortType()
	{
		return portType;
	}

	public void setPortType(int portType)
	{
		this.portType = portType;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String login(String arg1, Integer arg2, int arg3)
	{
		return arg1 + arg2 + arg3;
	}

	public BizResponseModel getBizRtnMdl()
	{
		return bizRtnMdl;
	}

	public void setBizRtnMdl(BizResponseModel bizRtnMdl)
	{
		this.bizRtnMdl = bizRtnMdl;
	}

	public BizRequestModel getBizReqMdl()
	{
		return bizReqMdl;
	}

	public void setBizReqMdl(BizRequestModel bizReqMdl)
	{
		this.bizReqMdl = bizReqMdl;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception
	{

		try
		{
			Class classInstanll = Class
					.forName("com.sealinkin.comm.model.CommReturnBo");

			String[] a = new String[] { "String", "Integer" };

			Class[] classArray = null;

			for (int i = 0; i < classInstanll.getMethods().length; i++)
			{
				if (classInstanll.getMethods()[i].getName().equals("login"))
				{
					Type[] paramTypeList = classInstanll.getMethods()[i]
							.getGenericParameterTypes();
					int ints = 0;
					classArray = new Class[paramTypeList.length];

					for (Type paramType : paramTypeList)
					{
						classArray[ints] = (Class) paramType;
						ints++;
					}
				}
			}

			Method method = classInstanll.getMethod("login", classArray);
			TransferDataModel obj = new TransferDataModel();

			Object[] argss = new Object[] { "1", 2, 3 };
			System.out.println(method.invoke(obj, argss));
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SecurityException e)
		{
			e.printStackTrace();
		}
	}

}
