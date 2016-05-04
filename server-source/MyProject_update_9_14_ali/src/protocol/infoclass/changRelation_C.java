package protocol.infoclass;

import tool.FormatTime;

public class changRelation_C extends HaveToHave{

	private int p = h_protocolfromclient1.changRelation_C;

	private String SUserID; // 发送信息者的ID
	
	private String RelationID = "0"; // 关系ID
	private String ActivityID = "0"; // 活动ID
	private String UserID = "0"; // 需变更关系的用户ID
	private String UploadTime = "0";
	/**
	 * 1.用户参加;
	 * 2.用申请;
	 * 3.活动方邀请;
	 * 4.用户关注
	 * 5.未审核通过
	 * 6.被踢出用户
	 * 状态跳变:
	 *    管理员不能发chang3
	 *    管理员发送的直接到1
	 *    普通用户到1或2
	 */
	private String Status ="0"; 

	public String getRelationID() {
		return RelationID;
	}

	public void setRelationID(String relationID) {
		RelationID = relationID;
	}

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}


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

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

	public String getSUserID() {
		return SUserID;
	}

	public void setSUserID(String sUserID) {
		SUserID = sUserID;
	}

	@Override
	public String toString() {
		return "changRelation_C [p=" + p + ", SUserID=" + SUserID
				+ ", RelationID=" + RelationID + ", ActivityID=" + ActivityID
				+ ", UserID=" + UserID + ", UploadTime=" + UploadTime
				+ ", Status=" + Status + "]";
	}

}
