package com.example.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Role;
import com.example.model.User;

import com.example.service.UserServiceImpl;
import com.example.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    
    public static User loginUser;

  /*  @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
*/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

   @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("userLogin") User userForm, Model model) {
    	System.out.println(userForm.getPassword());
	   User userInDB=userService.findByUsername(userForm.getUsername());
    	if (userInDB!=null && userInDB.getPassword().equals(userForm.getPassword())) { 
  	        model.addAttribute("username",userForm.getPassword());
        	if (userInDB.getRole()==Role.ADMIN) {
        		return "redirect:/welcome";
        	}
        	else{
        		loginUser=new User();
        		loginUser=userInDB;
        		return "redirect:/employee";
        	}
        }
        else
        {
        	 model.addAttribute("error", "Incorrect credentials.");
        	 return "login";
        }        	
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
      // userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userForm.setName(userForm.getUsername());
        userService.save(userForm);
  
      /// securityService.autologin(userForm.getUsername(), userForm.getPassword());
        System.out.println(userForm.getPassword());
        return "redirect:/welcome";
    }

    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String login(@ModelAttribute("userForm") User userForm, Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Invalid username and password.");
      
        
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    
 
}
