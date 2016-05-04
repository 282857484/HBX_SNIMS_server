package protocol.infoclass;

import tool.FormatTime;

public class deleteActivityInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.deleteActivityInfo_C;
	
	private String UserID = "0" ; // 必须   此用户ID
	
	private String ActivityID = "0"; // 必须   被删活动ID
//	private String BuildActivityUserID; // 必须   
//	private String ActivityManagerID; // 必须   
	private String UploadTime = "0"; // 必须   
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
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
	/**
	 * 只可以在客户端设置与使用
	 */
	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
	}
	public int getP() {
		return p;
	}
	@Override
	public String toString() {
		return "deleteActivityInfo_C [p=" + p + ", UserID=" + UserID
				+ ", ActivityID=" + ActivityID + ", UploadTime=" + UploadTime
				+ "]";
	}
	
	
	
}
