//package handlemethod;
//
//import java.net.SocketAddress;
//import java.util.ArrayList;
//import java.util.Queue;
//
//import protocol.infoclass.h_protocolfromclient1;
//import protocol.pushservice.h_protocol_pusher;
//import maintest.KeyObject;
//import maintest.ReadObject;
//import maintest.WriteObject;
//import maintest.server_version_2;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import com.google.gson.Gson;
//
//public class CopyOfhandle8_25 implements Runnable {
//
//	
//	public SocketAddress receivesocketaddress;
//	// 从receivedQueue中取出的String
//	String receiveString;
//	// 直接获取发送队列
//	Queue<WriteObject> sendQueue;
//
//	// 发送的消息
//	String sendString;
//	// 转换Json格式
//	public static Gson g = new Gson();
//	// 对象列表
//
//	WriteObject wo;
//
//	HandleCommand abc;
//
//
//
//	String timeStamp;
//
//	public CopyOfhandle8_25(ReadObject ro, Queue<WriteObject> qwo,
//			HandleCommand handleCenter) {
//		this.receivesocketaddress = ro.getSocketaddress();
//		this.receiveString = ro.getReceiveString();
//		this.sendQueue = qwo;
//		this.abc = handleCenter;
//	}
//
//	@Override
//	public void run() {
//		// 8-20 从类成员中剥离到这边
//		ArrayList<Object> objlist = new ArrayList<Object>();
//		// 8-20 从类成员中剥离到这边
//		KeyObject sa = new KeyObject();
//
//		System.out.println("receivesocketaddress: " + receivesocketaddress + "| receivestring: " + receiveString);
//
//		// 解析JSON列表
//		JSONArray objList = JSONArray.fromObject(receiveString);
//
//		// 得到对象数目
//		int size = objList.size();
//
//		for (int i = 0; i < size; i++) {
//			JSONObject obj = objList.getJSONObject(i);
//			int p = obj.getInt("p");
//
//			if ((p != h_protocol_pusher.REQ_Heart_Beat) && (p != h_protocol_pusher.ACK)) { // 独立处理
//				// 放入表,主要防止重复信息重复处理
//				timeStamp = obj.getString("UploadTime");
//				if (server_version_2.repeatMessageMap.containsKey(timeStamp)) {
//					sa = server_version_2.repeatMessageMap.get(timeStamp);
//
//					if (sa.receivesocketaddress.equals(receivesocketaddress)) { // 重复,查表重发
//						System.out.println("message repeat!");
//						wo = new WriteObject(sa.receivesocketaddress,
//								sa.sendMessage);
//						// 处理与判定
//						sendQueue.offer(wo);
//						continue; // repeat
//						
////						return;
//					} else { // ip数据不重复,只是相同的时间戳(还无法解决)
//						// continue,客户端是否会更换
//						sa.receivesocketaddress = receivesocketaddress;
//						server_version_2.repeatMessageMap.put(timeStamp, sa);
//					}
//				} else { // 数据不重复,
//					sa.receivesocketaddress = receivesocketaddress;
//					// server_version_2.repeatMessageMap.put(timeStamp,sa);
//				}
//			}
//
//			try {
//				switch (p) {
//				/**
//				 * 这里是侯斌的协议
//				 */
//				case h_protocolfromclient1.addActivity_C:
//					abc.h.addActivity_C_Deal(obj, objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.addActivityDiscuss_C:
//					abc.h.addActivityDiscuss_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.addActivitySpecificItem_C:
//					abc.h.addActivitySpecificItem_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.addPersonalInfo_C:
//					abc.h.addPersonalInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.addRelation_C:
//					abc.h.addRelation_C_Deal(obj, objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.addUser_C:
//					abc.h.addUser_C_Deal(obj, objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.changActivityInfo_C:
//					abc.h.changActivityInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.changRelation_C:
//					abc.h.changRelation_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.changUserInfo_C:
//					abc.h.changUserInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.deleteActivityDiscuss_C:
//					abc.h.deleteActivityDiscuss_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.deleteActivityInfo_C:
//					abc.h.deleteActivityInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.deleteActivitySpecific_C:
//					abc.h.deleteActivitySpecific_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.deletePersonalInfo_C:
//					abc.h.deletePersonalInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.deleteRelation_C:
//					abc.h.deleteRelation_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.deleteUserInfo_C:
//					abc.h.deleteUserInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.getActivityInfo_C:
//					abc.h.getActivityInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.getPersonalInfo_C:
//					abc.h.getPersonalInfo_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.getUserInfo_C:
//					abc.h.getUserInfo_C_Deal(obj, objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.getRelaion_C:
//					abc.h.getRelation_C_Deal(obj, objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.getActivityDiscussList_C:
//					abc.h.getActivityDiscussList_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.getActivitySpecificItemList_C:
//					abc.h.getActivitySpecificItemList_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.loginUser_C:
//					abc.h.loginUser_C_Deal(obj, objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.updateActivityOpinion_independent_C:
//					abc.h.updateActivityOpinion_independent_C_Deal(obj,
//							objlist, receivesocketaddress);
//					break;
//				case h_protocolfromclient1.addUserFeedback_C:
//					abc.h.addUserFeedback_C_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.CheckVersionAndOtherInfo_C:
//					abc.h.CheckVersionAndOtherInfo_Deal(obj, objlist,
//							receivesocketaddress);
//					break;
//				case h_protocolfromclient1.changeCode_C:
//					abc.h.changeCode_C_Deal(obj, objlist,receivesocketaddress);
//					break;
//					
//				case h_protocolfromclient1.EventStatistics:
//					abc.h.EventStatistics_Deal(obj, objlist,
//							receivesocketaddress);
//					return;
//
//				case h_protocol_pusher.REQ_Heart_Beat:
//					abc.hPusher.REQ_Heart_Beat_Deal(obj, objlist,
//							receivesocketaddress);
//					return;
//				case h_protocol_pusher.ACK:
//					abc.hPusher.ACK_Deal(obj, objlist, receivesocketaddress);
//					return;
//
//					/**
//					 * 其他
//					 */
//				default:
//					System.out.println("unknown packet！");
//
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		// if(p == h_protocol_pusher.REQ_Heart_Beat && p ==
//		// h_protocol_pusher.ACK) {
//		// return;
//		// }
//		if (objlist.isEmpty()) {
//			return;
//		}
//
//		sendString = g.toJson(objlist);
//
//		// 存储,防止重复
//		sa.sendMessage = sendString;
//		server_version_2.repeatMessageMap.put(timeStamp, sa);
//
//		wo = new WriteObject(receivesocketaddress, sendString);
//		// 处理与判定
//		sendQueue.offer(wo);
//		// key在下一次循环中被select选中！
//
//	}
//}
