package com.formacionbdi.springboot.app.item.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.business.ItemService;
import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.model.proxy.Product;
import com.formacionbdi.springboot.app.item.service.ProductClientRest;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductClientRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.list().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.detail(id), cantidad);
	}

	@Override
	public Product save(Product producto) {
		return clienteFeign.create(producto);
	}

	@Override
	public Product update(Product producto, Long id) {
		return clienteFeign.edit(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.delete(id);
	}

}
