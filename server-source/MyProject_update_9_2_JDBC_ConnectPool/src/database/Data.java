package database;


/**
 * 各种需要保存、运用的数据
 * @author Administrator
 *
 */
public class Data {
	int id;
	String accountNo;
	String passWord;
	String nickName;
	int sex;
	int age;
	String ipAddress;
	String portNo;
	
	int logNO;
	int senderId;
	int receiverId;
	String messageBody;
	String sendState;
	String sendTime;
	
	
	
//	public static void   main(String args[])
//	{
//		Data data=new Data();
//	    System.out.println(data.accountNo);
//	}
//	
	
	/**
	 * 无参构造方法
	 */
	public Data()
	{
		this.accountNo="love";
		this.passWord="123456yy";
		this.nickName="柯南";
		this.sex=0;
		this.age=10;
		this.ipAddress="192.168.0.108";
		this.portNo="8090";
		this.id=1;
		
		this.logNO=1;
		this.senderId=1;
		this.receiverId=1;
		this.messageBody="饿屎咧(⊙o⊙)…";
		this.sendState="发送成功！";
		this.sendTime="2013-10-01 12:00:00";
				
	}
	
    //*********************设置属性的值的方法*******************************
	public void setId(int id)
	{
		this.id=id;
	}
	
	
	public void setAccountNo(String accountNo)
	{
		this.accountNo=accountNo;
		//this.accountNo="love";
	}
	public void setPassWord(String passWord)
	{
		this.passWord=passWord;
		//this.passWord="123456yy";
	}
	
	public void setNickName(String nickName)
	{
		this.nickName=nickName;
		//this.nickName="柯南";
	}
	
	public void setSex(int sex)
	{
		this.sex=sex;
		//this.sex="Y";
	}
	public void setAge(int age)
	{
		this.age=age;
		//this.age=10;
	}
	
	
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress=ipAddress;
		//this.ipAddress="192.168.0.108";
	}
	public void setPortNo(String portNo)
	{
		this.portNo=portNo;
		//this.portNo="8090";
	}
	
	public void setLogNo(int logNO)
	{
		this.logNO=logNO;
	}
	
	public void setSenderId(int senderId)
	{
		this.senderId=senderId;
	}
	
	public void setReceiverId(int receiverId)
	{
		this.receiverId= receiverId;
	}
	
	public void setMessageBody(String messageBody)
	{
		this.messageBody=messageBody;
	}
	
	public void setSendState(String sendState)
	{
		this.sendState=sendState;
	}
	
	public void setSendTime(String sendTime)
	{
		this.sendTime=sendTime;
	}
	
	 //*********************获取属性的值的方法*******************************
	public int getId()
	{
		return id;
	}
	
	public String getAccountNo()
	
	{
		//System.out.println(this.accountNo);
		return accountNo;
	}
	public String getPassWord()
	{
		return passWord;
	}
	
	public String getNickName()
	{
		return nickName;
	}
	public int getSex()
	{
		return sex;
	}
	public int getAge()
	{
		return age;
	}
	public String getIpAddress()
	{
		return ipAddress;
	}
	public String getPortNo()
	{
		return portNo;
	}
	
	
	public int getLogNo()
	{
		return logNO;
	}
	
	public int getSenderId()
	{
		return senderId;
	}
	
	public int getReceiverId()
	{
		return receiverId;
	}
	
	public String getMessageBody()
	{
		return messageBody;
	}
	
	public String getSendState()
	{
		return sendState;
	}
	
	public String getSendTime()
	{
		return sendTime;
	}
	

}
