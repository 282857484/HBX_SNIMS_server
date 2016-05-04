package dealfileupload;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 服务器
 */
public class Server extends ServerSocket implements Runnable{

	static Logger logger = Logger.getLogger(Server.class.getName());

	private static final int DEAL_UPLOAD_FILE_PORT = FileConfig.PhotoTransferPort;
	private ServerSocket dealuploadfileserver;
	
	private static final int POOL_SIZE = FileConfig.PhotoReceiveThreadPOOLSIZE;
	ExecutorService fileReceivePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
	
	// (线程安全)正在处理文件数组
	private static Vector<String> fileProcessingList = new Vector<String>();

	public Server() throws IOException {
		super();
		int coreThreadNum = POOL_SIZE;
//		System.out.println("文件服务器开启的线程数: " + Runtime.getRuntime().availableProcessors() * coreThreadNum);
		
		logger.info("文件服务器开启的线程数: " + Runtime.getRuntime().availableProcessors() * coreThreadNum);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			dealuploadfileserver = new ServerSocket(DEAL_UPLOAD_FILE_PORT);

			while (true) {
				Socket client = dealuploadfileserver.accept();
				// 设置每一个连接的延时
				client.setSoTimeout(FileConfig.UserFileSockSoTimeout);
				if((client.isInputShutdown()) && (client.isOutputShutdown()))
				{
					System.out.println("FileClientSocket is NULL" );
				}
				else
				{
					System.out.println("fileclient(Server.java):  " + client.toString());
					fileReceivePool.execute(new fileReceiveThread(client));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		} finally {
			try {
				dealuploadfileserver.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
			}
		}
	}

	public static boolean addFileProcessing(String fileProcessing) {
		return Server.fileProcessingList.add(fileProcessing);
	}
	
	public static boolean checkFileProcessingExist(String fileProcessing) {
		return Server.fileProcessingList.contains(fileProcessing);
	}

	public static boolean deleteFileProcessing(String fileProcessing) {
		return Server.fileProcessingList.remove(fileProcessing);
	}
}