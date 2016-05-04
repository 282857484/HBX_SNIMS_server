package protocol.infoclass;

public class changFileNameOrder_C extends HaveToHave {

	private int p = h_protocolfromclient1.changFileNameOrder_C;
	private String UserID = "0";
	private String UploadTime = "0";
	private String PhotoNumber = "0";
	private String RadishPit1 = "0"; // 萝卜坑1,如果值为0则无图片
	private String RadishPit2 = "0"; // 萝卜坑2
	private String RadishPit3 = "0"; // 萝卜坑3
	private String RadishPit4 = "0"; // 萝卜坑4
	private String RadishPit5 = "0"; // 萝卜坑5
	private String RadishPit6 = "0"; // 萝卜坑6
	private String RadishPit7 = "0"; // 萝卜坑7
	private String RadishPit8 = "0"; // 萝卜坑8

	public void setRadishPit(String radishPit1, String radishPit2,
			String radishPit3, String radishPit4, String radishPit5,
			String radishPit6, String radishPit7, String radishPit8) {
		RadishPit1 = radishPit1;
		RadishPit2 = radishPit2;
		RadishPit3 = radishPit3;
		RadishPit4 = radishPit4;
		RadishPit5 = radishPit5;
		RadishPit6 = radishPit6;
		RadishPit7 = radishPit7;
		RadishPit8 = radishPit8;
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

	public int getP() {
		return p;
	}

	public String getRadishPit1() {
		return RadishPit1;
	}

	public String getRadishPit2() {
		return RadishPit2;
	}

	public String getRadishPit3() {
		return RadishPit3;
	}

	public String getRadishPit4() {
		return RadishPit4;
	}

	public String getRadishPit5() {
		return RadishPit5;
	}

	public String getRadishPit6() {
		return RadishPit6;
	}

	public String getRadishPit7() {
		return RadishPit7;
	}

	public String getRadishPit8() {
		return RadishPit8;
	}

	@Override
	public String toString() {
		return "changFileNameOrder_C [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", PhotoNumber=" + PhotoNumber
				+ ", RadishPit1=" + RadishPit1 + ", RadishPit2=" + RadishPit2
				+ ", RadishPit3=" + RadishPit3 + ", RadishPit4=" + RadishPit4
				+ ", RadishPit5=" + RadishPit5 + ", RadishPit6=" + RadishPit6
				+ ", RadishPit7=" + RadishPit7 + ", RadishPit8=" + RadishPit8
				+ "]";
	}

	public String getPhotoNumber() {
		return PhotoNumber;
	}

	public void setPhotoNumber(String photoNumber) {
		PhotoNumber = photoNumber;
	}

}
