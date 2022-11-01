package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	UserEntity findByUsername(String username);
	Boolean existsByUsername(String username); // �ش� Username�� db�� �����ϴ���?
	UserEntity findByUsernameAndPassword(String username, String password);
}
