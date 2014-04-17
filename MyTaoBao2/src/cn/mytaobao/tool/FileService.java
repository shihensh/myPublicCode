package cn.mytaobao.tool;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileService {
	private Context context;

	public FileService(Context context) {
		this.context = context;
	}

	/**
	 * * �������ݵ�sd���У���˽���ļ���ʽ���棩 * @param filename �ļ����� *
	 * 
	 * @param content
	 *            �ļ����� *
	 * @throws Exception
	 */
	public void save2sdCard(String fileName, String content) throws Exception {
		File file = new File(Environment.getExternalStorageDirectory(),
				fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(content.getBytes());
		outputStream.close();
	}
	/** 
     * �����ļ� 
     * @param bm 
     * @param fileName 
     * @throws IOException 
     */  
    public void save2sdCard(String fileName,Bitmap bm ) throws IOException {  
    	String sdCardDir = this.getSdCardDirectory();
        File dirFile = new File(sdCardDir);  
        if(!dirFile.exists()){  
            dirFile.mkdir();  
        }  
        File myCaptureFile = new File(sdCardDir ,fileName);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);  
        bos.flush();  
        bos.close();  
    }  
    
    /**
     * �����ļ����ֻ�CacheĿ¼  
     * @param subPath  Cache�µ���Ŀ¼
     * @param fileName Ҫ������ļ��� (����·��)
     * @param bm  //Ҫ�����ͼƬ��Դ
     * @throws IOException
     */
    public void save2CacheDir(String subPath,String fileName,Bitmap bm ) throws IOException {  
    	String cacheDir = this.getCacheDirectory();
        File dirFile = new File(cacheDir+subPath);  
        if(!dirFile.exists()){  
            dirFile.mkdirs();  
        }  
        File myCaptureFile = new File(dirFile ,fileName);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);  
        bos.flush();  
        bos.close();  
    } 
    /**
     * �����ļ����ֻ�CacheĿ¼  
     * @param subPath  Cache�µ���Ŀ¼
     * @param fileName Ҫ������ļ��� (����·��)
     * @param content  //Ҫ���������
     * @throws IOException
     */
    public void save2CacheDir(String subPath,String fileName,String content ) throws IOException {  
    	String cacheDir = this.getCacheDirectory();
        File dirFile = new File(cacheDir+subPath);  
        if(!dirFile.exists()){  
            dirFile.mkdirs();  
        }
        File myCaptureFile = new File(dirFile ,fileName);  
        FileOutputStream outputStream = new FileOutputStream(myCaptureFile); //context.openFileOutput(dirFile.getAbsolutePath()+"/"+fileName,
				//Context.MODE_PRIVATE);
		outputStream.write(content.getBytes());
		outputStream.close();
    }
    /**
     * ��������ָ�����ļ�ת��ΪBitmap��ʽ
     * @param fileName
     * @return
     */
    public Bitmap getBitmap2Cache(String fileName){
    	byte[] data;
		try {
			data = this.read(fileName);
			return this.getBitmapFromByte(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
    	
    }
    
    public String getString2Cache(String fileName){
    	byte[] data;
		try {
			File f = new File(fileName);
			data = this.read(f);
			return new String(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
    	
    }

	/**
	 * * �������ݣ���˽���ļ���ʽ���棩 * @param filename �ļ����� *
	 * 
	 * @param content
	 *            �ļ����� *
	 * @throws Exception
	 */
	public void save(String fileName, String content) throws Exception {
		FileOutputStream outputStream = context.openFileOutput(fileName,
				Context.MODE_PRIVATE);
		outputStream.write(content.getBytes());
		outputStream.close();
	}

	/**
	 * * �������ݣ���˽���ļ���ʽ����+׷�ӣ� *
	 * 
	 * @param filename
	 *            �ļ����� *
	 * 
	 * @param content
	 *            �ļ����� *
	 * @throws Exception
	 */
	public void saveAppend(String fileName, String content) throws Exception {
		FileOutputStream outputStream = context.openFileOutput(fileName,
				Context.MODE_APPEND);
		outputStream.write(content.getBytes());
		outputStream.close();
	}

	/**
	 * �������ݣ���������Ӧ�ö�ȡ��
	 * 
	 * @param filename
	 *            �ļ�����
	 * @param content
	 *            �ļ�����
	 * @throws Exception
	 */
	public void saveReadAble(String fileName, String content) throws Exception {
		FileOutputStream outputStream = context.openFileOutput(fileName,
				Context.MODE_WORLD_READABLE);
		outputStream.write(content.getBytes());
		outputStream.close();
	}

	/**
	 * �ļ���ȡ
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] read(String fileName) throws Exception {
		FileInputStream inputStream = context.openFileInput(fileName);
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {// ��ȡ�ļ���������
			byteArrayOutputStream.write(buffer, 0, len);// д�뵽�ڴ�
		}
		byte[] data = byteArrayOutputStream.toByteArray();// ���ڴ��еĶ���������תΪ����
		byteArrayOutputStream.close();
		inputStream.close();
		return data;
	}
	
	public byte[] read(File f) throws Exception {
		FileInputStream inputStream =  new FileInputStream(f);//context.openFileInput(fileName);
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {// ��ȡ�ļ���������
			byteArrayOutputStream.write(buffer, 0, len);// д�뵽�ڴ�
		}
		byte[] data = byteArrayOutputStream.toByteArray();// ���ڴ��еĶ���������תΪ����
		byteArrayOutputStream.close();
		inputStream.close();
		return data;
	}

	
	/**
	 * �����ⲿ�洢���Ƿ����
	 */
	public boolean isExternalStorageAvailable() {
		boolean state = false;
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
			state = true;
		}
		return state;
	}

	/**
	 * �����ⲿ�洢���Ƿ�ֻ�ɶ�
	 */
	public boolean isExternalStorageReadOnly() {
		boolean state = false;
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
			state = true;
		}
		return state;
	}
	/**
	 * ��ȡ�ֻ�����·��
	 * @return
	 */
	public  String getCacheDirectory() {
		String cacheDirPath = null;

		File cacheDir = this.context.getCacheDir();// getCacheDir();
		if (cacheDir != null) {
			cacheDirPath = cacheDir.getAbsolutePath();
		}
		return cacheDirPath;
	}

	/**
	 * ��ȡ�ֻ�SdCard·��
	 * @return
	 */
	public String getSdCardDirectory() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();// ��ȡSDCardĿ¼
			return sdCardDir.getAbsolutePath();
		}
		return "";
	}
	
	// �õ��洢�����ݿ��е�ͼƬ
	// eg imageView.setImageBitmap(bitmapobj);
	public  Bitmap getBitmapFromByte(byte[] temp) {
		if (temp != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			return bitmap;
		} else {
			// Bitmap bitmap=BitmapFactory.decodeResource(getResources(),
			// R.drawable.contact_add_icon);
			return null;
		}
	}


}
