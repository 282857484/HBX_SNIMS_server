package handlemethod;

import httptransfer.creatPoi;
import httptransfer.updatePoi;

import java.io.File;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import protocol.infoclass.CheckVersionAndOtherInfo_C;
import protocol.infoclass.CheckVersionAndOtherInfo_S;
import protocol.infoclass.EventStatistics;
import protocol.infoclass.addActivityDiscuss_C;
import protocol.infoclass.addActivityDiscuss_S;
import protocol.infoclass.addActivitySpecificItem_C;
import protocol.infoclass.addActivitySpecificItem_S;
import protocol.infoclass.addActivity_C;
import protocol.infoclass.addActivity_S;
import protocol.infoclass.addPersonalInfo_C;
import protocol.infoclass.addPersonalInfo_S;
import protocol.infoclass.addRelation_C;
import protocol.infoclass.addRelation_S;
import protocol.infoclass.addUserFeedback_C;
import protocol.infoclass.addUserFeedback_S;
import protocol.infoclass.addUser_C;
import protocol.infoclass.addUser_S;
import protocol.infoclass.changActivityInfo_C;
import protocol.infoclass.changActivityInfo_S;
import protocol.infoclass.changFileNameOrder_C;
import protocol.infoclass.changFileNameOrder_S;
import protocol.infoclass.changRelation_C;
import protocol.infoclass.changRelation_S;
import protocol.infoclass.changUserInfo_C;
import protocol.infoclass.changUserInfo_S;
import protocol.infoclass.changeCode_C;
import protocol.infoclass.changeCode_S;
import protocol.infoclass.deleteActivityDiscuss_C;
import protocol.infoclass.deleteActivityDiscuss_S;
import protocol.infoclass.deleteActivityInfo_C;
import protocol.infoclass.deleteActivityInfo_S;
import protocol.infoclass.deleteActivitySpecific_C;
import protocol.infoclass.deleteActivitySpecific_S;
import protocol.infoclass.deletePersonalInfo_C;
import protocol.infoclass.deletePersonalInfo_S;
import protocol.infoclass.deleteRelation_C;
import protocol.infoclass.deleteRelation_S;
import protocol.infoclass.deleteUserInfo_C;
import protocol.infoclass.deleteUserInfo_S;
import protocol.infoclass.getActivityDiscussList_C;
import protocol.infoclass.getActivityDiscussList_S;
import protocol.infoclass.getActivityInfo_C;
import protocol.infoclass.getActivityInfo_S;
import protocol.infoclass.getActivitySpecificItemList_C;
import protocol.infoclass.getActivitySpecificItemList_S;
import protocol.infoclass.getPersonalInfo_C;
import protocol.infoclass.getPersonalInfo_S;
import protocol.infoclass.getRelaion_C;
import protocol.infoclass.getRelaion_S;
import protocol.infoclass.getUserInfo_C;
import protocol.infoclass.getUserInfo_S;
import protocol.infoclass.loginUser_C;
import protocol.infoclass.loginUser_S;
import protocol.infoclass.protocolwithbaidustore;
import protocol.infoclass.updateActivityOpinion_independent_C;
import protocol.infoclass.updateActivityOpinion_independent_S;
import protocol.infoclass.userPushInfo_C;
import protocol.infoclass.userPushInfo_S;
import protocol.pushservice.SEND_Activity_Join;
import protocol.pushservice.SEND_Activity_Join_Checked;
import protocol.pushservice.SEND_Activity_Leave_word;
import protocol.pushservice.SEND_Person_Leave_Word;
import protocol.pushservice.SEND_UserOpinion;

import com.google.gson.Gson;

import database.ActivityDiscussSelectData;
import database.ActivitySelectData;
import database.ActivitySpecificItemsSelectData;
import database.PersonalSelectData;
import database.RelationSelectData;
import database.UserInfoSelectData;
import datasouce.DBConnectionManager;
import dealfileupload.photofilepath;

/**
 * 不要修改UpLoadTime!!!!!!
 * 
 * @author Administrator
 * 
 */
public class H_Founction {

	static Logger logger = Logger.getLogger(H_Founction.class.getName());

	static Gson g = handle.g;

