package com.mercureit.DebtCollectorBFF.services;

import java.util.List;

import com.mercureit.DebtCollectorBFF.dto.UserDto;

public interface IUserService {

	List<UserDto> getUsers();

	UserDto getUserConnecte();

	UserDto saveUser(UserDto userDto);

	UserDto updateUser(Long id, UserDto userDto);

	void deleteUser(Long id);
}
