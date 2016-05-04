package protocol.infoclass;

import tool.FormatTime;

public class changUserInfo_C extends HaveToHave{

	/*********必须项*********/
	private int p = h_protocolfromclient1.changUserInfo_C;
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0"; // 用户名称
	
	public String Code = "0"; // 必须 
	public String UserName = "0"; // UserName
	public String UserPhone = "0"; // 用户电话
	
	/*********非必须项*********/
	/**
	 * 更改!
	 * 这个字段被用作用户个人图片数量
	 */
	public String UserJoinActivity; // 用户参加活动(活动1,活动2,活动三, ....)逗号隔开
	public String UserAttentionClass; // 用户关注活动(活动1,活动2,活动三, ....)逗号隔开
	public String UserQQ; // 用户QQ
	public String UserWeiChat; // 用户微信
	public String UserTags; // 用户标签(善良,可爱,凶残, .....)
	public String UserClass; // 用户标签(学生,极客,把妹狂人, .....)
	public String UserDescribe; // 用户描述
	public String UserLevel; // 用户级别
	public String UserLogo; // 用户图标
	public String UserAge; // 用户年龄
	public String UserSex; // 用户性别
	public String School;
	public String Profession;
	public String Birthday;
	public String Home;
	
	
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
		return "changUserInfo_C [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Code=" + Code + "]";
	}
	
	
}
