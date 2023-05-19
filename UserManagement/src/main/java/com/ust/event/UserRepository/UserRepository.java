package com.ust.event.UserRepository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.event.UserModel.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByFirstName(String username);

	

}
