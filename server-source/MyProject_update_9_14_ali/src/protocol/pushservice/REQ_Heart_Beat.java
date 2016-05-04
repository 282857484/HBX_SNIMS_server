package protocol.pushservice;

public class REQ_Heart_Beat {

	private int p = h_protocol_pusher.REQ_Heart_Beat;
	private String UserID;
	private String UploadTime;
	private String Code; //

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public int getP() {
		return p;
	}

}
