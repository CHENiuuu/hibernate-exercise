package app;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.member.pojo.Member;

public class TestApp {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		//From MEMBER
		Root<Member> root = criteriaQuery.from(Member.class);
		
		//where USERNAME = ? and PASSWORD = ?
		criteriaQuery.where(criteriaBuilder.and(
				criteriaBuilder.equal(root.get("username"), "admin"),
				criteriaBuilder.equal(root.get("password"), "P@ssw0rd")
				));
		
		//select USERNAME, NICKNAME
		criteriaQuery.multiselect(root.get("username"), root.get("nickname"));
		
		//order by ID
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		Member member = session.createQuery(criteriaQuery).uniqueResult();
		System.out.println(member.getNickname());
		//還沒理解======================================================================================
				
		//新增資料
//		Member member = new Member();
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		
//		TestApp app = new TestApp();
//		app.insert(member);
//		System.out.println(member.getId());
		
		
		//刪除資料
//		TestApp app = new TestApp();
//		System.out.println(app.deleteById(4));
		
		//修改資料
//		TestApp app = new TestApp();
//		Member member = new Member();
//		member.setId(3);
//		member.setPass(true);
//		member.setRoleId(1);
//		System.out.println(app.updateById(member));
		
		//查詢資料
//		TestApp app = new TestApp();
//		System.out.println(app.selectById(3).getNickname());
		
		//查詢all
//		TestApp app = new TestApp();
//		app.selectAll().forEach(member -> System.out.println(member.getNickname()));
		
		//or
		
//		for (Member member : app.selectAll()) {
//			System.out.println(member.getNickname());
//		}
		
	}
	
//session在Hibernate裡面代表一個對資料庫的連線
	
	
	//新增資料
//	public Integer insert(Member member) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			session.persist(member);
//			transaction.commit();
//			return member.getId();
//		}catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
//	}
	
//	//刪除資料
//	public int deleteById(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			//先抓到要刪除的資料
//			Transaction transaction = session.beginTransaction();
//			Member member = session.get(Member.class, id);
//			session.remove(member);
//			transaction.commit();
//			return member.getId();
//		}catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return -1;  //基本型態的值不能為null，改成任意數值
//		}
//	}

//	//修改資料
//	public int updateById(Member newMember) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Member oldMember = session.get(Member.class, newMember.getId());
//			
//			//若有傳pass，就將此pass設定給oldMember
//			final Boolean pass = newMember.getPass();
//			if(pass != null) {
//				oldMember.setPass(pass);
//			}
//			
//			//若有傳roleId，就將此roleId設定給oldMember
//			final Integer roleId = newMember.getRoleId();
//			if(roleId != null) {
//				oldMember.setRoleId(roleId);
//			}
//			
//			
//			transaction.commit();
//			return 1;
//		}catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return -1;  //基本型態的值不能為null，改成任意數值
//		}
//	}

//	//查詢資料
//	public Member selectById(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Member member = session.get(Member.class, id);
//			transaction.commit();
//			return member;
//		}catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;  //基本型態的值不能為null，改成任意數值
//		}
//	}

	
	//查詢
	public List<Member> selectAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
//			Query<Member> query = session.createQuery("FROM Member", Member.class);
			Query<Member> query = session.createQuery(
				"SELECT new web.member.pojo.member(username, nickname) FROM Member", Member.class);
			List<Member> list = query.getResultList();
			transaction.commit();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;  
		}
	}
	
}
