package protocol.infoclass;

import tool.FormatTime;

public class deletePersonalInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.deletePersonalInfo_C;

	private String UserDiscussID = "0"; // 指定留言项
	private String UserID = "0"; // 指定用户ID
	private String UploadTime = "0";

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

	/**
	 * 只可以在客户端设置与使用
	 */
	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
	}

	public int getP() {
		return p;
	}

	@Override
	public String toString() {
		return "deletePersonalInfo_C [p=" + p + ", UserDiscussID="
				+ UserDiscussID + ", UserID=" + UserID + ", UploadTime="
				+ UploadTime + "]";
	}

}
