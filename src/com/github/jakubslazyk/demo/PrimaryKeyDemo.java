package com.github.jakubslazyk.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.jakubslazyk.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
										
		//create session
		Session session = factory.getCurrentSession();
		try{
			
			//create 3 student objects
			System.out.println("Creating 3 student object...");
			Student tempStudent1 = new Student("Imie1","Nazwisko1","Imie1.Nazwisko1@gmail.com");
			Student tempStudent2 = new Student("Imie2","Nazwisko2","Imie1.Nazwisko2@gmail.com");
			Student tempStudent3 = new Student("Imie3","Nazwisko3","Imie1.Nazwisko3@gmail.com");
			//start a transaction
			session.beginTransaction();
			//save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			factory.close();
		}
	}

}
