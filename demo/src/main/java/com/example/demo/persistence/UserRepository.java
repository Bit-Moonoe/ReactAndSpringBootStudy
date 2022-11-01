package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	UserEntity findByUsername(String username);
	Boolean existsByUsername(String username); // 해당 Username이 db에 존재하는지?
	UserEntity findByUsernameAndPassword(String username, String password);
}
