package cn.mytaobao.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

/**
 * 处理图片的工具类.
 * 
 */
public class ImageTools {

	/** */
	/**
	 * 图片去色,返回灰度图片
	 * 
	 * @param bmpOriginal
	 *            传入的图片
	 * @return 去色后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	/** */
	/**
	 * 去色同时加圆角
	 * 
	 * @param bmpOriginal
	 *            原图
	 * @param pixels
	 *            圆角弧度
	 * @return 修改后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
		return toRoundCorner(toGrayscale(bmpOriginal), pixels);
	}

	/** */
	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 *            需要修改的图片
	 * @param pixels
	 *            圆角的弧度
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/** */
	/**
	 * 使圆角功能支持BitampDrawable
	 * 
	 * @param bitmapDrawable
	 * @param pixels
	 * @return
	 */
	public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,
			int pixels) {
		Bitmap bitmap = bitmapDrawable.getBitmap();
		bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
		return bitmapDrawable;
	}

	/**
	 * 读取路径中的图片，然后将其转化为缩放后的bitmap
	 * 
	 * @param path
	 */
	public static void saveBefore(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回bm为空
		options.inJustDecodeBounds = false;
		// 计算缩放比
		int be = (int) (options.outHeight / (float) 200);
		if (be <= 0)
			be = 1;
		options.inSampleSize = 2; // 图片长宽各缩小二分之一
		// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
		bitmap = BitmapFactory.decodeFile(path, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println(w + "   " + h);
		// savePNG_After(bitmap,path);
		saveJPGE_After(bitmap, path);
	}

	/**
	 * 保存图片为PNG
	 * 
	 * @param bitmap
	 * @param name
	 */
	public static void savePNG_After(Bitmap bitmap, String name) {
		File file = new File(name);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存图片为JPEG
	 * 
	 * @param bitmap
	 * @param path
	 */
	public static void saveJPGE_After(Bitmap bitmap, String path) {
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片合成
	 * 
	 * @param bitmap
	 * @return
	 */
	private Bitmap createBitmap(Bitmap src, Bitmap watermark) {
		if (src == null) {
			return null;
		}
		int w = src.getWidth();
		int h = src.getHeight();
		int ww = watermark.getWidth();
		int wh = watermark.getHeight();
		// create the new blank bitmap
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(newb);
		// draw src into
		cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
		// draw watermark into
		cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// 在src的右下角画入水印
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// store
		cv.restore();// 存储
		return newb;
	}

	// 将图片转换成byte[]以便能将其存到数据库
	public static byte[] getByteFromBitmap(Bitmap bitmap) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			// Log.e(TAG, "transform byte exception");
		}
		return out.toByteArray();
	}

	// 得到存储在数据库中的图片
	// eg imageView.setImageBitmap(bitmapobj);
	public static Bitmap getBitmapFromByte(byte[] temp) {
		if (temp != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			return bitmap;
		} else {
			// Bitmap bitmap=BitmapFactory.decodeResource(getResources(),
			// R.drawable.contact_add_icon);
			return null;
		}
	}
    //将手机中的文件转换为Bitmap类型
	public static Bitmap getBitemapFromFile(File f) {
		if (!f.exists())
			return null;
		try {
			return BitmapFactory.decodeFile(f.getAbsolutePath());
		} catch (Exception ex) {
			return null;
		}
	}
	//将手机中的文件转换为Bitmap类型(重载+1)
	public static Bitmap getBitemapFromFile(String fileName) {
		
		try {
			return BitmapFactory.decodeFile(fileName);
		} catch (Exception ex) {
			return null;
		}
	}
	
	//将Drawable转化为Bitmap;(版本1)
	public static Bitmap drawableToBitmap1(Drawable drawable) {  
        
        Bitmap bitmap = Bitmap.createBitmap(  
                                   drawable.getIntrinsicWidth(),  
                                   drawable.getIntrinsicHeight(),  
                                   drawable.getOpacity() != PixelFormat.OPAQUE ?   
  
                                   Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);  
        return bitmap;  
   } 
	
	//将Drawable转化为Bitmap;(版本2)
	public static Bitmap drawableToBitmap2(Drawable drawable) {  
        
		BitmapDrawable bd = (BitmapDrawable)drawable;  
		Bitmap bm = bd.getBitmap(); 
        return bm;  
    }
	
	//将Bitmap转化为Drawable
	public static Drawable bitmapToDrawable(Bitmap bm) {  
		 BitmapDrawable bd= new BitmapDrawable(bm);
		 return bd;
    } 
	//将InputStream转化为byte[]
	private static byte[] streamToBytes(InputStream is) {  
	       ByteArrayOutputStream os = new ByteArrayOutputStream(1024);  
	       byte[] buffer = new byte[1024];  
	       int len;  
	       try {  
	           while ((len = is.read(buffer)) >= 0) {  
	               os.write(buffer, 0, len);  
	           }  
	       } catch (java.io.IOException e) {  
	       }  
	       return os.toByteArray();  
	 } 
	
	//将资源文件转化为Bitmap输出
	public static Bitmap getBitmapFromRes(Context context, int resourceId){
		InputStream inputStream = context.getResources().openRawResource(resourceId);
		byte[] result = streamToBytes(inputStream);
		return getBitmapFromByte(result);
	}
	
	//将资源文件转化为byte[]输出
	public static byte[] getBytesFromRes(Context context, int resourceId){
		InputStream inputStream = context.getResources().openRawResource(resourceId);
		byte[] result = streamToBytes(inputStream);
		return result;
	}
	
	


}