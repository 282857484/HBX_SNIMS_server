package protocol.pushservice;

public class SEND_Text {

	private int p = h_protocol_pusher.SEND_Text;
	private String Text; // 文本通知
	private String Photo; // 图片信息
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public int getP() {
		return p;
	}
	
	
}
