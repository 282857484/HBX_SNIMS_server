package protocol.pushservice;

import database.PersonalSelectData;

public class SEND_Person_Leave_Word {

	private int p = h_protocol_pusher.SEND_Person_Leave_Word;
//	private String PusherID;
	/**
	 * 用户留言项
	 */
	private PersonalSelectData psd;


	public PersonalSelectData getPsd() {
		return psd;
	}

	public void setPsd(PersonalSelectData psd) {
		this.psd = psd;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}


}
