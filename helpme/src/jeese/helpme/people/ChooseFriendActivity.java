package jeese.helpme.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jeese.helpme.R;
import jeese.helpme.adapter.ChooseFriend_Listview_Adapter;
import jeese.helpme.model.SortModel;
import jeese.helpme.util.CharacterParser;
import jeese.helpme.util.PinyinComparator;
import jeese.helpme.view.SideBar;
import jeese.helpme.view.SildingFinishLayout;
import jeese.helpme.view.SideBar.OnTouchingLetterChangedListener;
import jeese.helpme.view.SildingFinishLayout.OnSildingFinishListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseFriendActivity extends ActionBarActivity {

	private Toolbar mToolbar;
	private SildingFinishLayout mSildingFinishLayout;

	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private ChooseFriend_Listview_Adapter adapter;

	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	private PinyinComparator pinyinComparator;
	private List<Boolean> checkId;
	private int checknum = 0;

	private Button finishButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_friend);

		init();
	}

	private void init() {
		setToolBar();

		// ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		// �����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			public void onTouchingLetterChanged(String s) {
				// ����ĸ�״γ��ֵ�λ��
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.friend_listview);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (view.findViewById(R.id.checkimage).getVisibility() == 0) {
					checkId.set(position, false);
					view.findViewById(R.id.checkimage).setVisibility(4);
					checknum--;
					mToolbar.setTitle("ѡ����� ( " + checknum + " / 9 )");
				} else if (checknum < 9) {
					checkId.set(position, true);
					view.findViewById(R.id.checkimage).setVisibility(0);
					checknum++;
					mToolbar.setTitle("ѡ����� ( " + checknum + " / 9 )");
				} else {
					Toast.makeText(ChooseFriendActivity.this, "���ֻ��ѡ��9λ����Ŷ��",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		SourceDateList = filledData(
				getResources().getStringArray(R.array.name), getResources()
						.getStringArray(R.array.img_src_data));

		// �Ƿ�ѡ����û���
		checkId = new ArrayList<Boolean>();
		for (int i = 0; i < SourceDateList.size(); i++) {
			checkId.add(false);
		}

		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new ChooseFriend_Listview_Adapter(this, SourceDateList,
				checkId);
		sortListView.setAdapter(adapter);

		mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
		mSildingFinishLayout
				.setOnSildingFinishListener(new OnSildingFinishListener() {

					@Override
					public void onSildingFinish() {
						finish();
					}
				});

		// touchViewҪ���õ�ListView����
		mSildingFinishLayout.setTouchView(sortListView);

		finishButton = (Button) findViewById(R.id.finish);
		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("check_num", checknum);
				// ���ý����Ϣ
				setResult(007, intent);// ��Ҫ��ǰ�������resultCode����ʼֵ����0
				finish();

			}
		});

	}

	private void setToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		mToolbar.setTitle("ѡ����� ( " + checknum + " / 9 ) ");// �������������setSupportActionBar֮ǰ����Ȼ����Ч
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

	/**
	 * ΪListView�������
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String[] date, String[] imgData) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.length; i++) {
			SortModel sortModel = new SortModel();
			sortModel.setImgSrc(imgData[i]);
			sortModel.setName(date[i]);
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

}
