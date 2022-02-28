package com.captionmilk.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	@RequestMapping(value = "success")
	public String getSuccess()
	{
		
		return "Success";
	}
	
	
 

}
