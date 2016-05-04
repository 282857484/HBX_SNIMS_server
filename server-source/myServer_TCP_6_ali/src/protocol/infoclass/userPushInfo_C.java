package protocol.infoclass;

import protocol.zinfo.MomentBaseInfo;

public class userPushInfo_C extends HaveToHave{

	private int p = h_protocolfromclient1.userPushInfo_C;
	private String UploadTime = "0";
	private String UserID = "0";
	private String ThisUserID = "0";
	private MomentBaseInfo mbi  ;
	public int getP() {
		return p;
	}

	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getThisUserID() {
		return ThisUserID;
	}
	public void setThisUserID(String thisUserID) {
		ThisUserID = thisUserID;
	}

	@Override
	public String toString() {
		return "userPushInfo_C [p=" + p + ", UploadTime=" + UploadTime
				+ ", UserID=" + UserID + ", ThisUserID=" + ThisUserID + "]";
	}

	public MomentBaseInfo getMbi() {
		return mbi;
	}

	public void setMbi(MomentBaseInfo mbi) {
		this.mbi = mbi;
	}

//	public userPushInfo_C(int p, String uploadTime, String userID,
//			String thisUserID, MomentBaseInfo mbi) {
//		super();
//		this.p = p;
//		UploadTime = uploadTime;
//		UserID = userID;
//		ThisUserID = thisUserID;
//		this.mbi = mbi;
//	}

	
	
}
