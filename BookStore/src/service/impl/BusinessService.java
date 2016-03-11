package service.impl;

import java.util.List;

import domain.Category;

public interface BusinessService {

	/**添加分类**/
	void addCategory(Category category);

	/**查找分类**/
	Category findCategory(String id);

	/**得到所有分类**/
	List<Category> getAllCategory();

}