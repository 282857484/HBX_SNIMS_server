package maintest;
//功能检测方法（鲁）
/**
 * 
 * @author 鲁开文
 *
 */
public class functionTemp {

	/**
	 * 判断用户名是否合法
	 * 中文在夏宇天可以处理
	 * 如果用户名中含有除26个英文字母（大小写均可）和下划线以外的字符，则判定用户名不合法
	 * @param userName ：用户名
	 * @return true : 不合法          false:  合法
	 */
	public boolean userName_Illegal(final String userName){
		String name = userName;
		for(int i = 0; i< name.length(); i++ ){
			if(!(name.charAt(i) - 'a' > 0 || name.charAt(i) - 'a' <25
					|| name.charAt(i) - 'A' > 0 || name.charAt(i) - 'A' <25 
					|| name.charAt(i) == '_')){
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * 判断密码（长度）是否合法
	 * 在夏宇天那边判定密码复杂度
	 * 长度在在[6,18]的合法，否则非法
	 * @param key :用户密码
	 * @return  true : 不合法          false:  合法
	 */
	public static boolean key_Illegal(final String key){
		int key_numbers = key.length();
		if(key_numbers > 6 && key_numbers < 18)
			return false;
		return true;
	}
	
	
	 /**
	  * 判断注册是否重复，成功返回ID
	  * @param countNo 用户名（帐号）
	  * @return
	  */
	public static int judgeRegister(final String countNo){
		return -1;
	}
	
	/**
	 * 需要改成传入对象
	 * @param countNo
	 * @param passWord
	 * @return
	 */
	public static boolean judgeLogin(final String countNo,final String passWord){
		return false;
	}
}
