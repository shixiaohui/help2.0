package jeese.helpme.adapter;

import java.util.ArrayList;

import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.haarman.listviewanimations.ArrayAdapter;
import com.lidroid.xutils.BitmapUtils;

public class Home_ListView_Adapter extends ArrayAdapter<Integer> {

	private Context mContext;
	private final OnClickListener itemButtonClickListener;
	private BitmapUtils bitmapUtils;

	private ArrayList<Integer> myList;

	// 2种不同的布局
	public static final int VALUE_ASK = 0;// 提问的卡片
	public static final int VALUE_HELP = 1; // 求助的卡片

	public Home_ListView_Adapter(Context context, ArrayList<Integer> items,
			OnClickListener itemButtonClickListener) {
		super(items);
		myList = items;
		this.mContext = context;
		this.itemButtonClickListener = itemButtonClickListener;
		bitmapUtils = new BitmapUtils(mContext);
		bitmapUtils.configDefaultLoadingImage(R.drawable.user_head);

	}

	// @Override
	// public int getCount() {
	// return myList.size();
	// }
	//
	// @Override
	// public Integer getItem(int arg0) {
	// return myList.get(arg0);
	// }

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolderAsk holderAsk;
		ViewHolderHelp holderHelp;

		int type = getItemViewType(position);
		View view = convertView;

		if (view == null) {
			switch (type) {
			case VALUE_ASK:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.ask_card_1, parent, false);
				holderAsk = new ViewHolderAsk();
				holderAsk.titletext = (TextView) view
						.findViewById(R.id.ask_card_1_titletext);
				holderAsk.contenttext = (TextView) view
						.findViewById(R.id.ask_card_1_contenttext);
				holderAsk.headimage = (CircleImageView) view
						.findViewById(R.id.ask_card_1_headimage);
				holderAsk.headimagebutton = (ImageButton) view
						.findViewById(R.id.ask_card_1_headimagebutton);
				holderAsk.messagebutton = (Button) view
						.findViewById(R.id.ask_card_1_messagebutton);

				holderAsk.titletext.setText("我有话要说");
				holderAsk.contenttext
						.setText("目击众神死亡的草原上野花一片\n远在远方的风比远方更远\n我的琴声呜咽 泪水全无\n我把这远方的远归还草原\n一个叫木头 一个叫马尾\n我的琴声呜咽 泪水全无\n远方只有在死亡中凝聚野花一片\n明月如镜高悬草原映照千年岁月\n我的琴声呜咽 泪水全无\n只身打马过草原");
				holderAsk.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderAsk.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderAsk.headimagebutton
							.setOnClickListener(itemButtonClickListener);
					holderAsk.messagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderAsk);
				break;

			case VALUE_HELP:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.help_card_1, parent, false);
				holderHelp = new ViewHolderHelp();
				holderHelp.titletext = (TextView) view
						.findViewById(R.id.help_card_1_titletext);
				holderHelp.contenttext = (TextView) view
						.findViewById(R.id.help_card_1_contenttext);
				holderHelp.headimage = (CircleImageView) view
						.findViewById(R.id.help_card_1_headimage);
				holderHelp.headimagebutton = (ImageButton) view
						.findViewById(R.id.help_card_1_headimagebutton);
				holderHelp.messagebutton = (Button) view
						.findViewById(R.id.help_card_1_messagebutton);

				holderHelp.titletext.setText("天王盖地虎，宝塔镇河妖。你脸红什么？精神焕发！");
				holderHelp.contenttext
						.setText("Google Polymer框架，在2013年的Google I/O大会上发布。其来自加盟谷歌的原Palm webOS的Enyo框架团队打造，使用了最新的浏览器特性。但是对于目前仍然处于开发阶段的框架，又如何吸引开发者去使用呢？以前几乎没有，但是现在Material design推出之后，也许会有所改观。");
				holderHelp.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils
						.display(
								holderHelp.headimage,
								"http://img4.duitang.com/uploads/item/201302/20/20130220221710_u8zLv.thumb.700_0.jpeg");
				if (itemButtonClickListener != null) {
					holderHelp.headimagebutton
							.setOnClickListener(itemButtonClickListener);
					holderHelp.messagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderHelp);
				break;

			default:
				break;
			}
		} else {
			switch (type) {
			case VALUE_ASK:
				holderAsk = (ViewHolderAsk) view.getTag();
				holderAsk.titletext.setText("我有话要说");
				holderAsk.contenttext
						.setText("目击众神死亡的草原上野花一片\n远在远方的风比远方更远\n我的琴声呜咽 泪水全无\n我把这远方的远归还草原\n一个叫木头 一个叫马尾\n我的琴声呜咽 泪水全无\n远方只有在死亡中凝聚野花一片\n明月如镜高悬草原映照千年岁月\n我的琴声呜咽 泪水全无\n只身打马过草原");
				holderAsk.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils.display(holderAsk.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderAsk.headimagebutton
							.setOnClickListener(itemButtonClickListener);
					holderAsk.messagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				break;

			case VALUE_HELP:
				holderHelp = (ViewHolderHelp) view.getTag();
				holderHelp.titletext.setText("天王盖地虎，宝塔镇河妖。你脸红什么？精神焕发！");
				holderHelp.contenttext
						.setText("Google Polymer框架，在2013年的Google I/O大会上发布。其来自加盟谷歌的原Palm webOS的Enyo框架团队打造，使用了最新的浏览器特性。但是对于目前仍然处于开发阶段的框架，又如何吸引开发者去使用呢？以前几乎没有，但是现在Material design推出之后，也许会有所改观。");
				holderHelp.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				bitmapUtils
						.display(
								holderHelp.headimage,
								"http://img4.duitang.com/uploads/item/201302/20/20130220221710_u8zLv.thumb.700_0.jpeg");
				if (itemButtonClickListener != null) {
					holderHelp.headimagebutton
							.setOnClickListener(itemButtonClickListener);
					holderHelp.messagebutton
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
		if (position % 2 == 0) {
			type = VALUE_ASK;
		} else {
			type = VALUE_HELP;
		}

		return type;
	}

	/**
	 * 返回所有的layout的数量
	 * 
	 * */
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	private static class ViewHolderAsk {
		TextView titletext;
		TextView contenttext;
		CircleImageView headimage;
		ImageButton headimagebutton;
		Button messagebutton;
	}

	private static class ViewHolderHelp {
		TextView titletext;
		TextView contenttext;
		CircleImageView headimage;
		ImageButton headimagebutton;
		Button messagebutton;
	}

}
