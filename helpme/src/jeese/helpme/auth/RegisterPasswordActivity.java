package jeese.helpme.auth;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import jeese.helpme.R;
import jeese.helpme.activity.MainActivity;
import jeese.helpme.application.App;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import jeese.helpme.view.materialedittext.MaterialEditText;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterPasswordActivity extends ActionBarActivity {

	public static Activity RegisterPasswordActivity;
	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;
	private MaterialEditText password_edit;
	private MaterialEditText nickname_edit;
	private String phone;
	private String password;
	private String nickname;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_password);
		RegisterPasswordActivity = this;
		Bundle extras = getIntent().getExtras(); 
		phone = extras.getString("phone"); 
		init();
	}

	private void init() {
		setToolBar();

		password_edit = (MaterialEditText) findViewById(R.id.password_edit);
		nickname_edit = (MaterialEditText) findViewById(R.id.nickname_edit);

		Button finishButton = (Button) findViewById(R.id.finish_button);

		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//���ʷ�����ע��
				register();
			}
		});

		mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
		mSildingFinishLayout
				.setOnSildingFinishListener(new OnSildingFinishListener() {

					@Override
					public void onSildingFinish() {
						finish();
					}
				});

		// touchViewҪ���õ�ListView����
		mSildingFinishLayout.setTouchView(mSildingFinishLayout);

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("��дע����Ϣ");// �������������setSupportActionBar֮ǰ����Ȼ����Ч
		// toolbar.setSubtitle("������");
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void register() {

		password = password_edit.getText().toString();
		nickname = nickname_edit.getText().toString();

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("cellPhone", phone);
			jsonObject.put("password", password);
			jsonObject.put("nickname", nickname);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestParams params = new RequestParams();
		params.addBodyParameter("fields", jsonObject.toString());

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				"http://120.24.208.130:3333/api/auth/register", params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						System.out.println("���ʷ�����ʧ��");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						try {
							JSONObject replyObject = new JSONObject(arg0.result);
							String state = replyObject.getString("state");
							if(state.equals("true")){
								SharedPreferences preferences = getSharedPreferences(
										"e_help", Context.MODE_PRIVATE);
								Editor editor = preferences.edit();
								editor.putString("cellPhone", phone);
								editor.putString("password", password);
								editor.commit();
								
								System.out.println("ע��ɹ�");
								
								//ִ�е�½
								App myApp = ((App) getApplicationContext());
								myApp.login();
								
								// ҳ����ת����ҳ��
								Intent intent = new Intent(RegisterPasswordActivity.this,
										MainActivity.class);
								RegisterPasswordActivity.this.startActivity(intent);
								StartActivity.StartActivity.finish();
								finish();
								
							}else{
								System.out.println("ע��ʧ��");
							}
		

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

}
