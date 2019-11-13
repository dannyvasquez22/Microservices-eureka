package com.formacionbdi.springboot.app.productos.business;

import java.util.List;

import com.formacionbdi.springboot.app.productos.model.Product;

public interface IProductService {

	public List<Product> findAll();

	public Product findById(Long id);

	public Product save(Product producto);

	public void deleteById(Long id);
}
