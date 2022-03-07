package com.captionmilk.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.captionmilk.domain.Brand;
import com.captionmilk.domain.CategoryCM;
import com.captionmilk.domain.Quantity;
import com.captionmilk.domain.RepeatDays;
import com.captionmilk.jwt.JwtTokenUtil;
import com.captionmilk.model.CategoryDTO;
import com.captionmilk.model.LoginStatusDTO;
import com.captionmilk.model.ProductDTO;
import com.captionmilk.model.ProductDTO1;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.model.UsersDTO;
import com.captionmilk.repository.LoginDetailsRepository;
import com.captionmilk.service.JwtUserDetailsService;
import com.captionmilk.service.OTPService;
import com.captionmilk.service.ProductService;
import com.captionmilk.service.UserService;
import com.captionmilk.service.UtilService;




@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	@Autowired
	OTPService otpService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	LoginDetailsRepository loginDetailsRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UtilService utilService;
	
	@RequestMapping(value = "addCategory")
	public StatusDTO addCategory(@RequestBody CategoryDTO cat)
	{
		return productService.addCategory(cat);
	}
	
	
	@RequestMapping(value = "getCategories")
	
	
	public List<CategoryCM> getCategories()
	{
		return productService.getCategories();
		
	}
	
	@RequestMapping(value = "addProduct")
	public StatusDTO addProduct(@RequestBody ProductDTO prod)
	{
	return productService.addProduct(prod);
	}
	
	@RequestMapping(value = "getProducts")
	public List<ProductDTO1> getProducts(@RequestParam("selectedDate") String selectedDate, @RequestParam("contact") String contact, @RequestParam("type") String type)
	{
		return productService.getProducts(selectedDate,contact, type);
		
	}
	
	@RequestMapping(value = "getProductReport")
	public List<ProductDTO1> getProductReport(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate)
	{
		return productService.getProductReport(fromDate,toDate);
		
	}
	
	@RequestMapping(value = "statusChange")
	public StatusDTO statusChange(@RequestParam("pid") String pid,@RequestParam("id") String id)
	{
		return productService.statusChange(pid,id);
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "getRepeats")
	public List<RepeatDays> getRepeats()
	{
		return productService.getRepeats();
		
	}
	
	
	
	
	
	@RequestMapping(value = "addBrand")
	public StatusDTO addBrand(@RequestBody CategoryDTO cat)
	{
		return productService.addBrand(cat);
		
	}
	
	
	@RequestMapping(value = "getBrands")
	public List<Brand> getBrands()
	{
		return productService.getBrands();
		
	}
	
	
	
	
	@RequestMapping(value = "addQuantity")
	public StatusDTO addQuantity(@RequestBody CategoryDTO cat)
	{
		
		return productService.addQuantity(cat);
	}
	
	
	@RequestMapping(value = "getQuantities")
	public List<Quantity> getQuantities()
	{
		return productService.getQuantities();
		
	}
	
	
	@RequestMapping(value = "addUser")
	public StatusDTO addUser(@RequestParam("name") String name,@RequestParam("address") String address,@RequestParam("mobile") String mobile,@RequestParam("type") String type,@RequestParam("amount") String amount )
	{
		StatusDTO status = userService.addUser(name,address,mobile,type,amount);
		
		return status;
	}
	
	
	@RequestMapping(value = "getUsersList")
	public List<UsersDTO> getUsersList(@RequestParam("type") String type)
	{
		List<UsersDTO> users=userService.getUsersList(type);
		System.out.println(users);
		return users;
	}
	
	
	
	@RequestMapping(value = "sendOTP")
	public StatusDTO sendOTP(@RequestParam("mobile") String mobile,@RequestParam("email") String email,@RequestParam("name") String name)
	{
		try
		{
		return otpService.sendOTP(Long.valueOf(mobile),email,name);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid Details.");
		}
	}
	
	
	
	@RequestMapping(value = "sendLoginOTP")
	public StatusDTO sendLoginOTP(@RequestParam("mobile") String mobile)
	{
		try
		{
		return otpService.sendLoginOTP(Long.valueOf(mobile));
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid Details.");
		}
	}
	
	@RequestMapping(value = "verifyOTP")
	public StatusDTO verifyOTP(@RequestParam("mobile") String mobile,@RequestParam("otp") String otp)
	{
		try
		{
		return otpService.verifyOTP(Long.valueOf(mobile), otp);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid OTP.");
		}
	}
	
	@RequestMapping(value = "verifyLoginOTP")
	public LoginStatusDTO verifyLoginOTP(@RequestParam("mobile") String mobile,@RequestParam("otp") String otp)
	{
		
		
		LoginStatusDTO loginStatus=new LoginStatusDTO();
		
		try
		{
			StatusDTO status = otpService.verifyLoginOTP(Long.valueOf(mobile), otp);
			if(status.getStatus())
			{
				authenticate(mobile, mobile);
				final UserDetails loginDetails = userDetailsService
						.loadUserByUsername(mobile);

				final String token = jwtTokenUtil.generateToken(loginDetails);
				
				System.out.println("Token is "+token);
				
				loginStatus = new LoginStatusDTO(Long.valueOf(loginDetails.getUsername()), loginDetails.getAuthorities().toArray()[0].toString(), true, token ,"Login Successful!");
			}
			else
			{
				loginStatus = new LoginStatusDTO(0l, "", false, "","Please provide VALID OTP.");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			loginStatus = new LoginStatusDTO(0l, "", false, "","Please provide VALID OTP.");
		}
		
		return loginStatus;
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		//	System.out.println("entered in authenticate sub function...");
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			} catch (DisabledException e) {
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}
			//System.out.println("exited in authenticate sub function...");
		}
	
	
	@RequestMapping(value = "/getLoginDetails")
	public LoginStatusDTO getLoginDetails() throws Exception {
		
		LoginStatusDTO loginStatus=new LoginStatusDTO();
		
		 if(SecurityContextHolder.getContext().getAuthentication() == null)
		 {
			
				
				loginStatus.setStatus(false);
				loginStatus.setUserType("");
		 }
		 else
		 {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		loginStatus.setContact(Long.valueOf(userDetails.getUsername()));
		
		loginStatus.setStatus(true);
		
		System.out.println(Long.valueOf(userDetails.getUsername()));
			
		loginStatus.setUserType(userDetails.getAuthorities().toArray()[0].toString());
		 };
	
		return loginStatus;
	}
	
 

}
