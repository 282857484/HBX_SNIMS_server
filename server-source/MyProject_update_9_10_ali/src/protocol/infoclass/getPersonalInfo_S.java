package protocol.infoclass;

public class getPersonalInfo_S extends HaveToHave{

	private String UserDiscussID = "0"; // 用户发布信息
	private String UserID = "0"; // 被留言用户ID
	private String ThisUserID = "0"; // 留言用户ID
	private String UploadTime = "0"; // 上传时间
	private String PageSize = "10"; // 默认10个
	private String PageIndex = "0"; // 初始为第0页
	private String Mark = "0";
	private String Content = "0";
	
	private String PersonalInfoList;
	

	public String getUserDiscussID() {
		return UserDiscussID;
	}

	public void setUserDiscussID(String userDiscussID) {
		UserDiscussID = userDiscussID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}


	public String getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}

	public String getPageSize() {
		return PageSize;
	}

	public void setPageSize(String pageSize) {
		PageSize = pageSize;
	}

	public String getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(String pageIndex) {
		PageIndex = pageIndex;
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

	public String getPersonalInfoList() {
		return PersonalInfoList;
	}

	public void setPersonalInfoList(String personalInfoList) {
		PersonalInfoList = personalInfoList;
	}

	public String getThisUserID() {
		return ThisUserID;
	}

	public void setThisUserID(String thisUserID) {
		ThisUserID = thisUserID;
	}

	@Override
	public String toString() {
		return "getPersonalInfo_S [UserDiscussID=" + UserDiscussID
				+ ", UserID=" + UserID + ", ThisUserID=" + ThisUserID
				+ ", UploadTime=" + UploadTime + ", PageSize=" + PageSize
				+ ", PageIndex=" + PageIndex + ", Mark=" + Mark + ", Content="
				+ Content + ", PersonalInfoList=" + PersonalInfoList + "]";
	}

}
