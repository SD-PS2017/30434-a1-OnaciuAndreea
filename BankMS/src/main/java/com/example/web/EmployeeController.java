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
import com.example.model.Transfer;
import com.example.model.User;
import com.example.service.AccountService;
import com.example.service.ActivityService;
import com.example.service.ClientService;

@Controller
public class EmployeeController {

	@Autowired
	ClientService clientService;

	@Autowired
	AccountService accountService;
	

	@Autowired
	ActivityService activityService;

	AccountConvertor accountConverter = new AccountConvertor();
	
	Activities activity;
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String addClient(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		Date today = new Date();
		today.setHours(0);
		java.sql.Date sqlDate = new java.sql.Date(today.getTime());
		activity=new Activities(sqlDate,UserController.loginUser,ActivityType.LOGIN,"");
		activityService.save(activity);
		return "employee";
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.GET)
	public String updateClient(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "updateClient";
	}

	@RequestMapping(value = "/viewClient", method = RequestMethod.GET)
	public String viewClient(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "viewClient";
	}

	@RequestMapping(value = "/viewAccount", method = RequestMethod.GET)
	public String viewAccount(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "viewAccount";
	}
	
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccount(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		List<AccountType> activity = new ArrayList<AccountType>();
		activity.add(AccountType.CHECKING);
		activity.add(AccountType.DEPOSIT);
		activity.add(AccountType.SAVINGS);
		model.addAttribute("enum", activity);
		return "createAccount";
	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
	public String updateAccount(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "updateAccount";
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public String deleteAccount(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "deleteAccount";
	}

	@RequestMapping(value = "/transferMoney", method = RequestMethod.GET)
	public String transferMoney(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "transferMoney";
	}

	@RequestMapping(value = "/processUtilities", method = RequestMethod.GET)
	public String processUtilities(Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		return "processUtilities";
	}

	
	@RequestMapping(value = "/transferMoney", method = RequestMethod.POST)
	public String transferMoney(@ModelAttribute("transferMoney") Transfer t, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		String validate=validateTransfer(t);
		model.addAttribute("error",validate);
        if (validate.equals("")) model.addAttribute("message","The transfer was executed successfully!");   
		return "transferMoney";
	}
	
	
	@RequestMapping(value = "/processUtilities", method = RequestMethod.POST)
	public String processUtilities(@ModelAttribute("processUtilities") Transfer t, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		String validate=validateProcess(t);	
		model.addAttribute("error",validate);
        if (validate.equals("")) model.addAttribute("message","The transfer was executed successfully!");   
		return "processUtilities";
	}
	
	public String validateTransfer(Transfer t){
		Client c=clientService.findClientByCnp(t.getCnp());
		if (c==null) return "There is no person with this cnp!";
		
		if(t.getFromId()==t.getToId()) return "Transferring to the same account is useless.";
		
		Account a=accountService.findById(t.getFromId());
		if (a==null) return "The source account is not valid.";
		
		Account b=accountService.findById(t.getToId());
		if (b==null) return "The destination account is not valid.";
		
		if (a.getClient().getId()!=c.getId()) 
			return "The source account doesn't belong to this client.";
		
		if (a.getMoney()<=t.getMoney())
			return "There is not enough money to transfer in the source account";
		
		if (t.getMoney()<=0)
			return "The sum of money you enter is invalid";
		int moneyA=a.getMoney();
		a.setMoney(moneyA-t.getMoney());
		accountService.updateAccount(a.getId(),a);
		int moneyB=b.getMoney();
		b.setMoney(moneyB+t.getMoney());
		accountService.updateAccount(b.getId(),b);
		
		Date today = new Date();
		today.setHours(0);
		java.sql.Date sqlDate = new java.sql.Date(today.getTime());
		activity=new Activities(sqlDate,UserController.loginUser,ActivityType.TRANSFER_MONEY,
				"From account:"+a.getId() +"to "+ b.getId()+ "; Sum:"+t.getMoney());
		activityService.save(activity);
		
		return "";
	}
	
	
	public String validateProcess(Transfer t){
		Client c=clientService.findClientByCnp(t.getCnp());
		if (c==null) return "There is no person with this cnp!";
		
		Account a=accountService.findById(t.getFromId());
		if (a==null) return "The source account is not valid.";
				
		if (a.getClient().getId()!=c.getId()) 
			return "The source account doesn't belong to this client.";
		
		if (a.getMoney()<=t.getMoney())
			return "There is not enough money to transfer in the source account";
		
		if (t.getMoney()<=0)
			return "The sum of money you enter is invalid";
		int moneyA=a.getMoney();
		a.setMoney(moneyA-t.getMoney());
		accountService.updateAccount(a.getId(),a);
		
		Date today = new Date();
		today.setHours(0);
		java.sql.Date sqlDate = new java.sql.Date(today.getTime());
		activity=new Activities(sqlDate,UserController.loginUser,ActivityType.PROCESS_UTILITIES,
				"Account:"+a.getId()+"; Sum:"+t.getMoney());
		activityService.save(activity);
		
		return "";
	}
	
}
