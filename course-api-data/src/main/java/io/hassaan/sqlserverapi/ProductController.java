package io.hassaan.sqlserverapi;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import io.hassaan.configs.ConfigurationManager;
import io.hassaan.vm.RestMessage;
import io.hassaan.vm.StatusCodeEnum;

@RestController
public class ProductController {

/*	private String connStr = "jdbc:sqlserver://;server_name=HASSAAN-PC;databaseName=SpringLearning";
	private String username = "spring";
	private String password = "test123";*/
	
	private ConfigurationManager config = ConfigurationManager.Instance();
	private ProductRepository repo = null;
	private RestMessage message;

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public RestMessage getProducts() {
		try {
			repo = new ProductRepository(config.MSSQLConnectionString, config.MSSQLUsername, config.MSSQLPassword);
			List<Product> products = repo.getProducts();
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}/id")
	public RestMessage getProductById(@PathVariable long id) {
		try {
			repo = new ProductRepository(config.MSSQLConnectionString, config.MSSQLUsername, config.MSSQLPassword);
			Product product = repo.getProductById(id);
			message = new RestMessage(product, StatusCodeEnum.OK);

		} catch (Exception e) {
			
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{name}/name")
	public RestMessage getProductsByName(@PathVariable String name) {
		try {
			repo = new ProductRepository(config.MSSQLConnectionString, config.MSSQLUsername, config.MSSQLPassword);
			List<Product> products = repo.getProductByName(name);
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products/{name}/search")
	public RestMessage getProductsBySearch(@PathVariable String name) {
		try {
			repo = new ProductRepository(config.MSSQLConnectionString, config.MSSQLUsername, config.MSSQLPassword);
			List<Product> products = repo.searchProductByName(name);
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/products/add")
	public RestMessage addProduct(@RequestBody String name) {
		try {
			repo = new ProductRepository(config.MSSQLConnectionString, config.MSSQLUsername, config.MSSQLPassword);
			Product products = repo.addProduct(name);
			message = new RestMessage(products, StatusCodeEnum.OK);

		} catch (Exception e) {
			
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{id}/upsert")
	public RestMessage addProduct(@RequestBody Product p, @PathVariable long id) {
		try {
			repo = new ProductRepository(config.MSSQLConnectionString, config.MSSQLUsername, config.MSSQLPassword);
			int rs = repo.updateProduct(id,p.getName());
			
			String result = Integer.toString(rs) + " record(s) updated";
			
			message = new RestMessage(result, StatusCodeEnum.OK);

		} catch (Exception e) {
			
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
		}

		return message;
	}
}
