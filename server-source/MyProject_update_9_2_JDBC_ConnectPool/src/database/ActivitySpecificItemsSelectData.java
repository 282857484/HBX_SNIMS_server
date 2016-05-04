package database;

/**
 * h_activity_specificitems
 * @author Administrator
 *
 */
public class ActivitySpecificItemsSelectData {

	private long ActivityID = 0;
	private long ActivitySpecificItemsID = 0;
	private String UploadTime = "0";
	private String ActivityItemHoldPlace = "0";
	private String ActivityItemHoldTime = "0";
	private String ActivitySpecificItemDescribe = "0";
	private String ActivitySpecificItemPhoto = "0";

	public ActivitySpecificItemsSelectData(long activityID,
			long activitySpecificItemsID, String uploadTime,
			String activityItemHoldPlace, String activityItemHoldTime,
			String activitySpecificItemDescribe,
			String activitySpecificItemPhoto) {
		super();
		ActivityID = activityID;
		ActivitySpecificItemsID = activitySpecificItemsID;
		UploadTime = uploadTime;
		ActivityItemHoldPlace = activityItemHoldPlace;
		ActivityItemHoldTime = activityItemHoldTime;
		ActivitySpecificItemDescribe = activitySpecificItemDescribe;
		ActivitySpecificItemPhoto = activitySpecificItemPhoto;
	}

	public long getActivityID() {
		return ActivityID;
	}

	public void setActivityID(long activityID) {
		ActivityID = activityID;
	}

	public long getActivitySpecificItemsID() {
		return ActivitySpecificItemsID;
	}

	public void setActivitySpecificItemsID(long activitySpecificItemsID) {
		ActivitySpecificItemsID = activitySpecificItemsID;
	}

	public String getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}

	public String getActivityItemHoldPlace() {
		return ActivityItemHoldPlace;
	}

	public void setActivityItemHoldPlace(String activityItemHoldPlace) {
		ActivityItemHoldPlace = activityItemHoldPlace;
	}

	public String getActivityItemHoldTime() {
		return ActivityItemHoldTime;
	}

	public void setActivityItemHoldTime(String activityItemHoldTime) {
		ActivityItemHoldTime = activityItemHoldTime;
	}

	public String getActivitySpecificItemDescribe() {
		return ActivitySpecificItemDescribe;
	}

	public void setActivitySpecificItemDescribe(String activitySpecificItemDescribe) {
		ActivitySpecificItemDescribe = activitySpecificItemDescribe;
	}

	public String getActivitySpecificItemPhoto() {
		return ActivitySpecificItemPhoto;
	}

	public void setActivitySpecificItemPhoto(String activitySpecificItemPhoto) {
		ActivitySpecificItemPhoto = activitySpecificItemPhoto;
	}

	
}
