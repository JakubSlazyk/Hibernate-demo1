package com.github.jakubslazyk.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.jakubslazyk.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
								
		//create session
		Session session = factory.getCurrentSession();
		
		try{

			//start a transaction
			session.beginTransaction();
			
			//query students
			List <Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			//query students: lastName='Duck'
			theStudents=session.createQuery("from Student s where s.lastName='Duck'").getResultList();
			System.out.println("\n\nStudents who have last name of Duck");
			displayStudents(theStudents);
			//query students:lastName="Duck" or firstName="Jakub"
			theStudents=session.createQuery("from Student s where s.lastName='Duck' or s.firstName='Jakub'").getResultList();
			System.out.println("\n\nStudents who have last name of Duck or first Jakub");
			displayStudents(theStudents);
			///query students:email like %gmail.com
			theStudents=session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents who have email like %gmail.com");
			displayStudents(theStudents);
			
			//commit transaction
			
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent:theStudents)
			System.out.println(tempStudent);
	}

}
