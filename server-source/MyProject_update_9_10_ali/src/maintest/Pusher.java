package maintest;

import java.net.SocketAddress;
import java.util.List;

import database.PushMessageSelectData;

/**
 * 打包发送信息
 * @author Administrator
 *
 */
public class Pusher {

	public String TimeStamp;
	public SocketAddress UserSocketAddress;
	public List<PushMessageSelectData> PushMessageList; // save send class
}
