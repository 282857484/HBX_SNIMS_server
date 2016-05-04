package database;

import protocol.pushservice.h_protocol_pusher;

/**
 * 所发送信息项
 * @author Administrator
 *
 */
public class PushMessageSelectData {

	private int p = h_protocol_pusher.PushMessageSelectData;
	private long PushID = 0;
	private long UserID = 0;
	private String PushMessage = "0"; // 由SEND_信息组成
	public PushMessageSelectData(Long pushID, Long userID,
			String pushMessage) {
		PushID = pushID;
		UserID = userID;
		PushMessage = pushMessage;
	}
	public int getP(){
		return p;
	}
	
	public long getPushID() {
		return PushID;
	}
	public void setPushID(long pushID) {
		PushID = pushID;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public String getPushMessage() {
		return PushMessage;
	}
	public void setPushMessage(String pushMessage) {
		PushMessage = pushMessage;
	}


	
	
}
