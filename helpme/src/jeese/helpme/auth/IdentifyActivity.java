package jeese.helpme.auth;

import jeese.helpme.R;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class IdentifyActivity extends ActionBarActivity {
	
	public static Activity IdentifyActivity;
	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.identify_activity);
		IdentifyActivity = this;
		init();
	}

	private void init() {
		setToolBar();
		
		Button codeButton = (Button) findViewById(R.id.code_button);

		codeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 页面跳转到注册个人信息页面
				Intent intent = new Intent(IdentifyActivity.this,
						RegisterPasswordActivity.class);
				IdentifyActivity.this.startActivity(intent);
				RegisterPhoneActivity.RegisterPhoneActivity.finish();
				finish();
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

		// touchView要设置到ListView上面
		mSildingFinishLayout.setTouchView(mSildingFinishLayout);

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("填写验证码");// 标题的文字需在setSupportActionBar之前，不然会无效
		// toolbar.setSubtitle("副标题");
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
}
