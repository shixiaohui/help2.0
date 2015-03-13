package jeese.helpme.auth;

import jeese.helpme.R;
import jeese.helpme.view.MaterialDialog;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import jeese.helpme.view.materialedittext.MaterialEditText;
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
import android.widget.TextView;

public class RegisterPhoneActivity extends ActionBarActivity {

	public static Activity RegisterPhoneActivity;
	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;
	private MaterialDialog mMaterialDialog;
	private MaterialEditText phone_edit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_phone);
		RegisterPhoneActivity = this;
		init();
	}

	private void init() {
		setToolBar();
		
		phone_edit = (MaterialEditText) findViewById(R.id.phone_edit);
		
		mMaterialDialog = new MaterialDialog(this)
	    .setTitle("ȷ���ֻ�����")
	    .setMessage("���ǽ�������֤����ŵ�������룺\n��+86�� 156-2618-6016")
	    .setPositiveButton("ȷ��", new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            mMaterialDialog.dismiss();
	            
				// ҳ����ת����֤��ҳ��
				Intent intent = new Intent(RegisterPhoneActivity.this,
						IdentifyActivity.class);
				intent.putExtra("phone", phone_edit.getText().toString()); 
				RegisterPhoneActivity.this.startActivity(intent);
	        }
	    })
	    .setNegativeButton("ȡ��", new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            mMaterialDialog.dismiss();

	        }
	    });

		String html = "����Ϸ���ע�ᡱ��ť����ʾ��ͬ��";
		html += "<a href='http://www.baidu.com'>�����������ɼ�����Э�顷</a>";
		CharSequence charSequence = Html.fromHtml(html);

		TextView license = (TextView) findViewById(R.id.license);
		license.setText(charSequence);
		license.setMovementMethod(LinkMovementMethod.getInstance());

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

		// touchViewҪ���õ�ListView����
		mSildingFinishLayout.setTouchView(mSildingFinishLayout);

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("ʹ���ֻ���ע��");// �������������setSupportActionBar֮ǰ����Ȼ����Ч
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
