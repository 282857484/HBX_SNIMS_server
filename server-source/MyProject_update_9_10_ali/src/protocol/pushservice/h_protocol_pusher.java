package protocol.pushservice;

public interface h_protocol_pusher {

	/**
	 * 客户端确认包
	 */
	int ACK = 1001;
	
	/**
	 * 客户端主动发送
	 * 客户端心跳包
	 */
	int REQ_Heart_Beat = 1002;
	
	/**
	 * check8-17
	 * 触发:addPersonalInfo_C_Deal
	 * 推送给被留言人
	 * 个人留言包
	 */
	int SEND_Person_Leave_Word = 1003;
	
	/**
	 * 触发:changActivityInfo_C_Deal
	 * 推送给每一个参加活动的用户
	 * 活动变更包
	 * (暂时弃用)
	 */
	int SEND_Activity_Changed = 1004;
	
	/**
	 * check8-17
	 * 触发:addActivityDiscuss_C_Deal
	 * 推送给被留言人
	 * 推送给群主
	 * 活动留言包
	 */
	int SEND_Activity_Leave_word = 1005;
	
	/**
	 * check8-17
	 * 触发:addRelation_C_Deal
	 * 推送给管理员
	 * 参加活动包
	 * 如果是1则仅仅是提醒,如果是2则是要验证是否通过审核
	 */
	int SEND_Activity_Join = 1006;
	
	
	/**
	 * 打包类
	 * 触发:every
	 * SEND_被包含进去了
	 * String PushMessage就是SEND_的JSONString类型
	 */
	int PushMessageSelectData = 1007;
	
	/**
	 * check8-17
	 * 触发:changRelation_C_Deal
	 * 推送给被审核人
	 * 参加许可获得审核
	 */
	int SEND_Activity_Join_Checked = 1008;
	
	/**
	 * check8-17
	 * 触发:addUserFeedback_C_Deal-手动******
	 * 推送给发送反馈信息的用户
	 * 给用户反馈
	 */
	int SEND_UserFeedback_Response = 1009;
	
	/**
	 * check8-17
	 * 触发:手动-其他方法
	 * 推送给任何指定人
	 * 推送文本信息
	 */
	int SEND_Text = 1011;
	
	/**
	 * 用户评分后推送给活动管理员
	 * 触发:updateActivityOpinion_independent_C_Deal
	 * 推送给活动管理员
	 */
	int SEND_UserOpinion = 1012;

	/**
	 * 触发:userPushInfo_C_Deal
	 * 客户端推送动态
	 * 推送给指定人 
	 */
	int SEND_USER_WANTTO_PUSH = 1013;
	
}
