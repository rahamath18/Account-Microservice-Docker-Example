package com.example.account.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.account.dao.AccountDao;
import com.example.account.domain.Account;

@Service("serviceService")
@Transactional
public class AccountService {
	
	static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	AccountDao accountDao;
	
	public List<Account> getAllAccounts() {
		return accountDao.getAllAccounts();
	}

	public Account getAccount(int id) {
		return accountDao.getAccount(id);
	}

	public void addAccount(String userName, String password) {
		logger.debug("\n\n");
		logger.debug("AccountService|addAccount...");
		Account account = new Account();
		account.setUserName(userName);
		account.setPassword(password);
		account.setStatus("ACTIVE");
		account.setVersion(1);
		account.setLogicallyDeleted("N");
		
		if (account.getId() == null)
			account.setCreatedBy(account.getUserName());
		else
			account.setUpdatedBy(account.getUserName());
			
		if (account.getId() == null)
			account.setCreatedDate(new Date());
		else
			account.setUpdatedDate(new Date());
		
		accountDao.addAccount(account);
		logger.debug("AccountService|addAccount|after add " + account);

	}

	public void updateAccount(Account account, String generatedToken) {
		Account acc = retrieveAccountById(account.getId());
		acc.setToken(generatedToken);
		acc.setVersion(account.getVersion() + 1);
		acc.setUpdatedDate(new Date());
		acc.setUpdatedBy(account.getUserName());
		accountDao.updateAccount(acc);
	}

	public void deleteAccount(int id) {
		accountDao.deleteAccount(id);
	}
	
	public Account retrieveAccountById(Integer id) {
		return (Account) accountDao.getNamedQuery("Account.retrieveAccountById")
				.setParameter("id", id)
				.uniqueResult();
	}
	
	public Account retrieveAccountByUserName(String userName) {
		return (Account) accountDao.getNamedQuery("Account.retrieveAccountByUserName")
				.setParameter("userName", userName)
				.uniqueResult();
	}
	
	public Account retrieveAccount(String userName, String password) {
		return (Account) accountDao.getNamedQuery("Account.retrieveAccount")
				.setParameter("userName", userName)
				.setParameter("password", password)
				.uniqueResult();
	}
	
}
