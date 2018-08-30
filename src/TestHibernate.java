


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
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

import com.isoft.model.MyUser;

import sun.misc.BASE64Encoder;

public class TestHibernate {

	public static void main(String[] args) {
	
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		
		String hql = "select c,p from TCart c,TProduct p "
				+ "where c.TUserId='123' and c.TProductId=p.TProductId";
		Query query = session.createQuery(hql);
		List tempList = query.list();
		Iterator iterator = tempList.iterator();
		while(iterator.hasNext())
		{
			Object[] objArray = (Object[])iterator.next();
			TCart cart = (TCart)objArray[0];
			TProduct product = (TProduct)objArray[1];
			System.out.println("商品数量："+cart.getTNum()+"商品的名称:"+product.getTProductName());
		}
		
	}
	
	@Test
	public void testHql(){
		DbUtil dbUtil = DbUtil.getMyDbUtilInstance();
		Session session = dbUtil.getHwHibernateSession();
/*		String hql = "select u from TSysUser u";*/
	/*	String hql = "select u from TSysUser u where u.TUserName=?";*/
		
		/*String hql = "select u from TSysUser u where u.TUserName=:myName";*/
		String hql = "select u from TSysUser u where u.TUserName='jerry'";
		Query hQuery = session.createQuery(hql);
		//hQuery.setString("myName", "john");
		List<TSysUser> userList = hQuery.list();
		
	/*	Iterator iterator = userList.iterator();
		while(iterator.hasNext()){
			TSysUser user = (TSysUser)iterator.next();
			user.getTUserName();
		}*/
		
		for(TSysUser user : userList)
		{
			System.out.println(user.getTUserName()+"---"+user.getTGender());
		}
		session.close();
		
	}
	
	@Test
	public void test3(){
		DbUtil dbUtil = DbUtil.getMyDbUtilInstance();
		DbUtil dbUtil2 = DbUtil.getMyDbUtilInstance();
		if(dbUtil==dbUtil2){
			System.out.println("是同一个对象");
		}else{
			System.out.println("不是同。。。一个对象");
		}
	}
	// 删除用户
		@Test
		public void test5(){
			DbUtil dbUtil = DbUtil.getMyDbUtilInstance();
			
			Session session = dbUtil.getHwHibernateSession();
			Transaction tx = session.beginTransaction();
			TSysUser user = (TSysUser)session.get(TSysUser.class, "cc69b50c-54bf-4487-8ffb-938a361f65a0");
			try {
				session.delete(user);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	// 查询用户
	@Test
	public void test4(){
		DbUtil dbUtil = DbUtil.getMyDbUtilInstance();
		
		Session session = dbUtil.getHwHibernateSession();
		Transaction tx = session.beginTransaction();
		
		TSysUser user = (TSysUser)session.get(TSysUser.class, "cc69b50c-54bf-4487-8ffb-938a361f65a0");
		System.out.println(user.getTUserName());
		user.setTUserName("约翰....");
		try {
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 插入用户
	@Test
	public void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		// 单个实例  singleton 
		
		DbUtil dbUtil = DbUtil.getMyDbUtilInstance();
		
		Session session = dbUtil.getHwHibernateSession();
	
		Transaction tx = session.beginTransaction();
		TSysUser user1 = new TSysUser();
		user1.setTUserId(UUID.randomUUID().toString());
		user1.setTUserName("kite");
		user1.setTUserPwd(myMD5("134"));
		user1.setTGender("female");
		session.save(user1);
		tx.commit();
	}
	
	public String myMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
	     MessageDigest md5=MessageDigest.getInstance("MD5");
	     BASE64Encoder base64en = new BASE64Encoder();
	     String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	     return newstr;
	}
	
	@Test
	public void test1(){
		
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		
		TSysUser user = new TSysUser();
		user.setTUserId(UUID.randomUUID().toString());
		user.setTUserName("jerry");
		user.setTUserPwd("23432");
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}

}
