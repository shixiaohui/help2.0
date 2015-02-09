package jeese.helpme.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jeese.helpme.R;
import jeese.helpme.adapter.People_Listview_Adapter;
import jeese.helpme.model.SortModel;
import jeese.helpme.util.CharacterParser;
import jeese.helpme.util.PinyinComparator;
import jeese.helpme.view.SideBar;
import jeese.helpme.view.SideBar.OnTouchingLetterChangedListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class People_Fragment extends Fragment {
	
	private View mView;
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private People_Listview_Adapter adapter;
	
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	private PinyinComparator pinyinComparator;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mView = View.inflate(getActivity(), R.layout.people_fragment, null);
		initViews();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup parent = (ViewGroup) mView.getParent();
		if (parent != null) {
			parent.removeView(mView);
		}
		return mView;
	}
	
	private void initViews() {
		//ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) mView.findViewById(R.id.sidrbar);
		dialog = (TextView) mView.findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//�����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			public void onTouchingLetterChanged(String s) {
				//����ĸ�״γ��ֵ�λ��
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
				
			}
		});
		
		sortListView = (ListView) mView.findViewById(R.id.people_fragment_listview);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
				Toast.makeText(getActivity(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		SourceDateList = filledData(getResources().getStringArray(R.array.name),getResources().getStringArray(R.array.img_src_data));
		
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new People_Listview_Adapter(getActivity(), SourceDateList);
		sortListView.setAdapter(adapter);
		
	}
	
	/**
	 * ΪListView�������
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date,String[] imgData){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setImgSrc(imgData[i]);
			sortModel.setName(date[i]);
			//����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	
}
