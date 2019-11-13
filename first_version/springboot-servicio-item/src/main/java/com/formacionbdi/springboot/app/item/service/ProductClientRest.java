package com.formacionbdi.springboot.app.item.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.item.model.proxy.Product;

@FeignClient(name = "servicio-productos")
public interface ProductClientRest {
	
	@GetMapping("/list")
	public List<Product> list();
	
	@GetMapping("/view/{id}")
	public Product detail(@PathVariable Long id);
	
	@PostMapping("/create")
	public Product create(@RequestBody Product producto);
	
	@PutMapping("/edit/{id}")
	public Product edit(@RequestBody Product producto, @PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);

}
