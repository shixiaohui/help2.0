package jeese.helpme.home;

import io.rong.imkit.RongIM;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.FinalBitmap;

import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class HelpDetailsVisitorActivity extends Activity {

	private MyPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;
	private List<View> list;
	private View view1;
	private View view2;

	private View headerview1;
	private ListView mListView;
	private HelpDetailsVisitor_Listview_Adapter mhelpdetailsTab1ListViewAdapter;

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

		// 图标标题是否显示，如果设成false则不显示
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

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
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.TabListener tabListener = new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		};

		TypedArray iconIds = getResources().obtainTypedArray(
				R.array.actionbar_icons);
		TypedArray titleIds = getResources().obtainTypedArray(
				R.array.helpdetail_actionbar_titles);
		for (int i = 0; i < 2; i++) {
			View view = getLayoutInflater().inflate(
					R.layout.helpdetail_icon_actionbar_tab_layout, null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.helpdetail_icon);
			imageView.setImageResource(iconIds.getResourceId(i, -1));
			TextView textView = (TextView) view
					.findViewById(R.id.helpdetail_title);
			textView.setText(titleIds.getResourceId(i, -1));

			actionBar.addTab(actionBar.newTab().setCustomView(view)
					.setTabListener(tabListener));
		}

		enableEmbeddedTabs(actionBar);
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

		mListView = (ListView) view1
				.findViewById(R.id.helpdetail_visitor_tab1_listview);
		mListView.addHeaderView(headerview1, null, false);
		mhelpdetailsTab1ListViewAdapter = new HelpDetailsVisitor_Listview_Adapter(
				this, null, new ListItemButtonClickListener());
		mListView.setOnItemClickListener(new ListItemClickListener());
		mListView.setOnItemLongClickListener(new ListItemLongClickListener());
		mListView.setAdapter(mhelpdetailsTab1ListViewAdapter);
		mhelpdetailsTab1ListViewAdapter.addAll(getItems());
		// setListViewHeightBasedOnChildren(mListView);
	}

	private void setView2() {
		bt1 = (Button) view2.findViewById(R.id.button1);
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

		bt2 = (Button) view2.findViewById(R.id.button2);
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "10010";
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ num));
				startActivity(intent);

			}
		});
	}

	private void enableEmbeddedTabs(Object actionBar) {
		try {
			Method setHasEmbeddedTabsMethod = actionBar.getClass()
					.getDeclaredMethod("setHasEmbeddedTabs", boolean.class);
			setHasEmbeddedTabsMethod.setAccessible(true);
			setHasEmbeddedTabsMethod.invoke(actionBar, true);
		} catch (Exception e) {
			Log.v("EmbeddedTabs", e.getMessage().toString());

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(list.get(position));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Tab" + (position);
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
		for (int i = 0; i < 30; i++) {
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

}
