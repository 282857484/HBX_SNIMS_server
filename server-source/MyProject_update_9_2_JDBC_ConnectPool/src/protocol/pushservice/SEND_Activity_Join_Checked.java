package protocol.pushservice;

import database.ActivitySelectData;
import database.RelationSelectData;

public class SEND_Activity_Join_Checked {

	private int p = h_protocol_pusher.SEND_Activity_Join_Checked;
	private RelationSelectData rsd; // 关系详情
	private ActivitySelectData asd; // 活动详情
	
	public RelationSelectData getRsd() {
		return rsd;
	}
	public void setRsd(RelationSelectData rsd) {
		this.rsd = rsd;
	}
	public int getP() {
		return p;
	}
	public ActivitySelectData getAsd() {
		return asd;
	}
	public void setAsd(ActivitySelectData asd) {
		this.asd = asd;
	}
	
	
}
