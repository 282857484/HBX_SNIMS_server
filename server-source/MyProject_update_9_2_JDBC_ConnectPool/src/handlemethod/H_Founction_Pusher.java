package handlemethod;

import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import protocol.pushservice.ACK;
import protocol.pushservice.REQ_Heart_Beat;
import database.PushMessageSelectData;
import datasouce.DBConnectionManager;
import maintest.Pusher;
import maintest.server_version_2;
import net.sf.json.JSONObject;

public class H_Founction_Pusher {

	public static Logger logger = Logger.getLogger(H_Founction_Pusher.class
			.getName());

	// private static Connection connection = DatabaseJdbc.coonDb(null);

	/**
	 * 将数据库中的需要push的数据加载到push模块
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void REQ_Heart_Beat_Deal(JSONObject obj, ArrayList<Object> objlist,
			SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		Connection connection;
		connection = DBConnectionManager.getConnection();

		System.out.println("REQ_Heart_Beat_Deal-ing");
		REQ_Heart_Beat req_Heart_Beat = new REQ_Heart_Beat();
		String UserID = obj.getString("UserID");
		String UploadTime = obj.getString("UploadTime");
		String Code = obj.getString("Code");
		if (UserID.equals("")) {
			return;
		}
		req_Heart_Beat.setUserID(UserID);
		req_Heart_Beat.setUploadTime(UploadTime);
		req_Heart_Beat.setCode(Code);
		Pusher pusher = new Pusher();
		pusher.PushMessageList = new LinkedList<PushMessageSelectData>();
		pusher.UserSocketAddress = receivesocketaddress;
		pusher.TimeStamp = UploadTime;
		/**
		 * 
		 */
		// OnlineUserInfo oui = new
		// OnlineUserInfo(UploadTime,receivesocketaddress);
		// // maintest.server_version_2.connectUserMap.put(UserID, oui);
		// maintest.server_version_2.connectUserMap.putIfAbsent(UserID, oui);

		// load User Push info to pushMessageMap
		String SearchSQL = "SELECT * FROM h_user_push_message WHERE UserID = "
				+ Long.valueOf(UserID) + ";";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// System.out.println("SearchSQL : " +SearchSQL);
			// psSearch = connection.prepareStatement(SearchSQL);
			System.out.println("FUCK2");
			ResultSet rs = ps.executeQuery(SearchSQL);
			while (rs.next()) {
				PushMessageSelectData pmsd = new PushMessageSelectData(
						rs.getLong(1), rs.getLong(2), rs.getString(3));
				System.out.println("PushMessageList ADD");
				pusher.PushMessageList.add(pmsd);
				System.out.println("pushMessageMap PUT");
				server_version_2.pushMessageMap.put(UserID, pusher);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection(connection);
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		closeConnection(connection);

	}

	/**
	 * push得到了回应 将数据库中的push信息删除
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void ACK_Deal(JSONObject obj, ArrayList<Object> objlist,
			SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		Connection connection;
		connection = DBConnectionManager.getConnection();

		ACK ack = new ACK();
		ack.setUserID(obj.getString("UserID"));
		ack.setPushID(obj.getString("PushID"));
		// ack.setUploadTime(obj.getString("UploadTime"));

		String DeleteSQL = "DELETE FROM h_user_push_message WHERE PushID = "
				+ ack.getPushID() + " ;";
		// PreparedStatement psDelete;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psDelete = connection.prepareStatement(DeleteSQL);
			boolean resume = ps.execute(DeleteSQL);
			if (resume == true) {
				System.out.println("DELETE PUSH FALSE");
			} else {
			// 这个执行成功后也运行?
				System.out.println("DELETE PUSH TRUE");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection(connection);
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		closeConnection(connection);
	}

	private static void closeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
