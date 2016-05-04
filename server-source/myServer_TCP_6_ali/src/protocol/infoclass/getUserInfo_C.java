package protocol.infoclass;

import tool.FormatTime;

public class getUserInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.getUserInfo_C;
	/**
	 * 如果UserID = 0,则通过其他条件查找
	 */
	private String UserID = "0"; // 用户ID
	private String UploadTime = "0"; // 用户名称
	private String PageSize = "10" ; // 集合大小
	private String PageIndex = "0" ;

	public String SearchUserID; // 被搜索UserID
	public String UserName; // UserName
	public String UserPhone; // 用户电话

	public String UserQQ; // 用户QQ
	public String UserWeiChat; // 用户微信
	public String UserTags; // 用户标签(善良,可爱,凶残, .....)
	public String UserClass; // 用户标签(学生,极客,把妹狂人, .....)
	public String UserAge; // 范围搜索(minnum,maxnum)
	public String UserSex; // 搜索性别(1.男;2.女)

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

	@Override
	public String toString() {
		return "getUserInfo_C [p=" + p + ", UserID=" + UserID + ", UploadTime="
				+ UploadTime + ", PageSize=" + PageSize + ", PageIndex="
				+ PageIndex + "]";
	}

}
