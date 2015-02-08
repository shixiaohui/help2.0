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

	// 3�ֲ�ͬ�Ĳ���
	public static final int VALUE_ASK = 0;// �����ߵĿ�Ƭ
	public static final int VALUE_ANSWER = 1; // �����˵Ļش�
	public static final int VALUE_MYANSWER = 2; // �ҵĻش�

	public AskDetailsOwner_Listview_Adapter(Context context,
			ArrayList<Integer> items, OnClickListener itemButtonClickListener) {
		super(items);
		myList = items;
		this.mContext = context;
		this.itemButtonClickListener = itemButtonClickListener;
		fb = FinalBitmap.create(context);// ��ʼ��FinalBitmapģ��
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

				holderAsk.titletext.setText("���л�Ҫ˵");
				holderAsk.contenttext
						.setText("Ŀ�����������Ĳ�ԭ��Ұ��һƬ\nԶ��Զ���ķ��Զ����Զ\n�ҵ��������� ��ˮȫ��\n�Ұ���Զ����Զ�黹��ԭ\nһ����ľͷ һ������β\n�ҵ��������� ��ˮȫ��\nԶ��ֻ��������������Ұ��һƬ\n�����羵������ԭӳ��ǧ������\n�ҵ��������� ��ˮȫ��\nֻ��������ԭ");
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
						.setText("Google Polymer��ܣ���2013���Google I/O����Ϸ����������Լ��˹ȸ��ԭPalm webOS��Enyo����ŶӴ��죬ʹ�������µ���������ԡ����Ƕ���Ŀǰ��Ȼ���ڿ����׶εĿ�ܣ����������������ȥʹ���أ���ǰ����û�У���������Material design�Ƴ�֮��Ҳ��������Ĺۡ�");
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
						.setText("���������ô�����Android 5.0����֮��(�����11��3���Ƴٵ���11��)���µ�һ��Ӧ�øİ��ȳ��У�web��Ҳ�ܼ򵥵�ʵ����Android����һ�µĹ����˰ɡ�����InBoxΪ�׵Ĺȸ��Լ�Ӧ��ȫ����Material design���£��ʱ��������InBox��ҳ��Ҳ�����˺�Android�˸߶�һ�µ�ʱ��Polymer���ɴ��ڿ����׶Σ���δ������Խ��Խ��Ĳ�ȷ�����ء�");
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
				holderAsk.titletext.setText("���л�Ҫ˵");
				holderAsk.contenttext
						.setText("Ŀ�����������Ĳ�ԭ��Ұ��һƬ\nԶ��Զ���ķ��Զ����Զ\n�ҵ��������� ��ˮȫ��\n�Ұ���Զ����Զ�黹��ԭ\nһ����ľͷ һ������β\n�ҵ��������� ��ˮȫ��\nԶ��ֻ��������������Ұ��һƬ\n�����羵������ԭӳ��ǧ������\n�ҵ��������� ��ˮȫ��\nֻ��������ԭ");
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
						.setText("Google Polymer��ܣ���2013���Google I/O����Ϸ����������Լ��˹ȸ��ԭPalm webOS��Enyo����ŶӴ��죬ʹ�������µ���������ԡ����Ƕ���Ŀǰ��Ȼ���ڿ����׶εĿ�ܣ����������������ȥʹ���أ���ǰ����û�У���������Material design�Ƴ�֮��Ҳ��������Ĺۡ�");
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
						.setText("���������ô�����Android 5.0����֮��(�����11��3���Ƴٵ���11��)���µ�һ��Ӧ�øİ��ȳ��У�web��Ҳ�ܼ򵥵�ʵ����Android����һ�µĹ����˰ɡ�����InBoxΪ�׵Ĺȸ��Լ�Ӧ��ȫ����Material design���£��ʱ��������InBox��ҳ��Ҳ�����˺�Android�˸߶�һ�µ�ʱ��Polymer���ɴ��ڿ����׶Σ���δ������Խ��Խ��Ĳ�ȷ�����ء�");
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
	 * ��������Դ��position������Ҫ��ʾ�ĵ�layout��type
	 * 
	 * type��ֵ�����0��ʼ
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
	 * �������е�layout������
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
