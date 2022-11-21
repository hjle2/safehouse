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
		String passwd = user.getPwd();
		passwd = Encryption.Encryption(passwd,salt);
		try {
			User target=uService.login(user.getId(), passwd);
			if(target== null) {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<User>(target,HttpStatus.OK);
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
	public ResponseEntity<?> findPassword(@RequestBody String id, @RequestBody String tel){
		try {
			String temppass = Encryption.tempPassGenerate();
			User userdata = uService.searchPwdByIdTel(id, tel);
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
	
	
	@PostMapping("/key")
	public ResponseEntity<String> rUserKey(@RequestBody User user){
		String salt = "Seoul13";
		String baseurl = user.getId() + user.getPwd() + salt;
		StringBuilder builder = new StringBuilder();
		MessageDigest mod;
		try {
			mod = MessageDigest.getInstance("SHA-256");
			mod.update(baseurl.getBytes());
			byte data[]=mod.digest();
			for(byte b : data) {
				builder.append(String.format("%02x", b));
			}
			return new ResponseEntity<String>(builder.toString(),HttpStatus.OK);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
