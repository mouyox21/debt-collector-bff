/*
package com.mercureit.DebtCollectorBFF.controller;

import java.util.List;


import com.mercureit.DebtCollectorBFF.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercureit.DebtCollectorBFF.services.IUserService;

@RestController
	@RequestMapping(path = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	//@RolesAllowed("admin")
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<UserDto> userDto = userService.getUsers();

		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/user")
	public ResponseEntity<UserDto> getUserConnecte() {

		UserDto userDto = userService.getUserConnecte();

		return ResponseEntity.ok(userDto);
	}




	@PostMapping("/create-user")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {

		UserDto createdUser = userService.saveUser(userDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/update-user/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		UserDto updatedUser = userService.updateUser(id, userDto);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
*/
