package handlemethod;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;

import maintest.WriteObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import protocol.infoclass.h_protocolfromclient1;
import protocol.pushservice.h_protocol_pusher;

public class handle_TCP implements Runnable {

	private SelectionKey key;
	public final String ERRORECHO = "[{\"UploadTime\":\"999999999999999\",\"UserID\":\"0\",\"p\":-1}]"; // 服务器内部错误
	
	// 转换Json格式
	public static Gson g = new Gson();

	public handle_TCP(SelectionKey key) {
		this.key = key;
		key.cancel();
	}

	public void run() {
		SocketAddress receivesocketaddress = null;
		String outMessage = "[{\"UploadTime\":\"999999999999999\",\"UserID\":\"0\",\"p\":-2}]"; // 非法数据
		// TODO Auto-generated method stub
		ArrayList<Object> objlist = new ArrayList<Object>(1);

		// 分配一个新的字节缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(Config.BufferSize);
		SocketChannel sc = (SocketChannel) key.channel();
//		try {
//			receivesocketaddress = sc.;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int readBytes = 0;
		String receiveString = null;
		try {
			int ret;
			try {
				if ((ret = sc.read(buffer)) > 0) {
					readBytes += ret;
				}

			} catch (Exception e) {
				readBytes = 0;
				// ignore
			} finally {
				// 反转此缓冲区。首先对当前位置设置限制，然后将该位置设置为零
				buffer.flip();
			}

			if (readBytes > 0) {
				receiveString = Charset.forName("UTF-8").decode(buffer).toString()
						.trim();
//				buffer.clear();
//				buffer.
				buffer = null;
			}
		} finally {
			if (buffer != null)
				buffer.clear();
		}

		if (readBytes > 0) {
			System.out.println("message from client:" + receiveString);
			if(!checkLegitimacy(receiveString)) {
				System.out.println("ReceiveString not available");
				try {
					sc.write(Charset.forName("UTF-8").encode(outMessage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					try {
						sc.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				
				return;
			}
			
			// 解析JSON列表
			JSONArray objList = JSONArray.fromObject(receiveString);

			// 得到对象数目
			int size = objList.size();

			for (int i = 0; i < size; i++) {
				JSONObject obj = objList.getJSONObject(i);
				int p = obj.getInt("p");

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

						// 9-16
					case h_protocolfromclient1.EventStatistics:
						H_Founction.EventStatistics_Deal(obj, objlist,
								receivesocketaddress);
						break;
					case h_protocol_pusher.REQ_Heart_Beat:
						H_Founction_Pusher.REQ_Heart_Beat_Deal(obj, objlist,
								receivesocketaddress);
						break;
					case h_protocol_pusher.ACK:
						H_Founction_Pusher.ACK_Deal(obj, objlist, receivesocketaddress);
						break;

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
				try {
					sc.write(Charset.forName("UTF-8").encode(outMessage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						sc.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return;
			}
			outMessage = g.toJson(objlist) + "FUCKBITCHEVERYDAY";
			
			
			
//			String outMessage = "server response:" + message;
			try {
				ByteBuffer bufferS = Charset.forName("UTF-8").encode(outMessage);
//				System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
				sc.write(bufferS);
//				sc.write(bufferS, 0, bufferS.array().length);
				
				if(bufferS.hasRemaining()) {
//					System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					sc.write(bufferS);
				} else {
					try {
						sc.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("SENDING STRING: " + new String(outMessage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					sc.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} 
	}
	
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
