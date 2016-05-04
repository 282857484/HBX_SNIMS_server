package protocol.infoclass;

/**
 * 要求重写默认的toString()
 * @author Administrator
 *
 */
public abstract class HaveToHave {

	// 标准 toString
	public String BESTR(){
//		System.out.println(toString());
		return toString();
	}
	/**
	 * @X 发送字符不合法返回true
	 * @return
	 */
	public boolean isHaveTOHaveEmpty() {
		String myClassMemberStr = BESTR();
		if(myClassMemberStr.contains("=,")){
			System.out.println(myClassMemberStr);
			return true;
		} else {
			return false;
		}

//		return myClassMemberStr.contains("=,");
	}
	
	@Override
	abstract public String toString() ;
}
