package TestClient;

import java.util.ArrayList;

import protocol.infoclass.addCarLocation_C;
import protocol.infoclass.addCar_C;
import protocol.infoclass.addMaster_C;
import tool.FormatTime;

import com.google.gson.Gson;

public class Test_Info {

	Gson g = new Gson();

	public String Test_addMaster() {
		addMaster_C ac = new addMaster_C(FormatTime.getFormatTime(), "MLGB", "8", "21", "Mr.MLGB");
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ac);
		return g.toJson(list);
	}

	public String Test_addCar() {
		addCar_C ac = new addCar_C("1", FormatTime.getFormatTime(), "BMW", "DOCTORMADE", "1800000");
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ac);
		return g.toJson(list);
	}

	public String Test_addCarLoc() {
		addCarLocation_C ac = new addCarLocation_C("1", "1", FormatTime.getFormatTime(), "28.177600", "112.950200", "我的良驹", "BMW-宝马");
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ac);
		return g.toJson(list);
	}

//	public String Test_addActivity() {
//		addActivity_C li = new addActivity_C();
//		li.setActivityManagerID("20110808203");
//		li.setBuildActivityUserID("13007420476");
//		li.setStandardUploadTime();
//		li.ActivityAddress = "111111";
//		li.ActivityBelongClass = "222222";
//		li.ActivityDescribe = "3333333";
//		li.ActivityStartTime = "201411051430";
//		li.ActivityEndTime = "199211051712";
//		/**
//		 * 不能太长
//		 */
//		li.ActivityFlag = "1";
//		// 这里需要输入规范的经纬度格式
//		li.ActivityHoldPlace = "28.1,120.1";
//		li.ActivityLogo = "gerg";
//		li.ActivityMaxMemberNumber = "50";
//		li.ActivityMemberNumber = "1";
//		li.ActivityName = "SB";
//		li.ActivityOpinion = "4 5";
//		li.ActivityStartTime = "201411053012";
//		li.ActivityTags = "hdfh dg d";
//		li.HelpPhone = "13007420476";
//		li.IsDirectJoinIn = "1";
//
//		ArrayList<Object> list = new ArrayList<Object>();
//		list.add(li);
//
//		return g.toJson(list);
//	}
//
//	public String Test_addActivityDiscuss_C() {
//		// TODO Auto-generated method stub
//
//		addActivityDiscuss_C li = new addActivityDiscuss_C();
//		li.setActivityID("1");
//		li.setUserID("13007420476");
//		li.setStandardUploadTime();
//		/**
//		 * 不能太长
//		 */
//		li.ActivityName = "SB";
//		li.UserName = "侯斌"; // 中文无法存入
//		li.DiscussContent = "FUCK BITCH";
//		li.Photo = "ghdfh\\ggd";
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_addActivitySpecificItem_C() {
//		// TODO Auto-generated method stub
//		addActivitySpecificItem_C li = new addActivitySpecificItem_C();
//		li.setActivityID("1");
//		li.setStandardUploadTime();
//		/**
//		 * 不能太长
//		 */
//		li.ActivityItemHoldPlace = "454551,1518446";
//		li.ActivityItemHoldTime = "2146813";
//		li.ActivitySpecificItemDescribe = "fyjyg jdjdf jfgh  fgj ";
//		li.ActivitySpecificItemPhoto = "hgufdh i";
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_addPersonalInfo_C() {
//		// TODO Auto-generated method stub
//		addPersonalInfo_C li = new addPersonalInfo_C();
//		li.setUserID("13007420476");
//		li.setThisUserID("13848832588");
//		li.setStandardUploadTime();
//		/**
//		 * 不能太长
//		 */
//		li.UserName = "31re y23";
//		li.Content = "dfhseh";
//		li.Photo = "rwegfvs";
//		li.Place = " sdrryhaeryh";
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_addRelation_C() {
//		// TODO Auto-generated method stub
//		addRelation_C li = new addRelation_C();
//		li.setActivityID("1");
//		li.setUserID("13007420476");
//		li.setStandardUploadTime();
//		li.setStatus("2");
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_addUser_C() {
//		// TODO Auto-generated method stub
//		addUser_C li = new addUser_C();
//		li.setUserID("15802616881");
//		li.setStandardUploadTime();
//		li.Code = "hbhb123";
//		li.UserName = "王八蛋";
//		li.UserPhone = "15802616794";
//		// li.UserJoinActivity = "0";
//		// li.UserAttentionClass = "0";
//		li.UserDescribe = "Just an SB";
//		li.UserLogo = "fdheau hdf";
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_changActivityInfo_C() {
//		// TODO Auto-generated method stub
//		changActivityInfo_C li = new changActivityInfo_C();
//		li.setUserID("13007420476");
//		li.setActivityID("1");
//		li.setActivityManagerID("13848832588");
//		li.setStandardUploadTime();
//		li.IsDirectJoinIn = "2";
//		li.ActivityName = "FuCk ThE wOrLd";
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_changRelation_C() {
//		// TODO Auto-generated method stub
//		changRelation_C li = new changRelation_C();
//		li.setUserID("13027426407");
//		li.setRelationID("2");
//		li.setActivityID("1");
//		li.setUserID("13027426407");
//		li.setStandardUploadTime();
//		li.setStatus("1");
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_changUserInfo_C() {
//		// TODO Auto-generated method stub
//		changUserInfo_C li = new changUserInfo_C();
//		li.setUserID("13007421234");
//		li.setStandardUploadTime();
//		li.Code = "hbhb123456";
//		li.UserClass = "Play,SEE";
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_deleteActivityDiscuss_C() {
//		// TODO Auto-generated method stub
//		deleteActivityDiscuss_C li = new deleteActivityDiscuss_C();
//		li.setDiscussID("1");
//		li.setUserID("13007421234");
//		li.setActivityID("1");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_deleteActivityInfo_C() {
//		// TODO Auto-generated method stub
//		deleteActivityInfo_C li = new deleteActivityInfo_C();
//		li.setUserID("13007421234");
//		li.setActivityID("1");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_deleteActivitySpecific_C() {
//		// TODO Auto-generated method stub
//		deleteActivitySpecific_C li = new deleteActivitySpecific_C();
//		// li.setUserID("13007420476");
//		li.setUserID("13848832588");
//		li.setActivityID("1");
//		li.setActivitySpecificItemsID("1");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_deletePersonalInfo_C() {
//		// TODO Auto-generated method stub
//		deletePersonalInfo_C li = new deletePersonalInfo_C();
//		li.setUserDiscussID("1");
//		li.setUserID("13007420476");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_deleteRelation_C() {
//		// TODO Auto-generated method stub
//		deleteRelation_C li = new deleteRelation_C();
//		li.setActivityID("2");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_deleteUserInfo_C() {
//		// TODO Auto-generated method stub
//		deleteUserInfo_C li = new deleteUserInfo_C();
//		li.setUserID("15273131134");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_getActivityInfo_C() {
//		// TODO Auto-generated method stub
//		getActivityInfo_C li = new getActivityInfo_C();
//		// li.setActivityID("2");
//		li.ActivityName = "fuck";
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_getPersonalInfo_C() {
//		// TODO Auto-generated method stub
//		getPersonalInfo_C li = new getPersonalInfo_C();
//		// li.setActivityID("2");
//		li.setUserDiscussID("1");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_getUserInfo_C() {
//		// TODO Auto-generated method stub
//		getUserInfo_C li = new getUserInfo_C();
//		// li.setUserID("13007420476");
//		li.UserName = "FUCK";
//
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_getRelaion_C() {
//		// TODO Auto-generated method stub
//		getRelaion_C li = new getRelaion_C();
//
//		li.setActivityID("1");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_getActivityDiscussList_C() {
//		// TODO Auto-generated method stub
//		getActivityDiscussList_C li = new getActivityDiscussList_C();
//
//		li.setActivityID("2");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_getActivitySpecificItemList_C() {
//		// TODO Auto-generated method stub
//		getActivitySpecificItemList_C li = new getActivitySpecificItemList_C();
//
//		li.setActivityID("1");
//		li.setStandardUploadTime();
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String Test_loginUser_C() {
//		// TODO Auto-generated method stub
//		loginUser_C li = new loginUser_C();
//
//		li.setUserID("13007420476");
//		li.setStandardUploadTime();
//		li.setCode("hbhb123456");
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(li);
//		return g.toJson(list);
//	}
//
//	public String test_addUserFeedback() {
//		// TODO Auto-generated method stub
//		addUserFeedback_C ac = new addUserFeedback_C();
//
//		ac.setUserID("1");
//		ac.setConnect("13007420476");
//		ac.setFeedback("界面真TM丑");
//		ac.setUploadTime(FormatTime.getFormatTime());
//		ac.setPhoto("13007420476");
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(ac);
//		String G = g.toJson(list);
//		return G;
//	}
//
//	public String Test_REQ_Heart_Beat() {
//		// TODO Auto-generated method stub
//		REQ_Heart_Beat rhb = new REQ_Heart_Beat();
//
//		rhb.setUserID("13007420476");
//		rhb.setUploadTime(FormatTime.getFormatTime());
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(rhb);
//		String G = g.toJson(list);
//		return G;
//	}
//
//	public String Test_EventStatistics() {
//		// TODO Auto-generated method stub
//		EventStatistics es = new EventStatistics();
//
//		es.setUserID("13007420476");
//		es.setComponent("Button1");
//		es.setContent("用户点击了登陆按钮");
//		es.setEvent("OnClick");
//		es.setEventMark("1");
//		es.setUploadTime("201408201200000");
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(es);
//		String G = g.toJson(list);
//		return G;
//	}
//
//	public String Test_CheckVersionAndOtherInfo_C() {
//		// TODO Auto-generated method stub
//		CheckVersionAndOtherInfo_C cc = new CheckVersionAndOtherInfo_C();
//
//		cc.setUserID("13007420476");
//		cc.setUploadTime("201408201200000");
//		cc.setMacAddress("AC:23:S8:5R");
//		cc.setUUID("QWEJ43284JG489GH");
//		cc.setVersion("0");
//
//		ArrayList<Object> list = new ArrayList<Object>();
//
//		list.add(cc);
//		String G = g.toJson(list);
//		return G;
//	}
//	public String Test_CheckVersionAndOtherInfo_C() {
//	// TODO Auto-generated method stub
//	CheckVersionAndOtherInfo_C cc = new CheckVersionAndOtherInfo_C();
//
//	cc.setUserID("13007420476");
//	cc.setUploadTime("201408201200000");
//	cc.setMacAddress("AC:23:S8:5R");
//	cc.setUUID("QWEJ43284JG489GH");
//	cc.setVersion("0");
//
//	ArrayList<Object> list = new ArrayList<Object>();
//
//	list.add(cc);
//	String G = g.toJson(list);
//	return G;
//}
}