package com.example.otopark_yonetim.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.otopark_yonetim.exceptions.CustomException;
import com.example.otopark_yonetim.models.User;
import com.example.otopark_yonetim.payload.requests.ChangePasswordRequest;
import com.example.otopark_yonetim.payload.responses.UserResponse;
import com.example.otopark_yonetim.security.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers().stream().map(u -> new UserResponse(u)).toList();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getOneUser(@PathVariable Long userId) {
		User user = userService.getOneUserById(userId);
		if (user == null) {
			throw new CustomException("User not found");
		}
		UserResponse userResponse = new UserResponse(user);
		return ResponseEntity.ok(userResponse);
	}

	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteOneUser(userId);

	}

	@PostMapping("/{userId}/admin")
	public ResponseEntity<String> addUserAdmin(@PathVariable Long userId) {
		try {
			userService.addUserRole(userId, 2); // Adding 2 as a role //For Admin authority
			return ResponseEntity.ok("Admin role added successfully.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		boolean isPasswordChanged = userService.changePassword(changePasswordRequest.getUsername(),
				changePasswordRequest.getNewPassword());

		if (isPasswordChanged) {
			return ResponseEntity.ok("Password changed successfully!");
		} else {
			return ResponseEntity.badRequest().body("Error: Username not found!");
		}
	}

}