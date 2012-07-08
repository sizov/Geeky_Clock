package com.syzov.geekyClock.utils;

import android.content.Context;
import android.content.res.Resources;

import com.syzov.geekyClock.R;

public class ColorUtils {
	
	/** Retrieves drawable ID resource for LED by string of color.*/
	static public int getLedDrawableIdByColorString(String colorString, Context context){
		Resources resources = context.getResources();
		
		if(colorString == resources.getString(R.string.blackColor)){
			return R.drawable.dark_black_glossy_18px;
		}
		else if(colorString==resources.getString(R.string.whiteColor)){
			return R.drawable.white_glossy_18px;
		}
		else if(colorString==resources.getString(R.string.blueColor)){
			return R.drawable.lighy_blue_glossy_18px;
		}
		else if(colorString==resources.getString(R.string.greenColor)){
			return R.drawable.green_glossy_18px;
		}
		else if(colorString==resources.getString(R.string.redColor)){
			return R.drawable.red_glossy_18px;
		}
		else if(colorString==resources.getString(R.string.yellowColor)){
			return R.drawable.yellow_glossy_18px;
		}
		
		return 0;		
	}
	
	/** Retrieves drawable resource ID for background by string of color.*/
	static public int getBackgroundDrawableIdByColorString(String colorString, Context context){
		Resources resources = context.getResources();
		
		if(colorString == resources.getString(R.string.blackColor)){
			return R.drawable.appwidget_bg_clickable;
		}
		else if(colorString==resources.getString(R.string.whiteColor)){
			return R.drawable.appwidget_white_clickable;
		}
		return 0;		
	}


}
