package com.ust.event.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.event.UserModel.User;
import com.ust.event.UserRepository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public User findUserById(int id) {
		Optional<User> result = userRepository.findById(id);
		User user=result.get();
		return user;
	}
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}
	public void updateUser(int id,String firstName) {
		User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(firstName);
            userRepository.save(user);
        }
	}
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}
	public User findByUsername(String username,String password) {
		User user = userRepository.findByFirstName(username).orElseThrow(()->new NoSuchElementException());
		if(user.getPassword().equals(password)){
		return user;
		}
		else {
		throw new NullPointerException();
		}

		}

}
