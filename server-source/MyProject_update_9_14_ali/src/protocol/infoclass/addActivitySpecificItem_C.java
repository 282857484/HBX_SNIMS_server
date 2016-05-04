package protocol.infoclass;

import tool.FormatTime;

public class addActivitySpecificItem_C extends HaveToHave{

	/************必须项***********/
	private int p = h_protocolfromclient1.addActivitySpecificItem_C;
	private String ActivityID = "0"; // 活动ID
//	private String ActivitySpecificItemsID;
	private String UploadTime = "0"; // 上传时间
	private String UserID = "0"; // 上传用户
	
	/************非必须项***********/
	public String ActivityItemHoldPlace; // 活动举行地点 (latitude , longitude)
	public String ActivityItemHoldTime; // 生成标准时间格式
	public String ActivitySpecificItemDescribe; // 某活动条目
	public String ActivitySpecificItemPhoto; // 图片路径
	public String getActivityID() {
		return ActivityID;
	}
	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}
//	public String getActivitySpecificItemsID() {
//		return ActivitySpecificItemsID;
//	}
//	public void setActivitySpecificItemsID(String activitySpecificItemsID) {
//		ActivitySpecificItemsID = activitySpecificItemsID;
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
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	@Override
	public String toString() {
		return "addActivitySpecificItem_C [p=" + p + ", ActivityID="
				+ ActivityID + ", UploadTime=" + UploadTime + ", UserID="
				+ UserID + "]";
	}

	
}
