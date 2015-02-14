package jeese.helpme.activity;

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

import jeese.helpme.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PhoneActivity extends ActionBarActivity {

	private Toolbar mToolbar;
	private Button register;
	private TextView registertext;
	private Button login;
	private TextView logintext;
	private TextView sessiontext;
	private Button loginagain;
	private Button checkphone;
	private TextView checkphonetext;
	private Button logout;
	private TextView logouttext;
	private Button set;
	private TextView settext;

	private String session;

	private PreferencesCookieStore preferencesCookieStore;
	private Context mAppContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);
		mAppContext = getApplicationContext();
		preferencesCookieStore = new PreferencesCookieStore(
				mAppContext);
		initViews();
	}

	private void initViews() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("易助");// 标题的文字需在setSupportActionBar之前，不然会无效
		// toolbar.setSubtitle("副标题");
		setSupportActionBar(mToolbar);

		View.OnClickListener mylistener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.register:

					try {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("cellPhone", "110120119");
						jsonObject.put("password", "helloworld");
						jsonObject.put("nickname", "张全蛋");
						RequestParams params = new RequestParams();
						params.addBodyParameter("fields", jsonObject.toString());
						HttpUtils http1 = new HttpUtils();
						http1.send(HttpRequest.HttpMethod.POST,
								"http://120.24.208.130:3333/api/auth/register",
								params, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										registertext.setText(arg1 + "\n失败");

									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {
										registertext.setText("reply: "
												+ arg0.result + "\n成功");

									}

								});
					} catch (JSONException e) {
						e.printStackTrace();
					}

					break;

				case R.id.login:

					try {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("cellPhone", "110120119");
						jsonObject.put("password", "helloworld");
						RequestParams params = new RequestParams();
						params.addBodyParameter("fields", jsonObject.toString());
						final HttpUtils http2 = new HttpUtils();
						http2.configCookieStore(preferencesCookieStore);
						http2.send(HttpRequest.HttpMethod.POST,
								"http://120.24.208.130:3333/api/auth/login",
								params, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										logintext.setText(arg1 + "\n失败");

									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {

										logintext.setText("reply: "
												+ arg0.result + "\n成功");
										try {
											JSONObject replyObject = new JSONObject(
													arg0.result);
											JSONObject sessionObject = replyObject
													.getJSONObject("session");
											session = sessionObject
													.getString("sessionId");
//									        BasicClientCookie cookie = new BasicClientCookie("SESSION", session);
//									        cookie.setDomain("120.24.208.130");
//									        cookie.setPath("/api/auth");
//									        preferencesCookieStore.addCookie(cookie);
									        BasicClientCookie cookie1 = new BasicClientCookie("SESSION", session);
									        cookie1.setDomain("120.24.208.130");
									        cookie1.setPath("/api/user");
									        preferencesCookieStore.addCookie(cookie1);
											sessiontext.setText("session: "
													+ sessionObject
															.getString("sessionId"));
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										
//										DefaultHttpClient dh = (DefaultHttpClient) http2
//												.getHttpClient();
//										CookieStore cs = dh.getCookieStore();
//										List<Cookie> cookies = cs.getCookies();
//										for (int i = 0; i < cookies.size(); i++) {
//											if ("SESSION".equals(cookies.get(i)
//													.getName())) {
//												preferencesCookieStore
//														.addCookie(cookies
//																.get(i));
//												System.out.println("getcookie:"
//														+ cookies.get(i)
//																.getValue());
//												break;
//											}
//										}

									}

								});
					} catch (JSONException e) {
						e.printStackTrace();
					}

					break;

				case R.id.loginagain:

					try {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("cellPhone", "110120119");
						jsonObject.put("password", "helloworld");

						RequestParams params = new RequestParams();
						params.addBodyParameter("fields", jsonObject.toString());
						HttpUtils http3 = new HttpUtils();
						http3.configCookieStore(preferencesCookieStore);
						http3.send(HttpRequest.HttpMethod.POST,
								"http://120.24.208.130:3333/api/auth/login",
								params, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										logintext.setText(arg1 + "\n失败");

									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {

										logintext.setText("reply: "
												+ arg0.result + "\n成功");
										try {
											JSONObject replyObject = new JSONObject(
													arg0.result);
											JSONObject sessionObject = replyObject
													.getJSONObject("session");
											sessiontext.setText("session: "
													+ sessionObject
															.getString("sessionId"));
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}

								});
					} catch (JSONException e) {
						e.printStackTrace();
					}

					break;

				case R.id.checkphone:

					try {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("cellPhone", "110120119");

						RequestParams params = new RequestParams();
						params.addBodyParameter("fields", jsonObject.toString());
						HttpUtils http4 = new HttpUtils();
						http4.configCookieStore(preferencesCookieStore);
						http4.send(
								HttpRequest.HttpMethod.POST,
								"http://120.24.208.130:3333/api/auth/checkPhone",
								params, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										checkphonetext.setText(arg1 + "\n失败");

									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {
										checkphonetext.setText("reply: "
												+ arg0.result + "\n成功");

									}

								});
					} catch (JSONException e) {
						e.printStackTrace();
					}

					break;

				case R.id.logout:

					HttpUtils http5 = new HttpUtils();
					http5.configCookieStore(preferencesCookieStore);
					http5.send(HttpRequest.HttpMethod.POST,
							"http://120.24.208.130:3333/api/auth/logout",
							new RequestCallBack<String>() {

								@Override
								public void onFailure(HttpException arg0,
										String arg1) {
									logouttext.setText(arg1 + "\n失败");

								}

								@Override
								public void onSuccess(ResponseInfo<String> arg0) {
									logouttext.setText("reply: " + arg0.result
											+ "\n成功");

								}

							});

					break;
					
				case R.id.set:

					try {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("cellPhone", "110120119");
						jsonObject.put("sex", "0");
						jsonObject.put("age", "38");
						
						RequestParams params = new RequestParams();
						params.addBodyParameter("fields", jsonObject.toString());
						HttpUtils http6 = new HttpUtils();
						http6.configCookieStore(preferencesCookieStore);
						http6.send(
								HttpRequest.HttpMethod.POST,
								"http://120.24.208.130:3333/api/user/setUserInfo",
								params, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										settext.setText(arg1 + "\n失败");

									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {
										settext.setText("reply: "
												+ arg0.result + "\n成功");

									}

								});
					} catch (JSONException e) {
						e.printStackTrace();
					}

					break;

				default:
					break;
				}
			}
		};

		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(mylistener);

		registertext = (TextView) findViewById(R.id.registertext);

		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(mylistener);

		logintext = (TextView) findViewById(R.id.logintext);

		sessiontext = (TextView) findViewById(R.id.sessiontext);

		loginagain = (Button) findViewById(R.id.loginagain);
		loginagain.setOnClickListener(mylistener);

		checkphone = (Button) findViewById(R.id.checkphone);
		checkphone.setOnClickListener(mylistener);
		checkphonetext = (TextView) findViewById(R.id.checkphonetext);

		logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(mylistener);
		logouttext = (TextView) findViewById(R.id.logouttext);
		
		set = (Button) findViewById(R.id.set);
		set.setOnClickListener(mylistener);
		settext = (TextView) findViewById(R.id.settext);

	}
}
