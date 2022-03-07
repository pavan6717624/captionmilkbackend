package com.captionmilk.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captionmilk.domain.LoginDetails;
import com.captionmilk.domain.Users;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.model.UsersDTO;
import com.captionmilk.repository.LoginDetailsRepository;
import com.captionmilk.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	LoginDetailsRepository loginDetailsRepository;
	
	public List<UsersDTO> getUsersList(String type)
	{
		LoginDetails loggedUser = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		return usersRepository.findByTypeAndUserId(type, loggedUser.getContact());
	}
	
	@Transactional
	public StatusDTO addUser(String name, String address, String mobile, String type, String amount) {
		
		String type1="Caption";
		
		if(type.equalsIgnoreCase("Caption"))
			type1="Customer";
				
		LoginDetails loggedUser = loginDetailsRepository.findByContact(UtilService.getUser().getLoginId()).get();
		Optional<Users> optUser = usersRepository.findByContactAndTypeAndUserId(Long.valueOf(mobile),type,loggedUser.getContact());
		
		System.out.println(optUser);
		
		if(!optUser.isPresent())
		{
		
		Users user1=new Users( name,  Long.valueOf(mobile),  address, Long.valueOf(amount),type,loggedUser.getContact(),true);
		usersRepository.save(user1);
		
		Users user2=new Users( loggedUser.getName(),  loggedUser.getContact(),"", 0l,type1,Long.valueOf(mobile),false);
		usersRepository.save(user2);
		
		if(user2.getId()!=null )
		return new StatusDTO(true,type+" added Successfully.");
		else
		return new StatusDTO(false,"New "+type+" creation Failed.\nPlease check with Administrator.");
		}
		else
			return new StatusDTO(false,type+" with "+mobile +" already Exists.");
	}
}
