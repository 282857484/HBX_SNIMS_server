//package database;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import org.apache.log4j.Logger;
//
//
//public class DatabaseJdbc {
//
//	private static Connection conn;
//	static Logger logger = Logger.getLogger(DatabaseJdbc.class.getName());
//	
//
//	/**
//	 * @param args
//	 */
//
//
//	public static Connection coonDb(String dbURL) {
//		
//		logger.setLevel(log.Log4jLevel.ALL);
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			if(dbURL == null)
//			{
//				// 本机数据库禁止远程 所以只能0.0.0.0或127.0.0.1
//				dbURL = "jdbc:mysql://127.0.0.1:3306/myserverdb";
//			}
//			conn = DriverManager.getConnection(dbURL, "root", "hbhb123");
////			conn = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/myserverdb?user=root&password=hbhb123&useUnicode=true&characterEncoding=utf-8");
////			System.out.println("数据库连接成功");
//			return conn;
//		} catch (Exception e) {
//			System.out.println("数据库连接失败!!!请重新连接");
//			e.printStackTrace();
//			logger.error(log.ExceptionLogTool.getTrace(e));
//		}
//		return null;
//
//	}
//
//
//
//}