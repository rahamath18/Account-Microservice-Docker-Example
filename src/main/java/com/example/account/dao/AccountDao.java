package com.example.account.dao;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.account.domain.Account;

@Repository("accountDao")
public class AccountDao {
	
	static final Logger logger = LoggerFactory.getLogger(AccountDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}
	
	public Query getNamedQuery(String queryName) {
		return sessionFactory.getCurrentSession().getNamedQuery(queryName);
	}

	public List<Account> getAllAccounts() {
		Session session = sessionFactory.getCurrentSession();
		List<Account>  accountList = session.createQuery("from Account").list();
		return accountList;
	}

	public Account getAccount(int id) {
		Session session = sessionFactory.getCurrentSession();
		Account account = (Account) session.get(Account.class, id);
		return account;
	}

	public Account addAccount(Account account) {
		logger.debug("\n\n");
		logger.debug("AccountDao|addAccount...");
		try {
			sessionFactory.getCurrentSession().save(account);
		} catch (Exception ex) {
			logger.debug("AccountDao|addAccount...Exception....");
			ex.printStackTrace();
		}
		return account;
	}

	public void updateAccount(Account account) {
		Session session = sessionFactory.getCurrentSession();
		Hibernate.initialize(account);
		session.update(account);
	}

	public void deleteAccount(int id) {
		Session session = sessionFactory.getCurrentSession();
		Account p = (Account) session.load(Account.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	} 

}
