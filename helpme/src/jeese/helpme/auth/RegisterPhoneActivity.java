package jeese.helpme.auth;

import jeese.helpme.R;
import jeese.helpme.view.MaterialDialog;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterPhoneActivity extends ActionBarActivity {

	public static Activity RegisterPhoneActivity;
	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;
	private MaterialDialog mMaterialDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_phone);
		RegisterPhoneActivity = this;
		init();
	}

	private void init() {
		setToolBar();
		
		mMaterialDialog = new MaterialDialog(this)
	    .setTitle("确认手机号码")
	    .setMessage("我们将发送验证码短信到这个号码：\n（+86） 156-2618-6016")
	    .setPositiveButton("确认", new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            mMaterialDialog.dismiss();
				// 页面跳转到验证码页面
				Intent intent = new Intent(RegisterPhoneActivity.this,
						IdentifyActivity.class);
				RegisterPhoneActivity.this.startActivity(intent);
	        }
	    })
	    .setNegativeButton("取消", new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            mMaterialDialog.dismiss();

	        }
	    });

		String html = "点击上方“注册”按钮即表示您同意";
		html += "<a href='http://www.baidu.com'>《易助软件许可及服务协议》</a>";
		CharSequence charSequence = Html.fromHtml(html);

		TextView license = (TextView) findViewById(R.id.license);
		license.setText(charSequence);
		license.setMovementMethod(LinkMovementMethod.getInstance());

		EditText phone_edit = (EditText) findViewById(R.id.phone_edit);

		Button phoneButton = (Button) findViewById(R.id.phone_button);

		phoneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mMaterialDialog.show();
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
		mToolbar.setTitle("使用手机号注册");// 标题的文字需在setSupportActionBar之前，不然会无效
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
