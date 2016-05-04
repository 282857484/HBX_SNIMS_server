package protocol.infoclass;

import java.util.List;

import protocol.pushservice.h_protocol_pusher;
import database.PushMessageSelectData;

public class PushMessageListObject {
	private int p = h_protocol_pusher.PushMessageListObject;
	private List<PushMessageSelectData> pmsdList;
	public List<PushMessageSelectData> getPmsdList() {
		return pmsdList;
	}
	public void setPmsdList(List<PushMessageSelectData> pmsdList) {
		this.pmsdList = pmsdList;
	}
	public int getP() {
		return p;
	}

}
