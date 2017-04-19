package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.convertors.AccountConvertor;
import com.example.model.Account;
import com.example.model.Activities;
import com.example.model.User;
import com.example.repository.ActivityRepository;
import com.example.repository.UserRepository;

@Service
public class ActivityService {
	@Autowired
	ActivityRepository activityRepo;


	@Autowired
	UserRepository userRepo;
	
    public void save(Activities activity) {
    	activityRepo.save(activity);
    }
	
	public List<Activities> readActivities(String name){
		List<Activities> activities=new ArrayList<Activities>();
		User u=userRepo.findByName(name);			
		activities=activityRepo.findByUser(u);
		return activities;
	}

}
