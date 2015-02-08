package jeese.helpme.adapter;

import java.util.ArrayList;

import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.haarman.listviewanimations.ArrayAdapter;
import com.lidroid.xutils.BitmapUtils;

public class HelpDetailsVisitor_Listview_Adapter extends ArrayAdapter<Integer> {

	private Context mContext;
	private final OnClickListener itemButtonClickListener;
	private BitmapUtils bitmapUtils;

	private ArrayList<Integer> myList;

	// 3种不同的布局
	public static final int VALUE_HELP = 0;// 求助者的卡片
	public static final int VALUE_USER = 1; // 帮助者
	public static final int VALUE_EVENT = 2; // 事件

	public HelpDetailsVisitor_Listview_Adapter(Context context,
			ArrayList<Integer> items, OnClickListener itemButtonClickListener) {
		super(items);
		myList = items;
		this.mContext = context;
		this.itemButtonClickListener = itemButtonClickListener;
		bitmapUtils = new BitmapUtils(mContext);
		bitmapUtils.configDefaultLoadingImage(R.drawable.user_head);
	}

//	@Override
//	public int getCount() {
//		return myList.size();
//	}
//
//	@Override
//	public Integer getItem(int arg0) {
//		return myList.get(arg0);
//	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolderHelp holderHelp;
		ViewHolderUser holderUser;
		ViewHolderEvent holderEvent;

		int type = getItemViewType(position);
		View view = convertView;

		if (view == null) {
			switch (type) {
			case VALUE_HELP:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.helpdetail_visitor_card_1, parent, false);
				holderHelp = new ViewHolderHelp();
				holderHelp.titletext = (TextView) view
						.findViewById(R.id.helpdetail_visitor_card_1_titletext);
				holderHelp.contenttext = (TextView) view
						.findViewById(R.id.helpdetail_visitor_card_1_contenttext);
				holderHelp.headimage = (CircleImageView) view
						.findViewById(R.id.helpdetail_visitor_card_1_headimage);
				holderHelp.headimagebutton = (ImageButton) view
						.findViewById(R.id.helpdetail_visitor_card_1_headimagebutton);

				holderHelp.titletext.setText("我有话要说");
				holderHelp.contenttext
						.setText("目击众神死亡的草原上野花一片\n远在远方的风比远方更远\n我的琴声呜咽 泪水全无\n我把这远方的远归还草原\n一个叫木头 一个叫马尾\n我的琴声呜咽 泪水全无\n远方只有在死亡中凝聚野花一片\n明月如镜高悬草原映照千年岁月\n我的琴声呜咽 泪水全无\n只身打马过草原");
				holderHelp.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderHelp.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderHelp.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderHelp);
				break;

			case VALUE_USER:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.helpdetail_visitor_card_2, parent, false);
				holderUser = new ViewHolderUser();
				holderUser.headimage = (CircleImageView) view
						.findViewById(R.id.helpdetail_visitor_card_2_headimage);
				holderUser.headimagebutton = (ImageButton) view
						.findViewById(R.id.helpdetail_visitor_card_2_headimagebutton);

				holderUser.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderUser.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderUser.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderUser);
				break;

			case VALUE_EVENT:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.helpdetail_visitor_card_3, parent, false);
				holderEvent = new ViewHolderEvent();
				holderEvent.headimage = (CircleImageView) view
						.findViewById(R.id.helpdetail_visitor_card_3_headimage);
				holderEvent.headimagebutton = (ImageButton) view
						.findViewById(R.id.helpdetail_visitor_card_3_headimagebutton);

				holderEvent.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderEvent.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderEvent.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderEvent);
				break;

			default:
				break;
			}
		} else {
			switch (type) {
			case VALUE_HELP:
				holderHelp = (ViewHolderHelp) view.getTag();
				holderHelp.titletext.setText("我有话要说");
				holderHelp.contenttext
						.setText("目击众神死亡的草原上野花一片\n远在远方的风比远方更远\n我的琴声呜咽 泪水全无\n我把这远方的远归还草原\n一个叫木头 一个叫马尾\n我的琴声呜咽 泪水全无\n远方只有在死亡中凝聚野花一片\n明月如镜高悬草原映照千年岁月\n我的琴声呜咽 泪水全无\n只身打马过草原");
				holderHelp.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderHelp.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderHelp.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				break;

			case VALUE_USER:
				holderUser = (ViewHolderUser) view.getTag();
				holderUser.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderUser.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderUser.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				break;

			case VALUE_EVENT:
				holderEvent = (ViewHolderEvent) view.getTag();

				holderEvent.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderEvent.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderEvent.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				break;

			default:
				break;
			}
		}

		return view;
	}

	/**
	 * 根据数据源的position返回需要显示的的layout的type
	 * 
	 * type的值必须从0开始
	 * 
	 * */
	@Override
	public int getItemViewType(int position) {

		int type;
		if (position == 0) {
			type = VALUE_HELP;
		} else if (position > 0 && position <= 4) {
			type = VALUE_USER;
		} else {
			type = VALUE_EVENT;
		}

		return type;
	}

	/**
	 * 返回所有的layout的数量
	 * 
	 * */
	@Override
	public int getViewTypeCount() {
		return 3;
	}

	private static class ViewHolderHelp {
		TextView titletext;
		TextView contenttext;
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

	private static class ViewHolderUser {
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

	private static class ViewHolderEvent {
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

}
