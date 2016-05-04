package database;

/**
 * h_user_baseinfo
 * @author Administrator
 *
 */
public class UserInfoSelectData {

	private long UserID = 0;
	private String UserName = "0";
	private String UploadTime = "0";
	private String Code = "0";
	private String UserPhone = "0";
	private String UserJoinActivity = "0";
	private String UserAttentionClass = "0";
	private String UserQQ = "0";
	private String UserWeiChat = "0";
	private String UserTags = "0";
	private String UserClass = "0";
	private String UserDescribe = "0";
	private String UserLevel = "0";
	private String UserLogo = "0";
	private String UserAge = "0";
	private String UserSex = "0";
	private String School = "0";
	private String Profession = "0";
	private String Birthday = "0";
	private String Home = "0";

	public UserInfoSelectData(long userID, String userName, String uploadTime,
			String code, String userPhone, String userJoinActivity,
			String userAttentionClass, String userQQ, String userWeiChat,
			String userTags, String userClass, String userDescribe,
			String userLevel, String userLogo, String userAge, String userSex,
			String school, String profession, String birthday, String home) {
		super();
		UserID = userID;
		UserName = userName;
		UploadTime = uploadTime;
		Code = code;
		UserPhone = userPhone;
		UserJoinActivity = userJoinActivity;
		UserAttentionClass = userAttentionClass;
		UserQQ = userQQ;
		UserWeiChat = userWeiChat;
		UserTags = userTags;
		UserClass = userClass;
		UserDescribe = userDescribe;
		UserLevel = userLevel;
		UserLogo = userLogo;
		UserAge = userAge;
		UserSex = userSex;
		School = school;
		Profession = profession;
		Birthday = birthday;
		Home = home;
	}


	public long getUserID() {
		return UserID;
	}


	public void setUserID(long userID) {
		UserID = userID;
	}


	public String getUserName() {
		return UserName;
	}


	public void setUserName(String userName) {
		UserName = userName;
	}


	public String getUploadTime() {
		return UploadTime;
	}


	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}


	public String getCode() {
		return Code;
	}


	public void setCode(String code) {
		Code = code;
	}


	public String getUserPhone() {
		return UserPhone;
	}


	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}


	public String getUserJoinActivity() {
		return UserJoinActivity;
	}


	public void setUserJoinActivity(String userJoinActivity) {
		UserJoinActivity = userJoinActivity;
	}


	public String getUserAttentionClass() {
		return UserAttentionClass;
	}


	public void setUserAttentionClass(String userAttentionClass) {
		UserAttentionClass = userAttentionClass;
	}


	public String getUserQQ() {
		return UserQQ;
	}


	public void setUserQQ(String userQQ) {
		UserQQ = userQQ;
	}


	public String getUserWeiChat() {
		return UserWeiChat;
	}


	public void setUserWeiChat(String userWeiChat) {
		UserWeiChat = userWeiChat;
	}


	public String getUserTags() {
		return UserTags;
	}


	public void setUserTags(String userTags) {
		UserTags = userTags;
	}


	public String getUserClass() {
		return UserClass;
	}


	public void setUserClass(String userClass) {
		UserClass = userClass;
	}


	public String getUserDescribe() {
		return UserDescribe;
	}


	public void setUserDescribe(String userDescribe) {
		UserDescribe = userDescribe;
	}


	public String getUserLevel() {
		return UserLevel;
	}


	public void setUserLevel(String userLevel) {
		UserLevel = userLevel;
	}


	public String getUserLogo() {
		return UserLogo;
	}


	public void setUserLogo(String userLogo) {
		UserLogo = userLogo;
	}


	public String getUserAge() {
		return UserAge;
	}


	public void setUserAge(String userAge) {
		UserAge = userAge;
	}


	public String getUserSex() {
		return UserSex;
	}


	public void setUserSex(String userSex) {
		UserSex = userSex;
	}


	public String getSchool() {
		return School;
	}


	public void setSchool(String school) {
		School = school;
	}


	public String getProfession() {
		return Profession;
	}


	public void setProfession(String profession) {
		Profession = profession;
	}


	public String getBirthday() {
		return Birthday;
	}


	public void setBirthday(String birthday) {
		Birthday = birthday;
	}


	public String getHome() {
		return Home;
	}


	public void setHome(String home) {
		Home = home;
	}


	
	
}
