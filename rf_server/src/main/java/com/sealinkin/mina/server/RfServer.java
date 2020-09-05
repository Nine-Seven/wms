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
		// 监听即将到来的TCP 连接
		try {
			//创建无阻塞的监听Server
			IoAcceptor acceptor = new NioSocketAcceptor();
			//设置日志与编码解码过滤器
			
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
			//设置服务业务处理层
			acceptor.setHandler(new RfServerHandler());
			//设置每次读取的字节(缓存区)大小
			//acceptor.getSessionConfig().setReadBufferSize(BUFFER_SIZE);
			acceptor.getSessionConfig().setReadBufferSize(4096);
			//设置session空闲时间
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			//绑定server端口
			acceptor.bind(new InetSocketAddress(PORT));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(PORT+"服务器启动");
	}
	
	public static void main(String[] args)
	{
		RfServer sf = new RfServer();
		sf.startServer();
	}
}
