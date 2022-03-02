package com.captionmilk.model;

import lombok.Data;

@Data
public class StatusDTO {
	
	String message;
	Boolean status;
	
	public StatusDTO()
	{
		
	}
	
	public StatusDTO(Boolean status, String message)
	{
		this.status=status;
		this.message=message;
	}

}
