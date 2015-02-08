package jeese.helpme.home;

import java.util.ArrayList;

import jeese.helpme.R;
import jeese.helpme.adapter.AskDetailsOwner_Listview_Adapter;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class AskDeatilsOwnerActivity extends ActionBarActivity {

	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;

	// listview相关
	private ListView mListView1;
	private AskDetailsOwner_Listview_Adapter maskdetailsListViewAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.askdetail_owner);

		init();
	}

	private void init() {
		setToolBar();

		mListView1 = (ListView) findViewById(R.id.askdetail_owner_listview);
		maskdetailsListViewAdapter = new AskDetailsOwner_Listview_Adapter(
				this, null, new ListItemButtonClickListener());
		mListView1.setOnItemClickListener(new ListItemClickListener());
		mListView1.setOnItemLongClickListener(new ListItemLongClickListener());
		
//		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
//				maskdetailsListViewAdapter);
//		animAdapter.setAbsListView(mListView1);
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
