package com.formacionbdi.springboot.app.item.model;

import com.formacionbdi.springboot.app.item.model.proxy.Product;

public class Item {

	private Product producto;
	private Integer cantidad;

	public Item() {
	}

	public Item(Product producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
