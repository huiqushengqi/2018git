package com.gnsf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.gnsf.entity.User;

@Repository
public interface UserDao<User> {
	List<User> selectId(String username);
}
