package jeese.helpme.util;

import android.content.Context;

public class Dp2Px {
	
	public static int dp2px(Context context, float dp){
		final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dp * scale + 0.5f); 
	}

}
