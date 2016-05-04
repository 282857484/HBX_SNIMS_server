package database;

/**
 * h_activity_baseinfo
 * 
 * @author Administrator
 * 
 */
public class ActivitySelectData {

	private long ActivityID = 0;
	private long BuildActivityUserID = 0;
	private long ActivityManagerID = 0;
	private String UploadTime = "0";
	private String IsDirectJoinIn = "0";
	private String ActivityFlag = "0";
	private String ActivityMemberNumber = "0";
	private String ActivityMaxMemberNumber = "0";
	private String ActivityDescribe = "0";
	private String ActivityName = "0";
	private String ActivityStartTime = "0";
	private String ActivityEndTime = "0";
	private String ActivityHoldPlace = "0";
	private String HelpPhone = "0";
	private String ActivityBelongClass = "0";
	private String ActivityTags = "0";
	private String ActivityOpinion = "0";
	private String ActivityAddress = "0";
	private String ActivityLogo = "0";

	public ActivitySelectData(long activityID, long buildActivityUserID,
			long activityManagerID, String uploadTime, String isDirectJoinIn,
			String activityFlag, String activityMemberNumber,
			String activityMaxMemberNumber, String activityDescribe,
			String activityName, String activityStartTime,
			String activityEndTime, String activityHoldPlace, String helpPhone,
			String activityBelongClass, String activityTags,
			String activityOpinion, String activityAddress, String activityLogo) {
		super();
		ActivityID = activityID;
		BuildActivityUserID = buildActivityUserID;
		ActivityManagerID = activityManagerID;
		UploadTime = uploadTime;
		IsDirectJoinIn = isDirectJoinIn;
		ActivityFlag = activityFlag;
		ActivityMemberNumber = activityMemberNumber;
		ActivityMaxMemberNumber = activityMaxMemberNumber;
		ActivityDescribe = activityDescribe;
		ActivityName = activityName;
		ActivityStartTime = activityStartTime;
		ActivityEndTime = activityEndTime;
		ActivityHoldPlace = activityHoldPlace;
		HelpPhone = helpPhone;
		ActivityBelongClass = activityBelongClass;
		ActivityTags = activityTags;
		ActivityOpinion = activityOpinion;
		ActivityAddress = activityAddress;
		ActivityLogo = activityLogo;
	}

	public long getActivityID() {
		return ActivityID;
	}

	public void setActivityID(long activityID) {
		ActivityID = activityID;
	}

	public long getBuildActivityUserID() {
		return BuildActivityUserID;
	}

	public void setBuildActivityUserID(long buildActivityUserID) {
		BuildActivityUserID = buildActivityUserID;
	}

	public long getActivityManagerID() {
		return ActivityManagerID;
	}

	public void setActivityManagerID(long activityManagerID) {
		ActivityManagerID = activityManagerID;
	}

	public String getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}

	public String getIsDirectJoinIn() {
		return IsDirectJoinIn;
	}

	public void setIsDirectJoinIn(String isDirectJoinIn) {
		IsDirectJoinIn = isDirectJoinIn;
	}

	public String getActivityFlag() {
		return ActivityFlag;
	}

	public void setActivityFlag(String activityFlag) {
		ActivityFlag = activityFlag;
	}

	public String getActivityMemberNumber() {
		return ActivityMemberNumber;
	}

	public void setActivityMemberNumber(String activityMemberNumber) {
		ActivityMemberNumber = activityMemberNumber;
	}

	public String getActivityMaxMemberNumber() {
		return ActivityMaxMemberNumber;
	}

	public void setActivityMaxMemberNumber(String activityMaxMemberNumber) {
		ActivityMaxMemberNumber = activityMaxMemberNumber;
	}

	public String getActivityDescribe() {
		return ActivityDescribe;
	}

	public void setActivityDescribe(String activityDescribe) {
		ActivityDescribe = activityDescribe;
	}

	public String getActivityName() {
		return ActivityName;
	}

	public void setActivityName(String activityName) {
		ActivityName = activityName;
	}

	public String getActivityStartTime() {
		return ActivityStartTime;
	}

	public void setActivityStartTime(String activityStartTime) {
		ActivityStartTime = activityStartTime;
	}

	public String getActivityEndTime() {
		return ActivityEndTime;
	}

	public void setActivityEndTime(String activityEndTime) {
		ActivityEndTime = activityEndTime;
	}

	public String getActivityHoldPlace() {
		return ActivityHoldPlace;
	}

	public void setActivityHoldPlace(String activityHoldPlace) {
		ActivityHoldPlace = activityHoldPlace;
	}

	public String getHelpPhone() {
		return HelpPhone;
	}

	public void setHelpPhone(String helpPhone) {
		HelpPhone = helpPhone;
	}

	public String getActivityBelongClass() {
		return ActivityBelongClass;
	}

	public void setActivityBelongClass(String activityBelongClass) {
		ActivityBelongClass = activityBelongClass;
	}

	public String getActivityTags() {
		return ActivityTags;
	}

	public void setActivityTags(String activityTags) {
		ActivityTags = activityTags;
	}

	public String getActivityOpinion() {
		return ActivityOpinion;
	}

	public void setActivityOpinion(String activityOpinion) {
		ActivityOpinion = activityOpinion;
	}

	public String getActivityAddress() {
		return ActivityAddress;
	}

	public void setActivityAddress(String activityAddress) {
		ActivityAddress = activityAddress;
	}

	public String getActivityLogo() {
		return ActivityLogo;
	}

	public void setActivityLogo(String activityLogo) {
		ActivityLogo = activityLogo;
	}

}
