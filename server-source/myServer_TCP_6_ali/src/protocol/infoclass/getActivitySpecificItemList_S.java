package protocol.infoclass;

public class getActivitySpecificItemList_S extends HaveToHave{

	private int p = h_protocolfromserver1.getActivitySpecificItemList_S;

	private String UserID = "0";
	private String ActivityID = "0";
	private String ActivitySpecificItemsID = "0";
	private String UploadTime = "0";
	private String PageSize = "10"; // 集合大小
	private String PageIndex = "0";
	private String Mark = "0";
	private String Content = "0";

	private String ActivitySpecificItemList = "0";

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

	public String getActivitySpecificItemList() {
		return ActivitySpecificItemList;
	}

	public void setActivitySpecificItemList(String activitySpecificItemList) {
		ActivitySpecificItemList = activitySpecificItemList;
	}

	public int getP() {
		return p;
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

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "getActivitySpecificItemList_S [p=" + p + ", UserID=" + UserID
				+ ", ActivityID=" + ActivityID + ", ActivitySpecificItemsID="
				+ ActivitySpecificItemsID + ", UploadTime=" + UploadTime
				+ ", PageSize=" + PageSize + ", PageIndex=" + PageIndex
				+ ", Mark=" + Mark + ", Content=" + Content
				+ ", ActivitySpecificItemList=" + ActivitySpecificItemList
				+ "]";
	}

}
