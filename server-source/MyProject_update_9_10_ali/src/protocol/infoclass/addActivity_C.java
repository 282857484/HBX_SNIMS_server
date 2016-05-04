package protocol.infoclass;

import tool.FormatTime;




public class addActivity_C extends HaveToHave{

	/***********必须项**********/
	//private 是bigint或无法修改
	private int p = h_protocolfromclient1.addActivity_C;
//	private String ActivityID; // 必须   bigint 活动ID
	private String BuildActivityUserID = "0"; // 必须    bigint 创建人ID
	private String ActivityManagerID = "0"; // 必须    bigint 活动管理员ID
	private String UploadTime = "0"; // String 上传时间
	private String UserID = "0"; // 必须 和对用户身份
	
	
	/**
	 *  All String Type
	 */

	public String IsDirectJoinIn; // 必须    是否可直接加入活动(1.可以; 2.不可以)
	public String ActivityFlag; // 必须    活动通过标识(0代表未经过审核,1代表通过,,-1代表不通过)
	/**
	 * ActivityMemberNumber
	 * 这里需要服务器自己处理数目,输入1即可
	 */
	public String ActivityMemberNumber; // 必须    已参加活动人数
	public String ActivityMaxMemberNumber; // 必须    活动最大人数
	public String ActivityDescribe; //     活动描述
	public String ActivityName; // 必须    必须活动名称
	public String ActivityStartTime; // 必须    活动开始时间
	public String ActivityEndTime; // 必须    活动结束时间
	public String ActivityHoldPlace; // 必须    活动举行地点 (latitude , longitude)
	public String HelpPhone; // 必须    联系电话
	
	/********非必须项*******/
	public String ActivityBelongClass; // 活动所属类别 (大类别 , 小类别) 两级 
	public String ActivityTags; // 活动标签 (登山 , 爱好者 , 岳麓山 , ......) 
	/**
	 * ActivityOpinion
	 * 这里需要服务器自己处理数目
	 * 有单独方法单独处理
	 */
	public String ActivityOpinion; // (得分,总分)
	public String ActivityAddress; // 活动地点的汉字描述
	public String ActivityLogo; // 活动LOGO的绝对路径
	
	
	
	public String getBuildActivityUserID() {
		return BuildActivityUserID;
	}
	public void setBuildActivityUserID(String buildActivityUserID) {
		BuildActivityUserID = buildActivityUserID;
	}
	public String getActivityManagerID() {
		return ActivityManagerID;
	}
	public void setActivityManagerID(String activityManagerID) {
		ActivityManagerID = activityManagerID;
	}
	public int getP() {
		return p;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String UploadTime){
		this.UploadTime = UploadTime;
	}
	/**
	 * 只可以在客户端设置与使用
	 */
	public void setStandardUploadTime() {
		UploadTime = FormatTime.getFormatTime();
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	@Override
	public String toString() {
		return "addActivity_C [p=" + p + ", BuildActivityUserID="
				+ BuildActivityUserID + ", ActivityManagerID="
				+ ActivityManagerID + ", UploadTime=" + UploadTime
				+ ", UserID=" + UserID + "]";
	}

	
	
	
}
