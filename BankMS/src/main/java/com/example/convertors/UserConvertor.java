package com.example.convertors;

import java.util.ArrayList;
import java.util.List;

import com.example.dto.UserDTO;
import com.example.model.Role;
import com.example.model.User;

public class UserConvertor {
	public static User toUser(UserDTO dtoUser) {
		Role r = Role.valueOf(dtoUser.getRole());
		User user = new User(dtoUser.getUsername(), dtoUser.getPassword(), r, dtoUser.getName(), dtoUser.getSalary());
		return user;
	}

	public static UserDTO toUserDto(User user) {
		UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getRole().toString(), user.getName(),
				user.getSalary());
		;
		return userDTO;
	}

	public static List<UserDTO> toUsersDto(List<User> users) {
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();

		for (User user : users)
			usersDTO.add(toUserDto(user));

		return usersDTO;
	}

}
