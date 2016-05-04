package tool;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExcpetionWriter {

	public void WriteExceptionToDB(Exception e) {
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
	}
}
