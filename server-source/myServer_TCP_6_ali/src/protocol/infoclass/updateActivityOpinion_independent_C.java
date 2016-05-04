package protocol.infoclass;

import tool.FormatTime;

public class updateActivityOpinion_independent_C extends HaveToHave{
	private int p = h_protocolfromclient1.updateActivityOpinion_independent_C;
	private String UserID = "0";
	private String ActivityID = "0"; // 活动账户
	private String Opinion = "0"; // 评分
	private String UploadTime = "0";

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}

	public String getOpinion() {
		return Opinion;
	}

	public void setOpinion(String opinion) {
		Opinion = opinion;
	}

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
	
	/**
	 * 只可以在客户端设置与使用
	 */
	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
	}

	@Override
	public String toString() {
		return "updateActivityOpinion_independent_C [p=" + p + ", UserID="
				+ UserID + ", ActivityID=" + ActivityID + ", Opinion="
				+ Opinion + ", UploadTime=" + UploadTime + "]";
	}

}
