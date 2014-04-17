package cn.mytaobao.tool;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class HttpFormUtil {
	public static String post(String actionUrl, Map<String, String> params,
			 FormFile[] files) {
				    try {	        
				        String BOUNDARY = "---------7d4a6d158c9";      //数据分隔线
				        String MULTIPART_FORM_DATA = "multipart/form-data";
				        
				        URL url = new URL(actionUrl);
				        HttpURLConnection conn = (HttpURLConnection) 
			url.openConnection();
				        conn.setConnectTimeout(5* 1000);
				        conn.setDoInput(true);                   	//允许输入
				        conn.setDoOutput(true);                    	//允许输出
				        conn.setUseCaches(false);                  	//不使用Cache
				        conn.setRequestMethod("POST");	        
				        conn.setRequestProperty("Connection", "Keep-Alive");
				        conn.setRequestProperty("Charset", "UTF-8");
				        conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA 
			+ "; boundary=" + BOUNDARY);

				        StringBuilder sb = new StringBuilder();
				        for (Map.Entry<String, String> entry : params.entrySet()) {
			//构建表单字段内容
				            sb.append("--");
				            sb.append(BOUNDARY);
				            sb.append("\r\n");
				            sb.append("Content-Disposition: form-data; name=\""+ 
			entry.getKey() + "\"\r\n\r\n");
				            sb.append(entry.getValue());
				            sb.append("\r\n");
				        }
				        DataOutputStream outStream = new DataOutputStream
			(conn.getOutputStream());
				        outStream.write(sb.toString().getBytes());	//发送表单字段数据
				        for(FormFile file : files){               		//发送文件数据
				        	StringBuilder split = new StringBuilder();
				 	        split.append("--");
				 	        split.append(BOUNDARY);
				 	        split.append("\r\n");
				 	        split.append("Content-Disposition: form-data;name=\""+ 
		file.getFilname()+"\";filename=\""+ file.getFilname() + "\"\r\n");
				 	        split.append("Content-Type: "+ file.getContentType()+"\r\n\r\n");
				 	        outStream.write(split.toString().getBytes());
				 	        if(file.getInStream()!=null){
				 	        	byte[] buffer = new byte[1024];
				 	        	int len = 0;
				 	        	while((len = file.getInStream().read(buffer))!=-1){
				 	        		outStream.write(buffer, 0, len);
				 	        	}
				 	        	file.getInStream().close();
				 	        }else{
				 	        	outStream.write(file.getData(), 0, file.getData().length);
				 	        }
				 	        outStream.write("\r\n".getBytes());
				        }
				        byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();
			//数据结束标志	       
				        outStream.write(end_data);
				        outStream.flush();
				        int cah = conn.getResponseCode();
				        if (cah != 200) throw new RuntimeException("请求url失败");
				        InputStream is = conn.getInputStream();
				        int ch;
				        StringBuilder b = new StringBuilder();
				        while( (ch = is.read()) != -1 ){
				        	b.append((char)ch);
				        }	        
				        outStream.close();
				        conn.disconnect();
				        return b.toString();
				    } catch (Exception e) {
				    	throw new RuntimeException(e);
				    }
				}
				
				public static String post(String actionUrl, Map<String, String> params, 
			FormFile file) {
				   return post(actionUrl, params, new FormFile[]{file});
				}
}
