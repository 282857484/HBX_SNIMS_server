package protocol.infoclass;
public class addActivitySpecificItem_S extends HaveToHave{

	private int p = h_protocolfromserver1.addActivitySpecificItem_S;
	private String ActivityID = "0"; // 活动ID
	private String ActivitySpecificItemsID = "0"; // 活动项目标签
	private String UploadTime = "0"; // 上传时间
	private String UserID = "0"; // 必须 和对用户身份
	/**
	 * 1- 成功
	 * 2- 失败
	 */
	private String Mark = "0"; // 返回标志
	private String Content  = "0"; // 文字反馈
	
	public String getActivityID() {
		return ActivityID;
	}
	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}
	public String getActivitySpecificItemsID() {
		return ActivitySpecificItemsID;
	}
	public void setActivitySpecificItemsID(String activitySpecificItemsID) {
		ActivitySpecificItemsID = activitySpecificItemsID;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
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
		return "addActivitySpecificItem_S [p=" + p + ", ActivityID="
				+ ActivityID + ", ActivitySpecificItemsID="
				+ ActivitySpecificItemsID + ", UploadTime=" + UploadTime
				+ ", UserID=" + UserID + ", mark=" + Mark + ", content="
				+ Content + "]";
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
	
	
}
