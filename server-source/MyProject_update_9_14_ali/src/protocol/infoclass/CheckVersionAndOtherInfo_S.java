package protocol.infoclass;


public class CheckVersionAndOtherInfo_S extends HaveToHave{
	private int p = h_protocolfromserver1.CheckVersionAndOtherInfo_S;
	
	private String UserID = "0";
	private String UploadTime = "0";
	private String Version = "0"; // 应用版本
	private String URL = "0"; // 下载地址链接
	private String Text = "0"; // 附加文本
	private String UpdateTime = "0"; // 版本更新时间
	/**
	 * 1.需要更新
	 * 2.不需要更新
	 */
	private String Mark = "0";
	private String Content = "0";
	
	
	
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	public int getP() {
		return p;
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
	@Override
	public String toString() {
		return "CheckVersionAndOtherInfo_S [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Version=" + Version
				+ ", URL=" + URL + ", Text=" + Text + ", UpdateTime="
				+ UpdateTime + ", Mark=" + Mark + ", Content=" + Content + "]";
	}
	
}
