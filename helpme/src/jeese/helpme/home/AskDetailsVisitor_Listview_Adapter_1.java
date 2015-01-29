package jeese.helpme.home;

import java.util.ArrayList;

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

import com.haarman.listviewanimations.ArrayAdapter;

public class AskDetailsVisitor_Listview_Adapter_1 extends ArrayAdapter<Integer> {

	private Context mContext;
	private final OnClickListener itemButtonClickListener;
	private FinalBitmap fb;

	public AskDetailsVisitor_Listview_Adapter_1(Context context,
			ArrayList<Integer> items, OnClickListener itemButtonClickListener) {
		super(items);
		this.mContext = context;
		this.itemButtonClickListener = itemButtonClickListener;
		fb = FinalBitmap.create(context);// ³õÊ¼»¯FinalBitmapÄ£¿é
		fb.configLoadingImage(R.drawable.user_head);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(
					R.layout.askdetail_visitor_listviewitem, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) view
					.findViewById(R.id.askdetail_visitor_listviewitem_textview);
			viewHolder.headimage = (CircleImageView) view
					.findViewById(R.id.askdetail_visitor_listviewitem_headimage);
			viewHolder.headimagebutton = (ImageButton) view
					.findViewById(R.id.askdetail_visitor_listviewitem_headimagebutton);
			view.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.headimagebutton.setImageResource(R.drawable.headbutton_1);
		fb.display(viewHolder.headimage,
				"http://p.qq181.com/cms/1210/2012100413195471481.jpg");

		if (itemButtonClickListener != null) {
			viewHolder.headimagebutton
					.setOnClickListener(itemButtonClickListener);
		}

		return view;
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	private static class ViewHolder {
		TextView textView;
		CircleImageView headimage;
		ImageButton headimagebutton;
	}

}
