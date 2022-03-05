package com.captionmilk.model;

import lombok.Data;

@Data

public class BrandDTO {
	

	Long id;
	
	String name;
	String description;
	
	public BrandDTO()
	{
		
	}
	public BrandDTO(String name, String description) {
		
		this.name = name;
		this.description = description;
	}

}
