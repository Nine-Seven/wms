package com.sealinkin.mina.protocol;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

@SuppressWarnings("unused")
public class MyProtocalDecoder implements ProtocolDecoder
{

	private final AttributeKey CONTEXT = new AttributeKey(getClass(), "context");
	private final Charset charset;
	private byte  m_bytePackFlag = 0x8;
	private int maxPackLength = 8000;

	public MyProtocalDecoder()
	{
		this(Charset.defaultCharset());
	}

	public MyProtocalDecoder(Charset charset)
	{
		this.charset = charset;
	}

	public int getMaxLineLength()
	{
		return maxPackLength;
	}

	public void setMaxLineLength(int maxLineLength)
	{
		if (maxLineLength <= 0)
		{
			throw new IllegalArgumentException("maxLineLength: "
					+ maxLineLength);
		}
		this.maxPackLength = maxLineLength;
	}

	private Context getContext(IoSession session)
	{
		Context ctx;
		ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx == null)
		{
			ctx = new Context();
			session.setAttribute(CONTEXT, ctx);
		}
		return ctx;
	}

	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception
	{
		//包头长度
		final int packHeadLength = 5;
		// 先获取上次的处理上下文，其中可能有未处理完的数据
		Context ctx = getContext(session);
		// 先把当前buffer中的数据追加到Context的buffer当中
		ctx.append(in);
		// 把position指向0位置，把limit指向原来的position位置
		IoBuffer buf = ctx.getBuffer();
		buf.flip();
		
		//如果数据包长度小于包头长度，不处理
		int nCurrLength = buf.remaining();
		if (nCurrLength < packHeadLength)
			return ; 
		
		//然后按数据包的协议进行读取
		buf.mark();
		
		//读取消息头部分
		byte sFlag = buf.get();
		if (sFlag != m_bytePackFlag)
			return;
		
		byte[] byteDataLen = new byte[4];
		byteDataLen[0] = buf.get();
		byteDataLen[1] = buf.get();
		byteDataLen[2] = buf.get();
		byteDataLen[3] = buf.get();
		
		int length = ((((byteDataLen[3] & 0xff) << 24)    
				                | ((byteDataLen[2] & 0xff) << 16)    
				                | ((byteDataLen[1] & 0xff) << 8) | ((byteDataLen[0] & 0xff) << 0)));
		//如果数据长度为0，表明是错误数据，不处理
		if (length == 0)
		{
			return;
		}
		else if (nCurrLength >= length + packHeadLength)
		{
			byte[] data = new byte[length];
			buf.get(data);
			String content = new String(data,"GB2312");
	        out.write(content);
	        buf.reset();
		}
		else
		{	//数据未接收全,指针移到最后			
			buf.position(buf.limit());
		}
		
		/*byte[]  returnString = in.array();
        System.out.println(new String(returnString,"GB2312"));
        int limit = in.limit();
        byte[] data = new byte[limit];
        in.get(data);
        String returnTyped = new String(data,"GB2312");
        out.write(returnTyped);*/
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception
	{
	}

	public void dispose(IoSession session) throws Exception
	{
		Context ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx != null)
		{
			session.removeAttribute(CONTEXT);
		}
	}

	// 记录上下文，因为数据触发没有规模，很可能只收到数据包的一半
	// 所以，需要上下文拼起来才能完整的处理
	private class Context
	{
		private final CharsetDecoder decoder;
		private IoBuffer buf;
		private int matchCount = 0;
		private int overflowPosition = 0;

		private Context()
		{
			decoder = charset.newDecoder();
			buf = IoBuffer.allocate(2048).setAutoExpand(true);
		}

		public CharsetDecoder getDecoder()
		{
			return decoder;
		}

		public IoBuffer getBuffer()
		{
			return buf;
		}

		public int getOverflowPosition()
		{
			return overflowPosition;
		}

		public int getMatchCount()
		{
			return matchCount;
		}

		public void setMatchCount(int matchCount)
		{
			this.matchCount = matchCount;
		}

		public void reset()
		{
			overflowPosition = 0;
			matchCount = 0;
			decoder.reset();
		}

		public void append(IoBuffer in)
		{
			buf.put(in);
			/*IoBuffer bufTmp = IoBuffer.allocate(2048).setAutoExpand(true);
			buf.flip();
			int nLen = buf.remaining();
			if (nLen > 0)
			{
				bufTmp.put(buf);
			}
			bufTmp.put(in);
			
			bufTmp.flip();
			buf.clear();
			buf.put(bufTmp);*/
		}

	}
	
	public static void main(String[] args)
	{
		Short s = (short)0xf8;
		System.out.println(s.shortValue());
		System.out.println(s.doubleValue());
		System.out.println(changeHightDown((short)248));
	}

	
	public static int changeHightDown(short num)
	 {
	   if(num>65535||num<0)
	   {
	    return num;
	   }
	  else if(num<16)
	   {
	    String temp = Integer.toHexString(num);
	    String tempnew = "0"+temp+"00";
	    return Integer.parseInt(tempnew, 16);
	   }
	  else if(num<256&&num>=16)
	  {
	    String temp = Integer.toHexString(num);
	    String tempnew = temp+"00";
	    return Integer.parseInt(tempnew, 16);
	   
	  }
	  else if(num<4096&&num>=256)
	  {
	    String temp = Integer.toHexString(num);
	    String tempnew = temp.substring(1, 3)+"0"+temp.substring(0, 1);
	    return Integer.parseInt(tempnew, 16);
	  }
	  else
	  {
	   
	    String temp = Integer.toHexString(num);
	    String tempnew = temp.substring(2, 4)+temp.substring(0, 2);
	    return Integer.parseInt(tempnew, 16);
	   
	  }
	 
	 }
}
