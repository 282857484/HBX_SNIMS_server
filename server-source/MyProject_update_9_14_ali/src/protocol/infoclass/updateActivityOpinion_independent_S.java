package protocol.infoclass;
public class updateActivityOpinion_independent_S extends HaveToHave{

	private int p = h_protocolfromserver1.updateActivityOpinion_independent_S;
	private String UserID = "0";
	private String ActivityID = "0"; // 活动账号
	private String UploadTime = "0";
	private String GotGrade = "0"; // 取得的分数
	private String TotalFullGrade = "0"; // 人数
	private String Mark = "0";
	private String Content = "0";

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}

	public String getGotGrade() {
		return GotGrade;
	}

	public void setGotGrade(String gotGrade) {
		GotGrade = gotGrade;
	}

	public String getTotalFullGrade() {
		return TotalFullGrade;
	}

	public void setTotalFullGrade(String totalFullGrade) {
		TotalFullGrade = totalFullGrade;
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

	public int getP() {
		return p;
	}

	public String getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "updateActivityOpinion_independent_S [p=" + p + ", UserID="
				+ UserID + ", ActivityID=" + ActivityID + ", UploadTime="
				+ UploadTime + ", GotGrade=" + GotGrade + ", TotalFullGrade="
				+ TotalFullGrade + ", Mark=" + Mark + ", Content=" + Content
				+ "]";
	}

}
