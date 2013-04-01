package com.example.mypm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class GetPmValue {

	// Pm 2.5 server url
	private static final String GETURL = "http://pm25.in/api/querys/pm2_5.json";
	
	// my App Key
	private static final String APPKEY = "nuzDHvsyop8REaRH6H6r";
	
	// httpClient
	HttpClient httpClient = new DefaultHttpClient();

	public JSONObject getPmValue(final String myCity) {

		JSONArray jsonArray = null;
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		if (null == myCity || "".equals(myCity)) {
			return null;
		}
		StringBuilder url = new StringBuilder();
		url.append(GETURL).append("?city=").append(myCity).append("&token=")
				.append(APPKEY);
		HttpGet httpGet = new HttpGet(url.toString());

		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			// get Json Array
			jsonArray = new JSONArray(builder.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			return jsonArray.getJSONObject(5);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
