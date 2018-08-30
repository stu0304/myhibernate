package com.isoft.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
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
		
		Session session = DbUtil.getUtilInstance().getHBMSession();
		Transaction tx = session.beginTransaction();
		TSysUser user = new TSysUser();
		user.setTUserId(UUID.randomUUID().toString());
		user.setTUserName("汤姆");
		user.setTUserPwd(MD5Util.myMD5("123"));
		
		session.save(user);
		tx.commit();
		
		session.close();
	
		
	}
	
	@Test
	public void find() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Session session = DbUtil.getUtilInstance().getHBMSession();
		TSysUser user = (TSysUser)session.get(TSysUser.class, "38b15aed-8efe-4ee3-8946-43d01cced4b6");
	    System.out.println(user.getTUserName());
	    session.close();
	}
	
	@Test
	public void find2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Session session = DbUtil.getUtilInstance().getHBMSession();
		TSysUser user = (TSysUser)session.load(TSysUser.class, "38b15aed-8efe-4ee3-8946-43d01cced4b6");
	    System.out.println(user.getTUserName());
	    session.close();
	}

	@Test
	public void find3() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Session session = DbUtil.getUtilInstance().getHBMSession();
		Transaction tx = session.beginTransaction();
		
		TSysUser user = new TSysUser();
		user.setTUserId("15faea3c-82d6-46b3-883f-a33369007ce6");
		session.delete(user);
		tx.commit();
		
	    session.close();
	}
	
	@Test
	public void manyToOne() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Session session = DbUtil.getUtilInstance().getHBMSession();
		session.beginTransaction();

		District district = new District();
		district.setDistrictName("华盛顿");
		district.setDistrictId(1);
		
		
		Street street1 = new Street();
		street1.setStreetName("华尔街");
		street1.setDistrict(district);
		street1.setStreetId(2);
		
		Street street2 = new Street();
		street2.setStreetName("河南路");
		street2.setDistrict(district);
		street2.setStreetId(3);
		
		Street street3 = new Street();
		street3.setStreetName("唐人街");
		street3.setDistrict(district);
		street3.setStreetId(4);
		
		session.save(district);
		session.save(street1);
		session.save(street2);
		session.save(street3);

		session.getTransaction().commit();
		session.close();

		
	}
	
	@Test
	public void test3()
	{
		
		Session session = DbUtil.getUtilInstance().getHBMSession();
		String hql = "select s from Street s";
		Query query = session.createQuery(hql);
		List<Street> streetList = query.list();
		for (Street street : streetList)
		{
		System.out.println(street.getStreetName()+"---");
		}
		
		session.close();

	}
	
	
	
}