	/**
	 * 测试通过! 使用说明:本程序主要将收到的数据先判定合法性,接着存储到我们的服务器上,并转存至百度云上
	 * 部分合法性检测放置于手机端,服务器不做过多检测
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addActivity_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO
		addActivity_C ac = new addActivity_C();
		addActivity_S as = new addActivity_S();
		StringBuilder InsertSQL = new StringBuilder();
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		creatPoi creatpoi = new creatPoi();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分 RESOLVE
		ac.setActivityManagerID(obj.getString("ActivityManagerID"));
		ac.setBuildActivityUserID(obj.getString("BuildActivityUserID"));
		ac.setUploadTime(obj.getString("UploadTime"));

		ac.IsDirectJoinIn = obj.getString("IsDirectJoinIn");
		ac.ActivityFlag = obj.getString("ActivityFlag");
		ac.ActivityMemberNumber = obj.getString("ActivityMemberNumber");
		ac.ActivityMaxMemberNumber = obj.getString("ActivityMaxMemberNumber");
		ac.ActivityDescribe = obj.getString("ActivityDescribe");
		ac.ActivityName = obj.getString("ActivityName");
		ac.ActivityStartTime = obj.getString("ActivityStartTime");
		ac.ActivityEndTime = obj.getString("ActivityEndTime");
		ac.ActivityHoldPlace = obj.getString("ActivityHoldPlace");
		ac.HelpPhone = obj.getString("HelpPhone");

		// 设置相同项在最前面
		as.setUserID(ac.getBuildActivityUserID());
		as.setUploadTime(ac.getUploadTime());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("2");
			as.setContent("fail , some important element empty");
			objlist.add(as);
			logger.warn(obj);
			closeConnection(connection);
			return;
		}

		InsertSQL.append("INSERT into h_activity_baseinfo ");
		keyPart.append(" ( "
				+ " BuildActivityUserID , ActivityManagerID , UploadTime , IsDirectJoinIn , ActivityFlag , ActivityMemberNumber , ActivityMaxMemberNumber , ActivityDescribe , ActivityName , ActivityStartTime , ActivityEndTime , ActivityHoldPlace , HelpPhone ");
		valuePart.append(" values ("
				+ Long.valueOf(ac.getBuildActivityUserID()) + ","
				+ Long.valueOf(ac.getActivityManagerID()) + ", '"
				+ ac.getUploadTime() + "' , '" + ac.IsDirectJoinIn + "' , '"
				+ ac.ActivityFlag + "' , '" + ac.ActivityMemberNumber + "' , '"
				+ ac.ActivityMaxMemberNumber + "' , '" + ac.ActivityDescribe
				+ "' , '" + ac.ActivityName + "' , '" + ac.ActivityStartTime
				+ "' , '" + ac.ActivityEndTime + "' , '" + ac.ActivityHoldPlace
				+ "' , '" + ac.HelpPhone + "' ");

		if (obj.containsKey("ActivityBelongClass")) {
			ac.ActivityBelongClass = obj.getString("ActivityBelongClass");
			keyPart.append(", ActivityBelongClass ");
			valuePart.append(", '" + ac.ActivityBelongClass + "' ");
		}
		if (obj.containsKey("ActivityTags")) {
			ac.ActivityTags = obj.getString("ActivityTags");
			keyPart.append(", ActivityTags ");
			valuePart.append(", '" + ac.ActivityTags + "' ");
			creatpoi.setTags(ac.ActivityTags);
		}
		// 这里ActivityOpinion有单独方法处理评分
		if (obj.containsKey("ActivityOpinion")) {
			ac.ActivityOpinion = obj.getString("ActivityOpinion");
			keyPart.append(", ActivityOpinion ");
			valuePart.append(", '" + ac.ActivityOpinion + "' ");
		}
		if (obj.containsKey("ActivityAddress")) {
			ac.ActivityAddress = obj.getString("ActivityAddress");
			keyPart.append(", ActivityAddress ");
			valuePart.append(", '" + ac.ActivityAddress + "' ");
		}
		if (obj.containsKey("ActivityLogo")) {
			ac.ActivityLogo = obj.getString("ActivityLogo");
			keyPart.append(", ActivityLogo ");
			valuePart.append(", '" + ac.ActivityLogo + "' ");
		}

		keyPart.append(") ");
		valuePart.append(") ; ");

		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);

		// 逻辑部分判断LOGIC_JUDJE
		// 是否有此用户
		long UserID = Long.valueOf(ac.getBuildActivityUserID());
		String SearchUserIDSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ UserID + ";";
		// Statement psSearch, psInsert;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				System.out.println("Activity can build !");
			} else {
				as.setMark("2");
				as.setContent("fail , do not have this UserID");
				System.out
						.println("Activity build fail, search no this user ID!");
				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail , do not have this UserID");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 存入本服务器数据库SAVE_LOCIAL_DB
		long newAddActivityAddressID = 0;
		try {
			System.out.println("InsertSQL" + InsertSQL.toString());
			// ps = connection.prepareStatement(InsertSQL.toString(),
			// Statement.RETURN_GENERATED_KEYS);
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet result = ps.getGeneratedKeys();

			if (numberItem != 0) {
				as.setMark("1");
				as.setContent("success");
				System.out.println("Activity build success");

				if (result.next()) {
					newAddActivityAddressID = result.getInt(1);
					as.setActivityID(String.valueOf(newAddActivityAddressID));
				}
			} else {
				as.setMark("2");
				as.setContent("fail ,add no activity ");
				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			as.setMark("2");
			as.setContent("fail ,add activity SQLException");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 存入百度地理数据库SAVE_BAIDU_DB

		creatpoi.setCoord_type(protocolwithbaidustore.BAIDULL);
		creatpoi.setGeotable_id(String
				.valueOf(protocolwithbaidustore.baidutableactivityPOI));
		String[] LL = ac.ActivityHoldPlace.split(",");
		creatpoi.setLatitude(Double.valueOf(LL[0]));
		creatpoi.setLongitude(Double.valueOf(LL[1]));
		creatpoi.setAddress(ac.ActivityAddress);
		// tags 在上文已设置
		Map<String, Object> columnkey = new HashMap<String, Object>();
		columnkey.put("uploadingtime", ac.getUploadTime());
		columnkey.put("univeralindex", newAddActivityAddressID); // 活动账号
		columnkey.put("describe", ac.ActivityDescribe);
		columnkey.put("activity", ac.ActivityName);
		columnkey.put("launchman", ac.getBuildActivityUserID()); // launchman =?
																	// managerid
		columnkey.put("starttime", ac.ActivityStartTime);
		columnkey.put("endtime", ac.ActivityEndTime);
		columnkey.put("mobilephone", ac.HelpPhone);
		columnkey.put("single",
				ac.getUploadTime() + ac.getBuildActivityUserID());
		columnkey.put("activityalwaysjoinperson", 1);
		columnkey.put("managerid", ac.getBuildActivityUserID());
		columnkey.put("maxmembernumber", ac.ActivityMaxMemberNumber);
		columnkey.put("activitylogo", ac.ActivityLogo);
		columnkey.put("activityholdplace", ac.ActivityMemberNumber);

		creatpoi.setColumnkey(columnkey);

		maintest.server_version_2.handlePool
				.execute(new handleBaiduCloudDataThread(Integer
						.valueOf(creatpoi.getGeotable_id()), creatpoi, 0, 0));

		closeConnection(connection);

		objlist.add(as);

	}

	/**
	 * 测试通过! 添加活动发言,主要是非活动成员禁止发言
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addActivityDiscuss_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addActivityDiscuss_C ac = new addActivityDiscuss_C();
		addActivityDiscuss_S as = new addActivityDiscuss_S();
		StringBuilder InsertSQL = new StringBuilder();
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		ac.setActivityID(obj.getString("ActivityID"));
		ac.setUserID(obj.getString("UserID"));
		ac.setUploadTime(obj.getString("UploadTime"));

		// 相同发送部分
		as.setActivityID(ac.getActivityID());
		as.setUserID(ac.getUserID());
		as.setUploadTime(ac.getUploadTime());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("2");
			as.setContent("fail , some important element empty");

			objlist.add(as);
			closeConnection(connection);
			return;
		}

		InsertSQL.append("INSERT into h_activity_discusslist ");
		keyPart.append(" ( " + "ActivityID,UserID,UploadTime ");
		valuePart.append(" value ( " + Long.valueOf(ac.getActivityID()) + " , "
				+ Long.valueOf(ac.getUserID()) + " , '" + ac.getUploadTime()
				+ "' ");

		if (obj.containsKey("PointDiscussID")) {
			ac.PointDiscussID = obj.getString("PointDiscussID");
			keyPart.append(", PointDiscussID ");
			valuePart.append(", '" + ac.PointDiscussID + "' ");
		}
		if (obj.containsKey("ActivityName")) {
			ac.ActivityName = obj.getString("ActivityName");
			keyPart.append(", ActivityName ");
			valuePart.append(", '" + ac.ActivityName + "' ");
		}
		if (obj.containsKey("UserName")) {
			ac.UserName = obj.getString("UserName");
			keyPart.append(", UserName ");
			valuePart.append(", '" + ac.UserName + "' ");
		}
		if (obj.containsKey("DiscussContent")) {
			ac.DiscussContent = obj.getString("DiscussContent");
			keyPart.append(", DiscussContent ");
			valuePart.append(", '" + ac.DiscussContent + "' ");
		}
		if (obj.containsKey("Photo")) {
			ac.Photo = obj.getString("Photo");
			keyPart.append(", Photo ");
			valuePart.append(", '" + ac.Photo + "' ");
		}
		if (obj.containsKey("ThisUserID")) {
			ac.ThisUserID = obj.getString("ThisUserID");
			keyPart.append(", ThisUserID ");
			valuePart.append(", '" + ac.ThisUserID + "' ");
		}

		if (ac.Photo == null) {
			ac.Photo = "0";
		}

		keyPart.append(" ) ");
		valuePart.append(") ; ");
		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);

		// 逻辑部分
		// long UserID = Long.valueOf(ac.getUserID());
		long ActivityID = Long.valueOf(ac.getActivityID());
		// 这部分主要是可以
		String SearchUserIDSQL = "SELECT * FROM h_activity_baseinfo WHERE "
				+ " ActivityID = " + ActivityID + " ; ";

		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		long managerID = 0;
		long thisUserID = 0;

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				managerID = rs.getLong("ActivityManagerID");
			} else {
				as.setMark("2");
				as.setContent("fail , do not have this Activity");
				System.out
						.println("Activity build fail, SQL no this Activity! ");
				objlist.add(as);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail , SQLException");
			objlist.add(as);

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 数据存储部分
		// PreparedStatement psInsert;
		try {
			// psInsert = connection.prepareStatement(InsertSQL.toString(),
			// Statement.RETURN_GENERATED_KEYS);
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.getGeneratedKeys();

			if ((numberItem != 0)) {
				SEND_Activity_Leave_word send_ALW = new SEND_Activity_Leave_word();
				if (rs.next()) {
					as.setDiscussID(String.valueOf(rs.getLong(1)));
					as.setMark("1");
					as.setContent("success");
					ActivityDiscussSelectData adsd = new ActivityDiscussSelectData(
							rs.getLong(1), Long.valueOf(ac.getActivityID()),
							Long.valueOf(ac.getUserID()),
							Long.valueOf(ac.PointDiscussID),
							Long.valueOf(ac.ThisUserID), ac.getUploadTime(),
							ac.ActivityName, ac.UserName, ac.DiscussContent,
							ac.Photo);
					// System.out.println("_____________________" + adsd);
					thisUserID = adsd.getThisUserID();
					send_ALW.setAdsd(adsd);

					// InputInfoPushDB(ac.getUserID(), send_ALW);
					if (!ac.getUserID().equals(String.valueOf(managerID))) {
						InputInfoPushDB(String.valueOf(managerID), send_ALW, ps);
					}
					if ((thisUserID != 0)
							&& (thisUserID != Long.valueOf(ac.getUserID()))) {
						InputInfoPushDB(String.valueOf(thisUserID), send_ALW,
								ps);
					}
				} else {
					as.setMark("2");
					as.setContent("fail , insert error");
					objlist.add(as);
					closeConnection(connection);
					return;
				}

			} else {
				as.setMark("2");
				as.setContent("fail , insert error");
				objlist.add(as);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail , SQLException");
			objlist.add(as);
			System.out.println("Activity insert fail,SQLException! ");

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(as);
	}

	/**
	 * 测试通过! 主要保证有相应的活动,并且发送信息者是活动管理员或者是创办者
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addActivitySpecificItem_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addActivitySpecificItem_C ac = new addActivitySpecificItem_C();
		addActivitySpecificItem_S as = new addActivitySpecificItem_S();
		StringBuilder InsertSQL = new StringBuilder();
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		ac.setActivityID(obj.getString("ActivityID"));
		ac.setUploadTime(obj.getString("UploadTime"));
		ac.setUserID(obj.getString("UserID"));

		// 相同部分
		as.setActivityID(ac.getActivityID());
		as.setUserID(ac.getUserID());
		as.setUploadTime(ac.getUploadTime());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("2");
			as.setContent("fail , some important element empty");
			objlist.add(as);
			closeConnection(connection);
			return;
		}

		InsertSQL.append("INSERT into h_activity_specificitems ");
		keyPart.append(" ( " + "ActivityID,UploadTime ");
		valuePart.append(" value ( " + Long.valueOf(ac.getActivityID())
				+ " , '" + ac.getUploadTime() + "' ");

		if (obj.containsKey("ActivityItemHoldPlace")) {
			ac.ActivityItemHoldPlace = obj.getString("ActivityItemHoldPlace");
			keyPart.append(", ActivityItemHoldPlace ");
			valuePart.append(", '" + ac.ActivityItemHoldPlace + "' ");
		}
		if (obj.containsKey("ActivityItemHoldTime")) {
			ac.ActivityItemHoldTime = obj.getString("ActivityItemHoldTime");
			keyPart.append(", ActivityItemHoldTime ");
			valuePart.append(", '" + ac.ActivityItemHoldTime + "' ");
		}
		if (obj.containsKey("ActivitySpecificItemDescribe")) {
			ac.ActivitySpecificItemDescribe = obj
					.getString("ActivitySpecificItemDescribe");
			keyPart.append(", ActivitySpecificItemDescribe ");
			valuePart.append(", '" + ac.ActivitySpecificItemDescribe + "' ");
		}
		if (obj.containsKey("ActivitySpecificItemPhoto")) {
			ac.ActivitySpecificItemPhoto = obj
					.getString("ActivitySpecificItemPhoto");
			keyPart.append(", ActivitySpecificItemPhoto ");
			valuePart.append(", '" + ac.ActivitySpecificItemPhoto + "' ");
		}

		keyPart.append(" ) ");
		valuePart.append(") ; ");
		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);

		// 逻辑部分

		long UserID = Long.valueOf(ac.getUserID());
		String SearchUserIDSQL = "SELECT * FROM h_activity_baseinfo WHERE BuildActivityUserID = "
				+ UserID + " OR " + " ActivityManagerID = " + UserID + " ; ";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {

				System.out.println("This User Can Add Specific Item  !");
			} else {
				as.setMark("2");
				as.setContent("fail , This User Can't add specific item");
				System.out
						.println("Activity build fail, This User Can't add specific item! ");
				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail , SQLException");
			objlist.add(as);
			System.out
					.println("Activity specific item search fail,SQLException!");

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 数据存储部分
		// PreparedStatement psInsert;
		try {
			// psInsert = connection.prepareStatement(InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString());

			if (numberItem != 0) {
				as.setMark("1");
				as.setContent("success");
			} else {
				as.setMark("2");
				as.setContent("fail ,insert Item fail");
				objlist.add(as);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail , SQLException");
			objlist.add(as);
			System.out
					.println("Activity specific item insert fail,SQLException! ");
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(as);

	}

	/**
	 * 测试通过! 本功能主要是添加个人留言板
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addPersonalInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addPersonalInfo_C ac = new addPersonalInfo_C();
		addPersonalInfo_S as = new addPersonalInfo_S();
		StringBuilder InsertSQL = new StringBuilder();
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		ac.setUserID(obj.getString("UserID"));
		ac.setThisUserID(obj.getString("ThisUserID"));
		ac.setUploadTime(obj.getString("UploadTime"));

		as.setUserID(ac.getUserID());
		as.setThisUserID(ac.getThisUserID());
		as.setUploadTime(ac.getUploadTime());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("2");
			as.setContent("fail , some important element empty");
			objlist.add(as);
			closeConnection(connection);
			return;
		}

		InsertSQL.append("INSERT into h_user_discusslist ");
		keyPart.append(" ( " + "UserID,ThisUserID,UploadTime ");
		valuePart.append(" value ( " + Long.valueOf(ac.getUserID()) + ","
				+ Long.valueOf(ac.getThisUserID()) + " , '"
				+ ac.getUploadTime() + "' ");

		if (obj.containsKey("UserName")) {
			ac.UserName = obj.getString("UserName");
			keyPart.append(", UserName ");
			valuePart.append(", '" + ac.UserName + "' ");
		}
		if (obj.containsKey("Content")) {
			ac.Content = obj.getString("Content");
			keyPart.append(", Content ");
			valuePart.append(", '" + ac.Content + "' ");
		}
		if (obj.containsKey("Photo")) {
			ac.Photo = obj.getString("Photo");
			keyPart.append(", Photo ");
			valuePart.append(", '" + ac.Photo + "' ");
		}
		if (obj.containsKey("Place")) {
			ac.Place = obj.getString("Place");
			keyPart.append(", Place ");
			valuePart.append(", '" + ac.Place + "' ");
		}

		keyPart.append(" ) ");
		valuePart.append(") ; ");
		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);

		// 逻辑部分
		// no logic

		// 数据存储部分
		// PreparedStatement psInsert;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psInsert = connection.prepareStatement(InsertSQL.toString(),
			// Statement.RETURN_GENERATED_KEYS);
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.getGeneratedKeys();
			if (numberItem != 0) {
				as.setMark("1");
				as.setContent("success");
				SEND_Person_Leave_Word send_PLW = new SEND_Person_Leave_Word();
				if (rs.next()) {
					PersonalSelectData psd = new PersonalSelectData(
							rs.getLong(1), Long.valueOf(ac.getUserID()),
							Long.valueOf(ac.getThisUserID()),
							ac.getUploadTime(), ac.UserName, ac.Content,
							ac.Photo, ac.Place);
					send_PLW.setPsd(psd);
				}
				// InputInfoPushDB(ac.getUserID(), send_PLW);
				// 推送给被留言的人
				InputInfoPushDB(ac.getThisUserID(), send_PLW, ps);
			} else {
				as.setMark("2");
				as.setContent("fail");
				objlist.add(as);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(as);

	}

	/**
	 * 测试通过 本功能主要是将人与活动进行关联
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addRelation_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		// 解析部分
		addRelation_C ac = new addRelation_C();
		addRelation_S as = new addRelation_S();
		StringBuilder InsertSQL = new StringBuilder();
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		UserInfoSelectData uisd;
		ActivitySelectData asd;
		Connection connection;
		connection = DBConnectionManager.getConnection();

		ac.setActivityID(obj.getString("ActivityID"));
		ac.setUserID(obj.getString("UserID"));
		ac.setUploadTime(obj.getString("UploadTime"));
		ac.setStatus(obj.getString("Status"));

		int finalStatus = -1;
		int IsDirectJoinIn = -1;
		int ActivityMemberNumber = -1;
		int ActivityMaxMemberNumber = -1;
		// String UploadTime = "-1";
		String ManagerID = "-1";
		String ActivityID = ac.getActivityID();

		as.setActivityID(ac.getActivityID());
		as.setUserID(ac.getUserID());
		as.setUploadTime(ac.getUploadTime());
		as.setStatus(ac.getStatus());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("2");
			as.setStatus(ac.getStatus());
			as.setContent("fail , some important element empty");
			objlist.add(as);
			closeConnection(connection);
			return;
		}

		String SearchUserInfoSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ ac.getUserID() + " ; ";
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ResultSet rsuser = ps.executeQuery(SearchUserInfoSQL);
			if (rsuser.next()) {
				uisd = new UserInfoSelectData(rsuser.getLong(1),
						rsuser.getString(2), rsuser.getString(3),
						rsuser.getString(4), rsuser.getString(5),
						rsuser.getString(6), rsuser.getString(7),
						rsuser.getString(8), rsuser.getString(9),
						rsuser.getString(10), rsuser.getString(11),
						rsuser.getString(12), rsuser.getString(13),
						rsuser.getString(14), rsuser.getString(15),
						rsuser.getString(16), rsuser.getString(17),
						rsuser.getString(18), rsuser.getString(19),
						rsuser.getString(20));
			} else {
				as.setMark("2");
				as.setStatus(ac.getStatus());
				as.setContent("fail , 账户不存在...");
				objlist.add(as);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			as.setMark("2");
			as.setStatus(ac.getStatus());
			as.setContent("fail , SQLException");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 逻辑部分
		String SearchRepeatSQL = "SELECT * FROM h_activity_memberlist WHERE ActivityID = "
				+ ac.getActivityID() + " AND UserID = " + ac.getUserID() + " ;";
		// PreparedStatement psSearchRepeat;
		try {
			// psSearchRepeat = connection.prepareStatement(SearchRepeatSQL);
			ResultSet rs = ps.executeQuery(SearchRepeatSQL);
			if (rs != null) {
				if (rs.next()) {
					as.setMark("2");
					as.setStatus(ac.getStatus());
					as.setContent("fail , This User already in this activity");
					objlist.add(as);
					closeConnection(connection);
					return;
				} else {// 数据库之前没有相关信息,正确

				}
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setStatus(ac.getStatus());
			as.setContent("fail ");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 首先查找活动对参加者
		String SearchIDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
				+ Long.valueOf(ActivityID) + " ; ";
		// PreparedStatement psSearch;

		try {
			// psSearch = connection.prepareStatement(SearchIDSQL);
			ResultSet rs = ps.executeQuery(SearchIDSQL);
			if (rs.next()) {

				asd = new ActivitySelectData(rs.getLong(1), rs.getLong(2),
						rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17),
						rs.getString(18), rs.getString(19));
				// UploadTime = rs.getString("UploadTime");
				// ActivityID = rs.getString("ActivityID");
				IsDirectJoinIn = Integer
						.valueOf(rs.getString("IsDirectJoinIn"));
				ActivityMemberNumber = Integer.valueOf(rs
						.getString("ActivityMemberNumber"));
				ActivityMaxMemberNumber = Integer.valueOf(rs
						.getString("ActivityMaxMemberNumber"));
				ManagerID = String.valueOf(rs.getLong("BuildActivityUserID"));
			} else {
				as.setMark("2");
				as.setStatus(ac.getStatus());
				as.setContent("fail , This User Can't add specific item");
				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setStatus(ac.getStatus());
			as.setContent("fail ");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		finalStatus = longicJoin(IsDirectJoinIn,
				Integer.valueOf(ac.getStatus()));
		// 同步更新本地数据库与百度数据库
		if (finalStatus == 1) { // 验证
			if (ActivityMaxMemberNumber > ActivityMemberNumber) { // 会员人未满
				ActivityMemberNumber = ActivityMemberNumber++;
				String UpdateSQL = "UPDATE h_activity_baseinfo SET ActivityMemberNumber = '"
						+ ActivityMemberNumber
						+ "' WHERE ActivityID = "
						+ ActivityID + " ;";
				// PreparedStatement psUpdate;
				try {
					int numberItem = ps.executeUpdate(UpdateSQL.toString());
					if (numberItem != 0) {
						updateBaiduMemberNumber(ac.getUploadTime(),
								ac.getActivityID(), ActivityMemberNumber);
					} else {
						as.setMark("2");
						as.setStatus(ac.getStatus());
						as.setContent("fail,update fail");
						objlist.add(as);
						closeConnection(connection);
						return;
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					closeConnection(connection);
					e.printStackTrace();
					logger.error(log.ExceptionLogTool.getTrace(e));
					as.setMark("2");
					as.setStatus(ac.getStatus());
					as.setContent("fail");
					objlist.add(as);
					return;
				}

			} else { // 会员人满
				as.setMark("2");
				as.setStatus(ac.getStatus());
				as.setContent("fail, activity member full---"
						+ ActivityMaxMemberNumber + "," + ActivityMemberNumber);
				objlist.add(as);
				closeConnection(connection);
				return;
			}
		} else { // 其他状态
			as.setStatus(String.valueOf(finalStatus));
		}

		InsertSQL.append("INSERT into h_activity_memberlist ");
		keyPart.append(" ( " + "ActivityID,UserID,UploadTime,Status ");
		valuePart.append(" value ( " + Long.valueOf(ac.getActivityID()) + ","
				+ Long.valueOf(ac.getUserID()) + " , '" + ac.getUploadTime()
				+ "' , '" + finalStatus + "' ");

		keyPart.append(" ) ");
		valuePart.append(") ; ");
		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);

		// 数据存储部分
		// PreparedStatement psInsert;
		try {
			// psInsert = connection.prepareStatement(InsertSQL.toString(),
			// Statement.RETURN_GENERATED_KEYS);
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.getGeneratedKeys();

			if (numberItem != 0) {
				as.setMark("1");
				as.setContent("success");
				as.setStatus(String.valueOf(finalStatus));
				SEND_Activity_Join sendAJ = new SEND_Activity_Join();
				// rs.getString("RelationID");
				if (rs.next()) {
					RelationSelectData rsd = new RelationSelectData(
							rs.getLong(1), Long.valueOf(ac.getActivityID()),
							Long.valueOf(ac.getUserID()), ac.getUploadTime(),
							String.valueOf(finalStatus));
					sendAJ.setRsd(rsd);
					sendAJ.setUisd(uisd);
					sendAJ.setAsd(asd);
				}
				InputInfoPushDB(ManagerID, sendAJ, ps);
			} else {
				as.setMark("2");
				as.setContent("fail");
				as.setStatus(ac.getStatus());

				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail");
			as.setStatus(ac.getStatus());
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist
		objlist.add(as);
	}

	/**
	 * 对obj有要求
	 * 
	 * @param managerID
	 * @param obj
	 *            SEND_标志可传入
	 */
	private static void InputInfoPushDB(String managerID, Object obj,
			Statement ps) {
		// TODO Auto-generated method stub
		String savePushString = g.toJson(obj);
		// PreparedStatement psInsert;
		String InsertSQL = "INSERT into h_user_push_message (UserID, PushMessage) "
				+ "VALUE(" + managerID + ", '" + savePushString + "'); ";
		try {
			// psInsert = connection.prepareStatement(InsertSQL);
			int resume = ps.executeUpdate(InsertSQL);
			if (resume == 1) {
				System.out.println("InputInfoPushDB insert success");
			} else {
				System.out.println("InputInfoPushDB insert fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}
	}

	/**
	 * 返回用户与活动的状态
	 * 
	 * @param isDirectJoinIn
	 * @param requestStatus
	 * @return
	 */
	private static int longicJoin(int isDirectJoinIn, int requestStatus) {
		if (isDirectJoinIn == 1) { // 可以直接加入
			if (requestStatus == 1) {
				return 1;
			} else if (requestStatus == 2) {
				return 1;
			} else if (requestStatus == 3) {
				return 3;
			} else if (requestStatus == 4) {
				return 4;
			}
		} else if (isDirectJoinIn == 2) { // 不可以直接加入
			if (requestStatus == 1) {
				return 2;
			} else if (requestStatus == 2) {
				return 2;
			} else if (requestStatus == 3) {
				return 3;
			} else if (requestStatus == 4) {
				return 4;
			}
		}
		return requestStatus;
	}

	/**
	 * 测试通过
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addUser_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addUser_C ac = new addUser_C();
		addUser_S as = new addUser_S();
		StringBuilder InsertSQL = new StringBuilder();
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		ac.setUserID(obj.getString("UserID"));
		ac.setUploadTime(obj.getString("UploadTime"));

		ac.Code = obj.getString("Code");
		ac.UserName = obj.getString("UserName");
		ac.UserPhone = obj.getString("UserPhone");

		as.setUserID(ac.getUserID());
		as.setUploadTime(ac.getUploadTime());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("1");
			as.setContent("fail , some important element empty");
			objlist.add(as);
			closeConnection(connection);
			return;
		}

		InsertSQL.append("INSERT into h_user_baseinfo ");
		keyPart.append(" ( "
				+ " UserID , UploadTime , Code , UserName , UserPhone");
		valuePart.append(" value ( " + Long.valueOf(ac.getUserID()) + ", '"
				+ ac.getUploadTime() + "' , '" + ac.Code + "' , '"
				+ ac.UserName + "' , '" + ac.UserPhone + "' ");

		if (obj.containsKey("UserJoinActivity")) {
			ac.UserJoinActivity = obj.getString("UserJoinActivity");
			keyPart.append(", UserJoinActivity ");
			valuePart.append(", '" + ac.UserJoinActivity + "' ");
		}
		if (obj.containsKey("UserAttentionClass")) {
			ac.UserAttentionClass = obj.getString("UserAttentionClass");
			keyPart.append(", UserAttentionClass ");
			valuePart.append(", '" + ac.UserAttentionClass + "' ");
		}
		if (obj.containsKey("UserAttentionClass")) {
			ac.UserAttentionClass = obj.getString("UserAttentionClass");
			keyPart.append(", UserAttentionClass ");
			valuePart.append(", '" + ac.UserAttentionClass + "' ");
		}
		if (obj.containsKey("UserQQ")) {
			ac.UserQQ = obj.getString("UserQQ");
			keyPart.append(", UserQQ ");
			valuePart.append(", '" + ac.UserQQ + "' ");
		}
		if (obj.containsKey("UserWeiChat")) {
			ac.UserWeiChat = obj.getString("UserWeiChat");
			keyPart.append(", UserWeiChat ");
			valuePart.append(", '" + ac.UserWeiChat + "' ");
		}
		if (obj.containsKey("UserTags")) {
			ac.UserTags = obj.getString("UserTags");
			keyPart.append(", UserTags ");
			valuePart.append(", '" + ac.UserTags + "' ");
		}
		if (obj.containsKey("UserClass")) {
			ac.UserClass = obj.getString("UserClass");
			keyPart.append(", UserClass ");
			valuePart.append(", '" + ac.UserClass + "' ");
		}
		if (obj.containsKey("UserDescribe")) {
			ac.UserDescribe = obj.getString("UserDescribe");
			keyPart.append(", UserDescribe ");
			valuePart.append(", '" + ac.UserDescribe + "' ");
		}
		if (obj.containsKey("UserLevel")) {
			ac.UserLevel = obj.getString("UserLevel");
			keyPart.append(", UserLevel ");
			valuePart.append(", '" + ac.UserLevel + "' ");
		}
		if (obj.containsKey("UserLogo")) {
			ac.UserLogo = obj.getString("UserLogo");
			keyPart.append(", UserLogo ");
			valuePart.append(", '" + ac.UserLogo + "' ");
		}
		if (obj.containsKey("UserAge")) {
			ac.UserAge = obj.getString("UserAge");
			keyPart.append(", UserAge ");
			valuePart.append(", '" + ac.UserAge + "' ");
		}
		if (obj.containsKey("UserSex")) {
			ac.UserSex = obj.getString("UserSex");
			keyPart.append(", UserSex ");
			valuePart.append(", '" + ac.UserSex + "' ");
		}
		if (obj.containsKey("School")) {
			ac.School = obj.getString("School");
			keyPart.append(", School ");
			valuePart.append(", '" + ac.School + "' ");
		}
		if (obj.containsKey("Profession")) {
			ac.Profession = obj.getString("Profession");
			keyPart.append(", Profession ");
			valuePart.append(", '" + ac.Profession + "' ");
		}
		if (obj.containsKey("Birthday")) {
			ac.Birthday = obj.getString("Birthday");
			keyPart.append(", Birthday ");
			valuePart.append(", '" + ac.Birthday + "' ");
		}
		if (obj.containsKey("Home")) {
			ac.Home = obj.getString("Home");
			keyPart.append(", Home ");
			valuePart.append(", '" + ac.Home + "' ");
		}

		keyPart.append(" ) ");
		valuePart.append(" ) ; ");
		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);

		System.out.println("InsertSQL : " + InsertSQL);

		// 逻辑部分

		long UserID = Long.valueOf(ac.getUserID());
		String SearchUserIDSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ UserID + ";";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				as.setMark("2");
				as.setContent("UserID repeat");
				System.out.println("UserID repeat!");
				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("UserID repeat");
			objlist.add(as);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 数据存储部分
		// PreparedStatement psInsert;
		try {
			// psInsert = connection.prepareStatement(InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString());

			if (numberItem != 0) {
				as.setMark("1");
				as.setContent("success");
			} else {
				as.setMark("2");
				as.setContent("fail");
				objlist.add(as);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			as.setMark("2");
			as.setContent("UserID repeat");
			objlist.add(as);
			return;
		}

		closeConnection(connection);

		// 传入objlist
		objlist.add(as);
	}

	/**
	 * 添加用户反馈
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void addUserFeedback_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addUserFeedback_C ac = new addUserFeedback_C();
		addUserFeedback_S as = new addUserFeedback_S();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		ac.setConnect(obj.getString("Connect"));
		ac.setFeedback(obj.getString("Feedback"));
		ac.setPhoto(obj.getString("Photo"));
		ac.setUploadTime(obj.getString("UploadTime"));
		ac.setUserID(obj.getString("UserID"));

		as.setUserID(ac.getUserID());
		as.setUploadTime(ac.getUploadTime());

		if (ac.isHaveTOHaveEmpty()) {
			as.setMark("2");
			as.setContent("fail , some important element empty");
			objlist.add(as);
			closeConnection(connection);
			return;
		}

		String InsertSQL = "INSERT into feedback ( UserID , Feedback , Photo , Connect ) value ( "
				+ ac.getUserID()
				+ ", '"
				+ ac.getFeedback()
				+ "' , '"
				+ ac.getPhoto() + "' , '" + ac.getConnect() + "' );";

		// PreparedStatement psInsert;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psInsert = connection.prepareStatement(InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString());

			if (numberItem != 0) {
				as.setMark("1");
				as.setContent("success");

			} else {
				as.setMark("2");
				as.setContent("fail , insert error");
				objlist.add(as);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			closeConnection(connection);
			as.setMark("2");
			as.setContent("fail , SQLException");
			objlist.add(as);
			System.out.println("feedback insert fail,SQLException! ");

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		objlist.add(as);
	}

	/**
	 * 测试通过
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void changActivityInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		changActivityInfo_C cc = new changActivityInfo_C();
		changActivityInfo_S cs = new changActivityInfo_S();
		StringBuilder UpdateSQL = new StringBuilder();
		StringBuilder SetPart = new StringBuilder();
		StringBuilder WherePart = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		cc.setUserID(obj.getString("UserID"));
		cc.setActivityID(obj.getString("ActivityID"));
		// cc.setBuildActivityUserID(obj.getString("BuildActivityUserID"));
		cc.setActivityManagerID(obj.getString("ActivityManagerID"));
		cc.setUploadTime(obj.getString("UploadTime"));

		cs.setUserID(cc.getUserID());
		cs.setActivityID(cc.getActivityID());
		cs.setUploadTime(cc.getUploadTime());

		if (cc.isHaveTOHaveEmpty()) {
			cs.setMark("2");
			cs.setContent("fail , some important element empty");
			objlist.add(cs);
			closeConnection(connection);
			return;
		}

		UpdateSQL.append("UPDATE h_activity_baseinfo ");
		SetPart.append(" SET ");
		WherePart.append(" WHERE ActivityID = "
				+ Long.valueOf(cc.getActivityID()) + " ; ");

		SetPart.append(" ActivityManagerID = "
				+ Long.valueOf(cc.getActivityManagerID()) + " ");

		if (obj.containsKey("IsDirectJoinIn")) {
			cc.IsDirectJoinIn = obj.getString("IsDirectJoinIn");
			SetPart.append(" , IsDirectJoinIn = '" + cc.IsDirectJoinIn + "' ");
		}
		if (obj.containsKey("ActivityFlag")) {
			cc.ActivityFlag = obj.getString("ActivityFlag");
			SetPart.append(" , ActivityFlag = '" + cc.ActivityFlag + "' ");
		}
		if (obj.containsKey("ActivityMemberNumber")) {
			cc.ActivityMemberNumber = obj.getString("ActivityMemberNumber");
			SetPart.append(" , ActivityMemberNumber = '"
					+ cc.ActivityMemberNumber + "' ");
		}
		if (obj.containsKey("ActivityMaxMemberNumber")) {
			cc.ActivityMaxMemberNumber = obj
					.getString("ActivityMaxMemberNumber");
			SetPart.append(" , ActivityMaxMemberNumber = '"
					+ cc.ActivityMaxMemberNumber + "' ");
		}
		if (obj.containsKey("ActivityDescribe")) {
			cc.ActivityDescribe = obj.getString("ActivityDescribe");
			SetPart.append(" , ActivityDescribe = '" + cc.ActivityDescribe
					+ "' ");
		}
		if (obj.containsKey("ActivityName")) {
			cc.ActivityName = obj.getString("ActivityName");
			SetPart.append(" , ActivityName = '" + cc.ActivityName + "' ");
		}
		if (obj.containsKey("ActivityStartTime")) {
			cc.ActivityStartTime = obj.getString("ActivityStartTime");
			SetPart.append(" , ActivityStartTime = '" + cc.ActivityStartTime
					+ "' ");
		}
		if (obj.containsKey("ActivityEndTime")) {
			cc.ActivityEndTime = obj.getString("ActivityEndTime");
			SetPart.append(" , ActivityEndTime = '" + cc.ActivityEndTime + "' ");
		}
		if (obj.containsKey("ActivityHoldPlace")) {
			cc.ActivityHoldPlace = obj.getString("ActivityHoldPlace");
			SetPart.append(" , ActivityHoldPlace = '" + cc.ActivityHoldPlace
					+ "' ");
		}
		if (obj.containsKey("HelpPhone")) {
			cc.HelpPhone = obj.getString("HelpPhone");
			SetPart.append(" , HelpPhone = '" + cc.HelpPhone + "' ");
		}
		if (obj.containsKey("ActivityAddress")) {
			cc.HelpPhone = obj.getString("ActivityAddress");
			SetPart.append(" , ActivityAddress = '" + cc.ActivityAddress + "' ");
		}
		if (obj.containsKey("ActivityLogo")) {
			cc.ActivityLogo = obj.getString("ActivityLogo");
			SetPart.append(" , ActivityLogo = '" + cc.ActivityLogo + "' ");
		}

		UpdateSQL.append(SetPart);
		UpdateSQL.append(WherePart);

		// 逻辑部分
		String SearchUserIDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
				+ cc.getActivityID() + ";";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				if (rs.getLong("ActivityManagerID") == Long.valueOf(cc
						.getUserID())) {

					// 数据存储部分
					// PreparedStatement psUpdate;
					try {
						// psUpdate = connection.prepareStatement(UpdateSQL
						// .toString());
						int numberItem = ps.executeUpdate(UpdateSQL.toString());
						if (numberItem != 0) {
							cs.setMark("1");
							cs.setContent("success");
						} else {
							cs.setMark("2");
							cs.setContent("fail");
							objlist.add(cs);
							closeConnection(connection);
							return;
						}
					} catch (SQLException e) {
						closeConnection(connection);
						cs.setMark("2");
						cs.setContent("fail");
						objlist.add(cs);
						e.printStackTrace();
						logger.error(log.ExceptionLogTool.getTrace(e));

						return;
					}

				} else {
					cs.setMark("2");
					cs.setContent("fail");
					objlist.add(cs);
					closeConnection(connection);
					return;
				}
			} else {
				cs.setMark("2");
				cs.setContent("fail");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			cs.setMark("2");
			cs.setContent("fail");
			objlist.add(cs);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(cs);
	}

	/**
	 * 测试通过 针对活动管理员,对申请参加活动者进行处理
	 * 
	 * @author 侯斌
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void changRelation_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		changRelation_C cc = new changRelation_C();
		changRelation_S cs = new changRelation_S();
		StringBuilder UpdateSQL = new StringBuilder();
		StringBuilder SetPart = new StringBuilder();
		StringBuilder WherePart = new StringBuilder();
		SEND_Activity_Join_Checked SEND_AJC = new SEND_Activity_Join_Checked();
		ActivitySelectData asd;
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分

		cc.setSUserID(obj.getString("SUserID"));
		cc.setRelationID(obj.getString("RelationID"));
		cc.setActivityID(obj.getString("ActivityID"));
		cc.setUserID(obj.getString("UserID"));
		cc.setUploadTime(obj.getString("UploadTime"));
		cc.setStatus(obj.getString("Status"));

		UpdateSQL.append("UPDATE h_activity_memberlist ");
		// SetPart.append(" SET Status = '" + cc.getStatus() + "' ");
		WherePart.append(" WHERE RelationID = "
				+ Long.valueOf(cc.getRelationID()) + " ; ");

		// 逻辑部分
		cs.setRelationID(cc.getRelationID());
		cs.setActivityID(cc.getActivityID());
		cs.setUserID(cc.getSUserID());
		cs.setUploadTime(cc.getUploadTime());

		if (cc.isHaveTOHaveEmpty()) {
			cs.setMark("2");
			cs.setStatus(cc.getStatus());
			cs.setContent("fail , some important element empty");
			objlist.add(cs);
			closeConnection(connection);
			return;
		}
		if (cc.getSUserID().equals(cc.getUserID())) {
			cs.setMark("2");
			cs.setStatus(cc.getStatus());
			cs.setContent("fail ,群主无法修改自己的状态");
			objlist.add(cs);
			closeConnection(connection);
			return;
		}

		String SearchUserIDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
				+ Long.valueOf(cc.getActivityID())
				+ " AND BuildActivityUserID = " + cc.getSUserID() + " ; ";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				asd = new ActivitySelectData(rs.getLong(1), rs.getLong(2),
						rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17),
						rs.getString(18), rs.getString(19));
				int ActivityMemberNumber = Integer.valueOf(rs
						.getString("ActivityMemberNumber"));
				int ActivityMaxMemberNumber = Integer.valueOf(rs
						.getString("ActivityMaxMemberNumber"));
				if ((rs.getLong("ActivityManagerID") == Long.valueOf(cc
						.getSUserID()))
						|| (rs.getLong("BuildActivityUserID") == Long
								.valueOf(cc.getSUserID()))) {
					SetPart.append(" SET Status = '" + cc.getStatus() + "' ");
					if (cc.getStatus().equals("1")) { // 2或n->1 审核通过
						ActivityMemberNumber++;
					} else if (cc.getStatus().equals("6")) { // 1->6被踢出用户
						ActivityMemberNumber--;
					} else if (cc.getStatus().equals("5")) {
						// do nothing...
					}

					if (ActivityMaxMemberNumber >= ActivityMemberNumber) { // 会员人未满,添加删除均可
						String UpdateLocalSQL = "UPDATE h_activity_baseinfo SET ActivityMemberNumber = '"
								+ ActivityMemberNumber
								+ "' WHERE ActivityID = "
								+ cc.getActivityID()
								+ " ;";
						System.out.println("UpdateLocalSQL" + UpdateLocalSQL);
						// PreparedStatement psUpdate;
						try {
							// psUpdate = connection
							// .prepareStatement(UpdateLocalSQL.toString());
							int numberItem = ps.executeUpdate(UpdateLocalSQL
									.toString());
							if (numberItem != 0) {

								updateBaiduMemberNumber(cc.getUploadTime(),
										cc.getActivityID(),
										ActivityMemberNumber);
							} else {
								cs.setMark("2");
								cs.setStatus(cc.getStatus());
								cs.setContent("fail,update fail");
								objlist.add(cs);
								closeConnection(connection);
								return;
							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							closeConnection(connection);
							e.printStackTrace();
							logger.error(log.ExceptionLogTool.getTrace(e));
							cs.setMark("2");
							cs.setStatus(cc.getStatus());
							cs.setContent("fail");
							objlist.add(cs);
							return;
						}

					} else { // 会员人满
						cs.setMark("2");
						cs.setStatus(cc.getStatus());
						cs.setContent("fail, activity member full");
						objlist.add(cs);
						closeConnection(connection);
						return;
					}

					cs.setStatus(cc.getStatus());

					RelationSelectData rsd = new RelationSelectData(
							rs.getLong(1), rs.getLong(2), rs.getLong(3),
							rs.getString(4), rs.getString(5));
					SEND_AJC.setRsd(rsd);
					SEND_AJC.setAsd(asd);
					InputInfoPushDB(cc.getUserID(), SEND_AJC, ps);
				} else {
					cs.setMark("2");
					cs.setContent("fail, user no this right");
					objlist.add(cs);
					closeConnection(connection);
					return;
				}

			} else {
				cs.setMark("2");
				cs.setContent("fail, no this relation");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			cs.setMark("2");
			cs.setContent("fail, exception");
			objlist.add(cs);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 数据存储部分

		UpdateSQL.append(SetPart);
		UpdateSQL.append(WherePart);

		System.out.println("UpdateSQL : " + UpdateSQL);

		// PreparedStatement psUpdate;
		try {
			// psUpdate = connection.prepareStatement(UpdateSQL.toString());
			int numberItem = ps.executeUpdate(UpdateSQL.toString());
			// System.out.println("numberItem : " + numberItem);
			if (numberItem != 0) {
				cs.setMark("1");
				cs.setContent("success");
			} else {
				cs.setMark("2");
				cs.setContent("fail");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			cs.setMark("2");
			cs.setContent("fail");
			objlist.add(cs);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(cs);

	}

	/**
	 * 这里没有告诉id
	 * 
	 * @param UploadTime
	 * @param ActivityID
	 * @param activityMemberNumber
	 */
	private static void updateBaiduMemberNumber(String UploadTime,
			String ActivityID, int activityMemberNumber) {
		// TODO Auto-generated method stub
		updatePoi updatepoi = new updatePoi();
		updatepoi.setCoord_type(protocolwithbaidustore.BAIDULL);
		// 这里一定要填所属表
		updatepoi.setGeotable_id(String
				.valueOf(protocolwithbaidustore.baidutableactivityPOI));
		// tags 在上文已设置
		Map<String, Object> columnkey = new HashMap<String, Object>();
		columnkey.put("activityalwaysjoinperson", activityMemberNumber);
		// columnkey.put("uploadingtime", UploadTime);
		// columnkey.put("Single", UploadTime + ActivityID);
		columnkey.put("univeralindex", ActivityID);
		updatepoi.setColumnkey(columnkey);

		//
		maintest.server_version_2.handlePool
				.execute(new handleBaiduCloudDataThread(
						Integer.valueOf(protocolwithbaidustore.baidutableudpateactivityPOI),
						updatepoi, 0, 0));
	}

	/**
	 * 测试通过
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void changUserInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		changUserInfo_C cc = new changUserInfo_C();
		changUserInfo_S cs = new changUserInfo_S();
		StringBuilder UpdateSQL = new StringBuilder();
		StringBuilder SetPart = new StringBuilder();
		StringBuilder WherePart = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分

		cc.setUserID(obj.getString("UserID"));
		cc.setUploadTime(obj.getString("UploadTime"));

		cc.Code = obj.getString("Code");
		cc.UserName = obj.getString("UserName");
		cc.UserPhone = obj.getString("UserPhone");

		UpdateSQL.append("UPDATE h_user_baseinfo ");
		SetPart.append(" SET UploadTime = '" + cc.getUploadTime() + "' ");

		WherePart.append(" WHERE UserID = " + Long.valueOf(cc.getUserID())
				+ " ; ");

		// 必须项的判断
		if (!cc.Code.equals("0")) {
			SetPart.append(", Code = '" + cc.Code + "' ");
		}
		if (!cc.UserName.equals("0")) {
			SetPart.append(", UserName = '" + cc.UserName + "' ");
		}
		if (!cc.UserPhone.equals("0")) {
			SetPart.append(", UserPhone = '" + cc.UserPhone + "' ");
		}

		// 非必须项的判断
		if (obj.containsKey("UserJoinActivity")) {
			cc.UserJoinActivity = obj.getString("UserJoinActivity");
			SetPart.append(", UserJoinActivity = '" + cc.UserJoinActivity
					+ "' ");
		}
		if (obj.containsKey("UserAttentionClass")) {
			cc.UserAttentionClass = obj.getString("UserAttentionClass");
			SetPart.append(", UserAttentionClass = '" + cc.UserAttentionClass
					+ "' ");
		}
		if (obj.containsKey("UserQQ")) {
			cc.UserQQ = obj.getString("UserQQ");
			SetPart.append(", UserQQ = '" + cc.UserQQ + "' ");
		}
		if (obj.containsKey("UserWeiChat")) {
			cc.UserWeiChat = obj.getString("UserWeiChat");
			SetPart.append(", UserWeiChat = '" + cc.UserWeiChat + "' ");
		}
		if (obj.containsKey("UserTags")) {
			cc.UserTags = obj.getString("UserTags");
			SetPart.append(", UserTags = '" + cc.UserTags + "' ");
		}
		if (obj.containsKey("UserClass")) {
			cc.UserClass = obj.getString("UserClass");
			SetPart.append(", UserClass = '" + cc.UserClass + "' ");
		}
		if (obj.containsKey("UserDescribe")) {
			cc.UserDescribe = obj.getString("UserDescribe");
			SetPart.append(", UserDescribe = '" + cc.UserDescribe + "' ");
		}
		if (obj.containsKey("UserLevel")) {
			cc.UserLevel = obj.getString("UserLevel");
			SetPart.append(", UserLevel = '" + cc.UserLevel + "' ");
		}
		if (obj.containsKey("UserLogo")) {
			cc.UserLogo = obj.getString("UserLogo");
			SetPart.append(", UserLogo = '" + cc.UserLogo + "' ");
		}
		if (obj.containsKey("UserAge")) {
			cc.UserAge = obj.getString("UserAge");
			SetPart.append(", UserAge = '" + cc.UserAge + "' ");
		}
		if (obj.containsKey("UserSex")) {
			cc.UserSex = obj.getString("UserSex");
			SetPart.append(", UserSex = '" + cc.UserSex + "' ");
		}
		if (obj.containsKey("School")) {
			cc.School = obj.getString("School");
			SetPart.append(", School = '" + cc.School + "' ");
		}
		if (obj.containsKey("Profession")) {
			cc.Profession = obj.getString("Profession");
			SetPart.append(", Profession = '" + cc.Profession + "' ");
		}
		if (obj.containsKey("Birthday")) {
			cc.Birthday = obj.getString("Birthday");
			SetPart.append(", Birthday = '" + cc.Birthday + "' ");
		}
		if (obj.containsKey("Home")) {
			cc.Home = obj.getString("Home");
			SetPart.append(", Home = '" + cc.Home + "' ");
		}

		UpdateSQL.append(SetPart);
		UpdateSQL.append(WherePart);

		// 逻辑部分
		cs.setUserID(cc.getUserID());
		cs.setUploadTime(cc.getUploadTime());

		if (cc.isHaveTOHaveEmpty()) {
			cs.setMark("2");
			cs.setContent("fail , some important element empty");
			objlist.add(cs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		// System.out.println("UpdateSQL : " + UpdateSQL);
		// Check user code
		String SelectSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ cc.getUserID() + " ; ";
		// PreparedStatement psSelect;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelect = connection.prepareStatement(SelectSQL);
			ResultSet rs = ps.executeQuery(SelectSQL);
			if (rs.next()) {
				String Code = rs.getString("Code");
				if (Code.equals(cc.Code)) {

				} else {
					cs.setMark("2");
					cs.setContent("fail code was not right");
					objlist.add(cs);
					closeConnection(connection);
					return;
				}
			} else {
				cs.setMark("2");
				cs.setContent("fail no this user");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			cs.setMark("2");
			cs.setContent("fail no this user");
			objlist.add(cs);
			e.printStackTrace();
			return;
		}

		// PreparedStatement psUpdate;
		try {
			// psUpdate = connection.prepareStatement(UpdateSQL.toString());
			int numberItem = ps.executeUpdate(UpdateSQL.toString());
			// System.out.println("numberItem : " + numberItem);
			if (numberItem != 0) {
				cs.setMark("1");
				cs.setContent("success");
			} else {
				cs.setMark("2");
				cs.setContent("fail no this user");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			cs.setMark("2");
			cs.setContent("fail");
			objlist.add(cs);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(cs);
	}

	/**
	 * 修改个人信息
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void changeCode_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		changeCode_C cc = new changeCode_C();
		changeCode_S cs = new changeCode_S();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		cc.setUserID(obj.getString("UserID"));
		cc.setUploadTime(obj.getString("UploadTime"));
		cc.setCode(obj.getString("Code"));
		cc.setNewCode(obj.getString("NewCode"));

		cs.setUserID(cc.getUserID());
		cs.setUploadTime(cc.getUploadTime());

		if (cc.isHaveTOHaveEmpty()) {
			cs.setNewCode(cc.getCode());
			cs.setMark("2");
			cs.setContent("fail , some important element empty");
			objlist.add(cs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		// System.out.println("UpdateSQL : " + UpdateSQL);
		// Check user code
		String SelectSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ cc.getUserID() + " ; ";
		// PreparedStatement psSelect;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelect = connection.prepareStatement(SelectSQL);
			ResultSet rs = ps.executeQuery(SelectSQL);
			if (rs.next()) {
				String Code = rs.getString("Code");
				if (Code.equals(cc.getCode())) {

				} else {
					cs.setNewCode(cc.getCode());
					cs.setMark("2");
					cs.setContent("fail code was not right");
					objlist.add(cs);
					closeConnection(connection);
					return;
				}
			} else {
				cs.setNewCode(cc.getCode());
				cs.setMark("2");
				cs.setContent("fail no this user");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			cs.setNewCode(cc.getCode());
			cs.setMark("2");
			cs.setContent("fail no this user");
			objlist.add(cs);
			return;
		}

		String UpdateSQL = "UPDATE h_user_baseinfo SET Code = '"
				+ cc.getNewCode() + "' WHERE UserID = " + cc.getUserID()
				+ " ; ";

		// PreparedStatement psUpdate;
		try {
			// psUpdate = connection.prepareStatement(UpdateSQL.toString());
			int numberItem = ps.executeUpdate(UpdateSQL.toString());
			// System.out.println("numberItem : " + numberItem);
			if (numberItem != 0) {
				cs.setNewCode(cc.getNewCode());
				cs.setMark("1");
				cs.setContent("success");
			} else {
				cs.setNewCode(cc.getCode());
				cs.setMark("2");
				cs.setContent("fail no this user");
				objlist.add(cs);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			cs.setMark("2");
			cs.setContent("fail");
			objlist.add(cs);
			return;
		}

		closeConnection(connection);
		// 传入objlist

		objlist.add(cs);

	}

	/**
	 * 测试通过! 未验证人员身份!
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void deleteActivityDiscuss_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		deleteActivityDiscuss_C dc = new deleteActivityDiscuss_C();
		deleteActivityDiscuss_S ds = new deleteActivityDiscuss_S();
		StringBuilder DeleteSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分

		dc.setDiscussID(obj.getString("DiscussID"));
		dc.setActivityID(obj.getString("ActivityID"));
		dc.setUserID(obj.getString("UserID"));
		dc.setUploadTime(obj.getString("UploadTime"));

		ds.setDiscussID(dc.getDiscussID());
		ds.setActivityID(dc.getActivityID());
		ds.setUserID(dc.getUserID());
		ds.setUploadTime(dc.getUploadTime());

		if (dc.isHaveTOHaveEmpty()) {
			ds.setMark("2");
			ds.setContent("fail , some important element empty");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		// 逻辑部分

		// 数据存储部分
		DeleteSQL.append("DELETE FROM h_activity_discusslist WHERE ");
		DeleteSQL.append(" DiscussID = " + Long.valueOf(dc.getDiscussID())
				+ " ; ");
		// PreparedStatement psDelete;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psDelete = connection.prepareStatement(DeleteSQL.toString());
			int numberItem = ps.executeUpdate(DeleteSQL.toString());
			if (numberItem == 0) {
				ds.setMark("2");
				ds.setContent("fail no this item");
				objlist.add(ds);
				closeConnection(connection);
				return;
			} else {
				ds.setMark("1");
				ds.setContent("success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			ds.setMark("2");
			ds.setContent("fail no this item");
			objlist.add(ds);

			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(ds);
	}

	/**
	 * 测试通过!
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void deleteActivityInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		deleteActivityInfo_C dc = new deleteActivityInfo_C();
		deleteActivityInfo_S ds = new deleteActivityInfo_S();
		StringBuilder DeleteSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();
		// 解析部分

		dc.setUserID(obj.getString("UserID"));

		dc.setActivityID(obj.getString("ActivityID"));
		dc.setUploadTime(obj.getString("UploadTime"));

		// 逻辑部分
		ds.setUserID(dc.getUserID());
		ds.setUploadTime(ds.getUploadTime());
		ds.setActivityID(dc.getActivityID());

		if (dc.isHaveTOHaveEmpty()) {
			ds.setMark("2");
			ds.setContent("fail , some important element empty");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		String SearchUserIDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
				+ dc.getActivityID() + ";";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				if (rs.getString("ActivityManagerID").equals(dc.getUserID())) {
					// 数据存储部分
					DeleteSQL.append("DELETE FROM h_activity_baseinfo WHERE ");
					DeleteSQL.append(" ActivityID = "
							+ Long.valueOf(dc.getActivityID()) + " ; ");
					// PreparedStatement psDelete;
					try {
						// psDelete = connection.prepareStatement(DeleteSQL
						// .toString());
						int numberItem = ps.executeUpdate(DeleteSQL.toString());

						// System.out.println("numberItem : " + numberItem);
						if (numberItem == 0) {
							ds.setMark("2");
							ds.setContent("fail delete nothing");
							objlist.add(ds);
							closeConnection(connection);
							return;
						} else {
							ds.setMark("1");
							ds.setContent("success");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						closeConnection(connection);
						e.printStackTrace();
						logger.error(log.ExceptionLogTool.getTrace(e));
						ds.setMark("2");
						ds.setContent("throw EXCEPTION");
						objlist.add(ds);
						return;
					}

				} else {
					ds.setMark("2");
					ds.setContent("This User do not have the right");
					objlist.add(ds);
					closeConnection(connection);
					return;
				}
			} else {
				ds.setMark("2");
				ds.setContent("fail no this ActivityID");
				objlist.add(ds);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			ds.setMark("2");
			ds.setContent("fail throw EXCEPTION");
			objlist.add(ds);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(ds);
	}

	/**
	 * 测试通过!
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void deleteActivitySpecific_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		deleteActivitySpecific_C dc = new deleteActivitySpecific_C();
		deleteActivitySpecific_S ds = new deleteActivitySpecific_S();
		StringBuilder DeleteSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分

		dc.setUserID(obj.getString("UserID"));
		dc.setActivityID(obj.getString("ActivityID"));
		dc.setActivitySpecificItemsID(obj.getString("ActivitySpecificItemsID"));
		dc.setUploadTime(obj.getString("UploadTime"));

		// 逻辑部分
		ds.setActivityID(dc.getActivityID());
		ds.setActivitySpecificItemsID(dc.getActivitySpecificItemsID());
		ds.setUploadTime(dc.getUploadTime());
		ds.setUserID(dc.getUserID());

		if (dc.isHaveTOHaveEmpty()) {
			ds.setMark("2");
			ds.setContent("fail , some important element empty");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		String SearchUserIDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
				+ dc.getActivityID() + ";";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {
				System.out.println("ActivityManagerID"
						+ rs.getString("ActivityManagerID"));
				if (rs.getLong("ActivityManagerID") == Long.valueOf(dc
						.getUserID())) {
					// 数据存储部分
					DeleteSQL
							.append("DELETE FROM h_activity_specificitems WHERE ");

					if (!dc.getActivitySpecificItemsID().equals("0")) {
						DeleteSQL.append(" ActivitySpecificItemsID = "
								+ Long.valueOf(dc.getActivitySpecificItemsID())
								+ " ; ");
					} else if (!dc.getActivityID().equals("0")) {
						DeleteSQL.append(" ActivityID = "
								+ Long.valueOf(dc.getActivityID()) + " ; ");
					} else {
						ds.setMark("2");
						ds.setContent("fail neither ActivityID norActivitySpecificItemsID");
						objlist.add(ds);
						closeConnection(connection);
						return;
					}

					// PreparedStatement psDelete;
					try {
						// psDelete = connection.prepareStatement(DeleteSQL
						// .toString());
						int numberItem = ps.executeUpdate(DeleteSQL.toString());
						// System.out.println("numberItem : " + numberItem);
						if (numberItem == 0) {
							ds.setMark("2");
							ds.setContent("fail delete nothing");
							objlist.add(ds);
							closeConnection(connection);
							return;
						} else {
							ds.setMark("1");
							ds.setContent("success");
						}
					} catch (SQLException e) {
						closeConnection(connection);
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error(log.ExceptionLogTool.getTrace(e));
						ds.setMark("2");
						ds.setContent("throw EXCEPTION");
						objlist.add(ds);
						return;
					}

				} else {
					ds.setMark("2");
					ds.setContent("This User do not have the right");
					objlist.add(ds);
					closeConnection(connection);
					return;
				}
			} else {
				ds.setMark("2");
				ds.setContent("fail no this ActivityID");
				objlist.add(ds);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			ds.setMark("2");
			ds.setContent("fail no this ActivityID");
			objlist.add(ds);

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);
		// if(numberItem == 0)
		// {
		// ds.setMark("2");
		// ds.setContent("fail delete nothing");
		// }
		// else
		// {
		// ds.setMark("1");
		// ds.setContent("success");
		// }

		// 传入objlist

		objlist.add(ds);

	}

	/**
	 * 测试通过!
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void deletePersonalInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		deletePersonalInfo_C dc = new deletePersonalInfo_C();
		deletePersonalInfo_S ds = new deletePersonalInfo_S();
		StringBuilder DeleteSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		dc.setUserDiscussID(obj.getString("UserDiscussID"));
		dc.setUserID(obj.getString("UserID"));
		dc.setUploadTime(obj.getString("UploadTime"));

		// 逻辑部分
		ds.setUserDiscussID(dc.getUserDiscussID());
		ds.setUserID(dc.getUserID());
		ds.setUploadTime(dc.getUploadTime());

		if (dc.isHaveTOHaveEmpty()) {
			ds.setMark("2");
			ds.setContent("fail , some important element empty");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		String SearchUserIDSQL = "SELECT * FROM h_user_discusslist WHERE UserDiscussID = "
				+ dc.getUserDiscussID() + ";";
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch = connection.prepareStatement(SearchUserIDSQL);
			ResultSet rs = ps.executeQuery(SearchUserIDSQL);
			if (rs.next()) {

				if ((rs.getLong("UserID") == Long.valueOf(dc.getUserID()))
						|| (rs.getLong("ThisUserID") == Long.valueOf(dc
								.getUserID()))) {
					// 数据存储部分
					DeleteSQL.append("DELETE FROM h_user_discusslist WHERE ");
					DeleteSQL.append(" UserDiscussID = "
							+ Long.valueOf(dc.getUserDiscussID()) + " ; ");

					// PreparedStatement psDelete;
					try {
						// psDelete = connection.prepareStatement(DeleteSQL
						// .toString());
						int numberItem = ps.executeUpdate(DeleteSQL.toString());
						// System.out.println("numberItem : " + numberItem);
						if (numberItem == 0) {
							ds.setMark("2");
							ds.setContent("fail delete nothing");
							objlist.add(ds);
							closeConnection(connection);
							return;
						} else {
							ds.setMark("1");
							ds.setContent("success");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						closeConnection(connection);
						e.printStackTrace();
						logger.error(log.ExceptionLogTool.getTrace(e));
						ds.setMark("2");
						ds.setContent("throw EXCEPTION");
						objlist.add(ds);
						return;
					}

				} else {
					ds.setMark("2");
					ds.setContent("This User do not have the right");
					objlist.add(ds);
					closeConnection(connection);
					return;
				}
			} else {
				ds.setMark("2");
				ds.setContent("fail no this UserDiscussID");
				objlist.add(ds);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			ds.setMark("2");
			ds.setContent("fail no this UserDiscussID");
			objlist.add(ds);

			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(ds);
	}

	/**
	 * 测试通过! RelationID 首要 ActivityID SearchUserID UserID不是检索内容
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void deleteRelation_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		deleteRelation_C dc = new deleteRelation_C();
		deleteRelation_S ds = new deleteRelation_S();
		StringBuilder DeleteSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		dc.setRelationID(obj.getString("RelationID"));
		dc.setActivityID(obj.getString("ActivityID"));
		dc.setSearchUserID(obj.getString("SearchUserID"));
		dc.setUserID(obj.getString("UserID"));
		dc.setUploadTime(obj.getString("UploadTime"));

		// 逻辑部分
		ds.setUserID(dc.getUserID());
		ds.setUploadTime(dc.getUploadTime());

		if (dc.isHaveTOHaveEmpty()) {
			ds.setMark("2");
			ds.setContent("fail , some important element empty");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		DeleteSQL.append("DELETE FROM h_activity_memberlist WHERE ");
		if (!dc.getRelationID().equals("0")) {
			DeleteSQL.append(" RelationID = "
					+ Long.valueOf(dc.getRelationID()) + " ; ");
		} else if (!dc.getActivityID().equals("0")) {
			DeleteSQL.append(" ActivityID = "
					+ Long.valueOf(dc.getActivityID()) + " ; ");
		} else if (!dc.getSearchUserID().equals("0")) {
			DeleteSQL.append(" UserID = " + Long.valueOf(dc.getUserID())
					+ " ; ");
		} else {
			ds.setMark("2");
			ds.setContent("fail no valid value");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}
		// PreparedStatement psDelete, psSearch, psUpdate;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psDelete = connection.prepareStatement(DeleteSQL.toString());
			ResultSet DeleteRS = ps.executeQuery(DeleteSQL.toString());
			if (DeleteRS.next()) {
				String ActivityID = DeleteRS.getString("ActivityID");
				String Status = DeleteRS.getString("Status");
				if (Integer.valueOf(Status) == 1) {
					// 活动参加人员数查询
					String SearchSQL;
					SearchSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
							+ Long.valueOf(ActivityID) + " ;";
					// psSearch = connection.prepareStatement(SearchSQL);
					ResultSet searchRS = ps.executeQuery(SearchSQL);
					if (searchRS.next()) {
						int ActivityMemberNumber = Integer.valueOf(searchRS
								.getString("ActivityMemberNumber"));
						String UploadTime = searchRS.getString("UploadTime");

						ActivityMemberNumber--;

						// 修改参加人员数
						String UpdateSQL;
						UpdateSQL = "UPDATE h_activity_baseinfo SET ActivityMemberNumber = '"
								+ ActivityMemberNumber
								+ "' WHERE ActivityID = "
								+ Long.valueOf(ActivityID) + " ;";
						// psUpdate = connection.prepareStatement(UpdateSQL);
						boolean resume = ps.execute(UpdateSQL);
						if (resume == true) {
							ds.setMark("1");
							ds.setContent("success");
						} else {
							ds.setMark("2");
							ds.setContent("no this Item");
							objlist.add(ds);
							closeConnection(connection);
							return;
						}

						// 同步至百度数据库
						updateBaiduMemberNumber(UploadTime, ActivityID,
								ActivityMemberNumber);
					} else {
						ds.setMark("2");
						ds.setContent("no this Item");
						objlist.add(ds);
						closeConnection(connection);
						return;
					}
				}
			} else {
				ds.setMark("2");
				ds.setContent("no this Item");
				objlist.add(ds);
				closeConnection(connection);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			ds.setMark("2");
			ds.setContent("no this Item");
			objlist.add(ds);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(ds);
	}

	/**
	 * 测试通过! UserID 首要
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void deleteUserInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		deleteUserInfo_C dc = new deleteUserInfo_C();
		deleteUserInfo_S ds = new deleteUserInfo_S();
		StringBuilder DeleteSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		dc.setUserID(obj.getString("UserID"));
		dc.setUploadTime(obj.getString("UploadTime"));

		ds.setUserID(dc.getUserID());
		ds.setUploadTime(dc.getUploadTime());

		if (dc.isHaveTOHaveEmpty()) {
			ds.setMark("2");
			ds.setContent("fail , some important element empty");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		// 逻辑部分

		// 数据存储部分
		DeleteSQL.append("DELETE FROM h_user_baseinfo WHERE ");
		DeleteSQL.append(" UserID = " + Long.valueOf(dc.getUserID()) + " ; ");
		// PreparedStatement psDelete;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psDelete = connection.prepareStatement(DeleteSQL.toString());
			int numberItem = ps.executeUpdate(DeleteSQL.toString());
			if (numberItem == 0) {
				ds.setMark("2");
				ds.setContent("fail no this man ");
				objlist.add(ds);
				closeConnection(connection);
				return;
			} else {
				ds.setMark("1");
				ds.setContent("success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			ds.setMark("2");
			ds.setContent("throw exception");
			objlist.add(ds);
			closeConnection(connection);
			return;
		}

		closeConnection(connection);
		// 传入objlist

		objlist.add(ds);
	}

	/**
	 * 测试通过! 我试了两种关键字检测(ActivityID\ActivityName),其他的还没 ActivityID 首要
	 * 如果(ActivityID == 0)则其他条件不能全为空 返回的list中有转义字符
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void getActivityInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		getActivityInfo_C gc = new getActivityInfo_C();
		getActivityInfo_S gs = new getActivityInfo_S();
		StringBuilder GetSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		gc.setActivityID(obj.getString("ActivityID"));
		gc.setUploadTime(obj.getString("UploadTime"));
		gc.setPageSize(obj.getString("PageSize"));
		gc.setPageIndex(obj.getString("PageIndex"));
		gc.setUserID(obj.getString("UserID"));

		GetSQL.append("SELECT * FROM h_activity_baseinfo ");

		if (gc.getActivityID().equals("0")) {

			GetSQL.append(" WHERE ");

			if (obj.containsKey("ActivityName")) {
				gc.ActivityName = obj.getString("ActivityName");
				GetSQL.append(" ActivityName LIKE " + "'%" + gc.ActivityName
						+ "%' ");
			}
			if (obj.containsKey("ActivityStartTime")
					&& obj.containsKey("ActivityEndTime")) {
				gc.ActivityStartTime = obj.getString("ActivityStartTime");
				gc.ActivityEndTime = obj.getString("ActivityEndTime");
				GetSQL.append(" ActivityStartTime <= "
						+ Long.valueOf(gc.ActivityStartTime)
						+ " AND ActivityEndTime >= "
						+ Long.valueOf(gc.ActivityEndTime) + " ");
			}
			// 这里将查询的字段设为UserID,本身UserID与HelpPhone保持一致
			if (obj.containsKey("HelpPhone")) {
				gc.HelpPhone = obj.getString("HelpPhone");
				// GetSQL.append(" HelpPhone = '" + gc.HelpPhone + "' ");
				GetSQL.append(" UserID = " + gc.HelpPhone + " ");
			}
			if (obj.containsKey("ActivityBelongClass")) {
				gc.ActivityBelongClass = obj.getString("ActivityBelongClass");
				GetSQL.append(" ActivityBelongClass LIKE " + "'%"
						+ gc.ActivityBelongClass + "%' ");
			}
			if (obj.containsKey("ActivityTags")) {
				gc.ActivityTags = obj.getString("ActivityTags");
				GetSQL.append(" ActivityTags LIKE " + "'%" + gc.ActivityTags
						+ "%' ");
			}
			if (obj.containsKey("ActivityOpinion")) {
				gc.ActivityOpinion = obj.getString("ActivityOpinion");
				GetSQL.append(" ActivityOpinion >= "
						+ Double.valueOf(gc.ActivityOpinion) + " ");
			}

			GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
					* Integer.valueOf(gc.getPageSize()) + ","
					+ Integer.valueOf((gc.getPageIndex() + 1))
					* Integer.valueOf(gc.getPageSize()) + " ; ");

		} else {

			GetSQL.append(" WHERE ActivityID = "
					+ Long.valueOf(gc.getActivityID()) + " ; ");
		}

		// 逻辑部分

		gs.setUserID(gc.getUserID());
		gs.setUploadTime(gc.getUploadTime());

		gs.setPageSize(gc.getPageSize());
		gs.setPageIndex(gc.getPageIndex());

		if (gc.isHaveTOHaveEmpty()) {
			gs.setMark("2");
			gs.setContent("fail , some important element empty");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		ArrayList<ActivitySelectData> asdList = new ArrayList<ActivitySelectData>();
		// PreparedStatement psSelectSQL;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelectSQL = connection.prepareStatement(GetSQL.toString());
			ResultSet rs = ps.executeQuery(GetSQL.toString());

			while (rs.next()) {
				ActivitySelectData asd = new ActivitySelectData(rs.getLong(1),
						rs.getLong(2), rs.getLong(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getString(13),
						rs.getString(14), rs.getString(15), rs.getString(16),
						rs.getString(17), rs.getString(18), rs.getString(19));
				asdList.add(asd);
			}
			if (asdList.size() == 0) {
				gs.setMark("2");
				gs.setContent("fail no data");
				objlist.add(gs);
				closeConnection(connection);
				return;
			} else {
				gs.setMark("1");
				gs.setContent("success");
			}
			String ActivityInfoList = g.toJson(asdList);
			gs.setActivityInfoList(ActivityInfoList);
		} catch (SQLException e) {
			closeConnection(connection);
			gs.setMark("2");
			gs.setContent("fail no data");
			objlist.add(gs);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(gs);

	}

	/**
	 * 测试通过! UserDiscussID 首要 UserID ThisUserID
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void getPersonalInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		getPersonalInfo_C gc = new getPersonalInfo_C();
		getPersonalInfo_S gs = new getPersonalInfo_S();
		StringBuilder GetSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		gc.setUserDiscussID(obj.getString("UserDiscussID"));
		gc.setUserID(obj.getString("UserID"));
		gc.setThisUserID(obj.getString("ThisUserID"));
		gc.setUploadTime(obj.getString("UploadTime"));
		gc.setPageSize(obj.getString("PageSize"));
		gc.setPageIndex(obj.getString("PageIndex"));

		GetSQL.append("SELECT * FROM h_user_discusslist ");

		if (!gc.getUserDiscussID().equals("0")) {
			GetSQL.append(" WHERE UserDiscussID = "
					+ Long.valueOf(gc.getUserDiscussID()) + " ");
		} else if (!gc.getUserID().equals("0")) {
			GetSQL.append(" WHERE UserID = " + Long.valueOf(gc.getUserID())
					+ " ");
		} else if (!gc.getThisUserID().equals("0")) {
			GetSQL.append(" WHERE ThisUserID = "
					+ Long.valueOf(gc.getThisUserID()) + " ");
		} else {

		}
		GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
				* Integer.valueOf(gc.getPageSize()) + ","
				+ Integer.valueOf((gc.getPageIndex() + 1))
				* Integer.valueOf(gc.getPageSize()) + " ; ");

		// 逻辑部分

		gs.setUserDiscussID(gc.getUserDiscussID());
		gs.setUserID(gc.getUserID());
		gs.setThisUserID(gc.getThisUserID());
		gs.setUploadTime(gc.getUploadTime());
		gs.setPageSize(gc.getPageSize());
		gs.setPageIndex(gc.getPageIndex());

		if (gc.isHaveTOHaveEmpty()) {
			gs.setMark("2");
			gs.setContent("fail , some important element empty");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		ArrayList<PersonalSelectData> psdList = new ArrayList<PersonalSelectData>();
		// PreparedStatement psSelectSQL;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelectSQL = connection.prepareStatement(GetSQL.toString());
			ResultSet rs = ps.executeQuery(GetSQL.toString());

			while (rs.next()) {
				PersonalSelectData psd = new PersonalSelectData(rs.getLong(1),
						rs.getLong(2), rs.getLong(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8));
				psdList.add(psd);
			}

			if (psdList.size() == 0) {
				gs.setMark("2");
				gs.setContent("fail");
				objlist.add(gs);
				closeConnection(connection);
				return;
			} else {
				gs.setMark("1");
				gs.setContent("success");
			}

			String PersonalInfoList = g.toJson(psdList);
			gs.setPersonalInfoList(PersonalInfoList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			gs.setMark("2");
			gs.setContent("throw exception");
			objlist.add(gs);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(gs);

	}

	/**
	 * 测试通过 (测试内容:UserID\UserName) SearchUserID 首要 单一条件查询,不能同时使用
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void getUserInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		getUserInfo_C gc = new getUserInfo_C();
		getUserInfo_S gs = new getUserInfo_S();
		StringBuilder GetSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		gc.setUserID(obj.getString("UserID"));
		gc.setUploadTime(obj.getString("UploadTime"));
		gc.setPageSize(obj.getString("PageSize"));
		gc.setPageIndex(obj.getString("PageIndex"));

		GetSQL.append("SELECT * FROM h_user_baseinfo ");

		if (!gc.getUserID().equals("0")) {

			GetSQL.append(" WHERE ");

			if (obj.containsKey("SearchUserID")) {
				gc.SearchUserID = obj.getString("SearchUserID");
				GetSQL.append(" UserID = " + gc.SearchUserID + " ");
			}
			if (obj.containsKey("UserName")) {
				gc.UserName = obj.getString("UserName");
				GetSQL.append(" UserName LIKE " + "'%" + gc.UserName + "%' ");
			}
			if (obj.containsKey("UserPhone")) {
				gc.UserPhone = obj.getString("UserPhone");
				GetSQL.append(" UserPhone = '" + gc.UserPhone + "' ");
			}
			if (obj.containsKey("UserQQ")) {
				gc.UserQQ = obj.getString("UserQQ");
				GetSQL.append(" UserQQ = '" + gc.UserQQ + "' ");
			}
			if (obj.containsKey("UserWeiChat")) {
				gc.UserWeiChat = obj.getString("UserWeiChat");
				GetSQL.append(" UserWeiChat = '" + gc.UserWeiChat + "' ");
			}
			if (obj.containsKey("UserTags")) {
				gc.UserTags = obj.getString("UserTags");
				GetSQL.append(" UserTags LIKE " + "'%" + gc.UserTags + "%' ");
			}
			if (obj.containsKey("UserClass")) {
				gc.UserClass = obj.getString("UserClass");
				GetSQL.append(" UserClass LIKE " + "'%" + gc.UserClass + "%' ");
			}
			if (obj.containsKey("UserAge")) {
				gc.UserAge = obj.getString("UserAge");
				String[] minmax = gc.UserAge.split(",");
				int min = Integer.valueOf(minmax[0]);
				int max = Integer.valueOf(minmax[1]);
				GetSQL.append(" UserAge > " + min + " AND " + " UserAge < "
						+ max + " ");
			}
			if (obj.containsKey("UserSex")) {
				gc.UserSex = obj.getString("UserSex");
				GetSQL.append(" UserSex = '" + gc.UserSex + "' ");
			}

			GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
					* Integer.valueOf(gc.getPageSize()) + ","
					+ Integer.valueOf((gc.getPageIndex() + 1))
					* Integer.valueOf(gc.getPageSize()) + " ; ");

		} else {
			gs.setMark("2");
			gs.setContent("fail , userid can't equal 0");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 逻辑部分

		gs.setUserID(gc.getUserID());
		gs.setUploadTime(gc.getUploadTime());
		gs.setPageSize(gc.getPageSize());
		gs.setPageIndex(gc.getPageIndex());

		if (gc.isHaveTOHaveEmpty()) {
			gs.setMark("2");
			gs.setContent("fail , some important element empty");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		ArrayList<UserInfoSelectData> uisdList = new ArrayList<UserInfoSelectData>();
		// PreparedStatement psSelectSQL;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelectSQL = connection.prepareStatement(GetSQL.toString());
			ResultSet rs = ps.executeQuery(GetSQL.toString());

			while (rs.next()) {
				UserInfoSelectData uisd = new UserInfoSelectData(rs.getLong(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getString(13),
						rs.getString(14), rs.getString(15), rs.getString(16),
						rs.getString(17), rs.getString(18), rs.getString(19),
						rs.getString(20));
				uisd.setCode("0");
				uisdList.add(uisd);
			}

			if (uisdList.size() == 0) {
				gs.setMark("2");
				gs.setContent("fail not that user");
				objlist.add(gs);
				closeConnection(connection);
				return;
			} else {
				gs.setMark("1");
				gs.setContent("success");
			}

			String UserInfoList = g.toJson(uisdList);
			gs.setUserInfoList(UserInfoList);
		} catch (SQLException e) {
			closeConnection(connection);
			gs.setMark("2");
			gs.setContent("fail not that user");
			objlist.add(gs);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(gs);
	}

	/**
	 * 测试通过! RelationID 首要 UserID ActivityID
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void getRelation_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		getRelaion_C gc = new getRelaion_C();
		getRelaion_S gs = new getRelaion_S();
		StringBuilder GetSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		/**
		 * 指向查询(1-RelationID;2-ActivityID;3-UserID)
		 */
		int whichPrimarySearch = 0;
		// 解析部分
		gc.setRelationID(obj.getString("RelationID"));
		gc.setActivityID(obj.getString("ActivityID"));
		gc.setSearchUserID(obj.getString("SearchUserID"));
		gc.setUserID(obj.getString("UserID"));
		gc.setUploadTime(obj.getString("UploadTime"));
		gc.setPageSize(obj.getString("PageSize"));
		gc.setPageIndex(obj.getString("PageIndex"));
		gc.setExtraMark(obj.getString("ExtraMark"));

		GetSQL.append("SELECT * FROM h_activity_memberlist ");

		if (gc.getRelationID().equals("0")) {

			GetSQL.append(" WHERE ");

			if (!gc.getActivityID().equals("0")) {
				GetSQL.append(" ActivityID = "
						+ Long.valueOf(gc.getActivityID()) + " ");
				whichPrimarySearch = 2;
			} else if (!gc.getSearchUserID().equals("0")) {
				GetSQL.append(" UserID = " + Long.valueOf(gc.getSearchUserID())
						+ " ");
				whichPrimarySearch = 3;
			} else {
				whichPrimarySearch = -1;
			}

			GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
					* Integer.valueOf(gc.getPageSize()) + ","
					+ Integer.valueOf((gc.getPageIndex() + 1))
					* Integer.valueOf(gc.getPageSize()) + " ; ");

		} else {
			GetSQL.append(" WHERE ");
			GetSQL.append(" RelationID = " + Long.valueOf(gc.getRelationID())
					+ " ");
			GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
					* Integer.valueOf(gc.getPageSize()) + ","
					+ Integer.valueOf((gc.getPageIndex() + 1))
					* Integer.valueOf(gc.getPageSize()) + " ; ");
			whichPrimarySearch = 1;
		}

		// 逻辑部分

		gs.setRelationID(gc.getRelationID());
		gs.setActivityID(gc.getActivityID());
		gs.setUserID(gc.getUserID());
		gs.setUploadTime(gc.getUploadTime());
		gs.setExtraMark(gc.getExtraMark());

		if (gc.isHaveTOHaveEmpty()) {
			gs.setMark("2");
			gs.setContent("fail , some important element empty");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 数据查询部分
		ArrayList<RelationSelectData> rsdList = new ArrayList<RelationSelectData>();
		ArrayList<ActivitySelectData> asdList = new ArrayList<ActivitySelectData>();
		ArrayList<UserInfoSelectData> uisdList = new ArrayList<UserInfoSelectData>();
		ActivitySelectData asd = null;
		UserInfoSelectData uisd = null;

		// PreparedStatement psSelectSQL;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelectSQL = connection.prepareStatement(GetSQL.toString());
			ResultSet rs = ps.executeQuery(GetSQL.toString());

			while (rs.next()) {
				RelationSelectData rsd = new RelationSelectData(rs.getLong(1),
						rs.getLong(2), rs.getLong(3), rs.getString(4),
						rs.getString(5));
				rsdList.add(rsd);

				if (whichPrimarySearch == Integer.valueOf(gc.getExtraMark())) {
					switch (whichPrimarySearch) {
					case 0:
						gs.setMark("2");
						gs.setContent("whichPrimarySearch = 0 ,search what");
						objlist.add(gs);
						closeConnection(connection);
						return;
					case 1: // 基于RelationID查询关联的UserID与Activity
						// PreparedStatement psSelectASDSQL,psSelectUISDSQL;
						String SelectASDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
								+ rsd.getActivityID() + " ; ";
						String SelectUISDSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
								+ rsd.getUserID() + " ; ";
						// psSelectASDSQL = connection
						// .prepareStatement(SelectASDSQL);
						// psSelectUISDSQL = connection
						// .prepareStatement(SelectUISDSQL);
						ResultSet rsasd = ps.executeQuery(SelectASDSQL);
						if (rsasd.next()) {
							asd = new ActivitySelectData(rsasd.getLong(1),
									rsasd.getLong(2), rsasd.getLong(3),
									rsasd.getString(4), rsasd.getString(5),
									rsasd.getString(6), rsasd.getString(7),
									rsasd.getString(8), rsasd.getString(9),
									rsasd.getString(10), rsasd.getString(11),
									rsasd.getString(12), rsasd.getString(13),
									rsasd.getString(14), rsasd.getString(15),
									rsasd.getString(16), rsasd.getString(17),
									rsasd.getString(18), rsasd.getString(19));

						}
						ResultSet rsuisd = ps.executeQuery(SelectUISDSQL);
						if (rsuisd.next()) {
							uisd = new UserInfoSelectData(rsuisd.getLong(1),
									rsuisd.getString(2), rsuisd.getString(3),
									rsuisd.getString(4), rsuisd.getString(5),
									rsuisd.getString(6), rsuisd.getString(7),
									rsuisd.getString(8), rsuisd.getString(9),
									rsuisd.getString(10), rsuisd.getString(11),
									rsuisd.getString(12), rsuisd.getString(13),
									rsuisd.getString(14), rsuisd.getString(15),
									rsuisd.getString(16), rsuisd.getString(17),
									rsuisd.getString(18), rsuisd.getString(19),
									rsuisd.getString(20));

						}

						break;
					case 2: // 基于ActivityID查询关联的UserID中的详细信息
						// PreparedStatement psSelectAllUserID;
						String SearAllActivityUserID = "SELECT * FROM h_user_baseinfo WHERE UserID = "
								+ rsd.getUserID() + " ; ";
						// psSelectAllUserID = connection
						// .prepareStatement(SearAllActivityUserID);
						ResultSet rsalluserid = ps
								.executeQuery(SearAllActivityUserID);
						if (rsalluserid.next()) {
							uisdList.add(new UserInfoSelectData(rsalluserid
									.getLong(1), rsalluserid.getString(2),
									rsalluserid.getString(3), rsalluserid
											.getString(4), rsalluserid
											.getString(5), rsalluserid
											.getString(6), rsalluserid
											.getString(7), rsalluserid
											.getString(8), rsalluserid
											.getString(9), rsalluserid
											.getString(10), rsalluserid
											.getString(11), rsalluserid
											.getString(12), rsalluserid
											.getString(13), rsalluserid
											.getString(14), rsalluserid
											.getString(15), rsalluserid
											.getString(16), rsalluserid
											.getString(17), rsalluserid
											.getString(18), rsalluserid
											.getString(19), rsalluserid
											.getString(20)));
						}
						// gs.UserIDList = g.toJson(uisdList);
						break;
					case 3: // 基于UserID查询关联ActivityID中的详细信息
						String SelectAllActivityIDSQL = "SELECT * FROM h_activity_baseinfo WHERE ActivityID = "
								+ rsd.getActivityID() + " ; ";
						// PreparedStatement psSelectAllActivityID;
						// psSelectAllActivityID = connection
						// .prepareStatement(SelectAllActivityIDSQL);
						ResultSet rsactivityid = ps
								.executeQuery(SelectAllActivityIDSQL);
						if (rsactivityid.next()) {
							asdList.add(new ActivitySelectData(rsactivityid
									.getLong(1), rsactivityid.getLong(2),
									rsactivityid.getLong(3), rsactivityid
											.getString(4), rsactivityid
											.getString(5), rsactivityid
											.getString(6), rsactivityid
											.getString(7), rsactivityid
											.getString(8), rsactivityid
											.getString(9), rsactivityid
											.getString(10), rsactivityid
											.getString(11), rsactivityid
											.getString(12), rsactivityid
											.getString(13), rsactivityid
											.getString(14), rsactivityid
											.getString(15), rsactivityid
											.getString(16), rsactivityid
											.getString(17), rsactivityid
											.getString(18), rsactivityid
											.getString(19)));
						}

						break;
					default:
						gs.setMark("2");
						gs.setContent("whichPrimarySearch = ?");
						objlist.add(gs);
						closeConnection(connection);
						return;
					}
				}

			}

			switch (Integer.valueOf(gc.getExtraMark())) {
			case 1:
				gs.OneActivity = g.toJson(asd);
				gs.OneUser = g.toJson(uisd);
				break;
			case 2:
				gs.UserIDList = g.toJson(uisdList);
				break;
			case 3:
				gs.ActivityList = g.toJson(asdList);
				break;

			}

			if (rsdList.size() == 0) {
				gs.setMark("2");
				gs.setContent("fail no this item");
				objlist.add(gs);
				closeConnection(connection);
				return;
			} else {
				gs.setMark("1");
				gs.setContent("success");
			}

			String RelationList = g.toJson(rsdList);
			gs.setRelationList(RelationList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			gs.setMark("2");
			gs.setContent("fail throw exception");
			objlist.add(gs);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(gs);
	}

	/**
	 * 测试通过! ActivityID 首要
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void getActivityDiscussList_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		getActivityDiscussList_C gc = new getActivityDiscussList_C();
		getActivityDiscussList_S gs = new getActivityDiscussList_S();
		StringBuilder GetSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		gc.setActivityID(obj.getString("ActivityID"));
		gc.setUserID(obj.getString("UserID"));
		gc.setUploadTime(obj.getString("UploadTime"));
		gc.setPageSize(obj.getString("PageSize"));
		gc.setPageIndex(obj.getString("PageIndex"));

		GetSQL.append("SELECT * FROM h_activity_discusslist ");

		GetSQL.append(" WHERE ");

		if (!gc.getActivityID().equals("0")) {
			GetSQL.append(" ActivityID = " + Long.valueOf(gc.getActivityID())
					+ " ");
		} else if (!gc.getUserID().equals("0")) {
			GetSQL.append(" UserID = " + Long.valueOf(gc.getUserID()) + " ");
		}

		GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
				* Integer.valueOf(gc.getPageSize()) + ","
				+ Integer.valueOf((gc.getPageIndex() + 1))
				* Integer.valueOf(gc.getPageSize()) + " ; ");

		// 逻辑部分

		gs.setActivityID(gc.getActivityID());
		gs.setUserID(gc.getUserID());
		gs.setUploadTime(gc.getUploadTime());

		if (gc.isHaveTOHaveEmpty()) {
			gs.setMark("2");
			gs.setContent("fail , some important element empty");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分

		ArrayList<ActivityDiscussSelectData> adsdList = new ArrayList<ActivityDiscussSelectData>();
		// PreparedStatement psSelectSQL;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelectSQL = connection.prepareStatement(GetSQL.toString());
			ResultSet rs = ps.executeQuery(GetSQL.toString());

			while (rs.next()) {
				ActivityDiscussSelectData rsd = new ActivityDiscussSelectData(
						rs.getLong(1), rs.getLong(2), rs.getLong(3),
						rs.getLong(4), rs.getLong(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10));
				adsdList.add(rsd);
			}

			if (adsdList.size() == 0) {
				gs.setMark("1");
				gs.setContent("fail no item");
				objlist.add(gs);
				closeConnection(connection);
				return;
			} else {
				gs.setMark("1");
				gs.setContent("success");
			}

			String ActivityDiscussList = g.toJson(adsdList);
			gs.setActivityDiscussList(ActivityDiscussList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			gs.setMark("2");
			gs.setContent("fail throw exception");
			objlist.add(gs);
			return;

		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(gs);
	}

	/**
	 * 測試通過
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void getActivitySpecificItemList_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		getActivitySpecificItemList_C gc = new getActivitySpecificItemList_C();
		getActivitySpecificItemList_S gs = new getActivitySpecificItemList_S();
		StringBuilder GetSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		gc.setUserID(obj.getString("UserID"));
		gc.setActivityID(obj.getString("ActivityID"));
		gc.setActivitySpecificItemsID(obj.getString("ActivitySpecificItemsID"));
		gc.setUploadTime(obj.getString("UploadTime"));
		gc.setPageSize(obj.getString("PageSize"));
		gc.setPageIndex(obj.getString("PageIndex"));

		GetSQL.append("SELECT * FROM h_activity_discusslist ");

		GetSQL.append(" WHERE ");

		if (!gc.getActivityID().equals("0")) {
			GetSQL.append(" ActivityID = " + gc.getActivityID() + " ");
		} else if (!gc.getActivitySpecificItemsID().equals("0")) {
			GetSQL.append(" ActivitySpecificItemsID = "
					+ gc.getActivitySpecificItemsID() + " ");
		}

		GetSQL.append(" LIMIT " + Integer.valueOf(gc.getPageIndex())
				* Integer.valueOf(gc.getPageSize()) + ","
				+ Integer.valueOf((gc.getPageIndex() + 1))
				* Integer.valueOf(gc.getPageSize()) + " ; ");

		// 逻辑部分

		gs.setUserID(gc.getUserID());
		gs.setActivityID(gc.getActivityID());
		gs.setActivitySpecificItemsID(gc.getActivitySpecificItemsID());
		gs.setUploadTime(gc.getUploadTime());

		if (gc.isHaveTOHaveEmpty()) {
			gs.setMark("2");
			gs.setContent("fail , some important element empty");
			objlist.add(gs);
			closeConnection(connection);
			return;
		}

		// 数据存储部分
		ArrayList<ActivitySpecificItemsSelectData> asisdList = new ArrayList<ActivitySpecificItemsSelectData>();
		// PreparedStatement psSelectSQL;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSelectSQL = connection.prepareStatement(GetSQL.toString());
			ResultSet rs = ps.executeQuery(GetSQL.toString());

			while (rs.next()) {
				ActivitySpecificItemsSelectData rsd = new ActivitySpecificItemsSelectData(
						rs.getLong(1), rs.getLong(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7));
				asisdList.add(rsd);
			}

			if (asisdList.size() == 0) {
				gs.setMark("2");
				gs.setContent("fail no this item");
				objlist.add(gs);
				closeConnection(connection);
				return;
			} else {
				gs.setMark("1");
				gs.setContent("success");
			}

			String ActivitySpecificItemList = g.toJson(asisdList);
			gs.setActivitySpecificItemList(ActivitySpecificItemList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			gs.setMark("2");
			gs.setContent("fail throw exception");
			objlist.add(gs);
			return;
		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(gs);
	}

	/**
	 * 测试通过
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void loginUser_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		loginUser_C lc = new loginUser_C();
		loginUser_S ls = new loginUser_S();
		StringBuilder SearchUserIDSQL = new StringBuilder();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		lc.setUserID(obj.getString("UserID"));
		lc.setUploadTime(obj.getString("UploadTime"));
		lc.setCode(obj.getString("Code"));

		ls.setUserID(lc.getUserID());
		ls.setUploadTime(lc.getUploadTime());

		if (lc.isHaveTOHaveEmpty()) {
			ls.setMark("2");
			ls.setContent("fail , some important element empty");
			objlist.add(ls);
			closeConnection(connection);
			return;
		}

		SearchUserIDSQL.append("SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ Long.valueOf(lc.getUserID()) + " ; ");
		// 逻辑部分

		// 数据存储部分
		// PreparedStatement psSearch;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// psSearch =
			// connection.prepareStatement(SearchUserIDSQL.toString());
			ResultSet rs = ps.executeQuery(SearchUserIDSQL.toString());
			if (rs.next()) {
				if (lc.getCode().equals(rs.getString("Code"))) {
					ls.setMark("1");
					ls.setContent("success");

				} else {
					ls.setMark("2");
					ls.setContent("fail code is not right");
					objlist.add(ls);
					closeConnection(connection);
					return;
				}

			} else {
				ls.setMark("2");
				ls.setContent("fail no this user");
				objlist.add(ls);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			ls.setMark("2");
			ls.setContent("SQLException");
			objlist.add(ls);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;

		}

		closeConnection(connection);

		// 传入objlist

		objlist.add(ls);
	}

	/**
	 * 用户发送评分信息
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void updateActivityOpinion_independent_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		updateActivityOpinion_independent_C uc = new updateActivityOpinion_independent_C();
		updateActivityOpinion_independent_S us = new updateActivityOpinion_independent_S();
		StringBuilder SearchSQL = new StringBuilder();
		StringBuilder UpdateSQL = new StringBuilder();
		UserInfoSelectData uisd;
		ActivitySelectData asd;
		Connection connection;
		connection = DBConnectionManager.getConnection();

		// 解析部分
		uc.setUserID(obj.getString("UserID"));
		uc.setActivityID(obj.getString("ActivityID"));
		uc.setOpinion(obj.getString("Opinion"));
		uc.setUploadTime(obj.getString("UploadTime"));

		// 逻辑部分
		us.setUserID(uc.getUserID());
		us.setActivityID(uc.getActivityID());
		us.setUploadTime(uc.getUploadTime());

		if (uc.isHaveTOHaveEmpty()) {
			us.setMark("2");
			us.setContent("fail , some important element empty");
			us.setGotGrade("0");
			us.setTotalFullGrade("0");

			objlist.add(us);
			closeConnection(connection);
			return;
		}

		if (Integer.valueOf(uc.getOpinion()) > 10) {
			us.setMark("2");
			us.setContent("error,opinion over limit");
			us.setGotGrade("0");
			us.setTotalFullGrade("0");

			objlist.add(us);
			closeConnection(connection);
			return;
		}

		String SearchUserInfoSQL = "SELECT * FROM h_user_baseinfo WHERE UserID = "
				+ uc.getUserID() + " ; ";
		// PreparedStatement psSearchUser;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// psSearchUser = connection.prepareStatement(SearchUserInfoSQL);
			ResultSet rsuser = ps.executeQuery(SearchUserInfoSQL);
			if (rsuser.next()) {
				uisd = new UserInfoSelectData(rsuser.getLong(1),
						rsuser.getString(2), rsuser.getString(3),
						rsuser.getString(4), rsuser.getString(5),
						rsuser.getString(6), rsuser.getString(7),
						rsuser.getString(8), rsuser.getString(9),
						rsuser.getString(10), rsuser.getString(11),
						rsuser.getString(12), rsuser.getString(13),
						rsuser.getString(14), rsuser.getString(15),
						rsuser.getString(16), rsuser.getString(17),
						rsuser.getString(18), rsuser.getString(19),
						rsuser.getString(20));
			} else {
				us.setMark("2");
				us.setContent("error,no this user");
				us.setGotGrade("0");
				us.setTotalFullGrade("0");

				objlist.add(us);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			us.setMark("2");
			us.setContent("error,opinion over limit");
			us.setGotGrade("0");
			us.setTotalFullGrade("0");

			objlist.add(us);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		// 数据查询部分
		SearchSQL
				.append("SELECT * FROM h_activity_baseinfo WHERE ActivityID = ");
		SearchSQL.append(Long.valueOf(uc.getActivityID()) + " ; ");
		// PreparedStatement psSearch;

		long managerID = 0;
		try {
			// psSearch = connection.prepareStatement(SearchSQL.toString());
			ResultSet rs = ps.executeQuery(SearchSQL.toString());
			if (rs.next()) {
				asd = new ActivitySelectData(rs.getLong(1), rs.getLong(2),
						rs.getLong(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17),
						rs.getString(18), rs.getString(19));
				String gradeAndTotal = rs.getString("ActivityOpinion");
				managerID = rs.getLong("ActivityManagerID");
				String[] gradeTotal = gradeAndTotal.split(",");
				int GotGrade = Integer.valueOf(gradeTotal[0]);
				int TotalFullGrade = Integer.valueOf(gradeTotal[1]); // 人数
				GotGrade += Integer.valueOf(uc.getOpinion());
				TotalFullGrade += 1;
				gradeAndTotal = GotGrade + "," + TotalFullGrade;

				// 本地数据库数据更新部分
				UpdateSQL
						.append("UPDATE h_activity_baseinfo SET ActivityOpinion = '"
								+ gradeAndTotal
								+ "' WHERE ActivityID = "
								+ Long.valueOf(uc.getActivityID()) + ";");

				// PreparedStatement psUpdate;

				// psUpdate = connection.prepareStatement(UpdateSQL.toString());
				int UpdateRS = ps.executeUpdate(UpdateSQL.toString());
				if (UpdateRS > 0) {
					us.setMark("1");
					us.setContent("success");
					us.setGotGrade(String.valueOf(GotGrade));
					us.setTotalFullGrade(String.valueOf(TotalFullGrade));

					// 推送消息
					SEND_UserOpinion uo = new SEND_UserOpinion();
					uo.setUaoic(uc);
					uo.setUaois(us);
					uo.setUisd(uisd);
					uo.setAsd(asd);
					InputInfoPushDB(String.valueOf(managerID), uo, ps);
				} else {
					us.setMark("2");
					us.setContent("fail , no this fuck");
					us.setGotGrade("0");
					us.setTotalFullGrade("0");
					objlist.add(us);
					closeConnection(connection);
					return;
				}

				// 百度数据库更新
				updatePoi updatepoi = new updatePoi();
				updatepoi.setCoord_type(protocolwithbaidustore.BAIDULL);
				updatepoi.setGeotable_id(String
						.valueOf(protocolwithbaidustore.baidutableactivityPOI));
				// tags 在上文已设置
				Map<String, Object> columnkey = new HashMap<String, Object>();
				columnkey.put("activityopinion", gradeAndTotal);
				columnkey.put("univeralindex", uc.getActivityID());

				updatepoi.setColumnkey(columnkey);

				maintest.server_version_2.handlePool
						.execute(new handleBaiduCloudDataThread(
								Integer.valueOf(protocolwithbaidustore.baidutableudpateactivityPOI),
								updatepoi, 0, 0));

			} else {
				us.setMark("2");
				us.setContent("fail , no this fuck");
				us.setGotGrade("0");
				us.setTotalFullGrade("0");
				objlist.add(us);
				closeConnection(connection);
				return;
			}

		} catch (SQLException e) {
			closeConnection(connection);
			us.setMark("2");
			us.setContent("fail no this activity");
			objlist.add(us);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;

		}

		closeConnection(connection);

		// 传入objlist
		objlist.add(us);
	}

	/**
	 * 核查用户版本是否需要更新
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void CheckVersionAndOtherInfo_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		CheckVersionAndOtherInfo_C cvaoi = new CheckVersionAndOtherInfo_C();

		cvaoi.setUserID(obj.getString("UserID"));
		cvaoi.setUploadTime(obj.getString("UploadTime"));
		cvaoi.setVersion(obj.getString("Version"));
		cvaoi.setMacAddress(obj.getString("MacAddress"));
		cvaoi.setUUID(obj.getString("UUID"));

		CheckVersionAndOtherInfo_S vu = new CheckVersionAndOtherInfo_S();
		vu.setUserID(cvaoi.getUserID());
		vu.setUploadTime(cvaoi.getUploadTime());
		vu.setVersion(String.valueOf(Config.VersionNow));
		vu.setUpdateTime(Config.UpdateTime);
		vu.setURL(Config.VersionNowURL);
		vu.setText(Config.Text);

		if (cvaoi.isHaveTOHaveEmpty()) {
			vu.setMark("2");
			vu.setContent("fail , some important element empty");
			objlist.add(vu);
			return;
		}

		if (Config.VersionNow > Integer.valueOf(cvaoi.getVersion())) {

			vu.setMark("1");
			vu.setContent("must update!");
		} else {
			vu.setMark("2");
			vu.setContent("use new version!");
		}
		objlist.add(vu);

	}

	/**
	 * 统计事件
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void EventStatistics_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		EventStatistics es = new EventStatistics();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		es.setUserID(obj.getString("UserID"));
		es.setUploadTime(obj.getString("UploadTime"));
		es.setComponent(obj.getString("Component"));
		es.setEvent(obj.getString("Event"));
		es.setContent(obj.getString("Content"));
		es.setEventMark(obj.getString("EventMark"));

		StringBuilder InsertSQL = new StringBuilder();
		InsertSQL.append("INSERT INTO h_UserEvent ");
		InsertSQL
				.append(" ( UserID , UploadTime , Component , Event , Content , EventMark ) ");
		InsertSQL.append("VALUE ( ");
		InsertSQL.append(" " + es.getUserID() + " ");
		InsertSQL.append(", '" + es.getUploadTime() + "' ");
		InsertSQL.append(", '" + es.getComponent() + "' ");
		InsertSQL.append(", '" + es.getEvent() + "' ");
		InsertSQL.append(", '" + es.getContent() + "' ");
		InsertSQL.append(", '" + es.getEventMark() + "' ) ;");

		// PreparedStatement psInsert;
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// System.out.println("InsertSQL" + InsertSQL.toString());
			// psInsert = connection.prepareStatement(InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString());

			if (numberItem != 0) {
			} else {
				System.out.println("Insert error");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeConnection(connection);
			e.printStackTrace();
			logger.error(log.ExceptionLogTool.getTrace(e));
		}

		closeConnection(connection);

	}

	public static void userPushInfo_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		userPushInfo_C uc = new userPushInfo_C();
		userPushInfo_S us = new userPushInfo_S();
		Connection connection;
		connection = DBConnectionManager.getConnection();

		uc.setMbi(obj.getString("mbi"));
		uc.setThisUserID(obj.getString("ThisUserID"));
		uc.setUploadTime(obj.getString("UploadTime"));
		uc.setUserID(obj.getString("UserID"));

		us.setUploadTime(uc.getUploadTime());
		us.setUserID(uc.getUserID());

		if (uc.isHaveTOHaveEmpty()) {
			us.setMark("2");
			us.setContent("fail , some important element empty");

			objlist.add(us);
			closeConnection(connection);
			return;
		}
		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		InputInfoPushDB(uc.getThisUserID(), uc.getMbi(), ps);

		closeConnection(connection);

		us.setMark("1");
		us.setContent("success");

		objlist.add(us);

	}

	/**
	 * 有错误~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 前提是每个萝卜坑中的文件都要存在
	 * 
	 * @param obj
	 * @param objlist
	 * @param receivesocketaddress
	 */
	public static void changFileNameOrder_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		changFileNameOrder_C cc = new changFileNameOrder_C();
		changFileNameOrder_S cs = new changFileNameOrder_S();
		cc.setUserID(obj.getString("UserID"));
		cc.setUploadTime(obj.getString("UploadTime"));
		cc.setPhotoNumber(obj.getString("PhotoNumber"));
		cs.setUserID(cc.getUserID());
		cs.setUploadTime(cc.getUploadTime());

		File[] photoFile = new File[8];
		File[] photoFile_Small = new File[8];
		if (cc.isHaveTOHaveEmpty()) {
			cs.setMark("2");
			cs.setContent("fail , some important element empty");
			objlist.add(cs);
			return;
		}
		cc.setRadishPit(obj.getString("RadishPit1") + ".png",
				obj.getString("RadishPit2") + ".png",
				obj.getString("RadishPit3") + ".png",
				obj.getString("RadishPit4") + ".png",
				obj.getString("RadishPit5") + ".png",
				obj.getString("RadishPit6") + ".png",
				obj.getString("RadishPit7") + ".png",
				obj.getString("RadishPit8") + ".png");

		List<String> fileNameList = new ArrayList<String>(8);
		fileNameList.add(cc.getRadishPit1());
		fileNameList.add(cc.getRadishPit2());
		fileNameList.add(cc.getRadishPit3());
		fileNameList.add(cc.getRadishPit4());
		fileNameList.add(cc.getRadishPit5());
		fileNameList.add(cc.getRadishPit6());
		fileNameList.add(cc.getRadishPit7());
		fileNameList.add(cc.getRadishPit8());

		// 初始化文件名
		for (int fence = 0; fence < Integer.valueOf(cc.getPhotoNumber()); fence++) {
			photoFile[fence] = new File(photofilepath.tupianqiang
					+ fileNameList.get(fence));
			photoFile_Small[fence] = new File(photofilepath.tupianqiang_small
					+ fileNameList.get(fence));
			File ReplaceName = new File(photofilepath.tupianqiang
					+ fileNameList.get(fence) + "_C");
			if (!photoFile[fence].renameTo(ReplaceName)) {
				// 失败
			} else {
				photoFile[fence] = ReplaceName;
			}
			if (!photoFile_Small[fence].renameTo(ReplaceName)) {
				// 失败
			} else {
				photoFile_Small[fence] = ReplaceName;
			}
		}

		if (!cc.getRadishPit1().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit1());
			if (!photoFile[0].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[0].renameTo(changName);
			photoFile_Small[0].renameTo(changName);
		}
		if (!cc.getRadishPit2().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit2());
			if (!photoFile[1].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[1].renameTo(changName);
			photoFile_Small[1].renameTo(changName);
		}
		if (!cc.getRadishPit3().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit3());
			if (!photoFile[2].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[2].renameTo(changName);
			photoFile_Small[2].renameTo(changName);
		}
		if (!cc.getRadishPit4().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit4());
			if (!photoFile[3].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[3].renameTo(changName);
			photoFile_Small[3].renameTo(changName);
		}
		if (!cc.getRadishPit5().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit5());
			if (!photoFile[4].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[4].renameTo(changName);
			photoFile_Small[4].renameTo(changName);
		}
		if (!cc.getRadishPit6().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit6());
			if (!photoFile[5].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[5].renameTo(changName);
			photoFile_Small[5].renameTo(changName);
		}
		if (!cc.getRadishPit7().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit7());
			if (!photoFile[6].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[6].renameTo(changName);
			photoFile_Small[6].renameTo(changName);
		}
		if (!cc.getRadishPit8().equals("0" + ".png")) {
			File changName = new File(photofilepath.tupianqiang
					+ cc.getRadishPit8());
			if (!photoFile[7].exists()) {
				cs.setMark("2");
				cs.setContent("fail , file not exist");
				objlist.add(cs);
				return;
			}
			photoFile[7].renameTo(changName);
			photoFile_Small[7].renameTo(changName);
		}

		System.out.println("Photo File Change Name Successful");
		cs.setMark("1");
		cs.setContent("success");
		objlist.add(cs);

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
