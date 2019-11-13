package com.formacionbdi.springboot.app.productos.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.productos.business.IProductService;
import com.formacionbdi.springboot.app.productos.entity.ProductEntity;
import com.formacionbdi.springboot.app.productos.model.Product;
import com.formacionbdi.springboot.app.productos.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService{

	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		List<ProductEntity> listEntity = (List<ProductEntity>) productRepository.findAll();
		
		return listEntity.stream().map(productEntity -> {
			Product product = new Product();
			product.setId(productEntity.getId());
			product.setNombre(productEntity.getNombre());
			product.setPort(port);
			product.setPrecio(productEntity.getPrecio());
			product.setCreateAt(productEntity.getCreateAt());
			
			return product;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		ProductEntity productEntity = productRepository.findById(id).orElse(null); 
		
		if (productEntity != null) {
			return new Product(productEntity.getId(), productEntity.getNombre(), productEntity.getPrecio(), productEntity.getCreateAt(), port);
		}
		
		return null;
	}

	@Override
	@Transactional
	public Product save(Product product) {
		
		ProductEntity productEntityRequest = new ProductEntity();
		productEntityRequest.setNombre(product.getNombre());
		productEntityRequest.setPrecio(product.getPrecio());
		productEntityRequest.setCreateAt(product.getCreateAt());
		
		ProductEntity productEntityResponse = productRepository.save(productEntityRequest); 
		
		product.setId(productEntityResponse.getId());
		
		return product;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
