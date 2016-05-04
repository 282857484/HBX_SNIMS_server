package handlemethod;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Queue;

import protocol.infoclass.h_protocolfromclient1;
import protocol.pushservice.h_protocol_pusher;
import maintest.KeyObject;
import maintest.ReadObject;
import maintest.WriteObject;
import maintest.server_version_2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;

public class handle implements Runnable {

	// 转换Json格式
	public static Gson g = new Gson();
		
	public SocketAddress receivesocketaddress;
	// 从receivedQueue中取出的String
	String receiveString;
	// 直接获取发送队列
	Queue<WriteObject> sendQueue;

	// 发送的消息
	String sendString;
	
	// 对象列表

	WriteObject wo;

	HandleCommand abc;

	String timeStamp;

	public handle(ReadObject ro, Queue<WriteObject> qwo,
			HandleCommand handleCenter) {
		this.receivesocketaddress = ro.getSocketaddress();
		this.receiveString = ro.getReceiveString();
		this.sendQueue = qwo;
		this.abc = handleCenter;
	}

	@Override
	public void run() {
		// 8-20 从类成员中剥离到这边
		ArrayList<Object> objlist = new ArrayList<Object>();
		// 8-20 从类成员中剥离到这边
		KeyObject sa = new KeyObject();

		System.out.println("receivesocketaddress: " + receivesocketaddress
				+ "| receivestring: " + receiveString);
		if(!checkLegitimacy(receiveString)) {
			System.out.println("ReceiveString not available");
			return;
		}

		// 解析JSON列表
		JSONArray objList = JSONArray.fromObject(receiveString);

		// 得到对象数目
		int size = objList.size();

		for (int i = 0; i < size; i++) {
			JSONObject obj = objList.getJSONObject(i);
			int p = obj.getInt("p");

			if ((p != h_protocol_pusher.REQ_Heart_Beat)
					&& (p != h_protocol_pusher.ACK)) { // 独立处理
				// 放入表,主要防止重复信息重复处理
				timeStamp = obj.getString("UploadTime");
				if (server_version_2.repeatMessageMap.containsKey(timeStamp)) { // 可能有重复信息
					sa = server_version_2.repeatMessageMap.get(timeStamp);

					if (sa.receivesocketaddress.equals(receivesocketaddress)) { // 重复,查表重发
						System.out.println("message repeat!");
						wo = new WriteObject(sa.receivesocketaddress,
								sa.sendMessage);
						// 处理与判定
						sendQueue.offer(wo);
						return;
					} else { // ip数据不重复,只是相同的时间戳(还未解决)
						// continue,客户端是否会更换
						sa.receivesocketaddress = receivesocketaddress;
						server_version_2.repeatMessageMap.put(timeStamp, sa);
					}
				} else { // 数据不重复,
					sa.receivesocketaddress = receivesocketaddress;
					// server_version_2.repeatMessageMap.put(timeStamp,sa);
				}
			}

			try {
				switch (p) {
				/**
				 * 这里是侯斌的协议
				 */
				case h_protocolfromclient1.addActivity_C:
					H_Founction.addActivity_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.addActivityDiscuss_C:
					H_Founction.addActivityDiscuss_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.addActivitySpecificItem_C:
					H_Founction.addActivitySpecificItem_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.addPersonalInfo_C:
					H_Founction.addPersonalInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.addRelation_C:
					H_Founction.addRelation_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.addUser_C:
					H_Founction.addUser_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.changActivityInfo_C:
					H_Founction.changActivityInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.changRelation_C:
					H_Founction.changRelation_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.changUserInfo_C:
					H_Founction.changUserInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.deleteActivityDiscuss_C:
					H_Founction.deleteActivityDiscuss_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.deleteActivityInfo_C:
					H_Founction.deleteActivityInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.deleteActivitySpecific_C:
					H_Founction.deleteActivitySpecific_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.deletePersonalInfo_C:
					H_Founction.deletePersonalInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.deleteRelation_C:
					H_Founction.deleteRelation_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.deleteUserInfo_C:
					H_Founction.deleteUserInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.getActivityInfo_C:
					H_Founction.getActivityInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.getPersonalInfo_C:
					H_Founction.getPersonalInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.getUserInfo_C:
					H_Founction.getUserInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.getRelaion_C:
					H_Founction.getRelation_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.getActivityDiscussList_C:
					H_Founction.getActivityDiscussList_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.getActivitySpecificItemList_C:
					H_Founction.getActivitySpecificItemList_C_Deal(obj,
							objlist, receivesocketaddress);
					break;
				case h_protocolfromclient1.loginUser_C:
					H_Founction.loginUser_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.updateActivityOpinion_independent_C:
					H_Founction.updateActivityOpinion_independent_C_Deal(obj,
							objlist, receivesocketaddress);
					break;
				case h_protocolfromclient1.addUserFeedback_C:
					H_Founction.addUserFeedback_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.CheckVersionAndOtherInfo_C:
					H_Founction.CheckVersionAndOtherInfo_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.changeCode_C:
					H_Founction.changeCode_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.userPushInfo_C:
					H_Founction.userPushInfo_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_protocolfromclient1.changFileNameOrder_C:
					H_Founction.changFileNameOrder_C_Deal(obj, objlist,
							receivesocketaddress);
					break;

				case h_protocolfromclient1.EventStatistics:
					H_Founction.EventStatistics_Deal(obj, objlist,
							receivesocketaddress);
					return;

				case h_protocol_pusher.REQ_Heart_Beat:
					H_Founction_Pusher.REQ_Heart_Beat_Deal(obj, objlist,
							receivesocketaddress);
					return;
				case h_protocol_pusher.ACK:
					H_Founction_Pusher.ACK_Deal(obj, objlist, receivesocketaddress);
					return;

					/**
					 * 其他
					 */
				default:
					System.out.println("unknown packet！");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// if(p == h_protocol_pusher.REQ_Heart_Beat && p ==
		// h_protocol_pusher.ACK) {
		// return;
		// }
		if (objlist.isEmpty()) {
			return;
		}

		sendString = g.toJson(objlist);

		// 存储,防止重复
		sa.sendMessage = sendString;
		server_version_2.repeatMessageMap.put(timeStamp, sa);

		wo = new WriteObject(receivesocketaddress, sendString);
		// 处理与判定
		sendQueue.offer(wo);
		// key在下一次循环中被select选中！

	}

	//
	private static boolean checkLegitimacy(String receiveString) {
		// TODO Auto-generated method stub
		if(receiveString.length() < 20) {
			return false;
		}
		if((!receiveString.contains("UserID")) && (!receiveString.contains("UploadTime"))) {
			return false;
		}
		if(receiveString.contains("\"UploadTime\":\"0\"")) {
			return false;
		}
		
		return true;
	}
}
