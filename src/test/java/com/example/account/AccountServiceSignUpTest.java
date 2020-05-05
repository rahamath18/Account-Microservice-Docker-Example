package com.example.account;

import org.springframework.web.client.RestTemplate;

public class AccountServiceSignUpTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:3001/account";

	private static void signUp() {
		System.out.println("Testing signUp----------");
		RestTemplate restTemplate = new RestTemplate();
		//Account account = new Account();
		//restTemplate.postForObject(REST_SERVICE_URI + "/sign-up/", account, AccountDto.class);
	}
	
	public static void main(String args[]) {
		signUp();
	}

}
