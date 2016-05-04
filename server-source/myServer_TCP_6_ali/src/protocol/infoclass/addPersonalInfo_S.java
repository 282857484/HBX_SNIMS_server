package protocol.infoclass;


public class addPersonalInfo_S extends HaveToHave{

	private int p = h_protocolfromserver1.addPersonalInfo_S;
	
	private String ThisUserID = "0"; // 留言用户ID
	private String UploadTime = "0"; // 上传时间
	private String UserID = "0";
	/**
	 * 1.成功
	 * 2.失败
	 */
	private String Mark = "0";
	private String Content = "0";
	
	
	public String getThisUserID() {
		return ThisUserID;
	}


	public void setThisUserID(String thisUserID) {
		ThisUserID = thisUserID;
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


	public String getUserID() {
		return UserID;
	}


	public void setUserID(String userID) {
		UserID = userID;
	}


	@Override
	public String toString() {
		return "addPersonalInfo_S [p=" + p + ", ThisUserID=" + ThisUserID
				+ ", UploadTime=" + UploadTime + ", UserID=" + UserID
				+ ", Mark=" + Mark + ", Content=" + Content + "]";
	}
	
	
}
