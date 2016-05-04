package protocol.infoclass;

public class getPublic_C extends HaveToHave{
	
	private int p = h_protocolfromclient1.getPublic_C;
	private String UserID = "0";
	private String TimeStamp = "0";
	private String Code;
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
		return "getPublic_C [p=" + p + ", UserID=" + UserID + ", TimeStamp="
				+ TimeStamp + ", Code=" + Code + "]";
	}
	
	
}
