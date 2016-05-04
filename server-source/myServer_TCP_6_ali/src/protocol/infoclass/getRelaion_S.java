package protocol.infoclass;

public class getRelaion_S extends HaveToHave{

	private int p = h_protocolfromserver1.getRelaion_S;
	
	private String RelationID = "0"; // 关系ID
	private String ActivityID = "0"; // 活动ID
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0";
	private String PageSize = "10"; // 集合大小
	private String PageIndex = "0";
	private String Mark = "0";
	private String Content = "0";
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
	
	private String RelationList = "0";
	

	public String OneUser = "0"; // ExtraMark = 1 exist
	public String OneActivity = "0"; // ExtraMark = 1 exist
	public String UserIDList = "0"; // ExtraMark = 2 exist
	public String ActivityList = "0"; // ExtraMark = 3 exist

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

	public String getRelationList() {
		return RelationList;
	}

	public void setRelationList(String relationList) {
		RelationList = relationList;
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

	@Override
	public String toString() {
		return "getRelaion_S [p=" + p + ", RelationID=" + RelationID
				+ ", ActivityID=" + ActivityID + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", PageSize=" + PageSize
				+ ", PageIndex=" + PageIndex + ", Mark=" + Mark + ", Content="
				+ Content + ", ExtraMark=" + ExtraMark + ", RelationList="
				+ RelationList + "]";
	}
	
	
	
}
