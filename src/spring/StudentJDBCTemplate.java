package spring;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate Class - executes SQL queries, updates, statements..... 
 * Can configure a single instance of a Jdbc Template and then safely inject this shared reference
 * to multiple DAO(Data Access Objects)
 * 
 * DataSource - Java class for establishing a connection to a database 
 * 
 * Common pratice when using the JDBC Template Class is to configure a DataSource in your Spring 
 * configuration file and then dependency-inject that shared DataSource bean into your DAO classes,
 * and the JdbcTemplate in created in the setter for the DataSource. 
 */


public class StudentJDBCTemplate implements StudentDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	@Override
	public void setDataSource(DataSource ds) {
	      this.dataSource = ds;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(String name, Integer age) {
		String SQL = "insert into Student (name,age) values (?,?)";
		jdbcTemplateObject.update(SQL, name, age);
		System.out.println("Create Record Name = " + name + " Age = " + age);
		
	}

	@Override
	public Student getStudent(Integer id) {
		String SQL = "select * from Student where id = ?";
		Student student = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new StudentMapper());
		return student;
	}

	@Override
	public List<Student> listStudents() {
		String SQL = "select * from Student";
		List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
		
		return students;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from Student where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		
	}

	@Override
	public void update(Integer id, Integer age) {
		String SQL = "update Student set age  = ? where id = ?";
		jdbcTemplateObject.update(SQL, age, id);
		System.out.println("Updated Record with ID = " + id);
		
	}

}
