package protocol.infoclass;

import tool.FormatTime;

public class getActivitySpecificItemList_C extends HaveToHave{

	private int p = h_protocolfromclient1.getActivitySpecificItemList_C;

	private String UserID = "0";
	private String ActivityID = "0";
	private String ActivitySpecificItemsID = "0";
	private String UploadTime = "0";
	private String PageSize = "10"; // 集合大小
	private String PageIndex = "0";

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

	/**
	 * 只可以在客户端设置与使用
	 */
	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
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

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "getActivitySpecificItemList_C [p=" + p + ", UserID=" + UserID
				+ ", ActivityID=" + ActivityID + ", ActivitySpecificItemsID="
				+ ActivitySpecificItemsID + ", UploadTime=" + UploadTime
				+ ", PageSize=" + PageSize + ", PageIndex=" + PageIndex + "]";
	}

}
