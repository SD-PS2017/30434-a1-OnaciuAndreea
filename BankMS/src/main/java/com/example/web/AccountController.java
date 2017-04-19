package com.example.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.convertors.AccountConvertor;
import com.example.dto.AccountDTO;
import com.example.model.Account;
import com.example.model.AccountType;
import com.example.model.Activities;
import com.example.model.ActivityType;
import com.example.model.Client;
import com.example.service.AccountService;
import com.example.service.ActivityService;
import com.example.service.ClientService;

@Controller
public class AccountController {
	

	@Autowired
	ClientService clientService;

	@Autowired
	AccountService accountService;
	

	@Autowired
	ActivityService activityService;

	AccountConvertor accountConverter = new AccountConvertor();
	
	Activities activity;

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@ModelAttribute("createAccount") AccountDTO account, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		Client client;
		if (account.getCnp().equals("")) {
			model.addAttribute("error", "Please insert the CNP!");
		} else {
			client = clientService.findClientByCnp(account.getCnp());

			if (client == null) {
				model.addAttribute("error", "There is no client with this cnp !");
			} 
			else {

				if (account.getMoney() < 0) {
					model.addAttribute("error", "You cannot insert negative values.");
					} 
				else {
					Account accountToSave = new Account();
					accountToSave = accountConverter.toAccount(account,client);
					accountToSave.setType(AccountType.CHECKING);
					accountToSave.setClient(client);
					Date today = new Date();
					today.setHours(0);
					java.sql.Date sqlDate = new java.sql.Date(today.getTime());
					accountToSave.setDateOfCreation(sqlDate);
					Integer id=accountService.save(accountToSave);
					
					activity=new Activities(sqlDate,UserController.loginUser,ActivityType.ADD_ACCOUNT,"Account id:"+id);
					activityService.save(activity);
					
					model.addAttribute("message", "Account with id="+id+" was saved successfully !");
				}
			}
		}
			return "createAccount";

		}

	
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public String deleteAccount(@ModelAttribute("deleteAccount") AccountDTO account, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		Client client;
		if (account.getCnp().equals("")|| account.getId()==0) {
			model.addAttribute("error", "Please fill all the fields!");
		}
		else{
			client = clientService.findClientByCnp(account.getCnp());

			if (client == null) {
				model.addAttribute("error", "There is no client with this cnp !");
			} 
			else {
			 Account a=accountService.findById(account.getId());
			 if (a==null) {
				 model.addAttribute("error","This account doesn't exist.");
			 }
			 else {
				 if (!a.getClient().getCnp().equals(client.getCnp())){
					 model.addAttribute("error","This account does not correspond to this cnp.");
				 }
				 else{
					 accountService.deleteAccount(a.getId());
					 model.addAttribute("message","Account deleted successfully.");
					 
					Date today = new Date();
					today.setHours(0);
					java.sql.Date sqlDate = new java.sql.Date(today.getTime());
					activity=new Activities(sqlDate,UserController.loginUser,ActivityType.DELETE_ACCOUNT,"Account id:"+a.getId());
					activityService.save(activity);
				 }					 
			 }
			}
		}
		return "deleteAccount";
	}
	
	
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public String updateAccount(@ModelAttribute("updateAccount") AccountDTO account,@ModelAttribute("newAccount") AccountDTO newAccount, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		Client client;
		
		if ((account.getCnp().equals("")|| account.getId()==0)) {
			model.addAttribute("error", "Please fill all the fields!");
		}
		else{
			client = clientService.findClientByCnp(account.getCnp());

			if (client == null) {
				model.addAttribute("error", "There is no client with this cnp !");
			} 
			else {
			 Account a=accountService.findById(account.getId());
			 if (a==null) {
				 model.addAttribute("error","This account doesn't exist.");
			 }
			 else {
				 if (!a.getClient().getCnp().equals(client.getCnp())){
					 model.addAttribute("error","This account does not correspond to this cnp.");
				 }
				 else if (newAccount.getType().equals("")){
					 model.addAttribute("newAccount",a);
					 model.addAttribute("updateAccount",account);
					 
				 }	
				 else {
					 
					 model.addAttribute("message","Account saved successfully.");
				 }
			 }
			}
		}
		return "updateAccount";
	}
	
	@RequestMapping(value = "/viewAccount", method = RequestMethod.POST)
	public String viewAccount(@ModelAttribute("viewAccount") Client c, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		List<AccountDTO> accounts=new ArrayList<AccountDTO>();
		Client client;
		client = clientService.findClientByCnp(c.getCnp());

		if (client == null) {
			model.addAttribute("error", "There is no client with this cnp !");
			
		} 
		else{
		accounts=accountService.readAccounts(c.getCnp());
		model.addAttribute("accounts",accounts);
		}
		
		
		return "viewAccount";
	}
}
