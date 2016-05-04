package protocol.infoclass;

public interface h_protocolfromserver1 {
	int serverport=12345;
	
	/**
	 * 侯斌的协议号
	 */
	/**
	 * 添加用户
	 */
	int addUser_S = 501;
	
	/**
	 * 验证用户/修改在线状态
	 */
	int loginUser_S = 502;
	
	/**
	 * 查找用户
	 * (需要用户ID或名称或标签)
	 */
	int getUserInfo_S = 503;
	
	/**
	 * 修改用户信息
	 */
	int changUserInfo_S = 504;
	
	/**
	 * 删除用户
	 */
	int deleteUserInfo_S = 505;
	
	/**
	 * 发表信息
	 */
	int addPersonalInfo_S = 511;
	
	/**
	 * 获取个人发布信息
	 */
	int getPersonalInfo_C = 512;
	
	/**
	 * 删除信息
	 */
	int deletePersonalInfo_S = 513;
	
	/**
	 * 修改密码
	 */
	int changeCode_S = 514;
	
	/**
	 * 添加活动
	 */
	int addActivity_S = 551;
	
	/**
	 * 修改活动信息
	 */
	int changActivityInfo_S = 552;
	
	/**
	 * 查找活动详情
	 * (需要活动ID或标签或关键字)
	 */
	int getActivityInfo_S = 553;
	
	/**
	 * 删除活动
	 * (服务器系统自动删除可以不必特殊管理)
	 */
	int deleteActivityInfo_S = 554;
	
	
	/**
	 * 添加活动讨论列表
	 */
	int addActivityDiscuss_S = 601;
	
	/**
	 * 获取访问列表
	 */
	int getActivityDiscussList_S = 602;
	
	/**
	 * 删除活动列表
	 */
	int deleteActivityDiscuss_S = 603;
	
	/**
	 * add关联人与活动
	 */
	int addRelation_S = 651;
	
	/**
	 * 修改人与活动关系
	 */
	int changRelation_S = 652;
	
	/**
	 * 获取活动与用户关联
	 */
	int getRelaion_S = 653;
	
	/**
	 * 删除关系
	 */
	int deleteRelation_S = 654;
	
	/**
	 * 添加活动具体项目
	 */
	int addActivitySpecificItem_S = 701;
	
	/**
	 * 获取某活动具体项目
	 */
	int getActivitySpecificItemList_S = 702;
	
	/**
	 * 删除活动具体项目
	 */
	int deleteActivitySpecific_S = 703;
	
	/**
	 * 更新评分
	 */
	int updateActivityOpinion_independent_S = 801;
	
	/**
	 * 用户反馈
	 */
	int addUserFeedback_S = 802;
	
	/**
	 * check8-17
	 * 版本更新
	 */
	int CheckVersionAndOtherInfo_S = 901;
	
	/**
	 * 动态评论推送
	 */
	int userPushInfo_S = 904;

	/**
	 * 改变文件名称
	 */
	int changFileNameOrder_S = 905;

	/**
	 * 获取公要
	 */
	int getPublic_S = 906;



}
