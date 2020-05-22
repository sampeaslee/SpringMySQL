package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	   public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	StudentJDBCTemplate studentJDBCTemplate = 
			(StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
	
	System.out.println("------Creeating Student Records------");
	  studentJDBCTemplate.create("Zara", 11);
      studentJDBCTemplate.create("Nuha", 2);
      studentJDBCTemplate.create("Ayan", 15);
      
	System.out.println("------List all students------");
		for(Student s: studentJDBCTemplate.listStudents()){
			System.out.println(s.getName());
		}
		
	   }
	   
	   
}
	   
