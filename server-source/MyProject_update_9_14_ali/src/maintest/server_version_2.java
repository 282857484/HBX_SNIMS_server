package maintest;

import handlemethod.HandleCommand;
import handlemethod.handle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Timer;
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

public class server_version_2 {
	// public class server_version_2 {

	static {
		PropertyConfigurator.configure(MainServerConfig.log4jpropertiesPath);
		// 指定配置文件
		// PropertyConfigurator.configure("d:/myconfig/log4j.properties");
	}
	public static Logger logger = Logger.getLogger(server_version_2.class
			.getName());
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// 时间--3s
	private static final int TIMEOUT = MainServerConfig.UDPSocketSelectTimeOut;

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

	private static DatagramSocket sendSocket;

	public static HandleCommand abc;
	public static ExecutorService handlePool;

	/**
	 * @X 中断信号
	 */
	public static boolean flag = true;

	public static void destroy() {
		// TODO Auto-generated method stub
//		System.out.println("destory");
		logger.info("destory");
	}

	public static void init(DaemonContext arg0) throws DaemonInitException,
			Exception {
		// TODO Auto-generated method stub
//		System.out.println("init");
		logger.info("init");
	}

	//
	// public void start() throws Exception {
	// // TODO Auto-generated method stub
	// System.out.println("start");
	// logger.error("start");
	// }

	/**
	 * java作为服务项必须函数 这个不能丢失
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void stop(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("stop");

		DBConnectionManager.release();
		flag = false;
		logger.error("stop");
		DBConnectionManager.release();
	}

	/**
	 * java作为服务项必须函数 这个不能丢失
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void start(String[] args) throws Exception {
//		System.out.println("static start....");
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

		try {
			sendSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

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
		Selector selector1 = null;
		try {
			selector1 = Selector.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}
		// Selector selector2 = Selector.open();
		// 打开通道
		DatagramChannel channel = null;
		try {
			channel = DatagramChannel.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		// 非阻塞
		try {
			channel.configureBlocking(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}
		// 绑定端口
		try {
			channel.socket().bind(new InetSocketAddress(servPort));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}
		// 注册信道
		// channel.register(selector1, SelectionKey.OP_READ, new
		// ClientRecord());
		try {
			channel.register(selector1, SelectionKey.OP_READ);
		} catch (ClosedChannelException e) {
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
		Timer timerCheckRepeatMessage = new Timer();
		timerCheckRepeatMessage.schedule(new CheckRepeatMessageMap(), MainServerConfig.CheckRepeatDelay,
				MainServerConfig.CheckRepeatPeriod);
		// 将推送信息发送至客户端
		Timer timerPushMessageTimer = new Timer();
		timerPushMessageTimer.schedule(new PushMessageMap(), MainServerConfig.PushMessageDelay, MainServerConfig.PushMessagePeriod);
		// 开启文件接收服务器
		// try {
		try {
			new Thread(new Server()).start();
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
			while (!sendQueue.isEmpty()) {
				// System.out.println("--------可写--------");
				WriteObject wo = sendQueue.remove();

				try {
					handleWrite(wo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(log.ExceptionLogTool.getTrace(e));
				}
			}

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
				// System.out.println("--------WHILE循环--------");
				// 得到下一个元素
				SelectionKey key = keyIter1.next();
				// 删除此key
				keyIter1.remove();

				// 提示信息
				// System.out.println("successful select!");

				// key信道可读
				if (key.isReadable()) {
					// 处理读信息
					// System.out.println("--------可读--------");

					try {
						receiveQueue.offer(handleRead(key));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error(log.ExceptionLogTool.getTrace(e));
					}

					if (!receiveQueue.isEmpty()) {
						// System.out.println("--------提交处理线程--------");
						handlePool.execute(new handle(receiveQueue.remove(),
								sendQueue, abc));
					}
				}
			}
		}

	}

	/**
	 * 处理方法
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private static ReadObject handleRead(SelectionKey key) throws IOException {
		// TODO Auto-generated method stub
		// 将key的通道传入
		DatagramChannel channel = (DatagramChannel) key.channel();

		final int ECHOMAX = 1024 * 50;
		SocketAddress clientAddress;
		ByteBuffer buffer = ByteBuffer.allocate(ECHOMAX);

		clientAddress = channel.receive(buffer);
		// 读buffer，并显示buffer信息
		byte[] b = buffer.array();
		String s = new String(b, "UTF-8");
		String ss = s.trim();
		ReadObject rd = new ReadObject(clientAddress, ss);

		buffer.clear();

		return rd;
	}

	/**
	 * 处理方法
	 * 
	 * @param key
	 * @throws IOException
	 */
	private static void handleWrite(WriteObject wo) throws IOException {
		if (!wo.isMulticastaddress()) {
			// 初始化bytebuffer
			ByteBuffer bb = ByteBuffer.wrap(wo.getSendString().getBytes("UTF-8"));
			DatagramPacket sendPacket = new DatagramPacket(bb.array(),
					bb.array().length, wo.getSocketaddress());
			System.out.println("SENDING WRITE OBJECT: "
					+ new String(bb.array()));
			sendSocket.send(sendPacket);

		} else {
			// 多播
			MulticastSocket multicastsocket;
			multicastsocket = new MulticastSocket(12346);
			multicastsocket.joinGroup(wo.getMulticastInetaddress());
			multicastsocket.setTimeToLive(254);
			byte[] buffer = new byte[8192];
			buffer = wo.getSendString().getBytes();
			DatagramPacket multicastdatagrampacket = new DatagramPacket(buffer,
					buffer.length, wo.getMulticastInetaddress(),
					wo.getMulticastPort());
			multicastsocket.send(multicastdatagrampacket);
			multicastsocket.leaveGroup(wo.getMulticastInetaddress());
			multicastsocket.close();
			// key.interestOps(SelectionKey.OP_READ);
		}
	}

	public static void windowsService(String args[]) {
		String cmd = "start";
		if (args.length > 0) {
			cmd = args[0];
		}

		if ("start".equals(cmd)) {
			server_version_2.windowsStart();
		} else {
			server_version_2.windowsStop();
		}
	}

	public static void windowsStart() {
	}

	public static void windowsStop() {
	}

}
