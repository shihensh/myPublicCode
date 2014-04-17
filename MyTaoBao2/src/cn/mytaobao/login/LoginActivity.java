package cn.mytaobao.login;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import cn.mytaobao.constant.IMyTaoBao;
import cn.mytaobao.model.User;
import cn.mytaobao.tool.StreamTool;
import cn.mytaobao.tool.Tool;
import cn.mytaobao.ui.R;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Context context = null;
	private AutoCompleteTextView userNmae = null; // �˺�
	private ImageView history_user = null; // ��ʷ��½�û�
	private EditText passWord = null; // ����
	private Button userRegister = null; // ע��
	private Button userLogin = null; // ��½
	private CheckBox autoLogin = null; // ��ס�Զ���½
	private SharedPreferences sp = null;
	private ArrayList<String> names=new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login_page);
		init();
		String temp=sp.getString("names", "");
		if(!temp.equals("")){
		String[] namesArray=temp.substring(1, temp.length()-1).split(",");
		for(String s:namesArray){
			names.add(s.trim());
		}
		System.out.println(names.toString());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,names);
		userNmae.setAdapter(adapter);


		if(sp.getBoolean("autoLogin", false)){		//����ϴ���ѡ���Զ���½
			Tool.ShowMessage(context, "�Զ���¼���ҵ��Ա�������");
			Intent intent = new Intent(context, MyTaoBaoActivity.class);
			startActivity(intent);
			finish();
		}
		
		// ��½��ť�ļ���
		userLogin.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				String name = userNmae.getText().toString();
				String password = passWord.getText().toString();
				String result = "";		//����˷��صĽ��
				String success="";		//�ж��Ƿ��и��û�
				JSONObject item=null;	
				if (name.equals("") || password.equals("")) {
					Tool.ShowMessage(context, "�˻��������벻��Ϊ�գ�");
				} else {

					try {
						result = SubmitDataByGetMethod(name, password); // ��������ύ���ݲ����ط���˵Ľ��
						if(result.equals("0")){
						throw new Exception("�����������쳣��");
						}
						else{
						 item=new JSONObject(result);
						 success = item.getString("success");
						}
						
					} catch (Exception e) {
						Tool.ShowMessage(context, e.getMessage());
						e.printStackTrace();
					}
					if (success.equals("true")) { // �����½�ɹ�
						String juser="";
						try {
							juser = item.getString("user");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Gson gson=new Gson();
						User user=gson.fromJson(juser, User.class);	//����json
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+user.toString());
						if(names.indexOf(user.getuNmae())==-1){	//��������в����������
						names.add(0,user.getuNmae() );
						System.out.println(names.toString());
						if(names.size()==4)
							names.remove(names.size()-1);
						sp.edit().putString("names",names.toString()).commit();
						}
						
						if (autoLogin.isChecked()) { // ����������Զ���½����浽SharedPreferences
							sp.edit()
									.putString("userName", name)
									.putString("passWord", password)
									.putBoolean("autoLogin",
											autoLogin.isChecked()).commit();
						}
						Tool.ShowMessage(context, "��½�ɹ�����ӭ�����Ա����磡");
						//��ת��mytao����
						Intent intent = new Intent(context, MyTaoBaoActivity.class);
						startActivity(intent);
						finish();
					}
					else if(success.equals("false")){
						try {
							String toast=item.getString("error");
							Tool.ShowMessage(context, toast);
						} catch (JSONException e) {
							Tool.ShowMessage(context, "�������쳣��");
							e.printStackTrace();
						}
						
					}
					

				}
			}
		});
		// ע�ᰴť�ļ���
		userRegister.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				// ��ת��ע�����
				Intent intent = new Intent(context, RegisterActivity.class);
				startActivity(intent);
			}
		});
		history_user.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
			AlertDialog.Builder builder=new AlertDialog.Builder(context);
			builder.setTitle("��ѡ���Ա��");
			final String[] values = names.toArray(new String[0]);
			builder.setItems(values, new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
				
					userNmae.setText(values[which]);
					userNmae.setSelection(userNmae.getText().toString().length());
					userNmae.clearListSelection();
				}
			}).create().show();
			}
		});
	}

	private void init() {
		context = this;
		userNmae = (AutoCompleteTextView) this.findViewById(R.id.userName);
		history_user = (ImageView) this.findViewById(R.id.history_user);
		passWord = (EditText) this.findViewById(R.id.passWord);
		userRegister = (Button) this.findViewById(R.id.userRegist);
		userLogin = (Button) this.findViewById(R.id.userLogin);
		autoLogin = (CheckBox) this.findViewById(R.id.autoLogin);
		sp = this.getSharedPreferences("myconfig", Context.MODE_PRIVATE);
		
	}

	// GET��ʽ�ύ�û���½����
	private String SubmitDataByGetMethod(String name, String passWord)
			throws Exception {

		String path =IMyTaoBao.WEB_BASE_SERVER+ "UserServlet?format=json&type=login&name="
				+ URLEncoder.encode(name, "UTF-8") + "&password=" + passWord;
		Log.i("PATH", path);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3 * 1000);
		conn.setRequestMethod("GET");
		String result = "0"; // ��ʾ�����쳣
		if (conn.getResponseCode() == 200) {
			byte[] data = StreamTool.readInputStream(conn.getInputStream());
			result = new String(data);
		}
		return result;
	}

}
