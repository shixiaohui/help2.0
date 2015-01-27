package jeese.helpme.home;

import io.rong.imkit.RongIM;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;

import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;
import jeese.helpme.view.PagerSlidingTabStrip;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import jeese.helpme.view.observablescrollview.ObservableListView;
import jeese.helpme.view.observablescrollview.ObservableScrollViewCallbacks;
import jeese.helpme.view.observablescrollview.ScrollState;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class HelpDetailsVisitorActivity extends ActionBarActivity implements
		ObservableScrollViewCallbacks {

	private Toolbar mToolbar;
	private PagerSlidingTabStrip mPagerSlidingTabStrip;
	private SildingFinishLayout mSildingFinishLayout;
	// viewpager相关
	private MyPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;
	private List<View> list;
	private View view1;
	private View view2;

	// tab1相关
	private View headerview1;
	private ObservableListView mListView1;
	private HelpDetailsVisitor_Listview_Adapter_1 mhelpdetailsTab1ListViewAdapter1;

	// tab2相关
	private View headerview2;
	private ObservableListView mListView2;
	private HelpDetailsVisitor_Listview_Adapter_2 mhelpdetailsTab1ListViewAdapter2;

	// tab1_headerview相关
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
		setContentView(R.layout.helpdetails_visitor);

		fb = FinalBitmap.create(this);// 初始化FinalBitmap模块
		fb.configLoadingImage(R.drawable.user_head);

		mHandler = new Handler();

		init();
		setView1();
		setView2();
	}

	private void init() {
		setToolBar();

		mViewPager = (ViewPager) this
				.findViewById(R.id.helpsetails_visitor_pager);

		view1 = LayoutInflater.from(HelpDetailsVisitorActivity.this).inflate(
				R.layout.helpdetail_visitor_tab1, null);
		view2 = LayoutInflater.from(HelpDetailsVisitorActivity.this).inflate(
				R.layout.helpdetail_visitor_tab2, null);

		list = new ArrayList<View>();
		list.add(view1);
		list.add(view2);

		mPagerAdapter = new MyPagerAdapter();
		mViewPager.setAdapter(mPagerAdapter);
		mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		mPagerSlidingTabStrip.setViewPager(mViewPager);
		mPagerSlidingTabStrip
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
					}
				});
		initTabsValue();

	}

	/**
	 * mPagerSlidingTabStrip默认值配置
	 * 
	 */
	private void initTabsValue() {
		// 底部游标颜色
		mPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(
				R.color.colorPrimary));
		// tab的分割线颜色
		mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
		// tab背景
		mPagerSlidingTabStrip.setBackgroundColor(Color.parseColor("#FFFFFF"));
		// tab底线高度
		mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources()
						.getDisplayMetrics()));
		// 游标高度
		mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
						.getDisplayMetrics()));
		// 选中的文字颜色
		mPagerSlidingTabStrip.setSelectedTextColor(Color.parseColor("#252525"));
		// 正常文字颜色
		mPagerSlidingTabStrip.setTextColor(Color.parseColor("#999999"));
		// 文字大小
		mPagerSlidingTabStrip.setTextSize(42);
		// 自动填充屏幕
		mPagerSlidingTabStrip.setShouldExpand(true);

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle(null);// 标题的文字需在setSupportActionBar之前，不然会无效
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

	private void setView1() {

		headerview1 = LayoutInflater.from(HelpDetailsVisitorActivity.this)
				.inflate(R.layout.helpdetail_visitor_tab1_listview_headerview,
						null);

		headimage = (CircleImageView) headerview1
				.findViewById(R.id.helpdetail_visitor_tab1_headimage);
		headimagebutton = (ImageButton) headerview1
				.findViewById(R.id.helpdetail_visitor_tab1_headimagebutton);
		fb.display(headimage,
				"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
		headimagebutton.setImageResource(R.drawable.headbutton_1);

		bt1 = (Button) headerview1
				.findViewById(R.id.helpdetail_visitor_tab1_imbutton);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						String userId = "001";
						String title = "小狗";

						RongIM.getInstance().startPrivateChat(
								HelpDetailsVisitorActivity.this, userId, title);
					}
				});

			}
		});

		bt2 = (Button) headerview1
				.findViewById(R.id.helpdetail_visitor_tab1_phonebutton);
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "10010";
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ num));
				startActivity(intent);

			}
		});

		mListView1 = (ObservableListView) view1
				.findViewById(R.id.helpdetail_visitor_tab1_listview);
		mListView1.addHeaderView(headerview1, null, false);
		mhelpdetailsTab1ListViewAdapter1 = new HelpDetailsVisitor_Listview_Adapter_1(
				this, null, new ListItemButtonClickListener());
		mListView1.setOnItemClickListener(new ListItemClickListener());
		mListView1.setOnItemLongClickListener(new ListItemLongClickListener());
		mListView1.setAdapter(mhelpdetailsTab1ListViewAdapter1);
		mListView1.setScrollViewCallbacks(this);
		mhelpdetailsTab1ListViewAdapter1.addAll(getItems());

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

	private void setView2() {
		headerview2 = LayoutInflater.from(HelpDetailsVisitorActivity.this)
				.inflate(R.layout.helpdetail_visitor_tab2_listview_headerview,
						null);

		mListView2 = (ObservableListView) view2
				.findViewById(R.id.helpdetail_visitor_tab2_listview);
		mListView2.addHeaderView(headerview2, null, false);
		mhelpdetailsTab1ListViewAdapter2 = new HelpDetailsVisitor_Listview_Adapter_2(
				this, null, new ListItemButtonClickListener());
		mListView2.setOnItemClickListener(new ListItemClickListener());
		mListView2.setOnItemLongClickListener(new ListItemLongClickListener());
		mListView2.setAdapter(mhelpdetailsTab1ListViewAdapter2);
		mListView2.setScrollViewCallbacks(this);
		mhelpdetailsTab1ListViewAdapter2.addAll(getItems_1());
	}

	class MyPagerAdapter extends PagerAdapter {

		private final String[] TITLES = { "详情", "进度" };

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(list.get(position));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	public ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			items.add(i);
		}
		return items;
	}

	public ArrayList<Integer> getItems_1() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
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

	@Override
	public void onScrollChanged(int scrollY, boolean firstScroll,
			boolean dragging) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDownMotionEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpOrCancelMotionEvent(ScrollState scrollState) {
		ActionBar actionBar = getSupportActionBar();
		if (scrollState == ScrollState.UP) {
			if (actionBar.isShowing()) {
				actionBar.hide();
			}
		} else if (scrollState == ScrollState.DOWN) {
			if (!actionBar.isShowing()) {
				actionBar.show();
			}
		}

	}

}
