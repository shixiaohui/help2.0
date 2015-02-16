package jeese.helpme.people;

import com.lidroid.xutils.BitmapUtils;

import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;
import jeese.helpme.view.LabelView;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

public class UserDetailsActivity extends ActionBarActivity {

	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;

	private BitmapUtils bitmapUtils;
	private CircleImageView headimage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_details);

		bitmapUtils = new BitmapUtils(this);
		bitmapUtils.configDefaultLoadingImage(R.drawable.user_head);

		init();
	}

	private void init() {
		setToolBar();
		
        LabelView label = new LabelView(this);
        label.setText("三好学生");
        label.setBackgroundColor(0xffE91E63);
        label.setTargetView(findViewById(R.id.card), 25, LabelView.Gravity.RIGHT_TOP);

		headimage = (CircleImageView) findViewById(R.id.headimage);
		
		bitmapUtils.display(headimage, "http://www.touxiang.cn/uploads/20130608/08-023618_517.jpg");

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
		mToolbar.setTitle("详细资料");// 标题的文字需在setSupportActionBar之前，不然会无效
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
