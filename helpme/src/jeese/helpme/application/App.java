package jeese.helpme.application;

import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.PreferencesCookieStore;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;

public class App extends Application {

	private String sessionId = null;

	private PreferencesCookieStore preferencesCookieStore;

	private Handler handler = new Handler();

	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		preferencesCookieStore = new PreferencesCookieStore(this);

		// JPush�ĳ�ʼ��
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);

		// ��ʼ����
		RongIM.init(this);

		// RongIM.init(this, "k51hidwq1fspb", R.drawable.ic_launcher);

		// ������Ӧ�÷����������Ի�ȡ Token���ڱ�ʾ��������ֱ�������� hardcode �� token ��ֵ��
		// String token = getTokenFromAppServer();

		// �˴�ֱ�� hardcode �� token ��ֵ�����滻Ϊ���Լ��� Token��

		// 001
		// С��
		// String token =
		// "gmeGPMbpkNpRVFqmRk49jf9JXx3k/zTlRSJ1G8Af9CI3U4e9lF3jnvQKYp2ecC/dBVc7XyuL9skbqunN048Scg==";

		// 002
		// Сţ
		// String token =
		// "yg9B/Wh0bZm9Wab05Ia5Af9JXx3k/zTlRSJ1G8Af9CI3U4e9lF3jnlvB84WBZ50UdgkvtOX7ULs5ATrHbF6P1g==";

		// 003
		// С����
		String token = "wjfvk2IpBXF+sB/wrTU7icgXtCAuovQvahXBtnZqSZmQjRdyLfCC8elmntSLtDK7+ykyqIE2k4IxxMHaZ6bvcw==";

		// �������Ʒ�������
		try {
			RongIM.connect(token, new RongIMClient.ConnectCallback() {

				@Override
				public void onSuccess(String s) {
					// �˴��������ӳɹ���
					Log.d("Connect:", "Login successfully.");
				}

				@Override
				public void onError(ErrorCode errorCode) {
					// �˴��������Ӵ���
					Log.d("Connect:", "Login failed.");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login() {
		SharedPreferences preferences = getSharedPreferences("e_help",
				Context.MODE_PRIVATE);
		sessionId = preferences.getString("sessionId", null);
		BasicClientCookie cookie = new BasicClientCookie("SESSION", sessionId);
		cookie.setDomain("120.24.208.130");
		cookie.setPath("/api/auth");
		preferencesCookieStore.addCookie(cookie);

		String phone = preferences.getString("cellPhone", null);
		String password = preferences.getString("password", null);

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("cellPhone", "110110_11");
			jsonObject.put("password", "helloworld");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestParams params = new RequestParams();
		params.addBodyParameter("fields", jsonObject.toString());

		HttpUtils http = new HttpUtils();
		http.configCookieStore(preferencesCookieStore);
		http.send(HttpRequest.HttpMethod.POST,
				"http://120.24.208.130:3333/api/auth/login", params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						System.out.println("���ӷ�����ʧ��");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						try {
							JSONObject replyObject = new JSONObject(arg0.result);
							String state = replyObject.getString("state");
							if (state.equals("true")) {
								JSONObject sessionObject = replyObject
										.getJSONObject("session");
								sessionId = sessionObject
										.getString("sessionId");
								SharedPreferences preferences = getSharedPreferences(
										"e_help", Context.MODE_PRIVATE);
								Editor editor = preferences.edit();
								editor.putString("sessionId", sessionId);
								editor.putBoolean("login_status", true);
								editor.commit();

								System.out.println("��¼�ɹ� session��" + sessionId);

								handler.postDelayed(new Runnable() {

									@Override
									public void run() {
										login();
										handler.postDelayed(this, 3000000);
									}
								}, 3000000);
							}else{
								System.out.println("��½ʧ��");
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

}
