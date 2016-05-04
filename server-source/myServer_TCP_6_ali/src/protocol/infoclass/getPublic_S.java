package protocol.infoclass;

public class getPublic_S {

	private int p = h_protocolfromserver1.getPublic_S;
	private String UserID = "0";
	private String TimeStamp = "0";
	private String Mark = "0";
	private String Content = "0";
	private String Public = "0";
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
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
	public String getPublic() {
		return Public;
	}
	public void setPublic(String public1) {
		Public = public1;
	}
	public int getP() {
		return p;
	}
	@Override
	public String toString() {
		return "getPublic_S [p=" + p + ", UserID=" + UserID + ", TimeStamp="
				+ TimeStamp + ", Mark=" + Mark + ", Content=" + Content
				+ ", Public=" + Public + "]";
	}
	
	
}
