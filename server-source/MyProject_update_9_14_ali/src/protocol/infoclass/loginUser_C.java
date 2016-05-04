package protocol.infoclass;

import tool.FormatTime;

public class loginUser_C extends HaveToHave{

	private int p = h_protocolfromclient1.loginUser_C;
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0"; // 用户名称

	private String Code = "0"; // 必须

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

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public int getP() {
		return p;
	}

	@Override
	public String toString() {
		return "loginUser_C [p=" + p + ", UserID=" + UserID + ", UploadTime="
				+ UploadTime + ", Code=" + Code + "]";
	}

}