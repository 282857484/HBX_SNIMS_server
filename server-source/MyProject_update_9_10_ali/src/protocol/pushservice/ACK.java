package protocol.pushservice;

public class ACK {

	private int p = h_protocol_pusher.ACK;
	private String UserID;
//	private String UploadTime;
	private String PushID;

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getPushID() {
		return PushID;
	}

	public void setPushID(String pushID) {
		PushID = pushID;
	}

//	public String getUploadTime() {
//		return UploadTime;
//	}
//
//	public void setUploadTime(String uploadTime) {
//		UploadTime = uploadTime;
//	}



}
