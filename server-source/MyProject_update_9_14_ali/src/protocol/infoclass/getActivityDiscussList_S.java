package protocol.infoclass;

public class getActivityDiscussList_S extends HaveToHave{

	private int p = h_protocolfromserver1.getActivityDiscussList_S;

	private String ActivityID = "0";
	private String UserID = "0";
	private String UploadTime = "0";
	private String PageSize = "10"; // 集合大小
	private String PageIndex = "0";
	private String Mark = "0";
	private String Content = "0";

	private String ActivityDiscussList;

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
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

	public String getActivityDiscussList() {
		return ActivityDiscussList;
	}

	public void setActivityDiscussList(String activityDiscussList) {
		ActivityDiscussList = activityDiscussList;
	}

	public int getP() {
		return p;
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

	@Override
	public String toString() {
		return "getActivityDiscussList_S [p=" + p + ", ActivityID="
				+ ActivityID + ", UserID=" + UserID + ", UploadTime="
				+ UploadTime + ", PageSize=" + PageSize + ", PageIndex="
				+ PageIndex + ", Mark=" + Mark + ", Content=" + Content
				+ ", ActivityDiscussList=" + ActivityDiscussList + "]";
	}

}
