package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.UserDTO;
import com.example.model.Activities;
import com.example.model.Role;
import com.example.model.User;
import com.example.service.ActivityService;
import com.example.service.UserServiceImpl;

@Controller
public class AdminController {

	@Autowired
	UserServiceImpl userService;
	

	@Autowired
	ActivityService activityService;
	
    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("addUser") UserDTO userForm, Model model) {
        User userInDB=userService.findByUsername(userForm.getUsername());
    	if (userInDB!=null) { 
    		model.addAttribute("error", "The user already exists in the database!");
        }
        else if (userForm.getName().equals(" ")|| userForm.getUsername().equals(" ")|| userForm.getPassword().equals(" "))
        {
  	        model.addAttribute("error", "Complete all forms!");
        }
        else
        {
			if (userForm.getSalary()<0 || userForm.getPassword().length()<3 || userForm.getUsername().length()<3) {
				model.addAttribute("error","Invalid fields!");
			}
			else {
        	userForm.setRole(Role.DESK_EMPLOYEE.toString());
        	userService.addUser(userForm);
        	model.addAttribute("error", "User has been saved successfully!");
			}
        }
    	return "welcome";	
    }
    
   /*****DELETE USER*****/
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(Model model) {
        return "deleteUser";
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String searchUser(@ModelAttribute("deleteUser") UserDTO deleteUser, Model model) {

    		UserDTO user=new UserDTO();
    		user=userService.readUser(deleteUser.getName());
    		if (user==null){
    			model.addAttribute("error","There is no user with this name !");
    		}
    		else if ((deleteUser.getPassword().equals(" ")||deleteUser.getPassword().equals(""))){    		 
    			model.addAttribute("userToDelete",user);
    			
    		}
    		else{
    			userService.deleteUser(deleteUser.getName());
    			model.addAttribute("finalMessage","User was successfully deleted!");
    		}
    	
    	return "deleteUser";
    }

   /*****EDIT USER*****/
    
    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String editUser(Model model) {
        return "editUser";
    }
    
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editSearchUser(@ModelAttribute("editUser") UserDTO editUser, Model model) {

    		UserDTO user=new UserDTO();
    		user=userService.readUser(editUser.getName());
    		if (user==null){
    			model.addAttribute("error","There is no user with this name !");
    		}
    		else if ((editUser.getPassword().equals(" ")||editUser.getPassword().equals(""))){    		 
    			model.addAttribute("userToDelete",user);
    			
    		}
    		else{
    			if (editUser.getSalary()<0 || editUser.getPassword().length()<3 || editUser.getUsername().length()<3) {
    				model.addAttribute("finalMessage","Invalid fields!");
    			}
    			else {
    			editUser.setRole(Role.DESK_EMPLOYEE.toString());
    			userService.updateUser(user.getName(), editUser);
    			model.addAttribute("finalMessage","Information edit successfully!");
    			}
    		}
    	
    	return "editUser";
    }
    
    /*****SEE USERS***/
    @RequestMapping(value = "/seeUsers", method = RequestMethod.GET)
    public String seeUsers(Model model) {
    	List<UserDTO> employees=userService.readUsers();
    	model.addAttribute("employees",employees);
        return "seeUsers";
    }
    
    
    /*****SEE REPORT***/
    @RequestMapping(value = "/seeReport", method = RequestMethod.GET)
    public String seeReport(Model model) {
        return "seeReport";
    }
    
    
    /*****SEE REPORT***/
    @RequestMapping(value = "/seeReport", method = RequestMethod.POST)
    public String seeReport(@ModelAttribute("employee") UserDTO employee,Model model) {
    	UserDTO user=userService.readUser(employee.getName());
    	if (user==null) {
    		model.addAttribute("error","There is no employee with this name.");
    	}
    	else {
    		List<Activities> activities=activityService.readActivities(user.getName());
    		model.addAttribute("activities", activities);
    	}
        return "seeReport";
    }
    
}
