package protocol.infoclass;

public class deleteActivitySpecific_S extends HaveToHave{

	private int p = h_protocolfromserver1.deleteActivitySpecific_S;
	private String UserID = "0"; 
	private String ActivityID = "0";
	private String ActivitySpecificItemsID = "0";
	private String UploadTime = "0";
	private String Mark = "0";
	private String Content = "0";

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
		return "deleteActivitySpecific_S [p=" + p + ", UserID=" + UserID
				+ ", ActivityID=" + ActivityID + ", ActivitySpecificItemsID="
				+ ActivitySpecificItemsID + ", UploadTime=" + UploadTime
				+ ", Mark=" + Mark + ", Content=" + Content + "]";
	}



}
