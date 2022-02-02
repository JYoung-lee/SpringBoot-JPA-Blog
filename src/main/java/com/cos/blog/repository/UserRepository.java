package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO
// 자동으로 bean등록이 된다.
// @Repository 생략가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{  // JpaRepository안에 모든 메서드가 존재하고 있다.
		
}
