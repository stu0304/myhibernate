package com.isoft.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class TestMyHBM {

	@Test
	public void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		System.out.println(MD5Util.myMD5("123"));
	}
	
	@Test
	public void test1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		TSysUser user = new TSysUser();
		user.setTUserId(UUID.randomUUID().toString());
		user.setTUserName("wudi");
		user.setTUserPwd(MD5Util.myMD5("han"));
		
		session.save(user);
		tx.commit();
		
		session.close();
	
		
	}
}
