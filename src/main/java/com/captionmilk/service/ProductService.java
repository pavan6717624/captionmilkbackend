package com.captionmilk.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captionmilk.domain.Products;
import com.captionmilk.model.ProductDTO;
import com.captionmilk.model.ProductDTO1;
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
			newProduct.setMorning(prod.getMorning());
			newProduct.setEvening(prod.getEvening());
			newProduct.setAfternoon(prod.getAfternoon());
			newProduct.setServiceAvailed(true);
			newProduct.setOutOfHome(false);
						
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			
			if(!prod.getFromDate().trim().equals("null") && prod.getFromDate().trim().length() > 0)
				newProduct.setFromDate(Timestamp.valueOf(LocalDateTime.parse(prod.getFromDate()+" 00:00",dateFormatter)));
				if(!prod.getToDate().trim().equals("null") &&prod.getToDate().trim().length() > 0)
				newProduct.setToDate(Timestamp.valueOf(LocalDateTime.parse(prod.getToDate()+" 00:00",dateFormatter)));
							
			
		
			
			productRepository.save(newProduct);
		}
		else
		{
			product.get().setCategory(categoryRepository.findById(Long.valueOf(prod.getCategory())).get());
			product.get().setBrand(brandRepository.findById(Long.valueOf(prod.getBrand())).get());
			product.get().setQuantity(quantityRepository.findById(Long.valueOf(prod.getQuantity())).get());
			product.get().setRepeat(repeatRepository.findById(Long.valueOf(prod.getRepeatDays())).get());
			
			product.get().setAmount(prod.getAmount());
			product.get().setMorning(prod.getMorning());
			product.get().setEvening(prod.getEvening());
			product.get().setAfternoon(prod.getAfternoon());
			product.get().setServiceAvailed(prod.getServiceAvailed());
			product.get().setOutOfHome(prod.getOutOfHome());
			
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			if(!prod.getFromDate().trim().equals("null") &&prod.getFromDate().trim().length() > 0)
			product.get().setFromDate(Timestamp.valueOf(LocalDateTime.parse(prod.getFromDate()+" 00:00",dateFormatter)));
			if(!prod.getToDate().trim().equals("null") &&prod.getToDate().trim().length() > 0)
			product.get().setToDate(Timestamp.valueOf(LocalDateTime.parse(prod.getToDate()+" 00:00",dateFormatter)));
			
			
		
			productRepository.save(product.get());
		}
		return status;
		
		
	}

	public List<ProductDTO1> getProducts(String date) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		
		Timestamp monthStart = Timestamp.valueOf(LocalDateTime.parse(date+" 00:00",dateFormatter));
		monthStart.setDate(01);		
		return productRepository.getProducts(Timestamp.valueOf(LocalDateTime.parse(date+" 00:00",dateFormatter)), monthStart);
	}
	
	public List<ProductDTO1> getProductReport(String fromDate, String toDate) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		return productRepository.getProductReport(Timestamp.valueOf(LocalDateTime.parse(fromDate+" 00:00",dateFormatter)),
				Timestamp.valueOf(LocalDateTime.parse(toDate+" 00:00",dateFormatter)));
		
	}

	public StatusDTO statusChange(String pid, String id) {
		StatusDTO status = new StatusDTO();
		Optional<Products> product=productRepository.findById(Long.valueOf(pid));
		
		if(!product.isPresent())
		return status;
		if(id.equals("0"))
			product.get().setServiceAvailed(!product.get().getServiceAvailed());
		else if(id.equals("1"))
			product.get().setOutOfHome(!product.get().getOutOfHome());
		
		productRepository.save(product.get());
		
		return status;
	}

}
