package database;

/**
 * h_activity_discusslist
 * 
 * @author Administrator
 * 
 */
public class ActivityDiscussSelectData {

	private long DiscussID = 0;
	private long ActivityID = 0;
	private long UserID = 0;
	private long PointDiscussID = 0;
	private long ThisUserID = 0;
	private String UploadTime = "0";
	private String ActivityName = "0";
	private String UserName = "0";
	private String DiscussContent = "0";
	private String Photo = "0";

	public ActivityDiscussSelectData(long discussID, long activityID,
			long userID, long pointDiscussID, long thisUserID,
			String uploadTime, String activityName, String userName,
			String discussContent, String photo) {
		super();
		DiscussID = discussID;
		ActivityID = activityID;
		UserID = userID;
		PointDiscussID = pointDiscussID;
		ThisUserID = thisUserID;
		UploadTime = uploadTime;
		ActivityName = activityName;
		UserName = userName;
		DiscussContent = discussContent;
		Photo = photo;
	}

	public long getDiscussID() {
		return DiscussID;
	}

	public void setDiscussID(long discussID) {
		DiscussID = discussID;
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

	public long getPointDiscussID() {
		return PointDiscussID;
	}

	public void setPointDiscussID(long pointDiscussID) {
		PointDiscussID = pointDiscussID;
	}

	public String getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}

	public String getActivityName() {
		return ActivityName;
	}

	public void setActivityName(String activityName) {
		ActivityName = activityName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getDiscussContent() {
		return DiscussContent;
	}

	public void setDiscussContent(String discussContent) {
		DiscussContent = discussContent;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public long getThisUserID() {
		return ThisUserID;
	}

	public void setThisUserID(long thisUserID) {
		ThisUserID = thisUserID;
	}

}
