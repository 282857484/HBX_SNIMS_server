package protocol.infoclass;

public class deleteActivityInfo_S extends HaveToHave{

	private int p = h_protocolfromserver1.deleteActivityInfo_S;
	
	private String UserID = "0";
	private String ActivityID = "0"; // 必须   被删活动ID
//	private String BuildActivityUserID; // 必须   
//	private String ActivityManagerID; // 必须   
	private String UploadTime = "0"; // 必须   
	private String Mark = "0";
	private String Content = "0";
	public String getActivityID() {
		return ActivityID;
	}
	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}
//	public String getBuildActivityUserID() {
//		return BuildActivityUserID;
//	}
//	public void setBuildActivityUserID(String buildActivityUserID) {
//		BuildActivityUserID = buildActivityUserID;
//	}
//	public String getActivityManagerID() {
//		return ActivityManagerID;
//	}
//	public void setActivityManagerID(String activityManagerID) {
//		ActivityManagerID = activityManagerID;
//	}
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
		return "deleteActivityInfo_S [p=" + p + ", UserID=" + UserID
				+ ", ActivityID=" + ActivityID + ", UploadTime=" + UploadTime
				+ ", Mark=" + Mark + ", Content=" + Content + "]";
	}
	
	
}
