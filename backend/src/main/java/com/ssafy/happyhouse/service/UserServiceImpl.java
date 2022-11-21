package com.ssafy.happyhouse.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	private UserMapper dao;
	
	public UserServiceImpl(UserMapper dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void addUser(User user) throws SQLException {
		dao.addUser(user);
	}

	@Override
	public User login(String id, String pwd) throws SQLException {
		return dao.login(id, pwd);
	}

	@Override
	public void update(User user) throws SQLException {
		dao.update(user);
	}

	@Override
	public void delete(String id) throws SQLException {
		dao.delete(id);
	}

	@Override
	public User searchById(String id) throws SQLException {
		return dao.searchById(id);
	}

	@Override
	public User searchPwdByIdTel(String id, String tel) throws SQLException {
		return dao.searchPwdByIdTel(id, tel);
	}
}
