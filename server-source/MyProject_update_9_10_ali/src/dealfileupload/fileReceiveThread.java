package dealfileupload;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

public class fileReceiveThread implements Runnable {

	static Logger logger = Logger.getLogger(fileReceiveThread.class.getName());
	
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket client;

	private FileOutputStream fos;
	
	private String inputFilePath = "-1";
	private String outputFilePath = "-1";
	private String inputFileName = "-1";
	private String outputFileName = "-1";
	

	public fileReceiveThread(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String Path = "0";
		if (client == null) {
			System.out.println("fileReceiveThread-ClientSocket is NULL");
		}
		if (client.isInputShutdown()) {
			System.out.println("fileReceiveThread-after client import : inputShutdown");
		}
		if (client.isOutputShutdown()) {
			System.out.println("fileReceiveThread-after client import : outputShutdown");
		}
		try {
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			System.out.println("输入输出流创建成功 :" + dis.toString() + " ; "
					+ dos.toString());

			// 文件名.长度.类型
			String fileName = dis.readUTF();
			long fileLength = dis.readLong();
			int fileType = dis.readInt();
			 
			dos.flush();
			dos.writeBoolean(true);
			StringBuilder savePath = new StringBuilder();
			switch(fileType) {
			case FileType.ActivityItem:
				savePath.append(photofilepath.ActivityItem);
				outputFilePath = photofilepath.ActivityItem_small;
				break;
			case FileType.ActivityLogo:
				savePath.append(photofilepath.ActivityLogo);
				outputFilePath = photofilepath.ActivityLogo_small;
				break;
//			case FileType.biaoqing:
//				savePath.append(photofilepath.biaoqing);
//				break;
			case FileType.DongTai:
				savePath.append(photofilepath.DongTai);
				outputFilePath = photofilepath.DongTai_small;
				break;
			case FileType.TouXiang:
				savePath.append(photofilepath.TouXiang);
				outputFilePath = photofilepath.TouXiang_small;
				break;
			case FileType.tupianqiang:
				savePath.append(photofilepath.tupianqiang);
				outputFilePath = photofilepath.tupianqiang_small;
				break;
				
				
			default:
				System.out.println("fileReceiveThread : receive unknown file");
				
			};
			outputFileName = fileName;
			inputFileName = fileName;
			inputFilePath = savePath.toString();
			savePath.append(fileName);
			Path = savePath.toString();
			
			fos = new FileOutputStream(new File(savePath.toString()));

			byte[] sendBytes = new byte[1024];
			int transLen = 0;
			System.out.println("----开始接收文件<" + fileName + ">,文件大小为<"
					+ fileLength + ">----" + "fileType: " + fileType);
			while (true) {
				int read = 0;
				read = dis.read(sendBytes);
				if (read == -1)
					break;
				transLen += read;
//				System.out.println("接收文件进度" + 100 * transLen / fileLength
//						+ "%...");
				fos.write(sendBytes, 0, read);
				fos.flush();
			}
			System.out.println("----接收文件<" + fileName + ">成功----" + "接收大小: " + transLen + "byte");
			client.close();
			
			if(!Server.checkFileProcessingExist(Path)){
				Server.addFileProcessing(Path);
				PhotoCompressTool.CompressPhoto(inputFilePath, outputFilePath, inputFileName, outputFileName, FileConfig.XWidth, FileConfig.XHeight, FileConfig.IsZoomInScale);
				Server.deleteFileProcessing(Path);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		} finally {
			try {
				if (dis != null)
					dis.close();
				if (fos != null)
					fos.close();
				if (dos != null)
					dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
			}
		}
//		System.out.println("Compress ELEMENTS : " + inputFilePath + " , " + outputFilePath + " , " + inputFileName + " , " + outputFileName);
		

	}

}
