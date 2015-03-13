package jeese.helpme.activity;

import jeese.helpme.R;
import jeese.helpme.application.App;
import jeese.helpme.auth.StartActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// ��ȡ��¼״̬����
		SharedPreferences preferences = getSharedPreferences("e_help",
				Context.MODE_PRIVATE);
		Boolean login_status = preferences.getBoolean("login_status", false);

		// �жϵ�¼״̬
		if (login_status) {
			// �ѵ�¼״̬

			//ִ�е�½
			App myApp = ((App) getApplicationContext());
			myApp.login();

			//ҳ����ת����ҳ��
			Intent mainIntent = new Intent(Splash.this, MainActivity.class);
			Splash.this.startActivity(mainIntent);
			Splash.this.finish();

		} else {
			// δ��¼״̬
		
			//ҳ����ת����ʼҳ��
			Intent startIntent = new Intent(Splash.this, StartActivity.class);
			Splash.this.startActivity(startIntent);
			Splash.this.finish();
			
		}

	}

}
