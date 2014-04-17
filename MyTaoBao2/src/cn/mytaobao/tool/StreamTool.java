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
	 * ���������л�ȡ����
	 * @param inStream ������
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
	      /* new URL������ַ���� */
	      imageUrl = new URL(uriPic);
	    } catch (MalformedURLException e)
	    {
	      e.printStackTrace();
	    }
	    try
	    {
	      /* ȡ������ */
	      HttpURLConnection conn = (HttpURLConnection) imageUrl
	          .openConnection();
	      conn.connect();
	      /* ȡ�ûش���InputStream */
	      InputStream is = conn.getInputStream();
	      /* ��InputStream���Bitmap */
	      bitmap = BitmapFactory.decodeStream(is);
	      /* �ر�InputStream */
	      is.close();
	      
	    } catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return bitmap;
	  }
}
