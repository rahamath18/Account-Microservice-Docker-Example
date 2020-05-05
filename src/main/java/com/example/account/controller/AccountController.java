package com.example.account.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.domain.Account;
import com.example.account.service.AccountService;
import com.example.account.util.RandomAlphaNumericGenerator;

@RestController
public class AccountController {
	
	static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) {
		logger.debug("\n\n");
		logger.debug("signUp..." + userName);
		
		if (StringUtils.isEmpty(StringUtils.trimToNull(userName)) || 
				StringUtils.isEmpty(StringUtils.trimToNull(password)))
			return new ResponseEntity<>("{\"message\":\"Your Sign-Up Details Are Empty\"}", HttpStatus.OK);
		
		if(StringUtils.trim(userName).length() < 8 || StringUtils.trim(userName).length() > 20)
			return new ResponseEntity<>("{\"message\":\"User Name Length Min 8 and Max 20\"}", HttpStatus.OK);
		
		if(StringUtils.trim(password).length() < 8 || StringUtils.trim(password).length() > 12)
			return new ResponseEntity<>("{\"message\":\"Password Length Min 8 and Max 12\"}", HttpStatus.OK);
		
		Account acc = accountService.retrieveAccountByUserName(userName);
		
		if(acc != null && acc.getUserName().equalsIgnoreCase(userName))
			return new ResponseEntity<>("{\"message\":\"User already exists\"}", HttpStatus.OK);
		
		accountService.addAccount(userName, password);
		
		return new ResponseEntity<>("{\"message\":\"Your Sign-Up is successful\"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public ResponseEntity<Object> signIn(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) {
		logger.debug("signIn..." + userName);
		
		if (StringUtils.isEmpty(StringUtils.trimToNull(userName)) || 
				StringUtils.isEmpty(StringUtils.trimToNull(password)))
			return new ResponseEntity<>("{\"message\":\"Your Sign-In Details Are Empty\"}", HttpStatus.OK);
		
		if(StringUtils.trim(userName).length() < 8 || StringUtils.trim(userName).length() > 20)
			return new ResponseEntity<>("{\"message\":\"User Name Length Min 8 and Max 20\"}", HttpStatus.OK);
		
		if(StringUtils.trim(password).length() < 8 || StringUtils.trim(password).length() > 12)
			return new ResponseEntity<>("{\"message\":\"Password Length Min 8 and Max 12\"}", HttpStatus.OK);
		
		Account account = accountService.retrieveAccount(userName, password);
		if(account == null || account.getId() == null || account.getId() == 0)
			return new ResponseEntity<>("{\"message\":\"Your Sign-In failed\"}", HttpStatus.OK);
		
		String generatedToken = "";
		try {
			generatedToken = RandomAlphaNumericGenerator.getRandomAlphaNumeric(12);
			accountService.updateAccount(account, generatedToken);
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("token-header", generatedToken);
		
		return new ResponseEntity<>("{\"message\":\"Your Sign-In is successful\",\"token\":\" " + generatedToken + "\"}", 
				responseHeaders,
				HttpStatus.OK);
	} 

}