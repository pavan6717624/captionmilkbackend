package com.captionmilk.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captionmilk.domain.Category;
import com.captionmilk.domain.Products;
import com.captionmilk.model.ProductDTO;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.repository.BrandRepository;
import com.captionmilk.repository.CategoryRepository;
import com.captionmilk.repository.ProductRepository;
import com.captionmilk.repository.QuantityRepository;
import com.captionmilk.repository.RepeatRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	QuantityRepository quantityRepository;
	
	@Autowired
	RepeatRepository repeatRepository;

	public StatusDTO addProduct(ProductDTO prod) {
		
		System.out.println(prod);
		
		StatusDTO status = new StatusDTO();
		Optional<Products> product=productRepository.findById(prod.getId());
		if(!product.isPresent())
		{	
			Products newProduct =  new Products();
			newProduct.setCategory(categoryRepository.findById(Long.valueOf(prod.getCategory())).get());
			newProduct.setBrand(brandRepository.findById(Long.valueOf(prod.getBrand())).get());
			newProduct.setQuantity(quantityRepository.findById(Long.valueOf(prod.getQuantity())).get());
			newProduct.setRepeat(repeatRepository.findById(Long.valueOf(prod.getRepeatDays())).get());
			
			newProduct.setAmount(prod.getAmount());
			newProduct.setDaySchedule(prod.getDaySchedule());
						
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			
			if(!prod.getFromDate().trim().equals("null") && prod.getFromDate().trim().length() > 0)
				newProduct.setFromDate(Timestamp.valueOf(LocalDateTime.parse(prod.getFromDate()+" 00:00",dateFormatter)));
				if(!prod.getToDate().trim().equals("null") &&prod.getToDate().trim().length() > 0)
				newProduct.setToDate(Timestamp.valueOf(LocalDateTime.parse(prod.getToDate()+" 00:00",dateFormatter)));
							
			
			newProduct.setServiceAvailed(prod.getServiceAvailed());
			newProduct.setOutOfHome(prod.getOutOfHome());
			
			
			productRepository.save(newProduct);
		}
		else
		{
			product.get().setCategory(categoryRepository.findById(prod.getCid()).get());
			product.get().setBrand(brandRepository.findById(prod.getBid()).get());
			product.get().setQuantity(quantityRepository.findById(prod.getQid()).get());
			product.get().setRepeat(repeatRepository.findById(prod.getRid()).get());
			
			product.get().setAmount(prod.getAmount());
			product.get().setDaySchedule(prod.getDaySchedule());
						
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			if(!prod.getFromDate().trim().equals("null") &&prod.getFromDate().trim().length() > 0)
			product.get().setFromDate(Timestamp.valueOf(LocalDateTime.parse(prod.getFromDate()+" 00:00",dateFormatter)));
			if(!prod.getToDate().trim().equals("null") &&prod.getToDate().trim().length() > 0)
			product.get().setToDate(Timestamp.valueOf(LocalDateTime.parse(prod.getToDate()+" 00:00",dateFormatter)));
			
			
			product.get().setServiceAvailed(prod.getServiceAvailed());
			product.get().setOutOfHome(prod.getOutOfHome());
			
			
			productRepository.save(product.get());
		}
		return status;
		
		
	}

}
