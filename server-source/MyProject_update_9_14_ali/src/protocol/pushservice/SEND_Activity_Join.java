package protocol.pushservice;

import database.ActivitySelectData;
import database.RelationSelectData;
import database.UserInfoSelectData;

public class SEND_Activity_Join {

	private int p = h_protocol_pusher.SEND_Activity_Join;
	private RelationSelectData rsd; // 关系ID
	private UserInfoSelectData uisd; // 申请人的详细信息
	private ActivitySelectData asd; // 活动详情
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public RelationSelectData getRsd() {
		return rsd;
	}
	public void setRsd(RelationSelectData rsd) {
		this.rsd = rsd;
	}
	public UserInfoSelectData getUisd() {
		return uisd;
	}
	public void setUisd(UserInfoSelectData uisd) {
		this.uisd = uisd;
	}
	public ActivitySelectData getAsd() {
		return asd;
	}
	public void setAsd(ActivitySelectData asd) {
		this.asd = asd;
	}
	
	
}
