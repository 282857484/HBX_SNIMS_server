package protocol.infoclass;

public class userPushInfo_S extends HaveToHave{

	private int p = h_protocolfromserver1.userPushInfo_S;
	private String UploadTime = "0";
	private String UserID = "0";
	private String Mark = "0";
	private String Content = "0";
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
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getP() {
		return p;
	}
	@Override
	public String toString() {
		return "userPushInfo_S [p=" + p + ", UploadTime=" + UploadTime
				+ ", UserID=" + UserID + ", Mark=" + Mark + ", Content="
				+ Content + "]";
	}
	
	
}
