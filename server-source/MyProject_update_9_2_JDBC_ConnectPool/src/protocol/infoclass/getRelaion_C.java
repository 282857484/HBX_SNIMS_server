package protocol.infoclass;

import tool.FormatTime;

public class getRelaion_C extends HaveToHave{

	private int p = h_protocolfromclient1.getRelaion_C;

	private String RelationID = "0"; // 关系ID
	private String ActivityID = "0"; // 活动ID
	private String SearchUserID = "0";
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0";
	private String PageSize = "10"; // 集合大小
	private String PageIndex = "0"; // 页数索引
	/**
	 * 0-不带任何附加选项
	 * 1-基于RelationID查询关联的UserID与Activity
	 *   此时RelationId不为0,其他两项为0
	 * 2-基于ActivityID查询关联的UserID中的详细信息
	 *   此时ActivityID不为0,其他两项为0
	 * 3-基于UserID查询关联ActivityID中的详细信息
	 *   此时UserID不为0,其他两项为0
	 */
	private String ExtraMark = "0";
	

	public String getRelationID() {
		return RelationID;
	}

	public void setRelationID(String relationID) {
		RelationID = relationID;
	}

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
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

	public String getExtraMark() {
		return ExtraMark;
	}

	public void setExtraMark(String extraMark) {
		ExtraMark = extraMark;
	}

	public String getSearchUserID() {
		return SearchUserID;
	}

	public void setSearchUserID(String searchUserID) {
		SearchUserID = searchUserID;
	}

	@Override
	public String toString() {
		return "getRelaion_C [p=" + p + ", RelationID=" + RelationID
				+ ", ActivityID=" + ActivityID + ", SearchUserID="
				+ SearchUserID + ", UserID=" + UserID + ", UploadTime="
				+ UploadTime + ", PageSize=" + PageSize + ", PageIndex="
				+ PageIndex + ", ExtraMark=" + ExtraMark + "]";
	}

}
