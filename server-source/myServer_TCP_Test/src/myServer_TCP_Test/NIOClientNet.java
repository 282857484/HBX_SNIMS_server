package myServer_TCP_Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NIOClientNet implements Runnable {
	// String command = "WTF MESSAGE!";
	// String command =
	// "[{\"ActivityID\":\"8\",\"PageIndex\":\"0\",\"PageSize\":\"10\",\"UploadTime\":\"201409152224270\",\"UserID\":\"15273131134\",\"p\":53}]";
	// String command =
	// "[{\"Code\":\"0\",\"UploadTime\":\"201409170118524\",\"UserID\":\"15273131134\",\"p\":1002}]";
	private String command = null;
	private SocketChannel channel = null;
	Selector selector = null;
	private final int port = 12345;
	private final String ServerAddress = "127.0.0.1";

	public NIOClientNet(String command) {
		this.command = command;
		try {
			channel = SocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			channel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SocketAddress target = new InetSocketAddress(ServerAddress, port);
		try {
			channel.connect(target);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 用于套接字连接操作的操作集位
		try {
			channel.register(selector, SelectionKey.OP_CONNECT);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}

	public void handleNetNIOClient(String command) {
		this.command = command;
		try {
			channel = SocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			channel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SocketAddress target = new InetSocketAddress(ServerAddress, port);
		try {
			channel.connect(target);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 用于套接字连接操作的操作集位
		try {
			channel.register(selector, SelectionKey.OP_CONNECT);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		// SocketChannel channel = null;

		while (channel.isOpen() && (command != null)) {

			int nKeys = 0;
			try {
				nKeys = selector.select(200);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nKeys > 0) {
				System.out.println("select");
				for (SelectionKey key : selector.selectedKeys()) {
					System.out.println("SelectionKey key");

					if (key.isConnectable()) {
						System.out.println("isConnectable");
						SocketChannel sc = (SocketChannel) key.channel();
						try {
							sc.configureBlocking(false);
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							sc.register(selector, SelectionKey.OP_WRITE);
						} catch (ClosedChannelException e) {
							e.printStackTrace();
						}
						try {
							sc.finishConnect(); // true if, and only if, this channel's socket is now connected 
						} catch (IOException e) {
							e.printStackTrace();
							key.cancel();
							try {
								channel.close();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
						}
					}

					if (key.isValid() && key.isWritable()) {
						ByteBuffer buffer = ByteBuffer.allocate(1024 * 64);
						SocketChannel sc = (SocketChannel) key.channel();
						try {
							int countSend = sc.write(Charset.forName("UTF-8")
									.encode(command));
							System.out.println("write");
							if (countSend == command.length()) {
								command = null;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

						int readBytes = 0;
						try {
							int ret = 0;
							System.out.println("read");
							try {
								while ((ret = sc.read(buffer)) > 0) {
									readBytes += ret;
								}
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								buffer.flip();
							}
							if (readBytes > 0) {
								System.out.println(Charset.forName("UTF-8")
										.decode(buffer).toString());
								buffer.clear();
								buffer = null;
							}

						} finally {
							if (buffer != null) {
								try {
									selector.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							key.cancel();
							try {
								channel.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				}
			}
		}
	}

}