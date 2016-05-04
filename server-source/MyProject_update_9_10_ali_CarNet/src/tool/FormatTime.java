package tool;


import java.util.Calendar;


public class FormatTime {

	/**
	 * string的长度为15位 可以转为long 不可转为int
	 * 
	 * @return
	 */
	public static String getFormatTime() {
		Calendar c = Calendar.getInstance();
		StringBuilder formatTime = new StringBuilder();
		formatTime.append(c.get(Calendar.YEAR));
		int month = c.get(Calendar.MONTH) + 1;
		if (month < 10)
			formatTime.append("0" + String.valueOf(month));
		else
			formatTime.append(String.valueOf(month));
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10)
			formatTime.append("0" + String.valueOf(day));
		else
			formatTime.append(String.valueOf(day));
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if (hour < 10)
			formatTime.append("0" + String.valueOf(hour));
		else
			formatTime.append(String.valueOf(hour));
		int minute = c.get(Calendar.MINUTE);
		if (minute < 10)
			formatTime.append("0" + String.valueOf(minute));
		else
			formatTime.append(String.valueOf(minute));
		int second = c.get(Calendar.MILLISECOND);
		if (second < 10) {
			formatTime.append("00" + String.valueOf(second));
		} else if ((second > 10) && (second < 100)) {
			formatTime.append("0" + String.valueOf(second));
		} else {
			formatTime.append(String.valueOf(second));
		}
		return formatTime.toString();
	}
}
