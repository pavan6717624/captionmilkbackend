package com.captionmilk.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.captionmilk.domain.Brand;
import com.captionmilk.domain.CategoryCM;
import com.captionmilk.domain.LoginDetails;
import com.captionmilk.domain.Products;
import com.captionmilk.domain.Quantity;
import com.captionmilk.domain.RepeatDays;
import com.captionmilk.domain.Users;
import com.captionmilk.model.CategoryDTO;
import com.captionmilk.model.ProductDTO;
import com.captionmilk.model.ProductDTO1;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.repository.BrandRepository;
import com.captionmilk.repository.CategoryRepository;
import com.captionmilk.repository.LoginDetailsRepository;
import com.captionmilk.repository.ProductRepository;
import com.captionmilk.repository.QuantityRepository;
import com.captionmilk.repository.RepeatRepository;
import com.captionmilk.repository.UsersRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	QuantityRepository quantityRepository;
	
	@Autowired
	RepeatRepository repeatRepository;
	
	@Autowired
	LoginDetailsRepository loginDetailsRepository;
	
	@Transactional
	public StatusDTO addCategory(CategoryDTO cat)
	{
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		StatusDTO status = new StatusDTO();
		Optional<CategoryCM> category=categoryRepository.findById(cat.getId());
		if(!category.isPresent())
		categoryRepository.save(new CategoryCM(cat.getName(),cat.getDescription(),user));
		else
		{
			category.get().setDescription(cat.getDescription());
			category.get().setName(cat.getName());
			if(cat.getName().trim().length() == 0)
			{
				category.get().setStatus(false);
			}
			categoryRepository.save(category.get());
		}
		return status;
		
	}
	
	@Transactional
	public StatusDTO addBrand(@RequestBody CategoryDTO cat)
	{
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		StatusDTO status = new StatusDTO();
		Optional<Brand> category=brandRepository.findById(cat.getId());
		if(!category.isPresent())
			brandRepository.save(new Brand(cat.getName(),cat.getDescription(),user));
		else
		{
			category.get().setDescription(cat.getDescription());
			category.get().setName(cat.getName());
			if(cat.getName().trim().length() == 0)
			{
				category.get().setStatus(false);
			}
			brandRepository.save(category.get());
		}
		return status;
		
	}
	@Transactional
	public StatusDTO addQuantity(@RequestBody CategoryDTO cat)
	{
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		StatusDTO status = new StatusDTO();
		Optional<Quantity> category=quantityRepository.findById(cat.getId());
		if(!category.isPresent())
			quantityRepository.save(new Quantity(cat.getName(),cat.getDescription(),user));
		else
		{
			category.get().setDescription(cat.getDescription());
			category.get().setName(cat.getName());
			if(cat.getName().trim().length() == 0)
			{
				category.get().setStatus(false);
			}
			quantityRepository.save(category.get());
		}
		return status;
		
	}
	
	public List<Quantity> getQuantities()
	{
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		return quantityRepository.findByStatusAndUserOrderByIdDesc(true,user);
		
	}
	
	public List<Brand> getBrands()
	{
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		return brandRepository.findByStatusAndUserOrderByIdDesc(true,user);
		
	}
	
	
	public List<RepeatDays> getRepeats()
	{
		
		return repeatRepository.findByStatusOrderByIdDesc(true);
		
	}
	
	@Transactional
	public List<CategoryCM> getCategories()
	{
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		return categoryRepository.findByStatusAndUserOrderByIdDesc(true,user);
		
	}

	public StatusDTO addProduct(ProductDTO prod) {
		
		System.out.println(prod);
		LoginDetails user = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		StatusDTO status = new StatusDTO();
		Optional<Products> product=productRepository.findById(prod.getId());
		if(!product.isPresent())
		{	
			Products newProduct =  new Products();
			newProduct.setCategory(categoryRepository.findById(Long.valueOf(prod.getCategory())).get());
			newProduct.setBrand(brandRepository.findById(Long.valueOf(prod.getBrand())).get());
			newProduct.setQuantity(quantityRepository.findById(Long.valueOf(prod.getQuantity())).get());
			newProduct.setRepeat(repeatRepository.findById(Long.valueOf(prod.getRepeatDays())).get());
			
			newProduct.setLoginUser(user);
			
			newProduct.setUser(userRepository.findByContactAndTypeAndUserId(prod.getContact(),prod.getType(),user.getContact()).get());
			
			
			
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
			product.get().setLoginUser(user);
			product.get().setAmount(prod.getAmount());
			product.get().setMorning(prod.getMorning());
			product.get().setEvening(prod.getEvening());
			product.get().setAfternoon(prod.getAfternoon());
			product.get().setServiceAvailed(prod.getServiceAvailed());
			product.get().setOutOfHome(prod.getOutOfHome());
			product.get().setUser(userRepository.findByContactAndTypeAndUserId(prod.getContact(),prod.getType(),user.getContact()).get());
			
			
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			if(!prod.getFromDate().trim().equals("null") &&prod.getFromDate().trim().length() > 0)
			product.get().setFromDate(Timestamp.valueOf(LocalDateTime.parse(prod.getFromDate()+" 00:00",dateFormatter)));
			if(!prod.getToDate().trim().equals("null") &&prod.getToDate().trim().length() > 0)
			product.get().setToDate(Timestamp.valueOf(LocalDateTime.parse(prod.getToDate()+" 00:00",dateFormatter)));
			
			
		
			productRepository.save(product.get());
		}
		return status;
		
		
	}

	public List<ProductDTO1> getProducts(String date, String contact, String type) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LoginDetails loginUser = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		Users user = userRepository.findByContactAndTypeAndUserId(Long.valueOf(contact),type,loginUser.getContact()).get();
		Timestamp monthStart = Timestamp.valueOf(LocalDateTime.parse(date+" 00:00",dateFormatter));
		monthStart.setDate(01);		
		return productRepository.getProducts(Timestamp.valueOf(LocalDateTime.parse(date+" 00:00",dateFormatter)), monthStart,loginUser,user);
	}
	
	public List<ProductDTO1> getProductReport(String fromDate, String toDate) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LoginDetails loginUser = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		
		return productRepository.getProductReport(Timestamp.valueOf(LocalDateTime.parse(fromDate+" 00:00",dateFormatter)),
				Timestamp.valueOf(LocalDateTime.parse(toDate+" 00:00",dateFormatter)),loginUser);
		
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
