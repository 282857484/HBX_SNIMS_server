package maintest;

import handlemethod.HandleCommand;
import handlemethod.handle_TCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import maintest.config.MainServerConfig;

import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import datasouce.DBConnectionManager;
import dealfileupload.Server;

public class server_version_3 {
	// public class server_version_2 {

	static {
		PropertyConfigurator.configure(MainServerConfig.log4jpropertiesPath);
		// 指定配置文件
		// PropertyConfigurator.configure("d:/myconfig/log4j.properties");
	}
	public static Logger logger = Logger.getLogger(server_version_3.class
			.getName());
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static Selector selector1 = null;

	// 时间--3s
//	private static final int TIMEOUT = MainServerConfig.UDPSocketSelectTimeOut;
	private static final int TIMEOUT = MainServerConfig.TCPSocketSelectTimeOut;

	// 接收消息队列receiveQueue
	private static BlockingQueue<ReadObject> receiveQueue;
	// 发送队列sendQueue
	public static BlockingQueue<WriteObject> sendQueue;

	// <TimeStamp, KeyObject(receivesocketaddress,sendMessage)>
	public static ConcurrentHashMap<String, KeyObject> repeatMessageMap;

	// 推送信息列表<userID,pusher(TimeStamp,UserSocketAddress,List<PushMessageSelectData(PushID,UserID,PushMessage(json-dbinfo))>)>
	public static ConcurrentHashMap<String, Pusher> pushMessageMap;

	// 在线用户(UserID,info)
	/**
	 * 还未对其进行处理
	 */
	public static ConcurrentHashMap<String, OnlineUserInfo> connectUserMap;

	// private static ServerSocket serverSocket;

	public static HandleCommand abc;
	public static ExecutorService handlePool;

	/**
	 * @X 中断信号
	 */
	public static boolean flag = true;

	public static void destroy() {
		logger.info("destory");
	}

	public static void init(DaemonContext arg0) throws DaemonInitException,
			Exception {
		logger.info("init");
	}

	/**
	 * java作为服务项必须函数 这个不能丢失
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void stop(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("stop");

		DBConnectionManager.release();
		flag = false;

		selector1.close();
		System.out.println("Server has been shutdown!");
		logger.error("stop");
	}

	/**
	 * java作为服务项必须函数 这个不能丢失
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void start(String[] args) throws Exception {
		logger.info("static start....");

		String[] aaa = null;
		main(aaa);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

		logger.setLevel(log.Log4jLevel.ALL);
		logger.info("服务器启动~");
		logger.info(sdf.format(Calendar.getInstance().getTime()));

		DBConnectionManager.init();

		// try {
		// serverSocket = new ServerSocket(12345);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		receiveQueue = new LinkedBlockingQueue<ReadObject>();
		sendQueue = new LinkedBlockingQueue<WriteObject>();

		repeatMessageMap = new ConcurrentHashMap<String, KeyObject>();
		pushMessageMap = new ConcurrentHashMap<String, Pusher>();
		connectUserMap = new ConcurrentHashMap<String, OnlineUserInfo>();

		abc = new HandleCommand();

		// 端口号
		int servPort = MainServerConfig.MainServerSocketPort;
		final int POOL_SIZE = MainServerConfig.MainServerThreadPoolSize;

		// 打开选择器

		try {
			selector1 = Selector.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}
		// Selector selector2 = Selector.open();
		// 打开通道
		ServerSocketChannel channel = null;
		try {
			channel = ServerSocketChannel.open();
			channel.socket().bind(new InetSocketAddress(servPort));
			channel.configureBlocking(false);
			channel.register(selector1, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		// 处理线程池
		handlePool = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * POOL_SIZE);
		logger.info("UDP处理线程数" + Runtime.getRuntime().availableProcessors()
				* POOL_SIZE);

		// sendingInfoTable = new ConcurrentHashMap<String, WriteObject>();

		// 将过时信息定时删除
//		Timer timerCheckRepeatMessage = new Timer();
//		timerCheckRepeatMessage.schedule(new CheckRepeatMessageMap(),
//				MainServerConfig.CheckRepeatDelay,
//				MainServerConfig.CheckRepeatPeriod);
//		 将推送信息发送至客户端
//		Timer timerPushMessageTimer = new Timer();
//		timerPushMessageTimer.schedule(new PushMessageMap(),
//				MainServerConfig.PushMessageDelay,
//				MainServerConfig.PushMessagePeriod);
		// 开启文件接收服务器
		// try {
		try {
			handlePool.execute(new Server());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		/**
		 * 客户端发往服务器是可控的,服务器发往客户端不是可控的
		 */
		while (flag == true) {
			// key可写入
			// while (!sendQueue.isEmpty()) {
			// // System.out.println("--------可写--------");
			// WriteObject wo = sendQueue.remove();
			//
			// handleWrite(wo);
			// }

			try {
				if (selector1.select(TIMEOUT) == 0) {
					continue;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
			}

			// System.out.println("装入容器");
			// 装入容器
			Iterator<SelectionKey> keyIter1 = selector1.selectedKeys()
					.iterator();
			// 迭代
			while (keyIter1.hasNext()) {
				
				
				// 得到下一个元素
				SelectionKey key = keyIter1.next();
				// 删除此key
				keyIter1.remove();

				if (key.isAcceptable()) {
					System.out.println("key.isAcceptable()");
					handleAccept(key);
				}

				// key信道可读
				if (key.isValid() && key.isReadable()) {
					// 处理读信息
					// System.out.println("--------可读--------");

					System.out.println("isReadable()");
					handleRead(key);
				}

				
			}
		}

	}

	private static void handleRead(SelectionKey key) {
		// TODO Auto-generated method stub
		handlePool.execute(new handle_TCP(key));
	}

	private static void handleAccept(SelectionKey key) {
		// TODO Auto-generated method stub
		SocketChannel clientChannel;
		try {
			clientChannel = ((ServerSocketChannel) key.channel()).accept();
			clientChannel.configureBlocking(false);
			clientChannel.register(key.selector(), SelectionKey.OP_READ);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void windowsService(String args[]) {
		String cmd = "start";
		if (args.length > 0) {
			cmd = args[0];
		}

		if ("start".equals(cmd)) {
			server_version_3.windowsStart();
		} else {
			server_version_3.windowsStop();
		}
	}

	public static void windowsStart() {
	}

	public static void windowsStop() {
	}

}
