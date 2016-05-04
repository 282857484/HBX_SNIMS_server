//package handlemethod;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import datasouce.DBConnectionManager;
//
//public class txt {
//
//	Connection connection;
//	connection = DBConnectionManager.getConnection();
//	
//	Statement ps = null;
//	try {
//		ps = connection.createStatement();
//	} catch (SQLException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	
////	ps = connection.createStatement();
//	
//	 finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	 
//		try {
//			if (!connection.isClosed()) {
//				connection.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}
