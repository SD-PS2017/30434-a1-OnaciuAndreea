package com.example.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.convertors.AccountConvertor;
import com.example.model.Activities;
import com.example.model.ActivityType;
import com.example.model.Client;
import com.example.service.AccountService;
import com.example.service.ActivityService;
import com.example.service.ClientService;

@Controller
public class ClientController {
	
	

	@Autowired
	ClientService clientService;

	@Autowired
	AccountService accountService;
	

	@Autowired
	ActivityService activityService;

	AccountConvertor accountConverter = new AccountConvertor();

	Activities activity;

	public static final int LENGTH = 13;

	private static byte[] CONTROL_VALUES = new byte[] { 2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9 };

	private static int[] getDigits(String cnp) {
		int _cnp[] = new int[LENGTH];
		for (int i = 0; i < LENGTH; i++) {
			char c = cnp.charAt(i);
			if (!Character.isDigit(c)) {
				return null;
			}
			_cnp[i] = Character.digit(c, 10);
		}
		return _cnp;
	}

	private static int getControlSum(int[] twelveDigits) {
		int k = 0;
		for (int i = 0; i < 12; i++) {
			k += CONTROL_VALUES[i] * twelveDigits[i];
		}
		k %= 11;
		if (k == 10) {
			k = 1;
		}
		return k;
	}

	public static boolean validateLength(String cnp) {
		return cnp.length() == LENGTH;
	}

	public static boolean validateConsistency(String cnp) {
		if (cnp.length() != LENGTH) {
			return false;
		}
		int[] _cnp = getDigits(cnp);
		if (_cnp == null) {
			return false;
		}
		int k = getControlSum(_cnp);
		if (_cnp[LENGTH - 1] != k) {
			return false;
		}
		return true;
	}

	boolean validateCnp(String cnp) {
		return validateLength(cnp) && validateConsistency(cnp);
	}

	boolean validateIdentityCardNumber(Integer idCN) {
		return idCN > 0 && String.valueOf(idCN).length() == 6 ? true : false;
	}
	
	public boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public String saveClient(@ModelAttribute("addClient") Client clientForm, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		Client c = clientService.findClientByCnp(clientForm.getCnp());
		if (clientForm.getAddress().equals("") || clientForm.getCnp().equals("")
				|| clientForm.getIdentityCardNumber().equals("") || clientForm.getName().equals("")) {
			model.addAttribute("error", "Complete all forms!");
		} else if  (!isAlpha(clientForm.getName())){
			model.addAttribute("error", "The name is invalid.");
		} else if (!validateCnp(clientForm.getCnp())) {
			model.addAttribute("error", "The cnp is invalid.");
		} else if (!validateIdentityCardNumber(clientForm.getIdentityCardNumber())) {
			model.addAttribute("error", "The identity card number is invalid.");
		} else if (c != null) {
			model.addAttribute("error", "The user already exists in the database!");
		}

		else {
			Date today = new Date();
			today.setHours(0);
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			activity=new Activities(sqlDate,UserController.loginUser,ActivityType.ADD_CLIENT,"Client name:"+ clientForm.getName());
			activityService.save(activity);
			
			
			clientService.save(clientForm);
			model.addAttribute("message", "Client has been saved successfully!");
		}
		return "employee";
	}

	@RequestMapping(value = "/viewClient", method = RequestMethod.POST)
	public String searchClient(@ModelAttribute("viewClient") Client clientForm, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());

		Client client = new Client();
		client = clientService.findClientByCnp(clientForm.getCnp());
		if (client == null) {
			model.addAttribute("error", "There is no client with this cnp !");
		} else {
			model.addAttribute("viewClient", client);
		}

		return "viewClient";

	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public String editClient(@ModelAttribute("updateClient") Client clientForm, Model model) {
		model.addAttribute("user",UserController.loginUser.getName());
		Client client = new Client();
		client = clientService.findClientByCnp(clientForm.getCnp());
		if (client == null) {
			model.addAttribute("error", "There is no client with this cnp !");
		} else if (clientForm.getAddress().equals("") && clientForm.getName().equals("")) {
			model.addAttribute("viewClient", client);
		} else if (clientForm.getAddress().equals("") || clientForm.getCnp().equals("")
				|| clientForm.getIdentityCardNumber().equals("") || clientForm.getName().equals("")) {
			model.addAttribute("error1", "Please fill all the forms!");
		} else if (!validateCnp(clientForm.getCnp())) {
			model.addAttribute("error1", "The cnp is invalid.");
		} else if (!validateIdentityCardNumber(clientForm.getIdentityCardNumber())) {
			model.addAttribute("error1", "The identity card number is invalid.");
		} else {
			
			Date today = new Date();
			today.setHours(0);
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			activity=new Activities(sqlDate,UserController.loginUser,ActivityType.UPDATE_CLIENT,"Client name:"+clientForm.getName());
			activityService.save(activity);
			
			
			clientService.updateClient(clientForm.getCnp(), clientForm);
			model.addAttribute("message", "Client has been saved successfully!");
		}

		return "updateClient";
	}
}
