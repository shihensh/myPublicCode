package cn.mytaobao.login;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


import cn.mytaobao.constant.IMyTaoBao;
import cn.mytaobao.model.User;
import cn.mytaobao.tool.StreamTool;
import cn.mytaobao.tool.Tool;
import cn.mytaobao.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Context context = null;
	private EditText name = null; // ע���û�����
	private EditText passWord = null; // �û�����
	private EditText passWordAgain = null; // �ٴ�ȷ��
	private EditText address = null; // ��ϸ��ַ
	private EditText email = null; // ��������
	private EditText postCode = null; // �ʱ�
	private EditText phone = null; // �ֻ�����
	private Button btnKeyCode = null; // ��ȡ��̬���밴ť
	private EditText keyCode = null; // ��̬����
	private EditText etCode = null; // ��֤��
	private TextView radomNum = null; // ������֤��
	private Button btnRegister = null; // �ύ��ť
	private Button btnReturn = null; // ���ذ�ť
	private CheckBox agree = null; // ͬ���Ա�Э���ѡ��
	private String rName = null;
	private String rPassWord = null;
	private String rPassWordAgain = null;
	private String rPhone = null;
	private String rAddress = null;
	private String rEmail = null;
	private String rPostCode = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page);
		init();
		// ��֤�������� ��һ����֤��
		radomNum.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				radomNum.setText(getRandNum() + "");
			}
		});
		// �ύ��ť����
		btnRegister.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				getData(); // ȡ���ؼ��ϵ�����
				// ��֤����
				boolean b = Pattern
						.matches(
								"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
								email.getText().toString().trim());
				if (rName.equals("") || rPassWord.equals("")
						|| rPhone.equals("") || rEmail.equals("")) { // �˻������롢�绰�����䲻��Ϊ��
					Tool.ShowMessage(context, "����δ���");
				} else if (!rPassWord.equals(rPassWordAgain)) {
					Tool.ShowMessage(context, "��ȷ����������һ�£�");
				} else if (!b) {
					Tool.ShowMessage(context, "�����ʽ����ȷ��");
				} else if (!etCode.getText().toString()
						.equals(radomNum.getText().toString())) {
					Tool.ShowMessage(context, "���������֤���������������룡");
					etCode.setText(""); // �����֤�������
					radomNum.setText(getRandNum() + ""); // ����������֤��
				} else if (!agree.isChecked()) {
					Tool.ShowMessage(context, "����ûͬ���Ա�Э�飡");
				} else { // �ύ��������
					try {
						SubmitDataByPOSTMethod();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// ���ذ�ť�ļ���
		btnReturn.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void init() {
		context = this;
		name = (EditText) this.findViewById(R.id.registerName);
		passWord = (EditText) this.findViewById(R.id.registerPassWord);
		passWordAgain = (EditText) this.findViewById(R.id.passWordAgain);
		address = (EditText) this.findViewById(R.id.registerAdress);
		email = (EditText) this.findViewById(R.id.registerEmail);
		postCode = (EditText) this.findViewById(R.id.registerPostCode);
		phone = (EditText) this.findViewById(R.id.registerPhone);
		btnKeyCode = (Button) this.findViewById(R.id.getKingKey);
		etCode = (EditText) this.findViewById(R.id.etCode);
		radomNum = (TextView) this.findViewById(R.id.radomnum);
		btnReturn = (Button) this.findViewById(R.id.userReturn);
		btnRegister = (Button) this.findViewById(R.id.userRegist);
		agree = (CheckBox) this.findViewById(R.id.agree);
		radomNum.setText(getRandNum() + ""); // ��ʼ����֤��
	}

	// GET��ʽ�ύ����
	private void SubmitDataByGetMethod() throws Exception {
		String path = "http://192.168.7.113:8080/TaoBaoWeb/UserServlet?type=insert&name="
				+ URLEncoder.encode("hdhdh", "UTF-8") + "&password=123";
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			byte[] data = StreamTool.readInputStream(conn.getInputStream());
			String result = new String(data);
			Toast.makeText(RegisterActivity.this, result, 3000).show();
		}
	}

	// POST��ʽ�ύ����
	private void SubmitDataByPOSTMethod() throws Exception {
		String path = IMyTaoBao.WEB_BASE_SERVER+"TaoBaoWeb/UserServlet?";

		String params = "format=json" + "&type=insert" + "&name="
				+ URLEncoder.encode(rName, "UTF-8") + "&password=" + rPassWord
				+ "&phone=" + rPhone + "&address="
				+ URLEncoder.encode(rAddress, "UTF-8") + "&email=" + rEmail
				+ "&postCode=" + rPostCode;
		byte[] data = params.getBytes();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true); // ������ⷢ���������
		conn.setUseCaches(false); // �����л���
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("POST");
		// ��������http����ͷ
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.setRequestProperty(
				"Accept",
				"image/gif, image/jpeg, image/pjpeg,image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/vnd.application/x-ms-application, application/vnd.ms-excel, ms-powerpoint,application/msword, */*");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
		conn.setRequestProperty("Connection", "Keep-Alive");
		// ���Ͳ���
		DataOutputStream outStream = new DataOutputStream(
				conn.getOutputStream());
		outStream.write(data); // �Ѳ������ͳ�ȥ
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			byte[] returndata = StreamTool.readInputStream(conn
					.getInputStream());
			String result = new String(returndata);
			JSONObject item = new JSONObject(result);
			String success = item.getString("success");
			if (success.equals("true")) {
				String juser = item.getString("user");
				Gson gson = new Gson();
				User user = gson.fromJson(juser, User.class);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"
						+ user.toString());
				Tool.ShowMessage(context, "�ɹ�ע�ᣡ");
			} else {
				/*Tool.ShowMessage(context, "ע��ɹ�����ӭ�����Ա����磡");
				// ��ת��mytao����
				Intent intent = new Intent(context, MyTaoBaoActivity.class);
				startActivity(intent);
				finish();*/
				Tool.ShowMessage(context, "ע��ʧ�ܣ��������룡");
			}

		} else {
			Tool.ShowMessage(context, "�����������쳣��");
		}

	}

	/**
	 * ��ȡ�ı����е�����
	 */
	private void getData() {
		rName = name.getText().toString();
		rPassWord = passWord.getText().toString();
		rPassWordAgain = passWordAgain.getText().toString();
		rPhone = phone.getText().toString();
		rAddress = address.getText().toString();
		rEmail = email.getText().toString();
		rPostCode = postCode.getText().toString();

	}

	/**
	 * ��֤��������
	 * 
	 * @return 4λ�����֤��
	 */
	private int getRandNum() {
		Random rand = new Random(System.currentTimeMillis());
		int radomNum = 0;
		while (radomNum < 1000 || radomNum > 10000) {
			radomNum = (int) (rand.nextFloat() * 10000);
		}
		return radomNum;
	}
}
