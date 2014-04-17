package cn.mytaobao.dao;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.mytaobao.constant.IMyTaoBao;
import cn.mytaobao.model.Category;
import cn.mytaobao.model.Product;
import cn.mytaobao.tool.StreamTool;

public class ProductService {
	public List<Product> getProductByCategoryByPage(int pageIndex,
			int pageSize, int parentId) {
		Log.i("products", "2222");
		// List<Product> products = new ArrayList<Product>();
		try {
			String path = IMyTaoBao.WEB_BASE_SERVER+"ProductServlet?format=json&type=getProductByCategoryByPage&pageIndex="
					+ pageIndex + "&pageSize=" + pageSize;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();
			byte[] data = null;

			Log.i("products",
					"conn.getResponseCode():" + conn.getResponseCode());
			if (conn.getResponseCode() == 200) {
				Log.i("products", "1111111");
				data = StreamTool.readInputStream(inStream);
				Log.i("products", data + "");
			}
			String json = new String(data);

			Type typeList = new TypeToken<ArrayList<Product>>() {
			}.getType();
			Gson gson = new Gson();
			List<Product> products = gson.fromJson(json, typeList);

			// JSONArray array = new JSONArray(json);
			//
			// for (int i = 0; i < array.length(); i++) {
			// JSONObject item = array.getJSONObject(i);
			// int id = item.getInt("pId");
			// String pName = item.getString("pName");
			//
			// JSONObject jsonObject = item.getJSONObject("category");
			// Category category = new Category();
			// category.setcId(jsonObject.getInt("cId"));
			// category.setcName(jsonObject.getString("cName"));
			// category.setParentId(jsonObject.getInt("parentId"));
			// category.setOrderId(jsonObject.getInt("orderId"));
			//
			// String pImage = item.getString("pImage");
			// Double pPrice = item.getDouble("pPrice");
			// String note = item.getString("Note");
			// products.add(new Product(id, pName, category, pImage, pPrice,
			// note));
			// }
			return products;
		} catch (Exception e) {
			Log.i("products", "e.printStackTrace():" + e);

		}
		return null;
	}

}
