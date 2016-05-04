package protocol.infoclass;

public class userPushInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.userPushInfo_C;
	private String UploadTime = "0";
	private String UserID = "0";
	private String ThisUserID = "0";
	private String mbi = "0" ;
	public int getP() {
		return p;
	}

	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getThisUserID() {
		return ThisUserID;
	}
	public void setThisUserID(String thisUserID) {
		ThisUserID = thisUserID;
	}
	public String getMbi() {
		return mbi;
	}
	public void setMbi(String mbi) {
		this.mbi = mbi;
	}
	@Override
	public String toString() {
		return "userPushInfo_C [p=" + p + ", UploadTime=" + UploadTime
				+ ", UserID=" + UserID + ", ThisUserID=" + ThisUserID
				+ ", mbi=" + mbi + "]";
	}
	
	
	
}
