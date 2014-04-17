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
	private EditText name = null; // 注册用户名字
	private EditText passWord = null; // 用户密码
	private EditText passWordAgain = null; // 再次确定
	private EditText address = null; // 详细地址
	private EditText email = null; // 电子邮箱
	private EditText postCode = null; // 邮编
	private EditText phone = null; // 手机号码
	private Button btnKeyCode = null; // 获取动态密码按钮
	private EditText keyCode = null; // 动态密码
	private EditText etCode = null; // 验证码
	private TextView radomNum = null; // 生成验证码
	private Button btnRegister = null; // 提交按钮
	private Button btnReturn = null; // 返回按钮
	private CheckBox agree = null; // 同意淘宝协议的选项
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
		// 验证码点击监听 换一组验证码
		radomNum.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				radomNum.setText(getRandNum() + "");
			}
		});
		// 提交按钮监听
		btnRegister.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				getData(); // 取各控件上的内容
				// 验证邮箱
				boolean b = Pattern
						.matches(
								"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
								email.getText().toString().trim());
				if (rName.equals("") || rPassWord.equals("")
						|| rPhone.equals("") || rEmail.equals("")) { // 账户、密码、电话、邮箱不能为空
					Tool.ShowMessage(context, "还有未填项！");
				} else if (!rPassWord.equals(rPassWordAgain)) {
					Tool.ShowMessage(context, "请确定输入密码一致！");
				} else if (!b) {
					Tool.ShowMessage(context, "邮箱格式不正确！");
				} else if (!etCode.getText().toString()
						.equals(radomNum.getText().toString())) {
					Tool.ShowMessage(context, "您输入的验证码有误，请重新输入！");
					etCode.setText(""); // 清空验证码输入框
					radomNum.setText(getRandNum() + ""); // 重新生成验证码
				} else if (!agree.isChecked()) {
					Tool.ShowMessage(context, "您还没同意淘宝协议！");
				} else { // 提交到服务器
					try {
						SubmitDataByPOSTMethod();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// 返回按钮的监听
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
		radomNum.setText(getRandNum() + ""); // 初始化验证码
	}

	// GET方式提交数据
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

	// POST方式提交数据
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
		conn.setDoOutput(true); // 允许对外发送请求参数
		conn.setUseCaches(false); // 不进行缓存
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("POST");
		// 下面设置http请求头
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
		// 发送参数
		DataOutputStream outStream = new DataOutputStream(
				conn.getOutputStream());
		outStream.write(data); // 把参数发送出去
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
				Tool.ShowMessage(context, "成功注册！");
			} else {
				/*Tool.ShowMessage(context, "注册成功，欢迎来到淘宝世界！");
				// 跳转到mytao界面
				Intent intent = new Intent(context, MyTaoBaoActivity.class);
				startActivity(intent);
				finish();*/
				Tool.ShowMessage(context, "注册失败，请检查输入！");
			}

		} else {
			Tool.ShowMessage(context, "服务器端有异常！");
		}

	}

	/**
	 * 获取文本框中的内容
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
	 * 验证码生成器
	 * 
	 * @return 4位随机验证码
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
