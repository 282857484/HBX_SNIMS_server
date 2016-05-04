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

import protocol.infoclass.addCarLocation_C;
import protocol.infoclass.addCarLocation_S;
import protocol.infoclass.addCar_C;
import protocol.infoclass.addCar_S;
import protocol.infoclass.addMaster_C;
import protocol.infoclass.addMaster_S;
import protocol.infoclass.protocolwithbaidustore;

import com.google.gson.Gson;

import datasouce.DBConnectionManager;

/**
 * 不要修改UpLoadTime!!!!!!
 * 
 * @author Administrator
 * 
 */
public class H_Founction {

	static Logger logger = Logger.getLogger(H_Founction.class.getName());

	static Gson g = handle.g;

	public static void addMaster_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addMaster_C ac = new addMaster_C(obj.getString("uploadtime"),
				obj.getString("mastername"), obj.getString("masterlevel"),
				obj.getString("masterage"),
				obj.getString("masteridentification"));
		addMaster_S as = new addMaster_S();
		as.setUploadtime(ac.getUploadtime());

		Connection connection;
		connection = DBConnectionManager.getConnection();

		Statement ps = null;
		try {
			ps = connection.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 确保用户身份唯一性
		String SearchSQL = "";

		// 插入用户
		StringBuilder InsertSQL = new StringBuilder();
		InsertSQL.append("INSERT INTO carmasterinfo ");
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		keyPart.append(" ( ");
		keyPart.append(" uploadtime , mastername , masterlevel , masterage , masteridentification ");
		keyPart.append(" ) ");
		valuePart.append(" VALUES ( ");
		valuePart.append(ac.getUploadtime() + " , ");
		valuePart.append(" '" + ac.getMastername() + "' , ");
		valuePart.append(" '" + ac.getMasterage() + "' , ");
		valuePart.append(" '" + ac.getMasterage() + "' , ");
		valuePart.append(" '" + ac.getMasteridentification() + "'  ");
		valuePart.append(" ) ;");

		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);
		long newMasterID = 0;
		try {
			System.out.println("InsertSQL" + InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet result = ps.getGeneratedKeys();

			if (numberItem != 0) {
				if (result.next()) {
					as.setMark("1");
					as.setContent("success");
					newMasterID = result.getInt(1);
					as.setCarmasterid(String.valueOf(newMasterID));
				}
			} else {
				as.setMark("2");
				as.setContent("fail ,add no one");
				objlist.add(as);
				closeConnection(connection, ps);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			as.setMark("2");
			as.setContent("fail ,add activity SQLException");
			objlist.add(as);
			e.printStackTrace();
			closeConnection(connection, ps);
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection, ps);

		objlist.add(as);

	}

	public static void addCar_C_Deal(JSONObject obj, ArrayList<Object> objlist,
			SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stub
		addCar_C ac = new addCar_C(obj.getString("carmasterid"),
				obj.getString("uploadtime"), obj.getString("cartype"),
				obj.getString("cardevice"), obj.getString("carprice"));
		addCar_S as = new addCar_S();
		as.setCarmasterid(ac.getCarmasterid());
		as.setUploadtime(ac.getUploadtime());

		Connection connection;
		connection = DBConnectionManager.getConnection();

		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 确保car身份唯一性
		String SearchSQL = "";

		// 插入用户
		StringBuilder InsertSQL = new StringBuilder();
		InsertSQL.append("INSERT INTO carinfo ");
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		keyPart.append(" ( ");
		keyPart.append(" carmasterid , uploadtime , cartype , cardevice , carprice ");
		keyPart.append(" ) ");
		valuePart.append(" VALUES ( ");
		valuePart.append(ac.getCarmasterid() + " , ");
		valuePart.append(" " + ac.getUploadtime() + " , ");
		valuePart.append(" '" + ac.getCartype() + "' , ");
		valuePart.append(" '" + ac.getCardevice() + "' , ");
		valuePart.append(" '" + ac.getCarprice() + "'  ");
		valuePart.append(" ) ;");

		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);
		long newCarID = 0;
		try {
			System.out.println("InsertSQL: " + InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet result = ps.getGeneratedKeys();

			if (numberItem != 0) {
				if (result.next()) {
					as.setMark("1");
					as.setContent("success");
					newCarID = result.getInt(1);
					as.setCarid(String.valueOf(newCarID));
				}
			} else {
				as.setMark("2");
				as.setContent("fail ,add no one");
				objlist.add(as);
				closeConnection(connection, ps);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			as.setMark("2");
			as.setContent("fail ,add car SQLException");
			objlist.add(as);
			e.printStackTrace();
			closeConnection(connection, ps);
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		closeConnection(connection, ps);

		objlist.add(as);

	}

	public static void addCarLocation_C_Deal(JSONObject obj,
			ArrayList<Object> objlist, SocketAddress receivesocketaddress) {
		// TODO Auto-generated method stubString carid, String carmasterid,
		// String uploadtime, String latitude, String logitude, String title,
		// String tags
		addCarLocation_C ac = new addCarLocation_C(obj.getString("carid"),
				obj.getString("carmasterid"), obj.getString("uploadtime"),
				obj.getString("latitude"), obj.getString("logitude"),
				obj.getString("title"), obj.getString("tags"));
		addCarLocation_S as = new addCarLocation_S();
		as.setCarid(ac.getCarid());
		as.setCarmasterid(ac.getCarmasterid());
		as.setUploadtime(ac.getUploadtime());

		Connection connection;
		connection = DBConnectionManager.getConnection();

		Statement ps = null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 确保car身份唯一性
		String SearchSQL = "";

		// 插入用户
		StringBuilder InsertSQL = new StringBuilder();
		InsertSQL.append("INSERT INTO carlocation ");
		StringBuilder keyPart = new StringBuilder();
		StringBuilder valuePart = new StringBuilder();
		keyPart.append(" ( ");
		keyPart.append(" carid , carmasterid , uploadtime , latitude , logitude , title , tags ");
		keyPart.append(" ) ");
		valuePart.append(" VALUES ( ");
		valuePart.append(ac.getCarid() + " , ");
		valuePart.append(" " + ac.getCarmasterid() + " , ");
		valuePart.append(" " + ac.getUploadtime() + " , ");
		valuePart.append(" '" + ac.getLatitude() + "' , ");
		valuePart.append(" '" + ac.getLogitude() + "' , ");
		valuePart.append(" '" + ac.getTitle() + "' , ");
		valuePart.append(" '" + ac.getTags() + "'  ");
		valuePart.append(" ) ;");

		InsertSQL.append(keyPart);
		InsertSQL.append(valuePart);
		long newCarLocID = 0;
		try {
			System.out.println("InsertSQL" + InsertSQL.toString());
			int numberItem = ps.executeUpdate(InsertSQL.toString(),
					Statement.RETURN_GENERATED_KEYS);
			ResultSet result = ps.getGeneratedKeys();

			if (numberItem != 0) {
				if (result.next()) {
					as.setMark("1");
					as.setContent("success");
					newCarLocID = result.getInt(1);
					as.setCarlocid(String.valueOf(newCarLocID));
				}
			} else {
				as.setMark("2");
				as.setContent("fail ,add no one");
				objlist.add(as);
				closeConnection(connection, ps);
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			as.setMark("2");
			as.setContent("fail ,add car SQLException");
			objlist.add(as);
			e.printStackTrace();
			closeConnection(connection, ps);
			logger.error(log.ExceptionLogTool.getTrace(e));
			return;
		}

		creatPoi creatpoi = new creatPoi();
		// 存入百度地理数据库SAVE_BAIDU_DB

		creatpoi.setCoord_type(protocolwithbaidustore.BAIDULL);
		creatpoi.setGeotable_id(String
				.valueOf(protocolwithbaidustore.CreateCarLocation));
		creatpoi.setLatitude(Double.valueOf(ac.getLatitude()));
		creatpoi.setLongitude(Double.valueOf(ac.getLogitude()));
		creatpoi.setTitle(ac.getTitle());
		creatpoi.setTags(ac.getTags());
		// creatpoi.setAddress("...");
		Map<String, Object> columnkey = new HashMap<String, Object>();
		columnkey.put("privateid", ac.getCarmasterid()); // 用户ID
		columnkey.put("locid", as.getCarlocid()); // 独立的
		columnkey.put("carid", ac.getCarid()); // 车的ID
		
		
		columnkey.put("uploadingtime", ac.getUploadtime());
		creatpoi.setColumnkey(columnkey);

		maintest.server_version_2.handlePool
				.execute(new handleBaiduCloudDataThread(Integer
						.valueOf(creatpoi.getGeotable_id()), creatpoi, 0, 0));

		closeConnection(connection, ps);

		objlist.add(as);

	}

	private static void closeConnection(Connection connection, Statement ps) {
		try {
			// if(!ps.isClosed()) {
			// ps.close();
			// }
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
