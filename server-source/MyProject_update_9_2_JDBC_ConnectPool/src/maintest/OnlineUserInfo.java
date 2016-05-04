package maintest;

import java.net.SocketAddress;

public class OnlineUserInfo {

	String LastLoginTimeStamp;
	SocketAddress UserSocketAddress;
	// other info
	public OnlineUserInfo(String lastLoginTimeStamp,
			SocketAddress userSocketAddress) {
		super();
		LastLoginTimeStamp = lastLoginTimeStamp;
		UserSocketAddress = userSocketAddress;
	}
	
}