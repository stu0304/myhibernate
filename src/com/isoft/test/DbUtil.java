package com.isoft.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DbUtil {

	private static DbUtil dbUtil;
	
	private DbUtil()
	{
		
	}
	
	public static DbUtil getUtilInstance() {
		if(dbUtil==null)
		{
			return new DbUtil();
		}
		
		return dbUtil;
	}
	
	public Session getHBMSession()
	{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		
		return session;
	}
}
