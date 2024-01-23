package com.tourapplication.service;

import java.util.List;

import com.tourapplication.dto.UserDTO;

import javassist.NotFoundException;

public interface UserService {
	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long userId) throws NotFoundException;

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Long userId, UserDTO userDTO) throws NotFoundException;

	boolean deleteUserById(Long userId) throws NotFoundException;
}
