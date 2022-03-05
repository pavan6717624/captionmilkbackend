package com.captionmilk.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captionmilk.domain.Users;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.model.UsersDTO;
import com.captionmilk.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	UsersRepository usersRepository;
	
	public List<UsersDTO> getUsersList(String type)
	{
		return usersRepository.findByType(type);
	}
	
	@Transactional
	public StatusDTO addUser(String name, String address, String mobile, String type, String amount) {
		
				
		
		Optional<Users> optUser = usersRepository.findByContactAndType(Long.valueOf(mobile),type);
		
		System.out.println(optUser);
		
		if(!optUser.isPresent())
		{
		
		Users user=new Users( name,  Long.valueOf(mobile),  address, Long.valueOf(amount),type);
		usersRepository.save(user);
		
		if(user.getId()!=null)
		return new StatusDTO(true,type+" added Successfully.");
		else
		return new StatusDTO(false,"New "+type+" creation Failed.\nPlease check with Administrator.");
		}
		else
			return new StatusDTO(false,type+" with "+mobile +" already Exists.");
	}
}
