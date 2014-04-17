package cn.mytaobao.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

public class Tool {
   public static void ShowMessage(Context context,String msg){
	   Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
   }
	
	
	
	//get sysTime
	public String getSysNowTime(){
		Date now=new Date();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		String formatTime=format.format(now);
		return formatTime;
	}
	
}
