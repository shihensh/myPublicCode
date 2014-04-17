package cn.mytaobao.biz;

import java.util.List;

import cn.mytaobao.dao.ProductService;
import cn.mytaobao.model.Product;

public class ProductManager {
	private ProductService dao;

	public ProductManager() {
		dao = new ProductService();
	}

	public List<Product> getProductByCategoryByPage(int pageIndex,
			int pageSize, int parentId) {
		return dao.getProductByCategoryByPage(pageIndex, pageSize, parentId);
	}

}
