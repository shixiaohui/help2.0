package jeese.helpme.view;

import android.content.Context;   
import android.graphics.Canvas;  
import android.graphics.Path;
import android.util.AttributeSet;  
import android.view.View;
import android.widget.ImageView;  

public class CircleImageView extends ImageView {
    private Path mPath = new Path();

    public interface LockScreenLayoutListener {
        public void onUnLock();
    }

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cx = getMeasuredWidth() / 2;
        float cy = getMeasuredHeight() / 2;
        float cr = cx < cy ? cx : cy;

        mPath.reset();
        mPath.addCircle(cx, cy, cr, Path.Direction.CCW);
        canvas.clipPath(mPath);
        super.onDraw(canvas);
        mPath.reset();

    }

}  