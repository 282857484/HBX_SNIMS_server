package protocol.infoclass;

public class addActivity_S extends HaveToHave{

	private int p = h_protocolfromserver1.addActivity_S;
	private String UserID = "0";
	private String ActivityID;
	private String UploadTime = "0";
	/**
	 * 返回标记 1.成功 2.失败
	 */
	private String Mark;
	/**
	 * 返回说明
	 */
	private String Content;

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

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}

	@Override
	public String toString() {
		return "addActivity_S [p=" + p + ", UserID=" + UserID + ", ActivityID="
				+ ActivityID + ", UploadTime=" + UploadTime + ", Mark=" + Mark
				+ ", Content=" + Content + "]";
	}

	
}
