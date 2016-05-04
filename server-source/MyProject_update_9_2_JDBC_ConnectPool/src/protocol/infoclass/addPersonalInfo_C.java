package protocol.infoclass;

import tool.FormatTime;

public class addPersonalInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.addPersonalInfo_C;
	
	/*******必须项*******/
	
//	private String UserDiscussID; // 用户发布信息
	private String UserID = "0"; // 被留言用户ID
	private String ThisUserID = "0"; // 留言用户ID
	private String UploadTime = "0"; // 上传时间
	
	/*******非必须项********/
	public String UserName; // 留言用户姓名
	public String Content; // 留言内容
	public String Photo; // 内容中的图片
	public String Place; // 发布地点(latitude , longitude)
//	public String getUserDiscussID() {
//		return UserDiscussID;
//	}
//	public void setUserDiscussID(String userDiscussID) {
//		UserDiscussID = userDiscussID;
//	}
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
	public String getThisUserID() {
		return ThisUserID;
	}
	public void setThisUserID(String thisUserID) {
		ThisUserID = thisUserID;
	}
	@Override
	public String toString() {
		return "addPersonalInfo_C [p=" + p + ", UserID=" + UserID
				+ ", ThisUserID=" + ThisUserID + ", UploadTime=" + UploadTime
				+ "]";
	}

	
}
