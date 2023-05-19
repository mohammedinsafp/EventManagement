package com.ust.event.UserController;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ust.event.UserModel.User;
import com.ust.event.UserService.UserService;



@RestController
public class UserController {
		@Autowired
		UserService userService;
		
		@GetMapping("/users") 
		public List<User> getAllUsers() {
		List<User> users= userService.getAllUser();
			return users;		
		}
		@GetMapping("/user")
		public ResponseEntity<?> getUserbyUsername(@RequestParam("firstName") String username, @RequestParam("password") String password){
		try{
		User user = userService.findByUsername(username,password);
		return new ResponseEntity<>("Login Successfull", HttpStatus.OK);
		}
		catch (NoSuchElementException e){
		return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}
		catch (NullPointerException e){
		return new ResponseEntity<>("Incorrect Password",HttpStatus.NOT_FOUND);
		}
		}
//		@GetMapping("/user/{id}") 
//		public User getUser(@PathVariable int id) {
//			User user= userService.findUserById(id);	
//			return user;
//		}
		@PostMapping("/user")
		public User createCountry(@RequestBody User user) {
			
			userService.addUser(user);
					
			return user;		
			
		}
		@DeleteMapping("/user/{id}")
		public ResponseEntity<User> deleteUser(@PathVariable int id) {
			userService.deleteUser(id);
					return new ResponseEntity<>(HttpStatus.OK);
			}
		@PutMapping("/user/{id}") 
		public User updateUser(@PathVariable int id,@RequestBody User user ) {
			userService.updateUser(id,user.getFirstName()) ;
			return user;
			}
}
