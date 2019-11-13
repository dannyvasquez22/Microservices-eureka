package com.formacionbdi.springboot.app.item.model.proxy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
	
	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;
	private Integer port;
	
}
