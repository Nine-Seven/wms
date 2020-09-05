package com.sealinkin.mina.protocol;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

@SuppressWarnings("unused")
public class MyProtocalEncoder extends ProtocolEncoderAdapter
{

	private byte  m_bytePackFlag = 0x8;
	private final Charset charset;

	public MyProtocalEncoder(Charset charset)
	{
		this.charset = charset;
	}

	// 在此处实现包的编码工作，并把它写入输出流中
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception
	{
		final int packHeadLength = 5;		
		
		String value = (String) message;
		if (value != null)
		{
			int nDataLen = value.getBytes().length;
			//设置默认最大发送报文大小等于真实数据大小
			try {
				session.getConfig().setMaxReadBufferSize(nDataLen + packHeadLength);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				session.getConfig().setMaxReadBufferSize(nDataLen + packHeadLength+25);
				e.printStackTrace();
			}
			IoBuffer buf = IoBuffer.allocate(nDataLen + packHeadLength).setAutoExpand(true);	
			
			buf.put(m_bytePackFlag);
			buf.put((byte)(nDataLen & 0xFF));
			buf.put((byte)((nDataLen >> 8) & 0xFF));
			buf.put((byte)((nDataLen >> 16) & 0xFF));
			buf.put((byte)((nDataLen >> 24) & 0xFF));								
			buf.put(value.getBytes());			
			buf.flip();
			out.write(buf);
		}
		
	}

}
