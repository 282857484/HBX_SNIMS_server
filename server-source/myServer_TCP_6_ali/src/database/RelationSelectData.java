package database;

/**
 * h_activity_memberlist
 * @author Administrator
 *
 */
public class RelationSelectData {

	private long RelationID = 0;
	private long ActivityID = 0;
	private long UserID = 0;
	private String UploadTime = "0";
	private String Status = "0";
	public RelationSelectData(long relationID, long activityID, long memberID,
			String uploadTime, String status) {
		super();
		RelationID = relationID;
		ActivityID = activityID;
		UserID = memberID;
		UploadTime = uploadTime;
		Status = status;
	}
	public long getRelationID() {
		return RelationID;
	}
	public void setRelationID(long relationID) {
		RelationID = relationID;
	}
	public long getActivityID() {
		return ActivityID;
	}
	public void setActivityID(long activityID) {
		ActivityID = activityID;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
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
	
	

}
