package maintest;

import handlemethod.handle;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import database.PushMessageSelectData;

/**
 * 这里发送的是 -SEND-数据包
 * 
 * @author Administrator
 * 
 */
public class PushMessageMap extends TimerTask {

	WriteObject wo;
//	private static Connection connection = DatabaseJdbc.coonDb(null);
	public static Logger logger = Logger.getLogger(PushMessageMap.class
			.getName());

	// 转换Json格式
	Gson g = handle.g;

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		LoadingPushDBTo(server_version_2.connectUserMap,
//				server_version_2.pushMessageMap); // 加载数据到Map
		int count = 0;

		Iterator<Entry<String, Pusher>> iter = server_version_2.pushMessageMap
				.entrySet().iterator();
		Entry<String, Pusher> entry;
		while (iter.hasNext()) {
			
			entry = iter.next();
			String UserID = entry.getKey();
			Pusher push = entry.getValue();
//			System.out.println("push-UserID: " + UserID + " pushmessage: " + push.PushMessageList + " pushAddress: " + push.UserSocketAddress);

			for (PushMessageSelectData PushMessage : push.PushMessageList) { // PushMessageSelectData
				String sendString = g.toJson(PushMessage);
				StringBuilder sendStringPacket = new StringBuilder();
				sendStringPacket.append("[");
				sendStringPacket.append(sendString);
				sendStringPacket.append("]");
//				System.out.println("PushMessageSelectData write to WriteObject");
				wo = new WriteObject(push.UserSocketAddress, sendStringPacket.toString());
				server_version_2.sendQueue.offer(wo);
				count ++;
			}
			server_version_2.pushMessageMap.remove(UserID);

		}
		logger.info("pushMessageMap push msg number : " + count);
//		System.out.println("超时检测终端,会将重新启动");
	}
//  弃用,宜采用另一种模式:用户心跳包取
//	private void DealGetPusherInDB(String replace) {
//		
//	}

	/**
	 * 每次重新加载时需要等待客户端完全回复后才行 需要从数据库中加载当前在线用户
	 * 
	 * @param connectUserMap
	 * @param pushMessageMap
	 */
//	private void LoadingPushDBTo(
//			ConcurrentHashMap<String, OnlineUserInfo> connectUserMap,
//			ConcurrentHashMap<String, Pusher> pushMessageMap) {
//		// TODO Auto-generated method stub
//		Iterator<Entry<String, OnlineUserInfo>> iter = connectUserMap.entrySet().iterator();
//		Entry<String, OnlineUserInfo> entry;
//		while (iter.hasNext()) {
//			entry = iter.next();
//			String UserID = entry.getKey();
//			OnlineUserInfo userInfo = entry.getValue();
//			
//		}
//		
//		
//		
//		String SelectAllSQL = "SELECT * FROM h_user_push_message WHERE 1=1 ;";
//		PreparedStatement selectps;
//		try {
//			selectps = connection.prepareStatement(SelectAllSQL);
//			ResultSet rs = selectps.executeQuery();
//			while (rs.next()) {
//				PushMessageSelectData pmsd = new PushMessageSelectData(
//						rs.getLong(1), rs.getLong(2), rs.getString(3));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
