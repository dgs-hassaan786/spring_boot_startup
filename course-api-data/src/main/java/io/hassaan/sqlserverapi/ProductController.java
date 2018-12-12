package io.hassaan.sqlserverapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.hassaan.vm.RestMessage;
import io.hassaan.vm.StatusCodeEnum;

@RestController
public class ProductController {

	private RestMessage message;

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public RestMessage getProducts() {

		Connection conn;

		try {
			conn = getConnectionToDB();
			Statement stmt = null;
			String query = "select * from Products (nolock)";
			List<Product> products = new ArrayList<Product>();
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					Product p = new Product(rs.getLong("Id"), rs.getString("Name"));
					products.add(p);
				}
				
				message = new RestMessage(products, StatusCodeEnum.OK);
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			} finally {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}

	private Connection getConnectionToDB() throws Exception {
		try {
			// Properties pro = new Properties();
			String integratedConn = "jdbc:sqlserver://;server_name=HASSAAN-PC;integratedSecurity=true;databaseName=SpringLearning";

			Connection conn = DriverManager.getConnection(
					"jdbc:sqlserver://;server_name=HASSAAN-PC;databaseName=SpringLearning", "spring", "test123");

			return conn;
		} catch (Exception e) {

			throw e;
		}
	}

}
