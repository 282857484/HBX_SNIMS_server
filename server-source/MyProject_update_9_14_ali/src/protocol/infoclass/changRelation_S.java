package protocol.infoclass;


public class changRelation_S extends HaveToHave{

	private int p = h_protocolfromserver1.changRelation_S;

	private String RelationID = "0";
	private String ActivityID = "0";
	private String UserID = "0";
	private String UploadTime = "0";
	private String Status = "0";
	private String Mark = "0";
	private String Content = "0";

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
		return "changRelation_S [p=" + p + ", RelationID=" + RelationID
				+ ", ActivityID=" + ActivityID + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Status=" + Status
				+ ", Mark=" + Mark + ", Content=" + Content + "]";
	}

}
