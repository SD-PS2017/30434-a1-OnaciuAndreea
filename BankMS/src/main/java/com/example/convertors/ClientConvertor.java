package com.example.convertors;

import java.util.ArrayList;
import java.util.List;

import com.example.dto.ClientDTO;
import com.example.dto.UserDTO;
import com.example.model.Client;
import com.example.model.User;

public class ClientConvertor {
	public static Client toClient(ClientDTO dtoClient) {
		Client client = new Client(dtoClient.getName(),dtoClient.getIdentity_Card_number(),dtoClient.getCnp(),dtoClient.getAddress());
		return client;
	}

/*public static ClientDTO toClientDto(Client user) {
		UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getRole().toString(), user.getName(),
				user.getSalary());
		;
		return userDTO;
	}
*/
/*	public static List<UserDTO> toUsersDto(List<User> users) {
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();

		for (User user : users)
			//usersDTO.add(toUserDto(user));

		return usersDTO;
	}
*/
}
