package app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;

public class TestApp {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
//		Dept dept = session.get(Dept.class, 30);
//		var emps = dept.getEmps();
//		for (var emp : emps) {
//			System.out.println(emp.getEname());
//		}
		
//		Emp emp = session.get(Emp.class, 7369);
//		Dept dept = emp.getDept();
//		System.out.println(dept.getDeptno());
//		System.out.println(dept.getDname());
		
		Emp emp = session.get(Emp.class, 7369);
		Dept dept = emp.getDept();
		List<Emp> emps = dept.getEmps();
		for (Emp tmp : emps) {
			System.out.println(tmp.getEname());
		}
	}
}
