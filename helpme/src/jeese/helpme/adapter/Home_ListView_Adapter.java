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

	// 2�ֲ�ͬ�Ĳ���
	public static final int VALUE_ASK = 0;// ���ʵĿ�Ƭ
	public static final int VALUE_HELP = 1; // �����Ŀ�Ƭ

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

				holderAsk.titletext.setText("���л�Ҫ˵");
				holderAsk.contenttext
						.setText("Ŀ�����������Ĳ�ԭ��Ұ��һƬ\nԶ��Զ���ķ��Զ����Զ\n�ҵ��������� ��ˮȫ��\n�Ұ���Զ����Զ�黹��ԭ\nһ����ľͷ һ������β\n�ҵ��������� ��ˮȫ��\nԶ��ֻ��������������Ұ��һƬ\n�����羵������ԭӳ��ǧ������\n�ҵ��������� ��ˮȫ��\nֻ��������ԭ");
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

				holderHelp.titletext.setText("�����ǵػ��������������������ʲô�����������");
				holderHelp.contenttext
						.setText("Google Polymer��ܣ���2013���Google I/O����Ϸ����������Լ��˹ȸ��ԭPalm webOS��Enyo����ŶӴ��죬ʹ�������µ���������ԡ����Ƕ���Ŀǰ��Ȼ���ڿ����׶εĿ�ܣ����������������ȥʹ���أ���ǰ����û�У���������Material design�Ƴ�֮��Ҳ��������Ĺۡ�");
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
				holderAsk.titletext.setText("���л�Ҫ˵");
				holderAsk.contenttext
						.setText("Ŀ�����������Ĳ�ԭ��Ұ��һƬ\nԶ��Զ���ķ��Զ����Զ\n�ҵ��������� ��ˮȫ��\n�Ұ���Զ����Զ�黹��ԭ\nһ����ľͷ һ������β\n�ҵ��������� ��ˮȫ��\nԶ��ֻ��������������Ұ��һƬ\n�����羵������ԭӳ��ǧ������\n�ҵ��������� ��ˮȫ��\nֻ��������ԭ");
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
				holderHelp.titletext.setText("�����ǵػ��������������������ʲô�����������");
				holderHelp.contenttext
						.setText("Google Polymer��ܣ���2013���Google I/O����Ϸ����������Լ��˹ȸ��ԭPalm webOS��Enyo����ŶӴ��죬ʹ�������µ���������ԡ����Ƕ���Ŀǰ��Ȼ���ڿ����׶εĿ�ܣ����������������ȥʹ���أ���ǰ����û�У���������Material design�Ƴ�֮��Ҳ��������Ĺۡ�");
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
	 * ��������Դ��position������Ҫ��ʾ�ĵ�layout��type
	 * 
	 * type��ֵ�����0��ʼ
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
	 * �������е�layout������
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
