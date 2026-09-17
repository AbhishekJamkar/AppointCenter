package com.zosh.repository;

import com.zosh.modal.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
