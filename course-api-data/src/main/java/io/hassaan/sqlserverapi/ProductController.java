package io.hassaan.sqlserverapi;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import io.hassaan.vm.RestMessage;
import io.hassaan.vm.StatusCodeEnum;

@RestController
public class ProductController {

	private String connStr = "jdbc:sqlserver://;server_name=HASSAAN-PC;databaseName=SpringLearning";
	private String username = "spring";
	private String password = "test123";
	private ProductRepository repo = null;
	private RestMessage message;

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public RestMessage getProducts() {
		try {
			repo = new ProductRepository(connStr, username, password);
			List<Product> products = repo.getProducts();
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			// TODO: handle exception
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}/id")
	public RestMessage getProductById(@PathVariable long id) {
		try {
			repo = new ProductRepository(connStr, username, password);
			Product product = repo.getProductById(id);
			message = new RestMessage(product, StatusCodeEnum.OK);

		} catch (Exception e) {
			// TODO: handle exception
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{name}/name")
	public RestMessage getProductsByName(@PathVariable String name) {
		try {
			repo = new ProductRepository(connStr, username, password);
			List<Product> products = repo.getProductByName(name);
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			// TODO: handle exception
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{name}/search")
	public RestMessage getProductsBySearch(@PathVariable String name) {
		try {
			repo = new ProductRepository(connStr, username, password);
			List<Product> products = repo.searchProductByName(name);
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			// TODO: handle exception
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
}
