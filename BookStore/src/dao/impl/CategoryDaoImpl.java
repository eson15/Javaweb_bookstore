package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.CategoryDao;
import domain.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	/* (non-Javadoc)
	 * @see dao.impl.CategoryDao#add(domain.Category)
	 */
	@Override
	public void add(Category category){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object params[] = {category.getId(), category.getName(), category.getDescription()};
			runner.update(sql, params);
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.CategoryDao#find(java.lang.String)
	 */
	@Override
	public Category find(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category where id=?";
			return (Category)runner.query(sql, id, new BeanHandler(Category.class));
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.impl.CategoryDao#getAll()
	 */
	@Override
	public List<Category> getAll(){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category";
			return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}

}
