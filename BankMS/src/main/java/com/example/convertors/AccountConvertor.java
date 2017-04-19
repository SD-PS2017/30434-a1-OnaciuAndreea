package com.example.convertors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.AccountDTO;
import com.example.dto.UserDTO;
import com.example.model.Account;
import com.example.model.AccountType;
import com.example.model.Client;
import com.example.model.User;
import com.example.service.ClientService;

public class AccountConvertor {
	
	
	ClientService clientService=new ClientService();
	
	public  Account toAccount(AccountDTO dtoAccount,Client c) {

		AccountType type=type(dtoAccount.getType());
		Account account = new Account(type,dtoAccount.getMoney(),dtoAccount.getDate_of_Creation(),c);
		return account;
	}

	public static AccountDTO toAccountDto(Account account) {
		AccountDTO accountDTO = new AccountDTO(account.getId(),account.getDateOfCreation(),account.getMoney(),
				account.getType().toString(),account.getClient().getCnp());
		
		return accountDTO;
	}
	
	public AccountType type(String type){
		if (type.equals(AccountType.CHECKING.toString())){
			return AccountType.CHECKING;
		}
		
		if (type.equals(AccountType.DEPOSIT.toString())){
			return AccountType.DEPOSIT;
		}
		
		else {
			return AccountType.SAVINGS;
		}
	}
	
	public static List<AccountDTO> toAccountDto(List<Account> accounts) {
		List<AccountDTO> accountsDTO = new ArrayList<AccountDTO>();

		for (Account account: accounts)
			accountsDTO.add(toAccountDto(account));

		return accountsDTO;
	}

}
