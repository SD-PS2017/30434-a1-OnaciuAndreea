package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.convertors.AccountConvertor;
import com.example.dto.AccountDTO;
import com.example.model.Account;
import com.example.model.Client;
import com.example.repository.AccountRepository;
import com.example.repository.ClientRepository;

@Service
public class AccountService {
	
		@Autowired
		AccountRepository accountRepo;
		
		@Autowired
		ClientRepository clientRepo;
	
	    public Integer save(Account account) {
	    	Account a=accountRepo.save(account);
	    	return a.getId();
	    }

	    
	    public Account findById(Integer id) {
	        return accountRepo.findById(id);
	    }

		
		public List<AccountDTO> readAccounts(String clientCnp){
			List<Account> accounts=new ArrayList<Account>();
			Client c=clientRepo.findByCnp(clientCnp);			
			accounts=accountRepo.findByClient(c);
			return  AccountConvertor.toAccountDto(accounts);
		}
		
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
		
		public void updateAccount(Integer id, Account newAccount){
			Account acc=accountRepo.findById(id);	
			acc.setClient(newAccount.getClient());
			acc.setDateOfCreation(newAccount.getDateOfCreation());
			acc.setMoney(newAccount.getMoney());
			acc.setType(newAccount.getType());
			accountRepo.save(acc);
		}
		
		public void deleteAccount(Integer id){
			accountRepo.deleteById(id);		
		}

}
