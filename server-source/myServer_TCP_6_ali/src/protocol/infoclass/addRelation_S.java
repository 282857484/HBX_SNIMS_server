package protocol.infoclass;
public class addRelation_S extends HaveToHave{

	private int p = h_protocolfromserver1.addRelation_S;
	
	private String ActivityID = "0"; // 活动ID
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0"; // 
	/**
	 * 1.用户参加;
	 * 2.用户申请;
	 * 3.活动方邀请;
	 * 4.用户关注
	 */
	private String Status = "0"; // 状态
	/**
	 * 1-成功
	 * 2-失败
	 */
	private String Mark = "0"; // 标志
	private String Content = "0"; // 返回内容
	
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
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
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
		return "addRelation_S [p=" + p + ", ActivityID=" + ActivityID
				+ ", UserID=" + UserID + ", UploadTime=" + UploadTime
				+ ", Status=" + Status + ", Mark=" + Mark + ", Content="
				+ Content + "]";
	}
	
	
}
