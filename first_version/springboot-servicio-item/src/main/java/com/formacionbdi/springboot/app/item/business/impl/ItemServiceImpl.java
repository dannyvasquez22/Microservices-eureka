package com.formacionbdi.springboot.app.item.business.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.business.ItemService;
import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.model.proxy.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		List<Product> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/list", Product[].class));
		
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Product producto = clienteRest.getForObject("http://servicio-productos/view/{id}", Product.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Product save(Product producto) {
		HttpEntity<Product> body = new HttpEntity<Product>(producto);
		
		ResponseEntity<Product> response = clienteRest.exchange("http://servicio-productos/create", HttpMethod.POST, body, Product.class);
		Product productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Product update(Product producto, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Product> body= new HttpEntity<Product>(producto);
		ResponseEntity<Product> response = clienteRest.exchange("http://servicio-productos/edit/{id}", 
				HttpMethod.PUT, body, Product.class, pathVariables);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://servicio-productos/delete/{id}", pathVariables);
		
	}

}
