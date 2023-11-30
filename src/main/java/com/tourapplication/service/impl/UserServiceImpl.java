package com.tourapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourapplication.dto.UserDTO;
import com.tourapplication.entity.User;
import com.tourapplication.repo.UserRepository;
import com.tourapplication.service.UserService;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Long userId) throws NotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
		return convertToDTO(user);
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = convertToEntity(userDTO);
		return convertToDTO(userRepository.save(user));
	}

	@Override
	public UserDTO updateUser(Long userId, UserDTO userDTO) throws NotFoundException {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

		BeanUtils.copyProperties(userDTO, existingUser);
		return convertToDTO(userRepository.save(existingUser));
	}

	@Override
	public boolean deleteUserById(Long userId) throws NotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

		userRepository.delete(user);
		return true;
	}

	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}
}
