package cn.mytaobao.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class StreamTool {

	/**
	 * 从输入流中获取数据
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static Bitmap getURLBitmap(String uriPic)
	  {
	    URL imageUrl = null;
	    Bitmap bitmap = null;
	    try
	    {
	      /* new URL对象将网址传入 */
	      imageUrl = new URL(uriPic);
	    } catch (MalformedURLException e)
	    {
	      e.printStackTrace();
	    }
	    try
	    {
	      /* 取得联机 */
	      HttpURLConnection conn = (HttpURLConnection) imageUrl
	          .openConnection();
	      conn.connect();
	      /* 取得回传的InputStream */
	      InputStream is = conn.getInputStream();
	      /* 将InputStream变成Bitmap */
	      bitmap = BitmapFactory.decodeStream(is);
	      /* 关闭InputStream */
	      is.close();
	      
	    } catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return bitmap;
	  }
}
