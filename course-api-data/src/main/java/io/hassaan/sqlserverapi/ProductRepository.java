package io.hassaan.sqlserverapi;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import io.hassaan.db.SqlDbHelper;

public class ProductRepository {

	SqlDbHelper sdbc;

	public ProductRepository(String connStr, String username, String password) {
		sdbc = new SqlDbHelper(connStr, username, password);
	}

	public List<Product> getProducts() throws Exception {

		List<Product> products = new ArrayList<Product>();

		try {
			sdbc.createConnection();
			String command = "select * from Products (nolock)";
			ResultSet rs = sdbc.executeQuery(command);
			while (rs.next()) {
				Product p = new Product(rs.getLong("Id"), rs.getString("Name"));
				products.add(p);
			}

			return products;

		} catch (Exception e) {
			throw e;
		} finally {
			sdbc.closeConnection();
		}
	}

	public Product getProductById(long id) throws Exception {
		Product product = null;
		try {
			sdbc.createConnection();
			String command = "select * from Products (nolock) where Id = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			ResultSet rs = sdbc.executeQuery(command, params);
			while (rs.next()) {
				product = new Product(rs.getLong("Id"), rs.getString("Name"));
			}
			return product;
		} catch (Exception e) {
			throw e;
		} finally {
			sdbc.closeConnection();
		}
	}

	public List<Product> getProductByName(String name) throws Exception {
		List<Product> products = new ArrayList<Product>();
		try {
			sdbc.createConnection();
			String command = "select * from Products (nolock) where Name = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(name);
			ResultSet rs = sdbc.executeQuery(command, params);
			while (rs.next()) {
				products.add(new Product(rs.getLong("Id"), rs.getString("Name")));
			}
			return products;
		} catch (Exception e) {
			throw e;
		} finally {
			sdbc.closeConnection();
		}
	}

	public List<Product> searchProductByName(String name) throws Exception {
		List<Product> products = new ArrayList<Product>();
		try {
			sdbc.createConnection();
			String command = "select * from Products (nolock) where Name like ?";
			List<Object> params = new ArrayList<Object>();
			params.add('%' + name + '%');
			ResultSet rs = sdbc.executeQuery(command, params);
			while (rs.next()) {
				products.add(new Product(rs.getLong("Id"), rs.getString("Name")));
			}
			return products;
		} catch (Exception e) {
			throw e;
		} finally {
			sdbc.closeConnection();
		}
	}

	public Product addProduct(String name) throws Exception {

		try {
			sdbc.createConnection();
			String command = "insert into Products values (?)";
			List<Object> params = new ArrayList<Object>();
			params.add(name);
			ResultSet rs = sdbc.executeInsert(command, params);

			if (rs.next()) {
				return new Product(rs.getLong(1), name);
			}

			throw new Exception("Failed to insert the record");
		} catch (Exception e) {
			throw e;
		} finally {
			sdbc.closeConnection();
		}
	}
	
	public int updateProduct(long id, String name) throws Exception {
		try {
			sdbc.createConnection();
			String command = "update Products set name = ? where id = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(name);
			params.add(id);
			return sdbc.executeUpdate(command, params);					
		} catch (Exception e) {
			throw e;
		} finally {
			sdbc.closeConnection();
		}
	}
	

	
	

}
