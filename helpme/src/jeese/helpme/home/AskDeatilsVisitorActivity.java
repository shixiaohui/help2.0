package jeese.helpme.home;

import io.rong.imkit.RongIM;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;
import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class AskDeatilsVisitorActivity extends ActionBarActivity {

	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;

	// listview相关
	private View headerview1;
	private ListView mListView1;
	private AskDetailsVisitor_Listview_Adapter_1 maskdetailsListViewAdapter;

	// listview_headerview相关
	private FinalBitmap fb;
	private CircleImageView headimage;
	private ImageButton headimagebutton;
	private Button bt1;
	private Button bt2;

	// 用来实现 UI 线程的更新。
	private Handler mHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.askdetail_visitor);

		fb = FinalBitmap.create(this);// 初始化FinalBitmap模块
		fb.configLoadingImage(R.drawable.user_head);

		mHandler = new Handler();

		init();
	}

	private void init() {
		setToolBar();


		headerview1 = LayoutInflater.from(AskDeatilsVisitorActivity.this)
				.inflate(R.layout.askdetail_visitor_listview_headerview,
						null);

		headimage = (CircleImageView) headerview1
				.findViewById(R.id.askdetail_visitor_headimage);
		headimagebutton = (ImageButton) headerview1
				.findViewById(R.id.askdetail_visitor_headimagebutton);
		fb.display(headimage,
				"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
		headimagebutton.setImageResource(R.drawable.headbutton_1);

		bt1 = (Button) headerview1
				.findViewById(R.id.askdetail_visitor_imbutton);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						String userId = "001";
						String title = "小狗";

						RongIM.getInstance().startPrivateChat(
								AskDeatilsVisitorActivity.this, userId, title);
					}
				});

			}
		});

		bt2 = (Button) headerview1
				.findViewById(R.id.askdetail_visitor_phonebutton);
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "10010";
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ num));
				startActivity(intent);

			}
		});

		mListView1 = (ListView) findViewById(R.id.askdetail_visitor_listview);
		mListView1.addHeaderView(headerview1, null, false);
		maskdetailsListViewAdapter = new AskDetailsVisitor_Listview_Adapter_1(
				this, null, new ListItemButtonClickListener());
		mListView1.setOnItemClickListener(new ListItemClickListener());
		mListView1.setOnItemLongClickListener(new ListItemLongClickListener());
		mListView1.setAdapter(maskdetailsListViewAdapter);
		maskdetailsListViewAdapter.addAll(getItems());

		mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
		mSildingFinishLayout
				.setOnSildingFinishListener(new OnSildingFinishListener() {

					@Override
					public void onSildingFinish() {
						finish();
					}
				});

		// touchView要设置到ListView上面
		mSildingFinishLayout.setTouchView(mListView1);

	}
	
	public ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			items.add(i);
		}
		return items;
	}
	
	private final class ListItemButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {

		}
	}

	private final class ListItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

		}
	}

	private final class ListItemLongClickListener implements
			OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {

			return true;
		}

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("提问");// 标题的文字需在setSupportActionBar之前，不然会无效
		// toolbar.setSubtitle("副标题");
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		/* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过下面的两个回调方法来处理 */
		mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case android.R.id.home:
					finish();
					return true;
				default:
					break;
				}
				return true;
			}
		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
