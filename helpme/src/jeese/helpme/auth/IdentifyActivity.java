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
	private String phone;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.identify_activity);
		IdentifyActivity = this;
		Bundle extras = getIntent().getExtras(); 
		phone = extras.getString("phone"); 
		init();
	}

	private void init() {
		setToolBar();
		
		Button codeButton = (Button) findViewById(R.id.code_button);

		codeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ҳ����ת��ע�������Ϣҳ��
				Intent intent = new Intent(IdentifyActivity.this,
						RegisterPasswordActivity.class);
				intent.putExtra("phone", phone); 
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

		// touchViewҪ���õ�ListView����
		mSildingFinishLayout.setTouchView(mSildingFinishLayout);

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("��д��֤��");// �������������setSupportActionBar֮ǰ����Ȼ����Ч
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
}
