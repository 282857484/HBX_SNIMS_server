package protocol.infoclass;


public class EventStatistics extends HaveToHave{

	private int p = h_protocolfromclient1.EventStatistics;
	private String UserID = "0";
	private String UploadTime = "0";
	private String Component = "0";
	private String Event = "0";
	private String Content = "0";
	private String EventMark = "0";
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
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}

	public int getP() {
		return p;
	}
	public String getComponent() {
		return Component;
	}
	public void setComponent(String component) {
		Component = component;
	}
	public String getEventMark() {
		return EventMark;
	}
	public void setEventMark(String eventMark) {
		EventMark = eventMark;
	}
	@Override
	public String toString() {
		return "EventStatistics [p=" + p + ", UserID=" + UserID
				+ ", UploadTime=" + UploadTime + ", Component=" + Component
				+ ", Event=" + Event + ", Content=" + Content + ", EventMark="
				+ EventMark + "]";
	}
	
	
}
