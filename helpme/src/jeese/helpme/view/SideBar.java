package jeese.helpme.view;

import jeese.helpme.R;
import jeese.helpme.util.Dp2Px;
import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View {
	// �����¼�
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	// 26����ĸ
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private int choose = -1;// ѡ��
	private Paint paint = new Paint();

	private TextView mTextDialog;

	private Context mcontext;

	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mcontext = context;
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mcontext = context;
	}

	public SideBar(Context context) {
		super(context);
		mcontext = context;
	}

	/**
	 * ��д�������
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ��ȡ����ı䱳����ɫ.
		int height = getHeight();// ��ȡ��Ӧ�߶�
		int width = getWidth(); // ��ȡ��Ӧ���
		int singleHeight = height / (b.length + 1);// ��ȡÿһ����ĸ�ĸ߶�

		for (int i = 0; i < b.length; i++) {
			paint.setColor(Color.parseColor("#565656"));
			paint.setTypeface(Typeface.DEFAULT);
			paint.setAntiAlias(true);
			paint.setTextSize(Dp2Px.dp2px(mcontext, 14));
			// ѡ�е�״̬
			if (i == choose) {
				paint.setColor(Color.parseColor("#ffffff"));
				paint.setFakeBoldText(true);
			}
			// x��������м�-�ַ�����ȵ�һ��.
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			float yPos = (singleHeight * i) + (singleHeight * (float) 1.5);
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();// ���û���
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();// ���y����
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * b.length);// ���y������ռ�ܸ߶ȵı���*b����ĳ��Ⱦ͵��ڵ��b�еĸ���.

		switch (action) {
		case MotionEvent.ACTION_UP:// ̧��
			setBackgroundColor(Color.TRANSPARENT);
			choose = -1;//
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			setBackgroundResource(R.drawable.sidebar_background);
			if (oldChoose != c) {
				if (c >= 0 && c < b.length) {
					if (listener != null) {
						listener.onTouchingLetterChanged(b[c]);
					}
					if (mTextDialog != null) {
						mTextDialog.setText(b[c]);
						mTextDialog.setVisibility(View.VISIBLE);
					}

					choose = c;
					invalidate();
				}
			}

			break;
		}
		return true;
	}

	/**
	 * ���⹫���ķ���
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * �ӿ�
	 * 
	 * @author coder
	 * 
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}