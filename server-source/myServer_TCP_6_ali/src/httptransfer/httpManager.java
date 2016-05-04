package httptransfer;



import handlemethod.BugLevel;
import handlemethod.H_Founction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import net.sf.json.JSONObject;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * HTTP发送方法
 * 
 * @author 侯斌
 * 
 */
public class httpManager {
	static Logger logger = Logger.getLogger(httpManager.class.getName());

	/**
	 * 暂时 发送POST请求 需要修改返回值
	 */
	HttpClient httpClient = new DefaultHttpClient();
	String strResult = "doPostError";
	HttpResponse httpResponse = null;
	int retrytime = 3;

	public HttpResponse sendMessageToBDStore(creatPoi creatPoi, int what) {
		HttpClientParams.setCookiePolicy(httpClient.getParams(),
				CookiePolicy.BROWSER_COMPATIBILITY);
		String url = creatPoi.getUrl();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		HttpPost httpRequest = new HttpPost(url);

		// 设置数据
		params.add(new BasicNameValuePair("ak", creatPoi.getAk()));
		params.add(new BasicNameValuePair("address", creatPoi.getAddress()));
		params.add(new BasicNameValuePair("geotable_id", creatPoi
				.getGeotable_id()));
		params.add(new BasicNameValuePair("sn", creatPoi.getSn()));
//		params.add(new BasicNameValuePair("tags", creatPoi.getTags()));
		params.add(new BasicNameValuePair("title", creatPoi.getTitle()));
		params.add(new BasicNameValuePair("coord_type", String.valueOf(creatPoi
				.getCoord_type())));
		params.add(new BasicNameValuePair("latitude", String.valueOf(creatPoi
				.getLatitude())));
		params.add(new BasicNameValuePair("longitude", String.valueOf(creatPoi
				.getLongitude())));

		Iterator<Entry<String, Object>> iter = creatPoi.getColumnkey()
				.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) iter
					.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			if (val == null) {
			} else {
				params.add(new BasicNameValuePair(key, val.toString()));
			}
		}

		System.out.println("params : " + params.toString());

		while (retrytime >= 0) {

			try {
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
				httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					strResult = EntityUtils.toString(httpResponse.getEntity(),
							"UTF-8");
					String result = changeUnicodToUTF8.decodeUnicode(strResult)
							.toString();

					JSONObject json = new JSONObject(result);
					int status = json.getInt("status");
					String message = json.getString("message");
					// String id = json.getString("id");
					if(status != 0) {
						logger.error(result);
					}

					HTTPResponceInfo info = new HTTPResponceInfo();
					info.setStatus(status);
					info.setMessage(message);
					// info.setId(id);
					
					System.out.println("HTTPResponceInfo : " + result);

					retrytime = 0;

				} else {
					H_Founction.TraceError(BugLevel.USERERROR, params.toString(),
							httpResponse.toString(), "", "HTTP");
					strResult = "Error Response:"
							+ httpResponse.getStatusLine().toString();
					logger.error("strResult:  " + strResult + "  STATUS :"
							+ httpResponse.getStatusLine().getStatusCode());
					

				}
			} catch (ClientProtocolException e) {
				strResult = e.getMessage().toString();
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
				

			} catch (IOException e) {
				strResult = e.getMessage().toString();
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));

			} catch (Exception e) {
				System.out.println("FUCK U");      
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
			} finally {
				retrytime--;
			}
			
		}

		return httpResponse;

	}

	public HttpResponse sendMessageToBDStore(deletePoi deletePoi) {
		HttpClientParams.setCookiePolicy(httpClient.getParams(),
				CookiePolicy.BROWSER_COMPATIBILITY);
		String url = deletePoi.getUrl();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		HttpPost httpRequest = new HttpPost(url);

		// 设置数据
		params.add(new BasicNameValuePair("ak", deletePoi.getAk()));
		params.add(new BasicNameValuePair("bounds", deletePoi.getBounds()));
		params.add(new BasicNameValuePair("geotable_id", deletePoi
				.getGeotable_id()));
		params.add(new BasicNameValuePair("id", deletePoi.getId()));
		params.add(new BasicNameValuePair("sn", deletePoi.getSn()));
		params.add(new BasicNameValuePair("tags", deletePoi.getTags()));
		params.add(new BasicNameValuePair("title", deletePoi.getTitle()));

		Iterator<Entry<String, Object>> iter = deletePoi.getColumnkey()
				.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) iter
					.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			params.add(new BasicNameValuePair(key, val.toString()));
		}

		while (retrytime >= 0) {

			try {
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
				httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					strResult = EntityUtils.toString(httpResponse.getEntity(),
							"UTF-8");
					String result = changeUnicodToUTF8.decodeUnicode(strResult)
							.toString();

					JSONObject json = new JSONObject(result);
					int status = json.getInt("status");
					String message = json.getString("message");
					// String id = json.getString("id");
					if(status != 0) {
						logger.error(result);
					}

					HTTPResponceInfo info = new HTTPResponceInfo();
					info.setStatus(status);
					info.setMessage(message);
					// info.setId(id);

					retrytime = 0;

				} else {
					H_Founction.TraceError(BugLevel.USERERROR, params.toString(),
							httpResponse.toString(), "", "HTTP");
					strResult = "Error Response:"
							+ httpResponse.getStatusLine().toString();
					logger.error("strResult:  " + strResult + "  STATUS :"
							+ httpResponse.getStatusLine().getStatusCode());
					
					
				}
			} catch (ClientProtocolException e) {
				strResult = e.getMessage().toString();
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));

			} catch (IOException e) {
				strResult = e.getMessage().toString();
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));

			} catch (Exception e) {
				System.out.println("FUCK U");
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
			}
			retrytime--;
		}

		return httpResponse;

	}

	public HttpResponse sendMessageToBDStore(updatePoi updatePoi) {
		HttpClientParams.setCookiePolicy(httpClient.getParams(),
				CookiePolicy.BROWSER_COMPATIBILITY);
		String url = updatePoi.getUrl();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		HttpPost httpRequest = new HttpPost(url);

		// 设置数据
		params.add(new BasicNameValuePair("ak", updatePoi.getAk()));
//		params.add(new BasicNameValuePair("address", updatePoi.getAddress()));
		params.add(new BasicNameValuePair("geotable_id", updatePoi
				.getGeotable_id()));
//		params.add(new BasicNameValuePair("sn", updatePoi.getSn()));
//		params.add(new BasicNameValuePair("tags", updatePoi.getTags()));
//		params.add(new BasicNameValuePair("title", updatePoi.getTitle()));
		params.add(new BasicNameValuePair("coord_type", String
				.valueOf(updatePoi.getCoord_type())));
//		params.add(new BasicNameValuePair("latitude", String.valueOf(updatePoi
//				.getLatitude())));
//		params.add(new BasicNameValuePair("longitude", String.valueOf(updatePoi
//				.getLongitude())));

		Iterator<Entry<String, Object>> iter = updatePoi.getColumnkey()
				.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) iter
					.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			params.add(new BasicNameValuePair(key.toString(), val.toString()));
		}
		
		System.out.println("params: "+params);

		while (retrytime >= 0) {

			try {
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
				httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					strResult = EntityUtils.toString(httpResponse.getEntity(),
							"UTF-8");
					String result = changeUnicodToUTF8.decodeUnicode(strResult)
							.toString();

					JSONObject json = new JSONObject(result);
					int status = json.getInt("status");
					String message = json.getString("message");
					// String id = json.getString("id");
					if(status != 0) {
						logger.error(result);
					}

					HTTPResponceInfo info = new HTTPResponceInfo();
					info.setStatus(status);
					info.setMessage(message);
					// info.setId(id);

					retrytime = 0;

				} else {
					H_Founction.TraceError(BugLevel.USERERROR, params.toString(),
							httpResponse.toString(), "", "HTTP");
					strResult = "Error Response:"
							+ httpResponse.getStatusLine().toString();
					logger.error("strResult:  " + strResult + "  STATUS :"
							+ httpResponse.getStatusLine().getStatusCode());
					

				}
			} catch (ClientProtocolException e) {
				strResult = e.getMessage().toString();
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));

			} catch (IOException e) {
				strResult = e.getMessage().toString();
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));

			} catch (Exception e) {
				System.out.println("FUCK U");
				e.printStackTrace();
				logger.error(log.ExceptionLogTool.getTrace(e));
			}
			retrytime--;
		}
		return httpResponse;

	}

}
