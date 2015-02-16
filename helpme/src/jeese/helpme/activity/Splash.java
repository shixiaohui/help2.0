package jeese.helpme.activity;

import jeese.helpme.R;
import jeese.helpme.application.App;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 0; // 延迟时间

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// 判断app状态
		SharedPreferences preferences = getSharedPreferences("e_help",
				Context.MODE_PRIVATE);
		Boolean first_open = preferences.getBoolean("first_open_0.5.0", true);
		Boolean login_status = preferences.getBoolean("login_status", true);

		// 是否第一次打开
		if (first_open) {
			// 当前版本应用第一次打开
			// TODO 跳转欢迎页面
		} else {
			// 非第一次打开
		}

		// 登录状态
		if (login_status) {
			// 已登录状态

			App myApp = ((App) getApplicationContext());
			myApp.login();

			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent mainIntent = new Intent(Splash.this,
							MainActivity.class);
					Splash.this.startActivity(mainIntent);
					Splash.this.finish();

				}

			}, SPLASH_DISPLAY_LENGHT);

		} else {
			// 未登录状态
			// TODO 跳转输入手机号页面
		}

	}

}
