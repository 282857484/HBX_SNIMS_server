package protocol.infoclass;

public class getActivityInfo_S extends HaveToHave{

	private int p = h_protocolfromserver1.getActivityInfo_S;
	
	private String UserID = "0";
	private String UploadTime = "0";
	private String Mark = "0";
	private String Content = "0";
	private String PageSize = "10"; // 默认10个
	private String PageIndex = "0"; // 初始为1
	
	/**
	 * 由数据库原生类转换为JSON格式而成
	 */
	private String ActivityInfoList = "0";
	
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
	public String getActivityInfoList() {
		return ActivityInfoList;
	}
	public void setActivityInfoList(String activityInfoList) {
		ActivityInfoList = activityInfoList;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	@Override
	public String toString() {
		return "getActivityInfo_S [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Mark=" + Mark
				+ ", Content=" + Content + ", PageSize=" + PageSize
				+ ", PageIndex=" + PageIndex + ", ActivityInfoList="
				+ ActivityInfoList + "]";
	}
	
	
}
