package protocol.pushservice;

/**
 * 手动信息
 */
public class SEND_UserFeedback_Response {

	private int p = h_protocol_pusher.SEND_UserFeedback_Response;
	private String Feedback_Response = "0";
	public String getFeedback_Response() {
		return Feedback_Response;
	}
	public void setFeedback_Response(String feedback_Response) {
		Feedback_Response = feedback_Response;
	}
	public int getP() {
		return p;
	}
	
	
}
