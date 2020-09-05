package com.sealinkin.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.sealinkin.mina.handler.RfServerHandler;
import com.sealinkin.mina.protocol.MyProtocalCodecFactory;
import com.sealinkin.util.ReadSysConfig;

public class RfServer {

	private static final int PORT = Integer.parseInt( ReadSysConfig.getBundByKey("port") );
	//private static final int BUFFER_SIZE = Integer.parseInt( ReadSysConfig.getBundByKey("buffer_size") );
	private Logger log = Logger.getLogger(RfServer.class);
	
	public void startServer(){
		// ��������������TCP ����
		try {
			//�����������ļ���Server
			IoAcceptor acceptor = new NioSocketAcceptor();
			//������־�������������
			
			acceptor.getFilterChain().addLast("logger", new LoggingFilter());
			acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new MyProtocalCodecFactory(Charset
							.forName("UTF-8"))));
			/*acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"))));*/
			acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
			//���÷���ҵ�����
			acceptor.setHandler(new RfServerHandler());
			//����ÿ�ζ�ȡ���ֽ�(������)��С
			//acceptor.getSessionConfig().setReadBufferSize(BUFFER_SIZE);
			acceptor.getSessionConfig().setReadBufferSize(4096);
			//����session����ʱ��
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			//��server�˿�
			acceptor.bind(new InetSocketAddress(PORT));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(PORT+"����������");
	}
	
	public static void main(String[] args)
	{
		RfServer sf = new RfServer();
		sf.startServer();
	}
}
