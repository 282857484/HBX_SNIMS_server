package protocol.infoclass;

import tool.FormatTime;

public class getActivityInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.getActivityInfo_C;

	/**
	 * 如果服务器收到ID = 0时,则是通过其他条件查找
	 */
	private String UserID = "0";
	private String ActivityID = "0";
	private String UploadTime = "0"; // String 上传时间
	private String PageSize = "10"; // 默认10个
	private String PageIndex = "0"; // 初始为第0页

	public String ActivityName; // 活动名称
	public String ActivityStartTime; // 活动开始时间
	public String ActivityEndTime; // 活动结束时间
	public String HelpPhone; // 联系电话 , UserID

	/******** 非必须项 *******/
	public String ActivityBelongClass; // 活动所属类别 (大类别 , 小类别) 两级
	public String ActivityTags; // 活动标签 (登山 , 爱好者 , 岳麓山 , ......)
	public String ActivityOpinion; // (得分,总分)

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}

	/**
	 * 只可以在客户端设置与使用
	 */
	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
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

	public int getP() {
		return p;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "getActivityInfo_C [p=" + p + ", UserID=" + UserID
				+ ", ActivityID=" + ActivityID + ", UploadTime=" + UploadTime
				+ ", PageSize=" + PageSize + ", PageIndex=" + PageIndex + "]";
	}

}
