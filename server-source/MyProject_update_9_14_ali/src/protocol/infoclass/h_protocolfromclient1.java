package protocol.infoclass;

public interface h_protocolfromclient1 {
	/**
	 * 服务器IP地址
	 */
	String serverip="0.0.0.0";
	/**
	 * 服务器端口号
	 */
	int serverport=12345;
	
	/**
	 * 添加用户
	 */
	int addUser_C = 1;
	
	/**
	 * 验证用户/修改在线状态
	 */
	int loginUser_C = 2;
	
	/**
	 * 查找用户
	 * (需要用户ID或名称或标签)
	 */
	int getUserInfo_C = 3;
	
	/**
	 * 修改用户信息
	 */
	int changUserInfo_C = 4;
	
	/**
	 * 删除用户
	 */
	int deleteUserInfo_C = 5;
	
	/**
	 * 发表个人信息
	 */
	int addPersonalInfo_C = 11;
	
	/**
	 * 获取个人发布信息
	 */
	int getPersonalInfo_C = 12;
	
	/**
	 * 删除个人信息
	 */
	int deletePersonalInfo_C = 13;
	
	/**
	 * 修改密码
	 */
	int changeCode_C = 14;
	
	/**
	 * 添加活动
	 */
	int addActivity_C = 51;
	
	/**
	 * 修改活动信息
	 */
	int changActivityInfo_C = 52;
	
	/**
	 * 查找活动详情
	 * (需要活动ID或标签或关键字)
	 */
	int getActivityInfo_C = 53;
	
	/**
	 * 删除活动
	 * (服务器系统自动删除可以不必特殊管理)
	 */
	int deleteActivityInfo_C = 54;
	
	
	/**
	 * 添加活动讨论列表
	 */
	int addActivityDiscuss_C = 101;
	
	/**
	 * 获取访问列表
	 */
	int getActivityDiscussList_C = 102;
	
	/**
	 * 删除活动列表
	 */
	int deleteActivityDiscuss_C = 103;
	
	/**
	 * add关联人与活动
	 */
	int addRelation_C = 151;
	
	/**
	 * 修改人与活动关系
	 */
	int changRelation_C = 152;
	
	/**
	 * 获取活动与用户关联
	 */
	int getRelaion_C = 153;
	
	/**
	 * 删除关系
	 */
	int deleteRelation_C = 154;
	
	/**
	 * 添加活动具体项目
	 */
	int addActivitySpecificItem_C = 201;
	
	/**
	 * 获取某活动具体项目
	 */
	int getActivitySpecificItemList_C = 202;
	
	/**
	 * 删除活动具体项目
	 */
	int deleteActivitySpecific_C = 203;
	
	/**
	 * 更新评分
	 */
	int updateActivityOpinion_independent_C = 301;
	
	/**
	 * 添加用户反馈
	 */
	int addUserFeedback_C = 302;
	
	/**
	 * 核对用户应用信息版本号等
	 */
	int CheckVersionAndOtherInfo_C = 401;
	
	/**
	 * 事件统计
	 */
	int EventStatistics = 403;
	
	/**
	 * 动态评论推送
	 */
	int userPushInfo_C = 404;
	
	/**
	 * 改变文件名称
	 */
	int changFileNameOrder_C = 405;
	
	/**
	 * 获取公钥
	 */
	int getPublic_C = 406;
	
}
