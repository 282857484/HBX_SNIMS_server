package protocol.infoclass;

public class changeCode_S extends HaveToHave{

	private int p = h_protocolfromserver1.changeCode_S;
	private String UserID = "0";
	private String UploadTime = "0";
	private String NewCode = "0";
	private String Mark = "0";
	private String Content = "0";
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
	public String getNewCode() {
		return NewCode;
	}
	public void setNewCode(String newCode) {
		NewCode = newCode;
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
	@Override
	public String toString() {
		return "changeCode_S [p=" + p + ", UserID=" + UserID + ", UploadTime="
				+ UploadTime + ", NewCode=" + NewCode + ", Mark=" + Mark
				+ ", Content=" + Content + "]";
	}
	
	
}
