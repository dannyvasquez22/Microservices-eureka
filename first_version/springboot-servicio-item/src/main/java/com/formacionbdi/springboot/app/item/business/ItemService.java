package com.formacionbdi.springboot.app.item.business;

import java.util.List;

import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.model.proxy.Product;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);

	public Product save(Product producto);

	public Product update(Product producto, Long id);

	public void delete(Long id);
}
