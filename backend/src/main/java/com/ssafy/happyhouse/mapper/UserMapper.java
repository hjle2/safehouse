package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;

import com.ssafy.happyhouse.dto.User;

public interface UserMapper {
	void addUser(User user) throws SQLException;
	User login(String id, String pwd) throws SQLException;
	void update(User user) throws SQLException;
	void delete(String id) throws SQLException;
	User searchById(String id) throws SQLException;
	User searchPwdByIdTel(String id, String tel) throws SQLException;
}
