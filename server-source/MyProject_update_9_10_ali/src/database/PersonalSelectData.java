package database;

/**
 * 用户留言板
 * h_user_discusslist
 * @author Administrator
 *
 */
public class PersonalSelectData {

	private long UserDiscussID = 0;
	private long UserID = 0;
	private long ThisUserID = 0;
	private String UploadTime = "0";
	private String UserName = "0";
	private String Content = "0";
	private String Photo = "0";
	private String Place = "0";
	public PersonalSelectData(long userDiscussID, long userID, long thisUserID,
			String uploadTime, String userName, String content, String photo,
			String place) {
		super();
		UserDiscussID = userDiscussID;
		UserID = userID;
		ThisUserID = thisUserID;
		UploadTime = uploadTime;
		UserName = userName;
		Content = content;
		Photo = photo;
		Place = place;
	}
	public long getUserDiscussID() {
		return UserDiscussID;
	}
	public void setUserDiscussID(long userDiscussID) {
		UserDiscussID = userDiscussID;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public long getThisUserID() {
		return ThisUserID;
	}
	public void setThisUserID(long thisUserID) {
		ThisUserID = thisUserID;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
	
	

}
