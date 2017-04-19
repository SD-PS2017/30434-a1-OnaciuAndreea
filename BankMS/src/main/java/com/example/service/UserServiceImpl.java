package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.convertors.UserConvertor;
import com.example.dto.UserDTO;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

   // @Autowired
   // private BCryptPasswordEncoder bCryptPasswordEncoder;
    	

    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.DESK_EMPLOYEE);
        // user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
 
	
	public void addUser(UserDTO newUser){
		User userToSave=UserConvertor.toUser(newUser);
		userRepo.save(userToSave);
		
	//	userRepo.persist(userToSave);
	}
	
	public List<UserDTO> readUsers(){
		List<User> users=new ArrayList<User>();
		List<UserDTO> usersDTO=new ArrayList<UserDTO>();
		
		users=userRepo.findAll();
		usersDTO=UserConvertor.toUsersDto(users);
		
		return  usersDTO;
	}
	
	public UserDTO readUser(String name){
		User user=userRepo.findByName(name);
		if (user==null) return null;
		return UserConvertor.toUserDto(user);
	}
	
/*	public User findUserByUsername(String username){
		User user=userRepo.findByUsername(username);
		return user;//Convertor.toUserDto(user);
	}
	*/
	
	public void updateUser(String name,UserDTO userDTO){
		User userToSave=userRepo.findByName(name);	
		User newUser=UserConvertor.toUser(userDTO);
		userToSave.setPassword(newUser.getPassword());
		userToSave.setRole(newUser.getRole());
		userToSave.setSalary(newUser.getSalary());
		userToSave.setUsername(newUser.getUsername());
		userRepo.save(userToSave);
	}
	
	public void deleteUser(String name){
		userRepo.deleteByName(name);		
	}
	
}
