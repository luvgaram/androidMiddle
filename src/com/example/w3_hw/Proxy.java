package com.example.w3_hw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class Proxy {
	public String getJSON() {
		try {
			
			URL url = new URL("http://54.64.250.239:5009/loadData");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// 서버 접속시의 Time out(ms)
			conn.setConnectTimeout(10 * 1000);
			// Read시의 Time out(ms)
			conn.setReadTimeout(10 * 1000);
			
			// 요청 방식 선택
			conn.setRequestMethod("GET");
			// 연결을 지속하도록 함
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 캐릭터셋을 UTF-8로 요청
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			// 캐시된 데이터를 사용하지 않고 매번 서버로부터 다시 받음
			conn.setRequestProperty("Cashe-Control", "no-cashe");
			// 서버로부터 JSON 형식의 타입으로 데이터 요청
			conn.setRequestProperty("Accept", "application/json");
			
			// InputStream으로 서버로부터 응답을 받겠다는 옵션
			conn.setDoInput(true);
			conn.connect();
			
			int status = conn.getResponseCode();
			Log.i("test", "ProxyResponseCode:" + status);
			
			switch (status) {
			case 200:
			case 201:
				// 정상적으로 연결이 된 상태 (200, 201번 ResponseCode)
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("test", "NETWORK ERROR:" + e);
		}
		
		return null;
	}
}
