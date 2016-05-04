package protocol.infoclass;

public class deleteUserInfo_S extends HaveToHave{

	private int p = h_protocolfromserver1.deleteUserInfo_S;
	
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0"; // 用户名称
	private String Mark = "0";
	private String Content = "0";
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
		return "deleteUserInfo_S [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Mark=" + Mark
				+ ", Content=" + Content + "]";
	}
	
	
	
}
