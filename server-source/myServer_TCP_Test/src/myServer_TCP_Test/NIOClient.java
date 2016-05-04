package myServer_TCP_Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClient {
	public static ExecutorService handlePool;
	
	public static int POOL_SIZE = 10;
	public static long FUCK = 0;
	public static void main(String[] args) throws IOException {
		handlePool = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * POOL_SIZE);
		while (true) {
			handlePool.execute(new NIOClientNet("第" + FUCK++ + "个线程发送的信息"));
		}
	}

//	/**
//	 * @param args
//	 * @throws IOException
//	 */
//	public static void main(String[] args) throws IOException {
//		int port = 12345;
//		SocketChannel channel = SocketChannel.open();
//		channel.configureBlocking(false);
//
//		SocketAddress target = new InetSocketAddress("127.0.0.1", port);
//		channel.connect(target);
//		Selector selector = Selector.open();
//		// 用于套接字连接操作的操作集位
//		channel.register(selector, SelectionKey.OP_CONNECT);
//		// BufferedReader systemIn=new BufferedReader(new
//		// InputStreamReader(System.in));
//
//		while (channel.isOpen()) {
//			if (channel.isConnected() && channel.isOpen()) {
//				// String command=systemIn.readLine();
////				String command = "WTF MESSAGE!";
////				String command = "[{\"ActivityID\":\"8\",\"PageIndex\":\"0\",\"PageSize\":\"10\",\"UploadTime\":\"201409152224270\",\"UserID\":\"15273131134\",\"p\":53}]";
//				String command = "[{\"Code\":\"0\",\"UploadTime\":\"201409170118524\",\"UserID\":\"15273131134\",\"p\":1002}]";
//				channel.write(Charset.forName("UTF-8").encode(command));
//				System.out.println("channel.write");
//
//				if (command == null || "quit".equalsIgnoreCase(command.trim())) {
//					// systemIn.close();
//					channel.close();
//					selector.close();
//					System.out.println("Client quit !");
//					System.exit(0);
//				}
//			}
//			int nKeys = selector.select(10000);
//			if (nKeys > 0) {
//				System.out.println("select");
//				for (SelectionKey key : selector.selectedKeys()) {
//					System.out.println("SelectionKey key");
//					if (key.isConnectable()) {
//						System.out.println("isConnectable");
//						SocketChannel sc = (SocketChannel) key.channel();
//						sc.configureBlocking(false);
//						sc.register(selector, SelectionKey.OP_READ);
//						sc.finishConnect();
//					} else if (key.isReadable()) {
//						System.out.println("isReadable");
//						ByteBuffer buffer = ByteBuffer.allocate(1024 * 64);
//						SocketChannel sc = (SocketChannel) key.channel();
//						int readBytes = 0;
//						try {
//							int ret = 0;
//							try {
//								while ((ret = sc.read(buffer)) > 0) {
//									readBytes += ret;
//								}
//							} finally {
//								buffer.flip();
//							}
//							if (readBytes > 0) {
//								System.out.println(Charset.forName("UTF-8")
//										.decode(buffer).toString());
//								buffer.clear();
//								buffer = null;
//							}
//
//						} finally {
//							if (buffer != null) {
//								selector.close();
//							}
////							key.cancel();
////							buffer.clear();
//							channel.finishConnect();
//							channel.close();
////							selector.close();
//						}
//					}
//					
//					
//				}
////				key.cancel();
////				selector.selectedKeys().clear();
//			}
//		}
//	}

}