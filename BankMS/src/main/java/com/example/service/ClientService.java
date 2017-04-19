package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Client;
import com.example.repository.ClientRepository;
@Service
public class ClientService {
	    
		@Autowired
		ClientRepository clientRepo;
	
	
	    public void save(Client client) {
	        clientRepo.save(client);
	    }

	    
	    public Client findClientByCnp(String cnp) {
	        return clientRepo.findByCnp(cnp);
	    }
	 
		
		public void addClient(Client newClient){
			//User userToSave=UserConvertor.toUser(newUser);
			clientRepo.save(newClient);

		}
		
		/*public List<UserDTO> readUsers(){
			List<User> users=new ArrayList<User>();
			List<UserDTO> usersDTO=new ArrayList<UserDTO>();
			
			users=userRepo.findAll();
			usersDTO=UserConvertor.toUsersDto(users);
			
			return  usersDTO;
		}*/
		
		/*public UserDTO readUser(String name){
			User user=userRepo.findByName(name);
			if (user==null) return null;
			return UserConvertor.toUserDto(user);
		}*/
		
	/*	public User findUserByUsername(String username){
			User user=userRepo.findByUsername(username);
			return user;//Convertor.toUserDto(user);
		}
		*/
		
		public void updateClient(String cnp,Client client){
			Client clientToSave=clientRepo.findByCnp(cnp);
			clientToSave.setIdentityCardNumber(client.getIdentityCardNumber());
			clientToSave.setName(client.getName());
			clientToSave.setAddress(client.getAddress());
			clientRepo.save(clientToSave);
		}

}
