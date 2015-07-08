package me.kaede.tagview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Utils {

	public static int dpToPx(Context c, float dipValue) {
		DisplayMetrics metrics = c.getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
	}
	
	public static int spToPx(Context context, float spValue) {  
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics);
    }
}
