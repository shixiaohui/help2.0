package jeese.helpme.home;

import java.util.ArrayList;

import com.haarman.listviewanimations.ArrayAdapter;

import jeese.helpme.R;
import jeese.helpme.view.CircleImageView;
import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class AskDetailsOwner_Listview_Adapter extends ArrayAdapter<Integer> {

	private Context mContext;
	private final OnClickListener itemButtonClickListener;
	private FinalBitmap fb;

	private ArrayList<Integer> myList;

	// 3种不同的布局
	public static final int VALUE_ASK = 0;// 提问者的卡片
	public static final int VALUE_ANSWER = 1; // 其他人的回答
	public static final int VALUE_MYANSWER = 2; // 我的回答

	public AskDetailsOwner_Listview_Adapter(Context context,
			ArrayList<Integer> items, OnClickListener itemButtonClickListener) {
		super(items);
		myList = items;
		this.mContext = context;
		this.itemButtonClickListener = itemButtonClickListener;
		fb = FinalBitmap.create(context);// 初始化FinalBitmap模块
		fb.configLoadingImage(R.drawable.user_head);
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

		ViewHolderAsk holderAsk;
		ViewHolderAnswer holderAnswer;
		ViewHolderMyanswer holderMyanswer;

		int type = getItemViewType(position);
		View view = convertView;

		if (view == null) {
			switch (type) {
			case VALUE_ASK:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.askdetail_owner_card_1, parent, false);
				holderAsk = new ViewHolderAsk();
				holderAsk.titletext = (TextView) view
						.findViewById(R.id.askdetail_owner_card_1_titletext);
				holderAsk.contenttext = (TextView) view
						.findViewById(R.id.askdetail_owner_card_1_contenttext);
				holderAsk.headimage = (CircleImageView) view
						.findViewById(R.id.askdetail_owner_card_1_headimage);
				holderAsk.headimagebutton = (ImageButton) view
						.findViewById(R.id.askdetail_owner_card_1_headimagebutton);

				holderAsk.titletext.setText("我有话要说");
				holderAsk.contenttext
						.setText("目击众神死亡的草原上野花一片\n远在远方的风比远方更远\n我的琴声呜咽 泪水全无\n我把这远方的远归还草原\n一个叫木头 一个叫马尾\n我的琴声呜咽 泪水全无\n远方只有在死亡中凝聚野花一片\n明月如镜高悬草原映照千年岁月\n我的琴声呜咽 泪水全无\n只身打马过草原");
				holderAsk.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				fb.display(holderAsk.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderAsk.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderAsk);
				break;

			case VALUE_ANSWER:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.askdetail_owner_card_2, parent, false);
				holderAnswer = new ViewHolderAnswer();
				holderAnswer.contenttext = (TextView) view
						.findViewById(R.id.askdetail_owner_card_2_contenttext);
				holderAnswer.headimage = (CircleImageView) view
						.findViewById(R.id.askdetail_owner_card_2_headimage);
				holderAnswer.headimagebutton = (ImageButton) view
						.findViewById(R.id.askdetail_owner_card_2_headimagebutton);

				holderAnswer.contenttext
						.setText("Google Polymer框架，在2013年的Google I/O大会上发布。其来自加盟谷歌的原Palm webOS的Enyo框架团队打造，使用了最新的浏览器特性。但是对于目前仍然处于开发阶段的框架，又如何吸引开发者去使用呢？以前几乎没有，但是现在Material design推出之后，也许会有所改观。");
				holderAnswer.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				fb.display(holderAnswer.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderAnswer.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderAnswer);
				break;

			case VALUE_MYANSWER:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.askdetail_owner_card_3, parent, false);
				holderMyanswer = new ViewHolderMyanswer();
				holderMyanswer.contenttext = (TextView) view
						.findViewById(R.id.askdetail_owner_card_3_contenttext);
				holderMyanswer.headimage = (CircleImageView) view
						.findViewById(R.id.askdetail_owner_card_3_headimage);
				holderMyanswer.headimagebutton = (ImageButton) view
						.findViewById(R.id.askdetail_owner_card_3_headimagebutton);

				holderMyanswer.contenttext
						.setText("这样的最大好处就是Android 5.0发布之后(好像从11月3号推迟到了11号)，新的一轮应用改版热潮中，web端也能简单的实现与Android端相一致的功能了吧。在以InBox为首的谷歌自家应用全线向Material design风格靠拢的时候，甚至是InBox网页版也做到了和Android端高度一致的时候，Polymer依旧处于开发阶段，其未来存在越来越多的不确定因素。");
				holderMyanswer.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				fb.display(holderMyanswer.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderMyanswer.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				view.setTag(holderMyanswer);
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
				fb.display(holderAsk.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderAsk.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				break;

			case VALUE_ANSWER:
				holderAnswer = (ViewHolderAnswer) view.getTag();
				holderAnswer.contenttext
						.setText("Google Polymer框架，在2013年的Google I/O大会上发布。其来自加盟谷歌的原Palm webOS的Enyo框架团队打造，使用了最新的浏览器特性。但是对于目前仍然处于开发阶段的框架，又如何吸引开发者去使用呢？以前几乎没有，但是现在Material design推出之后，也许会有所改观。");
				holderAnswer.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				fb.display(holderAnswer.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderAnswer.headimagebutton
							.setOnClickListener(itemButtonClickListener);
				}

				break;

			case VALUE_MYANSWER:
				holderMyanswer = (ViewHolderMyanswer) view.getTag();

				holderMyanswer.contenttext
						.setText("这样的最大好处就是Android 5.0发布之后(好像从11月3号推迟到了11号)，新的一轮应用改版热潮中，web端也能简单的实现与Android端相一致的功能了吧。在以InBox为首的谷歌自家应用全线向Material design风格靠拢的时候，甚至是InBox网页版也做到了和Android端高度一致的时候，Polymer依旧处于开发阶段，其未来存在越来越多的不确定因素。");
				holderMyanswer.headimagebutton
						.setImageResource(R.drawable.headbutton_1);
				fb.display(holderMyanswer.headimage,
						"http://p.qq181.com/cms/1210/2012100413195471481.jpg");
				if (itemButtonClickListener != null) {
					holderMyanswer.headimagebutton
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
			type = VALUE_ASK;
		} else if (position % 4 == 0) {
			type = VALUE_MYANSWER;
		} else {
			type = VALUE_ANSWER;
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

	private static class ViewHolderAsk {
		TextView titletext;
		TextView contenttext;
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

	private static class ViewHolderAnswer {
		TextView contenttext;
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

	private static class ViewHolderMyanswer {
		TextView contenttext;
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

}
