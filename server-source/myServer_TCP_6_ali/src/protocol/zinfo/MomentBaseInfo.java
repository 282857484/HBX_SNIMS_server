package protocol.zinfo;

import java.io.Serializable;

/**
 * 动态数据类
 * @author zhuzhenke
 *
 */
public class MomentBaseInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int MomentId;//一个自增长的数列
	public String MessageId;//存在百度上的动态id  //唯一
	public String UserId;//发表动态的用户Id
	public String UserName;//发表动态的用户昵称
	public String UploadTime;//发表时间
	public String MomentContent;//动态内容
	public String PraiseNum;//赞的数量
	public String CritizenNum;//踩的数量
	public String MyUserId;//赞的人的id
	public String MyUserName;//赞的人的昵称
	public String HasOrNo;//是否已经加载赞或者踩：赞为1.踩为-1.0为没有赞或者踩
	
	public String Location;//位置 [12.1212,12.121233]
	public String Sex;///性别：1-男     2-女
	public String Age;//年龄
	//0819后面加的
	public String ShowPhotos;
	public MomentBaseInfo() {
		super();
	}
	
	public MomentBaseInfo(int momentId, String messageId, String userId,
			String userName, String uploadTime, String momentContent,
			String praiseNum, String critizenNum, String myUserId,
			String myUserName, String hasOrNo, String location, String sex,
			String age, String showPhotos) {
		super();
		MomentId = momentId;
		MessageId = messageId;
		UserId = userId;
		UserName = userName;
		UploadTime = uploadTime;
		MomentContent = momentContent;
		PraiseNum = praiseNum;
		CritizenNum = critizenNum;
		MyUserId = myUserId;
		MyUserName = myUserName;
		HasOrNo = hasOrNo;
		Location = location;
		Sex = sex;
		Age = age;
		ShowPhotos = showPhotos;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getShowPhotos() {
		return ShowPhotos;
	}
	public void setShowPhotos(String showPhotos) {
		ShowPhotos = showPhotos;
	}
	public int getMomentId() {
		return MomentId;
	}
	public void setMomentId(int momentId) {
		MomentId = momentId;
	}
	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getMomentContent() {
		return MomentContent;
	}
	public void setMomentContent(String momentContent) {
		MomentContent = momentContent;
	}
	public String getPraiseNum() {
		return PraiseNum;
	}
	public void setPraiseNum(String praiseNum) {
		PraiseNum = praiseNum;
	}
	public String getCritizenNum() {
		return CritizenNum;
	}
	public void setCritizenNum(String critizenNum) {
		CritizenNum = critizenNum;
	}
	public String getMyUserId() {
		return MyUserId;
	}
	public void setMyUserId(String myUserId) {
		MyUserId = myUserId;
	}
	public String getMyUserName() {
		return MyUserName;
	}
	public void setMyUserName(String myUserName) {
		MyUserName = myUserName;
	}
	public String getHasOrNo() {
		return HasOrNo;
	}
	public void setHasOrNo(String hasOrNo) {
		HasOrNo = hasOrNo;
	}
	@Override
	public String toString() {
		return "MomentBaseInfo [MomentId=" + MomentId + ", MessageId="
				+ MessageId + ", UserId=" + UserId + ", UserName=" + UserName
				+ ", UploadTime=" + UploadTime + ", MomentContent="
				+ MomentContent + ", PraiseNum=" + PraiseNum + ", CritizenNum="
				+ CritizenNum + ", MyUserId=" + MyUserId + ", MyUserName="
				+ MyUserName + ", HasOrNo=" + HasOrNo + "]";
	}
	
	
}
