package protocol.infoclass;

import tool.FormatTime;

public class addUserFeedback_C extends HaveToHave{

	private int p = h_protocolfromclient1.addUserFeedback_C;
	private String UserID = "0"; // 用户id
	private String UploadTime = "0"; // 上传时间
	private String Feedback = "0"; // 反馈
	private String Photo = "0"; // 反馈图片
	private String Connect = "0"; // 反馈的联系方式
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
	public String getFeedback() {
		return Feedback;
	}
	public void setFeedback(String feedback) {
		Feedback = feedback;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getConnect() {
		return Connect;
	}
	public void setConnect(String connect) {
		Connect = connect;
	}
	public int getP() {
		return p;
	}


	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
	}
	@Override
	public String toString() {
		return "addUserFeedback_C [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Feedback=" + Feedback
				+ ", Photo=" + Photo + ", Connect=" + Connect + "]";
	}
}
