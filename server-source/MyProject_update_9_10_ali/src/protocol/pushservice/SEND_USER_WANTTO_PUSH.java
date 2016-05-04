package protocol.pushservice;

import protocol.zinfo.MomentBaseInfo;

public class SEND_USER_WANTTO_PUSH {

	private int p = h_protocol_pusher.SEND_USER_WANTTO_PUSH;
	private MomentBaseInfo mbi;
	public MomentBaseInfo getMbi() {
		return mbi;
	}
	public void setMbi(MomentBaseInfo mbi) {
		this.mbi = mbi;
	}
	public int getP() {
		return p;
	}
	
}
