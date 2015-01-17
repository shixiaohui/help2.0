package jeese.helpme.home;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import jeese.helpme.R;
import jeese.helpme.location.MapActivity;
import jeese.helpme.view.SwipeRefreshLayout;
import jeese.helpme.view.SwipeRefreshLayout.OnLoadListener;
import jeese.helpme.view.SwipeRefreshLayout.OnRefreshListener;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Home_Fragment extends Fragment implements OnRefreshListener,
		OnLoadListener {

	private MyPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;
	private List<View> list;
	private View view1;
	private View view2;
	private View view3;
	private View view4;

	private View mView;

	private int pageid = 0;// µ±Ç°pageµÄid

	private ListView mListView1;
	private Home_ListView_Adapter mHomeListViewAdapter1;
	private SwipeRefreshLayout mSwipeLayout1;
	private ListView mListView2;
	private Home_ListView_Adapter mHomeListViewAdapter2;
	private SwipeRefreshLayout mSwipeLayout2;
	private ListView mListView3;
	private Home_ListView_Adapter mHomeListViewAdapter3;
	private SwipeRefreshLayout mSwipeLayout3;
	private ListView mListView4;
	private Home_ListView_Adapter mHomeListViewAdapter4;
	private SwipeRefreshLayout mSwipeLayout4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mView = View.inflate(getActivity(), R.layout.home_fragment, null);
		init();
		setView1();
		setView2();
		setView3();
		setView4();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup p = (ViewGroup) mView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return mView;
	}

	@SuppressWarnings("deprecation")
	private void init() {

		final ActionBar actionBar = getActivity().getActionBar();

		mViewPager = (ViewPager) mView.findViewById(R.id.home_fragment_pager);

		view1 = LayoutInflater.from(getActivity()).inflate(R.layout.home_tab1,
				null);
		view2 = LayoutInflater.from(getActivity()).inflate(R.layout.home_tab2,
				null);
		view3 = LayoutInflater.from(getActivity()).inflate(R.layout.home_tab3,
				null);
		view4 = LayoutInflater.from(getActivity()).inflate(R.layout.home_tab4,
				null);

		list = new ArrayList<View>();
		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);

		mPagerAdapter = new MyPagerAdapter();
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
				pageid = arg0;
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
				R.array.home_fragment_actionbar_icons);
		TypedArray titleIds = getResources().obtainTypedArray(
				R.array.home_fragment_actionbar_titles);
		for (int i = 0; i < 4; i++) {
			View view = getActivity().getLayoutInflater().inflate(
					R.layout.home_fragment_actionbar_tab_layout, null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.home_fragment_icon);
			imageView.setImageResource(iconIds.getResourceId(i, -1));
			TextView textView = (TextView) view
					.findViewById(R.id.home_fragment_title);
			textView.setText(titleIds.getResourceId(i, -1));

			actionBar.addTab(actionBar.newTab().setCustomView(view)
					.setTabListener(tabListener));
		}

		enableEmbeddedTabs(actionBar);
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

	@SuppressLint("ResourceAsColor")
	private void setView1() {
		mSwipeLayout1 = (SwipeRefreshLayout) view1
				.findViewById(R.id.home_tab1_swipe_container);
		mSwipeLayout1.setOnRefreshListener(this);
		mSwipeLayout1.setOnLoadListener(this);
		mSwipeLayout1.setColor(R.color.light_blue, R.color.red, R.color.green,
				R.color.orange);
		mSwipeLayout1.setMode(SwipeRefreshLayout.Mode.BOTH);
		mSwipeLayout1.setLoadNoFull(false);

		mListView1 = (ListView) view1.findViewById(R.id.home_tab1_listview);

		mHomeListViewAdapter1 = new Home_ListView_Adapter(getActivity(), null,
				new ListItemButtonClickListener());

		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
				mHomeListViewAdapter1);
		animAdapter.setAbsListView(mListView1);
		mListView1.setAdapter(animAdapter);

		mListView1.setOnItemClickListener(new ListItemClickListener());
		mListView1.setOnItemLongClickListener(new ListItemLongClickListener());

		mHomeListViewAdapter1.addAll(getItems());

	}

	@SuppressLint("ResourceAsColor")
	private void setView2() {
		mSwipeLayout2 = (SwipeRefreshLayout) view2
				.findViewById(R.id.home_tab2_swipe_container);
		mSwipeLayout2.setOnRefreshListener(this);
		mSwipeLayout2.setOnLoadListener(this);
		mSwipeLayout2.setColor(R.color.light_blue, R.color.red, R.color.green,
				R.color.orange);
		mSwipeLayout2.setMode(SwipeRefreshLayout.Mode.BOTH);
		mSwipeLayout2.setLoadNoFull(false);

		mListView2 = (ListView) view2.findViewById(R.id.home_tab2_listview);

		mHomeListViewAdapter2 = new Home_ListView_Adapter(getActivity(), null,
				new ListItemButtonClickListener());

		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
				mHomeListViewAdapter2);
		animAdapter.setAbsListView(mListView2);
		mListView2.setAdapter(animAdapter);

		mListView2.setOnItemClickListener(new ListItemClickListener());
		mListView2.setOnItemLongClickListener(new ListItemLongClickListener());

		mHomeListViewAdapter2.addAll(getItems());

	}

	@SuppressLint("ResourceAsColor")
	private void setView3() {
		mSwipeLayout3 = (SwipeRefreshLayout) view3
				.findViewById(R.id.home_tab3_swipe_container);
		mSwipeLayout3.setOnRefreshListener(this);
		mSwipeLayout3.setOnLoadListener(this);
		mSwipeLayout3.setColor(R.color.light_blue, R.color.red, R.color.green,
				R.color.orange);
		mSwipeLayout3.setMode(SwipeRefreshLayout.Mode.BOTH);
		mSwipeLayout3.setLoadNoFull(false);

		mListView3 = (ListView) view3.findViewById(R.id.home_tab3_listview);

		mHomeListViewAdapter3 = new Home_ListView_Adapter(getActivity(), null,
				new ListItemButtonClickListener());

		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
				mHomeListViewAdapter3);
		animAdapter.setAbsListView(mListView3);
		mListView3.setAdapter(animAdapter);

		mListView3.setOnItemClickListener(new ListItemClickListener());
		mListView3.setOnItemLongClickListener(new ListItemLongClickListener());

		mHomeListViewAdapter3.addAll(getItems());

	}

	@SuppressLint("ResourceAsColor")
	private void setView4() {
		mSwipeLayout4 = (SwipeRefreshLayout) view4
				.findViewById(R.id.home_tab4_swipe_container);
		mSwipeLayout4.setOnRefreshListener(this);
		mSwipeLayout4.setOnLoadListener(this);
		mSwipeLayout4.setColor(R.color.light_blue, R.color.red, R.color.green,
				R.color.orange);
		mSwipeLayout4.setMode(SwipeRefreshLayout.Mode.BOTH);
		mSwipeLayout4.setLoadNoFull(false);

		mListView4 = (ListView) view4.findViewById(R.id.home_tab4_listview);

		mHomeListViewAdapter4 = new Home_ListView_Adapter(getActivity(), null,
				new ListItemButtonClickListener());

		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
				mHomeListViewAdapter4);
		animAdapter.setAbsListView(mListView4);
		mListView4.setAdapter(animAdapter);

		mListView4.setOnItemClickListener(new ListItemClickListener());
		mListView4.setOnItemLongClickListener(new ListItemLongClickListener());

		mHomeListViewAdapter4.addAll(getItems());

	}

	public ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 30; i++) {
			items.add(i);
		}
		return items;
	}

	public ArrayList<Integer> getnewItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			items.add(i);
		}
		return items;
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (pageid == 0) {
					mHomeListViewAdapter1.addAll(0, getnewItems());
					mSwipeLayout1.setRefreshing(false);
					mHomeListViewAdapter1.notifyDataSetChanged();
				} else if (pageid == 1) {
					mHomeListViewAdapter2.addAll(0, getnewItems());
					mSwipeLayout2.setRefreshing(false);
					mHomeListViewAdapter2.notifyDataSetChanged();
				} else if (pageid == 2) {
					mHomeListViewAdapter3.addAll(0, getnewItems());
					mSwipeLayout3.setRefreshing(false);
					mHomeListViewAdapter3.notifyDataSetChanged();
				} else if (pageid == 3) {
					mHomeListViewAdapter4.addAll(0, getnewItems());
					mSwipeLayout4.setRefreshing(false);
					mHomeListViewAdapter4.notifyDataSetChanged();
				}

			}
		}, 2000);

	}

	@Override
	public void onLoad() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (pageid == 0) {
					mHomeListViewAdapter1.addAll(getnewItems());
					mSwipeLayout1.setLoading(false);
					mHomeListViewAdapter1.notifyDataSetChanged();
				} else if (pageid == 1) {
					mHomeListViewAdapter2.addAll(getnewItems());
					mSwipeLayout2.setLoading(false);
					mHomeListViewAdapter2.notifyDataSetChanged();
				} else if (pageid == 2) {
					mHomeListViewAdapter3.addAll(getnewItems());
					mSwipeLayout3.setLoading(false);
					mHomeListViewAdapter3.notifyDataSetChanged();
				} else if (pageid == 3) {
					mHomeListViewAdapter4.addAll(getnewItems());
					mSwipeLayout4.setLoading(false);
					mHomeListViewAdapter4.notifyDataSetChanged();
				}

			}
		}, 1000);

	}

	private final class ListItemButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (pageid == 0) {
				for (int i = mListView1.getFirstVisiblePosition(); i <= mListView1
						.getLastVisiblePosition(); i++) {
					if (v == mListView1
							.getChildAt(
									i - mListView1.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_markbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Mark Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
					} else if (v == mListView1
							.getChildAt(
									i - mListView1.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_helpbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Help Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
						Intent Intent = new Intent(getActivity(),
								MapActivity.class);
						startActivity(Intent);
					}
				}
			} else if (pageid == 1) {
				for (int i = mListView2.getFirstVisiblePosition(); i <= mListView2
						.getLastVisiblePosition(); i++) {
					if (v == mListView2
							.getChildAt(
									i - mListView2.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_markbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Mark Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
					} else if (v == mListView2
							.getChildAt(
									i - mListView2.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_helpbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Help Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
						Intent Intent = new Intent(getActivity(),
								MapActivity.class);
						startActivity(Intent);
					}
				}
			} else if (pageid == 2) {
				for (int i = mListView3.getFirstVisiblePosition(); i <= mListView3
						.getLastVisiblePosition(); i++) {
					if (v == mListView3
							.getChildAt(
									i - mListView3.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_markbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Mark Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
					} else if (v == mListView3
							.getChildAt(
									i - mListView3.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_helpbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Help Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
						Intent Intent = new Intent(getActivity(),
								MapActivity.class);
						startActivity(Intent);
					}
				}
			} else if (pageid == 3) {
				for (int i = mListView4.getFirstVisiblePosition(); i <= mListView4
						.getLastVisiblePosition(); i++) {
					if (v == mListView4
							.getChildAt(
									i - mListView4.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_markbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Mark Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
					} else if (v == mListView4
							.getChildAt(
									i - mListView4.getFirstVisiblePosition())
							.findViewById(
									R.id.home_fragment_listview_item_helpbutton)) {
						Toast.makeText(
								getActivity(),
								"Clicked on Help Action Button of List Item "
										+ i, Toast.LENGTH_SHORT).show();
						Intent Intent = new Intent(getActivity(),
								MapActivity.class);
						startActivity(Intent);
					}
				}
			}

		}
	}

	private final class ListItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent Intent = new Intent(getActivity(),
					HelpDetailsVisitorActivity.class);
			startActivity(Intent);
		}
	}

	private final class ListItemLongClickListener implements
			OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			Toast.makeText(getActivity(),
					"LongClicked on List Item " + position, Toast.LENGTH_SHORT)
					.show();
			return true;
		}

	}

}
