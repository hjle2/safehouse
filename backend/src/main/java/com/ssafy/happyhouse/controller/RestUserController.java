package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.service.SecurityService;
import com.ssafy.happyhouse.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class RestUserController {
	
	private String salt="";
	private SecurityService Encryption;
	private UserService uService;
	private final Logger logger = LoggerFactory.getLogger(RestHouseController.class);
	@Autowired
	public RestUserController(UserService uService,SecurityService Encryption) {
		this.uService = uService;
		this.Encryption = Encryption;
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestBody User user){
		logger.info("login");
		System.out.println(user);
		if(user.getId()== null || user.getPwd() == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		String passwd = user.getPwd();
		passwd = Encryption.Encryption(passwd,salt);
		try {
			User target=uService.login(user.getId(), passwd);
			if(target.getId()== null) {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
			String token = rUserKey(user);
			return new ResponseEntity<String>(token,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	//regist  
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody User user){
		logger.info("regist");
		System.out.println(user);
		if(user.getId() == null) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		try {
			User CheckUser=uService.searchById(user.getId());
			if(CheckUser != null) {
				return new ResponseEntity<Void>(HttpStatus.IM_USED);
			}
		} catch (SQLException e1) {
			new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		user.setPwd(Encryption.Encryption(user.getPwd(), salt));
		try {
			uService.addUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	//findpassword 임시 비밀번호 발급할 것.
	@PostMapping("/findpwd")
	public ResponseEntity<?> findPassword(@RequestBody User user){
		System.out.println("find pwd도착함");
		
		try {
			String temppass = Encryption.tempPassGenerate();
			User userdata = uService.searchPwdByIdTel(user.getId(), user.getTel());
			String EncodedTempass=Encryption.Encryption(temppass , salt);
			userdata.setPwd(EncodedTempass);
			uService.update(userdata);
			return new ResponseEntity<String>(temppass,HttpStatus.OK); 
		} catch(SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//modify 
	@PutMapping("/modifyUser")
	public ResponseEntity<?> modifyUser(@RequestParam("user") User user){
		logger.info("modify");
		try {
			String pass = Encryption.Encryption(user.getPwd(), salt);
			user.setPwd(pass);
			uService.update(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
		
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//delete
	@DeleteMapping("/removeUser")
	public ResponseEntity<?> removeUser(@RequestParam("id") String id){
		logger.info("remove");
		try {
			uService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public String rUserKey( User user){
		String salt = "Seoul13";
		String data = user.getId() + user.getTel();
		String result=Encryption.Encryption(data, salt);
		return result; 
	}
	
	
	
}
