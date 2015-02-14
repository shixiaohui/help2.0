package jeese.helpme.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;

import jeese.helpme.R;
import jeese.helpme.model.SortModel;
import jeese.helpme.view.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class People_Listview_Adapter extends BaseAdapter implements SectionIndexer{
	private List<SortModel> list = null;
	private Context mContext;
	private BitmapUtils bitmapUtils;
	
	public People_Listview_Adapter(Context mContext, List<SortModel> list) {
		this.mContext = mContext;
		this.list = list;
		bitmapUtils = new BitmapUtils(mContext);
		bitmapUtils.configDefaultLoadingImage(R.drawable.user_head);
	}
	
	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * @param list
	 */
	public void updateListView(List<SortModel> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.people_listview_item_1, null);
			viewHolder.headimage = (CircleImageView) view.findViewById(R.id.headimage);
			viewHolder.circle = (ImageView)view.findViewById(R.id.circleimage);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		//����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);
		
		//�����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
	
		bitmapUtils.display(viewHolder.headimage, this.list.get(position).getImgSrc());
		
		viewHolder.tvTitle.setText(this.list.get(position).getName());
		//viewHolder.tvTitle.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//�»���
		
		return view;

	}
	


	final static class ViewHolder {
		CircleImageView headimage;
		ImageView circle;
		TextView tvLetter;
		TextView tvTitle;
	}


	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	

	@Override
	public Object[] getSections() {
		return null;
	}
}